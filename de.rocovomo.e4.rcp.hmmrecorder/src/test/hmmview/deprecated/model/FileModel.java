/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package test.hmmview.deprecated.model;

import java.io.File;

public class FileModel extends Model<FileModelListener>
{
	public final Property<File> property;
	
	public FileModel() {
		property = new Property<File>(null);
	}
}
