package de.rocovomo.osgi.api;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
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

	public GenericServiceListener(BundleContext bundleContext,
			Class<T> typeParameterClass){
		this.bundleContext = bundleContext;
		this.typeParameterClass = typeParameterClass;
		
		String filter = "(" + Constants.OBJECTCLASS + "="
				+ typeParameterClass.getName() + ")";

		try {
			this.bundleContext.addServiceListener(this, filter);
		} catch (InvalidSyntaxException e) {
			// TODO What now? Shouldn't be wrong at that point
			e.printStackTrace();
		}
	}

	@Override
	public void serviceChanged(ServiceEvent event) {
		@SuppressWarnings("unchecked")
		T service = (T) OsgiUtil.getService(this.bundleContext,
				event.getServiceReference());

		if (event.getType() == ServiceEvent.REGISTERED) {
			serviceRegistered(service);
			this.services.add(service);
		}
		if (event.getType() == ServiceEvent.UNREGISTERING) {
			serviceUnregistered(service);
			this.services.remove(service);
		}
	}

	public abstract void serviceRegistered(T service);

	public abstract void serviceUnregistered(T service);
	
	public void stop(){
		this.bundleContext.removeServiceListener(this);
		stopListener();
	};
	
	public abstract void stopListener();
	
}
