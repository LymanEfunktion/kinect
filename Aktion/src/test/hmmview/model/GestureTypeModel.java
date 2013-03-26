package test.hmmview.model;

import test.hmmview.Model;
import hmm.gesture.reference.GestureType;

public class GestureTypeModel extends Model<GestureTypeModelListener>
{
	public final Property<GestureType> gestureType;
	
	public GestureTypeModel() {
		gestureType = new Property<GestureType>(null);
	}
}
