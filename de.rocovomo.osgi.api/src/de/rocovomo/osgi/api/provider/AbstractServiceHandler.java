package de.rocovomo.osgi.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

import de.rocovomo.osgi.util.OsgiUtil;

public abstract class AbstractServiceHandler<T> implements ServiceProvider<T> {

	private List<T> services = new ArrayList<>();

	public AbstractServiceHandler(BundleContext bundleContext) {
		discoverInitialServices(bundleContext);
		registerServiceListener(bundleContext);
	}

	@SuppressWarnings("unchecked")
	private void discoverInitialServices(BundleContext bundleContext) {
		try {
			Collection<ServiceReference<Object>> initialServices = OsgiUtil
					.discoverService(bundleContext, null, null);
			for (ServiceReference<Object> serviceReference : initialServices) {
				addService((T) bundleContext.getService(serviceReference));
			}
		} catch (InvalidSyntaxException e) {
			// TODO propagate to jvm
		}
	}

	private void registerServiceListener(final BundleContext bundleContext) {
		bundleContext.addServiceListener(new ServiceListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void serviceChanged(ServiceEvent serviceEvent) {
				ServiceReference<?> serviceReference = serviceEvent
						.getServiceReference();

				switch (serviceEvent.getType()) {
				case ServiceEvent.REGISTERED: {
					addService((T) bundleContext.getService(serviceReference));
					break;
				}
				case ServiceEvent.UNREGISTERING: {
					removeService((T) bundleContext
							.getService(serviceReference));
					break;
				}
				default:
					// do nothing
				}
			}
		});
	}

	private void addService(T t) {
		this.services.add(t);
	}

	private void removeService(T t) {
		this.services.remove(t);
	}

	public Collection<T> getServices() {
		return services;
	}
}
