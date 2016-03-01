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
package org.eclipse.graphiti.ui.internal.services.impl;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.internal.util.T;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.platform.IDiagramEditor;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.graphiti.ui.internal.GraphitiUIPlugin;
import org.eclipse.graphiti.ui.internal.services.IWorkbenchService;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorActionBarContributor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.EditorActionBarContributor;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class WorkbenchService implements IWorkbenchService {


	public IStatusLineManager getActiveStatusLineManager() {
		final IWorkbenchPart activePart = getActiveOrFirstWorkbenchWindow().getActivePage().getActivePart();
		if (activePart instanceof IViewPart) {
			final IViewPart viewPart = (IViewPart) activePart;
			return viewPart.getViewSite().getActionBars().getStatusLineManager();
		} else if (activePart instanceof IEditorPart) {
			final IEditorPart editorPart = (IEditorPart) activePart;
			final IEditorActionBarContributor contributor = editorPart.getEditorSite().getActionBarContributor();
			if (contributor instanceof EditorActionBarContributor) {
				final EditorActionBarContributor editorContributor = (EditorActionBarContributor) contributor;
				return editorContributor.getActionBars().getStatusLineManager();
			}
		}
		// this is a useless dummy but avoids nasty null pointer checks when
		// calling the method
		return new StatusLineManager();
	}


	public IWorkbenchWindow getActiveOrFirstWorkbenchWindow() {
		IWorkbenchWindow result = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (result == null) {
			final IWorkbenchWindow[] windows = PlatformUI.getWorkbench().getWorkbenchWindows();
			if (windows != null && windows.length > 0) {
				result = windows[0];
			}
		}
		return result;
	}

	/**
	 * Opens the given diagram in the diagram editor.
	 * 
	 * @param diagram
	 *            which should be opened
	 * @param domain
	 * @return the editor instance
	 */
	public IDiagramEditor openDiagramEditor(Diagram diagram, TransactionalEditingDomain domain, boolean disposeEditingDomain) {
		IDiagramEditor ret = null;

		String providerId = GraphitiUi.getExtensionManager().getDiagramTypeProviderId(diagram.getDiagramTypeId());
		ret = openDiagramEditor(diagram, domain, providerId, disposeEditingDomain);

		return ret;
	}

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
			boolean disposeEditingDomain) {
		IDiagramEditor ret = null;
		DiagramEditorInput diagramEditorInput = DiagramEditorInput.createEditorInput(diagram, domain, providerId, disposeEditingDomain);
		IWorkbenchPage workbenchPage = getActivePage();
		try {
			IEditorPart editorPart = IDE.openEditor(workbenchPage, diagramEditorInput, DiagramEditor.DIAGRAM_EDITOR_ID);
			if (editorPart instanceof IDiagramEditor) {
				ret = (IDiagramEditor) editorPart;
			}
		} catch (PartInitException e) {
			// $JL-EXC$
			T.racer().error(e.toString());
		}

		return ret;
	}

	/**
	 * Returns the currently active WorkbenchPage.
	 * 
	 * @return The currently active WorkbenchPage.
	 */
	private static IWorkbenchPage getActivePage() {
		IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (workbenchWindow != null)
			return workbenchWindow.getActivePage();
		return null;
	}


	@Override
	public Shell getShell() {
		return GraphitiUIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell();
	}
}
