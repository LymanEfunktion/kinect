package de.rocovomo.manager.speech;

import org.jnect.core.SpeechListener;

import de.rocovomo.action.api.SpeechDetector;
import de.rocovomo.speech.api.SpeechFactoryService;

public class SpeechFactory implements SpeechFactoryService {

	@Override
	public SpeechListener createSpeechListenerForAction(String speechString,
			SpeechDetector action) {
		return new SpeechListenerImpl(speechString, action);
	}

}
