package test.hmmview.model;

import test.hmmview.Model;


public class DoubleModel extends Model<DoubleModelListener>
{
	public final Property<Double> property;

	public DoubleModel()
	{
		this.property = new Property<Double>(0.);
	}
}
