package de.rocovomo.osgi.e4.rcp.view;

import javax.inject.Inject;

import org.eclipse.core.databinding.ObservablesManager;
import org.eclipse.swt.widgets.Composite;

public class KinectView {

	private ObservablesManager manager;
	
	@Inject
	public KinectView(Composite parent) {
		manager = new ObservablesManager();
		manager.runAndCollect(new Runnable() {
			public void run() {
			}
		});
	}
}
