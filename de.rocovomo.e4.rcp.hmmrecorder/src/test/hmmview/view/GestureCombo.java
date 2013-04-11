package test.hmmview.view;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;

import test.hmmview.model.IntegerModel;

import de.rocovomo.util.hmm.gesture.reference.GestureType;

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
				IStructuredSelection selection = (IStructuredSelection) event
						.getSelection();
				model.property.setValue(((GestureType) selection.getFirstElement())
						.getNumHmmNodes());
			}
		});

		viewer.setSelection(new StructuredSelection(GestureType.HAND_CIRCLE));

		// IStructuredSelection selection = (IStructuredSelection)
		// viewer.getSelection();
		// GestureType element = (GestureType) selection.getFirstElement();
	}
}
