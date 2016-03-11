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
package org.eclipse.epf.authoring.ui.actions;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.ui.action.CommandActionHandler;
import org.eclipse.epf.authoring.ui.editors.ProcessEditor;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Moves breakdown element in a process tree.
 * 
 * @author Phong Nguyen Le
 * @since  1.2
 */
public abstract class AbstractMoveAction extends
		CommandActionHandler implements IWorkbenchPartAction, IModifyingAction {

	protected ProcessEditor editor;

	/**
	 * Creates an instance
	 * @param text
	 */
	public AbstractMoveAction(String text) {
		super(null, text);
	}
	
	protected boolean accept(IStructuredSelection selection) {
		// support move only one activity for now
		//
		if(selection.size() == 1) {
			Object e = selection.getFirstElement();
			ITreeItemContentProvider ip = (ITreeItemContentProvider) getItemProvider(e);
			return e instanceof BreakdownElement && ip != null && ip.getParent(e) instanceof Activity;
		}
		
		return false;
	}
		
	/**
	 * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public boolean updateSelection(IStructuredSelection selection) {
		// support move only one activity for now
		//
		if(accept(selection)) {
			return super.updateSelection(selection);
		}
		return false;
	}
	
	protected IBSItemProvider getItemProvider(Object element) {
		if(domain instanceof AdapterFactoryEditingDomain) {
			AdapterFactory adapterFactory = ((AdapterFactoryEditingDomain)domain).getAdapterFactory();
			Object ip = adapterFactory.adapt(element, ITreeItemContentProvider.class);
			if(ip instanceof IBSItemProvider) {
				return (IBSItemProvider) ip;
			}
		}
		return null;
	}
	
	/**
	 * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#createCommand(java.util.Collection)
	 */
	@SuppressWarnings("unchecked")
	public Command createCommand(Collection<?> selection) {
		Object element = selection.iterator().next();
		IBSItemProvider ip = getItemProvider(element);
		if(ip != null) {
			Object parent = ((ITreeItemContentProvider)ip).getParent(element);
			if ((parent != null) && (parent instanceof Activity)) {
				return createMoveCommand((Activity)parent, element, ip.getEClasses());
			}
		}
		return UnexecutableCommand.INSTANCE;
		
	}
	
	protected abstract Command createMoveCommand(Activity act, Object element, Collection<EClass> eClasses);

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
