package de.rocovomo.osgi.util;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import de.rocovomo.osgi.api.GenericProvider;

/**
 * helper class for OSGi specific logging
 * 
 * @author voowoo <a href="mailto:vowe91@gmail.com">vowe91@gmail.com</a>
 * 
 */
public class OsgiLogging {

	private static Logger logger = Logger.getLogger(OsgiLogging.class);

	/**
	 * Logs:
	 * <p>
	 * "Starting <i>Bundle</i>(symbolic bundle name)"
	 * 
	 * @param bundleContext
	 *            OSGi bundle context
	 */
	public static void logBundleStartup(BundleContext bundleContext) {
		logger.info("Starting " + bundleContext.getBundle().getSymbolicName());
	};

	/**
	 * Logs:
	 * <p>
	 * "Started <i>Bundle</i>(symbolic bundle name)"
	 * 
	 * @param bundleContext
	 *            OSGi bundle context
	 */
	public static void logBundleStarted(BundleContext bundleContext) {
		logger.info("Started " + bundleContext.getBundle().getSymbolicName());
	}

	/**
	 * Logs:
	 * <p>
	 * "Stopping <i>Bundle</i>(symbolic bundle name)"
	 * 
	 * @param bundleContext
	 *            OSGi bundle context
	 */
	public static void logBundleShutdown(BundleContext bundleContext) {
		logger.info("Stopping " + bundleContext.getBundle().getSymbolicName());
	}

	/**
	 * Logs:
	 * <p>
	 * "Stopped <i>Bundle</i>(symbolic bundle name)"
	 * 
	 * @param bundleContext
	 *            OSGi bundle context
	 */
	public static void logBundleStopped(BundleContext bundleContext) {
		logger.info("Stopped " + bundleContext.getBundle().getSymbolicName());
	}

	public static void logServiceRegistered(BundleContext bundleContext,
			ServiceRegistration<?> serviceRegistration) {
		GenericProvider<?> provider = (GenericProvider<?>) bundleContext
				.getService(serviceRegistration.getReference());
		logger.info("Registered " + provider.getProvided() + ":"
				+ provider.getType());
	}

	/**
	 * Logs:
	 * <p>
	 * "Unregistering <i>Service:ServiceType</i>"
	 * 
	 * @param bundleContext
	 *            OSGi bundle context
	 */
	public static void logServiceUnregistering(BundleContext bundleContext,
			ServiceRegistration<?> serviceRegistration) {
		GenericProvider<?> provider = (GenericProvider<?>) bundleContext
				.getService(serviceRegistration.getReference());
		logger.info("Unregistering " + provider.getProvided() + ":"
				+ provider.getType());
	}

	/**
	 * Logs:
	 * <p>
	 * "Unregistered <i>Service:ServiceType</i>"
	 * 
	 * @param bundleContext
	 *            OSGi bundle context
	 */
	public static void logServiceUnregistered(BundleContext bundleContext,
			ServiceRegistration<?> serviceRegistration) {
		GenericProvider<?> provider = (GenericProvider<?>) bundleContext
				.getService(serviceRegistration.getReference());
		logger.info("Unregistered " + provider.getProvided() + ":"
				+ provider.getType());
	}
}
