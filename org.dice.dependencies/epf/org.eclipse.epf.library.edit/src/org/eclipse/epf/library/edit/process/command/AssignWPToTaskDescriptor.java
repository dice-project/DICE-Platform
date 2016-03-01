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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.Providers;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;


/**
 * Command to assign work products to task descriptor. It will set external
 * inputs, mandatory inputs, optional inputs and outputs.
 * 
 * @author Shilpa Toraskar
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class AssignWPToTaskDescriptor extends AddMethodElementCommand {

	private List workProducts;

	private Activity activity;

	private TaskDescriptor taskDesc;

	private Collection modifiedResources;
	
	private Collection affectedObjects;

	private int action;

	private HashMap map = new HashMap();

	List existingWPDescList = new ArrayList();

	List newWPDescList = new ArrayList();
	
	Collection<WorkProductDescriptor> subartifactDescriptors = new ArrayList<WorkProductDescriptor>();

	private MethodConfiguration config;
	
	private boolean calledForExculded = false;
	
	private DescriptorPropUtil propUtil;

	public AssignWPToTaskDescriptor(TaskDescriptor taskDesc, List workProducts,
			int action, MethodConfiguration config) {
		this(taskDesc, workProducts, action, config, false);
	}
	
	public AssignWPToTaskDescriptor(TaskDescriptor taskDesc, List workProducts,
			int action, MethodConfiguration config, boolean calledForExculded) {
		super(TngUtil.getOwningProcess(taskDesc));

		this.calledForExculded = calledForExculded;
		this.workProducts = workProducts;
		this.taskDesc = taskDesc;
		this.action = action;
		this.config = config;
		this.propUtil = DescriptorPropUtil.getDesciptorPropUtil();

		AdapterFactory aFactory = TngAdapterFactory.INSTANCE
				.getWBS_ComposedAdapterFactory();
		ItemProviderAdapter adapter = (ItemProviderAdapter) aFactory.adapt(
				taskDesc, ITreeItemContentProvider.class);
		Object parent = adapter.getParent(taskDesc);
		if (parent instanceof Activity) {
			this.activity = (Activity) parent;
		}
		this.modifiedResources = new HashSet();
		this.affectedObjects = new HashSet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		// add to default configuration if not there already
		if (!super.addToDefaultConfiguration(workProducts))
			return;

		for (Iterator it = workProducts.iterator(); it.hasNext();) {
			WorkProduct wp = (WorkProduct) it.next();
			WorkProductDescriptor newWpDesc = null;

			boolean isNewDescriptor = false;
			// check for local descriptor
			newWpDesc = (WorkProductDescriptor) ProcessCommandUtil
					.getDescriptor(wp, activity, config);
			if (newWpDesc == null) {
				// check for inherited descriptor
				newWpDesc = (WorkProductDescriptor) ProcessCommandUtil
						.getInheritedDescriptor(wp, activity, config);
				if (newWpDesc == null) {
					newWpDesc = ProcessUtil.createWorkProductDescriptor(wp);
					isNewDescriptor = true;

				}
			}
			if (isNewDescriptor)
				newWPDescList.add(newWpDesc);
			else
				existingWPDescList.add(newWpDesc);

			// get deliverable
			WorkProductDescriptor deliverable = UserInteractionHelper
					.getDeliverable(activity, wp);
			if (deliverable != null) {
				map.put(newWpDesc, deliverable);
			}
		}

		// add subartifacts to the activity's breakdown element list if there is any
		//
		for (Iterator<?> iter = workProducts.iterator(); iter
		.hasNext();) {
			Object element = iter.next();
			if (element instanceof Artifact) {
				Iterator<?> iterator = new AbstractTreeIterator<Object>(element, false) {
					
					/**
					 * Comment for <code>serialVersionUID</code>
					 */
					private static final long serialVersionUID = -4820477887426087262L;
					
					protected Iterator<?> getChildren(Object object) {
						Object subArtifacts = Providers.getConfigurationApplicator().getReference(
								(VariabilityElement) object,
								UmaPackage.eINSTANCE.getArtifact_ContainedArtifacts(),
								config);
						return ((Collection)subArtifacts).iterator();
					}
					
				};
				
				while (iterator.hasNext()) {
					Artifact subArtifact = (Artifact) iterator.next();
					WorkProductDescriptor newWpDesc = null;

					boolean isNewDescriptor = false;
					// check for local descriptor
					newWpDesc = (WorkProductDescriptor) ProcessCommandUtil
							.getDescriptor(subArtifact, activity, config);
					if (newWpDesc == null) {
						// check for inherited descriptor
						newWpDesc = (WorkProductDescriptor) ProcessCommandUtil
								.getInheritedDescriptor(subArtifact, activity, config);
						if (newWpDesc == null) {
							newWpDesc = ProcessUtil.createWorkProductDescriptor(subArtifact);
							isNewDescriptor = true;
						}
					}
					if (isNewDescriptor && !subartifactDescriptors.contains(newWpDesc)) {
						subartifactDescriptors.add(newWpDesc);
					}
				}
			}
		}

		redo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {

		if (action == IActionTypeConstants.ADD_EXTERNAL_INPUT) {
			taskDesc.getExternalInput().addAll(existingWPDescList);
			taskDesc.getExternalInput().addAll(newWPDescList);
		} else if (action == IActionTypeConstants.ADD_MANDATORY_INPUT) {
			taskDesc.getMandatoryInput().addAll(existingWPDescList);
			taskDesc.getMandatoryInput().addAll(newWPDescList);
		} else if (action == IActionTypeConstants.ADD_OPTIONAL_INPUT) {
			taskDesc.getOptionalInput().addAll(existingWPDescList);
			taskDesc.getOptionalInput().addAll(newWPDescList);
		} else if (action == IActionTypeConstants.ADD_OUTPUT) {
			taskDesc.getOutput().addAll(existingWPDescList);
			taskDesc.getOutput().addAll(newWPDescList);
		}

		if (ProcessUtil.isSynFree()) {
			if (calledForExculded) {
				List excludedList = null;
				EReference ref = null;
				if (action == IActionTypeConstants.ADD_MANDATORY_INPUT) {
					excludedList = taskDesc.getMandatoryInputExclude();
					ref = UmaPackage.eINSTANCE.getTaskDescriptor_MandatoryInput();
				} else if (action == IActionTypeConstants.ADD_OPTIONAL_INPUT) {
					excludedList = taskDesc.getOptionalInputExclude();
					ref = UmaPackage.eINSTANCE.getTaskDescriptor_OptionalInput();
				} else if (action == IActionTypeConstants.ADD_OUTPUT) {
					excludedList = taskDesc.getOutputExclude();
					ref = UmaPackage.eINSTANCE.getTaskDescriptor_Output();
				}
				if (excludedList != null) {
					excludedList.removeAll(workProducts);
				}
				
				TaskDescriptor greenParent = (TaskDescriptor) propUtil.getGreenParentDescriptor(taskDesc);
				if (greenParent != null) {
					EReference eRef = LibraryEditUtil.getInstance().getExcludeFeature(ref);
					List<WorkProduct> parentExecludeList = (List<WorkProduct>) greenParent.eGet(eRef);
					for (WorkProduct wp : (List<WorkProduct>) workProducts) {
						propUtil.removeGreenRefDelta(taskDesc, wp, eRef, true);
						if (parentExecludeList != null && parentExecludeList.contains(wp)) {
							propUtil.addGreenRefDelta(taskDesc, wp, eRef, false);
						}
					}
				}
			} else {
				propUtil.addLocalUsingInfo(existingWPDescList, taskDesc, getFeature(action));
				propUtil.addLocalUsingInfo(newWPDescList, taskDesc, getFeature(action));
			}
			
			for (WorkProductDescriptor rd : (List<WorkProductDescriptor>) newWPDescList) {
				propUtil.setCreatedByReference(rd, true);
			}
		}

		activity.getBreakdownElements().addAll(newWPDescList);
		if(!subartifactDescriptors.isEmpty()) {
			activity.getBreakdownElements().addAll(subartifactDescriptors);
		}

		if (map != null) {
			Set keyset = map.keySet();
			for (Iterator itor = keyset.iterator(); itor.hasNext();) {
				Object key = itor.next();
				WorkProductDescriptor wpDesc = (WorkProductDescriptor) map
						.get(key);

				// add to deliverable
				wpDesc.getDeliverableParts().add((WorkProductDescriptor) key);
			}
		}
	}
	
	private EReference getFeature(int action) {
		UmaPackage up = UmaPackage.eINSTANCE;
		
		if (action == IActionTypeConstants.ADD_MANDATORY_INPUT) {
			return up.getTaskDescriptor_MandatoryInput();		
		} 
		
		if (action == IActionTypeConstants.ADD_OPTIONAL_INPUT) {
			return up.getTaskDescriptor_OptionalInput();	
		}

		if (action == IActionTypeConstants.ADD_OUTPUT) {
			return up.getTaskDescriptor_Output();	
		}
		
		if (action == IActionTypeConstants.ADD_EXTERNAL_INPUT) {
			return up.getTaskDescriptor_ExternalInput();	
		}
		
		return null;
	}

	public void undo() {

		// remove from configuration if anything was added
		super.undo();

		if (action == IActionTypeConstants.ADD_EXTERNAL_INPUT) {
			taskDesc.getExternalInput().removeAll(existingWPDescList);
			taskDesc.getExternalInput().removeAll(newWPDescList);
		} else if (action == IActionTypeConstants.ADD_MANDATORY_INPUT) {
			taskDesc.getMandatoryInput().removeAll(existingWPDescList);
			taskDesc.getMandatoryInput().removeAll(newWPDescList);
		} else if (action == IActionTypeConstants.ADD_OPTIONAL_INPUT) {
			taskDesc.getOptionalInput().removeAll(existingWPDescList);
			taskDesc.getOptionalInput().removeAll(newWPDescList);
		} else if (action == IActionTypeConstants.ADD_OUTPUT) {
			taskDesc.getOutput().removeAll(existingWPDescList);
			taskDesc.getOutput().removeAll(newWPDescList);
		}

		if (ProcessUtil.isSynFree()) {
			if (calledForExculded) {
				List excludedList = null;
				EReference ref = null;
				if (action == IActionTypeConstants.ADD_MANDATORY_INPUT) {
					excludedList = taskDesc.getMandatoryInputExclude();
					ref = UmaPackage.eINSTANCE.getTaskDescriptor_MandatoryInput();
				} else if (action == IActionTypeConstants.ADD_OPTIONAL_INPUT) {
					excludedList = taskDesc.getOptionalInputExclude();
					ref = UmaPackage.eINSTANCE.getTaskDescriptor_OptionalInput();
				} else if (action == IActionTypeConstants.ADD_OUTPUT) {
					excludedList = taskDesc.getOutputExclude();
					ref = UmaPackage.eINSTANCE.getTaskDescriptor_Output();
				}
				if (excludedList != null) {
					excludedList.addAll(workProducts);
				}
				
				TaskDescriptor greenParent = (TaskDescriptor) propUtil.getGreenParentDescriptor(taskDesc);
				if (greenParent != null) {
					EReference eRef = LibraryEditUtil.getInstance().getExcludeFeature(ref);
					List<WorkProduct> parentExecludeList = (List<WorkProduct>) greenParent.eGet(eRef);
					for (WorkProduct wp : (List<WorkProduct>) workProducts) {
						propUtil.addGreenRefDelta(taskDesc, wp, eRef, true);
						if (parentExecludeList != null && parentExecludeList.contains(wp)) {
							propUtil.removeGreenRefDelta(taskDesc, wp, eRef, false);
						}
					}
				}
			} else {
				propUtil.removeLocalUsingInfo(existingWPDescList, taskDesc, getFeature(action));
				propUtil.removeLocalUsingInfo(newWPDescList, taskDesc, getFeature(action));
			}
		}
		
		activity.getBreakdownElements().removeAll(newWPDescList);
		activity.getBreakdownElements().removeAll(subartifactDescriptors);

		if (map != null) {
			Set keyset = map.keySet();
			for (Iterator itor = keyset.iterator(); itor.hasNext();) {
				Object key = itor.next();
				WorkProductDescriptor wpDesc = (WorkProductDescriptor) map
						.get(key);

				// remove it from deliverable
				wpDesc.getDeliverableParts()
						.remove((WorkProductDescriptor) key);
			}
		}
	}

	protected boolean prepare() {
		return true;
	}

	public Collection getModifiedResources() {
		if (workProducts != null &&  !workProducts.isEmpty()) {
			if (activity.eResource() != null) {
				modifiedResources.add(activity.eResource());
			}
			if (taskDesc.eResource() != null) {
				modifiedResources.add(taskDesc.eResource());
			}
		}
		return modifiedResources;
	}
	
	public Collection getAffectedObjects() {
		if (workProducts != null &&  !workProducts.isEmpty()) {
			affectedObjects.add(activity);
			affectedObjects.add(taskDesc);
			return affectedObjects;
		}
		return super.getAffectedObjects();
	}

}
