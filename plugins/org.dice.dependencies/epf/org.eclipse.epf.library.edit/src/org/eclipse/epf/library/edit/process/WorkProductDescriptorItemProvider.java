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
package org.eclipse.epf.library.edit.process;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.CopyCommand.Helper;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.epf.library.edit.ICachedChildrenItemProvider;
import org.eclipse.epf.library.edit.IConfigurator;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.process.command.WorkProductDescriptorCreateCopyCommand;
import org.eclipse.epf.library.edit.process.consolidated.ActivityItemProvider;
import org.eclipse.epf.library.edit.util.Comparators;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Deliverable;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.util.UmaUtil;


/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class WorkProductDescriptorItemProvider extends DescriptorItemProvider
implements ICachedChildrenItemProvider
{
	protected Collection cachedChildren;
	
	/**
	 * @param adapterFactory
	 */
	public WorkProductDescriptorItemProvider(AdapterFactory adapterFactory,
			ItemProviderAdapter delegateItemProvider) {
		super(adapterFactory, delegateItemProvider);
	}

	public Activity getActivity(Object object) {
		for (Object parent = getParent(object); parent != null;) {
			if (parent instanceof Activity)
				return (Activity) parent;
			AdapterFactory aFactory = getRootAdapterFactory();
			ItemProviderAdapter adapter = (ItemProviderAdapter) aFactory.adapt(
					parent, ITreeItemContentProvider.class);
			parent = adapter.getParent(parent);
		}
		return null;
	}

	private Object getDescriptor(Activity activity, Artifact artifact) {
		if (activity == null)
			return null;
		List list = activity.getBreakdownElements();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			Object obj = list.get(i);
			if ((obj instanceof WorkProductDescriptor)
					&& (artifact == ((WorkProductDescriptor) obj)
							.getWorkProduct())) {
				return obj;
			}
		}
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildrenFeatures(java.lang.Object)
	 */
	public Collection getChildrenFeatures(Object object) {
		// if (childrenFeatures == null)
		// {
		// childrenFeatures = new ArrayList();
		// childrenFeatures.add(UmaPackage.eINSTANCE.getWorkProductDescriptor_DeliverableParts());
		// }
		// return childrenFeatures;

		return Collections.EMPTY_LIST;
	}
	
	private void updateCachedChildren(Collection children) {
//		if(cachedChildren == null) {
//			cachedChildren = new ArrayList(children);
//		}
//		else {
//			cachedChildren.clear();
//			cachedChildren.addAll(children);
//		}
	}
	
	protected void addContainedArtifactDescriptors(WorkProductDescriptor wpDesc, Collection children, MethodConfiguration config) {
		Activity activity = UmaUtil.getParentActivity(wpDesc);
		if(activity != null) {
			Artifact artifact = (Artifact) wpDesc.getWorkProduct();
			List list = config == null ? null : LibraryEditUtil.getInstance().calc0nFeatureValue(artifact, UmaPackage.eINSTANCE.getArtifact_ContainedArtifacts(), config);
			if (list == null) {
				list = artifact.getContainedArtifacts();
			}
			int size = list.size();
			if(size > 0) {
				ArrayList artifactDescriptors = new ArrayList();
				for (int i = 0; i < size; i++) {
					Object descriptor = getDescriptor(activity, (Artifact) list
							.get(i));
					if (descriptor != null) {
						artifactDescriptors.add(descriptor);
					}
				}
				if(!artifactDescriptors.isEmpty()) {
					Collections.sort(artifactDescriptors, Comparators.PRESENTATION_NAME_COMPARATOR);
					children.addAll(artifactDescriptors);
				}
			}
		}
	}
	
	private IFilter getFilter(Object obj) {
		IFilter filter = null;
		Object parent = getParent(obj);
		IBSItemProvider adapter = (IBSItemProvider) getRootAdapterFactory()
				.adapt(parent, ITreeItemContentProvider.class);
		if (adapter instanceof ActivityItemProvider) {
			filter = ((ActivityItemProvider) adapter).getFilter();
		}

		return filter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		WorkProductDescriptor wpDesc = (WorkProductDescriptor) object;
		
		// don't show the children of the non-deliverable work product descriptor
		// that is deliverable part of a deliverable work product descriptor
		//
		if(!(wpDesc.getWorkProduct() instanceof Deliverable)) {
			Object parent = TngUtil.unwrap(getParent(object));
			if(parent instanceof WorkProductDescriptor
					&& ((WorkProductDescriptor)parent).getWorkProduct() instanceof Deliverable) {				
				updateCachedChildren(Collections.EMPTY_LIST);
				return Collections.EMPTY_LIST;
			}
		}
		
		Collection children = super.getChildren(object);

		// add deliverable parts
		//
		if(!wpDesc.getDeliverableParts().isEmpty()) {
			for (Iterator iter = wpDesc.getDeliverableParts().iterator(); iter
			.hasNext();) {
				WorkProductDescriptor desc = (WorkProductDescriptor) iter.next();
				if (desc.getSuperActivities() == null || ProcessUtil.isSynFree()) {
					children.add(desc);
				}
			}
		}
		
		IFilter filter = getFilter(object);
		MethodConfiguration config = null;
		if (filter == null) {
			filter = ProcessUtil.getFilter(adapterFactory);
		}
		if (filter instanceof IConfigurator) {
			config = ((IConfigurator) filter).getMethodConfiguration();
		}
		// get descriptors of contained artifacts
		//		
		if (wpDesc.getWorkProduct() instanceof Artifact) {
			addContainedArtifactDescriptors(wpDesc, children, config);
		}

		// set parent
		//
		AdapterFactory aFactory = getRootAdapterFactory();

		for (Iterator iter = children.iterator(); iter.hasNext();) {
			IBSItemProvider adapter = (IBSItemProvider) aFactory.adapt(iter
					.next(), ITreeItemContentProvider.class);
			adapter.setParent(object);
		}
		
		updateCachedChildren(children);
		
		return children;
	}
	
	/**
	 * Gets children from cache, fill the children cache if it is not initialized yet
	 * 
	 * @return
	 */
	public Collection getChildrenFromCache() {
		if(cachedChildren == null) {
			getChildren(target);
		}
		return cachedChildren;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.ICachedChildrenItemProvider#getRollupChildrenFromCache()
	 */
	public Collection getRollupChildrenFromCache() {
		return Collections.EMPTY_LIST;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.process.BreakdownElementItemProvider#dispose()
	 */
	public void dispose() {
		if(cachedChildren != null) {
			cachedChildren.clear();
			cachedChildren = null;
		}
		
		super.dispose();
	}

	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(WorkProductDescriptor.class)) {
//		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__MANDATORY_INPUT_TO:
//		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__OPTIONAL_INPUT_TO:
//		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__EXTERNAL_INPUT_TO:
//		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__OUTPUT_FROM:
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__ACTIVITY_ENTRY_STATE:
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__ACTIVITY_EXIT_STATE:
			fireNotifyChanged(new ViewerNotification(notification, notification
					.getNotifier(), false, true));
			return;
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS:
			List elements = ProcessUtil.getAffectedElements(notification, null);
			int eventType = notification.getEventType();
			boolean refresh = false;
			for (Iterator iter = elements.iterator(); iter.hasNext();) {
				BreakdownElement e = (BreakdownElement) iter.next();
				if (eventType == Notification.ADD
						|| eventType == Notification.ADD_MANY) {
					ProcessUtil.addToContainer(e, this, false);
				}
				if (e.getSuperActivities() != null) {
					fireNotifyChanged(new ViewerNotification(notification, e,
							false, true));
				} else {
					refresh = true;
				}
			}

			if (refresh) {
				fireNotifyChanged(new ViewerNotification(notification,
						notification.getNotifier(), true, false));
			}

			return;
		case UmaPackage.DESCRIBABLE_ELEMENT__PRESENTATION_NAME:
			WorkProductDescriptor wpd = (WorkProductDescriptor) notification
					.getNotifier();
			fireNotifyChanged(new ViewerNotification(notification, wpd, false,
					true));
			if (wpd.getWorkProduct() instanceof Deliverable) {
				for (Iterator iter = wpd.getDeliverableParts().iterator(); iter
						.hasNext();) {
					Object descriptor = iter.next();
					fireNotifyChanged(new ViewerNotification(notification,
							descriptor, false, true));
				}
			}
			return;
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__SUPPRESSED:
			Object notifier = notification.getNotifier();
			WorkProduct wp;
			boolean contentRefresh = notifier instanceof WorkProductDescriptor
				&& ((wp = ((WorkProductDescriptor)notifier).getWorkProduct()) instanceof Artifact
						|| wp instanceof Deliverable);
			fireNotifyChanged(new ViewerNotification(notification, notification
					.getNotifier(), contentRefresh, true));
			return;
		}

		super.notifyChanged(notification);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#setAttribute(java.lang.Object,
	 *      java.lang.String, java.lang.String)
	 */
	public void setAttribute(Object object, String prop, String txt) {
		WorkProductDescriptor wpd = (WorkProductDescriptor) object;

		if (prop == IBSItemProvider.COL_ENTRY_STATE) {
			wpd.setActivityEntryState(txt);
		} else if (prop == IBSItemProvider.COL_EXIT_STATE) {
			wpd.setActivityExitState(txt);
		} else {
			super.setAttribute(object, prop, txt);
		}
	}

	// private boolean isTopLevelArtifactInActivity(Activity activity, Object
	// obj)
	// {
	// AdapterFactory aFactory = TngUtil.getBestAdapterFactory(adapterFactory);
	//        
	// BSActivityItemProvider adapter = (BSActivityItemProvider)
	// aFactory.adapt(activity, ITreeItemContentProvider.class);
	// Collection children = adapter.getChildren(activity);
	// if (children.contains(obj))
	// {
	// //System.out.println("child - " + obj);
	// return true;
	// }
	// return false;
	// }

	public Collection getEClasses() {
		return ProcessUtil.getPBSEclasses();
	}

	public void moveUp(Object obj, IActionManager actionMgr) {
		Object parent = this.getParent(obj);
		if ((parent != null) && (parent instanceof Activity)) {
			TngUtil.moveUp((Activity) parent, obj, getEClasses(), actionMgr);
		}
		if ((parent != null) && (parent instanceof WorkProductDescriptor)) {
			WorkProduct wp = ((WorkProductDescriptor) parent).getWorkProduct();
			if (wp instanceof Artifact) {
				Artifact artifact = (Artifact) wp;
				List list = artifact.getContainedArtifacts();
				WorkProduct objWP = ((WorkProductDescriptor) obj)
						.getWorkProduct();

				int location = 0;
				// List newList = new ArrayList();
				for (int i = 0; i < list.size(); i++) {
					if (objWP.equals(list.get(i))) {
						location = i;
						break;
					}
				}

				((EList) artifact.getContainedArtifacts()).move(location,
						location - 1);
			}

			Activity activity = this.getActivity(obj);
			TngUtil.moveUp(activity, obj, getEClasses(), actionMgr);
		}
	}

	public void moveDown(Object obj, IActionManager actionMgr) {
		Object parent = this.getParent(obj);
		if ((parent != null) && (parent instanceof Activity)) {
			TngUtil.moveDown((Activity) parent, obj, getEClasses(), actionMgr);
		}
		if ((parent != null) && (parent instanceof WorkProductDescriptor)) {
			WorkProduct wp = ((WorkProductDescriptor) parent).getWorkProduct();
			if (wp instanceof Artifact) {
				Artifact artifact = (Artifact) wp;
				List list = artifact.getContainedArtifacts();
				WorkProduct objWP = ((WorkProductDescriptor) obj)
						.getWorkProduct();

				int location = 0;
				// List newList = new ArrayList();
				for (int i = 0; i < list.size(); i++) {
					if (objWP.equals(list.get(i))) {
						location = i;
						break;
					}
				}

				((EList) artifact.getContainedArtifacts()).move(location,
						location + 1);
			}

			Activity activity = this.getActivity(obj);
			TngUtil.moveDown(activity, obj, getEClasses(), actionMgr);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BreakdownElementItemProvider#createCreateCopyCommand(org.eclipse.emf.edit.domain.EditingDomain,
	 *      org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.edit.command.CopyCommand.Helper)
	 */
	protected Command createCreateCopyCommand(EditingDomain domain,
			EObject owner, Helper helper) {
		return new WorkProductDescriptorCreateCopyCommand(domain, owner, helper);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#factorRemoveCommand(org.eclipse.emf.edit.domain.EditingDomain, org.eclipse.emf.edit.command.CommandParameter)
	 */
	protected Command factorRemoveCommand(EditingDomain domain, CommandParameter commandParameter) {
		if(!(commandParameter.getOwner() instanceof Activity)) {
			Activity act = getActivity(target);
			if(act != null) {
				IEditingDomainItemProvider adapter = (IEditingDomainItemProvider) adapterFactory.adapt(act, IEditingDomainItemProvider.class);
				commandParameter.setOwner(act);
				return adapter.createCommand(act, domain, RemoveCommand.class, commandParameter);				
			}
		}
		return super.factorRemoveCommand(domain, commandParameter);
	}
	
	public String getAttribute(Object object, String property) {
		// EPF bug#: 144820 Some nested work product descriptors show wrong model info in work product usage view
		// for defect, override the method, previously DescriptorItemProvide.getAttribute use to have this code, moved to here. 
		if (property == IBSItemProvider.COL_MODEL_INFO) {
			StringBuffer modelInfo = new StringBuffer();
				if (object instanceof WorkProductDescriptor) {
//					ProcessUtil.getModelInfoForWorkProductDescriptor(modelInfo,
//							(WorkProductDescriptor) object);
					ProcessUtil.getWPDModelInfo(modelInfo, object, this);
				}
//			}
			return modelInfo.toString(); 
		}
		return super.getAttribute(object, property);
	}
	
	 protected boolean hasChildren(Object object, boolean optimized)
	  {
		boolean b =  super.hasChildren(object, optimized);
		return b;
	  }


}
