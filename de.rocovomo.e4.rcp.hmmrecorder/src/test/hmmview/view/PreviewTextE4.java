/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package test.hmmview.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import test.hmmview.model.Preview;

public class PreviewTextE4
{
	private Text text;

	public PreviewTextE4(Composite parent, Preview preview)
	{
		text = new Text(parent, SWT.BORDER | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		text.setText(preview.getStagedContent());
		text.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true, 2, 1));

		final Listener listener = new TextKeyListener(preview);
		if (preview.isEditable())
		{
			text.addListener(SWT.KeyUp, listener);
		}

		preview.addPropertyChangeListener("editable", new PropertyChangeListener()
		{
			@Override
			public void propertyChange(PropertyChangeEvent event)
			{
				if ((Boolean) event.getNewValue())
				{
					text.addListener(SWT.KeyUp, listener);
				} else
				{
					text.removeListener(SWT.KeyUp, listener);
				}
			}
		});
	}

	public Text getText()
	{
		return text;
	}

	private class TextKeyListener implements Listener
	{
		private Preview preview;

		public TextKeyListener(Preview preview)
		{
			this.preview = preview;
		}

		@Override
		public void handleEvent(Event event)
		{
			String text = ((Text) event.widget).getText();
			preview.setStagedContent(text);
		}
	}
}
