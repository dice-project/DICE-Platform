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
package org.eclipse.graphiti.features.impl;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.ISaveImageFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ISaveImageContext;
import org.eclipse.graphiti.internal.Messages;

/**
 * The Class DefaultSaveImageFeature.
 */
public class DefaultSaveImageFeature extends AbstractFeature implements ISaveImageFeature {

	/**
	 * Creates a new {@link DefaultSaveImageFeature}.
	 * 
	 * @param fp
	 *            the feature provider
	 */
	public DefaultSaveImageFeature(IFeatureProvider fp) {
		super(fp);
	}

	public boolean canSave(ISaveImageContext context) {
		return true;
	}

	public void postSave(ISaveImageContext context) {
	}

	public void preSave(ISaveImageContext context) {
	}

	public boolean canExecute(IContext context) {
		boolean ret = false;
		if (context instanceof ISaveImageContext) {
			ret = canSave((ISaveImageContext) context);
		}
		return ret;
	}

	public void execute(IContext context) {
		// TODO: not possible yet
	}

	@Override
	public String getName() {
		return NAME;
	}

	private static final String NAME = Messages.DefaultSaveImageFeature_0_xfld;
}
