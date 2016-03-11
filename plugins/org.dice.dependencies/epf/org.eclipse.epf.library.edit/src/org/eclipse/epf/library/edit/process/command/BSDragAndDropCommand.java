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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.epf.library.edit.command.ResourceAwareCompoundCommand;
import org.eclipse.epf.library.edit.command.ResourceAwareDragAndDropCommand;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.ProcessScopeUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.DependencyChecker;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.util.Scope;


/**
 * Abstract drag and drop command for process authoring
 * 
 * @author Phong Nguyen Le
 * @author Shilpa Toraskar
 * @since 1.0
 */
public abstract class BSDragAndDropCommand extends ResourceAwareDragAndDropCommand {

	/**
	 * @param domain
	 * @param owner
	 * @param location
	 * @param operations
	 * @param operation
	 * @param collection
	 */
	public BSDragAndDropCommand(EditingDomain domain, Object owner,
			float location, int operations, int operation, Collection collection) {
		super(domain, owner, location, operations, operation, collection);
	}

	protected boolean prepareDropCopyOn() {
		return prepareDropLinkOn();
	}

	protected boolean prepareDropLinkOn() {
		dragCommand = IdentityCommand.INSTANCE;
		dropCommand = UnexecutableCommand.INSTANCE;
		
		Scope scope = null;
		if (owner instanceof Activity) {
			Process proc = ProcessUtil.getProcess((Activity) owner); 
			scope = ProcessScopeUtil.getInstance().getScope(proc);
		}
		
		ArrayList list = new ArrayList();
		ArrayList actList = new ArrayList();
		for (Iterator iter = collection.iterator(); iter.hasNext();) {
			Object obj = iter.next();
			Object e = TngUtil.unwrap(obj);
			
			if (scope != null && e instanceof MethodElement) {
				ProcessScopeUtil.getInstance().addReferenceToScope(
						scope, (MethodElement) e, new HashSet<MethodElement>());
			}
			
			if (TngUtil.isLocked((EObject) owner))
				return false;
			if (accept(e)) {
				list.add(e);
			}
			// else if(obj instanceof Process) {
			// // disallow drop a process as an activity
			// }
			else if (e instanceof Activity && owner instanceof Activity) {
				Activity target = (Activity) owner;
				if (DependencyChecker.newCheckAct) {
					if (! DependencyChecker.checkCircularForMovingVariabilityElement
							(target, Collections.singletonList(e), true)) {
						return false;
					}					
				} else if (!DependencyChecker.checkCircularDependency((Activity) e, target).isOK()){
					return false;
				}
				
//				Process srcProc = TngUtil.getOwningProcess((BreakdownElement) obj);
//				Process targetProc = TngUtil.getOwningProcess(target);
//
//				if (!(srcProc instanceof DeliveryProcess && targetProc instanceof CapabilityPattern)) {
//					actList.add(obj);
//				}
				
				// no more check to prevent applying an activity of a delivery process onto a capability patter
				// since introduction of deep-copy. Deep copy can always be applied regardless of source and target
				// processes.
				//
				actList.add(e);
			}
		}

		boolean result;
		if (list.isEmpty() && actList.isEmpty()) {
			result = false;
		} else {
			CompoundCommand cmd = new ResourceAwareCompoundCommand();
			if (!list.isEmpty()) {
				cmd.append(createDropCommand(owner, list));
			}
			if (!actList.isEmpty()) {
				if (owner instanceof Activity)
					cmd.append(new ActivityDropCommand((Activity) owner, actList,
						getTargetViewer(), ((AdapterFactoryEditingDomain)domain).getAdapterFactory()));
			}
			dropCommand = cmd;
			result = dropCommand.canExecute();
		}
		return result;
	}

	protected Object getTargetViewer() {
		if (domain instanceof AdapterFactoryEditingDomain) {
			Process proc = TngUtil.getOwningProcess((BreakdownElement) owner);
			AdapterFactory adapterFactory = ((AdapterFactoryEditingDomain) domain)
					.getAdapterFactory();
			return UserInteractionHelper.getUIHelper().getViewer(adapterFactory, proc);
		}
		return null;
	}

	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.command.DragAndDropCommand#getAffectedObjects()
	 */
	public Collection getAffectedObjects() {
		return dropCommand.getAffectedObjects();		
	}

	protected abstract boolean accept(Object obj);

	protected abstract Command createDropCommand(Object owner, List dropSrc);

}
