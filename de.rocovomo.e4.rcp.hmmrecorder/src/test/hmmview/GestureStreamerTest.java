package test.hmmview;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.rocovomo.util.hmm.util.FileHandler;
import de.rocovomo.util.hmm.refactor.GestureChannel;

public class GestureStreamerTest extends Thread
{
	private FileHandler handler;
	private GestureChannel newchannel;
	private List<List<Float>> sequence;

	private long interval;
	private long frame;

	public GestureStreamerTest(long interval, long frame) throws IOException
	{
		handler = new FileHandler("data/data.stream", "rws");
		newchannel = new GestureChannel(handler.getChannelAppendigFile());
		this.interval = interval;
		this.frame = frame;
		this.sequence = new ArrayList<List<Float>>();
	}

	public void stream() throws IOException
	{
		long start = System.nanoTime();
		newchannel.write(sequence);
		// newchannel.map(sequence);
		long end = System.nanoTime();
		// System.out.println("It took " + (end - start) + " nanoseconds");
	}

	@Override
	public void run()
	{
		long time = 0L;
		try
		{
			while (time < frame)
			{
				sequence.add(Arrays.asList((float) Math.random(), (float) Math.random(),
						(float) Math.random()));
				time += interval;
				sleep(interval);
			}

		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException
	{
		GestureStreamerTest test = new GestureStreamerTest(100L, 800L);
		test.run();
		test.stream();
	}
}
