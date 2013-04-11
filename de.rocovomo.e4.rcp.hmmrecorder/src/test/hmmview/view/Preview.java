package test.hmmview.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import test.hmmview.model.BooleanModel;
import test.hmmview.model.BooleanModelListener;
import test.hmmview.model.FileModel;
import test.hmmview.model.StringModel;
import test.hmmview.model.StringModelListener;


public class Preview extends AbstractView
{

	private String title;

	// TODO: change file logic to create temporary file before save
	public Preview(Display display, FileModel model) throws IOException
	{
		final Shell shell = new Shell(display, SWT.CLOSE | SWT.RESIZE);
		GridLayout gridLayout = new GridLayout(2, false);

		shell.setBackground(COLOR_WHITE);
		shell.setLayout(gridLayout);

		shell.setLayoutData(new GridData(GridData.FILL_BOTH));
		BooleanModel saveModel = new BooleanModel();
		saveModel.addModelListener(new BooleanModelListener()
		{
			@Override
			public void modelChanged(BooleanModel model)
			{
				setShellTitle(shell, model.property.getValue());
			}
		});

		title = "Preview - " + model.property.getValue().getAbsolutePath();
		shell.setText(title);

		shell.addListener(SWT.Traverse, new Listener()
		{
			public void handleEvent(Event event)
			{
				switch (event.detail)
				{
				case SWT.TRAVERSE_ESCAPE:
					shell.close();
					event.detail = SWT.TRAVERSE_NONE;
					event.doit = false;
					break;
				}
			}
		});

		File file = model.property.getValue();
		FileInputStream input = new FileInputStream(file);
		FileChannel channel = input.getChannel();
		createView(shell, model, saveModel, channel);
		
		input.close();

		shell.open();

		shell.addListener(SWT.Close, new Listener()
		{
			@Override
			public void handleEvent(Event event)
			{
				shell.dispose();
			}
		});
	}

	private void setShellTitle(Shell shell, boolean isSaved)
	{
		shell.setText(title + (isSaved ? "" : " *"));
	}

	private void createView(Composite parent, FileModel fileModel,
			BooleanModel saveModel, FileChannel channel) throws IOException
	{

		final StringModel contentModel = new StringModel();
		StringModel textModel = new StringModel();
		BooleanModel toggleModel = new BooleanModel();

		textModel.addModelListener(new StringModelListener()
		{

			@Override
			public void modelChanged(StringModel model)
			{
				// System.out.println("model:" +
				// contentModel.property.getValue().equals(model.property.getValue()));
			}
		});

		new PreviewText(parent, saveModel, toggleModel, contentModel, textModel,
				getFileContent(channel));

		new EditButton(parent, toggleModel);
		new SaveButton(parent, fileModel, contentModel, textModel, saveModel);
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