package test.hmmview.view;

import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import test.hmmview.model.Trainer;

public class RecorderButtonE4
{
	
	public RecorderButtonE4(final Button button, WritableValue value)
	{
		button.addSelectionListener(new RecorderSelectionListener());
		button.setEnabled(((Trainer)value.getValue()).isRunnable());
	}

	private class RecorderSelectionListener implements SelectionListener
	{
		@Override
		public void widgetSelected(SelectionEvent e)
		{
			Button button = (Button) e.getSource();
			widgeting(button);
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e)
		{
			widgetSelected(e);
		}
	}

	private void widgeting(Button button)
	{
		button.setText((button.getSelection()) ? "Running" : "Start");
	}

}