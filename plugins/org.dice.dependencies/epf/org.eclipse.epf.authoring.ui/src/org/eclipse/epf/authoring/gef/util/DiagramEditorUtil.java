//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.authoring.gef.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.epf.diagram.ui.service.DiagramEditorHelper;
import org.eclipse.epf.library.edit.ICommandListener;
import org.eclipse.epf.library.edit.process.command.VaryActivityCommand;
import org.eclipse.ui.IEditorReference;

/**
 * Provides instructure to listens to actions like LocalContribution, Local
 * Replace in WorkBreakdown Structure. Loads at time of plugin load.
 * 
 * @see VaryActivityCommand
 * 
 * @author Shashidhar Kannoori
 * @author Shilpa Toraskar
 * @since 1.0
 */
public final class DiagramEditorUtil {

	private static DiagramEditorUtil instance = null;

	private List<ICommandListener> commandListeners;

	private DiagramEditorUtil() {
	}

	public static DiagramEditorUtil getInstance() {
		if (instance == null) {
			synchronized (DiagramEditorUtil.class) {
				if (instance == null) {
					instance = new DiagramEditorUtil();
				}
			}
		}
		return instance;
	}

	public List<ICommandListener> getVaryCommandListeners() {
		if (commandListeners == null) {
			commandListeners = new ArrayList<ICommandListener>();
		}

		commandListeners.add(new ICommandListener() {

			public void notifyExecuted(Command command) {
				// Get contributor/replacer of the activity.
				Object wrapper = ((VaryActivityCommand) command).getWrapper();
				DiagramEditorHelper.closeDiagramEditors(wrapper, 
						new ArrayList<IEditorReference>());
				DiagramEditorHelper.refreshParentDiagramEditors(wrapper,
						new ArrayList<IEditorReference>(), true);
			}

			public Class getCommandType() {
				return VaryActivityCommand.class;
			}

			public void preUndo(Command command) {
				// Get old contributor/replacer of the activity.
				Collection list = ((VaryActivityCommand) command).getResult();

				List listx = new ArrayList();
				for (Iterator iterator = list.iterator(); iterator.hasNext();) {
					Object wrapper = iterator.next();
					DiagramEditorHelper.closeDiagramEditors(wrapper, 
							new ArrayList<IEditorReference>());
					listx.clear();
				}

			}
			public void preExecute(Command command) {
			}

			public void postUndo(Command command) {
				DiagramEditorHelper.refreshParentDiagramEditors(((VaryActivityCommand) command).superActivity,
							new ArrayList<IEditorReference>(), false);
			}
		});

		return commandListeners;
	}

}