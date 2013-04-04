package hmm.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

@Deprecated
public class FileProvider
{
	private static RandomAccessFile file;
	
	public static FileChannel initFileChannel(String name, String mode)
			throws IOException
	{
		file = new RandomAccessFile(name, mode);
		file.seek(file.length());
		System.out.println(file.getFilePointer());
		return file.getChannel();
	}

	public static InputStreamReader initInputStream(RandomAccessFile file)
			throws IOException
	{
		return new InputStreamReader(new FileInputStream(file.getFD()));
	}

	public static InputStreamReader initInputStream(String name, String mode)
			throws IOException
	{
		file = new RandomAccessFile(name, mode);
		return initInputStream(file);
	}

	public static void closeConnection() throws IOException
	{
		file.close();
	}
}
