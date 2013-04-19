/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package test.hmmview.deprecated.view;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import test.hmmview.deprecated.model.BooleanModel;
import test.hmmview.deprecated.model.BooleanModelListener;
import test.hmmview.deprecated.model.StringModel;
import test.hmmview.deprecated.model.StringModelListener;


public class PreviewText {

	private Text text;
	private String savedContent;
	private TextKeyListener listener;

	public PreviewText(Composite parent, BooleanModel isSavedModel,
			BooleanModel toggleModel, StringModel savedContentModel,
			StringModel textContentModel, String previewText)
			throws IOException {

		listener = new TextKeyListener(isSavedModel, textContentModel);

		text = new Text(parent, SWT.BORDER | SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		savedContentModel.addModelListener(new StringModelListener() {

			@Override
			public void modelChanged(StringModel model) {
				setSavedContent(model.property.getValue());
			}
		});

		toggleModel.addModelListener(new BooleanModelListener() {

			@Override
			public void modelChanged(BooleanModel model) {
				boolean toggle = model.property.getValue();
				if (toggle) {
					text.addKeyListener(listener);
				} else {
					text.removeKeyListener(listener);
				}
				text.setEditable(toggle);
			}
		});
		text.setText(previewText);
		text.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true,
				true, 2, 1));
	}

	public String getSavedContent() {
		return savedContent;
	}

	public void setSavedContent(String content) {
		this.savedContent = content;
	}

	private class TextKeyListener implements KeyListener {

		private BooleanModel isSavedModel;
		private StringModel textContentModel;

		public TextKeyListener(BooleanModel isSavedModel, StringModel textContentModel) {
			this.isSavedModel = isSavedModel;
			this.textContentModel = textContentModel;
		}

		@Override
		public void keyReleased(KeyEvent e) {
			String text = ((Text) e.getSource()).getText();
			textContentModel.property.setValue(text);
			isSavedModel.property.setValue(getSavedContent().equals(text));
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}
	}
}
