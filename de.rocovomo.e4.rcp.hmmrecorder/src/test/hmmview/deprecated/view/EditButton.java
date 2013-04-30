/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package test.hmmview.deprecated.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import test.hmmview.deprecated.model.BooleanModel;
import test.hmmview.view.AbstractWidgetElement;

@Deprecated
public class EditButton extends AbstractWidgetElement {

	Button button;

	public EditButton(Composite parent, BooleanModel toggleModel) {
		button = addButton("Edit", parent, SWT.TOGGLE, new GridData(
				GridData.BEGINNING, -1, false, false, 1, 1), 37);
		button.setSelection(false);
		button.addSelectionListener(new EditSelectionListener(toggleModel));
	}

	private class EditSelectionListener implements SelectionListener {

		private BooleanModel toggleModel;

		public EditSelectionListener(BooleanModel toggleModel) {
			this.toggleModel = toggleModel;
		}

		@Override
		public void widgetSelected(SelectionEvent event) {
			Button button = (Button) event.getSource();
			button.setText((button.getSelection()) ? "Lock" : "Edit");
			toggleModel.property.setValue(button.getSelection());
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent event) {
			widgetSelected(event);
		}
	}
}
