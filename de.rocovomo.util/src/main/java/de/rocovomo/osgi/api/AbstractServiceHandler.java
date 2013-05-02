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
				this.services.add(this.bundleContext.getService(serviceReference));
			}
		} catch (InvalidSyntaxException e) {
			// TODO propagate to jvm
		}
	}

}
