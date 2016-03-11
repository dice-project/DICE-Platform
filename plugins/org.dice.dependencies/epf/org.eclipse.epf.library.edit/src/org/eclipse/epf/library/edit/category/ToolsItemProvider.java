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
package org.eclipse.epf.library.edit.category;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.util.LibraryEditConstants;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Tool;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;

/**
 * The item provider adapter for the "Tools" folder.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class ToolsItemProvider extends TransientCategoryPackageItemProvider {

	/**
	 * Creates a new instance.
	 */
	public ToolsItemProvider(AdapterFactory adapterFactory,
			ContentPackage target, String name) {
		super(adapterFactory, target, name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#collectNewChildDescriptors(java.util.Collection,
	 *      java.lang.Object)
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getContentPackage_ContentElements(), UmaFactory.eINSTANCE
				.createTool()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildrenFeatures(java.lang.Object)
	 */
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			childrenFeatures = new ArrayList();
			childrenFeatures.add(UmaPackage.eINSTANCE
					.getContentPackage_ContentElements());
		}
		return childrenFeatures;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#acceptAsChild(java.lang.Object)
	 */
	protected boolean acceptAsChild(Object obj) {
		if (!super.acceptAsChild(obj))
			return false;
		return obj instanceof Tool;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getImage(java.lang.Object)
	 */
	public Object getImage(Object object) {
		return LibraryEditPlugin.INSTANCE.getImage("full/obj16/Tools"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		Collection children = super.getChildren(object);
		// for (Iterator iter = children.iterator(); iter.hasNext();) {
		// Tool child = (Tool) iter.next();
		// ToolItemProvider itemProvider = (ToolItemProvider)
		// TngUtil.getBestAdapterFactory(adapterFactory).adapt(child,
		// ITreeItemContentProvider.class);
		// itemProvider.setParent(object);
		// }

		// Change Categories display in Library Navigator
		// (commented for this defect)
		// MethodPlugin plugin = UmaUtil.getMethodPlugin((Element)
		// ((ItemProviderAdapter)object).getTarget());
		// ContentPackage coreContentPkg = UmaUtil.findContentPackage(plugin,
		// ModelStructure.DEFAULT.coreContentPath);
		// UncategorizedToolMentorsItemProvider uncategorized = new
		// UncategorizedToolMentorsItemProvider(adapterFactory, coreContentPkg);
		// children.add(uncategorized);
		// if(groupItemProviderMap == null) {
		// groupItemProviderMap = new HashMap();
		// }
		// groupItemProviderMap.put(uncategorized.getText(null), uncategorized);
		return children;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#setDefaultName(java.lang.Object)
	 */
	public void setDefaultName(Object obj) {
		if (obj instanceof Tool) {
			TngUtil.setDefaultName(TngUtil.extract(((ContentPackage) target)
					.getContentElements(), Tool.class), (MethodElement) obj,
					LibraryEditConstants.NEW_TOOL);
		}
	}

}
