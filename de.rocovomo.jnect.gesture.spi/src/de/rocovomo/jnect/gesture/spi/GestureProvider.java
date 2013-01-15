package de.rocovomo.osgi.jnect.gesture.spi;

import java.util.Dictionary;

import de.rocovomo.osgi.jnect.gesture.RoCoVoMoGesture;

public interface GestureProvider {
	
	RoCoVoMoGesture getGesture();
	
	Dictionary<String, Object> getGestureProperties();
}
