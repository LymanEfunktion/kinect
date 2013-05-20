package de.rocovomo.e4.rcp.hmmrecorder.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileProvider
{
	public static String getFileContent(final FileChannel channel) throws IOException
	{
		ByteBuffer buffer = ByteBuffer.allocate(48);
		String content = "";
		int bytesRead = channel.read(buffer);
		while (bytesRead != -1)
		{
			buffer.flip();
			while (buffer.hasRemaining())
			{
				content += Character.toString((char) buffer.get());
			}
			buffer.clear();
			bytesRead = channel.read(buffer);
		}
		return content;
	}
}
