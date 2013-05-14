package de.rocovomo.osgi.api;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.Filter;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import de.rocovomo.osgi.util.OsgiUtil;

/**
 * 
 * @author voowoo <a href="mailto:vowe91@gmail.com">vowe91@gmail.com</a>
 * 
 * @param <T>
 */
public abstract class GenericServiceListener<T> implements ServiceListener {

	protected BundleContext bundleContext;
	protected final Class<T> typeParameterClass;

	protected List<T> services = new ArrayList<>();
	protected Filter filter;

	public GenericServiceListener(BundleContext bundleContext,
			Class<T> typeParameterClass) {
		this.bundleContext = bundleContext;
		this.typeParameterClass = typeParameterClass;

		try {
			filter = bundleContext.createFilter("(" + Constants.OBJECTCLASS
					+ "=" + typeParameterClass.getName() + ")");

			this.bundleContext.addServiceListener(this, this.filter.toString());
		} catch (InvalidSyntaxException e) {
			// TODO What now? Shouldn't be wrong at that point
			e.printStackTrace();
		}
	}

	@Override
	public void serviceChanged(ServiceEvent event) {
		Object service = OsgiUtil.getService(this.bundleContext,
				event.getServiceReference());

		// TODO check should not be neccessary and handled by the osgi framework
		// via the filter, but it somehow does not work as expected
		// only working for the moment
		if (service.getClass().getSuperclass().getName()
				.equals(typeParameterClass.getName())
				|| service.getClass().getName()
						.equals(typeParameterClass.getName())) {

			@SuppressWarnings("unchecked")
			T castedService = (T) service;
			if (event.getType() == ServiceEvent.REGISTERED) {
				serviceRegistered(castedService);
				this.services.add(castedService);
			}
			if (event.getType() == ServiceEvent.UNREGISTERING) {
				serviceUnregistered(castedService);
				this.services.remove(castedService);
			}
		}
	}

	public abstract void serviceRegistered(T service);

	public abstract void serviceUnregistered(T service);

	public void stop() {
		this.bundleContext.removeServiceListener(this);
		stopListener();
	};

	public abstract void stopListener();

}
