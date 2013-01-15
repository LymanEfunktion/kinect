package de.rocovomo.jnect.adapter.spi;

import java.util.Dictionary;

import de.rocovomo.jnect.adapter.RoCoVoMoAdapter;

public interface AdapterProvider {

	RoCoVoMoAdapter getAdapter();
	
	Dictionary<String, Object> getAdapterProperties();
}
