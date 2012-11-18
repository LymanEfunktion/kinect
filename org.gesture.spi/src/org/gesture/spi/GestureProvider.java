package org.gesture.spi;

import java.util.Dictionary;

import org.gesture.RoCoVoMoGesture;

public interface GestureProvider {
	
	RoCoVoMoGesture getGesture();
	
	Dictionary<String, Object> getGestureProperties();
}
