package de.rocovomo.osgi.api;

import java.util.Collection;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import de.rocovomo.osgi.util.OsgiUtil;

public abstract class AbstractServiceHandler<T> extends GenericServiceListener<T>{

	public AbstractServiceHandler(BundleContext bundleContext,
			Class<T> typeParameterClass){
		super(bundleContext, typeParameterClass);
		discoverInitialServices(bundleContext);
	}

	private void discoverInitialServices(BundleContext bundleContext) {
		try {
			Collection<ServiceReference<T>> initialServices = OsgiUtil
					.discoverService(bundleContext, this.typeParameterClass, null);
			for (ServiceReference<T> serviceReference : initialServices) {
				T service = this.bundleContext.getService(serviceReference);
				this.services.add(service);
			}
		} catch (InvalidSyntaxException e) {
			// TODO propagate to jvm
		}
	}

}
