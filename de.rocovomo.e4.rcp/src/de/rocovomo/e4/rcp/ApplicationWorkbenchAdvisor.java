package de.rocovomo.e4.rcp;

import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

/**
 * This workbench advisor creates the window advisor, and specifies
 * the perspective id for the initial window.
 */
public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {
	
    public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
    	System.out.println("suche window ...");
        return new ApplicationWorkbenchWindowAdvisor(configurer);
    }

	@Override
	public String getInitialWindowPerspectiveId() {
		// TODO Auto-generated method stub
		return Perspective.ID;
	}
}
