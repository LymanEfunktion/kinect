/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package de.rocovomo.e4.rcp.hmmrecorder.util;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ListIterator;

import de.rocovomo.util.hmm.refactor.GestureChannel;
import de.rocovomo.util.hmm.util.FileHandler;

public class GestureStreamer extends Thread
{
	private FileHandler handler;
	private GestureChannel newchannel;

	public GestureStreamer(URL url) throws IOException
	{
		handler = new FileHandler(url.getFile(), "rws");
		newchannel = new GestureChannel(handler.getChannelAppendigFile());
	}

	public void stream(List<List<List<Float>>> sequences) throws IOException
	{
		// long start = System.nanoTime();
		ListIterator<List<List<Float>>> iterator = sequences.listIterator();
		while (iterator.hasNext())
		{
			newchannel.write(iterator.next());
		}

		// newchannel.map(sequence);
		// long end = System.nanoTime();
		// System.out.println("It took " + (end - start) + " nanoseconds");
	}
}