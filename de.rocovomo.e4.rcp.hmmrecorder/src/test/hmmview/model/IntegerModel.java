package test.hmmview.model;



public class IntegerModel extends Model<IntegerModelListener>
{
	public final Property<Integer> property;

	public IntegerModel()
	{
		this.property = new Property<Integer>(0);
	}
}
