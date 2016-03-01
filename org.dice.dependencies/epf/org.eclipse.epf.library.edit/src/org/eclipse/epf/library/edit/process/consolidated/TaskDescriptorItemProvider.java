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
package org.eclipse.epf.library.edit.process.consolidated;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.emf.edit.provider.WrapperItemProvider;
import org.eclipse.epf.library.edit.IConfigurator;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.meta.TypeDefUtil;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.process.IBreakdownElementWrapperItemProviderFactory;
import org.eclipse.epf.library.edit.util.Comparators;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.PropUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.util.ExtendedReference;
import org.eclipse.epf.uma.util.ModifiedTypeMeta;


/**
 * @author Phong Nguyen Le
 * @authos Shilpa Toraskar
 * @since 1.0
 */
public class TaskDescriptorItemProvider extends
		org.eclipse.epf.library.edit.process.TaskDescriptorItemProvider {

	protected Collection cachedChildren;

	/**
	 * @param adapterFactory
	 * @param delegateItemProvider
	 */
	public TaskDescriptorItemProvider(AdapterFactory adapterFactory,
			ItemProviderAdapter delegateItemProvider) {
		super(adapterFactory, delegateItemProvider);
	}

	/**
	 * @see com.ibm.library.edit.process.BreakdownElementItemProvider#collectNewChildDescriptors(java.util.Collection,
	 *      java.lang.Object)
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
		newChildDescriptors.add(new CommandParameter(target,
				UmaPackage.eINSTANCE.getTaskDescriptor_PerformedPrimarilyBy(),
				UmaFactory.eINSTANCE.createRoleDescriptor()));

		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getTaskDescriptor_AdditionallyPerformedBy(),
				UmaFactory.eINSTANCE.createRoleDescriptor()));

		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getTaskDescriptor_AssistedBy(), UmaFactory.eINSTANCE
				.createRoleDescriptor()));

		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getTaskDescriptor_MandatoryInput(), UmaFactory.eINSTANCE
				.createWorkProductDescriptor()));

		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getTaskDescriptor_OptionalInput(), UmaFactory.eINSTANCE
				.createWorkProductDescriptor()));
		
		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getTaskDescriptor_ExternalInput(), UmaFactory.eINSTANCE
				.createWorkProductDescriptor()));
		
		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getTaskDescriptor_Output(), UmaFactory.eINSTANCE
				.createWorkProductDescriptor()));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BreakdownElementItemProvider#getChildrenFeatures(java.lang.Object)
	 */
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			childrenFeatures = new ArrayList();
			childrenFeatures.add(UmaPackage.eINSTANCE
					.getTaskDescriptor_PerformedPrimarilyBy());
			childrenFeatures.add(UmaPackage.eINSTANCE
					.getTaskDescriptor_AdditionallyPerformedBy());
			childrenFeatures.add(UmaPackage.eINSTANCE
					.getTaskDescriptor_AssistedBy());
			childrenFeatures.add(UmaPackage.eINSTANCE
					.getTaskDescriptor_MandatoryInput());
			childrenFeatures.add(UmaPackage.eINSTANCE
					.getTaskDescriptor_OptionalInput());
			childrenFeatures.add(UmaPackage.eINSTANCE
					.getTaskDescriptor_ExternalInput());
			childrenFeatures.add(UmaPackage.eINSTANCE
					.getTaskDescriptor_Output());
		}
		return childrenFeatures;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#isWrappingNeeded(java.lang.Object)
	 */
	protected boolean isWrappingNeeded(Object object) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getCreateChildText(java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.util.Collection)
	 */
	public String getCreateChildText(Object owner, Object feature,
			Object child, Collection selection) {
		return getFeatureText(feature);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getCreateChildImage(java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.util.Collection)
	 */
	public Object getCreateChildImage(Object owner, Object feature,
			Object child, Collection selection) {
		Object img = TngUtil.getImage(child);
		return img != null ? img : super.getCreateChildImage(owner, feature, child, selection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#createWrapper(org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object, int)
	 */
	protected Object createWrapper(EObject object, EStructuralFeature feature,
			Object value, int index) {
		BreakdownElementWrapperItemProvider wrapper = (BreakdownElementWrapperItemProvider) IBreakdownElementWrapperItemProviderFactory.INSTANCE
				.createWrapper(value, object, feature, index, adapterFactory);
		wrapper.setReadOnly(false);
		return wrapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BreakdownElementItemProvider#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		try {
			updateChildren(notification);
		} catch (Exception e) {
			e.printStackTrace();
		}

		switch (notification.getFeatureID(TaskDescriptor.class)) {
		case UmaPackage.TASK_DESCRIPTOR__PERFORMED_PRIMARILY_BY:
		case UmaPackage.TASK_DESCRIPTOR__ADDITIONALLY_PERFORMED_BY:
		case UmaPackage.TASK_DESCRIPTOR__ASSISTED_BY:
		case UmaPackage.TASK_DESCRIPTOR__MANDATORY_INPUT:
		case UmaPackage.TASK_DESCRIPTOR__OPTIONAL_INPUT:
		case UmaPackage.TASK_DESCRIPTOR__EXTERNAL_INPUT:
		case UmaPackage.TASK_DESCRIPTOR__OUTPUT:
			refreshChildren(notification);
		}

		try {
			super.notifyChanged(notification);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static void refreshChildren(ItemProviderAdapter ipa, Notification notification) {		
		if (ProcessUtil.isRefreshRequired(notification, null)) {
			ipa.fireNotifyChanged(new ViewerNotification(notification, notification
					.getNotifier(), true, false));

			AdapterFactory adapterFactory = ipa.getAdapterFactory();
			
			if(notification.getNotifier() instanceof BreakdownElement) {
				// need to refresh children of parent activity
				//
				Activity parent = ((BreakdownElement)notification.getNotifier()).getSuperActivities();
				Object adapter = adapterFactory.adapt(parent,
						ITreeItemContentProvider.class);
				if (adapter instanceof ItemProviderAdapter) {
					((ItemProviderAdapter) adapter)
					.fireNotifyChanged(new ViewerNotification(notification,
							parent, true, false));
				}

				// refresh extenders and local contributors of the parent
				//
				if(parent instanceof VariabilityElement) {
					for (Iterator iter = TngUtil.getGeneralizers(parent); iter.hasNext();) {
						VariabilityElement ve = (VariabilityElement) iter.next();
						if(ve.getVariabilityType() == VariabilityType.EXTENDS
								|| ve.getVariabilityType() == VariabilityType.LOCAL_CONTRIBUTION) {
							adapter = TngUtil.getAdapterByType(ve, adapterFactory);
							if (adapter instanceof ItemProviderAdapter) {
								((ItemProviderAdapter) adapter)
								.fireNotifyChanged(new ViewerNotification(notification,
										ve, true, false));
							}
						}
					}
				}
			}
		}
	}

	/**
	 * @param notification
	 */
	private void refreshChildren(Notification notification) {
		refreshChildren(this, notification);
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

	public Collection getChildren(Object obj) {
		childrenStoreMap = null;
		Collection children = super.getChildren(obj);
		List newChildren = new ArrayList();
		List primaryPerformers = new ArrayList();
		List additionalPerformers = new ArrayList();
		List assitedBy = new ArrayList();
		List mandatoryInput = new ArrayList();
		List externalInput = new ArrayList();
		List optionalInput = new ArrayList();
		List output = new ArrayList();

		IFilter filter = getFilter(obj);
		int sz = children.size();

		for (Iterator itor = children.iterator(); itor.hasNext();) {
			Object o = itor.next();
			if (o instanceof WrapperItemProvider) {
				Object child = ((WrapperItemProvider) o).getValue();
				
				
				if ((filter != null) && (filter.accept(child))) {
					if (((WrapperItemProvider) o).getFeature().equals(
							UmaPackage.eINSTANCE
									.getTaskDescriptor_PerformedPrimarilyBy())) {
						primaryPerformers.add(o);
					} else if (((WrapperItemProvider) o)
							.getFeature()
							.equals(
									UmaPackage.eINSTANCE
											.getTaskDescriptor_AdditionallyPerformedBy())) {
						additionalPerformers.add(o);
					} else if (((WrapperItemProvider) o).getFeature()
							.equals(
									UmaPackage.eINSTANCE
											.getTaskDescriptor_AssistedBy())) {
						assitedBy.add(o);
					} else if (((WrapperItemProvider) o).getFeature().equals(
							UmaPackage.eINSTANCE
									.getTaskDescriptor_MandatoryInput())) {
						mandatoryInput.add(o);
					} else if (((WrapperItemProvider) o).getFeature().equals(
							UmaPackage.eINSTANCE
									.getTaskDescriptor_ExternalInput())) {
						externalInput.add(o);
					} else if (((WrapperItemProvider) o).getFeature().equals(
							UmaPackage.eINSTANCE
									.getTaskDescriptor_OptionalInput())) {
						optionalInput.add(o);
					} else if (((WrapperItemProvider) o).getFeature().equals(
							UmaPackage.eINSTANCE.getTaskDescriptor_Output())) {
						output.add(o);
					}
				}
			}
		}

		// sort for all children
		Collections.sort(primaryPerformers, Comparators.PRESENTATION_NAME_COMPARATOR);
		Collections.sort(additionalPerformers, Comparators.PRESENTATION_NAME_COMPARATOR);
		Collections.sort(assitedBy, Comparators.PRESENTATION_NAME_COMPARATOR);
		Collections.sort(externalInput, Comparators.PRESENTATION_NAME_COMPARATOR);
		Collections.sort(mandatoryInput, Comparators.PRESENTATION_NAME_COMPARATOR);
		Collections.sort(optionalInput, Comparators.PRESENTATION_NAME_COMPARATOR);
		Collections.sort(output, Comparators.PRESENTATION_NAME_COMPARATOR);
		
		newChildren.addAll(primaryPerformers);
		newChildren.addAll(additionalPerformers);
		newChildren.addAll(assitedBy);
		newChildren.addAll(externalInput);
		newChildren.addAll(mandatoryInput);
		newChildren.addAll(optionalInput);
		newChildren.addAll(output);
		
		MethodConfiguration config = null;
		if (filter instanceof IConfigurator) {
			config = ((IConfigurator) filter).getMethodConfiguration();
		}
		newChildren = removeSubartifactsFromChildren(newChildren, true, config);
		updateCachedChildren(newChildren);
		
		if (obj instanceof TaskDescriptor) {
			TaskDescriptor td = (TaskDescriptor) obj;
			Task task = td.getTask();
			if (task != null) {
				ModifiedTypeMeta meta = TypeDefUtil.getMdtMeta(task);
				if (meta != null) {
					for (ExtendedReference eRef : meta.getReferences()) {
						if (ExtendedReference.Roles.equals(eRef.getContributeTo()) ||
								ExtendedReference.WorkProducts.equals(eRef.getContributeTo())) {
							List list = PropUtil.getPropUtil().getExtendedReferenceList(td, eRef, false);
							int ix = 0;
							for (Object item : list) {
								 Object wrapped = wrap(td, eRef.getReference(), item, ix++);
								 newChildren.add(wrapped);
							}
						}
					}
				}
			}				
		}
		
		return newChildren;
	}
	
	protected List removeSubartifactsFromChildren(Collection children, boolean unwrap, MethodConfiguration config) {
		return ProcessUtil.removeSubartifactsFromChildren(null, children, unwrap);
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

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.ICachedChildrenItemProvider#getChildrenFromCache()
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

	public void dispose() { 
		if(cachedChildren != null) {
			cachedChildren.clear();
			cachedChildren = null;
		}
		super.dispose();
	}
}
