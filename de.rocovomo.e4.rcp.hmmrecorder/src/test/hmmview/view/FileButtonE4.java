/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package test.hmmview.view;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.osgi.framework.Bundle;

public class FileButtonE4 extends AbstractWidgetElement
{
	public FileButtonE4(Composite parent)
	{
		addLabel("File", parent, new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING, 2, false, false,
				1, 1));
		final Label lFileURL = addLabel(" ", parent, SWT.BORDER, new GridData(GridData.FILL, -1,
				false, false, 3, 1));

		Button button = addButton("...", parent, SWT.PUSH, new GridData());

		// TODO: change file properties
		final FileDialog dialog = new FileDialog(parent.getShell(), SWT.SAVE);
		dialog.setFilterNames(new String[] { "Gesture Files", "All Files (*.*)" });
		dialog.setFilterExtensions(new String[] { "*.gf", "*.*" });
		Bundle bundle = Platform.getBundle("de.rocovomo.e4.rcp.hmmrecorder");
		URL fileURL = bundle.getEntry("/data");
		try
		{
			String path = FileLocator.resolve(fileURL).toURI().getPath();
			dialog.setFilterPath(path.substring(1));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		dialog.setFileName("GestureData.gf");

		button.addSelectionListener(new SelectionListener()
		{
			@Override
			public void widgetSelected(SelectionEvent event)
			{
				String url = dialog.open();
				if (url != null)
				{
					lFileURL.setText(url);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent event)
			{
				widgetSelected(event);
			}
		});
	}
}
