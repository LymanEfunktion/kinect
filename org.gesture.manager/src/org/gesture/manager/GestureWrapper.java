package org.gesture.manager;

import org.eclipse.emf.common.notify.Notification;
import org.gesture.RoCoVoMoGesture;
import org.gesture.spi.GestureProvider;

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
