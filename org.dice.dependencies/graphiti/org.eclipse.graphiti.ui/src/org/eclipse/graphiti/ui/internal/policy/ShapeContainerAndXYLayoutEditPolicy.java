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
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IMoveShapeFeature;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.internal.command.MoveShapeFeatureCommandWithContext;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.ui.internal.command.GefCommandWrapper;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class ShapeContainerAndXYLayoutEditPolicy extends ShapeXYLayoutEditPolicy {

	protected ShapeContainerAndXYLayoutEditPolicy(IConfigurationProvider configurationProvider) {
		super(configurationProvider);
	}

	@Override
	protected Command createAddCommand(EditPart child, Object constraint) {

		if (child.getModel() instanceof ConnectionDecorator) {
			return super.createAddCommand(child, constraint);
		}

		Command result = null;

		Object hostModel = getHost().getModel();
		if (hostModel instanceof ContainerShape) {
			ContainerShape containerShape = (ContainerShape) hostModel;

			Object model = child.getModel();
			if (model instanceof Shape) {
				Shape shape = (Shape) model;
				ContainerShape oldContainer = shape.getContainer();
				IMoveShapeContext context = createMoveShapeContext(shape, oldContainer, containerShape, constraint);
				IFeatureProvider featureProvider = getConfigurationProvider().getDiagramTypeProvider().getFeatureProvider();
				IMoveShapeFeature layoutShapeFeature = featureProvider.getMoveShapeFeature(context);
				if (layoutShapeFeature != null) {
					result = new GefCommandWrapper(new MoveShapeFeatureCommandWithContext(layoutShapeFeature, context),
							getConfigurationProvider().getDiagramEditor().getEditingDomain());
				}
			}
		}
		return result;
	}
}