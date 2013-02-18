package de.rocovomo.action.provider;

import de.rocovomo.action.api.Action;
import de.rocovomo.action.api.ActionProvider;

public class TestActionProvider extends ActionProvider{
	
	private final static String TYPE = "Jump-Action";

	public TestActionProvider(Action action) {
		super(action, TYPE);
	}

}
