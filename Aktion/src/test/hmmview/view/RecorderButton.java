package test.hmmview.view;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import test.hmmview.model.BooleanModel;
import test.hmmview.model.BooleanModelListener;

public class RecorderButton {

	private BooleanModel model;

	public RecorderButton(final Button button, BooleanModel model) {
		this.model = model;
		button.addSelectionListener(new RecorderSelectionListener());
		button.setEnabled(model.property.getValue());

		model.addModelListener(new BooleanModelListener() {

			@Override
			public void modelChanged(BooleanModel model) {
				widgeting(button);
			}
		});
	}

	private class RecorderSelectionListener implements SelectionListener {

		@Override
		public void widgetSelected(SelectionEvent e) {
			Button button = (Button) e.getSource();
			model.property.setValue(button.getSelection());
			widgeting(button);
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			widgetSelected(e);
		}

	}

	private void widgeting(Button button) {
		button.setText((button.getSelection()) ? "Running" : "Start");
	}

}
