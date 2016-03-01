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
package org.eclipse.epf.authoring.ui.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.action.CommandActionHandler;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.editors.ProcessEditor;
import org.eclipse.epf.library.edit.process.command.UpdateSuppressionFromBaseCommand;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Update suppression information from base elements
 * 
 * @author Phong Nguyen Le
 * @since  1.0
 */
public class UpdateSuppressionFromBaseAction extends
		CommandActionHandler implements IWorkbenchPartAction, IModifyingAction {

	private ProcessEditor editor;

	/**
	 * Creates an instance
	 * @param text
	 */
	public UpdateSuppressionFromBaseAction(String text) {
		super(null, text);
	}
	
	/**
	 * Creates an instance
	 *
	 */
	public UpdateSuppressionFromBaseAction() {
		this(AuthoringUIResources.ProcessEditor_updateSuppressionFromBaseAction_label); 
	}
	
	/**
	 * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public boolean updateSelection(IStructuredSelection selection) {
		ArrayList<Object> list = new ArrayList<Object>();
		for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if(Suppression.isValid(element)) {
				list.add(element);
			}
		}
		if(list.isEmpty()) {
			return false;
		}
		if(list.size() == 1 && 
				ProcessUtil.isRolledUpDescriptor(list.get(0), ((AdapterFactoryEditingDomain)domain).getAdapterFactory())) {
			return false;
		}
		return super.updateSelection(new StructuredSelection(list));
	}
	
	/**
	 * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#createCommand(java.util.Collection)
	 */
	public Command createCommand(Collection<?> selection) {
		return new UpdateSuppressionFromBaseCommand(getText(), selection, editor.getAdapterFactory(), editor.getSuppression()) {
			/* (non-Javadoc)
			 * @see org.eclipse.epf.library.edit.process.command.UpdateSuppressionFromBaseCommand#execute()
			 */
			public void execute() {
				super.execute();
				
				if(!getAffectedObjects().isEmpty()) {
					editor.refreshAllProcessEditors();
				}
			}
			
			/* (non-Javadoc)
			 * @see org.eclipse.epf.library.edit.process.command.UpdateSuppressionFromBaseCommand#undo()
			 */
			public void undo() {
				super.undo();
				
				if(!getAffectedObjects().isEmpty()) {
					editor.refreshAllProcessEditors();
				}
			}
		};
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.actions.IWorkbenchPartAction#setActiveWorkbenchPart(org.eclipse.ui.IWorkbenchPart)
	 */
	public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
		if(workbenchPart instanceof IEditingDomainProvider) {
			domain = ((IEditingDomainProvider)workbenchPart).getEditingDomain();
		}
		if(workbenchPart instanceof ProcessEditor) {
			editor = (ProcessEditor) workbenchPart;
		}		
		else if(workbenchPart == null) {
			editor = null;
		}
	}	
	
	private void superRun() {
		super.run();
	}
	
	/**
	 * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#run()
	 */
	public void run() {
		BusyIndicator.showWhile(editor.getEditorSite()
				.getShell().getDisplay(), new Runnable() {

					public void run() {
						superRun();
					}
			
		});
	}
}
