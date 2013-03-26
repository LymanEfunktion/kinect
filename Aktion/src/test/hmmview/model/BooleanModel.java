package test.hmmview.model;

import test.hmmview.Model;


public class BooleanModel extends Model<BooleanModelListener>
{
	public final Property<Boolean> property;

	public BooleanModel()
	{
		this.property = new Property<Boolean>(false);
	}
}
