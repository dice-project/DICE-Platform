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
package org.eclipse.epf.authoring.ui.actions;

import java.util.List;

import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.dialogs.ADDInfoDialog;
import org.eclipse.epf.authoring.ui.editors.AbstractDiagramEditor;
import org.eclipse.epf.authoring.ui.editors.ActivityDetailDiagramEditor;
import org.eclipse.epf.authoring.ui.filters.DescriptorConfigurationFilter;
import org.eclipse.epf.diagram.model.util.GraphicalDataHelper;
import org.eclipse.epf.diagram.model.util.GraphicalDataManager;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.Process;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * Action to show the ADDInfoDialog, to show the ActivityDetailDiagrams
 * in the process. Not in use.
 * @author Shashidhar Kannoori
 */
public class ActivityDetailDiagramInfoAction extends Action {

	protected Object selected;
	
	public ActivityDetailDiagramInfoAction() {
		super("Activity Detail Diagram Info"); //$NON-NLS-1$
	}
	
	public void run() {
		
		IFilter filter = new ADDFilter(null, null, null);
		
		ADDInfoDialog fd = new ADDInfoDialog(PlatformUI
				.getWorkbench().getActiveWorkbenchWindow()
				.getShell(), filter, getObject(), "Activity", //$NON-NLS-1$
				null);

		fd.setBlockOnOpen(true);
		fd.setTitle("Activity"); //$NON-NLS-1$
		fd.open();
		List list = fd.getSelectedItems();
		
		try {
			IEditorPart parent = getPagex().getActiveEditor();
			IEditorInput input = new ActivityDetailDiagramEditor.EditorInput(
					list.get(0), Suppression.getSuppression((Process)getObject()));
			IEditorPart part = getPagex().openEditor(input,
					ActivityDetailDiagramEditor.class
					.getName());
			if (part instanceof AbstractDiagramEditor) {
				AbstractDiagramEditor editor = (AbstractDiagramEditor) part;
				editor.setParentEditor(parent);
			}
		} catch (PartInitException exception) {
			AuthoringUIPlugin.getDefault().getLogger().logError(
					exception);
		}
		// open editor

		super.run();
	}
	
	public Object getObject(){
		return selected;
	}
	
	public IWorkbenchPage getPagex(){
		return null;
	}
	
	public boolean isEnabled() {
		return super.isEnabled();
	}
	public class ADDFilter extends DescriptorConfigurationFilter {
		public ADDFilter(MethodConfiguration methodConfig, Viewer viewer, String filterStr) {
			super(methodConfig, viewer, filterStr);
		}

		public boolean accept(Object obj) {
			// TODO Auto-generated method stub
			if(obj instanceof CapabilityPattern) return true;
			if(obj instanceof Activity){
				org.eclipse.epf.uma.Diagram d = GraphicalDataManager.getInstance()
				.getUMADiagram((Activity) obj, GraphicalDataHelper.ACTIVITY_DETAIL_DIAGRAM, false);
				if(d != null ) return true;
			}
			return false;
		}
	}
}
