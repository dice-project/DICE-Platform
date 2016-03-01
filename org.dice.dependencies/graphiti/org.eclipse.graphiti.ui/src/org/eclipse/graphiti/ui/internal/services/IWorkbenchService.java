/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.services;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.platform.IDiagramEditor;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;

/**
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public interface IWorkbenchService {

	/**
	 * Method returns the current {@link StatusLineManager}. If no
	 * StatusLineManager is available, a new StatusLineManager will be created
	 * and returned. In most cases this will not have any effect and will not be
	 * able to display status info, but it makes null pointer checks when
	 * calling the method obsolete
	 * 
	 * @return the status line manager of the active part or a new one if there
	 *         is no such part
	 */
	public IStatusLineManager getActiveStatusLineManager();

	/**
	 * @return the active workbench window, or the first one if there is no
	 *         active window
	 */
	public IWorkbenchWindow getActiveOrFirstWorkbenchWindow();

	/**
	 * Opens the given diagram in the diagram editor.
	 * 
	 * @param diagram
	 *            which should be opened
	 * @param domain
	 * @return the editor instance
	 */
	public IDiagramEditor openDiagramEditor(Diagram diagram, TransactionalEditingDomain domain, boolean disposeEditingDomain);

	/**
	 * Opens the given diagram in the diagram editor.
	 * 
	 * @param diagram
	 *            which should be opened
	 * @param domain
	 * @param providerId
	 *            the unique provider id of a diagram type provider which will
	 *            be used by the editor.
	 * @return the editor instance
	 */
	public IDiagramEditor openDiagramEditor(Diagram diagram, TransactionalEditingDomain domain, String providerId,
			boolean disposeEditingDomain);

	/**
	 * Returns the shell of the active workbench window.
	 * 
	 * @return the shell
	 */
	public Shell getShell();

}