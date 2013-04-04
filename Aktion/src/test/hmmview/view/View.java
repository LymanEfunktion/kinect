package test.hmmview.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import test.hmmview.model.BooleanModel;
import test.hmmview.model.DoubleModel;
import test.hmmview.model.FileModel;
import test.hmmview.model.FileModelListener;
import test.hmmview.model.GestureTypeModel;
import test.hmmview.model.IntegerModel;

public class View extends AbstractView {
	private BooleanModel isRunnableModel;
	private BooleanModel isSpeechEnabledModel;
	private IntegerModel statesModel;
	private DoubleModel intervalModel;
	private DoubleModel frameModel;

	final FileModel fileModel;
	final GestureTypeModel gestureTypeModel;

	public View(Composite parent, BooleanModel isRunnableModel,
			BooleanModel isSpeechEnabledModel, IntegerModel statesModel,
			DoubleModel intervalModel, DoubleModel frameModel,
			FileModel fileModel, GestureTypeModel gestureTypeModel) {
		super(parent);
		this.isRunnableModel = isRunnableModel;
		this.isSpeechEnabledModel = isSpeechEnabledModel;
		this.statesModel = statesModel;
		this.intervalModel = intervalModel;
		this.frameModel = frameModel;
		this.fileModel = fileModel;
		this.gestureTypeModel = gestureTypeModel;
	}

	public void createUI(Composite parent) {
		parent.setLayout(new FillLayout());

		GridLayout gridLayout = new GridLayout(5, false);
		gridLayout.verticalSpacing = 3;

		SashForm sashForm = new SashForm(parent, SWT.HORIZONTAL);
		sashForm.setBackground(COLOR_WHITE);

		final CTabFolder tabFolder = new CTabFolder(sashForm, SWT.BOTTOM
				| SWT.BORDER);

		CTabItem item = new CTabItem(tabFolder, SWT.NONE);
		item.setText("Demo");
		Composite inner = new Composite(tabFolder, SWT.NONE);
		inner.setBackground(COLOR_WHITE);
		item.setControl(inner);
		CTabItem item2 = new CTabItem(tabFolder, SWT.NONE);
		Composite inner2 = new Composite(tabFolder, SWT.NONE);
		inner2.setBackground(COLOR_WHITE);
		item2.setControl(inner2);
		item2.setText("Buffer");

		Composite hmmParts = new Composite(sashForm, SWT.NONE);
		hmmParts.setLayout(gridLayout);
		hmmParts.setBackground(COLOR_WHITE);

		addDefaultLabel("Choose gesture", hmmParts);
		final GestureCombo cGesture = new GestureCombo(hmmParts, SWT.NONE);

		addDefaultLabel("States", hmmParts);
		Text tStates = addDefaultText(hmmParts, statesModel.property.getValue());

		addDefaultLabel("Interval", hmmParts);
		Text tInterval = addDefaultText(hmmParts,
				intervalModel.property.getValue());

		addDefaultLabel("Time frame", hmmParts);
		Text tFrame = addDefaultText(hmmParts, frameModel.property.getValue());

		new SpeechCommandButton(addButton("Speech command enabled", hmmParts,
				SWT.CHECK, new GridData(GridData.HORIZONTAL_ALIGN_FILL, 2,
						false, false, 5, 1)), isSpeechEnabledModel);

		new RecorderButton(addButton("Start", hmmParts, SWT.TOGGLE,
				new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING, -1, false,
						false, 2, 1), 57), isRunnableModel);

		final Button bReset = addButton("Reset", hmmParts, SWT.PUSH,
				new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING, -1, false,
						false, 2, 1));

		final Button bAppend = addButton("Append", hmmParts, SWT.PUSH,
				new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING, -1, false,
						false, 1, 1));

		bAppend.setEnabled(fileModel.property.getValue() != null);

		addLabel("File", hmmParts, new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING, 2, false, false, 1, 1));

		final Label lFileURL = addLabel(" ", hmmParts, SWT.BORDER,
				new GridData(GridData.FILL, -1, false, false, 3, 1));

		Button bFileBrowse = addButton("...", hmmParts, SWT.PUSH,
				new GridData());

		final FileDialog dialog = new FileDialog(hmmParts.getShell(), SWT.SAVE);
		dialog.setFilterNames(new String[] { "Batch Files", "All Files (*.*)" });
		dialog.setFilterExtensions(new String[] { "*.bat", "*.*" });
		dialog.setFilterPath("c:\\");
		dialog.setFileName("yourFile.bat");

		fileModel.addModelListener(new FileModelListener() {

			@Override
			public void modelChanged(FileModel model) {
				// TODO Auto-generated method stub
				System.out.println("saved");
			}
		});

		new PreviewButton(addButton("Preview", hmmParts, SWT.PUSH,
				new GridData(GridData.END, -1, false, false, 4, 1)), fileModel);

		Button bSave = addButton("Save", hmmParts, SWT.PUSH, new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING, -1, false, false, 1, 1));

		bFileBrowse.addMouseListener(new MouseListener() {
			@Override
			public void mouseUp(MouseEvent paramMouseEvent) {
				String url = dialog.open();
				if (url != null) {
					lFileURL.setText(url);
				}
			}

			@Override
			public void mouseDown(MouseEvent paramMouseEvent) {
			}

			@Override
			public void mouseDoubleClick(MouseEvent paramMouseEvent) {
			}
		});

		bReset.addMouseListener(new MouseListener() {
			@Override
			public void mouseUp(MouseEvent paramMouseEvent) {
			}

			@Override
			public void mouseDown(MouseEvent paramMouseEvent) {
			}

			@Override
			public void mouseDoubleClick(MouseEvent paramMouseEvent) {
			}
		});
	}
}
