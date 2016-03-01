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
 * Created on 06.07.2005
 */
package org.eclipse.graphiti.ui.features;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.ICopyFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICopyContext;
import org.eclipse.graphiti.features.impl.AbstractFeature;
import org.eclipse.graphiti.internal.Messages;
import org.eclipse.graphiti.ui.internal.util.clipboard.ModelClipboard;

/**
 * The Class AbstractCopyFeature.
 */
public abstract class AbstractCopyFeature extends AbstractFeature implements ICopyFeature {

	private static final String NAME = Messages.AbstractCopyFeature_0_xfld;

	/**
	 * Creates a new {@link AbstractCopyFeature}.
	 * 
	 * @param fp
	 *            the {@link IFeatureProvider} fp
	 */
	public AbstractCopyFeature(IFeatureProvider fp) {
		super(fp);
	}

	public boolean canExecute(IContext context) {
		boolean ret = false;
		if (context instanceof ICopyContext) {
			ret = canCopy((ICopyContext) context);
		}
		return ret;
	}

	public void execute(IContext context) {
		if (context instanceof ICopyContext) {
			copy((ICopyContext) context);
		}
	}

	@Override
	public String getName() {
		return NAME;
	}

	/**
	 * Put to clipboard.
	 * 
	 * @param objects
	 *            the objects
	 */
	protected void putToClipboard(Object[] objects) {
		final List<EObject> list = new ArrayList<EObject>();
		for (final Object o : objects) {
			if (o instanceof EObject) {
				final EObject eo = (EObject) o;
				list.add(eo);
			}
		}
		final EObject[] content = list.toArray(new EObject[0]);
		ModelClipboard.getDefault().setContent(content);
	}
}