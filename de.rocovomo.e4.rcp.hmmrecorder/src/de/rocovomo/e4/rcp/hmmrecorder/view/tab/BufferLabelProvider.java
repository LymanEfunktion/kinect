/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package de.rocovomo.e4.rcp.hmmrecorder.view.tab;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import de.rocovomo.e4.rcp.hmmrecorder.model.State;
import de.rocovomo.e4.rcp.hmmrecorder.model.Vector;

public class BufferLabelProvider extends LabelProvider
{
	private static final Image STATE = getImage("property_obj.gif");
	private static final Image VECTOR = getImage("task_obj.gif");

	@Override
	public String getText(Object element)
	{
		if (element instanceof State)
		{
			State state = (State) element;
			return state.getNumber();
		}
		return ((Vector) element).getCoordinate();
	}

	@Override
	public Image getImage(Object element)
	{
		if (element instanceof State)
		{
			return STATE;
		}
		return VECTOR;
	}

	private static Image getImage(String file)
	{
		Bundle bundle = FrameworkUtil.getBundle(BufferLabelProvider.class);
		URL url = FileLocator.find(bundle, new Path("icons/" + file), null);
		ImageDescriptor image = ImageDescriptor.createFromURL(url);
		return image.createImage();

	}
}