/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package de.rocovomo.e4.rcp.hmmrecorder.view;

import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import de.rocovomo.e4.rcp.hmmrecorder.model.Trainer;
import de.rocovomo.e4.rcp.hmmrecorder.util.AbstractWidgetElement;

public class AppendButtonE4 extends AbstractWidgetElement
{
	private WritableValue value;
	
	public AppendButtonE4(Composite parent, WritableValue value)
	{
		Button button = addButton("Append", parent, SWT.PUSH, new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING, -1, false, false, 1, 1));
		this.value = value;
		button.addSelectionListener(new AppendCommandListener());
	}
	
	private class AppendCommandListener implements SelectionListener
	{
		@Override
		public void widgetSelected(SelectionEvent event)
		{
//			Button button = (Button) event.getSource();
			Trainer trainer = (Trainer) value.getValue();
			trainer.addSequence(trainer.getStagedSequence());
//			Preview preview = trainer.getPreview();
//			TODO: Implement better save logic
//			if (preview.isSaved())
//			{
//				System.out.println("baustelle");
//			}
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent event)
		{
			widgetSelected(event);
		}
	}
}
