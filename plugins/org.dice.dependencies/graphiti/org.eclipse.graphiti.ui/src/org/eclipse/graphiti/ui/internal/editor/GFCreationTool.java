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
package org.eclipse.graphiti.ui.internal.editor;

import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.tools.CreationTool;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.impl.CreateContext;
import org.eclipse.graphiti.internal.command.CommandExec;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.swt.events.KeyEvent;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFCreationTool extends CreationTool {
	/**
	 * Default constructor. Sets the default and disabled cursors.
	 */
	public GFCreationTool() {
		super();
	}

	/**
	 * Constructs a new CreationTool with the given factory.
	 * 
	 * @param aFactory
	 *            the creation factory
	 */
	public GFCreationTool(CreationFactory aFactory) {
		super(aFactory);
	}

	@Override
	protected boolean handleKeyUp(KeyEvent e) {
		Object object = getFactory().getNewObject();
		if (object instanceof ICreateFeature) {
			final ICreateFeature createFeature = (ICreateFeature) object;
			final CreateContext context = new CreateContext();
			IFeatureProvider featureProvider = createFeature.getFeatureProvider();
			IDiagramTypeProvider diagramTypeProvider = featureProvider.getDiagramTypeProvider();
			PictogramElement[] selectedPictogramElements = diagramTypeProvider.getDiagramEditor().getSelectedPictogramElements();
			ContainerShape containerShape = null;
			if (selectedPictogramElements != null) {
				for (int i = 0; i < selectedPictogramElements.length; i++) {
					if (selectedPictogramElements[i] instanceof ContainerShape) {
						containerShape = (ContainerShape) selectedPictogramElements[i];
						break;
					}
				}
			}
			if (containerShape == null) {
				containerShape = diagramTypeProvider.getDiagram();
			}
			context.setTargetContainer(containerShape);
			if (createFeature.canExecute(context)) {
				CommandExec.executeFeatureWithContext(createFeature, context);
			}
		}
		eraseTargetFeedback();
		unlockTargetEditPart();
		return true;
	}
}