/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package test.hmmview.view;

import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;

import test.hmmview.model.Trainer;
import de.rocovomo.util.hmm.gesture.reference.GestureType;

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
					return type.getParentDirNameList().toString();
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
				trainer.setNodes(((GestureType) selection.getFirstElement()).getNumHmmNodes());
				value.setValue(trainer);
			}
		});
		viewer.setSelection(new StructuredSelection(GestureType.HAND_CIRCLE));
	}
}
