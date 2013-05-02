package de.rocovomo.jnect.kinect.api;

import org.jnect.gesture.GestureListener;

public interface GestureListenerConnector {
	
	public void connectGestureListener(GestureListener gestureListener);
	public void disconnectGestureListener(GestureListener gestureListener);
}
