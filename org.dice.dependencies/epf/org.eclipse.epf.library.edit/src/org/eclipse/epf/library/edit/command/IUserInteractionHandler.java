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

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.edit.provider.IItemLabelProvider;

/**
 * Interface to handle user interactions during command execution
 * 
 * @author Phong Nguyen Le - Oct 3, 2006
 * @since 1.0
 */
public interface IUserInteractionHandler {
	/**
	 * Delivers messages to users
	 * 
	 * @author Phong Nguyen Le - Oct 26, 2006
	 * @since 1.0
	 */
	public static interface IMessenger {
		/**
		 * Shows information message
		 * 
		 * @param title
		 * @param msg
		 */
		void showInfo(String title, String msg);

		/**
		 * Shows warning message
		 * 
		 * @param title
		 * @param msg
		 */
		void showWarning(String title, String msg);

		/**
		 * Shows warning message with reason
		 * 
		 * @param title
		 * @param msg
		 * @param string
		 */
		void showWarning(String title, String msg, String reason);

		/**
		 * Shows error message
		 * 
		 * @param title
		 * @param msg
		 */
		void showError(String title, String msg);

		/**
		 * Shows error message with status
		 * 
		 * @param title
		 * @param msg
		 * @param status
		 */
		void showError(String title, String msg, IStatus status);

		/**
		 * Shows error message with reason and exception
		 * 
		 * @param title
		 * @param msg
		 * @param reason
		 * @param exception
		 */
		void showError(String title, String msg, String reason,
				Exception exception);

		void showError(String title, String msg, String reason, String details,
				Exception exception);
	}

	/**
	 * Action constant for user acceptance
	 */
	static final int ACTION_OK = 0;

	/**
	 * Action constant for user cancellation
	 */
	static final int ACTION_CANCEL = 1;

	/**
	 * Action constant for retry
	 */
	static final int ACTION_RETRY = 2;

	/**
	 * Action constant for abort
	 */
	static final int ACTION_ABORT = 3;
	
	static final int ACTION_YES = 4;
	
	static final int ACTION_NO = 5;

	/**
	 * Selects objects from the specified list <code>objectsToSelect</code>.
	 * 
	 * @param objectsToSelect
	 *            objects for user to select
	 * @param labelProvider
	 *            label provider used to display the given objects to user
	 * @param multiple
	 *            if <code>true</code> will allow multiple selection, single
	 *            selection if <code>false</code>
	 * @param title
	 *            the title of this selection action to display to user
	 * @param msg
	 *            the message to display to user
	 * @return the collection of selected elements, or <code>null</code> if
	 *         the selection was canceled.
	 */
	List select(List objectsToSelect, IItemLabelProvider labelProvider,
			boolean multiple, List intitialSelection, String title, String msg);

	/**
	 * Selects only one action from the given array of actions. The actions must
	 * be the constants {@link #ACTION_ABORT}, {@link #ACTION_CANCEL},
	 * {@link #ACTION_OK}, {@link #ACTION_RETRY}, ACTION_XXX
	 * 
	 * @param actions
	 * @param title
	 * @param msg
	 * @param status
	 * @return
	 */
	int selectOne(int[] actions, String title, String msg, IStatus status);

	/**
	 * Requests user inputs.
	 * 
	 * @param title
	 * @param msg
	 * @param userInputs
	 *            list of {@link UserInput user inputs} that have information
	 *            about input requests and reference to the input. The input
	 *            reference should be set to the object that user specified or
	 *            selected.
	 * @return false if user cancelled this request
	 */
	boolean requestInput(String title, String msg, List<UserInput> userInputs);

	/**
	 * Gets UI context
	 * 
	 * @return the UI context
	 */
	Object getUIContext();

	/**
	 * Gets the messenger
	 * 
	 * @return
	 */
	IMessenger getMessenger();
}
