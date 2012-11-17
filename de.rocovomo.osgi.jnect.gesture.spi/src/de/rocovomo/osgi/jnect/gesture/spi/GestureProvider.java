package de.rocovomo.osgi.jnect.gesture.spi;

import java.util.Dictionary;

import org.jnect.gesture.Gesture;

public interface GestureProvider {

	Gesture getGesture();
	
	Dictionary<String, Object> getGestureProperties();
}