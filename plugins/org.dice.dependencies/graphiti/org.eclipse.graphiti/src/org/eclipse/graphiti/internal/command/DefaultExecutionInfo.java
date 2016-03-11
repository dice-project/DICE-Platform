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
package org.eclipse.graphiti.internal.command;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.graphiti.IExecutionInfo;
import org.eclipse.graphiti.features.IFeatureAndContext;

/**
 * The Class DefaultExecutionInfo.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class DefaultExecutionInfo implements IExecutionInfo {
	private final List<IFeatureAndContext> executionList = new ArrayList<IFeatureAndContext>();

	/**
	 * Instantiates a new default execution info.
	 */
	public DefaultExecutionInfo() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.ei.IExecutionInfo#getExecutionList()
	 */
	public IFeatureAndContext[] getExecutionList() {
		return executionList.toArray(new IFeatureAndContext[0]);
	}

	/**
	 * Adds the feature and context.
	 * 
	 * @param fac
	 *            the fac
	 * 
	 * @return true, if successful
	 */
	public boolean addFeatureAndContext(IFeatureAndContext fac) {
		return executionList.add(fac);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String ret = getClass().getSimpleName();
		final int length = getExecutionList().length;
		if (length > 0) {
			for (int i = 0; i < length; i++) {
				IFeatureAndContext fac = getExecutionList()[i];
				ret = ret + "\n" + "  " + fac.toString() + "; "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}
		} else {
			ret = ret + " (execution list is empty)"; //$NON-NLS-1$
		}

		return ret;
	}
}
