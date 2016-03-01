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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IPasteFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IPasteContext;
import org.eclipse.graphiti.features.impl.AbstractFeature;
import org.eclipse.graphiti.internal.Messages;
import org.eclipse.graphiti.ui.internal.util.clipboard.ModelClipboard;

/**
 * The Class AbstractPasteFeature.
 */
public abstract class AbstractPasteFeature extends AbstractFeature implements IPasteFeature {

	private static final String NAME = Messages.AbstractPasteFeature_0_xfld;

	/**
	 * Creates a new {@link AbstractPasteFeature}.
	 * 
	 * @param fp
	 *            the feature provider
	 */
	public AbstractPasteFeature(IFeatureProvider fp) {
		super(fp);
	}

	public boolean canExecute(IContext context) {
		boolean ret = false;
		if (context instanceof IPasteContext) {
			ret = canPaste((IPasteContext) context);
		}
		return ret;
	}

	public void execute(IContext context) {
		if (context instanceof IPasteContext) {
			paste((IPasteContext) context);
		}
	}

	@Override
	public String getName() {
		return NAME;
	}

	/**
	 * Gets original content from clipboard.
	 * 
	 * @return the content from clipboard
	 * @throws IllegalStateException
	 *             if not called from UI thread
	 */
	protected Object[] getFromClipboard() {
		return ModelClipboard.getDefault().getContentAsEObjects(getDiagramEditor().getResourceSet());
	}

	/**
	 * Duplicates the clipboard's content.
	 * 
	 * @param target
	 *            an object acting as composite parent for the copies.
	 *            <code>null</code> if the copied elements should be top-level
	 *            elements.
	 * @return the copy result or <code>null</code> in case of an empty
	 *         clipboard
	 * @throws IllegalStateException
	 *             if not called from UI thread
	 * @see #isCompositionAllowed(EObject, EObject[])
	 */
	protected Object[] getCopiesFromClipBoard(Object target) {
		return ModelClipboard.getDefault().duplicateAndPaste(target, getDiagramEditor().getEditingDomain()).toArray();
	}

	/**
	 * Answers whether at least one of the given objects can be aggregated below
	 * the given parent as composite children.
	 * 
	 * @param parent
	 *            the composite parent
	 * @param objects
	 *            the objects to check
	 * @return <code>true</code> if at least one object may be a composite child
	 *         of <code>parent</code>
	 * @see #getCopiesFromClipBoard(Object)
	 */
	protected boolean isCompositionAllowed(EObject parent, EObject[] objects) {
		return ModelClipboard.getDefault().isCompositionAllowed(parent, objects);
	}

}