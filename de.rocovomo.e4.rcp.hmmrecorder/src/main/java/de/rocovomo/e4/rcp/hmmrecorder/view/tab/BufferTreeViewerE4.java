/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package de.rocovomo.e4.rcp.hmmrecorder.view.tab;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import de.rocovomo.e4.rcp.hmmrecorder.model.HMMModel;
import de.rocovomo.e4.rcp.hmmrecorder.model.Trainer;

public class BufferTreeViewerE4
{
	public BufferTreeViewerE4(Composite parent, Trainer trainer)
	{
		final TreeViewer viewer = new TreeViewer(parent, SWT.MULTI);
		viewer.setLabelProvider(new BufferLabelProvider());
		viewer.setContentProvider(new BufferContentProvider());
		viewer.setAutoExpandLevel(2);
		viewer.setInput(createModel(trainer.getStagedSequence()));
		trainer.addPropertyChangeListener("sequence", new PropertyChangeListener()
		{
			@Override
			public void propertyChange(PropertyChangeEvent event)
			{
				viewer.setInput(createModel(event.getNewValue()));
			}
		});
	}

	@SuppressWarnings("unchecked")
	private HMMModel createModel(Object input)
	{
		List<List<Float>> list = (List<List<Float>>) input;
		return new HMMModel(list);
	}
}
