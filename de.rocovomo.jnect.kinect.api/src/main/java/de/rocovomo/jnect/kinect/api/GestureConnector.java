package de.rocovomo.jnect.kinect.api;

import org.jnect.gesture.Gesture;

public interface GestureConnector {
	
	void connectGesture(Gesture gesture) throws GestureRecognitionNotStartedException;
	void disconnectGesture(Gesture gesture)throws GestureRecognitionNotStartedException;
}
