package test.hmmview.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;

public class FileButton extends AbstractWidgetElement
{
	public FileButton(Composite parent)
	{
		addLabel("File", parent, new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING, 2,
				false, false, 1, 1));
		final Label lFileURL = addLabel(" ", parent, SWT.BORDER, new GridData(
				GridData.FILL, -1, false, false, 3, 1));

		Button button = addButton("...", parent, SWT.PUSH, new GridData());

		// TODO: change file properties
		final FileDialog dialog = new FileDialog(parent.getShell(), SWT.SAVE);
		dialog.setFilterNames(new String[] { "Batch Files", "All Files (*.*)" });
		dialog.setFilterExtensions(new String[] { "*.bat", "*.*" });
		dialog.setFilterPath("c:\\");
		dialog.setFileName("yourFile.bat");
		
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
