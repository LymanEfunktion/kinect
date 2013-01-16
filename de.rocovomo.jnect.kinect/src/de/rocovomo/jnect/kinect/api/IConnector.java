package de.rocovomo.jnect.kinect.api;

import de.rocovomo.action.provider.api.ActionProvider;
import de.rocovomo.jnect.adapter.RoCoVoMoAdapter;

public interface IConnector {
	
	public boolean isConnected();
	public void stop();
	public void connectAction(ActionProvider provider);
	public void disconnectAction(ActionProvider provider);
	public void connectAdapter(RoCoVoMoAdapter provider);
	public void disconnectAdapter(RoCoVoMoAdapter provider);
}
