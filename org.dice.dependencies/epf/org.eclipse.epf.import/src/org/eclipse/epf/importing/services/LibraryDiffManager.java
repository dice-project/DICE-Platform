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
package org.eclipse.epf.importing.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.epf.importing.ImportPlugin;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Dimension;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodElementProperty;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Point;


/**
 * Manages the differences between method elements in the current library and
 * those that will be imported.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class LibraryDiffManager {

	private MethodLibrary baseLibrary;

	private MethodLibrary importLibraty;

	// Map of uid to ElementDiffTree, the root of the ElementDiffTree is the
	// library.
	private Map diffElementMap = new HashMap();

	// Map of uid to element for import library configuration.
	private Map importElementMap = new HashMap();

	// id to element map of the current library.
	private Map currentElementMap = new HashMap();

	// The diff tree.
	ElementDiffTree rootDiffTree = null;

	private boolean debug = ImportPlugin.getDefault().isDebugging();

	/**
	 * Creates a new instance.
	 */
	public LibraryDiffManager(MethodLibrary baseLibrary,
			MethodLibrary importLibraty) {
		this.baseLibrary = baseLibrary;
		this.importLibraty = importLibraty;

		rootDiffTree = new ElementDiffTree(baseLibrary, importLibraty);
	}

	/**
	 * Returns the import library.
	 */
	public MethodLibrary getImportingLibrary() {
		return this.importLibraty;
	}

	/**
	 * Builds the difference tree.
	 */
	public ElementDiffTree buildDiffTree() {
		// Build the uid to element map for the import library.
		buildUIDMap(importLibraty.eContents(), importElementMap, true, false);

		buildUIDMap(baseLibrary.eContents(), currentElementMap, true, true);

		// Get all the system packages in each plug-in so we can filter out
		// those packages when comparing the libraries.

		// Iterate the element in the current library, and build a diff tree by
		// comparing to the import library.
		List plugins = baseLibrary.getMethodPlugins();
		for (Iterator it = plugins.iterator(); it.hasNext();) {
			MethodPlugin plugin = (MethodPlugin) it.next();
			List systemPackages = TngUtil.getAllSystemPackages(plugin);
			
			// content category packages should be included into the diff tree 
			// otherwise the category information will be lost
			List categoryList = TngUtil.getContentCategoryPackages(plugin);
			for ( Iterator itc = categoryList.iterator(); itc.hasNext(); ) {
				Object o = itc.next();
				if ( systemPackages.contains(o) ) {
					systemPackages.remove(o);
				}
			}
			
			// the root customcategoty package needs to be included into the diff tree
			int i = 0;
			while (i < systemPackages.size() ) {
				MethodPackage pkg = (MethodPackage)systemPackages.get(i);
				if ( TngUtil.isRootCutomCategoryPackage(pkg) ) {
					systemPackages.remove(pkg);
					break;
				} else {
					i++;
				}
			}
			
			// Build the diff tree by iterating the current library.
			iterateElement(plugin, rootDiffTree, systemPackages);
		}

		List configs = baseLibrary.getPredefinedConfigurations();
		for (Iterator it = configs.iterator(); it.hasNext();) {
			MethodConfiguration config = (MethodConfiguration) it.next();

			// Build the diff tree by iterating the current library.
			iterateElement(config, rootDiffTree, new ArrayList());
		}

		// Process the new elements in the import library and add them to the
		// diff tree.
		handleNewElements(importLibraty);

		return rootDiffTree;
	}

	/**
	 * Returns the differnece tree.
	 */
	public ElementDiffTree getDiffTree() {
		return rootDiffTree;
	}

	/**
	 * Returns the differnece map.
	 */
	public Map getDiffTreeMap() {
		return diffElementMap;
	}

	/**
	 * Iterates the element in the current library and build a diff tree by
	 * comparing to the import library.
	 */
	private void iterateElement(MethodElement element,
			ElementDiffTree diffTree, List systemPackages) {
		if (!selectable(element)) {
			return;
		}

		// Update the diffelement UID map.
		if (!systemPackages.contains(element)
				&& !(element instanceof MethodLibrary)) {
			String uid = element.getGuid();
			ElementDiffTree diffChild = new ElementDiffTree(element,
					(MethodElement) importElementMap.get(uid));
			diffElementMap.put(uid, diffChild);
			diffTree.addChild(diffChild);
			diffTree = diffChild;
		}

		// Check with the importing element and create an ElementDiffInfo object
		EList children = element.eContents();
		if (children != null) {
			for (Iterator it = children.iterator(); it.hasNext();) {
				Object e = it.next();
				if (e instanceof MethodElement) {
					MethodElement child = (MethodElement) e;
					if (selectable(child)) {
						iterateElement(child, diffTree, systemPackages);
					}
				} else {
					if (debug ) {
						System.out
								.println("Error! " + e + " is not a MethodElement object"); //$NON-NLS-1$ //$NON-NLS-2$
					}
				}
			}
		}
	}

	/**
	 * If there are newly added elements in the importing library, add the new
	 * elements into the parent
	 */
	private void handleNewElements(MethodElement element) {
		if (!selectable(element)) {
			return;
		}

		if (isNewElement(element)) {
			// This is a new element, find the parent in the base library.
			MethodElement parent = (MethodElement) element.eContainer();
			while (isNewElement(parent)) {
				element = parent;
				parent = (MethodElement) parent.eContainer();
			}

			// Always merge libray plugins even it's from a different library.
			if (parent instanceof MethodLibrary) {
				parent = baseLibrary;
			}

			// Need to find the parent element in the current library so that we
			// know where to add the new element.
			MethodElement lib_parent = (MethodElement) currentElementMap
					.get(parent.getGuid());
			if (lib_parent == null) {
				lib_parent = baseLibrary;
			}
			ElementDiffTree elementDiffTree = new ElementDiffTree(null,
					element, lib_parent);

			// Find the parent diff tree.
			ElementDiffTree parentDiffTree = null;
			while ((parentDiffTree == null) && (parent != null)) {
				parentDiffTree = (ElementDiffTree) diffElementMap.get(parent
						.getGuid());
				parent = (MethodElement) parent.eContainer();
			}

			if (parentDiffTree == null) {
				parentDiffTree = rootDiffTree;
			}

			parentDiffTree.addChild(elementDiffTree);
		} else {
			// Iterate the children.
			List children = element.eContents();
			for (Iterator itc = children.iterator(); itc.hasNext();) {
				Object obj = itc.next();
				if (obj instanceof MethodElement) {
					MethodElement e = (MethodElement) obj; 
					handleNewElements(e);
				}
			}

		}
	}

	private boolean isNewElement(MethodElement element) {
		if (element == null || element instanceof MethodLibrary) {
			return false;
		}
		return !currentElementMap.containsKey(element.getGuid());
	}

	private void buildUIDMap(List elements, Map elementUIDMap, boolean recursive, boolean full) {
		MethodElement element;
		for (Iterator it = elements.iterator(); it.hasNext();) {
			Object e = it.next();
			if (e instanceof MethodElement) {
				element = (MethodElement) e;
				if (selectable(element) || full) {
					String uid = element.getGuid();
					elementUIDMap.put(uid, element);

					if (recursive) {
						buildUIDMap(element.eContents(), elementUIDMap,
								recursive, full);
					}
				}
			} else if ( !(e instanceof Point || e instanceof Dimension || e instanceof MethodElementProperty) ){
				ImportPlugin.getDefault().getLogger().logError(
							"Import error. " + e + " is not a MethodElement object"); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}

	}

	/**
	 * Checks if the method element is selectable.
	 */
	public boolean selectable(MethodElement element) {
		return (element instanceof MethodLibrary
				|| element instanceof MethodPlugin
				|| element instanceof MethodPackage
				|| element instanceof MethodConfiguration);
	}

	/**
	 * Returns the existing method element given by the guid.
	 */
	public MethodElement getExistingElement(String guid) {
		return (MethodElement)currentElementMap.get(guid);
	}

}
