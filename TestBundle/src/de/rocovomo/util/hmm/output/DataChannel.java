package de.rocovomo.util.hmm.output;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Deprecated
public class DataChannel {

	private FileChannel channel;
	private DataBuffer data;

	public DataChannel(FileChannel channel) {
		data = new DataBuffer();
		this.channel = channel;
	}

	public void write(float[] vector) throws IOException {
		channel.force(true);
		ByteBuffer buffer = data.bufferVector(vector);
		System.out.println(buffer.position());
		buffer.flip();
		while (buffer.hasRemaining()) {
			channel.write(buffer);
		}
	}

	public void write(List<List<Float>> sequence) throws IOException {
		ListIterator<List<Float>> seq_iterator = sequence.listIterator();
		ByteBuffer buffer;
		while (seq_iterator.hasNext()) {
			List<Float> vector = seq_iterator.next();
			Iterator<Float> vec_iterator = vector.iterator();
			while (vec_iterator.hasNext()) {
				float x = vec_iterator.next();
				float y = vec_iterator.next();
				float z = vec_iterator.next();
				buffer = data.bufferVector(new float[] { x, y, z });
				buffer.flip();
				while (buffer.hasRemaining()) {
					channel.write(buffer);
				}
			}
		}
		ByteBuffer tset = ByteBuffer.allocate(1).put((byte) 10);
		tset.flip();
		channel.write(tset);
	}

	public void read() throws IOException {
		if (channel.isOpen()) {
			ByteBuffer buffer = ByteBuffer.allocate((int) channel.size());
			int bytesRead = channel.read(buffer);
			while (bytesRead != -1) {
				System.out.println("Read " + bytesRead);
				buffer.flip();

				while (buffer.hasRemaining()) {
					System.out.print((char) buffer.get());
				}

				buffer.clear();
				bytesRead = channel.read(buffer);
			}
		}
	}

	public void setFileChannel(FileChannel initDefaultFile) {
		this.channel = initDefaultFile;
	}
}
