package hmm.output;

import java.nio.ByteBuffer;

@Deprecated
public class DataBuffer
{
	private static final byte LEFT_SQUARE_BRACKET = 91;
	private static final byte RIGHT_SQUARE_BRACKET = 93;
	private static final byte WHITESPACE = 32;
	private static final byte SEMICOLON = 59;
	private static final byte NEW_LINE = 10;

	private static final int BUFFER_SIZE = 48;

	public ByteBuffer initialize(int bufferSize)
	{
		ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
		return buffer;
	}

	public ByteBuffer bufferVector(float[] vector)
	{
		ByteBuffer buffer = initialize(BUFFER_SIZE);

		buffer.put(LEFT_SQUARE_BRACKET);
		buffer.put(WHITESPACE);
		for (float value : vector)
		{
			bufferCoordinate(buffer, value);
		}
		buffer.put(RIGHT_SQUARE_BRACKET);
		buffer.put(SEMICOLON);
		return buffer;
	}

	public ByteBuffer bufferVectorInclNewLine(float[] vector)
	{
		ByteBuffer buffer = bufferVector(vector);
		buffer.put(NEW_LINE);
		return buffer;
	}

	private void bufferCoordinate(ByteBuffer buffer, float value)
	{
		byte[] array = Float.toString(value).getBytes();
		buffer.put(array);
		buffer.put(WHITESPACE);
	}

	public ByteBuffer bufferNewLine()
	{
		ByteBuffer buffer = initialize(BUFFER_SIZE);
		buffer.put(NEW_LINE);
		return buffer;
	}
}