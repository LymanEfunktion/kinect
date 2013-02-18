package de.rocovomo.jnect.gesture.provider;

import de.rocovomo.jnect.gesture.api.GestureProvider;
import de.rocovomo.jnect.gesture.jump.JumpGesture;

// TODO javadoc missing
public class JumpGestureProvider extends GestureProvider{

	private final static String JUMP_GESTURE = "Jump-Gesture";
	
	public JumpGestureProvider() {
		super(new JumpGesture(),JUMP_GESTURE);
	}

}
