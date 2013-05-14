package de.rocovomo.manager.kinect.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import de.rocovomo.jnect.kinect.api.KinectManagement;
import de.rocovomo.osgi.util.OsgiUtil;

public class KinectManagerActivator implements BundleActivator {

	private BundleContext context;
	private GestureConnectorHandler gestureConnectorHandler;
	private GestureListenerConnectorHandler gestureListenerConnectorHandler;
	private SpeechListenerConnectorHandler speechListenerConnectorHandler;

	@Override
	public void start(BundleContext context) throws Exception {
		this.context = context;

		// TODO: all could belong to different bundles handling different
		// devices is there some way to analyze if they all belong to the same
		// bundle via id?

		KinectManagement kinectManagement = OsgiUtil.getSomeService(
				this.context, KinectManagement.class);

		this.gestureConnectorHandler = new GestureConnectorHandler(
				this.context, kinectManagement);
		this.gestureListenerConnectorHandler = new GestureListenerConnectorHandler(
				this.context, kinectManagement);
		this.speechListenerConnectorHandler = new SpeechListenerConnectorHandler(
				this.context, kinectManagement);

		this.context.addServiceListener(this.gestureConnectorHandler);
		this.context.addServiceListener(this.gestureListenerConnectorHandler);
		this.context.addServiceListener(this.speechListenerConnectorHandler);

	}

	@Override
	public void stop(BundleContext context) throws Exception {
		this.gestureConnectorHandler.stop();
		this.gestureListenerConnectorHandler.stop();
		this.speechListenerConnectorHandler.stop();
	}

}
