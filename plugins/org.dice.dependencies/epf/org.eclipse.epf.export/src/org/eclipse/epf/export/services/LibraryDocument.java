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
package org.eclipse.epf.export.services;

import java.io.File;
import java.io.FileFilter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epf.common.utils.XMLUtil;
import org.eclipse.epf.export.ExportPlugin;
import org.eclipse.epf.library.edit.util.MethodLibraryPropUtil;
import org.eclipse.epf.persistence.MultiFileSaveUtil;
import org.eclipse.epf.uma.MethodElementProperty;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.UmaPackage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Encapsulates a method library using a DOM document.
 * 
 * @author Jinhua Xi
 * @author Weiping Lu
 * @since 1.0
 */
public class LibraryDocument {

	public static final String TAG_methodPlugins = "methodPlugins"; //$NON-NLS-1$

	public static final String TAG_predefinedConfigurations = "predefinedConfigurations"; //$NON-NLS-1$

	public static final String TAG_resourceDescriptors = "resourceDescriptors"; //$NON-NLS-1$

	public static final String TAG_resourceSubManagers = "subManagers"; //$NON-NLS-1$
	
	public static final String TAG_methodElementProperty = "methodElementProperty"; //$NON-NLS-1$

	public static final String ATTR_href = "href"; //$NON-NLS-1$

	public static final String ATTR_id = "id"; //$NON-NLS-1$

	public static final String ATTR_uri = "uri"; //$NON-NLS-1$

	public static final String ATTR_guid = "guid"; //$NON-NLS-1$
	
	public static final String ATTR_value = "value"; //$NON-NLS-1$

	public static final String exportFile = "export.xmi"; //$NON-NLS-1$

	public static final String libraryFile = "library.xmi"; //$NON-NLS-1$

	public static final String ATTR_name = "name"; //$NON-NLS-1$
	
	protected File libFile;

	protected Document document;

	protected Element libTag = null;

	protected Element resTag = null;
	
	private HashMap guidToUriMap = null;
	
	private Map<String, String> guidToPlugNameMap;
	
	private int synFreeLibIx = -1; //-1: unset, 0: false, 1: true;

	/**
	 * Creates a new instance.
	 */
	public LibraryDocument(File libFile) throws Exception {
		this.libFile = libFile;
		init();
	}

	private void init() throws Exception {
		this.document = XMLUtil.loadXml(libFile);

		Element root = document.getDocumentElement();
		NodeList nodes = root.getElementsByTagName("org.eclipse.epf.uma:MethodLibrary"); //$NON-NLS-1$
		if (nodes != null && nodes.getLength() > 0) {
			libTag = (Element) nodes.item(0);
		}

		nodes = root
				.getElementsByTagName("org.eclipse.epf.uma.resourcemanager:ResourceManager"); //$NON-NLS-1$
		if (nodes != null && nodes.getLength() > 0) {
			resTag = (Element) nodes.item(0);
		}
	}

    /**
     * Returns the document field
     */
	public Document getDocument() {
		return this.document;
	}

    /**
     * Returns the libFile field
     */
	public File getFile() {
		return libFile;
	}

    /**
     * Returns the libTag field
     */
	public Element getLibTag() {
		return libTag;
	}

    /**
     * Returns the library name
     */
	public String getLibraryName() {
		return libTag.getAttribute("name"); //$NON-NLS-1$
	}

    /**
     * Returns the library guid
     */
	public String getLibraryGuid() {
		return libTag.getAttribute("guid"); //$NON-NLS-1$
	}

    /**
     * Returns the resource tag
     */
	public Element getResourceTag() {
		return resTag;
	}

    /**
     * Removes a plugin node
     */
	public void removePlugin(Element node) {
		libTag.removeChild(node);
	}

    /**
     * Removes a config node
     */
	public void removeConfiguration(Element node) {
		libTag.removeChild(node);
	}

    /**
     * Removes a resource descriptor node
     */
	public void removeResourceDescriptor(Element node) {
		resTag.removeChild(node);
	}

    /**
     * Returns plugin node list
     */
	public NodeList getPlugins() {
		return libTag.getElementsByTagName(TAG_methodPlugins);
	}

    /**
     * Returns configuration node list
     */
	public NodeList getConfigurations() {
		return libTag.getElementsByTagName(TAG_predefinedConfigurations);
	}

    /**
     * Returns resource descriptor node list
     */
	public NodeList getResourceDescriptors() {
		return resTag.getElementsByTagName(TAG_resourceDescriptors);
	}

    /**
     * Returns resource submanager node list
     */
	public NodeList getResourceSubManagers() {
		return resTag.getElementsByTagName(TAG_resourceSubManagers);
	}

    /**
     * Adds a plugin node
     */
	public void addPlugin(Element node) {
		libTag.appendChild(getValidNode(node));
	}

    /**
     * Adds a configuration node
     */
	public void addConfiguration(Element node) {
		libTag.appendChild(getValidNode(node));
	}

    /**
     * Adds a resource node
     */
	public void addResource(Element node) {
		resTag.appendChild(getValidNode(node));
	}

    /**
     * Returns a valid node
     */
	public Node getValidNode(Node node) {
		if (node.getOwnerDocument() == document) {
			return node;
		}

		return document.importNode(node, true);
	}

	/**
	 * remove plugins by guid
	 * 
	 * @param removeList
	 *            List a list of guids
	 */
	public void removePlugins(List removeList) {
		// Importing a package of plgins twice generates 2
		// identical plugins
		// remove the node will cause the node list to shrink, so don't increase
		// the index
		NodeList nodes = getPlugins();
		int i = 0;
		while (i < nodes.getLength()) {
			Element node = (Element) nodes.item(i);
			String guid = getGuid(node);
			if (removeList.contains(guid)) {
				libTag.removeChild(node);
			} else {
				i++;
			}
		}
	}

    /**
     * Remove the list from the configuration nodes
     */
	public void removeConfigurations(List removeList) {
		// remove the unneeded configurations
		NodeList nodes = getConfigurations();
		int i = 0;
		while (i < nodes.getLength()) {
			Element node = (Element) nodes.item(i);
			String guid = getGuid(node);
			if (removeList.contains(guid)) {
				libTag.removeChild(node);
			} else {
				i++;
			}
		}
	}

    /**
     * Remove the list from the resource nodes
     */
	public void removeResourceEntries(List removeList) {
		NodeList nodes = getResourceDescriptors();
		int i = 0;
		while (i < nodes.getLength()) {
			Element node = (Element) nodes.item(i);
			String guid = node.getAttribute(ATTR_id);
			String uri = node.getAttribute(ATTR_uri);
			if (removeList.contains(guid)) {
				resTag.removeChild(node);

				// check the plugin xmi file, if exists, delete the folder
				File plugn_file = getFileFromUri(uri);
				if (plugn_file.exists()) {
					// delete the folder ???
				}
			} else {
				i++;
			}
		}

		// also remove the sub managers
		nodes = getResourceSubManagers();
		i = 0;
		while (i < nodes.getLength()) {
			Element node = (Element) nodes.item(i);
			String guid = getSubManagerBaseGuid(node.getAttribute(ATTR_href));
			if (removeList.contains(guid)) {
				resTag.removeChild(node);
			} else {
				i++;
			}
		}
	}

	/**
	 * get the resource uri for the resource guid
	 * @param guid
	 * @return String the uri
	 */
	public String getResourceUri(String guid) {
		String uri = getUriFromGuidToUriMap(guid);
		if (uri == null) {
			uri = getResourceUri_(guid);
			if (uri != null) {
				//addToGuidToUriMap(guid, uri);
			}
		}
		return uri;
	}
	
	private String getResourceUri_(String guid) {
	
		NodeList nodes = getResourceDescriptors();
		for (int i = 0; i < nodes.getLength(); i++ ) {
			Element node = (Element) nodes.item(i);
			String id = node.getAttribute(ATTR_id);
			String uri = node.getAttribute(ATTR_uri);
			if ( guid.equals(id) ) {
				return decodeUri(uri);
			}
		}
		
		return null;
	}

	/**
	 * Returns the decored URI.
	 */
	public String decodeUri(String uri) {
		try {
			uri = URLDecoder.decode(uri, "UTF-8"); //$NON-NLS-1$
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return uri;
	}
	
	/**
	 * Returns the file given the uri.
	 */
	public File getFileFromUri(String uri) {
		uri = decodeUri(uri);
		
		int i = uri.indexOf("#"); //$NON-NLS-1$
		if (i > 0) {
			uri = uri.substring(0, i);
		}

		return new File(libFile.getParentFile(), uri);
	}

	/**
	 * Saves the document.
	 */
	public void save() throws Exception {
		saveAs(libFile.getAbsolutePath());
	}

	/**
	 * Saves the document in the file given by filePathName.
	 */
	public void saveAs(String filePathName) throws Exception {
		XMLUtil.saveDocument(this.document, filePathName);
	}

	/**
	 * Is the document for configutation specification export/import only?
	 */
	public boolean isConfigSpecsOnly() {
		NodeList nodes = getPlugins();
		if (nodes == null || nodes.getLength() == 0) {
			return true;
		}

		for (int i = 0; i < nodes.getLength(); i++) {
			Element node = (Element) nodes.item(i);
			String href = node.getAttribute(LibraryDocument.ATTR_href);
			String guid = getGuidFromHref(href);
			String uri = getResourceUri(guid);
			
			// check if the resource files are there
			File plugn_file = getFileFromUri(uri);
			if (plugn_file.exists()) {
				return false;
			}
		}

		return true;
	}

	// static hlper methods /////////////////////////////////////
	/**
	 * Returns submanager's base guid.
	 */
	public static String getSubManagerBaseGuid(String href) {
		final Pattern p = Pattern.compile(
				"uma://(.*?)#(.*?)", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$
		Matcher m = p.matcher(href);
		if (m.find()) {
			return m.group(1);
		}

		return href;
	}

	/**
	 * Returns child value of the child tag given by childTagName.
	 */
	public static String getChildValue(Element tag, String childTagName) {
		NodeList nodes = tag.getChildNodes();
		if (nodes == null || nodes.getLength() == 0) {
			return ""; //$NON-NLS-1$
		}

		int size = nodes.getLength();
		for (int i = 0; i < size; i++) {
			Node node = nodes.item(i);
			if ((node instanceof Element)
					&& ((Element) node).getTagName().equals(childTagName)) {
				return getNodeText((Element) node);
			}
		}

		return ""; //$NON-NLS-1$
	}

	/**
	 * text of a leaf node, without child element
	 * 
	 * @param tag
	 * @return
	 */
	public static String getNodeText(Element tag) {

		NodeList nodes = tag.getChildNodes();
		if (nodes == null || nodes.getLength() == 0) {
			return ""; //$NON-NLS-1$
		}

		int size = nodes.getLength();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < size; i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.TEXT_NODE) {
				buffer.append(node.getNodeValue());
			}
		}

		return buffer.toString();
	}

	/**
	 * Returns guid from href.
	 */
	public static String getGuidFromHref(String href) {
		int i = href.indexOf("#"); //$NON-NLS-1$
		if (i > 0) {
			return href.substring(i + 1);
		}

		return href;
	}

	/**
	 * Returns guid of the node.
	 */
	public static String getGuid(Element node) {
		String id = node.getAttribute("xmi:id"); //$NON-NLS-1$
		if ( id == null || id.length() == 0 ) {
			String href = node.getAttribute(ATTR_href);
			id = getGuidFromHref(href);
		}
		
		return id;
	}
	
	// 143033 - update config specs' importing and exporting to match with the new file format
	protected Element getConfigNode(Element configNode) {
		try {
			
			// new model defines href for configuration 
			String href = configNode.getAttribute(ATTR_href);
			if (href == null || href.length() == 0 ) {
				return configNode;
			} 

			String guid = getGuidFromHref(href);
			String uri = getResourceUri(guid);
			if ( uri == null ) {
				return configNode;
			}
			
			File source = getFileFromUri(uri);
			Document document = XMLUtil.loadXml(source);
			Element root = document.getDocumentElement();

			Element configTag = null;
			if (root.getTagName().equals("org.eclipse.epf.uma:MethodConfiguration")) //$NON-NLS-1$
			{
				configTag = root;
			} else {
				NodeList nodes = root
						.getElementsByTagName("org.eclipse.epf.uma:MethodConfiguration"); //$NON-NLS-1$
				if (nodes.getLength() > 0) {
					configTag = (Element) nodes.item(0);
				}
			}
			
			return configTag;

		} catch (Exception e) {
			ExportPlugin.getDefault().getLogger().logError(e);
		}
		
		return configNode;
	}
	
	/**
	 * Returns ConfigurationSpec instance given the element node config.
	 */
	public ConfigurationSpec getConfigurationSpec(Element config) {
		ConfigurationSpec spec = new ConfigurationSpec();
		
		Element configNode = getConfigNode(config);
		spec.guid = configNode.getAttribute("xmi:id"); //$NON-NLS-1$
		spec.name = configNode.getAttribute("name"); //$NON-NLS-1$
		spec.brief_desc = configNode.getAttribute("briefDescription"); //$NON-NLS-1$
		
		// get plugins
		NodeList nodes = configNode.getElementsByTagName("methodPluginSelection"); //$NON-NLS-1$
		if (nodes != null) {
			for (int i = 0; i < nodes.getLength(); i++) {
				Element node = (Element) nodes.item(i);
				String guid = getGuidFromHref(node.getAttribute(ATTR_href));
				spec.pluginIds.add(guid);
			}
		}

		// get packages
		nodes = configNode.getElementsByTagName("methodPackageSelection"); //$NON-NLS-1$
		if (nodes != null) {
			for (int i = 0; i < nodes.getLength(); i++) {
				Element node = (Element) nodes.item(i);
				String guid = getGuidFromHref(node.getAttribute(ATTR_href));
				spec.packageIds.add(guid);
			}
		}

		// get views
		nodes = configNode.getElementsByTagName("processViews"); //$NON-NLS-1$
		if (nodes != null) {
			for (int i = 0; i < nodes.getLength(); i++) {
				Element node = (Element) nodes.item(i);
				String guid = getGuidFromHref(node.getAttribute(ATTR_href));
				spec.viewIds.add(guid);
			}
		}
		
		// get added CCs
		nodes = configNode.getElementsByTagName("addedCategory"); //$NON-NLS-1$
		if (nodes != null) {
			for (int i = 0; i < nodes.getLength(); i++) {
				Element node = (Element) nodes.item(i);
				String guid = getGuidFromHref(node.getAttribute(ATTR_href));
				spec.addedCCIds.add(guid);
			}
		}
		
		// get substract CCs
		nodes = configNode.getElementsByTagName("subtractedCategory"); //$NON-NLS-1$
		if (nodes != null) {
			for (int i = 0; i < nodes.getLength(); i++) {
				Element node = (Element) nodes.item(i);
				String guid = getGuidFromHref(node.getAttribute(ATTR_href));
				spec.substractCCIds.add(guid);
			}
		}
		
		// get default view
		nodes = configNode.getElementsByTagName("defaultView"); //$NON-NLS-1$
		if (nodes != null) {
			for (int i = 0; i < nodes.getLength(); i++) {
				Element node = (Element) nodes.item(i);
				String guid = getGuidFromHref(node.getAttribute(ATTR_href));
				spec.defaultView = guid;
				break;
			}
		}

		// get meps
		nodes = configNode.getElementsByTagName(TAG_methodElementProperty); //$NON-NLS-1$
		if (nodes != null) {
			for (int i = 0; i < nodes.getLength(); i++) {
				Element node = (Element) nodes.item(i);
				String name = node.getAttribute(ATTR_name);
				String value = node.getAttribute(ATTR_value);
				MethodElementProperty mep = (MethodElementProperty) EcoreUtil
						.create(UmaPackage.eINSTANCE.getMethodElementProperty());
				mep.setName(name);
				mep.setValue(value);
				spec.mepList.add(mep);
			}
		}
		
		return spec;
	}
	
	/**
	 * Adds guid/uri pair to guidToUriMap.
	 */
	public void addToGuidToUriMap(String guid, String uri) {
		if (guidToUriMap == null) {
			guidToUriMap = new HashMap();
		}
		guidToUriMap.put(guid, uri);
	}
	
	/**
	 * Gets uri from guid.
	 */
	public String getUriFromGuidToUriMap(String guid) {
		return guidToUriMap == null ? null : (String) guidToUriMap.get(guid);
	}
	
	/**
	 * Create an instance.
	 */
	public static class ConfigDocVisitor {
		public void visit(File file, Element node) {			
		}
	}
	
	/**
	 * Visit configuration files given by the configuration file folder configDir.
	 */
	public static void visitConfigFiles(File configDir, ConfigDocVisitor visitor) {	
		FileFilter filter = new FileFilter() {
			public boolean accept(File pathname) {
				return !pathname.getName().equalsIgnoreCase("cvs") && 		//$NON-NLS-1$
						pathname.isDirectory() || pathname.getName().endsWith(MultiFileSaveUtil.DEFAULT_FILE_EXTENSION);
			}			
		};		
		File[] files = configDir.listFiles(filter);
		if (files == null) {
			return;
		}
		for (int i=0; i<files.length; i++) {
			File file = files[i];
			try {
				DocumentBuilder builder = DocumentBuilderFactory.newInstance()
						.newDocumentBuilder();
				Document doc = builder.parse(file);
				Element root = doc.getDocumentElement();
				visitor.visit(file, root);				
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}		
	}
	
	/**
	 * Build a map from pluign guids to resource manager guids.
	 */
	public Map buildPluginGuidToResMgrGuidMap(List pluginGuids) {
		if (pluginGuids == null || pluginGuids.size() == 0) {
			return null;
		}
		NodeList subMgrs = getResourceSubManagers();
		if (subMgrs == null || subMgrs.getLength() == 0) {
			return null;
		}				
		HashMap pluginGuidToResMgrGuidMap = new HashMap();
		for (int i=0; i<pluginGuids.size(); i++) {			
			String pluginGuid = (String) pluginGuids.get(i);
			pluginGuidToResMgrGuidMap.put(pluginGuid, null);
		}
		boolean isEmpty = true;
		for (int i=0; i<subMgrs.getLength(); i++) {			
			Element mgr = (Element) subMgrs.item(i);
			String  href = mgr.getAttribute(ATTR_href);
			String pluginGuid = getSubManagerBaseGuid(href);
			if (pluginGuidToResMgrGuidMap.containsKey(pluginGuid)) {
				pluginGuidToResMgrGuidMap.put(pluginGuid, getGuidFromHref(href));
				isEmpty = false;
			}
		}		
		return isEmpty ?  null : pluginGuidToResMgrGuidMap;
	}
	
	/**
	 * Save extra info (all plugin guids and names) in libTag's MethodElementProperty
	 */
	public void storeExtraInfo(List<MethodPlugin> plugins) {
		if (plugins == null) {
			return;
		}		

		Element versionNode = document.createElement(TAG_methodElementProperty);
		versionNode.setAttribute(ATTR_value, "0");	//$NON-NLS-1$ 
		libTag.appendChild(versionNode);		
		
		for (Iterator<MethodPlugin> it = plugins.iterator(); it.hasNext();) {
			MethodPlugin plug = it.next();
			
			Element guidNode = document.createElement(TAG_methodElementProperty);	
			guidNode.setAttribute(ATTR_value, plug.getGuid());
			Element nameNode = document.createElement(TAG_methodElementProperty);	
			nameNode.setAttribute(ATTR_value, plug.getName());
			
			libTag.appendChild(guidNode);
			libTag.appendChild(nameNode);
		}
		
	}	
	
	private void recallExtraInfo() {
		try {
			NodeList nodes = getMethodElementProperties();
			if (nodes == null || nodes.getLength() == 0) {
				return;
			}
			int ix = -1;
			for (int i = 0; i < nodes.getLength(); i++) {
				Element node = (Element) nodes.item(i);
				String name = node.getAttribute(ATTR_name);
				if (name ==null || name.trim().length() == 0) {
					ix = i;
					break;
				}
			}
			if (ix == -1) {
				return;
			}
			
			Element versionNode = (Element) nodes.item(ix);
			String versionStr = versionNode.getAttribute(ATTR_value);
			
			if (versionStr.equals("0")) { //$NON-NLS-1$ 
				guidToPlugNameMap = new HashMap<String, String>();
				for (int i = ix + 1; i < nodes.getLength();) {
					Element node = (Element) nodes.item(i);
					String guid = node.getAttribute(ATTR_value);
					node = (Element) nodes.item(i + 1);
					String name = node.getAttribute(ATTR_value);
					guidToPlugNameMap.put(guid, name);
					if (false) {				
						System.out.println("LD> guid: " + guid); //$NON-NLS-1$
						System.out.println("LD> name: " + name); //$NON-NLS-1$
					}					
					i += 2;
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
			guidToPlugNameMap = null;
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
	
    /**
     * Returns methodElementProperty node list
     */
	private NodeList getMethodElementProperties() {
		return libTag.getElementsByTagName(TAG_methodElementProperty);
	}
	
	public boolean isSynFreeLib() {
		if (synFreeLibIx == -1) {
			synFreeLibIx = 0;
			
			NodeList nodes = getMethodElementProperties();
			if (nodes != null) {
				for (int i = 0; i < nodes.getLength(); i++) {
					Element node = (Element) nodes.item(i);
					String name = node.getAttribute(ATTR_name);
					if (name != null && name.equals(MethodLibraryPropUtil.Library_SynFree)) {
						String value = node.getAttribute(ATTR_value);
						synFreeLibIx = Boolean.parseBoolean(value) ? 1 : 0;
						break;
					}
				}
			}
		}
		return synFreeLibIx > 0;
	}

	
}
