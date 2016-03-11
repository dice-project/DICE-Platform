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
import org.eclipse.epf.authoring.ui.filters.ProcessRoleFilter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.process.command.LinkMethodElementCommand;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.ui.LibraryUIText;
import org.eclipse.epf.uma.CompositeRole;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;


/**
 * The general tab section for Role Descriptor
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 * 
 */
public class RoleDescriptorGeneralSection extends DescriptorGeneralSection {
	protected RoleDescriptor element;

	private Label ctrl_method_element_label;

	private Text ctrl_method_element;

	private Button linkButton;
	
	private Button clearButton;

	/* (non-Javadoc)
	 * @see org.eclipse.epf.authoring.ui.properties.DescriptorGeneralSection#init()
	 */
	protected void init() {
		super.init();
		// get RoleDescriptor object
		element = (RoleDescriptor) getElement();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.authoring.ui.properties.DescriptorGeneralSection#createGeneralSection(org.eclipse.swt.widgets.Composite)
	 */
	protected void createGeneralSection(Composite composite) {
		super.createGeneralSection(composite);

		// method element
		ctrl_method_element_label = FormUI.createLabel(toolkit,
				generalComposite, PropertiesResources.Process_Type_Role); 

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

		// show/hide method role
		toggleMethodRole();
	}

	private void toggleMethodRole() {
		if (element instanceof CompositeRole) {
			ctrl_method_element_label.setVisible(false);
			ctrl_method_element.setVisible(false);
			linkButton.setVisible(false);
			clearButton.setVisible(false);
		} else {
			ctrl_method_element_label.setVisible(true);
			ctrl_method_element.setVisible(true);
			linkButton.setVisible(true);
			clearButton.setVisible(true);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.authoring.ui.properties.DescriptorGeneralSection#addListeners()
	 */
	protected void addListeners() {
		super.addListeners();

		linkButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				IFilter filter = new ProcessRoleFilter(
						getConfiguration(), null, FilterConstants.ROLES);
				List existingElements = new ArrayList();
				if (element.getRole() != null) {
					existingElements.add(element.getRole());
				}
				ItemsFilterDialog fd = new ItemsFilterDialog(PlatformUI
						.getWorkbench().getActiveWorkbenchWindow().getShell(),
						filter, element, FilterConstants.ROLES,
						existingElements);

				fd.setBlockOnOpen(true);
				fd.setViewerSelectionSingle(true);
				fd.setTitle(FilterConstants.ROLES);
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
								.getRoleDescriptor_Role(),
						null, -1);
				// update method element control
				ctrl_method_element.setText(getMethodElementName(element));
			}

			public void widgetDefaultSelected(SelectionEvent e1) {
			}
		});
	}

	private void setMethodElement(List items) {
		if ((items != null) && (items.size() >= 1)) {
			if (items.get(0) instanceof Role) {
				Role role = (Role) items.get(0);

//				List list = ProcessUtil
//						.getSiblings(TngAdapterFactory.INSTANCE
//								.getOBS_ComposedAdapterFactory(), getAdapter(),
//								element);
				boolean canAssign = true;
//				for (Iterator itor = list.iterator(); itor.hasNext();) {
//					Object obj = itor.next();
//					if (obj instanceof RoleDescriptor) {
//						RoleDescriptor roleDesc = (RoleDescriptor) obj;
//						if ((!roleDesc.equals(element))
//								&& (!roleDesc.getSuppressed().booleanValue())) {
//							Role exisingRole = roleDesc.getRole();
//							if (role.equals(exisingRole)) {
//								canAssign = false;
//								break;
//							}
//						}
//					}
//				}
				if (canAssign) {
					LinkMethodElementCommand cmd = new LinkMethodElementCommand(
							element, role, UmaPackage.ROLE_DESCRIPTOR__ROLE);
					actionMgr.execute(cmd);

					// set selection to same element to enable correct actions
					getEditor().setSelection(getSelection());
				} else {
					MessageFormat mf = new MessageFormat(PropertiesResources.Process_InvalidLinkMethodElement); 
					Object[] args = { role.getName() };
					AuthoringUIPlugin
							.getDefault()
							.getMsgDialog()
							.displayInfo(
									PropertiesResources.Process_LinkMethodElementTitle, mf.format(args)); 
				}
			}
		}
	}

	private String getMethodElementName(RoleDescriptor element) {
		String str = PropertiesResources.Process_None; 
		if (element.getRole() != null) {
//			str = element.getRole().getName();
			str = TngUtil.getLabelWithPath(element.getRole());
		}

		return str;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.authoring.ui.properties.DescriptorGeneralSection#updateControls()
	 */
	protected void updateControls() {
		super.updateControls();

		linkButton.setEnabled(editable);
		clearButton.setEnabled(editable);
		Object obj = getElement();
		if (obj instanceof RoleDescriptor) {
			RoleDescriptor roleDesc = (RoleDescriptor) obj;
			if ((roleDesc.getSuperActivities() == null)
					|| (roleDesc.getSuperActivities() == null)) {
				nameText.setEditable(true);
				presentationNameText.setEditable(true);
			} else {
				nameText.setEditable(editable);
				presentationNameText.setEditable(editable);
			}
		}

		if (isSyncFree()) {
			if (element.getRole() != null) {
				linkButton.setEnabled(false);
			}
			clearButton.setEnabled(false);
		}	
	}


	/* (non-Javadoc)
	 * @see org.eclipse.epf.authoring.ui.properties.DescriptorGeneralSection#refresh()
	 */
	public void refresh() {
		try {
			super.refresh();

			if (getElement() instanceof RoleDescriptor) {
				element = (RoleDescriptor) getElement();
				ctrl_method_element.setText(getMethodElementName(element));

				// show/hide method role
				toggleMethodRole();

			}
		} catch (Exception ex) {
			logger.logError(
					"Error refreshing RoleDescriptor general section: ", ex); //$NON-NLS-1$
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.authoring.ui.properties.DescriptorGeneralSection#getNamePrefix()
	 */
	public String getNamePrefix() {
		if (getElement() instanceof CompositeRole) {
			return LibraryUIText.TEXT_COMPOSITE_ROLE + ": "; //$NON-NLS-1$
		}
		return LibraryUIText.TEXT_ROLE_DESCRIPTOR + ": "; //$NON-NLS-1$
	}
	
	protected boolean isSyncFree() {
		return ProcessUtil.isSynFree();
	}
	
}
