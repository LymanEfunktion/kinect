package de.rocovomo.util.hmm.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class FileHandler {

	private RandomAccessFile file;
	
	public FileHandler(String name, String mode) throws FileNotFoundException {
		file = new RandomAccessFile(name,mode);
	}
	
	public FileChannel getChannelAppendigFile() throws IOException {
		file.seek(file.length());
		return file.getChannel();
	}
	
	public void closeFile() throws IOException {
		file.close();
	}
}
