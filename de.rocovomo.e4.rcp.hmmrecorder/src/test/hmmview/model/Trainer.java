/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package test.hmmview.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Trainer implements PropertyChangeListener
{

	private Integer nodes;
	private Double interval;
	private Boolean runnable;
	private Boolean speakable;

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
	
	public void setPreview(Preview preview) {
	    propertyChangeSupport.firePropertyChange("preview", this.preview,
	        this.preview = preview);
	}
}
