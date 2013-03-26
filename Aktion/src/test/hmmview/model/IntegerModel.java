package test.hmmview.model;

import test.hmmview.Model;


public class IntegerModel extends Model<IntegerModelListener>
{
	public final Property<Integer> property;

	public IntegerModel()
	{
		this.property = new Property<Integer>(0);
	}
}
