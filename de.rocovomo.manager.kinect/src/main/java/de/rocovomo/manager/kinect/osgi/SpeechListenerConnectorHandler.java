package de.rocovomo.manager.kinect.osgi;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;

import de.rocovomo.jnect.kinect.api.SpeechListenerConnector;
import de.rocovomo.osgi.api.AbstractServiceHandler;

public class SpeechListenerConnectorHandler extends
		AbstractServiceHandler<SpeechListenerConnector>{

	private SpeechListenerConnector service;
	private SpeechListenerHandler handler;

	public SpeechListenerConnectorHandler(BundleContext context)
			throws InvalidSyntaxException {
		super(context, SpeechListenerConnector.class);
		checkInitialDetectedService();
	}

	private void checkInitialDetectedService() {
		if (!this.services.isEmpty()) {
			this.service = this.services.get(0);
			this.handler = new SpeechListenerHandler(this.bundleContext, this.service);
		}
	}

	@Override
	public void serviceRegistered(SpeechListenerConnector service) {
		if (this.service == null && this.handler == null) {
			this.service = service;
			this.handler = new SpeechListenerHandler(this.bundleContext, this.service);
		}
	}

	@Override
	public void serviceUnregistered(SpeechListenerConnector service) {
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
