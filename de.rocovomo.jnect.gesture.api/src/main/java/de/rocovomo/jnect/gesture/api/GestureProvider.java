package de.rocovomo.jnect.gesture.api;

import org.jnect.gesture.Gesture;

import de.rocovomo.osgi.api.AbstractGenericProvider;
import de.rocovomo.osgi.api.Type;

public abstract class GestureProvider extends AbstractGenericProvider<Gesture> {

	public GestureProvider(RoCoVoMoGesture provided, Type type) {
		super(provided, type);
	}

}
