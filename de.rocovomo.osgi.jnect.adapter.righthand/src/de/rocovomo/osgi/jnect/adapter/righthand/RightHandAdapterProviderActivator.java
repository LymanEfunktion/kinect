package de.rocovomo.osgi.jnect.adapter.righthand;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import de.rocovomo.osgi.jnect.adapter.spi.AdapterProvider;

public class RightHandAdapterProviderActivator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	@SuppressWarnings("rawtypes")
	private ServiceRegistration serviceRegistration;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		RightHandAdapterProvider provider = new RightHandAdapterProvider(null);

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
