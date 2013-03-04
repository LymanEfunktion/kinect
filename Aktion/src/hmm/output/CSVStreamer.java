package hmm.output;

import hmm.input.CSVChannel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CSVStreamer
{
	private CSVChannel[] csvArray;

	public CSVStreamer(String[] names, String mode) throws IOException
	{
		int index = names.length;
		csvArray = new CSVChannel[index];
		for (int i = 0; i < index; i++)
		{
			csvArray[i] = new CSVChannel(names[i], mode);
		}
	}

	public boolean writeContent(String name, String mode) throws IOException
	{
		return writeContent(new RandomAccessFile(name, mode));
	}

	public boolean writeContent(RandomAccessFile file) throws IOException
	{
		boolean isWritten = false;
		List<List<String>> listX = csvArray[0].read();
		List<List<String>> listY = csvArray[1].read();
		List<List<String>> listZ = csvArray[2].read();

		if (listX.size() == listY.size() && listX.size() == listZ.size())
		{
			ListIterator<List<String>> xIt = listX.listIterator();
			ListIterator<List<String>> yIt = listY.listIterator();
			ListIterator<List<String>> zIt = listZ.listIterator();

			DataBuffer data = new DataBuffer();
			FileChannel fileChannel = file.getChannel();
			while (xIt.hasNext() && yIt.hasNext() && zIt.hasNext())
			{
				List<String> xx = xIt.next();
				List<String> yy = yIt.next();
				List<String> zz = zIt.next();

				Iterator<String> xxIt = xx.iterator();
				Iterator<String> yyIt = yy.iterator();
				Iterator<String> zzIt = zz.iterator();

				while (xxIt.hasNext() && yyIt.hasNext() && zzIt.hasNext())
				{
					Float x = new Float((String) xxIt.next());
					Float y = new Float((String) yyIt.next());
					Float z = new Float((String) zzIt.next());
					float[] vector = new float[] { x.floatValue(),
							y.floatValue(), z.floatValue() };
					fileChannel.force(true);
					ByteBuffer buffer = data.bufferVector(vector);
//					System.out.println(buffer.position());
					buffer.flip();
					while (buffer.hasRemaining())
					{
						fileChannel.write(buffer);
					}
				}
				ByteBuffer buffer = data.bufferWhitespace();
				buffer.flip();
				fileChannel.write(buffer);
			}
			isWritten = true;
		}
		return isWritten;
	}

	public static void main(String[] args) throws IOException
	{
		CSVStreamer streamer = new CSVStreamer(new String[] {
				"data/test/circle_x.csv", "data/test/circle_y.csv",
				"data/test/circle_z.csv" }, "rw");
		streamer.writeContent("data/test.stream", "rw");
	}
}
