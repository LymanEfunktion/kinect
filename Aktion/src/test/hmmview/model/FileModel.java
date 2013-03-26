package test.hmmview.model;

import java.io.RandomAccessFile;

import test.hmmview.Model;


public class FileModel extends Model<FileModelListener>
{
	public final Property<RandomAccessFile> file;
	
	public FileModel() {
		file = new Property<RandomAccessFile>(null);
	}
}
