package hmm;

import hmm.input.SequenceProvider;

import java.io.IOException;
import java.util.List;

import be.ac.ulg.montefiore.run.jahmm.ObservationVector;
import be.ac.ulg.montefiore.run.jahmm.OpdfMultiGaussianFactory;
import be.ac.ulg.montefiore.run.jahmm.io.FileFormatException;
import be.ac.ulg.montefiore.run.jahmm.learn.KMeansLearner;

public class Example {

	public static void main(String[] args) throws IOException, FileFormatException {
		SequenceProvider provider = SequenceProvider.getInstance();
		List<List<ObservationVector>> list = provider.readInput();

		KMeansLearner<ObservationVector> kml = new KMeansLearner<ObservationVector>(
				3, new OpdfMultiGaussianFactory(3), list);
//		Hmm<ObservationVector> fittedHmm = kml.learn();
	}
}