package deprecated;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import de.rocovomo.e4.rcp.hmmrecorder.util.Colors;


@Deprecated
public abstract class AbstractView implements Colors{

	public AbstractView() {	}

	public AbstractView(Composite parent) {
		parent.setBackground(COLOR_WHITE);
	}
	
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
	
	protected void addDefaultLabel(String text, Composite parent) {
		addLabel(text, parent, new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING, -1, true, false, 4, 1));
	}

	protected Text addDefaultText(Composite parent, Object input) {
		Text text = new Text(parent, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, -1, false, false, 1, 1));
		text.setText(input.toString());
		return text;
	}

	protected Label addLabel(String text, Composite parent, int style,
			GridData gridData) {
		Label label = new Label(parent, style);
		label.setText(text);
		label.setBackground(COLOR_WHITE);
		label.setLayoutData(gridData);
		return label;
	}

	protected Label addLabel(String text, Composite parent, GridData gridData) {
		return addLabel(text, parent, SWT.NONE, gridData);
	}
}