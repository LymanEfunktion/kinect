/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package de.rocovomo.e4.rcp.hmmrecorder.view.tab;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import de.rocovomo.e4.rcp.hmmrecorder.model.Trainer;
import de.rocovomo.e4.rcp.hmmrecorder.util.AbstractWidgetElement;

public class TabE4 extends AbstractWidgetElement
{
	public TabE4(Composite parent, Trainer trainer)
	{
		final CTabFolder tabFolder = new CTabFolder(parent, SWT.BOTTOM | SWT.BORDER);
//		CTabItem itemDemo = new CTabItem(tabFolder, SWT.NONE);
//		itemDemo.setText("Demo");
//		Composite compDemo = new Composite(tabFolder, SWT.NONE);
//		compDemo.setBackground(COLOR_WHITE);
//		itemDemo.setControl(compDemo);
		CTabItem itemBuffer = new CTabItem(tabFolder, SWT.NONE);
		Composite compBuffer = new Composite(tabFolder, SWT.NONE);
		compBuffer.setBackground(COLOR_WHITE);
		setupBufferPage(compBuffer, trainer);
		itemBuffer.setControl(compBuffer);
		itemBuffer.setText("Buffer");
		tabFolder.setSelection(itemBuffer);
	}

	private void setupBufferPage(Composite parent, Trainer trainer)
	{
		parent.setLayout(new FillLayout());
		new BufferTreeViewerE4(parent, trainer);
	}
}
