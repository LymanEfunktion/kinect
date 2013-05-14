package de.rocovomo.osgi.util;

import java.util.Collection;
import java.util.Dictionary;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

/**
 * provides utility methods for OSGi
 * 
 * @author voowoo <a href="mailto:vowe91@gmail.com">vowe91@gmail.com</a>
 * 
 */
public final class OsgiUtil {

	/**
	 * registers a service with the OSGi Framework 
	 * 
	 * @param bundleContext
	 *            {@link BundleContext}
	 * @param clazz
	 *            interface <code>T</code> of the service
	 * @param service
	 *            service object of type <code>S</code>
	 * @param properties
	 *            {@link Dicitonary} with meta information
	 * @return a {@link ServiceRegistration} object for service <code>S</code>
	 *         with interface <code>T</code>
	 */
	@SuppressWarnings("unchecked")
	public static <T, S extends T> ServiceRegistration<S> registerService(
			BundleContext bundleContext, Class<T> clazz, S service,
			Dictionary<String, Object> properties) {
		return (ServiceRegistration<S>) bundleContext.registerService(
				clazz.getName(), service, properties);
	}

	/**
	 * registers a service with the OSGi Framework
	 * 
	 * @param bundleContext
	 *            {@link BundleContext}
	 * @param clazz
	 *            interface <code>T</code> of the service
	 * @param service
	 *            service object of type <code>S</code>
	 * @return a {@link ServiceRegistration} object for service <code>S</code>
	 *         with interface <code>T</code>
	 */
	@SuppressWarnings("unchecked")
	public static <T, S extends T> ServiceRegistration<S> registerService(
			BundleContext bundleContext, Class<T> clazz, S service) {
		return (ServiceRegistration<S>) bundleContext.registerService(
				clazz.getName(), service, null);
	}

	/**
	 * returns a {@link Collection} of {@link ServiceReference} objects with
	 * interface <code>T</code>
	 * 
	 * @param bundleContext
	 *            {@link BundleContext}
	 * @param clazz
	 *            interface <code>T</code> of the service
	 * @param filter
	 *            for filter syntax see {@link Filter} <a href=
	 *            "http://www.osgi.org/javadoc/r4v43/core/org/osgi/framework/Filter.html"
	 *            >(online)</a>
	 * @return a {@link Collection} of {@link ServiceReference} objects with
	 *         interface <code>T</code>
	 * @throws InvalidSyntaxException
	 */
	public static <T> Collection<ServiceReference<T>> discoverService(
			BundleContext bundleContext, Class<T> clazz, String filter)
			throws InvalidSyntaxException {
		return bundleContext.getServiceReferences(clazz, filter);
	}

	/**
	 * returns a {@link Collection} of {@link ServiceReference} objects with
	 * interface <code>T</code> objects with interface <code>T</code>
	 * 
	 * @param bundleContext
	 *            {@link BundleContext}
	 * @param clazz
	 *            interface <code>T</code> of the service
	 * @return a {@link Collection} of {@link ServiceReference} objects with
	 *         interface <code>T</code>
	 */
	public static <T> Collection<ServiceReference<T>> discoverService(
			BundleContext bundleContext, Class<T> clazz) {
		try {
			return bundleContext.getServiceReferences(clazz, null);
		} catch (InvalidSyntaxException e) {
			// Exception should never be thrown, cause a non-existant filter
			// can't have a invalid syntax
		}
		return null;
	}

	/**
	 * returns the service object referenced by the
	 * <code>ServiceReference</code> object
	 * 
	 * @param context
	 *            {@link BundleContext}
	 * @param reference
	 *            {@link ServiceReference}
	 * @return service object with interface <code>T</code>
	 */
	public static <T> T getService(BundleContext bundleContext,
			ServiceReference<T> reference) {
		return bundleContext.getService(reference);
	}

	/**
	 * returns a service object with interface <code>T</code>
	 * 
	 * @param bundleContext
	 *            {@link BundleContext}
	 * @param clazz
	 *            interface <code>T</code> of the service
	 * @return service object with interface <code>T</code>
	 */
	public static <T> T getSomeService(BundleContext bundleContext,
			Class<T> clazz) {
		Collection<ServiceReference<T>> services = OsgiUtil.discoverService(
				bundleContext, clazz);
		if (services.isEmpty()) {
			return null;
		}
		return OsgiUtil.getService(bundleContext, services.iterator().next());
	}

	/**
	 * returns a service object with interface <code>T</code>
	 * 
	 * @param bundleContext
	 *            {@link BundleContext}
	 * @param clazz
	 *            interface <code>T</code> of the service
	 * @param filter
	 *            for filter syntax see {@link Filter} <a href=
	 *            "http://www.osgi.org/javadoc/r4v43/core/org/osgi/framework/Filter.html"
	 *            >(online)</a>
	 * @return service object with interface <code>T</code>
	 * @throws InvalidSyntaxException
	 */
	public static <T> T getSomeService(BundleContext bundleContext,
			Class<T> clazz, String filter) throws InvalidSyntaxException {
		Collection<ServiceReference<T>> services = OsgiUtil.discoverService(
				bundleContext, clazz, filter);
		if (services.isEmpty()) {
			return null;
		}
		return OsgiUtil.getService(bundleContext, services.iterator().next());
	}

}
