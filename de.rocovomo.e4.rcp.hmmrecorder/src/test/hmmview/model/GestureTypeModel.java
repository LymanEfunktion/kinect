package test.hmmview.model;

import de.rocovomo.util.hmm.gesture.reference.GestureType;

public class GestureTypeModel extends Model<GestureTypeModelListener>
{
	public final Property<GestureType> property;
	
	public GestureTypeModel() {
		property = new Property<GestureType>(null);
	}
}
