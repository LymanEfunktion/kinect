package de.rocovomo.jnect.kinect.api;

public class KinectNotStartedException extends KinectException{

	private static final long serialVersionUID = -8854673012368916016L;

	private static final String message = "Kinect is not started";
	
	public KinectNotStartedException() {
		super(message);
	}
	
	public KinectNotStartedException(String message) {
		super(message);
	}

	public KinectNotStartedException(Throwable cause) {
		super(message,cause);
	}
	
	public KinectNotStartedException(String message, Throwable cause) {
		super(message,cause);
	}
	
}
