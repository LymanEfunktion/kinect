package de.rocovomo.osgi.jnect.kinect;

import org.jnect.bodymodel.Body;
import org.jnect.core.KinectManager;
import org.jnect.gesture.GestureProxy;

import de.rocovomo.osgi.jnect.adapter.RoCoVoMoAdapter;
import de.rocovomo.osgi.jnect.adapter.spi.AdapterProvider;
import de.rocovomo.osgi.jnect.gesture.RoCoVoMoGesture;
import de.rocovomo.osgi.jnect.gesture.spi.GestureProvider;
//Necessary for adapter

public class Connector {

	private KinectManager kinect;
	private Body model;
	private GestureProxy proxy;
	
	private boolean isConnected;
	
	public Connector() {
		isConnected = initialize();
	}
	
	public boolean isConnected() {
		return isConnected;
	}
	
	private boolean initialize() {
		//TODO: log4j Logging
		try {
			kinect = KinectManager.INSTANCE;
			kinect.startKinect();
			proxy = GestureProxy.INSTANCE;
		} catch (Exception e) {
			return false;
		}
		addListeners();
		// KinectManager.INSTANCE.addSpeechListener(speechListener);
		// GestureProxy.INSTANCE.addGestureListener(gestureListener);
		kinect.startSkeletonTracking();
		model = kinect.getSkeletonModel();
//		KinectManager.INSTANCE.startSpeechRecognition();
		return true;
	}

	private void addToGestureProxy(RoCoVoMoGesture gesture) {
		//TODO: log4j Logging
		System.out.println("gesture:" + gesture.getClass().getSimpleName());
		proxy.addGestureDetector(gesture);
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

	public void connectGesture(GestureProvider provider) {
		//TODO: log4j Logging
		System.out.println("init:" + provider.getGestureProperties().get("gesture-type"));
		addToGestureProxy(provider.getGesture());
	}

	public void connectAdapter(AdapterProvider provider) {
		String type = (String) provider.getAdapterProperties().get("adapter-type");
		//TODO: log4j Logging
		System.out.println("init:" + type);
		if(type.equals("RightHand-Adapter")) {
			addRightHandAdapter(provider.getAdapter());
		}
		if (type.equals("LeftHand-Adapter")) {
			addLeftHandAdapter(provider.getAdapter());
		}
	}

	private void addLeftHandAdapter(RoCoVoMoAdapter adapter) {
		// TODO Auto-generated method stub
		System.out.println("adapter:" + adapter.getClass().getSimpleName());
		adapter.setElement(kinect.getSkeletonModel().getLeftHand());
		kinect.getSkeletonModel().getLeftHand().eAdapters().add(adapter);
	}

	private void addRightHandAdapter(RoCoVoMoAdapter adapter) {
		//TODO: log4j Logging
		System.out.println("adapter:" + adapter.getClass().getSimpleName());
		adapter.setElement(kinect.getSkeletonModel().getRightHand());
		kinect.getSkeletonModel().getRightHand().eAdapters().add(adapter);
		
	}

	class IThread extends Thread {
		public IThread() {
			
		}
		public void run() {
			try {
				while(true) {
					System.out.println(getWidthBetweenHands() + ":" + getHeightBetweenHands());
					sleep(500L);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void run() {
		IThread thread = new IThread();
	}
	
	private int getWidthBetweenHands() {
		return Math.round(model.getRightHand().getX()-model.getLeftHand().getX());
	}
	private int getHeightBetweenHands() {
		return Math.round(model.getLeftHand().getY()-model.getRightHand().getY());
	}
}