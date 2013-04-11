package test.hmmview.model;

import java.io.File;

public class FileModel extends Model<FileModelListener>
{
	public final Property<File> property;
	
	public FileModel() {
		property = new Property<File>(null);
	}
}
