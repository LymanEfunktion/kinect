package de.rocovomo.osgi.e4.rcp;

import org.eclipse.core.runtime.IProduct;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.branding.IProductConstants;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator{

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
//		PlatformUI.getPreferenceStore().putValue(IProductConstants.STARTUP_PROGRESS_RECT, "0,5,100,20");
//		PlatformUI.getPreferenceStore().putValue(IProductConstants.STARTUP_FOREGROUND_COLOR, "124316");
		PlatformUI.getPreferenceStore().setDefault(IWorkbenchPreferenceConstants.SHOW_PROGRESS_ON_STARTUP,true);
		String test = PlatformUI.getPreferenceStore().getString(IWorkbenchPreferenceConstants.SHOW_PROGRESS_ON_STARTUP);
//		IProductConstants.STARTUP_PROGRESS_RECT
		System.out.println(test);
		IProduct product = Platform.getProduct();
		System.out.println(product.getProperty(IProductConstants.STARTUP_FOREGROUND_COLOR));
		System.out.println(product.getProperty(IProductConstants.STARTUP_PROGRESS_RECT));
		System.out.println(product.getProperty(IProductConstants.STARTUP_MESSAGE_RECT));
		Activator.context = bundleContext;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}
}
