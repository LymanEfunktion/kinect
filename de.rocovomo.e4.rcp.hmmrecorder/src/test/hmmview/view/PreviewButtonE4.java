/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package test.hmmview.view;

import java.io.IOException;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;

import test.hmmview.model.Trainer;

public class PreviewButtonE4
{
	private WritableValue value;

	public PreviewButtonE4(Button button, WritableValue value)
	{
		this.value = value;
		button.addSelectionListener(new PreviewSelectionListener());
	}

	private class PreviewSelectionListener implements SelectionListener
	{
		@Override
		public void widgetSelected(SelectionEvent e)
		{
			try
			{
				widgeting(e.widget.getDisplay());
			} catch (IOException e1)
			{
				e1.printStackTrace();
			}
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e)
		{
			widgetSelected(e);
		}
	}

	private void widgeting(Display display) throws IOException
	{
		new PreviewE4(display,(Trainer) value.getValue());
	}
}
