package de.rocovomo.action.api;

import org.jnect.core.SpeechListener;
import org.jnect.gesture.GestureListener;

import de.rocovomo.jnect.gesture.api.RoCoVoMoGesture;
import de.rocovomo.robot.action.RobotAction;

// TODO javadoc
public abstract class Action {
	
	/**
	 * Value: String
	 */
	public static String TYPE = "action-type";

	public abstract GestureListener getGestureListener()
			throws NoValidGestureListenerException;

	public abstract SpeechListener getSpeechListener()
			throws NoValidSpeechListenerException;

	public abstract String getRequiredSpeechString();

	public abstract boolean isGestureEnabled();

	public abstract boolean isSpeechEnabled();

	protected RobotAction impl;
	private RoCoVoMoGesture gesture;

	public abstract void execute();

	public Action(RobotAction impl, RoCoVoMoGesture gesture) {
		this.impl = impl;
		this.gesture = gesture;
	}

	public RobotAction getImpl() {
		return impl;
	}

	public void setImpl(RobotAction impl) {
		this.impl = impl;
	}

	public RoCoVoMoGesture getRequiredGesture() {
		return this.gesture;
	};

}
