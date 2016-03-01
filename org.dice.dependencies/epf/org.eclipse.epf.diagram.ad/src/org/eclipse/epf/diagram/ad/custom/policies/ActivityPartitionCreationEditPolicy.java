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

import java.util.Iterator;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityPartitionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.CreateCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.requests.MoveRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * CreationEditPolicy for ActivityPartition.
 * 
 * @author Shashidhar Kannoori
 * @author Phong Nguyen Le
 */
public class ActivityPartitionCreationEditPolicy extends CreationEditPolicy {
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy#getCreateCommand(org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest)
	 * @modified
	 */
	protected Command getCreateCommand(CreateViewRequest request) {
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
				.getEditingDomain();
		CompositeTransactionalCommand cc = new CompositeTransactionalCommand(
				editingDomain, DiagramUIMessages.AddCommand_Label);

		Iterator descriptors = request.getViewDescriptors().iterator();

		while (descriptors.hasNext()) {
			CreateViewRequest.ViewDescriptor descriptor = (CreateViewRequest.ViewDescriptor) descriptors
					.next();
			View containerView = (View) getHost().getModel();
//			while(containerView.getElement() instanceof ActivityPartition){
//				containerView = (View)containerView.eContainer();
//			}
			CreateCommand createCommand = new CreateCommand(editingDomain,
					descriptor, containerView);

			cc.compose(createCommand);
		}
	return new ICommandProxy(cc.reduce());
	}
	
	/** Return a command to reparent both the semantic and view elements. */
	/**
	 * return a command to reparent both the semantic and view elements. 
	 * @param request the request
	 * @return command
	 */
	protected Command getReparentCommand(ChangeBoundsRequest request) {
		Iterator editParts = request.getEditParts().iterator();
		View container = (View)getHost().getAdapter(View.class);
		EObject context = container == null ? null : ViewUtil.resolveSemanticElement(container);
        CompositeCommand cc = new CompositeCommand(DiagramUIMessages.AddCommand_Label);
		while ( editParts.hasNext() ) {
			EditPart ep = (EditPart)editParts.next();
			if ( ep instanceof LabelEditPart ) {
				continue;
			}
			
			View view = (View)ep.getAdapter(View.class);
			if ( view == null ) {
				continue;
			}
			
			EObject semantic = ViewUtil.resolveSemanticElement(view);
			if ( semantic == null ) {
				cc.compose(getReparentViewCommand((IGraphicalEditPart)ep));
				cc.compose(adjustLocationCommand((IGraphicalEditPart)ep, request));
			}
			else if ( context != null && shouldReparent(semantic, context)) {
				cc.compose(getReparentCommand((IGraphicalEditPart)ep));
				cc.compose(adjustLocationCommand((IGraphicalEditPart)ep, request));
			}
		}
		return cc.isEmpty() ? null : new ICommandProxy(cc.reduce());
	}
	
	@Override
	protected ICommand getReparentCommand(IGraphicalEditPart gep) {
		CompositeCommand cc = new CompositeCommand(DiagramUIMessages.AddCommand_Label); 
		View container = (View)getHost().getModel();
		EObject context = ViewUtil.resolveSemanticElement(container);
		View view = (View)gep.getModel();
		EObject element = ViewUtil.resolveSemanticElement(view);

        TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
            .getEditingDomain();
        
        //
		// semantic
		if ( element != null ) {
			Command moveSemanticCmd =
				getHost().getCommand(
					new EditCommandRequestWrapper(
						new MoveRequest(editingDomain, context, UMLPackage.eINSTANCE.getActivityPartition_Node(), element)));
            
              if (moveSemanticCmd == null) {
                  return org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand.INSTANCE;
              }
			
			cc.compose ( new CommandProxy(moveSemanticCmd) );
		}
		//
		// notation
		cc.compose(getReparentViewCommand(gep));
		// in case of moving into partition we have to change the coordinates of element
		return cc;
	}

	private ICommand adjustLocationCommand(IGraphicalEditPart child,
			ChangeBoundsRequest request) {
		if (child instanceof ShapeEditPart) {
			Point location = request.getMoveDelta();
			Point childLocation = child.getFigure().getBounds().getLocation();
			int x = childLocation.x + location.x
					- ((ActivityPartitionEditPart)getHost()).getLocation().x;
			int y = childLocation.y + location.y
					- ((ActivityPartitionEditPart)getHost()).getLocation().y;

			ICommand boundsCommand = new SetBoundsCommand(
					((ShapeEditPart) child).getEditingDomain(),
					DiagramUIMessages.SetLocationCommand_Label_Resize, //$NON-NLS-1$
					new EObjectAdapter((View) child.getModel()),
					new Point(x, y));

			return boundsCommand;
		}
		return null;
	}
	
	static ICommand createAdjustLocationCommand(IGraphicalEditPart child,
			ChangeBoundsRequest request, IGraphicalEditPart newParent) {
		if (child instanceof ShapeEditPart) {
			Point location = request.getMoveDelta();
			Point newLocation = child.getFigure().getBounds().getLocation().getCopy().translate(location);
//			Dimension diff = ((GraphicalEditPart) child.getParent()).getFigure().getBounds()
//					.getLocation().getDifference(
//							newParent.getFigure().getBounds().getLocation());
//			newLocation.translate(diff);
//			newParent.getFigure().translateFromParent(newLocation);
			ICommand boundsCommand = new SetBoundsCommand(
					((ShapeEditPart) child).getEditingDomain(),
					DiagramUIMessages.SetLocationCommand_Label_Resize, //$NON-NLS-1$
					new EObjectAdapter((View) child.getModel()),
					newLocation);

			return boundsCommand;
		}
		return null;				
	}
}
