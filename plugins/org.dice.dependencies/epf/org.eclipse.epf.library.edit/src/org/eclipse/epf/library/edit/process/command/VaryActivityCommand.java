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
package org.eclipse.epf.library.edit.process.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.library.edit.ICommandListener;
import org.eclipse.epf.library.edit.Providers;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Process;


/**
 * @author Phong Nguyen Le - Oct 10, 2005
 * @since 1.0
 */
public abstract class VaryActivityCommand extends AbstractCommand implements
		IResourceAwareCommand {

	protected BreakdownElementWrapperItemProvider wrapper;

	private Process proc;

	protected List createdActivities;
	
	public Activity superActivity;

	public VaryActivityCommand(BreakdownElementWrapperItemProvider wrapper) {
		this.wrapper = wrapper;
		proc = (Process) wrapper.getTopItem();
	}

	public BreakdownElementWrapperItemProvider getWrapper() {
		return wrapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.command.IResourceAwareCommand#getModifiedResources()
	 */
	public Collection getModifiedResources() {
		if (proc.eResource() != null) {
			return Collections.singletonList(proc.eResource());
		}
		return Collections.EMPTY_LIST;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.AbstractCommand#prepare()
	 */
	protected boolean prepare() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		if (createdActivities == null) {
			createdActivities = new ArrayList();
		} else {
			createdActivities.clear();
		}

		doVary();

		List listeners = Providers
				.getCommandListeners(VaryActivityCommand.class);
		if (listeners != null) {
			for (Iterator iter = listeners.iterator(); iter.hasNext();) {
				ICommandListener listener = (ICommandListener) iter.next();
				listener.notifyExecuted(this);
			}
		}
	}

	protected abstract void doVary();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {
		// find the new wrapper
		//
		Object owner = wrapper.getOwner();
		ITreeItemContentProvider ip = (ITreeItemContentProvider) wrapper.getAdapterFactory().adapt(owner, ITreeItemContentProvider.class);
		for (Iterator iter = ip.getChildren(owner).iterator(); iter.hasNext();) {
			Object child = iter.next();		
			if(child instanceof BreakdownElementWrapperItemProvider &&
					((BreakdownElementWrapperItemProvider)child).getValue() == wrapper.getValue()) {
				wrapper = (BreakdownElementWrapperItemProvider) child;
				break;
			}
		}
		
		execute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.AbstractCommand#undo()
	 */
	public void undo() {
		List listeners = Providers
				.getCommandListeners(VaryActivityCommand.class);
		if (listeners != null) {
			for (Iterator iter = listeners.iterator(); iter.hasNext();) {
				ICommandListener listener = (ICommandListener) iter.next();
				listener.preUndo(this);
			}
		}

		if (!createdActivities.isEmpty()) {
			restoreOrder();
			Activity act = (Activity) createdActivities.get(0);
			superActivity = act.getSuperActivities();
			act.setSuperActivities(null);
			createdActivities.clear();
		}
		
		if (listeners != null) {
			for (Iterator iter = listeners.iterator(); iter.hasNext();) {
				ICommandListener listener = (ICommandListener) iter.next();
				listener.postUndo(this);
			}
		}
	}

	/**
	 * Restores the order of the siblings of the wrapper
	 */
	protected void restoreOrder() {
		Activity variedAct = (Activity) createdActivities.get(createdActivities.size() - 1);
		BreakdownElement be = (BreakdownElement) TngUtil.unwrap(wrapper);
		BreakdownElement prev = variedAct.getPresentedBefore();
		if(prev != null && proc == TngUtil.getOwningProcess(prev)) {
			prev.setPresentedAfter(be);
		}
		BreakdownElement next = variedAct.getPresentedAfter();
		if(next != null && proc == TngUtil.getOwningProcess(next)) {
			next.setPresentedBefore(be);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.AbstractCommand#getResult()
	 */
	public Collection getResult() {
		return createdActivities;
	}
}
