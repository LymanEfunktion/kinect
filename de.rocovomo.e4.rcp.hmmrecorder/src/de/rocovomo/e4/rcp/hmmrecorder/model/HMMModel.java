/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package de.rocovomo.e4.rcp.hmmrecorder.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class HMMModel
{
	private List<State> states;

	public HMMModel(List<List<Float>> sequence)
	{
		states = new ArrayList<State>();
		Iterator<List<Float>> iterator = sequence.listIterator();
		int i = 0;
		while (iterator.hasNext())
		{
			i++;
			states.add(new State(iterator.next(), i));
		}
	}

	public List<State> getStates()
	{
		return states;
	}
}