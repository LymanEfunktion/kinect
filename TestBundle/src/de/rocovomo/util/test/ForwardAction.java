package de.rocovomo.util.test;

import java.util.Observable;

public class ForwardAction extends Action {

	public ForwardAction(Impl impl) {
		super(impl);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable paramObservable, Object paramObject) {
		// TODO Auto-generated method stub
		if (paramObject.toString().startsWith("f")) {
			act();
		}
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub
		impl.act();
	}

}
