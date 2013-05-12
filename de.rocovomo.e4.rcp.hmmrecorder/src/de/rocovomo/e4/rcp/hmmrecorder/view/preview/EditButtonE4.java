/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package de.rocovomo.e4.rcp.hmmrecorder.view.preview;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import de.rocovomo.e4.rcp.hmmrecorder.model.Preview;
import de.rocovomo.e4.rcp.hmmrecorder.util.AbstractWidgetElement;

public class EditButtonE4 extends AbstractWidgetElement
{
	public EditButtonE4(Composite parent, Preview preview)
	{
		Button button = addButton("Edit", parent, SWT.TOGGLE, new GridData(GridData.BEGINNING, -1,
				false, false, 1, 1), 37);
		button.setSelection(preview.isEditable());
		button.addSelectionListener(new EditSelectionListener(preview));
	}

	private class EditSelectionListener implements SelectionListener
	{
		private Preview preview;

		public EditSelectionListener(Preview preview)
		{
			this.preview = preview;
		}

		@Override
		public void widgetSelected(SelectionEvent event)
		{
			Button button = (Button) event.getSource();
			button.setText((button.getSelection()) ? "Lock" : "Edit");
			preview.setEditable(button.getSelection());
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent event)
		{
			widgetSelected(event);
		}
	}
}
