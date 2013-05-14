package de.rocovomo.jnect.kinect.api;

public class GestureRecognitionNotStartedException extends KinectException {

	private static final long serialVersionUID = 1161031037024376158L;

	private static final String message = "Gesture Recognition Not Started";

	public GestureRecognitionNotStartedException() {
		super(message);
	}

	public GestureRecognitionNotStartedException(String message) {
		super(message);
	}

	public GestureRecognitionNotStartedException(Throwable cause) {
		super(message, cause);
	}

	public GestureRecognitionNotStartedException(String message, Throwable cause) {
		super(message, cause);
	}
}
