package de.rocovomo.osgi.jnect.kinect;

import org.eclipse.emf.common.notify.Adapter;
import org.jnect.bodymodel.Body;
import org.jnect.bodymodel.PositionedElement;
import org.jnect.bodymodel.RightHand;
import org.jnect.core.KinectManager;
import org.jnect.gesture.GestureProxy;

import org.eclipse.emf.common.notify.Notification;
import org.jnect.core.SpeechListener;

import de.rocovomo.osgi.jnect.adapter.RoCoVoMoAdapter;
import de.rocovomo.osgi.jnect.adapter.spi.AdapterProvider;
import de.rocovomo.osgi.jnect.gesture.RoCoVoMoGesture;
import de.rocovomo.osgi.jnect.gesture.spi.GestureProvider;

public class Connector {

	public static boolean initialize() {
		try {
		KinectManager.INSTANCE.startKinect();
		} catch (Exception e) {
			return false;
		}
		addListeners();
		// KinectManager.INSTANCE.addSpeechListener(speechListener);
		// GestureProxy.INSTANCE.addGestureListener(gestureListener);
		KinectManager.INSTANCE.startSkeletonTracking();
//		KinectManager.INSTANCE.startSpeechRecognition();
		return true;
		
//		final Adapter leftHandAdapter = new  LeftHandAdapter(window);
//		final Adapter rightHandAdapter = new RightHandAdapter(window);
		
//		kinectManager.startSkeletonTracking();
//		skeletonModel=kinectManager.getSkeletonModel();
//		kinectManager.getSkeletonModel().getLeftHand().eAdapters().add(leftHandAdapter );
//		kinectManager.getSkeletonModel().getRightHand().eAdapters().add(rightHandAdapter );
		
	}

	private static void addToGestureProxy(RoCoVoMoGesture gesture) {
		System.out.println("gesture:" + gesture.getClass().getSimpleName());
		GestureProxy.INSTANCE.addGestureDetector(gesture);
	}

	private static void addListeners() {
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

	public static void connectGesture(GestureProvider provider) {
		// TODO Geste anmelden ...
		System.out.println("init:" + provider.getGestureProperties().get("gesture-type"));
		addToGestureProxy(provider.getGesture());
	}

	public static void connectAdapter(AdapterProvider provider) {
		// TODO Auto-generated method stub
		System.out.println("init:" + provider.getAdapterProperties().get("adapter-type"));
		final RoCoVoMoAdapter adapter = provider.getAdapter();
		final Body body = KinectManager.INSTANCE.getSkeletonModel();
		adapter.setElement(body.getRightHand());
		KinectManager kinectManager = KinectManager.INSTANCE;
//		kinectManager.startKinect();
//		final RightHand rightHand = KinectManager.INSTANCE.getSkeletonModel().getRightHand();
		kinectManager.getSkeletonModel().getRightHand().eAdapters().add(adapter);
//		rightHand.
	}
}
