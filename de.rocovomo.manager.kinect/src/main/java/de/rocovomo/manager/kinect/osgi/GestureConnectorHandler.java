package de.rocovomo.manager.kinect.osgi;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;

import de.rocovomo.jnect.kinect.api.GestureConnector;
import de.rocovomo.jnect.kinect.api.KinectManagement;
import de.rocovomo.osgi.api.AbstractServiceHandler;

public class GestureConnectorHandler extends
		AbstractServiceHandler<GestureConnector> {

	private final Logger Log = Logger.getLogger(GestureConnectorHandler.class);

	private GestureConnector service;
	private GestureHandler handler;

	private KinectManagement kinectManagement;

	public GestureConnectorHandler(BundleContext context,
			KinectManagement kinectManagement) throws InvalidSyntaxException {
		super(context, GestureConnector.class);
		this.kinectManagement = kinectManagement;
		checkInitialDetectedService();
	}

	private void checkInitialDetectedService() {
		if (!this.services.isEmpty()) {
			this.service = this.services.get(0);
			newGestureHandler();
		}
	}

	@Override
	public void serviceRegistered(GestureConnector service) {
		if (this.service == null && this.handler == null) {
			this.service = service;
			newGestureHandler();
		}
	}

	private void newGestureHandler() {
		this.handler = new GestureHandler(this.bundleContext, service, this.kinectManagement);
		this.bundleContext.addServiceListener(this.handler);
		Log.info("ServiceManager for Gestures started");
	}

	@Override
	public void serviceUnregistered(GestureConnector service) {
		if (this.service.equals(service)) {
			this.service = null;
			this.handler.stop();
			this.handler = null;
		}
	}

	@Override
	public void stopListener() {
		Log.info("ServiceManager for Gestures stopped");
	}

}
