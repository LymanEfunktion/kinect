package de.rocovomo.jnect.gesture.provider;

import java.util.Dictionary;
import java.util.Hashtable;

import de.rocovomo.jnect.gesture.JumpGesture;
import de.rocovomo.jnect.gesture.RoCoVoMoGesture;
import de.rocovomo.jnect.gesture.spi.GestureProvider;

// TODO javadoc missing
public class JumpGestureProvider implements GestureProvider{

	private final static String PID = "132410384143";

	private final static String JUMP_GESTURE = "Jump-Gesture";

	private final Dictionary<String, Object> properties = new Hashtable<String, Object>();

	private final RoCoVoMoGesture gesture;
	
	public JumpGestureProvider() {
		properties.put(RoCoVoMoGesture.PID, PID);
		properties.put(RoCoVoMoGesture.TYPE, JUMP_GESTURE);
		gesture = new JumpGesture();
	}
	
	@Override
	public RoCoVoMoGesture getGesture() {
		return gesture;
	}

	@Override
	public Dictionary<String, Object> getGestureProperties() {
		return properties;
	}

}
