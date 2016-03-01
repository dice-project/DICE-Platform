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
package org.eclipse.epf.library.xmi.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.epf.library.edit.util.IOppositeFeatureLoader;
import org.eclipse.epf.persistence.MultiFileResourceSetImpl;
import org.eclipse.epf.uma.MethodElement;

/**
 * Manages the loading of opposite features for a XMI-based method library.
 *  
 * @author Phong Nguyen Le
 * @since  1.0
 */
public class XMIOppositeFeatureLoader implements IOppositeFeatureLoader {

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.util.IOppositeFeatureLoader#loadOppositeFeatures(java.util.Collection)
	 */
	public void loadOppositeFeatures(Collection elements) {
		HashSet oppositeFeatures = new HashSet();
		HashSet deletedGUIDs = new HashSet();
		MultiFileResourceSetImpl resourceSet = null;
		for (Iterator iter = elements.iterator(); iter.hasNext();) {
			Object o = (Object) iter.next();
			if(o instanceof MethodElement) {
				MethodElement e = (MethodElement) o;
				if(resourceSet == null) {
					Resource resource = e.eResource();
					ResourceSet rset;
					if(resource == null || !((rset = resource.getResourceSet()) instanceof MultiFileResourceSetImpl)) {
						continue;
					}
					resourceSet = (MultiFileResourceSetImpl) rset;
				}
				for (Iterator iterator = e.eAllContents(); iterator.hasNext();) {
					Object element = iterator.next();
					if (element instanceof MethodElement) {
						Collection features = ((MethodElement) element)
								.getOppositeFeatures();
						if (features != null
								&& !features.isEmpty()) {
							oppositeFeatures
									.addAll(features);
							deletedGUIDs.add(((MethodElement) element)
									.getGuid());
						}
					}
				}
				Collection features = e.getOppositeFeatures();
				if (features != null && !features.isEmpty()) {
					oppositeFeatures.addAll(features);
					deletedGUIDs.add(e.getGuid());
				}				
			}
		}
		
		resourceSet.loadOppositeFeatures(new ArrayList(oppositeFeatures), deletedGUIDs);
	}

}
