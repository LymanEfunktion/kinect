/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package de.rocovomo.e4.rcp.hmmrecorder.view.preview;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import de.rocovomo.e4.rcp.hmmrecorder.model.Preview;
import de.rocovomo.e4.rcp.hmmrecorder.util.AbstractWidgetElement;

public class StageButtonE4 extends AbstractWidgetElement
{
	private Button button;

	public StageButtonE4(Composite parent, Preview preview)
	{
		button = addButton("Save", parent, SWT.PUSH, new GridData(GridData.END, -1, true, false, 1,
				1));
		button.addListener(SWT.MouseUp, new TextMouseListener(preview));
	}

	public Button getButton()
	{
		return button;
	}

	private class TextMouseListener implements Listener
	{
		private Preview preview;

		public TextMouseListener(Preview preview)
		{
			this.preview = preview;
		}

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
			File aFile = preview.getFile();
			FileOutputStream fileOutputStream = new FileOutputStream(aFile, false);
			FileChannel channel = fileOutputStream.getChannel();
			String content = preview.getStagedContent();
			channel.force(true);
			byte[] bytes = content.getBytes();
			ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
			buffer.put(bytes);
			buffer.flip();
			while (buffer.hasRemaining())
			{
				channel.write(buffer);
			}
			fileOutputStream.close();
			preview.setSavedContent(content);
			preview.setSave(true);
		}
	}
}
