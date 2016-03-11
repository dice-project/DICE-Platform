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
package org.eclipse.epf.library.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.persistence.ILibraryResourceSet;
import org.eclipse.epf.persistence.MultiFileSaveUtil;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.util.UmaUtil;


/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public final class ModelStorage {
	
	/**
	 * return the MethodPlugins in the current MethodLibrary
	 * @return List
	 */
	public static List<MethodPlugin> getBaseModels() {
		MethodLibrary lib = LibraryService.getInstance().getCurrentMethodLibrary();
		if (lib != null)
			return new ArrayList<MethodPlugin>(lib.getMethodPlugins());
		return Collections.emptyList();
	}

	/**
	 * create a new MethodLibrary and return the associated Resource
	 * 
	 * @param resourceSet MultiFileResourceSetImpl
	 * @param name String
	 * @param dir String
	 * @param bMultiple boolean
	 * @return Resource
	 * @throws Exception
	 */
	public static Resource newLibrary(ILibraryResourceSet resourceSet,
			String name, String dir, boolean bMultiple) throws Exception {
		String lib_filename = null;
		// Map options = new
		// HashMap(MultiFileResourceSetImpl.DEFAULT_SAVE_OPTIONS);
		if (bMultiple) {
			lib_filename = MultiFileSaveUtil.DEFAULT_LIBRARY_MODEL_FILENAME;
			// options.put(MultiFileXMISaveImpl.MULTI_FILE, Boolean.TRUE);
		} else {
			lib_filename = name;
		}
		resourceSet.unload();
		Resource res = resourceSet.createResource(URI.createFileURI(dir
				+ File.separator + lib_filename));
		MethodLibrary lib = UmaFactory.eINSTANCE.createMethodLibrary();
		lib.setName(name);
		res.getContents().add(lib);
		resourceSet.save(null);
		return res;
	}
	
	/**
	 * initialize a new method plugin
	 * @param emptyModel MethodPlugin
	 * @return MethodPlugin
	 */
	public static MethodPlugin initialize(MethodPlugin emptyModel) {
		emptyModel.setUserChangeable(Boolean.TRUE);

		createContentPackages(emptyModel, ModelStructure.DEFAULT_DOMAIN_PATH);
		createContentPackages(emptyModel,
				ModelStructure.DEFAULT.disciplineDefinitionPath);
		createContentPackages(emptyModel, ModelStructure.DEFAULT.roleSetPath);
		createContentPackages(emptyModel,
				ModelStructure.DEFAULT.coreContentPath);
		createContentPackages(emptyModel,
				ModelStructure.DEFAULT.workProductTypePath);
		createContentPackages(emptyModel, ModelStructure.DEFAULT.toolPath);
		createContentPackages(emptyModel,
				ModelStructure.DEFAULT.standardCategoryPath);

		ContentPackage contentPkg = createContentPackages(emptyModel,
				ModelStructure.DEFAULT.customCategoryPath);
		TngUtil.createRootCustomCategory(contentPkg);

		// create DeliveryProcesses process package
		//
		ProcessPackage pkg = UmaFactory.eINSTANCE.createProcessPackage();
		pkg.setName(ModelStructure.DEFAULT.deliveryProcessPath[0]);
		emptyModel.getMethodPackages().add(pkg);

		// create ProcessContributions process package
		//
		ModelStructure.createProcessContributionPackage(emptyModel);

		// create CapabilityPatterns process package
		//
		pkg = UmaFactory.eINSTANCE.createProcessPackage();
		int len = ModelStructure.DEFAULT.capabilityPatternPath.length - 1;
		pkg.setName(ModelStructure.DEFAULT.capabilityPatternPath[len]);
		String[] path = new String[len];
		System.arraycopy(ModelStructure.DEFAULT.capabilityPatternPath, 0, path,
				0, len);
		ContentPackage contPkg = UmaUtil.findContentPackage(emptyModel, path);
		contPkg.getChildPackages().add(pkg);

		// // create inherited domains
		// //
		// List allBase = Misc.getAllBase(emptyModel);
		// // System.out.println("ModelStorage.initialize(): allBase="+allBase);
		// for(Iterator iter = allBase.iterator(); iter.hasNext();) {
		// MethodPlugin basePlugin = (MethodPlugin) iter.next();
		// ContentPackage baseDomainPkg = UmaUtil.findContentPackage(basePlugin,
		// ModelStructure.DEFAULT_DOMAIN_PATH);
		// InheritedCategoryPackageListener listener = new
		// InheritedDomainPackageListener(domainPkg);
		// baseDomainPkg.eAdapters().add(listener);
		// for (Iterator iterator =
		// baseDomainPkg.getContentElements().iterator(); iterator.hasNext();) {
		// Object element = (Object) iterator.next();
		// if(element instanceof Domain) {
		// ContentCategory category = (ContentCategory) element;
		// if(category.getVariabilityBasedOnElement() == null) {
		// // this category is not a inherited one, can be added.
		// //
		// domainPkg.getContentElements().add(listener.createInherited((ContentCategory)
		// element));
		// }
		// }
		// }
		// }

		// TODO: test on-fly creation of category contributor
		// MethodPluginItemProvider itemProvider = (MethodPluginItemProvider)
		// TngAdapterFactory.INSTANCE.getNavigatorView_ComposedAdapterFactory().adapt(emptyModel,
		// ITreeItemContentProvider.class);
		// itemProvider.addInheritedCategories();

		return emptyModel;
	}

	/**
	 * @param path
	 * @param emptyModel
	 */
	private static ContentPackage createContentPackages(MethodPlugin model,
			String[] path) {
		List list = model.getMethodPackages();
		ContentPackage pkg = UmaUtil.findContentPackage(list, path[0]);
		if (pkg == null) {
			pkg = UmaFactory.eINSTANCE.createContentPackage();
			pkg.setName(path[0]);
			list.add(pkg);
		}
		for (int i = 1; i < path.length; i++) {
			list = pkg.getChildPackages();
			pkg = UmaUtil.findContentPackage(list, path[i]);
			if (pkg == null) {
				pkg = UmaFactory.eINSTANCE.createContentPackage();
				pkg.setName(path[i]);
				list.add(pkg);
			}
		}
		return pkg;
	}

	/**
	 * load the containment proxies for the object
	 * @param obj EObject
	 */
	public static void loadContainmentProxies(EObject obj) {
		// for(TreeIterator iter = obj.eAllContents(); iter.hasNext();) {
		List list = obj.eContents();
		for (int i = 0; i < list.size(); i++) {
			InternalEObject o = (InternalEObject) list.get(i);
			// InternalEObject o = (InternalEObject)iter.next();
			if (o.eProxyURI() != null) {
				System.out.println("proxy URI = " + o.eProxyURI()); //$NON-NLS-1$
				ResourceSet resourceSet = o.eResource().getResourceSet();
				Resource res = resourceSet.getResource(o.eProxyURI(), true);
				InternalEObject resolvedObj = null;
				try {
					resolvedObj = (InternalEObject) res.getContents().get(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("resolvedObj = " + resolvedObj); //$NON-NLS-1$
				System.out.println("-----------------------------------"); //$NON-NLS-1$
				if (resolvedObj != null) {
					EcoreUtil.replace(o, resolvedObj);
					((BasicEList) res.getContents()).setData(1,
							new Object[] { resolvedObj });         
					o = resolvedObj;
				}
			}
			loadContainmentProxies(o);
		}
	}

	/**
	 * load all proxies for the object
	 * @param obj EObject
	 */
	public static void loadAllProxies(EObject obj) {
		for (Iterator iter = obj.eAllContents(); iter.hasNext();) {
			EObject element = (EObject) iter.next();
			for (Iterator iterator = element.eCrossReferences().iterator(); iterator
					.hasNext();) {
				iterator.next();
			}
		}

		// loadContainmentProxies(obj);
		// loadCrossReferenceProxies(obj);
	}

	/**
	 * load all cross reference proxies for the object
	 * @param obj EObject
	 */
	public static void loadCrossReferenceProxies(EObject obj) {
		List list = obj.eCrossReferences();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			EObject o = (EObject) list.get(i);
			loadCrossReferenceProxies(o);
		}
	}

	/**
	 * find a MethodElement with the given element name in the element list
	 * @param methodElements The element list
	 * @param name String
	 * @return MethodElement
	 */
	public static MethodElement findMethodElement(List methodElements,
			String name) {
		int size = methodElements.size();
		for (int i = 0; i < size; i++) {
			MethodElement element = (MethodElement) methodElements.get(i);
			if (name.equals(element.getName()))
				return element;
		}
		return null;
	}

	/**
	 * get of index int he list location for the element with the given name.
	 * @param methodElements List
	 * @param name String
	 * @return int
	 */
	public static int indexOf(List methodElements, String name) {
		int size = methodElements.size();
		for (int i = 0; i < size; i++) {
			MethodElement element = (MethodElement) methodElements.get(i);
			if (name.equals(element.getName()))
				return i;
		}
		return -1;
	}

}
