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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.epf.library.edit.command.BatchCommand;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodConfiguration;


/**
 * @author Phong Nguyen Le - Feb 9, 2006
 * @since  1.0
 */
public class DescriptorUpdateBatchCommand extends BatchCommand {
	private Set descriptorsToRefresh;
	private CompoundCommand refreshDescriptorsCommand;
	private Set synchFeatures;
	private MethodConfiguration config;
	
	/**
	 * @param clear
	 */
	public DescriptorUpdateBatchCommand(boolean clear, Set synchFeatures, MethodConfiguration config) {
		super(clear);
		this.synchFeatures = synchFeatures;
		this.config = config;
	}

	public Set getDescriptorsToRefresh() {
		if(descriptorsToRefresh == null) {
			descriptorsToRefresh = new HashSet();
		}
		return descriptorsToRefresh;
	}
	
	void setDescriptorsToRefresh(Set descriptorsToRefresh) {
		this.descriptorsToRefresh = descriptorsToRefresh;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.command.BatchCommand#dispose()
	 */
	public void dispose() {
		if(descriptorsToRefresh != null) {
			descriptorsToRefresh.clear();
			descriptorsToRefresh = null;
		}
		
		if(refreshDescriptorsCommand != null) {
			refreshDescriptorsCommand.dispose();
		}
		
		super.dispose();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.command.BatchCommand#canExecute()
	 */
	public boolean canExecute() {
		return super.canExecute() || (descriptorsToRefresh != null && !descriptorsToRefresh.isEmpty());
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.command.BatchCommand#execute()
	 */
	public void execute() {
		super.execute();
		
		// create refreshDescriptorsCommand
		//
		if(descriptorsToRefresh != null && !descriptorsToRefresh.isEmpty()
				&& refreshDescriptorsCommand == null) {
			refreshDescriptorsCommand = new CompoundCommand();
			for (Iterator iter = descriptorsToRefresh.iterator(); iter.hasNext();) {
				Descriptor desc = (Descriptor) iter.next();
				refreshDescriptorsCommand.append(new BasicSynchronizeDescriptorCommand(desc, synchFeatures, config));
				refreshDescriptorsCommand.append(new RemoveDuplicateReferenceCommand(desc, ProcessCommandUtil.CONTENT_ELEMENT_GUIDANCE_REFERENCES, config));
			}
		}

		// refresh descriptors
		//
		if(refreshDescriptorsCommand != null) {
			refreshDescriptorsCommand.execute();
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.command.BatchCommand#undo()
	 */
	public void undo() {
		if(refreshDescriptorsCommand != null) {
			refreshDescriptorsCommand.undo();
		}
		
		super.undo();
	}
}
