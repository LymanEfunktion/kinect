package de.rocovomo.osgi.jnect.kinect;

import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

import de.rocovomo.osgi.jnect.adapter.RoCoVoMoAdapter;
import de.rocovomo.osgi.jnect.adapter.spi.AdapterProvider;
import de.rocovomo.osgi.jnect.gesture.RoCoVoMoGesture;
import de.rocovomo.osgi.jnect.gesture.manager.GestureWrapper;
import de.rocovomo.osgi.jnect.gesture.spi.GestureProvider;

public class KinectActivator implements BundleActivator, ServiceListener {

	private static BundleContext context;

	private Map<ServiceReference<?>, ServiceRegistration<?>> registeredGestures = new HashMap<ServiceReference<?>, ServiceRegistration<?>>();

	BundleContext getContext() {
		return context;
	}

	public RoCoVoMoGesture usingAServiceTracker(BundleContext bundleContext)
			throws InterruptedException {
		String filter = "(objectClass=" + RoCoVoMoGesture.class.getName() + ")";

		ServiceTracker<Object, Object> tracker = new ServiceTracker<Object, Object>(
				bundleContext, filter, null);

		tracker.open();

		RoCoVoMoGesture gesture = (RoCoVoMoGesture) tracker.waitForService(0);

		return gesture;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		bundleContext.removeServiceListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		KinectActivator.context = bundleContext;

		String gestureFilter = "(objectClass="
				+ GestureProvider.class.getName() + ")";

		String adapterFilter = "(objectClass="
				+ AdapterProvider.class.getName() + ")";

		String filter = "(|" + gestureFilter + adapterFilter + ")";

		ServiceReference<?>[] references = bundleContext
				.getAllServiceReferences(null, filter);

		if (references != null) {
			// if (Connector.initialize()) {
			for (ServiceReference<?> serviceReference : references) {
				System.out.println(serviceReference.getBundle()
						.getSymbolicName());
				registerService(serviceReference);
			}
			// } else {
			// System.out.println("Init fehlgeschlagen");
			// }
		}

		bundleContext.addServiceListener(this, gestureFilter);
	}

	@Override
	public void serviceChanged(ServiceEvent serviceEvent) {
		ServiceReference<?> serviceReference = serviceEvent
				.getServiceReference();

		switch (serviceEvent.getType()) {
		case ServiceEvent.REGISTERED: {
			registerService(serviceReference);
			break;
		}
		case ServiceEvent.UNREGISTERING: {
			String[] serviceInterfaces = (String[]) serviceReference
					.getProperty("objectClass");
			if (GestureProvider.class.getName().equals(serviceInterfaces[0])) {
				unregisterGestureProvider(serviceReference);
			}
			context.ungetService(serviceReference);
			break;
		}
		default:
			// do nothing
		}
	}

	private void unregisterGestureProvider(
			ServiceReference<?> gestureProviderServiceReference) {
		ServiceRegistration<?> gestureServiceRegistration = registeredGestures
				.remove(gestureProviderServiceReference);

		if (gestureServiceRegistration != null) {
			gestureServiceRegistration.unregister();
		}
	}

	private void registerService(ServiceReference<?> serviceReference) {
		Object serviceObject = context.getService(serviceReference);

		if (serviceObject instanceof AdapterProvider) {
			registerAdapterProvider(serviceReference,
					(AdapterProvider) serviceObject);
		}
		
//		if (serviceObject instanceof GestureProvider) {
//			registerGestureProvider(serviceReference,
//					(GestureProvider) serviceObject);
//		}
	}

	private void registerAdapterProvider(ServiceReference<?> serviceReference,
			AdapterProvider provider) {
		RoCoVoMoAdapter adapter = provider.getAdapter();

		ServiceRegistration<?> adapterServiceRegistration = context
				.registerService(RoCoVoMoAdapter.class.getName(), adapter,
						provider.getAdapterProperties());

		Connector.connectAdapter(provider);
	}

	private void registerGestureProvider(ServiceReference<?> serviceReference,
			GestureProvider provider) {
		RoCoVoMoGesture gesture = new GestureWrapper(provider);

		ServiceRegistration<?> gestureServiceRegistration = context
				.registerService(RoCoVoMoGesture.class.getName(), gesture,
						provider.getGestureProperties());

		registeredGestures.put(serviceReference, gestureServiceRegistration);
		Connector.connectGesture(provider);
	}
}
