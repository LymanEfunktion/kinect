package de.rocovomo.action.provider;

import java.util.Dictionary;
import java.util.Hashtable;

import de.rocovomo.action.api.Action;
import de.rocovomo.action.provider.api.ActionProvider;

public class TestActionProvider implements ActionProvider{
	
	private final static String ACTION = "Jump-Action";

	private final Dictionary<String, Object> properties = new Hashtable<String, Object>();

	private final Action action;

	public TestActionProvider(Action action) {
		properties.put(Action.TYPE, ACTION);
		this.action = action;
	}
	
	@Override
	public Action getAction() {
		return action;
	}

	@Override
	public Dictionary<String, Object> getActionProperties() {
		return properties;
	}
	
}
