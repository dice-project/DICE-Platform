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

import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.authoring.ui.providers.VariabilityElementLabelProvider;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.command.MoveInSectionListCommand;
import org.eclipse.epf.library.edit.util.SectionList;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.Step;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;


/**
 * Dialog to order the {@link Step}s.
 * 
 */
public class SectionsOrderDialog extends Dialog {

	private Composite baseComposite;

	private Button ctrl_up, ctrl_down;

	private Table ctrl_steps;

	private TableViewer stepsTableViewer;

	private SectionList allSections;

	private IStructuredContentProvider stepsViewerContentProvider;

	private ILabelProvider stepsViewerLabelProvider;

	// private Task contentElement;
	private ContentElement contentElement;

	private IActionManager actionManager;

	private String title;

	private String message;

	private String label;

	private ArrayList commands = new ArrayList();

	/**
	 * Creates a <code>AdvancedSearchDialog</code> giveb the parent control.
	 * 
	 * @param parent
	 *            The parent control.
	 */
	public SectionsOrderDialog(Shell parent, ContentElement contentElement,
			IActionManager actionManager, String title, String message,
			String label) {
		super(parent);
		this.contentElement = contentElement;
		this.actionManager = actionManager;
		this.title = title;
		this.message = message;
		this.label = label;
		allSections = null;
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

		createLabel(baseComposite, message, 4);

		// Empty line
		createLabel(baseComposite, " ", 4); //$NON-NLS-1$

		createLabel(baseComposite, " ", 1); //$NON-NLS-1$
		createLabel(baseComposite, label, 3);

		createLabel(baseComposite, " ", 1); //$NON-NLS-1$
		ctrl_steps = new Table(baseComposite, SWT.BORDER | SWT.MULTI);
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

		// Empty line
		createLabel(baseComposite, " ", 4); //$NON-NLS-1$

		createLine(baseComposite, 4);

		super.getShell().setText(title);

		addListeners();
		loadData();

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
				if (allSections == null) {
					allSections = new SectionList(contentElement,
							SectionList.STEPS_FOR_ELEMENT_AND_PARENTS);
				}
				return allSections.toArray();
			}
		};

		stepsViewerLabelProvider = new VariabilityElementLabelProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {

			public boolean isExternal(Object element) {
				return !contentElement.getPresentation().getSections()
						.contains(element);
			}

		};
	}

	public void addListeners() {
		stepsTableViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {

					public void selectionChanged(SelectionChangedEvent event) {
						IStructuredSelection selection = (IStructuredSelection) stepsTableViewer
								.getSelection();
						if (selection.size() > 0
								&& !TngUtil.isLocked(contentElement)) {
							if (stepsTableViewer.getTable().getSelectionIndex() > 0
									&& !TngUtil.isLocked(contentElement)
									&& selection.size() == 1) {
								ctrl_up.setEnabled(true);
							} else
								ctrl_up.setEnabled(false);

							if (stepsTableViewer.getTable().getSelectionIndex() < stepsTableViewer
									.getTable().getItemCount() - 1
									&& !TngUtil.isLocked(contentElement)
									&& selection.size() == 1) {
								ctrl_down.setEnabled(true);
							} else
								ctrl_down.setEnabled(false);
						}
					}
				});

		ctrl_up.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) stepsTableViewer
						.getSelection();

				ArrayList moveUpItems = new ArrayList();
				moveUpItems.addAll(selection.toList());
				MoveInSectionListCommand cmd = new MoveInSectionListCommand(
						contentElement, moveUpItems, allSections, 1);
				actionManager.execute(cmd);
				commands.add(cmd);

				stepsTableViewer.refresh();

				if (stepsTableViewer.getTable().getSelectionIndex() > 0
						&& !TngUtil.isLocked(contentElement)) {
					ctrl_up.setEnabled(true);
				} else
					ctrl_up.setEnabled(false);
				if (stepsTableViewer.getTable().getSelectionIndex() < stepsTableViewer
						.getTable().getItemCount() - 1
						&& !TngUtil.isLocked(contentElement)) {
					ctrl_down.setEnabled(true);
				} else
					ctrl_down.setEnabled(false);
			}
		});

		ctrl_down.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) stepsTableViewer
						.getSelection();
				ArrayList moveDownItems = new ArrayList();
				moveDownItems.addAll(selection.toList());
				MoveInSectionListCommand cmd = new MoveInSectionListCommand(
						contentElement, moveDownItems, allSections, 0);
				actionManager.execute(cmd);
				commands.add(cmd);

				stepsTableViewer.refresh();

				if (stepsTableViewer.getTable().getSelectionIndex() > 0
						&& !TngUtil.isLocked(contentElement)) {
					ctrl_up.setEnabled(true);
				} else
					ctrl_up.setEnabled(false);
				if (stepsTableViewer.getTable().getSelectionIndex() < stepsTableViewer
						.getTable().getItemCount() - 1
						&& !TngUtil.isLocked(contentElement)) {
					ctrl_down.setEnabled(true);
				} else
					ctrl_down.setEnabled(false);
			}
		});
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
		allSections.apply();
		super.okPressed();
	}

	protected void cancelPressed() {
		if (!commands.isEmpty()) {
			for (int i = commands.size() - 1; i > -1; i--) {
				Object cmd = commands.get(i);
				if (cmd instanceof MoveInSectionListCommand) {
					((MoveInSectionListCommand) cmd).undo();
				}
			}
		}
		super.cancelPressed();
	}
}