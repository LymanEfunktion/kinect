package de.rocovomo.osgi.jnect.adapter.righthand;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.jnect.bodymodel.RightHand;

import de.rocovomo.osgi.jnect.adapter.JnectAdapter;

public class RightHandAdapter extends AdapterImpl implements JnectAdapter{
	
	private final RightHand rightHand;
	
	public RightHandAdapter(final RightHand rightHand) {
		this.rightHand = rightHand;
	}

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
		
	}
}
