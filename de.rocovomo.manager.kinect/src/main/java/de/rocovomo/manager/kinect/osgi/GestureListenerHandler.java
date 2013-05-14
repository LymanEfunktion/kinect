package de.rocovomo.manager.kinect.osgi;

import static de.rocovomo.jnect.kinect.api.KinectStatus.GESTURE_RECOGNITION_STOPPED;

import java.util.ArrayDeque;
import java.util.Deque;

import org.apache.log4j.Logger;
import org.jnect.gesture.GestureListener;
import org.osgi.framework.BundleContext;

import de.rocovomo.jnect.kinect.api.GestureListenerConnector;
import de.rocovomo.jnect.kinect.api.GestureRecognitionNotStartedException;
import de.rocovomo.jnect.kinect.api.KinectManagement;
import de.rocovomo.jnect.kinect.api.KinectStatus;
import de.rocovomo.jnect.kinect.api.KinectStatusObserver;
import de.rocovomo.osgi.api.AbstractServiceHandler;

public class GestureListenerHandler extends
		AbstractServiceHandler<GestureListener> implements KinectStatusObserver {

	private final Logger Log = Logger.getLogger(GestureListenerHandler.class);

	private GestureListenerConnector gestureListenerConnector;
	private KinectManagement kinectManagement;

	private boolean recognitionAvailable;
	private Deque<GestureListener> servicesToAdd = new ArrayDeque<>();
	private Deque<GestureListener> servicesToRemove = new ArrayDeque<>();

	public GestureListenerHandler(BundleContext bundleContext,
			GestureListenerConnector gestureListenerConnector,
			KinectManagement kinectManagement) {
		super(bundleContext, GestureListener.class);
		this.kinectManagement = kinectManagement;
		this.kinectManagement.addStatusObserver(this);
		this.gestureListenerConnector = gestureListenerConnector;
	}

	@Override
	public void serviceRegistered(GestureListener t) {
		if (recognitionAvailable) {
			connectGestureListener(t);
		} else {
			this.servicesToAdd.add(t);
		}
	}

	@Override
	public void serviceUnregistered(GestureListener t) {
		if (recognitionAvailable) {
			disconnectGestureListener(t);
		} else {
			this.servicesToRemove.add(t);
		}
	}

	@Override
	public void stopListener() {
		kinectManagement.removeStatusObserver(this);
	}

	@Override
	public void statusChanged(KinectStatus status) {
		if (status == GESTURE_RECOGNITION_STOPPED) {
			recognitionAvailable = false;
		}
		if (status == KinectStatus.GESTURE_RECOGNITION_STARTED) {
			recognitionAvailable = true;
			handleBufferedServices();
		}
	}

	private void handleBufferedServices() {
		while (!this.servicesToAdd.isEmpty()) {
			connectGestureListener(servicesToAdd.pop());
		}
		while (!this.servicesToRemove.isEmpty()) {
			disconnectGestureListener(servicesToRemove.pop());
		}
	}

	private void connectGestureListener(GestureListener listener) {
		try {
			this.gestureListenerConnector.connectGestureListener(listener);
		} catch (GestureRecognitionNotStartedException e) {
			Log.error("Cannot connect GestureListener", e);
		}
	}

	private void disconnectGestureListener(GestureListener listener) {
		try {
			this.gestureListenerConnector.disconnectGestureListener(listener);
		} catch (GestureRecognitionNotStartedException e) {
			Log.error("Cannot disconnect GestureListener", e);
		}
	}

}
