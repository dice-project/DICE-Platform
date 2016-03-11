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
/**
 * 
 */
package org.eclipse.epf.diagram.ad.custom.policies;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityPartitionEditPart;
import org.eclipse.epf.diagram.ad.part.ActivityDiagramEditorPlugin;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.commands.AddCommand;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.MoveRequest;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * @author Shashidhar Kannoori
 * @author Shilpa Toraskar
 * 
 */
public class ActivityXYLayoutEditPolicy extends XYLayoutEditPolicy {

	/**
	 * 
	 */
	public ActivityXYLayoutEditPolicy() {
	}

	protected Command createAddCommand(EditPart child, Object constraint) {
		Object parent  = child.getParent();		
		if ((parent instanceof ActivityPartitionEditPart) &&
			( child instanceof ShapeEditPart && constraint instanceof Rectangle)) {
			Rectangle rect = (Rectangle) constraint;
			IFigure parentFigure = ((ActivityPartitionEditPart)parent).getFigure();			
			View view = (View) child.getModel();
			boolean adjustLocationNeeded = ViewUtil
					.resolveSemanticElement(view) != null
					|| parentFigure.getBounds().contains(rect.getLocation());
			if(adjustLocationNeeded) {
				int x = rect.x - ((ActivityPartitionEditPart) parent).getLocation().x;
				int y = rect.y - ((ActivityPartitionEditPart) parent).getLocation().y;

				ICommand boundsCommand = 
					new SetBoundsCommand(((ShapeEditPart) child).getEditingDomain(),
							DiagramUIMessages.SetLocationCommand_Label_Resize,  //$NON-NLS-1$
							new EObjectAdapter(view),
									new Point(x, y)); 

					return new ICommandProxy(boundsCommand);
			}
		}
		
		return super.createAddCommand(child, constraint);
	}

	protected Command getMoveChildrenCommand(Request request) {
		return super.getMoveChildrenCommand(request);
	}


	protected Command createChangeConstraintCommand(EditPart child,
			Object constraint) {
		return super.createChangeConstraintCommand(child, constraint);
	}


	protected Command createChangeConstraintCommand(
			ChangeBoundsRequest request, EditPart child, Object constraint) {
		Command cmd = super.createChangeConstraintCommand(request, child,
				constraint);
		if (child instanceof ActivityPartitionEditPart) {
			List list = getNodesInPartition((ActivityPartitionEditPart) child);
			if (list != null && !list.isEmpty()) {
				for (Iterator iter = list.iterator(); iter.hasNext();) {
					EditPart element = (EditPart) iter.next();
					if (element instanceof ShapeNodeEditPart) {
						ChangeBoundsRequest req = new ChangeBoundsRequest(
								REQ_RESIZE_CHILDREN);
						req.setEditParts(element);
						req.setMoveDelta(request.getMoveDelta());
						req.setSizeDelta(request.getSizeDelta());
						req.setLocation(request.getLocation());
						req.setExtendedData(request.getExtendedData());
						req.setResizeDirection(request.getResizeDirection());
						cmd = cmd.chain(element.getParent().getCommand(req));
					}
				}
			}
		}else{
			EditPart parent = child.getParent();
			if(parent instanceof DiagramEditPart){
				List children = ((DiagramEditPart)parent).getChildren();
				for (Iterator iter = children.iterator(); iter.hasNext();) {
					EditPart part = (EditPart) iter.next();
					if(part instanceof ActivityPartitionEditPart){
						Node view = (Node)part.getModel();
						LayoutConstraint lConstaint = view.getLayoutConstraint();
						if(lConstaint instanceof Bounds){
							Rectangle partitionConstraint = ((ActivityPartitionEditPart)part).getFigure().getBounds();
							if(constraint instanceof Rectangle){
								if(partitionConstraint.contains((Rectangle)constraint)){
									request.setType(RequestConstants.REQ_CREATE);
									cmd = cmd.chain(part.getCommand(request));
								}
							}
						}else{
							if (ActivityDiagramEditorPlugin.getInstance().isDebugging()) {
								System.out.println("LayoutConstraint: "+ lConstaint); //$NON-NLS-1$
							}
						}
					}
				}
			}
		}
		return cmd;
	}
	
	
	private static List getNodesInPartition(ActivityPartitionEditPart child) {
		List nodes = new ArrayList();
		ActivityPartition partition = (ActivityPartition)((Node)child.getModel()).getElement();
		List list = partition.getNodes();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object element = (Object) iter.next();
			nodes.add(findEditPartyByElement(child.getParent(), element));
		}
		return nodes;
	}
	
	private static EditPart findEditPartyByElement(EditPart parent,
			Object element) {
		List list = parent.getChildren();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			EditPart editPart = (EditPart) iter.next();
			if (((Node) editPart.getModel()).getElement() == element) {
				return editPart;
			}
		}
		return null;
	}

	@Override
	protected Command getAddCommand(Request request) {
		CompoundCommand command = new CompoundCommand();
		command.setDebugLabel("Add in ConstrainedLayoutEditPolicy with Reparent if necessary");//$NON-NLS-1$
		command.add(super.getAddCommand(request));
		
		IGraphicalEditPart childPart;
		if(request instanceof ChangeBoundsRequest && request.getType() == REQ_ADD){
			ChangeBoundsRequest changeRequest =(ChangeBoundsRequest)request; 
			List editParts = changeRequest.getEditParts();
			for (int i = 0; i < editParts.size(); i++) {
				childPart = (IGraphicalEditPart)editParts.get(i);
				IGraphicalEditPart parent = (IGraphicalEditPart)childPart.getParent();
				if(parent instanceof ActivityPartitionEditPart){
					Point point = childPart.getFigure().getBounds().getLocation().getCopy();
					point.translate(changeRequest.getMoveDelta());
					if(!parent.getFigure().getBounds().contains(point)){
						command.add(getReparentCommand(childPart, parent, changeRequest));
					}
				}
			}
		}
		return command.unwrap();
//		return null;
	}
	
	
	protected Command getReparentCommand(IGraphicalEditPart gep, IGraphicalEditPart oldParent,
			ChangeBoundsRequest changeRequest) {
		CompositeCommand cc = new CompositeCommand(DiagramUIMessages.AddCommand_Label); 
		View container = (View)getHost().getModel();
		EObject context = ViewUtil.resolveSemanticElement(container);
		View view = (View)gep.getModel();
		EObject element = ViewUtil.resolveSemanticElement(view);

        TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
            .getEditingDomain();
        
		if ( element != null ) {
			cc.compose(new ReparentingElementsCommand(new MoveRequest(editingDomain, context, UMLPackage.eINSTANCE.getActivity_Node(), element),
					element,view, oldParent));
			cc.compose(new AddCommand(gep.getEditingDomain(), new EObjectAdapter(container),
					  new EObjectAdapter(view)));
			cc.compose(adjustLocationCommand(gep, changeRequest));
		}
		return cc.isEmpty() ? null : new ICommandProxy(cc.reduce());
	}
	
	
	public class ReparentingElementsCommand extends EditElementCommand{

		private final EObject moveElement;
		private IGraphicalEditPart oldParent;
		private View view;
		
		public ReparentingElementsCommand(MoveRequest req, EObject element, View view, 
				IGraphicalEditPart oldParent) {
			this("reparent on move", element, req); //$NON-NLS-1$
			this.oldParent = oldParent;
			this.view = view;
		}
		
		protected ReparentingElementsCommand(String label, EObject elementToEdit, 
				IEditCommandRequest request) {
			super(label, elementToEdit, request);
			this.moveElement = elementToEdit; 
		}

		@Override
		protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
			View parentView = (View)getHost().getModel();
			if(parentView.getElement() instanceof Activity){
				if(moveElement instanceof ActivityNode){
					View oldParentView = (View)oldParent.getModel();
					((ActivityNode)moveElement).getInPartitions().remove(oldParentView.getElement());
				}
			}
			return null;
		}
		@Override
		public boolean canExecute() {
			if(moveElement != null) return true;
			return false;
		}
	}
	
	private ICommand adjustLocationCommand(IGraphicalEditPart child,
			ChangeBoundsRequest request) {
		if (child instanceof ShapeEditPart) {
			Point location = request.getMoveDelta().getCopy();
			Point childLocation = child.getFigure().getBounds().getLocation();
			location.x+=childLocation.x;
			location.y+=childLocation.y;
			ICommand boundsCommand = new SetBoundsCommand(
					((ShapeEditPart) child).getEditingDomain(),
					DiagramUIMessages.SetLocationCommand_Label_Resize, //$NON-NLS-1$
					new EObjectAdapter((View) child.getModel()),
					location);

			return boundsCommand;
		}
		return null;
	}
}
