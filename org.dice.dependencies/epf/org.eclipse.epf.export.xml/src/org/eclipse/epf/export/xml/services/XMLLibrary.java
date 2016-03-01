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
package org.eclipse.epf.export.xml.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.epf.common.service.versioning.VersionUtil;
import org.eclipse.epf.dataexchange.util.ContentProcessor;
import org.eclipse.epf.dataexchange.util.ILogger;
import org.eclipse.epf.export.xml.ExportXMLResources;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.util.MethodElementPropertyHelper;
import org.eclipse.epf.library.edit.util.MethodLibraryPropUtil;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.ecore.IModelObject;
import org.eclipse.epf.uma.ecore.Type;
import org.eclipse.epf.xml.uma.BreakdownElement;
import org.eclipse.epf.xml.uma.ContentCategoryPackage;
import org.eclipse.epf.xml.uma.ContentElement;
import org.eclipse.epf.xml.uma.ContentPackage;
import org.eclipse.epf.xml.uma.DocumentRoot;
import org.eclipse.epf.xml.uma.MethodConfiguration;
import org.eclipse.epf.xml.uma.MethodElement;
import org.eclipse.epf.xml.uma.MethodElementProperty;
import org.eclipse.epf.xml.uma.MethodLibrary;
import org.eclipse.epf.xml.uma.MethodPackage;
import org.eclipse.epf.xml.uma.MethodPlugin;
import org.eclipse.epf.xml.uma.ProcessComponent;
import org.eclipse.epf.xml.uma.ProcessElement;
import org.eclipse.epf.xml.uma.ProcessPackage;
import org.eclipse.epf.xml.uma.Section;
import org.eclipse.epf.xml.uma.UmaFactory;
import org.eclipse.epf.xml.uma.UmaPackage;
import org.eclipse.epf.xml.uma.VariabilityType;
import org.eclipse.epf.xml.uma.WorkBreakdownElement;
import org.eclipse.epf.xml.uma.WorkOrder;
import org.eclipse.epf.xml.uma.WorkOrderType;
import org.eclipse.epf.xml.uma.util.UmaResourceFactoryImpl;
import org.eclipse.osgi.util.NLS;

import com.ibm.icu.text.SimpleDateFormat;

/**
 * XmlLibrary represents a method library loaded from a specified xml file The
 * xml file should be valid to the xml schema for MethodLibrary
 * 
 * @author Jinhua Xi
 * @since 1.0
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=162153
 */
public class XMLLibrary {

	public static final String WorkOrderPropStringSep = "\n\n";			//$NON-NLS-1$
	public static final String WorkOrderPropStringFieldSep = "\n";			//$NON-NLS-1$
	
	private String filePath;

	private ILogger logger;

	private ContentProcessor contentProc = null;

	private MethodLibrary rootObject;

	// map of content category package for each plugin id
	private Map contentCategoryPkgMap = new HashMap();

	// map of created elements, guid - EDataObject
	private Map elementsMap = new HashMap();
	
	private Map<String, String> guidToPlugNameMap;
	
	private Map<WorkOrder, org.eclipse.epf.uma.WorkOrder> successOrWorkOrderMap;
	
	private int synFreeLibIx = -1; //-1: unset, 0: false, 1: true;

	/**
	 * Creates a new instance.
	 */
	public XMLLibrary(ContentProcessor contentProc, ILogger logger,
			String filePath) {
		this.contentProc = contentProc;
		this.logger = logger;
		this.filePath = filePath;

	}

	/**
	 * @return root object.
	 */
	public IModelObject getRoot() {
		return this.rootObject;
	}

	/**
	 * @return file path string.
	 */
	public String getFilePath() {
		return this.filePath;
	}

	/**
	 * get an array of plugin ids in the library
	 * 
	 * @return
	 */
	public String[] getPluginIds() {
		List plugins = rootObject.getMethodPlugin();
		String[] ids = new String[plugins.size()];
		for (int i = 0; i < plugins.size(); i++) {
			ids[i] = ((MethodPlugin) plugins.get(i)).getId();
		}

		return ids;
	}
	
	public String[] getConfigIds() {
		List configs = rootObject.getMethodConfiguration();
		String[] ids = new String[configs.size()];
		for (int i = 0; i < configs.size(); i++) {
			ids[i] = ((MethodConfiguration) configs.get(i)).getId();
		}

		return ids;
	}

	/**
	 * @param id
	 * @param name
	 * @return a new XML library.
	 */
	public IModelObject createLibrary(String id, String name) {

		MethodLibrary root = UmaFactory.eINSTANCE.createMethodLibrary();
		String version = "";								//$NON-NLS-1$	
		for (Iterator iter = VersionUtil.getAllToolIDs().iterator();iter.hasNext();) {
			String toolID = (String)iter.next();
			String toolVersion = VersionUtil.getVersions(toolID).getMinToolVersionForCurrentXMLSchemaVersion().getToolVersion().toString();
			if (version.length() > 0) {
				version += VersionUtil.XML_VERSIONS_SEPARATOR;
			}
			version += toolID + VersionUtil.XML_TOOL_VERSION_SEPARATOR + toolVersion;		
		}
		if (version.length() > 0) {
			root.setTool(version);
		}
		return create(id, name, root);
	}

	/**
	 * Loads the xml file.
	 */
	public void load() throws Exception {
/*		try {*/
			ResourceSet resourceSet = new ResourceSetImpl();

			// Get the URI of the model file.
			//
			URI fileURI = URI.createFileURI((new File(this.filePath)).getAbsolutePath());

			// Create a resource for this file.
			//
			// Resource resource = resourceSet.createResource(fileURI);
			Resource resource = new UmaResourceFactoryImpl()
					.createResource(fileURI);
			resourceSet.getResources().add(resource);

			resource.load(new HashMap());

			// Resource resource = resourceSet.getResource(fileURI, false);

			// EClass eClass =
			// ExtendedMetaData.INSTANCE.getDocumentRoot(umaPackage);

			DocumentRoot root = (DocumentRoot) resource.getContents().get(0);
			for (Iterator itr = root.eContents().iterator(); itr.hasNext();) {
				Object o = itr.next();
				if (o instanceof MethodLibrary) {
					this.rootObject = (MethodLibrary) o;
					break;
				}
			}
/*
			// System.out.println("Resource loaded");
		} catch (IOException e) {
			logger.logError(NLS.bind(
					ExportXMLResources.xmlLibrary_error_load_xml, filePath), e);
		}*/
	}

	/**
	 * create the library and return the root object
	 * 
	 * @param filePath
	 * @return
	 */
	private MethodLibrary create(String id, String name, MethodElement root) {

		// Create a resource set
		//
		ResourceSet resourceSet = new ResourceSetImpl();

		// Get the URI of the model file.
		//
		File file = new File(this.filePath);
		URI fileURI = URI.createFileURI(file.getAbsolutePath());

		// Create a resource for this file.
		//
		// Resource resource = resourceSet.createResource(fileURI);
		Resource resource = new UmaResourceFactoryImpl()
				.createResource(fileURI);
		resourceSet.getResources().add(resource);

		// Add the initial model object to the contents.
		// root.setId(id);
		// root.setName(name);

		setElement(id, root);

		// this.rootObject = root;
		if (root != null) {
			resource.getContents().add(root);
		}

		try {
			Map options = new HashMap();
			options.put(XMLResource.OPTION_ENCODING, "UTF-8"); //$NON-NLS-1$
			resource.save(options);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (root instanceof MethodLibrary) {
			this.rootObject = (MethodLibrary) root;
		}

		return this.rootObject;
	}

	/**
	 * @param pluginId
	 * @return a content catergory package in the plugin given by pluginId.
	 */
	public IModelObject getContentCategoryPackage(String pluginId) {

		ContentCategoryPackage pkg = (ContentCategoryPackage) contentCategoryPkgMap
				.get(pluginId);
		if (pkg != null) {
			return pkg;
		}

		MethodPlugin plugin = (MethodPlugin) getElement(pluginId);
		if (plugin == null) {
			logger.logWarning(NLS.bind(ExportXMLResources.xmlLibrary_no_plugin,
					pluginId));
			return null;
		}

		pkg = UmaFactory.eINSTANCE.createContentCategoryPackage();
		pkg.setName("ContentCategories"); //$NON-NLS-1$

		setElement(EcoreUtil.generateUUID(), pkg);

		plugin.getMethodPackage().add(pkg);

		contentCategoryPkgMap.put(pluginId, pkg);
		return pkg;
	}

	/**
	 * Saves the xml file.
	 */
	public void save() throws Exception {
		// Save the contents of the resource to the file system.
		//
		Map options = new HashMap();
		options.put(XMLResource.OPTION_ENCODING, "UTF-8"); //$NON-NLS-1$
		Resource resource = rootObject.eResource();
		resource.save(options);
	}

	/**
	 * @return the root object.
	 */
	public IModelObject open() {
		return rootObject;
	}

	/**
	 * @param guid
	 * @return the corresponding base library element.
	 */
	public IModelObject getElement(String guid) {
		return (IModelObject) elementsMap.get(guid);
	}

	/**
	 * @param obj
	 * @return the corresponding base library element.
	 */
	public IModelObject getElement(Object obj) {
		if (obj instanceof MethodElement) {
			return getElement(((MethodElement) obj).getId());
		}

		return null;
	}

	
	/**
	 * @param obj
	 * @return elmenent id string.
	 */
	public String getElementId(IModelObject obj) {

		if (obj instanceof MethodElement) {
			return ((MethodElement) obj).getId();
		} else if (obj instanceof WorkOrder) {
			return ((WorkOrder) obj).getId();
		}

		return null;
	}

	private void setElement(String guid, IModelObject obj) {
		// addElementToContainer(container, obj);
		if (!elementsMap.containsKey(guid)) {

			if (obj instanceof MethodElement) {
				((MethodElement) obj).setId(guid);
			}

			elementsMap.put(guid, obj);
		}
	}

	// private void addElementToContainer(EDataObject container, EDataObject
	// child) {
	//		
	// try {
	// if ( container == null ) {
	// return;
	// }
	//			
	// if ( child instanceof MethodLibrary || child instanceof MethodPlugin ||
	// child instanceof MethodPackage ) {
	// return;
	// }
	//			
	// while ( !(container instanceof MethodPackage) && (container != null) ) {
	// container = container.eContainer();
	// }
	//			
	// if ( container instanceof ContentPackage ) {
	// ((ContentPackage)container).getContentElement().add(child);
	// } else if ( container instanceof ProcessPackage ) {
	// ((ProcessPackage)container).getProcessElement().add(child);
	// } else {
	// System.out.println("Don't know how to set element " + child);
	// }
	// } catch (RuntimeException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	/**
	 * create a child element with the specified guid
	 * 
	 * @param container
	 *            container of the element to be created
	 * @param umaFeatureName
	 *            uma feature for the element to be created
	 * @param umaEClassName
	 *            uma EClass name for the feature
	 * @param umaElementType
	 *            uma element type for the element to be created
	 * @param guid
	 *            guid of the element to be created
	 * @return EDataObject the Xml uma element
	 */
	public IModelObject createElement(IModelObject container,
			String umaFeatureName, String umaEClassName, String umaElementType,
			String guid) {

		IModelObject obj = getElement(guid);
		if (obj == null) {
			if (FeatureManager.INSTANCE.isUnneededRmcFeature(umaFeatureName)) {
				return null;
			}
			EStructuralFeature feature = FeatureManager.INSTANCE.getXmlFeature(
					container.eClass(), umaFeatureName, umaElementType);
			if (feature == null) {
				logger.logWarning(NLS.bind(
						ExportXMLResources.xmlLibrary_no_feature, container
								.eClass().getName(), umaFeatureName));
				return null;
			}

			// if ( !(feature instanceof EReference) ) {
			// System.out.println("Error creating element: containment feature
			// should be EReference type. " + feature.getName());
			// //return null;
			// }

			// // this is an easier way, test it,
			// // does not work since feature.getEType() returns the base
			// element type, not the extended one
			// // for example, it returns MethodPackage instead of
			// ProcessPackage or ContentPackage
			// // so we need to make our own map
			// EClassifier c = feature.getEType();
			// if ( !c.getName().equals(umaEClassName) ) {
			// System.out.println("EClass name not match: " + c.getName() + "
			// --- " + umaEClassName);
			// }

			EClass objClass = FeatureManager.INSTANCE
					.getXmlEClass(umaEClassName);
			if (objClass == null) {
				logger.logWarning(NLS.bind(
						ExportXMLResources.xmlLibrary_no_class, umaEClassName));
				return null;
			}

			obj = (IModelObject) EcoreUtil.create(objClass);
			setElement(guid, obj);

			if (obj instanceof WorkOrder) {
				// WorkOrder is not contained in a processPackage in xml model
				// it's contained by the breakdownElement
				// so we save the element id-object to the map and continue
				// don't set the feature value
				
				// the workOrder in EPF UMA model is referenced by feature
				// WorkBreakDownElement_LinkToPredecessor,
				// the corresponding feature in the xml model is 
				// WorkBreakDownElement_Predecessor, 
				// the feature value is a list of guid of the WorkOrders
				// when this feature value is set in the setReferenceValue() call
				// get the XmlUma object, if it's WorkOrder, 
				// add to the current workorder to the WorkBreakDownElement
				// Jinhua Xi, 08/24/2006
				return obj;
			}

			// note: all element references are string type (id)
			// package references are object references
			if (feature.isMany()) {
				List values = (List) container.eGet(feature);
				try {
					if (feature instanceof EAttribute) {
						values.add(guid);

						// need to put the object in a container
						// for example, the ContaiedArtifacts
						addToContainer(container, obj);

					} else if (feature instanceof EReference) {
						try {
							values.add(obj);
						} catch (RuntimeException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						logger
								.logWarning(NLS
										.bind(
												ExportXMLResources.xmlLibrary_error_create_element,
												feature.getName()));
					}
				} catch (RuntimeException e) {
					logger.logError(NLS.bind(
							ExportXMLResources.xmlLibrary_error_set_value, obj,
							container), e);
					// e.printStackTrace();
				}
			} else {
				if (feature instanceof EAttribute) {
					container.eSet(feature, guid);
					addToContainer(container, obj);
				} else if (feature instanceof EReference) {
					container.eSet(feature, obj);
				} else {
					logger.logWarning(NLS.bind(
							ExportXMLResources.xmlLibrary_error_create_element,
							feature.getName()));
				}
			}
		}

		return obj;
	}

	private void addToContainer(EObject container, EObject obj) {

		if (container == null) {
			return;
		}
		if (container instanceof ContentPackage) {
			((ContentPackage) container).getContentElement().add((ContentElement)obj);
		} else if (container instanceof ProcessPackage) {
			((ProcessPackage) container).getProcessElement().add((ProcessElement)obj);
			// } else if ( (obj instanceof Constraint) && (container instanceof
			// MethodElement) ) {
			// // the owner rule should be a containment 0..n feature, waiting
			// for model fix
			// //((MethodElement)container).setO
		} else {
			addToContainer(container.eContainer(), obj);
		}
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
		if (obj == null || featureName == null || value == null) {
			return;
		}

		if (value instanceof List || value instanceof IModelObject) {
			if (featureName.equals("methodElementProperty")) {		//$NON-NLS-1$
				setMepFeatureValue(obj, featureName, value);
				return;
			}
			logger.logWarning(NLS.bind(
					ExportXMLResources.xmlLibrary_invalid_feature_value,
					featureName, value));

			return;
		}

		// find the feature and set the value
		EStructuralFeature feature = FeatureManager.INSTANCE.getXmlFeature(obj
				.eClass(), featureName);
		if (feature == null) {

			// ignore missing features for WorkOrder
			if (!(obj instanceof WorkOrder)) {
				Object[] paras = new Object[] { featureName, obj, value };
				logger
						.logWarning(NLS
								.bind(
										ExportXMLResources.xmlLibrary_error_set_value_2,
										paras));
			}
			return;
		}

		// 158688 - Missing template files in exported xml library
		// process the attachment url
		if ( feature == UmaPackage.eINSTANCE.getGuidanceDescription_Attachment() ) {
			if ( value != null ) {
				value = contentProc.resolveAttachmentResources(obj, value.toString().trim());
			}
			obj.eSet(feature, value);
		}
		
		if (feature instanceof EAttribute) {
			try {
				if (feature.getName().equals("variabilityType")) { //$NON-NLS-1$
					value = VariabilityType.get(value.toString());
				} else if (feature.getName().equals("linkType")) { //$NON-NLS-1$
					value = WorkOrderType.get(value.toString());
				} else if (value instanceof java.util.Date) {
					// convert java.util.Date to xml Date
					value = getXmlDate((java.util.Date) value);
				} else if ((contentProc != null)
						&& (obj instanceof MethodElement)
						&& (value instanceof String)) {
					value = contentProc.resolveResourceFiles(
							(MethodElement) obj, (String) value);
				} else if (value instanceof java.net.URI) {									
					org.eclipse.epf.uma.MethodPlugin srcPlugin = ExportResourceHandler.getSourcePlugin((org.eclipse.epf.xml.uma.MethodElement) obj);		
					value = srcPlugin.getName() + "/" + ((java.net.URI) value).getPath(); ////$NON-NLS-1$
					if (contentProc != null) {
						contentProc.copyResource((String) value, obj, srcPlugin);
					}
				}
				obj.eSet(feature, value);
			} catch (RuntimeException e) {
				Object[] paras = new Object[] { featureName, obj, value };
				logger
						.logError(
								NLS
										.bind(
												ExportXMLResources.xmlLibrary_error_set_value_2,
												paras), e);
			}
		} else {
			System.out.println(ExportXMLResources.xmlLibrary_error_set_value_3); 
		}

	}

	private void setMepFeatureValue(IModelObject obj, String featureName, Object value) {
		List srcList = (List) value;
		if (srcList == null || srcList.isEmpty()) {
			return;
		}
		if (obj instanceof WorkOrder) {
			setMepFeatureValueForWorkOrder(srcList, (WorkOrder) obj);
			return;
		}
		
		EStructuralFeature feature = FeatureManager.INSTANCE.getXmlFeature(obj
				.eClass(), featureName);
		List tgtList = (List) obj.eGet(feature);
		for (int i=0; i < srcList.size(); i++) {
			Object srcItem = srcList.get(i);
			if (srcItem instanceof org.eclipse.epf.uma.MethodElementProperty) {
				org.eclipse.epf.uma.MethodElementProperty mep = 
						(org.eclipse.epf.uma.MethodElementProperty) srcItem;
				EClass objClass = FeatureManager.INSTANCE
				.getXmlEClass("MethodElementProperty");	//$NON-NLS-1$
				MethodElementProperty xmlMep = (MethodElementProperty) EcoreUtil.create(objClass);
				xmlMep.setValue(mep.getValue());
				xmlMep.setName(mep.getName());
				tgtList.add(xmlMep);
			}
		}
		//obj.eSet(feature, tgtList);
	}

	private void setMepFeatureValueForWorkOrder(List srcList, WorkOrder xmlWorkOrder) {
		Object obj = getSuccessOrWorkOrderMap().get(xmlWorkOrder);
		org.eclipse.epf.uma.WorkOrder umaWorkOrder = null;
		org.eclipse.epf.uma.ProcessElement scopeElement = null;
		if (obj instanceof org.eclipse.epf.uma.WorkOrder) {
			umaWorkOrder = (org.eclipse.epf.uma.WorkOrder) obj;
		}
		
		
		
		String propertiesValue = "";	//$NON-NLS-1$	
		for (Object srcItem : srcList) {
			if (srcItem instanceof org.eclipse.epf.uma.MethodElementProperty) {
				org.eclipse.epf.uma.MethodElementProperty mep = 
						(org.eclipse.epf.uma.MethodElementProperty) srcItem;
				String srcName = mep.getName();
				String srcValue = mep.getValue();
				if (srcName != null && srcValue != null
						&& srcName.length() > 0 && srcValue.length() > 0) {
					if (propertiesValue.length() > 0) {
						propertiesValue += WorkOrderPropStringSep;				//$NON-NLS-1$
					}
					propertiesValue += "name=" + srcName;		//$NON-NLS-1$
					propertiesValue += WorkOrderPropStringFieldSep + "value=" + srcValue;	//$NON-NLS-1$
					
					if (umaWorkOrder != null && srcName.equals(MethodElementPropertyHelper.WORK_ORDER__SUCCESSOR)) {
						if (scopeElement == null) {
							Object cont = umaWorkOrder.eContainer();
							if (cont instanceof org.eclipse.epf.uma.ProcessPackage) {
								List<org.eclipse.epf.uma.ProcessElement> peList = 
									((org.eclipse.epf.uma.ProcessPackage) cont).getProcessElements();
								if (peList != null) {
									for (org.eclipse.epf.uma.ProcessElement pe : peList) {
										if (pe instanceof org.eclipse.epf.uma.Activity) {
											scopeElement = pe;
											break;
										}
									}
								}
							}
						}
						if (scopeElement != null) {
							propertiesValue += WorkOrderPropStringFieldSep + "scope=" + scopeElement.getGuid();		//$NON-NLS-1$
						}
					}
					
				}
			}
		}
		if (propertiesValue.length() > 0) {
			xmlWorkOrder.setProperties(propertiesValue);
		}
	}

	/**
	 * set the id references for the object
	 * 
	 * @param obj
	 *            EDataObject the object
	 * @param featureName
	 *            String the feature of the object
	 * @param idValue
	 *            String the id reference value of the feature
	 * @param valueType
	 *            Type the object type of the reference. Need to have this to
	 *            determine the xml feature in case the feature mappting is not
	 *            unique
	 * @throws Exception
	 */
	public void setReferenceValue(IModelObject obj, String featureName,
			String idValue, Type valueType) throws Exception {
		if (obj == null || featureName == null || idValue == null) {
			return;
		}

		if (FeatureManager.INSTANCE.isUnneededRmcFeature(featureName)) {
			return;
		}

		// find the feature and set the value
		EStructuralFeature feature = FeatureManager.INSTANCE.getXmlFeature(obj
				.eClass(), featureName, valueType.getName());
		if (feature == null) {
			Object[] paras = new Object[] { featureName, obj, idValue };
			logger.logWarning(NLS.bind(
					ExportXMLResources.xmlLibrary_error_set_reference, paras));
			return;
		}

		String str = feature.getEType().getName();
		if (str.equals("String")) { //$NON-NLS-1$
			if (feature.isMany()) {
				List l = (List) obj.eGet(feature);
				if (!l.contains(idValue)) {
					l.add(idValue);
				}
			} else {
				obj.eSet(feature, idValue);
			}
		} else if (feature == UmaPackage.eINSTANCE
				.getActivity_BreakdownElement()) {
			// special handling for breakdown element. In uma, breakdown
			// elements are under process packages
			// in xml model, they are owned by the activity
			IModelObject v = getElement(idValue);
			if (v instanceof BreakdownElement) {
				EObject old_container = v.eContainer();
				List l = (List) obj.eGet(feature);
				if (!l.contains(v)) {
					l.add(v);
				}

				// if the old container package is empty, delete it
				if ((old_container instanceof ProcessPackage)
						&& (old_container.eContainer() instanceof ProcessComponent)) {
					if (((ProcessPackage) old_container).getProcessElement()
							.size() == 0) {
						((ProcessPackage) old_container.eContainer())
								.getMethodPackage().remove(old_container);
					}
				}
			}
		} else if ( feature == UmaPackage.eINSTANCE.getWorkBreakdownElement_Predecessor() ) {
			// the orker order needs to be added to the WorkBreakdownElement
			// since in Xml model the work order is contained by the WorkBreakdownElement
			// Jinhua Xi, 08/24/2006
			Object v = getElement(idValue);
			if (v instanceof WorkOrder) {
				((WorkBreakdownElement)obj).getPredecessor().add((WorkOrder)v);
			}
		} else {
			// not handled, add warning log
			// the id feature value is not set
			// JInhua Xi, TODO
		}
	}

	/**
	 * @param dt
	 * @return a Xml date.
	 */
	public Object getXmlDate(java.util.Date dt) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); //$NON-NLS-1$
		String dtStr = sdf.format(dt);
		dtStr = dtStr.replace(' ', 'T');
		EDataType type = XMLTypePackage.eINSTANCE.getDateTime();
		return XMLTypeFactory.eINSTANCE.createFromString(type, dtStr);

	}

	private String getString(Object obj) {
		String str = ""; //$NON-NLS-1$
		if (obj instanceof MethodElement) {
			MethodElement me = (MethodElement) obj;
			str = me.getType().getName() + ":" + me.getName(); //$NON-NLS-1$
		} else if (obj != null) {
			str = obj.toString();
		}

		return str;
	}

	/**
	 * fix the library so that it meets the uma library standards
	 * 
	 */
	public void fixLibraryForImport() {

		fixGuid((MethodElement) rootObject);

		if (rootObject instanceof MethodLibrary) {
			for (Iterator it = ((MethodLibrary) rootObject).getMethodPlugin()
					.iterator(); it.hasNext();) {
				fixPlugin((MethodPlugin) it.next());
			}
		} else if (rootObject instanceof MethodPlugin) {
			fixPlugin((MethodPlugin) rootObject);
		}
	}

	/**
	 * fix the library so that it meets the xml schema standards
	 * also remove possible structure errors such as ProcessComponent without process in it.
	 * 
	 */
	public void fixLibraryForExport() {

		List invalidItems = new ArrayList();
		for (Iterator it = ((MethodLibrary) rootObject).eAllContents(); it.hasNext();) {
			Object obj = it.next();
			if ( obj instanceof ProcessComponent ) {
				org.eclipse.epf.xml.uma.Process proc = ((ProcessComponent)obj).getProcess();
				if ( proc == null ) {
					invalidItems.add(obj);
				}
			}
		}

		// remove the objects
		while (invalidItems.size() > 0 ) {
			EObject obj = (EObject)invalidItems.remove(0);
			EcoreUtil.remove(obj);
		}
	}
	
	/**
	 * fix task descriptors for export
	 * 
	 */
	public void fixTaskDescriptorsForExport(Map<String, TaskDescriptor> tdMap) {
		if (tdMap == null || tdMap.isEmpty()) {
			return;
		}		
		for (Iterator<Map.Entry<String, TaskDescriptor>> it = tdMap.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, TaskDescriptor> entry = it.next();
			String guid = entry.getKey();
			TaskDescriptor td = entry.getValue();
			org.eclipse.epf.xml.uma.TaskDescriptor xmlTd = (org.eclipse.epf.xml.uma.TaskDescriptor) elementsMap.get(guid);
			assert(xmlTd != null);
			if (xmlTd == null) {
				continue;
			}
			List steps = td.getSelectedSteps();
			for (int i=0; i<steps.size(); i++) {
				org.eclipse.epf.uma.MethodElement step = (org.eclipse.epf.uma.MethodElement) steps.get(i);
				Section xmlStep =  (Section) elementsMap.get(step.getGuid());
				if (xmlStep == null) {	//Bug 300749: this can happen if td's plugin is not in the exported list 
					xmlStep = buildSection(step);
				}
				if (xmlStep != null) {
					xmlStep = (Section) EcoreUtil.copy(xmlStep);
					xmlTd.getStep().add(xmlStep);
				}
			}			
		}		
	}
			
	/**
	 * get a list of referenced plugins for the library. The referenced plugins
	 * does not include the ones inside this library. When importing, these
	 * plugins must be in the target library already.
	 * 
	 * @return List a list of referenced plugins
	 */
	public List getReferencedPlugins() {

		List externalPlugins = new ArrayList();

		if (!(rootObject instanceof MethodLibrary)) {
			return externalPlugins;
		}

		List selectedPlugins = new ArrayList();
		for (Iterator it = ((MethodLibrary) rootObject).getMethodPlugin()
				.iterator(); it.hasNext();) {
			MethodPlugin plugin = (MethodPlugin) it.next();
			String id = plugin.getId();
			selectedPlugins.add(id);
			if (externalPlugins.contains(id)) {
				externalPlugins.remove(id);
			}

			for (Iterator itr = plugin.getReferencedMethodPlugin().iterator(); itr
					.hasNext();) {
				String ref = (String) itr.next();
				if (!selectedPlugins.contains(ref)
						&& !externalPlugins.contains(ref)) {
					externalPlugins.add(ref);
				}
			}
		}

		return externalPlugins;
	}

	private void fixGuid(MethodElement element) {
		if (element == null) {
			return;
		}

		// if the xml element does not have an id specified, create a new one
		String id = element.getId();
		if (id == null || id.equals("")) { //$NON-NLS-1$
			id = EcoreUtil.generateUUID();
			element.setId(id);
			String msg = NLS.bind(ExportXMLResources.xmlLibrary_new_id,
					getString(element), id);
			logger.logWarning(msg);
		} else {
			// check if the element has a unique id or not
			Object old = getElement(id);
			if ((old != null) && (old != element)) {
				if (! ( (old instanceof Section) && 
						(element instanceof Section) 
					   )) {
					logger.logWarning(NLS.bind(
						ExportXMLResources.xmlLibrary_id_not_unique, id));
				}
			}

			setElement(id, element);
		}
		// iterate the children
		for (Iterator it = element.eContents().iterator(); it.hasNext();) {
			Object o = it.next();
			if (o instanceof MethodElement) {
				fixGuid((MethodElement) o);
			}
		}
	}

	private void fixPlugin(MethodPlugin plugin) {

		// find all processes and make sure they are wrapped with a
		// ProcessComponent,
		// if not, create one.
		// collect the processes to be fixed,
		// don't fix within the iteration, may cause concurrent modification
		// exeception
		List procs = new ArrayList();
		for (Iterator it = plugin.eAllContents(); it.hasNext();) {
			EObject o = (EObject) it.next();
			if (isProcess(o)) {
				procs.add(o);
			}
		}

		if (procs.size() > 0) {
			for (Iterator it = procs.iterator(); it.hasNext();) {
				fixProcess((org.eclipse.epf.xml.uma.Process) it.next());
			}
		}
	}

	/**
	 * In EPF, a process (Capability pattern and Delivery Process) can be reside
	 * in a ProcessPackage or a ProcessComponent. If it's in a processComponent,
	 * it is treated as a root level process. If it's in a ProcessPackage, it is
	 * treated as a local copy of another process. So we need to check the
	 * parent and see it this process is contained by a ProcessComponent.
	 * 
	 * @param Obj
	 * @return boolean
	 */
	private boolean isProcess(EObject obj) {
		if (!(obj instanceof org.eclipse.epf.xml.uma.Process)) {
			return false;
		}

		obj = obj.eContainer();
		if (obj instanceof ProcessComponent) {
			return true;
		}

		// if it's immediate parent is not a ProcessComponent but it's within a
		// ProcessComponent
		// it's a local copy of the process
		while (((obj = obj.eContainer()) != null)
				&& (obj instanceof ProcessPackage)) {
			if (obj instanceof ProcessComponent) {
				return false;
			}
		}

		return true;
	}

	private void fixProcess(org.eclipse.epf.xml.uma.Process proc) {
		IModelObject container = (IModelObject) proc.eContainer();
		ProcessComponent pc = null;
		if (container instanceof ProcessComponent) {
			return;
		}

		if (!(container instanceof ProcessPackage)) {
			String msg = NLS
					.bind(
							ExportXMLResources.xmlLibrary_error_process_wrong_container,
							proc.getName(), container.getType().getName());
			logger.logWarning(msg);
			return;
		}

		pc = UmaFactory.eINSTANCE.createProcessComponent();
		String id = getTargetParentId(proc.getId());
		if (id == null) {
			id = EcoreUtil.generateUUID();
		}

		pc.setId(id);
		pc.setName(proc.getName());
		((ProcessPackage) container).getProcessElement().remove(proc);
		((ProcessPackage) container).getMethodPackage().add(pc);
		pc.setProcess(proc);

	}

	/**
	 * if the process exists in the target library, get the process component id
	 * from the target library
	 */
	private String getTargetParentId(String id) {
		ILibraryManager manager = LibraryService.getInstance()
				.getCurrentLibraryManager();
		if (manager != null) {
			EObject obj = manager.getMethodElement(id);
			if (obj != null) {
				obj = obj.eContainer();
				if (obj instanceof org.eclipse.epf.uma.MethodElement) {
					return ((org.eclipse.epf.uma.MethodElement) obj).getGuid();
				}
			}
		}

		return null;
	}

	/**
	 * @param pkg
	 * @return the list of all processes in pkg.
	 */
	public List getAllProcesses(MethodPackage pkg) {
		List processes = new ArrayList();
		_iteratePackageForProcesses(pkg, processes);

		return processes;
	}

	private void _iteratePackageForProcesses(MethodPackage pkg, List processes) {
		if (pkg instanceof ProcessComponent) {
			org.eclipse.epf.xml.uma.Process p = ((ProcessComponent) pkg)
					.getProcess();
			if (p != null && !processes.contains(p)) {
				processes.add(p);
			}
		}

		for (Iterator it = pkg.getMethodPackage().iterator(); it.hasNext();) {
			_iteratePackageForProcesses((MethodPackage) it.next(), processes);
		}
	}

	
	/**
	 * Save extra info (all plugin guids and names) in libTag's MethodElementProperty
	 */
	protected void storeExtraInfo(List<org.eclipse.epf.uma.MethodPlugin> plugins) {
		if (plugins == null) {
			return;
		}		
		
		EStructuralFeature feature = FeatureManager.INSTANCE.getXmlFeature(rootObject
				.eClass(), "methodElementProperty");		//$NON-NLS-1$
		
		List mepList = (List) rootObject.eGet(feature);
		EClass mepClass = FeatureManager.INSTANCE.getXmlEClass("MethodElementProperty");	//$NON-NLS-1$
		
		MethodElementProperty xmlMep = (MethodElementProperty) EcoreUtil.create(mepClass);
		
		//Version
		xmlMep.setValue("0");	//$NON-NLS-1$
		mepList.add(xmlMep);
					
		for (Iterator<org.eclipse.epf.uma.MethodPlugin> it = plugins.iterator(); it.hasNext();) {
			org.eclipse.epf.uma.MethodPlugin plug = it.next();
			
			xmlMep = (MethodElementProperty) EcoreUtil.create(mepClass);
			xmlMep.setValue(plug.getGuid());
			mepList.add(xmlMep);
			
			xmlMep = (MethodElementProperty) EcoreUtil.create(mepClass);
			xmlMep.setValue(plug.getName());
			mepList.add(xmlMep);
		}
		
	}
	
	/**
	 * Get guid to plugin map
	 */
	public Map<String, String> getGuidToPlugNameMap() {
		if (guidToPlugNameMap == null) {
			recallExtraInfo();
		}		
		return guidToPlugNameMap;
	}
	
	private void recallExtraInfo() {
		try {
			EStructuralFeature feature = FeatureManager.INSTANCE.getXmlFeature(rootObject
					.eClass(), "methodElementProperty");		//$NON-NLS-1$
			
			List<MethodElementProperty> mepList = (List<MethodElementProperty>) rootObject.eGet(feature);
			if (mepList == null || mepList.isEmpty()) {
				return;
			}
			guidToPlugNameMap = new HashMap<String, String>();
			
			MethodElementProperty mep = mepList.get(0);
			if (mep.getValue().equals("0")) { //$NON-NLS-1$  
				for (int i = 1; i < mepList.size();) {
					String guid = mepList.get(i).getValue();
					String name = mepList.get(i+1).getValue();
					guidToPlugNameMap.put(guid, name);
					i += 2;
				}
			}
		} catch (Exception e) {
			guidToPlugNameMap = new HashMap<String, String>();
			e.printStackTrace();
		}
	}

	public Map<WorkOrder, org.eclipse.epf.uma.WorkOrder> getSuccessOrWorkOrderMap() {
		if (successOrWorkOrderMap == null) {
			successOrWorkOrderMap = new HashMap<WorkOrder, org.eclipse.epf.uma.WorkOrder>();
		}
		return successOrWorkOrderMap;
	}
	
	private org.eclipse.epf.xml.uma.Section buildSection(
			org.eclipse.epf.uma.MethodElement umaStep) {
		if (! (umaStep instanceof org.eclipse.epf.uma.Section)) {
			return null;
		}

		org.eclipse.epf.xml.uma.Section xmlSection = org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE
				.createSection();
		
		List<EStructuralFeature> features = umaStep.eClass()
				.getEAllStructuralFeatures();
		
		for (EStructuralFeature feature : features) {
			Object value = umaStep.eGet(feature);
			try {
				if (value instanceof String) {	//Ignore other type values
					setAtributeFeatureValue(xmlSection, feature.getName(), value);
				}
			} catch (Exception e) {
				String msg = NLS.bind(
						ExportXMLResources.exportXMLService_feature_error,
						LibraryUtil.getTypeName(umaStep), feature.getName());
				logger.logError(msg, e);
			}
		}

		return xmlSection;
	}
	
	public boolean isSynFreeLib() {
		if (synFreeLibIx == -1) {
			synFreeLibIx = 0;
			
			EStructuralFeature feature = FeatureManager.INSTANCE.getXmlFeature(rootObject
					.eClass(), "methodElementProperty");		//$NON-NLS-1$
			
			List<MethodElementProperty> mepList = (List<MethodElementProperty>) rootObject.eGet(feature);
			if (mepList == null || mepList.isEmpty()) {
				return false;
			}
			
			for (MethodElementProperty mep : mepList) {
				String name = mep.getName();
				if (name != null && name.equals(MethodLibraryPropUtil.Library_SynFree)) {
					String value = mep.getValue();
					synFreeLibIx = Boolean.parseBoolean(value) ? 1 : 0;
					break;
				}
			}
		}
		return synFreeLibIx > 0;
	}
	
}
