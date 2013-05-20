/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package de.rocovomo.e4.rcp.hmmrecorder.view.preview;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import de.rocovomo.e4.rcp.hmmrecorder.model.Preview;
import de.rocovomo.e4.rcp.hmmrecorder.util.Colors;

public class PreviewShellE4 implements Colors
{
	private Shell shell;
	
	public PreviewShellE4(Display display, final Preview preview)
	{
		initShell(display, preview);
		shell.addListener(SWT.Traverse, new Listener()
		{
			public void handleEvent(Event event)
			{
				if (event.detail == SWT.TRAVERSE_ESCAPE)
				{
					shell.close();
					event.detail = SWT.TRAVERSE_NONE;
					event.doit = false;
				}
			}
		});
		preview.addPropertyChangeListener("save", new PropertyChangeListener()
		{
			@Override
			public void propertyChange(PropertyChangeEvent event)
			{
				shell.setText(getTitle(preview.getFile().getAbsolutePath(),
						(Boolean) event.getNewValue()));
			}
		});
	}

	private void initShell(Display display, Preview preview)
	{
		shell = new Shell(display, SWT.CLOSE | SWT.RESIZE);
		shell.setText(getTitle(preview.getFile().getAbsolutePath(), preview.isSaved()));
		shell.setBackground(COLOR_WHITE);
		shell.setLayout(new GridLayout(2, false));
		shell.setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	private String getTitle(String text, boolean isSaved)
	{
		return "Preview - " + text + (isSaved ? "" : " *");
	}

	public Shell getShell()
	{
		return shell;
	}

	public void open()
	{
		shell.open();
		shell.addListener(SWT.Close, new Listener()
		{
			@Override
			public void handleEvent(Event event)
			{
				shell.dispose();
			}
		});
	}
}
