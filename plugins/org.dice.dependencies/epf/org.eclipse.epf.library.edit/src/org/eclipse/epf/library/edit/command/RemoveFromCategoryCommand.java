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
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.edit.util.Misc;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * This command is used to remove a method element from a category.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class RemoveFromCategoryCommand extends AbstractCommand implements
		IResourceAwareCommand {

	private ContentCategory category;

	private MethodElement element;

	private EStructuralFeature feature;

	private String[] contentPkgPath;

	private ContentCategory usedCategory;

	private int removedElementIndex = -1;

	private boolean removed = false;

	private List usedCategories;

	public RemoveFromCategoryCommand(ContentCategory category,
			MethodElement element, EStructuralFeature feature,
			String[] contentPkgPath, List usedCategories) {
		this.category = category;
		this.element = element;
		this.feature = feature;
		this.contentPkgPath = contentPkgPath;
		this.usedCategories = usedCategories;
	}

	public Collection getModifiedResources() {
		if (usedCategory != null && usedCategory.eResource() != null) {
			return Collections.singletonList(usedCategory.eResource());
		}

		return Collections.EMPTY_LIST;
	}

	protected boolean prepare() {
		return true;
	}

	public void execute() {
		MethodPlugin categoryPlugin = UmaUtil.getMethodPlugin(category);
		MethodPlugin elementPlugin = UmaUtil.getMethodPlugin(element);

		if (categoryPlugin != elementPlugin
				&& Misc.isBaseOf(categoryPlugin, elementPlugin, new HashMap<String, Boolean>())) {
			usedCategory = TngUtil.findContributor(UmaUtil.findContentPackage(
					elementPlugin, contentPkgPath), category);
		} else {
			usedCategory = category;
		}

		redo();
	}

	public void redo() {
		if (usedCategory == null)
			return;

		if (feature.isMany()) {
			Collection collection = (Collection) usedCategory.eGet(feature);
			if (collection instanceof EList) {
				removedElementIndex = ((EList) collection).indexOf(element);
			}
			removed = collection.remove(element);
		} else {
			usedCategory.eSet(feature, null);
			removed = true;
		}
		if (removed) {
			if (!usedCategories.contains(category)) {
				usedCategories.add(category);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.AbstractCommand#undo()
	 */
	public void undo() {
		if (removed) {
			if (feature.isMany()) {
				Collection collection = (Collection) usedCategory.eGet(feature);
				if (collection instanceof EList && removedElementIndex != -1) {
					((EList) collection).add(removedElementIndex, element);
				} else
					collection.add(element);
			} else {
				usedCategory.eSet(feature, element);
			}
			removed = false;
		}
		if (usedCategories.contains(category)) {
			usedCategories.remove(category);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.AbstractCommand#getResult()
	 */
	public Collection getResult() {
		if (removed) {
			return Collections.singletonList(element);
		}
		return Collections.EMPTY_LIST;
	}

}
