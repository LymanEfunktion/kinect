package de.rocovomo.action;

import org.jnect.core.SpeechListener;
import org.jnect.gesture.Gesture;
import org.jnect.gesture.GestureListener;

import de.rocovomo.robot.action.RobotActionImpl;

public abstract class Action {

	public abstract GestureListener getGestureListener() throws NoValidGestureListenerException;

	public abstract SpeechListener getSpeechListener() throws NoValidSpeechListenerException;

	public abstract Class<? extends Gesture> getRequiredGesture();

	public abstract String getRequiredSpeechString();

	public abstract boolean isGestureEnabled();

	public abstract boolean isSpeechEnabled();
	
	protected RobotActionImpl impl;
	
	public abstract void execute();
	
	public Action(RobotActionImpl impl) {
		this.impl = impl;
	}
	
	public RobotActionImpl getImpl() {
		return impl;
	}
	
	public void setImpl(RobotActionImpl impl) {
		this.impl = impl;
	}
}
