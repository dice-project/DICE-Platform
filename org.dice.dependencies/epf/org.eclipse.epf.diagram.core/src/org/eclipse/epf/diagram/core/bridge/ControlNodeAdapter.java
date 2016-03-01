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
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.diagram.core.bridge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkOrder;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ControlNode;

/**
 * @author Phong Nguyen Le
 *
 * @since 1.2
 */
public class ControlNodeAdapter extends NodeAdapter {

	protected boolean addToUMA(ActivityEdge link) {
		if (!super.addToUMA(link))
			return false;

		Activity diagramActivity = (Activity) BridgeHelper.getMethodElement(getDiagram());
		MethodElement srcElement = BridgeHelper.getMethodElement(link.getSource());
		if (srcElement instanceof WorkBreakdownElement) {
			if (BridgeHelper.isSynchBar(link.getTarget())) {
				Collection targetActNodes = new ArrayList();
				BridgeHelper.getSyncBarTargetNodes(link
						.getTarget(), targetActNodes);
				WorkBreakdownElement pred = (WorkBreakdownElement) srcElement;
				for (Iterator<?> iter = targetActNodes.iterator(); iter.hasNext();) {
					ActivityNode node = (ActivityNode) iter.next();
					WorkBreakdownElement targetElement = (WorkBreakdownElement) BridgeHelper.getMethodElement(node);
					addWorkOrder(node, targetElement, null, pred, diagramActivity, null);
				}
			}
		} else if (BridgeHelper.isSynchBar(link.getSource())) {
			// Predecessor should be created only in case of
			// Syncronization Bar, not for DecisionPoint.
			Collection srcActNodes = new ArrayList();
			BridgeHelper.getSyncBarSourceNodes(link.getSource(), srcActNodes);
			Collection targetActNodes = new ArrayList();
			BridgeHelper.getSyncBarTargetNodes(link.getTarget(), targetActNodes);

			for (Iterator iter = targetActNodes.iterator(); iter.hasNext();) {
				ActivityNode node = ((ActivityNode) iter.next());
				WorkBreakdownElement succ = (WorkBreakdownElement) BridgeHelper.getMethodElement(node);
				for (Iterator<?> iterator = srcActNodes.iterator(); iterator
				.hasNext();) {
					ActivityNode srcNode = (ActivityNode) iterator.next();
					WorkBreakdownElement pred = (WorkBreakdownElement) BridgeHelper
						.getMethodElement(srcNode);
					addWorkOrder(node, succ, srcNode, pred, diagramActivity, null);
				}
			}
		}

		return true;
	}
	
	protected void removeFromUMA(ActivityEdge link, ActivityNode oldSource, ActivityNode oldTarget) {
		org.eclipse.epf.uma.Activity diagramActivity = (org.eclipse.epf.uma.Activity) BridgeHelper.getMethodElement(getDiagram());
		ProcessPackage pkg = (ProcessPackage) diagramActivity.eContainer();
		Collection<Object> srcActNodes = new ArrayList<Object>();			
		BridgeHelper.getSourceNodes(srcActNodes, oldSource, WorkBreakdownElement.class);
		MethodElement targetElement = BridgeHelper.getMethodElement(oldTarget);
		if (targetElement instanceof WorkBreakdownElement) {
			WorkBreakdownElement targetWbe = (WorkBreakdownElement) targetElement;			
			for (Iterator<?> iterator = srcActNodes.iterator(); iterator.hasNext();) {
				ActivityNode node = (ActivityNode) iterator.next();
				if(targetWbe.getSuperActivities() == diagramActivity) { // target element is local
					if (BridgeHelper.canRemoveAllPreds(link, node, oldTarget)) {
						MethodElement srcElement = BridgeHelper.getMethodElement(node);					
						WorkOrder wo;
						while ((wo = UmaUtil.findWorkOrder(targetWbe, srcElement)) != null) {
							getActionManager().doAction(IActionManager.REMOVE, targetWbe, 
									UmaPackage.Literals.WORK_BREAKDOWN_ELEMENT__LINK_TO_PREDECESSOR, wo, -1);
						}
					}
				} else { // target is an inherited element
					WorkBreakdownElement pred = (WorkBreakdownElement) BridgeHelper.getMethodElement(node);
					WorkOrder order = ProcessUtil.findWorkOrder(diagramActivity, targetWbe, pred);
					if(order != null) {
						getActionManager().doAction(IActionManager.REMOVE, pkg, 
								UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS, order, -1);
					}
				}
			}
		} else if (oldTarget instanceof ControlNode) {
			Collection<Object> targetActNodes = new ArrayList<Object>();
			BridgeHelper.getTargetNodes(targetActNodes,
					oldTarget, WorkBreakdownElement.class);

			// remove the work orders of target activities that have the
			// predecessor in srcActNodes
			//
			for (Iterator<?> iter = targetActNodes.iterator(); iter.hasNext();) {
				ActivityNode node = ((ActivityNode) iter.next());
				WorkBreakdownElement targetE = (WorkBreakdownElement) BridgeHelper.getMethodElement(node);
				for (Iterator<?> iterator = srcActNodes.iterator(); iterator
						.hasNext();) {
					ActivityNode prednode = (ActivityNode) iterator.next();
					if(targetE.getSuperActivities() == diagramActivity) {  // target element is local
						if (BridgeHelper.canRemoveAllPreds(link, prednode,
								node)) {
							MethodElement srcElement = BridgeHelper.getMethodElement(prednode);
							WorkOrder wo;
							while ((wo = UmaUtil.findWorkOrder(targetE, srcElement)) != null) {
								getActionManager().doAction(IActionManager.REMOVE, targetE, 
										UmaPackage.Literals.WORK_BREAKDOWN_ELEMENT__LINK_TO_PREDECESSOR, wo, -1);
							}
						}
					} else { // target element is inherited
						WorkBreakdownElement pred = (WorkBreakdownElement) BridgeHelper.getMethodElement(prednode);
						WorkOrder order = ProcessUtil.findWorkOrder(diagramActivity, targetE, pred);
						if(order != null) {
							getActionManager().doAction(IActionManager.REMOVE, pkg, 
									UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS, order, -1);
						}
					}
				}
			}
		}
	}
}
