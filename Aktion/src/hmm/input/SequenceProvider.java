package hmm.input;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import be.ac.ulg.montefiore.run.jahmm.ObservationVector;
import be.ac.ulg.montefiore.run.jahmm.io.FileFormatException;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationSequencesReader;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationVectorReader;

public class SequenceProvider {

	private static SequenceProvider provider;

	private SequenceProvider() {
	}

	public static SequenceProvider getInstance() {
		if (provider == null) {
			provider = new SequenceProvider();
		}
		return provider;
	}

	public List<List<ObservationVector>> readInput() {
		Reader reader;
		List<List<ObservationVector>> v = null;
		
		try {
			reader = new FileReader("A.seq");
			
			v = ObservationSequencesReader.readSequences(
					new ObservationVectorReader(3), reader);
			reader.close();
		} catch (IOException | FileFormatException e) {
			e.printStackTrace();
		}
		return v;
	}
}
