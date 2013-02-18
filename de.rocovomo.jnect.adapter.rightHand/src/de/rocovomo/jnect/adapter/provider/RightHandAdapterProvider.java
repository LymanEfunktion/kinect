package de.rocovomo.jnect.adapter.provider;

import de.rocovomo.jnect.adapter.api.AdapterProvider;
import de.rocovomo.osgi.jnect.adapter.righthand.RightHandAdapter;

public class RightHandAdapterProvider extends AdapterProvider {

	private final static String RIGHT_HAND = "RightHand-Adapter";
	
	public RightHandAdapterProvider() {
		super(new RightHandAdapter(), RIGHT_HAND);
	}

}
