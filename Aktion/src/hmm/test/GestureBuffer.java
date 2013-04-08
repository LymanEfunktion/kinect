package hmm.test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Iterator;
import java.util.List;

public class GestureBuffer {
	private static final byte LEFT_SQUARE_BRACKET = 91;
	private static final byte RIGHT_SQUARE_BRACKET = 93;
	private static final byte WHITESPACE = 32;
	private static final byte SEMICOLON = 59;
	private static final byte NEW_LINE = 10;

	private static final int BUFFER_SIZE = 96;

	private MappedByteBuffer mappingBuffer;

	public GestureBuffer() {
	}

	public GestureBuffer(FileChannel channel) throws IOException {
		mappingBuffer = channel.map(FileChannel.MapMode.READ_WRITE,
				channel.position(), 350);
	}

	public ByteBuffer bufferVector(List<Float> vector) {
		ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
		buffer.put(LEFT_SQUARE_BRACKET);
		buffer.put(WHITESPACE);
		Iterator<Float> iterator = vector.iterator();
		while (iterator.hasNext()) {
			buffer.put(getValueAsByte(iterator.next()));
			buffer.put(WHITESPACE);
		}
		buffer.put(RIGHT_SQUARE_BRACKET);
		buffer.put(SEMICOLON).flip();
		return buffer;
	}

	private byte[] getValueAsByte(float value) {
		return Float.toString(value).getBytes();
	}

	public ByteBuffer bufferNewLine() {
		ByteBuffer buffer = ByteBuffer.allocate(10);
		buffer.put(NEW_LINE).flip();
		return buffer;
	}

	public void mapVector(List<Float> vector) {
		mappingBuffer.put(LEFT_SQUARE_BRACKET);
		mappingBuffer.put(WHITESPACE);
		Iterator<Float> iterator = vector.iterator();
		while (iterator.hasNext()) {
			mappingBuffer.put(getValueAsByte(iterator.next()));
			mappingBuffer.put(WHITESPACE);
		}
		mappingBuffer.put(RIGHT_SQUARE_BRACKET);
		mappingBuffer.put(SEMICOLON);

	}

	public void mapNewLine() {
		mappingBuffer.put(NEW_LINE);
	}
}