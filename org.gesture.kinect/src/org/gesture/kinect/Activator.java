package org.gesture.kinect;

import org.gesture.RoCoVoMoGesture;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator, ServiceListener{

	private static BundleContext context;

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
		Activator.context = bundleContext;

		String filter = "(objectClass=" + RoCoVoMoGesture.class.getName() + ")";

		ServiceReference[] serviceReferences = bundleContext
				.getAllServiceReferences(null, filter);

		if (serviceReferences != null) {
			// TODO: connect Gestures
			test(serviceReferences[0]);
		} else {
			bundleContext.addServiceListener(this, filter);
		}
	}

	private void test(ServiceReference serviceReference) {
		// TODO Auto-generated method stub
		RoCoVoMoGesture gesture = (RoCoVoMoGesture)  context.getService(serviceReference);
		System.out.println("asdf;lakej");
	}

	public RoCoVoMoGesture usingAServiceTracker(BundleContext bundleContext)
			throws InterruptedException {
		String filter = "(&(objectClass=" + RoCoVoMoGesture.class.getName() + ")("
				+ RoCoVoMoGesture.TYPE + "=Sealed-First-Price))";

		ServiceTracker tracker = new ServiceTracker(bundleContext, filter, null);

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

	@Override
	public void serviceChanged(ServiceEvent event) {
		switch (event.getType()) {
		case ServiceEvent.REGISTERED: {
			// TODO: connect Gestures
			break;
		}
		default:
			// do nothing
		}
	}
}
