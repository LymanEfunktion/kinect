package de.rocovomo.logging.osgi;

import static org.osgi.framework.ServiceEvent.MODIFIED;
import static org.osgi.framework.ServiceEvent.REGISTERED;
import static org.osgi.framework.ServiceEvent.UNREGISTERING;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

public class ServiceListenerImpl implements ServiceListener {

	private final Logger Log = Logger.getLogger("ServiceLogging");
	private final BundleContext context;

	public ServiceListenerImpl(BundleContext context) {
		this.context = context;
	}

	@Override
	public void serviceChanged(ServiceEvent event) {
		int type = event.getType();
		ServiceReference<?> serviceRef = event.getServiceReference();
		String serviceInterfaceName = returnServiceInterfaceName(serviceRef);
		String serviceImplName = returnServiceImplName(serviceRef);
		if (type == REGISTERED) {
			Log.info("Registered Serivce: Class " + serviceImplName
					+ " registered as a service with the interface "
					+ serviceInterfaceName);
		}
		if (type == UNREGISTERING) {
			Log.info("Unregistered Service: Class " + serviceImplName
					+ " unregistered as a service with the interface "
					+ serviceInterfaceName);
		}
		if (type == MODIFIED) {
			// uninteresting
		}

	}

	private String returnServiceImplName(ServiceReference<?> serviceRef) {
		return this.context.getService(serviceRef).getClass().getName();
	}

	private String returnServiceInterfaceName(ServiceReference<?> serviceRef) {
		String[] string = (String[]) serviceRef.getProperty("objectClass");
		return Arrays.toString(string);
	}

}
