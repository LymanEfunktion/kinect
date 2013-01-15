package de.rocovomo.action.activator;

import java.util.Dictionary;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import de.rocovomo.action.TestAction;
import de.rocovomo.action.api.Action;
import de.rocovomo.action.provider.TestActionProvider;
import de.rocovomo.action.provider.api.ActionProvider;
import de.rocovomo.action.robot.TestRobotAction;
import de.rocovomo.jnect.gesture.RoCoVoMoGesture;
import de.rocovomo.jnect.gesture.spi.GestureProvider;

// TODO javadoc missing, logging missing
public class ActionActivator implements BundleActivator, ServiceListener {

	@SuppressWarnings("rawtypes")
	private ServiceRegistration serviceRegistration;
	private BundleContext context;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		this.context = bundleContext;

		ServiceReference<?>[] ref = bundleContext.getAllServiceReferences(
				GestureProvider.class.getName(), null);
		if (ref != null) {
			GestureProvider pro = (GestureProvider) ref[0];
			registerActionProvider(pro);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		serviceRegistration.unregister();
	}

	@Override
	public void serviceChanged(ServiceEvent event) {
		int eventType = event.getType();
		ServiceReference<?> ref = event.getServiceReference();

		if (eventType == ServiceEvent.REGISTERED) {
			registerService(ref);
		}
		if (eventType == ServiceEvent.UNREGISTERING) {
			// TODO what will happen?
		}
	}

	private void registerService(ServiceReference<?> ref) {
		Object serviceObject = context.getService(ref);
		if (serviceObject instanceof GestureProvider) {
			GestureProvider pro = (GestureProvider) serviceObject;
			registerActionProvider(pro);
		}
	}

	private void registerActionProvider(GestureProvider pro) {
		Dictionary<String, Object> dic = pro.getGestureProperties();
		String type = (String) dic.get(RoCoVoMoGesture.TYPE);
		// TODO lookup via String?
		if (type.equals("Jump-Gesture")) {
			Action action = new TestAction(new TestRobotAction(),
					pro.getGesture());
			ActionProvider provider = new TestActionProvider(action);
			serviceRegistration = context.registerService(
					ActionProvider.class.getName(), provider,
					provider.getActionProperties());
		}
	}
}
