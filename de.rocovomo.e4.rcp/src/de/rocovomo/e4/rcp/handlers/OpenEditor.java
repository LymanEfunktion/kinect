package de.rocovomo.e4.rcp.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MInputPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

public class OpenEditor {

	@Execute
	public void open(MyInput input, MApplication application,
			EModelService modelService, EPartService partService) {
		// Assuming that all editors should open in the the stack with the
		// ID "org.eclipse.e4.primaryDataStack"

		MPartStack stack = (MPartStack) modelService.find(
				"org.eclipse.e4.primaryDataStack", application);

		MInputPart part = MBasicFactory.INSTANCE.createInputPart();
		// Pointing to the contributing class
		part.setContributionURI("bundleclass://de.vogella.rcp.e4.todo/de.vogella.rcp.e4.parts.Part1");
		part.setInputURI(input.getInputURI());
		part.setIconURI("platform:/plugin/de.vogella.rcp.e4.todo/icons/sample.gif");
		part.setLabel(input.getName());
		part.setTooltip(input.getTooltip());
		part.setCloseable(true);
		stack.getChildren().add(part);
		partService.showPart(part, PartState.ACTIVATE);
	}
}