package de.rocovomo.action;

import java.util.HashSet;
import java.util.Set;

import org.jnect.core.SpeechListener;
import org.jnect.gesture.Gesture;
import org.jnect.gesture.GestureListener;

import de.rocovomo.action.api.Action;
import de.rocovomo.action.api.NoValidGestureListenerException;
import de.rocovomo.action.api.NoValidSpeechListenerException;
import de.rocovomo.jnect.gesture.api.RoCoVoMoGesture;
import de.rocovomo.robot.action.RobotAction;

public class TestAction extends Action {

	TestAction testAction;

	private String speechString;

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
		return new SpeechListener() {

			Set<String> set = new HashSet<String>();

			@Override
			public void notifySpeech(String speech) {
				System.out.println("Speech " + speech + " recognized");

			}

			@Override
			public Set<String> getWords() {
				if (this.set.isEmpty()) {
					this.set.add(speechString);
				}
				return set;
			}
		};
	}

	@Override
	public String getRequiredSpeechString() {
		return speechString;
	}

	@Override
	public boolean isGestureEnabled() {
		return true;
	}

	@Override
	public boolean isSpeechEnabled() {
		return true;
	}

}
