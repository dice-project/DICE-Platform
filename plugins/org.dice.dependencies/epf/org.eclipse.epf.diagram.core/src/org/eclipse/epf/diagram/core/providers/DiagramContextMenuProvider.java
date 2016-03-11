/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.diagram.core.providers;

import org.eclipse.epf.diagram.core.part.AbstractDiagramEditor;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;

/**
 * Class to handle the popupmenu items 
 * @author Shashidhar Kannoori
 */
public class DiagramContextMenuProvider extends
		org.eclipse.gmf.runtime.diagram.ui.providers.DiagramContextMenuProvider {

	/**
	 * @param part
	 * @param viewer
	 */
	public DiagramContextMenuProvider(IWorkbenchPart part, EditPartViewer viewer) {
		super(part, viewer);
	}
	
	@Override
	public void menuAboutToShow(IMenuManager menu) {
		super.menuAboutToShow(menu);
		IContributionItem item =  find(ActionIds.ACTION_DELETE_FROM_DIAGRAM);
		if(item != null)
		item.setVisible(false);

		IContributionItem item1=  find(ActionIds.ACTION_DELETE_FROM_MODEL);
		if(item1 != null)
		item1.setVisible(false);
		
		IContributionItem item2 = find(ActionIds.MENU_NAVIGATE);
		if(item2 != null)
		item2.setVisible(false);
		
		IContributionItem item3 = find(ActionFactory.PROPERTIES.getId());
		if(item3 != null)
		item3.setVisible(false);
		
		EditDomain domain = getViewer().getEditDomain();
		if(domain instanceof DiagramEditDomain){
			IEditorPart part=  ((DiagramEditDomain)domain).getEditorPart();
			if(part instanceof AbstractDiagramEditor){
				((AbstractDiagramEditor)part).contributeToContextMenu(menu);
			}
		}
	}
}
