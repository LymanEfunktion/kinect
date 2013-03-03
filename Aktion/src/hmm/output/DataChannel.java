package hmm.output;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class DataChannel
{

	private FileChannel channel;
	private DataBuffer data;

	public DataChannel(FileChannel channel)
	{
		data = new DataBuffer();
		this.channel = channel;
	}

	public void write(float[] vector) throws IOException
	{
		channel.force(true);
		ByteBuffer buffer = data.bufferVectorInclWhitespace(vector);
		System.out.println(buffer.position());
		buffer.flip();
		while (buffer.hasRemaining())
		{
			channel.write(buffer);
		}
	}

	public void read() throws IOException
	{
		if (channel.isOpen())
		{
			ByteBuffer buffer = ByteBuffer.allocate((int) channel.size());
			int bytesRead = channel.read(buffer);
			while (bytesRead != -1)
			{
				System.out.println("Read " + bytesRead);
				buffer.flip();

				while (buffer.hasRemaining())
				{
					System.out.print((char) buffer.get());
				}

				buffer.clear();
				bytesRead = channel.read(buffer);
			}
		}
	}

	public void setFileChannel(FileChannel initDefaultFile)
	{
		this.channel = initDefaultFile;
	}
}
