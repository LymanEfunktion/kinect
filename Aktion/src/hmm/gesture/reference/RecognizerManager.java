package hmm.gesture.reference;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

class RecognizerManager {
	
	static final int NUM_ONE_HANDED_PLAYER_GESTURE_NODES = 6;
	static final int NUM_TWO_HANDED_PLAYER_GESTURE_NODES = 9;
	
	static final int NUM_ONE_HANDED_RINGMASTER_GESTURE_NODES = 5;
	static final int NUM_TWO_HANDED_RINGMASTER_GESTURE_NODES = 7;
	
	private static Logger logger = Logger.getLogger(RecognizerManager.class);
	
	private Map<GestureType, Recognizer> recognizerMap =
			new HashMap<GestureType, Recognizer>(GestureType.values().length);
	
	RecognizerManager() {
		// Initialize the map of gesture recognizers
		for (GestureType gesture : GestureType.values()) {
			this.recognizerMap.put(gesture, new Recognizer(gesture));
		}
	}
	
	/**
	 * Train the given gesture's recognizer with the given data set.
	 * @param gesture The gesture whose recognizer will be trained.
	 * @param dataSet The data set to train the gesture's recognizer with.
	 * @return true on successful training, false on failure to train.
	 */
	boolean train(GestureType gesture) {
		Recognizer gestureRecog = this.recognizerMap.get(gesture);
		assert(gestureRecog != null);
		return gestureRecog.train(gesture.getNumHmmNodes());
	}
	
	/**
	 * Untrain the given gesture's recognizer to a blank state.
	 * @param gesture The gesture whose recognizer will be untrained / cleared.
	 */
	void untrain(GestureType gesture) {
		this.recognizerMap.put(gesture, new Recognizer(gesture));
	}
	
	/**
	 * Tests whether a gesture would even be REMOTELY considered acceptable for testing.
	 * @param gestureInstance The gesture to test.
	 * @return true if acceptable, false if not.
	 */
	static boolean isAcceptableGesture(GestureInstance gestureInstance) {
		if (!gestureInstance.isValid()) {
			return false;
		}
		
		// The gesture shouldn't be too too short...
		if (gestureInstance.getMaxTimeDiff() < GestureRecognizer.MINIMUM_GESTURE_RECOGNITION_TIME_IN_SECS) {
			return false;
		}
		else if (gestureInstance.getMaxTimeDiff() > GestureRecognizer.MAXIMUM_GESTURE_RECOGNITION_TIME_IN_SECS) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Attempts to recognize the given, novel gesture instance among all of the gesture
	 * recognizers in this manager.
	 * @param inst The novel gesture instance to identify/recognize.
	 * @param isRingmasterGesture Whether the provided gesture instance is supposed to be a ringmaster gesture or not.
	 * @return The gesture type that was recognized, null on no recognized gesture.
	 */
	GestureType recognize(GestureInstance inst) {
		// Weed out strange and anomalous data
		if (!RecognizerManager.isAcceptableGesture(inst)) {
			logger.info("Ignoring gesture - too short from beginning to end!");
			return null;
		}
		
		Map<GestureGenre, Double> bestProbabilityMap =
				new Hashtable<GestureGenre, Double>(GestureGenre.values().length);
		Map<GestureGenre, GestureType> bestGestureTypeMap =
				new Hashtable<GestureGenre, GestureType>(GestureGenre.values().length);	
		Map<GestureGenre, Double> lowestAcceptableProbMap =
				new Hashtable<GestureGenre, Double>(GestureGenre.values().length);
		
		// Setup variables for tracking the probabilities of various gestures
		double currProbability = -Double.MAX_VALUE;
		for (GestureGenre genre : GestureGenre.values()) {
			bestProbabilityMap.put(genre, new Double(-Double.MAX_VALUE));
		}
		
		// Go through each recognizer - only use recognizers that are built for the correct glove data
		// as is being provided by the gesture instance being recognized. We split the recognition 'bests'
		// into categories based on the genre of the gesture (i.e., "basic", "special", "easter-egg" gestures)
		// Later on, we favour basic gestures over special gestures and special gestures over easter-egg gestures.
		for (Recognizer recognizer : this.recognizerMap.values()) {
			GestureType gestureType = recognizer.getGestureType();
						
			// Find the highest probability gestures for each 'genre' of gesture...
			currProbability = recognizer.lnProbability(inst);
			Double bestProbability = bestProbabilityMap.get(gestureType.getGenre());
			if (currProbability > bestProbability) {
				bestProbabilityMap.put(gestureType.getGenre(), currProbability);
				bestGestureTypeMap.put(gestureType.getGenre(), gestureType);
				lowestAcceptableProbMap.put(gestureType.getGenre(), recognizer.getLowestAcceptableLnProbability());
			}

		}
		
		GestureType bestGesture = bestGestureTypeMap.get(GestureGenre.INTERFACE);

		// Make sure the best gesture's probability exceeds its lowest minimum probability
		double bestProb = bestProbabilityMap.get(bestGesture.getGenre());
		double lowestAcceptableProb = lowestAcceptableProbMap.get(bestGesture.getGenre());
		if (bestProb < lowestAcceptableProb) {
			logger.info("Gesture was not recognized because it did not meet its minimum acceptable probability threshold.");
			return null;
		}
				
		logger.info("Best found matching gesture: " + bestGesture.toString());
		return bestGesture;
	}
	
	/**
	 * Performs gesture recognition on the given gesture instance for all gestures and
	 * places the full result into the returned data.
	 * @param inst The gesture instance to identify/recognize.
	 * @param isRingmasterGesture Whether the provided gesture instance is supposed to be a ringmaster gesture or not.
	 * @return The full result of the recognition procedure, with data for all gestures.
	 */
	GestureRecognitionResult recognizeWithFullResult(GestureInstance inst) {
		Map<GestureType, GestureProbabilities> resultMapping = new HashMap<GestureType, GestureProbabilities>();
		
		// Weed out strange and anomalous data
		if (!RecognizerManager.isAcceptableGesture(inst)) {
			logger.info("Ignoring gesture - too short from beginning to end!");
			for (Entry<GestureType, Recognizer> entry : this.recognizerMap.entrySet()) {
				resultMapping.put(entry.getKey(), new GestureProbabilities(entry.getValue().getLowestAcceptableLnProbability(), 0.0, 0.0));
			}
			return new GestureRecognitionResult(inst, resultMapping);
		}
		
		
		
		return new GestureRecognitionResult(inst, resultMapping);
	}
	
	/**
	 * Clears all of the currently loaded recognizers to blank states.
	 * WARNING: Clears all loaded training data.
	 */
	void clearRecognizers() {
		this.recognizerMap.clear();
		// Initialize the map of gesture recognizers
		for (GestureType gesture : GestureType.values()) {
			this.recognizerMap.put(gesture, new Recognizer(gesture));
		}
	}
	
	/**
	 * Writes/Saves all of the recognizers in this manager.
	 * @param writer The writer )to write the recognizers to.
	 * @return true on success, false on failure.
	 */
	boolean writeRecognizers(Writer writer) {

		try {
			writer.write("" + this.recognizerMap.size() + "\n");
			for (Recognizer recognizer : this.recognizerMap.values()) {
				recognizer.save(writer);
			}
		}
		catch (IOException ex) {
			System.err.println(ex.toString());
			return false;
		}

		return true;
	}
	
	/**
	 * Reads/Loads all of the recognizers in this manager.
	 * @param reader The reader for reading the recognizers from.
	 * @return true on success, false on failure.
	 */
	boolean readRecognizers(Reader reader) {
		try {
			
			// Begin by reading the number of recognizers to read in from the file...

			char[] charArray = new char[1];
 			int numRecognizers = 0;
			String numRecognizersStr = "";
			
			reader.read(charArray);
			while (charArray[0] != '\n') {
				if (charArray[0] == '\r') {
					reader.read(charArray);
					continue;
				}
				numRecognizersStr += charArray[0];
				reader.read(charArray);
			}
			try {
				numRecognizers = Integer.valueOf(numRecognizersStr);
			}
			catch (NumberFormatException ex) {
				System.err.println(ex.toString());
				return false;
			}
	
			for (int i = 0; i < numRecognizers; i++) {
				Recognizer newRecognizer = new Recognizer();
				newRecognizer.load(reader);
				this.recognizerMap.put(newRecognizer.getGestureType(), newRecognizer);
			}
		}
		catch (IOException ex) {
			System.err.println(ex.toString());
			return false;
		}
		
		return true;
	}
	
}
