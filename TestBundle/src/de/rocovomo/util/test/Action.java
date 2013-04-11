package de.rocovomo.util.test;

import java.util.Observer;

public abstract class Action implements Observer{

	protected Impl impl;
	
	public abstract void act();
	
	public Action(Impl impl) {
		this.impl = impl;
	}
	
	public Impl getImpl() {
		return impl;
	}
	
	public void setImpl(Impl impl) {
		this.impl = impl;
	}
}
