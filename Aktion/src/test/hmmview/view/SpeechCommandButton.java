package test.hmmview.view;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import test.hmmview.model.BooleanModel;
import test.hmmview.model.BooleanModelListener;

public class SpeechCommandButton {
	
	private BooleanModel model;
	
	public SpeechCommandButton(final Button button, BooleanModel model) {
		this.model = model;
		button.addSelectionListener(new SpeechCommandSelectionListener());
		button.setSelection(model.property.getValue());
		
		model.addModelListener(new BooleanModelListener() {
			
			@Override
			public void modelChanged(BooleanModel model) {
				widgeting(button);
			}
		});
	}
	
	private class SpeechCommandSelectionListener implements SelectionListener {
		
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
		String text = ("Speech command " + ((button.getSelection()) ? "enabled"
				: "disabled"));
		button.setText(text);
		button.pack();
	}
}