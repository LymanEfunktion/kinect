package de.rocovomo.jnect.kinect.api;

public abstract class KinectException extends Exception {

	private static final long serialVersionUID = 7069134038392476603L;

	public KinectException(String message) {
		super(message);
	}

	public KinectException(String message, Throwable cause) {
		super(message, cause);
	}

	public KinectException(Throwable cause) {
		super(cause);
	}

}
