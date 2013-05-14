package de.rocovomo.manager.kinect.osgi;

import static de.rocovomo.jnect.kinect.api.KinectStatus.GESTURE_RECOGNITION_STOPPED;

import java.util.ArrayDeque;
import java.util.Deque;

import org.apache.log4j.Logger;
import org.jnect.gesture.Gesture;
import org.osgi.framework.BundleContext;

import de.rocovomo.jnect.gesture.api.GestureProvider;
import de.rocovomo.jnect.kinect.api.GestureConnector;
import de.rocovomo.jnect.kinect.api.GestureRecognitionNotStartedException;
import de.rocovomo.jnect.kinect.api.KinectManagement;
import de.rocovomo.jnect.kinect.api.KinectStatus;
import de.rocovomo.jnect.kinect.api.KinectStatusObserver;
import de.rocovomo.osgi.api.AbstractServiceHandler;

public class GestureHandler extends AbstractServiceHandler<GestureProvider>
		implements KinectStatusObserver {

	private final Logger Log = Logger.getLogger(GestureHandler.class);

	private GestureConnector gestureConnector;
	private KinectManagement kinectManagement;
	private boolean recognitionAvailable;
	private Deque<GestureProvider> servicesToAdd = new ArrayDeque<>();
	private Deque<GestureProvider> servicesToRemove = new ArrayDeque<>();

	public GestureHandler(BundleContext bundleContext,
			GestureConnector gestureConnector, KinectManagement kinectManagement) {
		super(bundleContext, GestureProvider.class);
		this.kinectManagement = kinectManagement;
		this.gestureConnector = gestureConnector;
	}

	@Override
	public void serviceRegistered(GestureProvider t) {
		if (recognitionAvailable) {
			connectGesture(t.getProvided());
		} else {
			this.servicesToAdd.add(t);
		}
	}

	@Override
	public void serviceUnregistered(GestureProvider t) {
		if (recognitionAvailable) {
			disconnectGesture(t.getProvided());
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
			connectGesture(servicesToAdd.pop().getProvided());
		}
		while (!this.servicesToRemove.isEmpty()) {
			disconnectGesture(servicesToRemove.pop().getProvided());
		}
	}

	private void connectGesture(Gesture gesture) {
		try {
			this.gestureConnector.connectGesture(gesture);
		} catch (GestureRecognitionNotStartedException e) {
			Log.error("Cannot connect gesture", e);
		}
	}

	private void disconnectGesture(Gesture gesture) {
		try {
			this.gestureConnector.disconnectGesture(gesture);
		} catch (GestureRecognitionNotStartedException e) {
			Log.error("Cannot disconnect gesture", e);
		}
	}
}
