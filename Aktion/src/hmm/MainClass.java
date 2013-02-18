package hmm;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class MainClass {
	public static void main(String[] args) {
		try {
			test();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void test() throws Exception {
		long[] primes = new long[] { 1, 2, 3, 5, 7 };
		File aFile = new File("C:/test/primes.txt");
		FileOutputStream outputFile = null;
		outputFile = new FileOutputStream(aFile);
		FileChannel file = outputFile.getChannel();
		ByteBuffer[] buffers = new ByteBuffer[3];
		buffers[0] = ByteBuffer.allocate(8);
		buffers[2] = ByteBuffer.allocate(8);
		String primeStr = null;
		for (long prime : primes) {
			primeStr = "prime = " + prime;
			buffers[0].putDouble((double) primeStr.length()).flip();
			buffers[1] = ByteBuffer.allocate(primeStr.length());
			buffers[1].put(primeStr.getBytes()).flip();
			buffers[2].putLong(prime).flip();
			file.write(buffers);
			buffers[0].clear();
			buffers[2].clear();
		}
		System.out.println("File written is " + file.size() + "bytes.");
		outputFile.close();
	}
}