package de.rocovomo.util.hmm.gesture.reference;

import java.util.Arrays;
import java.util.List;

@Deprecated
public enum GestureTypeOLD
{
	HAND_CIRCLE(8, ActionType.CIRCLE_AROUND, GestureGenre.ROBOT_CONTROL, Arrays.asList("HAND_CIRCLE")),
	HAND_STRAIGHT(5, ActionType.CIRCLE_AROUND, GestureGenre.ROBOT_CONTROL, Arrays.asList("HAND_STRAIGHT")),
	HAND_DIRECTION(8, ActionType.CIRCLE_AROUND, GestureGenre.ROBOT_CONTROL, Arrays.asList("HAND_DIRECTION")),
	HAND_UNLOCK(4, ActionType.CIRCLE_AROUND, GestureGenre.ROBOT_CONTROL, Arrays.asList("HAND_UNLOCK")),
	HAND_HALT(5, ActionType.CIRCLE_AROUND, GestureGenre.ROBOT_CONTROL, Arrays.asList("HAND_HALT"));

	final private ActionType actionFactoryType;
	final private GestureGenre genre;
	final private int numHmmNodes;
	final private List<String> parentDirNames;

	private double lowestLnProbability;
	private double highestLnProbability;

	GestureTypeOLD(int numHmmNodes, ActionType actionFactoryType, GestureGenre genre,
			List<String> parentDirNames)
	{
		this.actionFactoryType = actionFactoryType;
		this.genre = genre;
		this.numHmmNodes = numHmmNodes;
		this.parentDirNames = parentDirNames;
		setLowestLnProbability(Double.MAX_VALUE);
		setHighestLnProbability(-Double.MAX_VALUE);
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

	public double getLowestLnProbability()
	{
		return lowestLnProbability;
	}

	public void setLowestLnProbability(double lowestLnProbability)
	{
		this.lowestLnProbability = lowestLnProbability;
	}

	public double getHighestLnProbability()
	{
		return highestLnProbability;
	}

	public void setHighestLnProbability(double highestLnProbability)
	{
		this.highestLnProbability = highestLnProbability;
	}
}
