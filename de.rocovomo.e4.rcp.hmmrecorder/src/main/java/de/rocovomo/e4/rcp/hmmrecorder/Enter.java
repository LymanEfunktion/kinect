package de.rocovomo.e4.rcp.hmmrecorder;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

@SuppressWarnings("restriction")
public class Enter
{
	@Execute
	public void execute(Shell shell)
	{
		MessageDialog.openInformation(shell, "Test", "Just testing");
	}
}
