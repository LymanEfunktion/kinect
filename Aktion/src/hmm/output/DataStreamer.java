package hmm.output;

import hmm.util.FileProvider;

import java.io.IOException;

import org.jnect.bodymodel.Body;
import org.jnect.bodymodel.PositionedElement;
import org.jnect.core.impl.BodyProviderDefaultImpl;

public class DataStreamer
{
	private PositionedElement element;
	private DataChannel channel;

	public DataStreamer() throws IOException
	{
		channel = new DataChannel(FileProvider.initFileChannel(
				"data/data.stream", "rw"));
	}

	public static void main(String[] args) throws IOException
	{
		DataStreamer st = new DataStreamer();
		st.test();
	}

	public void test()
	{
		Body body = new BodyProviderDefaultImpl().getBody();
		element = body.getLeftHand();
		IThread thread = new IThread();
		thread.run();
	}

	class IThread extends Thread
	{

		public IThread()
		{

		}

		public void run()
		{
			try
			{
				while (true)
				{
					float[] vector = new float[] { element.getX(),
							element.getY(), element.getZ() };
					channel.write(vector);
					sleep(100L);
				}
			} catch (InterruptedException | IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
