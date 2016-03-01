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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.AdapterFactoryTreeIterator;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.Suppression;

/**
 * @author Phong Nguyen Le - Feb 10, 2006
 * @since  1.0
 */
public class UpdateSuppressionFromBaseCommand extends AbstractCommand implements
		IResourceAwareCommand {

	private Suppression suppression;
	private Collection elements;
	private HashSet inheritedElements;
	private AdapterFactory adapterFactory;
	private Collection affectedObjects;
	private HashMap objectToSuppressionStateMap;

	/**
	 * 
	 */
	public UpdateSuppressionFromBaseCommand() {
		super();
	}

	/**
	 * @param label
	 */
	public UpdateSuppressionFromBaseCommand(String label, Collection elements, AdapterFactory adapterFactory, Suppression suppression) {
		super(label);
		this.elements = elements;
		this.adapterFactory = adapterFactory;
		this.suppression = suppression;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.AbstractCommand#dispose()
	 */
	public void dispose() {
		if(affectedObjects != null) {
			affectedObjects.clear();
			affectedObjects = null;
		}
		
		if(inheritedElements != null) {
			inheritedElements.clear();
			inheritedElements = null;
		}
		
		if(objectToSuppressionStateMap != null) {
			objectToSuppressionStateMap.clear();
			objectToSuppressionStateMap = null;
		}
		
		super.dispose();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.command.IResourceAwareCommand#getModifiedResources()
	 */
	public Collection getModifiedResources() {
		Resource resource = suppression.getProcess().eResource();
		if(resource != null) {
			return Collections.singletonList(resource);
		}
		else {
			return Collections.EMPTY_LIST;
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.AbstractCommand#getAffectedObjects()
	 */
	public Collection getAffectedObjects() {
		if(affectedObjects == null) {
			return super.getAffectedObjects();
		}
		else {
			return affectedObjects;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		if(affectedObjects == null) {
			affectedObjects = new ArrayList();
		}
		else {
			affectedObjects.clear();
		}
		
		if(objectToSuppressionStateMap == null) {
			objectToSuppressionStateMap = new HashMap();
		}
		else {
			objectToSuppressionStateMap.clear();
		}
		
		for (Iterator iter = getInheritedElements().iterator(); iter.hasNext();) {
			BreakdownElementWrapperItemProvider wrapper = (BreakdownElementWrapperItemProvider) iter.next();
			boolean wasSuppressed = suppression.isInSuppressedList(wrapper);
			if(suppression.updateSuppressionFromBase(wrapper)) {
				affectedObjects.add(wrapper);
				objectToSuppressionStateMap.put(wrapper, Boolean.valueOf(wasSuppressed));
			}										
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {		
		execute();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.AbstractCommand#undo()
	 */
	public void undo() {
		if(objectToSuppressionStateMap != null && !objectToSuppressionStateMap.isEmpty()) {
			for (Iterator iter = objectToSuppressionStateMap.entrySet().iterator(); iter.hasNext();) {
				Map.Entry entry = (Map.Entry) iter.next();
				Boolean suppressed = (Boolean) entry.getValue();
				if(suppressed.booleanValue()) {
					suppression.suppress(Collections.singletonList(entry.getKey()));
				}
				else {
					suppression.reveal(Collections.singletonList(entry.getKey()));
				}
			}
			affectedObjects.clear();
			affectedObjects.addAll(elements);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.AbstractCommand#prepare()
	 */
	protected boolean prepare() {
		return true;
	}

	private Collection getInheritedElements() {
		if(inheritedElements == null) {
			inheritedElements = new HashSet();
			for (Iterator iter = elements.iterator(); iter.hasNext();) {
				Object element = (Object) iter.next();
				for (Iterator treeIter = new AdapterFactoryTreeIterator(adapterFactory, element); treeIter.hasNext();) {
					Object e = treeIter.next();
					if(ProcessUtil.isInherited(e)) {
						inheritedElements.add(e);
					}
				}
			}
		}
		return inheritedElements;
	}


}
