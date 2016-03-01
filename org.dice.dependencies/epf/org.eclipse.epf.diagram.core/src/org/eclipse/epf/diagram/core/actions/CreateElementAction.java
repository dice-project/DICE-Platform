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
package org.eclipse.epf.diagram.core.actions;

import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.epf.diagram.core.part.AbstractDiagramEditor;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateUnspecifiedTypeRequest;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author Shashidhar Kannoori
 *
 */
public class CreateElementAction extends Action {

	private IWorkbenchPart targetPart;
	private List elementTypes = new ArrayList<ElementType>();
	private PreferencesHint preferenceHint = null;
	/**
	 * 
	 */
	public CreateElementAction(IWorkbenchPart targetPart, String text) {
		super(text);
		this.targetPart = targetPart;
	}

	@Override
	public void run() {
		Request req= new CreateUnspecifiedTypeRequest(getElementTypes(), getPreferenceHint());
		DiagramEditPart diagramEditPart = getDiagramEditPart();
		if(diagramEditPart.isEditModeEnabled()){
			Command cmd = getDiagramEditPart().getCommand(req);
			if(cmd.canExecute()){
				cmd.execute();
				if(cmd instanceof ICommandProxy){
					CommandResult results = ((ICommandProxy)cmd).getICommand().getCommandResult();
					System.out.println(results);
				}
			}
		}
		
	}
	
	private DiagramEditPart getDiagramEditPart(){
		if(targetPart != null && targetPart instanceof AbstractDiagramEditor){
			return ((AbstractDiagramEditor)targetPart).getDiagramEditPart();
		}
		return null;
	}

	public List getElementTypes() {
		return elementTypes;
	}

	public void setElementTypes(List elementTypes) {
		this.elementTypes = elementTypes;
	}

	public PreferencesHint getPreferenceHint() {
		return preferenceHint;
	}

	public void setPreferenceHint(PreferencesHint preferenceHint) {
		this.preferenceHint = preferenceHint;
	}
	
}
