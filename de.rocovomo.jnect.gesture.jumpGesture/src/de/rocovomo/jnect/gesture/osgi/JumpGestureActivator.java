package de.rocovomo.jnect.gesture.osgi;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import de.rocovomo.jnect.gesture.RoCoVoMoGesture;
import de.rocovomo.jnect.gesture.provider.JumpGestureProvider;
import de.rocovomo.jnect.gesture.spi.GestureProvider;

// TODO javadoc missing, logging missing
public class JumpGestureActivator implements BundleActivator {

	private static Logger logger = Logger.getLogger(JumpGestureActivator.class);

	private BundleContext context;

	@SuppressWarnings("rawtypes")
	private ServiceRegistration serviceRegistration;

	private JumpGestureProvider provider;

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
		this.provider = new JumpGestureProvider();

		serviceRegistration = bundleContext.registerService(
				GestureProvider.class.getName(), this.provider,
				this.provider.getGestureProperties());
		logger.info("Registered "
				+ this.provider.getGesture()
				+ ":"
				+ this.provider.getGestureProperties()
						.get(RoCoVoMoGesture.TYPE));
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
		logger.info("Unregistering "
				+ this.provider.getGesture()
				+ ":"
				+ this.provider.getGestureProperties()
						.get(RoCoVoMoGesture.TYPE));
		serviceRegistration.unregister();
		logger.info("Unregistered "
				+ this.provider.getGesture()
				+ ":"
				+ this.provider.getGestureProperties()
						.get(RoCoVoMoGesture.TYPE));
		logger.info("Stopped " + this.context.getBundle().getSymbolicName());
	}
}
