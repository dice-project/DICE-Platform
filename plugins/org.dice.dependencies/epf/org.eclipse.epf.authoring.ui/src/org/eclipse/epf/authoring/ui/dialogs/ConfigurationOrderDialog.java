//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.authoring.ui.providers.VariabilityElementLabelProvider;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.command.MoveInConfigurationCommand;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.jface.dialogs.Dialog;
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
 * Open a dialog to order configuration views
 * @author Xiang Dong Hu
 * @since 1.2
 *  fix for https://bugs.eclipse.org/bugs/show_bug.cgi?id=173827
 */
public class ConfigurationOrderDialog extends Dialog {

	private Composite baseComposite;

	private Button ctrl_up, ctrl_down;

	private Table ctrl_views;

	private TableViewer viewsTableViewer;

//	private ContentElementOrderList allSteps;

	private IStructuredContentProvider viewsViewerContentProvider;

	private ILabelProvider viewsViewerLabelProvider;
	
	private MethodConfiguration config;

	private IActionManager actionManager;
	
	private ArrayList commands = new ArrayList();
	
	public ConfigurationOrderDialog(Shell parent, 
			MethodConfiguration config, IActionManager actionManager) {
		super(parent);
		this.config = config;
		this.actionManager = actionManager;
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
				AuthoringUIResources.ConfigurationOrderDialog_NavigationViews, 3);

		createLabel(baseComposite, " ", 1); //$NON-NLS-1$
		ctrl_views = new Table(baseComposite, SWT.MULTI | SWT.BORDER);
		{
			GridData gridData = new GridData(GridData.FILL_BOTH);
			gridData.horizontalSpan = 2;
			gridData.widthHint = 300;
			gridData.heightHint = 400;
			ctrl_views.setLayoutData(gridData);
		}

		initProviders();

		viewsTableViewer = new TableViewer(ctrl_views);
		viewsTableViewer.setContentProvider(viewsViewerContentProvider);
		viewsTableViewer.setLabelProvider(viewsViewerLabelProvider);

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

		super
				.getShell()
				.setText(
						AuthoringUIResources.ConfigurationOrderDialog_description);

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
	
	private void initProviders() {
		viewsViewerContentProvider = new AdapterFactoryContentProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {
			public Object[] getElements(Object object) {
				return config.getProcessViews().toArray();
			}
		};

		viewsViewerLabelProvider = new VariabilityElementLabelProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {

			public boolean isExternal(Object element) {
				return !config.getProcessViews().contains(element);
			}

		};
	}
	
	public void addListeners() {
		viewsTableViewer
		.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) viewsTableViewer
						.getSelection();
				if (selection.size() > 0) {
					if (viewsTableViewer.getTable().getSelectionIndex() > 0
							&& selection.size() == 1) {
						ctrl_up.setEnabled(true);
					} else
						ctrl_up.setEnabled(false);

					if (viewsTableViewer.getTable().getSelectionIndex() < viewsTableViewer
							.getTable().getItemCount() - 1
							&& selection.size() == 1) {
						ctrl_down.setEnabled(true);
					} else
						ctrl_down.setEnabled(false);
				}
			}

		});
		
		ctrl_up.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) viewsTableViewer
						.getSelection();

				ArrayList moveUpItems = new ArrayList();
				moveUpItems.addAll(selection.toList());
				EStructuralFeature feature = UmaPackage.eINSTANCE
						.getMethodConfiguration_ProcessViews();
				MoveInConfigurationCommand cmd = new MoveInConfigurationCommand(
						config, moveUpItems, feature,1);
				actionManager.execute(cmd);
				commands.add(cmd);

				viewsTableViewer.refresh();

				if (viewsTableViewer.getTable().getSelectionIndex() > 0) {
					ctrl_up.setEnabled(true);
				} else
					ctrl_up.setEnabled(false);
				if (viewsTableViewer.getTable().getSelectionIndex() < viewsTableViewer
						.getTable().getItemCount() - 1) {
					ctrl_down.setEnabled(true);
				} else
					ctrl_down.setEnabled(false);
			}
		});
		
		ctrl_down.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) viewsTableViewer
						.getSelection();
				ArrayList moveDownItems = new ArrayList();
				moveDownItems.addAll(selection.toList());
				EStructuralFeature feature = UmaPackage.eINSTANCE
						.getMethodConfiguration_ProcessViews();
				MoveInConfigurationCommand cmd = new MoveInConfigurationCommand(
						config, moveDownItems, feature,0);
				actionManager.execute(cmd);
				commands.add(cmd);

				viewsTableViewer.refresh();

				if (viewsTableViewer.getTable().getSelectionIndex() > 0) {
					ctrl_up.setEnabled(true);
				} else
					ctrl_up.setEnabled(false);
				if (viewsTableViewer.getTable().getSelectionIndex() < viewsTableViewer
						.getTable().getItemCount() - 1) {
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
		viewsTableViewer.setInput(config);
	}
	
	/**
	 * Called when the OK button is selected.
	 */
	protected void okPressed() {
//		allSteps.apply();
		super.okPressed();
	}

	protected void cancelPressed() {
		if (!commands.isEmpty()) {
			for (int i = commands.size() - 1; i > -1; i--) {
				Object cmd = commands.get(i);
				if (cmd instanceof MoveInConfigurationCommand) {
					((MoveInConfigurationCommand) cmd).undo();
				}
			}
		}
		super.cancelPressed();
	}

}
