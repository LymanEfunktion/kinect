package de.rocovomo.action.api;

import de.rocovomo.osgi.api.AbstractGenericProvider;

public abstract class ActionProvider extends AbstractGenericProvider<Action>{

	public ActionProvider(Action provided, String type) {
		super(provided);
		addProperty(Action.TYPE, type);
	}
	
	@Override
	public String getType() {
		return (String) getProperties().get(Action.TYPE);
	}
}