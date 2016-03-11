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
package org.eclipse.epf.authoring.gef.edit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.epf.diagram.model.ActivityDetailDiagram;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.TaskNode;
import org.eclipse.epf.diagram.model.WorkProductComposite;
import org.eclipse.epf.diagram.model.WorkProductDescriptorNode;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;


/**
 * @author Phong Nguyen Le
 * @author Shashidhar Kannoori
 * @since 1.0
 * 
 */
public class ActivityDetailDiagramEditPart extends DiagramEditPart {

	int y = 60;

	boolean xaxisbegin = false;

	boolean loading = true;

	final static int spacing = 40;

	final static int yspacing = 20;

	boolean miscellaneous = false;

	int x = 10;

	public static final String OUTPUTS = "outputs"; //$NON-NLS-1$

	public static final String INPUTS = "inputs"; //$NON-NLS-1$

	private List recentlyAddedParts = new ArrayList();

	/** 
	 * EditPart for ActivityDetailDiagram.
	 */
	public ActivityDetailDiagramEditPart(ActivityDetailDiagram model) {
		super(model);
	}

	/**
	 * Override method super.resetChildrenLocations. In this, All the Task's
	 * MandatoryInput workproducts placed on top. and RoleTask composite in the
	 * middle. and Task's output workproducts south.
	 */
	protected void resetChildrenLocations() {
		final int yspacing = 40;
		List rolecompositeList = new ArrayList();
		List inputworkproductCompositeList = new ArrayList();
		List outputWorkProductCompositeList = new ArrayList();
		List miscList = new ArrayList();
		List taskList = new ArrayList();
		this.recentlyAddedParts = new ArrayList();

		HashMap map = new HashMap();

		for (Iterator iter = getChildren().iterator(); iter.hasNext();) {
			Object child = iter.next();
			Node n = (Node) ((EditPart) child).getModel();
			if (n.getLocation() == null || n.getLocation().x == -1) {
				this.recentlyAddedParts.add(child);
			}
			if (child instanceof RoleTaskCompositeEditPart) {
				rolecompositeList.add(child);
				List list = ((RoleTaskCompositeEditPart) child).getChildren();
				for (Iterator it = list.iterator(); it.hasNext();) {
					Object obj = it.next();
					Node objNode = (Node) ((NodeEditPart) obj).getModel();
					if (objNode instanceof TaskNode) {
						taskList.add(objNode);
						HashMap taskMap = new HashMap();
						List inputList = new ArrayList();
						List outputList = new ArrayList();
						taskMap.put("inputs", inputList); //$NON-NLS-1$
						taskMap.put("outputs", outputList); //$NON-NLS-1$
						map.put(objNode, taskMap);
					}
				}
			} else if (child instanceof WorkProductCompositeEditPart) {
				GraphicalEditPart part = (GraphicalEditPart) child;
				if (part.getModel() instanceof WorkProductComposite) {
					WorkProductComposite node = (WorkProductComposite) part
							.getModel();
					if (node.getType() == WorkProductComposite.INPUTS) {
						inputworkproductCompositeList.add(child);
					} else if (node.getType() == WorkProductComposite.OUTPUTS) {
						outputWorkProductCompositeList.add(child);
					}
					calcSize(child);
				}
			} else {
				miscList.add(child);
			}
		}

		// Set size for the rolecomposite.
		if (!rolecompositeList.isEmpty()) {
			for (Iterator itor = rolecompositeList.iterator(); itor.hasNext();) {
				Object object = itor.next();
				List list = ((RoleTaskCompositeEditPart) object).getChildren();
				for (Iterator itor1 = list.iterator(); itor1.hasNext();) {
					Object obj = itor1.next();
					if (obj instanceof DescriptorNodeEditPart) {
						if (((DescriptorNodeEditPart) obj).getModel() instanceof TaskNode) {
							TaskNode taskNode = (TaskNode) ((DescriptorNodeEditPart) obj)
									.getModel();
							TaskDescriptor task = (TaskDescriptor) taskNode
									.getObject();
							for (Iterator wpitor = inputworkproductCompositeList
									.iterator(); wpitor.hasNext();) {
								WorkProductCompositeEditPart wpEditPart = (WorkProductCompositeEditPart) wpitor
										.next();
								WorkProductComposite wpCompositeNode = (WorkProductComposite) wpEditPart
										.getModel();
								Object wpObject = wpCompositeNode.getObject();
								if (wpObject.equals(task)) {
									int width = wpEditPart.getFigure()
											.getPreferredSize().width;
									int height = ((DescriptorNodeEditPart) obj)
											.getFigure().getPreferredSize().height;
									((DescriptorNodeEditPart) obj)
											.getFigure()
											.setPreferredSize(
													new Dimension(width, height));
									if (((WorkProductComposite) wpEditPart
											.getModel()).getType() == WorkProductComposite.INPUTS) {
										List inputList = (List) ((HashMap) map
												.get(taskNode)).get(INPUTS);
										inputList.add(wpEditPart);
									} else if (((WorkProductComposite) wpEditPart
											.getModel()).getType() == WorkProductComposite.OUTPUTS) {
										List outputList = (List) ((HashMap) map
												.get(taskNode)).get(OUTPUTS);
										outputList.add(wpEditPart);
									}
								}
							}
						}
					}
				}
			}
		}

		if (!rolecompositeList.isEmpty()) {
			for (int i = 0; i < rolecompositeList.size(); i++) {
				List confirmedSeqInputList = new ArrayList();
				List confirmedSeqOutputList = new ArrayList();
				List list = ((RoleTaskCompositeEditPart) rolecompositeList
						.get(i)).getChildren();
				for (int j = 0; j < list.size(); j++) {
					DescriptorNodeEditPart task = (DescriptorNodeEditPart) list
							.get(j);
					if (task.getModel() instanceof TaskNode) {
						TaskNode tasknode = (TaskNode) task.getModel();
						for (int k = 0; k < inputworkproductCompositeList
								.size(); k++) {
							WorkProductComposite wpcomp = ((WorkProductComposite) ((WorkProductCompositeEditPart) inputworkproductCompositeList
									.get(k)).getModel());
							if (wpcomp.getObject().equals(tasknode.getObject())) {
								if (wpcomp.getType() == WorkProductComposite.INPUTS) {
									confirmedSeqInputList
											.add(inputworkproductCompositeList
													.get(k));
								}
							}
						}
						for (int k = 0; k < outputWorkProductCompositeList
								.size(); k++) {
							WorkProductComposite wpcomp = ((WorkProductComposite) ((WorkProductCompositeEditPart) outputWorkProductCompositeList
									.get(k)).getModel());
							if (wpcomp.getObject().equals(tasknode.getObject())) {
								if (wpcomp.getType() == WorkProductComposite.OUTPUTS) {
									confirmedSeqOutputList
											.add(outputWorkProductCompositeList
													.get(k));
								}
							}
						}
					}
				}
				if (!confirmedSeqInputList.isEmpty()) {
					setObjectsLocations(confirmedSeqInputList);
					y = cachedPoint.y + cachedHeight + yspacing;
				}
				List roleC = new ArrayList();
				roleC.add(rolecompositeList.get(i));
				setObjectsLocations(roleC);

				y = cachedPoint.y + cachedHeight + yspacing;
				if (!confirmedSeqOutputList.isEmpty()) {
					setObjectsLocations(confirmedSeqOutputList);
					y = cachedPoint.y + cachedHeight + yspacing;
				}
			}

		}

		if (!miscList.isEmpty()) {
			if (debug) {
				System.out.println("Print Size of misc: " + miscList.size()); //$NON-NLS-1$
			}
			if (!loading) {
				miscellaneous = true;
			}
			setObjectsLocations(miscList);
			miscellaneous = false;
		}

		loading = false;
	}

	private void calcSize(Object child) {
		List list = ((GraphicalEditPart) child).getChildren();
		int largestWidth = 0;
		int largestHeight = 0;
		for (Iterator it = list.iterator(); it.hasNext();) {
			Object obj = it.next();
			Node objNode = (Node) ((NodeEditPart) obj).getModel();
			if (objNode instanceof WorkProductDescriptorNode) {
				int nodeWidth = ((DescriptorNodeEditPart) obj).getFigure()
						.getPreferredSize().width;
				int nodeHeight = ((DescriptorNodeEditPart) obj).getFigure()
						.getPreferredSize().height;
				if (nodeWidth > largestWidth)
					largestWidth = nodeWidth;
				if (nodeHeight > largestHeight)
					largestHeight = nodeHeight;
			}
		}
		((WorkProductCompositeEditPart) child).getFigure().setPreferredSize(
				new Dimension(largestWidth * 2 + 10, 20 + largestHeight
						* (list.size() / 2 + 1)));
	}

	private void setObjectsLocations(List list) {

		xaxisbegin = true;
		x = 10;
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object child = iter.next();
			if (child instanceof GraphicalEditPart) {
				GraphicalEditPart part = (GraphicalEditPart) child;
				if (part.getModel() instanceof Node) {
					Node node = (Node) part.getModel();
					Point p = node.getLocation();
					if (p == null || p.x == -1) {
						int viewerWidth = part.getRoot().getViewer()
								.getControl().getBounds().width;
						if (!xaxisbegin) {
							x = cachedPoint.x + cachedWidth + spacing;
						}
						// check if p is null, create Point.
						if (p == null)
							p = new Point();

						if (!loading && miscellaneous) {
							p.x = x;
							p.y = 10;
						} else {
							p.x = x;
							p.y = y;
						}
						node.setLocation(p);
						if (!miscellaneous)
							cachedPoint = p;
						cachedWidth = part.getFigure().getPreferredSize().width;
						if (part.getFigure().getPreferredSize().height > cachedHeight)
							cachedHeight = part.getFigure().getPreferredSize().height;
						xaxisbegin = false;
						if (debug) {
							System.out
									.println("Point : " + new Point(x, y).toString() + "And width=" //$NON-NLS-1$ //$NON-NLS-2$
											+ cachedWidth
											+ ", Height=" + cachedHeight + ", Width of Viewer: " + viewerWidth); //$NON-NLS-1$ //$NON-NLS-2$
						}
					}
				}
			}
		}
	}

	/**
	 * Returns recently addded editparts. Usually called 
	 * by diagram services to do autolayout adjustment.
	 */
	public List getRecentlyAddedParts() {
		return recentlyAddedParts;
	}

	/**
	 * Clear the recently added editparts from the list.
	 */
	public void clearRecentlyAddedParts() {
		recentlyAddedParts.clear();
	}

}
