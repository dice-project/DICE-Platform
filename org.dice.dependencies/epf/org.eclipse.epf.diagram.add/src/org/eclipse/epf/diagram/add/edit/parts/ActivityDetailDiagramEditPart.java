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
package org.eclipse.epf.diagram.add.edit.parts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.epf.common.CommonPlugin;
import org.eclipse.epf.diagram.add.edit.policies.ActivityDetailDiagramCanonicalEditPolicy;
import org.eclipse.epf.diagram.add.edit.policies.ActivityDetailDiagramItemSemanticEditPolicy;
import org.eclipse.epf.diagram.core.part.AbstractDiagramEditor;
import org.eclipse.epf.diagram.core.part.DiagramEditorInputProxy;
import org.eclipse.epf.diagram.core.part.IDiagramEditorInputProvider;
import org.eclipse.epf.diagram.core.part.util.DiagramEditorUtil;
import org.eclipse.epf.diagram.core.services.DiagramHelper;
import org.eclipse.epf.diagram.model.TaskNode;
import org.eclipse.epf.diagram.model.WorkProductComposite;
import org.eclipse.epf.diagram.model.WorkProductDescriptorNode;
import org.eclipse.epf.diagram.model.util.TxUtil;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.util.MeasurementUnitHelper;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;

/**
 * @modified
 */
public class ActivityDetailDiagramEditPart extends DiagramEditPart {

	Point cachedPoint = new Point(-1, -1);

	protected boolean debug = false;

	static int cachedWidth = 10;

	static int cachedHeight = 10;

	int y = 60;

	boolean xaxisbegin = false;

	boolean loading = true;

	final static int spacing = 40;

	final static int yspacing = 20;

	boolean miscellaneous = false;

	int x = 10;

	public static final String OUTPUTS = "outputs"; //$NON-NLS-1$

	public static final String INPUTS = "inputs"; //$NON-NLS-1$

	private List<EditPart> recentlyAddedParts = new ArrayList<EditPart>();

	private IMapMode mapMode;
	/**
	 * @generated
	 */
	public static final String MODEL_ID = "ADD"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 79;

	/**
	 * @generated
	 */
	public ActivityDetailDiagramEditPart(View view) {
		super(view);
		
		mapMode = MeasurementUnitHelper.getMapMode(view.getDiagram().getMeasurementUnit());
	}
	
	private View getView() {
		return (View) getModel();
	}
	
	private void doLayout() {
		EditPartViewer viewer = getViewer();
		if(viewer instanceof IDiagramEditorInputProvider) {
			IEditorPart editor = ((IDiagramEditorInputProvider)viewer).getEditor();
			if(editor instanceof AbstractDiagramEditor) {
				((AbstractDiagramEditor)editor).resetLayout();
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		Figure f = new FreeformLayer() {
			protected void layout() {
				if (hasMisplacedChildren()) {
					resetChildrenLocations();
				}
				else {
					super.layout();
				}
			}
		};

		f.setBorder(new MarginBorder(20));
		f.setLayoutManager(new FreeformLayout());

		return f;
	}

	/**
	 * Checks whether there are any misplaced children
	 * @return
	 */
	protected boolean hasMisplacedChildren() {
		List locationList = new ArrayList();

		for (Iterator iter = getChildren().iterator(); iter.hasNext();) {
			Object child = iter.next();
			if (child instanceof EditPart) {
				Object model = ((EditPart) child).getModel();
				if (model instanceof Node) {
					Node node = (Node) model;
					Location p = (Location) node.getLayoutConstraint();
					locationList.add(p);
					if (debug) {
						System.out.println("Node-" + node); //$NON-NLS-1$
						System.out.println("loc-" + p); //$NON-NLS-1$
					}
				}
			}
		}
		if (locationList.size() == 1)
			return false;
		if (locationList.size() > 0) {
			boolean needToReset = true;

			for (int i = 1; i < locationList.size(); i++) {
				Location currentLoc = (Location) locationList.get(i);
				Location previousLoc = (Location) locationList.get(i - 1);
				if (!((currentLoc.getX() == previousLoc.getX() + 10) && (currentLoc
						.getY() == previousLoc.getY() + 10))) {
					needToReset = false;
					break;
				}
			}
			return needToReset;
		}
		return false;
	}

	/**
	 * Reset all children locations
	 * In this, All the Task's MandatoryInput workproducts placed on top and RoleTask 
	 * composite in the middle. and Task's output workproducts bottom.
	 * 
	 * TODO - Have to revisit this method and do some cleanup
	 * 
	 */
	protected void resetChildrenLocations() {

		final int yspacing = 40;
		List rolecompositeList = new ArrayList();
		List inputworkproductCompositeList = new ArrayList();
		List outputWorkProductCompositeList = new ArrayList();
		List miscList = new ArrayList();
		List taskList = new ArrayList();
		this.recentlyAddedParts = new ArrayList();

		//		HashMap map = new HashMap();

		for (Iterator iter = getChildren().iterator(); iter.hasNext();) {
			Object child = iter.next();
			Node n = (Node) ((EditPart) child).getModel();
			//			if (n.getLocation() == null || n.getLocation().x == -1) {
			this.recentlyAddedParts.add((EditPart) child);
			//			}
			if (child instanceof RoleTaskCompositeEditPart) {
				rolecompositeList.add(child);

				List list = ((RoleTaskCompositeEditPart) child).getChildren();
				for (Iterator it = list.iterator(); it.hasNext();) {
					Object obj = it.next();
					if (obj instanceof TaskNodeEditPart) {
						View viewObj = (View) ((NodeEditPart) obj).getModel();
						Object o = viewObj.getElement();
						if (o instanceof TaskNode) {
							taskList.add(o);
						}
					}

				}
			} else if (child instanceof WorkProductCompositeEditPart) {
				WorkProductCompositeEditPart part = (WorkProductCompositeEditPart) child;
				View viewObj = (View) ((NodeEditPart) part).getModel();
				Object o = viewObj.getElement();

				if (o instanceof WorkProductComposite) {
					WorkProductComposite node = (WorkProductComposite) o;
					if (node.getType() == WorkProductComposite.INPUTS) {
						inputworkproductCompositeList.add(child);
					} else if (node.getType() == WorkProductComposite.OUTPUTS) {
						outputWorkProductCompositeList.add(child);
					}
					//					calcSize(child);
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
					if (obj instanceof NodeEditPart) {
						View viewObj = (View) ((NodeEditPart) obj).getModel();
						Object objNode = viewObj.getElement();
						if (objNode instanceof TaskNode) {
							TaskNode taskNode = (TaskNode) objNode;

							TaskDescriptor task = (TaskDescriptor) taskNode
									.getObject();
							int inputWidth, inputHeight;
							int outputWidth, outputHeight;
							inputWidth = inputHeight = outputWidth = outputHeight = 0;
							for (Iterator wpitor = inputworkproductCompositeList
									.iterator(); wpitor.hasNext();) {
								WorkProductCompositeEditPart wpEditPart = (WorkProductCompositeEditPart) wpitor
										.next();
								WorkProductComposite wpCompositeNode = (WorkProductComposite) ((View) wpEditPart
										.getModel()).getElement();
								Object wpObject = wpCompositeNode.getObject();
								
								if (wpObject != null && wpObject.equals(task)) {
									inputWidth = wpEditPart.getFigure()
											.getPreferredSize().width;
									inputHeight = ((NodeEditPart) obj)
											.getFigure().getPreferredSize().height;
								}
							}
							
							for (Iterator wpitor = outputWorkProductCompositeList
									.iterator(); wpitor.hasNext();) {
								WorkProductCompositeEditPart wpEditPart = (WorkProductCompositeEditPart) wpitor
										.next();
								WorkProductComposite wpCompositeNode = (WorkProductComposite) ((View) wpEditPart
										.getModel()).getElement();
								Object wpObject = wpCompositeNode.getObject();
								if (wpObject != null && wpObject.equals(task)) {
									outputWidth = wpEditPart.getFigure()
											.getPreferredSize().width;
									outputHeight = ((NodeEditPart) obj)
											.getFigure().getPreferredSize().height;			
								}
							}
							int width, height;
							if (inputWidth > outputWidth)
								width = inputWidth;
							else
								width = outputWidth;
							if (inputHeight > outputHeight) 
								height = inputHeight;
							else
								height = outputHeight;
							
							((NodeEditPart) obj)
							.getFigure()
							.setPreferredSize(
									new Dimension(width, height));
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
					Object o = list.get(j);
					if (o instanceof NodeEditPart) {
						NodeEditPart task = (NodeEditPart) list.get(j);
						if (((View) task.getModel()).getElement() instanceof TaskNode) {
							TaskNode tasknode = (TaskNode) ((View) task
									.getModel()).getElement();
							for (int k = 0; k < inputworkproductCompositeList
									.size(); k++) {
								WorkProductComposite wpcomp = (WorkProductComposite) ((View) ((WorkProductCompositeEditPart) inputworkproductCompositeList
										.get(k)).getModel()).getElement();

								if (wpcomp != null && wpcomp.getObject().equals(
										tasknode.getObject())) {
									if (wpcomp.getType() == WorkProductComposite.INPUTS) {
										confirmedSeqInputList
												.add(inputworkproductCompositeList
														.get(k));
									}
								}
							}
							for (int k = 0; k < outputWorkProductCompositeList
									.size(); k++) {
								WorkProductComposite wpcomp = (WorkProductComposite) ((View) ((WorkProductCompositeEditPart) outputWorkProductCompositeList
										.get(k)).getModel()).getElement();

								if (wpcomp.getObject().equals(
										tasknode.getObject())) {
									if (wpcomp.getType() == WorkProductComposite.OUTPUTS) {
										confirmedSeqOutputList
												.add(outputWorkProductCompositeList
														.get(k));
									}
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

		//		if (!miscList.isEmpty()) {
		//			if (debug) {
		//				System.out.println("Print Size of misc: " + miscList.size()); //$NON-NLS-1$
		//			}
		//			if (!loading) {
		//				miscellaneous = true;
		//			}
		//			setObjectsLocations(miscList);
		//			miscellaneous = false;
		//		}

		final View view = getView();
		try {
			TxUtil.runInTransaction(view.getDiagram(), new Runnable() {

				public void run() {
					view.persist();
					view.persistChildren();
				}
				
			});
		} catch (Exception e) {
			CommonPlugin.getDefault().getLogger().logError(e);
		}
		loading = false;

	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new ActivityDetailDiagramItemSemanticEditPolicy());
		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE,
				new ActivityDetailDiagramCanonicalEditPolicy());
	}

	/**
	 * It sets object location on cavas
	 * @param list
	 * 
	 */
	private void setObjectsLocations(List list) {
		xaxisbegin = true;
		x = 50;
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object child = iter.next();
			if (child instanceof GraphicalEditPart) {
				final GraphicalEditPart part = (GraphicalEditPart) child;
				final Node node = (Node) part.getModel();

				if (node instanceof Node) {
					final Location location = (Location) node.getLayoutConstraint();

					if (debug) {
						System.out.println("Node - " + part); //$NON-NLS-1$
						System.out.println("Location - " + location); //$NON-NLS-1$
					}

					if (!xaxisbegin) {
						x = cachedPoint.x + cachedWidth + spacing;
					}

					if (!loading && miscellaneous) {
						location.setX(mapMode.DPtoLP(x));
						location.setY(mapMode.DPtoLP(10));

					} else {
						try {
							TxUtil.runInTransaction(getView().getDiagram(), new Runnable() {

								public void run() {
									location.setX(mapMode.DPtoLP(x));
									location.setY(mapMode.DPtoLP(y));

									if (part instanceof RoleTaskCompositeEditPart) {
										((RoleTaskCompositeEditPart) part)
												.updatebounds();
									}
									if (part instanceof WorkProductCompositeEditPart) {
										((WorkProductCompositeEditPart) part)
												.updatebounds();
									}

								}
								
							});
						} catch (Exception e) {
							CommonPlugin.getDefault().getLogger().logError(e);
						}

						if (debug)
							System.out.println("NewLocation - " + location); //$NON-NLS-1$

						if (!miscellaneous)
							cachedPoint = new Point(location.getX(), location
									.getY());
						cachedWidth = part.getFigure().getPreferredSize().width;
						if (part.getFigure().getPreferredSize().height > cachedHeight)
							cachedHeight = part.getFigure().getPreferredSize().height;
						xaxisbegin = false;
						if (debug) {
							//							System.out
							//									.println("Point : " + new Point(x, y).toString() + "And width=" //$NON-NLS-1$ //$NON-NLS-2$
							//											+ cachedWidth
							//											+ ", Height=" + cachedHeight + ", Width of Viewer: " + viewerWidth); //$NON-NLS-1$ //$NON-NLS-2$
						}
					}
				}
			}
		}
	}

	/**
	 * Calculates and sets size of the given node
	 * @param child
	 * 
	 * TODO - Not used currently, we need to see whether we need this method
	 */
	private void calcSize(Object child) {
		List list = ((GraphicalEditPart) child).getChildren();
		int largestWidth = 0;
		int largestHeight = 0;
		for (Iterator it = list.iterator(); it.hasNext();) {
			Object obj = it.next();

			if (obj instanceof NodeEditPart) {
				View viewObj = (View) ((NodeEditPart) obj).getModel();
				Object objNode = viewObj.getElement();
				if (objNode instanceof WorkProductDescriptorNode) {
					int nodeWidth = ((NodeEditPart) obj).getFigure()
							.getPreferredSize().width;
					int nodeHeight = ((NodeEditPart) obj).getFigure()
							.getPreferredSize().height;
					if (nodeWidth > largestWidth)
						largestWidth = nodeWidth;
					if (nodeHeight > largestHeight)
						largestHeight = nodeHeight;
				}
			}

		}
		((WorkProductCompositeEditPart) child).getFigure().setPreferredSize(
				new Dimension(largestWidth * 2 + 10, 20 + largestHeight
						* (list.size() / 2 + 1)));
	}

	/**
	 * Returns recently addded editparts. Usually called 
	 * by diagram services to do autolayout adjustment.
	 */
	public List<EditPart> getRecentlyAddedParts() {
		return recentlyAddedParts;
	}

	/**
	 * Clear the recently added editparts from the list.
	 */
	public void clearRecentlyAddedParts() {
		recentlyAddedParts.clear();
	}

	@Override
	protected void handleNotificationEvent(Notification event) {
		if (NotationPackage.Literals.VIEW__VISIBLE.equals(event.getFeature())) {
			Object notifier = event.getNotifier();
			if (notifier == getModel())
				refreshVisibility();
			else if (event.getNotifier() instanceof Node) {
				refreshChildren();
				DiagramEditorUtil.refreshConnectionEditParts(this);
			}
		} else
			super.handleNotificationEvent(event);
	}
	
	@Override
	public void activate() {
		super.activate();
		Object model = getModel();
		if (model instanceof View) {
			EObject e = ((View) model).getElement();
			if (e instanceof org.eclipse.epf.diagram.model.Node) {
				((org.eclipse.epf.diagram.model.Node) e).addConsumer(this);
			}
		}
	}
	
	@Override
	public void deactivate() {
		super.deactivate();
		Object model = getModel();
		if (model instanceof View) {
			EObject e = ((View) model).getElement();
			if (e instanceof org.eclipse.epf.diagram.model.Node) {
				((org.eclipse.epf.diagram.model.Node) e).removeConsumer(this);
			}
		}
	}
	
}
