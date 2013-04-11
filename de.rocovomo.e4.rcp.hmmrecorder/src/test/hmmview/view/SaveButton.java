package test.hmmview.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import test.hmmview.model.BooleanModel;
import test.hmmview.model.FileModel;
import test.hmmview.model.StringModel;


public class SaveButton extends AbstractWidgetElement {

	private Button button;

	public SaveButton(Composite parent, FileModel fileModel,
			StringModel contentModel, StringModel textModel,
			BooleanModel saveModel) {
		button = addButton("Save", parent, SWT.PUSH, new GridData(GridData.END,
				-1, true, false, 1, 1));

		button.addMouseListener(new TextMouseListener(fileModel, contentModel,
				textModel, saveModel));
	}

	public Button getButton() {
		return button;
	}

	private class TextMouseListener implements MouseListener {

		FileModel fileModel;
		StringModel contentModel;
		StringModel textModel;
		BooleanModel saveModel;

		public TextMouseListener(FileModel fileModel, StringModel contentModel,
				StringModel textModel, BooleanModel saveModel) {
			this.fileModel = fileModel;
			this.contentModel = contentModel;
			this.textModel = textModel;
			this.saveModel = saveModel;
		}

		@Override
		public void mouseUp(MouseEvent event) {
			try {
				save();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void mouseDown(MouseEvent e) {
		}

		@Override
		public void mouseDoubleClick(MouseEvent e) {
		}

		private void save() throws IOException {
//			TODO: File changed dilemma!
//			use createTmpFile
//			use WatchService API 
//			warum wird file change nicht getriggert?!!??
			File aFile = fileModel.property.getValue();
//			System.out.print(aFile.length());
			FileOutputStream fileOutputStream = new FileOutputStream(
					aFile , false);
			FileChannel channel = fileOutputStream.getChannel();
			String content = textModel.property.getValue();
			channel.force(true);
			byte[] bytes = content.getBytes();
			ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
			buffer.put(bytes);
			buffer.flip();
			while (buffer.hasRemaining()) {
				channel.write(buffer);
			}
			fileOutputStream.close();
			saveModel.property.setValue(true);
//			System.out.println(" " + aFile.equals(fileModel.property.getValue()) + ":" + aFile.length());
			contentModel.property.setValue(content);
		}
	}
}
