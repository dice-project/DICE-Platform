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
package org.eclipse.epf.authoring.gef.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.AdapterFactoryTreeIterator;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.diagram.model.ActivityDetailDiagram;
import org.eclipse.epf.diagram.model.ActivityDiagram;
import org.eclipse.epf.diagram.model.Diagram;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.RoleNode;
import org.eclipse.epf.diagram.model.TaskNode;
import org.eclipse.epf.diagram.model.TypedNode;
import org.eclipse.epf.diagram.model.WorkBreakdownElementNode;
import org.eclipse.epf.diagram.model.WorkProductDependencyDiagram;
import org.eclipse.epf.diagram.model.util.GraphicalDataHelper;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkProductDescriptor;

/**
 * Validating routines for diagram editing
 * 
 * @author Phong Nguyen Le
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public final class Validation {

	private static final String errMsg_CanNotConnect = AuthoringUIResources.DiagramValidation_err_cannot_connect_text; 

	private static final String errMsg_SamePredAndSucc = AuthoringUIResources.DiagramValidation_err_samepredandsuc_text; 

	private static final String errMsg_CanNotDelete = AuthoringUIResources.DiagramValidation_err_cannot_delete_text; 

	/**
	 * Checks if user can connect the given nodes.
	 * 
	 * @param source
	 * @param target
	 * @return null if connection is allowed, error message otherwise.
	 */
	public static String checkConnect(Node source, Node target) {
		if (source.getDiagram() != target.getDiagram())
			return AuthoringUIResources.DiagramValidation_err_cannot_connect_text;

		if (source.getDiagram() instanceof ActivityDiagram) {
			AdapterFactory adapterFactory = TngAdapterFactory.INSTANCE
					.getWBS_ComposedAdapterFactory();
			Object adapter = adapterFactory.adapt(source.getDiagram()
					.getObject(), ITreeItemContentProvider.class);
			Object proc = null;
			if (adapter instanceof IBSItemProvider) {
				proc = ((IBSItemProvider) adapter).getTopItem();
			}
			// get all breakdown elements in this process
			List<Object> allElements = new ArrayList<Object>();
			for (Iterator iter = new AdapterFactoryTreeIterator(adapterFactory,
					proc); iter.hasNext();) {
				Object obj = iter.next();
				allElements.add(TngUtil.unwrap(obj));
			}
			if (proc != null) {
				// If target is ReadOnly (means extends) and allow the link if
				// its parent activity is a Contributor
				if (target.getDiagram().isReadOnly())
					return errMsg_CanNotConnect;
				if (source.getObject() instanceof WorkBreakdownElement) {
					if (target.getObject() instanceof WorkBreakdownElement) {
						if (ProcessUtil.checkCircular(
								(WorkBreakdownElement) target.getObject(),
								(WorkBreakdownElement) source.getObject(),
								allElements)) {
							return errMsg_SamePredAndSucc;
						}
						if (target.isReadOnly())
							return errMsg_CanNotConnect;
					} else if (target instanceof TypedNode) {
						// should not allow incoming connection to start node
						// and FreeText.
						if (((TypedNode) target).getType() == TypedNode.START
								|| ((TypedNode) target).getType() == TypedNode.FREE_TEXT) {
							return errMsg_CanNotConnect;
						}

						Collection targetNodes = GraphicalDataHelper
								.getTargetNodes((TypedNode) target,
										WorkBreakdownElementNode.class);
						WorkBreakdownElement pred = (WorkBreakdownElement) source
								.getObject();
						for (Iterator iter = targetNodes.iterator(); iter
								.hasNext();) {
							Node node = ((Node) iter.next());
							if (node.isReadOnly())
								return errMsg_CanNotConnect;
							if (ProcessUtil.checkCircular(
									(WorkBreakdownElement) node.getObject(),
									pred, allElements)) {
								return errMsg_SamePredAndSucc;
							}
						}
					}
				} else if (source instanceof TypedNode) {
					if (target == source) {
						return errMsg_CanNotConnect;
					}
					// should not allow outgoing connection from End Node and
					// Free Text
					if (((TypedNode) source).getType() == TypedNode.END
							|| ((TypedNode) source).getType() == TypedNode.FREE_TEXT) {
						return errMsg_CanNotConnect;
					}

					if (target instanceof TypedNode) {

						/*
						 * If source and target are TypedNodes, avoid circular
						 * loop connection.
						 */
						if (checkSyncBarCircularLooop((TypedNode) source,
								(TypedNode) target) != null) {
							return errMsg_CanNotConnect;
						}

						// should not allow incoming connection to start node
						// and FreeText.
						if (((TypedNode) target).getType() == TypedNode.START
								|| ((TypedNode) target).getType() == TypedNode.FREE_TEXT) {
							return errMsg_CanNotConnect;
						}

						Collection srcNodes = GraphicalDataHelper
								.getSourceNodes((TypedNode) source,
										WorkBreakdownElementNode.class);
						if (!srcNodes.isEmpty()) {
							Collection targetNodes = GraphicalDataHelper
									.getTargetNodes((TypedNode) target,
											WorkBreakdownElementNode.class);
							for (Iterator iter = targetNodes.iterator(); iter
									.hasNext();) {
								Node node = ((Node) iter.next());
								if (node.isReadOnly())
									return errMsg_CanNotConnect;
								WorkBreakdownElement succ = (WorkBreakdownElement) node
										.getObject();
								for (Iterator iterator = srcNodes.iterator(); iterator
										.hasNext();) {
									WorkBreakdownElement pred = (WorkBreakdownElement) ((Node) iterator
											.next()).getObject();
									if (ProcessUtil.checkCircular(succ, pred,
											allElements)) {
										return errMsg_SamePredAndSucc;
									}
								}
							}
						}
					} else if (target instanceof WorkBreakdownElementNode) {

						if (target.isReadOnly())
							return errMsg_CanNotConnect;

						Collection srcNodes = GraphicalDataHelper
								.getSourceNodes((TypedNode) source,
										WorkBreakdownElementNode.class);
						WorkBreakdownElement succ = (WorkBreakdownElement) ((Node) target)
								.getObject();
						for (Iterator iterator = srcNodes.iterator(); iterator
								.hasNext();) {
							WorkBreakdownElement pred = (WorkBreakdownElement) ((Node) iterator
									.next()).getObject();
							if (ProcessUtil.checkCircular(succ, pred,
									allElements)) {
								return errMsg_SamePredAndSucc;
							}
						}

					}
				}
			}
		} else if (source.getDiagram() instanceof ActivityDetailDiagram) {
			if (source.isReadOnly() || target.isReadOnly()) {
				return errMsg_CanNotConnect;
			}

			if ((source.getObject() instanceof RoleDescriptor || target
					.getObject() instanceof RoleDescriptor)
					|| (source.getObject() instanceof TaskDescriptor && target
							.getObject() instanceof TaskDescriptor)
					|| (source.getObject() instanceof WorkProductDescriptor && target
							.getObject() instanceof WorkProductDescriptor)) {
				return errMsg_CanNotConnect;
			} else {
				// check for duplicate connection
				//
				for (Iterator iter = source.getOutgoingConnections().iterator(); iter
						.hasNext();) {
					Link link = (Link) iter.next();
					if (link.getTarget() == target) {
						return errMsg_CanNotConnect;
					}
				}

			}
		} else if (source.getDiagram() instanceof WorkProductDependencyDiagram) {
			if (source.isReadOnly() || target.isReadOnly()) {
				return errMsg_CanNotConnect;
			}
		}

		return null;
	}

	public static String checkDelete(Link link) {

		// if (link.getTarget() != null
		// && link.getTarget().getDiagram() instanceof ActivityDiagram) {
		// if (link.getTarget().isReadOnly()){
		// if(link.getSource() instanceof TypedNode){
		// if(((TypedNode)link.getSource()).getType() == TypedNode.DECISION){
		// return null;
		// }
		// }
		// return errMsg_CanNotDelete;
		// }
		// if (link.getTarget() instanceof TypedNode) {
		//				
		// TypedNode typednode = (TypedNode)link.getTarget();
		// if(typednode.getType() == TypedNode.DECISION){
		// return null;
		// }
		// if(link.getSource() instanceof TypedNode){
		// typednode = (TypedNode)link.getSource();
		// if(typednode.getType() == TypedNode.DECISION){
		// return null;
		// }
		// }
		//				
		// if (link.getSource().isReadOnly())
		// return errMsg_CanNotDelete;
		//				
		// if (link.getSource().getObject() instanceof WorkBreakdownElement
		// || (link.getSource() instanceof TypedNode && !GraphicalDataHelper
		// .getSourceNodes((TypedNode) link.getSource(),
		// WorkBreakdownElementNode.class)
		// .isEmpty())) {
		//					
		// Collection targetNodes =
		// GraphicalDataHelper.getTargetNodes((TypedNode) link.getTarget(),
		// WorkBreakdownElementNode.class);
		// for (Iterator iter = targetNodes.iterator(); iter.hasNext();) {
		// Node node = (Node) iter.next();
		// if (node.isReadOnly()) {
		// return errMsg_CanNotDelete;
		// }
		// }
		// }
		//
		// }
		// if (link.getSource() instanceof TypedNode) {
		// TypedNode typednode = (TypedNode)link.getSource();
		// if(typednode.getType() == TypedNode.DECISION){
		// return null;
		// }
		// if (link.getTarget().isReadOnly())
		// return errMsg_CanNotDelete;
		// }
		// } else if ((link.getTarget() != null &&
		// link.getTarget().isReadOnly())
		// || (link.getSource() != null && link.getSource().isReadOnly())) {
		// return errMsg_CanNotDelete;
		// }
		// return null;

		Node source = link.getSource();
		Node target = link.getTarget();

		// Special handle for Activity Detail Diagram links cannot be deleted.
		if (target != null
				&& target.getDiagram() instanceof ActivityDetailDiagram) {
			return errMsg_CanNotDelete;
		}

		if (target != null && target.getDiagram() instanceof ActivityDiagram) {

			// Target = breakdownelement
			if (target instanceof WorkBreakdownElementNode) {
				// Source = Breakdown element
				if (source instanceof WorkBreakdownElementNode) {
					if (link.getTarget().isReadOnly())
						return errMsg_CanNotDelete;
					else
						return null;
				}
				// Source = TypedNode
				if (source instanceof TypedNode) {
					// Source = Decision Node
					TypedNode sourceNode = (TypedNode) source;
					if (!target.isReadOnly()) {
						return null;
					} else if (sourceNode.getType() == TypedNode.DECISION) {
						return null;
					} else if (sourceNode.getType() == TypedNode.SYNCH_BAR) {
						return checkSyncBarIncomingLinks(sourceNode);
					} else if (sourceNode.isReadOnly()) {
						return errMsg_CanNotDelete;
					}
				}
			}// Target is TypedNode
			else if (target instanceof TypedNode) {

				TypedNode typedNode = (TypedNode) target;
				// if Target is TypedNode and its a DecisionPoint - should be
				// black.
				if (typedNode.getType() == TypedNode.DECISION) {
					return null;
				}
				// If Target is TypedNode and and Source is WorkBreakdownElement
				// and readonly
				// Link should be green.
				if (source instanceof WorkBreakdownElementNode) {
					// Check Target - is having any indirect(outgoing
					// connnection) contains decision points.
					if (checkSyncBarOutgoingLinks((TypedNode) target) == null)
						return null;
					if (source.isReadOnly())
						return errMsg_CanNotDelete;
				}
				if (source instanceof TypedNode) {
					TypedNode sourceTypedNode = (TypedNode) source;
					// Source is Typed Node and its a decision point return
					// null.
					if (sourceTypedNode.getType() == TypedNode.DECISION) {
						return null;
					}
					// if source is SyncBar and check all its incoming
					// connections.
					if (sourceTypedNode.getType() == TypedNode.SYNCH_BAR) {
						return checkSyncBarIncomingLinks(sourceTypedNode);
					}
				}
			}
		}
		return null;
	}

	public static String checkDelete(Node node) {
		if (node.isReadOnly())
			return errMsg_CanNotDelete;
		Diagram diagram = node.getDiagram();
		if (diagram instanceof ActivityDiagram) {
			if (node instanceof TypedNode) {
				Collection targetNodes = GraphicalDataHelper.getTargetNodes(
						(TypedNode) node, WorkBreakdownElementNode.class);
				for (Iterator iter = targetNodes.iterator(); iter.hasNext();) {
					Node target = (Node) iter.next();
					if (target.isReadOnly()) {
						return errMsg_CanNotDelete;
					}
				}
			}
		} else if (diagram instanceof ActivityDetailDiagram) {
			if (node instanceof RoleNode) {
				return errMsg_CanNotDelete;
			}
		}
		return null;
	}

	public static String checkReconnect(Node source, Node target, Link link) {
		if (source.getDiagram() instanceof ActivityDiagram) {
			if (link.getTarget() != null) {
				if (link.getTarget().isReadOnly()) {
					return errMsg_CanNotConnect;
				} else {
					if (link.getTarget() instanceof TypedNode) {
						Collection targetNodes = GraphicalDataHelper
								.getTargetNodes((TypedNode) link.getTarget(),
										WorkBreakdownElementNode.class);
						for (Iterator iter = targetNodes.iterator(); iter
								.hasNext();) {
							Node node = (Node) iter.next();
							if (node.isReadOnly()) {
								return errMsg_CanNotConnect;
							}
						}
					}
				}
			}
		}

		return checkConnect(source, target, link);
	}

	/*
	 * Method will not allow multiple link between two nodes. Needs to call
	 * CreakLinkCommand and ReconnectLinkCommand. Any action on a Link should
	 * call this checkConnect(Node source, Node target, Link link) before allow
	 * to connect.
	 */
	public static String checkConnect(Node source, Node target, Link link) {

		if (source == target && !(source.getObject() instanceof Activity)) {
			return errMsg_CanNotConnect;
		}

		List links = source.getOutgoingConnections();
		if (links != null) {
			// This is need for reconnect to same target node.
			if (links.contains(link)) {
				if (link.getTarget().equals(target)) {
					return null;
				}
			}
			// This is need for new connect and reconnect to different target
			// node.
			for (Iterator iter = links.iterator(); iter.hasNext();) {
				Link linkx = (Link) iter.next();
				Object linkxtarget = linkx.getTarget();
				if (linkxtarget != null && linkxtarget.equals(target)) {
					return errMsg_CanNotConnect;
				}
			}
		}
		return checkConnect(source, target);
	}

	/*
	 * To avoid circular looping between synchronization bars. If SycnBar1 ->
	 * SyncBar2 (connected) then SyncBar2 -> SyncBar1 cannot connect. if
	 * syncbar1 -> syncbar2->syncbar3 then syncbar3 -> syncbar1 disallowed.
	 * 
	 */
	public static String checkSyncBarCircularLooop(TypedNode sNode,
			TypedNode tNode) {
		List list = tNode.getOutgoingConnections();
		if (!list.isEmpty() && list.size() > 0) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Link link = (Link) iterator.next();
				Node typednode = link.getTarget();
				if (sNode.equals(typednode))
					return errMsg_CanNotConnect;
				if (typednode instanceof TypedNode) {
					return checkSyncBarCircularLooop(sNode,
							(TypedNode) typednode);
				}
			}
		}
		return null;
	}

	/*
	 * Method to Check if SyncBar inComming connections have any source is a
	 * readonly. @return
	 */
	public static String checkSyncBarIncomingLinks(TypedNode typedNode) {
		for (Iterator iter = typedNode.getIncomingConnections().iterator(); iter
				.hasNext();) {
			Link link = (Link) iter.next();
			Node source = link.getSource();
			if (source instanceof WorkBreakdownElementNode) {
				if (source.isReadOnly())
					return errMsg_CanNotDelete;
			} else if (source instanceof TypedNode) {
				if (((TypedNode) source).getType() == TypedNode.SYNCH_BAR)
					if (checkSyncBarIncomingLinks((TypedNode) source) != null)
						return errMsg_CanNotDelete;
			}
		}
		return null;
	}

	/*
	 * Method to check if synchronization bar outgoing connection has any target
	 * is readonly.
	 */
	public static String checkSyncBarOutgoingLinks(TypedNode typedNode) {
		for (Iterator iter = typedNode.getOutgoingConnections().iterator(); iter
				.hasNext();) {
			Link link = (Link) iter.next();
			Node target = link.getTarget();
			if (target instanceof WorkBreakdownElementNode) {
				if (target.isReadOnly())
					return errMsg_CanNotDelete;
			} else if (target instanceof TypedNode) {
				if (((TypedNode) target).getType() == TypedNode.SYNCH_BAR)
					if (checkSyncBarOutgoingLinks((TypedNode) target) != null)
						return errMsg_CanNotDelete;
			}
		}
		return null;
	}

	/**
	 * Check to see link is readonly.
	 */
	public static boolean isReadOnly(Link link) {
		Node target = link.getTarget();
		Node source = link.getSource();
		if (target != null && target.getDiagram() instanceof ActivityDiagram) {
			return checkDelete(link) != null;
		} else if (target != null
				&& target.getDiagram() instanceof ActivityDetailDiagram) {
			if ((source != null && source instanceof TaskNode && source
					.isReadOnly())
					|| (target instanceof TaskNode && target.isReadOnly()))
				return true;
		}
		return false;
	}

}
