package test.hmmview;

import java.io.File;
import java.io.IOException;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import test.hmmview.model.FileModel;
import test.hmmview.view.Preview;

public class HmmViewDemo
{
	public static void main(String[] args) throws IOException
	{
		Display display = Display.getDefault();
		final Shell shell = new Shell(display);

		GridLayout gridLayout = new GridLayout(4, false);
		gridLayout.verticalSpacing = 8;

		shell.setLayout(new FillLayout());

		startMVC(new Composite(shell, 0));
//		startPreview(display);
		
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

	private static void startPreview(Display display) throws IOException {
		FileModel model = new FileModel();
		
		File file = new File("data/defaultdata.stream");
		model.property.setValue(file);
		Preview pre = new Preview(display, model);
	}

	private static void startMVC(Composite parent)
	{
		Control control = new Control(parent, new File("data/defaultdata.stream"));
	}
}