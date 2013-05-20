/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package deprecated.view;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import deprecated.model.BooleanModel;


@Deprecated
public class RecorderButton
{
	private BooleanModel model;

	public RecorderButton(final Button button, BooleanModel model)
	{
		this.model = model;
		button.addSelectionListener(new RecorderSelectionListener());
		button.setEnabled(model.property.getValue());
	}

	private class RecorderSelectionListener implements SelectionListener
	{
		@Override
		public void widgetSelected(SelectionEvent e)
		{
			Button button = (Button) e.getSource();
//			model.property.setValue(button.getSelection());
			widgeting(button);
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e)
		{
			widgetSelected(e);
		}
	}

	private void widgeting(Button button)
	{
		button.setText((button.getSelection()) ? "Running" : "Start");
	}

}
