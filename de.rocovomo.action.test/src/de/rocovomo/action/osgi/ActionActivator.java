package de.rocovomo.action.osgi;

import java.util.Dictionary;

import org.apache.log4j.Logger;
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

	private static Logger logger = Logger.getLogger(ActionActivator.class);

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
		logger.info("Starting " + this.context.getBundle().getSymbolicName());

		ServiceReference<?>[] ref = bundleContext.getAllServiceReferences(
				GestureProvider.class.getName(), null);
		if (ref != null) {
			GestureProvider pro = (GestureProvider) context.getService(ref[0]);
			registerActionProvider(pro);
		}

		this.context.addServiceListener(this);

		logger.info("Started " + this.context.getBundle().getSymbolicName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		logger.info("Stopping " + this.context.getBundle().getSymbolicName());
		logger.info("Unregistering " + this.provider.getProvided() + ":"
				+ this.provider.getProperties().get(Action.TYPE));
		serviceRegistration.unregister();
		logger.info("Unregistered " + this.provider.getProvided() + ":"
				+ this.provider.getProperties().get(Action.TYPE));
		logger.info("Stopped " + this.context.getBundle().getSymbolicName());
	}

	@Override
	public void serviceChanged(ServiceEvent event) {
		logger.debug("ServiceChanged");
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
		// TODO lookup via String?
		if (type.equals(TestAction.TYPE)) {
			Action action = new TestAction(new TestRobotAction(),
					provider.getProvided());
			this.provider = new TestActionProvider(action);
			serviceRegistration = context.registerService(
					ActionProvider.class.getName(), this.provider,
					this.provider.getProperties());
			logger.info("Registered " + this.provider.getProvided()+ ":"
					+ this.provider.getProperties().get(Action.TYPE));
		}
	}
}
