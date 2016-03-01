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
package org.eclipse.graphiti.ui.internal.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.internal.command.CommandContainer;
import org.eclipse.graphiti.internal.command.GenericFeatureCommandWithContext;
import org.eclipse.graphiti.internal.command.ICommand;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.internal.command.GefCommandWrapper;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public abstract class AbstractPreDefinedAction extends SelectionAction implements IAvailable {

	private IConfigurationProvider configurationProvider;

	public AbstractPreDefinedAction(IWorkbenchPart part, IConfigurationProvider configurationProvider) {
		super(part);
		this.configurationProvider = configurationProvider;
	}

	protected IConfigurationProvider getConfigurationProvider() {
		return configurationProvider;
	}

	protected IFeatureProvider getFeatureProvider() {
		return getConfigurationProvider().getDiagramTypeProvider().getFeatureProvider();
	}

	protected PictogramElement[] getSelectedPictogramElements() {
		List<?> selectedObjects = getSelectedObjects();
		List<Object> list = new ArrayList<Object>();
		for (Iterator<?> iter = selectedObjects.iterator(); iter.hasNext();) {
			Object o = iter.next();
			if (o instanceof EditPart) {
				EditPart editPart = (EditPart) o;
				if (editPart.getModel() instanceof PictogramElement) {
					list.add(editPart.getModel());
				}
			}
		}

		return list.toArray(new PictogramElement[0]);
	}

	protected void executeOnCommandStack(ICommand command) {
		CommandStack commandStack = getConfigurationProvider().getDiagramEditor().getEditDomain().getCommandStack();
		commandStack.execute(new GefCommandWrapper(command, getConfigurationProvider().getDiagramEditor().getEditingDomain()));
	}

	protected void genericRun(IContextAndFeatureProvider cfProvider) {
		final IFeatureProvider featureProvider = getFeatureProvider();
		CommandContainer commandContainer = new CommandContainer(featureProvider);

		PictogramElement pes[] = getSelectedPictogramElements();
		for (int i = 0; i < pes.length; i++) {
			final PictogramElement pe = pes[i];
			IContext context = cfProvider.createContext(pe);
			IFeature feature = cfProvider.provideFeatureForContext(context);
			if (feature != null) {
				// CommandExec.executeFeatureWithContext(feature, context);
				commandContainer.add(new GenericFeatureCommandWithContext(feature, context));
			}
		}

		executeOnCommandStack(commandContainer);
	}
}