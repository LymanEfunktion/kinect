package test.hmmview;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.databinding.observable.value.WritableValue;

import test.hmmview.model.Trainer;

import de.rocovomo.util.hmm.util.FileHandler;
import de.rocovomo.util.hmm.refactor.GestureChannel;

public class GestureStreamer extends Thread
{
	private FileHandler handler;
	private GestureChannel newchannel;
	private List<List<Float>> sequence;

	private long interval;
	private long frame;

	public GestureStreamer(WritableValue value) throws IOException
	{
		URL url = ((Trainer) value.getValue()).getFileUrl();
		handler = new FileHandler(url.getFile(), "rws");
		newchannel = new GestureChannel(handler.getChannelAppendigFile());
		setInterval(0L);
		setFrame(0L);
		this.sequence = new ArrayList<List<Float>>();
	}

	public void stream() throws IOException
	{
		// long start = System.nanoTime();
		newchannel.write(sequence);
		// newchannel.map(sequence);
		// long end = System.nanoTime();
		// System.out.println("It took " + (end - start) + " nanoseconds");
	}

	@Override
	public void run()
	{
		long time = 0L;
		try
		{
			while (time < getFrame())
			{
				sequence.add(Arrays.asList((float) Math.random(), (float) Math.random(),
						(float) Math.random()));
				time += getInterval();
				sleep(getInterval());
			}

		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public long getInterval()
	{
		return interval;
	}

	public void setInterval(long interval)
	{
		this.interval = interval;
	}

	public long getFrame()
	{
		return frame;
	}

	public void setFrame(long frame)
	{
		this.frame = frame;
	}
}