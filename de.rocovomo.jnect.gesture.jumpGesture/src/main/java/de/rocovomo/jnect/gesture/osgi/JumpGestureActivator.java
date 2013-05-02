package de.rocovomo.jnect.gesture.osgi;

import static de.rocovomo.util.Dictionaries.toDictionary;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import de.rocovomo.jnect.gesture.api.GestureProvider;
import de.rocovomo.jnect.gesture.api.RoCoVoMoGesture;
import de.rocovomo.jnect.gesture.provider.JumpGestureProvider;
import de.rocovomo.osgi.util.OsgiUtil;

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

		OsgiUtil.registerService(bundleContext, GestureProvider.class,
				this.provider, toDictionary(this.provider.getProperties()));

		logger.info("Registered "
				+ this.provider.getProvided()
				+ ":"
				+ this.provider.getProperties()
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
				+ this.provider.getProvided()
				+ ":"
				+ this.provider.getProperties()
						.get(RoCoVoMoGesture.TYPE));
		serviceRegistration.unregister();
		logger.info("Unregistered "
				+ this.provider.getProvided()
				+ ":"
				+ this.provider.getProperties()
						.get(RoCoVoMoGesture.TYPE));
		logger.info("Stopped " + this.context.getBundle().getSymbolicName());
	}
}
