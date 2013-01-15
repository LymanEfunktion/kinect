package de.rocovomo.action;

import org.jnect.core.SpeechListener;
import org.jnect.gesture.Gesture;
import org.jnect.gesture.GestureListener;

import de.rocovomo.robot.action.RobotAction;

public abstract class Action {

	public abstract GestureListener getGestureListener() throws NoValidGestureListenerException;

	public abstract SpeechListener getSpeechListener() throws NoValidSpeechListenerException;

	public abstract Class<? extends Gesture> getRequiredGesture();

	public abstract String getRequiredSpeechString();

	public abstract boolean isGestureEnabled();

	public abstract boolean isSpeechEnabled();
	
	protected RobotAction impl;
	
	public abstract void execute();
	
	public Action(RobotAction impl) {
		this.impl = impl;
	}
	
	public RobotAction getImpl() {
		return impl;
	}
	
	public void setImpl(RobotAction impl) {
		this.impl = impl;
	}
}
