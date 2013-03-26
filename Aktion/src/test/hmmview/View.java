package test.hmmview;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import test.hmmview.model.BooleanModel;
import test.hmmview.model.DoubleModel;
import test.hmmview.model.FileModel;
import test.hmmview.model.GestureTypeModel;
import test.hmmview.model.IntegerModel;

public class View
{
	private Color COLOR_WHITE;

	private BooleanModel isRunnableModel;
	private BooleanModel isSpeechEnabledModel;
	private IntegerModel statesModel;
	private DoubleModel intervalModel;
	private DoubleModel frameModel;

	final FileModel fileModel;
	final GestureTypeModel gestureTypeModel;

	public View(BooleanModel isRunnableModel, BooleanModel isSpeechEnabledModel,
			IntegerModel statesModel, DoubleModel intervalModel, DoubleModel frameModel,
			FileModel fileModel, GestureTypeModel gestureTypeModel)
	{
		this.isRunnableModel = isRunnableModel;
		this.isSpeechEnabledModel = isSpeechEnabledModel;
		this.statesModel = statesModel;
		this.intervalModel = intervalModel;
		this.frameModel = frameModel;
		this.fileModel = fileModel;
		this.gestureTypeModel = gestureTypeModel;
	}

	public void createUI(final Composite parent)
	{
		COLOR_WHITE = parent.getDisplay().getSystemColor(SWT.COLOR_WHITE);
		Color white = new Color(parent.getDisplay(), new RGB(255, 255, 255));

		GridLayout gridLayout = new GridLayout(5, false);
		gridLayout.verticalSpacing = 3;

		GridData gridEnd = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridEnd.horizontalSpan = 1;
		gridEnd.verticalSpan = 1;
		FillLayout fillLayout = new FillLayout();
		fillLayout.marginWidth = 5;
		fillLayout.marginHeight = 5;
		parent.setLayout(fillLayout);
		parent.setBackground(white);

		SashForm sashForm = new SashForm(parent, SWT.HORIZONTAL);
		sashForm.setBackground(white);

		final CTabFolder tabFolder = new CTabFolder(sashForm, SWT.BOTTOM | SWT.BORDER);

		CTabItem item = new CTabItem(tabFolder, SWT.NONE);
		item.setText("Demo");
		Composite inner = new Composite(tabFolder, SWT.NONE);
		inner.setBackground(white);
		item.setControl(inner);
		CTabItem item2 = new CTabItem(tabFolder, SWT.NONE);
		Composite inner2 = new Composite(tabFolder, SWT.NONE);
		inner2.setBackground(white);
		item2.setControl(inner2);
		item2.setText("Buffer");

		Composite right = new Composite(sashForm, SWT.NONE);
		right.setLayout(gridLayout);
		right.setBackground(white);

		addDefaultLabel("Choose gesture", right);

		final Combo cGesture = new Combo(right, SWT.NONE);
		cGesture.setItems(new String[] { "test", "1212", "232323", "343434", "44" });
		cGesture.setText(cGesture.getItem(0));
		cGesture.setLayoutData(gridEnd);
		cGesture.addTraverseListener(new TraverseListener()
		{
			@Override
			public void keyTraversed(TraverseEvent event)
			{
				if (event.detail == SWT.TRAVERSE_RETURN)
				{
					event.doit = false;
					event.detail = SWT.TRAVERSE_NONE;
					String newText = cGesture.getText();
					cGesture.add(newText);
					cGesture.setSelection(new Point(0, newText.length()));
					cGesture.pack();
				}
			}
		});

		addDefaultLabel("States", right);

		Text tStates = addDefaultText(right, statesModel.property.getValue());

		addDefaultLabel("Interval", right);

		Text tInterval = addDefaultText(right, intervalModel.property.getValue());

		addDefaultLabel("Time frame", right);

		Text tFrame = addDefaultText(right, frameModel.property.getValue());

		final Button bSpeechCmd = addButton("Speech command enabled", right, SWT.CHECK,
				new GridData(GridData.HORIZONTAL_ALIGN_FILL, 2, false, false, 5, 1));

		bSpeechCmd.addSelectionListener(new SelectionListener()
		{
			@Override
			public void widgetSelected(SelectionEvent paramSelectionEvent)
			{
				isSpeechEnabledModel.property.setValue(bSpeechCmd.getSelection());
				String text = ("Speech command " + ((bSpeechCmd.getSelection()) ? "enabled"
						: "disabled"));
				bSpeechCmd.setText(text);
				bSpeechCmd.pack();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent paramSelectionEvent)
			{
			}
		});

		final Button bRecord = addButton("Start", right, SWT.TOGGLE, new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING, -1, false, false, 2, 1), 57);
		bRecord.setEnabled(isRunnableModel.property.getValue());

		final Button bReset = addButton("Reset", right, SWT.PUSH, new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING, -1, false, false, 2, 1));

		final Button bAppend = addButton("Append", right, SWT.PUSH, new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING, -1, false, false, 1, 1));

		bAppend.setEnabled(fileModel.file.getValue() != null);
		
		addLabel("File", right, new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING, 2,
				false, false, 1, 1));

		final Label lFileURL = addLabel(" ", right, SWT.BORDER, new GridData(
				GridData.FILL, -1, false, false, 3, 1));

		Button bFileBrowse = addButton("...", right, SWT.PUSH, new GridData());

		final FileDialog dialog = new FileDialog(right.getShell(), SWT.OPEN);
		dialog.setFilterNames(new String[] { "Batch Files", "All Files (*.*)" });
		dialog.setFilterExtensions(new String[] { "*.bat", "*.*" });
		dialog.setFilterPath("c:\\");
		dialog.setFileName("yourFile.bat");

		Button bPreview = addButton("Preview", right, SWT.PUSH, new GridData(
				GridData.END, -1, false, false, 4, 1));
		Button bSave = addButton("Save", right, SWT.PUSH, new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING, -1, false, false, 1, 1));

		bFileBrowse.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseUp(MouseEvent paramMouseEvent)
			{
				String url = dialog.open();
				if (url != null)
				{
					lFileURL.setText(url);
				}
			}

			@Override
			public void mouseDown(MouseEvent paramMouseEvent)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDoubleClick(MouseEvent paramMouseEvent)
			{
				// TODO Auto-generated method stub

			}
		});

		bReset.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseUp(MouseEvent paramMouseEvent)
			{
			}

			@Override
			public void mouseDown(MouseEvent paramMouseEvent)
			{
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseDoubleClick(MouseEvent paramMouseEvent)
			{
				// TODO Auto-generated method stub

			}
		});

		bRecord.addSelectionListener(new SelectionListener()
		{
			@Override
			public void widgetSelected(SelectionEvent paramSelectionEvent)
			{
				bRecord.setText((bRecord.getSelection()) ? "Running" : "Start");
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent paramSelectionEvent)
			{
			}
		});
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
		addLabel(text, parent, new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING, -1,
				true, false, 4, 1));
	}

	private Text addDefaultText(Composite parent, Object input)
	{
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridData.horizontalSpan = 1;
		gridData.verticalSpan = 1;

		Text text = new Text(parent, SWT.BORDER);
		text.setLayoutData(gridData);
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
