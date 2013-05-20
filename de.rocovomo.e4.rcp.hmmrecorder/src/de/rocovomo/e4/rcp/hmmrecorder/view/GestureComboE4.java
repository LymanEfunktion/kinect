/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package de.rocovomo.e4.rcp.hmmrecorder.view;

import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;

import de.rocovomo.e4.rcp.hmmrecorder.model.Trainer;
import de.rocovomo.e4.rcp.hmmrecorder.util.AbstractWidgetElement;
import de.rocovomo.util.hmm.gesture.GestureType;

public class GestureComboE4 extends AbstractWidgetElement
{
	private ComboViewer viewer;

	public GestureComboE4(Composite parent, final WritableValue value, int style)
	{
		addDefaultLabel("Choose gesture", parent);

		viewer = new ComboViewer(parent, style);

		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setLabelProvider(new LabelProvider()
		{
			@Override
			public String getText(Object element)
			{
				if (element instanceof GestureType)
				{
					GestureType type = (GestureType) element;
					return type.getName();
				}
				return super.getText(element);
			}
		});
		viewer.setInput(GestureType.values());
		viewer.addSelectionChangedListener(new ISelectionChangedListener()
		{
			@Override
			public void selectionChanged(SelectionChangedEvent event)
			{
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				Trainer trainer = (Trainer) value.getValue();
				GestureType gesture = (GestureType) selection.getFirstElement();
				trainer.setNodes(gesture.getNumHmmNodes());
				trainer.setGesture(gesture);
				value.setValue(trainer);
			}
		});
		viewer.setSelection(new StructuredSelection(GestureType.HAND_CIRCLE));
	}
}
