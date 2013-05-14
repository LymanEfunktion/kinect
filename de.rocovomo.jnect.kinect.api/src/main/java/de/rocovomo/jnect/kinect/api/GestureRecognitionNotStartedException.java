package de.rocovomo.jnect.kinect.api;

public class GestureRecognitionNotStartedException extends KinectException{

	private static final long serialVersionUID = 1161031037024376158L;

	public GestureRecognitionNotStartedException() {
		super("Gesture Recognition is not Started");
	}
	
	public GestureRecognitionNotStartedException(Throwable cause) {
		super(cause);
	}
}
