//------------------------------------------------------------------------------
// Copyright (c) 2005, 2006 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.authoring.ui.dialogs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.authoring.ui.providers.VariabilityElementLabelProvider;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.configuration.DefaultElementRealizer;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.command.MoveInListCommand;
import org.eclipse.epf.library.edit.util.CategorySortHelper;
import org.eclipse.epf.library.edit.util.ContentElementOrderList;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodElementProperty;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

/**
 * Open an dialog to order content elements
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 * 
 */
public class ContentElementsOrderDialog extends Dialog {

	private MethodConfiguration config;

	private Composite baseComposite;

	private Button ctrl_up, ctrl_down;

	private Table ctrl_steps;

	private TableViewer stepsTableViewer;

	// private ContentElementOrderList allSteps;
	private ContentElementOrderList allSteps;

	private IStructuredContentProvider stepsViewerContentProvider;

	private ILabelProvider stepsViewerLabelProvider;

	// private Task contentElement;
	private ContentElement contentElement;

	// the Element to use for sorting
	private MethodElement sortElement;

	private IActionManager actionManager;

	private ArrayList commands = new ArrayList();

	protected ComboViewer viewer_sort;

	protected IStructuredContentProvider contentProviderSort = new AdapterFactoryContentProvider(
			TngAdapterFactory.INSTANCE
					.getNavigatorView_ComposedAdapterFactory()) {
		public Object[] getElements(Object object) {
			List<String> sortTypesList = CategorySortHelper
					.getCategorySortTypes();
			return sortTypesList.toArray();
		}
	};

	protected ILabelProvider labelProviderSort = new AdapterFactoryLabelProvider(
			TngAdapterFactory.INSTANCE
					.getNavigatorView_ComposedAdapterFactory()) {
		public String getText(Object object) {
			if (object instanceof String) {
				String str = ((String) object);
				return CategorySortHelper.getSortTypeDisplayName(str);
			}
			return null;
		}
	};

	private EStructuralFeature feature;

	/**
	 * Creates a <code>AdvancedSearchDialog</code> giveb the parent control.
	 * 
	 * @param parent
	 *            The parent control.
	 */
	public ContentElementsOrderDialog(Shell parent,
			ContentElement contentElement, IActionManager actionManager) {
		super(parent);
		this.contentElement = contentElement;
		if (TngUtil.isContributor(contentElement)) {
			this.sortElement = contentElement.getVariabilityBasedOnElement();
		} else {
			this.sortElement = contentElement;
		}
		this.actionManager = actionManager;
		this.allSteps = null;

	}

	/**
	 * @see Dialog#createDialogArea(Composite parent)
	 */
	protected Control createDialogArea(Composite parent) {
		baseComposite = (Composite) super.createDialogArea(parent);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 4;
		baseComposite.setLayout(gridLayout);
		baseComposite.setLayoutData(new GridData());

		// Empty line
		createLabel(baseComposite, " ", 4); //$NON-NLS-1$

		createLabel(baseComposite, " ", 1); //$NON-NLS-1$
		createLabel(
				baseComposite,
				AuthoringUIResources.ContentElementsOrderDialog_ContentElement_text,
				3); 

		createLabel(baseComposite, " ", 1); //$NON-NLS-1$
		ctrl_steps = new Table(baseComposite, SWT.MULTI | SWT.BORDER);
		{
			GridData gridData = new GridData(GridData.FILL_BOTH);
			gridData.horizontalSpan = 2;
			gridData.widthHint = 300;
			gridData.heightHint = 400;
			ctrl_steps.setLayoutData(gridData);
		}

		initProviders();

		stepsTableViewer = new TableViewer(ctrl_steps);
		stepsTableViewer.setContentProvider(stepsViewerContentProvider);
		stepsTableViewer.setLabelProvider(stepsViewerLabelProvider);

		Composite orderButtonPane = new Composite(baseComposite, SWT.NULL);
		{
			GridData gridData = new GridData(GridData.VERTICAL_ALIGN_CENTER
					| GridData.HORIZONTAL_ALIGN_CENTER);
			gridData.horizontalSpan = 1;
			orderButtonPane.setLayoutData(gridData);
			orderButtonPane.setLayout(new GridLayout());
		}

		ctrl_up = createButton(orderButtonPane, AuthoringUIText.UP_BUTTON_TEXT);
		ctrl_up.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		ctrl_down = createButton(orderButtonPane,
				AuthoringUIText.DOWN_BUTTON_TEXT);
		ctrl_down.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		ctrl_up.setEnabled(false);
		ctrl_down.setEnabled(false);

		createLabel(orderButtonPane, LibraryEditResources.SortType_Label, 1);
		Combo ctrl_sort = new Combo(orderButtonPane, SWT.SINGLE | SWT.FLAT
				| SWT.READ_ONLY);
		viewer_sort = new ComboViewer(ctrl_sort);
		viewer_sort.setContentProvider(contentProviderSort);
		viewer_sort.setLabelProvider(labelProviderSort);
		viewer_sort.setInput(contentElement);
		// set initial selection
		String sortType = CategorySortHelper.getCategorySortValue(sortElement);
		viewer_sort.setSelection(new StructuredSelection(sortType), true);
		enableButtons();

		// Empty line
		createLabel(baseComposite, " ", 4); //$NON-NLS-1$

		createLine(baseComposite, 4);

		super
				.getShell()
				.setText(
						AuthoringUIResources.ContentElementsOrderDialog_description_text); 

		addListeners();
		loadData();

		// contributors cannot change the sort type - the base element's sort is
		// used
		if (TngUtil.isLocked(contentElement) || TngUtil.isContributor(contentElement)) {
			viewer_sort.getCombo().setEnabled(false);
		} else {
			viewer_sort.getCombo().setEnabled(true);
		}

		return baseComposite;
	}

	private void createLine(Composite parent, int ncol) {
		Label line = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL
				| SWT.BOLD);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = ncol;
		line.setLayoutData(gridData);
	}

	private Label createLabel(Composite parent, String text, int nCol) {
		Label label = new Label(parent, SWT.NONE);
		label.setText(text);
		{
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
			gridData.horizontalSpan = nCol;
			label.setLayoutData(gridData);
		}

		return label;
	}

	private Button createButton(Composite parent, String label) {
		Button button = new Button(parent, SWT.NONE);
		button.setText(label);
		return button;
	}

	/**
	 * Creates the dialog buttons.
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		// Create the OK button.
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);

		// Create the Cancel button.
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	private void initProviders() {
		stepsViewerContentProvider = new AdapterFactoryContentProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {
			public Object[] getElements(Object object) {

				// if (allSteps == null) {
				// allSteps = new ContentElementOrderList(
				// contentElement,
				// ContentElementOrderList.CONTENT_ELEMENTS__FOR_ELEMENT_AND_PARENTS);
				// }
				// List returnList =
				// CategorySortHelper.sortCategoryElements(sortElement,
				// allSteps.toArray());
				// return returnList.toArray();

				if (allSteps == null) {
					allSteps = new ContentElementOrderList(
							contentElement,
							ContentElementOrderList.CONTENT_ELEMENTS__FOR_ELEMENT_AND_PARENTS,
							feature);
				}
				List returnList = CategorySortHelper.sortCategoryElements(
						sortElement, allSteps.toArray());
				return returnList.toArray();
			}
		};

		stepsViewerLabelProvider = new VariabilityElementLabelProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {

			public boolean isExternal(Object element) {
				return !((List) ((ContentCategory) contentElement)
						.eGet(feature)).contains(element);
			}

			public String getColumnText(Object object, int columnIndex) {
				if (getConfig() != null && object instanceof MethodElement) {
					object = ConfigurationHelper.getCalculatedElement((MethodElement)object,
							DefaultElementRealizer
									.newElementRealizer(getConfig()));
				}
				return super.getColumnText(object, columnIndex);
			}
			
		};
	}

	public void addListeners() {

		stepsTableViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {

					public void selectionChanged(SelectionChangedEvent event) {
						enableButtons();
					}

				});

		ctrl_up.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) stepsTableViewer
						.getSelection();

				ArrayList moveUpItems = new ArrayList();
				moveUpItems.addAll(selection.toList());
				EStructuralFeature feature = UmaPackage.eINSTANCE
						.getCustomCategory_CategorizedElements();
				MoveInListCommand cmd = new MoveInListCommand(
						(ContentCategory) contentElement, moveUpItems,
						allSteps, feature, MoveInListCommand.UP);

				actionManager.execute(cmd);
				commands.add(cmd);

				refreshViewers();

				enableButtons();
			}
		});

		ctrl_down.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) stepsTableViewer
						.getSelection();
				ArrayList moveDownItems = new ArrayList();
				moveDownItems.addAll(selection.toList());
				EStructuralFeature feature = UmaPackage.eINSTANCE
						.getCustomCategory_CategorizedElements();
				MoveInListCommand cmd = new MoveInListCommand(
						(ContentCategory) contentElement, moveDownItems,
						allSteps, feature, MoveInListCommand.DOWN);
				actionManager.execute(cmd);
				commands.add(cmd);

				refreshViewers();

				enableButtons();
			}
		});

		viewer_sort
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						IStructuredSelection selection = (IStructuredSelection) viewer_sort
								.getSelection();
						String sortType = (String) selection.getFirstElement();
						MethodElementProperty prop = CategorySortHelper
								.getCategorySortProperty(contentElement);

						if (prop == null) {
							prop = CategorySortHelper
									.createNewSortProperty(sortType);
							actionManager
									.doAction(
											IActionManager.ADD,
											contentElement,
											UmaPackage.eINSTANCE
													.getMethodElement_MethodElementProperty(),
											prop, -1);
						} else {
							actionManager.doAction(IActionManager.SET, prop,
									UmaPackage.eINSTANCE
											.getMethodElementProperty_Value(),
									sortType, -1);
						}
						refreshViewers();
						enableButtons();
					}
				});

	}

	private void refreshViewers() {
		stepsTableViewer.refresh();
	}

	/**
	 * Load initial data from model
	 */
	private void loadData() {
		stepsTableViewer.setInput(contentElement);
	}

	/**
	 * Called when the OK button is selected.
	 */
	protected void okPressed() {
		allSteps.apply();

		super.okPressed();
	}

	protected void cancelPressed() {
		if (!commands.isEmpty()) {
			for (int i = commands.size() - 1; i > -1; i--) {
				Object cmd = commands.get(i);
				if (cmd instanceof MoveInListCommand) {
					((MoveInListCommand) cmd).undo();
				}
			}
		}
		super.cancelPressed();
	}

	public void setFeature(EStructuralFeature feature) {
		this.feature = feature;
	}

	private void enableButtons() {
		if (isShouldEnableUp()) {
			ctrl_up.setEnabled(true);
		} else
			ctrl_up.setEnabled(false);

		if (isShouldEnableDown()) {
			ctrl_down.setEnabled(true);
		} else
			ctrl_down.setEnabled(false);
	}
	
	protected boolean isShouldEnableUp() {
		IStructuredSelection selection = (IStructuredSelection) stepsTableViewer
			.getSelection();
		if (selection.size() == 1 &&
				stepsTableViewer.getTable().getSelectionIndex() > 0 &&
				!TngUtil.isLocked(contentElement) && 
				CategorySortHelper.isManualCategorySort(sortElement)) {
			return true;
		} else {
			return false;
		}
	}
	
	protected boolean isShouldEnableDown() {
		IStructuredSelection selection = (IStructuredSelection) stepsTableViewer
			.getSelection();
		if (selection.size() == 1 &&
				(stepsTableViewer.getTable().getSelectionIndex() < stepsTableViewer
					.getTable().getItemCount() - 1) &&
				!TngUtil.isLocked(contentElement) &&
				CategorySortHelper.isManualCategorySort(sortElement)) {
			return true;
		} else {
			return false;
		}
	}
	
	private MethodConfiguration getConfig() {
		return config;
	}

	public void setConfig(MethodConfiguration config) {
		this.config = config;
	}

}