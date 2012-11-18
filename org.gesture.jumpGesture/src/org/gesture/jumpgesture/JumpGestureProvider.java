package org.gesture.jumpgesture;

import java.util.Dictionary;
import java.util.Hashtable;

import org.gesture.RoCoVoMoGesture;
import org.gesture.spi.GestureProvider;

public class JumpGestureProvider implements GestureProvider{

	private final static String PID = "132410384143";

	private final static String JUMP_GESTURE = "Jump-Gesture";

	private final Dictionary<String, Object> properties = new Hashtable<String, Object>();

	private final RoCoVoMoGesture gesture;
	
	public JumpGestureProvider() {
		System.out.println("Start");
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
