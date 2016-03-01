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

package org.eclipse.epf.library.edit.itemsfilter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.util.PracticePropUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.Deliverable;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.epf.uma.util.UserDefinedTypeMeta;

/**
 * This is a filter for variability base element selection. The filter will
 * check if object can be accepted as the base element of the filter owner.
 * 
 * Filter rules:
 * 
 * 1. The element itself and it's generalizers (contributor, replacer, or
 * extender) should be filtered out
 * 2. Any elements in the containment chain of the above, should be filtered
 * out
 * 3. if any of the base elements can't be selected, then this element can't
 * be selected.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class VariabilityBaseElementFilter implements
		org.eclipse.epf.library.edit.IFilter {

	private VariabilityElement element;

	private List badguys = new ArrayList();

	public VariabilityBaseElementFilter(VariabilityElement element) {
		this.element = element;

		// first of all, you can't vary yourself
		badguys.add(element);

		// get all my generalizers
		Iterator it = TngUtil.getGeneralizers(element);
		while (it.hasNext()) {
			VariabilityElement ve = (VariabilityElement) it.next();
			badguys.add(ve);

			// the containment chain on my generalizers can't be my base
			handleContainmentChain(ve);
		}

		// element's in my chain can't be my base
		handleContainmentChain(element);
	}

	private void handleContainmentChain(VariabilityElement e) {
		if (!hasContainmentFeature()) {
			return;
		}

		if (e instanceof CustomCategory) {
			List roots = new ArrayList();
			loadRootCustomCategories((CustomCategory) e, roots);
			badguys.addAll(roots);
		} else if (e instanceof Deliverable) {
			List roots = new ArrayList();
			deliverableParts((Deliverable) e, roots);
			badguys.addAll(roots);
		} else {
			// if the element has containment feature,
			// you can't select the element in the containment chain
			EObject root = getChainRoot(e);
			if (!badguys.contains(root)) {
				badguys.add(root);
			}

			/*
			 * hold off for now // also only one element in a chain can be
			 * selected as base // so get all the base elements in the element
			 * chain, and block the element chain of the base elements
			 * filterUsedBaseChain((VariabilityElement)root); for (Iterator it =
			 * root.eAllContents(); it.hasNext(); ) { Object o = it.next(); if (
			 * o.getClass().isInstance(element) ) {
			 * filterUsedBaseChain((VariabilityElement)o); } }
			 */
		}

	}

	private void deliverableParts(Deliverable e, List roots) {
		List list = ((Deliverable) e).getDeliveredWorkProducts();
		if (list != null && list.size() > 0) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Object obj = iterator.next();
				if (obj instanceof Deliverable) {
					roots.add(obj);
					deliverableParts((Deliverable) obj, roots);
				}
			}
		}
	}

	private void loadRootCustomCategories(CustomCategory e, List roots) {
		List cs = AssociationHelper.getCustomCategories(e);
		if (cs != null && cs.size() > 0) {
			for (Iterator it = cs.iterator(); it.hasNext();) {
				CustomCategory cc = (CustomCategory) it.next();
				if (!TngUtil.isRootCustomCategory(cc)) {
					loadRootCustomCategories(cc, roots);
				} else {
					if (!roots.contains(e)) {
						roots.add(e);
					}
					break;
				}
			}
		} else if (!roots.contains(e)) {
			roots.add(e);
		}
	}

	// private void filterUsedBaseChain(VariabilityElement e) {
	// VariabilityElement base = e.getVariabilityBasedOnElement();
	// if (base != null) {
	// // if the element chain is selected as base in the current chain,
	// // no element in that chain can be futher selected
	// Object root = getChainRoot(base);
	// if (!badguys.contains(root)) {
	// badguys.add(root);
	// }
	// }
	// }

	private boolean hasContainmentFeature() {
		return element instanceof Domain || element instanceof CustomCategory
				|| element instanceof Artifact || element instanceof Practice
				|| element instanceof Deliverable;
	}

	private EObject getChainRoot(MethodElement e) {
		EObject root = e;
		Object type = element.getType();
		EObject parent = e;
		while ((parent = parent.eContainer()) != null
				&& ((MethodElement) parent).getType() == type) {
			root = parent;
		}

		return root;
	}

	public boolean accept(Object obj) {

		// always accept packages
		if (obj instanceof MethodLibrary || obj instanceof MethodPlugin
				|| obj instanceof MethodPackage
				|| obj instanceof MethodConfiguration) {
			return true;
		}

		// must be variability element and can't be itself
		if (obj == element || !(obj instanceof VariabilityElement)) {
			return false;
		}

		// must be the same type
		VariabilityElement ve = (VariabilityElement) obj;
		if (ve.getType() != element.getType()) {
			return false;
		}
		
		if (element instanceof Practice && ve instanceof Practice) {
			Practice p1 = (Practice) element;
			Practice p2 = (Practice) ve;
			PracticePropUtil propUtil = PracticePropUtil.getPracticePropUtil();
			UserDefinedTypeMeta meta1 = null;
			UserDefinedTypeMeta meta2 = null;
			try {
				meta1 = propUtil.getUtdData(p1);
				meta2 = propUtil.getUtdData(p2);
			} catch (Exception e) {
				LibraryEditPlugin.getDefault().getLogger().logError(e);
			}
			if (meta1 != null && meta2 != null) {
				if (! meta1.getId().equals(meta2.getId())) {
					return false;
				}
			} else if (!(meta1 == null && meta2 == null)) {
				return false;
			}
		}

		// can't be my any of my generalizers
		if (badguys.contains(obj)) {
			return false;
		}

		// if this element's base chain is a badguy, can't accept, otherwise, a
		// circular lock
		while ((ve = ve.getVariabilityBasedOnElement()) != null) {
			if (badguys.contains(ve)) {
				return false;
			}
		}
		// anything else ?
		return true;
	}

}
