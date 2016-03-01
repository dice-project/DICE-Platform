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
import org.eclipse.emf.edit.provider.WrapperItemProvider;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.meta.TypeDefUtil;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.process.IBreakdownElementWrapperItemProviderFactory;
import org.eclipse.epf.library.edit.util.Comparators;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.PropUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.epf.uma.util.ExtendedReference;
import org.eclipse.epf.uma.util.ModifiedTypeMeta;


/**
 * RoleDescriptor Item provider for consolidated view
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class RoleDescriptorItemProvider extends
		org.eclipse.epf.library.edit.process.RoleDescriptorItemProvider {

	/**
	 * @param adapterFactory
	 * @param delegateItemProvider
	 */
	public RoleDescriptorItemProvider(AdapterFactory adapterFactory,
			ItemProviderAdapter delegateItemProvider) {
		super(adapterFactory, delegateItemProvider);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BreakdownElementItemProvider#collectNewChildDescriptors(java.util.Collection,
	 *      java.lang.Object)
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
		newChildDescriptors.add(new CommandParameter(target,
				UmaPackage.eINSTANCE.getRoleDescriptor_ResponsibleFor(),
				UmaFactory.eINSTANCE.createWorkProductDescriptor()));

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
					.getRoleDescriptor_ResponsibleFor());
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
		updateChildren(notification);

		switch (notification.getFeatureID(RoleDescriptor.class)) {
		case UmaPackage.ROLE_DESCRIPTOR__RESPONSIBLE_FOR:
			refreshChildren(notification);
			return;
		}

		super.notifyChanged(notification);
	}

	/**
	 * @param notification
	 */
	private void refreshChildren(Notification notification) {
		if (ProcessUtil.isRefreshRequired(notification, null)) {
			Process topAct = (Process) getTopItem();
			AdapterFactory rootAdapterFactory = getRootAdapterFactory();

			ProcessUtil.refreshViewer(rootAdapterFactory, topAct);
		}
		
		// refresh children of this item provider, its parent activity and
		// all extenders, local contributors of the parent activity
		//
		TaskDescriptorItemProvider.refreshChildren(this, notification);
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
		RoleDescriptor roleDesc = (RoleDescriptor) obj;
		List newChildren;
	
		// If roles has any of taskdescriptor linked then don't show any
		// children otherwise show it's linked workproduct descriptor
		if(!AssociationHelper.getPrimaryTaskDescriptors(roleDesc).isEmpty() ||
				!AssociationHelper.getAdditionalTaskDescriptors(roleDesc).isEmpty() ||
				!AssociationHelper.getAssistedTaskDescriptors(roleDesc).isEmpty())
		{
			newChildren = Collections.EMPTY_LIST;
		}
		else {
			// get children
			Collection children = super.getChildren(obj);
			newChildren = new ArrayList();
			IFilter filter = getFilter(obj);
			
			for (Iterator itor = children.iterator(); itor.hasNext();) {
				Object o = itor.next();
				if (o instanceof WrapperItemProvider) {
					Object child = ((WrapperItemProvider) o).getValue();
					if ((filter != null) && (filter.accept(child))) {
						newChildren.add(o);
					}
				}
			}
			
			Role role = roleDesc.getRole();
			if (role != null) {
				ModifiedTypeMeta meta = TypeDefUtil.getMdtMeta(role);
				if (meta != null) {
					for (ExtendedReference eRef : meta.getReferences()) {
						if (ExtendedReference.WorkProducts.equals(eRef.getContributeTo())) {
							List list = PropUtil.getPropUtil().getExtendedReferenceList(roleDesc, eRef, false);
							int ix = 0;
							for (Object item : list) {
								 Object wrapped = wrap(roleDesc, eRef.getReference(), item, ix++);
								 newChildren.add(wrapped);
							}
						}
					}
				}
			}				
			
		}
		
		//		 sort the children
		Collections.sort(newChildren, Comparators.PRESENTATION_NAME_COMPARATOR);
		
		updateCachedChildren(newChildren);
		return newChildren;
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

}
