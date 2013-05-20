/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package de.rocovomo.e4.rcp.hmmrecorder.view.preview;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
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

import de.rocovomo.e4.rcp.hmmrecorder.model.Preview;
import de.rocovomo.e4.rcp.hmmrecorder.model.Trainer;
import de.rocovomo.e4.rcp.hmmrecorder.util.FileProvider;

public class PreviewE4 
{
	public PreviewE4(Display display, Trainer trainer) throws IOException
	{
		Preview preview = initPreview();
		trainer.setPreview(preview);
		
		PreviewShellE4 window = new PreviewShellE4(display, preview);
		
		createUI(window.getShell(), trainer);
		window.open();
	}
	
	private void createUI(Composite parent, Trainer trainer)
	{
		Preview preview = trainer.getPreview();
		Text text = new PreviewTextE4(parent, preview).getText();
		new EditButtonE4(parent, preview);
		new StageButtonE4(parent, preview);
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
		String content = FileProvider.getFileContent(channel);
		preview.setStagedContent(content);
		preview.setSavedContent(content);
		preview.setSave(true);
		input.close();
		return preview;
	}
}
