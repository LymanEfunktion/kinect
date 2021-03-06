package de.rocovomo.e4.rcp;

import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator, ServiceListener {

	private static BundleContext context;
	
	private Map<ServiceReference<?>, ServiceRegistration<?>> registeredKinect = new HashMap<ServiceReference<?>, ServiceRegistration<?>>();
	@SuppressWarnings("rawtypes")
	private ServiceRegistration serviceRegistration;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		// PlatformUI.getPreferenceStore().putValue(IProductConstants.STARTUP_PROGRESS_RECT,
		// "0,5,100,20");
		// PlatformUI.getPreferenceStore().putValue(IProductConstants.STARTUP_FOREGROUND_COLOR,
		// "124316");
//		PlatformUI.getPreferenceStore().setDefault(
//				IWorkbenchPreferenceConstants.SHOW_PROGRESS_ON_STARTUP, true);
//		String test = PlatformUI.getPreferenceStore().getString(
//				IWorkbenchPreferenceConstants.SHOW_PROGRESS_ON_STARTUP);
//		// IProductConstants.STARTUP_PROGRESS_RECT
//		System.out.println(test);
//		IProduct product = Platform.getProduct();
//		System.out.println(product
//				.getProperty(IProductConstants.STARTUP_FOREGROUND_COLOR));
//		System.out.println(product
//				.getProperty(IProductConstants.STARTUP_PROGRESS_RECT));
//		System.out.println(product
//				.getProperty(IProductConstants.STARTUP_MESSAGE_RECT));
		Activator.context = bundleContext;

//		String kinectFilter = "(objectClass=" + KinectProvider.class.getName()
//				+ ")";
//
//		ServiceReference<?>[] references = bundleContext
//				.getAllServiceReferences(null, kinectFilter);
//
//		if (references != null) {
//			for (ServiceReference<?> serviceReference : references) {
//				System.out.println(serviceReference.getBundle()
//						.getSymbolicName());
//				registerService(serviceReference);
//			}
//		}
//
//		bundleContext.addServiceListener(this, kinectFilter);
	}

//	private void registerService(ServiceReference<?> serviceReference) {
//		//TODO: Do something with Kinect
//		Object serviceObject = context.getService(serviceReference);
//
//		if (serviceObject instanceof KinectProvider) {
//			System.out.println("test");
//			registerKinectProvider(serviceReference,
//					(KinectProvider) serviceObject);
//		}
//	}
//
//	private void registerKinectProvider(ServiceReference<?> serviceReference,
//			KinectProvider provider) {
//		// TODO Auto-generated method stub
//		IConnector connector = provider.getConnector();
//
//		ServiceRegistration<?> kinectServiceRegistration = context
//				.registerService(IConnector.class.getName(), connector,
//						provider.getKinectProperties());
//
//		registeredKinect.put(serviceReference, kinectServiceRegistration);
//	}

	/*
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		bundleContext.removeServiceListener(this);

		serviceRegistration.unregister();
	}

	@Override
	public void serviceChanged(ServiceEvent event) {
//		ServiceReference<?> serviceReference = event.getServiceReference();
//
//		switch (event.getType()) {
//		case ServiceEvent.REGISTERED: {
//			registerService(serviceReference);
//			break;
//		}
//		case ServiceEvent.UNREGISTERING: {
//			context.ungetService(event.getServiceReference());
//			break;
//		}
//		default:
//			// do nothing
//		}
	}
}