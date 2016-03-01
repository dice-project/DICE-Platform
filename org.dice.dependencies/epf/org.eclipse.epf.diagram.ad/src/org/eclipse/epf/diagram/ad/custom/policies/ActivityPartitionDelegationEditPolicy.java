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
package org.eclipse.epf.diagram.ad.custom.policies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ContainerEditPolicy;

/**
 * ActivityPartition delegates to its compartment edit policy
 *   
 */
public class ActivityPartitionDelegationEditPolicy extends
        AbstractEditPolicy {

    private EditPart childEditPart;
    private EditPolicy childEditPolicy;
    private final String childViewID;
    private final String editPolicyID;
    
    
    public ActivityPartitionDelegationEditPolicy(String childId, String editPolicyId) {
        childViewID = childId;
        editPolicyID = editPolicyId;
    }
    

   /**
    * (non-Javadoc)
    * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#activate()
    */
    public void activate() {
        childEditPart =
            ((IGraphicalEditPart)getHost()).getChildBySemanticHint(childViewID);
        if (childEditPart == null) {
            return;
        }
        childEditPolicy = childEditPart.getEditPolicy(editPolicyID);
        childEditPolicy.setHost(childEditPart);
        childEditPolicy.activate();
    }

  /**
   * (non-Javadoc)
   * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#deactivate()
   */
    public void deactivate() {
        if (childEditPolicy == null) {
            return;
        }
        childEditPolicy.deactivate();
        childEditPart = null;
        childEditPolicy = null;
    }


   /**
    * 
    */
    public void eraseSourceFeedback(Request request) {
        if (childEditPolicy == null) {
            return;
        }
        childEditPolicy.eraseSourceFeedback(request);
    }


    /*
     * (non-Javadoc)
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#eraseTargetFeedback(org.eclipse.gef.Request)
     * @modified
     */
    public void eraseTargetFeedback(Request request) {
        if (childEditPolicy == null) {
            return;
        }
        childEditPolicy.eraseTargetFeedback(request);
    }


    /*
     * (non-Javadoc)
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getCommand(org.eclipse.gef.Request)
     * @modified
     */
    public Command getCommand(Request request) {
        if (childEditPolicy == null) {
            return null;
        }
        if(childEditPolicy instanceof ContainerEditPolicy && 
        		request instanceof ChangeBoundsRequest){
        	return null;
        }
        return childEditPolicy.getCommand(request);
    }


    /*
     * (non-Javadoc)
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getTargetEditPart(org.eclipse.gef.Request)
     * @modified
     */
    public EditPart getTargetEditPart(Request request) {
        if (childEditPolicy == null) {
            return null;
        }
        return childEditPolicy.getTargetEditPart(request);
    }


    /*
     * (non-Javadoc)
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#showSourceFeedback(org.eclipse.gef.Request)
     * @modified
     */
    public void showSourceFeedback(Request request) {
        if (childEditPolicy == null) {
            return;
        }
        childEditPolicy.showSourceFeedback(request);
    }


    /*
     * (non-Javadoc)
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#showTargetFeedback(org.eclipse.gef.Request)
     * @modified
     */
    public void showTargetFeedback(Request request) {
        if (childEditPolicy == null) {
            return;
        }
        childEditPolicy.showTargetFeedback(request);
    }


    /*
     * (non-Javadoc)
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#understandsRequest(org.eclipse.gef.Request)
     * @modified
     */
    public boolean understandsRequest(Request req) {
        if (childEditPart == null) {
            activate();
        }
        if (childEditPolicy == null) {
            return false;
        }
        return childEditPolicy.understandsRequest(req);
    }


}
