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
/*
 * Created on 05.04.2005
 */
package org.eclipse.graphiti.ui.internal.parts;

import org.eclipse.gef.EditPolicy;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;

/**
 * EditPart for a fix point anchor. Such an anchor can be positioned at a fixed
 * point of the container. For the graphical notation see {@link FixPointAnchor}
 * .
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class FixPointAnchorEditPart extends AnchorEditPart implements IFixPointAnchorEditPart {

	/**
	 * The Constructor.
	 * 
	 * @param anchor
	 *            the anchor
	 * @param configurationProvider
	 *            the configuration provider
	 */
	public FixPointAnchorEditPart(IConfigurationProvider configurationProvider, FixPointAnchor anchor) {
		super(configurationProvider, anchor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, getConfigurationProvider().getEditPolicyFactory().createModelObjectDeleteEditPolicy(
				getConfigurationProvider()));
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, getConfigurationProvider().getEditPolicyFactory().createConnectionEditPolicy());
	}
}