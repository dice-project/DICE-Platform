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
package org.eclipse.graphiti.features.impl;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.context.impl.RemoveContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.services.Graphiti;

/**
 * The Class UpdateNoBoFeature. An update feature, which marks a pictogram
 * element without a linked business object as "update needed". On "update" such
 * a pictogram element is deleted.
 */
public class UpdateNoBoFeature extends AbstractUpdateFeature {

	/**
	 * Creates a new {@link UpdateNoBoFeature}.
	 * 
	 * @param fp
	 *            the feature provider
	 */
	public UpdateNoBoFeature(IFeatureProvider fp) {
		super(fp);
	}

	public boolean canUpdate(IUpdateContext context) {
		return context.getPictogramElement() != null;
	}

	public IReason updateNeeded(IUpdateContext context) {
		IReason ret = Reason.createFalseReason();
		PictogramElement pe = context.getPictogramElement();
		if (pe != null) {
			PictogramLink linkForPictogramElement = Graphiti.getLinkService().getLinkForPictogramElement(pe);
			if (linkForPictogramElement != null && linkForPictogramElement.getBusinessObjects().isEmpty()) {
				ret = new Reason(true, "No business object linked to graphical representation"); //$NON-NLS-1$
			}
		}
		return ret;
	}

	public boolean update(IUpdateContext context) {
		boolean ret = false;
		PictogramElement pe = context.getPictogramElement();
		if (pe != null) {
			PictogramLink linkForPictogramElement = Graphiti.getLinkService().getLinkForPictogramElement(pe);
			if (linkForPictogramElement != null) {
				List<EObject> businessObject = linkForPictogramElement.getBusinessObjects();
				if (businessObject != null && businessObject.isEmpty()) {
					ret = removeIfPossible(pe);
					ret = true;
				}
			}
		}
		return ret;
	}

	private boolean removeIfPossible(PictogramElement pe) {
		IRemoveContext context = new RemoveContext(pe);
		final IRemoveFeature removeFeature = getFeatureProvider().getRemoveFeature(context);
		if (removeFeature != null && removeFeature.canRemove(context)) {
			removeFeature.remove(context);
			return true;
		}
		return false;
	}
}
