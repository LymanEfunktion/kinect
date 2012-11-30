package de.rocovomo.osgi.jnect.gesture.manager;

import org.eclipse.emf.common.notify.Notification;
import de.rocovomo.osgi.jnect.gesture.RoCoVoMoGesture;
import de.rocovomo.osgi.jnect.gesture.spi.GestureProvider;

@Deprecated
public class GestureWrapper extends RoCoVoMoGesture {

	private GestureProvider delegate;
	
	public GestureWrapper(GestureProvider delegate) {
		this.delegate = delegate;
	}

	@Override
	protected boolean isGestureDetected(Notification notification) {
		// TODO Auto-generated method stub
		return false;
	}
}
