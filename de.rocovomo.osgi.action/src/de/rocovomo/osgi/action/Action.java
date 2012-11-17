package de.rocovomo.osgi.action;

import org.jnect.core.SpeechListener;
import org.jnect.gesture.Gesture;
import org.jnect.gesture.GestureListener;

public interface Action {

	GestureListener getGestureListener() throws NoValidGestureListenerException;
	
	SpeechListener getSpeechListener() throws NoValidSpeechListenerException;
	
	Class<? extends Gesture> getRequiredGesture();

	String getRequiredSpeechString();

	boolean isGestureEnabled();

	boolean isSpeechEnabled();
}
