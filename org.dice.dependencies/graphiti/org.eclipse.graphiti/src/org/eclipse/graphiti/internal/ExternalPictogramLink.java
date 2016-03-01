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
package org.eclipse.graphiti.internal;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;

/**
 * The Class ExternalPictogramLink.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class ExternalPictogramLink extends EObjectImpl implements PictogramLink {

	public static final String KEY_INDEPENDENT_PROPERTY = "independentObject"; //$NON-NLS-1$

	private PictogramElement pictogramElement;

	/**
	 * The Constructor.
	 */
	public ExternalPictogramLink() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.mm.links.PictogramLink#getBusinessObjects()
	 */
	public EList<EObject> getBusinessObjects() {
		throw new UnsupportedOperationException("Please use DataMapping.getBusinessObject(PictogramLink link)"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.mm.links.PictogramLink#getPictogramElement()
	 */
	public PictogramElement getPictogramElement() {
		return pictogramElement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.mm.links.PictogramLink#setPictogramElement(org.eclipse
	 * .graphiti.mm.pictograms.PictogramElement)
	 */
	public void setPictogramElement(PictogramElement newValue) {
		pictogramElement = newValue;
	}

	@Override
	public EList<Property> getProperties() {
		return new BasicEList<Property>();
	}

}
