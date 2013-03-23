/*******************************************************************************
 * Copyright (c) 2012 jnect.org.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eugen Neufeld - initial API and implementation
 *******************************************************************************/
package de.rocovomo.e4.rcp.humandiagram.handler;

import javax.inject.Named;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.jnect.core.KinectManager;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.services.IServiceConstants;

import de.rocovomo.e4.rcp.humandiagram.editor.HumanDiagramGraphicalEditor;

@SuppressWarnings("restriction")
public class StartGefHandler
{

	@Execute
	public void execute(IEclipseContext context,
			@Named(IServiceConstants.ACTIVE_SHELL) Shell shell)
			throws ExecutionException
	{
		// MCommand command = item.getCommand();
		// // Prints out the commmand ID
		// System.out.println(command.getElementId());
		System.out.println("starting Kinect..");
		KinectManager.INSTANCE.startSkeletonTracking();

		System.out.println("Hier stimmt noch nicht alles");
		((HumanDiagramGraphicalEditor) PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor())
				.setContent(KinectManager.INSTANCE.getSkeletonModel());

	}

	@CanExecute
	public boolean canExecute()
	{
		// TODO add some logic here
		return true;
	}
}
