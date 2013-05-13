package de.rocovomo.manager.kinect.osgi;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;

import de.rocovomo.jnect.kinect.api.GestureListenerConnector;
import de.rocovomo.osgi.api.AbstractServiceHandler;

public class GestureListenerConnectorHandler extends
		AbstractServiceHandler<GestureListenerConnector> {

	private final Logger Log = Logger
			.getLogger(GestureListenerConnectorHandler.class);

	private GestureListenerConnector service;
	private GestureListenerHandler handler;

	public GestureListenerConnectorHandler(BundleContext context)
			throws InvalidSyntaxException {
		super(context, GestureListenerConnector.class);
		checkInitialDetectedService();
	}

	private void checkInitialDetectedService() {
		if (!this.services.isEmpty()) {
			this.service = this.services.get(0);
			newGestureListenerHandler();
		}
	}

	private void newGestureListenerHandler() {
		this.handler = new GestureListenerHandler(this.bundleContext,
				this.service);
		this.bundleContext.addServiceListener(this.handler);
		Log.info("ServiceManager for GestureListeners started");
	}

	@Override
	public void serviceRegistered(GestureListenerConnector service) {
		if (this.service == null && this.handler == null) {
			this.service = service;
			this.handler = new GestureListenerHandler(this.bundleContext,
					this.service);
		}
	}

	@Override
	public void serviceUnregistered(GestureListenerConnector service) {
		if (this.service.equals(service)) {
			this.service = null;
			this.handler.stop();
			this.handler = null;
		}
	}

	@Override
	public void stopListener() {
		Log.info("ServiceManager for GestureListeners stopped");
	}

}
