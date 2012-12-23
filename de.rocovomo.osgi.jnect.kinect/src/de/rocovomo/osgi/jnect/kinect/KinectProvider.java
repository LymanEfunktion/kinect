package de.rocovomo.osgi.jnect.kinect;

import java.util.Dictionary;
import java.util.Hashtable;


public class KinectProvider {

	private final static String PID = "132410384143";

	private final static String KINECT_MANAGER = "Kinect-Manager";

	private final Dictionary<String, Object> properties = new Hashtable<String, Object>();

	private Connector connector;
	
	public KinectProvider() {
		properties.put("process-id", PID);
		properties.put("type", KINECT_MANAGER);
		this.connector = new Connector();
	}

	public Connector getConnector() {
		return connector;
	}

	public Dictionary<String, Object> getKinectProperties() {
		return properties;
	}
}
