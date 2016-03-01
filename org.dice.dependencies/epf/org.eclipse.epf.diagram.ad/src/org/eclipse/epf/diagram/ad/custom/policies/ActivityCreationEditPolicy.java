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
package org.eclipse.epf.diagram.ad.custom.policies;

import java.util.Iterator;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author Phong Nguyen Le
 * @since 1.2
 */
public class ActivityCreationEditPolicy extends CreationEditPolicy {
	@Override
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
				IGraphicalEditPart gep = (IGraphicalEditPart)ep;
				if(shouldReparent(gep, request)) {
					cc.compose(getReparentViewCommand(gep));
				}
			}
			else if ( context != null && shouldReparent(semantic, context)) {
				cc.compose(getReparentCommand((IGraphicalEditPart)ep));
			}
		}
		return cc.isEmpty() ? null : new ICommandProxy(cc.reduce());
	}

	protected boolean shouldReparent(IGraphicalEditPart ep, ChangeBoundsRequest request) {
		// replace parent only if the view does not have any parent yet or the
		// bounds are not inside the current parent figure
		//
		View view = (View) ep.getModel();
		if(view.eContainer() != null) {
			Point location = ep.getFigure().getBounds().getLocation().getTranslated(request.getMoveDelta());
			IFigure parentFig = ((GraphicalEditPart)ep.getParent()).getFigure();
			Rectangle parentBounds = parentFig.getBounds();	
			return !parentBounds.contains(location);
		}
		else {
			return true;
		}
	}
}
