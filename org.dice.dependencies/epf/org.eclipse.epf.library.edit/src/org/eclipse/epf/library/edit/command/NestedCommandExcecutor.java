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
package org.eclipse.epf.library.edit.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.util.ExtensionManager;

/**
 * @author Phong Nguyen Le
 * @since 1.1
 */
public class NestedCommandExcecutor {
	private Command command;
	private ArrayList<Command> nestedCommands;

	public NestedCommandExcecutor(Command command) {
		this.command = command;
	}
	
	public List<Command> getNestedCommands() {
		if(nestedCommands == null) {
			nestedCommands = new ArrayList<Command>();
		}
		return nestedCommands;
	}

	public void executeNestedCommands() {
		List<INestedCommandProvider> nestedCommandProviders = ExtensionManager.getNestedCommandProviders();
		if(!nestedCommandProviders.isEmpty()) {
			for (INestedCommandProvider cmdProvider : nestedCommandProviders) {
				try {
					Command cmd = cmdProvider.createNestedCommand(command);
					if(cmd != null && cmd.canExecute()) {							
						cmd.execute();
						getNestedCommands().add(cmd);
					}
				}
				catch(Exception e) {
					LibraryEditPlugin.getDefault().getLogger().logError(e);
				}
			}
		}	
	}

	public void undoNestedCommands() {
		if(nestedCommands != null && !nestedCommands.isEmpty()) {
			for(int i = nestedCommands.size() - 1; i > -1; i--) {
				Command cmd = (Command) nestedCommands.get(i);
				try {
					if(cmd.canUndo()) {
						cmd.undo();
					}
				}
				catch(Exception e) {
					LibraryEditPlugin.getDefault().getLogger().logError(e);
				}
				finally {
					try { cmd.dispose(); } catch(Exception e) {}
				}
			}
			nestedCommands.clear();
		}
	}

	public void dispose() {
		if(nestedCommands != null && !nestedCommands.isEmpty()) {
			for(int i = nestedCommands.size() - 1; i > -1; i--) {
				Command cmd = (Command) nestedCommands.get(i);
				try { cmd.dispose(); } catch(Exception e) {}
			}
			nestedCommands.clear();
		}
	}
	
	public Collection<Resource> getModifiedResources() {
		Collection<Resource> modifiedResources = new HashSet<Resource>();
		if(nestedCommands != null && !nestedCommands.isEmpty()) {
			for(int i = nestedCommands.size() - 1; i > -1; i--) {
				Command cmd = (Command) nestedCommands.get(i);
				if(cmd instanceof IResourceAwareCommand) {
					try {
						Collection resources = ((IResourceAwareCommand)cmd).getModifiedResources();
						if(resources != null) {
							modifiedResources.addAll(resources);
						}
					}
					catch(Exception e) {
						LibraryEditPlugin.getDefault().getLogger().logError(e);
					}
				}
			}
		}
		return modifiedResources.isEmpty() ? Collections.EMPTY_SET : modifiedResources;
	}
}
