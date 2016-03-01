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

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.WorkBreakdownElement;


/**
 * Command to move down element in breakdown structure
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class MoveDownCommand extends AbstractCommand implements
		IResourceAwareCommand {
	private Activity activity;

	private Collection modifiedResources;

	private int elementLocation = 0;

	private int transferLocation = 0;

	private Object elementObj;

	private Collection eClasses;
	
	private boolean adjacent = false;
	
	private Map<WorkBreakdownElement, WorkBreakdownElement> globalPresentedAfterMap = new HashMap<WorkBreakdownElement, WorkBreakdownElement>();

	/**
	 * 
	 */
	public MoveDownCommand(Activity activity, Object elementObj,
			Collection eClasses) {
		super();
		this.activity = activity;
		this.elementObj = elementObj;
		this.eClasses = eClasses;

		this.modifiedResources = new HashSet();
		if (activity.eResource() != null) {
			modifiedResources.add(activity.eResource());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		if (elementObj instanceof WorkBreakdownElement) {
			boolean completelyDone = MoveUpCommand.handleWbeGlobalMove(activity,
					(WorkBreakdownElement) elementObj, false, globalPresentedAfterMap);
			if (completelyDone) {
				return;
			}
		}
		
		List allElements = activity.getBreakdownElements();

		for (int i = 0; i < allElements.size(); i++) {
			Object obj = allElements.get(i);
			if (obj.equals(elementObj)) {
				elementLocation = i;
				break;
			}
		}

		if (allElements.size() > 0) {
			transferLocation = allElements.size() - 1;
		}
		for (int i = elementLocation + 1; i < allElements.size(); i++) {
			Object obj = allElements.get(i);
			if (TngUtil.isEClassInstanceOf(eClasses, obj)) {
				transferLocation = i;
				break;
			}
		}

		BreakdownElement next =  (BreakdownElement) allElements.get(transferLocation);
		BreakdownElement e = (BreakdownElement) elementObj;
		if(e.getPresentedAfter() == next) {
			adjacent = true;
		}

		redo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {
		if(adjacent) {
			// swap "prensented-after" element
			//
			BreakdownElement next =  (BreakdownElement) activity.getBreakdownElements().get(transferLocation);
			BreakdownElement e = (BreakdownElement) elementObj;
			e.setPresentedAfter(next.getPresentedAfter());
			next.setPresentedAfter(e);
		}
		((EList) activity.getBreakdownElements()).move(transferLocation,
				elementLocation);
	
	}

	public void undo() {
		((EList) activity.getBreakdownElements()).move(elementLocation,
				transferLocation);
		if(adjacent) {
			// restore "prensented-after" element
			//
			BreakdownElement next =  (BreakdownElement) activity.getBreakdownElements().get(transferLocation);
			BreakdownElement e = (BreakdownElement) elementObj;
			next.setPresentedAfter(e.getPresentedAfter());
			e.setPresentedAfter(next);
		}
	}

	protected boolean prepare() {
		return true;
	}

	public Collection getModifiedResources() {
		return modifiedResources;
	}
}
