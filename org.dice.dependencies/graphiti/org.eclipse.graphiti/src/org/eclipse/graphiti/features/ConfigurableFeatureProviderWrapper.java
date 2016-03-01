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
/**
 * 
 */
package org.eclipse.graphiti.features;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.graphiti.features.context.IAddBendpointContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IMoveAnchorContext;
import org.eclipse.graphiti.features.context.IMoveBendpointContext;
import org.eclipse.graphiti.features.context.IMoveConnectionDecoratorContext;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.IPasteContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.features.context.IRemoveBendpointContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;

/**
 * The Class ConfigurableFeatureProviderWrapper.
 */
public class ConfigurableFeatureProviderWrapper extends DefaultFeatureProviderWrapper {

	/**
	 * Creates a new {@link ConfigurableFeatureProviderWrapper}.
	 * 
	 * @param innerFeatureProvider
	 *            the inner feature provider
	 */
	public ConfigurableFeatureProviderWrapper(IFeatureProvider innerFeatureProvider) {
		super(innerFeatureProvider);
	}

	@Override
	public IReason canAdd(IAddContext context) {
		IFeatureChecker featureChecker = getFeatureChecker();
		if (featureChecker == null || featureChecker.allowAdd(context)) {
			return super.canAdd(context);
		} else {
			return Reason.createFalseReason();
		}
	}

	@Override
	public IReason canLayout(ILayoutContext context) {
		IFeatureChecker featureChecker = getFeatureChecker();
		if (featureChecker == null || featureChecker.allowLayout(context)) {
			return super.canLayout(context);
		} else {
			return Reason.createFalseReason();
		}
	}

	@Override
	public IReason canUpdate(IUpdateContext context) {
		IFeatureChecker featureChecker = getFeatureChecker();
		if (featureChecker == null || featureChecker.allowUpdate(context)) {
			return super.canUpdate(context);
		} else {
			return Reason.createFalseReason();
		}
	}

	@Override
	public IAddBendpointFeature getAddBendpointFeature(IAddBendpointContext context) {
		IFeatureChecker featureChecker = getFeatureChecker();
		if (featureChecker == null || featureChecker.allowAdd(context)) {
			return super.getAddBendpointFeature(context);
		} else {
			return null;
		}
	}

	@Override
	public IAddFeature getAddFeature(IAddContext context) {
		IFeatureChecker featureChecker = getFeatureChecker();
		if (featureChecker == null || featureChecker.allowAdd(context)) {
			return super.getAddFeature(context);
		} else {
			return null;
		}
	}

	@Override
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		IFeatureChecker featureChecker = getFeatureChecker();
		if (featureChecker == null || featureChecker.allowCreate()) {
			return super.getCreateConnectionFeatures();
		} else {
			return new ICreateConnectionFeature[0];
		}
	}

	@Override
	public ICreateFeature[] getCreateFeatures() {
		IFeatureChecker featureChecker = getFeatureChecker();
		if (featureChecker == null || featureChecker.allowCreate()) {
			return super.getCreateFeatures();
		} else {
			return new ICreateFeature[0];
		}
	}

	@Override
	public ICustomFeature[] getCustomFeatures(ICustomContext context) {
		IFeatureChecker featureChecker = getFeatureChecker();
		if (featureChecker == null) {
			return super.getCustomFeatures(context);
		} else if (featureChecker.allowCustomFeatures(context)) {
			ICustomFeature[] customFeatures = super.getCustomFeatures(context);
			return filterAllowedCustomFeatures(customFeatures, context);
		} else {
			return new ICustomFeature[0];
		}
	}

	private ICustomFeature[] filterAllowedCustomFeatures(ICustomFeature[] features, IContext context) {
		List<ICustomFeature> retList = new ArrayList<ICustomFeature>();
		for (int i = 0; i < features.length; i++) {
			ICustomFeature feature = features[i];
			if (getFeatureChecker().allow(feature, context)) {
				retList.add(feature);
			}
		}
		return retList.toArray(new ICustomFeature[0]);
	}

	@Override
	public IDeleteFeature getDeleteFeature(IDeleteContext context) {
		IFeatureChecker featureChecker = getFeatureChecker();
		if (featureChecker == null || featureChecker.allowDelete(context)) {
			return super.getDeleteFeature(context);
		} else {
			return null;
		}
	}

	@Override
	public IDirectEditingFeature getDirectEditingFeature(IDirectEditingContext context) {
		IFeatureChecker featureChecker = getFeatureChecker();
		if (featureChecker == null || featureChecker.allowAdd(context)) {
			return super.getDirectEditingFeature(context);
		} else {
			return null;
		}
	}

	@Override
	public IFeature[] getDragAndDropFeatures(IPictogramElementContext context) {
		IFeatureChecker featureChecker = getFeatureChecker();
		if (featureChecker == null || featureChecker.allowDragAndDrop(context)) {
			return super.getDragAndDropFeatures(context);
		} else {
			return new IFeature[0];
		}
	}

	protected IFeatureChecker getFeatureChecker() {
		IToolBehaviorProvider currentToolBehaviorProvider = getDiagramTypeProvider().getCurrentToolBehaviorProvider();
		if (currentToolBehaviorProvider instanceof IFeatureCheckerHolder) {
			IFeatureCheckerHolder holder = (IFeatureCheckerHolder) currentToolBehaviorProvider;
			IFeatureChecker featureChecker = holder.getFeatureChecker();
			return featureChecker;
		}
		return null;
	}

	@Override
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		IFeatureChecker featureChecker = getFeatureChecker();
		if (featureChecker == null || featureChecker.allowAdd(context)) {
			return super.getLayoutFeature(context);
		} else {
			return null;
		}
	}

	@Override
	public IMoveAnchorFeature getMoveAnchorFeature(IMoveAnchorContext context) {
		IFeatureChecker featureChecker = getFeatureChecker();
		if (featureChecker == null || featureChecker.allowMove(context)) {
			return super.getMoveAnchorFeature(context);
		} else {
			return null;
		}
	}

	@Override
	public IMoveBendpointFeature getMoveBendpointFeature(IMoveBendpointContext context) {
		IFeatureChecker featureChecker = getFeatureChecker();
		if (featureChecker == null || featureChecker.allowMove(context)) {
			return super.getMoveBendpointFeature(context);
		} else {
			return null;
		}
	}

	@Override
	public IMoveConnectionDecoratorFeature getMoveConnectionDecoratorFeature(IMoveConnectionDecoratorContext context) {
		IFeatureChecker featureChecker = getFeatureChecker();
		if (featureChecker == null || featureChecker.allowMove(context)) {
			return super.getMoveConnectionDecoratorFeature(context);
		} else {
			return null;
		}
	}

	@Override
	public IMoveShapeFeature getMoveShapeFeature(IMoveShapeContext context) {
		IFeatureChecker featureChecker = getFeatureChecker();
		if (featureChecker == null || featureChecker.allowMove(context)) {
			return super.getMoveShapeFeature(context);
		} else {
			return null;
		}
	}

	@Override
	public IPasteFeature getPasteFeature(IPasteContext context) {
		IFeatureChecker featureChecker = getFeatureChecker();
		if (featureChecker == null || featureChecker.allowPaste(context)) {
			return super.getPasteFeature(context);
		} else {
			return null;
		}
	}

	@Override
	public IReconnectionFeature getReconnectionFeature(IReconnectionContext context) {
		IFeatureChecker featureChecker = getFeatureChecker();
		if (featureChecker == null || featureChecker.allowReconnect(context)) {
			return super.getReconnectionFeature(context);
		} else {
			return null;
		}
	}

	@Override
	public IRemoveBendpointFeature getRemoveBendpointFeature(IRemoveBendpointContext context) {
		IFeatureChecker featureChecker = getFeatureChecker();
		if (featureChecker == null || featureChecker.allowRemove(context)) {
			return super.getRemoveBendpointFeature(context);
		} else {
			return null;
		}
	}

	@Override
	public IRemoveFeature getRemoveFeature(IRemoveContext context) {
		IFeatureChecker featureChecker = getFeatureChecker();
		if (featureChecker == null || featureChecker.allowRemove(context)) {
			return super.getRemoveFeature(context);
		} else {
			return null;
		}
	}

	@Override
	public IResizeShapeFeature getResizeShapeFeature(IResizeShapeContext context) {
		IFeatureChecker featureChecker = getFeatureChecker();
		if (featureChecker == null || featureChecker.allowResize(context)) {
			return super.getResizeShapeFeature(context);
		} else {
			return null;
		}
	}

	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		IFeatureChecker featureChecker = getFeatureChecker();
		if (featureChecker == null || featureChecker.allowUpdate(context)) {
			return super.getUpdateFeature(context);
		} else {
			return null;
		}
	}
}
