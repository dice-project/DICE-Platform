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
package org.eclipse.epf.library.edit.process.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.ActivityHandler;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;

/**
 * @author Phong Nguyen Le - Jul 6, 2006
 * @since  1.0
 */
public class LocallyReplaceAndDeepCopyCommand extends ReplaceActivityCommand {

	/**
	 * @param wrapper
	 */
	public LocallyReplaceAndDeepCopyCommand(BreakdownElementWrapperItemProvider wrapper) {
		super(wrapper);		
		setLabel(LibraryEditResources.localReplacementAndDeepCopy_text);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.process.command.ReplaceActivityCommand#doVary()
	 */
	protected void doVary() {
		// deep copy the activity
		//
		Activity activity = (Activity) TngUtil.unwrap(wrapper);
		if(!UserInteractionHelper.confirmDeepCopy(Collections.singletonList(activity))) {
			return;
		}		
		Process targetProcess = (Process) wrapper.getTopItem();
		MethodConfiguration deepCopyConfig = null;
		try {
			deepCopyConfig = UserInteractionHelper.chooseDeepCopyConfiguration(targetProcess, wrapper.getAdapterFactory());
		}
		catch(OperationCanceledException e) {
			return;
		}
		ActivityHandler activityHandler = new ActivityHandler();
		activityHandler.setDeepCopyConfig(deepCopyConfig);
		activityHandler.setTargetProcess(targetProcess);
		activityHandler.deepCopy(activity);
		Activity copy = (Activity) activityHandler.getActivities().get(0);
		
		super.doVary();
		
		// replace the local replacement with deep copy
		//
		int pos = createdActivities.size() - 1;
		Activity localReplacement = (Activity) createdActivities.get(pos);		
		for (Iterator iter = localReplacement.getOppositeFeatures().iterator(); iter.hasNext();) {
			OppositeFeature oppositeFeature = (OppositeFeature) iter.next();
			Object value = localReplacement.getOppositeFeatureValue(oppositeFeature);
			if(value != null) {
				EStructuralFeature feature = oppositeFeature.getTargetFeature();
				if(oppositeFeature.isMany()) {
					for (Iterator iterator = new ArrayList(((Collection)value)).iterator(); iterator
					.hasNext();) {		
						EObject o = (EObject) iterator.next();
						if(feature.isMany()) {
							List list = (List) o.eGet(feature);
							list.remove(localReplacement);
							list.add(copy);
						}
						else {
							o.eSet(feature, copy);
						}
					}
				}
				else {
					EObject o = (EObject) value;
					if(feature.isMany()) {
						List list = (List) o.eGet(feature);
						list.remove(localReplacement);
						list.add(copy);
					}
					else {
						o.eSet(feature, copy);
					}
				}
			}
		}
		createdActivities.set(pos, copy);
		copy.setVariabilityBasedOnElement(localReplacement.getVariabilityBasedOnElement());
		copy.setVariabilityType(localReplacement.getVariabilityType());
		copy.setPresentedAfter(localReplacement.getPresentedAfter());
		copy.setPresentedBefore(localReplacement.getPresentedBefore());
		Activity superAct = localReplacement.getSuperActivities();
		int id = superAct.getBreakdownElements().indexOf(localReplacement);
		superAct.getBreakdownElements().set(id, copy);
		
		// add copy package to the package of super activity
		//
		((ProcessPackage)superAct.eContainer()).getChildPackages().add((MethodPackage) copy.eContainer());
	}
}
