package de.rocovomo.logging.osgi;

import static org.osgi.framework.BundleEvent.INSTALLED;
import static org.osgi.framework.BundleEvent.STARTED;
import static org.osgi.framework.BundleEvent.STARTING;
import static org.osgi.framework.BundleEvent.STOPPED;
import static org.osgi.framework.BundleEvent.STOPPING;
import static org.osgi.framework.BundleEvent.UNINSTALLED;

import org.apache.log4j.Logger;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;

public class BundleListenerImpl implements BundleListener {

	private final Logger Log = Logger.getLogger("BundleLogging");

	@Override
	public void bundleChanged(BundleEvent event) {
		int type = event.getType();
		Bundle bundle = event.getBundle();
		if (type == INSTALLED) {
			Log.info("Installed bundle: " + bundle);
		}
		if (type == UNINSTALLED) {
			Log.info("Uninstalled bundle: " + bundle);
		}
		if (type == STARTING) {
			Log.info("Starting bundle: " + bundle);
		}
		if (type == STOPPING) {
			Log.info("Stopping bundle: " + bundle);
		}
		if (type == STARTED) {
			Log.info("Started bundle: " + bundle);
		}
		if (type == STOPPED) {
			Log.info("Stopped bundle: " + bundle);
		}
	}

}
