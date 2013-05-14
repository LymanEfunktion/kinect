package de.rocovomo.jnect.kinect.osgi;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import de.rocovomo.jnect.kinect.Kinect;
import de.rocovomo.jnect.kinect.api.GestureConnector;
import de.rocovomo.jnect.kinect.api.GestureListenerConnector;
import de.rocovomo.jnect.kinect.api.KinectManagement;
import de.rocovomo.jnect.kinect.api.SpeechListenerConnector;
import de.rocovomo.osgi.util.OsgiUtil;

/**
 * TODO: Description * Logging
 * 
 * @author Volker
 * 
 */
public class KinectActivator implements BundleActivator {

	private Logger log = Logger.getLogger(KinectActivator.class);

	private BundleContext context;
	private Kinect kinect;
	private ServiceRegistration<Kinect> gestureConnectorService;
	private ServiceRegistration<Kinect> gestureListenerConnectorService;
	private ServiceRegistration<Kinect> speechListenerConnectorService;
	private ServiceRegistration<Kinect> kinectManagementService;

	@Override
	public void start(BundleContext context) throws Exception {
		this.context = context;
		this.kinect = new Kinect();
		
		this.kinectManagementService = OsgiUtil.registerService(this.context,
				KinectManagement.class, this.kinect);
		this.kinect.start();
		
		// register aspects of Kinect functionalities as services
		this.speechListenerConnectorService = OsgiUtil.registerService(
				this.context, SpeechListenerConnector.class, this.kinect);
		
		this.gestureConnectorService = OsgiUtil.registerService(this.context,
				GestureConnector.class, this.kinect);

		this.gestureListenerConnectorService = OsgiUtil.registerService(
				this.context, GestureListenerConnector.class, this.kinect);

		

		

		this.log.info("Kinect starting");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		this.speechListenerConnectorService.unregister();
		this.gestureListenerConnectorService.unregister();
		this.gestureConnectorService.unregister();
		this.kinectManagementService.unregister();
		this.kinect.stop();
		this.log.info("Kinect stopping");
	}

}
