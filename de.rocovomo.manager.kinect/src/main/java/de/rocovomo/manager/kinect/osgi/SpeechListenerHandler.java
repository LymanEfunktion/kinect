package de.rocovomo.manager.kinect.osgi;

import static de.rocovomo.jnect.kinect.api.KinectStatus.SPEECH_RECOGNITION_STOPPED;

import java.util.ArrayDeque;
import java.util.Deque;

import org.apache.log4j.Logger;
import org.jnect.core.SpeechListener;
import org.osgi.framework.BundleContext;

import de.rocovomo.jnect.kinect.api.KinectManagement;
import de.rocovomo.jnect.kinect.api.KinectStatus;
import de.rocovomo.jnect.kinect.api.KinectStatusObserver;
import de.rocovomo.jnect.kinect.api.SpeechListenerConnector;
import de.rocovomo.jnect.kinect.api.SpeechRecognitionNotStartedException;
import de.rocovomo.osgi.api.AbstractServiceHandler;

public class SpeechListenerHandler extends
		AbstractServiceHandler<SpeechListener> implements KinectStatusObserver {

	private final Logger Log = Logger.getLogger(SpeechListenerHandler.class);

	private SpeechListenerConnector speechListenerConnector;
	private KinectManagement kinectManagement;

	private boolean recognitionAvailable;
	private Deque<SpeechListener> servicesToAdd = new ArrayDeque<>();
	private Deque<SpeechListener> servicesToRemove = new ArrayDeque<>();

	public SpeechListenerHandler(BundleContext bundleContext,
			SpeechListenerConnector speechListenerConnector,
			KinectManagement kinectManagement) {
		super(bundleContext, SpeechListener.class);
		this.kinectManagement = kinectManagement;
		this.kinectManagement.addStatusObserver(this);
		this.speechListenerConnector = speechListenerConnector;
	}

	@Override
	public void serviceRegistered(SpeechListener t) {
		if (recognitionAvailable) {
			connectSpeechListener(t);
		} else {
			this.servicesToAdd.add(t);
		}
	}

	@Override
	public void serviceUnregistered(SpeechListener t) {
		if (recognitionAvailable) {
			disconnectSpeechListener(t);
		} else {
			this.servicesToRemove.add(t);
		}
	}

	@Override
	public void stopListener() {
		this.kinectManagement.removeStatusObserver(this);
	}

	@Override
	public void statusChanged(KinectStatus status) {
		if (status == SPEECH_RECOGNITION_STOPPED) {
			recognitionAvailable = false;
		}
		if (status == KinectStatus.SPEECH_RECOGNITION_STARTED) {
			recognitionAvailable = true;
			handleBufferedServices();
		}
	}

	private void handleBufferedServices() {
		while (!this.servicesToAdd.isEmpty()) {
			connectSpeechListener(servicesToAdd.pop());
		}
		while (!this.servicesToRemove.isEmpty()) {
			disconnectSpeechListener(servicesToRemove.pop());
		}
	}

	private void connectSpeechListener(SpeechListener t) {
		try {
			this.speechListenerConnector.connectSpeechListener(t);
		} catch (SpeechRecognitionNotStartedException e) {
			Log.error("Cannot connect SpeechListener", e);
		}
	}

	private void disconnectSpeechListener(SpeechListener t) {
		try {
			this.speechListenerConnector.disconnectSpeechListener(t);
		} catch (SpeechRecognitionNotStartedException e) {
			Log.error("Cannot disconnect SpeechListener", e);
		}
	}

}
