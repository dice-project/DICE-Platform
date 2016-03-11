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
package org.eclipse.epf.library.prefs;

import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.util.LibraryEditConstants;

/**
 * Constants to be used in preference store.
 * 
 * @author Phong Nguyen Le
 * @author Jinhua Xi
 * @since 1.0
 */
public interface PreferenceConstants {

	public static final String PREFIX = "org.eclipse.epf.library.pref."; //$NON-NLS-1$

	// moved from to here to share the constants
	public static final String PREF_WBS_COLUMNS = LibraryEditConstants.PREF_WBS_COLUMNS;

	public static final String PREF_TBS_COLUMNS = LibraryEditConstants.PREF_TBS_COLUMNS;

	public static final String PREF_WPBS_COLUMNS = LibraryEditConstants.PREF_WPBS_COLUMNS;

	// define the default columns for wbs, tbs, and wpbs layout
	public static final String[] DEFAULT_WBS_COLUMNS = {
			IBSItemProvider.COL_PRESENTATION_NAME,
			IBSItemProvider.COL_ID,
			// IBSItemProvider.COL_PREFIX,
			IBSItemProvider.COL_PREDECESSORS, IBSItemProvider.COL_MODEL_INFO,
			IBSItemProvider.COL_TYPE, IBSItemProvider.COL_IS_PLANNED,
			IBSItemProvider.COL_IS_REPEATABLE,
			IBSItemProvider.COL_HAS_MULTIPLE_OCCURRENCES,
			IBSItemProvider.COL_IS_ONGOING,
			IBSItemProvider.COL_IS_EVENT_DRIVEN,
			IBSItemProvider.COL_IS_OPTIONAL };

	public static final String[] DEFAULT_TBS_COLUMNS = {
			IBSItemProvider.COL_PRESENTATION_NAME,
			// IBSItemProvider.COL_ID,
			// IBSItemProvider.COL_PREFIX,
			IBSItemProvider.COL_MODEL_INFO, IBSItemProvider.COL_TEAMS,
			IBSItemProvider.COL_TYPE, IBSItemProvider.COL_IS_PLANNED,
			IBSItemProvider.COL_HAS_MULTIPLE_OCCURRENCES,
			IBSItemProvider.COL_IS_OPTIONAL };

	public static final String[] DEFAULT_WPBS_COLUMNS = {
			IBSItemProvider.COL_PRESENTATION_NAME,
			// IBSItemProvider.COL_ID,
			// IBSItemProvider.COL_PREFIX,
			IBSItemProvider.COL_MODEL_INFO, IBSItemProvider.COL_ENTRY_STATE,
			IBSItemProvider.COL_EXIT_STATE, IBSItemProvider.COL_DELIVERABLE,
			IBSItemProvider.COL_TYPE, IBSItemProvider.COL_IS_PLANNED,
			IBSItemProvider.COL_HAS_MULTIPLE_OCCURRENCES,
			IBSItemProvider.COL_IS_OPTIONAL };

}
