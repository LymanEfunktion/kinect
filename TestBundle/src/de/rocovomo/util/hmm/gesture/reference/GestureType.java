package de.rocovomo.util.hmm.gesture.reference;

import java.util.Arrays;
import java.util.List;

public enum GestureType
{
	HAND_CIRCLE(8, ActionType.CIRCLE_AROUND, GestureGenre.ROBOTC_ONTROL, Arrays.asList("HAND_CIRCLE")),
	HAND_STRAIGHT(5, ActionType.CIRCLE_AROUND, GestureGenre.ROBOTC_ONTROL, Arrays.asList("HAND_STRAIGHT")),
	HAND_DIRECTION(8, ActionType.CIRCLE_AROUND, GestureGenre.ROBOTC_ONTROL, Arrays.asList("HAND_DIRECTION")),
	HAND_UNLOCK(4, ActionType.CIRCLE_AROUND, GestureGenre.ROBOTC_ONTROL, Arrays.asList("HAND_UNLOCK")),
	HAND_HALT(5, ActionType.CIRCLE_AROUND, GestureGenre.ROBOTC_ONTROL, Arrays.asList("HAND_HALT"));
	

	final private ActionType actionFactoryType;
	final private GestureGenre genre;

	final private int numHmmNodes;

	final private List<String> parentDirNames;

	GestureType(int numHmmNodes, ActionType actionFactoryType,
			GestureGenre genre, List<String> parentDirNames)
	{

		this.actionFactoryType = actionFactoryType;

		this.genre = genre;

		this.numHmmNodes = numHmmNodes;

		this.parentDirNames = parentDirNames;
	}

	public ActionType getActionFactoryType()
	{
		return this.actionFactoryType;
	}

	public GestureGenre getGenre()
	{
		return this.genre;
	}

	public List<String> getParentDirNameList()
	{
		return this.parentDirNames;
	}

	public int getNumHmmNodes()
	{
		return this.numHmmNodes;
	}
}
