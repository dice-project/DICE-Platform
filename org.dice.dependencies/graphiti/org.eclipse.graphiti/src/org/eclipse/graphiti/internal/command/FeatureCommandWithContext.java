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
import org.eclipse.graphiti.features.IFeatureAndContext;
import org.eclipse.graphiti.features.context.IContext;

/**
 * The Class FeatureCommandWithContext.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public abstract class FeatureCommandWithContext extends FeatureCommand implements IFeatureAndContext {

	/**
	 * The context.
	 */
	IContext context = null;

	/**
	 * The Constructor.
	 * 
	 * @param context
	 *            the context
	 * @param feature
	 *            the feature
	 */
	protected FeatureCommandWithContext(IFeature feature, IContext context) {
		super(feature);
		setContext(context);
	}

	/**
	 * Gets the context.
	 * 
	 * @return Returns the context.
	 */
	public IContext getContext() {
		return context;
	}

	/**
	 * Sets the context.
	 * 
	 * @param context
	 *            The context to set.
	 */
	private void setContext(IContext context) {
		this.context = context;
	}

	// public boolean canExecute() {
	// return false;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.internal.command.ICommand#canUndo()
	 */
	public boolean canUndo() {
		return getFeature().canUndo(getContext());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.internal.command.ICommand#undo()
	 */
	public boolean undo() {
		return false;
	}
}