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

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.internal.command.CommandExec;
import org.eclipse.graphiti.internal.command.GenericFeatureCommandWithContext;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class CreateModelObjectCommand extends GFCommand {

	private final Rectangle rectangle;

	public CreateModelObjectCommand(IConfigurationProvider configurationProvider, ICreateFeature feature, ICreateContext context,
			Rectangle rectangle) {
		super(configurationProvider);
		setFeature(feature);
		setContext(context);
		this.rectangle = rectangle;
	}

	@Override
	public boolean canExecute() {
		boolean ret = ((ICreateFeature) getFeature()).canCreate((ICreateContext) getContext());
		return ret;
	}

	@Override
	public void execute() {
		// create business-content
		// feature.create(context);
		GenericFeatureCommandWithContext genericFeatureCommandWithContext = new GenericFeatureCommandWithContext(getFeature(), getContext());
		CommandExec.getSingleton().executeCommand(genericFeatureCommandWithContext, getTransactionalEditingDomain());
	}

	/**
	 * @return Returns the rectangle.
	 */
	protected Rectangle getRectangle() {
		return rectangle;
	}

	@Override
	public boolean canUndo() {
		return true;
	}

	@Override
	public void undo() {
	}
}