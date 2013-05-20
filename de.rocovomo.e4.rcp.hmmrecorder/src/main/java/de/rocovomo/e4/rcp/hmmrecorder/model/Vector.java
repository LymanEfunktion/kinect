/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package de.rocovomo.e4.rcp.hmmrecorder.model;

public class Vector
{
	private float value;
	private char coordinate;

	public Vector(float value, char coordinate)
	{
		this.value = value;
		this.coordinate = coordinate;
	}

	public String getCoordinate()
	{
		return coordinate + ": " + value;
	}
}