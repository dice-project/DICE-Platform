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
package org.eclipse.graphiti.ui.internal.editor;

import org.eclipse.emf.common.command.Command;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.internal.command.ICommand;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFOnEmfCommand implements ICommand {

	private Command emfCommand;
	private IFeatureProvider featureProvider;

	public GFOnEmfCommand(Command emfCom, IFeatureProvider fp) {
		this.emfCommand = emfCom;
		this.featureProvider = fp;
	}

	@Override
	public boolean canExecute() {
		return emfCommand.canExecute();
	}

	@Override
	public boolean canUndo() {
		return emfCommand.canUndo();
	}

	@Override
	public boolean execute() {
		emfCommand.execute();
		return true;
	}

	@Override
	public boolean undo() {
		emfCommand.undo();
		return true;
	}

	@Override
	public String getDescription() {
		return emfCommand.getDescription();
	}

	@Override
	public IFeatureProvider getFeatureProvider() {
		return featureProvider;
	}

}
