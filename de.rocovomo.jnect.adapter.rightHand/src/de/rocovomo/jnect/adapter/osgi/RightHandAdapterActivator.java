package de.rocovomo.jnect.adapter.osgi;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import de.rocovomo.jnect.adapter.api.AdapterProvider;
import de.rocovomo.jnect.adapter.api.RoCoVoMoAdapter;
import de.rocovomo.jnect.adapter.provider.RightHandAdapterProvider;
import de.rocovomo.osgi.util.OsgiUtil;

public class RightHandAdapterActivator implements BundleActivator {

	private static Logger logger = Logger
			.getLogger(RightHandAdapterActivator.class);

	private BundleContext context;

	@SuppressWarnings("rawtypes")
	private ServiceRegistration serviceRegistration;

	private RightHandAdapterProvider provider;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		this.context = bundleContext;
		logger.info("Starting " + this.context.getBundle().getSymbolicName());

		this.provider = new RightHandAdapterProvider();

		OsgiUtil.registerService(bundleContext, AdapterProvider.class,
				this.provider, provider.getProperties());
		logger.info("Registered " + this.provider.getProvided() + ":"
				+ this.provider.getProperties().get(RoCoVoMoAdapter.TYPE));

		logger.info("Started " + this.context.getBundle().getSymbolicName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		logger.info("Stopping " + this.context.getBundle().getSymbolicName());
		logger.info("Unregistering " + this.provider.getProvided() + ":"
				+ this.provider.getProperties().get(RoCoVoMoAdapter.TYPE));
		serviceRegistration.unregister();
		logger.info("Unregistered " + this.provider.getProvided() + ":"
				+ this.provider.getProperties().get(RoCoVoMoAdapter.TYPE));
		logger.info("Stopped " + this.context.getBundle().getSymbolicName());
	}
}
