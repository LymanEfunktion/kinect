/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package de.rocovomo.util.hmm.gesture;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.ListIterator;

import org.jnect.bodymodel.Body;
import org.jnect.bodymodel.PositionedElement;
import org.jnect.bodymodel.RightHand;
import org.jnect.bodymodel.impl.BodymodelFactoryImpl;

import de.rocovomo.util.hmm.gesture.reference.ActionType;
import de.rocovomo.util.hmm.gesture.reference.GestureGenre;

public enum GestureType
{
	HAND_CIRCLE(8, ActionType.CIRCLE_AROUND, GestureGenre.ROBOT_CONTROL, RightHand.class,"HAND_CIRCLE"),
	HAND_STRAIGHT(5, ActionType.CIRCLE_AROUND, GestureGenre.ROBOT_CONTROL, RightHand.class,"HAND_STRAIGHT"),
	HAND_DIRECTION(8, ActionType.CIRCLE_AROUND, GestureGenre.ROBOT_CONTROL, RightHand.class,"HAND_DIRECTION"),
	HAND_UNLOCK(4, ActionType.CIRCLE_AROUND, GestureGenre.ROBOT_CONTROL, RightHand.class, "HAND_UNLOCK"),
	HAND_HALT(5, ActionType.CIRCLE_AROUND, GestureGenre.ROBOT_CONTROL, RightHand.class,"HAND_HALT");

	final private ActionType actionFactoryType;
	final private GestureGenre genre;
	final private int numHmmNodes;
	final private String name;
	private Class<PositionedElement> test;

	private double lowestLnProbability;
	private double highestLnProbability;

	<T> GestureType(int numHmmNodes, ActionType actionFactoryType, GestureGenre genre, Class<? extends PositionedElement> test, String name)
	{
		this(numHmmNodes,actionFactoryType,genre,name);
		if (test instanceof Class<?>) {
		this.test =  (Class<PositionedElement>) test;
		}
	}
	
	GestureType(int numHmmNodes, ActionType actionFactoryType, GestureGenre genre, String name)
	{
		this.actionFactoryType = actionFactoryType;
		this.genre = genre;
		this.name = name;
		this.numHmmNodes = numHmmNodes;

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

	public String getName()
	{
		return this.name;
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

	public PositionedElement getElement(Body skeleton)
	{		
		ListIterator<Method> iter = Arrays.asList(skeleton.getClass().getMethods()).listIterator();
		while(iter.hasNext())
		{
			Method method = iter.next();
			if(method.getReturnType().equals(test))
			{
				try
				{
					return (PositionedElement) method.invoke(skeleton,  new Object[0]);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
				{
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public PositionedElement getElement()
	{
		BodymodelFactoryImpl factory = new BodymodelFactoryImpl();
		
		ListIterator<Method> iter = Arrays.asList(factory.getClass().getMethods()).listIterator();
		while(iter.hasNext())
		{
			Method method = iter.next();
			if(method.getReturnType().equals(test))
			{
				try
				{
					return (PositionedElement) method.invoke(factory,  new Object[0]);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
				{
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
