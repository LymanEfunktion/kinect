package test.hmmview.model;



public class DoubleModel extends Model<DoubleModelListener>
{
	public final Property<Double> property;

	public DoubleModel()
	{
		this.property = new Property<Double>(0.);
	}
}
