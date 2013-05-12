/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package deprecated.model;

import de.rocovomo.util.hmm.gesture.reference.GestureTypeOLD;

public class GestureTypeModel extends Model<GestureTypeModelListener>
{
	public final Property<GestureTypeOLD> property;
	
	public GestureTypeModel() {
		property = new Property<GestureTypeOLD>(null);
	}
}
