package de.rocovomo.jnect.adapter.api;

import de.rocovomo.osgi.api.AbstractGenericProvider;
import de.rocovomo.osgi.api.Type;


/**
 * Implementation of Abstract Class {@link AbstractGenericProvider}, <br />
 * this implementation provides a {@link RoCoVoMoAdapter}
 * 
 * @author voowoo <a href="mailto:vowe91@gmail.com">vowe91@gmail.com</a>
 * 
 */
public abstract class AdapterProvider extends
		AbstractGenericProvider<RoCoVoMoAdapter> {

	private AdapterType adapterType;

	public AdapterProvider(RoCoVoMoAdapter adapter, AdapterType adapterType) {
		super(adapter,Type.Adapter);
		this.adapterType = adapterType;
	}
}
