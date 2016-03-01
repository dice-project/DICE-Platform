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
package org.eclipse.graphiti.features;

import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IPasteContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.context.ISplitConnectionContext;
import org.eclipse.graphiti.features.context.IUpdateContext;

/**
 * The Class FeatureCheckerAdapter is a convenience implementation of
 * IFeatureChecker. All methods return either true or false.
 * 
 * This class is expected to be sub-classed.
 */
public class FeatureCheckerAdapter implements IFeatureChecker {

	private final boolean allow;

	/**
	 * Creates an {@link FeatureCheckerAdapter}.
	 * 
	 * @param allow the boolean allow
	 */
	public FeatureCheckerAdapter(boolean allow) {
		this.allow = allow;
	}

	public boolean allowAdd(IContext context) {
		return this.allow;
	}

	public boolean allowConnectionSplit(ISplitConnectionContext context) {
		return this.allow;
	}

	public boolean allowCreate() {
		return this.allow;
	}

	public boolean allowCustomFeatures(ICustomContext context) {
		return this.allow;
	}

	public boolean allowDelete(IDeleteContext context) {
		return this.allow;
	}

	public boolean allowDragAndDrop(IPictogramElementContext context) {
		return this.allow;
	}

	public boolean allowLayout(ILayoutContext context) {
		return this.allow;
	}

	public boolean allowMove(IContext context) {
		return this.allow;
	}

	public boolean allowPaste(IPasteContext context) {
		return this.allow;
	}

	public boolean allowReconnect(IReconnectionContext context) {
		return this.allow;
	}

	public boolean allowRemove(IContext context) {
		return this.allow;
	}

	public boolean allowResize(IResizeShapeContext context) {
		return this.allow;
	}

	public boolean allowUpdate(IUpdateContext context) {
		return this.allow;
	}

	public boolean allow(IFeature feature, IContext context) {
		return this.allow;
	}

}
