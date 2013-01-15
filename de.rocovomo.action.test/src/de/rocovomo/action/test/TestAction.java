package de.rocovomo.action.test;

import org.jnect.core.SpeechListener;
import org.jnect.gesture.Gesture;
import org.jnect.gesture.GestureListener;

import de.rocovomo.action.Action;
import de.rocovomo.action.NoValidGestureListenerException;
import de.rocovomo.action.NoValidSpeechListenerException;
import de.rocovomo.robot.action.RobotAction;

public class TestAction extends Action {

	TestAction testAction;

	public TestAction(RobotAction impl) {
		super(impl);
		testAction = this;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		impl.execute();
	}

	@Override
	public GestureListener getGestureListener()
			throws NoValidGestureListenerException {
		// TODO Auto-generated method stub
		return new GestureListener() {
			@Override
			public void notifyGestureDetected(Class<? extends Gesture> gesture) {
				if (testAction.isGestureEnabled()
						&& testAction.getRequiredGesture().equals(gesture)) {
					testAction.execute();
				}
			}
		};
	}

	@Override
	public SpeechListener getSpeechListener()
			throws NoValidSpeechListenerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends Gesture> getRequiredGesture() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRequiredSpeechString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isGestureEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSpeechEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
