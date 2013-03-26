package test.hmmview;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class HmmViewDemo
{
	public static void main(String[] args)
	{
		Display display = new Display();
		final Shell shell = new Shell(display);

		GridLayout gridLayout = new GridLayout(4, false);
		gridLayout.verticalSpacing = 8;

		shell.setLayout(new FillLayout());

		startMVC(new Composite(shell, 0));
		
		shell.open();

		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
		}
		display.dispose();
	}

	private static void startMVC(Composite parent)
	{
		Control control = new Control(parent);
	}
}