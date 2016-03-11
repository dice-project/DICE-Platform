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
package org.eclipse.epf.library.edit.process;

import java.util.Collection;
import java.util.List;

import org.eclipse.epf.library.edit.IStatefulItemProvider;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.util.PredecessorList;


/**
 * This is the base interface of all item providers for breakdown element used
 * by the Process Editor. It defines the constants for column names and methods
 * to support row index, roll-up view, getting/setting breakdown element's
 * attributes, predecessor list...
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public interface IBSItemProvider extends //ILibraryItemProvider,
		IStatefulItemProvider {

	/** Column ID constants */
	public static final String COL_NAME = "name"; //$NON-NLS-1$

	public static final String COL_PREFIX = "prefix"; //$NON-NLS-1$

	public static final String COL_ID = "id"; //$NON-NLS-1$

	public static final String COL_PREDECESSORS = "predecessors"; //$NON-NLS-1$

	public static final String COL_IS_REPEATABLE = "is_repeatable"; //$NON-NLS-1$

	public static final String COL_IS_ONGOING = "is_ongoing"; //$NON-NLS-1$

	public static final String COL_IS_EVENT_DRIVEN = "is_event_driven"; //$NON-NLS-1$

	public static final String COL_ENTRY_STATE = "entry_state"; //$NON-NLS-1$

	public static final String COL_EXIT_STATE = "exit_state"; //$NON-NLS-1$

	public static final String COL_TEAMS = "teams"; //$NON-NLS-1$  

	public static final String COL_TYPE = "type"; //$NON-NLS-1$

	public static final String COL_MODEL_INFO = "model_info"; //$NON-NLS-1$

	public static final String COL_PRESENTATION_NAME = "presentation_name"; //$NON-NLS-1$

	public static final String COL_DELIVERABLE = "deliverable"; //$NON-NLS-1$

	public static final String COL_IS_OPTIONAL = "is_optional"; //$NON-NLS-1$

	public static final String COL_IS_PLANNED = "is_planned"; //$NON-NLS-1$

	public static final String COL_HAS_MULTIPLE_OCCURRENCES = "has_multiple_occurrences"; //$NON-NLS-1$

	public static final String[] COLUMNS = { COL_NAME, COL_PREFIX, COL_ID,
			COL_PREDECESSORS, COL_IS_REPEATABLE, COL_IS_ONGOING,
			COL_IS_EVENT_DRIVEN, COL_ENTRY_STATE, COL_EXIT_STATE, COL_TEAMS,
			COL_TYPE, COL_MODEL_INFO, COL_PRESENTATION_NAME, COL_DELIVERABLE };
	
	int getId();

	void setId(int id);

	// IBSItemProvider getItemProvider(EObject eObj);

	void setParent(Object obj);

	Object getTopItem();

	void setTopItem(Object top);

	boolean isRolledUp();

	void setRolledUp(boolean b);

	boolean isFirstElement(Object obj);

	boolean isLastElement(Object obj);

	// List getFilteredBreakdownElements(Object activityObj, Object obj);

	void moveUp(Object obj, IActionManager actionMgr);

	void moveDown(Object obj, IActionManager actionMgr);

	Collection getEClasses();

	// GraphicalData getGraphicalData();

	// void setGraphicalData(GraphicalData data);

	/**
	 * Gets the attribute of the given object as a string.
	 * 
	 * @param object
	 * @param property
	 *            one of the column ID constants COL_XXX
	 * @return String value of the attribute
	 */
	String getAttribute(Object object, String property);

	/**
	 * Sets the attribute of the given object
	 * 
	 * @param object
	 * @param property
	 *            one of the column ID constants COL_XXX
	 * @param textValue
	 */
	void setAttribute(Object object, String property, String textValue);

	List getListeners();

	PredecessorList getPredecessors();

	Boolean isExpanded();

	void setExpanded(Boolean b);

	IResourceAwareCommand createDropCommand(Object owner, List dropElements);

}
