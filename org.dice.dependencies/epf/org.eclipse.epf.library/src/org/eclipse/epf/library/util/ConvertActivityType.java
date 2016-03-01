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
package org.eclipse.epf.library.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.epf.uma.UmaPackage;


/**
 * class to convert activity types
 * 
 * @author Jeff Hardy
 * @author Shilpa Toraskar
 * @since 1.0
 *
 */
public class ConvertActivityType {

//	private static Activity oldActivity = null;
//	private static Activity newActivity = null;
//	
//	// original activity's container
//	private static ProcessPackage oldActivityContainer = null;
//	
//	// the original activity's breakdown list
//	private static List oldActivityBreakdown = null;
//	
//	// index of the original activity in it's superActivity's breakdown list
//	private static int oldActivityBreakdownIndex = -1;
//	
//	// original Activity's parent activity
//	private static Activity superAct = null;
//	
//	// parent activity's breakdown list
//	private static EList superActbreakdownList = null;
//	
//	// original Activity's variabilityType
//	private static VariabilityType oldActVarType = null;
//	
//	// original Activity's variabilityElement
//	private static VariabilityElement oldActVarElement = null;
//	
//	// original Activity's immeditateVarieties
//	private static List oldActImmediateVarietiesList = null;

	public static int[] compatibleActivities = { UmaPackage.ACTIVITY,
			UmaPackage.ITERATION,
			UmaPackage.PHASE,
			UmaPackage.CAPABILITY_PATTERN,
			UmaPackage.DELIVERY_PROCESS,
	};

	public static List compatibleActivitiesList = new ArrayList();
	static {
		for (int i = 0; i < compatibleActivities.length; i++)
			compatibleActivitiesList.add(new Integer(compatibleActivities[i]));
	}

	// This method is buggy and is replaced with TypeConverter.convertActivity()
	//
//	public static Activity convertActivity(Activity oldActivity, int newType,
//			DeleteMethodElementCommand command) {
//
//		
//		ConvertActivityType.oldActivity = oldActivity;
//		
//		// activity is already this type
//		if (newType == oldActivity.eClass().getClassifierID()) {
//			return null;
//		}
//		
//		// newType is not valid
//		if (!compatibleActivitiesList.contains(new Integer(newType))) {
//			return null;
//		}
//		
//		Activity newActivity = doConvert(newType, command);
//
//		return newActivity;
//	}
//
//	private static Activity doConvert(int newType, DeleteMethodElementCommand command) {
//		
//		try {
//			File oldActivityPathFile = new File(LibraryService.getInstance()
//					.getCurrentMethodLibraryPath(), MethodLibraryPersister
//					.getElementPath(oldActivity));
//
//			// create new activity
//			newActivity = createNewActivity(newType);
//
//			// TODO: store oldActivity's features here, and don't set them until
//			// after oldactivity is deleted
//			
//			// store oldActivity's breakdown list
//			oldActivityBreakdown = new ArrayList();
//			for (Iterator iter = oldActivity.getBreakdownElements().iterator();iter.hasNext();) {
//				oldActivityBreakdown.add(iter.next());
//			}
//			oldActivityBreakdownIndex = getIndexInBreakdown(oldActivity);
//			
//			// copy all data from oldActivity to newActivity
//			Iterator iter = oldActivity.eClass().getEAllStructuralFeatures()
//					.iterator();
//			while (iter.hasNext()) {
//				EStructuralFeature feature = (EStructuralFeature) iter.next();
//				Object o = oldActivity.eGet(feature);
//				if (feature.getFeatureID() != UmaPackage.DESCRIBABLE_ELEMENT__PRESENTATION
//						&& feature.getFeatureID() != UmaPackage.METHOD_ELEMENT__GUID
//						&& feature.getFeatureID() != UmaPackage.ACTIVITY__BREAKDOWN_ELEMENTS
//						&& feature.getFeatureID() != UmaPackage.ACTIVITY__SUPER_ACTIVITIES 
//						&& feature.getFeatureID() != UmaPackage.PROCESS__INCLUDES_PATTERNS
//						&& feature.getFeatureID() != UmaPackage.PROCESS__VALID_CONTEXT
//						&& feature.getFeatureID() != UmaPackage.PROCESS__DEFAULT_CONTEXT
//						&& feature.getFeatureID() != UmaPackage.DELIVERY_PROCESS__COMMUNICATIONS_MATERIALS
//						&& feature.getFeatureID() != UmaPackage.DELIVERY_PROCESS__EDUCATION_MATERIALS
//						) {
//					newActivity.eSet(feature, o);
//				}
//			}
//
//			// store Guid here
//			String oldActivityGuid = oldActivity.getGuid();
//
//			// store old activity's eContainer
//			oldActivityContainer = (ProcessPackage) oldActivity
//					.eContainer();
//
//			// set container
//			oldActivityContainer.getProcessElements().remove(oldActivity);
//			oldActivityContainer.getProcessElements().add(newActivity);
//
//			// create presentation
//			ContentDescription newContentDesc = ContentDescriptionFactory
//					.createContentDescription(newActivity);
//			newActivity.setPresentation(newContentDesc);
//
//			File newActivityPathFile = new File(LibraryService.getInstance()
//					.getCurrentMethodLibraryPath(), MethodLibraryPersister
//					.getElementPath(newActivity));
//			ContentResourceScanner scanner = new ContentResourceScanner(
//					oldActivityPathFile);
//			scanner.setTargetRootPath(newActivityPathFile);
//
//			// copy all presentation data from oldActivity to newActivity
//			ContentDescription oldContentDesc = oldActivity.getPresentation();
//			iter = oldContentDesc.eClass().getEAllAttributes().iterator();
//			while (iter.hasNext()) {
//				EAttribute attrib = (EAttribute) iter.next();
//				if (attrib.getFeatureID() != UmaPackage.PROCESS_DESCRIPTION__EXTERNAL_ID
//						&& attrib.getFeatureID() != UmaPackage.PROCESS_DESCRIPTION__SCOPE
//						&& attrib.getFeatureID() != UmaPackage.PROCESS_DESCRIPTION__USAGE_NOTES
//						&& attrib.getFeatureID() != UmaPackage.DELIVERY_PROCESS_DESCRIPTION__ESTIMATING_TECHNIQUE
//						&& attrib.getFeatureID() != UmaPackage.DELIVERY_PROCESS_DESCRIPTION__PROJECT_CHARACTERISTICS
//						&& attrib.getFeatureID() != UmaPackage.DELIVERY_PROCESS_DESCRIPTION__PROJECT_MEMBER_EXPERTISE
//						&& attrib.getFeatureID() != UmaPackage.DELIVERY_PROCESS_DESCRIPTION__RISK_LEVEL
//						&& attrib.getFeatureID() != UmaPackage.DELIVERY_PROCESS_DESCRIPTION__SCALE
//						&& attrib.getFeatureID() != UmaPackage.DELIVERY_PROCESS_DESCRIPTION__TYPE_OF_CONTRACT
//						) {
//					Object o = oldContentDesc.eGet(attrib);
//					if (o instanceof String && ((String) o).trim().length() > 0) {
//						// process links
//						scanner.resolveResources(newActivity, (String) o, ""); //$NON-NLS-1$
//						// scanner.resolveResources(newActivity, (String) o,
//						// MethodLibraryPersister.getElementPath(newActivity));
//						// //$NON-NLS-1$
//					}
//					newContentDesc.eSet(attrib, o);
//				}
//			}
//			
//			// make breakdown links
//			List oldActBreakdownList = new ArrayList();
//			oldActBreakdownList.addAll(oldActivity.getBreakdownElements());
//			for (iter = oldActBreakdownList.iterator();iter.hasNext();) {
//				BreakdownElement be = (BreakdownElement)iter.next();
//				be.setSuperActivities(null);
//			}
//			
//			oldActivity.getBreakdownElements().clear();
//			newActivity.getBreakdownElements().addAll(oldActivityBreakdown);
//			// move new activity to proper index in the parent act's breakdown list
//			superAct = UmaUtil.getParentActivity(oldActivity);
//			superActbreakdownList = (EList)superAct.getBreakdownElements();
//			superActbreakdownList.add(oldActivityBreakdownIndex, newActivity);
//			
//			// handle variability
//			oldActVarElement = oldActivity.getVariabilityBasedOnElement();
//			oldActVarType = oldActivity.getVariabilityType();
//			oldActImmediateVarietiesList = new ArrayList();
//			oldActImmediateVarietiesList.addAll(AssociationHelper.getImmediateVarieties(oldActivity));
//			
//			if (oldActVarElement != null && oldActVarType != null) {
//				newActivity.setVariabilityBasedOnElement(oldActVarElement);
//				newActivity.setVariabilityType(oldActVarType);
//			}
//			
//			for (iter = oldActImmediateVarietiesList.iterator();iter.hasNext();) {
//				VariabilityElement ve = (VariabilityElement) iter.next();
//				ve.setVariabilityBasedOnElement(newActivity);
//			}
//
//			// create temp ProcessPackage and move oldActivity to it
//			ProcessPackage tempParent = UmaFactory.eINSTANCE.createProcessPackage();
//			tempParent.getProcessElements().add(oldActivity);
//			tempParent.setName("temp PP for deletion"); //$NON-NLS-1$
//
//			// delete old Activity here - if fails, roll back by deleting new Activity
//			command.execute();
//
//			if (!command.executed || command.failed) {
//				// delete failed, or user selected cancel on the "delete
//				// references" dialog
//				// clean up new Activity
//
//				undo();
//
//				newActivity = null;
//				newContentDesc = null;
//				return null;
//			}
//
//			// set new activity's GUID
//			newActivity.setGuid(oldActivityGuid);
//			
//			// TODO: update editor contents 
//
//			// TODO: on rollback, library may be dirty because of the
//			// container/contentdesc changes we've done
//			// fix this by storing all the old activity's info, then only create
//			// new activity after old one has
//			// been deleted.
//
//			Set modifiedResourceSet = new HashSet();
//			
//			// save new activity and its presentation
//			IFileBasedLibraryPersister.FailSafeMethodLibraryPersister persister = ContentDescriptionFactory
//					.getMethodLibraryPersister().getFailSafePersister();
//			try {
//				if (newActivity.eResource() != null)
//					modifiedResourceSet.add(newActivity.eResource());
//				if (newActivity.getPresentation().eResource() != null)
//					modifiedResourceSet.add(newActivity.getPresentation().eResource());
//				if (oldActivityContainer.eResource() != null)
//					modifiedResourceSet.add(oldActivityContainer.eResource());
//				if (superAct.eResource() != null)
//					modifiedResourceSet.add(superAct.eResource());
//
//				for (iter = modifiedResourceSet.iterator();iter.hasNext();) {
//					Resource res = (Resource) iter.next();
//					persister.save(res);
//				}
//				persister.commit();
//
//			} catch (Exception e) {
//				persister.rollback();
//				command.undo();
//				
//				undo();
//				LibraryPlugin
//						.getDefault()
//						.getMsgDialog()
//						.displayError(
//								LibraryResources.convertActivityError_title,
//								NLS.bind(LibraryResources.saveConvertedActivityError_msg, newActivity.getName()), 
//								LibraryResources.error_reason,
//								e);
//				newActivity = null;
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			command.undo();
//			undo();
//			LibraryPlugin
//					.getDefault()
//					.getMsgDialog()
//					.displayError(
//							LibraryResources.convertActivityError_title,
//							NLS.bind(LibraryResources.convertActivityError_msg, newActivity.getName()), 
//							LibraryResources.error_reason, 
//							ex);
//			newActivity = null;
//		}
//		return newActivity;
//	}
//
//	private static Activity createNewActivity(int newType) {
//		switch (newType) {
//			case UmaPackage.ACTIVITY:
//				return UmaFactory.eINSTANCE.createActivity();
//			case UmaPackage.ITERATION:
//				return UmaFactory.eINSTANCE.createIteration();
//			case UmaPackage.PHASE:
//				return UmaFactory.eINSTANCE.createPhase();
//			default:
//				return null;
//		}
//	}
//	
//	private static int getIndexInBreakdown(Activity act) {
//		int index = -1;
//		Activity superAct = UmaUtil.getParentActivity(act);
//		if (superAct instanceof ProcessComponent) {
//			// get process, find breakdown
//			index = ((ProcessComponent)superAct).getProcess().getBreakdownElements().indexOf(act);
//		}
//		else {
//			index = superAct.getBreakdownElements().indexOf(act);
//		}
//		return index;
//	}
//	
//	private static void undo() {
//		// undo moving of oldActivity to the fake PP
//		if (oldActivityContainer != null) {
//			oldActivityContainer.getProcessElements().remove(newActivity);
//			if (!oldActivityContainer.getProcessElements().contains(oldActivity))
//				oldActivityContainer.getProcessElements().add(oldActivity);
//		}
//		// undo the breakdown link changes
//		if (newActivity != null) {
//			newActivity.getBreakdownElements().clear();
//		}
//		if (oldActivityBreakdown != null && oldActivity != null) {
////			oldActivity.getBreakdownElements().clear();
//			oldActivity.getBreakdownElements().addAll(oldActivityBreakdown);
//		}
//		if (superActbreakdownList != null) {
//			if (newActivity != null) {
//				superActbreakdownList.remove(newActivity);
//			}
//			if (oldActivityBreakdownIndex != -1 && oldActivity != null &&
//					!superActbreakdownList.contains(oldActivity))
//				superActbreakdownList.add(oldActivityBreakdownIndex, oldActivity);
//		}
//		
//		// variability
//		if (oldActVarElement != null && oldActVarType != null) {
//			oldActivity.setVariabilityBasedOnElement(oldActVarElement);
//			oldActivity.setVariabilityType(oldActVarType);
//		}
//		
//		if (oldActImmediateVarietiesList != null) {
//			for (Iterator iter = oldActImmediateVarietiesList.iterator();iter.hasNext();) {
//				VariabilityElement ve = (VariabilityElement) iter.next();
//				ve.setVariabilityBasedOnElement(oldActivity);
//			}
//		}
//
//
//	}
}