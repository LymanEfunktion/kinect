package de.rocovomo.jnect.adapter.lefthand;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.jnect.bodymodel.LeftHand;
import org.jnect.bodymodel.PositionedElement;

import de.rocovomo.jnect.adapter.api.RoCoVoMoAdapter;


public class LeftHandAdapter extends AdapterImpl implements RoCoVoMoAdapter {

	private LeftHand leftHand;
	
	public LeftHandAdapter() {}
	
	public void notifyChanged(Notification notification) {
//		System.out.println("x: " + leftHand.getX() + "| y: "
//				+ leftHand.getY() + "| z: " + leftHand.getZ());
	}
	
	@Override
	public Notifier getTarget() {
		// TODO Auto-generated method stub
		return (Notifier) leftHand;
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
		leftHand = (LeftHand) element;
		setTarget((Notifier) element);
	}	
}
