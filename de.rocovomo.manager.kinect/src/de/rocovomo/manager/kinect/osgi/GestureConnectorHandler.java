package de.rocovomo.manager.kinect.osgi;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;

import de.rocovomo.jnect.kinect.api.GestureConnector;
import de.rocovomo.osgi.api.AbstractServiceHandler;

public class GestureConnectorHandler extends
		AbstractServiceHandler<GestureConnector> {

	private GestureConnector service;
	private GestureHandler handler;

	public GestureConnectorHandler(BundleContext context)
			throws InvalidSyntaxException {
		super(context, GestureConnector.class);
		checkInitialDetectedService();
	}

	private void checkInitialDetectedService() {
		if (!this.services.isEmpty()) {
			this.service = this.services.get(0);
			this.handler = new GestureHandler(this.bundleContext,
					this.service);
		}
	}

	@Override
	public void serviceRegistered(GestureConnector service) {
		if (this.service == null && this.handler == null) {
			this.service = service;
			this.handler = new GestureHandler(this.bundleContext,
					service);
		}
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
		// TODO Auto-generated method stub

	}

}
