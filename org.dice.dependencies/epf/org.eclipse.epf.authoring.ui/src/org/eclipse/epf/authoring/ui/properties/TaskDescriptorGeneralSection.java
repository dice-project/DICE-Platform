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
package org.eclipse.epf.authoring.ui.properties;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.dialogs.ItemsFilterDialog;
import org.eclipse.epf.authoring.ui.filters.ProcessTaskFilter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.process.command.LinkMethodElementCommand;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.ui.LibraryUIText;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;


/**
 * The general tab section for Task Descriptor
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 * 
 */
public class TaskDescriptorGeneralSection extends
		WorkBreakdownElementGeneralSection {
	protected TaskDescriptor element;

	private Button synchronizedButton;

	private Text ctrl_method_element;

	private Button linkButton;
	
	private Button clearButton;

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.WorkBreakdownElementGeneralSection#init()
	 */
	protected void init() {
		super.init();
		// get TaskDescriptor object
		element = (TaskDescriptor) getElement();
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.WorkBreakdownElementGeneralSection#createGeneralSection(org.eclipse.swt.widgets.Composite)
	 */
	protected void createGeneralSection(Composite composite) {
		super.createGeneralSection(composite);

		// create composite for checkbox
		checkBoxComposite = FormUI.createComposite(toolkit, generalComposite,
				GridData.FILL_HORIZONTAL, numOfColumns, true);

		// synchronized with source
		synchronizedButton = FormUI.createCheckButton(toolkit, checkBoxComposite,
				1);
		synchronizedButton.setText(PropertiesResources.BreakdownElement_Option_Synchronized); 

		// method element
		FormUI.createLabel(toolkit, generalComposite, PropertiesResources.Process_Type_Task); 
		ctrl_method_element = FormUI.createText(toolkit, generalComposite,
				SWT.DEFAULT, 1);

		ctrl_method_element.setText(getMethodElementName(element));
		ctrl_method_element.setEnabled(false);

		Composite buttonComposite = FormUI.createComposite(toolkit,
				generalComposite, SWT.NONE, 2, true);
		linkButton = FormUI.createButton(toolkit, buttonComposite, SWT.PUSH, 1);
		linkButton
				.setText(PropertiesResources.Process_Button_LinkMethodElement); 

		clearButton = FormUI.createButton(toolkit, buttonComposite, SWT.PUSH, 1);
		clearButton
				.setText(PropertiesResources.Process_Button_ClearMethodElement); 


	}

	private void setMethodElement(List items) {
		if ((items != null) && (items.size() >= 1)) {
			if (items.get(0) instanceof Task) {
				Task task = (Task) items.get(0);

//				List siblings = ProcessUtil
//						.getSiblings(TngAdapterFactory.INSTANCE
//								.getWBS_ComposedAdapterFactory(), getAdapter(),
//								element);
				boolean canAssign = true;
//			
//
//				for (Iterator itor = list.iterator(); itor.hasNext();) {
//					Object obj = itor.next();
//					if (obj instanceof TaskDescriptor) {
//						TaskDescriptor taskDesc = (TaskDescriptor) obj;
//						if ((!taskDesc.equals(element))
//								&& (!taskDesc.getSuppressed().booleanValue())) {
//							Task exisingTask = taskDesc.getTask();
//							if (task.equals(exisingTask)) {
//								canAssign = false;
//								break;
//							}
//						}
//					}
//				}
				if (canAssign) {
					LinkMethodElementCommand cmd = new LinkMethodElementCommand(
							element, task, UmaPackage.TASK_DESCRIPTOR__TASK);
					actionMgr.execute(cmd);

					// set selection to same element to enable correct actions
					getEditor().setSelection(getSelection());
				} else {
					MessageFormat mf = new MessageFormat(PropertiesResources.Process_InvalidLinkMethodElement); 
					Object[] args = { task.getName() };
					AuthoringUIPlugin
							.getDefault()
							.getMsgDialog()
							.displayInfo(
									PropertiesResources.Process_LinkMethodElementTitle, mf.format(args)); 
				}
			}
		}
	}

	private String getMethodElementName(TaskDescriptor element) {
		String str = PropertiesResources.Process_None; 
		if (element.getTask() != null) {
//			str = element.getTask().getName();
			str = TngUtil.getLabelWithPath(element.getTask());
		}

		return str;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.WorkBreakdownElementGeneralSection#addListeners()
	 */
	protected void addListeners() {
		super.addListeners();

		synchronizedButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				actionMgr.doAction(IActionManager.SET, element,
						UmaPackage.eINSTANCE
								.getDescriptor_IsSynchronizedWithSource(),
						Boolean.valueOf(synchronizedButton.getSelection()), -1);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		linkButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				IFilter filter = new ProcessTaskFilter(
						getConfiguration(), null, FilterConstants.TASKS);
				List existingElements = new ArrayList();
				if (element.getTask() != null) {
					existingElements.add(element.getTask());
				}
				ItemsFilterDialog fd = new ItemsFilterDialog(PlatformUI
						.getWorkbench().getActiveWorkbenchWindow().getShell(),
						filter, element, FilterConstants.TASKS,
						existingElements);

				fd.setBlockOnOpen(true);
				fd.setViewerSelectionSingle(true);
				fd.setTitle(FilterConstants.TASKS);
				fd.setEnableProcessScope(true);
				fd.setSection(getSection());
				fd.open();
				setMethodElement(fd.getSelectedItems());

				// update method element control
				ctrl_method_element.setText(getMethodElementName(element));
				if (isSyncFree()) {
					getEditor().updateOnLinkedElementChange(element);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e1) {
			}
		});
		
		
		clearButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				actionMgr.doAction(IActionManager.SET, element,
						UmaPackage.eINSTANCE
								.getTaskDescriptor_Task(),
						null, -1);
				// update method element control
				ctrl_method_element.setText(getMethodElementName(element));
			}

			public void widgetDefaultSelected(SelectionEvent e1) {
			}
		});
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.WorkBreakdownElementGeneralSection#updateControls()
	 */
	protected void updateControls() {
		super.updateControls();
		synchronizedButton.setEnabled(editable);
		linkButton.setEnabled(editable);
		clearButton.setEnabled(editable);
		if (isSyncFree()) {
			if (element.getTask() != null) {
				linkButton.setEnabled(false);
			}
			clearButton.setEnabled(false);
			synchronizedButton.setVisible(false);
			synchronizedButton.setEnabled(false);
		}	
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.properties.WorkBreakdownElementGeneralSection#refresh()
	 */
	public void refresh() {
		try {
			if (getElement() instanceof TaskDescriptor) {
				super.refresh();

				element = (TaskDescriptor) getElement();
				synchronizedButton.setSelection(element
						.getIsSynchronizedWithSource().booleanValue());
				ctrl_method_element.setText(getMethodElementName(element));
			}
		} catch (Exception ex) {
			logger
					.logError(
							"Error refreshing TaskDescriptor general section: " + element, ex); //$NON-NLS-1$
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.WorkBreakdownElementGeneralSection#getNamePrefix()
	 */
	public String getNamePrefix() {
		return LibraryUIText.TEXT_TASK_DESCRIPTOR + ": "; //$NON-NLS-1$
	}
	
	protected boolean isSyncFree() {
		return ProcessUtil.isSynFree();
	}
	
}
