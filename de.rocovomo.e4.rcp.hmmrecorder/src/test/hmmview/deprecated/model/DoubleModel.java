/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package test.hmmview.deprecated.model;

public class DoubleModel extends Model<DoubleModelListener>
{
	public final Property<Double> property;

	public DoubleModel()
	{
		this.property = new Property<Double>(0.);
	}
}
