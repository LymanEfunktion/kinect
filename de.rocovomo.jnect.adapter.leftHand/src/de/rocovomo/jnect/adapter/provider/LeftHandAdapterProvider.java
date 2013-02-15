package de.rocovomo.jnect.adapter.provider;

import de.rocovomo.jnect.adapter.api.AdapterProvider;
import de.rocovomo.jnect.adapter.lefthand.LeftHandAdapter;

public class LeftHandAdapterProvider extends AdapterProvider {

	private final static String LEFT_HAND = "LeftHand-Adapter";
	
	public LeftHandAdapterProvider() {
		super(new LeftHandAdapter(),LEFT_HAND);
	}

}

