package hmm.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class FileProvider {

	private RandomAccessFile defaultFile;
	private FileChannel channel;

	public FileProvider() throws FileNotFoundException {
		defaultFile = new RandomAccessFile("data/defaultdata.stream", "rw");
	}

	public FileChannel getChannel() {
		return channel;
	}

	public FileChannel initDefaultFileChannel() throws FileNotFoundException {
		channel = defaultFile.getChannel();
		return defaultFile.getChannel();
	}

	public InputStreamReader initDefaultInputStream() throws IOException {
		return new InputStreamReader(new FileInputStream(defaultFile.getFD()));
	}

	public void closeConnection() throws IOException {
		channel.close();
		defaultFile.close();
	}
}
