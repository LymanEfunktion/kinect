package de.rocovomo.osgi.jnect.gesture.jumpgesture;

import java.util.Dictionary;
import java.util.Hashtable;

import org.jnect.gesture.Gesture;

import de.rocovomo.osgi.jnect.gesture.GestureMetaData;
import de.rocovomo.osgi.jnect.gesture.spi.GestureProvider;

public class JumpGestureProvider implements GestureProvider, GestureMetaData {

	private static final String PID = "132410384143";

	private static final String JUMP_GESTURE = "Jump-Gesture";

	private final Dictionary<String, Object> properties = new Hashtable<String, Object>();

	private final Gesture gesture;
	
	public JumpGestureProvider() {
		properties.put(GestureMetaData.PID, PID);
		properties.put(GestureMetaData.TYPE, JUMP_GESTURE);
		gesture = new JumpGesture();
	}
	
	@Override
	public Gesture getGesture() {
		return gesture;
	}

	@Override
	public Dictionary<String, Object> getGestureProperties() {
		return properties;
	}

}
