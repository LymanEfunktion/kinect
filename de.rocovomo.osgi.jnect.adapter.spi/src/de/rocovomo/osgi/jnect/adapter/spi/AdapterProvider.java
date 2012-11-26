package de.rocovomo.osgi.jnect.adapter.spi;

import java.util.Dictionary;

import de.rocovomo.osgi.jnect.adapter.JnectAdapter;

public interface AdapterProvider {

	JnectAdapter getAdapter();
	
	Dictionary<String, Object> getGestureProperties();
}
