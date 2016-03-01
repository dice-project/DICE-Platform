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
package org.eclipse.epf.diagram.ad.custom.commands;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.emf.core.util.CrossReferenceAdapter;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityPartition;

/**
 * DestroyCommand to destroy ActivityPartition and all the elements inside the partitions.
 * @author Shashidhar Kannoori
 *
 */
public class ActivityPartitionDestroyCommand extends DestroyElementCommand {

	
	private EditPart host;

	/**
	 * @param request
	 */
	public ActivityPartitionDestroyCommand(DestroyElementRequest request, EditPart host) {
		super(request);
		this.host = host;
	}
	
	protected EObject getElementToDestroy() {
		View view = (View) host.getModel();
		EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if (annotation != null) {
			return view;
		}
		return super.getElementToDestroy();
	}
	
	protected void tearDownIncomingReferences(EObject destructee) {
		
//		moveChildrensToParent(destructee);
		
		CrossReferenceAdapter crossReferencer = CrossReferenceAdapter
				.getExistingCrossReferenceAdapter(destructee);
		if (crossReferencer != null) {
			Collection inverseReferences = crossReferencer
					.getInverseReferences(destructee);
			if (inverseReferences != null) {
				int size = inverseReferences.size();
				if (size > 0) {
					Setting setting;
					EReference eRef;
					Setting[] settings = (Setting[]) inverseReferences
							.toArray(new Setting[size]);
					for (int i = 0; i < size; ++i) {
						setting = settings[i];
						eRef = (EReference) setting
								.getEStructuralFeature();
						if (eRef.isChangeable()
								&& (eRef.isDerived() == false)
								&& (eRef.isContainment() == false)
								&& (eRef.isContainer() == false)) {
//							EcoreUtil.remove(setting.getEObject());
							EcoreUtil.remove(setting.getEObject(), eRef,
									destructee);
						}
					}
				}
			}
		}
	}

	private void moveChildrensToParent(EObject destructee) {
		if(destructee instanceof ActivityPartition){
			ActivityPartition partition = (ActivityPartition)destructee;
			Activity activity = partition.getInActivity();
			List nodes = partition.getNodes();
			
			for (Iterator iter = nodes.iterator(); iter.hasNext();) {
				ActivityNode node = (ActivityNode) iter.next();
				activity.getNodes().add(node);
				node.getInPartitions().remove(partition);
				//iter.remove();
			}
			for (Iterator iter = partition.getEdges().iterator(); iter.hasNext();) {
				ActivityEdge edge = (ActivityEdge) iter.next();
				activity.getEdges().add(edge);
				edge.getInPartitions().remove(partition);
				//iter.remove();
			}
		}
	}

}
