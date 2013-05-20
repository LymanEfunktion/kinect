package de.rocovomo.util.hmm.output;


import java.io.IOException;

import de.rocovomo.util.hmm.util.FileProvider;

@Deprecated
public class DataStreamer {
	// private PositionedElement element;
	private DataChannel channel;

	public DataStreamer() throws IOException {
		channel = new DataChannel(FileProvider.initFileChannel(
				"data/data.stream", "rw"));
	}

	public static void main(String[] args) throws IOException {
		DataStreamer st = new DataStreamer();
		st.test();
	}

	public void test() {
		// Body body = new BodyProviderDefaultImpl().getBody();
		// element = body.getLeftHand();
		IThread thread = new IThread();
		thread.run();
	}

	class IThread extends Thread {

		public IThread() {

		}

		public void run() {
			try {
				while (true) {
					// float[] vector = new float[] { element.getX(),
					// element.getY(), element.getZ() };
					float[] vector = new float[] { (float) Math.random(),
							(float) Math.random(), (float) Math.random() };
					channel.write(vector);
					sleep(100L);
				}
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
			}
		}
	}
}
