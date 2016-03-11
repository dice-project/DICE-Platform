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
/**
 * 
 */
package org.eclipse.epf.diagram.core.commands;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.epf.diagram.core.DiagramCorePlugin;
import org.eclipse.epf.diagram.core.services.DiagramHelper;
import org.eclipse.epf.diagram.core.services.DiagramManager;
import org.eclipse.epf.diagram.core.services.DiagramService;
import org.eclipse.epf.library.edit.command.DeleteMethodElementCommand;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.persistence.FileManager;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.gmf.runtime.common.ui.services.editor.EditorService;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.ui.PlatformUI;

/**
 * Command to delete the diagram/diagram element of given Activity/CP, this command is performed
 * through nested commands in {@link DeleteMethodElementCommand}.  
 * @author Shashidhar Kannoori
 */
public class DeleteDiagramCommand extends AbstractCommand implements IResourceAwareCommand {

	private Collection elementsToRemove;
	private Process targetProcess;
	private DiagramManager dMgr = null;

	/**
	 * 
	 */
	public DeleteDiagramCommand(Collection elementsToRemove, Process targetProcess) {
		this.elementsToRemove = elementsToRemove;
		this.targetProcess = targetProcess;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		DiagramService diagramSvc = new DiagramService();
		try {
			for (Iterator iter = elementsToRemove.iterator(); iter.hasNext();) {
				MethodElement element = (MethodElement) iter.next();
				if (element instanceof Activity) {
					Collection<Diagram> diagrams = diagramSvc
							.getDiagrams((Activity) element);
					for (Iterator iterator = diagrams.iterator(); iterator
							.hasNext();) {
						Diagram diagram = (Diagram) iterator.next();
						if (diagram != null) {
							// Before deleting close opened diagram editors.
							closeDiagramEditor(diagram);
							// Delete Diagram.
							DiagramHelper.deleteDiagram(diagram, true);
						}
					}
				}
			}
		} catch (Exception e) {
			DiagramCorePlugin.getDefault().getLogger().logError(e);
		}
		finally {
			diagramSvc.dispose();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {
		execute();
	}
	
	private DiagramManager getDiagramManager() {
		if(dMgr == null) {
			dMgr = DiagramManager.getInstance(targetProcess, this);
		}
		return dMgr;
	}

	public Collection getModifiedResources() {
		DiagramManager dMgr = getDiagramManager();
		HashSet<Object> modifiedResources = new HashSet<Object>();
		boolean fileExist = true;
		try {
			if (dMgr != null && dMgr.getResource() != null
					&& !dMgr.getResource().getContents().isEmpty()) {
				File file = new File(FileManager.toFileString(dMgr
						.getResource().getURI()));
				if (!file.exists())
					fileExist = false;
				if (fileExist)
					modifiedResources.add(dMgr.getResource());
			}
		} catch (Exception e) {
			DiagramCorePlugin.getDefault().getLogger().logError(e);
		}
		return modifiedResources;
	}
	
	@Override
	public boolean canExecute() {
		return targetProcess != null;
	}
	
	@Override
	public void dispose() {
		if(dMgr != null){
			dMgr.removeConsumer(this);
		}
		super.dispose();
	}
	
	private void closeDiagramEditor(Diagram diagram){
		List editors = EditorService.getInstance().getRegisteredEditorParts();
		Iterator it = editors.iterator();
		while (it.hasNext()) {
			Object obj = it.next();

			if (obj instanceof DiagramEditor) {
				DiagramEditor diagramEditor = ((DiagramEditor) obj);
				if (diagramEditor.getDiagram().equals(diagram)) {
					//it's already open, just close it.
					PlatformUI.getWorkbench().getActiveWorkbenchWindow()
						.getActivePage().activate(diagramEditor);
					PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().closeEditor(diagramEditor, false);
				}
			}
		}
	}
	
}
