package de.rocovomo.osgi.util;

import java.util.Dictionary;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

/**
 * provides utility methods for OSGi
 * 
 * @author voowoo <a href="mailto:vowe91@gmail.com">vowe91@gmail.com</a>
 * 
 */
public final class OsgiUtil {

	@SuppressWarnings("unchecked")
	public static <T, S extends T> ServiceRegistration<S> registerService(
			BundleContext bundleContext, Class<T> clazz, S service,
			Dictionary<String, Object> properties) {
		return (ServiceRegistration<S>) bundleContext.registerService(
				clazz.getName(), service, properties);
	}

	@SuppressWarnings("unchecked")
	public static <T, S extends T> ServiceRegistration<S> registerService(
			BundleContext bundleContext, Class<T> clazz, S service) {
		return (ServiceRegistration<S>) bundleContext.registerService(
				clazz.getName(), service, null);
	}
	
	
}
