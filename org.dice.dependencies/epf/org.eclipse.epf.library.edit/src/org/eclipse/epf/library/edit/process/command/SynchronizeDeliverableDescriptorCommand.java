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

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.epf.uma.Deliverable;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProductDescriptor;


/**
 * Command to synchronize attributes and deliverable parts of a deliverable descriptor
 * 
 * @author Phong Nguyen Le - Feb 9, 2006
 * @since  1.0
 */
public class SynchronizeDeliverableDescriptorCommand extends
		BasicSynchronizeDescriptorCommand {
	
	private Set descriptorsToRefresh;
	private Map wpDescToDeliverableParts;
	private DescriptorUpdateBatchCommand  updateDeliverablePartsCmd;
	
	/**
	 * @param descriptor
	 * @param synchFeatures
	 * @param config
	 */
	public SynchronizeDeliverableDescriptorCommand(WorkProductDescriptor descriptor,
			Set synchFeatures, MethodConfiguration config) {
		super(descriptor, synchFeatures, config);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.process.command.BasicSynchronizeDescriptorCommand#execute()
	 */
	public void execute() {
		// update linked element if needed
		// synchronize attributes
		//
		super.execute();
		
		if(descriptorsToRefresh == null) {
			descriptorsToRefresh = new HashSet();
		}
		else {
			descriptorsToRefresh.clear();
		}
		if(wpDescToDeliverableParts == null) {
			wpDescToDeliverableParts = new HashMap();
		}
		else {
			wpDescToDeliverableParts.clear();
		}
		WorkProductDescriptor wpd = (WorkProductDescriptor) descriptor;
		ProcessCommandUtil.createDeliverableParts(wpd, (Deliverable)wpd.getWorkProduct(), config,
				wpDescToDeliverableParts, descriptorsToRefresh);
		
		if(!wpDescToDeliverableParts.isEmpty() || !descriptorsToRefresh.isEmpty()) {
			if(updateDeliverablePartsCmd == null) {
				updateDeliverablePartsCmd = new DescriptorUpdateBatchCommand(true, synchFeatures, config);
				updateDeliverablePartsCmd.setDescriptorsToRefresh(descriptorsToRefresh);
			}
			else {
				updateDeliverablePartsCmd.getObjectToNewFeatureValuesMap().clear();
			}
			for (Iterator iter = wpDescToDeliverableParts.entrySet().iterator(); iter
			.hasNext();) {
				Map.Entry entry = (Map.Entry) iter.next();
				Object wpDesc = entry.getKey();
				updateDeliverablePartsCmd.getObjectToNewFeatureValuesMap().put(wpDesc, 
						Collections.singletonMap(UmaPackage.eINSTANCE.getWorkProductDescriptor_DeliverableParts(), entry.getValue()));
			}
			
			updateDeliverablePartsCmd.execute();
		}		
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.process.command.BasicSynchronizeDescriptorCommand#undo()
	 */
	public void undo() {
		if(updateDeliverablePartsCmd != null) {
			updateDeliverablePartsCmd.undo();
		}
		
		super.undo();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.AbstractCommand#dispose()
	 */
	public void dispose() {		
		super.dispose();
		
		if(updateDeliverablePartsCmd != null) {
			updateDeliverablePartsCmd.dispose();
		}
	}
}
