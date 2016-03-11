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
package org.eclipse.epf.publishing.ui.wizards;

import java.io.File;

import org.eclipse.epf.authoring.gef.viewer.ActivityDiagramService;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.diagram.ui.service.DiagramImageService;
import org.eclipse.epf.library.layout.ElementLayoutManager;
import org.eclipse.epf.publishing.services.AbstractViewBuilder;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;


/**
 * The publish configuration progress dialog.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public class PublishProgressMonitorDialog extends ProgressMonitorDialog {

	private Composite diagramViewerHolder;

	private AbstractViewBuilder viewBuilder;

	/**
	 * Creates a new instance.
	 */
	public PublishProgressMonitorDialog(Shell parent,
			AbstractViewBuilder viewBuilder) {
		super(parent);
		this.viewBuilder = viewBuilder;
	}

	protected Control createDialogArea(Composite parent) {
		Control ctrl = super.createDialogArea(parent);

		if (viewBuilder != null) {
			// Create the diagram viewer holder and the diagram service.
			diagramViewerHolder = new Composite(parent, SWT.NONE);
			diagramViewerHolder.setLayoutData(new GridData(1, 1));
			diagramViewerHolder.setLayout(new GridLayout());
			diagramViewerHolder.setVisible(false);

			ElementLayoutManager layoutMgr = viewBuilder.getLayoutMgr();
			String newDiagrams = AuthoringUIPlugin.getDefault().getPreferenceStore().getString("PUBLISH_NEW_DIAGRAM"); //$NON-NLS-1$
			Boolean newDiagram = new Boolean(true);
			if(newDiagrams != null && newDiagrams.length() > 0){
				newDiagram = new Boolean(newDiagrams);
			}
			if (newDiagram.booleanValue()) {
				DiagramImageService diagramService = new DiagramImageService(
						diagramViewerHolder,
						new File(layoutMgr.getPublishDir()));
				diagramService.setConfig(layoutMgr.getConfiguration());
				diagramService.setPublishedUnCreatedADD(viewBuilder
						.getOptions().isPublishUnopenADD());
				diagramService.setPublishADForActivityExtension(viewBuilder
						.getOptions().isPublishBaseAD());
				layoutMgr.setActivityDiagramService(diagramService);
			} else {
				ActivityDiagramService diagramService = new ActivityDiagramService(
						diagramViewerHolder,
						new File(layoutMgr.getPublishDir()));
				diagramService.setPublishedUnCreatedADD(viewBuilder
						.getOptions().isPublishUnopenADD());
				diagramService.setPublishADForActivityExtension(viewBuilder
						.getOptions().isPublishBaseAD());
				layoutMgr.setActivityDiagramService(diagramService);
			}
		}

		return ctrl;
	}

	/*
	 * (non-Javadoc) Method declared on Window.
	 */
	public boolean close() {
		
		viewBuilder = null;
		return super.close();
	}
	
}
