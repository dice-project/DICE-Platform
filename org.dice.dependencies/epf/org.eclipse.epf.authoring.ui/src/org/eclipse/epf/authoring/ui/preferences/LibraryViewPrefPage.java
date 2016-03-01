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
package org.eclipse.epf.authoring.ui.preferences;

import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.library.ui.LibraryUIResources;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.gmf.runtime.diagram.ui.internal.pagesetup.DefaultValues;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.WorkspaceViewerProperties;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Library view options preference page
 * 
 * @author Jeff Hardy
 * @since 1.2
 */
public class LibraryViewPrefPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	
	public LibraryViewPrefPage() {
		super(GRID);
	}

	@Override
	protected void createFieldEditors() {
		// TODO Auto-generated method stub
		Composite parent = getFieldEditorParent();
		RadioGroupFieldEditor defaultLinkTypeEditor = new RadioGroupFieldEditor(
	    	     			ApplicationPreferenceConstants.PREF_LIB_VIEW_DND_DEFAULT_LINKTYPE,
	    	     			AuthoringUIResources.defaultLinkTypePrefLabel, 1,
	    	     			new String[][] {
	    	     					{LibraryUIResources.elementLink_name, ResourceHelper.ELEMENT_LINK_CLASS_elementLink},
	    	     					{LibraryUIResources.elementLinkWithType_name, ResourceHelper.ELEMENT_LINK_CLASS_elementLinkWithType},
	    	     					{LibraryUIResources.elementLinkWithUserText_name, ResourceHelper.ELEMENT_LINK_CLASS_elementLinkWithUserText}
	    	     			}, parent, true);
		defaultLinkTypeEditor.setPreferenceStore(doGetPreferenceStore());
		addField(defaultLinkTypeEditor);
	}
	
	public void init(IWorkbench workbench) {
	}
	
	protected IPreferenceStore doGetPreferenceStore() {
		return AuthoringUIPlugin.getDefault().getPreferenceStore();
	}
	
	/**
	 * Initializes printing preferences to their default values.
	 */
	public static void initDefaults(IPreferenceStore store) {
		store.setDefault(ApplicationPreferenceConstants.PREF_LIB_VIEW_DND_DEFAULT_LINKTYPE,
				ResourceHelper.ELEMENT_LINK_CLASS_elementLink);
	}


}
