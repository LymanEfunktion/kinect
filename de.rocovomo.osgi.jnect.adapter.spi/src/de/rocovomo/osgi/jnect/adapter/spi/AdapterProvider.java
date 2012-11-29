package de.rocovomo.osgi.jnect.adapter.spi;

import java.util.Dictionary;

import de.rocovomo.osgi.jnect.adapter.RoCoVoMoAdapter;

public interface AdapterProvider {

	RoCoVoMoAdapter getAdapter();
	
	Dictionary<String, Object> getAdapterProperties();
}
