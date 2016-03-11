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
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.library.edit.process.BSActivityItemProvider;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.ExposedAdapterFactory;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.util.AssociationHelper;


/**
 * Activity item provider for consolidated view
 * 
 * @author Shilpa Toraskar
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class ActivityItemProvider extends BSActivityItemProvider {

	/**
	 * @param adapterFactory
	 */
	public ActivityItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BSActivityItemProvider#getObject(org.eclipse.epf.uma.Descriptor)
	 */
	protected Object getObject(Descriptor descriptor) {
		return ((TaskDescriptor) descriptor).getTask();
	}

	public Collection getEClasses() {
		return Collections.EMPTY_LIST;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BSActivityItemProvider#doRefreshAffectedViewers()
	 */
	protected void doRefreshAffectedViewers() {
		Object proc = getTopItem();
		Object itemProvider = adapterFactory.adapt(proc,
				ITreeItemContentProvider.class);
		if (itemProvider instanceof BSActivityItemProvider) {
			((BSActivityItemProvider) itemProvider)
					.setRefreshAllIDsRequired(true);
		}

		ProcessUtil
				.refreshIDsInViewers((ExposedAdapterFactory) getRootAdapterFactory());
	}
	
	protected Collection removeSubartifactsFromChildren(Collection children, boolean unwrap) {
		MethodConfiguration config = getConfigurator() == null ? null : getConfigurator().getMethodConfiguration();
		return ProcessUtil.removeSubartifactsFromChildren(config, children, unwrap);
	}

	public Collection getChildren(Object obj) {
		List newChildren = new ArrayList();
		Collection children = super.getChildren(obj);
		children = removeSubartifactsFromChildren(children, false);
		List roleDescriptors = new ArrayList();
		List wpDescriptors = new ArrayList();
		for (Iterator itor = children.iterator(); itor.hasNext();) {
			Object object = itor.next();
			Object e = TngUtil.unwrap(object);
			
			// don't return roledescriptor which are linked to taskdescriptor
			// 
			if (e instanceof RoleDescriptor) {
				RoleDescriptor roleDesc = (RoleDescriptor) e;
				if(AssociationHelper.getPrimaryTaskDescriptors(roleDesc).isEmpty() &&
						AssociationHelper.getAdditionalTaskDescriptors(roleDesc).isEmpty() &&
						AssociationHelper.getAssistedTaskDescriptors(roleDesc).isEmpty()) 
				{
					roleDescriptors.add(object);
				} else if (ProcessUtil.isSynFree() && ! DescriptorPropUtil.getDesciptorPropUtil().isCreatedByReference(roleDesc)) {
					roleDescriptors.add(object);	
				}
			}
			// don't return wpdescriptor which are linked to either
			// taskdescriptor or roledescriptor
			else if (e instanceof WorkProductDescriptor) {
				WorkProductDescriptor wpDesc = (WorkProductDescriptor) e;
				if(AssociationHelper.getExternalInputTo(wpDesc).isEmpty() &&
						AssociationHelper.getMandatoryInputTo(wpDesc).isEmpty() &&
						AssociationHelper.getOptionalInputTo(wpDesc).isEmpty() &&
						AssociationHelper.getOutputFrom(wpDesc).isEmpty() &&
						AssociationHelper.getResponsibleRoleDescriptors(wpDesc).isEmpty())
				{
					wpDescriptors.add(object);
				} else if (ProcessUtil.isSynFree() && ! DescriptorPropUtil.getDesciptorPropUtil().isCreatedByReference(wpDesc)) {
					wpDescriptors.add(object);	
				}
			} else {
				newChildren.add(object);
			}
		}
	
		newChildren.addAll(roleDescriptors);
		newChildren.addAll(wpDescriptors);
		
		updateCachedChildren(newChildren);
		
		return newChildren;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.process.BSActivityItemProvider#getRollupChildrenFromCache()
	 */
	public Collection getRollupChildrenFromCache() {
		return Collections.EMPTY_LIST;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.process.BSActivityItemProvider#isRolledUp()
	 */
	public boolean isRolledUp() {
		return false;
	}
	
	@Override
	protected boolean acceptAsChild(Object parent, Object child) {	
//		if (parent instanceof Activity) {
//			child = TngUtil.unwrap(child);
//			if(child instanceof Activity || child instanceof TaskDescriptor
//					|| child instanceof Milestone) {
//				return super.acceptAsChild(child);
//			}
//			return false;
//		}			
		return super.acceptAsChild(child);
	}	

}
