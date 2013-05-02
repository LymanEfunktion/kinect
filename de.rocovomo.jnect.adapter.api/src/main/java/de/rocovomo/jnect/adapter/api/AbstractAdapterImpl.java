package de.rocovomo.jnect.adapter.api;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.jnect.bodymodel.PositionedElement;

public abstract class AbstractAdapterImpl<T extends PositionedElement> extends
		AdapterImpl implements RoCoVoMoAdapter {

	private T bodypart;

	@Override
	public abstract void notifyChanged(Notification msg);

	@Override
	public void setElement(PositionedElement element) {
		bodypart = (T) element;
		setTarget(element);
	}

}
