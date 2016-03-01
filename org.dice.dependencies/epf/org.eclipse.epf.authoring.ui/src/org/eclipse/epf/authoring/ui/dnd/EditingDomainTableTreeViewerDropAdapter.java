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
package org.eclipse.epf.authoring.ui.dnd;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.edit.command.DragAndDropCommand;
import org.eclipse.emf.edit.command.DragAndDropFeedback;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.dnd.EditingDomainViewerDropAdapter;
import org.eclipse.epf.library.edit.util.ProcessScopeUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.jface.viewers.TableTreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Widget;

/**
 * Drop adapter for drag and drop
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class EditingDomainTableTreeViewerDropAdapter extends
		EditingDomainViewerDropAdapter {

	public EditingDomainTableTreeViewerDropAdapter(EditingDomain domain,
			Viewer viewer) {
		super(domain, viewer);
	}


	/**
	 * @see org.eclipse.emf.edit.ui.dnd.EditingDomainViewerDropAdapter#dragEnter(org.eclipse.swt.dnd.DropTargetEvent)
	 */
	public void dragEnter(DropTargetEvent event) {
		if (event.detail == DND.DROP_NONE) {
			event.detail = DND.DROP_COPY;
		}
		super.dragEnter(event);
	}

	/**
	 * @see org.eclipse.emf.edit.ui.dnd.EditingDomainViewerDropAdapter#dragOver(org.eclipse.swt.dnd.DropTargetEvent)
	 */
	public void dragOver(DropTargetEvent event) {
		if (event.detail == DND.DROP_NONE) {
			event.detail = DND.DROP_COPY;
		}

		super.dragOver(event);
	}

	/**
	 * @see org.eclipse.emf.edit.ui.dnd.EditingDomainViewerDropAdapter#dropAccept(org.eclipse.swt.dnd.DropTargetEvent)
	 */
	public void dropAccept(DropTargetEvent event) {
		if (event.detail == DND.DROP_NONE) {
			event.detail = DND.DROP_COPY;
		}

		super.dropAccept(event);
	}

	/**
	 * @see org.eclipse.emf.edit.ui.dnd.EditingDomainViewerDropAdapter#helper(org.eclipse.swt.dnd.DropTargetEvent)
	 */
	protected void helper(DropTargetEvent event) {
		Table table = null;
		TableItem item = null;
		if (viewer instanceof TableTreeViewer) {
			table = ((TableTreeViewer) viewer).getTableTree().getTable();
			item = table.getItem(table.toControl(event.x, event.y));
			event.item = item;
		}

		superHelper(event);

		if (item != null && (event.feedback & DND.FEEDBACK_SELECT) != 0) {
			table.setSelection(new TableItem[] { item });
		}
	}

	/**
	 * Get drop target
	 * 
	 * @param item
	 * @return drop target object
	 */
	protected Object getDropTarget(Widget item) {
		return extractDropTarget(item);
	}

	/**
	 * This is called to indicate that the drop action should be invoked.
	 * @see org.eclipse.emf.edit.ui.dnd.EditingDomainViewerDropAdapter#drop(org.eclipse.swt.dnd.DropTargetEvent)
	 */
	public void drop(DropTargetEvent event) {
		// A command was created if the source was available early, and the
		// information used to create it was cached...
		if (dragAndDropCommandInformation != null) {
			// Recreate the command.
			command = dragAndDropCommandInformation.createCommand();
		} else {
			// Otherwise, the source should be available now as event.data, and
			// we can create the command.
			source = extractDragSource(event.data);
			Object target = getDropTarget(event.item);
			command = DragAndDropCommand.create(domain, target,
					getLocation(event), event.operations, originalOperation,
					source);
		}

		// If the command can execute...
		if (command.canExecute()) {
			// Execute it.
			domain.getCommandStack().execute(command);
		} else {
			// Otherwise, let's call the whole thing off.
			event.detail = DND.DROP_NONE;
			command.dispose();
		}

		// Clean up the state.
		command = null;
		commandTarget = null;
		source = null;
	}

	/**
	 * This method is called the same way for each of the
	 * {@link org.eclipse.swt.dnd.DropTargetListener} methods, except for leave
	 * and drop. If the source is available early, it creates or revalidates the
	 * {@link DragAndDropCommand}, and updates the event's detail (operation)
	 * and feedback (drag under effect), appropriately.
	 */
	protected void superHelper(DropTargetEvent event) {
		// If we can't do anything else, we'll provide the default select
		// feedback and enable auto-scroll and auto-expand effects.
		event.feedback = DND.FEEDBACK_SELECT | getAutoFeedback();

		// If we don't already have it, try to get the source early. We can't
		// give feedback if it's not available yet (this is platform-dependent).
		if (source == null) {
			source = getDragSource(event);
			if (source == null)
				return;
		}

		// Get the target object from the item widget and the mouse location in
		// it.
		Object target = getDropTarget(event.item);
		float location = getLocation(event);

		// Determine if we can create a valid command at the current location.
		boolean valid = false;
		modifiySource(target);
		
		// If we don't have a previous cached command...
		if (command == null) {
			// We'll need to keep track of the information we use to create the
			// command, so that we can recreate it in drop.
			dragAndDropCommandInformation = new DragAndDropCommandInformation(
					domain, target, location, event.operations,
					originalOperation, source);

			// Remember the target; create the command and test if it is
			// executable.
			commandTarget = target;
			command = dragAndDropCommandInformation.createCommand();
			valid = command.canExecute();
		} else {
			// Check if the cached command can provide DND
			// feedback/revalidation.
			if (target == commandTarget
					&& command instanceof DragAndDropFeedback) {
				// If so, revalidate the command.
				valid = ((DragAndDropFeedback) command).validate(target,
						location, event.operations, originalOperation, source);

				// Keep track of any changes to the command information.
				dragAndDropCommandInformation = new DragAndDropCommandInformation(
						domain, target, location, event.operations,
						originalOperation, source);
			} else {
				// If not, dispose the current command and create a new one.
				dragAndDropCommandInformation = new DragAndDropCommandInformation(
						domain, target, location, event.operations,
						originalOperation, source);
				commandTarget = target;
				command.dispose();
				command = dragAndDropCommandInformation.createCommand();
				valid = command.canExecute();
			}
		}

		// If this command can provide detailed drag and drop feedback...
		if (command instanceof DragAndDropFeedback) {
			// Use it for the operation and drag under effect.
			DragAndDropFeedback dragAndDropFeedback = (DragAndDropFeedback) command;
			event.detail = dragAndDropFeedback.getOperation();
			event.feedback = dragAndDropFeedback.getFeedback()
					| getAutoFeedback();
		} else if (!valid) {
			// There is no executable command, so we'd better nix the whole
			// deal.
			event.detail = DND.DROP_NONE;
		}
	}
	
	private void modifiySource(Object target) {
		if (source == null || source.isEmpty()) {
			return;
		}
		if (!(target instanceof Activity)) {
			return;
		}
		Process proc = ProcessUtil.getProcess((Activity) target);
		if (! ProcessScopeUtil.getInstance().isConfigFree(proc)) {
			return;
		}

		boolean toModify = false; 
		for (Object obj : source) {
			if (obj instanceof ProcessComponent) {
				toModify = true;
				break;
			}
		}
		if (!toModify) {
			return;
		}
		List<Object> list = new ArrayList<Object>();
		for (Object obj : source) {
			if (obj instanceof ProcessComponent) {
				Object process = ((ProcessComponent) obj).getProcess();
				if (process != null) {
					obj = process;
				}
			}
			list.add(obj);
		}
		source = list;
	}

}
