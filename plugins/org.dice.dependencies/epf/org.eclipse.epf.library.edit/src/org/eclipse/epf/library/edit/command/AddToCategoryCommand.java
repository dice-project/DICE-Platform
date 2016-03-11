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
package org.eclipse.epf.library.edit.command;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.Misc;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.ecore.impl.MultiResourceEObject;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * This command is used to assign a method element to a category. If the category
 * is in a base method plug-in of the element, this command will automatically
 * create a contributor to the base category and assign the element to the
 * contributing category. This is avoids modifying the base method plug-in.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class AddToCategoryCommand extends AbstractCommand implements
		IResourceAwareCommand {

	private ContentCategory category;

	private EStructuralFeature feature;

	private MethodElement element;

	private String[] categoryPkgPath;

	private boolean checkForExistingContributor;

	private ContentCategory usedCategory = null;

	private boolean createNewContributor = false;

	private ContentPackage categoryPkg;

	private Collection<Resource> modifiedResources;

	private Object oldOppositeFeatureValue;
	
	private List usedCategories;

	public AddToCategoryCommand(ContentCategory category,
			MethodElement element, EStructuralFeature feature,
			String[] categoryPkgPath, boolean checkForExistingContributor,
			List usedCategories) {
		this.category = category;
		this.feature = feature;
		this.element = element;
		this.categoryPkgPath = categoryPkgPath;
		this.checkForExistingContributor = checkForExistingContributor;
		this.usedCategories = usedCategories;
	}

	protected boolean prepare() {
		// check if this operation will modify the element in opposite feature
		// value
		//
		return UserInteractionHelper.checkModifyOpposite(category, feature,
				element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.AbstractCommand#getLabel()
	 */
	public String getLabel() {
		return "Add To Category"; //$NON-NLS-1$
	}
	
	private void prepareCategory() {
		// create contributor for category if it is not in the same plugin with
		// the element
		//
		MethodPlugin elementPlugin = UmaUtil.getMethodPlugin(element);
		MethodPlugin categoryPlugin = UmaUtil.getMethodPlugin(category);
		if (categoryPlugin != elementPlugin
				&& Misc.isBaseOf(categoryPlugin, elementPlugin,
						new HashMap<String, Boolean>())) {
			if (category.getVariabilityBasedOnElement() != null) {
				throw new IllegalArgumentException(
						"Could not add element to an extended category that is in a different plug-in: " + category); //$NON-NLS-1$
			}
			if (!feature.isMany()) {
				throw new UnsupportedOperationException();
			}
			categoryPkg = UmaUtil.findContentPackage(elementPlugin,
					categoryPkgPath);
			ContentCategory contrib = null;
			if (checkForExistingContributor) {
				// look for the existing contributor for the category
				//
				contrib = TngUtil.findContributor(categoryPkg, category);
			}

			if (contrib == null) {
				createNewContributor = true;
			} else {
				usedCategory = contrib;
			}
		} else {
			usedCategory = category;
		}
	}

	public void execute() {
		prepareCategory();

		modifiedResources = new HashSet<Resource>();

		redo();
	}

	public void redo() {
		if (createNewContributor) {
			ContentCategory contrib = (ContentCategory) UmaFactory.eINSTANCE
					.create(category.eClass());
			contrib.setVariabilityBasedOnElement(category);
			contrib.setVariabilityType(VariabilityType.CONTRIBUTES);
			categoryPkg.getContentElements().add(contrib);
			usedCategory = contrib;
			if (usedCategory instanceof CustomCategory) {
				CustomCategory root = TngUtil.getRootCustomCategory(UmaUtil
						.getMethodPlugin(usedCategory));
				List list = root.getCategorizedElements();
				list.add(usedCategory);
				TngUtil.setDefaultName(root, (CustomCategory)usedCategory, category.getName());
			} else {
				usedCategory.setName(category.getName());
			}
			// set presentation name empty for new contributors
			usedCategory.setPresentationName("");
		}
		OppositeFeature oppositeFeature = OppositeFeature
				.getOppositeFeature(feature);
		MultiResourceEObject mrEObj = (MultiResourceEObject) element;
		oldOppositeFeatureValue = mrEObj.getOppositeFeatureMap().get(
				oppositeFeature);
		if (feature.isMany()) {
			Collection collection = (Collection) usedCategory.eGet(feature);
			if (oldOppositeFeatureValue != null && !oppositeFeature.isMany()) {
				if (oldOppositeFeatureValue != usedCategory) {
					EObject oldOppVal = (EObject) oldOppositeFeatureValue;
					((Collection) oldOppVal.eGet(feature)).remove(element);
					if (oldOppVal.eResource() != null) {
						modifiedResources.add(oldOppVal.eResource());
					}

					collection.add(element);
				}
			}
			if (!collection.contains(element)) {
				collection.add(element);
			}
		} else {
			if (oldOppositeFeatureValue != null && !oppositeFeature.isMany()) {
				if (oldOppositeFeatureValue != usedCategory) {
					EObject oldOppVal = (EObject) oldOppositeFeatureValue;
					oldOppVal.eSet(feature, null);
					if (oldOppVal.eResource() != null) {
						modifiedResources.add(oldOppVal.eResource());
					}
				}
			}
			if (usedCategory != oldOppositeFeatureValue) {
				usedCategory.eSet(feature, element);
			}
		}

		if (usedCategory.eResource() != null) {
			modifiedResources.add(usedCategory.eResource());
		}
		if (usedCategories.contains(category)) {
			usedCategories.remove(category);
		}
	}

	public void undo() {
		OppositeFeature oppositeFeature = OppositeFeature
				.getOppositeFeature(feature);
		if (feature.isMany()) {
			Collection collection = (Collection) usedCategory.eGet(feature);
			collection.remove(element);
		} else {
			usedCategory.eSet(feature, null);
		}

		// restore old value of the single-value opposite feature
		//
		if (!oppositeFeature.isMany() && oldOppositeFeatureValue != null
				&& oldOppositeFeatureValue != usedCategory) {
			EObject oldOppVal = (EObject) oldOppositeFeatureValue;
			if (feature.isMany()) {
				((Collection) oldOppVal.eGet(feature)).add(element);
			} else {
				oldOppVal.eSet(feature, element);
			}
		}

		if (createNewContributor) {
			if (TngUtil.isEmpty(usedCategory)) {
				MethodPlugin usedCategoryPlugin = UmaUtil.getMethodPlugin(usedCategory);
				EcoreUtil.remove(usedCategory);
				if (usedCategory instanceof CustomCategory) {
					TngUtil.getRootCustomCategory(usedCategoryPlugin)
							.getCategorizedElements().remove(usedCategory);
				}
				usedCategory = null;
			}
		}
		if (!usedCategories.contains(category)) {
			usedCategories.add(category);
		}

	}

	public Collection<?> getAffectedObjects() {
		return Collections.singletonList(usedCategory);
	}

	public Collection<Resource> getModifiedResources() {
		
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=146662
		// Below adding category's resource to modifiedResources is needed, 
		// because before executing command, need to verify if resource is mutable or not
		// getModifiedResources() called before executing actual execute() in few cases.
		// Useful in special cases like if resource is in version control.
		//
		if(modifiedResources == null) {
			modifiedResources = new HashSet<Resource>();
			prepareCategory();
			if(usedCategory != null && usedCategory.eResource() != null) {
				modifiedResources.add(usedCategory.eResource());
			}
			if(createNewContributor && usedCategory == null && element != null) {
				MethodPlugin plugin = UmaUtil.getMethodPlugin(element);
				if(plugin != null && plugin.eResource() != null) {
					modifiedResources.add(plugin.eResource());
				}
			}
		}
		
		if (modifiedResources == null) {
			return Collections.emptyList();
		}
		return modifiedResources;
	}

}
