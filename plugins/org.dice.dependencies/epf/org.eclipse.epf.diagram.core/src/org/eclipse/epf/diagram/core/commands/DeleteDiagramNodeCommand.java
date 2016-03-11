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
//------------------------------------------------------------------------------
//Copyright (c) 2005, 2007 IBM Corporation and others.
//All rights reserved. This program and the accompanying materials
//are made available under the terms of the Eclipse Public License v1.0
//which accompanies this distribution, and is available at
//http://www.eclipse.org/legal/epl-v10.html
//
//Contributors:
//IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.diagram.core.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epf.diagram.core.DiagramCorePlugin;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.core.services.DiagramService;
import org.eclipse.epf.diagram.model.util.TxUtil;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.uma.Activity;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

/**
 * Command to delete diagram nodes of the deleted breakdown elements.
 * 
 * @author Phong Nguyen Le
 * @since 1.2
 */
public class DeleteDiagramNodeCommand extends AbstractCommand implements IResourceAwareCommand {
	
	private Activity activity;
	private Collection<?> breakdownElements;
	private DiagramService diagramSvc;

	public DeleteDiagramNodeCommand(Activity activity, Collection<?> breakdownElements) {
		this.activity = activity;
		this.breakdownElements = breakdownElements;
	}

	public Collection<Resource> getModifiedResources() {
		if(diagramSvc != null) {
			org.eclipse.epf.diagram.core.services.DiagramManager mgr = diagramSvc.getDiagramManager(activity);
			if(mgr != null) {
				try {
					Resource resource = mgr.getResource();
					if(resource != null) {
						return Collections.singleton(resource);
					}
				} catch (CoreException e) {
					DiagramCorePlugin.getDefault().getLogger().logError(e);
				}
			}
		}
		return Collections.EMPTY_LIST;
	}

	public void execute() {
		Collection<Diagram> diagrams = diagramSvc.getDiagrams(activity);
		Collection<View> viewsToDelete = new ArrayList<View>();
		for (Diagram diagram : diagrams) {
			AbstractTreeIterator<View> iterator = new AbstractTreeIterator<View>(diagram, false) {

				private static final long serialVersionUID = 1L;

				@Override
				protected Iterator<? extends View> getChildren(Object object) {
					if(object instanceof View) {
						return ((View)object).getChildren().iterator();
					}
					return Collections.EMPTY_LIST.iterator();
				}

			};
			while(iterator.hasNext()) {
				View view = iterator.next();
				if(breakdownElements.contains(BridgeHelper.getMethodElement(view, activity))) {
					viewsToDelete.add(view);
				}
			}
		}
		if(!viewsToDelete.isEmpty()) {
			for (View view : viewsToDelete) {
				final EObject umlElement = view.getElement();			
				ViewUtil.destroy(view);
				// delete UML node as well if it is not deleted yet
				//
				if(umlElement != null && umlElement.eContainer() != null) {
					try {
						TxUtil.runInTransaction(umlElement, new Runnable() {

							public void run() {
								EcoreUtil.delete(umlElement);
							}
							
						});
					} catch (ExecutionException e) {
						DiagramCorePlugin.getDefault().getLogger().logError(e);
					}

				}
			}
		}
	}

	public void redo() {
		execute();
	}
	
	@Override
	public boolean canUndo() {
		return false;
	}
	
	@Override
	protected boolean prepare() {		
		diagramSvc = new DiagramService();
		Collection<Diagram> diagrams = diagramSvc.getDiagrams(activity);
		return !diagrams.isEmpty();
	}

	@Override
	public void dispose() {
		activity = null;
		breakdownElements = null;
		diagramSvc.dispose();
	}
}
