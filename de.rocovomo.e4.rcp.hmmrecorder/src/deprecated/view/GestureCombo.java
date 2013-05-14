/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package deprecated.view;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;

import de.rocovomo.e4.rcp.hmmrecorder.util.AbstractWidgetElement;
import de.rocovomo.util.hmm.gesture.reference.GestureTypeOLD;
import deprecated.model.IntegerModel;

@Deprecated
public class GestureCombo extends AbstractWidgetElement
{
	private ComboViewer viewer;

	public GestureCombo(Composite parent, final IntegerModel model, int style)
	{
		addDefaultLabel("Choose gesture", parent);

		viewer = new ComboViewer(parent, style);

		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setLabelProvider(new LabelProvider()
		{
			@Override
			public String getText(Object element)
			{
				if (element instanceof GestureTypeOLD)
				{
					GestureTypeOLD type = (GestureTypeOLD) element;
					return type.getParentDirNameList().toString();
				}
				return super.getText(element);
			}
		});

		viewer.setInput(GestureTypeOLD.values());

		viewer.addSelectionChangedListener(new ISelectionChangedListener()
		{
			@Override
			public void selectionChanged(SelectionChangedEvent event)
			{
				IStructuredSelection selection = (IStructuredSelection) event
						.getSelection();
				model.property.setValue(((GestureTypeOLD) selection.getFirstElement())
						.getNumHmmNodes());
			}
		});

		viewer.setSelection(new StructuredSelection(GestureTypeOLD.HAND_CIRCLE));

		// IStructuredSelection selection = (IStructuredSelection)
		// viewer.getSelection();
		// GestureType element = (GestureType) selection.getFirstElement();
	}
}
