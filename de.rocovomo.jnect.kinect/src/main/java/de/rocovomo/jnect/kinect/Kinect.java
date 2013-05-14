package de.rocovomo.jnect.kinect;

import static de.rocovomo.jnect.kinect.api.KinectStatus.GESTURE_RECOGNITION_STARTED;
import static de.rocovomo.jnect.kinect.api.KinectStatus.GESTURE_RECOGNITION_STOPPED;
import static de.rocovomo.jnect.kinect.api.KinectStatus.KINECT_STARTED;
import static de.rocovomo.jnect.kinect.api.KinectStatus.KINECT_STOPPED;
import static de.rocovomo.jnect.kinect.api.KinectStatus.SPEECH_RECOGNITION_STARTED;
import static de.rocovomo.jnect.kinect.api.KinectStatus.SPEECH_RECOGNITION_STOPPED;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jnect.core.KinectManager;
import org.jnect.core.SpeechListener;
import org.jnect.gesture.Gesture;
import org.jnect.gesture.GestureListener;
import org.jnect.gesture.GestureProxy;

import de.rocovomo.jnect.kinect.api.GestureConnector;
import de.rocovomo.jnect.kinect.api.GestureListenerConnector;
import de.rocovomo.jnect.kinect.api.GestureRecognitionNotStartedException;
import de.rocovomo.jnect.kinect.api.KinectManagement;
import de.rocovomo.jnect.kinect.api.KinectNotConnectedException;
import de.rocovomo.jnect.kinect.api.KinectNotStartedException;
import de.rocovomo.jnect.kinect.api.KinectStatus;
import de.rocovomo.jnect.kinect.api.KinectStatusObserver;
import de.rocovomo.jnect.kinect.api.SpeechListenerConnector;
import de.rocovomo.jnect.kinect.api.SpeechRecognitionNotStartedException;

public class Kinect implements GestureConnector, GestureListenerConnector,
		SpeechListenerConnector, KinectManagement {

	private final Logger Log = Logger.getLogger(Kinect.class);

	private final String error_kinect = "Can't establish a connection to the device. Check if a Kinect device is plugged into the computer";

	private List<KinectStatusObserver> observers = new ArrayList<>();

	private KinectManager kinect = KinectManager.INSTANCE;
	private GestureProxy gestureProxy;

	private boolean kinectConnected;
	private Exception kinectNotConnectedException;

	public boolean start() {
		try {
			// when a Kinect is connected this blocks
			// until it is started, if no Kinect device is physically installed
			// an System.ArgumentOutOfRangeException is thrown
			this.kinect.startKinect();
			this.kinectConnected = true;
			notifyObservers(KINECT_STARTED);
			Log.debug("Kinect started");
		} catch (Exception e) {
			// should it still be logged?
			Log.error(error_kinect, e);

			this.kinectConnected = false;
			this.kinectNotConnectedException = e;
			return isRunning();
		}
		this.kinect = KinectManager.INSTANCE;
		this.gestureProxy = GestureProxy.INSTANCE;

		startSpeechRecognition();
		startGestureRecognition();

		return isRunning();
	}

	private void startGestureRecognition() {
		this.kinect.startSkeletonTracking();
		notifyObservers(GESTURE_RECOGNITION_STARTED);
		Log.debug("Gesture Recognition started");
	}

	private void startSpeechRecognition() {
		this.kinect.startSpeechRecognition();
		notifyObservers(SPEECH_RECOGNITION_STARTED);
		Log.debug("Speech Recognition started");
	}

	public boolean stop() {
		if (isGestureRecognitionStarted()) {
			this.kinect.stopSkeletonTracking();
			notifyObservers(GESTURE_RECOGNITION_STOPPED);
			Log.debug("Gesture Recognition stopped");
		}
		if (isSpeechRecognitionStarted()) {
			this.kinect.stopSpeechRecognition();
			notifyObservers(SPEECH_RECOGNITION_STOPPED);
			Log.debug("Speech Recognition stopped");
		}
		if (isRunning()) {
			this.kinect.stopKinect();
			notifyObservers(KINECT_STOPPED);
			Log.debug("Kinect stopped");
		}
		return isRunning();
	}

	@Override
	public boolean isRunning() {
		return this.kinect.isStarted();
	}

	public boolean isSpeechRecognitionStarted() {
		return this.kinect.isSpeechRecognitionStarted()
				&& this.kinect.isStarted();
	}

	@Override
	public boolean isGestureRecognitionStarted() {
		return this.kinect.isSkeletonTrackingStarted()
				&& this.kinect.isStarted();
	}

	@Override
	public void connectGestureListener(GestureListener gestureListener)
			throws GestureRecognitionNotStartedException {
		checkGestureRecognition();
		this.gestureProxy.addGestureListener(gestureListener);
		Log.debug("GestureListener: " + gestureListener + " added");
	}

	@Override
	public void disconnectGestureListener(GestureListener gestureListener)
			throws GestureRecognitionNotStartedException {
		checkGestureRecognition();
		this.gestureProxy.removeGestureListener(gestureListener);
		Log.debug("GestureListener: " + gestureListener + " removed");
	}

	/*
	 * TODO: Speechrecognition needs to be stopped to add a Speechlistener at
	 * runtime
	 */
	@Override
	public void connectSpeechListener(SpeechListener speechListener)
			throws SpeechRecognitionNotStartedException {
		checkSpeechRecognition();
		this.kinect.addSpeechListener(speechListener);
		Log.debug("SpeechListener: " + speechListener + " added");
	}

	/*
	 * TODO: Speechrecognition needs to be stopped to remove a Speechlistener at
	 * runtime
	 */
	@Override
	public void disconnectSpeechListener(SpeechListener speechListener)
			throws SpeechRecognitionNotStartedException {
		checkSpeechRecognition();
		this.kinect.removeSpeechListener(speechListener);
		Log.debug("SpeechListener: " + speechListener + " added");
	}

	@Override
	public void connectGesture(Gesture gesture)
			throws GestureRecognitionNotStartedException {
		checkGestureRecognition();
		this.gestureProxy.addGestureDetector(gesture);
		Log.debug("Gesture: " + gesture + " added");
	}

	@Override
	public void disconnectGesture(Gesture gesture)
			throws GestureRecognitionNotStartedException {
		checkGestureRecognition();
		this.gestureProxy.removeGestureDetector(gesture);
		Log.debug("Gesture: " + gesture + " removed");
	}

	private void notifyObservers(KinectStatus status) {
		for (KinectStatusObserver observer : observers) {
			observer.statusChanged(status);
		}
	}

	private void checkGestureRecognition()
			throws GestureRecognitionNotStartedException {
		try {
			checkKinectStarted();
		} catch (KinectNotStartedException e) {
			throw new GestureRecognitionNotStartedException(e);
		}
		if (!isGestureRecognitionStarted()) {
			throw new GestureRecognitionNotStartedException();
		}
	}

	private void checkSpeechRecognition()
			throws SpeechRecognitionNotStartedException {
		try {
			checkKinectConnected();
		} catch (KinectNotConnectedException e) {
			throw new SpeechRecognitionNotStartedException(e);
		}
		try {
			checkKinectStarted();
		} catch (KinectNotStartedException e) {
			throw new SpeechRecognitionNotStartedException(e);
		}
		if (!isSpeechRecognitionStarted()) {
			throw new SpeechRecognitionNotStartedException();
		}
	}

	private void checkKinectStarted() throws KinectNotStartedException {
		try {
			checkKinectConnected();
		} catch (KinectNotConnectedException e) {
			throw new KinectNotStartedException(e);
		}
		if (!isRunning()) {
			throw new KinectNotStartedException();
		}
	}

	private void checkKinectConnected() throws KinectNotConnectedException {
		if (this.kinectConnected) {
			throw new KinectNotConnectedException(
					this.kinectNotConnectedException);
		}
	}

	@Override
	public void addStatusObserver(KinectStatusObserver observer) {
		this.observers.add(observer);
	}

	@Override
	public void removeStatusObserver(KinectStatusObserver observer) {
		this.observers.remove(observer);
	}

}
