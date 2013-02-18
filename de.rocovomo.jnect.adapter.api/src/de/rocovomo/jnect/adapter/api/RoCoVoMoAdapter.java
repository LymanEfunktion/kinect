package de.rocovomo.jnect.adapter.api;

import org.eclipse.emf.common.notify.Adapter;
import org.jnect.bodymodel.PositionedElement;

import de.rocovomo.osgi.api.GenericProvider;

public interface RoCoVoMoAdapter extends Adapter {
	/**
	 * TODO SE: wird das noch gebraucht? siehe dazu {@link GenericProvider}.getPid()
	 * <p>
	 * Value: String
	 */
	public static final String PID = "adapter-process-id";

	/**
	 * Value: String
	 */
	public static final String TYPE = "adapter-type";
	
	public void setElement(PositionedElement element);
}
