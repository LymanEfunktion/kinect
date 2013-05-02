package de.rocovomo.jnect.kinect.api;

import org.eclipse.emf.common.notify.Adapter;

public interface AdapterConnector {

	public void connectAdapter(Adapter adapter);
	public void disconnectAdapter(Adapter adapter);

}
