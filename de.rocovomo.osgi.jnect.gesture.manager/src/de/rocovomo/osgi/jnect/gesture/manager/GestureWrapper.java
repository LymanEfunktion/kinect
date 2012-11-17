package de.rocovomo.osgi.jnect.gesture.manager;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.jnect.gesture.Gesture;
import org.osgi.framework.ServiceRegistration;

import de.rocovomo.osgi.jnect.gesture.spi.GestureProvider;

public class GestureWrapper extends Gesture {

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
