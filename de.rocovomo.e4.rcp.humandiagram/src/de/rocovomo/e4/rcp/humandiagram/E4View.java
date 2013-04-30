package de.rocovomo.e4.rcp.humandiagram;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.editparts.FreeformGraphicalRootEditPart;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.swt.widgets.Composite;
import org.jnect.bodymodel.Body;
import org.jnect.core.KinectManager;

import de.rocovomo.e4.rcp.humandiagram.editpart.HumanDiagramEditPartFactory;

public class E4View
{
	public static final String ID = "de.rocovomo.e4.rcp.humandiagram.e4view";

	private DefaultEditDomain editDomain;

	private Body skeleton;

	private GraphicalViewer graphicalViewer;

	@Inject
	public E4View()
	{
		System.out.println("injected");
	}

	@PostConstruct
	public void createUI(final Composite parent)
	{
		editDomain = new DefaultEditDomain(null);
		graphicalViewer = new ScrollingGraphicalViewer();

		editDomain.addViewer(graphicalViewer);

		graphicalViewer.createControl(parent);
		graphicalViewer.setRootEditPart(new FreeformGraphicalRootEditPart());
		graphicalViewer.setEditPartFactory(new HumanDiagramEditPartFactory());
		skeleton = KinectManager.INSTANCE.getSkeletonModel();
		graphicalViewer.setContents(skeleton);
		graphicalViewer.getControl().setBackground(ColorConstants.listBackground);
	}

	@PreDestroy
	public void destroy()
	{
	}
}
