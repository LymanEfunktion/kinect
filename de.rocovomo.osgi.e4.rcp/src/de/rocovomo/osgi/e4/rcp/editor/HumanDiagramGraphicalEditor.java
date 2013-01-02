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
package de.rocovomo.osgi.e4.rcp.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.jnect.bodymodel.Body;

import de.rocovomo.osgi.e4.rcp.editpart.HumanDiagramEditPartFactory;

public class HumanDiagramGraphicalEditor extends GraphicalEditor {

	public HumanDiagramGraphicalEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		getGraphicalViewer().setEditPartFactory(new HumanDiagramEditPartFactory());
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		//
	}

	public void setContent(Body container) {
		getGraphicalViewer().setContents(container);
	}

	@Override
	protected void initializeGraphicalViewer() {
		// TODO Auto-generated method stub
	}

}
