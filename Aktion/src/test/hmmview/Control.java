package test.hmmview;

import java.io.File;

import org.eclipse.swt.widgets.Composite;

import test.hmmview.model.BooleanModel;
import test.hmmview.model.DoubleModel;
import test.hmmview.model.FileModel;
import test.hmmview.model.GestureTypeModel;
import test.hmmview.model.IntegerModel;
import test.hmmview.view.View;

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
		
		File file = new File("data/defaultdata.stream");
		fileModel.property.setValue(file);
		
		final GestureTypeModel gestureTypeModel = new GestureTypeModel();

		View view = new View(parent, isRunnableModel, isSpeechEnabledModel, statesModel, intervalModel,
				frameModel, fileModel, gestureTypeModel);
		view.createUI(parent);
	}
}
