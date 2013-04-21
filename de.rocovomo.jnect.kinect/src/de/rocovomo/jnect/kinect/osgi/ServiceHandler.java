package de.rocovomo.jnect.kinect.osgi;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

import de.rocovomo.osgi.api.AbstractServiceHandler;
import de.rocovomo.osgi.util.OsgiUtil;

public class ServiceHandler extends AbstractServiceHandler{
	
	public ServiceHandler(BundleContext bundleContext) {
		super(bundleContext, managedServices);
		registerServiceListener(bundleContext);
		discoverInitialServices(bundleContext);
	}
}
