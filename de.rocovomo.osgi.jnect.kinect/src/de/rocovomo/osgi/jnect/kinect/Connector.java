package de.rocovomo.osgi.jnect.kinect;

import java.util.ResourceBundle;

import org.jnect.core.KinectManager;

public class Connector {

	public boolean initialize() {
		KinectManager.INSTANCE.startKinect();

		addListeners();
		// KinectManager.INSTANCE.addSpeechListener(speechListener);
		// GestureProxy.INSTANCE.addGestureListener(gestureListener);
		KinectManager.INSTANCE.startSkeletonTracking();
		KinectManager.INSTANCE.startSpeechRecognition();

		addGestureDetectors();
		// GestureProxy.INSTANCE.addGestureDetector(new JumpGestureDetector());
		// GestureProxy.INSTANCE.addGestureDetector(new
		// CrouchGestureDetector());
		return true;
	}

	private void addGestureDetectors() {
		// TODO Auto-generated method stub
		ResourceBundle rb = ResourceBundle
				.getBundle("de.rocovomo.osgi.jnect.gesture");
		rb.getObject("paramString");
	}

	private void addListeners() {
		// TODO: Add Listeners
		// gestureListener = new GestureListener() {
		// @Override
		// public void notifyGestureDetected(
		// Class<? extends Gesture> gesture) {
		// if (currentState.isGestureEnabled()
		// && currentState.getRequiredGesture()
		// .equals(gesture)) {
		// performStateOperation();
		// }
		// }
		// };
		//
		// speechListener = new SpeechListener() {
		//
		// @Override
		// public void notifySpeech(String speech) {
		// if (currentState.isSpeechEnabled()
		// && currentState.getRequiredSpeechString()
		// .equalsIgnoreCase(speech)) {
		// performStateOperation();
		// }
		// }
		//
		// @Override
		// public Set<String> getWords() {
		// Set<String> words = new HashSet<String>();
		// words.add(DEBUG_START);
		// words.add(FIX_BUG);
		// words.add("bo");
		// return words;
		// }
		//
		// };
	}
}
