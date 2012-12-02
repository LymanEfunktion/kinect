package de.rocovomo.osgi.jnect.adapter.lefthand;

import java.util.Dictionary;
import java.util.Hashtable;

import de.rocovomo.osgi.jnect.adapter.RoCoVoMoAdapter;
import de.rocovomo.osgi.jnect.adapter.spi.AdapterProvider;

public class LeftHandAdapterProvider implements AdapterProvider {

	// TODO: Change PID
	private final static String PID = "132410379143";

	private final static String LEFT_HAND = "LeftHand-Adapter";

	private final Dictionary<String, Object> properties = new Hashtable<String, Object>();

	private final RoCoVoMoAdapter adapter;

	public LeftHandAdapterProvider() {
		properties.put(RoCoVoMoAdapter.PID, PID);
		properties.put(RoCoVoMoAdapter.TYPE, LEFT_HAND);
		adapter = new LeftHandAdapter();
	}

	@Override
	public RoCoVoMoAdapter getAdapter() {
		return adapter;
	}

	@Override
	public Dictionary<String, Object> getAdapterProperties() {
		return properties;
	}
}
