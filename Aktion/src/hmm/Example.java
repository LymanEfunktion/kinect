package hmm;

import hmm.util.ObservationProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import be.ac.ulg.montefiore.run.jahmm.Hmm;
import be.ac.ulg.montefiore.run.jahmm.ObservationInteger;
import be.ac.ulg.montefiore.run.jahmm.ObservationVector;
import be.ac.ulg.montefiore.run.jahmm.OpdfIntegerFactory;
import be.ac.ulg.montefiore.run.jahmm.OpdfMultiGaussian;
import be.ac.ulg.montefiore.run.jahmm.OpdfMultiGaussianFactory;
import be.ac.ulg.montefiore.run.jahmm.io.FileFormatException;
import be.ac.ulg.montefiore.run.jahmm.learn.BaumWelchLearner;
import be.ac.ulg.montefiore.run.jahmm.learn.KMeansLearner;

public class Example
{
	private static ObservationProvider provider = ObservationProvider.getInstance();

	private static Hmm<ObservationInteger> hmm;
	private static List<List<ObservationInteger>> sequences;
//	private static KullbackLeiblerDistanceCalculator klc;
//	private static MarkovGenerator<ObservationInteger> mg;
//	private static MarkovGenerator<ObservationVector> mgR;
//	private static Hmm<ObservationVector> hmmR;

	protected static void setUp()
	{
		hmm = new Hmm<ObservationInteger>(3, new OpdfIntegerFactory(10));
		hmm.getOpdf(0)
				.fit(new ObservationInteger(1), new ObservationInteger(2));
//		mg = new MarkovGenerator<ObservationInteger>(hmm);

		sequences = new ArrayList<List<ObservationInteger>>();
//		for (int i = 0; i < 100; i++)
//			sequences.add(mg.observationSequence(100));

//		klc = new KullbackLeiblerDistanceCalculator();
		
//		hmmR = new Hmm<ObservationVector>(0, null);
//		mgR = new MarkovGenerator<ObservationVector>(hmmR);

		KMeansLearner<ObservationVector> kml = new KMeansLearner<ObservationVector>(
				8, new OpdfMultiGaussianFactory(3), provider.readInput("data/test.stream", "rw"));
		Hmm<ObservationVector> fittedHmm = kml.learn();


		System.out.println(fittedHmm.toString());
	}

	public static void main(String[] args) throws IOException,
			FileFormatException
	{

		setUp();
		testIt();
		kMean();
	}

	public static void testIt()
	{
		double[] mean = { 2., 4. };
		double[][] covariance = { { 3., 2. }, { 2., 4. } };

		OpdfMultiGaussian omg = new OpdfMultiGaussian(mean, covariance);

		ObservationVector[] obs = new ObservationVector[10000];
		for (int i = 0; i < obs.length; i++)
		{
			obs[i] = omg.generate();
		}

		omg.fit(obs);
//		System.out.println(omg.toString());
	}

	public static void kMean()
	{
		// sequences = provider.readInputInteger();
		KMeansLearner<ObservationInteger> kml = new KMeansLearner<ObservationInteger>(
				5, new OpdfIntegerFactory(10), sequences);
		Hmm<ObservationInteger> initHmm = kml.learn();

		BaumWelchLearner bwl = new BaumWelchLearner();
		Hmm<ObservationInteger> learntHmm = bwl.learn(initHmm, sequences);
		System.out.println(learntHmm.toString());
	}

	// public void testBaumWelch()
	// {
	// /* Model sequences using BW algorithm */
	//
	// BaumWelchLearner bwl = new BaumWelchLearner();
	//
	// Hmm<ObservationInteger> bwHmm = bwl.learn(hmm, sequences);
	//
	// assertEquals(0., klc.distance(bwHmm, hmm), DELTA);
	//
	// /* Model sequences using the scaled BW algorithm */
	//
	// BaumWelchScaledLearner bwsl = new BaumWelchScaledLearner();
	// bwHmm = bwsl.learn(hmm, sequences);
	//
	// assertEquals(0., klc.distance(bwHmm, hmm), DELTA);
	// }
}