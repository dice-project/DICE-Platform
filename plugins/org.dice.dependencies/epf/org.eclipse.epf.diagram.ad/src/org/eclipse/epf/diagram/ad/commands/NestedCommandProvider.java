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
//------------------------------------------------------------------------------
//Copyright (c) 2005, 2007 IBM Corporation and others.
//All rights reserved. This program and the accompanying materials
//are made available under the terms of the Eclipse Public License v1.0
//which accompanies this distribution, and is available at
//http://www.eclipse.org/legal/epl-v10.html
//
//Contributors:
//IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.diagram.ad.commands;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.epf.diagram.core.DiagramCorePlugin;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.core.services.DiagramManager;
import org.eclipse.epf.library.edit.command.INestedCommandProvider;
import org.eclipse.epf.library.edit.util.IDiagramManager;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.ui.util.TypeConverter.TypeConversionCommand;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Process;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.ActivityNode;

/**
 * @author Phong Nguyen Le
 * @since 1.2
 */
public class NestedCommandProvider implements INestedCommandProvider {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.command.INestedCommandProvider#createNestedCommand(org.eclipse.emf.common.command.Command)
	 */
	public Command createNestedCommand(Command command) {
		if(command instanceof TypeConversionCommand) {
			return createNestedCommandForTypeConversion(command);
		}
		return null;
	}

	private Command createNestedCommandForTypeConversion(Command command) {
		// change type of activity node in diagram
		//
		Collection<?> result = command.getResult();
		Activity activity = null;
		if(!result.isEmpty()) {
			Object e = result.iterator().next();
			if(e instanceof Activity) {
				activity = (Activity) e;
			}
			else {
				return null;
			}
		}
		if(activity != null) {
			Process proc = TngUtil.getOwningProcess(activity);
			if(proc != null) {
				DiagramManager mgr = DiagramManager.getInstance(proc, this);
				try {
					String newType = BridgeHelper.getType(activity);					
					List<Diagram> diagrams = mgr.getDiagrams(activity.getSuperActivities(), IDiagramManager.ACTIVITY_DIAGRAM);
					SetTypeCommand cmd = new SetTypeCommand();
					for (Diagram diagram : diagrams) {
						org.eclipse.uml2.uml.Activity umlAct = (org.eclipse.uml2.uml.Activity) diagram.getElement();
						for (ActivityNode node : umlAct.getNodes()) {
							String uriStr = BridgeHelper.getEAnnotationDetail(node, BridgeHelper.UMA_URI);
							if(uriStr != null) {
								URI uri = URI.createURI(uriStr);
								String guid = uri.fragment();
								if(activity.getGuid().equals(guid)) {
									View view = BridgeHelper.getView(diagram, node);
									cmd.prepare(view, node, newType);
								}
							}
						}
					}
					if(cmd.canExecute()) {
						return cmd;
					}
				} catch (CoreException e) {
					DiagramCorePlugin.getDefault().getLogger().logError(e);
				}
				finally {
					mgr.removeConsumer(this);
				}
			}
		}
		return null;
	}

	public Command createRelatedObjects(Collection createdElements,
			Command createCommand) {
		return null;
	}

	public Command removeRelatedObjects(Collection deletedElements,
			Command deleteCommand) {
		return null;
	}

}
