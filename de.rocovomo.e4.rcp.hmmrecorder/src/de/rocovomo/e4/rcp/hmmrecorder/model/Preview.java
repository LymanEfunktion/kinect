/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package de.rocovomo.e4.rcp.hmmrecorder.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;

public class Preview
{
	private boolean isEditable;
	private boolean isSaved;
	private String stagedContent;
	private String savedContent;
	private File file;

	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	public Preview()
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

	public boolean isEditable()
	{
		return isEditable;
	}

	public void setEditable(boolean isEditable)
	{
		propertyChangeSupport.firePropertyChange("editable", this.isEditable,
				this.isEditable = isEditable);
	}

	public boolean isSaved()
	{
		return isSaved;
	}

	public void setSave(boolean isSaved)
	{
		propertyChangeSupport.firePropertyChange("save", this.isSaved, this.isSaved = isSaved);
	}

	public File getFile()
	{
		return file;
	}

	public void setFile(File file)
	{
		propertyChangeSupport.firePropertyChange("file", this.file, this.file = file);
	}

	public String getStagedContent()
	{
		return stagedContent;
	}

	public void setStagedContent(String content)
	{
		setSave(content.equals(savedContent));
		propertyChangeSupport.firePropertyChange("stagedContent", this.stagedContent, this.stagedContent = content);
	}

	public String getSavedContent()
	{
		return savedContent;
	}

	public void setSavedContent(String content)
	{
		propertyChangeSupport.firePropertyChange("content", this.savedContent, this.savedContent = content);
	}
}
