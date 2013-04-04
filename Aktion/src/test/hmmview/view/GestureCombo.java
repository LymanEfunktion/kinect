package test.hmmview.view;

import hmm.gesture.reference.GestureType;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

public class GestureCombo {

	private Combo combo;

	public GestureCombo(Composite parent, int style) {
		combo = new Combo(parent, style);

		addListofGestrureTypes();
		initDefault();
	}

	private void addListofGestrureTypes() {
		// TODO: add Object, maybe change swt to jface element
		for (GestureType item : GestureType.values()) {
			combo.add(item.getParentDirNameList().toString());
		}
	}

	private void initDefault() {
		combo.setText(combo.getItem(0));
		combo.setLayoutData(new GridData(GridData.END, -1, false, false, 1, 1));
	}
}
