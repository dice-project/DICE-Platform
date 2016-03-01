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
package org.eclipse.epf.authoring.ui.properties;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.process.command.DeleteUnusedDescriptorsCommand;
import org.eclipse.epf.library.edit.ui.ReferenceSelection;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.ui.actions.ProcessDeleteAction;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.util.UmaUtil;


/**
 * Command to remove descriptors
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class RemoveDescriptorCommand extends AbstractCommand implements
		IResourceAwareCommand {

	private List elements;

	private Descriptor desc;

	private Collection modifiedResources;

	private Command deleteUnusedDescriptorsCommand = null;

	private int featureID;

	private List references;
	
	private boolean localUse;
	
	private DescriptorPropUtil propUtil;
	
	public RemoveDescriptorCommand(Descriptor desc, List elements, int featureID) {
		this(desc, elements, featureID, true);
	}

	/**
	 * Remove relationships
	 */
	public RemoveDescriptorCommand(Descriptor desc, List elements, int featureID, boolean localUse) {

		super();

		this.elements = elements;
		this.desc = desc;
		this.featureID = featureID;
		this.localUse = localUse;
		this.propUtil = DescriptorPropUtil.getDesciptorPropUtil();

		this.modifiedResources = new HashSet();
	}
	
	/**
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		
		List notUsedReferences = new ArrayList();
		for (int i = 0; i < elements.size(); i++) {
			Descriptor o = (Descriptor) elements.get(i);

			boolean checkRef;
			if (o instanceof WorkProductDescriptor)
			{
				checkRef = ProcessUtil.checkWorkProductDescriptorReferences(Collections
				.singleton(desc), (WorkProductDescriptor) o, true, featureID);
			}
			else
				checkRef = ProcessUtil.checkDescriptorReferences(desc, o);
			
			if (!checkRef) {
				Activity act = UmaUtil.getParentActivity(desc);
				if (act.getBreakdownElements().contains(o))
					notUsedReferences.add(o);
			}
		}
		if (!(notUsedReferences.isEmpty())) {
			Object[] refToBeDeleted = ReferenceSelection
					.getReferences(notUsedReferences);
			references = Arrays.asList(refToBeDeleted);
		}
		
		redo();
	}

	/**
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {

		if (desc instanceof TaskDescriptor)
		{
			switch (featureID) {
			case UmaPackage.TASK_DESCRIPTOR__PERFORMED_PRIMARILY_BY:
//				((TaskDescriptor) desc).setPerformedPrimarilyBy(null);
				((TaskDescriptor) desc).getPerformedPrimarilyBy().removeAll(
						elements);
				break;
			case UmaPackage.TASK_DESCRIPTOR__ADDITIONALLY_PERFORMED_BY:
				((TaskDescriptor) desc).getAdditionallyPerformedBy().removeAll(
						elements);
				break;
			case UmaPackage.TASK_DESCRIPTOR__ASSISTED_BY:
				((TaskDescriptor) desc).getAssistedBy().removeAll(elements);
				break;
			case UmaPackage.TASK_DESCRIPTOR__MANDATORY_INPUT:
				((TaskDescriptor) desc).getMandatoryInput().removeAll(elements);
				break;
			case UmaPackage.TASK_DESCRIPTOR__EXTERNAL_INPUT:
				((TaskDescriptor) desc).getExternalInput().removeAll(elements);
				break;
			case UmaPackage.TASK_DESCRIPTOR__OPTIONAL_INPUT:
				((TaskDescriptor) desc).getOptionalInput().removeAll(elements);
				break;
			case UmaPackage.TASK_DESCRIPTOR__OUTPUT:
				((TaskDescriptor) desc).getOutput().removeAll(elements);
				break;
			}
		}
		else if (desc instanceof RoleDescriptor)
		{
			switch (featureID){
			case UmaPackage.ROLE_DESCRIPTOR__RESPONSIBLE_FOR:
				((RoleDescriptor) desc).getResponsibleFor().removeAll(elements);
				break;
			}
		} else if (desc instanceof WorkProductDescriptor) {
			switch (featureID){
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS:
				((WorkProductDescriptor) desc).getDeliverableParts().removeAll(elements);
				break;
			}
		}
		
		if (ProcessUtil.isSynFree()) {
			if (localUse) {
				propUtil.removeLocalUsingInfo(elements, desc, (EReference)desc.eClass().getEStructuralFeature(featureID));
			}
		}

		if (references != null && !references.isEmpty()) {
			if (deleteUnusedDescriptorsCommand == null) {
				deleteUnusedDescriptorsCommand = new DeleteUnusedDescriptorsCommand(
						references, false, Collections.EMPTY_LIST) {

					protected Command delete(List elements) {
						return ProcessDeleteAction.delete(elements);
					}
				};
			}
			deleteUnusedDescriptorsCommand.execute();
		}

		if (!elements.isEmpty()) {
			
			if (desc.eResource() != null) {
				modifiedResources.add(desc.eResource());
			}
		}
	}

	/**
	 * @see org.eclipse.emf.common.command.AbstractCommand#undo()
	 */
	public void undo() {

		// basically remove from configuration if anything was added

		if (desc instanceof TaskDescriptor)
		{
			switch (featureID) {
			case UmaPackage.TASK_DESCRIPTOR__PERFORMED_PRIMARILY_BY:
//				Object o = elements.get(0);
//				if (o instanceof RoleDescriptor)
//					((TaskDescriptor) desc).setPerformedPrimarilyBy((RoleDescriptor)o);
				((TaskDescriptor) desc).getPerformedPrimarilyBy().addAll(
						elements);
				break;
			case UmaPackage.TASK_DESCRIPTOR__ADDITIONALLY_PERFORMED_BY:
				((TaskDescriptor) desc).getAdditionallyPerformedBy().addAll(
						elements);
				break;
			case UmaPackage.TASK_DESCRIPTOR__ASSISTED_BY:
				((TaskDescriptor) desc).getAssistedBy().addAll(elements);
				break;
			case UmaPackage.TASK_DESCRIPTOR__MANDATORY_INPUT:
				((TaskDescriptor) desc).getMandatoryInput().addAll(elements);
				break;
			case UmaPackage.TASK_DESCRIPTOR__EXTERNAL_INPUT:
				((TaskDescriptor) desc).getExternalInput().addAll(elements);
				break;
			case UmaPackage.TASK_DESCRIPTOR__OPTIONAL_INPUT:
				((TaskDescriptor) desc).getOptionalInput().addAll(elements);
				break;
			case UmaPackage.TASK_DESCRIPTOR__OUTPUT:
				((TaskDescriptor) desc).getOutput().addAll(elements);
				break;
			}
		}
		else if (desc instanceof RoleDescriptor)
		{
			switch (featureID) {
			case UmaPackage.ROLE_DESCRIPTOR__RESPONSIBLE_FOR:
				((RoleDescriptor) desc).getResponsibleFor().addAll(elements);
				break;
			}
		} else if (desc instanceof WorkProductDescriptor) {
			switch (featureID){
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS:
				((WorkProductDescriptor) desc).getDeliverableParts().addAll(elements);
				break;
			}
		}
		
		if (ProcessUtil.isSynFree()) {
			if (localUse) {
				propUtil.addLocalUsingInfo(elements, desc, (EReference)desc.eClass().getEStructuralFeature(featureID));
			}
		}
		
		if (references != null && !references.isEmpty()) {
			try {
				if (deleteUnusedDescriptorsCommand != null)
					deleteUnusedDescriptorsCommand.undo();
			} catch (RuntimeException exception) {
			
			}
		}
		
	}
	
	/**
	 * @see org.eclipse.emf.common.command.AbstractCommand#dispose()
	 */
	public void dispose()
	{
		if (deleteUnusedDescriptorsCommand != null)
			deleteUnusedDescriptorsCommand.dispose();
		if(references != null) {
			references.clear();
		}
		if(modifiedResources != null) {
			modifiedResources.clear();
		}
		
		super.dispose();
	}

	/**
	 * @see org.eclipse.emf.common.command.AbstractCommand#prepare()
	 */
	protected boolean prepare() {
		return true;
	}

	/**
	 * @see org.eclipse.epf.library.edit.command.IResourceAwareCommand#getModifiedResources()
	 */
	public Collection getModifiedResources() {
		return modifiedResources;
	}
	
	public Collection getAffectedObjects() {
		if (desc != null) {
			return Arrays.asList(new Object[] { desc });
		}
		return super.getAffectedObjects();
	}
	
	protected int getFeatureID() {
		return featureID;
	}

}
