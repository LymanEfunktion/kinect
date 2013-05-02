package de.rocovomo.speech.api;

import org.jnect.core.SpeechListener;

import de.rocovomo.action.api.SpeechDetector;

public interface SpeechFactoryService {

	SpeechListener createSpeechListenerForAction(String speechString,
			SpeechDetector action);
}
