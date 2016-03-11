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
package org.eclipse.graphiti.notification;

import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Interface INotificationService.
 */
public interface INotificationService {

	/**
	 * Calculate dirty pictogram elements.
	 * 
	 * @param bos
	 *            the changed business objects
	 * @return the pictogram element[]
	 */
	PictogramElement[] calculateRelatedPictogramElements(Object[] bos);

	/**
	 * Update dirty pictogram elements.
	 * 
	 * @param pes
	 *            the dirty pes
	 */
	void updatePictogramElements(PictogramElement[] pes);

}
