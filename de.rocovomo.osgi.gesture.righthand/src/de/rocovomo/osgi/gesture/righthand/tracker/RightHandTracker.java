package de.rocovomo.osgi.gesture.righthand.tracker;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.jnect.bodymodel.RightHand;
import org.jnect.core.KinectManager;

public class RightHandTracker {

	public static RightHandTracker INSTANCE = new RightHandTracker();

	private RightHandTracker() {

	};

	public void printRightHandPosition() {
		KinectManager.INSTANCE.startKinect();
		KinectManager.INSTANCE.startSkeletonTracking();
		final RightHand rightHand = KinectManager.INSTANCE.getSkeletonModel()
				.getRightHand();
		rightHand.eAdapters().add(new Adapter() {
			@Override
			public void notifyChanged(Notification notification) {
				System.out.println("x: " + rightHand.getX() + "| y: "
						+ rightHand.getY() + "| z: " + rightHand.getZ());
			}

			@Override
			public Notifier getTarget() {
				// TODO Auto-generated method stub
				return rightHand;
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
			
		});
	}
	
	public void stop() {
		KinectManager.INSTANCE.stopKinect();
	}
}
