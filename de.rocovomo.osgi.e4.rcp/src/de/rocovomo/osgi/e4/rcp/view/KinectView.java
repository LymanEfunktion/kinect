package de.rocovomo.osgi.e4.rcp.view;

import javax.inject.Inject;

import org.eclipse.core.databinding.ObservablesManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.jnect.core.KinectManager;

public class KinectView {

	private ObservablesManager manager;
	
	@Inject
	public KinectView(Composite parent) {
		manager = new ObservablesManager();
		if(!PlatformUI.isWorkbenchRunning()) {
			System.out.println("Problem");
		} else {
			System.out.println("Compatibility Layer online!");
		}
		manager.runAndCollect(new Runnable() {
			public void run() {
				System.out.println("weiter gehts ...");
				IWorkbench bench =  PlatformUI.getWorkbench();
//				PlatformUI.getWorkbench().getActiveWorkbenchWindow().openPage(perspectiveId, input)
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				
//				HumanDiagramGEF.editorID
//				page.openEditor(input, editorId)
				
				IWorkbenchPart adsf = page.getActivePart();
				IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
//				((HumanDiagramGraphicalEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()).setContent(KinectManager.INSTANCE.getSkeletonModel());
//				KinectManager.INSTANCE.startSkeletonTracking();
//				((HumanDiagramGraphicalEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()).setContent(KinectManager.INSTANCE.getSkeletonModel());
			}
		});
	}
}
