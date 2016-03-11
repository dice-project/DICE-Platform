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
 * The Interface IFeatureChecker.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IFeatureChecker {

	boolean allow(IFeature feature, IContext context);

	boolean allowAdd(IContext context);

	boolean allowConnectionSplit(ISplitConnectionContext context);

	boolean allowCreate();

	boolean allowCustomFeatures(ICustomContext context);

	boolean allowDelete(IDeleteContext context);

	boolean allowDragAndDrop(IPictogramElementContext context);

	boolean allowLayout(ILayoutContext context);

	boolean allowMove(IContext context);

	boolean allowPaste(IPasteContext context);

	boolean allowReconnect(IReconnectionContext context);

	boolean allowRemove(IContext context);

	boolean allowResize(IResizeShapeContext context);

	boolean allowUpdate(IUpdateContext context);
}
