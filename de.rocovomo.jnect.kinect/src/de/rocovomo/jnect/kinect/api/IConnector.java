package de.rocovomo.jnect.kinect.api;

import de.rocovomo.jnect.adapter.spi.AdapterProvider;
import de.rocovomo.jnect.gesture.spi.GestureProvider;

public interface IConnector {
	
	public boolean isConnected();
	public void connectGesture(GestureProvider provider);
	public void run();
	public void connectAdapter(AdapterProvider provider);
}
