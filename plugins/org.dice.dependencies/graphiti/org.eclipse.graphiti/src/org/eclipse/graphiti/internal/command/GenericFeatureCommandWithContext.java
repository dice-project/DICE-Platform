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
 * Created on 04.07.2005
 */
package org.eclipse.graphiti.internal.command;

import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.IContext;

/**
 * The Class GenericFeatureCommandWithContext.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GenericFeatureCommandWithContext extends FeatureCommandWithContext {

	/**
	 * The Constructor.
	 * 
	 * @param context
	 *            the context
	 * @param feature
	 *            the feature
	 */
	public GenericFeatureCommandWithContext(IFeature feature, IContext context) {
		super(feature, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.internal.command.ICommand#canExecute()
	 */
	public boolean canExecute() {
		boolean ret = true;
		IFeature f = getFeature();
		IContext c = getContext();
		ret = ret && f != null && c != null && f.canExecute(c);
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.internal.command.ICommand#execute()
	 */
	public boolean execute() {
		if (getContext() != null && getFeature() != null && getFeature().canExecute(getContext())) {
			getFeature().execute(getContext());
			return true;
		} else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.internal.command.FeatureCommandWithContext#canUndo()
	 */
	@Override
	public boolean canUndo() {
		boolean ret = false;
		IFeature f = getFeature();
		IContext c = getContext();
		ret = f != null && f.canUndo(c);
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.internal.command.FeatureCommandWithContext#undo()
	 */
	@Override
	public boolean undo() {
		boolean ret = false;
		// IFeature f = getFeature();
		// IContext c = getContext();
		// ret = f != null && f.undo(c);
		return ret;
	}
}