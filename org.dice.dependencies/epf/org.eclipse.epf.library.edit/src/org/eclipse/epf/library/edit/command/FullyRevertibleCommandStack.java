//------------------------------------------------------------------------------
// Copyright (c) 2005, 2006 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.edit.command;

import java.util.EventObject;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.process.command.BSDragAndDropCommand;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.util.model.util.StringResource;
import org.eclipse.osgi.util.NLS;

/**
 * A command stack that tries its best to fully revert to its initial state.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class FullyRevertibleCommandStack extends BasicCommandStack {

	public static interface IErrorReporter {
		void showError(String msg);
	}

	private IErrorReporter errReporter;

	private ActionManager actionMgr;

	public FullyRevertibleCommandStack(ActionManager actionMgr) {
		this.actionMgr = actionMgr;
	}

	public FullyRevertibleCommandStack(ActionManager actionMgr,
			IErrorReporter errReporter) {
		this(actionMgr);
		this.errReporter = errReporter;
	}

	public void setErrorReporter(IErrorReporter errReporter) {
		this.errReporter = errReporter;
	}
	
	private void registerAsModifier(Command command) {
		Set<Resource> modifiedResources = TngUtil.getModifiedResources(command);
		if(!modifiedResources.isEmpty()) {
			for (Iterator iter = modifiedResources.iterator(); iter.hasNext();) {
				Resource resource = (Resource) iter.next();
				if (!(resource instanceof StringResource))
					actionMgr.registerAsModifierOf(resource);
			}
		}
	}

	public boolean doExecute(Command command) {
		if (!command.canUndo())
			return false; // throw new IllegalArgumentException("Cannot
		// execute command that cannot de undone");
		super.execute(command);
		
		// special handling for ActivityDropCommand
		//
		// TODO: all commands must return a correct value in getAffectedObject()
		// if getAffectedObject() return an empty collection, the action manager
		// will not register it as a modifier of the resources returned by getModifiedResources()
		// and the editor might not be marked dirty as the result.
		//
		if(command instanceof BSDragAndDropCommand && command.getAffectedObjects().isEmpty()) {
			if(top != -1) {
				commandList.remove(top--);
				if(top != -1) {
					mostRecentCommand = (Command) commandList.get(top);
				}
				else {
					mostRecentCommand = null;
				}
			}
			return false;
		}
		
		boolean ret = getMostRecentCommand() != null;
		if (ret && actionMgr != null) {			
			registerAsModifier(command);
		}
		notifyListeners(new CommandStackChangedEvent(this,
				CommandStackChangedEvent.EXECUTED));
		return ret;
	}

	public void execute(Command command) {
		doExecute(command);
	}

	public void redo() {
		super.redo();
		notifyListeners(new CommandStackChangedEvent(this,
				CommandStackChangedEvent.EXECUTED));
	}

	public void undo() {
		if (canUndo()) {
			Command command = (Command) commandList.get(top--);
			try {
				command.undo();
				mostRecentCommand = command;
			} catch (RuntimeException exception) {
				CommonPlugin.INSTANCE
						.log(new WrappedException(
								CommonPlugin.INSTANCE
										.getString("_UI_IgnoreException_exception"), exception).fillInStackTrace()); //$NON-NLS-1$

				mostRecentCommand = null;
				top++;

				if (errReporter != null) {
					Object[] object = new Object[1];
					object[0] = command;
					object[1] = exception;
					errReporter.showError(NLS.bind(LibraryEditResources.undoCommandError_msg, object)); 
				}
			}
			notifyListeners(new CommandStackChangedEvent(this,
					CommandStackChangedEvent.UNDO));
		}
	}

	public boolean undoAll() {
		try {
			while (canUndo()) {
				Command command = (Command) commandList.get(top--);
				try {
					command.undo();
					mostRecentCommand = command;
				} catch (RuntimeException exception) {
					CommonPlugin.INSTANCE
							.log(new WrappedException(
									CommonPlugin.INSTANCE
											.getString("_UI_IgnoreException_exception"), exception).fillInStackTrace()); //$NON-NLS-1$

					mostRecentCommand = null;
					top++;

					if (errReporter != null) {
						// errReporter.showError("Cannot undo command: '" +
						// command + "', exception: " + exception);
						Object[] object = new Object[1];
						object[0] = command;
						object[1] = exception;
						errReporter
								.showError(NLS.bind(LibraryEditResources.undoCommandError_msg, object)); 
					}

					return false;
				}
			}
		} finally {
			notifyListeners(new CommandStackChangedEvent(this,
					CommandStackChangedEvent.UNDO_ALL));
		}

		return true;
	}

	protected void notifyListeners(EventObject event) {
		for (Iterator i = listeners.iterator(); i.hasNext();) {
			((CommandStackListener) i.next()).commandStackChanged(event);
		}
	}

	protected void notifyListeners() {
		//
	}

	public void saveIsDone() {
		super.saveIsDone();
		notifyListeners(new CommandStackChangedEvent(this,
				CommandStackChangedEvent.SAVED));
		commandList.clear();
		top = -1;
		saveIndex = -1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.BasicCommandStack#isSaveNeeded()
	 */
	public boolean isSaveNeeded() {
		return super.isSaveNeeded();
	}

}
