package test.hmmview.model;



public class BooleanModel extends Model<BooleanModelListener>
{
	public final Property<Boolean> property;

	public BooleanModel()
	{
		this.property = new Property<Boolean>(false);
	}
}
