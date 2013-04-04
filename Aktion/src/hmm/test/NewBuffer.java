package hmm.test;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;

public class NewBuffer
{
	private static final byte LEFT_SQUARE_BRACKET = 91;
	private static final byte RIGHT_SQUARE_BRACKET = 93;
	private static final byte WHITESPACE = 32;
	private static final byte SEMICOLON = 59;
	private static final byte NEW_LINE = 10;

	private static final int BUFFER_SIZE = 96;

	public ByteBuffer bufferVector(List<Float> vector)
	{
		ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
		buffer.put(LEFT_SQUARE_BRACKET);
		buffer.put(WHITESPACE);
		Iterator<Float> iterator = vector.iterator();
		while (iterator.hasNext())
		{
			buffer.put(getValueAsByte(iterator.next()));
			buffer.put(WHITESPACE);
		}
		buffer.put(RIGHT_SQUARE_BRACKET);
		buffer.put(SEMICOLON).flip();
		return buffer;
	}

	private byte[] getValueAsByte(float value)
	{
		return Float.toString(value).getBytes();
	}

	public ByteBuffer bufferNewLine()
	{
		ByteBuffer buffer = ByteBuffer.allocate(10);
		buffer.put(NEW_LINE).flip();
		return buffer;
	}
}