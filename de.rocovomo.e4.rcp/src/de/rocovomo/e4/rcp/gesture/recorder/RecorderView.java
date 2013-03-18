package de.rocovomo.e4.rcp.gesture.recorder;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Shell;

@SuppressWarnings("restriction")
public class RecorderView
{
	private boolean disabled = true;

	@Inject
	public RecorderView()
	{
		disabled = false;
	}

	@PostConstruct
	public void createUI(final Shell shell)
	{
		GridLayout gridLayout = new GridLayout(4, false);
		gridLayout.verticalSpacing = 8;

		shell.setLayout(gridLayout);
	}

	@PreDestroy
	public void destroy()
	{
		disabled = true;
	}

	@Inject
	public void setSelection(
			@Optional @Named(IServiceConstants.ACTIVE_SELECTION) Object selection)
	{
		if (!disabled)
		{
			System.out.println("Selection changed to "
					+ ((selection == null) ? "'null'" : selection.toString()));
		}
	}
}
