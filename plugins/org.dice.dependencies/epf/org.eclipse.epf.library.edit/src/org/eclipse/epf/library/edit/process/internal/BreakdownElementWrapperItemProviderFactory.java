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
package org.eclipse.epf.library.edit.process.internal;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.epf.library.edit.process.ActivityWrapperItemProvider;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.CompositeRoleWrapperItemProvider;
import org.eclipse.epf.library.edit.process.DeliverableDescriptorWrapperItemProvider;
import org.eclipse.epf.library.edit.process.IBreakdownElementWrapperItemProviderFactory;
import org.eclipse.epf.library.edit.process.MilestoneWrapperItemProvider;
import org.eclipse.epf.library.edit.process.RoleDescriptorWrapperItemProvider;
import org.eclipse.epf.library.edit.process.TaskDescriptorWrapperItemProvider;
import org.eclipse.epf.library.edit.process.TeamProfileWrapperItemProvider;
import org.eclipse.epf.library.edit.process.WorkProductDescriptorWrapperItemProvider;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.CompositeRole;
import org.eclipse.epf.uma.Deliverable;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.WorkProductDescriptor;


/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class BreakdownElementWrapperItemProviderFactory implements
		IBreakdownElementWrapperItemProviderFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBreakdownElementWrapperItemProviderFactory#createWrapper(java.lang.Object,
	 *      java.lang.Object, org.eclipse.emf.common.notify.AdapterFactory)
	 */
	public IWrapperItemProvider createWrapper(Object value,
			Object owner, AdapterFactory adapterFactory) {
		return createWrapper(value, owner, null, CommandParameter.NO_INDEX,
				adapterFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBreakdownElementWrapperItemProviderFactory#createWrapper(java.lang.Object,
	 *      java.lang.Object, org.eclipse.emf.ecore.EStructuralFeature, int,
	 *      org.eclipse.emf.common.notify.AdapterFactory)
	 */
	public IWrapperItemProvider createWrapper(Object value,
			Object owner, EStructuralFeature feature, int index,
			AdapterFactory adapterFactory) {
		value = TngUtil.unwrap(value);
		if (value instanceof Activity) {
			return new ActivityWrapperItemProvider((Activity) value, owner,
					feature, index, adapterFactory);
		} else if (value instanceof Milestone) {
			return new MilestoneWrapperItemProvider((Milestone) value, owner,
					feature, index, adapterFactory);
		} else if (value instanceof CompositeRole) {
			return new CompositeRoleWrapperItemProvider((CompositeRole) value,
					owner, feature, index, adapterFactory);
		} else if (value instanceof RoleDescriptor) {
			return new RoleDescriptorWrapperItemProvider(
					(RoleDescriptor) value, owner, feature, index,
					adapterFactory);
		} else if (value instanceof TaskDescriptor) {
			return new TaskDescriptorWrapperItemProvider(
					(TaskDescriptor) value, owner, feature, index,
					adapterFactory);
		} else if (value instanceof TeamProfile) {
			return new TeamProfileWrapperItemProvider((TeamProfile) value,
					owner, feature, index, adapterFactory);
		} else if (value instanceof WorkProductDescriptor) {
			WorkProductDescriptor wpd = (WorkProductDescriptor) value;
			if (wpd.getWorkProduct() instanceof Deliverable) {
				return new DeliverableDescriptorWrapperItemProvider(wpd, owner,
						feature, index, adapterFactory);
			}
			return new WorkProductDescriptorWrapperItemProvider(wpd, owner,
					feature, index, adapterFactory);
		} else if (value instanceof BreakdownElement) {
			return new BreakdownElementWrapperItemProvider(
					(BreakdownElement) value, owner, feature, index,
					adapterFactory);
		}
		return null;
	}

}
