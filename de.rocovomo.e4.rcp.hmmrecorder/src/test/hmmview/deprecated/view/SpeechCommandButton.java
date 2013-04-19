/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package test.hmmview.deprecated.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import test.hmmview.deprecated.model.BooleanModel;
import test.hmmview.deprecated.model.BooleanModelListener;
import test.hmmview.view.AbstractWidgetElement;

public class SpeechCommandButton extends AbstractWidgetElement
{
	private BooleanModel model;

	public SpeechCommandButton(Composite parent, BooleanModel model)
	{
		final Button button = addButton("Speech command enabled", parent, SWT.CHECK,
				new GridData(GridData.HORIZONTAL_ALIGN_FILL, 2, false, false, 5, 1));
		this.model = model;
		button.addSelectionListener(new SpeechCommandSelectionListener());
		button.setSelection(model.property.getValue());

		model.addModelListener(new BooleanModelListener()
		{

			@Override
			public void modelChanged(BooleanModel model)
			{
				widgeting(button);
			}
		});
	}

	private class SpeechCommandSelectionListener implements SelectionListener
	{

		@Override
		public void widgetSelected(SelectionEvent e)
		{
			Button button = (Button) e.getSource();
			model.property.setValue(button.getSelection());
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
		String text = ("Speech command " + ((button.getSelection()) ? "enabled"
				: "disabled"));
		button.setText(text);
		button.pack();
	}
}