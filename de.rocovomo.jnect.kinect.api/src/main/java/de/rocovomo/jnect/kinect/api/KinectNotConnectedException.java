package de.rocovomo.jnect.kinect.api;

public class KinectNotConnectedException extends KinectException {

	private static final long serialVersionUID = 9148227519630523869L;

	private static final String message = "No Kinect Device connected";
	
	public KinectNotConnectedException() {
		super(message);
	}
	
	public KinectNotConnectedException(String message) {
		super(message);
	}

	public KinectNotConnectedException(Throwable cause) {
		super(message,cause);
	}
	
	public KinectNotConnectedException(String message, Throwable cause) {
		super(message, cause);
	}

}
