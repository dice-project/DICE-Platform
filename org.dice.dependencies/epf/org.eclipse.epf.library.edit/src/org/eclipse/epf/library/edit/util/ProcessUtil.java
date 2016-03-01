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
package org.eclipse.epf.library.edit.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.AdapterFactoryTreeIterator;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.emf.edit.provider.WrapperItemProvider;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.edit.IConfigurationApplicator;
import org.eclipse.epf.library.edit.IConfigurator;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.Providers;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.meta.TypeDefUtil;
import org.eclipse.epf.library.edit.process.ActivityWrapperItemProvider;
import org.eclipse.epf.library.edit.process.BSActivityItemProvider;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.ComposedBreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.DescribableElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.process.IBreakdownElementWrapperItemProviderFactory;
import org.eclipse.epf.library.edit.process.RoleDescriptorWrapperItemProvider;
import org.eclipse.epf.library.edit.process.TaskDescriptorWrapperItemProvider;
import org.eclipse.epf.library.edit.process.WorkProductDescriptorWrapperItemProvider;
import org.eclipse.epf.library.edit.process.command.BSDropCommand;
import org.eclipse.epf.library.edit.process.command.CopyHelper;
import org.eclipse.epf.library.edit.process.command.ProcessCommandUtil;
import org.eclipse.epf.library.edit.process.command.ProcessDeepCopyCommand;
import org.eclipse.epf.library.edit.process.command.WBSDropCommand;
import org.eclipse.epf.library.edit.process.internal.BreakdownElementWrapperItemProviderFactory;
import org.eclipse.epf.library.edit.realization.IRealizationManager;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.PredecessorList.DepthLevelItemProvider;
import org.eclipse.epf.library.edit.validation.NameChecker;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.CompositeRole;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.Deliverable;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodElementProperty;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.Section;
import org.eclipse.epf.uma.Step;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkOrder;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.provider.UmaEditPlugin;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.epf.uma.util.ExtendedReference;
import org.eclipse.epf.uma.util.ModifiedTypeMeta;
import org.eclipse.epf.uma.util.Scope;
import org.eclipse.epf.uma.util.UmaUtil;

import com.ibm.icu.text.MessageFormat;
import com.ibm.icu.util.StringTokenizer;


/**
 * Utility class that defines static methods for process authoring
 * 
 * @author Phong Nguyen Le
 * @author Shilpa Toraskar
 * @since 1.0
 */
public final class ProcessUtil { 

	private static Collection<EClass> OBSEclasses = null;

	private static Collection<EClass> WBSEclasses = null;

	private static Collection<EClass> PBSEclasses = null;

	private static Collection<VariabilityType> extendAndLocalContributionVariabilityTypes = null;
	
	public static boolean isSynFree() {
		boolean synFree = LibraryEditUtil.getInstance().isSynFree();
		return synFree;
	}

	private static Collection<VariabilityType> getExtendAndLocalContributionVariabilityTypes() {
		if (extendAndLocalContributionVariabilityTypes == null) {
			extendAndLocalContributionVariabilityTypes = new ArrayList<VariabilityType>();
			extendAndLocalContributionVariabilityTypes
					.add(VariabilityType.EXTENDS);
			extendAndLocalContributionVariabilityTypes
					.add(VariabilityType.LOCAL_CONTRIBUTION);
		}
		return extendAndLocalContributionVariabilityTypes;
	}

	public static String checkBreakdownElementName(
			AdapterFactory adapterFactory, BreakdownElement e, String name, Suppression suppression) {
		return NameChecker.checkName(adapterFactory, e, UmaPackage.eINSTANCE
				.getNamedElement_Name(), name, suppression);
	}
	
	public static String checkBreakdownElementName(
			AdapterFactory adapterFactory, BreakdownElement e, String name, Suppression suppression, boolean ignoreSuppressed) {
		return NameChecker.checkName(adapterFactory, e, UmaPackage.eINSTANCE
				.getNamedElement_Name(), name, suppression, ignoreSuppressed);
	}

	public static String checkBreakdownElementPresentationName(
			AdapterFactory adapterFactory, BreakdownElement e, String name, Suppression suppression) {
		// might allow empty presentation name for extender or contributor. The element will inherit
		// the presentation name from the base element. Check will be performed on the inherited name
		//
		if (name == null || name.length() == 0) {
			if (isExtendingOrLocallyContributing(e)) {
				BreakdownElement base = (BreakdownElement) ((VariabilityElement) e)
						.getVariabilityBasedOnElement();
				name = getPresentationName(base);
			}
		}
		return NameChecker.checkName(adapterFactory, e, UmaPackage.eINSTANCE
				.getMethodElement_PresentationName(), name, suppression);
	}
	
	public static String checkBreakdownElementPresentationName(
			AdapterFactory adapterFactory, BreakdownElement e, String name, Suppression suppression, boolean ignoreSuppressed) {

		return NameChecker.checkName(adapterFactory, e, UmaPackage.eINSTANCE
				.getMethodElement_PresentationName(), name, suppression, ignoreSuppressed);
	}

	public static Object getRootProcess(AdapterFactory adapterFactory,
			ItemProviderAdapter adapter, Object obj) {
		Object parent = adapter.getParent(obj);
		adapter = (ItemProviderAdapter) adapterFactory.adapt(parent,
				ITreeItemContentProvider.class);
		if (parent == null) {
			return obj;
		} else {
			return getRootProcess(adapterFactory, adapter, parent);
		}
	}

	public static Collection<EClass> getWBSEclasses() {
		if (WBSEclasses == null) {
			WBSEclasses = new HashSet<EClass>();
			WBSEclasses.add(UmaPackage.eINSTANCE.getTaskDescriptor());
			WBSEclasses.add(UmaPackage.eINSTANCE.getActivity());
			WBSEclasses.add(UmaPackage.eINSTANCE.getMilestone());
		}
		return WBSEclasses;
	}

	public static Collection<EClass> getOBSEclasses() {
		if (OBSEclasses == null) {
			OBSEclasses = new HashSet<EClass>();
			OBSEclasses.add(UmaPackage.eINSTANCE.getRoleDescriptor());
			OBSEclasses.add(UmaPackage.eINSTANCE.getActivity());
			OBSEclasses.add(UmaPackage.eINSTANCE.getMilestone());
			OBSEclasses.add(UmaPackage.eINSTANCE.getTeamProfile());
			OBSEclasses.add(UmaPackage.eINSTANCE.getCompositeRole());
		}

		return OBSEclasses;
	}

	public static Collection<EClass> getPBSEclasses() {
		if (PBSEclasses == null) {
			PBSEclasses = new HashSet<EClass>();
			PBSEclasses.add(UmaPackage.eINSTANCE.getWorkProductDescriptor());
			PBSEclasses.add(UmaPackage.eINSTANCE.getActivity());
			PBSEclasses.add(UmaPackage.eINSTANCE.getMilestone());
		}
		return PBSEclasses;
	}

	public static boolean isFirstElement(AdapterFactory adapterFactory,
			ItemProviderAdapter itemProvider, Object obj) {
		Object parent = itemProvider.getParent(obj);
		if (parent instanceof TeamProfile)
			return true;
		LinkedList siblings = getSiblings(adapterFactory, itemProvider, obj);
		if (siblings != null && !siblings.isEmpty()) {
			return siblings.getFirst() == obj;
		}
		return true;
	}

	public static LinkedList getSiblings(AdapterFactory adapterFactory,
			ItemProviderAdapter itemProvider, Object obj) {
		Object parent = itemProvider.getParent(obj);
		if (parent == null)
			return null;
		ITreeItemContentProvider parentItemProvider = (ITreeItemContentProvider) adapterFactory
				.adapt(parent, ITreeItemContentProvider.class);
		if (parentItemProvider != null) {
			return new LinkedList(parentItemProvider.getChildren(parent));
		}
		return null;
	}

	public static boolean isLastElement(AdapterFactory adapterFactory,
			ItemProviderAdapter itemProvider, Object obj) {
		Object parent = itemProvider.getParent(obj);
		if (parent instanceof TeamProfile)
			return true;
		LinkedList siblings = getSiblings(adapterFactory, itemProvider, obj);
		if (siblings != null && !siblings.isEmpty()) {
			return siblings.getLast() == obj;
		}
		return true;
	}

	/**
	 * Gets elements that have been affected after the event that triggered the
	 * given notification.
	 * 
	 * @param notification
	 * @param filter
	 * @return
	 */
	public static List getAffectedElements(Notification notification,
			IFilter filter) {
		List affectedElements = new ArrayList();
		switch (notification.getEventType()) {
		case Notification.ADD:
			Object obj = notification.getNewValue();
			if (filter == null || filter.accept(obj)) {
				affectedElements.add(obj);
			}
			break;
		case Notification.ADD_MANY:
			for (Iterator iter = ((Collection) notification.getNewValue())
					.iterator(); iter.hasNext();) {
				Object element = iter.next();
				if (filter == null || filter.accept(element)) {
					affectedElements.add(element);
				}
			}
			break;
		case Notification.REMOVE:
			obj = notification.getOldValue();
			if (filter == null || filter.accept(obj)) {
				affectedElements.add(obj);
			}
			break;
		case Notification.REMOVE_MANY:
			for (Iterator iter = ((Collection) notification.getOldValue())
					.iterator(); iter.hasNext();) {
				Object element = iter.next();
				if (filter == null || filter.accept(element)) {
					affectedElements.add(element);
				}
			}
			break;

		case Notification.MOVE:
			obj = notification.getNewValue();
			if (obj instanceof Collection) {
				for (Iterator iter = ((Collection) obj).iterator(); iter
						.hasNext();) {
					Object element = iter.next();
					if (filter == null || filter.accept(element)) {
						affectedElements.add(element);
					}
				}
			} else {
				if (filter == null || filter.accept(obj)) {
					affectedElements.add(obj);
				}
			}
			break;
		}
		return affectedElements;
	}

	public static boolean isRefreshRequired(Notification notification,
			IFilter filter) {
		switch (notification.getEventType()) {
		case Notification.SET:
		case Notification.ADD:
			Object obj = notification.getNewValue();
			if (filter == null || filter.accept(obj)) {
				return true;
			}
			break;
		case Notification.ADD_MANY:
			for (Iterator iter = ((Collection) notification.getNewValue())
					.iterator(); iter.hasNext();) {
				Object element = iter.next();
				if (filter == null || filter.accept(element)) {
					return true;
				}
			}
			break;
		case Notification.REMOVE:
			obj = notification.getOldValue();
			if (filter == null || filter.accept(obj)) {
				return true;
			}
			break;
		case Notification.REMOVE_MANY:
			for (Iterator iter = ((Collection) notification.getOldValue())
					.iterator(); iter.hasNext();) {
				Object element = iter.next();
				if (filter == null || filter.accept(element)) {
					return true;
				}
			}
			break;

		case Notification.MOVE:
			obj = notification.getNewValue();
			if (obj instanceof Collection) {
				for (Iterator iter = ((Collection) obj).iterator(); iter
						.hasNext();) {
					Object element = iter.next();
					if (filter == null || filter.accept(element)) {
						return true;
					}
				}
			} else {
				if (filter == null || filter.accept(obj)) {
					return true;
				}
			}
			break;
		}

		return false;
	}

	public static void addToContainer(BreakdownElement e,
			ItemProviderAdapter itemProvider) {
		addToContainer(e, itemProvider, true);
	}

	/**
	 * Adds the given BreakdownElement to a new container if it does not have
	 * one or moves it to the right container if requested.
	 * 
	 * @param e
	 * @param itemProvider
	 */
	public static void addToContainer(BreakdownElement e,
			ItemProviderAdapter itemProvider, boolean move) {
		ProcessPackage pkg = null;
		Object parent = itemProvider.getParent(e);
		if (parent instanceof EObject) {
			EObject container = ((EObject) parent).eContainer();
			if (container instanceof ProcessPackage) {
				pkg = (ProcessPackage) container;
			}
		}

		if (pkg == null) {
			return;
		}

		if (e instanceof Activity) {
			Activity act = (Activity) e;
			if (act.eContainer() == null) {
				// create new ProcessPackage for the activity
				//
				ProcessPackage newPkg = UmaFactory.eINSTANCE
						.createProcessPackage();
				newPkg.setName(act.getName());
				pkg.getChildPackages().add(newPkg);
				newPkg.getProcessElements().add(e);
			} else if (move) {
				// Check if the activity's ProcessPackage is at the right place.
				// If not, move it to the right ProcessComponent.
				//
				ProcessPackage oldPkg = (ProcessPackage) act.eContainer();
				if (oldPkg.eContainer() != pkg) {
					pkg.getChildPackages().add(oldPkg);
				}
			}
		} else {
			if (e.eContainer() == null) {
				pkg.getProcessElements().add(e);
			} else if (move && e.eContainer() != pkg) {
				// move to new ProcessPackage
				//
				pkg.getProcessElements().add(e);
			}
		}
	}

	public static void setParent(Object child, Object parent,
			AdapterFactory adapterFactory) {
		Object adapter = adapterFactory.adapt(child,
				ITreeItemContentProvider.class);
		if (adapter instanceof IBSItemProvider) {
			((IBSItemProvider) adapter).setParent(parent);
		} 
//		else {
//			System.out
//					.println(LibraryEditResources.util_ProcessUtil_err_setparent + child); //$NON-NLS-1$
//			System.out
//					.println(LibraryEditResources.util_ProcessUtil_childadapter + adapter); //$NON-NLS-1$
//		}
	}

	/**
	 * Creates a new RoleDescriptor from the given role.
	 * 
	 * @param role
	 * @return
	 */
	public static RoleDescriptor createRoleDescriptor(Role role) {
		return createRoleDescriptor(role, false);
	}
	
	/**
	 * Creates a new RoleDescriptor from the given role.
	 * 
	 * @param role
	 * @return
	 */
	public static RoleDescriptor createRoleDescriptor(Role role, boolean isDynamic) {
		RoleDescriptor roleDesc = UmaFactory.eINSTANCE.createRoleDescriptor();
		roleDesc.setRole(role);
		String presentationName = role.getPresentationName();
		roleDesc.setName(role.getName());
		roleDesc.setPresentationName(StrUtil.isBlank(presentationName) ? role
				.getName() : presentationName);
		// roleDesc.setBriefDescription(role.getBriefDescription());
		if (isSynFree() && isDynamic) {
			DescriptorPropUtil.getDesciptorPropUtil().setCreatedByReference(roleDesc, true);
		}
		return roleDesc;
	}

	public static WorkProductDescriptor createWorkProductDescriptor(
			WorkProduct wp) {
		return createWorkProductDescriptor(wp, false);
	}
	
	public static WorkProductDescriptor createWorkProductDescriptor(
			WorkProduct wp, boolean isDynamic) {
		WorkProductDescriptor wpDesc = UmaFactory.eINSTANCE
				.createWorkProductDescriptor();
		wpDesc.setWorkProduct(wp);
		wpDesc.setName(wp.getName());
		wpDesc
				.setPresentationName(StrUtil.isBlank(wp.getPresentationName()) ? wp
						.getName()
						: wp.getPresentationName());
		// wpDesc.setBriefDescription(wp.getBriefDescription());
		if (isSynFree() && isDynamic) {
			DescriptorPropUtil.getDesciptorPropUtil().setCreatedByReference(wpDesc, true);
		}
		return wpDesc;
	}

	public static boolean refreshNeeded(AdapterFactory adapterFactory,
			BSActivityItemProvider itemProvider) {
		return UserInteractionHelper.getUIHelper().refreshNeeded(adapterFactory, itemProvider);
	}
	
	public static void refreshPredeccessorLists(AdapterFactory factory, Process proc) {
		List<DepthLevelItemProvider> list = PredecessorList.createItemProviderList(proc, factory);
		for (DepthLevelItemProvider ip : list) {
			Object itemProvider = ip.getItemProvider();
			if(itemProvider instanceof IBSItemProvider) {
				PredecessorList predList = ((IBSItemProvider)itemProvider).getPredecessors();
				if(predList != null) {
					predList.refresh(list);
				}
			}
		}
	}
	
	/**
	 * Updates IDs of work breakdown elements of the view of the given process
	 * 
	 * @param factory
	 *            adapter factory of the current process view (WBS, TBS, WPBS,
	 *            CBS)
	 * @param proc
	 *            must be refreshed.
	 */
	public static void updateIDs(AdapterFactory factory, Process proc) {
		AdapterFactoryTreeIterator<Object> iter = new AdapterFactoryTreeIterator<Object>(
				factory, proc) {
			private static final long serialVersionUID = 1L;
			@Override
			protected Iterator<Object> getChildren(Object o) {
				Object e = TngUtil.unwrap(o); 
				if(e instanceof Descriptor || e instanceof Milestone) {
					return Collections.emptyList().iterator();
				}
				return super.getChildren(o);
			}			
		};

		Object obj;
		for (int id = 0; iter.hasNext();) {
			obj = iter.next();
			Object adapter = factory.adapt(obj, ITreeItemContentProvider.class);
			if (adapter instanceof IBSItemProvider) {
				IBSItemProvider itemProvider = (IBSItemProvider) adapter;
				if (itemProvider != null
						&& TngUtil.unwrap(obj) instanceof WorkBreakdownElement) {
					itemProvider.setId(id++);
				}
			}
		}
	}

	public static void refreshViewer(AdapterFactory factory, Process proc) {
		UserInteractionHelper.getUIHelper().refreshViewer(factory, proc);
	}

	/**
	 * Refreshes the element IDs in those viewers that require this.
	 * 
	 * @param adapterFactory
	 */
	public static void refreshIDsInViewers(final ExposedAdapterFactory adapterFactory) {
		UserInteractionHelper.getUIHelper().refreshIDsInViewer(adapterFactory);
	}

	public static void refreshAllViewers(final ExposedAdapterFactory adapterFactory) {
		UserInteractionHelper.getUIHelper().refreshAllViewers(adapterFactory);
	}
	
	/**
	 * @param e
	 *            the breakdown element whose predecessor list to be checked.
	 * @param predIdList
	 *            a list of predecessor IDs (Integer) as shown in the ID column
	 *            of the editor
	 * @param adapterFactory
	 * @param process
	 * @param predecessors
	 *            output, the predecessor list
	 * @return null if predList is valid, an error message otherwise
	 */
	public static String checkPredecessorList(WorkBreakdownElement e,
			List<Integer> predIdList, AdapterFactory adapterFactory, Object process,
			List<Object> predecessors) {
		// get BreakdownElement list from ID list
		//
		List<Object> beList = new ArrayList<Object>();
		List<Object> allElements = new ArrayList<Object>();
		for (Iterator<Object> iter = new AdapterFactoryTreeIterator<Object>(adapterFactory,
				process); iter.hasNext();) {
			Object obj = iter.next();
			IBSItemProvider itemProvider = (IBSItemProvider) adapterFactory
					.adapt(obj, ITreeItemContentProvider.class);
			Integer id = new Integer(itemProvider.getId());
			Object element = TngUtil.unwrap(obj);
			allElements.add(element);
			if (predIdList.contains(id)) {
				beList.add(obj);
			}
		}

		// check for circular dependency
		//
		int size = beList.size();
		for (int i = 0; i < size; i++) {
			Object obj = TngUtil.unwrap(beList.get(i));
			if (obj instanceof WorkBreakdownElement) {
				WorkBreakdownElement pred = (WorkBreakdownElement) obj;
				if (ProcessUtil.checkCircular(e, pred, allElements)) {
					return LibraryEditResources.util_ProcessUtil_err_same_breakdown_element; 
				}
				if (TngUtil.isSubelementOf(pred, e, adapterFactory)) {
					return LibraryEditResources.util_ProcessUtil_err_same_sub_element; 
				}
				if (TngUtil.isSuperElementOf(pred, e, adapterFactory)) {
					return LibraryEditResources.util_ProcessUtil_err_child_element; 
				}
			} else
				return LibraryEditResources.util_ProcessUtil_err_wrong_element; 
		}

		if (predecessors != null)
			predecessors.addAll(beList);
		
		return null;
	}

	/**
	 * Checks for circular dependency
	 * 
	 * @param successor
	 * @param predecessor
	 * @param list
	 *            the list of all breakdown elements in the breakdown structure
	 */
	 public static boolean checkCircular(WorkBreakdownElement successor,
			WorkBreakdownElement predecessor, List list) {
		if (successor == predecessor) {
			if (predecessor.getIsRepeatable().booleanValue()) {
				return false;
			}
			return true;
		}
		for (Iterator<?> iter = predecessor.getLinkToPredecessor().iterator(); iter
				.hasNext();) {
			WorkOrder element = (WorkOrder) iter.next();
			WorkBreakdownElement pred = element.getPred();
			if (pred != predecessor && list.contains(pred)) {
				if (checkCircular(successor, pred, list))
					return true;
			}
		}
		return false;
	}
	 
	public static void getAllPredecessorList(WorkBreakdownElement predecessor,
			List list, List predList) {
		if (!predList.contains(predecessor))
			predList.add(predecessor);
		for (Iterator iter = predecessor.getLinkToPredecessor().iterator(); iter
				.hasNext();) {
			WorkOrder element = (WorkOrder) iter.next();
			WorkBreakdownElement pred = (WorkBreakdownElement) element
					.getPred();
			if (pred != predecessor && list.contains(pred)) {
				if (!predList.contains(pred)) {
					predList.add(pred);
					getAllPredecessorList(pred, list, predList);
				}
			}
		}
	}

	/**
	 * 
	 * @param e
	 *            the breakdown element whose predecessor list to be checked.
	 * @param predList
	 *            a comma-separated list of predecessor IDs
	 * @param adapterFactory
	 * @param topAct
	 * @param predecessors
	 *            output, the predecessor list
	 * @param allElements
	 *            output, the whole breakdown element list is copied into this
	 *            list.
	 * @return null if predList is valid. The predecessor list is saved in
	 *         predecessors and the whole breakdown element list is saved in
	 *         allElements.
	 */
	public static String checkPredecessorList(WorkBreakdownElement e,
			String predList, AdapterFactory adapterFactory, Object topAct,
			List<Object> predecessors) {
		List<Integer> idList = new ArrayList<Integer>();
		for (StringTokenizer tokens = new StringTokenizer(predList, ","); tokens.hasMoreTokens();) { //$NON-NLS-1$
			String token = tokens.nextToken().trim();
			Integer id;
			try {
				id = new Integer(token);
			} catch (NumberFormatException ex) {
				return LibraryEditResources.invalidPredecessorError_msg; 
			}
			idList.add(id);
		}

		return checkPredecessorList(e, idList, adapterFactory, topAct,
				predecessors);
	}

	/**
	 * Adds the given object's method package and plugin to the default
	 * configuration of the given process if they are not in the configuration
	 * yet.
	 * 
	 * @param proc
	 * @param object
	 * @param addedObjects
	 */
	public static void addToDefaultConfiguration(Process proc, EObject e,
			Set addedObjects) {
		// add the element's method package to the default configuration
		//
		MethodConfiguration config = proc.getDefaultContext();
		if (e instanceof Task) {
			addTaskToDefaultConfiguration(proc, (Task) e, addedObjects, true);
		} else if (e instanceof WorkProduct) {
			WorkProduct wp = (WorkProduct) e;
			addWPToDefaultConfiguration(proc, wp, addedObjects);
			List tasks = getTasksForWorkProduct(wp, config);
			if (tasks != null)
			{
				for (Iterator iter = tasks.iterator(); iter.hasNext();) {
					addTaskToDefaultConfiguration(proc, (Task) iter.next(),
							addedObjects, false);
				}
			}
		} else if (e instanceof Role) {
			Role role = (Role) e;
			addRoleToDefaultConfiguration(proc, role, addedObjects);

		} else if (e instanceof Activity) {
			addActivityToDefaultConfiguration(proc, (Activity) e, addedObjects);
		} else if (e instanceof Guidance) {
			TngUtil.addToConfiguration(config, (Guidance) e, addedObjects);
		}
	}
	
	private static void addActivityToDefaultConfiguration(Process proc, Activity activity, Set addedObjects) {
		MethodConfiguration config = proc.getDefaultContext();
		TngUtil.addTo(config, UmaUtil.getProcessComponent(activity), addedObjects);
		// add linked element of descriptors
		//
		for (Iterator iter = activity.getBreakdownElements().iterator(); iter.hasNext();) {
			Object e = (Object) iter.next();
			if(e instanceof Descriptor) {
				MethodElement o = ProcessUtil.getAssociatedElement((Descriptor) e);
				if(o != null) {
					addToDefaultConfiguration(proc, o, addedObjects);
				}
			}
			else if (e instanceof Activity){
				// this is needed for adding sub-activities descriptors 
				addActivityToDefaultConfiguration(proc, (Activity) e, addedObjects);
			}
		}

		// add all the base elements if there is any
		for (Activity base = (Activity) activity.getVariabilityBasedOnElement(); base != null; base = (Activity) base
		.getVariabilityBasedOnElement()) {
			addActivityToDefaultConfiguration(proc, base, addedObjects);
		}
	}

	private static void addTaskToDefaultConfiguration(Process proc, Task task,
			Set addedObjects, boolean includeWorkProducts) {
		MethodConfiguration config = proc.getDefaultContext();

		TngUtil.addToConfiguration(config, task, addedObjects);

		// add all other dependencies
		//

		// add roles
		ArrayList<EObject> dependencies = new ArrayList<EObject>();
		dependencies.addAll(task.getAdditionallyPerformedBy());
		dependencies.addAll(task.getPerformedBy());
		for (EObject eObject : dependencies) {
			TngUtil.addToConfiguration(config, eObject,
					addedObjects);
		}

		// add work products
		if (includeWorkProducts) {
			dependencies.clear();
			dependencies.addAll(task.getOptionalInput());
			dependencies.addAll(task.getOutput());
			dependencies.addAll(task.getMandatoryInput());
			for (EObject eObject : dependencies) {
				addWPToDefaultConfiguration(proc, (WorkProduct) eObject,
						addedObjects);
			}
		}

		for (Section step : task.getSteps()) {			
			EObject base = step.getVariabilityBasedOnElement();
			if (base != null) {
				TngUtil.addToConfiguration(config, base, addedObjects);
			}
		}

	}

	private static void addRoleToDefaultConfiguration(Process proc,
			Role role, Set addedObjects) {
		MethodConfiguration config = proc.getDefaultContext();
		TngUtil.addToConfiguration(config, role, addedObjects);
		
		// add work products
		ArrayList dependencies = new ArrayList();
	
		dependencies.addAll(role.getModifies());
		dependencies.addAll(role.getResponsibleFor());
		for (Iterator iter = dependencies.iterator(); iter.hasNext();) {
			addWPToDefaultConfiguration(proc, (WorkProduct) iter.next(),
					addedObjects);
		}
	}
	
	private static void addWPToDefaultConfiguration(Process proc,
			WorkProduct wp, Set addedObjects) {
		MethodConfiguration config = proc.getDefaultContext();
		TngUtil.addToConfiguration(config, wp, addedObjects);
		if (wp instanceof Deliverable) {
			for (Iterator iter = ((Deliverable) wp).getDeliveredWorkProducts()
					.iterator(); iter.hasNext();) {
				TngUtil.addToConfiguration(config, (EObject) iter.next(),
						addedObjects);
			}
		}
	}

	public static void updateTaskDescriptorSteps(Activity activity,
			TaskDescriptor taskDesc) {
		if (taskDesc == null) {
			return;
		}

		Task task = taskDesc.getTask();

		IConfigurationApplicator configApplicator = Providers
				.getConfigurationApplicator();
		MethodConfiguration config = TngUtil.getOwningProcess(activity)
				.getDefaultContext();
		List<Step> steps = (List<Step>) configApplicator.getReference(task,
				UmaPackage.eINSTANCE.getTask_Steps(), config);

		// add those steps to TaskDescriptor if they are not there yet.
		//
		for (Step step : steps) {
			if (!taskDesc.getSelectedSteps().contains(step)) {
				taskDesc.getSelectedSteps().add(step);
			}
		}
	}

	/**
	 * Gets the MethodElement that the given descriptor is associated with.
	 * 
	 * @param descriptor
	 * @return
	 */
	public static MethodElement getAssociatedElement(Descriptor descriptor) {
		if (descriptor instanceof RoleDescriptor) {
			return ((RoleDescriptor) descriptor).getRole();
		} else if (descriptor instanceof TaskDescriptor) {
			return ((TaskDescriptor) descriptor).getTask();
		} else if (descriptor instanceof WorkProductDescriptor) {
			return ((WorkProductDescriptor) descriptor).getWorkProduct();
		}
		return null;
	}
	
	public static EReference getLinkReference(Descriptor descriptor) {
		if (descriptor instanceof RoleDescriptor) {
			return UmaPackage.eINSTANCE.getRoleDescriptor_Role();
		} else if (descriptor instanceof TaskDescriptor) {
			return UmaPackage.eINSTANCE.getTaskDescriptor_Task();
		} else if (descriptor instanceof WorkProductDescriptor) {
			return UmaPackage.eINSTANCE.getWorkProductDescriptor_WorkProduct();
		}
		return null;

	}

	public static void disposeWrappers(Collection children) {
		if (children == null)
			return;
		for (Iterator iter = children.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (element instanceof IWrapperItemProvider) {
				((IWrapperItemProvider) element).dispose();
			}
		}
	}

	/**
	 * Get all tasks that have the given workproduct in their output
	 * in the given configuration
	 * 
	 * @param wp
	 * @param config
	 * @return
	 */
	public static List getTasksForWorkProduct(WorkProduct wp,
			MethodConfiguration config) {
		IConfigurationApplicator configApplicator = Providers.getConfigurationApplicator();

		List tasks = (List) configApplicator.getReference(wp, AssociationHelper.WorkProduct_OutputFrom_Tasks, config);

		return tasks;
	}

	/**
	 * Get all workproducts that have the role as responsible role in the
	 * given configuration
	 * 
	 * @param wp
	 * @param config
	 * @return
	 */
	public static List getWorkProductsForRole(Role role,
			MethodConfiguration config) {
		IConfigurationApplicator configApplicator = Providers.getConfigurationApplicator();
		List wps = (List) configApplicator.getReference(role, UmaPackage.eINSTANCE.getRole_ResponsibleFor(), config);
		return wps;
	}

	/**
	 * Gets all workproducts which are, except the contributor, in the given
	 * plugin.
	 * 
	 * @param plugin
	 * @return
	 */
	public static List getAllWorkProducts(MethodPlugin plugin) {

		ContentPackage coreContentPkg = UmaUtil.findContentPackage(plugin,
				ModelStructure.DEFAULT.coreContentPath);
		Iterator iter = new AbstractTreeIterator(coreContentPkg, false) {

			/**
			 * Comment for <code>serialVersionUID</code>
			 */
			private static final long serialVersionUID = 3833752066324837937L;

			protected Iterator getChildren(Object object) {
				if (!(object instanceof ContentPackage))
					return Collections.EMPTY_LIST.iterator();
				ContentPackage pkg = (ContentPackage) object;
				List children = new ArrayList();
				for (Iterator iterator = pkg.getChildPackages().iterator(); iterator
						.hasNext();) {
					Object element = iterator.next();
					if (element instanceof ContentPackage) {
						children.add(element);
					}
				}
				for (Iterator iterator = pkg.getContentElements().iterator(); iterator
						.hasNext();) {
					Object element = iterator.next();

					if ((element instanceof WorkProduct)
							&& !TngUtil
									.isContributor((VariabilityElement) element)) {
						children.add(element);
					}
				}
				return children.iterator();
			}

		};
		List methodElements = new ArrayList();
		while (iter.hasNext()) {
			Object e = iter.next();
			if (e instanceof WorkProduct) {
				methodElements.add(e);
			}
		}
		return methodElements;
	}

	/**
	 * Gets all task, except the contributor, in the given plugin.
	 * 
	 * @param plugin
	 * @return
	 */
	public static List getAllTasks(MethodPlugin plugin) {
		ContentPackage coreContentPkg = UmaUtil.findContentPackage(plugin,
				ModelStructure.DEFAULT.coreContentPath);
		Iterator iter = new AbstractTreeIterator(coreContentPkg, false) {

			/**
			 * Comment for <code>serialVersionUID</code>
			 */
			private static final long serialVersionUID = 3833752066324837937L;

			protected Iterator getChildren(Object object) {
				if (!(object instanceof ContentPackage))
					return Collections.EMPTY_LIST.iterator();
				ContentPackage pkg = (ContentPackage) object;
				List children = new ArrayList();
				for (Iterator iterator = pkg.getChildPackages().iterator(); iterator
						.hasNext();) {
					Object element = iterator.next();
					if (element instanceof ContentPackage) {
						children.add(element);
					}
				}
				for (Iterator iterator = pkg.getContentElements().iterator(); iterator
						.hasNext();) {
					Object element = iterator.next();
					if (element instanceof Task
							&& !TngUtil
									.isContributor((VariabilityElement) element)) {
						children.add(element);
					}
				}
				return children.iterator();
			}

		};
		List tasks = new ArrayList();
		while (iter.hasNext()) {
			Object e = iter.next();
			if (e instanceof Task) {
				tasks.add(e);
			}
		}
		return tasks;
	}

	public static boolean isExtendingOrLocallyContributing(BreakdownElement e) {
		return TngUtil.isGeneralizer(e,
				getExtendAndLocalContributionVariabilityTypes());
	}
	

	public static String getPresentationName(BreakdownElement e) {
		if (e.getPresentationName() == null
				|| e.getPresentationName().length() == 0) {
			if (isExtendingOrLocallyContributing(e)) {
				BreakdownElement base = (BreakdownElement) ((VariabilityElement) e)
						.getVariabilityBasedOnElement();
				return getPresentationName(base);
			}
		}

		return e.getPresentationName();
	}

	public static String getLabelWithPath(BreakdownElement e) {
		StringBuffer path = new StringBuffer(e.getName());
		Process proc = TngUtil.getOwningProcess(e);
		if (proc != null) {
			path.append(", "); //$NON-NLS-1$
			MethodPlugin plugin = UmaUtil.getMethodPlugin(proc);
			if (plugin != null) {
				path.append(plugin.getName());
			}
			if (e != proc) {
				path.append('/').append(proc.getName());
			}
		}
		return path.toString();
	}

	/**
	 * Gets the model info of the given BreakdownElement in the given
	 * StringBuffer
	 * 
	 * @param e
	 * @param adapter
	 * @param modelInfo
	 */
	public static void getModelInfo(BreakdownElement e, Object adapter,
			StringBuffer modelInfo) {
		if (e instanceof VariabilityElement) {
			VariabilityElement ve = (VariabilityElement) e;
			VariabilityElement base = ve.getVariabilityBasedOnElement();
			if (base != null) {
				VariabilityType type = ve.getVariabilityType();
				if (modelInfo.length() > 0) {
					modelInfo.append("; "); //$NON-NLS-1$
				}
				String pattern = null;
				if (type == VariabilityType.CONTRIBUTES) {
					pattern = LibraryEditResources.util_ProcessUtil_contributesto; 
				} else if (type == VariabilityType.LOCAL_CONTRIBUTION) {
					pattern = LibraryEditResources.util_ProcessUtil_localContributesto; 
				} else if (type == VariabilityType.EXTENDS) {
					pattern = LibraryEditResources.process_extends; 
				} else if (type == VariabilityType.REPLACES) {
					pattern = LibraryEditResources.process_replaces; 
				} else if (type == VariabilityType.LOCAL_REPLACEMENT) {
					pattern = LibraryEditResources.process_localReplaces; 
				}

				// if(adapter instanceof ItemProviderAdapter) {
				// AdapterFactory factory =
				// ((ItemProviderAdapter)adapter).getAdapterFactory();
				// Object itemProvider = factory.adapt(base,
				// ITreeItemContentProvider.class);
				// if(itemProvider instanceof IBSItemProvider) {
				// IBSItemProvider bsItemProvider = (IBSItemProvider)
				// itemProvider;
				// Process proc = (Process) bsItemProvider.getTopItem();
				// if(proc != null) {
				// strbuf.append(UmaUtil.getMethodPlugin(proc).getName()).append(':')
				// .append(proc.getName()).append(':');
				// }
				// }
				// }

				// Process proc = TngUtil.getOwningProcess((BreakdownElement)
				// base);
				// if(proc != null) {
				// MethodPlugin plugin = UmaUtil.getMethodPlugin(proc);
				// if(plugin != null) {
				// modelInfo.append(UmaUtil.getMethodPlugin(proc).getName()).append(':');
				// }
				// modelInfo.append(proc.getName());
				// }
				//				
				// if(base != proc) {
				// modelInfo.append(':').append(base.getName());
				// }
				// modelInfo.append("'"); //$NON-NLS-1$
				if (pattern != null) {
					String path = getLabelWithPath((BreakdownElement) base);
					modelInfo.append(MessageFormat.format(pattern,
							new Object[] { path }));

					getModelInfo((BreakdownElement) base, adapter, modelInfo);
				}
			}
		} else if (adapter instanceof BreakdownElementWrapperItemProvider) {
			BreakdownElementWrapperItemProvider wrapper = (BreakdownElementWrapperItemProvider) adapter;
			wrapper.getOwner();
			if (wrapper.getFeature() != null) {
				modelInfo.append(TngUtil.getFeatureText(wrapper.getFeature()));
			} else {
				if (wrapper instanceof WorkProductDescriptorWrapperItemProvider) {
//					getModelInfoForWorkProductDescriptor(modelInfo,
//							(WorkProductDescriptor) TngUtil.unwrap(wrapper));
					
					getWPDModelInfo(modelInfo, wrapper, wrapper);
					
				}
			}
		}
	}
	
	public static String getColumnName(String property) {
		if (IBSItemProvider.COL_ID.equals(property)) {
			return IBSItemProvider.COL_ID;
		} else if (IBSItemProvider.COL_NAME.equals(property)) {
			return IBSItemProvider.COL_NAME;
		} else if (IBSItemProvider.COL_PRESENTATION_NAME.equals(property)) {
			return IBSItemProvider.COL_PRESENTATION_NAME;
		} else if (IBSItemProvider.COL_PREDECESSORS.equals(property)) {
			return IBSItemProvider.COL_PREDECESSORS;
		} else if (IBSItemProvider.COL_IS_EVENT_DRIVEN.equals(property)) {
			return IBSItemProvider.COL_IS_EVENT_DRIVEN;
		} else if (IBSItemProvider.COL_IS_ONGOING.equals(property)) {
			return IBSItemProvider.COL_IS_ONGOING;
		} else if (IBSItemProvider.COL_IS_REPEATABLE.equals(property)) {
			return IBSItemProvider.COL_IS_REPEATABLE;
		} else if (IBSItemProvider.COL_PREFIX.equals(property)) {
			return IBSItemProvider.COL_PREFIX;
		} else if (IBSItemProvider.COL_MODEL_INFO.equals(property)) {
			return IBSItemProvider.COL_MODEL_INFO;
		} else if (IBSItemProvider.COL_TYPE.equals(property)) {
			return IBSItemProvider.COL_TYPE;
		} else if (IBSItemProvider.COL_TEAMS.equals(property)) {
			return IBSItemProvider.COL_TEAMS;
		} else if (IBSItemProvider.COL_ENTRY_STATE.equals(property)) {
			return IBSItemProvider.COL_ENTRY_STATE;
		} else if (IBSItemProvider.COL_EXIT_STATE.equals(property)) {
			return IBSItemProvider.COL_EXIT_STATE;
		} else if (IBSItemProvider.COL_DELIVERABLE.equals(property)) {
			return IBSItemProvider.COL_DELIVERABLE;
		} else if (IBSItemProvider.COL_IS_OPTIONAL.equals(property)) {
			return IBSItemProvider.COL_IS_OPTIONAL;
		} else if (IBSItemProvider.COL_IS_PLANNED.equals(property)) {
			return IBSItemProvider.COL_IS_PLANNED;
		} else if (IBSItemProvider.COL_HAS_MULTIPLE_OCCURRENCES.equals(property)) {
			return IBSItemProvider.COL_HAS_MULTIPLE_OCCURRENCES;
		}
		return null;
	}

	public static String getAttribute(Object object, String property,
			Object adapter) {
		BreakdownElement e = (BreakdownElement) TngUtil.unwrap(object);
		if (property == IBSItemProvider.COL_ID) {
			if (e instanceof WorkBreakdownElement
					&& adapter instanceof IBSItemProvider) {
				return String.valueOf(((IBSItemProvider) adapter).getId());
			}
		} else if (property == IBSItemProvider.COL_NAME) {
			if (adapter instanceof IItemLabelProvider) {
				return ((IItemLabelProvider) adapter).getText(e);
			}
		} else if (property == IBSItemProvider.COL_PRESENTATION_NAME) {
			return getPresentationName(e);
		} else if (property == IBSItemProvider.COL_PREDECESSORS) {
			if (adapter instanceof IBSItemProvider) {
				return ((IBSItemProvider) adapter).getPredecessors().toString();
			}
		} else if (property == IBSItemProvider.COL_IS_EVENT_DRIVEN) {
			if (e instanceof WorkBreakdownElement) {
				return String.valueOf(((WorkBreakdownElement) e)
						.getIsEventDriven());
			}
		} else if (property == IBSItemProvider.COL_IS_ONGOING) {
			if (e instanceof WorkBreakdownElement) {
				return String
						.valueOf(((WorkBreakdownElement) e).getIsOngoing());
			}
		} else if (property == IBSItemProvider.COL_IS_REPEATABLE) {
			if (e instanceof WorkBreakdownElement) {
				return String.valueOf(((WorkBreakdownElement) e)
						.getIsRepeatable());
			}
		} else if (property == IBSItemProvider.COL_PREFIX) {
			return e.getPrefix();
		} else if (property == IBSItemProvider.COL_MODEL_INFO) {
			StringBuffer modelInfo = new StringBuffer();
			getModelInfo(e, adapter, modelInfo);
			return modelInfo.toString();
		} else if (property == IBSItemProvider.COL_TYPE) {
			String typeName = null;
			if (e instanceof WorkProductDescriptor) {
				WorkProduct wp = ((WorkProductDescriptor) e)
						.getWorkProduct();
				if (wp != null) {
					typeName = wp.eClass().getName() + "Descriptor"; //$NON-NLS-1$  
				}
			}
			if (typeName == null && e instanceof EObject) {
				typeName = ((EObject) e).eClass().getName();
			}
			if (typeName != null) {
				return UmaEditPlugin.INSTANCE
						.getString("_UI_" + typeName + "_type"); //$NON-NLS-1$ //$NON-NLS-2$
			}
		} else if (property == IBSItemProvider.COL_TEAMS) {
			if (e instanceof TeamProfile) {
				return TngUtil.getPresentationName(((TeamProfile) e)
						.getSuperTeam());
			}
			else if (e instanceof RoleDescriptor && adapter instanceof ITreeItemContentProvider) {
				AdapterFactory adapterFactory = null;
				if(adapter instanceof BreakdownElementWrapperItemProvider) {
					adapterFactory = ((BreakdownElementWrapperItemProvider)adapter).getAdapterFactory();
				}
				else if(adapter instanceof ItemProviderAdapter) {
					adapterFactory = ((ItemProviderAdapter)adapter).getAdapterFactory();
				}
				return getTeamListString(getTeamProfiles(object, (ITreeItemContentProvider)adapter, adapterFactory));
 			}
		}
		if (property == IBSItemProvider.COL_ENTRY_STATE) {
			if (e instanceof WorkProductDescriptor) {
				WorkProductDescriptor wpd = (WorkProductDescriptor) e;
				return TngUtil.checkNull(wpd.getActivityEntryState());
			}
		} else if (property == IBSItemProvider.COL_EXIT_STATE) {
			if (e instanceof WorkProductDescriptor) {
				WorkProductDescriptor wpd = (WorkProductDescriptor) e;
				return TngUtil.checkNull(wpd.getActivityExitState());
			}
		} else if (property == IBSItemProvider.COL_DELIVERABLE) {
			if (e instanceof WorkProductDescriptor) {
				List deliverables = AssociationHelper
						.getDeliverableDescriptors((WorkProductDescriptor) e);
				if (deliverables.isEmpty())
					return ""; //$NON-NLS-1$
				StringBuffer strBuf = new StringBuffer();
				int max = deliverables.size() - 1;
				for (int i = 0; i < max; i++) {
					strBuf.append(
							TngUtil.getPresentationName(deliverables.get(i)))
							.append(',');
				}
				strBuf.append(TngUtil
						.getPresentationName(deliverables.get(max)));
				return strBuf.toString();
			}
		} else if (property == IBSItemProvider.COL_IS_OPTIONAL) {
			return String.valueOf(e.getIsOptional());
		} else if (property == IBSItemProvider.COL_IS_PLANNED) {
			return String.valueOf(e.getIsPlanned());
		} else if (property == IBSItemProvider.COL_HAS_MULTIPLE_OCCURRENCES) {
			return String.valueOf(e.getHasMultipleOccurrences());
		}
		return ""; //$NON-NLS-1$
	}

	/**
	 * Get parent activity for team profile breakdown element
	 * UmaUtil.getParentActivity doesn't work correctly for sub-teams
	 * This method should only be used for breakdown elements under teamallocation
	 * @param team
	 * @return
	 */
	public static Object getParentActivityOfTeam(BreakdownElement brElement)
	{
		AdapterFactory adapterFactory = TngAdapterFactory.INSTANCE.getOBS_ComposedAdapterFactory();
		ItemProviderAdapter adapter = (ItemProviderAdapter) adapterFactory
				.adapt(brElement, ITreeItemContentProvider.class);
		Object parent = adapter.getParent(brElement);
		while (!(parent instanceof Activity)) {
			brElement = (BreakdownElement) parent;
			adapter = (ItemProviderAdapter) adapterFactory.adapt(brElement,
					ITreeItemContentProvider.class);
			parent = adapter.getParent(brElement);
		}

		return parent;	
	}
	
	
	/**
	 * Return roledescriptors under activity which could possible match for
	 * roles under the teams. It will recurse thru all child activities to find 
	 * role descriptors
	 * 
	 * @param adapterFactory
	 * @param parent
	 * @param roleDescList
	 * @param roles
	 */
	public static void getRoleDescriptor(AdapterFactory adapterFactory,
			Activity parent,  List roleDescList, List roles) {
		ItemProviderAdapter adapter = (ItemProviderAdapter) adapterFactory
				.adapt(parent, ITreeItemContentProvider.class);

		Object o = adapter.getChildren(parent);
		if (o instanceof List) {
			List children = (List) o;
			for (Iterator itor = children.iterator(); itor.hasNext();) {
				Object obj = itor.next();
				if (obj instanceof Activity) {
					getRoleDescriptor(adapterFactory, (Activity) obj,
							roleDescList, roles);
				}
				if (obj instanceof RoleDescriptor)
				{
					RoleDescriptor roleDesc = (RoleDescriptor) obj;
					if ((roleDesc.getRole()!= null) && 
							(roles.contains(roleDesc.getRole())))
						roleDescList.add(obj);
				}
			}
		}
	}
	
	private static String getTeamListString(List teamProfiles) {
		if (teamProfiles.isEmpty())
			return ""; //$NON-NLS-1$
		StringBuffer strBuf = new StringBuffer();
		int max = teamProfiles.size() - 1;
		for (int i = 0; i < max; i++) {
			strBuf.append(TngUtil.getPresentationName(teamProfiles.get(i)))
					.append(',');
		}
		strBuf.append(TngUtil.getPresentationName(teamProfiles.get(max)));
		return strBuf.toString();
	}

	public static void setAttribute(WorkBreakdownElement e, String prop,
			String txt) {
		if (prop == IBSItemProvider.COL_NAME) {
			e.setName(txt);
		} else if (prop == IBSItemProvider.COL_PREFIX) {
			e.setPrefix(txt);
		} else if (prop == IBSItemProvider.COL_IS_EVENT_DRIVEN) {
			e.setIsEventDriven(Boolean.valueOf(txt));
		} else if (prop == IBSItemProvider.COL_IS_ONGOING) {
			e.setIsOngoing(Boolean.valueOf(txt));
		} else if (prop == IBSItemProvider.COL_IS_REPEATABLE) {
			e.setIsRepeatable(Boolean.valueOf(txt));
		}
	}

	public static void setAttribute(IActionManager actionMgr,
			BreakdownElement e, String prop, String txt) {
		if (prop == IBSItemProvider.COL_NAME) {
			actionMgr.doAction(IActionManager.SET, e, UmaPackage.eINSTANCE
					.getNamedElement_Name(), txt, -1);
		} else if (prop == IBSItemProvider.COL_PREFIX) {
			actionMgr.doAction(IActionManager.SET, e, UmaPackage.eINSTANCE
					.getBreakdownElement_Prefix(), txt, -1);
		} else if (prop == IBSItemProvider.COL_IS_EVENT_DRIVEN) {
			actionMgr.doAction(IActionManager.SET, e, UmaPackage.eINSTANCE
					.getWorkBreakdownElement_IsEventDriven(), Boolean
					.valueOf(txt), -1);
		} else if (prop == IBSItemProvider.COL_IS_ONGOING) {
			actionMgr.doAction(IActionManager.SET, e, UmaPackage.eINSTANCE
					.getWorkBreakdownElement_IsOngoing(), Boolean.valueOf(txt),
					-1);
		} else if (prop == IBSItemProvider.COL_IS_REPEATABLE) {
			actionMgr.doAction(IActionManager.SET, e, UmaPackage.eINSTANCE
					.getWorkBreakdownElement_IsRepeatable(), Boolean
					.valueOf(txt), -1);
		} else if (prop == IBSItemProvider.COL_PRESENTATION_NAME) {
			actionMgr.doAction(IActionManager.SET, e, UmaPackage.eINSTANCE
					.getMethodElement_PresentationName(), txt, -1);
		} else if (prop == IBSItemProvider.COL_IS_OPTIONAL) {
			actionMgr
					.doAction(IActionManager.SET, e, UmaPackage.eINSTANCE
							.getBreakdownElement_IsOptional(), Boolean
							.valueOf(txt), -1);
		} else if (prop == IBSItemProvider.COL_IS_PLANNED) {
			actionMgr.doAction(IActionManager.SET, e, UmaPackage.eINSTANCE
					.getBreakdownElement_IsPlanned(), Boolean.valueOf(txt), -1);
		} else if (prop == IBSItemProvider.COL_HAS_MULTIPLE_OCCURRENCES) {
			actionMgr.doAction(IActionManager.SET, e, UmaPackage.eINSTANCE
					.getBreakdownElement_HasMultipleOccurrences(), Boolean
					.valueOf(txt), -1);
		} else if (e instanceof WorkProductDescriptor) {
			if (prop == IBSItemProvider.COL_ENTRY_STATE) {
				actionMgr
						.doAction(IActionManager.SET, e, UmaPackage.eINSTANCE
								.getWorkProductDescriptor_ActivityEntryState(),
								txt, -1);
			} else if (prop == IBSItemProvider.COL_EXIT_STATE) {
				actionMgr.doAction(IActionManager.SET, e, UmaPackage.eINSTANCE
						.getWorkProductDescriptor_ActivityExitState(), txt, -1);
			}
		}

	}

	public static Activity generalize(Activity base, VariabilityType type) {		
		Activity act = (Activity) UmaFactory.eINSTANCE.create(base.eClass());
		act.setName(base.getName());
		if (type == VariabilityType.LOCAL_REPLACEMENT) {
			String presentationName = getPresentationName(base);
			act.setPresentationName(StrUtil.isBlank(presentationName) ? base
					.getName() : presentationName);
		}
		act.setVariabilityBasedOnElement(base);
		act.setVariabilityType(type);

		if (type == VariabilityType.EXTENDS) {
			// inherit boolean attributes from base
			//
			for (Iterator iter = base.eClass().getEAllAttributes().iterator(); iter
					.hasNext();) {
				EAttribute attribute = (EAttribute) iter.next();
				if (attribute.getEAttributeType().getInstanceClass() == Boolean.class) {
					act.eSet(attribute, base.eGet(attribute));
				}
			}
		}

		// copy predecessors list
		// TODO: need to check with variability rules.
		//
		ArrayList workOrders = new ArrayList();
		for (Iterator iter = new ArrayList(base.getLinkToPredecessor())
				.iterator(); iter.hasNext();) {
			workOrders.add(TngUtil.copy((WorkOrder) iter.next()));
		}

		act.getLinkToPredecessor().addAll(workOrders);
		return act;
	}

	/**
	 * Returns list of elements specified in class "type" eg.(RoleDescriptor,
	 * WorkProductDescriptor" until the root level.
	 * 
	 * @param adapterFactory
	 * @param element
	 * @param type
	 * @param items
	 * @return
	 */
	public static List getElementsInScope(AdapterFactory adapterFactory,
			BreakdownElement element, Class type, List items) {
		// get children for activity
		ITreeItemContentProvider itemProvider = (ITreeItemContentProvider) adapterFactory
				.adapt(element, ITreeItemContentProvider.class);
		if (element instanceof Activity) {
			Collection children = ((Activity) element).getBreakdownElements();
			for (Iterator itor = children.iterator(); itor.hasNext();) {
				Object obj = itor.next();
				if (type.isInstance(obj)) {
					// System.out.println("Obj -" + obj);
					if (!(items.contains(obj))) {
						items.add(obj);
					}
				}
			}
		}

		// get parent
		Object currentParent = itemProvider.getParent(element);
		if (currentParent != null) {
			// go up
			getElementsInScope(adapterFactory,
					(BreakdownElement) currentParent, type, items);
		}
		return items;

	}

	/**
	 * Get roles from roledescriptor list
	 * 
	 * @param roleDescList
	 * @return
	 */

	public static List getRoles(List roleDescList) {
		List roleList = new ArrayList();
		if (roleDescList != null) {
			for (Iterator itor = roleDescList.iterator(); itor.hasNext();) {
				Object obj = itor.next();
				if (obj instanceof RoleDescriptor) {
					Role role = ((RoleDescriptor) obj).getRole();
					roleList.add(role);
				}
			}
		}
		return roleList;
	}

	/**
	 * Get associated method element list
	 * 
	 * @param descriptorList
	 * @return
	 */
	public static List getAssociatedElementList(List descriptorList) {
		List elementList = new ArrayList();
		if (descriptorList != null) {
			for (Iterator itor = descriptorList.iterator(); itor.hasNext();) {
				Object obj = itor.next();
				if (obj instanceof Descriptor) {
					MethodElement element = getAssociatedElement((Descriptor) obj);
					elementList.add(element);
				}
			}
		}
		return elementList;
	}

	public static BreakdownElement getTopBreakdownElement(ProcessComponent pc) {
		BreakdownElement be = pc.getProcess();
		if (be == null)
			return null;
		Object adapter = TngAdapterFactory.INSTANCE
				.getWBS_ComposedAdapterFactory().adapt(be,
						ITreeItemContentProvider.class);
		if (!(adapter instanceof IBSItemProvider))
			return be;
		IBSItemProvider itemProvider = (IBSItemProvider) adapter;
		if (itemProvider.getTopItem() == null) {
			itemProvider.setTopItem(be);
			itemProvider = (IBSItemProvider) TngAdapterFactory.INSTANCE
					.getOBS_ComposedAdapterFactory().adapt(be,
							ITreeItemContentProvider.class);
			itemProvider.setTopItem(be);
			itemProvider = (IBSItemProvider) TngAdapterFactory.INSTANCE
					.getPBS_ComposedAdapterFactory().adapt(be,
							ITreeItemContentProvider.class);
			itemProvider.setTopItem(be);
			itemProvider = (IBSItemProvider) TngAdapterFactory.INSTANCE
					.getProcessComposedAdapterFactory().adapt(be,
							ITreeItemContentProvider.class);
			itemProvider.setTopItem(be);
		}
		ProcessUtil.updateIDs(be, be);
		return be;
	}

	private static void updateIDs(EObject topAct, Object newObj) {
		ItemProviderAdapter adapter = (ItemProviderAdapter) TngAdapterFactory.INSTANCE
				.getWBS_ComposedAdapterFactory().adapt(topAct,
						ITreeItemContentProvider.class);
		AdapterFactory factory = TngUtil.getBestAdapterFactory(adapter
				.getAdapterFactory());
		boolean updateWholeProcess = topAct == newObj
				&& topAct instanceof Process;
		if (updateWholeProcess) {
			Process proc = (Process) topAct;
			updateIDs(factory, proc);
			refreshViewer(factory, proc);
		} else {
			AdapterFactoryTreeIterator iter = new AdapterFactoryTreeIterator(
					factory, topAct);
			updateIDs(factory, iter, newObj);
		}

		// No more ID column in TBS and WPBS
		//
		// adapter = (ItemProviderAdapter)
		// TngAdapterFactory.INSTANCE.getOBS_ComposedAdapterFactory().adapt(topAct,
		// ITreeItemContentProvider.class);
		// factory = TngUtil.getBestAdapterFactory(adapter.getAdapterFactory());
		// iter = new AdapterFactoryTreeIterator(factory, topAct);
		// ProcessUtil.updateIDs(factory, iter, newObj);
		// adapter = (ItemProviderAdapter)
		// TngAdapterFactory.INSTANCE.getPBS_ComposedAdapterFactory().adapt(topAct,
		// ITreeItemContentProvider.class);
		// factory = TngUtil.getBestAdapterFactory(adapter.getAdapterFactory());
		// iter = new AdapterFactoryTreeIterator(factory, topAct);
		// ProcessUtil.updateIDs(factory, iter, newObj);

		adapter = (ItemProviderAdapter) TngAdapterFactory.INSTANCE
				.getProcessComposedAdapterFactory().adapt(topAct,
						ITreeItemContentProvider.class);
		factory = TngUtil.getBestAdapterFactory(adapter.getAdapterFactory());
		if (updateWholeProcess) {
			Process proc = (Process) topAct;
			updateIDs(factory, proc);
			refreshViewer(factory, proc);
		} else {
			AdapterFactoryTreeIterator iter = new AdapterFactoryTreeIterator(
					factory, topAct);
			updateIDs(factory, iter, newObj);
		}
	}

	/**
	 * @param iter
	 * @param newObj
	 */
	public static void updateIDs(AdapterFactory factory,
			AdapterFactoryTreeIterator iter, Object newObj) {
		int id = 0;
		Object obj;
		Map changeMap = new HashMap();
		while (iter.hasNext()) {
			obj = iter.next();
			IBSItemProvider itemProvider = (IBSItemProvider) factory.adapt(obj,
					ITreeItemContentProvider.class);
			itemProvider.setId(id++);
			changeMap.put(obj, itemProvider);
		}

		// refresh the label
		//
		for (Iterator iterator = changeMap.entrySet().iterator(); iterator
				.hasNext();) {
			Map.Entry entry = (Map.Entry) iterator.next();
			IChangeNotifier adapter = (IChangeNotifier) entry.getValue();
			obj = entry.getKey();
			adapter.fireNotifyChanged(new ViewerNotification(
					new NotificationImpl(Notification.SET, obj, obj), obj,
					false, true));
		}
	}

	/**
	 * 
	 * @param descriptor
	 * @param obj
	 * @return
	 * @see #checkDescriptorReferences(Collection, Descriptor)
	 */
	public static boolean checkDescriptorReferences(Descriptor descriptor,
			Descriptor obj) {
		Collection descriptors = descriptor != null ? Collections
				.singleton(descriptor) : Collections.EMPTY_SET;
		return checkDescriptorReferences(descriptors, obj);
	}

	
	/**
	 * Check whether there are any references of the object "obj". If references
	 * exist in any descriptor in <code>descriptors</code>, ignore it.
	 * 
	 * @param descriptors
	 * @param obj
	 * @return
	 */
	public static boolean checkDescriptorReferences(Collection descriptors,
			Descriptor obj) {
		boolean referencesExists = false;

		if (obj instanceof RoleDescriptor) {
			RoleDescriptor refObject = (RoleDescriptor) obj;
			List list = new ArrayList();
			list.addAll(AssociationHelper.getAssistedTaskDescriptors(refObject));
//			list.addAll(refObject.getResponsibleFor());
			list.addAll(AssociationHelper.getAdditionalTaskDescriptors(refObject));
			list.addAll(AssociationHelper.getPrimaryTaskDescriptors(refObject));
			list.addAll(AssociationHelper.getTeamProfiles(refObject));

			for (Iterator refItor = list.iterator(); refItor.hasNext();) {
				Object refItorObject = (Object) refItor.next();
				if (((refItorObject instanceof Descriptor) && !descriptors
						.contains(refItorObject))
						|| (refItorObject instanceof TeamProfile)) {
					referencesExists = true;
					break;
				}

			}
		} else if (obj instanceof WorkProductDescriptor) {		
			return checkWorkProductDescriptorReferences(descriptors, (WorkProductDescriptor) obj, false, -1);
		} else if (obj instanceof TaskDescriptor) {
			TaskDescriptor refObject = (TaskDescriptor) obj;
			List list = new ArrayList();
			list.addAll(refObject.getAdditionallyPerformedBy());
			list.addAll(refObject.getAssistedBy());
			list.addAll(refObject.getExternalInput());
			list.addAll(refObject.getMandatoryInput());
			list.addAll(refObject.getOptionalInput());
			list.addAll(refObject.getOutput());
			list.addAll(refObject.getPerformedPrimarilyBy());

			for (Iterator refItor = list.iterator(); refItor.hasNext();) {
				Object refItorObject = (Object) refItor.next();
				if ((refItorObject instanceof Descriptor)
						&& !descriptors.contains(refItorObject)) {
					referencesExists = true;
					break;
				}
			}
		}

		return referencesExists;
	}
	
	/**
	 * Check whether there are any references of the workProductdescriptor object "refObject. 
	 * If references exist in any descriptor in <code>descriptors</code>, ignore it.
	 * 
	 * @param descriptors
	 * @param refObject
	 * @param removeRelation - flag to indicate whether we're removing relation
	 *     By default this should be false. 
	 *     This is only applicable if you are coming from properties view. 
	 *     eg. removing relationship of work product descriptor to task descriptor 
	 * @param featureID
	 * 		This is only applicable if "removeRelation" is true. You need to give featureID
	 * 		for which relation you are removing.
	 * @return
	 */
	public static boolean checkWorkProductDescriptorReferences(
			Collection descriptors, WorkProductDescriptor refObject, boolean removeRelation, int featureID) {
		
		List allObjects = new ArrayList();
		allObjects.add(refObject);

		Activity parentActivity = UmaUtil.getParentActivity(refObject);
		// check whether reference object instance of artifact
		if (refObject.getWorkProduct() instanceof Artifact) {
			List containedArtifacts = ((Artifact) refObject.getWorkProduct())
					.getContainedArtifacts();

			List containedWpDescList = getWpDescForArtifacts(
					containedArtifacts, parentActivity);

			List containerArtifacts = new ArrayList();
			Artifact artifact = ((Artifact) refObject.getWorkProduct())
					.getContainerArtifact();
			while (artifact != null) {
				containerArtifacts.add(artifact);
				artifact = ((Artifact) artifact).getContainerArtifact();
			}
			List containerWpDescList = getWpDescForArtifacts(
					containerArtifacts, parentActivity);

			allObjects.addAll(containedWpDescList);
			allObjects.addAll(containerWpDescList);
		}

		for (int i = 0; i < allObjects.size(); i++) {
			WorkProductDescriptor wpObj = (WorkProductDescriptor) allObjects
					.get(i);

			List list = new ArrayList();
			list.addAll(wpObj.getImpactedBy());
			list.addAll(wpObj.getImpacts());
			list.addAll(AssociationHelper.getResponsibleRoleDescriptors(wpObj));
			list.addAll(wpObj.getDeliverableParts());

			List inputList = new ArrayList();
			inputList.addAll(AssociationHelper.getExternalInputTo(wpObj));
			inputList.addAll(AssociationHelper.getMandatoryInputTo(wpObj));
			inputList.addAll(AssociationHelper.getOptionalInputTo(wpObj));

			List outputList = AssociationHelper.getOutputFrom(wpObj);
			if (removeRelation)
			{
				switch (featureID) {
				case UmaPackage.TASK_DESCRIPTOR__EXTERNAL_INPUT:
				case UmaPackage.TASK_DESCRIPTOR__MANDATORY_INPUT:
				case UmaPackage.TASK_DESCRIPTOR__OPTIONAL_INPUT:
					if (outputList.containsAll(descriptors))
						return true;
					break;
				case UmaPackage.TASK_DESCRIPTOR__OUTPUT :				
					if (inputList.containsAll(descriptors))
						return true;
				}
			}

			list.addAll(inputList);
			list.addAll(outputList);
			
			for (Iterator refItor = list.iterator(); refItor.hasNext();) {
				Object refItorObject = (Object) refItor.next();
				if ((refItorObject instanceof Descriptor)
						&& !descriptors.contains(refItorObject)) {
					return true;
				}
			}
		}
		return false;
	}

	
	/**
	 * Return list of corresponding work product descriptors for artifacts
	 * @param artifacts
	 * @param parentActivity
	 * @return
	 */
	public static List getWpDescForArtifacts(List artifacts,
			Activity parentActivity) {
		List wpDescList = new ArrayList();
		for (int i = 0; i < artifacts.size(); i++) {
			Artifact artifact = (Artifact) artifacts.get(i);
			if (parentActivity != null) {
				List brElements = parentActivity.getBreakdownElements();

				for (Iterator itor = brElements.iterator(); itor.hasNext();) {
					Object brObj = (Object) itor.next();
					if (brObj instanceof WorkProductDescriptor) {
						WorkProductDescriptor wpDesc = (WorkProductDescriptor) brObj;
						if (wpDesc.getWorkProduct() instanceof Artifact) {
							if (wpDesc.getWorkProduct().equals(artifact))
								wpDescList.add(wpDesc);
						}
					}
				}
			}
		}
		return wpDescList;
	}

	public static CompositeRole createCompositeRole(CompositeRole obj) {
		CompositeRole compRole = UmaFactory.eINSTANCE.createCompositeRole();
		compRole.getAggregatedRoles().addAll(obj.getAggregatedRoles());

		String presentationName = obj.getPresentationName();
		compRole.setName(obj.getName());
		compRole.setPresentationName(StrUtil.isBlank(presentationName) ? obj
				.getName() : presentationName);
		return compRole;
	}

	public static void addTaskToActivity(Task task, Activity act) {
		WBSDropCommand cmd = new WBSDropCommand(act, Collections
				.singletonList(task));
		BSDropCommand.IExecutor executor = cmd.getExecutor();
		if (executor.preExecute()) {
			executor.doExcecute();
		}
	}

	public static boolean hasProcessNotOfType(ProcessPackage pkg,
			EClass processType) {
		if (pkg instanceof ProcessComponent) {
			Process proc = ((ProcessComponent) pkg).getProcess();
			if (proc != null && !processType.isInstance(proc)) {
				return true;
			}
		}
		for (Iterator iterator = pkg.getChildPackages().iterator(); iterator
				.hasNext();) {
			Object childPkg = iterator.next();
			if (childPkg instanceof ProcessComponent) {
				Process proc = ((ProcessComponent) childPkg).getProcess();
				if (proc != null && !processType.isInstance(proc)) {
					return true;
				}
			} else if (childPkg instanceof ProcessPackage) {
				if (hasProcessNotOfType((ProcessPackage) childPkg, processType)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Find correspoding roleDescriptor in team since we maintain two role
	 * descriptor for the same role
	 * 
	 * @param team
	 * @param roleDesc
	 * @return
	 */
	public static Object findRoleDescriptor(TeamProfile team,
			RoleDescriptor roleDesc) {
		List teamRoles = team.getTeamRoles();

		for (Iterator itor = teamRoles.iterator(); itor.hasNext();) {
			Object itorObject = itor.next();
			if (roleDesc instanceof CompositeRole) {
				if (itorObject instanceof CompositeRole) {
					List firstObjectAggRoles = ((CompositeRole) roleDesc)
							.getAggregatedRoles();
					List secondObjectAggRoles = ((CompositeRole) itorObject)
							.getAggregatedRoles();
					if (firstObjectAggRoles.equals(secondObjectAggRoles)) {
						return itorObject;
					}
				}
			} else if (roleDesc instanceof RoleDescriptor) {
				if ((itorObject instanceof RoleDescriptor)
						&& (!(itorObject instanceof CompositeRole))) {
					Object objRole = ((RoleDescriptor) roleDesc).getRole();
					Object itorObjRole = ((RoleDescriptor) itorObject)
							.getRole();
					if (objRole.equals(itorObjRole)) {
						return itorObject;
					}
				}
			}
		}

		return null;
	}

	/**
	 * Creates a columnIndexToNameMap from the comma-separated list of column
	 * names
	 * 
	 * @param newValue
	 * @return
	 */
	public static Map toColumnIndexToNameMap(String colNames) {
		Map columnIndexToNameMap = new HashMap();
		StringTokenizer tokens = new StringTokenizer(colNames, ","); //$NON-NLS-1$
		int id = 0;
		while (tokens.hasMoreTokens()) {
			String token = tokens.nextToken();
			columnIndexToNameMap.put(new Integer(id), getColumnID(token));
			id++;
		}
		return columnIndexToNameMap;
	}

	/**
	 * @param token
	 * @return
	 */
	private static String getColumnID(String colName) {
		for (int i = 0; i < IBSItemProvider.COLUMNS.length; i++) {
			String col = IBSItemProvider.COLUMNS[i];
			if (col.equals(colName)) {
				return col;
			}
		}
		return null;
	}

	// This method is used to check whether an new descriptor can be created for a given method element
	// under the given activity, considering all inherited descriptors of the activity.
	// It is NOT EFFICIENT to collect all linked elements of the inherited descriptors into a list and then check if
	// whether the given method element is in the list.
	//
	// Use ProcessCommandUtil#getValidDescriptor(Object obj, Activity activity) instead
	//
//	public static List getVariabilityElement(Object owner) {
//		List baseElements = new ArrayList();
//		if (owner instanceof Activity) {
//			Activity activity = (Activity) owner;
//			while (!activity.getVariabilityType().equals(
//					VariabilityType.NA)) {
//				VariabilityElement element = activity
//						.getVariabilityBasedOnElement();
//
//				if ((element != null) && (element instanceof Activity)) {
//					Activity baseActivity = (Activity) element;
//
//					List breakdownElements = baseActivity
//							.getBreakdownElements();
//					for (Iterator itor = breakdownElements.iterator(); itor
//							.hasNext();) {
//						Object object = itor.next();
//						if (object instanceof Descriptor) {
//							Object baseObj = getAssociatedElement((Descriptor) object);
//							if (baseObj != null) {
//								baseElements.add(baseObj);
//							}
//						}
//					}
//
//					activity = baseActivity;
//				}
//				else {
//					break;
//				}
//			}
//		}
//
//		return baseElements;
//	}

	/**
	 * Refreshes predecessor list of all item providers of the given process
	 * 
	 * @param factory
	 * @param proc
	 * @param elements
	 */
	public static void removePredecessors(AdapterFactory factory, Process proc,
			List removedElements) {
		HashSet elements = new HashSet(removedElements);
		for (Iterator iter = new AdapterFactoryTreeIterator(factory, proc); iter
				.hasNext();) {
			Object obj = iter.next();
			IBSItemProvider itemProvider = (IBSItemProvider) factory.adapt(obj,
					ITreeItemContentProvider.class);
			if (itemProvider != null
					&& TngUtil.unwrap(obj) instanceof WorkBreakdownElement) {
				for (Iterator iterator = itemProvider.getPredecessors()
						.iterator(); iterator.hasNext();) {
					Object e = TngUtil.unwrap(iterator.next());
					if (e instanceof ItemProviderAdapter) {
						e = ((ItemProviderAdapter) e).getTarget();
					}
					if (elements.contains(e)) {
						iterator.remove();
					}
				}
			}
		}

	}

	private static void addToActivity(Activity act, BreakdownElement be, Object[] prevAndNext) {
		Object next = prevAndNext[1];
		boolean added = false;
		if(next != null) {
			int id = act.getBreakdownElements().indexOf(next);
			if(id != -1) { 
				act.getBreakdownElements().add(id, be);
				added = true;
			}
		}
		if(!added) {
			Object prev = prevAndNext[0];
			if(prev != null) {				
				int id = act.getBreakdownElements().indexOf(prev);
				if(id != -1) {
					act.getBreakdownElements().add(id + 1, be);
				}
				else {
					act.getBreakdownElements().add(be);
				}
			}
			else {
				act.getBreakdownElements().add(be);
			}
		}

	}
	
	/**
	 * Locally contributes to the inherited activity represented by the given adapter
	 * 
	 * @param adapter
	 * @param createdActivities
	 * @return
	 */
	public static Activity contributeToActivity(
			BreakdownElementWrapperItemProvider adapter, List createdActivities) {
		Object parent = adapter.getParent(null);
		Object[] prevAndNext = ProcessUtil
				.getPreviousAndNext(adapter);
		if (parent instanceof BreakdownElementWrapperItemProvider) {
			parent = contributeToActivity(
					(BreakdownElementWrapperItemProvider) parent,
					createdActivities);
		}
		Activity act = ProcessUtil.generialize(adapter,
				VariabilityType.LOCAL_CONTRIBUTION, prevAndNext);
		Activity parentAct = ((Activity) parent);
		addToActivity(parentAct, act, prevAndNext);
		
		try {
			copySuppressions(adapter, act);
		} catch (Throwable e) {
			LibraryEditPlugin.getDefault().getLogger().logError(e);
		}
		
		createdActivities.add(act);
		return act;
	}
		
	private static void copySuppressions(
			BreakdownElementWrapperItemProvider adapterForBaseAct,
			Activity contributerAct) {
		IBreakdownElementWrapperItemProviderFactory factory = BreakdownElementWrapperItemProviderFactory.INSTANCE;
		BreakdownElementWrapperItemProvider newAdapter = (BreakdownElementWrapperItemProvider) factory
				.createWrapper(contributerAct, adapterForBaseAct.getOwner(),
						adapterForBaseAct.getAdapterFactory());
		Map<BreakdownElement, BreakdownElementWrapperItemProvider> newElementProviderMap = new HashMap<BreakdownElement, BreakdownElementWrapperItemProvider>();
		for (Object newItem : newAdapter.getChildren(null)) {
			if (newItem instanceof BreakdownElementWrapperItemProvider) {
				Object unwrapped = TngUtil.unwrap(newItem);
				if (unwrapped instanceof BreakdownElement) {
					newElementProviderMap.put((BreakdownElement) unwrapped,
							(BreakdownElementWrapperItemProvider) newItem);
				}
			}
		}

		Object top = adapterForBaseAct.getTopItem();
		Process process = top instanceof Process ? (Process) top : null;
		Suppression sup = process == null ? null : Suppression
				.getSuppression(process);
		if (sup == null) {
			return;
		}
		List<BreakdownElementWrapperItemProvider> toSuppressList = new ArrayList<BreakdownElementWrapperItemProvider>();
		List<BreakdownElementWrapperItemProvider> toRevealList = new ArrayList<BreakdownElementWrapperItemProvider>();
		for (Object oldItem : adapterForBaseAct.getChildren(null)) {
			if (!(oldItem instanceof BreakdownElementWrapperItemProvider)) {
				continue;
			}
			Object unwrapped = TngUtil.unwrap(oldItem);
			if (!(unwrapped instanceof BreakdownElement)) {
				continue;
			}
			BreakdownElementWrapperItemProvider newProvider = newElementProviderMap.get(unwrapped);
			if (newProvider == null) {
				continue;
			}
			BreakdownElementWrapperItemProvider oldProvider = (BreakdownElementWrapperItemProvider) oldItem;
			boolean oldSup = sup.isSuppressed(oldProvider);
			boolean newSup = sup.isSuppressed(newProvider);
			if (oldSup != newSup) {
				if (oldSup) {
					toSuppressList.add(newProvider);
				} else {
					toRevealList.add(newProvider);
				}
			}
		}
		if (! toSuppressList.isEmpty()) {
			sup.suppress(toSuppressList);
		}
		if (! toRevealList.isEmpty()) {
			sup.reveal(toRevealList);
		}
	}

	/**
	 * Locally replaces the inherited activity represented by the given adapter
	 * 
	 * @param adapter
	 * @param createdActivities
	 */
	public static void replaceActivityLocally(
			BreakdownElementWrapperItemProvider adapter, List createdActivities) {
		Object parent = adapter.getParent(null);
		Object[] prevAndNext = ProcessUtil
				.getPreviousAndNext(adapter);
		if (parent instanceof BreakdownElementWrapperItemProvider) {
			parent = contributeToActivity(
					(BreakdownElementWrapperItemProvider) parent,
					createdActivities);
		}
		Activity act = ProcessUtil.generialize(adapter,
				VariabilityType.LOCAL_REPLACEMENT, prevAndNext);
		Activity parentAct = ((Activity) parent);
		addToActivity(parentAct, act, prevAndNext);
		createdActivities.add(act);
	}

	static Object[] getPreviousAndNext(
			BreakdownElementWrapperItemProvider adapter) {
		Object parent = adapter.getParent(null);
		AdapterFactory adapterFactory = TngUtil.getBestAdapterFactory(adapter
				.getAdapterFactory());
		ITreeItemContentProvider itemProvider = (ITreeItemContentProvider) adapterFactory
				.adapt(parent, ITreeItemContentProvider.class);
		List children = (List) itemProvider.getChildren(parent);
		int id = children.indexOf(adapter) + 1;
		Object next = null;
		if (id < children.size()) {
			next = children.get(id);
		}
		Object prev = null;
		id -= 2;
		if (id > -1) {
			prev = children.get(id);
		}

		Object[] prevAndNext = { prev, next };
		return prevAndNext;
	}

	static Activity generialize(BreakdownElementWrapperItemProvider adapter,
			VariabilityType type, Object[] prevAndNext) {
		AdapterFactory adapterFactory = TngUtil.getBestAdapterFactory(adapter
				.getAdapterFactory());

		Activity act = generalize((Activity) TngUtil.unwrap(adapter), type);
		Object next = prevAndNext[1];
		if(next != null) {
			act.setPresentedAfter((BreakdownElement) TngUtil.unwrap(next));
			if(next instanceof BreakdownElement) {
				((BreakdownElement)next).setPresentedBefore(act);
			}
		}
		Object prev = prevAndNext[0];
		if(prev != null) {
			act.setPresentedBefore((BreakdownElement) TngUtil.unwrap(prev));
			if (prev instanceof BreakdownElement) {
				((BreakdownElement)prev).setPresentedAfter(act);
			}
		}
		IBSItemProvider bsItemProvider = (IBSItemProvider) adapterFactory
				.adapt(act, ITreeItemContentProvider.class);
		bsItemProvider.setExpanded(adapter.isExpanded());
		return act;
	}

	/**
	 * Gets the immediate base process of the given
	 * BreakdownElementWrapperItemProvider that represents an inherited
	 * breakdown element.
	 * 
	 * @param itemProvider
	 * @return
	 */
	public static Process getImmediateBaseProcess(
			BreakdownElementWrapperItemProvider itemProvider) {
		Activity inheritor = getInheritor(itemProvider);
		if (inheritor != null) {
			return TngUtil.getOwningProcess((BreakdownElement) inheritor
					.getVariabilityBasedOnElement());
		}
		return null;
	}

	/**
	 * Gets the activity in the process of the given item provider that inherits
	 * the element represented by the given item provider
	 * 
	 * @param itemProvider
	 * @return
	 */
	public static Activity getInheritor(
			BreakdownElementWrapperItemProvider itemProvider) {
		if (itemProvider.isReadOnly()) {
			// this represents an inherited breakdown element
			//
			for (Object parent = itemProvider.getParent(itemProvider); parent != null;) {
				if (parent instanceof BreakdownElementWrapperItemProvider) {
					parent = ((BreakdownElementWrapperItemProvider) parent)
							.getParent(parent);
				} else {
					return (Activity) parent;
				}
			}
		}
		return null;
	}

	/**
	 * Gets parent list of <code>wrapper</code> from <code>from</code>,
	 * excluded <code>from</code>
	 * 
	 * @param from
	 * @param wrapper
	 * @return list of unwrapped parent objects
	 */
	public static List getParentList(Object from,
			BreakdownElementWrapperItemProvider wrapper) {
		return getParentList(wrapper, from, wrapper.getAdapterFactory());
	}
	
	/**
	 * Gets the list of GUIDs from the top element to the wrapper inclusive.
	 * 
	 * @param wrapper
	 * @return
	 */
	public static LinkedList<String> getGuidList(BreakdownElementWrapperItemProvider wrapper) {
		LinkedList<String> guidList = new LinkedList<String>();
		List parentList = ProcessUtil.getParentList(null, wrapper);
		if (!parentList.isEmpty()) {
			for (Iterator iter = parentList.iterator(); iter.hasNext();) {
				MethodElement e = (MethodElement) iter.next();
				// exclude TaskDescriptor and RoleDescriptor from the parent
				// path b/c those descriptors can be
				// parent only in CBS view
				if (!(e instanceof TaskDescriptor || e instanceof RoleDescriptor)) {
					guidList.add(e.getGuid());
				}
			}
		}
		MethodElement e = (MethodElement) TngUtil.unwrap(wrapper);
		guidList.add(e.getGuid());
		return guidList;
	}
	
	/**
	 * Gets the list of GUIDs from start to activity, excluded start activity.
	 * @param from
	 * @param activity
	 * @return
	 */
	public static LinkedList<String> getGuidList(Activity start, Activity activity) {
		LinkedList<String> guidList = new LinkedList<String>();
		for(Activity parent = activity; parent != null && (start == null || parent != start); parent = parent.getSuperActivities()) {
			guidList.addFirst(parent.getGuid());
		}
		return guidList;
	}
	
	public static List<String> getGuidList(Object activityOrWrapper) {
		if(activityOrWrapper instanceof BreakdownElementWrapperItemProvider) {
			return getGuidList((BreakdownElementWrapperItemProvider)activityOrWrapper);
		}
		else if(activityOrWrapper instanceof Activity) {
			return getGuidList(null, (Activity) activityOrWrapper);
		}
		return null;
	}
	
	public static String toGuidPath(List<String> guidList) {
		StringBuffer path = new StringBuffer();
		for (String guid : guidList) {
			path.append('/').append(guid);
		}
		return path.toString();
	}
	
	/**
	 * Gets parent list of <code>object</code> from <code>from</code>,
	 * excluded <code>from</code>
	 * 
	 * @param object
	 * @param from
	 * @param adapterFactory
	 * @return list of unwrapped parent objects
	 */
	public static List getParentList(Object object, Object from, AdapterFactory adapterFactory) {
		ArrayList parentList = new ArrayList();
		ITreeItemContentProvider itemProvider = (ITreeItemContentProvider) adapterFactory.adapt(object, ITreeItemContentProvider.class);
		for (Object parent = itemProvider.getParent(object); parent != from
				&& parent != null;) {
			Object obj = TngUtil.unwrap(parent);
			MethodElement e;			
			if (obj instanceof MethodElement) {
				e = (MethodElement) obj;
				ITreeItemContentProvider adapter;
				if (parent instanceof ITreeItemContentProvider) {
					adapter = (ITreeItemContentProvider) parent;
				} else {
					adapter = (ITreeItemContentProvider) adapterFactory.adapt(
							parent, ITreeItemContentProvider.class);
				}
				if(ProcessUtil.isTopProcess(e)) {
					parent = null;
				}
				else {
					parent = adapter.getParent(parent);
				}
			} else {
				// must be a ItemProviderAdapter
				//
				ItemProviderAdapter adapter = ((ItemProviderAdapter) obj);
				e = (MethodElement) adapter.getTarget();
				parent = adapter.getParent(parent);
			}
			parentList.add(0, e);
		}
		return parentList;
	}

	/**
	 * Gets the wrappers of all breakdown structure (BS) views in the following
	 * order: WBS, TBS, WPBS
	 * 
	 * @param wrapper
	 *            the wrapper of a BS view
	 * @return
	 */
	public static List getWrappers(BreakdownElementWrapperItemProvider wrapper, AdapterFactory[] adapterFactories) {
		ArrayList rolledUpItemProviders = new ArrayList();
		try {
			AdapterFactory rootAdapterFactory = TngUtil.getBestAdapterFactory(wrapper.getAdapterFactory());
			Object proc = wrapper.getTopItem();						
			ArrayList objects = new ArrayList(ProcessUtil.getParentList(proc,
					wrapper));
			ArrayList wrappers = new ArrayList();
			for (int i = 0; i < adapterFactories.length; i++) {
				AdapterFactory adapterFactory = adapterFactories[i];
				if (adapterFactory == rootAdapterFactory) {
					wrappers.add(wrapper);
					continue;
				}
				Object e = TngUtil.unwrap(wrapper);
				objects.add(e);
				Object object = proc;
				for (Iterator iter1 = objects.iterator(); iter1.hasNext();) {
					Object element = iter1.next();
					ITreeItemContentProvider adapter = (ITreeItemContentProvider) adapterFactory
					.adapt(object, ITreeItemContentProvider.class);
					
					// make sure the activity is rolled down before looking into its children
					//
					Object be = TngUtil.unwrap(object);								
					if(be instanceof Activity) {
						if(adapter instanceof BSActivityItemProvider) {
							BSActivityItemProvider itemProvider = (BSActivityItemProvider) adapter;
							if(itemProvider.isRolledUp()) {
								itemProvider.basicSetRolledUp(false);
								rolledUpItemProviders.add(itemProvider);
							}
						}
						else if(adapter instanceof IBSItemProvider) {
							IBSItemProvider itemProvider = (IBSItemProvider) adapter;
							if(itemProvider.isRolledUp()) {
								itemProvider.setRolledUp(false);
								rolledUpItemProviders.add(itemProvider);
							}
						}
					}
					
					find_child: for (Iterator iterator = adapter
							.getChildren(object).iterator(); iterator.hasNext();) {
						Object child = iterator.next();
						if (element == TngUtil.unwrap(child)) {
							object = child;
							break find_child;
						}
					}
				}
				if (object instanceof BreakdownElementWrapperItemProvider) {
					wrappers.add(object);
				} else {
					throw new RuntimeException(
							"Could not find wrapper for " + e + " using adapter factory " + adapterFactory); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
			return wrappers;
		}
		finally {
			if(!rolledUpItemProviders.isEmpty()) {
				// restore the rolledUp flag
				//
				for (Iterator iter = rolledUpItemProviders.iterator(); iter
				.hasNext();) {
					((BSActivityItemProvider) iter.next()).basicSetRolledUp(true);
				}
			}
		}
	}
	
	/**
	 * Gets WorkProductDescriptor specific model info 
	 * @param modelInfo
	 * @param object WorkProductDescriptor or its wrapper
	 * @param itemProvider item provider of WorkProductDescriptor or its wrapper
	 */
	public static void getWPDModelInfo(StringBuffer modelInfo, Object object, Object itemProvider) {
		Object element = TngUtil.unwrap(object);
		if(element instanceof WorkProductDescriptor) {	
			WorkProductDescriptor wpd = (WorkProductDescriptor)element;
			if(itemProvider instanceof ITreeItemContentProvider) {
				// check if this work product descriptor is shown under a task descriptor or role descriptor
				//
				AdapterFactory adapterFactory = null;
				if(itemProvider instanceof ItemProviderAdapter) {
					adapterFactory = ((ItemProviderAdapter)itemProvider).getAdapterFactory();
				}
				else if(itemProvider instanceof BreakdownElementWrapperItemProvider) {
					adapterFactory = ((BreakdownElementWrapperItemProvider)itemProvider).getAdapterFactory();
				}
				if(adapterFactory != null) {
					Object parentDescriptor = null;
					ITreeItemContentProvider adapter = (ITreeItemContentProvider)itemProvider;
					findParentDescriptor:
						for(Object parent = adapter.getParent(object); parent != null; parent = adapter.getParent(parent)) {
							Object e = TngUtil.unwrap(parent);
							if(e instanceof TaskDescriptor) {
								parentDescriptor = e;
								break findParentDescriptor;
							}
							else if(e instanceof Activity) {
								break findParentDescriptor;
							}
							adapter = (ITreeItemContentProvider) adapterFactory.adapt(parent, ITreeItemContentProvider.class);
						}
					if(parentDescriptor != null) {	
						// work product descriptor is shown under a task descriptor
						// show only model info related to this task descriptor
						//
						ArrayList features = new ArrayList();
						if(AssociationHelper.getMandatoryInputTo(wpd).contains(parentDescriptor)) {
							features.add(UmaPackage.eINSTANCE.getTaskDescriptor_MandatoryInput());
						}
						if(AssociationHelper.getOptionalInputTo(wpd).contains(parentDescriptor)) {
							features.add(UmaPackage.eINSTANCE.getTaskDescriptor_OptionalInput());							
						}
						if(AssociationHelper.getOutputFrom(wpd).contains(parentDescriptor)) {
							features.add(UmaPackage.eINSTANCE.getTaskDescriptor_Output());
						}
						
						ModifiedTypeMeta meta = TypeDefUtil.getMdtMeta(UmaPackage.eINSTANCE.getTask());
						if (meta != null) {
						TaskDescriptor td = (TaskDescriptor) parentDescriptor;
							for (ExtendedReference eRef : meta.getReferences()) {
								if (ExtendedReference.WorkProducts.equals(eRef.getContributeTo())) {
									List list = PropUtil.getPropUtil().getExtendedReferenceList(td, eRef, false);
									if (list.contains(wpd)) {
										features.add(eRef.getReference());
									}
								}
							}
						}
						
						if(!features.isEmpty()) {
							if (modelInfo.toString().length() > 0) {
								modelInfo.append(","); //$NON-NLS-1$
							}
							for (int i = 0; i < features.size(); i++) {
								EStructuralFeature feature = (EStructuralFeature) features.get(i);
								modelInfo.append(TngUtil.getFeatureText(feature));
								if(i < features.size() -1){
									modelInfo.append(","); //$NON-NLS-1$
								}
							}						
						}
						return;
					}
				}
			}
			getModelInfoForWorkProductDescriptor(modelInfo, wpd);
		}
	}

	/**
	 * Retrieves the ModelInformation specific to WorkProductDescriptor. Model
	 * Information is for PBS (WorkProductDescriptors, including Extended
	 * Activity's WorkProductDescriptors).
	 */
	private static void getModelInfoForWorkProductDescriptor(
			StringBuffer modelInfo, WorkProductDescriptor object) {
		String comma = ","; //$NON-NLS-1$
		if (!AssociationHelper.getMandatoryInputTo(object).isEmpty()) {
			if (modelInfo.toString().length() > 0) {
				modelInfo.append(comma);
			}
			modelInfo.append(UmaEditPlugin.INSTANCE
					.getString("_UI_TaskDescriptor_mandatoryInput_feature")); //$NON-NLS-1$
		}
		if (!AssociationHelper.getOptionalInputTo(object).isEmpty()) {
			if (modelInfo.toString().length() > 0) {
				modelInfo.append(comma);
			}
			modelInfo.append(UmaEditPlugin.INSTANCE
					.getString("_UI_TaskDescriptor_optionalInput_feature")); //$NON-NLS-1$
		}
		if (!AssociationHelper.getOutputFrom(object).isEmpty()) {
			if (modelInfo.toString().length() > 0) {
				modelInfo.append(comma);
			}
			modelInfo.append(UmaEditPlugin.INSTANCE
					.getString("_UI_TaskDescriptor_output_feature")); //$NON-NLS-1$
		}
		ModifiedTypeMeta meta = TypeDefUtil.getMdtMeta(UmaPackage.eINSTANCE.getTask());
		if (meta != null) {
			for (ExtendedReference eRef : meta.getReferences()) {
				if (ExtendedReference.WorkProducts.equals(eRef.getContributeTo())) {
					List list = PropUtil.getPropUtil().getReferencingList(object, eRef);
					if (list != null && !list.isEmpty()) {
						if (modelInfo.toString().length() > 0) {
							modelInfo.append(comma);
						}
						modelInfo.append(eRef.getName());
					}
				}
			}
		}
	}

	public static Collection getDuplicateDescriptorsAfterReveal(Collection elementsToReveal) {
		Collection duplicates = new ArrayList();
		for (Iterator iter = elementsToReveal.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if(element instanceof Descriptor) {
				Descriptor desc = (Descriptor) element;
				MethodElement e = ProcessUtil.getAssociatedElement(desc);
				if(e != null) {
					Activity act = UmaUtil.getParentActivity(desc);
					MethodConfiguration config = TngUtil.getOwningProcess(act).getDefaultContext();
					e = (MethodElement) Providers.getConfigurationApplicator().resolve(e, config);
					Object duplicate = ProcessCommandUtil.getDescriptor(e, act, config);
					if(duplicate != null && !duplicates.contains(duplicate)) {					
						duplicates.add(duplicate);
					}
				}
			}
		}
		
		return duplicates;
	}
	
	public static Collection<Descriptor> getDuplicateDescriptorsAfterSuppress(Collection elementsToSuppress) {
		Collection<Descriptor> duplicates = new ArrayList<Descriptor>();
		for (Iterator iter = elementsToSuppress.iterator(); iter.hasNext();) {
			Object item = iter.next();
			if(item instanceof Descriptor) {
				Descriptor desc = (Descriptor) item;
				MethodElement e = ProcessUtil.getAssociatedElement(desc);
				if(e != null) {
					Activity act = UmaUtil.getParentActivity(desc);
					for (Iterator iterator = act.getBreakdownElements().iterator(); iterator.hasNext();) {
						BreakdownElement element = (BreakdownElement) iterator.next();
						if(element != desc && element instanceof Descriptor && element.getSuppressed().booleanValue()) {
							MethodElement linkedElement = ProcessUtil.getAssociatedElement((Descriptor) element);
							if(linkedElement == e) {
								duplicates.add((Descriptor) element);
							}
						}
					}
				}
			}
		}
		
		return duplicates;
		
	}

	/**
	 * Checks if the given object is representing a inherited element in a breakdown structure tree of process editor
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isInherited(Object object) {
		return object instanceof DescribableElementWrapperItemProvider
		&& ((DescribableElementWrapperItemProvider)object).isInherited();
	}
	
	/**
	 * Checks if the given activity has inherited any breakdown element via extension of local contribution
	 * 
	 * @param activity
	 * @return
	 */
	public static boolean hasInherited(Activity activity) {
		Iterator iter = new AbstractTreeIterator(activity) {

			/**
			 * Comment for <code>serialVersionUID</code>
			 */
			private static final long serialVersionUID = 0L;

			protected Iterator getChildren(Object object) {
				if(object instanceof Activity) {
					return ((Activity)object).getBreakdownElements().iterator();
				}
				return Collections.EMPTY_LIST.iterator();
			}
			
		};
		while(iter.hasNext()) {
			Object o = iter.next();
			if(o instanceof Activity) {
				Activity act = (Activity) o;
				VariabilityElement ve = act.getVariabilityBasedOnElement();
				if(ve != null) {
					VariabilityType type = act.getVariabilityType();
					if(type == VariabilityType.EXTENDS || type == VariabilityType.LOCAL_CONTRIBUTION) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean isContributed(Object object) {
		return object instanceof BreakdownElementWrapperItemProvider
		&& ((BreakdownElementWrapperItemProvider)object).isContributed();
	}	
	
	public static void getAllSubTeams(TeamProfile team, List teams) {
		teams.add(team);
		List subTeams = team.getSubTeam();
		for (Iterator itor = subTeams.iterator(); itor.hasNext();) {
			TeamProfile subTeam = (TeamProfile) itor.next();

			getAllSubTeams(subTeam, teams);
		}
	}
	
	private static List getTeamProfiles(Object object, ITreeItemContentProvider adapter, AdapterFactory adapterFactory) {
		RoleDescriptor roleDesc = (RoleDescriptor) TngUtil.unwrap(object);
		List teams = new ArrayList(AssociationHelper.getTeamProfiles(roleDesc));
		
		// get all the team profiles that are in scope of the wrapper
		//
		HashSet visibleTeams = new HashSet();
		for (Object parent = adapter.getParent(object); parent != null;) {
			adapter = (ITreeItemContentProvider) adapterFactory.adapt(parent, ITreeItemContentProvider.class);		
			for (Iterator iter = adapter.getChildren(parent).iterator(); iter.hasNext();) {
				Object e = TngUtil.unwrap(iter.next());
				if (e instanceof TeamProfile) {
					List activityTeams = new ArrayList();
					getAllSubTeams((TeamProfile) e, activityTeams);
					for (Iterator itor = activityTeams.iterator(); itor.hasNext();)	{
						Object o = itor.next();
						if(o instanceof TeamProfile && ((TeamProfile)o).getTeamRoles().contains(roleDesc)) {
							visibleTeams.add(o);
						}
					}
				}
			}
			Object newParent = adapter.getParent(parent);
			if(newParent == null && parent instanceof Activity) {
				newParent = UmaUtil.getParentActivity((Activity)parent);
			}
			parent = newParent;
		}
		
		// remove any team profile that is not in the scope of the wrapper
		//
		for (Iterator iter = teams.iterator(); iter.hasNext();) {
			Object team = iter.next();
			if(!visibleTeams.contains(team)) {
				iter.remove();
			}
		}
		
		return teams;

	}
	
	/**
	 * Gets the list of visible team profiles for the role descriptor represented by the given wrapper
	 * 
	 * @param roleDescWrapper
	 * @return
	 */
	public static List getTeamProfiles(RoleDescriptorWrapperItemProvider roleDescWrapper) {
		return getTeamProfiles(roleDescWrapper, roleDescWrapper, roleDescWrapper.getAdapterFactory());
	}
	
	public static List getTeamProfiles(RoleDescriptor roleDesc, AdapterFactory adapterFactory) {
		ITreeItemContentProvider adapter = (ITreeItemContentProvider) adapterFactory.adapt(roleDesc, ITreeItemContentProvider.class);
		return getTeamProfiles(roleDesc, adapter, adapterFactory);
	}
	
	/**
	 * Return list of recursive child elements for the given activity and given type
	 * @param act
	 * @param type
	 * @param collection
	 */
	public static void getChildElements(Activity act, Class type, Collection collection) {
		for (int i = act.getBreakdownElements().size() - 1; i > -1; i--) {
			Object element = act.getBreakdownElements().get(i);
			if(type.isInstance(element)) {
				collection.add(element);
			}
			if(element instanceof Activity) {
				getChildElements((Activity) element, type, collection);
			}
		}
	}
	
	public static IFilter getFilter(AdapterFactory adapterFactory) {
		AdapterFactory rootAdapterFactory = TngUtil.getBestAdapterFactory(adapterFactory);
		if(rootAdapterFactory instanceof ConfigurableComposedAdapterFactory) {
			return ((ConfigurableComposedAdapterFactory)rootAdapterFactory).getFilter();
		}
		return null;
	}
	
	/**
	 * Gets the calculated list of artifact contained by the given artifact based on the
	 * configuration associated with the given adapter factory.
	 * 
	 * @param artifact
	 * @param adapterFactory
	 * @return
	 */
	public static List getContainedArtifacts(Artifact artifact, AdapterFactory adapterFactory) {
		MethodConfiguration config = null;
		IFilter filter = ProcessUtil.getFilter(adapterFactory);
		if(filter instanceof IConfigurator) {
			config = ((IConfigurator)filter).getMethodConfiguration();
		}
		
		if(config == null) {
			return artifact.getContainedArtifacts();
		}
		else {
			return (List) Providers.getConfigurationApplicator().getReference(
					artifact,
					UmaPackage.eINSTANCE.getArtifact_ContainedArtifacts(),
					config);
		}		
	}
	
	public static List getContainedArtifactsDescriptors(WorkProductDescriptor artifactDesc, List artifactDescriptors) {
		if(artifactDescriptors.isEmpty()) {
			return Collections.EMPTY_LIST;
		}
		Artifact artifact = (Artifact) artifactDesc.getWorkProduct();
		
		// collect own and contributing subartifact descriptors
		//
		ArrayList containedDescriptors = new ArrayList();
		for(int i = artifactDescriptors.size() - 1; i > -1; i--) {
			WorkProductDescriptor wpd = ((WorkProductDescriptor)artifactDescriptors.get(i));
			WorkProduct a = wpd.getWorkProduct();
			if(a != null) {
				EObject container = a.eContainer();
				if(container == artifact || 
						(container instanceof Artifact && TngUtil.isContributorOf(artifact, (Artifact) container))) {
					containedDescriptors.add(wpd);
				}
			}
		}
		return containedDescriptors;
	}
	
//	public static boolean isContainedBy(Artifact parent, Artifact child, AdapterFactory adapterFactory) {
//		MethodConfiguration config = null;
//		IFilter filter = ProcessUtil.getFilter(adapterFactory);
//		if(filter instanceof IConfigurator) {
//			config = ((IConfigurator)filter).getMethodConfiguration();
//		}
//		
//		if(config == null) {
//			return UmaUtil.isContainedBy(child, parent);
//		}
//		
//		final MethodConfiguration cfg = config;		
//		Iterator iter = new AbstractTreeIterator(parent, false) {
//
//			/**
//			 * Comment for <code>serialVersionUID</code>
//			 */
//			private static final long serialVersionUID = 1L;
//
//			protected Iterator getChildren(Object object) {
//				return ((Collection) Providers.getConfigurationApplicator().getReference(
//						(VariabilityElement) object,
//						UmaPackage.eINSTANCE.getArtifact_ContainedArtifacts(),
//						cfg)).iterator();
//			}
//			
//		};
//		while(iter.hasNext()) {
//			if(child == iter.next()) {
//				return true;
//			}
//		}
//		
//		return false;
//	}
	
	/**
	 * Checks if the given WorkProductDescriptor <code>parent</code> can represent the parent of the WorkProductDescriptor
	 * <code>child</code> based on the relationship of their linked artifact and the list of available artifact descriptors
	 */
	public static boolean isContainedBy(WorkProductDescriptor parent, WorkProductDescriptor child, final List artifactDescList) {
//		Iterator iter = new AbstractTreeIterator(parent, false) {
//			
//			/**
//			 * Comment for <code>serialVersionUID</code>
//			 */
//			private static final long serialVersionUID = 1L;
//			
//			protected Iterator getChildren(Object object) {
//				return getContainedArtifactsDescriptors((WorkProductDescriptor) object, artifactDescList).iterator();
//			}
//			
//		};
//		while(iter.hasNext()) {
//			if(child == iter.next()) {
//				return true;
//			}
//		}
//		
//		return false;
		
		return UmaUtil.isContainedBy(child.getWorkProduct(), parent.getWorkProduct());
	}
	
	public static List removeSubartifactsFromChildren(MethodConfiguration config, final Collection children, boolean unwrap) {
		List artifactList = new ArrayList();
	
		// get the artifact list
		//
		for (Iterator iter = children.iterator(); iter.hasNext();) {
			Object child = iter.next();
			if(unwrap) {
				child = TngUtil.unwrap(child);
			}
			if (child instanceof WorkProductDescriptor) {
				WorkProduct wp = ((WorkProductDescriptor) child)
						.getWorkProduct();
				if (wp instanceof Artifact) {
					artifactList.add(wp);
				}
			}
		}
	
		// process the artifact list to get the top-most ones
		//
		Set topMostArtifacts = new HashSet();
		Artifact candidate = null;
	
		boolean found = false;
		while (!artifactList.isEmpty()) {
			
			if (!found)
			{
				candidate = (Artifact) artifactList.get(0);
				artifactList.remove(0);
			}
			for (Iterator iter = artifactList.iterator(); iter.hasNext();) {
				Artifact artifact = (Artifact) iter.next();
				found = false;
				// if(candidate.getContainedArtifacts().contains(artifact)) {
				if (LibraryEditUtil.getInstance().isContainedBy(artifact, candidate, config)) {
					iter.remove();
				}
				else if (LibraryEditUtil.getInstance().isContainedBy(candidate, artifact, config)) {
					iter.remove();
					candidate = artifact;
					found = true;
					break;
				}
			}
			if (!found || artifactList.isEmpty())
				topMostArtifacts.add(candidate);
			else if (artifactList.isEmpty())
					topMostArtifacts.add(candidate);		
		}
	
		List result = new ArrayList();
		for (Iterator ci = children.iterator(); ci.hasNext();) {
			Object child = ci.next();
			Object e = unwrap ? TngUtil.unwrap(child) : child;
			boolean selected = false;
			if (e instanceof WorkProductDescriptor) {
				WorkProductDescriptor wpd = ((WorkProductDescriptor) e);
				WorkProduct wp = wpd.getWorkProduct();
				if (!(wp instanceof Artifact)) {
					selected = true;
				} else if (topMostArtifacts.contains(wp)) {
					selected = true;
				}
			} else {
				selected = true;
			}
			if (selected) {
				result.add(child);
			}
		}
		return result;
	}

	/**
	 * This method helps build the artifact descriptor tree correctly
	 * 
	 * @param children
	 * @param unwrap
	 * @param adapterFactory
	 * @return
	 */
	public static List removeSubartifactsFromChildren(final Collection children, boolean unwrap, AdapterFactory adapterFactory) {
		List artifactDescList = new ArrayList();

		// get the artifact list
		//
		for (Iterator iter = children.iterator(); iter.hasNext();) {
			Object child = iter.next();
			if(unwrap) {
				child = TngUtil.unwrap(child);
			}
			if (child instanceof WorkProductDescriptor) {
				WorkProduct wp = ((WorkProductDescriptor) child)
						.getWorkProduct();
				if (wp instanceof Artifact) {
					artifactDescList.add(child);
				}
			}
		}

		if(artifactDescList.isEmpty()) {
			if(children instanceof List) {
				return (List) children;
			}
			else {
				return new ArrayList(children);
			}
		}
		
		MethodConfiguration config = null;
		if (adapterFactory != null) {
			IFilter filter = getFilter(adapterFactory);
			if (filter instanceof IConfigurator) {
				config = ((IConfigurator) filter).getMethodConfiguration();
			}
		}		
		
		// process the artifact list to get the top-most ones
		//
		Set topMostArtifactDescList = new HashSet();
		List artifactDescriptors = new ArrayList(artifactDescList);
		WorkProductDescriptor candidate = null;
		boolean found = false;
		while (!artifactDescList.isEmpty()) {
			
			if (!found)
			{
				candidate = (WorkProductDescriptor) artifactDescList.get(0);
				artifactDescList.remove(0);
			}
			for (Iterator iter = artifactDescList.iterator(); iter.hasNext();) {
				WorkProductDescriptor artifactDesc = (WorkProductDescriptor) iter.next();
				found = false;
//				if (ProcessUtil.isContainedBy(candidate, artifactDesc, artifactDescriptors)) {
				if (LibraryEditUtil.getInstance().isContainedBy(artifactDesc.getWorkProduct(), candidate.getWorkProduct(), config)) {
					
					iter.remove();
				}
//				else if (ProcessUtil.isContainedBy(artifactDesc, candidate, artifactDescriptors)) {
				else if (LibraryEditUtil.getInstance().isContainedBy(artifactDesc.getWorkProduct(), candidate.getWorkProduct(), config)) {
					iter.remove();
					candidate = artifactDesc;

					found = true;
					break;
				}
			}
			if (!found || artifactDescList.isEmpty())
				topMostArtifactDescList.add(candidate);
			else if (artifactDescList.isEmpty())
					topMostArtifactDescList.add(candidate);		
		}

		List result = new ArrayList();
		for (Iterator ci = children.iterator(); ci.hasNext();) {
			Object child = ci.next();
			Object e = unwrap ? TngUtil.unwrap(child) : child;
			boolean selected = false;
			if (e instanceof WorkProductDescriptor) {
				WorkProductDescriptor wpd = ((WorkProductDescriptor) e);
				WorkProduct wp = wpd.getWorkProduct();
				if (!(wp instanceof Artifact)) {
					selected = true;
				} else if (topMostArtifactDescList.contains(wpd)) {
					selected = true;
				}
			} else {
				selected = true;
			}
			if (selected) {
				result.add(child);
			}
		}
		return result;
	}

	public static Activity findActivity(ProcessPackage procPackage) {
		if(procPackage instanceof ProcessComponent) {
			return ((ProcessComponent)procPackage).getProcess();
		}
		for (Iterator iterator = procPackage
				.getProcessElements().iterator(); iterator
				.hasNext();) {
			Object element = iterator.next();
			if (element instanceof Activity) {
				return ((Activity) element);
			}
		}
		return null;
	}	
	
	public static void fixBreakdonwElementOrderRecursively(Activity act) {
		Iterator iter = new AbstractTreeIterator(act) {

			/**
			 * Comment for <code>serialVersionUID</code>
			 */
			private static final long serialVersionUID = 3368261685663354478L;

			protected Iterator getChildren(Object object) {
				ArrayList activities = new ArrayList();
				for (Iterator iterator = ((Activity)object).getBreakdownElements().iterator(); iterator
						.hasNext();) {
					Object element = iterator.next();
					if(element instanceof Activity) {
						activities.add(element);
					}
				}
				return activities.iterator();
			}
			
		};
		while(iter.hasNext()) {
			fixBreakdownElementOrder((Activity)iter.next());
		}
	}

	/**
	 * @param activity
	 */
	public static void fixBreakdownElementOrder(Activity activity) {
		EList list = (EList) activity.getBreakdownElements();
		for (Iterator iter = new ArrayList(list).iterator(); iter.hasNext();) {
			BreakdownElement e = (BreakdownElement) iter.next();
			BreakdownElement succ = e.getPresentedAfter();
			if(succ != null && succ != e) {
				int succId = list.indexOf(succ);
				if(succId != -1) {
					int id = list.indexOf(e);
					if(id != succId - 1) {
						if(id < succId) {
							list.move(succId - 1, id);
						}
						else {
							list.move(id, succId);
						}
					}
					e.setPresentedAfter(null);
				}
			}
		}
	}

	/**
	 * Initializes item provider path of the given activity, the path from top process to the activity
	 * 
	 * @param activity
	 * @param adapterFactory
	 */
	public static void initializeItemProviderPath(Activity activity, AdapterFactory adapterFactory) {
		ITreeItemContentProvider ip = (ITreeItemContentProvider) adapterFactory.adapt(activity, ITreeItemContentProvider.class);
		IBSItemProvider bsIp = (IBSItemProvider) ip;
		Object top = bsIp.getTopItem();
		if(!(top instanceof Process) || !(((EObject)top).eContainer() instanceof ProcessComponent)) {
			// item provider tree of the owner's process is not initialized yet
			// initialize it.

			// get the activity path
			//
			ArrayList actPath = new ArrayList();
			for(activity = activity.getSuperActivities(); activity != null; activity = activity.getSuperActivities()) {
				actPath.add(0, activity);
			}
			for (Iterator iter = actPath.iterator(); iter.hasNext();) {
				Object act = iter.next();
				ip = (ITreeItemContentProvider) adapterFactory.adapt(act, ITreeItemContentProvider.class);
				ip.getChildren(act);
			}
		}
	}		

	/**
	 * Check if the given activity or any of its subactivities is a contributor or replacer
	 * 
	 * @param activity
	 * @return
	 */
	public static boolean hasContributorOrReplacer(Activity activity) {
		Iterator iter = new AbstractTreeIterator(activity) {

			/**
			 * Comment for <code>serialVersionUID</code>
			 */
			private static final long serialVersionUID = 0L;

			protected Iterator getChildren(Object object) {
				if(object instanceof Activity) {
					ArrayList children = new ArrayList();
					for (Iterator iterator = ((Activity)object).getBreakdownElements().iterator(); iterator
							.hasNext();) {
						Object element = iterator.next();
						if(element instanceof VariabilityElement) {
							children.add(element);
						}
					}
					return children.iterator();
				}
				return Collections.EMPTY_LIST.iterator();
			}
			
		};
		
		while(iter.hasNext()) {
			VariabilityElement ve = (VariabilityElement) iter.next();
			VariabilityElement base = ve.getVariabilityBasedOnElement();
			VariabilityType vType = ve.getVariabilityType();
			if(base != null && (vType == VariabilityType.CONTRIBUTES || vType == VariabilityType.REPLACES)) {
//				Process proc = TngUtil.getOwningProcess((BreakdownElement) base);
//				if(proc != process) {
//					return true;
//				}
				
				return true;
			}
		}
		
		return false;
	}	
	
	/**
	 * @param target
	 * @return
	 */
	public static boolean isTopProcess(Object target) {
		return target instanceof Process && ((Process)target).eContainer() instanceof ProcessComponent;
	}

	/**
	 * @param wpDescList
	 * @return
	 */
	public static WorkProductDescriptor getWorkProductDescriptor(Collection elements, WorkProduct wp) {
		for (Iterator iter = elements.iterator(); iter.hasNext();) {
			Object e = (Object) iter.next();
			if(e instanceof WorkProductDescriptor) {
				WorkProductDescriptor wpd = ((WorkProductDescriptor)e);
				if(wpd.getWorkProduct() == wp) {
					return wpd;
				}

			}
		}
		return null;
	}

	/**
	 * @param taskDescList
	 * @param task
	 * @return
	 */
	public static TaskDescriptor getTaskDescriptor(Collection elements, Task task) {
		for (Iterator iter = elements.iterator(); iter.hasNext();) {
			Object e = (Object) iter.next();
			if(e instanceof TaskDescriptor) {
				TaskDescriptor td = (TaskDescriptor) e;
				if(td.getTask() == task) {
					return td;
				}
			}
		}
		return null;
	}

	/**
	 * Gets the owning work breakdown element of a work order.
	 * 
	 * @param workOrder
	 *            a work order
	 * @return a work breakdown element that owns the work order or
	 *         <code>null</code>
	 */
	public static WorkBreakdownElement getOwner(WorkOrder workOrder) {
		return AssociationHelper.getSuccessor(workOrder);
	}

	public static ComposedBreakdownElementWrapperItemProvider getComposedWrapper(Object object) {
		while(!(object instanceof ComposedBreakdownElementWrapperItemProvider)
				&& object instanceof IWrapperItemProvider) {
			object = ((IWrapperItemProvider)object).getValue();
		}
		if(object instanceof ComposedBreakdownElementWrapperItemProvider) {
			return (ComposedBreakdownElementWrapperItemProvider) object;
		}
		return null;
	}
	
	/**
	 * Gets the display name for the given breakdown structure column ID. The
	 * breakdown structure column IDs are defined as constants in
	 * {@link IBSItemProvider} that start with COL_
	 * 
	 * @param columnName
	 *            one of the columns ID constants (COL_XXX) defined in
	 *            {@link IBSItemProvider}
	 * @return
	 */
	public static final String getColumnDisplayName(String columnId) {
		try {
			return LibraryEditPlugin.INSTANCE.getString("BS_Column_" + columnId); //$NON-NLS-1$ 
		} catch (MissingResourceException e) {
		}
		return columnId;
	}
	
	
	/**
	 * Get list of task descriptors under selected activities
	 * @param selection
	 * @return
	 */
	public static List getTaskDescriptors(List selection) {
		List taskDescriptors = new ArrayList();
		ExposedAdapterFactory adapterFactory = (ExposedAdapterFactory) TngAdapterFactory.INSTANCE
				.getWBS_ComposedAdapterFactory();
		for (Iterator itor = selection.iterator(); itor.hasNext();) {
			Object itorObj = itor.next();
			if (itorObj instanceof Activity
					|| itorObj instanceof ActivityWrapperItemProvider) {
				getTaskDescriptors(adapterFactory, itorObj, taskDescriptors);
			}
			if (itorObj instanceof TaskDescriptor
					|| itorObj instanceof TaskDescriptorWrapperItemProvider) {
				if (!taskDescriptors.contains(itorObj))
					taskDescriptors.add(itorObj);
			}
		}
		return taskDescriptors;
	}
	
	
	private static void getTaskDescriptors(AdapterFactory adapterFactory, Object act,
			List taskDescriptors) {
		Object list = null;
		if (act instanceof Activity) {
			ItemProviderAdapter adapter = (ItemProviderAdapter) adapterFactory
					.adapt(act, ITreeItemContentProvider.class);
			// get children
			list = adapter.getChildren(act);
		} else if (act instanceof ActivityWrapperItemProvider) {
			list = ((WrapperItemProvider) act).getChildren(act);
		}
		if (list != null && list instanceof List) {
			List children = (List) list;
			for (Iterator childIter = children.iterator(); childIter.hasNext();) {
				Object obj = childIter.next();
				if (obj instanceof Activity || obj instanceof ActivityWrapperItemProvider) {
					getTaskDescriptors(adapterFactory, obj, taskDescriptors);
				}
				
				if (obj instanceof TaskDescriptor
						|| obj instanceof TaskDescriptorWrapperItemProvider) {
					if (!taskDescriptors.contains(obj))
						taskDescriptors.add(obj);
				}
			}
		}
	}

	/**
	 * Checks if the given object is a descriptor or its wrapper of rolled-up activity.
	 * 
	 * @param o
	 * @param adapterFactory
	 * @return
	 */
	public static boolean isRolledUpDescriptor(Object o,
			AdapterFactory adapterFactory) {
		if(adapterFactory != null && TngUtil.unwrap(o) instanceof Descriptor) {
			ITreeItemContentProvider adapter = (ITreeItemContentProvider) adapterFactory.adapt(o, ITreeItemContentProvider.class);
			Object parent = adapter.getParent(o);
			if(TngUtil.unwrap(parent) instanceof Activity) {
				Object parentAdapter = adapterFactory.adapt(parent, ITreeItemContentProvider.class);
				return parentAdapter instanceof IBSItemProvider 
				&& ((IBSItemProvider)parentAdapter).isRolledUp();
			}			
		}
		return false;
	}

//	/**
//	 * Creates a deep copy of a process to the specified process package.
//	 * 
//	 * @param monitor
//	 * @param process
//	 * @prarm newProcessName
//	 * @param deepCopyConfig
//	 * @param targetPackage
//	 * @return Process the newly created process
//	 */
//	public static org.eclipse.epf.uma.Process deepCopy(
//			IProgressMonitor monitor, 
//			org.eclipse.epf.uma.Process process, 
//			String newProcessName,
//			MethodConfiguration deepCopyConfig, 
//			ProcessPackage targetPackage) {
//		CopyHelper helper = new CopyHelper();	
//		try {
//			return deepCopy(monitor, process, newProcessName, deepCopyConfig, targetPackage, helper);
//		}
//		finally {
//			helper.clear();
//		}
//	}
		
	public static Process deepCopy(
			IProgressMonitor monitor, 
			org.eclipse.epf.uma.Process process, 
			String newProcessName,
			MethodConfiguration deepCopyConfig, 
			ProcessPackage targetPackage,
			CopyHelper copyHelper,
			IConfigurator configurator, boolean handleAutoSyn) {
		
		if (handleAutoSyn) {
			if (deepCopyConfig instanceof Scope) {
				MethodConfiguration tempConfig = UmaFactory.eINSTANCE.createMethodConfiguration();
				for (MethodPlugin plugin : deepCopyConfig.getMethodPluginSelection()) {
					tempConfig.getMethodPluginSelection().add(plugin);
					tempConfig.getMethodPackageSelection().addAll(UmaUtil.getAllMethodPackages(plugin));
				}
				deepCopyConfig = tempConfig;
				configurator.setMethodConfiguration(deepCopyConfig);
			}
						
			IRealizationManager mgr = LibraryEditUtil.getInstance()
					.getRealizationManager(deepCopyConfig);
			mgr.updateProcessModel(process);
		}
		
		Process proc = deepCopy_(monitor, process, newProcessName,
				deepCopyConfig, targetPackage, copyHelper, configurator, handleAutoSyn);
		
		return proc;
	}
	
	private static  Process deepCopy_(
			IProgressMonitor monitor, 
			org.eclipse.epf.uma.Process process, 
			String newProcessName,
			MethodConfiguration deepCopyConfig, 
			ProcessPackage targetPackage,
			CopyHelper copyHelper,
			IConfigurator configurator, boolean handleAutoSyn) {		
		// if the targetPackage is null, use the same package of the source process
		if ( targetPackage == null ) {
			targetPackage = (ProcessPackage)process.eContainer().eContainer();
		}
		
		//		new deep-copied process's reference
		org.eclipse.epf.uma.Process newProcess = null;
	
		if(monitor == null) {
			monitor = new NullProgressMonitor();
		}

		ProcessDeepCopyCommand cmd = new ProcessDeepCopyCommand(process, newProcessName, 
				copyHelper, deepCopyConfig, targetPackage, monitor,configurator);
		try {
			cmd.execute();
			Collection result = cmd.getResult();
			if(!result.isEmpty()) {
				newProcess = (org.eclipse.epf.uma.Process) result.toArray()[0];
				// fix breakdown element order of all activities
				//
				fixBreakdonwElementOrderRecursively(newProcess);	
				cmd.copySuppressionStates();
			}
		}
		finally {
			cmd.dispose();
		}
		ActivityHandler.fixGuidReferences(copyHelper);	
		
		return newProcess;
	}
	
	public static boolean accept(Activity act, IFilter processFilter, boolean checkOwningProcess) {
		if(processFilter == null) {
			return true;
		}
		Activity base = (Activity) act.getVariabilityBasedOnElement();
		VariabilityType type = act.getVariabilityType();
		if (base == null)
		{
			if(checkOwningProcess) {
				return processFilter.accept(TngUtil.getOwningProcess(act));
			}
			else {
				return true;
			}
		}
		else if(type == VariabilityType.EXTENDS || type == VariabilityType.LOCAL_CONTRIBUTION || type == VariabilityType.LOCAL_REPLACEMENT)
		{
			// check owning process of base activity only for extends and local contribution/replacement
			//
			boolean ret;
			do {
				ret = processFilter.accept(TngUtil.getOwningProcess(base));
				type = base.getVariabilityType();
				base = (Activity) base.getVariabilityBasedOnElement();
			} while (ret
					&& base != null
					&& (type == VariabilityType.EXTENDS
							|| type == VariabilityType.LOCAL_CONTRIBUTION || type == VariabilityType.LOCAL_REPLACEMENT));
			return ret;
		}
		else {
			return true;
		}
	}
	
	/**
	 * Returns the Process to which an activity belongs
	 * @param activity
	 * @return
	 */
	public static Process getProcess(Activity activity) {
		ProcessComponent procComp = UmaUtil.getProcessComponent(activity);
		if (procComp != null) {
			org.eclipse.epf.uma.Process proc = procComp.getProcess();
			return proc;
		}
		return null;
	}
	
	/**
	 * Returns the Process to which a breakdownElement belongs
	 * @param activity
	 * @return
	 */
	public static Process getProcessForBreakdownElement(BreakdownElement breakdownElement) {
		if (breakdownElement instanceof Activity) {
			return getProcess((Activity) breakdownElement);
		}
		Activity superAct = breakdownElement.getSuperActivities();		
		return superAct == null ? null : getProcess(superAct);
	}
	
	/**
	 * Finds WorkOrder instance for the specified <code>inheritedChild</code>
	 * of the specified parent activity with the given predecessor.
	 * 
	 * If the inherited (read-only) work breakdown elements of an activity are
	 * allowed to have additional predecessors, the information about these
	 * predecessors will be stored in WorkOrder objects that will be saved in
	 * process package of the activity. The GUID of inherited children will be
	 * stored in property (@link {@link MethodElementProperty} named "successor" 
	 * ({@link MethodElementPropertyHelper#WORK_ORDER__SUCCESSOR} of the WorkOrder.
	 * 
	 * @param parent
	 * @param inheritedChild
	 * @param predecessor
	 * @return
	 */
	public static WorkOrder findWorkOrder(Activity parent, WorkBreakdownElement inheritedChild, WorkBreakdownElement predecessor)  {
		ProcessPackage pkg = (ProcessPackage) parent.eContainer();
		for (Object element : pkg.getProcessElements()) {
			if(element instanceof WorkOrder) {
				WorkOrder workOrder = (WorkOrder) element;
				if(workOrder.getPred() == predecessor) {
					MethodElementProperty prop = MethodElementPropertyHelper.getProperty(workOrder, MethodElementPropertyHelper.WORK_ORDER__SUCCESSOR);
					if(prop != null && inheritedChild.getGuid().equals(prop.getValue())) {
						return workOrder;
					}
				}
			}
		}
		return null;
	}
	
	public static WorkOrder createDefaultWorkOrderForInheritedChild(Activity parent, WorkBreakdownElement inheritedChild, WorkBreakdownElement predecessor) {
		WorkOrder workOrder = UmaFactory.eINSTANCE.createWorkOrder();
		workOrder.setPred(predecessor);
		MethodElementPropertyHelper.setProperty(workOrder, MethodElementPropertyHelper.WORK_ORDER__SUCCESSOR, inheritedChild.getGuid());
		return workOrder;
	}

	public static boolean isCustomWorkOrder(WorkOrder object) {
		return MethodElementPropertyHelper.getProperty(object, MethodElementPropertyHelper.WORK_ORDER__SUCCESSOR) != null;
	}
	
	public static boolean isCustomWorkOrderOf(WorkOrder wo, WorkBreakdownElement e) {
		MethodElementProperty prop = MethodElementPropertyHelper.getProperty(wo, MethodElementPropertyHelper.WORK_ORDER__SUCCESSOR);
		return prop != null && e.getGuid().equals(prop.getValue());
	}
	
	public static boolean processDeepcopyDiagarm = false;
	public static boolean isProcessDeepcopyDiagarm() {
		return processDeepcopyDiagarm;
	}

	public static void setProcessDeepcopyDiagarm(boolean processDeepcopyDiagarm) {
		ProcessUtil.processDeepcopyDiagarm = processDeepcopyDiagarm;
	}
	
	
	
	
}