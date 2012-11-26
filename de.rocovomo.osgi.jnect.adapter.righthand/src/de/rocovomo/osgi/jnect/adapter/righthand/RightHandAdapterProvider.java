package de.rocovomo.osgi.jnect.adapter.righthand;

import java.util.Dictionary;
import java.util.Hashtable;

import org.jnect.bodymodel.RightHand;

import de.rocovomo.osgi.jnect.adapter.JnectAdapter;
import de.rocovomo.osgi.jnect.adapter.spi.AdapterProvider;


public class RightHandAdapterProvider implements AdapterProvider {

	private final static String PID = "132410384143";

	private final static String RIGHT_HAND = "RightHand-Adapter";

	private final Dictionary<String, Object> properties = new Hashtable<String, Object>();
	
	private final JnectAdapter adapter;
	
	public RightHandAdapterProvider(RightHand rightHand) {
		System.out.println("Start Adapter");
		properties.put(JnectAdapter.PID, PID);
		properties.put(JnectAdapter.TYPE, RIGHT_HAND);
		adapter = new RightHandAdapter(rightHand);
	}

	@Override
	public JnectAdapter getAdapter() {
		return adapter;
	}

	@Override
	public Dictionary<String, Object> getGestureProperties() {
		return properties;
	}
}
