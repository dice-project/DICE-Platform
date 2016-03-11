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
package org.eclipse.epf.importing.xml.services;

import java.io.File;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epf.common.utils.NetUtil;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.dataexchange.importing.LibraryService;
import org.eclipse.epf.dataexchange.util.ContentProcessor;
import org.eclipse.epf.dataexchange.util.ILogger;
import org.eclipse.epf.export.xml.services.FeatureManager;
import org.eclipse.epf.export.xml.services.XMLLibrary;
import org.eclipse.epf.importing.xml.ImportXMLPlugin;
import org.eclipse.epf.importing.xml.ImportXMLResources;
import org.eclipse.epf.library.edit.util.MethodElementPropertyHelper;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.persistence.MultiFileSaveUtil;
import org.eclipse.epf.services.IFileManager;
import org.eclipse.epf.services.Services;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.DisciplineGrouping;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodElementProperty;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessElement;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.RoleSet;
import org.eclipse.epf.uma.RoleSetGrouping;
import org.eclipse.epf.uma.Section;
import org.eclipse.epf.uma.Tool;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkOrder;
import org.eclipse.epf.uma.WorkOrderType;
import org.eclipse.epf.uma.WorkProductType;
import org.eclipse.epf.uma.ecore.IModelObject;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.epf.xml.uma.TaskDescriptor;
import org.eclipse.osgi.util.NLS;

import com.ibm.icu.text.SimpleDateFormat;

/**
 * UmaLibrary represents a method library and provide methods to create elements
 * and set attributes and feature values
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class UmaLibrary {

	private boolean debug = ImportXMLPlugin.getDefault().isDebugging();

	private boolean overwrite = false;
	
	private int mergeLevel = 0;
	
	private ILogger logger;

	private ContentProcessor contentProc = null;
	
	private Map newElementsMap = new HashMap();
	
	private Map renameElementMap;

	// map of created elements, guid - EDataObject
	Map elementsMap = new HashMap();

	// keep the source element ids so we can determine the elements that are not in the xml source library
	// this is needed for deleting un-needed elements
	// 154149 - allow package level merging for xml import
	HashSet sourceElementIDs = new HashSet();
	
	/**
	 * The constructor.
	 * @param contentProc
	 * @param logger
	 * @param overwrite
	 */
	public UmaLibrary(Map renameElementMap, ContentProcessor contentProc, ILogger logger, boolean overwrite) {
		this.renameElementMap = renameElementMap;
		this.logger = logger;
		this.contentProc = contentProc;
		this.overwrite = overwrite;

		buildElementsMap();
	}

	/**
	 * @return the current library as the root object.
	 */
	public MethodLibrary getRoot() {
		return org.eclipse.epf.library.LibraryService.getInstance()
				.getCurrentMethodLibrary();
	}

	private void buildElementsMap() {
		// build the elements map for easy operation

		elementsMap.clear();
		elementsMap.put(getRoot().getGuid(), getRoot());
		TreeIterator it = getRoot().eAllContents();
		while (it.hasNext()) {
			Object e = it.next();
			if (e instanceof MethodElement) {
				elementsMap.put(((MethodElement) e).getGuid(), e);
			}
		}
	}

	/**
	 * Creates a content catergory.
	 * @param pluginId
	 * @param xmlEClassName
	 * @param xmlElementType
	 * @param id
	 * @return
	 */
	public IModelObject createContentCategory(String pluginId,
			String xmlEClassName, String xmlElementType, String id) {

		setSourceId(id);
		
		// make sure the same object created only once
		IModelObject obj = getElement(id);
		if (obj != null) {
			return obj;
		}

		MethodPlugin plugin = (MethodPlugin) getElement(pluginId);

		EClass objClass = FeatureManager.INSTANCE.getRmcEClass(xmlEClassName);
		if (objClass == null) {
			logger.logWarning(NLS.bind(ImportXMLResources.library_error_no_eclass, xmlEClassName));
			return null;
		}

		obj = (IModelObject) EcoreUtil.create(objClass);
		setElement(id, obj);

		ContentPackage rootPkg = (ContentPackage) getRootPackage(plugin, obj);
		rootPkg.getContentElements().add((ContentCategory)obj);
		return obj;
	}

	/**
	 * Gets the root package
	 * @param plugin
	 * @param obj
	 * @return
	 */
	public MethodPackage getRootPackage(MethodPlugin plugin, Object obj) {

		String[] path = null;
		if (obj instanceof CustomCategory) {
			path = ModelStructure.DEFAULT.customCategoryPath;
		} else if (obj instanceof Discipline
				|| obj instanceof DisciplineGrouping) {
			path = ModelStructure.DEFAULT.disciplineDefinitionPath;
		} else if (obj instanceof Domain) {
			path = ModelStructure.DEFAULT.domainPath;
		} else if (obj instanceof RoleSet || obj instanceof RoleSetGrouping) {
			path = ModelStructure.DEFAULT.roleSetPath;
		} else if (obj instanceof Tool) {
			path = ModelStructure.DEFAULT.toolPath;
		} else if (obj instanceof WorkProductType) {
			path = ModelStructure.DEFAULT.workProductTypePath;
		}

		if (path != null) {
			return UmaUtil.findMethodPackage(plugin, path);
		} else {
			logger.logWarning(NLS.bind(ImportXMLResources.library_no_package, obj)); 
		}

		return null;
	}

	/**
	 * Creates a method plugin.
	 * @param id
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public IModelObject createMethodPlugin(String id, String name)
			throws Exception {
		
		// make sure the same object created only once
		IModelObject obj = getElement(id);
		boolean isOld = obj != null;
		if (obj == null) {
			Map options = new HashMap();
			options.put("renameElementMap", renameElementMap);//$NON-NLS-1$
			obj = LibraryService.INSTANCE.createPlugin(name, id, options);

			setElement(id, obj);
		}

		// make sure the system packages are included
		setSourceId(id);
		List pkgs = TngUtil.getAllSystemPackages((MethodPlugin)obj);
		for (Iterator it = pkgs.iterator(); it.hasNext(); ) {
			MethodPackage pkg = (MethodPackage)it.next();
			setSourceId(pkg.getGuid());
			if (!isOld) {
				newElementsMap.put(pkg.getGuid(), pkg);
			}
		}
		
		return obj;
	}

	/**
	 * Gets content's root package.
	 * @param plugin
	 * @return
	 */
	public IModelObject getContentRootPackage(IModelObject plugin) {
		if (!(plugin instanceof MethodPlugin)) {
			return null;
		}
		return UmaUtil.findMethodPackage((MethodPlugin) plugin,
				ModelStructure.DEFAULT.coreContentPath);
	}

	/**
	 * Gets capability pattern' root package.
	 * @param plugin
	 * @return
	 */
	public IModelObject getCapabilityPatternRootPackage(IModelObject plugin) {
		if (!(plugin instanceof MethodPlugin)) {
			return null;
		}
		return UmaUtil.findMethodPackage((MethodPlugin) plugin,
				ModelStructure.DEFAULT.capabilityPatternPath);
	}

	/**
	 * Gets delivery process' root package.
	 * @param plugin
	 * @return
	 */
	public IModelObject getDeliveryProcessRootPackage(IModelObject plugin) {
		if (!(plugin instanceof MethodPlugin)) {
			return null;
		}
		return UmaUtil.findMethodPackage((MethodPlugin) plugin,
				ModelStructure.DEFAULT.deliveryProcessPath);
	}

//	public EDataObject getProcessContributionRootPackage(EDataObject plugin) {
//		if (!(plugin instanceof MethodPlugin)) {
//			return null;
//		}
//		return UmaUtil.findMethodPackage((MethodPlugin) plugin,
//				ModelStructure.DEFAULT.processContributionPath);
//	}

	/**
	 * Creates an element.
	 * @param container
	 * @param xmlFeatureName
	 * @param xmlEClassName
	 * @param xmlElementType
	 * @param id
	 * @return
	 */
	public IModelObject createElement(IModelObject container,
			String xmlFeatureName, String xmlEClassName, String xmlElementType,
			String id) {

		setSourceId(id);

		// make sure the same object created only once
		IModelObject obj = getElement(id);
		if (obj != null) {
			if (obj instanceof Activity) {
				EObject eobj = obj.eContainer();
				if (eobj instanceof ProcessPackage) {
					ProcessPackage p = (ProcessPackage) eobj;
					setSourceId(p.getGuid());
				}
			}
			return obj;
		}

		// if ( obj instanceof MethodPlugin ) {
		// return getElement(id);
		// }

		// // this is an easier way, test it,
		// // does not work since feature.getEType() returns the base element
		// type, not the extended one
		// // for example, it returns MethodPackage instead of ProcessPackage or
		// ContentPackage
		// // so we need to make our own map
		// EClassifier c = feature.getEType();

		EClass objClass = FeatureManager.INSTANCE.getRmcEClass(xmlEClassName);
		if (objClass == null) {
			logger.logWarning(NLS.bind(ImportXMLResources.library_error_no_eclass, xmlEClassName)); 
			return null;
		}

		obj = (IModelObject) EcoreUtil.create(objClass);	

		if (obj instanceof WorkOrder) {
			// WorkOrder is not a method element in xml uma, need to create it
			// with a guid
			//id = EcoreUtil.generateUUID();

			// also need to add the element into the container of the owner
			((ProcessPackage) container.eContainer()).getProcessElements().add(
					(WorkOrder)obj);
		}

		setElement(id, obj);

		try {
			if (container instanceof MethodPlugin) {
				MethodPlugin plugin = (MethodPlugin) container;
				// need special handling for new plugin
				if (obj instanceof ContentPackage) {
					// content package and owner is Plugin, goto the root
					// content package
					MethodPackage pkg_core_content = UmaUtil.findMethodPackage(
							plugin, ModelStructure.DEFAULT.coreContentPath);
					pkg_core_content.getChildPackages().add((ContentPackage)obj);
				} else if (obj instanceof ProcessComponent) {
					MethodPackage pkg_cp = UmaUtil.findMethodPackage(plugin,
							ModelStructure.DEFAULT.capabilityPatternPath);
					pkg_cp.getChildPackages().add((ProcessComponent)obj);

				} else if (obj instanceof ProcessPackage) {
					// this is a root process package
					// put into the root delivery processes package
					MethodPackage pkg_dp = UmaUtil.findMethodPackage(plugin,
							ModelStructure.DEFAULT.deliveryProcessPath);
					pkg_dp.getChildPackages().add((ProcessPackage)obj);
				}
			} else {

				EStructuralFeature feature = FeatureManager.INSTANCE
						.getRmcFeature(container.eClass(), xmlFeatureName);
				if (feature == null) {
					return null;
				}

				if ((container instanceof Activity)
						&& feature == UmaPackage.eINSTANCE
								.getActivity_BreakdownElements()
						&& obj instanceof BreakdownElement) {

					// the container needs to be a process package
					EObject pkg = container;
					while ((pkg != null) && !(pkg instanceof ProcessPackage)) {
						pkg = pkg.eContainer();
					}

					// if the object is an activity, create a package for it and
					// all it's breakdown elements
					if ((obj instanceof Activity)) {
						List childPkgs = ((ProcessPackage) pkg)
								.getChildPackages();
						ProcessPackage pp = null;
						if (true || childPkgs.size() == 0) {	//158363: always create a package for act
							pp = UmaFactory.eINSTANCE.createProcessPackage();
							pp.setName(((MethodElement) container).getName());
							setElement(EcoreUtil.generateUUID(), pp);
							setSourceId(pp.getGuid());
							childPkgs.add(pp);
						} else {
							pp = (ProcessPackage) childPkgs.get(0);
						}

						pkg = pp;
					}

					((ProcessPackage) pkg).getProcessElements().add((ProcessElement)obj);
				}

				// note: all element references are string type (id)
				// package references are object references
				if (feature.isMany()) {
					List values = (List) container.eGet(feature);
					values.add(obj);
				} else {
					container.eSet(feature, obj);
				}
			}				
		} catch (RuntimeException e) {
			logger.logWarning(NLS.bind(ImportXMLResources.library_error_create_element, e.getMessage()));
			// e.printStackTrace();
		}

		return obj;
	}

	private void setElement(String guid, IModelObject obj) {
		// addElementToContainer(container, obj);
				
		if (!elementsMap.containsKey(guid)) {

			if (obj instanceof MethodElement) {
				((MethodElement) obj).setGuid(guid);
			}

			elementsMap.put(guid, obj);
					
			setDirty(obj);
			newElementsMap.put(guid, obj);
		}
	}

	private void setSourceId(String id) {
		// 154149 - allow package level merging for xml import
		if ( !sourceElementIDs.contains(id) ) {
			sourceElementIDs.add(id);
		}
	}
	
	private void setDirty(EObject obj) {
		// mark the resource as dirty
		Resource res = obj.eResource();
		if ( res != null && !res.isModified() ) {
			res.setModified(true);
		}
	}
	
	/**
	 * Gets element given the guid.
	 * @param guid
	 * @return
	 */
	public IModelObject getElement(String guid) {
		return (IModelObject) elementsMap.get(guid);
	}

	private Date createDate(String dateStr) {
		dateStr = dateStr.replace('T', ' ');
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); //$NON-NLS-1$
		return f.parse(dateStr, new ParsePosition(0));

	}

	private String getString(Object obj) {
		String str = ""; //$NON-NLS-1$
		if (obj instanceof MethodElement) {
			str = LibraryUtil.getTypeName((MethodElement) obj);
		} else if (obj != null) {
			str = obj.toString();
		}

		return str;
	}

	/**
	 * Sets attribute feature value.
	 * @param obj
	 * @param featureName
	 * @param value
	 * @throws Exception
	 */
	public void setAtributeFeatureValue(IModelObject obj, String featureName,
			Object value) throws Exception {
		boolean oldNotify = obj.eDeliver();
		obj.eSetDeliver(false);
		setAtributeFeatureValue_(obj, featureName, value);
		obj.eSetDeliver(oldNotify);
	}
	
	private void setAtributeFeatureValue_(IModelObject obj, String featureName,
			Object value) throws Exception {
		if (obj == null || featureName == null || value == null) {
			return;
		}
		
		if (value instanceof List || value instanceof IModelObject) {
			if (featureName.equals("methodElementProperty")) {		//$NON-NLS-1$
				setMepFeatureValue(obj, featureName, value);
				return;
			}
			throw new Exception(NLS.bind(ImportXMLResources.library_error_set_attribute_2, value)); 
		}

		// find the feature and set the value
		EStructuralFeature feature = FeatureManager.INSTANCE.getRmcFeature(obj
				.eClass(), featureName);
		if (feature == null) {
			Object[] paras = new Object[] { getString(obj), featureName, value };
			logger.logWarning(NLS.bind(ImportXMLResources.library_error_set_attribute, paras) + obj); 
			return;
		} 
		
		setDirty(obj);

		// 158688 - Missing template files in exported xml library
		// process the attachment url
		if ( feature == UmaPackage.eINSTANCE.getGuidanceDescription_Attachments() ) {
			if ( value != null ) {
				value = contentProc.resolveAttachmentResources(obj, value.toString().trim());
			}
			obj.eSet(feature, value);
		}
		
		else if (feature instanceof EReference && value instanceof String) {
			// if the uma feature is a reference feature
			// but the xml feature is an attribute
			// the attribute value must be a guid
			Object e = getElement((String) value);
			if (e != null) {
				if (feature.isMany()) {
					((List) obj.eGet(feature)).add(e);
				} else {
					obj.eSet(feature, e);
				}
			} else {
				Object[] paras = new Object[] { getString(obj),
						feature.getName(), value };
				String msg = NLS.bind(ImportXMLResources.library_error_set_attribute, paras); 
				logger.logWarning(msg);
			}
		} else if (feature instanceof EAttribute) {
			try {
				if (feature.getName().equals("variabilityType")) { //$NON-NLS-1$
					value = VariabilityType.get(value.toString());
				} else if (feature.getName().equals("linkType")) { //$NON-NLS-1$
					value = WorkOrderType.get(value.toString());
				} else if (feature == UmaPackage.eINSTANCE
						.getMethodUnit_ChangeDate()) {
					// convert java.util.Date to xml Date
					value = createDate(value.toString());
				} else if (feature.getName().equals("nodeicon") //$NON-NLS-1$
						|| feature.getName().equals("shapeicon")) { //$NON-NLS-1$
					MethodPlugin tgtPlugin = UmaUtil.getMethodPlugin((MethodElement) obj);
					int ix = tgtPlugin.getName().length() + 1;
										
					String path = value.toString().substring(ix);
					try {
						value = new java.net.URI(path);
					} catch (Throwable e) {
						value = new java.net.URI(NetUtil.encodeFileURL(path));
					}

					// need to copy the resource file to the target library
					contentProc.copyResource(((java.net.URI) value).getPath(), obj, tgtPlugin);
				} else if ((contentProc != null)
						&& (obj instanceof MethodElement)
						&& (value instanceof String)) {
					value = contentProc.resolveResourceFiles(
							(MethodElement) obj, (String) value);
				}
				obj.eSet(feature, value);
			} catch (RuntimeException e) {
				Object[] paras = new Object[] { getString(obj), featureName,
						value };
				logger.logError(NLS.bind(ImportXMLResources.library_error_set_attribute, paras), e); 
				// e.printStackTrace();
			}
		} else {
			Object[] paras = new Object[] { getString(obj), featureName, value };
			logger.logWarning(NLS.bind(ImportXMLResources.library_error_set_attribute_3, paras)); 
		}

	}

	private void setMepFeatureValue(IModelObject obj, String featureName, Object value) {
		List srcList = (List) value;
		int sz = srcList == null ? 0 : srcList.size();		
		EStructuralFeature feature = FeatureManager.INSTANCE.getRmcFeature(obj
				.eClass(), featureName);
		List tgtList = (List) obj.eGet(feature);
		int tsz = tgtList == null ? 0 : tgtList.size();

		//Source list is empty
		if (sz == 0) {
			if (tsz == 0) {
				return;
			}
			tgtList.removeAll(tgtList);
			setDirty(obj);
			return;
		}
		
		//Soruce and target are potentially the same
		boolean same = false;
		if (sz == tsz) {
			same = true;
			for (int i=0; i < sz; i++) {
				org.eclipse.epf.xml.uma.MethodElementProperty srcMep = 
					(org.eclipse.epf.xml.uma.MethodElementProperty) srcList.get(i);
				MethodElementProperty tgtMep = 
					(MethodElementProperty) tgtList.get(i);
				if (! srcMep.getName().equals(tgtMep.getName())) {
					same = false;
					break;
				}
				if (! srcMep.getValue().equals(tgtMep.getValue())) {
					same = false;
					break;
				}
			}
		}
		if (same) {
			return;
		}
		
		//Soruce and target are not the same
		tgtList.removeAll(tgtList);
		for (int i=0; i < sz; i++) {
			Object srcItem = srcList.get(i);
			if (srcItem instanceof org.eclipse.epf.xml.uma.MethodElementProperty) {
				org.eclipse.epf.xml.uma.MethodElementProperty mep = 
						(org.eclipse.epf.xml.uma.MethodElementProperty) srcItem;
				EClass objClass = FeatureManager.INSTANCE
				.getRmcEClass("MethodElementProperty");	//$NON-NLS-1$
				MethodElementProperty xmlMep = (MethodElementProperty) EcoreUtil.create(objClass);
				xmlMep.setValue(mep.getValue());
				xmlMep.setName(mep.getName());
				tgtList.add(xmlMep);
			}
		}
		setDirty(obj);
		//obj.eSet(feature, tgtList);
	}

	/**
	 * Sets reference value.
	 * @param obj
	 * @param featureName
	 * @param id
	 * @throws Exception
	 */
	public void setReferenceValue(IModelObject obj, String featureName, String id)
			throws Exception {
		if (obj == null || featureName == null || id == null) {
			return;
		}
		
		EObject value = getElement(id);
		if (value == null) {
			logger.logWarning(NLS.bind(ImportXMLResources.library_error_set_reference, featureName, id));

			return;
		}

		// find the feature and set the value
		EStructuralFeature feature = FeatureManager.INSTANCE.getRmcFeature(obj
				.eClass(), featureName);
		if (feature == null) {
			Object[] paras = new Object[] { getString(obj), featureName, id };
			logger.logWarning(NLS.bind(ImportXMLResources.library_error_set_reference_2, paras)); 
			return;
		}

		// don't iterate the containment feature
		if (value.eContainer() == obj) {
			return;
		}

		// String str = feature.getEType().getName();
		if (feature.isMany()) {
			try {
				List items = (List) obj.eGet(feature);
				if (!items.contains(value)) {
					items.add(value);
				}
			} catch (RuntimeException e) {
				Object[] paras = new Object[] { getString(obj), featureName, id };
				logger
						.logError(
								NLS.bind(ImportXMLResources.library_error_set_reference_2, paras), e); 
			}
		} else {
			obj.eSet(feature, value);
		}
		
		setDirty(obj);
	}

	/**
	 * Sets work order.
	 * @param umaWorkOrder
	 * @param predId
	 * @deprecated
	 */
	public void setWorkOrder(Object umaWorkOrder, String predId) {
		if (umaWorkOrder instanceof WorkOrder) {
			WorkBreakdownElement e = (WorkBreakdownElement) getElement(predId);
			((WorkOrder) umaWorkOrder).setPred(e);
			
			setDirty((EObject)umaWorkOrder);

		}
	}

	/**
	 * Fixes library.
	 */
	public void fixLibrary() {
		MethodLibrary rootObject = getRoot();
		for (Iterator it = ((MethodLibrary) rootObject).getMethodPlugins()
				.iterator(); it.hasNext();) {
			fixPlugin((MethodPlugin) it.next());
		}
	}
	
	/**
	 * fix task descriptors for import
	 * 
	 */
	public void fixTaskDescriptors(Map<String, TaskDescriptor> tdMap) {
		if (tdMap == null || tdMap.isEmpty()) {
			return;
		}		
		for (Iterator<Map.Entry<String, TaskDescriptor>> it = tdMap.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, TaskDescriptor> entry = it.next();
			String guid = entry.getKey();
			TaskDescriptor td = entry.getValue();
			org.eclipse.epf.uma.TaskDescriptor umaTd = (org.eclipse.epf.uma.TaskDescriptor) elementsMap.get(guid);
			assert(umaTd != null);
			if (umaTd == null) {
				continue;
			}
			List steps = td.getStep();
			for (int i=0; i<steps.size(); i++) {
				org.eclipse.epf.xml.uma.MethodElement step = (org.eclipse.epf.xml.uma.MethodElement) steps.get(i);
				Section umaStep =  (Section) elementsMap.get(step.getId());
				assert(umaStep != null);
				if (umaStep != null) {
					umaTd.getSelectedSteps().add(umaStep);
				}
			}			
		}		
	}

	private MethodPackage cpRoot = null;

	private MethodPackage dpRoot = null;

//	private MethodPackage pcRoot = null;

	private void fixPlugin(MethodPlugin plugin) {

		// fix the root custom categories
		fixCustomCategories(plugin);

		List cpPkgs = new ArrayList();
		List dpPkgs = new ArrayList();
//		List pcPkgs = new ArrayList();

		cpRoot = UmaUtil.findMethodPackage(plugin,
				ModelStructure.DEFAULT.capabilityPatternPath);
		dpRoot = UmaUtil.findMethodPackage(plugin,
				ModelStructure.DEFAULT.deliveryProcessPath);
//		pcRoot = UmaUtil.findMethodPackage(plugin,
//				ModelStructure.DEFAULT.processContributionPath);
		getAllPackages(cpRoot, cpPkgs);
		getAllPackages(dpRoot, dpPkgs);
		
//		if ( pcPkgs != null ) {
//			getAllPackages(pcRoot, pcPkgs);
//		}
			
		List procs = new ArrayList();
		for (Iterator it = plugin.eAllContents(); it.hasNext();) {
			EObject o = (EObject)it.next();
			boolean oldNotify = o.eDeliver();
			try	{
				// turn off notifications to avoid possible deadlock or thread issue
				// 154142 - import xml caused thread access error
				o.eSetDeliver(false);
				
				if (o instanceof ProcessComponent) {
					procs.add(o);
				} else if (o instanceof DescribableElement) {
/*					String pName = ((DescribableElement) o).getPresentationName();
					if (pName == null || pName.length() == 0) {
						((DescribableElement) o)
								.setPresentationName(((DescribableElement) o)
										.getName());
					}*/
				}
	
				// fix the name string
				if (o instanceof MethodElement && !(o instanceof Section)) {
					String name = ((MethodElement) o).getName();
					String new_name = StrUtil.makeValidFileName(name);
					if ( !new_name.equals(name) ) {
						((MethodElement) o).setName(new_name);
					}
				}
			} finally {
				o.eSetDeliver(oldNotify);
			}
		}

		if (procs.size() > 0) {
			for (Iterator it = procs.iterator(); it.hasNext();) {
				ProcessComponent pc = (ProcessComponent) it.next();
				Object proc = pc.getProcess();
				if (proc instanceof DeliveryProcess) {
					if (!dpPkgs.contains(pc)) {
						moveProcessComponent(pc, dpRoot);
					}
				} else if (proc instanceof CapabilityPattern) {
					if (!cpPkgs.contains(pc)) {
						moveProcessComponent(pc, cpRoot);
					}
				}
			}
		}
	}

	private void getAllPackages(MethodPackage pkg, List pkgs) {
		pkgs.add(pkg);
		for (Iterator it = pkg.getChildPackages().iterator(); it.hasNext();) {
			getAllPackages((MethodPackage) it.next(), pkgs);
		}
	}

	/**
	 * move the process package to the specified container, if the package is
	 * the only child of it's parent, move it's parent
	 * 
	 * @param pkg
	 * @param container
	 */
	private void moveProcessComponent(ProcessPackage pkg,
			MethodPackage container) {
		// make sure Capability patterns are in the Capability packages
		Object o = pkg.eContainer();
		if ((o instanceof ProcessPackage)
				&& (((ProcessPackage) o).getChildPackages().size() == 1)
				&& o != cpRoot && o != dpRoot ) {
			moveProcessComponent((ProcessPackage) o, container);
		} else {
			container.getChildPackages().add(pkg);
		}
	}

	/**
	 * find the root custom cagegory and put to the root package
	 * 
	 * @param plugin
	 *            MethodPlugin
	 */
	private void fixCustomCategories(MethodPlugin plugin) {
		ContentPackage pkg = UmaUtil.findContentPackage(plugin,
				ModelStructure.DEFAULT.customCategoryPath);
		List items = new ArrayList(pkg.getContentElements());

		List rootItems = new ArrayList();
		while (items.size() > 0) {
			CustomCategory cc = (CustomCategory) items.remove(0);
			rootItems.add(cc);
			
			// 158691Custom Category is different in exported library
			// need to remove sub-categories recursively
			fixCustomCategories(cc, items, rootItems);
		}

		TngUtil.getRootCustomCategory(plugin).getCategorizedElements().addAll(
				rootItems);

	}
	
	/**
	 * find the root custom categories by removing the sub-categories recursively
	 * 
	 * @param cc
	 * @param items
	 * @param rootItems
	 */
	private void fixCustomCategories(CustomCategory cc, List items, List rootItems) {
		for (Iterator it = cc.getSubCategories().iterator(); it.hasNext();) {
			Object item = it.next();
			items.remove(item);
			rootItems.remove(item);
			fixCustomCategories((CustomCategory)item, items, rootItems);
		}
		
		// also iterate the contained elements
		for (Iterator it = cc.getCategorizedElements().iterator(); it
				.hasNext();) {
			Object item = it.next();
			if (item instanceof CustomCategory) {
				items.remove(item);
				rootItems.remove(item);
				fixCustomCategories((CustomCategory)item, items, rootItems);
			}
		}
	}
	
	/**
	 * delete the un-needed elements are clear resources 
	 */
	public void deleteElements() {
		
		IFileManager fileMgr = Services.getFileManager();

		List deletedElements = new ArrayList();
		
		for (Iterator it = elementsMap.entrySet().iterator(); it.hasNext(); ) {
			Map.Entry entry = (Map.Entry)it.next();
			String id = (String)entry.getKey();
			MethodElement e = (MethodElement)entry.getValue();
			if ( sourceElementIDs.contains(id) ) {
				continue;
			} else if (overwrite) {
				boolean toDelete = !(e instanceof MethodLibrary || e instanceof MethodPlugin
									|| e instanceof MethodConfiguration);
				if (toDelete) {
					MethodElement plugin = UmaUtil.getMethodPlugin(e);
					toDelete = plugin != null && !isNewElement(plugin.getGuid())
					&& sourceElementIDs.contains(plugin.getGuid());
				}
				if (toDelete) {
					deletedElements.add(e);
					handleActivityRemoval(e);
					//EcoreUtil.remove(e);
					continue;
				}
			}
			
			if (!overwrite && mergeLevel == 2) {
				continue;
			}			
			
			if ( (e instanceof CustomCategory) && TngUtil.isRootCustomCategory( (CustomCategory)e ) ) {
				continue;
			}			
			
			// delete the element if the containg package is in the source library
			// don't delete if the package is not in the xml library
			MethodPackage pkg = TngUtil.getMethodPackage(e);
			
			if ( pkg != null ) { 
				String pid = pkg.getGuid();
				if ( sourceElementIDs.contains(pid) ) {
					deletedElements.add(e);
					handleActivityRemoval(e);
					
					// delete the element from the library
					//EcoreUtil.remove(e);				
				} else if (e instanceof Activity) {
					
					boolean toCheck = true;
					MethodPackage pc = pkg;					
					while(pc != null && ! (pc instanceof ProcessComponent)) {
						pc = TngUtil.getParentMethodPackage(pc);
					}
					if (pc instanceof ProcessComponent) {
						toCheck = sourceElementIDs.contains(pc.getGuid());
					}
					
					if (toCheck) {
						pkg = TngUtil.getMethodPackage(pkg);
						while (pkg != null) {
							pid = pkg.getGuid();
							if (sourceElementIDs.contains(pid)) {
								deletedElements.add(e);
								handleActivityRemoval(e);
								break;
							}
							pkg = TngUtil.getParentMethodPackage(pkg);
						}
					}
				}
			}
		}
		
		// delete the resources
		deleteResoruces(deletedElements);
	}

	//	164581
	private void handleActivityRemoval(MethodElement e) {
		if (!(e instanceof BreakdownElement)) {
			return;
		}
		BreakdownElement eact = (BreakdownElement) e;
		Activity sact = eact.getSuperActivities();
		if (sact != null) {
			sact.getBreakdownElements().remove(eact);
		}
	}
	
	// clear resources for deleted elements
	private void deleteResoruces(List deletedElements) {
		if ( deletedElements.size() == 0 ) {
			return;
		}
		
		IFileManager fileMgr = Services.getFileManager();

		for (Iterator it = deletedElements.iterator(); it.hasNext(); ) {
			MethodElement e = (MethodElement)it.next();
			
			EObject obj = null;
			if ( e instanceof DescribableElement ) {
				obj = ((DescribableElement)e).getPresentation();
			} else if ( e instanceof ContentDescription ||
					overwrite && e instanceof ProcessComponent) {
				obj = e;
			}
			if ( obj != null ) {
				MethodElement plugin = UmaUtil.getMethodPlugin(e);
				Resource pluginRes = plugin == null ? null : plugin.eResource();
				
				Resource res = obj.eResource();
				if ( res != null && res != pluginRes) {		//Protected from bugs like 177042
					String file = res.getURI().toFileString();
					if ( debug ) {
						System.out.println("deleting resource: " + file); //$NON-NLS-1$
					}
					
					if ( !fileMgr.delete(file) ) {
						if ( debug ) {
							System.out.println("unable to delete file: " + file); //$NON-NLS-1$
						}
					}
					
					EObject resMgr = MultiFileSaveUtil.getResourceManager(res);
					if (resMgr != null) {
						EcoreUtil.remove(resMgr);
					}
					EObject resDes = MultiFileSaveUtil.getResourceDescriptor(res);
					if (resDes != null) {
						EcoreUtil.remove(resDes);
					}
					if (overwrite) {						
						File folder = new File(file).getParentFile();
						File files[] = folder.listFiles();
						if (files == null || files.length == 0 ) {
							if ( debug ) {
								System.out.println("deleting folder resource: " + folder); //$NON-NLS-1$
							}
							if ( !fileMgr.delete(folder.getAbsolutePath()) ) {
								if ( debug ) {
									System.out.println("unable to delete folder file: " + folder); //$NON-NLS-1$
								}
							}
						}
					}
				}								
			}
			
			EcoreUtil.remove(e);
		}
	}
			
	/**
	 * @param guid
	 * @return true if the element given by guid is new.
	 */
	public boolean isNewElement(String guid) {
		return newElementsMap.containsKey(guid);
	}
	
	public void setMergeLevel(int mergeLevel) {
		this.mergeLevel = mergeLevel;
	}
	
	private boolean toHandle(IModelObject rmcObj, EStructuralFeature rmcFeature) {
		EStructuralFeature feature = rmcFeature;
		if (feature == null || feature.isDerived()) {
			return false;
		}
		
		EReference eref = null;
		if (rmcFeature instanceof EReference) {
			eref = (EReference) rmcFeature;
			if (eref.isContainment()) {
				if (mergeLevel != 2) {	//The old delete code would take of it
					return false;
				}
				if (eref.isMany()) {
					return false;
				}
			} else if (rmcObj instanceof ContentCategory) {
				if (mergeLevel == 2 && eref.isMany()) {
					return false;
				}
			} else if (rmcFeature == UmaPackage.eINSTANCE
					.getWorkBreakdownElement_LinkToPredecessor()) {
				return false;
			}
		}
		
		if (rmcObj instanceof MethodPlugin) {
			if (feature == UmaPackage.eINSTANCE.getMethodPlugin_MethodPackages()) {
				return false;
			}
		} else if (rmcObj instanceof MethodConfiguration) {
			if (eref != null) {
				return false;
			}
		} else if (rmcObj instanceof MethodPackage) {
			if (rmcFeature == UmaPackage.eINSTANCE.getMethodPackage_ChildPackages() &&
					!(rmcObj instanceof ProcessPackage)) {
				return false;
			}
		} 
		
		return true;
	}
	
	private boolean isManyReference(EStructuralFeature rmcFeature) {
		if (! (rmcFeature instanceof EReference)) {
			return false;
		}		
		return ((EReference) rmcFeature).isMany();
	}
	
	public void handleNullXmlValue(IModelObject xmlObj, IModelObject rmcObj, String xmlFeatureName) throws Exception {		
		if (rmcObj instanceof MethodLibrary) {
			return;
		}
		
		EStructuralFeature feature = FeatureManager.INSTANCE.getRmcFeature(
				rmcObj.eClass(), xmlFeatureName);
		if (! toHandle(rmcObj, feature)) {
			return;
		}

		Object rmcValue = rmcObj.eGet(feature);
		if (rmcValue == null) {
			return;
		}

		if (rmcValue instanceof List) {
			initRmcList(rmcObj, feature);
			return;
		}

		boolean oldNotify = rmcObj.eDeliver();
		rmcObj.eSet(feature, null);
		rmcObj.eSetDeliver(oldNotify);
		setDirty(rmcObj);
	}
	
	public void initListValueMerge(IModelObject xmlObj, IModelObject rmcObj, String xmlFeatureName,
			List xmlList, Set<EStructuralFeature> seenRmcFeatures) throws Exception {
		if (rmcObj instanceof MethodLibrary) {
			return;
		}
		
		if (! (xmlList instanceof List)) {
			return;
		}
		
		EStructuralFeature feature = FeatureManager.INSTANCE.getRmcFeature(
				rmcObj.eClass(), xmlFeatureName);
		if (feature == null || seenRmcFeatures.contains(feature)) {	//feature could be mapped from more than
			return;													//one xml features
		}
		seenRmcFeatures.add(feature);
		
		if (! toHandle(rmcObj, feature)) {
			return;
		}

		initRmcList(rmcObj, feature);
	}

	private void initRmcList(IModelObject rmcObj, EStructuralFeature feature) {
		if (!isManyReference(feature)) {
			return;
		}

		Object rmcValue = rmcObj.eGet(feature);
		if (!(rmcValue instanceof List)) {
			return;
		}
		List rmcList = (List) rmcValue;

		if (rmcList.isEmpty()) {
			return;
		}

		boolean oldNotify = rmcObj.eDeliver();
		rmcObj.eSetDeliver(false);
		rmcList.clear();
		rmcObj.eSetDeliver(oldNotify);
		setDirty(rmcObj);
	}
	
	public void handleWorkOrder(org.eclipse.epf.xml.uma.WorkOrder xmlWorkOrder, WorkOrder umaWorkOrder) {	
		org.eclipse.epf.xml.uma.WorkOrderType xmlLinkType = xmlWorkOrder.getLinkType();
		if (xmlLinkType != null) {
			WorkOrderType umaLinkType = org.eclipse.epf.uma.WorkOrderType
					.get(xmlLinkType.toString());
			umaWorkOrder.setLinkType(umaLinkType);
		}
						
		boolean isSuccessor = false;
		String xmlPropertiesValue = xmlWorkOrder.getProperties();
		String scopeValue = null;
		if (xmlPropertiesValue != null) {
			EClass objClass = FeatureManager.INSTANCE.getRmcEClass("MethodElementProperty");	//$NON-NLS-1$
			List tgtList = umaWorkOrder.getMethodElementProperty();
			tgtList.removeAll(tgtList);
			
			List<String> propList = TngUtil.convertStringsToList(xmlPropertiesValue, XMLLibrary.WorkOrderPropStringSep);
			for (String prop : propList) {
				if (! prop.startsWith("name=")) {		//$NON-NLS-1$
					continue;
				}
				int ix = prop.indexOf(XMLLibrary.WorkOrderPropStringFieldSep + "value=");//$NON-NLS-1$
				if (ix < 6) {
					continue;
				}
				String name = prop.substring(5, ix).trim();
				if (name.length() == 0) {
					continue;
				}
				int valueStartingIx = ix + 7;
				if (prop.length() < valueStartingIx) {
					continue;
				}
				String strFromValueStartingIx = prop.substring(valueStartingIx);
				String value = strFromValueStartingIx;
				ix = strFromValueStartingIx.indexOf(XMLLibrary.WorkOrderPropStringFieldSep + "scope=");//$NON-NLS-1$
				if (ix > 0) {
					value = strFromValueStartingIx.substring(0, ix);
					int scopeValueStartingIx = ix + 7;
					scopeValue = strFromValueStartingIx.substring(scopeValueStartingIx);
				}
			
				MethodElementProperty umaMep = (MethodElementProperty) EcoreUtil.create(objClass);
				umaMep.setName(name);
				umaMep.setValue(value);
				tgtList.add(umaMep);
				if (name.equals(MethodElementPropertyHelper.WORK_ORDER__SUCCESSOR)) {		//$NON-NLS-1$						 
					isSuccessor = true;
				}
			}
		}

		WorkBreakdownElement e = (WorkBreakdownElement) getElement(xmlWorkOrder.getValue());
		
		if (isSuccessor && scopeValue != null) {			
			EObject cont = e.eContainer();
			if (cont instanceof ProcessPackage) {
				ProcessPackage parent = (ProcessPackage) cont;
				ProcessElement scopeElement = (ProcessElement) getElement(scopeValue);
				if (scopeElement != null) {
					ProcessPackage scopePkg = (ProcessPackage) scopeElement.eContainer();
					parent.getProcessElements().remove(umaWorkOrder);
					scopePkg.getProcessElements().add(umaWorkOrder);
				}
			}
			e.getLinkToPredecessor().remove(umaWorkOrder);
		}
		
		umaWorkOrder.setPred(e);
		setDirty(umaWorkOrder);		
	}
	
}