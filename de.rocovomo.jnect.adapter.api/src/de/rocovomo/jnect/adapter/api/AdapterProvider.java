package de.rocovomo.jnect.adapter.api;

import de.rocovomo.osgi.api.AbstractGenericProvider;

/**
 * Implementation of Abstract Class {@link AbstractGenericProvider}, <br />
 * this implementation provides a {@link RoCoVoMoAdapter}
 * 
 * @author voowoo <a href="mailto:vowe91@gmail.com">vowe91@gmail.com</a>
 * 
 */
public abstract class AdapterProvider extends
		AbstractGenericProvider<RoCoVoMoAdapter> {

	public AdapterProvider(RoCoVoMoAdapter adapter, String adapterType) {
		super(adapter);
		addProperty(RoCoVoMoAdapter.TYPE, adapterType);
	}

	@Override
	public String getType() {
		return (String) getProperties().get(RoCoVoMoAdapter.TYPE);
	}
}
