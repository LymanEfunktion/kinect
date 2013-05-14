package de.rocovomo.jnect.kinect.api;

public class KinectNotStartedException extends KinectException{

	private static final long serialVersionUID = -8854673012368916016L;

	public KinectNotStartedException(String message) {
		super(message);
	}

	public KinectNotStartedException() {
		super("Kinect is not started");
	}

	public KinectNotStartedException(Throwable cause) {
		super(cause);
	}
	
}
