package de.rocovomo.jnect.adapter.api;

import org.eclipse.emf.common.notify.Adapter;
import org.jnect.bodymodel.PositionedElement;

public interface RoCoVoMoAdapter extends Adapter {
	/**
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
