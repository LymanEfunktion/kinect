package de.rocovomo.util.hmm.refactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.ListIterator;

public class GestureChannel
{
	private FileChannel channel;
	private GestureBuffer data;

	public GestureChannel(FileChannel channel) throws IOException
	{
		data = new GestureBuffer();
		this.channel = channel;
	}

	public void map(List<List<Float>> sequence) throws IOException{
		ListIterator<List<Float>> seq_iterator = sequence.listIterator();
		while (seq_iterator.hasNext())
		{
			data.mapVector(seq_iterator.next());
		}
		data.mapNewLine();
	}
	
	public void write(List<List<Float>> sequence) throws IOException
	{
		System.out.println(channel.position());
		ListIterator<List<Float>> seq_iterator = sequence.listIterator();
		while (seq_iterator.hasNext())
		{
			channel.write(data.bufferVector(seq_iterator.next()));
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