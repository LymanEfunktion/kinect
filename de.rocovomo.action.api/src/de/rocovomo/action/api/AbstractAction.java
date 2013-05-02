package de.rocovomo.action.api;
import java.util.Timer;
import java.util.TimerTask;

import de.rocovomo.action.api.GestureDetector;
import de.rocovomo.action.api.SpeechDetector;

public abstract class AbstractAction implements GestureDetector, SpeechDetector {

	private boolean speechDetected = false;
	private boolean gestureDetected = false;
	private Timer timer;
	private long defaultDelay = 800;
	private long gestureDetectionDelay = defaultDelay;
	private long speechDetectionDelay = defaultDelay;

	public AbstractAction() {
		this.timer = new Timer();
	}

	public AbstractAction(long gestureDetectionDelay, long speechDetectionDelay) {
		this.timer = new Timer();
		this.gestureDetectionDelay = gestureDetectionDelay;
		this.speechDetectionDelay = speechDetectionDelay;
	}

	@Override
	public void notifySpeechDetected() {
		setSpeechAsDetected();
		evaluateActionExecution();
		scheduleSpeechDetectionReset();
	}

	@Override
	public void notifyGestureDetected() {
		setGestureAsDetected();
		evaluateActionExecution();
		scheduleGestureDetectionReset();
	}

	private void setGestureAsDetected() {
		this.gestureDetected = true;
	}

	private void setSpeechAsDetected() {
		this.speechDetected = true;
	}

	private void scheduleGestureDetectionReset() {
		this.timer.schedule(new ResetGestureDetection(),
				this.gestureDetectionDelay);
	}

	private void scheduleSpeechDetectionReset() {
		this.timer.schedule(new ResetSpeechDetection(),
				this.speechDetectionDelay);
	}

	private void evaluateActionExecution() {
		if (this.gestureDetected && this.speechDetected) {
			execute();
		}
	}

	protected abstract void execute();

	class ResetGestureDetection extends TimerTask {

		@Override
		public void run() {
			gestureDetected = false;
		}

	}

	class ResetSpeechDetection extends TimerTask {

		@Override
		public void run() {
			speechDetected = false;
		}

	}
}
