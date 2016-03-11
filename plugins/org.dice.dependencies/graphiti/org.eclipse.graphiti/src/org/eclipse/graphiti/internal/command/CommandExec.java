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

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.internal.pref.GFPreferences;
import org.eclipse.graphiti.internal.util.T;

/**
 * The Class CommandExec.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class CommandExec {

	private static int commandExecutionDepth2 = 0;

	private static CommandExec singleton = null;

	private CommandExec() {
		super();
	}

	/**
	 * Execute command.
	 * 
	 * @param command
	 *            the command
	 * @param editingDomain
	 *            the transactional editingDomain
	 * @return true, if successful
	 */
	public boolean executeCommand(ICommand command, TransactionalEditingDomain editingDomain) {
		commandExecutionDepth2++;
		long start = System.currentTimeMillis();

		String indent = ""; //$NON-NLS-1$

		final boolean reallyTraceIt = GFPreferences.getInstance().isCPUProfilingTraceActive() && commandExecutionDepth2 < 4;

		if (reallyTraceIt && T.racer().info()) {
			for (int i = 1; i < commandExecutionDepth2; i++) {
				indent = indent + "  "; //$NON-NLS-1$
			}
			if (commandExecutionDepth2 == 1) {
				T.racer().info("\n"); //$NON-NLS-1$
			}
			T.racer().info(indent + ">start CommandExec.executeCommand(command, connection) => openCommandGroup => " //$NON-NLS-1$
					+ command.getDescription() + " >"); //$NON-NLS-1$
		}

		boolean ret;
		CommandStack commandStack = editingDomain.getCommandStack();

		try {
			GFPreparableCommand gfRecordingCommand = new GFPreparableCommand(editingDomain, command);
			commandStack.execute(gfRecordingCommand); // gfRecordingCommand.execute();
			ret = gfRecordingCommand.getExecutionResult();
		} catch (Exception ex) {
			T.racer().error("CommandExec.executeCommand() " + ex, ex); //$NON-NLS-1$
			commandStack.undo();
			ret = true;
		}

		long stop = System.currentTimeMillis();

		if (reallyTraceIt && T.racer().info()) {
			T.racer().info(indent + "<stop (" + (stop - start) + " ms) " //$NON-NLS-1$ //$NON-NLS-2$
					+ "CommandExec.executeCommand(command, connection) => closeCommandGroup => " + command.getDescription() + " <"); //$NON-NLS-1$ //$NON-NLS-2$
			if (commandExecutionDepth2 == 1) {
				T.racer().info("\n"); //$NON-NLS-1$
			}
		}
		commandExecutionDepth2--;

		return ret;
	}

	/**
	 * Gets the singleton.
	 * 
	 * @return the singleton
	 */
	public static CommandExec getSingleton() {
		if (singleton == null) {
			singleton = new CommandExec();
		}
		return singleton;
	}

	/**
	 * Execute feature with context.
	 * 
	 * @param feature
	 *            the feature
	 * @param context
	 *            the context
	 */
	public static void executeFeatureWithContext(IFeature feature, IContext context) {
		GenericFeatureCommandWithContext genericFeatureCommandWithContext = new GenericFeatureCommandWithContext(feature, context);
		TransactionalEditingDomain editingDomain = feature.getFeatureProvider().getDiagramTypeProvider().getDiagramEditor()
				.getEditingDomain();
		CommandExec.getSingleton().executeCommand(genericFeatureCommandWithContext, editingDomain);
	}

}
