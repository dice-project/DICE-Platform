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
package org.eclipse.graphiti.ui.internal.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class AddModelObjectCommand extends AbstractCommand {

	private List<AddContext> contextList; // contains IAddContext

	public AddModelObjectCommand(IConfigurationProvider configurationProvider, ContainerShape parent, ISelection sel, Rectangle rectangle) {
		this(configurationProvider, parent, sel, rectangle, null);
	}

	public AddModelObjectCommand(IConfigurationProvider configurationProvider, ContainerShape parent, ISelection sel, Rectangle rectangle,
			Connection connection) {
		super(configurationProvider);

		IStructuredSelection s = (IStructuredSelection) sel;
		if (s == null) {
			s = StructuredSelection.EMPTY;
		}

		contextList = new ArrayList<AddContext>();

		int x = rectangle.x;
		int y = rectangle.y;

		for (Iterator<?> iter = s.iterator(); iter.hasNext();) {
			Object next = iter.next();

			if (next instanceof IAdaptable) {
				IAdaptable adaptable = (IAdaptable) next;
				Object adapter = adaptable.getAdapter(EObject.class);
				if (adapter instanceof EObject) {
					next = adapter;
				}
			}
			AddContext ctx = new AddContext();
			ctx.setNewObject(next);
			ctx.setTargetContainer(parent);
			ctx.setLocation(x, y);
			ctx.setTargetConnection(connection);
			contextList.add(ctx);

			x += 10;
			y += 10;
		}
	}

	@Override
	public boolean canExecute() {
		IFeatureProvider featureProvider = getFeatureProvider();

		if (featureProvider != null && contextList.size() > 0) {
			// try to find an add-feature for each object in the selection
			for (Iterator<AddContext> iter = contextList.iterator(); iter.hasNext();) {
				IAddContext ctx = iter.next();
				IAddFeature f = featureProvider.getAddFeature(ctx);
				if (f == null) {
					return false;
				} else {
					boolean canAdd = f.canAdd(ctx);
					if (canAdd == true) {
						return true;
					}
				}
			}
			// return false;
		}

		return false;
	}

	@Override
	public void execute() {
		for (Iterator<AddContext> iter = contextList.iterator(); iter.hasNext();) {
			IAddContext ctx = iter.next();
			getFeatureProvider().addIfPossible(ctx);
		}
	}

	@Override
	public boolean canUndo() {
		return false;
	}
}