package de.rocovomo.action.api;

import de.rocovomo.osgi.api.AbstractGenericProvider;

public abstract class ActionProvider extends AbstractGenericProvider<SpeechDetector>{

	public ActionProvider(SpeechDetector provided, String type) {
		super(provided);
		addProperty(SpeechDetector.TYPE, type);
	}
	
	@Override
	public String getType() {
		return (String) getProperties().get(SpeechDetector.TYPE);
	}
}