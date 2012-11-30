package de.rocovomo.osgi.jnect.adapter.righthand;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.jnect.bodymodel.PositionedElement;
import org.jnect.bodymodel.RightHand;

import de.rocovomo.osgi.jnect.adapter.RoCoVoMoAdapter;

public class RightHandAdapter extends AdapterImpl implements RoCoVoMoAdapter{
	
	private RightHand rightHand;
	
	public RightHandAdapter() {}

	public void notifyChanged(Notification notification) {
		System.out.println("x: " + rightHand.getX() + "| y: "
				+ rightHand.getY() + "| z: " + rightHand.getZ());
	}
	
	@Override
	public Notifier getTarget() {
		// TODO Auto-generated method stub
		return (Notifier) rightHand;
	}

	@Override
	public boolean isAdapterForType(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setTarget(Notifier arg0) {
		// TODO Auto-generated method stub
		target = arg0;
	}

	@Override
	public void setElement(PositionedElement element) {
		// TODO Auto-generated method stub
		rightHand = (RightHand) element;
		setTarget((Notifier) element);
	}
}
