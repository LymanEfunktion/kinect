package old.toBeDeleted;

import org.jnect.bodymodel.Body;
import org.jnect.core.KinectManager;
import org.jnect.core.SpeechListener;
import org.jnect.gesture.Gesture;
import org.jnect.gesture.GestureListener;
import org.jnect.gesture.GestureProxy;

import de.rocovomo.jnect.kinect.api.GestureConnector;
import de.rocovomo.jnect.kinect.api.GestureListenerConnector;
import de.rocovomo.jnect.kinect.api.SpeechListenerConnector;

public class Connector implements GestureConnector, GestureListenerConnector,
		SpeechListenerConnector {
	
	private KinectManager kinect = KinectManager.INSTANCE;
	private Body mode = kinect.getSkeletonModel();
	private GestureProxy proxy = GestureProxy.INSTANCE;
	
	public Connector() {
		// TODO: log4j Logging
				try {
					this.kinect = KinectManager.INSTANCE;
					this.kinect.startKinect(); // when a Kinect is connected this blocks
												// until it is started
					this.proxy = GestureProxy.INSTANCE;
				} catch (Exception e) {
					//TODO: what now? Kinect not available
				}
	}
	
	@Override
	public void connectGesture(SpeechListener speechListener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnectGesture(SpeechListener speechListener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connectGesture(GestureListener gestureListener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnectGesture(GestureListener gestureListener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connectGesture(Gesture gesture) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnectGesture(Gesture gesture) {
		// TODO Auto-generated method stub
		
	}

}
