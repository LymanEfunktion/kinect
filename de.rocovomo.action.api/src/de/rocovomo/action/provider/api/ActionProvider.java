package de.rocovomo.action.provider.api;

import java.util.Dictionary;

import de.rocovomo.action.api.Action;
// TODO javadoc
public interface ActionProvider {
	
	Action getAction();
	Dictionary<String, Object> getActionProperties();

}