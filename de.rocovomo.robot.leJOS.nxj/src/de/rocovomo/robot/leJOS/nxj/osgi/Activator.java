package de.rocovomo.robot.leJOS.nxj.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import de.rocovomo.robot.api.RobotMovement;
import de.rocovomo.robot.leJOS.nxj.impl.RobotMovementImpl;

public class Activator implements BundleActivator {

	private BundleContext context;
	private ServiceRegistration<RobotMovement> serviceRegistration;

	@Override
	public void start(BundleContext context) throws Exception {
		this.context = context;

		RobotMovement robot = new RobotMovementImpl();

		this.serviceRegistration = this.context.registerService(
				RobotMovement.class, robot, null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		this.serviceRegistration.unregister();
	}

}
