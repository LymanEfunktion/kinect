/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package de.rocovomo.e4.rcp.hmmrecorder.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.jnect.bodymodel.PositionedElement;
import org.jnect.core.KinectManager;

import de.rocovomo.e4.rcp.hmmrecorder.model.Trainer;

public class RecorderButtonE4
{
	private Trainer trainer;

	public RecorderButtonE4(final Button button, WritableValue value)
	{
		button.addSelectionListener(new RecorderSelectionListener());
		trainer = (Trainer) value.getValue();
		trainer.addPropertyChangeListener("runnable", new PropertyChangeListener()
		{
			@Override
			public void propertyChange(PropertyChangeEvent event)
			{
				button.setEnabled((Boolean) event.getNewValue());
			}
		});
	}

	private class RecorderSelectionListener implements SelectionListener
	{
		@Override
		public void widgetSelected(SelectionEvent e)
		{
			Button button = (Button) e.getSource();
			button.setText(widgeting(button.getSelection()));
			record(button);
			button.setText(widgeting(false));
			button.setSelection(false);
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e)
		{
			widgetSelected(e);
		}
	}

	private String widgeting(boolean isRunning)
	{
		return isRunning ? "Running" : "Start";
	}

	private void record(Button button)
	{
		if (button.getSelection())
		{
			KinectThread thread = new KinectThread();
			long interval = Math.round(trainer.getInterval() * 1000);
			long frame = Math.round(interval * trainer.getNodes());
			thread.run(interval, frame);
			trainer.setStagedSequence(thread.getSequence());
		}
	}

	private class KinectThread extends Thread
	{
		private List<List<Float>> sequence;
		private PositionedElement bodypart;

		public KinectThread()
		{
			sequence = new ArrayList<List<Float>>();
			bodypart = trainer.getGesture().getElement(KinectManager.INSTANCE.getSkeletonModel());
		}

		public void run(long interval, long frame)
		{
			try
			{
				long time = 0L;
				while (time < frame)
				{
					List<Float> vector = new ArrayList<Float>();
					vector.add(bodypart.getX());
					vector.add(bodypart.getY());
					vector.add(bodypart.getZ());
					sequence.add(vector);
					time += interval;
					sleep(100L);
				}
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

		public List<List<Float>> getSequence()
		{
			return sequence;
		}
	}
}