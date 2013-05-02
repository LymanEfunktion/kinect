/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package test.hmmview.view;

import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import test.hmmview.model.Trainer;

public class SpeechCommandButtonE4 extends AbstractWidgetElement
{
	private WritableValue value;

	public SpeechCommandButtonE4(Composite parent, WritableValue value)
	{
		Button button = addButton("Speech command enabled", parent, SWT.CHECK, new GridData(
				GridData.HORIZONTAL_ALIGN_FILL, 2, false, false, 5, 1));
		this.value = value;
		button.addSelectionListener(new SpeechCommandSelectionListener());
		button.setSelection(((Trainer) value.getValue()).isSpeechActivated());
	}

	private class SpeechCommandSelectionListener implements SelectionListener
	{
		@Override
		public void widgetSelected(SelectionEvent e)
		{
			Button button = (Button) e.getSource();
			widgeting(button);
			((Trainer) value.getValue()).setSpeechCommand(button.getSelection());
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e)
		{
			widgetSelected(e);
		}
	}

	private void widgeting(Button button)
	{
		String text = ("Speech command " + ((button.getSelection()) ? "enabled" : "disabled"));
		button.setText(text);
		button.pack();
	}
}
