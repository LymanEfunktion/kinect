package de.rocovomo.util.hmm.gesture.reference;


import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import de.rocovomo.util.hmm.util.ObservationProvider;

import be.ac.ulg.montefiore.run.jahmm.Hmm;
import be.ac.ulg.montefiore.run.jahmm.ObservationVector;
import be.ac.ulg.montefiore.run.jahmm.OpdfMultiGaussian;
import be.ac.ulg.montefiore.run.jahmm.OpdfMultiGaussianFactory;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationSequencesReader;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationSequencesWriter;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationVectorReader;
import be.ac.ulg.montefiore.run.jahmm.learn.BaumWelchScaledLearner;
import be.ac.ulg.montefiore.run.jahmm.learn.KMeansLearner;

@Deprecated
public class Recognizer
{
	private static Logger logger = Logger.getLogger(Recognizer.class);
	private Hmm<ObservationVector> recognizer;

	private static ObservationProvider provider = ObservationProvider
			.getInstance();

	private double lowestLnProbability;
	private double highestLnProbability;

	public Recognizer(GestureType gesture)
	{
		recognizer = null;
	}

	public Recognizer()
	{
		// TODO Auto-generated constructor stub
	}

	double getLowestAcceptableLnProbability()
	{
		return lowestLnProbability
				+ ((highestLnProbability - lowestLnProbability) / 10.0);
	}

	boolean train(int numStates)
	{
		List<List<ObservationVector>> sequences = provider.readInput(
				"data/train.stream", "rw");
		int dimension = sequences.get(0).get(0).dimension();

		KMeansLearner<ObservationVector> kMeansLerner = new KMeansLearner<ObservationVector>(
				numStates, new OpdfMultiGaussianFactory(dimension), sequences);
		try
		{
			Hmm<ObservationVector> kMeansHmm = kMeansLerner.iterate();
			BaumWelchScaledLearner bwl = new BaumWelchScaledLearner();
		bwl.setNbIterations(15);
			recognizer = bwl.learn(kMeansHmm, sequences);
			for (int i = 0; i < recognizer.nbStates(); i++)
			{
				OpdfMultiGaussian obsvec = (OpdfMultiGaussian) recognizer
						.getOpdf(i);
				double[] mean = obsvec.mean();

				for (int j = 0; j < mean.length; j++)
				{
					// System.out.println(mean[j]);
					if (Double.isNaN(mean[j]) || Double.isInfinite(mean[j]))
					{
						return false;
					}
				}
			}
			lowestLnProbability = Double.MAX_VALUE;
			highestLnProbability = -Double.MAX_VALUE;
			for (int i = 0; i < sequences.size(); i++)
			{
				double lnProb = recognizer.lnProbability(sequences.get(i));
				if (lnProb < lowestLnProbability)
				{
					lowestLnProbability = lnProb;
				}
				if (lnProb > highestLnProbability)
				{
					highestLnProbability = lnProb;
				}
				System.out.println("Index "
						+ i
						+ ": (Base Probability: "
						+ this.recognizer.probability(sequences.get(i),
								this.recognizer
										.mostLikelyStateSequence(sequences
												.get(i)))
						+ ", ln Probability: " + lnProb + ")");
			}
		} catch (IllegalArgumentException e)
		{
			return false;
		}

		return true;
	}

	public static void main(String[] args)
	{
		Recognizer test = new Recognizer(GestureType.HAND_CIRCLE);
		List<ObservationVector> test2 = new ArrayList<ObservationVector>();
		ObservationVector vector = new ObservationVector(new double[]{1.0d, 1.0d, 2.0});
		System.out.println(test.train(8));
		System.out.println(test.highestLnProbability + " "
				+ test.lowestLnProbability);
	}

	public GestureType getGestureType()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public double lnProbability(GestureInstance inst)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public void load(Reader reader)
	{
		// TODO Auto-generated method stub
		
	}

	public void save(Writer writer)
	{
		// TODO Auto-generated method stub
		
	}
}
