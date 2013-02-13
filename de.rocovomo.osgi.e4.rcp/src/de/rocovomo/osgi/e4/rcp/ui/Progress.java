package de.rocovomo.osgi.e4.rcp.ui;

import org.eclipse.core.runtime.IProgressMonitor;

public class Progress implements IProgressMonitor{

	@Override
	public void beginTask(String name, int totalWork) {
		// TODO Auto-generated method stub
		System.out.println("wo");
	}

	@Override
	public void done() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalWorked(double work) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCanceled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCanceled(boolean value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTaskName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void subTask(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void worked(int work) {
		// TODO Auto-generated method stub
		
	}

}
