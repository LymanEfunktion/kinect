/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package test.hmmview.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.osgi.framework.Bundle;

import test.hmmview.model.Preview;
import test.hmmview.model.Trainer;

public class PreviewE4 extends AbstractView
{

	public PreviewE4(Display display, Trainer trainer) throws IOException
	{
		Preview preview = initPreview();
		trainer.setPreview(preview);
		
		PreviewShellE4 test = new PreviewShellE4(display, preview);
		
		createUI(test.getShell(), trainer);
		test.open();
	}
	
	private void createUI(Composite parent, Trainer trainer)
	{
		Preview preview = trainer.getPreview();
		Text text = new PreviewTextE4(parent, preview).getText();
		new EditButtonE4(parent, preview);
		new SaveButtonE4(parent, preview);
		bindValues(trainer, text);
	}

	private void bindValues(Trainer trainer, Text text)
	{
		DataBindingContext ctx = new DataBindingContext();

		IObservableValue widgetValue = WidgetProperties.editable().observe(text);
		IObservableValue modelValue = BeanProperties.value(Trainer.class, "preview.editable")
				.observe(trainer);
		ctx.bindValue(widgetValue, modelValue);
	}

	private Preview initPreview() throws IOException
	{
		File file = null;
		try
		{
			Bundle bundle = Platform.getBundle("de.rocovomo.e4.rcp.hmmrecorder");
			URL fileURL = bundle.getEntry("data/defaultdata.stream");
			file = new File(FileLocator.resolve(fileURL).toURI());

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		Preview preview = new Preview();
		preview.setEditable(false);
		preview.setFile(file);
		FileInputStream input = new FileInputStream(file);
		FileChannel channel = input.getChannel();
		String content = getFileContent(channel);
		preview.setStagedContent(content);
		preview.setSavedContent(content);
		preview.setSave(true);
		input.close();
		return preview;
	}
	
	private String getFileContent(final FileChannel channel) throws IOException
	{
		ByteBuffer buffer = ByteBuffer.allocate(48);
		String content = "";
		int bytesRead = channel.read(buffer);
		while (bytesRead != -1)
		{
			buffer.flip();
			while (buffer.hasRemaining())
			{
				content += Character.toString((char) buffer.get());
			}
			buffer.clear();
			bytesRead = channel.read(buffer);
		}
		return content;
	}
}
