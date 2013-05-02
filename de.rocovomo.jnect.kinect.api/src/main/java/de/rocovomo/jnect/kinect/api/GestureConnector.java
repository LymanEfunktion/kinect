package de.rocovomo.jnect.kinect.api;

import org.jnect.gesture.Gesture;

public interface GestureConnector {
	
	public void connectGesture(Gesture gesture);
	public void disconnectGesture(Gesture gesture);
}
