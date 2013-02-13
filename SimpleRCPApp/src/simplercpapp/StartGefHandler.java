package simplercpapp;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.widgets.Shell;
import org.jnect.core.KinectManager;

public class StartGefHandler {

	@Inject
	public void execute(
			IEclipseContext context,
			@Named(IServiceConstants.ACTIVE_SHELL) Shell shell) throws ExecutionException {
		System.out.println("starting Kinect..");
//		KinectManager.INSTANCE.startSkeletonTracking();

//		((HumanDiagramGraphicalEditor) PlatformUI.getWorkbench()
//				.getActiveWorkbenchWindow().getActivePage().getActiveEditor())
//				.setContent(KinectManager.INSTANCE.getSkeletonModel());
	}

}
