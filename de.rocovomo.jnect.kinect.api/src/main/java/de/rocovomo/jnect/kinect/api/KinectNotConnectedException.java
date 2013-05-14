package de.rocovomo.jnect.kinect.api;

public class KinectNotConnectedException extends KinectException {

	private static final long serialVersionUID = 9148227519630523869L;

	public KinectNotConnectedException(String message) {
		super(message);
	}

	public KinectNotConnectedException(String message, Throwable cause) {
		super(message, cause);
	}

	public KinectNotConnectedException(Throwable cause) {
		super(cause);
	}

}
