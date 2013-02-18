package de.rocovomo.jnect.gesture.api;

import de.rocovomo.osgi.api.AbstractGenericProvider;

public abstract class GestureProvider extends
		AbstractGenericProvider<RoCoVoMoGesture> {

	public GestureProvider(RoCoVoMoGesture provided, String type) {
		super(provided);
		addProperty(RoCoVoMoGesture.TYPE, type);
	}

	@Override
	public String getType() {
		return (String) getProperties().get(RoCoVoMoGesture.TYPE);
	}

}
