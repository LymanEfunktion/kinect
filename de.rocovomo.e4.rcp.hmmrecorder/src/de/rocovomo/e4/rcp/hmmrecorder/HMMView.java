package de.rocovomo.e4.rcp.hmmrecorder;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.swt.widgets.Composite;

import test.hmmview.Control;

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

		Control control = new Control(parent);
		
//	    GridLayout gridLayout = new GridLayout(4, false);
//	    gridLayout.verticalSpacing = 8;
		
//	    parent.setLayout(gridLayout);
//	    TableEditorSnippet test = new TableEditorSnippet(parent);

//		final GestureCombo cGesture = new GestureCombo(parent, SWT.NONE);
//	    ComboBoxCellEditorsSnippet asdf = new ComboBoxCellEditorsSnippet(parent);
	    
//		final FormLayout form = new FormLayout();
//		parent.setLayout(form);
	}

	@PreDestroy
	public void destroy()
	{

	}
}
