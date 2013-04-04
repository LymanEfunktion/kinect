package test.hmmview.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import test.hmmview.model.BooleanModel;
import test.hmmview.model.BooleanModelListener;
import test.hmmview.model.FileModel;
import test.hmmview.model.StringModel;
import test.hmmview.model.StringModelListener;

public class Preview extends AbstractView {

	private String title;

	public Preview(Display display, FileModel model) throws IOException {
		final Shell shell = new Shell(display, SWT.CLOSE | SWT.RESIZE);
		BooleanModel saveModel = new BooleanModel();
		saveModel.addModelListener(new BooleanModelListener() {

			@Override
			public void modelChanged(BooleanModel model) {
				setShellTitle(shell, model.property.getValue());
			}
		});

		title = "Preview - " + model.property.getValue().getAbsolutePath();

		File file = model.property.getValue();
		FileInputStream input = new FileInputStream(file);
		FileChannel channel = input.getChannel();
		createView(shell, model, saveModel, channel);

		input.close();
	}

	private void setShellTitle(Shell shell, boolean isSaved) {
		shell.setText(title + (isSaved ? "" : " *"));
	}

	private void createView(final Shell shell, FileModel fileModel,
			BooleanModel saveModel, FileChannel channel) throws IOException {
		GridLayout gridLayout = new GridLayout(2, false);

		shell.setBackground(COLOR_WHITE);
		shell.setLayout(gridLayout);
		shell.setText(title);
		shell.setLayoutData(new GridData(GridData.FILL_BOTH));

		final StringModel contentModel = new StringModel();
		StringModel textModel = new StringModel();
		BooleanModel toggleModel = new BooleanModel();

		textModel.addModelListener(new StringModelListener() {

			@Override
			public void modelChanged(StringModel model) {
				// System.out.println("model:" +
				// contentModel.property.getValue().equals(model.property.getValue()));
			}
		});

		new PreviewText(shell, saveModel, toggleModel, contentModel, textModel,
				getFileContent(channel));

		new EditButton(shell, toggleModel);
		new SaveButton(shell, fileModel, contentModel, textModel, saveModel);

		shell.open();

		shell.addListener(SWT.Close, new Listener() {
			@Override
			public void handleEvent(Event event) {
				shell.dispose();
			}
		});
	}

	private String getFileContent(final FileChannel channel) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(48);
		String content = "";
		int bytesRead = channel.read(buffer);
		while (bytesRead != -1) {
			buffer.flip();
			while (buffer.hasRemaining()) {
				content += Character.toString((char) buffer.get());
			}
			buffer.clear();
			bytesRead = channel.read(buffer);
		}
		return content;
	}
}