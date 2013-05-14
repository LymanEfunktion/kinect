/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package de.rocovomo.e4.rcp.hmmrecorder.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.URL;
import java.util.List;

import de.rocovomo.util.hmm.gesture.GestureType;

public class Trainer implements PropertyChangeListener
{
	private Integer nodes;
	private Double interval;
	private Boolean runnable;
	private Boolean speakable;
	private Boolean savable;
	private URL fileUrl;
	private List<List<List<Float>>> sequences;
	private List<List<Float>> stagedSequence;
	private GestureType gesture;

	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	private Preview preview;

	public Trainer()
	{

	}

	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
	{
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener)
	{
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0)
	{
		propertyChangeSupport.firePropertyChange("preview", null, preview);
	}

	public Double getInterval()
	{
		return interval;
	}

	public void setInterval(Double interval)
	{
		propertyChangeSupport.firePropertyChange("interval", this.interval,
				this.interval = interval);
	}

	public Integer getNodes()
	{
		return nodes;
	}

	public void setNodes(Integer nodes)
	{
		propertyChangeSupport.firePropertyChange("nodes", this.nodes, this.nodes = nodes);
	}

	public Boolean isSpeechActivated()
	{
		return speakable;
	}

	public void setSpeechCommand(Boolean value)
	{
		propertyChangeSupport.firePropertyChange("speakable", this.speakable,
				this.speakable = value);
	}

	public Boolean isRunnable()
	{
		return runnable;
	}

	public void setRunnable(Boolean value)
	{
		propertyChangeSupport.firePropertyChange("runnable", this.runnable, this.runnable = value);
	}

	public Preview getPreview()
	{
		return preview;
	}

	public void setPreview(Preview preview)
	{
		propertyChangeSupport.firePropertyChange("preview", this.preview, this.preview = preview);
	}

	public URL getFileUrl()
	{
		return fileUrl;
	}

	public void setFileUrl(URL fileUrl)
	{
		propertyChangeSupport.firePropertyChange("url", this.fileUrl, this.fileUrl = fileUrl);
	}

	public Boolean isSavable()
	{
		return savable;
	}

	public void setSavable(Boolean savable)
	{
		propertyChangeSupport.firePropertyChange("savable", this.savable, this.savable = savable);
	}

	public List<List<List<Float>>> getSequences()
	{
		return sequences;
	}

	public void setSequences(List<List<List<Float>>> sequences)
	{
		propertyChangeSupport.firePropertyChange("sequences", this.sequences,
				this.sequences = sequences);
	}

	public void addSequence(List<List<Float>> sequence)
	{
		propertyChangeSupport.firePropertyChange("sequences", this.sequences,
				this.sequences.add(sequence));
	}

	public GestureType getGesture()
	{
		return gesture;
	}

	public void setGesture(GestureType gesture)
	{
		propertyChangeSupport.firePropertyChange("gesture", this.gesture, this.gesture = gesture);
	}

	public List<List<Float>> getStagedSequence()
	{
		return stagedSequence;
	}

	public void setStagedSequence(List<List<Float>> stagedSequence)
	{
		propertyChangeSupport.firePropertyChange("sequence", this.stagedSequence, this.stagedSequence = stagedSequence);
	}

	public void clearList()
	{
		this.stagedSequence.clear();
		this.sequences.clear();
	}
}
