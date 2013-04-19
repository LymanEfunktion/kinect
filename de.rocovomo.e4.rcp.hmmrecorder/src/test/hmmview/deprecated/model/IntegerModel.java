/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package test.hmmview.deprecated.model;

public class IntegerModel extends Model<IntegerModelListener>
{
	public final Property<Integer> property;

	public IntegerModel()
	{
		this.property = new Property<Integer>(0);
	}
}
