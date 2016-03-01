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
package org.eclipse.graphiti.ui.internal.partfactory;

import java.util.Map;

import org.eclipse.gef.EditPart;
import org.eclipse.graphiti.internal.util.T;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.ManhattanConnection;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.ui.internal.config.AbstractConfigurationProviderHolder;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;
import org.eclipse.graphiti.ui.internal.config.IEditPartFactory;
import org.eclipse.graphiti.ui.internal.parts.BoxRelativeAnchorEditPart;
import org.eclipse.graphiti.ui.internal.parts.ConnectionDecoratorEditPart;
import org.eclipse.graphiti.ui.internal.parts.ContainerShapeEditPart;
import org.eclipse.graphiti.ui.internal.parts.DiagramEditPart;
import org.eclipse.graphiti.ui.internal.parts.FixPointAnchorEditPart;
import org.eclipse.graphiti.ui.internal.parts.FreeFormConnectionEditPart;
import org.eclipse.graphiti.ui.internal.parts.ManhattanConnectionEditPart;
import org.eclipse.graphiti.ui.internal.parts.ShapeEditPart;

/**
 * A concrete implementation of the interface IEditPartFactory, which works on a
 * pictogram model.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class PictogramsEditPartFactory extends AbstractConfigurationProviderHolder implements IEditPartFactory {

	/**
	 * Creates a new PictogramsEditPartFactory.
	 */
	public PictogramsEditPartFactory(IConfigurationProvider configurationProvider) {
		super(configurationProvider);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart,
	 * java.lang.Object)
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart ret = null;
		IConfigurationProvider configurationProvider = getConfigurationProvider();

		Map<?, ?> epRegistry = getConfigurationProvider().getDiagramEditor().getGraphicalViewer().getEditPartRegistry();
		if (epRegistry != null && epRegistry.containsKey(model)) {
			T.racer().warning("PictogramsEditPartFactory.createEditPart()", "edit part for this model already exists"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (model instanceof Shape) {
			Shape shape = (Shape) model;
			if (!(shape instanceof Diagram) && !shape.isActive()) {
				// return ret;
				// the following is a temorary fix just to make it fly
				ret = new ShapeEditPart(configurationProvider, shape);
			}
		}
		if (ret == null) {
			if (model instanceof Diagram) {
				ret = new DiagramEditPart(configurationProvider, (Diagram) model);
			} else if (model instanceof ConnectionDecorator) {
				ret = new ConnectionDecoratorEditPart(configurationProvider, (Shape) model);
			} else if (model instanceof ContainerShape) {
				ret = new ContainerShapeEditPart(configurationProvider, (ContainerShape) model);
			} else if (model instanceof Shape) {
				ret = new ShapeEditPart(configurationProvider, (Shape) model);
			} else if (model instanceof ManhattanConnection) {
				ret = new ManhattanConnectionEditPart(configurationProvider, (ManhattanConnection) model);
			} else if (model instanceof FreeFormConnection) {
				ret = new FreeFormConnectionEditPart(configurationProvider, (FreeFormConnection) model);
			} else if (model instanceof FixPointAnchor) {
				ret = new FixPointAnchorEditPart(configurationProvider, (FixPointAnchor) model);
			} else if (model instanceof BoxRelativeAnchor) {
				ret = new BoxRelativeAnchorEditPart(configurationProvider, (BoxRelativeAnchor) model);
			}
		}

		// check whether autoswitch to direct editing has been set
		// if yes: store the affected edit part in the editor for later use in
		// the refresh method
		// if (ret instanceof ShapeEditPart) {
		// IDirectEditingInfo dei =
		// getConfigurationProvider().getDiagramTypeProvider().getFeatureProvider()
		// .getDirectEditingInfo();
		// if (dei.isActive() && model.equals(dei.getMainPictogramElement())) {
		// getConfigurationProvider().getDiagramEditor().setDirectEditingEditPart((ShapeEditPart)
		// ret);
		// }
		// }

		return ret;
	}
}