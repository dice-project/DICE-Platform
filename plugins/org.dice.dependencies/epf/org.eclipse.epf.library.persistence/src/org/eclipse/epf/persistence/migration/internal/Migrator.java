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
package org.eclipse.epf.persistence.migration.internal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.IllegalValueException;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.emf.mapping.ecore2xml.Ecore2XMLPackage;
import org.eclipse.emf.mapping.ecore2xml.Ecore2XMLRegistry;
import org.eclipse.emf.mapping.ecore2xml.impl.Ecore2XMLRegistryImpl;
import org.eclipse.emf.mapping.ecore2xml.util.Ecore2XMLExtendedMetaData;
import org.eclipse.epf.persistence.MultiFileResourceSetImpl;
import org.eclipse.epf.persistence.MultiFileURIConverter;
import org.eclipse.epf.persistence.MultiFileXMIHelperImpl;
import org.eclipse.epf.persistence.MultiFileXMIResourceImpl;
import org.eclipse.epf.persistence.migration.UMA2UMAResourceHandler;
import org.eclipse.epf.persistence.util.PersistenceResources;
import org.eclipse.epf.persistence.util.PersistenceUtil;
import org.eclipse.epf.resourcemanager.ResourceDescriptor;
import org.eclipse.epf.resourcemanager.ResourceManager;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.Guideline;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.ContentDescriptionFactory;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * Migrates one version of UMA library to another using the provided Ecore2XML
 * mapping files and UMA2UMAResourceHandler. The Ecore2XML mapping files
 * specifies the name changes of classes/attributes while UMA2UMAResourceHandler
 * handles the data moving.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class Migrator {
	private static final String[] NS_URIs = { Migrator.OLD_UMA_NS_URI,
			Migrator.OLD_UMA_NS_URI, Migrator.UMA_NS_URI };

	private static final URI[] ECORE2XML_URIs = {
			URI
					.createURI("platform:/plugin/com.ibm.rpm.library.persistence/migration/uma.ecore2xml") //$NON-NLS-1$
			,
			URI
					.createURI("platform:/plugin/com.ibm.rpm.library.persistence/migration/uma2.ecore2xml") //$NON-NLS-1$
			,
			URI
					.createURI("platform:/plugin/com.ibm.rpm.library.persistence/migration/uma3.ecore2xml") //$NON-NLS-1$
	};

	public static final String OLD_UMA_NS_URI = "http:///com/ibm/uma.ecore"; //$NON-NLS-1$

	public static final String UMA_NS_URI = "http://www.ibm.com/uma/1.0.2/uma.ecore"; //$NON-NLS-1$

	private ExtendedMetaData[] extendedMetaDatas;

	private UMA2UMAResourceHandler0 resourceHandler;

	public HashMap oldPathToNewURIMap = new HashMap();

	public Collection anyTypeFeatureValues = new ArrayList();

	private static final ExtendedMetaData getExtendedMetaData(String oldNsURI,
			URI ecore2XMLURI) {
		EPackage.Registry ePackageRegistry = new EPackageRegistryImpl(
				EPackage.Registry.INSTANCE);
		ePackageRegistry.put(oldNsURI, UmaPackage.eINSTANCE);

		Ecore2XMLRegistry ecore2xmlRegistry = new Ecore2XMLRegistryImpl(
				Ecore2XMLRegistry.INSTANCE);

		ecore2xmlRegistry
				.put(oldNsURI, EcoreUtil.getObjectByType(new ResourceSetImpl()
						.getResource(ecore2XMLURI, true).getContents(),
						Ecore2XMLPackage.eINSTANCE.getXMLMap()));

		return new Ecore2XMLExtendedMetaData(ePackageRegistry,
				ecore2xmlRegistry) {
			public EClassifier getType(EPackage ePackage, String name) {
				EClassifier type = super.getType(ePackage, name);

				if (type == null) {
					// try to get type from the package with the given name
					//
					List eClassifiers = ePackage.getEClassifiers();
					for (int i = 0, size = eClassifiers.size(); i < size; ++i) {
						EClassifier eClassifier = (EClassifier) eClassifiers
								.get(i);
						if (name.equals(eClassifier.getName())) {
							return eClassifier;
						}
					}

				}

				return type;
			}
		};
	}

	public Migrator() {
		this(NS_URIs, ECORE2XML_URIs);
	}

	public Migrator(String[] nsURIs, URI[] ecore2XMLURIs) {
		int size = ecore2XMLURIs.length;
		extendedMetaDatas = new ExtendedMetaData[size];
		for (int i = 0; i < ecore2XMLURIs.length; i++) {
			extendedMetaDatas[i] = getExtendedMetaData(nsURIs[i],
					ecore2XMLURIs[i]);
		}
		this.resourceHandler = new UMA2UMAResourceHandler0();
	}

	private static void updateStatus(IProgressMonitor monitor, String msg) {
		if (monitor != null) {
			monitor.setTaskName(msg);
		} else {
			System.out.println(msg);
		}
	}

	private static class AnyTypeFeatureValue {
		String ownerId;

		EObject owner;

		EStructuralFeature feature;

		String valueId;

		AnyType value;

		int index;
	}

	class MigratorURIConverter extends MultiFileURIConverter {
		private ResourceManager oldResMgr;

		/**
		 * @param resourceSet
		 */
		public MigratorURIConverter(MultiFileResourceSetImpl resourceSet) {
			super(resourceSet);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.ibm.uma.persistence.MultiFileURIConverter#normalize(org.eclipse.emf.common.util.URI)
		 */
		public URI normalize(URI uri) {
			URI normalized = super.normalize(uri);
			if (normalized == null && SCHEME.equalsIgnoreCase(uri.scheme())) {
				if (oldResMgr != null) {
					String id = uri.authority();
					normalized = getURIFromOldResourceManager(id);
					if (normalized != null) {
						if (uri.hasFragment()) {
							normalized = normalized.appendFragment(uri
									.fragment());
						} else {
							normalized = normalized.appendFragment(id);
						}
					}
				}

			}
			return normalized;
		}

		public URI getURIFromOldResourceManager(String id) {
			if (oldResMgr == null)
				return null;
			ResourceDescriptor desc = oldResMgr.getResourceDescriptor(id);
			if (desc != null) {
				URI normalized = desc.getResolvedURI();
				URI newURI = (URI) oldPathToNewURIMap.get(normalized
						.toFileString());
				if (newURI != null) {
					normalized = newURI;
				}
				return normalized;
			}
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.ibm.uma.persistence.MultiFileURIConverter#dispose()
		 */
		public void dispose() {
			if (oldResMgr != null) {
				oldResMgr.dispose();
				oldResMgr = null;
			}

			super.dispose();
		}

		/**
		 * @param resMgr2
		 */
		public void setOldResourceManager(ResourceManager resMgr) {
			oldResMgr = resMgr;
		}

		public ResourceManager getOldResourceManager() {
			return oldResMgr;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.ibm.uma.persistence.MultiFileURIConverter#setURIMapping(org.eclipse.emf.ecore.EObject,
		 *      org.eclipse.emf.common.util.URI, java.util.Set)
		 */
		public void setURIMapping(EObject e, URI uri, Set modifiedResources) {
			// code to support migration from older version
			//
			if (e instanceof MethodElement) {
				MigratorURIConverter uriConverter = (MigratorURIConverter) ((MultiFileResourceSetImpl) e
						.eResource().getResourceSet()).getURIConverter();
				ResourceManager oldResMgr = uriConverter
						.getOldResourceManager();
				if (oldResMgr != null) {
					ResourceDescriptor desc = oldResMgr
							.getResourceDescriptor(((MethodElement) e)
									.getGuid());
					if (desc != null) {
						oldPathToNewURIMap.put(desc.getResolvedURI()
								.toFileString(), uri);
					}
				}
			}

			super.setURIMapping(e, uri, modifiedResources);
		}
	}

	class MigratorResourceSet extends MultiFileResourceSetImpl {
		/*
		 * (non-Javadoc)
		 * 
		 * @see com.ibm.uma.persistence.MultiFileResourceSetImpl#getURIConverter()
		 */
		public URIConverter getURIConverter() {
			if (uriConverter == null) {
				uriConverter = new MigratorURIConverter(this);
			}
			return uriConverter;
		}

		private void loadOldResourceManager(String libPath) throws IOException {
			// trying to load the resource descriptors file
			//
			String libDir = new File(libPath).getParent() + File.separator;
			File file = new File(libDir, RESMGR_XMI);
			ResourceManager resMgr = null;
			MultiFileURIConverter multiFileURIConverter = (MultiFileURIConverter) getURIConverter();
			if (file.exists()) {
				Resource resMgrRes = new XMIResourceImpl(URI.createFileURI(file
						.getAbsolutePath())); // super.getResource(URI.createFileURI(file.getAbsolutePath()),
				// true);
				resMgrRes.load(null);
				if (!resMgrRes.getContents().isEmpty()) {
					Object obj = resMgrRes.getContents().get(0);
					if (obj instanceof ResourceManager) {
						resMgr = (ResourceManager) obj;
						// resMgr.resolve();
					} else {
						System.err
								.println("Invalid ResourceManager file: " + file); //$NON-NLS-1$
					}
				}
			}
			((MigratorURIConverter) multiFileURIConverter)
					.setOldResourceManager(resMgr);
		}

		public MethodLibrary loadLibrary(String path) throws Exception {
			loadOldResourceManager(path);

			return loadLibraryWithoutReset(path);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.ibm.uma.persistence.MultiFileResourceSetImpl#findEObjectInUnloadedResources(java.lang.String)
		 */
		protected EObject findEObjectInUnloadedResources(String id) {
			Object object = null;
			if (getResourceManager() != null) {
				object = super.findEObjectInUnloadedResources(id, false);
			}
			if (object == null) {
				URI uri = ((MigratorURIConverter) getURIConverter())
						.getURIFromOldResourceManager(id);
				Resource resource = super.getResource(uri, true);
				return resource.getEObject(id);
			}
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.ibm.uma.persistence.MultiFileResourceSetImpl#getEObject(org.eclipse.emf.common.util.URI,
		 *      boolean)
		 */
		public EObject getEObject(URI uri, boolean loadOnDemand) {
			// EObject eObj = superGetEObject(uri, loadOnDemand);
			EObject eObj = super.getEObject(uri, loadOnDemand);
			if (eObj == null
					&& MigratorURIConverter.SCHEME.equalsIgnoreCase(uri
							.scheme()) && !uri.hasFragment()) {
				eObj = super.getEObject(uri.authority());
			}
			// if(uri.authority().equals("{11B60A4B-A0C7-4D5C-891A-346E8652A4EB}"))
			// {
			// System.out.println("MigratorResourceSet.getEObject(): eObj = " +
			// eObj);
			// }
			if (eObj == null || eObj.eIsProxy()) {
				throw new RuntimeException(
						"Could not load object with URI '" + uri + "' (normalized URI: '" + getURIConverter().normalize(uri) + "').\nPlease see log file for more details."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}
			return eObj;
		}

		public EObject superGetEObject(URI uri, boolean loadOnDemand) {
			try {
				URI normalized = getURIConverter().normalize(uri);
				if (normalized == null) {
					return null;
				}
				Resource resource = null;
				resource = getResource(normalized.trimFragment(), loadOnDemand);
				if (resource != null) {
					String fragment = normalized.fragment();
					if (fragment == null || fragment.length() == 0) {
						return PersistenceUtil.getMethodElement(resource);
					}
					try {
						return resource.getEObject(fragment);
					} catch (NullPointerException e) {
						throw e;
					}
				} else {
					return null;
				}
			} catch (RuntimeException e) {
				//
			}
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.ibm.uma.persistence.MultiFileResourceSetImpl#createResource(org.eclipse.emf.common.util.URI)
		 */
		public Resource createResource(URI uri) {
			Resource result = new MultiFileXMIResourceImpl(uri) {
				protected XMLHelper createXMLHelper() {
					return new MultiFileXMIHelperImpl(this) {
						public void setValue(EObject object,
								EStructuralFeature feature, Object value,
								int position) {
							boolean old = logError;
							try {
								logError = false;
								super
										.setValue(object, feature, value,
												position);
							} catch (RuntimeException e) {
								if (value instanceof AnyType
										&& object instanceof MethodElement
								// && object instanceof WorkBreakdownElement
								// && feature ==
								// UmaPackage.eINSTANCE.getWorkBreakdownElement_LinkToPredecessor()
								) {
									AnyTypeFeatureValue fvalue = new AnyTypeFeatureValue();
									// fvalue.owner = object;
									fvalue.ownerId = ((MethodElement) object)
											.getGuid();
									fvalue.feature = feature;
									// fvalue.value = (AnyType) value;
									fvalue.valueId = (String) UMA2UMAResourceHandler
											.getSingleValue(((AnyType) value)
													.getAnyAttribute(), "guid"); //$NON-NLS-1$
									fvalue.index = position;
									anyTypeFeatureValues.add(fvalue);
								} else {
									throw e;
								}
							} finally {
								logError = old;
							}
						}

						public List setManyReference(
								org.eclipse.emf.ecore.xmi.XMLHelper.ManyReference reference,
								String location) {
							List exceptions = super.setManyReference(reference,
									location);
							if (exceptions == null)
								return null;
							for (Iterator iter = exceptions.iterator(); iter
									.hasNext();) {
								Object ex = iter.next();
								if (ex instanceof IllegalValueException) {
									IllegalValueException ive = (IllegalValueException) ex;
									if (ive.getValue() instanceof AnyType
											&& ive.getObject() instanceof MethodElement) {
										AnyTypeFeatureValue fvalue = new AnyTypeFeatureValue();
										fvalue.ownerId = ((MethodElement) ive
												.getObject()).getGuid();
										fvalue.feature = ive.getFeature();
										fvalue.valueId = (String) UMA2UMAResourceHandler
												.getSingleValue(((AnyType) ive
														.getValue())
														.getAnyAttribute(),
														"guid"); //$NON-NLS-1$
										fvalue.index = -1;
										anyTypeFeatureValues.add(fvalue);
										iter.remove();
									}
								}
							}
							if (exceptions.isEmpty()) {
								return null;
							}
							return exceptions;
						}
					};
				}
			};
			result.setTrackingModification(true);
			getResources().add(result);
			return result;
		}
	}

	public void migrate(String libPath, IProgressMonitor monitor)
			throws Exception {
		Set classNames = new HashSet();
		classNames.add("Technique"); //$NON-NLS-1$

		MultiFileResourceSetImpl resourceSet = new MigratorResourceSet();
		// ResourceManager oldResMgr = null;
		for (int i = 0; i < extendedMetaDatas.length; i++) {
			resourceSet.getLoadOptions().put(
					XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
			if (i < 2) {
				resourceSet.getLoadOptions().put(
						XMLResource.OPTION_RESOURCE_HANDLER, resourceHandler);
			}
			resourceSet.getLoadOptions()
					.put(XMLResource.OPTION_EXTENDED_META_DATA,
							extendedMetaDatas[i]);

			if (i == 0) {
				resourceHandler.savePresentationURIFor(classNames);
			} else {
				resourceHandler.savePresentationURIFor(null);
			}

			updateStatus(monitor, PersistenceResources.loadLibraryTask_name);

			// if(oldResMgr != null) {
			// ((MigratorURIConverter)resourceSet.getURIConverter()).setOldResourceManager(oldResMgr);
			// resourceSet.loadLibraryWithoutReset(libPath);
			// }
			// else {
			resourceSet.loadLibrary(libPath);
			// }

			MethodLibrary lib = resourceSet.getMethodLibrary();
			if (lib == null)
				return;

			// load all the resources in memory
			//
			updateStatus(monitor, PersistenceResources.loadResourcesTask_name);

			for (Iterator iter = lib.eAllContents(); iter.hasNext();) {
				EObject element = (EObject) iter.next();
				if (element instanceof Guideline && i > 0) {
					// hack to restore the right presentation for old Technique
					//
					Guideline guideline = (Guideline) element;
					URI uri = resourceHandler.getPresentationURI(guideline
							.getGuid());
					if (uri != null) {
						ContentDescription presentation = (ContentDescription) resourceSet
								.getEObject(uri, true);
						guideline.setPresentation(presentation);
					}
				}
				if (element instanceof MethodElement) {
					try {
						for (Iterator iterator = element.eCrossReferences()
								.iterator(); iterator.hasNext();) {
							iterator.next();
						}
					} catch (Exception e) {
						CommonPlugin.INSTANCE.log(e);
						System.err
								.println("Error iterate thru cross references of element: " + element); //$NON-NLS-1$
					}
				}
			}

			if (i == 0) {
				// oldResMgr =
				// ((MigratorURIConverter)resourceSet.getURIConverter()).getOldResourceManager();

				migrateProcessContentDescriptions(monitor, lib);

				updateStatus(monitor, PersistenceResources.moveDataTask_name);

			}

			if (i < 2) {
				resourceHandler.moveData();
				resourceHandler.clearMoveInfos();
			}

			if (i == extendedMetaDatas.length - 1) {
				migrateProcesses(monitor, lib);

				restoreReferences(resourceSet);

			}

			updateStatus(monitor, PersistenceResources.saveLibraryTask_name);

			resourceSet.save(null, true);
			resourceSet.reset();

		}

		// delete old resmgr.xmi
		//
		try {
			File resMgrFile = new File(new File(libPath).getParentFile(),
					MultiFileResourceSetImpl.RESMGR_XMI);
			if (resMgrFile.exists()) {
				resMgrFile.delete();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void restoreReferences(MultiFileResourceSetImpl resourceSet) {
		for (Iterator iter = anyTypeFeatureValues.iterator(); iter.hasNext();) {
			AnyTypeFeatureValue element = (AnyTypeFeatureValue) iter.next();
			if (element.ownerId != null && element.valueId != null) {
				EObject owner = resourceSet.getEObject(element.ownerId);
				Object value = resourceSet.getEObject(element.valueId);
				UMA2UMAResourceHandler.setValue(owner, element.feature, value);
			}
		}
		anyTypeFeatureValues.clear();
	}

	private static void migrateProcesses(IProgressMonitor monitor,
			MethodLibrary lib) {
		updateStatus(monitor, PersistenceResources.fixPresentationNameTask_name);

		String[][] procPkgPaths = MultiFileResourceSetImpl.PROCESS_PACKAGE_PATHS;
		for (Iterator iter = lib.getMethodPlugins().iterator(); iter.hasNext();) {
			MethodPlugin plugin = (MethodPlugin) iter.next();
			ArrayList procPkgs = new ArrayList();
			for (int j = 0; j < procPkgPaths.length; j++) {
				MethodPackage pkg = UmaUtil.findMethodPackage(plugin,
						procPkgPaths[j]);
				if (pkg != null) {
					procPkgs.add(pkg);
				}
			}
			for (Iterator iterator = procPkgs.iterator(); iterator.hasNext();) {
				ProcessPackage pkg = (ProcessPackage) iterator.next();
				for (Iterator iterator1 = pkg.getChildPackages().iterator(); iterator1
						.hasNext();) {
					EObject childPkg = (EObject) iterator1.next();
					if (childPkg instanceof ProcessComponent) {
						org.eclipse.epf.uma.Process proc = ((ProcessComponent) childPkg)
								.getProcess();
						if (proc != null) {
							try {
								fixPresentationName(proc);
							} catch (RuntimeException e) {
								throw e;
							}
						} else {
							String msg = Migrator.class.getName()
									+ ": invalid ProcessComponent (with no Process): " + childPkg //$NON-NLS-1$
									+ "\n  ProcessComponent's resource URI: " + (childPkg.eResource() != null ? childPkg.eResource().getURI() : null) //$NON-NLS-1$
									+ "\n  Parent package: " + pkg //$NON-NLS-1$
									+ "\n  Parent package's resource URI: " + (pkg.eResource() != null ? pkg.eResource().getURI() : null) //$NON-NLS-1$
							;
							CommonPlugin.INSTANCE.log(msg);
							System.err.println(msg);
						}
					}
				}
			}
		}
	}

	private static void migrateProcessContentDescriptions(
			IProgressMonitor monitor, MethodLibrary lib) {
		updateStatus(monitor,
				PersistenceResources.migrateContentDescriptionsTask_name);

		String[][] procPkgPaths = MultiFileResourceSetImpl.PROCESS_PACKAGE_PATHS;
		for (Iterator iter = lib.getMethodPlugins().iterator(); iter.hasNext();) {
			MethodPlugin plugin = (MethodPlugin) iter.next();
			ArrayList procPkgs = new ArrayList();
			for (int j = 0; j < procPkgPaths.length; j++) {
				MethodPackage pkg = UmaUtil.findMethodPackage(plugin,
						procPkgPaths[j]);
				if (pkg != null) {
					procPkgs.add(pkg);
				}
			}
			for (Iterator iterator = procPkgs.iterator(); iterator.hasNext();) {
				ProcessPackage pkg = (ProcessPackage) iterator.next();
				for (Iterator iterator1 = pkg.getChildPackages().iterator(); iterator1
						.hasNext();) {
					Object childPkg = (Object) iterator1.next();
					if (childPkg instanceof ProcessComponent) {
						org.eclipse.epf.uma.Process proc = ((ProcessComponent) childPkg)
								.getProcess();
						migrateContentDescription(proc);
					}
				}
			}
		}
	}

	private static void migrateContentDescription(BreakdownElement e) {
		if (ContentDescriptionFactory.hasPresentation(e)) {
			ContentDescription content = e.getPresentation();
			EClass eCls = ContentDescriptionFactory
					.getContentDescriptionEClass(e);
			if (eCls != content) {
				ContentDescription newContent = ContentDescriptionFactory
						.createContentDescription(e);
				newContent.setMainDescription(content.getMainDescription());
				newContent.setKeyConsiderations(content.getKeyConsiderations());
				newContent.getSections().addAll(content.getSections());
				e.setPresentation(newContent);
			}
		}
		if (e instanceof Activity) {
			for (Iterator iter = ((Activity) e).getBreakdownElements()
					.iterator(); iter.hasNext();) {
				migrateContentDescription((BreakdownElement) iter.next());
			}
		}
	}

	private static void fixPresentationName(BreakdownElement e) {
		if (e.getPresentationName() == null
				|| e.getPresentationName().trim().length() == 0) {
			e.setPresentationName(e.getName());
		}
		if (e instanceof Activity) {
			for (Iterator iter = ((Activity) e).getBreakdownElements()
					.iterator(); iter.hasNext();) {
				fixPresentationName((BreakdownElement) iter.next());
			}
		}
	}

}
