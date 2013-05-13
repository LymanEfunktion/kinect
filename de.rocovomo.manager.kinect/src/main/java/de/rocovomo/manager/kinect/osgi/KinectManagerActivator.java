package de.rocovomo.manager.kinect.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class KinectManagerActivator implements BundleActivator {

	private BundleContext context;
	private GestureConnectorHandler gestureConnectorHandler;
	private GestureListenerConnectorHandler gestureListenerConnectorHandler;
	private SpeechListenerConnectorHandler speechListenerConnectorHandler;

	@Override
	public void start(BundleContext context) throws Exception {
		this.context = context;
		this.gestureConnectorHandler = new GestureConnectorHandler(this.context);
		this.gestureListenerConnectorHandler = new GestureListenerConnectorHandler(
				this.context);
		this.speechListenerConnectorHandler = new SpeechListenerConnectorHandler(
				this.context);

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
