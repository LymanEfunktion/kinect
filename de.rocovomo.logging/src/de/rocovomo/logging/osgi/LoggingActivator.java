package de.rocovomo.logging.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleListener;
import org.osgi.framework.ServiceListener;

public class LoggingActivator implements BundleActivator {

	private BundleContext context;
	private BundleListener bundleListener = new BundleListenerImpl();
	private ServiceListener serviceListener;

	@Override
	public void start(BundleContext context) throws Exception {
		this.context = context;
		this.serviceListener = new ServiceListenerImpl(context);
		this.context.addBundleListener(this.bundleListener);
		this.context.addServiceListener(this.serviceListener);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		this.context.removeBundleListener(this.bundleListener);
		this.context.removeServiceListener(this.serviceListener);
	}

}
