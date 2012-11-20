package de.rocovomo.osgi.jnect.gesture.manager;

import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import de.rocovomo.osgi.jnect.gesture.RoCoVoMoGesture;
import de.rocovomo.osgi.jnect.gesture.spi.GestureProvider;

@SuppressWarnings("rawtypes")
public class GestureManagerActivator implements BundleActivator,
		ServiceListener {

	private static BundleContext context;

	private Map<ServiceReference, ServiceRegistration> registeredGestures = new HashMap<ServiceReference, ServiceRegistration>();

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
		GestureManagerActivator.context = bundleContext;
		String gestureFilter = "(objectClass="
				+ GestureProvider.class.getName() + ")";
		ServiceReference<?>[] references = bundleContext
				.getAllServiceReferences(null, gestureFilter);

		if (references != null) {
			for (ServiceReference serviceReference : references) {
				registerService(serviceReference);
			}
		}

		bundleContext.addServiceListener(this, gestureFilter);
	}

	@Override
	public void serviceChanged(ServiceEvent serviceEvent) {
		ServiceReference serviceReference = serviceEvent.getServiceReference();

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
			ServiceReference gestureProviderServiceReference) {
		ServiceRegistration gestureServiceRegistration = registeredGestures
				.remove(gestureProviderServiceReference);

		if (gestureServiceRegistration != null) {
			gestureServiceRegistration.unregister();
		}
	}

	@SuppressWarnings("unchecked")
	private void registerService(ServiceReference serviceReference) {
		Object serviceObject = context.getService(serviceReference);

		if (serviceObject instanceof RoCoVoMoGesture) {
			registerGestureProvider(serviceReference,
					(GestureProvider) serviceObject);
		}
	}

	private void registerGestureProvider(ServiceReference serviceReference,
			GestureProvider provider) {
		RoCoVoMoGesture gesture = new GestureWrapper(provider);

		ServiceRegistration gestureServiceRegistration = context
				.registerService(RoCoVoMoGesture.class.getName(), gesture,
						provider.getGestureProperties());

		registeredGestures.put(serviceReference, gestureServiceRegistration);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		GestureManagerActivator.context = null;
	}
}