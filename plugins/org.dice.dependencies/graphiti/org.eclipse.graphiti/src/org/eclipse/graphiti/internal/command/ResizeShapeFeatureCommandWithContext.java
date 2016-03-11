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
 * Created on 05.07.2005
 */
package org.eclipse.graphiti.internal.command;

import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.context.IResizeShapeContext;

/**
 * The Class ResizeShapeFeatureCommandWithContext.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class ResizeShapeFeatureCommandWithContext extends FeatureCommandWithContext {

	/**
	 * The Constructor.
	 * 
	 * @param feature
	 *            the feature
	 * @param context
	 *            the context
	 */
	public ResizeShapeFeatureCommandWithContext(IFeature feature, IResizeShapeContext context) {
		super(feature, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		boolean ret = true;
		if (ret) {
			IResizeShapeFeature f = getResizeShapeFeature();
			ret = f != null && f.canResizeShape(getResizeShapeContext());
		}
		return ret;
	}

	/**
	 * @return
	 */
	private IResizeShapeContext getResizeShapeContext() {
		IResizeShapeContext ret = null;
		if (getContext() instanceof IResizeShapeContext) {
			ret = (IResizeShapeContext) getContext();
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public final boolean execute() {
		IResizeShapeContext layouShapeContext = getResizeShapeContext();
		IResizeShapeFeature resizeShapeFeature = getResizeShapeFeature();

		resizeShapeFeature.resizeShape(layouShapeContext);

		// create new positions of box-relative anchors
		// LayoutUtil.resizeBoxRelativeAnchors(getResizeShapeContext().getShape());

		return true;
	}

	/**
	 * Gets the resize shape feature.
	 * 
	 * @return the resize shape feature
	 */
	protected IResizeShapeFeature getResizeShapeFeature() {
		IResizeShapeFeature ret = null;
		if (getFeature() instanceof IResizeShapeFeature) {
			ret = (IResizeShapeFeature) getFeature();
		} else {
			return ret;
		}
		return ret;
	}
}