package de.rocovomo.jnect.gesture.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import de.rocovomo.jnect.gesture.provider.JumpGestureProvider;
import de.rocovomo.jnect.gesture.spi.GestureProvider;

// TODO javadoc missing, logging missing
public class JumpGestureActivator implements BundleActivator {

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
