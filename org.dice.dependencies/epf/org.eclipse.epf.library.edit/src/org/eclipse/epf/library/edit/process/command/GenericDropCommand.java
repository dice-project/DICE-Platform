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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.util.TngUtil;


/**
 * Drop command to drop any object on a target that supports adapting drop
 * object to its own object.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class GenericDropCommand extends AbstractCommand implements
		IResourceAwareCommand {

	private Collection dropElements;

	private ArrayList featureElements;

	private Collection modifiedResources;

	private EObject target;

	private EStructuralFeature feature;

	private ElementAdapter dropAdapter;

	public GenericDropCommand(EObject target, EStructuralFeature feature,
			Collection dropElements, ElementAdapter dropAdapter) {
		this.target = target;
		this.feature = feature;
		this.dropElements = dropElements;
		this.dropAdapter = dropAdapter;
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
	 * @see com.ibm.library.edit.command.IResourceAwareCommand#getModifiedResources()
	 */
	public Collection getModifiedResources() {
		if (modifiedResources == null) {
			modifiedResources = new HashSet();
		}
		return modifiedResources;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		// compile the featureElements list
		//
		featureElements = new ArrayList();
		for (Iterator iter = dropElements.iterator(); iter.hasNext();) {
			Object element = TngUtil.unwrap(iter.next());
			element = dropAdapter.adapt(element);
			if (element != null) {
				featureElements.add(element);
			}
			// if(element instanceof Role) {
			// if(createDescriptor) {
			// featureElements.add(ProcessUtil.createRoleDescriptor((Role)
			// element));
			// }
			// else {
			// featureElements.add(element);
			// }
			// }
		}

		redo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {
		if (!featureElements.isEmpty()) {
			((List) target.eGet(feature)).addAll(featureElements);
			if (target.eResource() != null) {
				getModifiedResources().add(target.eResource());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.AbstractCommand#undo()
	 */
	public void undo() {
		((List) target.eGet(feature)).removeAll(featureElements);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.AbstractCommand#getAffectedObjects()
	 */
	public Collection getAffectedObjects() {
		return featureElements;
	}

	public static interface ElementAdapter {
		/**
		 * Adapts the drop element to the right element for the drop target.
		 * 
		 * @param dropElement
		 * @return
		 */
		Object adapt(Object dropElement);
	}

}
