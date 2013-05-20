/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package de.rocovomo.e4.rcp.hmmrecorder.view;

import java.io.IOException;

import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import de.rocovomo.e4.rcp.hmmrecorder.model.Trainer;
import de.rocovomo.e4.rcp.hmmrecorder.util.AbstractWidgetElement;
import de.rocovomo.e4.rcp.hmmrecorder.util.GestureStreamer;

public class SaveButtonE4 extends AbstractWidgetElement
{
	private WritableValue value;

	public SaveButtonE4(Composite parent, WritableValue value) throws IOException
	{
		this.value = value;
		Button button = addButton("Save", parent, SWT.PUSH, new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING, -1, false, false, 1, 1));
		// Trainer trainer = (Trainer) value.getValue();
		// Preview preview = trainer.getPreview();
		button.addListener(SWT.MouseUp, new TextMouseListener());
	}

	private class TextMouseListener implements Listener
	{
		@Override
		public void handleEvent(Event event)
		{
			try
			{
				save();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		private void save() throws IOException
		{
			Trainer trainer = (Trainer) value.getValue();
			GestureStreamer streamer = new GestureStreamer(trainer.getFileUrl());
			streamer.stream(trainer.getSequences());
			trainer.clearList();
		}
	}
}
