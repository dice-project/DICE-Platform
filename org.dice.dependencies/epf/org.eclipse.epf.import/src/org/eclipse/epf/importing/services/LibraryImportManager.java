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

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epf.authoring.ui.views.LibraryView;
import org.eclipse.epf.common.ui.util.MsgDialog;
import org.eclipse.epf.export.services.DiagramHandler;
import org.eclipse.epf.importing.ImportPlugin;
import org.eclipse.epf.importing.ImportResources;
import org.eclipse.epf.library.ILibraryResourceManager;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.meta.IMetaDef;
import org.eclipse.epf.library.edit.meta.TypeDefUtil;
import org.eclipse.epf.library.edit.util.MethodPluginPropUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.services.SafeUpdateController;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.persistence.MultiFileResourceSetImpl;
import org.eclipse.epf.persistence.MultiFileXMIHelperImpl;
import org.eclipse.epf.services.IFileBasedLibraryPersister;
import org.eclipse.epf.services.IFileManager;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.services.Services;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.DiagramElement;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodElementProperty;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.ExtendedAttribute;
import org.eclipse.epf.uma.util.UmaUtil;


/**
 * Manages the importing of a library.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class LibraryImportManager {

	private Map<String, String> renamePluginMap = new HashMap<String, String>();
	private Map renameElementMap = new HashMap();
	private Map setElementMap = null;

	private LibraryDiffManager diffMgr;
	private ElementDiffTree rootTree;
	
	private boolean configFolderError = false;

	//protected AdapterFactoryEditingDomain editingDomain = null;

	//protected AdapterFactoryContentProvider afcp = null;

	private MethodLibrary baseLibrary = null;
	private MethodLibrary importingLibrary = null;

	// guid of replaced element id to the element in the base library
	// we need this to redirect the reference of new elements from the importing
	// libraty to the base library
	private Map replacedElementMap = new HashMap();

	// keep the new elements, we need to iterate their feature value and
	// redirect the valeu element
	// from the importing library to the base library
	private List newElements = new ArrayList();
		
	// deleted elements,
	private List deletedElements = new ArrayList();
	
	private boolean debug = ImportPlugin.getDefault().isDebugging();
	
	// list of user selected items to be imported
	List checkedList = new ArrayList();
	
	// flag to indicate the file cheout status
	IStatus fileCheckedOutStatus = null;
	
	// resource file path for new resoruces
	List newResources = new ArrayList();
	
	ResourceScanner resScanner;
	
	private DiagramHandler diagramHandler;
	
	/**
	 * Creates a new instance.
	 */
	public LibraryImportManager(LibraryDiffManager diffMgr,
			List checkedItems) {
		this.diffMgr = diffMgr;
		this.rootTree = diffMgr.getDiffTree();
		
		if ( checkedItems != null ) {
			// checkedList contains a list of ElementDiffTree objects
			// get the packages as well
			this.checkedList.addAll(checkedItems);
			for (Iterator it = checkedItems.iterator(); it.hasNext(); ) {
				ElementDiffTree diffTree = (ElementDiffTree) it.next();
				MethodElement e = diffTree.getBaseElement();
				if ( e != null ) {
					checkedList.add(e);
				}
								
				if ( e instanceof ProcessComponent ) {
					LibraryUtil.getAllChildPackages((MethodPackage)e, checkedList);
				}
				
				// also incldue the hidden root custom package if the package is the CustomCategories package
				// 150895 - Import Config: CustomCategory did not sync up by import
				if ( e instanceof MethodPackage ) {
					List children = ((MethodPackage)e).getChildPackages();
					if ( children.size() == 1 ) {
						MethodPackage pkg  = (MethodPackage)children.get(0);
						if ( TngUtil.isRootCutomCategoryPackage(pkg) ) {
							checkedList.add(pkg);
						}
					}
				}
				
				e = diffTree.getImportElement();
				if ( e != null ) {
					checkedList.add(e);
				}
				
				if ( e instanceof ProcessComponent ) {
					LibraryUtil.getAllChildPackages((MethodPackage)e, checkedList);
				}

				
				// for new item, add all the sub packages
				if ( diffTree.isNew() ) {
					if ( e instanceof MethodPlugin ) {
						checkedList.addAll(LibraryUtil.getAllPackages((MethodPlugin)e));
					} else if ( e instanceof MethodPackage ) {
						LibraryUtil.getAllChildPackages((MethodPackage)e, checkedList);
					} 
				}
			}
			
		} else {
			checkedList = null;
		}
		
		
		init();

		// Get the current library.
		baseLibrary = (MethodLibrary) this.rootTree.getBaseElement();
		importingLibrary = (MethodLibrary) this.rootTree
				.getImportElement();

		// create the resource scanner
		resScanner = new ResourceScanner(
				LibraryUtil.getLibraryRootPath(importingLibrary), 
				LibraryUtil.getLibraryRootPath(baseLibrary), renamePluginMap);
			
		diagramHandler = new DiagramHandler(
				LibraryUtil.getLibraryRootPath(importingLibrary), 
				LibraryUtil.getLibraryRootPath(baseLibrary)) {				
			//[0]: sourceFile
			//[1]: targetFile
			protected File[] getFiles(org.eclipse.epf.uma.MethodElement elem) {
				return super.getFiles(elem, false);
			}
			
		};
		
	}
		
	private void init() {
//		// Set the tree content.
//		List factories = new ArrayList();
//		factories.add(new ResourceItemProviderAdapterFactory());
//		factories.add(new UmaItemProviderAdapterFactory());
//		factories.add(new ReflectiveItemProviderAdapterFactory());
//
//		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(
//				factories);
//		afcp = new AdapterFactoryContentProvider(adapterFactory);
//
//		BasicCommandStack commandStack = new BasicCommandStack();
//
//		// Create the editing domain with a special command stack.
//		editingDomain = new AdapterFactoryEditingDomain(adapterFactory,
//				commandStack, new HashMap());
//
	}

	private boolean isSelected(ElementDiffTree diffTree) {
		if ( checkedList == null || checkedList.contains(diffTree) ) {
			return true;
		}
		
		MethodElement e = diffTree.getBaseElement();		
		if ( e == null ) {
			e = diffTree.getImportElement();
		}
		
		return isSelected(e);
	}
	
	private boolean isSelected(MethodElement e) {
		
		if ( checkedList == null || 
				e instanceof ContentDescription || 
				e.eContainer() instanceof ContentDescription || 
				e instanceof MethodLibrary ) {
			return true;
		}
		
		if ( checkedList.size() == 0 ) {
			return false;
		}
		
		if ( e instanceof MethodConfiguration ) {
			return checkedList.contains(e);
		}
		
		MethodElement o = (MethodElement)LibraryUtil.getSelectable(e);
		if ( o == null ) {
			return false;
		} 
		
		if ( o instanceof MethodLibrary ) {
			return true;
		}
		
		return checkedList.contains(o);
	}
	
	/**
	 * Does merge from the import library into the base library.
	 */
	public void doMerge(boolean replaceExisting, IProgressMonitor monitor) throws Exception {
		configFolderError = false;
		
		if ( debug ) {
			System.out.println("Merging configuration ..."); //$NON-NLS-1$
		}
			
		// Save the library path before detaching the resource.

		if ( debug ) {
			System.out.println("loading library ..."); //$NON-NLS-1$
		}
		
		List unlockedPlugins = unlockPlugins();
		if (! unlockedPlugins.isEmpty() && (fileCheckedOutStatus != null)
				&& !fileCheckedOutStatus.isOK()) {
			// log error
			fileCheckOutError();
			return;
		}

		LibraryUtil.loadAll(baseLibrary);
		LibraryUtil.loadAll(importingLibrary);

		// Detach the new library from the current resource so it can be
		// added to a new Library Processor instance.
		LibraryUtil.detachFromResource(importingLibrary);

		// for test
		importingLibrary.setName("ImportingLib: " + importingLibrary.getName()); //$NON-NLS-1$
		
		if ( debug ) {
			System.out.println("Process configuration diff tree ..."); //$NON-NLS-1$
		}

		// before processing, save the original resources 
		// so that we can find the newly added resources
		List oldResources = new ArrayList(
				baseLibrary.eResource().getResourceSet().getResources());
		
		// Iterate each element and the necessary merges.
		processDiffTree(rootTree, replaceExisting);

		handleSetElements();
		
		if ( debug ) {
			System.out.println("perform integrity checking ..."); //$NON-NLS-1$
		}

		// perform an integrity check of the library
		// fix any danfling elements due to package/element replacement
		doIntegrityCheck();

		// now process the new resources and mark them dirst so that 
		// the data will be saved again. 
		// This step is critical since the first round only created the data structure
		// some of the cross-referenced elements might be lost on the first saving
		// for example, when create a method configuration with references to a new plugin,
		// which are not saved yet, those references will be lost.
		// 145891 - Import Configuration: default config is loss after import
		handleNewResources(oldResources);
		
		handleNameReplace(renameElementMap);
		
		diagramHandler.postRegisterElements();
		
		// get all the modified resources and resource files
		// check CM for file check-out
		if ( debug ) {
			System.out.println("check out files ..."); //$NON-NLS-1$
		}
		
		// clear resources for deleted elements
		deleteResoruces();
	
		checkModifiedFiles();
				
		if ( fileCheckedOutStatus.isOK() ) {
		 
			if ( debug ) {
				System.out.println("copying resource files ..."); //$NON-NLS-1$
			}
			resScanner.execute();
			diagramHandler.execute();

			if ( debug ) {
				System.out.println("saving library ..."); //$NON-NLS-1$
			}
			
			if (! configFolderError) {
				if (unlockedPlugins.size() > 0) {
					lockUnlockedPlugins(unlockedPlugins);
				}
				LibraryUtil.saveLibrary(baseLibrary, false, false);
			}
		} 
		else {
			fileCheckOutError();
		}	
// if ( debug ) {
// System.out.println("copying resource files ..."); //$NON-NLS-1$
// }
//
// // Copy the resource files in the current library to the new library.
// // For simplicity sake, copy all resource files if the files do not
// // exist in the target library or if the files are newer.
// String includes = "resources/**, **/resources/**"; //$NON-NLS-1$
//
// LayoutResources.copyDir(srcDir, destDir, includes, null);

		if ( debug ) {
			System.out.println("Merging configuration done..."); //$NON-NLS-1$
		}

	}
	
	private void fileCheckOutError() {
		// log error
		SafeUpdateController.syncExec(new Runnable() {
			public void run() {
				String title = ImportResources.importConfigWizard_title; 
				String msg = ImportResources.ImportConfigurationWizard_ERR_Import_configuration; 
				new MsgDialog(ImportPlugin.getDefault())
					.displayError(title, msg, fileCheckedOutStatus);
				}
		});

		if ( debug ) {
			System.out.println("Checkout files failed ..."); //$NON-NLS-1$
		}
	}
		
	private void handleNewResources(List oldResources) {
		for (Iterator it = baseLibrary.eResource().getResourceSet()
				.getResources().iterator(); it.hasNext(); ) {
			Resource res = (Resource)it.next();
			if ( !oldResources.contains(res) ) {
				logNewResource(res);
			}
		}	
	}
	
	
	/**
	 * Processes the diff tree. Only need to do the first level since the
	 * replace or add is done at the method package level.
	 */
	private void processDiffTree(ElementDiffTree diffTree,
			boolean replaceExisting) throws Exception {
		if (diffTree == null || !isSelected(diffTree) ) {
			return;
		}

		// There is a change.
		int state = diffTree.getDiffState();
		MethodElement base = diffTree.getBaseElement();
		MethodElement imp = diffTree.getImportElement();
		if (replaceExisting && (state == ElementDiffTree.DIFF_STATE_CHANGED) ) {

			if ( (base instanceof MethodConfiguration)) {
				// replace the configuration by adding the new packages into the
				// configuration
				doReplaceConfiguration((MethodConfiguration) base, (MethodConfiguration) imp);
			}
			else if ( (base instanceof MethodPlugin)) {
				// Replace the plugin with the non-package features.
				doReplacePlugin((MethodPlugin) base, (MethodPlugin) imp);
			}
			else if ( (base instanceof MethodPackage)) {
				// Replace the package.
				doReplacePackage((MethodPackage) base, (MethodPackage) imp);
			}
			else
			{
				if (debug ) {
					System.out.println("What is this: " + LibraryUtil.getTypeName(base)); //$NON-NLS-1$
				}
			}
		} else if (state == ElementDiffTree.DIFF_STATE_NEW) {
			// Add the new package from import library.
			doAdd(diffTree.getBaseParentElement(), diffTree.getImportElement()); 
		}

		// Process the children recursively if it's not a new one.
		if (state != ElementDiffTree.DIFF_STATE_NEW) {
			List children = diffTree.getChildren();
			if (children != null && children.size() > 0) {
				for (Iterator it = children.iterator(); it.hasNext();) {
					processDiffTree((ElementDiffTree) it.next(),
							replaceExisting);
				}
			}
		}
	}

	/**
	 * update the configuration by replacing the non-package features, and
	 * adding the new packages into the configuration
	 * 
	 * @param oldObj
	 *            MethodConfiguration
	 * @param newObj
	 *            MethodConfiguration
	 */
	private void doReplaceConfiguration(MethodConfiguration oldObj, MethodConfiguration newObj)
	{
		if ( isReplaced(oldObj) ) {
			return;
		}
		setReplaced(oldObj);
		
		List features = LibraryUtil.getStructuralFeatures(oldObj);
//		List properties = oldObj.getInstanceProperties();
		if (features != null) {
			for (int i = 0; i < features.size(); i++) {
				EStructuralFeature feature = (EStructuralFeature) features.get(i);
				// don't delete old feature values
				Object newValue = newObj.eGet(feature);
				setFeatureValue(oldObj, feature, newValue, false);
			}
		}
	}

// private void mergeElementList(List source, List target)
// {
// List ids = new ArrayList();
// for ( Iterator it = target.iterator(); it.hasNext(); )
// {
// MethodElement e = (MethodElement)it.next();
// ids.add(e.getGuid());
// }
//
// for ( Iterator it = source.iterator(); it.hasNext(); )
// {
// MethodElement e = (MethodElement)it.next();
// if ( !ids.contains(e.getGuid()) )
// {
// target.add(e);
// }
// }
// }

	/**
	 * replace the plugin features, but not the plugin object and it's packages
	 * 
	 * @param oldObj
	 *            MethodPlugin
	 * @param newObj
	 *            MethodPlugin
	 */
	private void doReplacePlugin(MethodPlugin oldObj, MethodPlugin newObj) {
		if ( isReplaced(oldObj) ) {
			return;
		}
		setReplaced(oldObj);
		
		List features = LibraryUtil.getStructuralFeatures(oldObj);
//		List properties = oldObj.getInstanceProperties();
		if (features != null) {
			for (int i = 0; i < features.size(); i++) {
				EStructuralFeature feature = (EStructuralFeature) features.get(i);
				if ( feature != UmaPackage.eINSTANCE.getMethodPlugin_MethodPackages() )
				{
					Object newValue = newObj.eGet(feature);
					setFeatureValue(oldObj, feature, newValue, true);
				}
				/* No, don't do this, fix the diff manager to include the root package instead
				 * 150895 - Import Config: CustomCategory did not sync up by import
				else {
					// custom categories package needs special handing
					// we need to start with the root custom category otherwise
					// all child categories will be lost
					MethodPackage oldPkg = UmaUtil.findContentPackage(oldObj, ModelStructure.DEFAULT_CUSTOM_CATEGORY_PATH);
// ContentPackage newPkg = UmaUtil.findContentPackage(newObj,
// ModelStructure.DEFAULT.DEFAULT_CUSTOM_CATEGORY_PATH);
					setReplaced(oldPkg);
					
					// find the root custom category and start from there
					CustomCategory oldCC = TngUtil.getRootCustomCategory(oldObj);
					CustomCategory newCC = TngUtil.getRootCustomCategory(newObj);
					oldPkg = (MethodPackage)oldCC.eContainer();
					setReplaced(oldPkg);
					doReplaceElement(oldCC, newCC);
				}
				*/
			}
		}

	}



	/**
	 * Replaces the atrributes and contained elements, but not the child
	 * packages
	 */
	private void doReplacePackage(MethodPackage oldObj, MethodPackage newObj) {
		if ( isReplaced(oldObj) ) {
			return;
		}
		setReplaced(oldObj);

		if ( debug ) {
			System.out.println("Replacing package " + LibraryUtil.getTypeName(oldObj)); //$NON-NLS-1$
		}
		List features = LibraryUtil.getStructuralFeatures(oldObj);
//		List properties = oldObj.getInstanceProperties();
		
		boolean isProcessPackage = oldObj instanceof ProcessPackage;
		
		if (features != null) {
			for (int i = 0; i < features.size(); i++) {
				EStructuralFeature feature = (EStructuralFeature) features.get(i);
				if ( feature == UmaPackage.eINSTANCE.getMethodPackage_ChildPackages() &&
					 !isProcessPackage)
				{					
					continue;
				}

				Object newValue = newObj.eGet(feature);
				setFeatureValue(oldObj, feature, newValue, true);
			}
		}

	}

// private MethodElement objOld = null;
// private MethodElement objNew = null;
// private void testIt() {
// if ( objOld != null ) {
// System.out.println("Old container =" + objOld.eContainer());
// System.out.println("New container =" + objNew.eContainer());
// if ( objOld.eContainer() == null ) {
// System.out.println("old is gone!");
// }
// if ( objNew.eContainer() == null ) {
// System.out.println("new is gone!");
// }
// }
// }
	
	/**
	 * replace the element by replacing the feature values, but don't replace
	 * the object if it is marked as not replaceable. recursive the feature
	 * value and replace it
	 * 
	 * @param oldObj
	 *            MethodPlugin
	 * @param newObj
	 *            MethodPlugin
	 */
	private void doReplaceElement(MethodElement oldObj, MethodElement newObj) {
		
		// check if the element is already replaced or not
		if ( isReplaced(oldObj) ) {
			return;
		}

		if ( diffMgr.selectable(oldObj) ) {
			return;
		}

		if ( !isSelected(oldObj) ) {
			return;
		}
		
		// if content description, check if it's changed or not
		if ( (oldObj instanceof ContentDescription) && (newObj instanceof ContentDescription) ) {
			if ( LibraryUtil.isIdentical((ContentDescription)oldObj, (ContentDescription)newObj) ) {
				if (debug ) {
					Resource res = oldObj.eResource();
					if ( res != null ) {
						System.out.println("Identical element not replaced: " + res.getURI().toFileString() ); //$NON-NLS-1$
					}
				}
				
				// even though the content is the same, we still need to copy the resources
				scanResources(oldObj, true);
				return;
			} 
		}
		
		// set repalced first to avoid calling into the same object recursively
		setReplaced(oldObj);
		
		if ( debug ) {
			System.out.println("Replacing element " + LibraryUtil.getTypeName(oldObj)); //$NON-NLS-1$
		}
		List features = LibraryUtil.getStructuralFeatures(oldObj);
//		List properties = oldObj.getInstanceProperties();
		if (features != null) {
			for (int i = 0; i < features.size(); i++) {
				EStructuralFeature feature = (EStructuralFeature) features.get(i);

				// package structure is handled by the diff tree
				// so we don't process the related feature
				if ( feature == UmaPackage.eINSTANCE.getMethodPlugin_MethodPackages()
						|| feature == UmaPackage.eINSTANCE.getMethodPackage_ChildPackages() 
						|| feature.isDerived() )
				{
					continue;
				}

				Object newValue = newObj.eGet(feature);
				setFeatureValue(oldObj, feature, newValue, true);
			}
		}

	}

	/**
	 * set the new value from the importing library to the old object in the
	 * base library. if the value or onle of values are not replaceable, suach
	 * as MethodLibrary, MethodPlugin, MethodPackage, etc, those values should
	 * be substitued with the one in the old library
	 * 
	 * @param element
	 * @param feature
	 * @param value
	 */
	

	static MethodElement newCont = null;
	static MethodElement newElem = null;
	private void setFeatureValue(MethodElement element, EStructuralFeature feature, Object newValue, boolean doDelete)
	{		
		if ( canIgnore(feature) ) {
			return;
		}
		
		boolean oldNotify = element.eDeliver();
		try
		{
			// turn off notifications to avoid possible deadlock or thread issue
			element.eSetDeliver(false);
			
			if ( canReset(feature) ) {
				element.eSet(feature, newValue);
			}
			else if ( feature.isMany() && newValue instanceof List)
			{
				List oldValue = (List)element.eGet(feature);
				if (feature.getName().equals("methodElementProperty")) {		//$NON-NLS-1$
					setMepFeatureValue(element, feature, oldValue, (List) newValue);
					return;
				}
				ElementListDiff diff = new ElementListDiff(oldValue, (List)newValue);
				if ( doDelete && diff.deletedItems.size() > 0 ) {
					oldValue.removeAll(diff.deletedItems);
					logRemovedElements(diff.deletedItems);
					if ( debug ) {
						System.out.println("  Deleting feature value, feature: " + feature.getName() + ",  element: "  //$NON-NLS-1$ //$NON-NLS-2$
								+ LibraryUtil.getTypeName(element) + ", values: " +  diff.deletedItems); //$NON-NLS-1$
					}
				}

				if ( diff.newItems.size() > 0 ) {					
					// save the new elements and fix the references later
					// note: the newly added feature values might not be REALLY
					// new element to the base library
					// it might be just a newly added reference, so check and
					// make sure
					// if it's old element, do replace again
					for ( Iterator itn = diff.newItems.iterator(); itn.hasNext(); ) {
						MethodElement newObj = (MethodElement)itn.next();
						
						// only process the new object if it's selected
						if ( isSelected(newObj) ) {
							MethodElement oldObj = diffMgr.getExistingElement(newObj.getGuid());
							if ( oldObj == null ) {
								oldValue.add(newObj);
								//logNewElement(newObj);
								logNewElementUnderExistingPlugin(newObj);
							} else {
								oldValue.add(oldObj);
								doReplaceElement(oldObj, newObj);
							}
						}
					}
					
					if ( debug ) {
						System.out.println("  Adding feature value, feature: " + feature.getName() + ",  element: "  //$NON-NLS-1$ //$NON-NLS-2$
								+ LibraryUtil.getTypeName(element) + ", values: " +  diff.newItems); //$NON-NLS-1$
					}
					
				}

				if ( diff.oldNewMap.size() > 0 ) {
					for ( Iterator it = diff.oldNewMap.entrySet().iterator(); it.hasNext(); ) {
						Map.Entry entry = (Map.Entry) it.next();
						MethodElement oldObj = (MethodElement)entry.getKey();
						
						// only process the new object if it's selected
						if ( !isSelected(oldObj) ) {
							continue;
						}
						
						MethodElement newObj = (MethodElement)entry.getValue();
						if ( canReset(oldObj) ) {
							if ( debug ) {
								System.out.println("  Resetting feature value, feature: " + feature.getName() + ",  element: "  //$NON-NLS-1$ //$NON-NLS-2$
										+ LibraryUtil.getTypeName(element) + ", value: " +  LibraryUtil.getTypeName(oldObj)); //$NON-NLS-1$
							}

							EcoreUtil.replace(element, feature, oldObj, newObj);
							logResetElement(newObj);
						} else {
							doReplaceElement(oldObj, newObj); 
						}
					}
				}
			}
			else if (newValue instanceof MethodElement)
			{
				// if the old and new element are the same,
				// only replace the feature value
				MethodElement o = (MethodElement)element.eGet(feature);
				MethodElement n = (MethodElement)newValue;
				
				// only process the new object if it's selected
				if ( (o == null || isSelected(o)) && isSelected(n) ) {

					if ( debug ) {
						System.out.println("  Replacinging feature value, feature: " + feature.getName() + ",  element: "  //$NON-NLS-1$ //$NON-NLS-2$
								+ LibraryUtil.getTypeName(element) + ", value: " +  LibraryUtil.getTypeName(o)); //$NON-NLS-1$
					}
	
					if ( newValue instanceof ContentDescription ) {
						// always replace content description (not reset) to
						// reserve the existing resource (xmi file)
						// since the contentDescription is unique the a method
						// element, so we don't need to compare the guid
												
						// if both object has no container, there is no such
						// description, ignore it
						if ( o.eContainer() != null || n.eContainer() != null ) {
							if ( o.eContainer() == null ) {
								element.eSet(feature, o);
							}
							doReplaceElement(o, n);						
						}
					}
					// if the element can be replaced, such as for
					// ContentDescription element
					// set the value directly
					else if ( canReset(n) ) {
						element.eSet(feature, newValue);
						//logNewElement(n);
						logNewElementUnderExistingPlugin(n);
					}
					else
					{
						if ( o != null && n != null && o.getGuid().equals(n.getGuid()) ) {
							 doReplaceElement(o, n);
						}
						else {													
							registerSetElement(element, feature, newValue);
							
							element.eSet(feature, newValue);
							//logNewElement(n);
							logNewElementUnderExistingPlugin(n);
						}
					}
				}
			}
			else if ( feature == UmaPackage.eINSTANCE.getNamedElement_Name() ) {
				// check difference name, if replace, need to rename resources if if has it's own resource
				// like content elements, plugins, configuirations, and process components					
				if (! (newValue instanceof String)) {
					throw new UnsupportedOperationException();
				}
				String oldName = (String)element.eGet(feature);
				if (!newValue.equals(oldName)) {					
					ensureUniqueNameForExistingElement(element, oldName, (String) newValue, renameElementMap);										
				}
			} else {
				element.eSet(feature, newValue);
				
				// scan and copy the resources
				scanResources(element, feature, newValue);
			}

		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally
		{
			element.eSetDeliver(oldNotify);
		}
	}

	private void setMepFeatureValue(MethodElement element, EStructuralFeature feature, List oldValue, List newValue) {
		int sz = newValue.size();
		if (oldValue.size() == sz) {
			if (sz == 0) {
				return;
			}
			boolean same = true;
			for (int i=0; i < sz; i++) {
				MethodElementProperty oldMep = (MethodElementProperty) oldValue.get(i);
				MethodElementProperty newMep = (MethodElementProperty) newValue.get(i);
				if (! oldMep.getName().equals(newMep.getName()) ||
					! oldMep.getValue().equals(newMep.getValue())) {
					same = false;
					break;
				}
			}
			if (same) {
				return;
			}					
		}
		oldValue.removeAll(oldValue);
		oldValue.addAll(newValue);		
	}

	private void scanResources(MethodElement element, boolean recursive) {

		if ( element == null ) {
			return;
		}
		
		List features = LibraryUtil.getStructuralFeatures(element, true);
//		List properties = element.getInstanceProperties();
		if (features != null) {
			for (int i = 0; i < features.size(); i++) {
				EStructuralFeature feature = (EStructuralFeature) features.get(i);
				if ( !(feature instanceof EAttribute) ) {
					continue;
				}

//				Object value = element.eGet(feature);
				Object value = TypeDefUtil.getInstance().eGet(element, feature);
				scanResources(element, feature, value);
			}
		}
		
		if ( !recursive ) {
			return;
		}
		
		for (Iterator it = element.eAllContents(); it.hasNext(); ) {
			EObject obj = (EObject)it.next();
			
			if (obj instanceof MethodLibrary
					|| obj instanceof MethodPlugin 
					|| obj instanceof MethodPackage 
					|| obj instanceof MethodConfiguration) {
				continue;
			}
			
			if ( obj instanceof MethodElement ) {
				scanResources((MethodElement)obj, false);
			}
		}
	}
	
	private void scanResources(MethodElement element, EStructuralFeature feature, Object newValue) {
		diagramHandler.registerElement(element);
		
		ExtendedAttribute eAtt = TypeDefUtil.getInstance().getAssociatedExtendedAttribute(feature);
		
		// scan the resources
		if ( feature == UmaPackage.eINSTANCE.getGuidanceDescription_Attachments() ||
				(eAtt != null && eAtt.getValueType().equalsIgnoreCase(IMetaDef.attachment))) {
			
			// process the attachments
			String urls = (String)newValue;
			if ( (urls != null) && urls.length() != 0 ) {
				StringTokenizer st = new StringTokenizer(urls, "|"); // this is hardcoded somehow //$NON-NLS-1$
				while (st.hasMoreTokens() ) {
					String url = st.nextToken();
					resScanner.copyResource (element, url);
				}						
			}
		} else if ( newValue instanceof String ) {
			resScanner.scan(element, newValue.toString());
		} else if (newValue instanceof URI ) {
			MethodPlugin plugin = UmaUtil.getMethodPlugin(element);
			resScanner.copyResource ( ((URI)newValue).getPath(), plugin);
		}

	}
	
	private void doAdd(EObject owner, MethodElement newObj) throws Exception {
		if ( debug ) {
			System.out.println("Adding element " + LibraryUtil.getTypeName((MethodElement)newObj)); //$NON-NLS-1$
		}

		// the owner can only be library and package
		if (owner == null) {
			return;
		}
		
		ensureUniqueName(owner, newObj, renameElementMap);
		
		boolean error = false;
		
		if (owner instanceof MethodLibrary) {			
			// can be configuration or plugin
			if (newObj instanceof MethodPlugin) {
				MethodPlugin plugin = (MethodPlugin)newObj;
				
				MethodPluginPropUtil propUtil = MethodPluginPropUtil.getMethodPluginPropUtil();				
				if (ProcessUtil.isSynFree() && ! propUtil.isSynFree(plugin)) {
					propUtil.setSynFree(plugin, true);
				}
				
				checkModifiedFiles();				
				error = ! fileCheckedOutStatus.isOK();				
								
				// don't intitialize the storage since this will recreate the global packages
				// and such causing two set of global packages
				// 145850 - Import Configuration: CP/DPs did not shown in authering
				// ModelStorage.initialize(plugin);
				// NO, since the global packages are already created
				if (!error) {
					((MethodLibrary)owner).getMethodPlugins().add(plugin);
					try {
						MultiFileXMIHelperImpl.unmodifiedGetValue = true;
						LibraryUtil.saveLibrary((MethodLibrary)owner, false, false);
					} finally {
						MultiFileXMIHelperImpl.unmodifiedGetValue = false;
					}
				}
								
			} else if (newObj instanceof MethodConfiguration ) {
				checkModifiedFiles();				
				error = configFolderError || ! fileCheckedOutStatus.isOK();
				if (!error) {
					MethodLibrary lib = (MethodLibrary)owner;
					lib.getPredefinedConfigurations().add((MethodConfiguration)newObj);
					ILibraryPersister persister = ((MultiFileResourceSetImpl) lib.eResource().getResourceSet()).getPersister();
					if (persister instanceof IFileBasedLibraryPersister) {
						IFileBasedLibraryPersister ip = (IFileBasedLibraryPersister) persister;
						File configFolder = ip.getDefaultMethodConfigurationFolder(lib);
						if (configFolder != null) {
							ip.setDefaultMethodConfigurationFolder(lib, configFolder);
						} else {
							error = true;
							configFolderError = true;
						}
					}
					if (! error) {
						try {
							MultiFileXMIHelperImpl.unmodifiedGetValue = true;
							LibraryUtil.saveLibrary((MethodLibrary)owner, false, false);
						} finally {
							MultiFileXMIHelperImpl.unmodifiedGetValue = false;
						}
					}
				}
			} else {
				error = true;			
			}
		} else if ( (owner instanceof MethodPackage) 
				&& (owner.eContainer() != null) 
				&& (newObj instanceof MethodPackage) ) {
			((MethodPackage)owner).getChildPackages().add((MethodPackage)newObj);
		} else {
			error = true;
		}
			
		if ( error ) {
			String ownerStr = owner instanceof MethodElement ? 
					LibraryUtil.getTypeName((MethodElement)owner) : owner.toString();
					
			String msg = "can't add " + LibraryUtil.getTypeName(newObj) //$NON-NLS-1$
			+ " to " + ownerStr; //$NON-NLS-1$
			
			ImportPlugin.getDefault().getLogger().logError(msg); 
			if ( debug ) {
				System.out.println(msg);
			}
		} else {
			logNewElement(newObj);
		}
	}

	private boolean checkModifiedConfigs(MethodPlugin plugin) {
		List configList = LibraryUtil.getAssociatedConfigurations(plugin);
		List baseConfigs = baseLibrary.getPredefinedConfigurations();
		Map baseConfigMap = new HashMap();
		for (int i=0; i<baseConfigs.size(); i++) {
			MethodConfiguration config = (MethodConfiguration) baseConfigs.get(i);
			baseConfigMap.put(config.getGuid(), config);
		}
		final List modifiedFiles = new ArrayList();
		for (int i=0; i<configList.size(); i++) {
			MethodConfiguration config = (MethodConfiguration) configList.get(i);
			config = (MethodConfiguration) baseConfigMap.get(config.getGuid());
			if (config != null) {
				Resource res = config.eResource();
				if (res != null) {
					modifiedFiles.add(res.getURI().toFileString());
				}
			}
		}
		SafeUpdateController.syncExec(new Runnable() {
			public void run() {
				fileCheckedOutStatus = FileModifyChecker.checkModify(modifiedFiles);
			}
		});
		fileCheckOutError();
		
		return fileCheckedOutStatus.isOK();
	}

	private void doIntegrityCheck()
	{		
		// now the newly added elements may have feature value element that
		// still pointing to the importing library
		// we need to replace those with the one in the base library
		while (newElements.size() > 0) {
			MethodElement newObj = (MethodElement)newElements.remove(0);
			try {
				if ( newObj instanceof DiagramElement ) {
					fixDiagram((DiagramElement)newObj);
				} else {
					fixNewElementReferences(newObj);
				}
			} catch (Exception e) {
				if (debug) {
					System.out.println("Exception while fixing new element " + LibraryUtil.getTypeName(newObj)); //$NON-NLS-1$
				}
				e.printStackTrace();
			}
		}
		
		// the configurations needs to be fixed
		List configs = baseLibrary.getPredefinedConfigurations();
		for ( Iterator it = configs.iterator(); it.hasNext(); )
		{
			MethodConfiguration config = (MethodConfiguration)it.next();
			LibraryUtil.validateMethodConfiguration(null, config);
		}
	}

	private List processedNewElements = new ArrayList();
	
	
	
	/**
	 * iterate all features, if the feature value references an element which is
	 * an old element, replace it with the old element. if the element is new,
	 * iterate it's containment elements
	 * 
	 * @param newObj
	 */
	private void fixNewElementReferences(MethodElement newObj) {
		if ( processedNewElements.contains(newObj) ) {
			return;
		}
		
		processedNewElements.add(newObj);
		
		if ( debug ) {
			System.out.println("Fixing element " + LibraryUtil.getTypeName(newObj)); //$NON-NLS-1$
		}
		
		List features = LibraryUtil.getStructuralFeatures(newObj);
//		List properties = newObj.getInstanceProperties();
		if (features != null) {
			for (int i = 0; i < features.size(); i++) {
				EStructuralFeature feature = (EStructuralFeature) features.get(i);
				Object value = newObj.eGet(feature);

				if ( value instanceof List) {
					
					// fixing the feature value may cause the list to change
					// iterate on a new copy to avoid
					// ConcurrentModificationException
					for (Iterator it= new ArrayList(((List)value)).iterator(); it.hasNext(); ) {
						Object o = it.next();
						fixNewElementFeatureValue(newObj, feature, o);
					}
				} else {
					fixNewElementFeatureValue(newObj, feature, value);
				}
			}
		}	
	}
	
	
	private void fixDiagram(DiagramElement newObj) {		

		if ( processedNewElements.contains(newObj) ) {
			return;
		}
		
		processedNewElements.add(newObj);
		
		if ( debug ) {
			System.out.println("Fixing diagram element " + LibraryUtil.getTypeName(newObj)); //$NON-NLS-1$
		}
		
		List features = LibraryUtil.getStructuralFeatures(newObj);
//		List properties = newObj.getInstanceProperties();
		if (features != null) {
			for (int i = 0; i < features.size(); i++) {
				EStructuralFeature feature = (EStructuralFeature) features.get(i);
				Object value = newObj.eGet(feature);

				if ( value instanceof List) {
					
					// fixing the feature value may cause the list to change
					// iterate on a new copy to avoid
					// ConcurrentModificationException
					for (Iterator it= new ArrayList(((List)value)).iterator(); it.hasNext(); ) {
						Object o = it.next();
						if ( o instanceof MethodElement ) {
							if ( feature == UmaPackage.eINSTANCE.getUMASemanticModelBridge_Element() ) {
								fixNewElementFeatureValue(newObj, feature, o);
							} else if ( (o instanceof DiagramElement) ) {
								fixDiagram((DiagramElement)o);
							} 
						}
					}
				} else if ( value instanceof MethodElement ) {
					if ( feature == UmaPackage.eINSTANCE.getUMASemanticModelBridge_Element() ) {
						fixNewElementFeatureValue(newObj, feature, value);
					} else if ( (value instanceof DiagramElement) ) {
						fixDiagram((DiagramElement)value);
					} 
				}
			}
		}	
	}
	
	/**
	 * check if the value is an old element in the base, if yes, replace it with
	 * the old one if it's new, iterate it's feature value
	 * 
	 * @param feature
	 * @param obj
	 */
	private void fixNewElementFeatureValue(MethodElement element, EStructuralFeature feature, Object obj) {
		if ( !(obj instanceof MethodElement) ) {
			return;
		}
		
		if ( debug ) {
			System.out.println("fixing element feature value " + LibraryUtil.getTypeName(element) + ", feature=" + feature.getName()); //$NON-NLS-1$ //$NON-NLS-2$
		}

		boolean oldNotify = element.eDeliver();
		try
		{
			element.eSetDeliver(false);
			
			MethodElement newObj = (MethodElement)obj;
			MethodElement oldObj = getReplaced(newObj.getGuid());
			if ( oldObj == null ) {
				// get existing element, for example, the global packages in the
				// configuration feature
				oldObj = diffMgr.getExistingElement(newObj.getGuid());
			}
			
			if ( oldObj == null ) {
				// it's a new one
				fixNewElementReferences(newObj);
			} else {
				
				try {
					// replace the newObj with the oldObj
					EcoreUtil.replace(element, feature, newObj, oldObj); 
				}
				catch (Exception ex) {
					// if replace failed, remove the feature
					EcoreUtil.remove(element, feature, newObj); 
					if (debug ) {
						System.out.println("Replaceing feature value failed for element ["  //$NON-NLS-1$
								+ LibraryUtil.getTypeName(element) + "], feature ["  //$NON-NLS-1$
								+ feature.getName() + "], value="  //$NON-NLS-1$
								+ newObj + ". The feature value is removed. "); //$NON-NLS-1$
						
					}
				}
			}	
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally
		{
			element.eSetDeliver(oldNotify);
		}
	}
	
	private boolean canReset(MethodElement e) {
		boolean reset = false;
		
		// an element can be reset if you are sure that no other element(s)
		// reference to it
		// reset elements may have references to other elements in the importing
		// library, that needs to be fixed later
		
		// for anything contained by ContentDescription, reset it
		if ( e == null /* || e.eContainer() instanceof ContentDescription */
				|| e instanceof DiagramElement) {
			reset = true;
		}
				
		return reset;
	}

	private boolean canReset(EStructuralFeature feature) {
		// for anything contained by ContentDescription, reset it
		// no don't reset the sections since it will cause the one in the
		// importing library removed
		// which caused problem for TaskDescription where the same feature
		// (Steps) can't find the steps from the importing library
		// which then will treat the steps in the base library being deteted
// if ( feature == UmaPackage.eINSTANCE.getContentDescription_Sections()) {
// return true;
// }
		return false;
	}

	private boolean canIgnore(EStructuralFeature feature) {
		// don't reset the plugin lock status
		if ( feature == UmaPackage.eINSTANCE.getMethodPlugin_UserChangeable()) {
			return true;
		}

		// 146144 - Import Configuration: some unchange elements disappear in authoring after import
		// this is caused by a bug in ArtifactImpl, when setContainerArtifact to null, 
		//  it set the eContainer to null, if the eContainer is a content package, 
		// the artifact is removed from the package
		//  this bug will be fixed in M4. 
		// for now, ignore this feature since this is an opposite feature, we don't need to handle it	
		if ( feature == UmaPackage.eINSTANCE.getArtifact_ContainerArtifact() ) {
			return true;
		}
		return false;
	}
	
	private boolean isReplaced(MethodElement oldObj) {
		return replacedElementMap.containsKey(oldObj.getGuid());
	}

	private void setReplaced(MethodElement oldObj) {
		String guid = oldObj.getGuid();
		if ( !replacedElementMap.containsKey(guid) ) {
			replacedElementMap.put(guid, oldObj);
			setModified(oldObj);
		}
	}
	
	/**
	 * get the old element been replaced
	 * 
	 * @param oldObj
	 * @return
	 */
	private MethodElement getReplaced(String guid) {
		return (MethodElement)replacedElementMap.get(guid);
	}
	
	/**
	 * when new elements are added into the library, the associated resource
	 * files must be copied over as well
	 * 
	 * @param newObj
	 *            MethodElement
	 */
	private void copyNewElementResources(MethodElement newObj) {
		
//		List properties = newObj.getInstanceProperties();
//		if (properties != null) {
//			for (int i = 0; i < properties.size(); i++) {
//				EStructuralFeature feature = (EStructuralFeature) properties.get(i);
//				Object value = newObj.eGet(feature);
//				if ( value instanceof URI ) {
//					resScanner.copyResource(((URI)value).getPath());
//				} else if ( value instanceof String ) {
//					resScanner.scan(newObj, (String)value);
//				}
//			}
//		}
//		
//		for ( Iterator it = newObj.eContents().iterator(); it.hasNext(); ) {
//			Object o = it.next();
//			if ( o instanceof MethodElement ) {
//				copyNewElementResources((MethodElement)o);
//			}
//		}
		
		scanResources(newObj, true);
	}
	
	private void logNewElement(MethodElement newObj) {
		
		// make sure the element is not in the original library
		// since we have the guid-element map build up in the diff manager,
		// check there
		if ( !newElements.contains(newObj) && diffMgr.getExistingElement(newObj.getGuid()) == null) {
			newElements.add(newObj);
			copyNewElementResources(newObj);
			setModified(newObj);
		}
	}
	
	private void logResetElement(MethodElement newObj) {
		if ( !newElements.contains(newObj) ) {
			newElements.add(newObj);
			setModified(newObj);
		}
	}

	private void logNewResource(Resource res ) {
		if ( res != null ) {
			
			// mark the resource as dirty so that we can save it again
			// the first save may lose cross references 
			res.setModified(true);
			String file = res.getURI().toFileString();
			if ( !newResources.contains(file) ) {
				newResources.add(file);
			}
		}
	}
	
	private void logRemovedElements(List items) {
		
		if ( items == null || items.size() == 0 ) {
			return;
		}
		
		// removed elements can be removed from any feature
		// check if the element's container is null
		// if is, then the element is deleted and the resource should be deleted
		// as well
		for (Iterator it = items.iterator(); it.hasNext(); ) {
			MethodElement e = (MethodElement)it.next();
			if ( (e.eContainer() == null) && !deletedElements.contains(e) ) {			
				deletedElements.add(e);
			}			
		}
	}
	
	private void setModified(EObject obj) {
		if ( obj != null ) {
			Resource res = obj.eResource();
			if ( res != null ) {
				if ( !res.isModified() ) {
					res.setModified(true);				
				}
// } else {
// System.out.println("No resource for " + obj);
				
				if (debug && res.isModified() ) {
					System.out.println("Modified: " + res.getURI().toFileString()); //$NON-NLS-1$
				}

			}
		}
	}

	private List getModifiedResources(MethodLibrary lib) {
		
		// avoid the newly added element resources
		List modifiedList = new ArrayList();
		Resource res = lib.eResource();
		if ( res != null ) {
			ResourceSet resSet = res.getResourceSet();
			for ( Iterator it = resSet.getResources().iterator(); it.hasNext(); ) {
				res = (Resource) it.next();
				if ( res != null && res.isModified() ) {
					String file = res.getURI().toFileString();
					if ( newResources.contains(file) ) {
						continue;
					}
					
					if ( debug ) {
						System.out.println("Resource modified: " + file); //$NON-NLS-1$
					}		
					modifiedList.add(file);
				}
			}
		}
		return modifiedList;
	}

	// clear resources for deleted elements
	private void deleteResoruces() {
		if ( deletedElements.size() == 0 ) {
			return;
		}
		
		IFileManager fileMgr = Services.getFileManager();

		for (Iterator it = deletedElements.iterator(); it.hasNext(); ) {
			MethodElement e = (MethodElement)it.next();
			
			EObject obj = null;
			if ( e instanceof DescribableElement ) {
				obj = ((DescribableElement)e).getPresentation();
			} else if ( e instanceof ContentDescription ) {
				obj = e;
			}
			if ( obj != null ) {
				Resource res = obj.eResource();
				if ( res != null ) {
					String file = res.getURI().toFileString();
					if ( debug ) {
						System.out.println("deleting resource: " + file); //$NON-NLS-1$
					}
					
					if ( !fileMgr.delete(file) ) {
						if ( debug ) {
							System.out.println("unable to delete file: " + file); //$NON-NLS-1$
						}
					}
				}								
			}
		}
	}

	
	public class ElementListDiff {

		List newItems = new ArrayList();
		List deletedItems = new ArrayList();
		
		// use LinkedHashMap to reserve the order of the values
		Map oldNewMap = new LinkedHashMap();

		// with the old and new list of MethodElements,
		// find the common ones, the deleted ones, and the new ones
		public ElementListDiff(List oldList, List newList) {
			// Map oldListGuidMap = getGuidMap(oldList);
			Map newListGuidMap = getGuidMap(newList);

			newItems.addAll(newList);

			for (Iterator it = oldList.iterator(); it.hasNext(); ) {
				MethodElement oldObj = (MethodElement)it.next();
				String guid = oldObj.getGuid();
				Object newObj = newListGuidMap.get(guid);
				if ( newObj != null ) {
					oldNewMap.put(oldObj, newObj);
					newItems.remove(newObj);
				}
				else
				{
					// it's been deleted
					deletedItems.add(oldObj);
				}
			}
		}

		private Map getGuidMap(List items) {
			Map m = new HashMap();
			for (Iterator it= items.iterator(); it.hasNext(); ) {
				MethodElement e = (MethodElement)it.next();
				m.put(e.getGuid(), e);
			}

			return m;
		}
	}	
	
	/**
	 * Handles name replacement.
	 */
	public static void handleNameReplace(Map renameElementMap) {
		Iterator it = renameElementMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			Object[] val = (Object[])entry.getValue();
			
			MethodElement element = (MethodElement) val[0];
			String newName = val.length == 4 ? element.getGuid() : (String) val[1];
			rename(element, newName);
			if (val.length == 4) {
				element = (MethodElement) val[2];
				newName = (String) val[3];
				rename(element, newName);
				
				element = (MethodElement) val[0];
				newName = (String) val[1];
				rename(element, newName);
			}
		}
		
		//Make resouces dirty again, as rename process would mark resouce un-modified
		it = renameElementMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			Object[] val = (Object[])entry.getValue();			
			MethodElement element = (MethodElement) val[0];
			element.eResource().setModified(true);
		}
	}
	
	private static void rename(MethodElement element, String newName) {
		if (newName.equals(element.getName())) {
			return;
		}
		if (element instanceof ContentDescription || element.eResource() == null) {
			element.setName(newName);
			if (element instanceof ProcessComponent) {
				Process proc = ((ProcessComponent) element)
						.getProcess();
				proc.setName(newName);
			}
		} else {
			LibraryView.runRename(element, newName);
		}
	}
		
	private void registerSetElement(MethodElement element, EStructuralFeature feature, Object newValue) {
		String guid0 = ((MethodElement)newValue).getGuid();
		String guid1 = element.getGuid();
		Object[] val = new Object[3];
		val[0] = feature;
		val[1] = newValue;
		val[2] = element;
		if (setElementMap == null) {
			setElementMap = new HashMap();
		}
		setElementMap.put(element.getName() + guid0 + guid1, val);
	}
	
	private void handleSetElements() {
		if (setElementMap == null) {
			return;
		}
		for (Iterator it = setElementMap.entrySet().iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			Object[] val = (Object[])entry.getValue();
			EStructuralFeature feature = (EStructuralFeature) val[0];
			MethodElement newValue = (MethodElement) val[1];
			MethodElement element = (MethodElement) val[2];
			String guid = newValue.getGuid();
			MethodElement mergedValue = (MethodElement) replacedElementMap.get(guid);
			if (mergedValue != null && mergedValue != newValue) {
				element.eSet(feature, mergedValue);
			}			
		}
	}

	/**
	 * Ensures unique name.
	 */
	public static void ensureUniqueName(EObject owner, MethodElement newObj, Map renameElementMap) {
		if (owner == null) {
			return;
		}
		Class cls = newObj.getClass();
		Map nameMap = new HashMap();
		for (int i=0; i < owner.eContents().size(); i++) {
			Object oldObj = owner.eContents().get(i);
			if (oldObj.getClass() == cls && oldObj != newObj) {
				MethodElement oldElem = (MethodElement) oldObj;
				nameMap.put(oldElem.getName(), oldElem);
			}
		}
		String name = newObj.getName();
		String renamed = name;
		while (nameMap.containsKey(renamed)) {
			renamed += "_renamed"; //$NON-NLS-1$
		}
		if (renamed != name) {
			newObj.setName(renamed);
			
			Object[] entryVal = new Object[4];
			entryVal[0] = newObj;
			entryVal[1] = name;
			entryVal[2] = nameMap.get(name);
			entryVal[3] = renamed;
			renameElementMap.put(newObj.getGuid(), entryVal);			
		}
	}
	
	/**
	 * Ensures unique name for existing element.
	 */
	public static void ensureUniqueNameForExistingElement(MethodElement element, String oldName, String newName, Map renameElementMap) {
		EObject elementOwner = element.eContainer();					
		if (elementOwner != null) {
			element.setName(newName);
			ensureUniqueName(elementOwner, element, renameElementMap);
			if (!element.getName().equals(newName)) {	//if handled then return
				return;
			}			
			element.setName(oldName);
		}			
		Object[] entryVal = new Object[2];
		entryVal[0] = element;
		entryVal[1] = newName;
		renameElementMap.put(element.getGuid(), entryVal);
	}

	private void checkModifiedFiles() {
		final List modifiedFiles = getModifiedResources(baseLibrary);
		modifiedFiles.addAll(resScanner.getFilesTobeReplaced());
		modifiedFiles.addAll(diagramHandler.getModifiedFiles());

		SafeUpdateController.syncExec(new Runnable() {
			public void run() {
				fileCheckedOutStatus = FileModifyChecker.checkModify(modifiedFiles);
			}
		});
	}

	private List unlockPlugins() {

		List pluginIds = new ArrayList();
		
		Map<String, MethodPlugin> map = new HashMap<String, MethodPlugin>();
		Map<String, MethodPlugin> guidMap = new HashMap<String, MethodPlugin>();
		List<MethodPlugin> basePlugins = LibraryService.getInstance().getCurrentMethodLibrary()
											.getMethodPlugins();
		for (int i = 0; i < basePlugins.size(); i++) {
			MethodPlugin plugin = basePlugins.get(i);
			map.put(plugin.getName(), plugin);
			guidMap.put(plugin.getGuid(), plugin);
		}
		
		if (map.isEmpty()) {
			return pluginIds;
		}
		
		List fileNameToCheck = new ArrayList();
		List<MethodPlugin> importPlugins = importingLibrary.getMethodPlugins();
		for (int i = 0; i < importPlugins.size(); i++) {
			MethodPlugin plugin = importPlugins.get(i);
			String name = plugin.getName();
			String guid = plugin.getGuid();
			
			MethodPlugin basePlugin0 = guidMap.get(guid);
			if (basePlugin0 != null) {
				String name0 = basePlugin0.getName();
				if (! name0.equals(name)) {
					renamePluginMap.put(name0, name);
				}
			}
			
			MethodPlugin basePlugin = map.get(name);
			boolean toAdd = false;
			if (basePlugin != null && ! basePlugin.getGuid().equals(guid)
					&& ! basePlugin.getUserChangeable()) {
				toAdd = true;
			} else {
				basePlugin = guidMap.get(guid);
				if (basePlugin != null /*&& ! basePlugin.getName().equals(name)*/
						&& ! basePlugin.getUserChangeable()) {
					toAdd = true;
				} 
			}
			
			if (toAdd){				
				pluginIds.add(basePlugin.getGuid());
				basePlugin.setUserChangeable(new Boolean(true));
				Resource res = basePlugin.eResource();
				if (res != null && res.getURI() != null) {
					String fileName = res.getURI().toFileString();
					fileNameToCheck.add(fileName);
				}
			}
		}	
		
		if (fileNameToCheck.size() > 0) {
			final List modifiedFiles = fileNameToCheck;	
			SafeUpdateController.syncExec(new Runnable() {
				public void run() {
					fileCheckedOutStatus = FileModifyChecker.checkModify(modifiedFiles);
				}
			});
		}
		
		return pluginIds;
	}

	/**
	 * @param unlockedPlugins
	 */
	public static void lockUnlockedPlugins(List unlockedPlugins) {
		List plugins = LibraryService.getInstance().getCurrentMethodLibrary()
				.getMethodPlugins();
		for (Iterator it = plugins.iterator(); it.hasNext();) {
			MethodPlugin plugin = (MethodPlugin) it.next();
			if (unlockedPlugins.contains(plugin.getGuid())) {
				plugin.setUserChangeable(new Boolean(false));
			}
		}
	}
	
	private void logNewElementUnderExistingPlugin(MethodElement newObj) {
		ILibraryResourceManager libResMgr = ResourceHelper.getResourceMgr(newObj);
		String pluginPath = libResMgr.getPhysicalPluginPath(newObj);
		if (pluginPath != null) {
			logNewElement(newObj);
		}
	}
	
}