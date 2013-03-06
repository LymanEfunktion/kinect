package hmm;

import hmm.util.ObservationProvider;

import java.io.IOException;
import java.util.List;

import be.ac.ulg.montefiore.run.jahmm.ForwardBackwardCalculator;
import be.ac.ulg.montefiore.run.jahmm.Hmm;
import be.ac.ulg.montefiore.run.jahmm.ObservationInteger;
import be.ac.ulg.montefiore.run.jahmm.ObservationVector;
import be.ac.ulg.montefiore.run.jahmm.OpdfMultiGaussian;
import be.ac.ulg.montefiore.run.jahmm.OpdfMultiGaussianFactory;
import be.ac.ulg.montefiore.run.jahmm.draw.GenericHmmDrawerDot;
import be.ac.ulg.montefiore.run.jahmm.io.FileFormatException;
import be.ac.ulg.montefiore.run.jahmm.learn.BaumWelchLearner;
import be.ac.ulg.montefiore.run.jahmm.learn.KMeansLearner;
import be.ac.ulg.montefiore.run.jahmm.toolbox.KullbackLeiblerDistanceCalculator;

public class Example
{
	private static ObservationProvider provider = ObservationProvider
			.getInstance();

	private static List<List<ObservationInteger>> sequences;
	private static KullbackLeiblerDistanceCalculator klc;

	protected static void setUp() throws IOException
	{
		klc = new KullbackLeiblerDistanceCalculator();

		List<List<ObservationVector>> test = provider.readInput(
				"data/train.stream", "rw");
		KMeansLearner<ObservationVector> kml = new KMeansLearner<ObservationVector>(
				8, new OpdfMultiGaussianFactory(3), test);

		Hmm<ObservationVector> kmlHmm = kml.learn();

		GenericHmmDrawerDot hmmDrawer = new GenericHmmDrawerDot();

		hmmDrawer.write(kmlHmm, "test.dot");
		
		BaumWelchLearner bwl = new BaumWelchLearner();

		Hmm<ObservationVector> bwlHmm = bwl.learn(kmlHmm, test);

		ForwardBackwardCalculator fbcalc = new ForwardBackwardCalculator(test.get(0), kmlHmm);

		System.out.println(fbcalc.alphaElement(0, 0));
		//		System.out.println(fbcalc.toString());
		
//		System.out.println(kmlHmm.toString());
	}

//	 private Hmm<ObservationVector> buildInitHmm()
//	 {
//	 Hmm<ObservationVector> hmm = new Hmm<ObservationVector>(8,
//	 new OpdfMultiGaussianFactory(3));
//	
//	 hmm.setPi(0, 0.50);
//	 hmm.setPi(1, 0.50);
//	
//	 hmm.setOpdf(0, new OpdfMultiGaussian);
//	 hmm.setOpdf(1, new OpdfDiscrete<Packet>(Packet.class, new double[] {
//	 0.1, 0.9 }));
//	
//	 hmm.setAij(0, 1, 0.2);
//	 hmm.setAij(0, 0, 0.8);
//	 hmm.setAij(1, 0, 0.2);
//	 hmm.setAij(1, 1, 0.8);
//	
//	 return hmm;
//	 }

	private Hmm<ObservationVector> buildHmm()
	{
		return null;
	}

	public static void main(String[] args) throws IOException,
			FileFormatException
	{
		setUp();
		testIt();
	}

	public static void testIt() throws IOException
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
		Hmm<ObservationVector> hmm = new Hmm<ObservationVector>(8,
				new OpdfMultiGaussianFactory(2));
		hmm.setOpdf(2, omg);

		GenericHmmDrawerDot hmmDrawer = new GenericHmmDrawerDot();

		System.out.println(hmm.toString());
		
		hmmDrawer.write(hmm, "test.dot");
	}
}