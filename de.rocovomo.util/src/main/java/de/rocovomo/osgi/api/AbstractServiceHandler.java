package de.rocovomo.osgi.api;

import java.util.Collection;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import de.rocovomo.osgi.util.OsgiUtil;

public abstract class AbstractServiceHandler<T> extends
		GenericServiceListener<T> {

	public AbstractServiceHandler(BundleContext bundleContext,
			Class<T> typeParameterClass) {
		super(bundleContext, typeParameterClass);
		discoverInitialServices(bundleContext);
	}

	@SuppressWarnings("unchecked")
	private void discoverInitialServices(BundleContext bundleContext) {
		try {
			Collection<ServiceReference<T>> initialServices = OsgiUtil
					.discoverService(bundleContext, this.typeParameterClass,
							filter.toString());
			for (ServiceReference<T> serviceReference : initialServices) {

				// TODO check should not be neccessary and handled by the osgi framework
				// via the filter, but it somehow does not work as expected
				// only working for the moment
				Object service = OsgiUtil.getService(this.bundleContext,
						serviceReference);
				if (service.getClass().getSuperclass().getName()
						.equals(typeParameterClass.getName())
						|| service.getClass().getName()
								.equals(typeParameterClass.getName())) {
					this.services.add((T) service);
				}
			}
		} catch (InvalidSyntaxException e) {
			// TODO propagate to jvm
		}
	}

}
