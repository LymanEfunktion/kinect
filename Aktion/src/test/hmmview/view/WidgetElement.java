package test.hmmview.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;

import test.hmmview.model.Model;

public abstract class WidgetElement {

	protected Model model;
	protected Color COLOR_WHITE;
	
	public WidgetElement(Composite parent, Model model) {
		COLOR_WHITE = parent.getDisplay().getSystemColor(SWT.COLOR_WHITE);
	}
}
