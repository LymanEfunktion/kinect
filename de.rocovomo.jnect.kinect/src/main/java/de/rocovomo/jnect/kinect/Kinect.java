package de.rocovomo.jnect.kinect;

import org.jnect.core.KinectManager;
import org.jnect.core.SpeechListener;
import org.jnect.gesture.Gesture;
import org.jnect.gesture.GestureListener;
import org.jnect.gesture.GestureProxy;

import de.rocovomo.jnect.kinect.api.GestureConnector;
import de.rocovomo.jnect.kinect.api.GestureListenerConnector;
import de.rocovomo.jnect.kinect.api.SpeechListenerConnector;

public class Kinect implements GestureConnector, GestureListenerConnector, SpeechListenerConnector{

	private KinectManager kinect = KinectManager.INSTANCE;
	private GestureProxy gestureProxy;

	public boolean start() {
		// TODO: log4j Logging
		try {
			this.kinect = KinectManager.INSTANCE;
			this.kinect.startKinect(); // when a Kinect is connected this blocks
										// until it is started
			this.gestureProxy = GestureProxy.INSTANCE;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void stop() {
		this.kinect.stopSkeletonTracking();
		this.kinect.stopSpeechRecognition();
		this.kinect.stopKinect();
	}

	@Override
	public void connectGesture(Gesture gesture) {
		this.gestureProxy.addGestureDetector(gesture);
	}

	@Override
	public void disconnectGesture(Gesture gesture) {
		this.gestureProxy.removeGestureDetector(gesture);
	}

	@Override
	public void connectGestureListener(GestureListener gestureListener) {
		this.gestureProxy.addGestureListener(gestureListener);
	}

	@Override
	public void disconnectGestureListener(GestureListener gestureListener) {
		this.gestureProxy.removeGestureListener(gestureListener);
	}

	/*TODO: Speechrecognition needs to be stopped to add a Speechlistener at runtime*/
	@Override
	public void connectSpeechListener(SpeechListener speechListener) {
		this.kinect.addSpeechListener(speechListener);
	}

	/*TODO: Speechrecognition needs to be stopped to remove a Speechlistener at runtime*/
	@Override
	public void disconnectSpeechListener(SpeechListener speechListener) {
		this.kinect.removeSpeechListener(speechListener);
	}

}
