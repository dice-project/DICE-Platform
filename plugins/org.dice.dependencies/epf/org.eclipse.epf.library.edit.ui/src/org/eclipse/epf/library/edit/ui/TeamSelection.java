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
package org.eclipse.epf.library.edit.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IUserInteractionHandler;
import org.eclipse.epf.library.edit.util.AdapterFactoryItemLabelProvider;
import org.eclipse.epf.library.edit.util.ExtensionManager;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Shell;


/**
 * UI Dialog class which will ask user to assign a role to team automatically.
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class TeamSelection {

	/**
	 * It shows dialog box all teams. Returns team user selected
	 * 
	 * @param element
	 * @return
	 */
	public static TeamProfile getSelectedTeam(List teamList, Role role, Shell shell) {
//		ILabelProvider labelProvider = new AdapterFactoryLabelProvider(
//				TngAdapterFactory.INSTANCE.getOBS_ComposedAdapterFactory()) {
//			public String getText(Object obj) {
//				if (obj instanceof TeamProfile) {
//					return ((TeamProfile) obj).getName();
//				}
//				return ""; //$NON-NLS-1$
//			}
//		};
//		if(shell == null) {
//			try {
//				shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(); //MsgBox.getDefaultShell();
//			}
//			catch(Exception e) {
//				
//			}
//		}
//		ElementListSelectionDialog dlg = new ElementListSelectionDialog(shell, labelProvider);
//
//		dlg.setBlockOnOpen(true);
//		dlg.setElements(teamList.toArray());
//		dlg.setMultipleSelection(false);
//		dlg
//				.setMessage(NLS.bind(LibraryEditResources.selectTeamsDialog_text, (new Object[] { role.getName() }))); 
//		dlg.setTitle(LibraryEditResources.selectTeamsDialog_title); //$NON-NLS-1$
//		dlg.setFilter(null);
//		dlg.open();
//		Object obj = dlg.getFirstResult();
//		// dispose
//		labelProvider.dispose();
//		return (TeamProfile) obj;
		
		final IUserInteractionHandler uiHandler = ExtensionManager
				.getDefaultUserInteractionHandler();
		if (uiHandler != null) {
			final IItemLabelProvider labelProvider = new AdapterFactoryItemLabelProvider(
					TngAdapterFactory.INSTANCE.getOBS_ComposedAdapterFactory());
			final String title = LibraryEditResources.selectTeamsDialog_title;
			final String msg = NLS.bind(LibraryEditResources.selectTeamsDialog_text, (new Object[] { role.getName() }));
			final List finalSelected = new ArrayList();
			final List finalTeams = new ArrayList();
			finalTeams.addAll(teamList);

			UserInteractionHelper.getUIHelper().runSafely(new Runnable() {
				public void run() {
					List selected = uiHandler.select(finalTeams, labelProvider, false,
							finalTeams, title, msg);

					if (selected != null)
						finalSelected.addAll(selected);
				}
			}, true);

			if (finalSelected == null) {
				throw new OperationCanceledException();
			}
			if(finalSelected.isEmpty()) {
				return null;
			}
			return (TeamProfile) finalSelected.get(0);
		}

		// no user interaction handler available
		// return null
		return null;
	}

}
