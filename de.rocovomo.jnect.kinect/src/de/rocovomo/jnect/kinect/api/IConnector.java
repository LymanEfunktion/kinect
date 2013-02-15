package de.rocovomo.jnect.kinect.api;

import de.rocovomo.action.provider.api.ActionProvider;
import de.rocovomo.jnect.adapter.api.AdapterProvider;
import de.rocovomo.jnect.gesture.provider.api.GestureProvider;

public interface IConnector {
	
	public boolean isConnected();
	public void stop();
	public void connectAction(ActionProvider provider);
	public void disconnectAction(ActionProvider provider);
	public void connectAdapter(AdapterProvider adapter);
	public void disconnectAdapter(AdapterProvider serviceObject);
	public void connectGesture(GestureProvider gesture);
	public void disconnectGesture(GestureProvider serviceObject);
}
