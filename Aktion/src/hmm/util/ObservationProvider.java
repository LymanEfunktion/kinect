package hmm.util;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import be.ac.ulg.montefiore.run.jahmm.ObservationInteger;
import be.ac.ulg.montefiore.run.jahmm.ObservationVector;
import be.ac.ulg.montefiore.run.jahmm.io.FileFormatException;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationIntegerReader;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationSequencesReader;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationVectorReader;

public class ObservationProvider
{
	private static ObservationProvider provider;

	public static ObservationProvider getInstance()
	{
		if (provider == null)
		{
			provider = new ObservationProvider();
		}
		return provider;
	}
	
	public List<List<ObservationVector>> readInput(String name, String mode)
	{
		Reader reader;
		List<List<ObservationVector>> sequences = null;

		try
		{
			reader = FileProvider.initInputStream(name, mode);

			sequences = ObservationSequencesReader.readSequences(
					new ObservationVectorReader(), reader);
			reader.close();
		} catch (IOException | FileFormatException e)
		{
			e.printStackTrace();
		}
		return sequences;
	}

	public List<List<ObservationInteger>> readInputInteger(String name,
			String mode)
	{
		Reader reader;
		List<List<ObservationInteger>> sequences = null;

		try
		{
			reader = FileProvider.initInputStream(name, mode);

			sequences = ObservationSequencesReader.readSequences(
					new ObservationIntegerReader(), reader);
			reader.close();
		} catch (IOException | FileFormatException e)
		{
			e.printStackTrace();
		}
		return sequences;
	}
}
