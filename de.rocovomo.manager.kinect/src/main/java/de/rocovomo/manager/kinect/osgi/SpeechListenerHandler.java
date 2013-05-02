package de.rocovomo.manager.kinect.osgi;

import org.jnect.core.SpeechListener;
import org.osgi.framework.BundleContext;

import de.rocovomo.jnect.kinect.api.SpeechListenerConnector;
import de.rocovomo.osgi.api.AbstractServiceHandler;

public class SpeechListenerHandler extends
		AbstractServiceHandler<SpeechListener> {

	private SpeechListenerConnector speechListenerConnector;

	public SpeechListenerHandler(BundleContext bundleContext,
			SpeechListenerConnector speechListenerConnector) {
		super(bundleContext, SpeechListener.class);
		this.speechListenerConnector = speechListenerConnector;
	}

	@Override
	public void serviceRegistered(SpeechListener t) {
		this.speechListenerConnector.connectSpeechListener(t);
	}

	@Override
	public void serviceUnregistered(SpeechListener t) {
		this.speechListenerConnector.disconnectSpeechListener(t);
	}

	@Override
	public void stopListener() {
		// TODO Auto-generated method stub
		
	}

}
