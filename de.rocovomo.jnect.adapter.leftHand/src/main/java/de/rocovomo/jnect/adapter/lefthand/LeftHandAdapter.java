package de.rocovomo.jnect.adapter.lefthand;

import org.eclipse.emf.common.notify.Notification;
import org.jnect.bodymodel.LeftHand;

import de.rocovomo.jnect.adapter.api.AbstractAdapterImpl;

public class LeftHandAdapter extends AbstractAdapterImpl<LeftHand> {

	@Override
	public void notifyChanged(Notification msg) {
		// System.out.println("x: " + leftHand.getX() + "| y: "
		// + leftHand.getY() + "| z: " + leftHand.getZ());
	}

}
