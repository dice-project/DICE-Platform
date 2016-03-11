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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * This interface class is used to support undo operations in the
 * form editors.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public interface IActionManager {

	public static final int SET = Notification.SET;

	public static final int ADD = Notification.ADD;

	public static final int REMOVE = Notification.REMOVE;

	public static final int ADD_MANY = Notification.ADD_MANY;

	public static final int REMOVE_MANY = Notification.REMOVE_MANY;

	/**
	 * Performs the action as described in parameters
	 * 
	 * @param actionType
	 *            one of the above defined action constants
	 * @param object
	 *            the object to perform action upon
	 * @param feature
	 *            the feature to be modified
	 * @param value
	 *            the feature value
	 * @param index
	 *            specify -1 if no index
	 * @return <code>true</code> if successfull, <code>false</code>
	 *         otherwise
	 */
	boolean doAction(int actionType, EObject object,
			EStructuralFeature feature, Object value, int index);

	/**
	 * Executes the given command
	 * 
	 * @param cmd
	 * @return <code>true</code> if successfull, <code>false</code>
	 *         otherwise
	 */
	boolean execute(IResourceAwareCommand cmd);

	void undo();

	void redo();

	boolean undoAll();

	void saveIsDone();

	boolean isSaveNeeded();

	/**
	 * Gets resources first modified by this action manager
	 */
	Collection getModifiedResources();

	void dispose();

}