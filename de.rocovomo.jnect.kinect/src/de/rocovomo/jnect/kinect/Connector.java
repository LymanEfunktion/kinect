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
import de.rocovomo.jnect.adapter.api.AdapterProvider;
import de.rocovomo.jnect.adapter.api.RoCoVoMoAdapter;
import de.rocovomo.jnect.gesture.api.RoCoVoMoGesture;
import de.rocovomo.jnect.gesture.provider.api.GestureProvider;
import de.rocovomo.jnect.kinect.api.IConnector;

public class Connector extends Observable implements IConnector {

	private KinectManager kinect;
	private Body model;
	private GestureProxy proxy;

	private static Logger logger = Logger.getLogger(Connector.class);

	private boolean isConnected;
	private boolean lefthand;
	private boolean righthand;

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
		this.kinect.startSkeletonTracking();
		this.model = kinect.getSkeletonModel();
		// TODO Volker Werling: proper speech recognition starting, restarting
		// and stopping
		if (!this.kinect.isSpeechRecognitionStarted()) {
			this.kinect.addSpeechListener(new SpeechListener() {

				@Override
				public void notifySpeech(String speech) {
					// TODO VW: remove
					System.out.println("Speech");
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

	public void connectAdapter(AdapterProvider provider) {
		String type = (String) provider.getProperties().get(
				RoCoVoMoAdapter.TYPE);
		if (type.equals("RightHand-Adapter")) {
			addRightHandAdapter(provider.getProvided());
		}
		if (type.equals("LeftHand-Adapter")) {
			addLeftHandAdapter(provider.getProvided());
		}
		logger.info("Adapter added " + provider.getProvided() + ":"
				+ provider.getProperties().get(RoCoVoMoAdapter.TYPE));
		evaluateRun();
	}

	// TODO test
	private void evaluateRun() {
		if (lefthand && righthand) {
			run();
		}
	}

	@Override
	public void disconnectAdapter(AdapterProvider provider) {
		String type = (String) provider.getProperties().get(
				RoCoVoMoAdapter.TYPE);
		if (type.equals("RightHand-Adapter")) {
			removeRightHandAdapter(provider.getProvided());
		}
		if (type.equals("LeftHand-Adapter")) {
			removeLeftHandAdapter(provider.getProvided());
		}
		logger.info("Adapter removed " + provider.getProvided() + ":"
				+ provider.getProperties().get(RoCoVoMoAdapter.TYPE));
	}

	// TODO
	private void removeLeftHandAdapter(RoCoVoMoAdapter adapter) {

	}

	// TODO
	private void removeRightHandAdapter(RoCoVoMoAdapter adapter) {

	}

	private void addLeftHandAdapter(RoCoVoMoAdapter adapter) {
		logger.debug("adapter:" + adapter.getClass().getSimpleName());
		adapter.setElement(kinect.getSkeletonModel().getLeftHand());
		kinect.getSkeletonModel().getLeftHand().eAdapters().add(adapter);
		lefthand = true;
	}

	private void addRightHandAdapter(RoCoVoMoAdapter adapter) {
		logger.debug("adapter:" + adapter.getClass().getSimpleName());
		adapter.setElement(kinect.getSkeletonModel().getRightHand());
		kinect.getSkeletonModel().getRightHand().eAdapters().add(adapter);
		righthand = true;

	}

	class IThread extends Thread {
		public IThread() {

		}

		public void run() {
			try {
				while (true) {
					System.out.println(getWidthBetweenHands() + ":"
							+ getHeightBetweenHands());
					logger.info(getWidthBetweenHands() + ":"
							+ getHeightBetweenHands());
					sleep(500L);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void run() {
		IThread thread = new IThread();
		thread.run();
	}

	private float getWidthBetweenHands() {
		return (model.getRightHand().getX() - model.getLeftHand().getX());
	}

	private float getHeightBetweenHands() {
		return (model.getLeftHand().getY() - model.getRightHand().getY());
	}

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
			try {
				addGestureListener(action.getGestureListener());
				logger.info("GestureListener added " + provider.getAction()
						+ ":" + provider.getActionProperties().get(Action.TYPE));
			} catch (NoValidGestureListenerException e) {
				logger.error("No GestureListener available for: "
						+ provider.getAction() + ":"
						+ provider.getActionProperties().get(Action.TYPE));
			}
		}
		if (action.isSpeechEnabled()) {
			try {
				addSpeechListener(action.getSpeechListener());
				logger.info("Speechlistener added " + provider.getAction()
						+ ":" + provider.getActionProperties().get(Action.TYPE));
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
			try {
				removeGestureListener(action.getGestureListener());
				logger.info("Gesturelistener removed " + provider.getAction()
						+ ":" + provider.getActionProperties().get(Action.TYPE));
			} catch (NoValidGestureListenerException e) {
				logger.error("No GestureListener available for: "
						+ provider.getAction() + ":"
						+ provider.getActionProperties().get(Action.TYPE));
			}
		}
		if (action.isSpeechEnabled()) {
			try {
				removeSpeechListener(action.getSpeechListener());
				logger.info("Speechlistener removed " + provider.getAction()
						+ ":" + provider.getActionProperties().get(Action.TYPE));
			} catch (NoValidSpeechListenerException e) {
				logger.error("No SpeechListener available for: "
						+ provider.getAction() + ":"
						+ provider.getActionProperties().get(Action.TYPE));
			}
		}
	}

	@Override
	public void connectGesture(GestureProvider provider) {
		RoCoVoMoGesture gesture = provider.getGesture();
		addGesture(gesture);
		logger.info("Gesture added " + provider.getGesture() + ":"
				+ provider.getGestureProperties().get(RoCoVoMoGesture.TYPE));
	}

	@Override
	public void disconnectGesture(GestureProvider provider) {
		RoCoVoMoGesture gesture = provider.getGesture();
		removeGesture(gesture);
		logger.info("Gesture removed " + provider.getGesture() + ":"
				+ provider.getGestureProperties().get(RoCoVoMoGesture.TYPE));
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