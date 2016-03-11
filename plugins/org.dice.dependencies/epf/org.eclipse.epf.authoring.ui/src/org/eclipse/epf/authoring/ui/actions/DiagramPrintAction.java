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

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.print.PrintGraphicalViewerOperation;
import org.eclipse.gef.ui.actions.PrintAction;
import org.eclipse.swt.SWT;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.ui.IWorkbenchPart;

/**
 * 
 * Print diagram
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 *
 */
public class DiagramPrintAction extends PrintAction {

	GraphicalViewer viewer;
	/**
	 * @param part
	 */
	public DiagramPrintAction(IWorkbenchPart part, GraphicalViewer viewer) {
		super(part);
		this.viewer = viewer;
	}
	
	/* 
	 * @see org.eclipse.gef.ui.actions.PrintAction#run()
	 */
	public void run() {
		//GraphicalViewer viewer;
		PrintDialog dialog = new PrintDialog(viewer.getControl().getShell(), SWT.NULL);
		PrinterData data = dialog.open();
		
		if (data != null) {
			PrintGraphicalViewerOperation op = 
						new PrintGraphicalViewerOperation(new Printer(data), viewer);
			op.run(getWorkbenchPart().getTitle());
		}
		super.run();
	}
	
	/**
	 * Set graphical viewer
	 * @param viewer
	 */
	public void setViewer(GraphicalViewer viewer) {
		this.viewer = viewer;
	}
	
	/**
	 * Return graphical viewer
	 * @return viewer
	 */
	public GraphicalViewer getViewer() {
		return viewer;
	}

}
