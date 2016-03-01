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

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.impl.DeleteContext;
import org.eclipse.graphiti.internal.command.GenericFeatureCommandWithContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.internal.command.GefCommandWrapper;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;

/**
 * An EditPolicy to handle the deletion of EditParts.
 * 
 * @see org.eclipse.graphiti.ui.internal.policy.IEditPolicyFactory#createModelObjectDeleteEditPolicy(IConfigurationProvider)
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class ModelObjectDeleteEditPolicy extends ComponentEditPolicy {

	private IConfigurationProvider _configurationProvider;

	/**
	 * Creates a new ModelObjectDeleteEditPolicy.
	 * 
	 * @param configurationProvider
	 *            The IConfigurationProvider.
	 */
	protected ModelObjectDeleteEditPolicy(IConfigurationProvider configurationProvider) {
		_configurationProvider = configurationProvider;
	}

	protected final IConfigurationProvider getConfigurationProvider() {
		return _configurationProvider;
	}

	/**
	 * Is called, when an EditPart shall be deleted. It creates an
	 * ICommandFactory.createDeleteModelObjectCommand().
	 * 
	 * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#createDeleteCommand(org.eclipse.gef.requests.GroupRequest)
	 */
	@Override
	protected Command createDeleteCommand(GroupRequest request) {
		if (getHost().getParent() == null) // do not allow to delete the
			// root-object itself
			return null;

		EditPart modelObjectEditPart = getHost();

		Object model = modelObjectEditPart.getModel();

		if (!(model instanceof PictogramElement))
			return null;

		DeleteContext context = new DeleteContext((PictogramElement) model);
		IFeatureProvider featureProvider = getConfigurationProvider().getDiagramTypeProvider().getFeatureProvider();
		IDeleteFeature feature = null;
		if (featureProvider != null) {
			feature = featureProvider.getDeleteFeature(context);
		}
		if (feature == null)
			return null;

		GenericFeatureCommandWithContext command = new GenericFeatureCommandWithContext(feature, context);

		return new GefCommandWrapper(command, getConfigurationProvider().getDiagramEditor().getEditingDomain());

	}
}