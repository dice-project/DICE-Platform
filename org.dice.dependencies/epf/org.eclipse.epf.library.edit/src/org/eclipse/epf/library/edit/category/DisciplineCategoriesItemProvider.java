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
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.util.LibraryEditConstants;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.DisciplineGrouping;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * The item provider adapter for the "Disciplines" folder.
 * <p>
 * This class will be renamed as DisciplinesItemProvider in EPF M5.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class DisciplineCategoriesItemProvider extends
		TransientCategoryPackageItemProvider {

	/**
	 * Creates a new instance.
	 */
	public DisciplineCategoriesItemProvider(AdapterFactory adapterFactory,
			ContentPackage target, String name) {
		super(adapterFactory, target, name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.category.TransientCategoryPackageItemProvider#isInherited(org.eclipse.epf.uma.ContentCategory)
	 */
	protected boolean isInherited(ContentCategory category) {
		return (category instanceof Discipline || category instanceof DisciplineGrouping)
				&& category.getVariabilityBasedOnElement() != null;
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
				.createDisciplineGrouping()));

		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getContentPackage_ContentElements(), UmaFactory.eINSTANCE
				.createDiscipline()));
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

	public static boolean accept(Object obj) {
		if (obj instanceof DisciplineGrouping) {
			return true;
		}
		if (obj instanceof Discipline) {
			List groups = AssociationHelper.getDisciplineGroups((Discipline) obj);
			if (groups.isEmpty()) {
				return true;
			} else {
				for (Object group : groups) {
					if (group instanceof DisciplineGrouping) {
						if (UmaUtil.getMethodPlugin((DisciplineGrouping)group) == UmaUtil.getMethodPlugin((Discipline)obj)) {
							return false;
						}
					}
				}
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#acceptAsChild(java.lang.Object)
	 */
	protected boolean acceptAsChild(Object obj) {
		if (!super.acceptAsChild(obj))
			return false;
		return accept(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getImage(java.lang.Object)
	 */
	public Object getImage(Object object) {
		return LibraryEditPlugin.INSTANCE.getImage("full/obj16/Disciplines"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		Collection children = super.getChildren(object);

		for (Iterator iter = children.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (element instanceof Discipline) {
				Discipline child = (Discipline) element;
				DisciplineItemProvider itemProvider = (DisciplineItemProvider) TngUtil
						.getBestAdapterFactory(adapterFactory).adapt(child,
								ITreeItemContentProvider.class);
				itemProvider.setParent(object);
			}
		}

		return children;
	}

	public void setDefaultName(Object obj) {
		if (obj instanceof Discipline) {
			TngUtil.setDefaultName(TngUtil.extract(((ContentPackage) target)
					.getContentElements(), Discipline.class),
					(MethodElement) obj, LibraryEditConstants.NEW_DISCIPLINE);
		} else if (obj instanceof DisciplineGrouping) {
			TngUtil.setDefaultName(TngUtil.extract(((ContentPackage) target)
					.getContentElements(), DisciplineGrouping.class),
					(MethodElement) obj,
					LibraryEditConstants.NEW_DISCIPLINE_GROUPING);
		}
	}

}
