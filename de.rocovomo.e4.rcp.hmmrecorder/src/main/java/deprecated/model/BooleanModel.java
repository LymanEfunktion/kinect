/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package deprecated.model;

public class BooleanModel extends Model<BooleanModelListener>
{
	public final Property<Boolean> property;

	public BooleanModel()
	{
		this.property = new Property<Boolean>(false);
	}
}
