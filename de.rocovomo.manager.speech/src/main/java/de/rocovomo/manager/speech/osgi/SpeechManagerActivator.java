package de.rocovomo.manager.speech.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import de.rocovomo.manager.speech.SpeechFactory;
import de.rocovomo.speech.api.SpeechFactoryService;

public class SpeechManagerActivator implements BundleActivator {

	private SpeechFactory speechFactory;
	private BundleContext bundleContext;
	private ServiceRegistration<SpeechFactoryService> registeredFactoryService;

	@Override
	public void start(BundleContext context) throws Exception {
		this.speechFactory = new SpeechFactory();
		this.bundleContext = context;
		this.registeredFactoryService = this.bundleContext.registerService(
				SpeechFactoryService.class, this.speechFactory, null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		this.registeredFactoryService.unregister();
	}

}
