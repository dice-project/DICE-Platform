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

import java.util.Arrays;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.tools.ConnectionDragCreationTool;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.internal.features.context.impl.base.PictogramElementContext;
import org.eclipse.graphiti.internal.services.GraphitiInternal;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;
import org.eclipse.graphiti.ui.internal.util.gef.MultiCreationFactory;

/**
 * EditPart for a box relative anchor. Such an anchor can be positioned relative
 * to a container. For the graphical notation see {@link BoxRelativeAnchor}.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class BoxRelativeAnchorEditPart extends AnchorEditPart implements IBoxRelativeAnchorEditPart {

	/**
	 * The Constructor.
	 * 
	 * @param anchor
	 *            the anchor
	 * @param configurationProvider
	 *            the configuration provider
	 */
	public BoxRelativeAnchorEditPart(IConfigurationProvider configurationProvider, BoxRelativeAnchor anchor) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.editparts.AbstractGraphicalEditPart#getDragTracker(org
	 * .eclipse.gef.Request)
	 */
	@Override
	public DragTracker getDragTracker(Request request) {

		PictogramElementContext context = new PictogramElementContext(getPictogramElement());
		IFeature[] dragAndDropFeatures = getConfigurationProvider().getDiagramTypeProvider().getFeatureProvider().getDragAndDropFeatures(
				context);
		if (dragAndDropFeatures == null || dragAndDropFeatures.length == 0)
			return super.getDragTracker(request);

		ConnectionDragCreationTool tool = new ConnectionDragCreationTool() {

			/**
			 * changed order: feedback gets deleted after command is executed
			 * (popup!)
			 */
			@Override
			protected boolean handleCreateConnection() {

				Command endCommand = getCommand();
				setCurrentCommand(endCommand);
				executeCurrentCommand();
				eraseSourceFeedback();

				return true;
			}

		};

		tool.setFactory(new MultiCreationFactory(Arrays.asList(dragAndDropFeatures)));

		return tool;

	}

	/**
	 * Show on demand.
	 * 
	 * @return true, if successful
	 */
	public boolean showOnDemand() {
		// TODO: this is a temporary workaround until the metamodel has been
		// changed
		return GraphitiInternal.getEmfService().isObjectAlive(getPictogramElement())
				&& Graphiti.getPeService().getProperty(getPictogramElement(), "SHOWONDEMAND") != null; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.ui.internal.parts.AnchorEditPart#createFigure()
	 */
	@Override
	protected IFigure createFigure() {

		if (!showOnDemand())
			return super.createFigure();

		IFigure theFigure = super.createFigure();
		theFigure.setVisible(false);
		return theFigure;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.ui.internal.parts.AnchorEditPart#refreshVisuals()
	 */
	@Override
	protected void refreshVisuals() {
		if (showOnDemand()) {
			delegate.refreshFigureForEditPart();
			getFigure().setVisible(false);
		} else
			super.refreshVisuals();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.ui.internal.parts.AnchorEditPart#getFeatureProvider
	 * ()
	 */
	@Override
	public IFeatureProvider getFeatureProvider() {
		IFeatureProvider ret = null;
		if (delegate != null) {
			ret = delegate.getFeatureProvider();
		}
		return ret;
	}

}