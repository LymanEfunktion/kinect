package de.rocovomo.osgi.jnect.adapter.lefthand;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import de.rocovomo.osgi.jnect.adapter.spi.AdapterProvider;

public class LeftHandAdapterProviderActivator implements BundleActivator {

	private static BundleContext context;

	@SuppressWarnings("rawtypes")
	private ServiceRegistration serviceRegistration;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		LeftHandAdapterProvider provider = new LeftHandAdapterProvider();

		serviceRegistration = bundleContext.registerService(
				AdapterProvider.class.getName(), provider,
				provider.getAdapterProperties());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		serviceRegistration.unregister();
	}

}
