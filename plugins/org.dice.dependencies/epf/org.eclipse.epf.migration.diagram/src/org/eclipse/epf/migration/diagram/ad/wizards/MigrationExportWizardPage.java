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
package org.eclipse.epf.migration.diagram.ad.wizards;

import java.util.Map;

import org.eclipse.epf.migration.diagram.DiagramMigrationPlugin;
import org.eclipse.epf.migration.diagram.MigrationExportConstants;
import org.eclipse.epf.migration.diagram.MigrationResources;
import org.eclipse.epf.migration.diagram.util.MigrationUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Process;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * Export wizard page for exporting diagrams
 * 
 * @author Shilpa Toraskar
 * @since 1.2
 *
 */
public class MigrationExportWizardPage extends WizardPage implements
		IWizardPage, Listener {

	public static final String PAGE_NAME = MigrationExportWizardPage.class
			.getName();

	private static final String[] EMPTY_ARRAY = new String[0];

	private Button capabilityPatternRadioButton;

	private Button deliveryProcessRadioButton;

	private Combo processCombo;

//	private Combo contextCombo;

	private Combo activityCombo;

	private Combo templateNameCombo;

	private Combo templateDirCombo;

	private Map capabilityPatterns;

	private Map deliveryProcesses;

	private Map activities;

	private ModifyListener modifyListener = new ModifyListener() {
		public void modifyText(ModifyEvent e) {
			setPageComplete(isPageComplete());
		}
	};

	protected MigrationExportWizardPage() {
		super(PAGE_NAME);
		setTitle(MigrationResources.workflow_export_wizard_title);
		setDescription(MigrationResources.workflow_export_wizard_text);
		
		setImageDescriptor(DiagramMigrationPlugin.getDefault()
				.getImageDescriptor("full/wizban/ExportXML.gif")); //$NON-NLS-1$
	}

	public void createControl(Composite parent) {
		final Shell shell = parent.getShell();

		// Create the composite to hold the widgets.
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout(1, false));

		// Create the Process group.
		Group processGroup = new Group(composite, SWT.NULL);
		processGroup.setLayout(new GridLayout(1, false));
		processGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		processGroup
				.setText(MigrationResources.workflow_export_wizard_processGroup_text);

		Composite buttonComposite = new Composite(processGroup, SWT.NULL);
		buttonComposite.setLayout(new GridLayout(2, false));

		capabilityPatternRadioButton = new Button(buttonComposite, SWT.RADIO);
		capabilityPatternRadioButton
				.setText(MigrationResources.workflow_export_wizard_capabilityPatternRadioButton_text);
		capabilityPatternRadioButton
				.addSelectionListener(new SelectionListener() {
					public void widgetSelected(SelectionEvent event) {
						updateProcessCombo(capabilityPatterns);
						updateActivityCombo(getProcess());
					}

					public void widgetDefaultSelected(SelectionEvent event) {
					}
				});

		deliveryProcessRadioButton = new Button(buttonComposite, SWT.RADIO);
		deliveryProcessRadioButton
				.setText(MigrationResources.workflow_export_wizard_deliveryProcessRadioButton_text);
		deliveryProcessRadioButton
				.addSelectionListener(new SelectionListener() {
					public void widgetSelected(SelectionEvent event) {
						updateProcessCombo(deliveryProcesses);
						updateActivityCombo(getProcess());
					}

					public void widgetDefaultSelected(SelectionEvent event) {
					}
				});

		Composite processComposite = new Composite(processGroup, SWT.NULL);
		processComposite.setLayout(new GridLayout(2, false));
		processComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label processNameLabel = new Label(processComposite, SWT.NONE);
		processNameLabel
				.setText(MigrationResources.workflow_export_wizard_processNameLabel_text);

		processCombo = new Combo(processComposite, SWT.BORDER | SWT.READ_ONLY);
		processCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		processCombo.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent event) {
//				updateContextCombo(getProcess());
//				if (getProcessContextName() != null) {
//					String contextName = getProcessContextName();
//					Map result = MigrationUtil.getContexts(getProcess());
//					updateActivityCombo(getProcess(),
//							(MethodConfiguration) result.get(contextName));
//				}
				updateActivityCombo(getProcess());
			}

			public void widgetDefaultSelected(SelectionEvent event) {
			}
		});

//		Label contextNameLabel = new Label(processComposite, SWT.NONE);
//		contextNameLabel
//				.setText(MigrationResources.workflow_export_wizard_contextNameLabel_text);
//
//		contextCombo = new Combo(processComposite, SWT.BORDER | SWT.READ_ONLY);
//		contextCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		contextCombo.addModifyListener(modifyListener);
//
//		contextCombo.addSelectionListener(new SelectionListener() {
//			public void widgetSelected(SelectionEvent event) {
//				String contextName = getProcessContextName();
//				Map result = MigrationUtil.getContexts(getProcess());
//				updateActivityCombo(getProcess(), (MethodConfiguration) result
//						.get(contextName));
//			}
//
//			public void widgetDefaultSelected(SelectionEvent event) {
//			}
//		});

		Label activitiesNameLabel = new Label(processComposite, SWT.NONE);
		activitiesNameLabel
				.setText(MigrationResources.workflow_export_wizard_activitiesNameLabel_text);

		activityCombo = new Combo(processComposite, SWT.BORDER | SWT.READ_ONLY);
		activityCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		activityCombo.addModifyListener(modifyListener);

		capabilityPatterns = MigrationUtil.getCapabilityPatterns();
		deliveryProcesses = MigrationUtil.getDeliveryProcesses();
		String savedProcessType = DiagramMigrationPlugin.getDefault()
				.getPreferenceStore().getString(
						MigrationExportConstants.PROCESS_TYPE);
		if (savedProcessType != null
				&& savedProcessType.equals("DeliveryProcess") && deliveryProcesses.size() > 0) { //$NON-NLS-1$
			deliveryProcessRadioButton.setSelection(true);
			updateProcessCombo(deliveryProcesses);
		} else if (savedProcessType != null && capabilityPatterns != null && capabilityPatterns.size() > 0) {
			capabilityPatternRadioButton.setSelection(true);
			updateProcessCombo(capabilityPatterns);
		} else if (capabilityPatterns != null && capabilityPatterns.size() > 0) {
			capabilityPatternRadioButton.setSelection(true);
			updateProcessCombo(capabilityPatterns);
		} else {
			deliveryProcessRadioButton.setSelection(true);
			updateProcessCombo(deliveryProcesses);
		}

		updateActivityCombo(getProcess());
		
		// Create the Project Template group.
		Group templateGroup = new Group(composite, SWT.NULL);
		templateGroup.setLayout(new GridLayout(1, false));
		templateGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		templateGroup
				.setText(MigrationResources.workflow_export_wizard_templateGroup_text);

		Composite templateComposite = new Composite(templateGroup, SWT.NULL);
		templateComposite.setLayout(new GridLayout(3, false));
		templateComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label templateNameLabel = new Label(templateComposite, SWT.NONE);
		templateNameLabel
				.setText(MigrationResources.workflow_export_wizard_templateNameLabel_text);

		templateNameCombo = new Combo(templateComposite, SWT.BORDER);
		templateNameCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		String prevName = DiagramMigrationPlugin.getDefault()
				.getPreferenceStore().getString(
						MigrationExportConstants.TEMPLATE_NAMES);
		if (prevName != null) {
			templateNameCombo.add(prevName);
		}
		templateNameCombo.addModifyListener(modifyListener);

		new Label(templateComposite, SWT.NONE);

		Label dirLabel = new Label(templateComposite, SWT.NONE);
		dirLabel
				.setText(MigrationResources.workflow_export_wizard_dirLabel_text);

		templateDirCombo = new Combo(templateComposite, SWT.BORDER);
		templateDirCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		String savedDir = DiagramMigrationPlugin.getDefault()
				.getPreferenceStore().getString(
						MigrationExportConstants.TARGET_DIRECTORIES);
		if (savedDir != null) {
			templateDirCombo.add(savedDir);
			templateDirCombo.setText(savedDir);
		}
		templateDirCombo.addModifyListener(modifyListener);

		Button browseButton = new Button(templateComposite, SWT.NONE);
		browseButton
				.setText(MigrationResources.workflow_export_wizard_browseButton_text);
		browseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				try {
					DirectoryDialog dialog = new DirectoryDialog(shell,
							SWT.NONE);
					String selectedDir = dialog.open();
					if (selectedDir != null) {
						templateDirCombo.add(selectedDir, 0);
						templateDirCombo.setText(selectedDir);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		setControl(composite);
	}

	public void handleEvent(Event event) {
	}

	/**
	 * Updates the Process Combo.
	 */
	protected void updateProcessCombo(Map processes) {
		if (processes != null && processes.size() > 0) {
			String[] names = new String[processes.size()];
			processes.keySet().toArray(names);
			processCombo.setItems(names);
			String savedProcess = DiagramMigrationPlugin.getDefault()
					.getPreferenceStore().getString(
							MigrationExportConstants.PROCESS_NAME);
			if (savedProcess != null && processes.containsKey(savedProcess)) {
				processCombo.setText(savedProcess);
			} else {
				processCombo.setText(processCombo.getItem(0));
			}
//			updateContextCombo(getProcess());
		} else {
			processCombo.setItems(EMPTY_ARRAY);
		}
	}

	/**
	 * Updates the Context Combo.
	 */
//	protected void updateContextCombo(Process process) {
//		try {
//			Map contexts = MigrationUtil.getContexts(process);
//			if (contexts != null && contexts.size() > 0) {
//				String[] names = new String[contexts.size()];
//				contexts.keySet().toArray(names);
//				contextCombo.setItems(names);
//				String savedContext = DiagrammingMigrationPlugin.getDefault()
//						.getPreferenceStore().getString(
//								MigrationExportConstants.PROCESS_CONTEXT);
//				if (savedContext != null && contexts.containsKey(savedContext)) {
//					contextCombo.setText(savedContext);
//				} else {
//					String defaultContext = process.getDefaultContext()
//							.getName();
//					contextCombo.setText(defaultContext);
//				}
//			} else {
//				contextCombo.setItems(EMPTY_ARRAY);
//			}
//		} catch (Exception e) {
//		}
//	}

	/**
	 * Updates the activitity Combo.
	 */
	protected void updateActivityCombo(Process process) {
		try {
			activities = MigrationUtil.getActivities(process);
			if (activities != null && activities.size() > 0) {
				String[] names = new String[activities.size()];
				activities.keySet().toArray(names);
				activityCombo.setItems(names);
				// String savedContext = WorkflowExpPlugin.getDefault()
				// .getPreferenceStore().getString(
				// WorkflowExportConstants.ACTIVITY_CONTEXT);
				// if (savedContext != null &&
				// contexts.containsKey(savedContext)) {
				// activityCombo.setText(savedContext);
				// } else {
				// String defaultContext = process.getDefaultContext()
				// .getName();
				// activityCombo.setText(defaultContext);
				// }
				activityCombo.setText(names[0]);
			} else {
				activityCombo.setItems(EMPTY_ARRAY);
			}
			
		} catch (Exception e) {
		}
	}

	/**
	 * Returns the selected Process.
	 */
	public Process getProcess() {
		if (capabilityPatternRadioButton.getSelection()) {
			String name = processCombo.getText();
			return (Process) capabilityPatterns.get(name);
		} else {
			String name = processCombo.getText();
			return (Process) deliveryProcesses.get(name);
		}
	}

	/**
	 * Returns the selected Process context.
	 */
//	public String getProcessContextName() {
//		return (String) contextCombo.getText();
//	}

	/**
	 * Returns the template name.
	 */
	public String getTemplateName() {
		return templateNameCombo.getText();
	}

	/**
	 * Returns the target directory.
	 */
	public String getTargetDirectory() {
		return templateDirCombo.getText();
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#isCompleted()
	 */
	public boolean isPageComplete() {
		if (processCombo.getText().trim().length() > 0
//				&& contextCombo.getText().trim().length() > 0
				&& templateNameCombo.getText().trim().length() > 0
				&& templateDirCombo.getText().trim().length() > 0
				&& activityCombo.getText().trim().length() > 0) {
			return true;
		}
		return false;
	}

	public Activity getActivity() {
		if (activityCombo.getSelectionIndex() != -1) {
			String activityName = activityCombo.getText();
			Activity activity = (Activity) activities.get(activityName);
			return activity;
		}
		return null;
	}

//	public MethodConfiguration getContext() {
//		if (getProcessContextName() != null) {
//			String contextName = getProcessContextName();
//			Map result = MigrationUtil.getContexts(getProcess());
//			return (MethodConfiguration) result.get(contextName);
//		}
//		return null;
//	}
}
