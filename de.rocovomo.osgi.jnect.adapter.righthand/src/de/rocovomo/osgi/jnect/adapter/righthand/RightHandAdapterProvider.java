package de.rocovomo.osgi.jnect.adapter.righthand;

import java.util.Dictionary;
import java.util.Hashtable;

import org.jnect.bodymodel.RightHand;

import de.rocovomo.osgi.jnect.adapter.RoCoVoMoAdapter;
import de.rocovomo.osgi.jnect.adapter.spi.AdapterProvider;


public class RightHandAdapterProvider implements AdapterProvider {

	//TODO: Change PID
	private final static String PID = "132410384143";

	private final static String RIGHT_HAND = "RightHand-Adapter";

	private final Dictionary<String, Object> properties = new Hashtable<String, Object>();
	
	private final RoCoVoMoAdapter adapter;
	
	public RightHandAdapterProvider() {
		System.out.println("Start Adapter");
		properties.put(RoCoVoMoAdapter.PID, PID);
		properties.put(RoCoVoMoAdapter.TYPE, RIGHT_HAND);
		adapter = new RightHandAdapter();
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
