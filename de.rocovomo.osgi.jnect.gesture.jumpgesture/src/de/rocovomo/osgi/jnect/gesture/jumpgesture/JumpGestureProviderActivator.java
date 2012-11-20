package de.rocovomo.osgi.jnect.gesture.jumpgesture;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import de.rocovomo.osgi.jnect.gesture.spi.GestureProvider;

public class JumpGestureProviderActivator implements BundleActivator {

//	private static BundleContext context;

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
		JumpGestureProvider provider = new JumpGestureProvider();

		serviceRegistration = bundleContext.registerService(
				GestureProvider.class.getName(), provider,
				provider.getGestureProperties());
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
