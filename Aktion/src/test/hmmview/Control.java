package test.hmmview;

import org.eclipse.swt.widgets.Composite;

import test.hmmview.model.BooleanModel;
import test.hmmview.model.DoubleModel;
import test.hmmview.model.FileModel;
import test.hmmview.model.GestureTypeModel;
import test.hmmview.model.IntegerModel;

public class Control
{
	public Control(Composite parent)
	{
		final BooleanModel isRunnableModel = new BooleanModel();
		final BooleanModel isSpeechEnabledModel = new BooleanModel();
		final IntegerModel statesModel = new IntegerModel();
		final DoubleModel intervalModel = new DoubleModel();
		final DoubleModel frameModel = new DoubleModel();

		final FileModel fileModel = new FileModel();
		final GestureTypeModel gestureTypeModel = new GestureTypeModel();

		View view = new View(isSpeechEnabledModel, isSpeechEnabledModel, statesModel,
				frameModel, frameModel, fileModel, gestureTypeModel);
		view.createUI(parent);
	}
}
