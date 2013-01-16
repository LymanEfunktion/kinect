package de.rocovomo.jnect.kinect;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jnect.bodymodel.Body;
import org.jnect.core.KinectManager;
import org.jnect.core.SpeechListener;
import org.jnect.gesture.Gesture;
import org.jnect.gesture.GestureListener;
import org.jnect.gesture.GestureProxy;

import de.rocovomo.action.api.Action;
import de.rocovomo.action.api.NoValidGestureListenerException;
import de.rocovomo.action.api.NoValidSpeechListenerException;
import de.rocovomo.action.provider.api.ActionProvider;
import de.rocovomo.jnect.adapter.RoCoVoMoAdapter;
import de.rocovomo.jnect.kinect.api.IConnector;

public class Connector extends Observable implements IConnector {

	private KinectManager kinect;
	private Body model;
	private GestureProxy proxy;

	private static Logger logger = Logger.getLogger(Connector.class);

	private boolean isConnected;

	public Connector() {
		isConnected = initialize();
	}

	public boolean isConnected() {
		return isConnected;
	}

	private boolean initialize() {
		// TODO: log4j Logging
		try {
			this.kinect = KinectManager.INSTANCE;
			this.kinect.startKinect(); // when a Kinect is connected this blocks
										// until it is started
			this.proxy = GestureProxy.INSTANCE;
		} catch (Exception e) {
			return false;
		}
		// KinectManager.INSTANCE.addSpeechListener(speechListener);
		// GestureProxy.INSTANCE.addGestureListener(gestureListener);
		this.kinect.startSkeletonTracking();
		this.model = kinect.getSkeletonModel();
		// TODO Volker Werling: proper speech recognition starting, restarting and stopping
		if (!this.kinect.isSpeechRecognitionStarted()) {
			this.kinect.addSpeechListener(new SpeechListener() {
				
				@Override
				public void notifySpeech(String speech) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public Set<String> getWords() {
					Set<String> s = new HashSet<>();
					s.add("World");
					return s;
				}
			});
			this.kinect.startSpeechRecognition();
		}
		return true;
	}

	// public void connectAdapter(AdapterProvider provider) {
	// String type = (String) provider.getAdapterProperties().get(
	// "adapter-type");
	// // TODO: log4j Logging
	// logger.debug("init:" + type);
	// if (type.equals("RightHand-Adapter")) {
	// addRightHandAdapter(provider.getAdapter());
	// }
	// if (type.equals("LeftHand-Adapter")) {
	// addLeftHandAdapter(provider.getAdapter());
	// }
	// }
	//
	// private void addLeftHandAdapter(RoCoVoMoAdapter adapter) {
	// // TODO Auto-generated method stub
	// logger.debug("adapter:" + adapter.getClass().getSimpleName());
	// adapter.setElement(kinect.getSkeletonModel().getLeftHand());
	// kinect.getSkeletonModel().getLeftHand().eAdapters().add(adapter);
	// }
	//
	// private void addRightHandAdapter(RoCoVoMoAdapter adapter) {
	// // TODO: log4j Logging
	// logger.debug("adapter:" + adapter.getClass().getSimpleName());
	// adapter.setElement(kinect.getSkeletonModel().getRightHand());
	// kinect.getSkeletonModel().getRightHand().eAdapters().add(adapter);
	//
	// }
	//
	// class IThread extends Thread {
	// public IThread() {
	//
	// }
	//
	// public void run() {
	// try {
	// while (true) {
	// System.out.println(getWidthBetweenHands() + ":"
	// + getHeightBetweenHands());
	// logger.info(getWidthBetweenHands() + ":"
	// + getHeightBetweenHands());
	// sleep(500L);
	// }
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	// }
	// }
	//
	// public void run() {
	// IThread thread = new IThread();
	// thread.run();
	// }
	//
	// private float getWidthBetweenHands() {
	// return (model.getRightHand().getX() - model.getLeftHand().getX());
	// }
	//
	// private float getHeightBetweenHands() {
	// return (model.getLeftHand().getY() - model.getRightHand().getY());
	// }

	@Override
	public void stop() {
		this.kinect.stopSkeletonTracking();
		this.kinect.stopSpeechRecognition();
		this.kinect.stopKinect();
	}

	@Override
	public void connectAction(ActionProvider provider) {
		Action action = provider.getAction();
		if (action.isGestureEnabled()) {
			addGesture(action.getRequiredGesture());
			logger.info("Gesture added " + action.getRequiredGesture());
			try {
				addGestureListener(action.getGestureListener());
				logger.info("GestureListener added "
						+ action.getGestureListener());
			} catch (NoValidGestureListenerException e) {
				logger.error("No GestureListener available for: "
						+ provider.getAction() + ":"
						+ provider.getActionProperties().get(Action.TYPE));
			}
		}
		if (action.isSpeechEnabled()) {
			try {
				addSpeechListener(action.getSpeechListener());
				logger.info("Speechlistener added "
						+ action.getSpeechListener());
			} catch (NoValidSpeechListenerException e) {
				logger.error("No SpeechListener available for: "
						+ provider.getAction() + ":"
						+ provider.getActionProperties().get(Action.TYPE));
			}
		}
	}

	@Override
	public void disconnectAction(ActionProvider provider) {
		Action action = provider.getAction();
		if (action.isGestureEnabled()) {
			removeGesture(action.getRequiredGesture());
			try {
				removeGestureListener(action.getGestureListener());
			} catch (NoValidGestureListenerException e) {
				logger.error("No GestureListener available for: "
						+ provider.getAction() + ":"
						+ provider.getActionProperties().get(Action.TYPE));
			}
		}
		if (action.isSpeechEnabled()) {
			try {
				removeSpeechListener(action.getSpeechListener());
			} catch (NoValidSpeechListenerException e) {
				logger.error("No SpeechListener available for: "
						+ provider.getAction() + ":"
						+ provider.getActionProperties().get(Action.TYPE));
			}
		}
	}

	@Override
	public void connectAdapter(RoCoVoMoAdapter provider) {
		// TODO

	}

	@Override
	public void disconnectAdapter(RoCoVoMoAdapter provider) {
		// TODO

	}

	private void addGestureListener(GestureListener gestureListener) {
		this.proxy.addGestureListener(gestureListener);
	}

	private void removeGestureListener(GestureListener gestureListener) {
		this.proxy.removeGestureListener(gestureListener);
	}

	private void addGesture(Gesture gesture) {
		this.proxy.addGestureDetector(gesture);
	}

	private void removeGesture(Gesture gesture) {
		this.proxy.removeGestureDetector(gesture);
	}

	/**
	 * TODO fix: will only work for the first Speechlistener discovered,
	 * Speechrecognition needs to be stopped to add a Speechlistener at runtime
	 */
	private void addSpeechListener(SpeechListener speechListener) {
		this.kinect.addSpeechListener(speechListener);

	}

	/**
	 * TODO fix: will only work for the first Speechlistener discovered,
	 * Speechrecognition needs to be stopped to remove a Speechlistener at
	 * runtime
	 */
	private void removeSpeechListener(SpeechListener speechListener) {
		this.kinect.removeSpeechListener(speechListener);
	}

}