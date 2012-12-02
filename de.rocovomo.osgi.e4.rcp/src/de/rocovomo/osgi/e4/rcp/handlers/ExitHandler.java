package de.rocovomo.osgi.e4.rcp.handlers;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.e4.ui.workbench.modeling.IWindowCloseHandler;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TrayItem;

public class ExitHandler {

	@Execute
	public void execute(IWorkbench workbench, IEclipseContext context,
			@Named(IServiceConstants.ACTIVE_SHELL) Shell shell)
			throws InvocationTargetException, InterruptedException {
		close(shell);
	}

	public boolean close(final Shell shell) {
		System.out.println("adsf");
		// TODO Auto-generated method stub
		
		final TrayItem trayItem = new TrayItem(shell.getDisplay().getDefault().getSystemTray(), SWT.NONE);
    	trayItem.setText("RoCoVoMo App");
    	Image trayImage = ImageDescriptor.createFromFile(getClass(), "/icons/sample.gif").createImage(shell.getDisplay());
    	trayItem.setImage(trayImage);
    	trayItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("tutut");
				shell.setVisible(true);
				shell.setActive();
				shell.setFocus();
				shell.setMinimized(false);
				trayItem.dispose();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
    		
    	});
    	shell.setVisible(false);
    	return false;
	}
}
