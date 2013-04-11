package de.rocovomo.util.hmm.input;


import java.io.IOException;
import java.io.Reader;
import java.nio.channels.FileChannel;
import java.util.List;

import de.rocovomo.util.hmm.util.FileProvider;

public class CSVChannel
{
	private FileChannel channel;
	private Reader reader;

	public CSVChannel(FileChannel channel)
	{
		this.channel = channel;
	}

	public CSVChannel(String name, String mode) throws IOException
	{
		channel = FileProvider.initFileChannel(name, mode);
		reader = FileProvider.initInputStream(name, mode);
	}

	public List<List<String>> read() throws IOException
	{
		if (channel.isOpen())
		{
			CSVBuffer csvBuffer = new CSVBuffer(reader);
			return csvBuffer.getContent();
		}
		return null;
	}
}
