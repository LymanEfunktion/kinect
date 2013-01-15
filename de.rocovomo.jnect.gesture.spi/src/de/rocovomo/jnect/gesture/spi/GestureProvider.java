package de.rocovomo.jnect.gesture.spi;

import java.util.Dictionary;

import de.rocovomo.jnect.gesture.RoCoVoMoGesture;

public interface GestureProvider {
	
	RoCoVoMoGesture getGesture();
	
	Dictionary<String, Object> getGestureProperties();
}
