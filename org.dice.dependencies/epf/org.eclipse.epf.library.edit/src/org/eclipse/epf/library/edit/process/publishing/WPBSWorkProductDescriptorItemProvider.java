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
package org.eclipse.epf.library.edit.process.publishing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.util.UmaUtil;


/**
 * @author Phong Nguyen Le - Mar 22, 2006
 * @since  1.0
 */
public class WPBSWorkProductDescriptorItemProvider extends
		org.eclipse.epf.library.edit.process.WorkProductDescriptorItemProvider {

	/**
	 * @param adapterFactory
	 * @param delegateItemProvider
	 */
	public WPBSWorkProductDescriptorItemProvider(AdapterFactory adapterFactory,
			ItemProviderAdapter delegateItemProvider) {
		super(adapterFactory, delegateItemProvider);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.process.WorkProductDescriptorItemProvider#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		if(cachedChildren == null) {
			cachedChildren = new ArrayList(super.getChildren(object));
		}
		return cachedChildren;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.process.WorkProductDescriptorItemProvider#addContainedArtifactDescriptors(org.eclipse.epf.uma.WorkProductDescriptor, java.util.Collection)
	 */
	@Override
	protected void addContainedArtifactDescriptors(WorkProductDescriptor wpDesc, Collection children, MethodConfiguration config) {
		Activity activity = UmaUtil.getParentActivity(wpDesc);
		if(activity != null) {
			// collect all artifact descriptors or their wrappers under the same activity
			//
			List artifactDescList = new ArrayList();
			Collection siblings = activity.getBreakdownElements();
			IFilter filter = ProcessUtil.getFilter(adapterFactory);
			for (Iterator iter = siblings.iterator(); iter.hasNext();) {
				Object e = iter.next();
				if(e instanceof WorkProductDescriptor 
						&& ((WorkProductDescriptor)e).getWorkProduct() instanceof Artifact
						&& (filter == null || filter.accept(e))) {
					artifactDescList.add(e);
				}
			}
			
			children.addAll(ProcessUtil.getContainedArtifactsDescriptors(wpDesc, artifactDescList));			
		}

	}
}
