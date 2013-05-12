/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package deprecated.model;

public class StringModel extends Model<StringModelListener> {
	
	public final Property<String> property;

	public StringModel()
	{
		this.property = new Property<String>("");
	}
}