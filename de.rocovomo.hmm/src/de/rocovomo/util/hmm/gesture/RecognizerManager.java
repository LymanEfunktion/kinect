/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package de.rocovomo.util.hmm.gesture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.log4j.Logger;

import be.ac.ulg.montefiore.run.jahmm.ObservationVector;
import de.rocovomo.util.hmm.gesture.reference.GestureGenre;

public class RecognizerManager
{
	// private static Logger logger = Logger.getLogger(RecognizerManager.class);
	private Map<GestureType, Recognizer> recognizerMap = new HashMap<GestureType, Recognizer>(
			GestureType.values().length);

	public RecognizerManager()
	{
		for (GestureType gesture : GestureType.values())
		{
			recognizerMap.put(gesture, new Recognizer(gesture));
		}
	}

	public void trainAll()
	{
		for (GestureType gesture : GestureType.values())
		{
			System.out.println("training: " + gesture.getName());
			train(gesture);
		}
	}

	public void train(GestureType gesture)
	{
		Recognizer gestureRecog = recognizerMap.get(gesture);
		gestureRecog.train();
	}

	private List<ObservationVector> convertFloatToObservationVector(List<List<Float>> sequence)
	{
		List<ObservationVector> vector = new ArrayList<ObservationVector>();
		ListIterator<List<Float>> iterator = sequence.listIterator();
		while (iterator.hasNext())
		{
			ListIterator<Float> innerIterator = iterator.next().listIterator();
			double x = innerIterator.next();
			double y = innerIterator.next();
			double z = innerIterator.next();
			vector.add(new ObservationVector(new double[] { x, y, z }));
		}
		return vector;
	}

	public GestureType recognizeIt(List<List<Float>> observation)
	{
		return recognize(convertFloatToObservationVector(observation));
	}

	public GestureType recognize(List<ObservationVector> observation)
	{
		Map<GestureGenre, Double> bestProbabilityMap = new Hashtable<GestureGenre, Double>(
				GestureGenre.values().length);
		Map<GestureGenre, GestureType> bestGestureTypeMap = new Hashtable<GestureGenre, GestureType>(
				GestureGenre.values().length);
		Map<GestureGenre, Double> lowestAcceptableProbMap = new Hashtable<GestureGenre, Double>(
				GestureGenre.values().length);

		// Setup variables for tracking the probabilities of various gestures
		double currProbability = -Double.MAX_VALUE;
		for (GestureGenre genre : GestureGenre.values())
		{
			bestProbabilityMap.put(genre, new Double(-Double.MAX_VALUE));
		}

		// Go through each recognizer - only use recognizers that are built for
		// the correct glove data
		// as is being provided by the gesture instance being recognized. We
		// split the recognition 'bests'
		// into categories based on the genre of the gesture (i.e., "basic",
		// "special", "easter-egg" gestures)
		// Later on, we favour basic gestures over special gestures and special
		// gestures over easter-egg gestures.
		for (Recognizer recognizer : this.recognizerMap.values())
		{
			GestureType gestureType = recognizer.getGestureType();

			// Find the highest probability gestures for each 'genre' of
			// gesture...
			currProbability = recognizer.lnProbability(observation);
			Double bestProbability = bestProbabilityMap.get(gestureType.getGenre());
			if (currProbability > bestProbability)
			{
				bestProbabilityMap.put(gestureType.getGenre(), currProbability);
				bestGestureTypeMap.put(gestureType.getGenre(), gestureType);
				lowestAcceptableProbMap.put(gestureType.getGenre(),
						recognizer.getLowestAcceptableLnProbability());
			}

		}

		GestureType bestGesture = bestGestureTypeMap.get(GestureGenre.ROBOT_CONTROL);

		// Make sure the best gesture's probability exceeds its lowest minimum
		// probability
		double bestProb = bestProbabilityMap.get(bestGesture.getGenre());
		double lowestAcceptableProb = lowestAcceptableProbMap.get(bestGesture.getGenre());
		if (bestProb < lowestAcceptableProb)
		{
			// logger.info("Gesture was not recognized because it did not meet its minimum acceptable probability threshold.");
			return null;
		}

		// logger.info("Best found matching gesture: " +
		// bestGesture.toString());
		return bestGesture;
	}

	public static void main(String[] args)
	{
		RecognizerManager manager = new RecognizerManager();
		List<List<Float>> sequence = new ArrayList<List<Float>>();
		for (int i = 0; i < 10; i++)
		{
			List<Float> vector = new ArrayList<Float>();
			vector.add((float) Math.random());
			vector.add((float) Math.random());
			vector.add((float) Math.random());
			sequence.add(vector);
		}
		System.out.println("Starting training\n========================");
		long start = System.nanoTime();
		manager.trainAll();
		System.out.println("------------------------\nStarting recognizing\n========================");
		manager.recognize(setupTest2());
		long end = System.nanoTime();
		System.out.println("Time: " + (end - start) + " ns");

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
}
