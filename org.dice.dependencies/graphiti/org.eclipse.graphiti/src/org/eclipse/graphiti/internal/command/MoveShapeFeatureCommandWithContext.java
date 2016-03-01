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
import org.eclipse.graphiti.features.IMoveShapeFeature;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.internal.util.T;

/**
 * The Class MoveShapeFeatureCommandWithContext.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class MoveShapeFeatureCommandWithContext extends FeatureCommandWithContext {

	/**
	 * The Constructor.
	 * 
	 * @param feature
	 *            the feature
	 * @param context
	 *            the context
	 */
	public MoveShapeFeatureCommandWithContext(IFeature feature, IMoveShapeContext context) {
		super(feature, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		boolean ret = getMoveShapeFeature().canMoveShape(getMoveShapeContext());
		return ret;
	}

	/**
	 * @return
	 */
	private IMoveShapeContext getMoveShapeContext() {
		IMoveShapeContext ret = null;
		if (getContext() instanceof IMoveShapeContext) {
			ret = (IMoveShapeContext) getContext();
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public final boolean execute() {
		IMoveShapeContext layouShapeContext = getMoveShapeContext();
		IMoveShapeFeature moveShapeFeature = getMoveShapeFeature();

		if (T.racer().info()) {
			T.racer().info(
					"MoveShapeFeatureCommandWithContext", "execute", "call to IMoveShape.moveShape(IMoveShapeContext context) context: " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
							+ layouShapeContext);
		}
		moveShapeFeature.moveShape(layouShapeContext);
		return true;
	}

	/**
	 * Gets the move shape feature.
	 * 
	 * @return the move shape feature
	 */
	protected IMoveShapeFeature getMoveShapeFeature() {
		IMoveShapeFeature ret = null;
		if (getFeature() instanceof IMoveShapeFeature) {
			ret = (IMoveShapeFeature) getFeature();
		} else {
			return ret;
		}
		return ret;
	}
}