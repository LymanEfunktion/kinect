package de.rocovomo.manager.speech;

import java.util.HashSet;
import java.util.Set;

import org.jnect.core.SpeechListener;

import de.rocovomo.action.api.SpeechDetector;

public class SpeechListenerImpl extends SpeechListener {

	private Set<String> wordSet = new HashSet<>();
	private SpeechDetector action;
	private String word;

	public SpeechListenerImpl(String speechString, SpeechDetector action) {
		this.word = speechString;
		this.wordSet.add(speechString);
		this.action = action;
	}

	@Override
	public void notifySpeech(String speech) {
		if (speech.equals(word)) {
			this.action.notifySpeechDetected();
		}
	}

	@Override
	public Set<String> getWords() {
		return this.wordSet;
	}

}
