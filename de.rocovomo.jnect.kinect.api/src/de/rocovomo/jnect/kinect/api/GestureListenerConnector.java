package de.rocovomo.jnect.kinect.api;

import org.jnect.gesture.GestureListener;

public interface GestureListenerConnector {
	
	public void connectGesture(GestureListener gestureListener);
	public void disconnectGesture(GestureListener gestureListener);
}
