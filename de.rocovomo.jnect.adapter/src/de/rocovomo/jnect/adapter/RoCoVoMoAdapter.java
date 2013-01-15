package de.rocovomo.jnect.adapter;

import org.eclipse.emf.common.notify.Adapter;
import org.jnect.bodymodel.PositionedElement;

public interface RoCoVoMoAdapter extends Adapter {
	/**
	 * Value: String
	 */
	public static String PID = "adapter-process-id";

	/**
	 * Value: String
	 */
	public static String TYPE = "adapter-type";
	
	public void setElement(PositionedElement element);
}
