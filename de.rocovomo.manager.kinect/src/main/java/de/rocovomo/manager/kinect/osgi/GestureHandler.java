package de.rocovomo.manager.kinect.osgi;

import org.osgi.framework.BundleContext;

import de.rocovomo.jnect.gesture.api.GestureProvider;
import de.rocovomo.jnect.kinect.api.GestureConnector;
import de.rocovomo.osgi.api.AbstractServiceHandler;

public class GestureHandler extends AbstractServiceHandler<GestureProvider>{


	private GestureConnector gestureConnector;

	public GestureHandler(BundleContext bundleContext, GestureConnector gestureConnector) {
		super(bundleContext, GestureProvider.class);
		this.gestureConnector = gestureConnector;
	}

	@Override
	public void serviceRegistered(GestureProvider t) {
		this.gestureConnector.connectGesture(t.getProvided());
	}

	@Override
	public void serviceUnregistered(GestureProvider t) {
		this.gestureConnector.disconnectGesture(t.getProvided());
	}
	
	@Override
	public void stopListener() {
		// Do nothing so far
	}
}
