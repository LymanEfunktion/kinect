package test.hmmview.view;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public abstract class AbstractWidgetElement implements Colors{
	
	protected Button addButton(String text, Composite parent, int style,
			GridData gridData, int widthHint) {
		gridData.widthHint = widthHint;
		Button button = new Button(parent, style);
		button.setText(text);
		button.setLayoutData(gridData);
		button.setBackground(COLOR_WHITE);
		return button;
	}

	protected Button addButton(String text, Composite parent, int style,
			GridData gridData) {
		return addButton(text, parent, style, gridData, -1);
	}
}
