package de.rocovomo.action;

import org.jnect.core.SpeechListener;
import org.jnect.gesture.Gesture;
import org.jnect.gesture.GestureListener;

import de.rocovomo.action.api.Action;
import de.rocovomo.action.api.NoValidGestureListenerException;
import de.rocovomo.action.api.NoValidSpeechListenerException;
import de.rocovomo.jnect.gesture.RoCoVoMoGesture;
import de.rocovomo.robot.action.RobotAction;

public class TestAction extends Action {

	TestAction testAction;

	public TestAction(RobotAction impl, RoCoVoMoGesture gesture) {
		super(impl, gesture);
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
