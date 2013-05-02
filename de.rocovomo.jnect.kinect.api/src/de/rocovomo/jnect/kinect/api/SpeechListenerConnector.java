package de.rocovomo.jnect.kinect.api;

import org.jnect.core.SpeechListener;

public interface SpeechListenerConnector {

	public void connectGesture(SpeechListener speechListener);
	public void disconnectGesture(SpeechListener speechListener);

}
