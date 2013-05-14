package de.rocovomo.jnect.kinect.api;

public class SpeechRecognitionNotStartedException extends KinectException {

	private static final long serialVersionUID = 2829191286384177898L;

	private static final String message = "Speech recognition component is not started";
	
	public SpeechRecognitionNotStartedException() {
		super(message);
	}

	public SpeechRecognitionNotStartedException(String message) {
		super(message);
	}

	public SpeechRecognitionNotStartedException(Throwable cause) {
		super(message,cause);
	}

	public SpeechRecognitionNotStartedException(String message, Throwable cause) {
		super(message,cause);
	}
}
