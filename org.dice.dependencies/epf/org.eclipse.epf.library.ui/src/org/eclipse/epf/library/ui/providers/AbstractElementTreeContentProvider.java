//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007, 2008 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.ui.providers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.ui.LibraryUIResources;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessElement;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;

/**
 * An abstract tree content provider an element tree viewer.
 * 
 * @author Kelvin Low
 * @author Phong Nguyen Le
 * @author Pierre Padovani
 * @since 1.0
 */
public abstract class AbstractElementTreeContentProvider implements
		ITreeContentProvider {

	private static final String PKG_NAME_CONTENT = "Content"; //$NON-NLS-1$

	private static final String PKG_NAME_CORE_CONTENT = "CoreContent"; //$NON-NLS-1$

	private static final String PKG_NAME_CATEGORIES = "Categories"; //$NON-NLS-1$

	private static final String PKG_NAME_DISCIPLINES = "Disciplines"; //$NON-NLS-1$

	private static final String PKG_NAME_DOMAINS = "Domains"; //$NON-NLS-1$

	private static final String PKG_NAME_WORK_PRODUCT_TYPES = "WP Types"; //$NON-NLS-1$

	private static final String PKG_NAME_ROLESETS = "RoleSets"; //$NON-NLS-1$

	private static final String PKG_NAME_TOOLS = "Tools"; //$NON-NLS-1$

	private static final String PKG_NAME_CUSTOM_CATEGORIES = "CustomCategories"; //$NON-NLS-1$

	private static final String PKG_NAME_CAPABILITY_PATTERNS = "CapabilityPatterns"; //$NON-NLS-1$

	private static final String PKG_NAME_DELIVERY_PROCESSES = "DeliveryProcesses"; //$NON-NLS-1$

	private static final String METHOD_CONTENT = LibraryUIResources.elementTCProvider_methodContent;

	private static final String CONTENT_PACKAGES = LibraryUIResources.elementTCProvider_contentPackages;

	private static final String STANDARD_CATEGORIES = LibraryUIResources.elementTCProvider_standardCategories;

	private static final String DISCIPLINES = LibraryUIResources.elementTCProvider_disciplines;

	private static final String DOMAINS = LibraryUIResources.elementTCProvider_domains;

	private static final String WORK_PRODUCT_TYPES = LibraryUIResources.elementTCProvider_workProductTypes;

	private static final String ROLESETS = LibraryUIResources.elementTCProvider_roleSets;

	private static final String TOOLS = LibraryUIResources.elementTCProvider_tools;

	private static final String CUSTOM_CATEGORIES = LibraryUIResources.elementTCProvider_customCategories;

	private static final String PROCESSES = LibraryUIResources.elementTCProvider_processes;

	private static final String CAPABILITY_PATTERNS = LibraryUIResources.elementTCProvider_capabilityPatterns;

	private static final String DELIVERY_PROCESSES = LibraryUIResources.elementTCProvider_deliveryProcesses;

	private static final Object[] EMPTY_LIST = new Object[0];

	private TreeViewer treeViewer;

	private Map<Object, Set<Object>> elementMap = new HashMap<Object, Set<Object>>();

	/**
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(Object)
	 */
	public Object[] getChildren(Object parentElement) {
		if (elementMap == null) {
			return EMPTY_LIST;
		}
		Set<Object> children = elementMap.get(parentElement);
		if (children == null) {
			return EMPTY_LIST;
		}
		return children.toArray();
	}

	/**
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(Object)
	 */
	public Object getParent(Object element) {
		if (element instanceof MethodPlugin) {
			return null;
		} else if (element instanceof ElementTreeContentProviderUIFolder) {
			return ((ElementTreeContentProviderUIFolder) element).getParent();
		} else if (element instanceof ProcessPackage) {
			ProcessPackage processPackage = (ProcessPackage) element;
			Object parent = processPackage.eContainer();
			if (parent instanceof ProcessComponent) {
				ProcessComponent processComponent = (ProcessComponent) parent;
				return processComponent.getProcess();
			} else {
				return parent;
			}
		} else if (element instanceof MethodPackage) {
			MethodPackage methodPackage = (MethodPackage) element;
			Object parent = methodPackage.eContainer();
			if (parent instanceof MethodPlugin) {
				return parent;
			}
			MethodPackage parentPackage = methodPackage.getParentPackage();
			return parentPackage;
		} else if (element instanceof Artifact) {
			Artifact artifact = (Artifact) element;
			Artifact containerArtifact = artifact.getContainerArtifact();
			if (containerArtifact != null) {
				return containerArtifact;
			} else {
				return UmaUtil.getContentPackage(artifact);
			}
		} else if (element instanceof Practice) {
			Practice practice = (Practice) element;
			return practice.getContainer();
		} else if (element instanceof Discipline) {
			Discipline discipline = (Discipline) element;
			return discipline.getContainer();
		} else if (element instanceof Domain) {
			Domain domain = (Domain) element;
			return domain.getContainer();
		} else if (element instanceof ContentElement) {
			return UmaUtil.getContentPackage((ContentElement) element);
		} else if (element instanceof Process) {
			Process process = (Process) element;
			ProcessPackage parent = (ProcessPackage) process.eContainer();
			if (parent != null) {
				return parent.eContainer();
			}
		} else if (element instanceof Activity) {
			ProcessPackage processPackage = UmaUtil
					.getProcessPackage((ProcessElement) element);
			if (processPackage != null) {
				ProcessPackage parentPackage = (ProcessPackage) processPackage
						.getParentPackage();
				if (parentPackage != null) {
					if (parentPackage instanceof ProcessComponent) {
						ProcessComponent processComponent = (ProcessComponent) parentPackage;
						return processComponent.getProcess();
					}
					List<ProcessElement> processElements = parentPackage
							.getProcessElements();
					for (ProcessElement processElement : processElements) {
						if (processElement instanceof Activity
								&& processElement.getName().equals(
										parentPackage.getName())) {
							return processElement;
						}
					}
				}
			}
		} else if (element instanceof ProcessElement) {
			ProcessPackage processPackage = UmaUtil
					.getProcessPackage((ProcessElement) element);
			if (processPackage != null) {
				if (processPackage instanceof ProcessComponent) {
					ProcessComponent processComponent = (ProcessComponent) processPackage;
					return processComponent.getProcess();
				}
				List<ProcessElement> processElements = processPackage
						.getProcessElements();
				for (ProcessElement processElement : processElements) {
					if (processElement instanceof Activity
							&& processElement.getName().equals(
									processPackage.getName())) {
						return processElement;
					}
				}
				return processPackage;
			}
		}
		return null;
	}

	/**
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(Object)
	 */
	public boolean hasChildren(Object element) {
		return getChildren(element).length > 0;
	}

	public void clear() {
		treeViewer.refresh();
	}

	public void dispose() {
	}

	/**
	 * This method must return the source of the data that is populated in
	 * this tree.
	 * 
	 * Implementors must implement this method.
	 * @return
	 */
	public abstract Object getContentSource();

	protected void insert(Object child, boolean refreshViewer) {
		Object parent = getParent(child);
		while (parent != null) {
			if (insertChild(parent, child)) {
				if (refreshViewer) {
					treeViewer.add(parent, child);
				}
			} else {
				if (refreshViewer) {
					treeViewer.refresh(parent);
				}
				return;
			}
			child = parent;
			parent = getParent(child);
		}
		if (insertChild(getContentSource(), child)) {
			if (refreshViewer) {
				treeViewer.add(getContentSource(), child);
			}
		}
	}

	protected boolean insertChild(Object parent, Object child) {
		Set<Object> children = elementMap.get(parent);
		if (children == null) {
			children = new HashSet<Object>();
			elementMap.put(parent, children);
		}
		return children.add(child);
	}

	protected void replace(Object parent, Object child, Object newChild) {
		insert(newChild, false);
		elementMap.put(newChild, elementMap.get(child));
		elementMap.remove(child);
		Set<Object> children = elementMap.get(parent);
		children.remove(child);
	}

	/**
	 * Implementing classes must override and implement this method.
	 * 
	 * @param element
	 * @param refreshViewer
	 */
	protected abstract void remove(Object element, boolean refreshViewer);

	protected void removeFromSiblings(Object element, Object parent) {
		Set<Object> siblings = elementMap.get(parent);
		if (siblings != null) {
			siblings.remove(element);
		}
	}

	protected void insertUIFolders(MethodPlugin element) {
		ElementTreeContentProviderUIFolder methodContentFolder = new ElementTreeContentProviderUIFolder(
				METHOD_CONTENT, ExtendedImageRegistry.getInstance().getImage(
						LibraryEditPlugin.INSTANCE
								.getImage("full/obj16/Content")), element); //$NON-NLS-1$
		ElementTreeContentProviderUIFolder processesFolder = new ElementTreeContentProviderUIFolder(
				PROCESSES, ExtendedImageRegistry.getInstance().getImage(
						LibraryEditPlugin.INSTANCE
								.getImage("full/obj16/Processes")), element); //$NON-NLS-1$
		Object[] methodPackages = getChildren(element);
		for (int j = 0; j < methodPackages.length; j++) {
			Object methodPackage = methodPackages[j];
			if (methodPackage instanceof ContentPackage
					&& ((ContentPackage) methodPackage).getName().equals(
							PKG_NAME_CONTENT)) {
				Object[] packages = getChildren(methodPackage);
				for (int k = 0; k < packages.length; k++) {
					Object pkg = packages[k];
					if (pkg instanceof ContentPackage) {
						if (((ContentPackage) pkg).getName().equals(
								PKG_NAME_CORE_CONTENT)) {
							replace(
									element,
									pkg,
									new ElementTreeContentProviderUIFolder(
											CONTENT_PACKAGES,
											ExtendedImageRegistry
													.getInstance()
													.getImage(
															LibraryEditPlugin.INSTANCE
																	.getImage("full/obj16/MethodPackages")), methodContentFolder)); //$NON-NLS-1$
						} else if (((ContentPackage) pkg).getName().equals(
								PKG_NAME_CATEGORIES)) {
							ElementTreeContentProviderUIFolder standardCategoriesFolder = new ElementTreeContentProviderUIFolder(
									STANDARD_CATEGORIES,
									ExtendedImageRegistry
											.getInstance()
											.getImage(
													LibraryEditPlugin.INSTANCE
															.getImage("full/obj16/StandardCategories")), methodContentFolder); //$NON-NLS-1$
							ElementTreeContentProviderUIFolder customCategoriesFolder = new ElementTreeContentProviderUIFolder(
									CUSTOM_CATEGORIES,
									ExtendedImageRegistry
											.getInstance()
											.getImage(
													LibraryEditPlugin.INSTANCE
															.getImage("full/obj16/CustomCategories")), methodContentFolder); //$NON-NLS-1$
							Object[] contentPackages = getChildren(pkg);
							for (int l = 0; l < contentPackages.length; l++) {
								Object contentPackage = contentPackages[l];
								if (contentPackage instanceof ContentPackage) {
									String pkgName = ((ContentPackage) contentPackage)
											.getName();
									if (pkgName
											.equals(PKG_NAME_CUSTOM_CATEGORIES)) {
										insert(customCategoriesFolder, false);
										elementMap.put(customCategoriesFolder,
												elementMap.get(contentPackage));
									} else if (pkgName
											.equals(PKG_NAME_DISCIPLINES)) {
										insert(standardCategoriesFolder, false);
										replace(
												element,
												contentPackage,
												new ElementTreeContentProviderUIFolder(
														DISCIPLINES,
														ExtendedImageRegistry
																.getInstance()
																.getImage(
																		LibraryEditPlugin.INSTANCE
																				.getImage("full/obj16/Disciplines")), standardCategoriesFolder)); //$NON-NLS-1$
									} else if (pkgName.equals(PKG_NAME_DOMAINS)) {
										insert(standardCategoriesFolder, false);
										replace(
												element,
												contentPackage,
												new ElementTreeContentProviderUIFolder(
														DOMAINS,
														ExtendedImageRegistry
																.getInstance()
																.getImage(
																		LibraryEditPlugin.INSTANCE
																				.getImage("full/obj16/Domains")), standardCategoriesFolder)); //$NON-NLS-1$
									} else if (pkgName
											.equals(PKG_NAME_WORK_PRODUCT_TYPES)) {
										insert(standardCategoriesFolder, false);
										replace(
												element,
												contentPackage,
												new ElementTreeContentProviderUIFolder(
														WORK_PRODUCT_TYPES,
														ExtendedImageRegistry
																.getInstance()
																.getImage(
																		LibraryEditPlugin.INSTANCE
																				.getImage("full/obj16/WorkProductTypes")), standardCategoriesFolder)); //$NON-NLS-1$
									} else if (pkgName
											.equals(PKG_NAME_ROLESETS)) {
										insert(standardCategoriesFolder, false);
										replace(
												element,
												contentPackage,
												new ElementTreeContentProviderUIFolder(
														ROLESETS,
														ExtendedImageRegistry
																.getInstance()
																.getImage(
																		LibraryEditPlugin.INSTANCE
																				.getImage("full/obj16/Roles")), standardCategoriesFolder)); //$NON-NLS-1$
									} else if (pkgName.equals(PKG_NAME_TOOLS)) {
										insert(standardCategoriesFolder, false);
										replace(
												element,
												contentPackage,
												new ElementTreeContentProviderUIFolder(
														TOOLS,
														ExtendedImageRegistry
																.getInstance()
																.getImage(
																		LibraryEditPlugin.INSTANCE
																				.getImage("full/obj16/Tools")), standardCategoriesFolder)); //$NON-NLS-1$
									}
								}
							}
							elementMap.remove(pkg);
						}
					} else if (pkg instanceof ProcessPackage
							&& ((ProcessPackage) pkg).getName().equals(
									PKG_NAME_CAPABILITY_PATTERNS)) {
						replace(
								element,
								pkg,
								new ElementTreeContentProviderUIFolder(
										CAPABILITY_PATTERNS,
										ExtendedImageRegistry
												.getInstance()
												.getImage(
														LibraryEditPlugin.INSTANCE
																.getImage("full/obj16/CapabilityPatterns")), processesFolder)); //$NON-NLS-1$
					}
				}
				Set<Object> children = (Set<Object>) elementMap.get(element);
				children.remove(methodPackage);
			} else if (methodPackage instanceof ProcessPackage
					&& ((ProcessPackage) methodPackage).getName().equals(
							PKG_NAME_DELIVERY_PROCESSES)) {
				replace(
						element,
						methodPackage,
						new ElementTreeContentProviderUIFolder(
								DELIVERY_PROCESSES,
								ExtendedImageRegistry
										.getInstance()
										.getImage(
												LibraryEditPlugin.INSTANCE
														.getImage("full/obj16/DeliveryProcesses")), processesFolder)); //$NON-NLS-1$
			}
		}
	}

	/**
	 * Inserts the UI folders into the search result tree.
	 */
	protected void insertUIFolders(Object inputElement) {
		Object[] elements = getChildren(inputElement);
		for (int i = 0; i < elements.length; i++) {
			Object element = elements[i];
			if (element instanceof MethodPlugin) {
				insertUIFolders((MethodPlugin) element);
			}
		}
	}

	/**
	 * Getter for the tree viewer
	 * @return TreeViewer
	 */
	public TreeViewer getTreeViewer() {
		return treeViewer;
	}

	/**
	 * Setter for the tree viewer. Classes that implement this abstract class must call
	 * this method and set the tree viewer from within their inputChanged method.
	 * 
	 * @param treeViewer
	 */
	public void setTreeViewer(TreeViewer treeViewer) {
		this.treeViewer = treeViewer;
	}

	/**
	 * Getter for the elementMap
	 * 
	 * @return elementMap
	 */
	public Map<Object, Set<Object>> getElementMap() {
		return elementMap;
	}

}
