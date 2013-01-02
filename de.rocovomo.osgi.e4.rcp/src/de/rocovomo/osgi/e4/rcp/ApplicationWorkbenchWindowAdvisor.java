package de.rocovomo.osgi.e4.rcp;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.part.FileInPlaceEditorInput;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

//    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
////        return new ApplicationActionBarAdvisor(configurer);
//    }
    
//    public void preWindowOpen() {
//        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
//        configurer.setInitialSize(new Point(600, 400));
//        configurer.setShowCoolBar(true);
//        configurer.setShowStatusLine(false);
//    }
    
    
    /*
	 * @see org.eclipse.ui.application.WorkbenchWindowAdvisor#postWindowCreate()
	 */
	@Override
	public void postWindowCreate() {
		System.out.println("try...");
		try {
			/**
			 * Open a digraph editor on a file. The file does not exist, but
			 * since our editor does not read or write any data we are ok.
			 */
			System.out.println("init fake file");
			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(
					new Path("/project/file.digraph1")); //$NON-NLS-1$
			getWindowConfigurer().getWindow().getActivePage().openEditor(
					new FileInPlaceEditorInput(file),
					"de.rocovomo.osgi.e4.rcp.editor.HumanDiagramGraphicalEditor"); //$NON-NLS-1$
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		super.postWindowCreate();
	}
	
	/*
	 * @see org.eclipse.ui.application.WorkbenchWindowAdvisor#preWindowOpen()
	 */
	@Override
	public void preWindowOpen() {
		getWindowConfigurer().setShowCoolBar(false);
		getWindowConfigurer().setShowStatusLine(false);
		getWindowConfigurer().setShowMenuBar(false);
	}

    public boolean preWindowShellClose() {
    	final TrayItem trayItem = new TrayItem(Display.getDefault().getSystemTray(), SWT.NONE);
    	trayItem.setText("RoCoVoMo App");
    	trayItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("tutut");
				Shell workbenchWindowShell = getWindowConfigurer().getWindow().getShell();
				workbenchWindowShell.setVisible(true);
				workbenchWindowShell.setActive();
				workbenchWindowShell.setFocus();
				workbenchWindowShell.setMinimized(false);
				trayItem.dispose();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
    		
    	});
    	getWindowConfigurer().getWindow().getShell().setVisible(false);
    	return false;
    }
}
