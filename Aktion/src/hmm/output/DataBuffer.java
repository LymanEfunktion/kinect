package hmm.output;

import java.nio.ByteBuffer;

public class DataBuffer {

	private static final byte LEFT_SQUARE_BRACKET = 91;
	private static final byte RIGHT_SQUARE_BRACKET = 93;
	private static final byte WHITESPACE = 32;
	private static final byte SEMICOLON = 59;
	private static final byte NEW_LINE = 10;

	private static final int DIM_LIMIT = 3;
	private static final int BUFFER_SIZE = 96;

	public ByteBuffer initialize(int bufferSize) {
		ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
		return buffer;
	}
	
	private void checkVector(float[] vector) {
		if (vector.length != DIM_LIMIT)
			throw new IndexOutOfBoundsException("Vektor muss Dimension "
					+ DIM_LIMIT + " besitzen!");
	}

	public ByteBuffer bufferVector(float[] vector) {
		checkVector(vector);
		ByteBuffer buffer = initialize(BUFFER_SIZE);
		
		buffer.put(LEFT_SQUARE_BRACKET);
		buffer.put(WHITESPACE);
		for (int i = 0; i < DIM_LIMIT; i++) {
			bufferCoordinate(buffer, vector[i]);
		}
		buffer.put(RIGHT_SQUARE_BRACKET);
		buffer.put(SEMICOLON);
		return buffer;
	}
	
	public ByteBuffer bufferVectorInclWhitespace(float[] vector) {
		ByteBuffer buffer = bufferVector(vector);
		buffer.put(NEW_LINE);
		return buffer;
	}

	private void bufferCoordinate(ByteBuffer buffer, float value) {
		byte[] array = Float.toString(value).getBytes();
		buffer.put(array);
		buffer.put(WHITESPACE);
	}

	public ByteBuffer bufferWhitespace()
	{
		ByteBuffer buffer = initialize(BUFFER_SIZE);
		buffer.put(NEW_LINE);
		return buffer;
	}
}