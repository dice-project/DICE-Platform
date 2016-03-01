/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.parts;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.ui.model.WorkbenchAdapter;

/**
 * The Class GFAdapter.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFAdapter extends WorkbenchAdapter implements IGFAdapter {

	private static final EObject[] NO_REF_OBJECTS = new EObject[0];

	/**
	 * Creates a new {@link GFAdapter}.
	 */
	public GFAdapter() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.model.WorkbenchAdapter#getLabel(java.lang.Object)
	 */
	@Override
	public String getLabel(Object object) {
		String addition = null;
		PictogramElement pe = getPictogramElement(object);
		if (pe != null) {
			addition = pe.toString();
		}
		return "PE: " + addition + ""; //$NON-NLS-1$//$NON-NLS-2$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.ui.internal.parts.IGFAdapter#getPictogramElement
	 * (java.lang.Object)
	 */
	public PictogramElement getPictogramElement(Object object) {
		if (object instanceof IPictogramElementEditPart) {
			IPictogramElementEditPart peEditPart = (IPictogramElementEditPart) object;
			return peEditPart.getPictogramElement();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.ui.internal.parts.IGFAdapter#getBusinessObjects
	 * (java.lang.Object)
	 */
	public EObject[] getBusinessObjects(Object object) {
		EObject[] ret = NO_REF_OBJECTS;
		PictogramElement pe = getPictogramElement(object);
		if (pe != null) {
			ret = Graphiti.getLinkService().getAllBusinessObjectsForLinkedPictogramElement(pe);
		}
		return ret;
	}

}
