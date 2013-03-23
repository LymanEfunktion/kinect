package de.rocovomo.e4.rcp;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public static String ID = "de.rocovomo.e4.rcp.perspective";

	/*
	 * @see
	 * org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui
	 * .IPageLayout)
	 */
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);

//		 layout.addStandaloneView(NavigationView.ID, false, IPageLayout.LEFT,
//		 0.25f, editorArea);
//		 IFolderLayout folder = layout.createFolder("messages",
//		 IPageLayout.TOP,
//		 0.5f, editorArea);
//		 folder.addPlaceholder(View.ID + ":*");
//		 folder.addView(View.ID);
//
//		// Add the "pure" e4 part to the perspective. The given view ID is
//		// matching the PartDescriptor ID in "MyLegacyIDE.e4xmi"
		IFolderLayout bottom = layout.createFolder("bottom",
				IPageLayout.BOTTOM, 0.70f, editorArea);
		bottom.addView("de.rocovomo.e4.rcp.lwjglview");
		bottom.addView("de.rocovomo.e4.rcp.recorder");
//
//		layout.getViewLayout(NavigationView.ID).setCloseable(false);
	}
}
