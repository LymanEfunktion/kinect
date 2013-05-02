package de.rocovomo.jnect.gesture.provider;

import de.rocovomo.jnect.gesture.api.GestureProvider;
import de.rocovomo.jnect.gesture.jump.JumpGesture;
import de.rocovomo.osgi.api.Type;

public class JumpGestureProvider extends GestureProvider {

	private final String JUMP_GESTURE = "Jump-Gesture";

	public JumpGestureProvider() {
		super(new JumpGesture(), Type.Gesture);
	}

}
