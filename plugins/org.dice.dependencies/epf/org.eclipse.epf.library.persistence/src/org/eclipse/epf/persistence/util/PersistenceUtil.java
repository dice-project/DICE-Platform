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
package org.eclipse.epf.persistence.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epf.common.service.versioning.EPFVersions;
import org.eclipse.epf.common.service.versioning.VersionUtil;
import org.eclipse.epf.library.persistence.ILibraryResource;
import org.eclipse.epf.library.persistence.ILibraryResourceSet;
import org.eclipse.epf.persistence.FileManager;
import org.eclipse.epf.persistence.MultiFileResourceSetImpl;
import org.eclipse.epf.persistence.MultiFileSaveUtil;
import org.eclipse.epf.persistence.PersistencePlugin;
import org.eclipse.epf.persistence.migration.internal.Migrator;
import org.eclipse.epf.persistence.refresh.internal.RefreshEvent;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.UmaPackage;
import org.osgi.framework.Version;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Utility class with static helper methods for library persistence
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class PersistenceUtil {

	/**
	 * Gets the current namespace URI of UMA.
	 */
	public static final String getUMANsURI() {
		return UmaPackage.eNS_URI;
	}

	public static final String getUMANsURI(String libPath) {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = builder.parse(new File(libPath));
			Element root = doc.getDocumentElement();
			String nsURI = root.getAttribute("xmlns:com.ibm.uma"); //$NON-NLS-1$
			if (nsURI == null || nsURI.equals("")) { //$NON-NLS-1$
				nsURI = root.getAttribute("xmlns:org.eclipse.epf.uma"); //$NON-NLS-1$
			}
			if (nsURI == null || nsURI.equals("")) { //$NON-NLS-1$
				nsURI = root.getAttribute("xmlns:uma") + "/uma.ecore"; //$NON-NLS-1$ //$NON-NLS-2$
			}
			return nsURI;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static final boolean conversionRequired(String libPath) {
		String currentNsURI = PersistenceUtil.getUMANsURI();
		String libNsURI = PersistenceUtil.getUMANsURI(libPath);
		if (currentNsURI.equals(libNsURI))
			return false;
		if (Migrator.OLD_UMA_NS_URI.equals(libNsURI))
			return true;
		return false;
	}

	public static final IResource getWorkspaceResource(Object obj) {
		Resource resource = null;
		if (obj instanceof DescribableElement) {
			DescribableElement e = (DescribableElement) obj;
			ContentDescription content = e.getPresentation();
			resource = content.eResource();
			if (resource == null) {
				resource = e.eResource();
			}
		} else if (obj instanceof EObject) {
			resource = ((EObject) obj).eResource();
		} else if (obj instanceof Resource) {
			resource = (Resource) obj;
		}
		if (resource != null && resource.getURI().isFile()) {
			return FileManager.getResourceForLocation(resource.getURI()
					.toFileString());
		}
		return null;
	}

	public static final Collection<EObject> getProxies(EObject obj) {
		Collection<EObject> proxies = new ArrayList<EObject>();
		for (Iterator iter = obj.eAllContents(); iter.hasNext();) {
			EObject o = (EObject) iter.next();
			if (o.eIsProxy()) {
				proxies.add(o);
			}
		}
		return proxies;
	}

	/**
	 * Checks the primary tool version stored in the given resource
	 * 
	 * @param resource
	 * @return <li> < 0 if the version of the primary tool stored in the given resource is missing or older than current
	 *         <li> 0 if the primary tool version in the given resource matches the current version
	 *         <li> > 0 if  the primary tool version in the given resource is newer than the current version
	 */
	public static final int checkToolVersion(Resource resource) {
		File file = new File(resource.getURI().toFileString());
		Map fileVersionMap = VersionUtil.readVersionsFromFile(file);
		if(fileVersionMap != null) {
			String version = (String) fileVersionMap.get(VersionUtil.getPrimaryToolID());
			if(version != null) {
				EPFVersions versions = VersionUtil.getVersions(VersionUtil.getPrimaryToolID());
				return versions.getCurrentVersion().compareToolVersionTo(new Version(version));
			}
		}
		return -1;
	}

	/**
	 * Gets the first method element in contents of the given resource.
	 *  
	 * @param resource
	 * @return
	 */
	public static MethodElement getMethodElement(Resource resource) {
		for (Iterator iter = resource.getContents().iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (element instanceof MethodElement) {
				return (MethodElement) element;
			}
		}
		return null;
	}
	
	/**
	 * Resolves the proxy and its containers
	 * 
	 * @param proxy
	 * @return
	 */
	public static EObject resolve(EObject proxy, ResourceSet resourceSet) {
		EObject resolved;
		try {
			resolved = EcoreUtil.resolve(proxy, resourceSet);
		} catch (Exception e) {
			resolved = proxy;
		}
		EObject container = proxy.eContainer();
		if (resolved.eContainer() == null && container != null) {
			if (container.eIsProxy()) {
				container = resolve(container, resourceSet);
			}
			EReference ref = proxy.eContainmentFeature();
			if (ref.isMany()) {
				List values = (List) container.eGet(ref);
				for (Iterator iter = values.iterator(); iter.hasNext(); iter
						.next())
					;
			} else {
				container.eGet(ref);
			}
		}
		return resolved;
	}
	
	public static Resource getResource(IPath path, ResourceSet resourceSet) {
		return getResource(path.toOSString(), resourceSet);
	}
	
	public static Resource getResource(String path, ResourceSet resourceSet) {
		File file = new File(path);
		for (Resource resource : new ArrayList<Resource>(resourceSet.getResources())) {
			if(resource != null) {
				URI finalURI = MultiFileSaveUtil.getFinalURI(resource);
				if(finalURI.isFile() && file.equals(new File(finalURI.toFileString()))) {
					return resource;
				}
			}
		}
		return null;
	}
	
	/**
	 * Resolves container of the MethodElement in the given resource.
	 * 
	 * @param resource
	 */
	public static void resolveContainer(Resource resource) {
		ResourceSet resourceSet = resource.getResourceSet();
		if(resourceSet instanceof MultiFileResourceSetImpl) {
			((MultiFileResourceSetImpl)resourceSet).resolveContainer(resource);
		}
	}

	public static MethodElement getMethodElement(IResource iResource, ResourceSet resourceSet) {
		MethodElement element = null;
		URI uri = URI.createFileURI(iResource.getLocation().toOSString());
		Resource resource = resourceSet.getResource(uri, true);		
		if (resource != null) { 
			if(resourceSet instanceof MultiFileResourceSetImpl) {
				((MultiFileResourceSetImpl)resourceSet).resolveContainer(resource);
			}
			element = PersistenceUtil.getMethodElement(resource);
			if (element instanceof ContentDescription) {
				EObject container = ((ContentDescription)element).eContainer();
				if (container instanceof MethodElement) {
					element = (MethodElement)container;
				}
			}
		}
		return element;
	}
	
	public static void loadAllLibraryResources(MethodLibrary lib) {
		for (Iterator iter = lib.eAllContents(); iter.hasNext();) {
			EObject element = (EObject) iter.next();
			if (element instanceof MethodElement) {
				try {
					for (Iterator iterator = element.eCrossReferences()
							.iterator(); iterator.hasNext();) {
						iterator.next();
					}
				} catch (Exception e) {
					CommonPlugin.INSTANCE.log(e);
				}
			}
		}
	}
	
	public static String getTopAttribute(File file, final String attributeName) {
		// Create the SAX parser factory.
		SAXParserFactory spf = SAXParserFactory.newInstance();

		// Create the SAX parser.
		try {
			SAXParser saxParser = spf.newSAXParser();
			final String[] valueHolder = new String[1];
			try {
			saxParser.parse(file, new DefaultHandler() {	
				private boolean started;
				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
					if(started) {
						throw new OperationCanceledException();
					}
//					System.out.println("uri: " + uri);
//					System.out.println("localName: " + localName);
//					System.out.println("qName: " + qName);
					valueHolder[0] = attributes.getValue(attributeName);
					started = true;
				}
			});
			}
			catch(OperationCanceledException e) {
				//
			}
			return valueHolder[0];
		} catch (Exception e) {
			PersistencePlugin.getDefault().getLogger().logError(e);
		}
		return null;
	}
		
	public static void unload(Collection<Resource> removedResources) {
		Map<MultiFileResourceSetImpl, Collection<Resource>> resourceSetToUnloadedResourcesMap = new HashMap<MultiFileResourceSetImpl, Collection<Resource>>();
		for (Resource resource : removedResources) {
			ResourceSet resourceSet = resource.getResourceSet();
			boolean unloaded = false;
			try {
				if(resourceSet instanceof ILibraryResourceSet) {
					if(((ILibraryResourceSet)resourceSet).unload(resource, Collections.EMPTY_MAP)) {
						resourceSet.getResources().remove(resource);
						unloaded = true;
					}
				}
				else {
					if(resource.isLoaded()) {
						resource.unload();
						unloaded = true;
					}
					if(resourceSet != null) {
						resourceSet.getResources().remove(resource);
					}
				}
			} catch (Exception e) {
				PersistencePlugin.getDefault().getLogger().logError(e);
			}	
			if(unloaded && resourceSet instanceof MultiFileResourceSetImpl) {
				Collection<Resource> unloadedResources = resourceSetToUnloadedResourcesMap.get(resourceSet);
				if(unloadedResources == null) {
					unloadedResources = new ArrayList<Resource>();
					resourceSetToUnloadedResourcesMap.put((MultiFileResourceSetImpl) resourceSet, unloadedResources);
				}
				unloadedResources.add(resource);
			}
		}
		for (Map.Entry<MultiFileResourceSetImpl, Collection<Resource>> entry : resourceSetToUnloadedResourcesMap.entrySet()) {
			entry.getKey().notifyRefreshListeners(new RefreshEvent(entry.getValue()));
		}
	}
	
	/**
	 * Checks if the given method element has a GUID that is the same as GUID of
	 * one element in the given elements list.
	 * 
	 * @param e
	 * @param elements
	 * @return true if the given method element has duplicate GUID
	 */
	public static boolean hasDuplicateGUID(MethodElement e, Collection<? extends MethodElement> elements) {
		for (MethodElement element : elements) {
			if(e.getGuid().equals(element.getGuid())) {
				return true;
			}
		}
		return false;
	}
	
	private static String toPrefix(String path) {
		return path.endsWith(File.separator) ? path : path + File.separator;
	}
	
	/**
	 * 
	 * @param resources
	 * @param oldPrefix
	 * @param newPrefix
	 * @return Resources with new URI
	 */
	public static Collection<Resource> replaceURIPrefix(Collection<Resource> resources, String oldPrefix, String newPrefix) {
		URI oldPrefixURI = URI.createFileURI(toPrefix(oldPrefix));
		URI newPrefixURI = URI.createFileURI(toPrefix(newPrefix));
		ArrayList<Resource> resourcesWithNewURI = new ArrayList<Resource>();
		for(Resource resource : resources) {
			URI uri = resource.getURI();
			URI newURI = null;
			try {
				newURI = uri.replacePrefix(oldPrefixURI, newPrefixURI);
			}
			catch(Exception e) {
				
			}
			if(newURI != null && !newURI.equals(resource.getURI())) {
				resource.setURI(newURI);
				resourcesWithNewURI.add(resource);
			}
		}
		return resourcesWithNewURI;
	}
	
	public static URI getProxyURI(EObject object) {
		Resource resource = object.eResource();
		URI uri = null;
		if(resource instanceof ILibraryResource) {
			ILibraryResource libResource = (ILibraryResource) resource;
			uri = libResource.getProxyURI(object);			
		}
		else {
			uri = resource.getURI().appendFragment(resource.getURIFragment(object));
		}
		return uri;
	}
	
	public static MultiFileResourceSetImpl getImportPluginResourceSet() {
		MultiFileResourceSetImpl resourceSet;
		resourceSet = new MultiFileResourceSetImpl(false) {
			protected void demandLoad(Resource resource) throws IOException {
				if (! skipDemandLoad(resource)) {
					super.demandLoad(resource);
				}
			}
			private boolean skipDemandLoad(Resource res) {
				File file = new File(res.getURI().toFileString());
				if (! file.exists() && file.getName().equals(MultiFileSaveUtil.DEFAULT_PLUGIN_MODEL_FILENAME)) {
					return true;
				}
				return false;
			}
		};
		return resourceSet;
	}

	
	public static void main(String[] args) {
		String attrib = getTopAttribute(new File(args[0]), args[1]);
		System.out.println(attrib);
	}
}