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
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.model.IWorkbenchAdapter2;

/**
 * The Interface IGFAdapter.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public interface IGFAdapter extends IWorkbenchAdapter, IWorkbenchAdapter2 {

	/**
	 * Gets the pictogram element.
	 * 
	 * @param object
	 *            the object
	 * 
	 * @return the pictogram element
	 */
	PictogramElement getPictogramElement(Object object);

	/**
	 * Gets the business objects.
	 * 
	 * @param object
	 *            the object
	 * 
	 * @return the business objects
	 */
	EObject[] getBusinessObjects(Object object);
}
