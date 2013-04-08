package test.hmmview;

import hmm.test.GestureChannel;
import hmm.util.FileHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GestureStreamer extends Thread
{
	private FileHandler handler;
	
	private GestureChannel newchannel;

	private List<List<Float>> sequence;

	private long interval;
	private long frame;

	public GestureStreamer(long interval, long frame) throws IOException
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
//		newchannel.map(sequence);
		long end = System.nanoTime();
		System.out.println("It took " + (end - start) + " nanoseconds");
	}

//	float f = 23f;
//    byte[] op = new byte[4];
//    int fi = Float.floatToIntBits(f);
//    for (int i = 0; i < 4; i++)
//        {
//            int offset = (op.length - 1 - i) * 8;
//            op[i] = (byte) ((fi >>> offset) & 0xff);
//        }
//    for(byte b : op)
//        {
//            System.out.format("0x%02X ", b);
//        }

	// FileChannel fc = new RandomAccessFile("temp.tmp", "rw").getChannel();
	// IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0,
	// fc.size()).asIntBuffer();
	// for (int i = 0; i < 10; i++)
	// ib.put(i);
	// fc.close();

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
		GestureStreamer test = new GestureStreamer(100L, 800L);
		test.run();
		test.stream();
	}
}
