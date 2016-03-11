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
package org.eclipse.epf.library.layout.elements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.layout.ElementLayoutManager;
import org.eclipse.epf.library.layout.util.XmlElement;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;


/**
 * The layout for work breakdown Elements.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class WorkBreakdownElementLayout extends AbstractProcessElementLayout {

	public void init(ElementLayoutManager layoutManager, MethodElement element) {
		super.__init(layoutManager, element);
	}

	/**
	 * override this method to handle the workorder
	 */
	public void loadReferences(XmlElement elementXml, boolean includeReferences) {
		List features = LibraryUtil.getStructuralFeatures(element);
//		List properties = element.getInstanceProperties();
		if (features != null) {
			for (int i = 0; i < features.size(); i++) {
				EStructuralFeature feature = (EStructuralFeature) features
						.get(i);
				if (feature.getEType() instanceof EClass) {
					if (feature != UmaPackage.eINSTANCE
							.getWorkBreakdownElement_LinkToPredecessor()) {
						loadFeature(feature, elementXml, includeReferences);
					} else {
						super.loadWorkOrder(elementXml);
					}
				}
			}
		}
		
		Collection oppositeProperties = new ArrayList(element.getOppositeFeatures());
		for (Iterator z= oppositeProperties.iterator(); z.hasNext(); )
		{
			OppositeFeature ofeature = (OppositeFeature) z.next();
			loadFeature(ofeature, elementXml, includeReferences);
		}
	}

}
