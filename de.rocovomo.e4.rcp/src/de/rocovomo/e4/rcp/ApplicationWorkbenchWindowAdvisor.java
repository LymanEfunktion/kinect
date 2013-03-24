package de.rocovomo.e4.rcp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor
{

	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer)
	{
		super(configurer);
	}

	/*
	 * @see org.eclipse.ui.application.WorkbenchWindowAdvisor#preWindowOpen()
	 */
	@Override
	public void preWindowOpen()
	{
	}

	public boolean preWindowShellClose()
	{
		final TrayItem trayItem = new TrayItem(Display.getDefault().getSystemTray(),
				SWT.NONE);
		trayItem.setText("RoCoVoMo App");
		trayItem.addSelectionListener(new SelectionListener()
		{

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				System.out.println("tutut");
				Shell workbenchWindowShell = getWindowConfigurer().getWindow().getShell();
				workbenchWindowShell.setVisible(true);
				workbenchWindowShell.setActive();
				workbenchWindowShell.setFocus();
				workbenchWindowShell.setMinimized(false);
				trayItem.dispose();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e)
			{
				// TODO Auto-generated method stub

			}

		});
		getWindowConfigurer().getWindow().getShell().setVisible(false);
		return false;
	}
}
