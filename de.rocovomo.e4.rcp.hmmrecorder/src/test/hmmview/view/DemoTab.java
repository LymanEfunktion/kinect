package test.hmmview.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Composite;

public class DemoTab extends AbstractWidgetElement
{

	public DemoTab(Composite parent)
	{
		final CTabFolder tabFolder = new CTabFolder(parent, SWT.BOTTOM
				| SWT.BORDER);

		CTabItem item = new CTabItem(tabFolder, SWT.NONE);
		item.setText("Demo");
		Composite inner = new Composite(tabFolder, SWT.NONE);
		inner.setBackground(COLOR_WHITE);
		item.setControl(inner);
		CTabItem item2 = new CTabItem(tabFolder, SWT.NONE);
		Composite inner2 = new Composite(tabFolder, SWT.NONE);
		inner2.setBackground(COLOR_WHITE);
		item2.setControl(inner2);
		item2.setText("Buffer");
	}
}
