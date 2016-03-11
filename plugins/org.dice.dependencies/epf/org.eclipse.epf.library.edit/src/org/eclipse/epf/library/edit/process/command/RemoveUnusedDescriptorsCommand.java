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
import java.util.HashSet;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.epf.uma.util.UmaUtil;


/**
 * @author Phong Nguyen Le - Nov 30, 2005
 * @since 1.0
 */
public class RemoveUnusedDescriptorsCommand extends AbstractCommand implements
		IResourceAwareCommand {

	public static final Object[] TASK_DESCRIPTOR__RELATIONSHIPS = {
		UmaPackage.eINSTANCE.getTaskDescriptor_AdditionallyPerformedBy(),		
		UmaPackage.eINSTANCE.getTaskDescriptor_AssistedBy(),
		UmaPackage.eINSTANCE.getTaskDescriptor_PerformedPrimarilyBy(),
		UmaPackage.eINSTANCE.getTaskDescriptor_MandatoryInput(),
		UmaPackage.eINSTANCE.getTaskDescriptor_ExternalInput(),
		UmaPackage.eINSTANCE.getTaskDescriptor_OptionalInput(),
		UmaPackage.eINSTANCE.getTaskDescriptor_Output()
	};
	
	public static final Object[] ROLE_DESCRIPTOR__RELATIONSHIPS = {
		AssociationHelper.RoleDescriptor_AssistsIn_TaskDescriptors,
		AssociationHelper.RoleDescriptor_AdditionalTaskDescriptors,
		UmaPackage.eINSTANCE.getRoleDescriptor_ResponsibleFor()
	};
	
	public static final Object[] WORK_PRODUCT_DESCRIPTOR__RELATIONSHIPS = {
		UmaPackage.eINSTANCE.getWorkProductDescriptor_ImpactedBy(),
		UmaPackage.eINSTANCE.getWorkProductDescriptor_Impacts(),
		AssociationHelper.WorkProductDescriptor_MandatoryInputTo_TaskDescriptors,
		AssociationHelper.WorkProductDescriptor_ExternalInputTo_TaskDescriptors,
		AssociationHelper.WorkProductDescriptor_OptionalInputTo_TaskDescriptors,
		AssociationHelper.WorkProductDescriptor_OutputFrom_TaskDescriptors,
		AssociationHelper.WorkProductDescriptor_ResponsibleRoleDescriptors
	};
	
	private HashSet removedDescriptors;
	protected boolean aborted; 

	/**
	 * 
	 */
	public RemoveUnusedDescriptorsCommand() {
		super();
	}

	/**
	 * @param label
	 */
	public RemoveUnusedDescriptorsCommand(String label) {
		super(label);
	}

	/**
	 * @param label
	 * @param description
	 */
	public RemoveUnusedDescriptorsCommand(String label, String description) {
		super(label, description);
	}
	
	public boolean isAborted() {
		return aborted;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.command.IResourceAwareCommand#getModifiedResources()
	 */
	public Collection getModifiedResources() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {
	}

	private Object getParent(Descriptor obj) {
		// AdapterFactory factory = null;
		// if (obj instanceof RoleDescriptor) {
		// factory = TngAdapterFactory.INSTANCE
		// .getOBS_ComposedAdapterFactory();
		// } else if (obj instanceof TaskDescriptor) {
		// factory = TngAdapterFactory.INSTANCE
		// .getWBS_ComposedAdapterFactory();
		// } else if (obj instanceof WorkProductDescriptor) {
		// factory = TngAdapterFactory.INSTANCE
		// .getPBS_ComposedAdapterFactory();
		// }
		//
		// ItemProviderAdapter provider = (ItemProviderAdapter)
		// factory.adapt(obj,
		// ITreeItemContentProvider.class);
		// Object act = provider.getParent(obj);
		//
		// return act;

		return UmaUtil.getParentActivity(obj);
	}

	protected void delete(Object[] refTobeDeleted) {
		if (refTobeDeleted != null) {
			for (int i = 0; i < refTobeDeleted.length; i++) {
				Descriptor obj = (Descriptor) refTobeDeleted[i];
				// get parent and remove ref object
				Object act = getParent(obj);
				if (act instanceof Activity) {
					if (((Activity) act).getBreakdownElements().remove(obj)) {
						getRemovedDescriptors().add(obj);
					}
				}
			}
		}
	}

	public Collection getRemovedDescriptors() {
		if (removedDescriptors == null) {
			removedDescriptors = new HashSet();
		}
		return removedDescriptors;
	}

}
