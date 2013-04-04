package test.hmmview.model;

public class StringModel extends Model<StringModelListener> {
	
	public final Property<String> property;

	public StringModel()
	{
		this.property = new Property<String>("");
	}
}