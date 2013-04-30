package de.rocovomo.util.hmm.gesture;

import java.util.List;

import org.apache.log4j.Logger;

import be.ac.ulg.montefiore.run.jahmm.Hmm;
import be.ac.ulg.montefiore.run.jahmm.ObservationVector;
import be.ac.ulg.montefiore.run.jahmm.OpdfMultiGaussian;
import be.ac.ulg.montefiore.run.jahmm.OpdfMultiGaussianFactory;
import be.ac.ulg.montefiore.run.jahmm.learn.BaumWelchScaledLearner;
import be.ac.ulg.montefiore.run.jahmm.learn.KMeansLearner;
import de.rocovomo.util.hmm.gesture.reference.GestureType;
import de.rocovomo.util.hmm.util.ObservationProvider;

public class Recognizer
{
	private Logger logger = Logger.getLogger(Recognizer.class);
	private Hmm<ObservationVector> recognizer;

	final GestureType HAND_CIRCLE = GestureType.HAND_CIRCLE;
	GestureType HAND_DIRECTION = GestureType.HAND_DIRECTION;
	GestureType HAND_HALT = GestureType.HAND_HALT;
	GestureType HAND_STRAIGHT = GestureType.HAND_STRAIGHT;
	GestureType HAND_UNLOCK = GestureType.HAND_UNLOCK;

	public Recognizer()
	{
		// TODO Auto-generated constructor stub
	}

	double getLowestAcceptableLnProbability(GestureType type)
	{
		return type.getLowestLnProbability()
				+ ((type.getHighestLnProbability() - type.getLowestLnProbability()) / 10.0);
	}

	public static void main(String[] args)
	{
		Recognizer recog = new Recognizer();

		recog.train();
		recog.test();
	}

	private void test()
	{
		
//		System.out.println(getLowestAcceptableLnProbability(HAND_CIRCLE));
	}

	public void train()
	{
		train(HAND_CIRCLE, "data/train.stream", "rw");
	}

	private boolean train(GestureType type, String name, String mode)
	{
		ObservationProvider provider = new ObservationProvider();
		List<List<ObservationVector>> sequences = provider.readInput(name, mode);

		int dimension = sequences.get(0).get(0).dimension();

		KMeansLearner<ObservationVector> kMeansLerner = new KMeansLearner<ObservationVector>(
				type.getNumHmmNodes(), new OpdfMultiGaussianFactory(dimension), sequences);
		double lowestLnProbability = Double.MAX_VALUE;
		double highestLnProbability = -Double.MAX_VALUE;
		try
		{
			Hmm<ObservationVector> kMeansHmm = kMeansLerner.iterate();
			BaumWelchScaledLearner bwl = new BaumWelchScaledLearner();
			bwl.setNbIterations(15);
			recognizer = bwl.learn(kMeansHmm, sequences);
			for (int i = 0; i < recognizer.nbStates(); i++)
			{
				OpdfMultiGaussian obsvec = (OpdfMultiGaussian) recognizer.getOpdf(i);
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
								this.recognizer.mostLikelyStateSequence(sequences.get(i)))
						+ ", ln Probability: " + lnProb + ")");
			}
		} catch (IllegalArgumentException e)
		{
			return false;
		}
		type.setHighestLnProbability(highestLnProbability);
		type.setLowestLnProbability(lowestLnProbability);
		return true;
	}
}
