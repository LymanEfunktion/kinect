package de.rocovomo.jnect.kinect.api;

import org.jnect.core.SpeechListener;

public interface SpeechListenerConnector {

	public void connectSpeechListener(SpeechListener speechListener) throws SpeechRecognitionNotStartedException;
	public void disconnectSpeechListener(SpeechListener speechListener)throws SpeechRecognitionNotStartedException;

}
