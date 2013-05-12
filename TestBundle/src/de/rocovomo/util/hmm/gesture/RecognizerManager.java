/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package de.rocovomo.util.hmm.gesture;

import java.util.HashMap;
import java.util.Map;

import de.rocovomo.util.hmm.gesture.reference.GestureType;

public class RecognizerManager
{
	private Map<GestureType, Recognizer> recognizerMap = new HashMap<GestureType, Recognizer>(
			GestureType.values().length);

	public RecognizerManager()
	{
		for (GestureType gesture : GestureType.values())
		{
			this.recognizerMap.put(gesture, new Recognizer(gesture));
		}
	}

	public void train(GestureType gesture)
	{
		Recognizer gestureRecog = this.recognizerMap.get(gesture);
		gestureRecog.train();
	}
}
