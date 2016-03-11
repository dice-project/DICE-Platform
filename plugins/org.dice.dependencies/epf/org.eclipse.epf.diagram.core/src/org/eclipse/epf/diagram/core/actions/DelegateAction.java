/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.diagram.core.actions;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.ui.action.AbstractActionHandler;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;

/**
 * IAction to delegate to IObjectActionDelegate. 
 * @author Shashidhar Kannoori
 */
public class DelegateAction extends AbstractActionHandler {

	private IActionDelegate delegate;
	private IWorkbenchPart part;
	
	public DelegateAction(IWorkbenchPage page) {
		super(page.getActivePart());
		this.part = page.getActivePart();
	}
	/**
	 * 
	 */
	public DelegateAction(IWorkbenchPart part, String id) {
		//super(text);
		super(part);
		this.part = part;
		createDelegate(id);
	}
	
	@Override
	public void run() {
		runWithEvent(null);
	}
	@Override
	public void runWithEvent(Event event) {
		if(delegate != null){
			refresh();
			delegate.run(this);
		}
		// Reset action and delegate actions, because selectionChanged listeners don't notify here.
		if(delegate instanceof ActionDelegate){
			((ActionDelegate)delegate).setEnabled(true);
		}
		setEnabled(true);
	}

	@Override
	protected void doRun(IProgressMonitor progressMonitor) {
		// TODO Auto-generated method stub
		
	}

	public void refresh() {
		ISelection selection = part.getSite().getSelectionProvider().getSelection();
		if(selection == null){
			selection = StructuredSelection.EMPTY;
		}
		delegate.selectionChanged(this, selection);
	}

	private void createDelegate(String id){
		if(id.equals(ActionFactory.DELETE.getId())){
			delegate = new DeleteElementActionDelegate(this,part);
		}
	}
	@Override
	public boolean isEnabled() {
		refresh();
		return ((ActionDelegate)delegate).isEnabled();
	}
	
}
