/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package test.hmmview;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import test.hmmview.deprecated.view.DemoTab;
import test.hmmview.deprecated.view.FileButton;
import test.hmmview.model.Trainer;
import test.hmmview.view.Colors;
import test.hmmview.view.GestureComboE4;
import test.hmmview.view.PreviewButtonE4;
import test.hmmview.view.RecorderButtonE4;
import test.hmmview.view.SpeechCommandButtonE4;

@SuppressWarnings("restriction")
public class e4View implements Colors
{
	private Text tInterval;
	private Text tStates;
	private Trainer trainer;
	private WritableValue value;
	public static final String ID = "de.rocovomo.e4.rcp.hmmrecorder.swt.e4View";

	@Inject
	public e4View(Composite parent)
	{
		value = new WritableValue();
		setTrainer();
		value.setValue(trainer);
	}

	private void setTrainer()
	{
		trainer = new Trainer();
		
		trainer.setInterval(0.0);
		trainer.setNodes(0);
		trainer.setRunnable(false);
		trainer.setSpeechCommand(false);
	}

	@PostConstruct
	public void createUI(Composite parent)
	{
		Display display = parent.getDisplay();
		display.addFilter(SWT.KeyDown, new Listener()
		{
			@Override
			public void handleEvent(Event arg0)
			{
				if (((arg0.stateMask & SWT.CTRL) == SWT.CTRL) && (arg0.keyCode == 's'))
				{
					System.out.println("do save");
				}
			}
		});
		
		FillLayout fill = new FillLayout();
		fill.marginHeight = 5;
		fill.marginWidth = 5;
		parent.setLayout(fill);
		parent.setBackground(COLOR_WHITE);

		SashForm sashForm = new SashForm(parent, SWT.HORIZONTAL);
		sashForm.setBackground(COLOR_WHITE);

		new DemoTab(sashForm);

		Composite hmmParts = new Composite(sashForm, SWT.NONE);
		GridLayout gridLayout = new GridLayout(5, false);
		gridLayout.verticalSpacing = 3;
		hmmParts.setLayout(gridLayout);
		hmmParts.setBackground(COLOR_WHITE);

		final GestureComboE4 cGesture = new GestureComboE4(hmmParts, value, SWT.NONE);

		addDefaultLabel("States", hmmParts);
		tStates = addDefaultText(hmmParts, ((Trainer) value.getValue()).getNodes());
		tStates.setEditable(false);

		addDefaultLabel("Interval", hmmParts);
		tInterval = addDefaultText(hmmParts, ((Trainer) value.getValue()).getInterval());

		addDefaultLabel("Time frame", hmmParts);
		final Text tFrame = addDefaultText(hmmParts, 0.0);
		tFrame.setEditable(false);

		tInterval.addListener(SWT.KeyDown, new Listener()
		{
			@Override
			public void handleEvent(Event paramEvent)
			{
				if (paramEvent.keyCode == SWT.KEYPAD_CR || paramEvent.keyCode == SWT.CR)
				{
					double value = 0.0;
					try
					{
						value = Double.parseDouble(tInterval.getText());
					} catch (Exception e)
					{
					}
					tFrame.setText(String.valueOf(trainer.getNodes() * value));
					trainer.setRunnable(true);
				}
			}
		});

		new SpeechCommandButtonE4(hmmParts, value);

		new RecorderButtonE4(addButton("Start", hmmParts, SWT.TOGGLE, new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING, -1, false, false, 2, 1), 57), value);

		final Button bReset = addButton("Reset", hmmParts, SWT.PUSH, new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING, -1, false, false, 2, 1));

		final Button bAppend = addButton("Append", hmmParts, SWT.PUSH, new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING, -1, false, false, 1, 1));

		new FileButton(hmmParts);

		new PreviewButtonE4(addButton("Preview", hmmParts, SWT.PUSH, new GridData(GridData.END, -1,
				false, false, 4, 1)), value);

		Button bSave = addButton("Save", hmmParts, SWT.PUSH, new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING, -1, false, false, 1, 1));

		bindValues(tInterval);
	}

	@Focus
	public void setFocus()
	{
		tInterval.setFocus();
	}

	private void bindValues(Text interval)
	{
		// The DataBindingContext object will manage the databindings
		// Lets bind it
		DataBindingContext ctx = new DataBindingContext();

		IObservableValue widgetValue = WidgetProperties.text(SWT.Modify).observe(interval);
		IObservableValue modelValue = BeanProperties.value(Trainer.class, "interval").observe(
				trainer);
		// Add an validator so that age can only be a number
		IValidator validator = new DoubleValidator();

		UpdateValueStrategy strategy = new UpdateValueStrategy();
		strategy.setBeforeSetValidator(validator);

		Binding bindValue = ctx.bindValue(widgetValue, modelValue, strategy, null);

		widgetValue = WidgetProperties.text(SWT.Modify).observe(tStates);

		modelValue = BeanProperties.value(Trainer.class, "nodes").observe(trainer);
		ctx.bindValue(widgetValue, modelValue);
		// Add some decorations
		ControlDecorationSupport.create(bindValue, SWT.TOP | SWT.LEFT);
	}

	private Button addButton(String text, Composite parent, int style, GridData gridData,
			int widthHint)
	{
		gridData.widthHint = widthHint;
		Button button = new Button(parent, style);
		button.setText(text);
		button.setLayoutData(gridData);
		button.setBackground(COLOR_WHITE);
		return button;
	}

	private Button addButton(String text, Composite parent, int style, GridData gridData)
	{
		return addButton(text, parent, style, gridData, -1);
	}

	private void addDefaultLabel(String text, Composite parent)
	{
		addLabel(text, parent, new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING, -1, true, false,
				4, 1));
	}

	private Text addDefaultText(Composite parent, Object input)
	{
		Text text = new Text(parent, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, -1, false, false, 1, 1));
		text.setText(input.toString());
		return text;
	}

	private Label addLabel(String text, Composite parent, int style, GridData gridData)
	{
		Label label = new Label(parent, style);
		label.setText(text);
		label.setBackground(COLOR_WHITE);
		label.setLayoutData(gridData);
		return label;
	}

	private Label addLabel(String text, Composite parent, GridData gridData)
	{
		return addLabel(text, parent, SWT.NONE, gridData);
	}
}