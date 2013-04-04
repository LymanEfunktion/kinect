package hmm.test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.ListIterator;

public class NewChannel
{
	private FileChannel channel;
	private NewBuffer data;

	public NewChannel(FileChannel channel)
	{
		data = new NewBuffer();
		this.channel = channel;
	}

	public void write(List<List<Float>> sequence) throws IOException
	{
		System.out.println(channel.position());
		channel.position(channel.size());
		ListIterator<List<Float>> seq_iterator = sequence.listIterator();
		while (seq_iterator.hasNext())
		{
			List<Float> vector = seq_iterator.next();
			channel.write(data.bufferVector(vector));
		}
		channel.write(data.bufferNewLine());
	}

	public void read() throws IOException
	{
		ByteBuffer buf = ByteBuffer.allocateDirect(10);

		int numRead = 0;
		while (numRead >= 0)
		{
			buf.rewind();
			numRead = channel.read(buf);
			buf.rewind();
			for (int i = 0; i < numRead; i++)
			{
				System.out.print((char) buf.get());
			}
		}
	}
}