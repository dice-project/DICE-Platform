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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.graphiti.internal.services.GraphitiInternal;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.ChopboxAnchor;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;

/**
 * A class, which contains helper-methods, which are necessary to implement the
 * interface IAnchorContainerEditPart. It is not possible to make this an
 * EditPart itself, because of different inheritance-hierarchies used in the
 * sub-classes.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class AnchorContainerDelegate extends PictogramElementDelegate implements IAnchorContainerDelegate {

	/**
	 * Creates a new AnchorContainerDelegate.
	 * 
	 * @param configurationProvider
	 *            the configuration provider
	 * @param pictogramElement
	 *            the pictogram element
	 * @param containerEditPart
	 *            the container edit part
	 */
	protected AnchorContainerDelegate(IConfigurationProvider configurationProvider, PictogramElement pictogramElement,
			EditPart containerEditPart) {
		super(configurationProvider, pictogramElement, containerEditPart);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.ui.internal.parts.IAnchorContainerDelegate#
	 * getModelChildren()
	 */
	public List<PictogramElement> getModelChildren() {
		List<PictogramElement> result = new ArrayList<PictogramElement>();
		AnchorContainer notAnAnchorElement = (AnchorContainer) getPictogramElement();
		if (!GraphitiInternal.getEmfService().isObjectAlive(notAnAnchorElement)) {
			return result;
		}
		Collection<Anchor> allAnchors = notAnAnchorElement.getAnchors();
		for (Anchor anchor : allAnchors) {
			if (anchor.isActive() && !(anchor instanceof ChopboxAnchor)) {
				result.add(anchor);
			}
		}
		return result;
	}

	public void refreshDecorators() {
		PictogramElement pe = getPictogramElement();
		GraphicsAlgorithm ga = pe.getGraphicsAlgorithm();
		IToolBehaviorProvider tbp = getConfigurationProvider().getDiagramTypeProvider().getCurrentToolBehaviorProvider();
		addDecorators(ga, pe, getFigureForGraphicsAlgorithm(ga), tbp);
	}
}