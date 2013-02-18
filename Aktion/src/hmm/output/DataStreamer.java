package hmm.output;

import hmm.file.FileProvider;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.jnect.bodymodel.Body;
import org.jnect.bodymodel.PositionedElement;
import org.jnect.core.impl.BodyProviderDefaultImpl;

public class DataStreamer {

	PositionedElement element;
	DataChannel channel;

	public DataStreamer() throws FileNotFoundException {
		channel = new DataChannel();
		channel.setFileChannel(new FileProvider().initDefaultFileChannel());
	}

	public static void main(String[] args) throws FileNotFoundException {
		DataStreamer st = new DataStreamer();
		st.test();
	}

	public void test() {
		Body body = new BodyProviderDefaultImpl().getBody();
		element = body.getLeftHand();
		IThread thread = new IThread();
		thread.run();
	}

	class IThread extends Thread {

		public IThread() {

		}

		public void run() {

			try {
				while (true) {
					float[] vector = new float[] { element.getX(),
							element.getY(), element.getZ() };
					channel.write(vector);
					sleep(100L);
				}
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
			}
		}
	}
}
