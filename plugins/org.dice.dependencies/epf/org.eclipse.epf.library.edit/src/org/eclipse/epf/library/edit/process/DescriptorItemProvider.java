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

import java.text.MessageFormat;
import java.util.Iterator;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.WorkProductDescriptor;


/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class DescriptorItemProvider extends BreakdownElementItemProvider {

	/**
	 * @param adapterFactory
	 * @param delegateItemProvider
	 */
	public DescriptorItemProvider(AdapterFactory adapterFactory,
			ItemProviderAdapter delegateItemProvider) {
		super(adapterFactory, delegateItemProvider);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BreakdownElementItemProvider#getAttribute(java.lang.Object,
	 *      java.lang.String)
	 */
	public String getAttribute(Object object, String property) {
		if (property == IBSItemProvider.COL_MODEL_INFO) {
			StringBuffer modelInfo = new StringBuffer();
			Object e = ProcessUtil.getAssociatedElement((Descriptor) object);
			if (e instanceof VariabilityElement) {
				VariabilityElement ve = (VariabilityElement) e;
				Object base = ve.getVariabilityBasedOnElement();
				if (base != null) {
					// look for existing descriptor that is associated with the
					// base in the activity
					//
					Object parent = null;
					for (parent = getParent(object); parent != null
							&& !(parent instanceof Activity);) {
						ITreeItemContentProvider adapter = (ITreeItemContentProvider) getRootAdapterFactory()
								.adapt(parent, ITreeItemContentProvider.class);
						parent = adapter.getParent(parent);
					}
					if (parent != null) {
						ITreeItemContentProvider adapter = (ITreeItemContentProvider) getRootAdapterFactory()
								.adapt(parent, ITreeItemContentProvider.class);
						Object desc = null;
						find_descriptor_loop: for (Iterator iter = adapter
								.getChildren(parent).iterator(); iter.hasNext();) {
							Object child = TngUtil.unwrap(iter.next());
							if (child instanceof Descriptor
									&& ProcessUtil
											.getAssociatedElement((Descriptor) child) == base) {
								desc = child;
								break find_descriptor_loop;
							}
						}
						if (desc != null) {
							String str = null;
							if (ve.getVariabilityType() == VariabilityType.EXTENDS) {
								str = LibraryEditResources.process_extends; 
							} else if (ve.getVariabilityType() == VariabilityType.REPLACES) {
								str = LibraryEditResources.process_replaces; 
							}
							String name = ((DescribableElement) desc)
									.getPresentationName();
							if (str != null)
								modelInfo.append(MessageFormat.format(str,
										new Object[] { name }));
							else
								modelInfo.append(""); //$NON-NLS-1$
						}
					}
				}

				if (object instanceof WorkProductDescriptor) {
//					ProcessUtil.getModelInfoForWorkProductDescriptor(modelInfo,
//							(WorkProductDescriptor) object);
					ProcessUtil.getWPDModelInfo(modelInfo, object, this);
				}
			}
			return modelInfo.toString(); 
		}
		return super.getAttribute(object, property);
	}

}