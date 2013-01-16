package de.rocovomo.jnect.adapter.provider.api;

import java.util.Dictionary;

import de.rocovomo.jnect.adapter.api.RoCoVoMoAdapter;

public interface AdapterProvider {

	RoCoVoMoAdapter getAdapter();
	
	Dictionary<String, Object> getAdapterProperties();
}
