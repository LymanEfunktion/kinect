package de.rocovomo.manager.kinect.osgi;

import org.jnect.gesture.GestureListener;
import org.osgi.framework.BundleContext;

import de.rocovomo.jnect.kinect.api.GestureListenerConnector;
import de.rocovomo.osgi.api.AbstractServiceHandler;

public class GestureListenerHandler extends
		AbstractServiceHandler<GestureListener> {

	private GestureListenerConnector gestureListenerConnector;

	public GestureListenerHandler(BundleContext bundleContext,
			GestureListenerConnector gestureListenerConnector) {
		super(bundleContext, GestureListener.class);
		this.gestureListenerConnector = gestureListenerConnector;
	}

	@Override
	public void serviceRegistered(GestureListener t) {
		this.gestureListenerConnector.connectGestureListener(t);
	}

	@Override
	public void serviceUnregistered(GestureListener t) {
		this.gestureListenerConnector.disconnectGestureListener(t);
	}

	@Override
	public void stopListener() {
		// TODO Auto-generated method stub
		
	}

}
