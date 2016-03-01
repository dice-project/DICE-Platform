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
package org.eclipse.graphiti.ui.internal.policy;

import org.eclipse.gef.EditPolicy;
import org.eclipse.graphiti.ui.internal.config.AbstractConfigurationProviderHolder;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;

/**
 * A default implementation of the interface IEditPolicyFactory.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class DefaultEditPolicyFactory extends AbstractConfigurationProviderHolder implements IEditPolicyFactory {

	/**
	 * Creates a new DefaultEditPolicyFactory.
	 * 
	 * @param configurationProvider
	 *            The IConfigurationProvider, to which this factory belongs.
	 */
	public DefaultEditPolicyFactory(IConfigurationProvider configurationProvider) {
		super(configurationProvider);
	}

	public void dispose() {
	}

	public EditPolicy createShapeForbidLayoutEditPolicy() {
		return new ShapeForbidLayoutEditPolicy(getConfigurationProvider());
	}

	public EditPolicy createShapeXYLayoutEditPolicy() {
		// return new ShapeXYLayoutEditPolicy(getConfigurationProvider().getCommandFactory());
		return new ShapeContainerAndXYLayoutEditPolicy(getConfigurationProvider());
	}

	public EditPolicy createShapeHighlightEditPolicy() {
		return new ShapeHighlightEditPolicy(getConfigurationProvider());
	}

	public static String HOVER_POLICY_KEY = "hover"; //$NON-NLS-1$

	public EditPolicy createShapeHoverEditPolicy() {
		return new ShapeHoverEditPolicy(getConfigurationProvider());
	}

	public EditPolicy createConnectionHighlightEditPolicy() {
		return new ConnectionHighlightEditPolicy(getConfigurationProvider());
	}

	public EditPolicy createConnectionEditPolicy() {
		return new GFNodeEditPolicy(getConfigurationProvider());
	}

	public EditPolicy createDirectEditPolicy() {
		return new DefaultDirectEditPolicy(getConfigurationProvider());
	}

	public EditPolicy createModelObjectDeleteEditPolicy(IConfigurationProvider configurationProvider) {
		return new ModelObjectDeleteEditPolicy(configurationProvider);
	}

	public EditPolicy createConnectionBendpointsEditPolicy() {
		return new ConnectionBendpointEditPolicy(getConfigurationProvider());
		// return new MoveBendpointEditPolicy();
	}

	public EditPolicy createConnectionDeleteEditPolicy(IConfigurationProvider configurationProvider) {
		return new DefaultConnectionEditPolicy(configurationProvider);
	}

}