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
package org.eclipse.epf.library.preferences;

import org.eclipse.epf.common.preferences.IPreferenceStoreWrapper;
import org.eclipse.epf.library.LibraryPlugin;

/**
 * The Library preferences.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class LibraryPreferences {

	/**
	 * Preference key for storing the URI of the method library that was opened
	 * in the last session.
	 */
	public static final String SAVED_METHOD_LIRARY_URI = "savedMethodLibraryURI"; //$NON-NLS-1$

	/**
	 * Preference key for storing the type of the method library that was opened
	 * in the last session.
	 */
	public static final String SAVED_METHOD_LIRARY_TYPE = "savedMethodLibraryType"; //$NON-NLS-1$

	/**
	 * Preference key for storing the discard unresolved references.
	 */
	public static final String DISCARD_UNRESOLVED_REFERENCES = "discardUnresolvedReferences"; //$NON-NLS-1$

	/**
	 * Preference key for use new extends semantics.
	 */
	public static final String USE_NEW_EXTENDS_SEMANTICS = "useNewExtendsSemantics"; //$NON-NLS-1$

	/**
	 * Preference key for the horizontal spacing between tasks and roles in the
	 * role diagram.
	 */
	public static final String ROLE_DIAGRAM_HORIZONTAL_SPACING = "roleDiagramHorizontalSpacing"; //$NON-NLS-1$

	/**
	 * Preference key for the vertical spacing between tasks and roles in the
	 * role diagram.
	 */
	public static final String ROLE_DIAGRAM_VERTICAL_SPACING = "roleDiagramVerticalSpacing"; //$NON-NLS-1$

	/**
	 * Preference key for the maximun element label text lines in the role
	 * diagram.
	 */
	public static final String ROLE_DIAGRAM_MAX_TEXT_LINES = "roleDiagramElementLabelTextLines"; //$NON-NLS-1$

	private static final boolean DEFAULT_DISCARD_UNRESOLVED_REFERENCES = false;

	private static final boolean DEFAULT_USE_NEW_EXTENDS_SEMANTICS = true;

	private static final int DEFAULT_ROLE_DIAGRAM_HORIZONTAL_SPACING = 70;

	private static final int DEFAULT_ROLE_DIAGRAM_VERTICAL_SPACING = 30;

	private static final int DEFAULT_ROLE_DIAGRAM_MAX_TEXT_LINES = 3;

	// The plug-in specific preference store.
	private static IPreferenceStoreWrapper prefStore = LibraryPlugin.getDefault()
			.getPreferenceStore();

	static {
		// Initialize the default preference values.
		prefStore.setDefault(DISCARD_UNRESOLVED_REFERENCES,
				DEFAULT_DISCARD_UNRESOLVED_REFERENCES);
		prefStore.setDefault(USE_NEW_EXTENDS_SEMANTICS,
				DEFAULT_USE_NEW_EXTENDS_SEMANTICS);

		prefStore.setDefault(ROLE_DIAGRAM_HORIZONTAL_SPACING,
				DEFAULT_ROLE_DIAGRAM_HORIZONTAL_SPACING);
		prefStore.setDefault(ROLE_DIAGRAM_VERTICAL_SPACING,
				DEFAULT_ROLE_DIAGRAM_VERTICAL_SPACING);
		prefStore.setDefault(ROLE_DIAGRAM_MAX_TEXT_LINES,
				DEFAULT_ROLE_DIAGRAM_MAX_TEXT_LINES);
	}

	/**
	 * Gets the URI of the method library that was opened in the last session.
	 */
	public static String getSavedMethodLibraryURI() {
		return prefStore.getString(SAVED_METHOD_LIRARY_URI);
	}

	/**
	 * Saves the URI of the method library that was opened in the last session.
	 */
	public static void setSavedMethodLibraryURI(String value) {
		prefStore.setValue(SAVED_METHOD_LIRARY_URI, value);
	}

	/**
	 * Gets the type of the method library that was opened in the last session.
	 */
	public static String getSavedMethodLibraryType() {
		return prefStore.getString(SAVED_METHOD_LIRARY_TYPE);
	}

	/**
	 * Saves the type of the method library that was opened in the last session.
	 */
	public static void setSavedMethodLibraryType(String value) {
		prefStore.setValue(SAVED_METHOD_LIRARY_TYPE, value);
	}

	/**
	 * Gets the default discard unresolved references preference.
	 */
	public static boolean getDefaultDiscardUnresolvedReferences() {
		return DEFAULT_DISCARD_UNRESOLVED_REFERENCES;
	}

	/**
	 * Gets the discard unresolved references preference.
	 */
	public static boolean getDiscardUnresolvedReferences() {
		return prefStore.getBoolean(DISCARD_UNRESOLVED_REFERENCES);
	}

	/**
	 * Saves the discard unresolved references preference.
	 */
	public static void setDiscardUnresolvedReferences(boolean value) {
		prefStore.setValue(DISCARD_UNRESOLVED_REFERENCES, value);
	}

	/**
	 * Gets the default use new extends semantics preference.
	 */
	public static boolean getDefaultUseNewExtendsSemantics() {
		return DEFAULT_USE_NEW_EXTENDS_SEMANTICS;
	}

	/**
	 * Gets the use new extends semantics preference.
	 */
	public static boolean getUseNewExtendsSemantics() {
		return prefStore.getBoolean(USE_NEW_EXTENDS_SEMANTICS);
	}

	/**
	 * Saves the use new extends semantics preference.
	 */
	public static void setUseNewExtendsSemantics(boolean value) {
		prefStore.setValue(USE_NEW_EXTENDS_SEMANTICS, value);
	}

	/**
	 * Gets the default role diagram horizontal spacing.
	 */
	public static int getDefaultRoleDiagramHorizontalSpacing() {
		return DEFAULT_ROLE_DIAGRAM_HORIZONTAL_SPACING;
	}

	/**
	 * Gets the role diagram horizontal spacing.
	 */
	public static int getRoleDiagramHorizontalSpacing() {
		int value = prefStore.getInt(ROLE_DIAGRAM_HORIZONTAL_SPACING);
		return value > 0 ? value : DEFAULT_ROLE_DIAGRAM_HORIZONTAL_SPACING;
	}

	/**
	 * Saves the role diagram horizontal spacing.
	 */
	public static void setRoleDiagramHorizontalSpacing(int value) {
		prefStore.setValue(ROLE_DIAGRAM_HORIZONTAL_SPACING, value);
	}

	/**
	 * Gets the default role diagram vertical spacing.
	 */
	public static int getDefaultRoleDiagramVerticalSpacing() {
		return DEFAULT_ROLE_DIAGRAM_VERTICAL_SPACING;
	}

	/**
	 * Gets the role diagram vertical spacing.
	 */
	public static int getRoleDiagramVerticalSpacing() {
		int value = prefStore.getInt(ROLE_DIAGRAM_VERTICAL_SPACING);
		return value > 0 ? value : DEFAULT_ROLE_DIAGRAM_VERTICAL_SPACING;
	}

	/**
	 * Saves the role diagram vertical spacing.
	 */
	public static void setRoleDiagramVerticalSpacing(int value) {
		prefStore.setValue(ROLE_DIAGRAM_VERTICAL_SPACING, value);
	}

	/**
	 * Gets the default role diagram element label maximum text lines.
	 */
	public static int getDefaultRoleDiagramMaximumTextLines() {
		return DEFAULT_ROLE_DIAGRAM_MAX_TEXT_LINES;
	}

	/**
	 * Gets the role diagram element label maximum text lines.
	 */
	public static int getRoleDiagramMaximumTextLines() {
		int value = prefStore.getInt(ROLE_DIAGRAM_MAX_TEXT_LINES);
		return value > 0 ? value : DEFAULT_ROLE_DIAGRAM_MAX_TEXT_LINES;
	}

	/**
	 * Saves the role diagram element label maximum text lines.
	 */
	public static void setRoleDiagramMaximumTextLines(int value) {
		prefStore.setValue(ROLE_DIAGRAM_MAX_TEXT_LINES, value);
	}

}
