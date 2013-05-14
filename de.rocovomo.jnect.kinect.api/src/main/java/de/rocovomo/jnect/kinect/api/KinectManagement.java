package de.rocovomo.jnect.kinect.api;

public interface KinectManagement {
	
	boolean start();
	boolean isRunning();
	boolean isSpeechRecognitionStarted();
	boolean isGestureRecognitionStarted();
	void addStatusObserver(KinectStatusObserver observer);
	void removeStatusObserver(KinectStatusObserver observer);
	boolean stop();
	
}
