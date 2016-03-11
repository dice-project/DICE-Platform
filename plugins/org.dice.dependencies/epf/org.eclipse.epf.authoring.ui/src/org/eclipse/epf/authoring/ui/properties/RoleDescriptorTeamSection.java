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
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.common.ui.util.MsgDialog;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.process.RoleDescriptorWrapperItemProvider;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.CompositeRole;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ListSelectionDialog;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;


/**
 * The Role descriptor - team profile section
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 * 
 */
public class RoleDescriptorTeamSection extends OBSRelationSection {
	private Image titleImage = null;


	/**
	 * @see org.eclipse.epf.authoring.ui.properties.OBSRelationSection#createControls(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.OBSRelationSection#initContentProvider()
	 */
	protected void initContentProvider() {
		contentProvider = new AdapterFactoryContentProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {
			public Object[] getElements(Object object) {
				return getSelectedTeams(element).toArray();
			}
		};

		viewer.setContentProvider(contentProvider);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.OBSRelationSection#init()
	 */
	protected void init() {
		super.init();
		element = (RoleDescriptor) element;

		
		setTabData(PropertiesResources.RoleDescriptor_Team_SectionTitle, 
				PropertiesResources.RoleDescriptor_Team_SectionDescription,
				PropertiesResources.RoleDescriptor_Team_Table1); 
	}

	
	/**
	 * @see org.eclipse.epf.authoring.ui.properties.OBSRelationSection#refresh()
	 */
	public void refresh() {
		try {
			if (getElement() instanceof RoleDescriptor) {
				super.refresh();

				element = (RoleDescriptor) getElement();
				viewer.refresh();
			}
		} catch (Exception ex) {
			logger.logError(
					"Error refreshing Role Descriptor team section ", ex); //$NON-NLS-1$
		}
	}

	private void add(Object[] items) {
		// update the model
		if (items != null) {
			for (int i = 0; i < items.length; i++) {
				TeamProfile team = (TeamProfile) items[i];
				if (element instanceof CompositeRole) {
					CompositeRole compRole = ProcessUtil
							.createCompositeRole((CompositeRole) element);
					actionMgr.doAction(IActionManager.ADD, team,
							UmaPackage.eINSTANCE.getTeamProfile_TeamRoles(),
							compRole, -1);
				} else if (element instanceof RoleDescriptor) {
					Role role = ((RoleDescriptor) element).getRole();
					if (role != null) {
						if (!isPartOfTeam(role, team)) {
							RoleDescriptor roleDesc = ProcessUtil
									.createRoleDescriptor(role);
							actionMgr.doAction(IActionManager.ADD, team,
									UmaPackage.eINSTANCE
											.getTeamProfile_TeamRoles(),
									roleDesc, -1);
						}
					} else {
						MessageFormat mf = new MessageFormat(
								PropertiesResources.Process_TeamAssignError); 
						Object[] args = { element.getName(), team.getName() };
						String message = mf.format(args);
						String title = getEditor().getTitle();
						MsgDialog dialog = AuthoringUIPlugin.getDefault()
								.getMsgDialog();
						dialog.displayError(title, message, ""); //$NON-NLS-1$
						return;
					}
				}
				actionMgr.doAction(IActionManager.ADD, team,
						UmaPackage.eINSTANCE.getTeamProfile_TeamRoles(),
						element, -1);
			}
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.OBSRelationSection#remove(java.util.ArrayList)
	 */
	protected void remove(ArrayList rmItems) {
		// update the model
		if (!rmItems.isEmpty()) {
			for (Iterator itor = rmItems.iterator(); itor.hasNext();) {
				TeamProfile team = (TeamProfile) itor.next();
				Role role = ((RoleDescriptor) element).getRole();
				actionMgr.doAction(IActionManager.REMOVE, team,
						UmaPackage.eINSTANCE.getTeamProfile_TeamRoles(),
						element, -1);

				// Get the list of roleDescriptors from team profile which
				// has same role original role descriptor
				List teamRoles = team.getTeamRoles();
				List roleDescList = new ArrayList();
				for (int i = 0; i < teamRoles.size(); i++) {
					RoleDescriptor roleDesc = (RoleDescriptor) teamRoles.get(i);

					if (roleDesc.getRole().equals(role))
					{
						roleDescList.add(roleDesc);
					}
				}

				// if roleDescripor just has one element, delete that one too.
				// since that would be the last one
				if (roleDescList.size() == 1) {
					actionMgr.doAction(IActionManager.REMOVE, team,
							UmaPackage.eINSTANCE.getTeamProfile_TeamRoles(),
							(RoleDescriptor) roleDescList.get(0), -1);
				}
			}
		}
	}
	
	
	private boolean isPartOfTeam(Role role, TeamProfile team) {
		List teamRoles = team.getTeamRoles();
		List roles = new ArrayList();
		for (Iterator itor = teamRoles.iterator(); itor.hasNext();) {
			RoleDescriptor roleDesc = (RoleDescriptor) itor.next();
			roles.add(roleDesc.getRole());
		}

		if (roles.contains(role))
			return true;

		return false;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.OBSRelationSection#openAddDialog()
	 */
	protected void openAddDialog() {
		IStructuredContentProvider teamContentProvider = new AdapterFactoryContentProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {
			public Object[] getElements(Object object) {
				List items = getTeams();
				items.removeAll(getSelectedTeams(element));
				return items.toArray();
			}
		};

		ILabelProvider teamLabelProvider = new AdapterFactoryLabelProvider(
				TngAdapterFactory.INSTANCE.getOBS_ComposedAdapterFactory());

		ListSelectionDialog dlg = new ListSelectionDialog(PlatformUI
				.getWorkbench().getActiveWorkbenchWindow().getShell(), element,
				teamContentProvider, teamLabelProvider, PropertiesResources.Process_TeamDialogMessage); 
		titleImage = Display.getCurrent().getActiveShell().getImage();
		ListSelectionDialog.setDefaultImage(titleImage);

		dlg.setTitle(PropertiesResources.Process_TeamDialogTitle); 
		dlg.setBlockOnOpen(true);
		dlg.open();
		add(dlg.getResult());
	}

	public void dispose() {
		super.dispose();

		if (contentProvider != null) {
			contentProvider.dispose();
		}
	}

	/**
	 * Get parent
	 * 
	 * @param element
	 * @return
	 * 		Parent of the element
	 */
	protected Object getParent(BreakdownElement element) {
		AdapterFactory aFactory = TngAdapterFactory.INSTANCE
				.getOBS_ComposedAdapterFactory();
		ItemProviderAdapter adapter = (ItemProviderAdapter) aFactory.adapt(
				element, ITreeItemContentProvider.class);
		Object parent = adapter.getParent(element);

		return parent;
	}

	/**
	 * Get Teams from parent
	 * 
	 * @return
	 * 			List of teams
	 */
	private List getTeams() {
		ArrayList items = new ArrayList();
		Object parent = getParent(element);
		while (parent != null) {
			if (parent instanceof Activity) {
				Activity act = (Activity) parent;
				List breakdownElements = act.getBreakdownElements();
				for (Iterator it = breakdownElements.iterator(); it.hasNext();) {
					BreakdownElement element = (BreakdownElement) it.next();
					if (element instanceof TeamProfile) {
						TeamProfile team = (TeamProfile) element;
						items.add(team);
						// add any sub teams if there
						List subTeams = team.getSubTeam();
						items.addAll(subTeams);

					}
				}
			}

			parent = getParent((BreakdownElement) parent);
		}

		return items;
	}

	/**
	 * Get selected teams for that role descriptor
	 * 
	 * @param object
	 * @return
	 * 		List of selected team 
	 */
	private List getSelectedTeams(EObject object) {
		Object input = getInput();
		
		if (input instanceof RoleDescriptorWrapperItemProvider) {
			return ProcessUtil
					.getTeamProfiles((RoleDescriptorWrapperItemProvider) input);
		} else {
			RoleDescriptor roleDesc = (RoleDescriptor) object;
			return ProcessUtil.getTeamProfiles(roleDesc, getAdapterFactory());
		}

	}
}