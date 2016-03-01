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

import java.util.Iterator;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditorInput;
import org.eclipse.epf.authoring.ui.preferences.ApplicationPreferenceConstants;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.HTMLTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

/**
 * The drag adapter for the Library Viewer.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class LibraryViewerDragAdapter extends ViewerDragAdapter {

	/**
	 * Creates a new <code>EditorDragAdapter</code>.
	 */
	public LibraryViewerDragAdapter(Viewer viewer) {
		super(viewer);
	}

	/**
	 * Called when dragging is initiated.
	 */
	public void dragStart(DragSourceEvent event) {
		super.dragStart(event);
		IStructuredSelection selection = (IStructuredSelection) viewer
			.getSelection();
		event.doit = false;
		for (Iterator iter = selection.toList().iterator();iter.hasNext();) {
			Object selectedElement = TngUtil.unwrap(iter.next());
			if (selectedElement instanceof MethodElement &&
					!(selectedElement instanceof MethodConfiguration) &&
					!(selectedElement instanceof ContentPackage)) {
				event.doit = true;
			}
		}		
	}

	/**
	 * Called to transfer the data.
	 */
	public void dragSetData(DragSourceEvent event) {
		if (HTMLTransfer.getInstance().isSupportedType(event.dataType) ||
				TextTransfer.getInstance().isSupportedType(event.dataType)) {
			IStructuredSelection selection = (IStructuredSelection) viewer
					.getSelection();
			
			String links = ""; //$NON-NLS-1$
			for (Iterator iter = selection.toList().iterator();iter.hasNext();) {
				Object selectedElement = TngUtil.unwrap(iter.next());
				if (selectedElement instanceof MethodElement) {
					String linkText = getHyperlink((MethodElement) selectedElement);					
					if (Platform.getOS().equals(Platform.WS_WIN32)) {
						if (links.length() > 0) {
							links += StrUtil.LINE_FEED;
						}
						links += linkText;
					}
					else {
						if (links.length() > 0) {
							links += StrUtil.LINE_FEED;
						}
						links += linkText;
					}
				}
				
			}
			if (links.length() > 0) {
				event.data = links;
			}
		}
		super.dragSetData(event);
	}

	/**
	 * Returns the hyperlink for the given Method element.
	 */
	protected String getHyperlink(MethodElement dragElement) {
		MethodElement dropElement = null;
		IWorkbenchPage activePage = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		IEditorPart activeEditor = activePage.getActiveEditor();
		if (activeEditor != null) {
			IEditorInput editorInput = activeEditor.getEditorInput();
			if (editorInput instanceof MethodElementEditorInput) {
				dropElement = ((MethodElementEditorInput) editorInput)
						.getMethodElement();
			}
		}
		
		if(dragElement instanceof ProcessComponent){
			dragElement = ((ProcessComponent)dragElement).getProcess();
		}

		String href = ResourceHelper.getUrl(dragElement, dropElement, ".html"); //$NON-NLS-1$
		
		// obey LibraryViewPrefPage
		String defaultLinkType = AuthoringUIPlugin.getDefault().getPreferenceStore().getString(ApplicationPreferenceConstants.PREF_LIB_VIEW_DND_DEFAULT_LINKTYPE);
		String text = ""; //$NON-NLS-1$
		if (ResourceHelper.ELEMENT_LINK_CLASS_elementLinkWithType.equals(defaultLinkType)) {
			text = ResourceHelper.getElementLinkText(dragElement, true);
		} else {
			text = ResourceHelper.getElementLinkText(dragElement, false);
		}
		
		return ResourceHelper.getElementLink(dragElement, text,
				"file://" + href, defaultLinkType); //$NON-NLS-1$
	}

}
