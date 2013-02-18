package de.rocovomo.action.osgi;

import static de.rocovomo.osgi.util.OsgiLogging.logBundleShutdown;
import static de.rocovomo.osgi.util.OsgiLogging.logBundleStarted;
import static de.rocovomo.osgi.util.OsgiLogging.logBundleStartup;
import static de.rocovomo.osgi.util.OsgiLogging.logBundleStopped;
import static de.rocovomo.osgi.util.OsgiLogging.logServiceRegistered;
import static de.rocovomo.osgi.util.OsgiLogging.logServiceUnregistered;
import static de.rocovomo.osgi.util.OsgiLogging.logServiceUnregistering;

import java.util.Dictionary;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import de.rocovomo.action.TestAction;
import de.rocovomo.action.api.Action;
import de.rocovomo.action.api.ActionProvider;
import de.rocovomo.action.provider.TestActionProvider;
import de.rocovomo.action.robot.TestRobotAction;
import de.rocovomo.jnect.gesture.api.GestureProvider;
import de.rocovomo.jnect.gesture.api.RoCoVoMoGesture;

// TODO javadoc missing, logging missing
public class ActionActivator implements BundleActivator, ServiceListener {

	@SuppressWarnings("rawtypes")
	private ServiceRegistration serviceRegistration;
	private BundleContext context;

	private ActionProvider provider;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		this.context = bundleContext;
		logBundleStartup(bundleContext);

		ServiceReference<?>[] ref = bundleContext.getAllServiceReferences(
				GestureProvider.class.getName(), null);
		if (ref != null) {
			GestureProvider pro = (GestureProvider) context.getService(ref[0]);
			registerActionProvider(pro);
		}

		this.context.addServiceListener(this);

		logBundleStarted(bundleContext);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		logBundleShutdown(bundleContext);
		logServiceUnregistering(bundleContext, this.serviceRegistration);
		this.serviceRegistration.unregister();
		logServiceUnregistered(bundleContext, this.serviceRegistration);
		logBundleStopped(bundleContext);
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

	private void registerActionProvider(GestureProvider provider) {
		Dictionary<String, Object> dic = provider.getProperties();
		String type = (String) dic.get(RoCoVoMoGesture.TYPE);
		// TODO String?!?!?!?!
		if (type.equals("Jump-Gesture")) {
			Action action = new TestAction(new TestRobotAction(),
					provider.getProvided());
			this.provider = new TestActionProvider(action);
			serviceRegistration = context.registerService(
					ActionProvider.class.getName(), this.provider,
					this.provider.getProperties());
			logServiceRegistered(this.context, serviceRegistration);
		}
	}
}
