package de.rocovomo.e4.rcp.hmmrecorder;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Sash;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

public class HMMView
{

	@Inject
	public HMMView()
	{

	}

	@PostConstruct
	public void createUI(final Composite parent)
	{
//		final Sash sash = new Sash(parent, SWT.VERTICAL);

	    GridLayout gridLayout = new GridLayout(4, false);
	    gridLayout.verticalSpacing = 8;
		
	    parent.setLayout(gridLayout);
//	    TableEditorSnippet test = new TableEditorSnippet(parent);
	    ComboBoxCellEditorsSnippet asdf = new ComboBoxCellEditorsSnippet(parent);
	    
		final FormLayout form = new FormLayout();
		parent.setLayout(form);
	}

	@PreDestroy
	public void destroy()
	{

	}
}
