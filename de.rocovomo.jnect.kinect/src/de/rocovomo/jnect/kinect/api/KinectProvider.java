package de.rocovomo.jnect.kinect.api;

import java.util.Dictionary;
import java.util.Hashtable;

import de.rocovomo.jnect.kinect.Connector;


public class KinectProvider {

	private final static String PID = "132410384143";

	private final static String KINECT_MANAGER = "Kinect-Manager";

	private final Dictionary<String, Object> properties = new Hashtable<String, Object>();

	private IConnector connector;
	
	public KinectProvider() {
		properties.put("process-id", PID);
		properties.put("type", KINECT_MANAGER);
		this.connector = new Connector();
	}

	public IConnector getConnector() {
		return connector;
	}

	public Dictionary<String, Object> getKinectProperties() {
		return properties;
	}
}
