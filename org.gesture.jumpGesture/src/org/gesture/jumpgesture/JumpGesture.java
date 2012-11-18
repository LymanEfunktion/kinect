package org.gesture.jumpgesture;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.gesture.RoCoVoMoGesture;
import org.jnect.bodymodel.Head;
import org.jnect.bodymodel.LeftFoot;
import org.jnect.bodymodel.PositionedElement;
import org.jnect.bodymodel.RightFoot;
import org.jnect.gesture.util.MovingAverageCalculator;

public class JumpGesture extends RoCoVoMoGesture {

	private static final int NUM_PERIODS = 10;
	private static final float THRESHOLD_HEAD = 0.1f;
	private static final float THRESHOLD_FOOT = 0.1f;

	private MovingAverageCalculator yMovingAvgHead;
	private MovingAverageCalculator yMovingAvgFoot;

	private boolean gestureHead = false;
	private boolean gestureFootLeft = false;
	private boolean gestureFootRight = false;

	private boolean alreadyNotified = false;

	public JumpGesture() {
		this.yMovingAvgHead = new MovingAverageCalculator(NUM_PERIODS);
		// Two feet in one moving average
		this.yMovingAvgFoot = new MovingAverageCalculator(NUM_PERIODS * 2);
	}

	@Override
	public boolean isGestureDetected(Notification notification) {
		if (notification.getEventType() == Notification.SET
				&& notification.wasSet()) {
			EAttribute feature = (EAttribute) notification.getFeature();
			PositionedElement humanBodyPart = (PositionedElement) notification
					.getNotifier();

			if ("y".equals(feature.getName())) {
				float sensorValue = notification.getNewFloatValue();

				if (Head.class.isInstance(humanBodyPart)) {
					this.yMovingAvgHead.addValue(sensorValue);
					float avgHeadValue = this.yMovingAvgHead.getMovingAvg();
					gestureHead = (sensorValue > avgHeadValue + THRESHOLD_HEAD);
				} else if (LeftFoot.class.isInstance(humanBodyPart)) {
					this.yMovingAvgFoot.addValue(sensorValue);
					float avgFootValue = this.yMovingAvgFoot.getMovingAvg();
					gestureFootLeft = (sensorValue > avgFootValue
							+ THRESHOLD_FOOT);
				} else if (RightFoot.class.isInstance(humanBodyPart)) {
					this.yMovingAvgFoot.addValue(sensorValue);
					float avgFootValue = this.yMovingAvgFoot.getMovingAvg();
					gestureFootRight = (sensorValue > avgFootValue
							+ THRESHOLD_FOOT);
				}

				if (gestureHead && gestureFootLeft && gestureFootRight) {
					if (!this.alreadyNotified) {
						this.alreadyNotified = true;
						return true;
					}
				} else {
					this.alreadyNotified = false;
				}
			}
		}
		return false;
	}
}
