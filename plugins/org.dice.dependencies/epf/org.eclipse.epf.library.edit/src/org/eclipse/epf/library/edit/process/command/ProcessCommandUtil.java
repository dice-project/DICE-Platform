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
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.library.edit.IConfigurationApplicator;
import org.eclipse.epf.library.edit.Providers;
import org.eclipse.epf.library.edit.command.BatchCommand;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Deliverable;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.util.AssociationHelper;


/**
 * @author Phong Nguyen Le - Nov 23, 2005
 * @since 1.0
 */
public final class ProcessCommandUtil {
	public static final Set<EReference> CONTENT_ELEMENT_GUIDANCE_REFERENCES = Collections.unmodifiableSet(new HashSet<EReference>(Arrays.asList(new EReference[] {
			// guidance
			UmaPackage.eINSTANCE.getContentElement_Checklists(),
			UmaPackage.eINSTANCE.getContentElement_ConceptsAndPapers(),
			UmaPackage.eINSTANCE.getContentElement_Examples(),
			UmaPackage.eINSTANCE.getContentElement_Guidelines(),
			UmaPackage.eINSTANCE.getContentElement_Assets(),
			UmaPackage.eINSTANCE.getContentElement_SupportingMaterials()
	})));

	public static final EStructuralFeature[] DESCRIPTOR_REFRESHABLE_FEATURES = {
		// UmaPackage.eINSTANCE.getNamedElement_Name(),
		// UmaPackage.eINSTANCE.getMethodElement_PresentationName(),
		UmaPackage.eINSTANCE.getMethodElement_BriefDescription(),
		// guidance
//		UmaPackage.eINSTANCE.getBreakdownElement_Checklists(),
//		UmaPackage.eINSTANCE.getBreakdownElement_Concepts(),
//		UmaPackage.eINSTANCE.getBreakdownElement_Examples(),
//		UmaPackage.eINSTANCE.getBreakdownElement_Guidelines(),
//		UmaPackage.eINSTANCE.getBreakdownElement_ReusableAssets(),
//		UmaPackage.eINSTANCE.getBreakdownElement_SupportingMaterials()
	};

	public static final EStructuralFeature[] TASK_DESCRIPTOR_REFRESHABLE_FEATURES = {
		UmaPackage.eINSTANCE.getTaskDescriptor_AdditionallyPerformedBy(),
		UmaPackage.eINSTANCE.getTaskDescriptor_MandatoryInput(),
		UmaPackage.eINSTANCE.getTaskDescriptor_OptionalInput(),
		UmaPackage.eINSTANCE.getTaskDescriptor_Output(),
		UmaPackage.eINSTANCE.getTaskDescriptor_PerformedPrimarilyBy(),
		UmaPackage.eINSTANCE.getTaskDescriptor_SelectedSteps() 
	};

	public static final EStructuralFeature[] ROLE_DESCRIPTOR_REFRESHABLE_FEATURES = { 
		UmaPackage.eINSTANCE.getRoleDescriptor_ResponsibleFor() 
	};

//	public static final EStructuralFeature[] WORK_PRODUCT_REFRESHABLE_FEATURES = {
//
//	};

	/**
	 * Creates a refreshable feature map for the given descriptor. A descriptor
	 * feature is considered refreshable if there is a similiar feature in its
	 * linked MethodElement
	 * 
	 * @param descriptor
	 * @return the refreshable feature map for the given descriptor
	 */
	public static Map createRefreshableFeatureMap(Descriptor descriptor, Set excludeFeatures) {
		Map featureMap = new HashMap();
		addToFeatureMap(descriptor, DESCRIPTOR_REFRESHABLE_FEATURES, featureMap, excludeFeatures);
		if (descriptor instanceof TaskDescriptor) {
			addToFeatureMap(descriptor, TASK_DESCRIPTOR_REFRESHABLE_FEATURES,
					featureMap, excludeFeatures);
		} else if (descriptor instanceof RoleDescriptor) {
			addToFeatureMap(descriptor, ROLE_DESCRIPTOR_REFRESHABLE_FEATURES,
					featureMap, excludeFeatures);
		} else if (descriptor instanceof WorkProductDescriptor) {
			WorkProductDescriptor wpd = (WorkProductDescriptor) descriptor;
			EStructuralFeature feature = UmaPackage.eINSTANCE
			.getWorkProductDescriptor_DeliverableParts();
			if (wpd.getWorkProduct() instanceof Deliverable && !excludeFeatures.contains(feature)) {
				featureMap.put(feature, wpd.getDeliverableParts());
			}
		}
		return featureMap;
	}

	private static void addToFeatureMap(EObject eObject,
			EStructuralFeature[] features, Map featureMap, Set excludeFeatures) {
		for (int i = 0; i < features.length; i++) {
			EStructuralFeature feature = features[i];
			if (!excludeFeatures.contains(feature)) {
				Object value = eObject.eGet(feature);
				if (feature.isMany()) {
					value = new ArrayList((Collection) value);
				}
				featureMap.put(feature, value);
			}
		}
	}

	// private static boolean isValidReference(EObject e) {
	// if (e.eIsProxy()) {
	// return false;
	// }
	// if (e instanceof Descriptor) {
	// MethodElement element = ProcessUtil
	// .getAssociatedElement((Descriptor) e);
	// if (element == null || element.eIsProxy()) {
	// return false;
	// }
	// }
	// return true;
	// }

	/**
	 * Clears all attributes. For references, remove only those that are
	 * descriptors which are not linked to any content element.
	 * 
	 * @param descriptor
	 * @return
	 */
	public static Map clearRefreshableFeatures(Descriptor descriptor, Set excludeFeatures) {
		Map featureMap = createRefreshableFeatureMap(descriptor, excludeFeatures);
		for (Iterator iter = featureMap.keySet().iterator(); iter.hasNext();) {
			EStructuralFeature feature = (EStructuralFeature) iter.next();
			if (feature instanceof EAttribute) {
				descriptor.eSet(feature, feature.getDefaultValue());
			} else {
				if (feature.isMany()) {
					// work around to avoid ArrayIndexOutOfBoundsException
					//
					List list = ((List) descriptor.eGet(feature));
					switch (list.size()) {
					case 0:
						break;
					case 1:
						list.remove(0);
						break;
					default:
						list.clear();
					}
				} else {
					descriptor.eSet(feature, feature.getDefaultValue());
				}
				// if(feature.isMany()) {
				// List list = ((List)descriptor.eGet(feature));
				// List elementsToRemove = new ArrayList();
				// for (Iterator iterator = list.iterator(); iterator
				// .hasNext();) {
				// EObject e = (EObject) iterator.next();
				// if(!isValidReference(e)) {
				// elementsToRemove.add(e);
				// }
				// }
				// if(!elementsToRemove.isEmpty()) {
				// list.removeAll(elementsToRemove);
				// System.out
				// .println("ProcessCommandUtil.clearRefreshableFeatures():
				// changed");
				// }
				// }
				// else {
				// EObject obj = (EObject) descriptor.eGet(feature);
				// if(obj != null && obj != feature.getDefaultValue() &&
				// !isValidReference(obj)) {
				// descriptor.eSet(feature, feature.getDefaultValue());
				// System.out
				// .println("ProcessCommandUtil.clearRefreshableFeatures():
				// changed");
				// }
				// }
			}
		}

		return featureMap;
	}

	/**
	 * Clears all the refreshable features of the given descriptor
	 * 
	 * @param descriptor
	 * @param descriptorToOldRefreshableFeaturesMap
	 * @param excludeFeatures features to exclude from refreshing
	 */
	public static boolean clearDescriptor(Descriptor descriptor,
			Map descriptorToOldRefreshableFeaturesMap,
			Set excludeFeatures) {
		if (descriptorToOldRefreshableFeaturesMap != null) {
			// need to clear all the refreshable features of the existing
			// descriptor
			//
			if (!descriptorToOldRefreshableFeaturesMap.containsKey(descriptor)) {
				descriptorToOldRefreshableFeaturesMap
						.put(descriptor, clearRefreshableFeatures(descriptor, excludeFeatures));
				return true;
			}
		}
		return false;
	}
	
	private static boolean isValidDescriptorOf(Object element, MethodConfiguration config, Object descriptor, boolean excludeSuppressed) {		
		if(descriptor instanceof Descriptor) {
			Descriptor desc =  ((Descriptor)descriptor);
			if(excludeSuppressed && desc.getSuppressed().booleanValue()) {
				return false;
			}

			Object elementObj = ProcessUtil.getAssociatedElement(desc);
			
			if (Providers.getConfigurationApplicator() == null) {	//ConfigurationHelper.serverMode == true
				elementObj = LibraryEditUtil.getInstance().getCalcualtedElement((MethodElement) elementObj, config);				
			} else {	
				elementObj = Providers.getConfigurationApplicator().resolve(elementObj, config);
			}
			
			return element == elementObj;
		}
		return false;
	}
	
	/**
	 * Checks if the given <code>object</code> is a valid descriptor or descriptor wrapper of the given <code>element</code>
	 * @param element
	 * @param config
	 * @param object
	 * 
	 * @return
	 */
	private static boolean isValidDescriptorOrDescriptorWrapperOf(Object element, MethodConfiguration config, Suppression suppression, Object object) {
		if(object instanceof BreakdownElementWrapperItemProvider) {
			if(suppression.isInSuppressedList((BreakdownElementWrapperItemProvider) object)) {
				return false;
			}
			else {
				return isValidDescriptorOf(element, config, TngUtil.unwrap(object), false);
			}
		}
		return isValidDescriptorOf(element, config, object, true);
	}

	/**
	 * Gets the unsuppressed descriptor from a descriptor list. The descriptor list is usually
	 * the breakdown elements of an activity.
	 * 
	 * @param obj
	 * @param descriptorList
	 * @return
	 */
	public static Object getDescriptor(Object obj, List descriptorList, MethodConfiguration config) {
		return getDescriptor(obj, descriptorList, config, true);
	}
	
	static Object getDescriptor(Object obj, List descriptorList, MethodConfiguration config, boolean excludeSuppressed) {
		int size = descriptorList.size();
		for (int j = 0; j < size; j++) {
			// iterate thru list to see whether a valid descriptor linked with the given object
			// already exists
			//
			Object object = descriptorList.get(j);
			if(isValidDescriptorOf(obj, config, object, excludeSuppressed)) {
				return object;
			}
		}
		return null;
	}
	
	/**
	 * Gets the local descriptor, preferably unsuppressed one, of the given role, task, or workProduct.
	 * 
	 * @param obj
	 * @param activity
	 * @param config
	 * @return
	 */
	public static Object getBestDescriptor(Object obj, Activity activity, MethodConfiguration config) {
		List<?> descriptorList = activity.getBreakdownElements();
		int size = descriptorList.size();
		Descriptor descriptor = null;
		for (int j = 0; j < size; j++) {
			// iterate thru list to see whether a valid descriptor linked with the given object
			// already exists
			//
			Object object = descriptorList.get(j);
			if(isValidDescriptorOf(obj, config, object, false)) {
				Descriptor desc = (Descriptor) object;
				if(!desc.getSuppressed()) {
					return desc;
				} else if (descriptor == null) {
					descriptor = desc;
				}
			}
		}
		return descriptor;

	}
	
	/**
	 * Gets the valid descriptor, either local or inherited, of the given object under the given activity
	 * 
	 * @param obj
	 * @param activity
	 * @return
	 */
	public static Descriptor getValidDescriptor(Object obj, Activity activity, AdapterFactory adapterFactory) {
		Process proc = TngUtil.getOwningProcess(activity);
		MethodConfiguration config = proc.getDefaultContext();
		ITreeItemContentProvider adapter = (ITreeItemContentProvider) adapterFactory.adapt(activity, ITreeItemContentProvider.class);
		for (Iterator iter = adapter.getChildren(activity).iterator(); iter.hasNext();) {
			Object child = iter.next();
			if(isValidDescriptorOrDescriptorWrapperOf(obj, config, Suppression.getSuppression(proc), child)) {
				return (Descriptor) TngUtil.unwrap(child);
			}
		}
		return null;
	}
	
	

	/**
	 * Gets the local unsuppressed descriptor of the given role, task, or workProduct.
	 * 
	 * @param obj
	 * @param activity
	 * @return The descriptor of the object, or null if not exist.
	 */
	public static Object getDescriptor(Object obj, Activity activity, MethodConfiguration config) {
		return getDescriptor(obj, activity.getBreakdownElements(), config);
	}

	/**
	 * Creates Role Descriptors for the responsible roles of the given work product
	 * descriptor.
	 * 
	 * @param wpDesc
	 * @param activity
	 * @param roleDescriptors newly created role descriptors will be added to this list
	 * @return
	 */
	public static void addResponsibleRoleDescriptors(
			WorkProductDescriptor wpDesc, Activity activity,
			List<RoleDescriptor> roleDescriptors, Set descriptorsToRefresh, MethodConfiguration config) {
		if (wpDesc != null) {
			WorkProduct wp = wpDesc.getWorkProduct();
			if (wp != null) {
				if (TngUtil.isContributor(wp)) {
					wp = (WorkProduct) TngUtil.getBase(wp);
				}
				// get responsible role for work product
				List roles = AssociationHelper.getResponsibleRoles(wp);
				for (Iterator iterator = roles.iterator(); iterator.hasNext();) {
					Role role = (Role) iterator.next();
					if (TngUtil.isContributor(role)) {
						role = (Role) TngUtil.getBase(role);
					}
					boolean isNewDescriptor = false;
					// check for roledescriptor whether it's present in activity
					// breakdown elements
					RoleDescriptor roleDesc = (RoleDescriptor) getBestDescriptor(
							role, activity, config);
					if (roleDesc == null) {
						// check for roledescriptor whether it's present in base
						// activity.
						roleDesc = (RoleDescriptor) ProcessCommandUtil.getInheritedDescriptor(
								role, activity, config);
						if (roleDesc == null) {
							// check for roledescriptor whether it's present in
							// list of roledescriptors passed in
							roleDesc = (RoleDescriptor) getDescriptor(role,
									roleDescriptors, config);
							if (roleDesc == null) {
								roleDesc = ProcessUtil.createRoleDescriptor(role);
								roleDescriptors.add(roleDesc);
								isNewDescriptor = true;
							}
						}
					}
					if (!isNewDescriptor && descriptorsToRefresh != null 
							&& roleDesc.getIsSynchronizedWithSource().booleanValue()) {
						descriptorsToRefresh.add(roleDesc);
					}						
				}
			}
		}
	}

	/**
	 * Collects work product descriptors for the given
	 * <code>taskDescriptorFeature</code> based on the corresponding feature
	 * in the linked task of the given <code>taskDescriptor</code>
	 * 
	 * @param task the task that the task descriptor is linked to
	 * @param taskDescriptor the task descriptor whose work product descriptors in the given <code>taskDescriptorFeature</code>
	 *        will be collected
	 * @param taskFeature the task feature that is corresponding to the task descriptor feature
	 * @param taskDescriptorFeature the feature of task descriptor that holds references to 
	 *        the work product descriptor to collect
	 * @param activity the activity of descriptors
	 * @param config the selected configuration
	 * @param wpDescriptors
	 * @param wpdToDeliverablePartsMap
	 * @param wpdToDeliverableDescriptorMap
	 * @param descriptorsToRefresh
	 *            ouput that keeps the descriptor features that need to be
	 *            refreshed
	 * @param descriptorToNewFeatureValuesMap
	 *            output that keeps the collected work product descriptors
	 */
	private static void collectWorkProductDescrtiptors(
			Task task,
			TaskDescriptor taskDescriptor, EReference taskFeature,
			EStructuralFeature taskDescriptorFeature,
			MethodConfiguration config, Activity activity, List wpDescriptors,
			Map wpdToDeliverablePartsMap, Map wpdToDeliverableDescriptorMap,
			Set descriptorsToRefresh, Map descriptorToNewFeatureValuesMap) {
		List workProducts = (List) Providers.getConfigurationApplicator()
				.getReference(task, taskFeature, config);
		List list = new ArrayList();
		ProcessCommandUtil.createWorkProductDescriptors(workProducts, list, activity,
				wpDescriptors, wpdToDeliverablePartsMap,
				wpdToDeliverableDescriptorMap, descriptorsToRefresh, config);
		if (!list.isEmpty()) {
			BatchCommand.addFeatureValues(descriptorToNewFeatureValuesMap, taskDescriptor,
					taskDescriptorFeature, list);
		}
	}

	/**
	 * Creates work product descriptors from the given work products for the
	 * given activity if it does not have them already
	 * 
	 * @param workProducts
	 *            WorkProduct list
	 * @param featureValue
	 *            target WorkProductDescriptor list for the given WorkProduct
	 *            list
	 * @param activity
	 * @param wpDescriptors
	 *            newly created work product descriptor (output)
	 */
	static void createWorkProductDescriptors(List workProducts,
			List featureValue, Activity activity, List wpDescriptors,
			Map wpdToDeliverablePartsMap, Map wpdToDeliverableDescriptorMap,
			Set descriptorsToRefresh, MethodConfiguration config) {
		if (workProducts != null) {
			boolean wasEmpty = featureValue.isEmpty();
			for (int j = 0; j < workProducts.size(); j++) {
				WorkProduct wpObj = (WorkProduct) workProducts.get(j);
				if (wpObj != null) {
					if (TngUtil.isContributor(wpObj)) {
						wpObj = (WorkProduct) TngUtil.getBase(wpObj);
					}
					WorkProductDescriptor wpDesc = (WorkProductDescriptor) getBestDescriptor(wpObj, activity, config);
					if (wpDesc == null) {
	
						// get inherited work product descriptors
						wpDesc = (WorkProductDescriptor) ProcessCommandUtil
								.getInheritedDescriptor(wpObj, activity, config);
						if (wpDesc == null) {
							wpDesc = (WorkProductDescriptor) getDescriptor(wpObj, wpDescriptors, config);
							if (wpDesc == null) {
								wpDesc = ProcessCommandUtil.createWorkProductDescriptor(wpObj,
										config, wpdToDeliverablePartsMap, true);
								wpDescriptors.add(wpDesc);
	
								// automatic adding to the existing deliverable
								// descriptor in the activity's scope if there
								// is
								// any
								// valid one.
								//
								WorkProductDescriptor descriptor = UserInteractionHelper
										.getDeliverable(activity, wpObj);
								if (descriptor != null) {
									wpdToDeliverableDescriptorMap.put(wpDesc,
											descriptor);
								}
	
							} else {
								if (descriptorsToRefresh != null) {
									descriptorsToRefresh.add(wpDesc);
								}
								if (wpObj instanceof Deliverable
										&& wpdToDeliverablePartsMap != null) {
									ProcessCommandUtil.createDeliverableParts(wpDesc,
											(Deliverable) wpObj, config,
											wpdToDeliverablePartsMap, descriptorsToRefresh);
								}
							}
						}
					}
					if (wasEmpty || !featureValue.contains(wpDesc)) {
						featureValue.add(wpDesc);
					}
				}
			}
		}
	}

	/**
	 * Creates a task descriptor for the given task within the activity if it
	 * does not exist already, and update the roleDescriptors and wpDescriptors lists
	 * associated with this Task descriptor. Caller should add the new task
	 * descriptor and the otherDescriptors into the activity's breakdown element
	 * list.
	 * 
	 * @param task
	 * @param activity
	 * @param roleDescriptors
	 *            List to hold the created role descriptors associated with this
	 *            new task descriptor, no role descriptor will be created if
	 *            roleDescriptors is null
	 * 
	 * @param wpDescriptors
	 *            List to hold the created workproduct descriptors associated
	 *            with this new task descriptor, no workproduct descriptor will
	 *            be created if wpDescriptors is null
	 * 
	 * @param wpdToDeliverableDescriptorMap 
	 *            Map to hold work product descriptor and deliverable descriptor to assign
	 *            the work product descriptor to
	 *             
	 * @return TaskDescriptor or null if the task descriptor for the given task
	 *         already exists, and updated the roleDescriptors and wpDescriptors
	 *         lists
	 */
	public static TaskDescriptor createTaskDescriptor(Task task,
			Activity activity, List roleDescriptors, List wpDescriptors,
			Map wpdToDeliverablePartsMap, Map wpdToDeliverableDescriptorMap,
			Set descriptorsToRefresh, Map descriptorToNewFeatureValuesMap,
			MethodConfiguration config, boolean useExistingDescriptor, Set synchFeatures) {
		
		return createTaskDescriptor(
				task,
				null,
				activity, 
				roleDescriptors, 
				wpDescriptors,
				wpdToDeliverablePartsMap, 
				wpdToDeliverableDescriptorMap,
				descriptorsToRefresh, 
				descriptorToNewFeatureValuesMap,
				config, 
				useExistingDescriptor, 
				synchFeatures);	
	}
	
	public static TaskDescriptor createTaskDescriptor(Task task, TaskDescriptor taskDescriptorTosyn,
			Activity activity, List roleDescriptors, List wpDescriptors,
			Map wpdToDeliverablePartsMap, Map wpdToDeliverableDescriptorMap,
			Set descriptorsToRefresh, Map descriptorToNewFeatureValuesMap,
			MethodConfiguration config, boolean useExistingDescriptor, Set synchFeatures) {
		if (TngUtil.isContributor(task)) {
			task = (Task) TngUtil.getBase(task);
		}
	
		// create task descriptor object
		//
		TaskDescriptor taskDesc = null;
		if (useExistingDescriptor) {
			taskDesc = taskDescriptorTosyn == null ? 
					(TaskDescriptor) getBestDescriptor(task, activity, config) :
						taskDescriptorTosyn;
		}
		boolean isNewTaskDescriptor = false;
	
		// if ( taskDesc != null )
		// {
		// return taskDesc;
		// }
		// taskDesc = createTaskDescriptor(task);
	
		if (taskDesc == null) {
			taskDesc = ProcessCommandUtil.createTaskDescriptor(task, config);
			isNewTaskDescriptor = true;
		} else {
			if (descriptorsToRefresh != null && taskDesc.getIsSynchronizedWithSource().booleanValue()) {
				descriptorsToRefresh.add(taskDesc);
			}
		}
	
		IConfigurationApplicator configApplicator = Providers
				.getConfigurationApplicator();
	
		if (isNewTaskDescriptor) {
			// add all task steps to TaskDescriptor step selection
			//
			List steps = (List) configApplicator.getReference(task
					.getPresentation(), task, UmaPackage.eINSTANCE
					.getContentDescription_Sections(), config);
			taskDesc.getSelectedSteps().addAll(steps);
			
		}
	
		// moved this code to BSDropCommand#execute() to support undo and
		// progress monitor
		//
		// else {
		// for (Iterator iter = steps.iterator(); iter.hasNext();) {
		// Object step = iter.next();
		// if (!taskDesc.getSelectedSteps().contains(step)) {
		// taskDesc.getSelectedSteps().add(step);
		// }
		// }
		// }
	
		if (roleDescriptors != null) {
			// get/create role descriptor for primary performers
			//
			EReference ref = UmaPackage.eINSTANCE.getTask_PerformedBy();
			List primaryPerformers = synchFeatures.contains(ref) ? (List) configApplicator.getReference(task,
					ref, config) : null;
			if (primaryPerformers != null) {
				for (int j = 0; j < primaryPerformers.size(); j++) {
					Role role = (Role) primaryPerformers.get(j);
					if (role != null) {
						// if (TngUtil.isContributor(role)) {
						// role = (Role) TngUtil.getBase(role);
						// }
			
						// check for local descriptor
						RoleDescriptor primaryRoleDesc = (RoleDescriptor) getBestDescriptor(
								role, activity, config);
						boolean isNewRoleDescriptor = false;
						if (primaryRoleDesc == null) {
							// check for inherited descriptor
							primaryRoleDesc = (RoleDescriptor) ProcessCommandUtil.getInheritedDescriptor(
									role, activity, config);
							if (primaryRoleDesc == null) {
								// check for descriptor in passed in descriptor list
								primaryRoleDesc = (RoleDescriptor) getDescriptor(role,
										roleDescriptors, config);
								if (primaryRoleDesc == null) {
									primaryRoleDesc = ProcessUtil.createRoleDescriptor(role, true);
									isNewRoleDescriptor = true;
									roleDescriptors.add(primaryRoleDesc);
								}
							}
						}
						if (descriptorsToRefresh != null && !isNewRoleDescriptor
								&& primaryRoleDesc.getIsSynchronizedWithSource().booleanValue()) {
							descriptorsToRefresh.add(primaryRoleDesc);
						}
						if (isNewTaskDescriptor && isNewRoleDescriptor) {
							taskDesc.getPerformedPrimarilyBy().add(primaryRoleDesc);
						} else {
							BatchCommand.addFeatureValue(descriptorToNewFeatureValuesMap, taskDesc,
									UmaPackage.eINSTANCE
											.getTaskDescriptor_PerformedPrimarilyBy(),
									primaryRoleDesc);
						}
					}
				}
			}
	
			// get/create role descriptors for additional performing roles
			//
			ref = UmaPackage.eINSTANCE.getTask_AdditionallyPerformedBy();			
			List additionalPerformers = synchFeatures.contains(ref) ? (List) configApplicator.getReference(
					task, ref, config) : null;
			if (additionalPerformers != null) {
				for (int j = 0; j < additionalPerformers.size(); j++) {
					Role roleObj = (Role) additionalPerformers.get(j);
					// check for local descriptor
					RoleDescriptor roleDesc = (RoleDescriptor) getBestDescriptor(
							roleObj, activity, config);
					boolean isNewRoleDescriptor = false;
					if (roleDesc == null) {
						// check for inherited descriptor
						roleDesc = (RoleDescriptor) ProcessCommandUtil.getInheritedDescriptor(
								roleObj, activity, config);
						if (roleDesc == null) {
							roleDesc = (RoleDescriptor) getDescriptor(roleObj,
									roleDescriptors, config);
							if (roleDesc == null) {
								roleDesc = ProcessUtil.createRoleDescriptor(roleObj, true);
								isNewRoleDescriptor = true;
								roleDescriptors.add(roleDesc);
							}
						}
					}
					if (descriptorsToRefresh != null && !isNewRoleDescriptor
							&& roleDesc.getIsSynchronizedWithSource().booleanValue()) {
						descriptorsToRefresh.add(roleDesc);
					}
					if (isNewTaskDescriptor && isNewRoleDescriptor) {
						taskDesc.getAdditionallyPerformedBy().add(roleDesc);
					} else {
						BatchCommand.addFeatureValue(
								descriptorToNewFeatureValuesMap,
								taskDesc,
								UmaPackage.eINSTANCE
										.getTaskDescriptor_AdditionallyPerformedBy(),
								roleDesc);
	
					}
				}
			}
		}
		
	
		if (wpDescriptors != null) {
			if (!isNewTaskDescriptor && !synchFeatures.contains(UmaPackage.eINSTANCE.getDeliverable_DeliveredWorkProducts())) {
				wpdToDeliverablePartsMap = null;
			}
			
			// create work product descriptor for input workproducts
			//
			if (isNewTaskDescriptor || synchFeatures.contains(UmaPackage.eINSTANCE.getTask_MandatoryInput())) {
				collectWorkProductDescrtiptors(task, taskDesc, UmaPackage.eINSTANCE
						.getTask_MandatoryInput(), UmaPackage.eINSTANCE
						.getTaskDescriptor_MandatoryInput(), config, activity,
						wpDescriptors, wpdToDeliverablePartsMap,
						wpdToDeliverableDescriptorMap, descriptorsToRefresh,
						descriptorToNewFeatureValuesMap);
			}
			if (isNewTaskDescriptor || synchFeatures.contains(UmaPackage.eINSTANCE.getTask_OptionalInput())) {
				collectWorkProductDescrtiptors(task, taskDesc, UmaPackage.eINSTANCE
						.getTask_OptionalInput(), UmaPackage.eINSTANCE
						.getTaskDescriptor_OptionalInput(), config, activity,
						wpDescriptors, wpdToDeliverablePartsMap,
						wpdToDeliverableDescriptorMap, descriptorsToRefresh,
						descriptorToNewFeatureValuesMap);
			}
	
			// create work product descriptor for output workproducts
			//
			if (isNewTaskDescriptor || synchFeatures.contains(UmaPackage.eINSTANCE.getTask_Output())) {
				collectWorkProductDescrtiptors(task, taskDesc, UmaPackage.eINSTANCE
						.getTask_Output(), UmaPackage.eINSTANCE
						.getTaskDescriptor_Output(), config, activity,
						wpDescriptors, wpdToDeliverablePartsMap,
						wpdToDeliverableDescriptorMap, descriptorsToRefresh,
						descriptorToNewFeatureValuesMap);
			}
		}
	
		if (isNewTaskDescriptor) {
			return taskDesc;
		}
	
		return null;
	}

	private static TaskDescriptor createTaskDescriptor(Task task, MethodConfiguration config) {
		TaskDescriptor taskDesc = UmaFactory.eINSTANCE.createTaskDescriptor();
		taskDesc.setTask(task);
		taskDesc.setName(task.getName());
//		taskDesc.setPresentationName(StrUtil
//				.isBlank(task.getPresentationName()) ? task.getName() : task
//				.getPresentationName());
		
		String pName = LibraryEditUtil.getInstance().getPresentationName(task, config);
		taskDesc.setPresentationName(pName);
			
		// taskDesc.setBriefDescription(task.getBriefDescription());
		return taskDesc;
	}

	/**
	 * Creates deliverable parts for the given work product descriptor from the
	 * deliverable work products of the given deliverable add put them in
	 * <code>wpDescToDeliverableParts</code>
	 * 
	 * @param wpd
	 * @param deliverable
	 * @param wpDescToDeliverableParts
	 *            (output) map of work product descriptor to its newly created
	 *            deliverable parts
	 */
	public static void createDeliverableParts(WorkProductDescriptor wpd,
			Deliverable deliverable, MethodConfiguration config,
			Map wpDescToDeliverableParts, Set descriptorsToRefresh) {
		IConfigurationApplicator configApplicator = Providers
				.getConfigurationApplicator();
	
		List deliveredWPs = (List) configApplicator.getReference(deliverable,
				UmaPackage.eINSTANCE.getDeliverable_DeliveredWorkProducts(),
				config);
		List parts = new ArrayList();
		if (wpd.getDeliverableParts().isEmpty()) {
			for (Iterator iter = deliveredWPs.iterator(); iter.hasNext();) {
				parts.add(ProcessCommandUtil.createWorkProductDescriptor(
						(WorkProduct) iter.next(), config,
						wpDescToDeliverableParts));
			}
		} else {
			for (Iterator iter = deliveredWPs.iterator(); iter.hasNext();) {
				WorkProduct workProduct = (WorkProduct) iter.next();
				WorkProductDescriptor desc = (WorkProductDescriptor) getDescriptor(workProduct, wpd.getDeliverableParts(), config);
				if (desc == null) {
					desc = ProcessCommandUtil.createWorkProductDescriptor(
							workProduct, config, wpDescToDeliverableParts);
				} else {
					if(descriptorsToRefresh != null) {
						descriptorsToRefresh.add(desc);
					}
					if (workProduct instanceof Deliverable) {
						createDeliverableParts(desc, (Deliverable) workProduct,
								config, wpDescToDeliverableParts, descriptorsToRefresh);
					}
				}
				parts.add(desc);
			}
		}
		if (!parts.isEmpty()) {
			wpDescToDeliverableParts.put(wpd, parts);
		}
	}

	public static WorkProductDescriptor createWorkProductDescriptor(
			WorkProduct wp, MethodConfiguration config,
			Map wpDescToDeliverableParts) {
		return createWorkProductDescriptor(wp, config, wpDescToDeliverableParts, false);
	}
	
	/**
	 * Creates work product descriptor for the given work product
	 * 
	 * @param wp
	 * @param config
	 * @param wpDescToDeliverableParts
	 *            (output) map of work product descriptor to its newly created
	 *            deliverable parts
	 * @return new WorkProductDescriptor or <code>null</code> if the
	 *         descriptor for the given work product already exists
	 */
	public static WorkProductDescriptor createWorkProductDescriptor(
			WorkProduct wp, MethodConfiguration config,
			Map wpDescToDeliverableParts, boolean isDynamic) {
		WorkProductDescriptor wpd = ProcessUtil.createWorkProductDescriptor(wp, isDynamic);
		if (wp instanceof Deliverable && wpDescToDeliverableParts != null) {
			createDeliverableParts(wpd, (Deliverable) wp, config,
					wpDescToDeliverableParts, null);
		}
		return wpd;
	}

	/**
	 * Get inhertied descriptor for the given role, task or workProduct. Unlike local descriptor, an inherited 
	 * descriptor that is externally suppressed is considered valid.
	 * 
	 * @param obj
	 * @param activity
	 * @return descriptor of the object, null if not exists
	 */
	public static Object getInheritedDescriptor(Object obj, Activity activity, MethodConfiguration config) {	
//		if (ProcessUtil.isSynFree()) {
//			return null;
//		}
		for (VariabilityType variabilityType = activity.getVariabilityType();
			variabilityType == VariabilityType.EXTENDS || variabilityType == VariabilityType.LOCAL_CONTRIBUTION;
			variabilityType = activity.getVariabilityType()) {
			VariabilityElement element = activity.getVariabilityBasedOnElement();
	
			if (element instanceof Activity) {
				Activity baseActivity = (Activity) element;
				Object desc = getBestDescriptor(obj, baseActivity, config);
				if(desc != null) {
					return desc;
				}
				activity = baseActivity;
			}
			else {
				break;
			}
		}
	
		return null;
	}
	
	/**
	 * Get the map of task to features that contain the work product 
	 * (input or output of the task)
	 * 
	 * @param tasks
	 * @param wp
	 * @return Map of task to features that contain the work product
	 */
	public static Map getFeaturesMap(List tasks, WorkProduct wp, MethodConfiguration config) {
		Map featuresMap = new HashMap();
		EReference[] taskFeatures = {
				UmaPackage.eINSTANCE.getTask_MandatoryInput(),
				UmaPackage.eINSTANCE.getTask_OptionalInput(),
				UmaPackage.eINSTANCE.getTask_Output()
		};
		for (int i = tasks.size() - 1; i > -1; i--) {
			Task task = (Task) tasks.get(i);
			for (int j = 0; j < taskFeatures.length; j++) {
				EReference feature = taskFeatures[j];
				Object value = Providers.getConfigurationApplicator().getReference(task, feature, config);
				if((feature.isMany() && ((List)value).contains(wp)) ||
						value == wp) 
				{
					Collection features = (Collection) featuresMap.get(task);
					if(features == null) {
						features = new ArrayList();	
						featuresMap.put(task, features);
					}
					features.add(feature);
				}
			}
		}
		return featuresMap;
	}

}
