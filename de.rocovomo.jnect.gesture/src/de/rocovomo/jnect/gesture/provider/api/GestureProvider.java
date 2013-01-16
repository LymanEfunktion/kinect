package de.rocovomo.jnect.gesture.provider.api;

import java.util.Dictionary;

import de.rocovomo.jnect.gesture.api.RoCoVoMoGesture;

public interface GestureProvider {
	
	RoCoVoMoGesture getGesture();
	Dictionary<String, Object> getGestureProperties();
}
