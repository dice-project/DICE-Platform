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

import java.util.EventObject;
import java.util.List;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.impl.WorkspaceCommandStackImpl;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.graphiti.features.IContextHolder;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.IFeatureHolder;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.internal.DefaultFeatureAndContext;
import org.eclipse.graphiti.internal.command.CommandContainer;
import org.eclipse.graphiti.internal.command.DefaultExecutionInfo;
import org.eclipse.graphiti.internal.command.FeatureCommand;
import org.eclipse.graphiti.internal.command.GFPreparableCommand2;
import org.eclipse.graphiti.internal.command.ICommand;
import org.eclipse.graphiti.tb.IContextEntry;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;
import org.eclipse.graphiti.ui.internal.command.ContextEntryCommand;
import org.eclipse.graphiti.ui.internal.command.CreateConnectionCommand;
import org.eclipse.graphiti.ui.internal.command.GFCommand;
import org.eclipse.graphiti.ui.internal.command.GefCommandWrapper;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;

/**
 * The Class GFCommandStack.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFCommandStack extends CommandStack implements CommandStackListener {

	private org.eclipse.emf.common.command.CommandStack emfCommandStack;

	private IConfigurationProvider configurationProvider;

	private TransactionalEditingDomain editingDomain;

	public GFCommandStack(IConfigurationProvider configurationProvider, TransactionalEditingDomain editingDomain) {
		emfCommandStack = editingDomain.getCommandStack();
		emfCommandStack.addCommandStackListener(this);
		setConfigurationProvider(configurationProvider);
		this.editingDomain = editingDomain;
	}

	@Override
	public boolean canRedo() {
		return getEmfCommandStack().canRedo();
	}

	@Override
	public boolean canUndo() {
		return getEmfCommandStack().canUndo();
	}

	@Override
	public void dispose() {
		super.dispose();
		emfCommandStack.removeCommandStackListener(this);
		emfCommandStack = null;
		configurationProvider = null;
		editingDomain = null;
	}

	@Override
	public void execute(Command gefCommand) {
		if (gefCommand == null) {
			return;
		}

		org.eclipse.emf.common.command.Command emfCommand = transformFromGefToEmfCommand(gefCommand);
		org.eclipse.emf.common.command.Command gfPreparableCommand = new GFPreparableCommand2(getTransactionalEditingDomain(), emfCommand);

		IToolBehaviorProvider tbp = getConfigurationProvider().getDiagramTypeProvider().getCurrentToolBehaviorProvider();

		DefaultExecutionInfo executionInfo = new DefaultExecutionInfo();
		completeExecutionInfo(executionInfo, gefCommand);

		// IStatus prepareExecutionStatus =
		// PreparableCommand.prepareExecution(modelConnector.getEditingDomain(),
		// getConfigurationProvider().getFeatureProvider().getAffectedResourcesForModification());
		// if (!prepareExecutionStatus.isOK()) {
		// return;
		// }

		tbp.preExecute(executionInfo);
		getEmfCommandStack().execute(gfPreparableCommand);
		tbp.postExecute(executionInfo);

		// Check if the executed feature has really done changes (indicated by
		// IFeature.hasDoneChanges). If
		// not, remove the operation of the command used to execute the feature
		// from the command stack. It
		// will then also no longer appear as an entry in the undo stack. This
		// is especially necessary for
		// direct editing in a pattern environment when e.g. a new object is
		// created and directly offered
		// for direct editing (handled internally as two different commands
		// because of EMF restrictions).
		if (gefCommand instanceof GefCommandWrapper) {
			GefCommandWrapper gefCommandWrapper = (GefCommandWrapper) gefCommand;
			ICommand command = gefCommandWrapper.getCommand();
			if (command instanceof FeatureCommand) {
				if (!((FeatureCommand) command).getFeature().hasDoneChanges()) {
					// Use the default context and retrieve the last operation
					WorkspaceCommandStackImpl workspaceCommandStackImpl = (WorkspaceCommandStackImpl) getEmfCommandStack();
					IUndoContext context = workspaceCommandStackImpl.getDefaultUndoContext();
					IUndoableOperation operation = workspaceCommandStackImpl.getOperationHistory().getUndoOperation(context);

					// Replace the found operation with an empty set
					workspaceCommandStackImpl.getOperationHistory().replaceOperation(operation, new IUndoableOperation[0]);

					// Update the editor actions bars, especially Edit --> Undo
					notifyListeners(gefCommand, CommandStack.POST_MASK);
				}
			}
		}
	}

	@Override
	public void flush() {
		super.flush();
	}

	@Override
	public Object[] getCommands() {
		return super.getCommands();
	}

	@Override
	public Command getRedoCommand() {
		org.eclipse.emf.common.command.Command emfCommand = getEmfCommandStack().getRedoCommand();
		return emfCommand != null ? transformFromEmfToGefCommand(emfCommand) : null;
	}

	private Command transformFromEmfToGefCommand(org.eclipse.emf.common.command.Command emfCommand) {
		if (emfCommand instanceof EmfOnGefCommand) {
			EmfOnGefCommand emfOnGefCommand = (EmfOnGefCommand) emfCommand;
			return emfOnGefCommand.getGefCommand();
		}
		return new GefOnEmfCommand(emfCommand);
	}

	private org.eclipse.emf.common.command.Command transformFromGefToEmfCommand(Command gefCommand) {
		if (gefCommand instanceof GefOnEmfCommand) {
			GefOnEmfCommand gefOnEmfCommand = (GefOnEmfCommand) gefCommand;
			return gefOnEmfCommand.getEmfCommand();
		}
		return new EmfOnGefCommand(gefCommand);
	}

	@Override
	public Command getUndoCommand() {
		org.eclipse.emf.common.command.Command emfCommand = getEmfCommandStack().getUndoCommand();
		return emfCommand != null ? transformFromEmfToGefCommand(emfCommand) : null;
	}

	@Override
	public boolean isDirty() {
		return super.isDirty();
	}

	@Override
	public void redo() {
		getEmfCommandStack().redo();
	}

	@Override
	public void undo() {
		getEmfCommandStack().undo();
	}

	private org.eclipse.emf.common.command.CommandStack getEmfCommandStack() {
		return emfCommandStack;
	}

	private TransactionalEditingDomain getTransactionalEditingDomain() {
		return editingDomain;
	}

	@Override
	public void commandStackChanged(EventObject event) {
		notifyListeners();
		notifyListeners(null, 0);
	}

	private IConfigurationProvider getConfigurationProvider() {
		return configurationProvider;
	}

	private void setConfigurationProvider(IConfigurationProvider configurationProvider) {
		this.configurationProvider = configurationProvider;
	}

	private static DefaultExecutionInfo completeExecutionInfo(DefaultExecutionInfo executionInfo, Command gefCommand) {
		if (gefCommand instanceof CompoundCommand) {
			CompoundCommand compoundCommand = (CompoundCommand) gefCommand;
			@SuppressWarnings("unchecked")
			final List<Command> commands = compoundCommand.getCommands();
			for (Command childCommand : commands) {
				completeExecutionInfo(executionInfo, childCommand);
			}
		}
		if (gefCommand instanceof CreateConnectionCommand) {
			CreateConnectionCommand createConnectionCommand = (CreateConnectionCommand) gefCommand;
			final IFeature[] features = createConnectionCommand.getFeatures();
			for (IFeature feature : features) {
				executionInfo.addFeatureAndContext(new DefaultFeatureAndContext(feature, null));
			}
		}
		if (gefCommand instanceof GefCommandWrapper) {
			GefCommandWrapper gefCommandWrapper = (GefCommandWrapper) gefCommand;
			final ICommand graphitiCommand = gefCommandWrapper.getCommand();
			completeExecutionInfo(executionInfo, graphitiCommand);
		}
		if (gefCommand instanceof GFCommand) {
			final GFCommand gfCommand = (GFCommand) gefCommand;
			executionInfo.addFeatureAndContext(new DefaultFeatureAndContext(gfCommand.getFeature(), gfCommand.getContext()));
		}
		if (gefCommand instanceof ContextEntryCommand) {
			ContextEntryCommand cec = (ContextEntryCommand) gefCommand;
			IContextEntry contextEntry = cec.getContextEntry();
			executionInfo.addFeatureAndContext(new DefaultFeatureAndContext(contextEntry.getFeature(), contextEntry.getContext()));
		}

		return executionInfo;
	}

	private static DefaultExecutionInfo completeExecutionInfo(DefaultExecutionInfo executionInfo, ICommand gfCommand) {
		if (gfCommand instanceof CommandContainer) {
			CommandContainer cc = (CommandContainer) gfCommand;
			final ICommand[] childCommands = cc.getCommands();
			for (int i = 0; i < childCommands.length; i++) {
				ICommand childCommand = childCommands[i];
				completeExecutionInfo(executionInfo, childCommand);
			}
		}

		else {
			IContext context = null;
			IFeature feature = null;
			if (gfCommand instanceof IFeatureHolder) {
				IFeatureHolder featureHolder = (IFeatureHolder) gfCommand;
				feature = featureHolder.getFeature();
			}
			if (gfCommand instanceof IContextHolder) {
				IContextHolder contextHolder = (IContextHolder) gfCommand;
				context = contextHolder.getContext();
			}
			executionInfo.addFeatureAndContext(new DefaultFeatureAndContext(feature, context));
		}

		return executionInfo;
	}
}