package de.rocovomo.jnect.kinect.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import de.rocovomo.jnect.kinect.Kinect;
import de.rocovomo.jnect.kinect.api.GestureConnector;
import de.rocovomo.jnect.kinect.api.GestureListenerConnector;
import de.rocovomo.jnect.kinect.api.SpeechListenerConnector;
import de.rocovomo.osgi.util.OsgiUtil;

/**
 * TODO: Description * Logging
 * @author Volker
 *
 */
public class KinectActivator implements BundleActivator {

	private BundleContext context;
	private Kinect kinect;
	private ServiceRegistration<Kinect> gestureConnectorService;
	private ServiceRegistration<Kinect> gestureListenerConnectorService;
	private ServiceRegistration<Kinect> speechListenerConnectorService;

	@Override
	public void start(BundleContext context) throws Exception {
		this.context = context;
		this.kinect = new Kinect();
		this.kinect.start();

		this.gestureConnectorService = OsgiUtil.registerService(this.context,
				GestureConnector.class, this.kinect);
		this.gestureListenerConnectorService = OsgiUtil.registerService(
				this.context, GestureListenerConnector.class, this.kinect);
		this.speechListenerConnectorService = OsgiUtil.registerService(
				this.context, SpeechListenerConnector.class, this.kinect);

	}

	@Override
	public void stop(BundleContext context) throws Exception {
		this.kinect.stop();
		this.speechListenerConnectorService.unregister();
		this.gestureListenerConnectorService.unregister();
		this.gestureConnectorService.unregister();
	}

}
