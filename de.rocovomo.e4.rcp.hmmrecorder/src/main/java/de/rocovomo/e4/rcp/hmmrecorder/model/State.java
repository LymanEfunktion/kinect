/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package de.rocovomo.e4.rcp.hmmrecorder.model;

import java.util.ArrayList;
import java.util.List;


public class State
{
	private int node;
	List<Vector> vector;

	public State(List<Float> list, int node)
	{
		vector = new ArrayList<Vector>();
		vector.add(new Vector(list.get(0), 'X'));
		vector.add(new Vector(list.get(1), 'Y'));
		vector.add(new Vector(list.get(2), 'Z'));
		this.node = node;
	}

	public List<Vector> getVector()
	{
		return vector;
	}

	public String getNumber()
	{
		return "State " + node;
	}
}