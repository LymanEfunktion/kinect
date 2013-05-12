/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package de.rocovomo.util.hmm.gesture;

import java.util.ArrayList;
import java.util.List;

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
	private Hmm<ObservationVector> recognizer;
	private GestureType type;

	public Recognizer(GestureType type)
	{
		this.type = type;
	}

	double getLowestAcceptableLnProbability(GestureType type)
	{
		return type.getLowestLnProbability()
				+ ((type.getHighestLnProbability() - type.getLowestLnProbability()) / 10.0);
	}

	private void test(List<ObservationVector> sequence)
	{
		/**
		 * Liest die Sequenz aus Daten in das HMM ein und spuckt lnProb aus
		 * Diese muss mit Probs der anderen HMMs der anderen Gesten verglichen
		 * werden Die Sequenz - sprich Geste - mit der niedrigsten lnProb Zahl
		 * gewinnt und wird erkannt!
		 */
		System.out.println(recognizer.lnProbability(sequence));
		System.out.println(getLowestAcceptableLnProbability(type));
	}

	public void train()
	{
		/**
		 * Lädt die trainigsdaten hier für kreisbewegungen Muss für jede Geste
		 * separart durchgeführt werden und dessen HMM im System gespeichert
		 * werden
		 */
		train(type, "data/train.stream", "rw");
	}

	private boolean train(GestureType type, String name, String mode)
	{
		/**
		 * Um alle Gestentypen zu unterstützen muss recognizer extrahiert werden
		 * und in einer Map oder einem anderen Objekt gespeichert werden oder
		 * man setzt über den Recognizer einen RecognizerManager auf
		 */
		ObservationProvider provider = new ObservationProvider();
		List<List<ObservationVector>> sequences = provider.readInput(name, mode);

		int dimension = sequences.get(0).get(0).dimension();

		KMeansLearner<ObservationVector> kMeansLerner = new KMeansLearner<ObservationVector>(10,
				new OpdfMultiGaussianFactory(dimension), sequences);
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

	public static void main(String[] args)
	{
		//Training der Daten - hier nur für Kreisbewegung
		Recognizer recog = new Recognizer(GestureType.HAND_CIRCLE);
		recog.train();

		//Test mittels verschiedener Daten
		List<ObservationVector> sequence = new ArrayList<ObservationVector>();
		for (int i = 0; i < 10; i++)
		{
			double[] state = new double[] { Math.random(), Math.random(), Math.random() };
			sequence.add(new ObservationVector(state));
		}
		recog.test(sequence);
		recog.test(setupTest2());
		recog.test(setupTest());
		//Nach Prinzip wird Sequenz aus setupTest2 erkannt
	}

	private static List<ObservationVector> setupTest2()
	{
		List<ObservationVector> sequence = new ArrayList<ObservationVector>();
		sequence.add(new ObservationVector(new double[] { 277.77768, 82.81095, 1203.105 }));
		sequence.add(new ObservationVector(new double[] { 284.68365, -11.130719, 1196.9127 }));
		sequence.add(new ObservationVector(new double[] { 292.94604, -95.658066, 1185.6426 }));
		sequence.add(new ObservationVector(new double[] { 255.44606, 28.872587, 1177.3461 }));
		sequence.add(new ObservationVector(new double[] { 237.7724, -193.60861, 1184.2189 }));
		sequence.add(new ObservationVector(new double[] { 300.64633, -98.84761, 1213.9707 }));
		sequence.add(new ObservationVector(new double[] { 285.5185, 43.555706, 1232.435 }));
		sequence.add(new ObservationVector(new double[] { 312.29172, -44.465973, 1228.5061 }));
		sequence.add(new ObservationVector(new double[] { 316.96466, -102.097374, 1249.8452 }));
		sequence.add(new ObservationVector(new double[] { 285.26047, 12.219854, 1246.115 }));
		return sequence;
	}

	private static List<ObservationVector> setupTest()
	{
		List<ObservationVector> sequence = new ArrayList<ObservationVector>();
		sequence.add(new ObservationVector(new double[] { 242.96841, -399.36673, 1096.3035 }));
		sequence.add(new ObservationVector(new double[] { 256.67307, -347.05222, 1112.2028 }));
		sequence.add(new ObservationVector(new double[] { 2.921609, -455.98038, 1163.373 }));
		sequence.add(new ObservationVector(new double[] { 141.52026, -430.9413, 1154.9133 }));
		sequence.add(new ObservationVector(new double[] { 153.64037, -402.88107, 1091.3782 }));
		sequence.add(new ObservationVector(new double[] { 175.10986, -398.0459, 1131.1959 }));
		sequence.add(new ObservationVector(new double[] { -147.84436, -416.24478, 1133.5172 }));
		sequence.add(new ObservationVector(new double[] { -100.43646, -435.33215, 1180.0018 }));
		sequence.add(new ObservationVector(new double[] { -36.680676, -375.0737, 1095.3447 }));
		sequence.add(new ObservationVector(new double[] { 112.940186, -320.9169, 1133.5038 }));
		return sequence;
	}
}
