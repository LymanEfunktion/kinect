package test.hmmview;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import test.hmmview.model.BooleanModel;
import test.hmmview.model.DoubleModel;
import test.hmmview.model.FileModel;
import test.hmmview.model.GestureTypeModel;
import test.hmmview.model.IntegerModel;
import test.hmmview.model.IntegerModelListener;
import test.hmmview.view.AbstractView;
import test.hmmview.view.DemoTab;
import test.hmmview.view.FileButton;
import test.hmmview.view.GestureCombo;
import test.hmmview.view.PreviewButton;
import test.hmmview.view.RecorderButton;
import test.hmmview.view.SpeechCommandButton;

public class View extends AbstractView
{
	private BooleanModel isRunnableModel;
	private BooleanModel isSpeechEnabledModel;
	private IntegerModel statesModel;
	private DoubleModel intervalModel;
	private DoubleModel frameModel;

	final FileModel fileModel;
	final GestureTypeModel gestureTypeModel;

	public View(Composite parent, BooleanModel isRunnableModel,
			BooleanModel isSpeechEnabledModel, IntegerModel statesModel,
			DoubleModel intervalModel, DoubleModel frameModel, FileModel fileModel,
			GestureTypeModel gestureTypeModel)
	{
		super(parent);
		this.isRunnableModel = isRunnableModel;
		this.isSpeechEnabledModel = isSpeechEnabledModel;
		this.statesModel = statesModel;
		this.intervalModel = intervalModel;
		this.frameModel = frameModel;
		this.fileModel = fileModel;
		this.gestureTypeModel = gestureTypeModel;

		parent.getDisplay().addFilter(SWT.KeyDown, new Listener()
		{
			public void handleEvent(Event e)
			{
				if (((e.stateMask & SWT.CTRL) == SWT.CTRL) && (e.keyCode == 's'))
				{
					System.out.println("do save");
				}
			}
		});
	}

	public void createUI(Composite parent)
	{
		FillLayout fill = new FillLayout();
		fill.marginHeight = 5;
		fill.marginWidth = 5;
		parent.setLayout(fill);

		SashForm sashForm = new SashForm(parent, SWT.HORIZONTAL);
		sashForm.setBackground(COLOR_WHITE);

		new DemoTab(sashForm);

		Composite hmmParts = new Composite(sashForm, SWT.NONE);
		GridLayout gridLayout = new GridLayout(5, false);
		gridLayout.verticalSpacing = 3;
		hmmParts.setLayout(gridLayout);
		hmmParts.setBackground(COLOR_WHITE);

		final GestureCombo cGesture = new GestureCombo(hmmParts, statesModel, SWT.NONE);

		addDefaultLabel("States", hmmParts);
		final Text tStates = addDefaultText(hmmParts, statesModel.property.getValue());
		tStates.setEditable(false);

		statesModel.addModelListener(new IntegerModelListener()
		{
			@Override
			public void modelChanged(IntegerModel model)
			{
				tStates.setText(Integer.toString(model.property.getValue()));
			}
		});

		addDefaultLabel("Interval", hmmParts);
		final Text tInterval = addDefaultText(hmmParts, intervalModel.property.getValue());

		addDefaultLabel("Time frame", hmmParts);
		final Text tFrame = addDefaultText(hmmParts, frameModel.property.getValue());
		tFrame.setEditable(false);

		tInterval.addListener(SWT.KeyDown, new Listener()
		{
			@Override
			public void handleEvent(Event paramEvent)
			{
				if (paramEvent.keyCode == SWT.KEYPAD_CR || paramEvent.keyCode == SWT.CR)
				{
					double value = 0.0;
					try {
						value = Double.parseDouble(tInterval.getText());
					} catch (Exception e) {
						isRunnableModel.property.setValue(false);
					}
					tFrame.setText(String.valueOf(statesModel.property.getValue()
							* value));
					if (isRunnable(value)) {
						isRunnableModel.property.setValue(true);
					}
				}
			}
		});

		new SpeechCommandButton(hmmParts, isSpeechEnabledModel);

		new RecorderButton(addButton("Start", hmmParts, SWT.TOGGLE, new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING, -1, false, false, 2, 1), 57),
				isRunnableModel);

		final Button bReset = addButton("Reset", hmmParts, SWT.PUSH, new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING, -1, false, false, 2, 1));

		final Button bAppend = addButton("Append", hmmParts, SWT.PUSH, new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING, -1, false, false, 1, 1));

		bAppend.setEnabled(fileModel.property.getValue() != null);

		new FileButton(hmmParts);

		new PreviewButton(addButton("Preview", hmmParts, SWT.PUSH, new GridData(
				GridData.END, -1, false, false, 4, 1)), fileModel);

		Button bSave = addButton("Save", hmmParts, SWT.PUSH, new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING, -1, false, false, 1, 1));
	}

	protected boolean isRunnable(double value)
	{
		return value > 0.0;
	}
}
