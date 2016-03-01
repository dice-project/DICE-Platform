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

import java.util.List;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IUserInteractionHandler;
import org.eclipse.epf.library.edit.util.AdapterFactoryItemLabelProvider;
import org.eclipse.epf.library.edit.util.ExtensionManager;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.osgi.util.NLS;

/**
 * UI Dialog class which will give user to select references to be deleted
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class ReferenceSelection {

	public static Object[] getReferences(List ref, Descriptor desc) {
		return getReferences(ref, NLS.bind(LibraryEditResources.ui_ref_delete,
				desc.getName()));
	}

	/**
	 * Shows dialog box for all references to be deleted for the given
	 * descriptor. These references are not being used anywhere else.
	 * 
	 * @param ref
	 * @return
	 */
	public static Object[] getReferences(List ref) {

		String message = LibraryEditResources.ui_ref_delete2;
		try {
			return getReferences(ref, message);
		} catch (OperationCanceledException e) {
			return null;
		}
	}

	/**
	 * It shows selection dialog box with list of reference (<code>ref</code>)
	 * for user to select.
	 * 
	 * @param ref
	 * @return
	 * @exception OperationCanceledException
	 *                if user canceled
	 */
	public static Object[] getReferences(List ref, String msg)
			throws OperationCanceledException {
		// IStructuredContentProvider contentProvider = new
		// AdapterFactoryContentProvider(
		// TngAdapterFactory.INSTANCE
		// .getNavigatorView_ComposedAdapterFactory()) {
		// public Object[] getElements(Object object) {
		// return ((List) object).toArray();
		// }
		// };
		// ILabelProvider labelProvider = new AdapterFactoryLabelProvider(
		// TngAdapterFactory.INSTANCE
		// .getNavigatorView_ComposedAdapterFactory());
		//
		// try {
		// ProcessListSelectionDialog dlg = new
		// ProcessListSelectionDialog(PlatformUI
		// .getWorkbench().getActiveWorkbenchWindow().getShell(), ref,
		// contentProvider, labelProvider, msg);
		//
		// dlg.setTitle(LibraryEditResources.ui_references); //$NON-NLS-1$
		// dlg.setBlockOnOpen(true);
		// if (dlg.open() == Dialog.CANCEL) {
		// throw new OperationCanceledException();
		// }
		// Object objs[] = dlg.getResult();
		// return objs;
		// } finally {
		// contentProvider.dispose();
		// labelProvider.dispose();
		// }

		IUserInteractionHandler uiHandler = ExtensionManager
				.getDefaultUserInteractionHandler();
		if (uiHandler != null) {
			IItemLabelProvider labelProvider = new AdapterFactoryItemLabelProvider(
					TngAdapterFactory.INSTANCE
							.getNavigatorView_ComposedAdapterFactory());
			String title = LibraryEditResources.ui_references;
			List selected = uiHandler.select(ref, labelProvider, true, ref,
					title, msg);
			if (selected == null) {
				throw new OperationCanceledException();
			}
			return selected.toArray();
		}

		// no user interaction handler available
		// return the entire reference list
		//
		return ref.toArray();
	}
}
