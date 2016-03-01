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
package org.eclipse.epf.authoring.gef.viewer;

import org.eclipse.epf.authoring.gef.edit.DiagramEditPart;
import org.eclipse.epf.authoring.gef.edit.WPDiagramEditPartFactory;
import org.eclipse.epf.diagram.model.ModelFactory;
import org.eclipse.epf.diagram.model.WorkProductDependencyDiagram;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.swt.widgets.Composite;

/**
 * The graphical viewer for the WP Dependency diagram.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class WPDependencyDiagramViewer extends AbstractDiagramGraphicalViewer {

	public WPDependencyDiagramViewer(Composite parent) {
		super(parent);

	}

	protected EditPartFactory createEditPartFactory() {
		return new WPDiagramEditPartFactory();
	}

	protected EditPart createEditPart(Object e, IFilter filter, Suppression sup) {
		WorkProductDependencyDiagram diagram = ModelFactory.eINSTANCE
				.createWorkProductDependencyDiagram();
		diagram.setFilter(filter);
		if (sup != null) {
			diagram.setSuppression(sup);
		}

		diagram.setObject(e);
		EditPart part = new DiagramEditPart(diagram);
		part.setModel(diagram);
		return part;
	}

	protected String getDiagramType() {
		return ResourceHelper.DIAGRAM_TYPE_WP_DEPENDENCY;
	}

}
