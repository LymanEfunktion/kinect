/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package de.rocovomo.e4.rcp.hmmrecorder.view.tab;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.rocovomo.e4.rcp.hmmrecorder.model.HMMModel;
import de.rocovomo.e4.rcp.hmmrecorder.model.State;

class BufferContentProvider implements ITreeContentProvider
{
	private HMMModel model;

	public Object[] getElements(Object inputElement)
	{
		return model.getStates().toArray();
	}

	public void dispose()
	{

	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
	{
		this.model = (HMMModel) newInput;
	}

	public Object[] getChildren(Object parentElement)
	{
		if (parentElement instanceof State)
		{
			State state = (State) parentElement;
			return state.getVector().toArray();
		}
		return null;
	}

	public Object getParent(Object element)
	{
		return null;
	}

	public boolean hasChildren(Object element)
	{
		if (element instanceof State)
		{
			return true;
		}
		return false;
	}
}