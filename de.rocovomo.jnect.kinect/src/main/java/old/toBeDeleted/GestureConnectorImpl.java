package old.toBeDeleted;

import org.jnect.gesture.Gesture;

import de.rocovomo.jnect.kinect.api.GestureConnector;

public class GestureConnectorImpl implements GestureConnector{
	
	private Kinect kinect;

	public GestureConnectorImpl(Kinect kinect) {
		this.kinect = kinect;
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
