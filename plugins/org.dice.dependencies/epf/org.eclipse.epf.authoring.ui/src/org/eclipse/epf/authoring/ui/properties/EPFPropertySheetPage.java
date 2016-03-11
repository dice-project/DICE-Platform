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
package org.eclipse.epf.authoring.ui.properties;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.authoring.ui.editors.ProcessEditor;
import org.eclipse.epf.authoring.ui.forms.ProcessBreakdownStructureFormPage;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.views.properties.IPropertySheetEntry;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertySheetEntry;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * This class represents property sheet page for process elements
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 * 
 */
public class EPFPropertySheetPage extends TabbedPropertySheetPage {

	private IPropertySheetEntry rootEntry;

	private static IPropertySourceProvider sourceProvider;

	private static ISelection selection = null;

	private static IActionManager actionMgr;

	private static ProcessEditor processEditor;

	public static String formPageID = null;
	
	private static ProcessBreakdownStructureFormPage formPage = null;

	public EPFPropertySheetPage(ProcessEditor editor) {
		super((ITabbedPropertySheetPageContributor) editor);

		actionMgr = ((ProcessEditor) editor).getActionManager();
		processEditor = editor;

	}

	/**
	 * Get action manager
	 * @return
	 * 			Action Manager
	 */
	public static IActionManager getActionManager() {
		return actionMgr;
	}

	/**
	 * Get editor associated with property sheet page
	 * @return
	 * 			Process Editor
	 */
	public static ProcessEditor getEditor() {
		return processEditor;
	}

	/**
	 * Sets the given property source provider as the property source provider.
	 * <p>
	 * Calling this method is only valid if you are using this page's default
	 * root entry.
	 * </p>
	 * 
	 * @param newProvider
	 *            the property source provider
	 */
	public void setPropertySourceProvider(IPropertySourceProvider newProvider) {
		sourceProvider = newProvider;
		if (rootEntry == null) {
			// create a new root
			PropertySheetEntry root = new PropertySheetEntry();

			rootEntry = root;
		}
		if (sourceProvider != null) {
			// set the property source provider for root entry
			if (rootEntry instanceof PropertySheetEntry) {
				((PropertySheetEntry) rootEntry)
						.setPropertySourceProvider(sourceProvider);
			}
		}
	}

	/**
	 * Return property source provider
	 */
	public static IPropertySourceProvider getPropertySourceProvider() {
		return sourceProvider;
	}

	/**
	 * Return selection of the object from breakdown structure of the process editor
	 */
	public static ISelection getSelection() {
		return selection;
	}


	/**
	 * @see org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage#selectionChanged(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IWorkbenchPart part, ISelection sel) {
		selection = sel;

		if (part instanceof ProcessEditor) {
			processEditor = (ProcessEditor) part;
			actionMgr = processEditor.getActionManager();
			IFormPage activePage = processEditor.getActivePageInstance();
			if (activePage instanceof ProcessBreakdownStructureFormPage)
				formPage = (ProcessBreakdownStructureFormPage) activePage;
			formPageID = activePage != null ? activePage.getId() : null;
		}

		super.selectionChanged(part, selection);
		
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage#handlePartActivated(org.eclipse.ui.IWorkbenchPart)
	 */
	public void handlePartActivated(IWorkbenchPart part) {
		if (part instanceof ProcessEditor) {
			processEditor = (ProcessEditor) part;
			selection = processEditor.getSelection();
			actionMgr = processEditor.getActionManager();
			IFormPage activePage = processEditor.getActivePageInstance();
			if (activePage instanceof ProcessBreakdownStructureFormPage)
				formPage = (ProcessBreakdownStructureFormPage) activePage;
			formPageID = activePage != null ? activePage.getId() : null;
		}

		super.handlePartActivated(part);
		
		this.refresh();
	}

	/**
	 * Get current adapter factory of editor
	 * @return
	 * 			Adapter Factory
	 */
	public static AdapterFactory getAdapterFactory() {
		return formPage.getAdapterFactory();
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage#refresh()
	 */
	public void refresh() {

		// will see if this works..
		try {
			super.refresh();
		} catch (Exception e) {

		}
		
// TODO - get rid of internal APIs.
//		if (this.getCurrentTab() != null) {
//			this.getCurrentTab().refresh();
//
//			// also refresh section
//			ISection section = this.getCurrentTab().getSectionAtIndex(0);
//			if (section != null) {
//				section.refresh();
//			}
//		}
	}

}
