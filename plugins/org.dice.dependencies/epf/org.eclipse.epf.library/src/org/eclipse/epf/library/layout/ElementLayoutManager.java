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
package org.eclipse.epf.library.layout;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.configuration.DefaultElementRealizer;
import org.eclipse.epf.library.configuration.ElementRealizer;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.util.ConfigurableComposedAdapterFactory;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.layout.diagram.IActivityDiagramService;
import org.eclipse.epf.library.layout.elements.AbstractElementLayout;
import org.eclipse.epf.library.layout.elements.AbstractProcessElementLayout;
import org.eclipse.epf.library.layout.elements.GeneralLayout;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodElementProperty;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.util.UmaUtil;


/**
 * @author Jinhua Xi
 * @since 1.0
 */
public class ElementLayoutManager {

	private static final String PACKAGE_NAME = ElementLayoutManager.class
			.getPackage().getName();

	private static final String default_config_path = "noconfig"; //$NON-NLS-1$

	private boolean isPublishingMode = false; // default to browsing mode
	
	private String publish_dir = null;

	private MethodConfiguration config = null;

	private IActivityDiagramService diagramService = null;
	
	// need to seperate the adapter factory form content and diagram
	// diagram adaptor factory should not be rolled up
	private LayoutAdapterFactory contentLayoutAdapterFactory = null;
	private LayoutAdapterFactory diagramLayoutAdapterFactory = null;
	
	// cache the suppression object for publishing model only
	// map of proc guid to Suppresiion object	
	private Map supressionMap = null;
	
	private IContentValidator validator = null;
	
	private ElementRealizer realizer = null;
	
	private Map<String, String> toRenamePluginFolderMap;
	
	// the layout extensions. each extension is identified by the property id of the configuration
	private Map<String, LayoutExtension> layoutExtensions = new HashMap<String, LayoutExtension>();
	
	public boolean isPublishingMode()
	{
		return this.isPublishingMode;
	}
	
	public ElementLayoutManager() {
		this(null, null, null, false);
		buildPublishDir(null);
	}

	public ElementLayoutManager(MethodConfiguration config) {
		this(config, null, null, false);
	}

	public ElementLayoutManager(MethodConfiguration config, String publishdir, IContentValidator validator, boolean isPublishingMode) {
		this.validator = validator;
		this.isPublishingMode = isPublishingMode;
		
		if (config != null) {
			this.config = config;
		}

		if (isPublishingMode) {
			buildPublishDir(publishdir);
		}

		contentLayoutAdapterFactory = new LayoutAdapterFactory(this.config);
		diagramLayoutAdapterFactory = new LayoutAdapterFactory(this.config);
		
		//	load the layout extensions for this configuration
		loadLayoutExtensions();
	}

	private boolean publishDirBuilt = false;
	public void buildPublishDir(String publishdir) {
		if (publishDirBuilt) {
			return;
		}
		publishDirBuilt = true;
		
		if (publishdir == null) {
			publishdir = LayoutResources.getDefaultPublishDir();
			if (!publishdir.endsWith(File.separator)) {
				publishdir += File.separator;
			}

			// also append the configuration path
			if (this.config == null) {
				publishdir += default_config_path;
			} else {
				String str = StrUtil.removeSpecialCharacters(this.config.getName());
				if ( str == "" ) { //$NON-NLS-1$
					str = Integer.toHexString(this.config.toString().hashCode());
				}
				publishdir += str;
			}
		}

		setPublishDir(publishdir);
	}

	private void loadLayoutExtensions() {
		if ( config == null ) {
			return;
		}
		
		// load the layout extensions for this configuration
		List props = config.getMethodElementProperty();
		if ( props != null && props.size() > 0 ) {
			for (Iterator itp = props.iterator(); itp.hasNext(); ) {
				MethodElementProperty prop = (MethodElementProperty)itp.next();
				String id = prop.getName();
				LayoutExtension ext= LayoutExtensionFactory.getInstance().createExtension(id);
				if ( ext != null ) {
					ext.init(this);
					layoutExtensions.put(id, ext);
				}
				
			}
		}		
	}
	
	/**
	 * Returns the content validator.
	 */
	public IContentValidator getValidator() {
		if (validator == null) {
			validator = new DefaultContentValidator(getPublishDir());
		}
		return validator;
	}
	
	public ElementRealizer getElementRealizer() {
		if ( realizer == null ) {
			realizer = DefaultElementRealizer.newElementRealizer(config);
			realizer.setFilter(contentLayoutAdapterFactory.getFilter());
		}
		
		return realizer;		
	}
	
	public void setElementRealizer(ElementRealizer realizer) {
		this.realizer = realizer;		
	}
	
	public ConfigurableComposedAdapterFactory getWBSAdapterFactory() {
		return contentLayoutAdapterFactory.wbsAdapterFactory;
	}

	public ConfigurableComposedAdapterFactory getTBSAdapterFactory() {
		return contentLayoutAdapterFactory.tbsAdapterFactory;
	}

	public ConfigurableComposedAdapterFactory getWPBSAdapterFactory() {
		return contentLayoutAdapterFactory.wpbsAdapterFactory;
	}

	public ConfigurableComposedAdapterFactory getCBSAdapterFactory() {
		return contentLayoutAdapterFactory.cbsAdapterFactory;
	}

	public ConfigurableComposedAdapterFactory getDiagramAdapterFactory() {
		return diagramLayoutAdapterFactory.wbsAdapterFactory;
	}
	
	public void setActivityDiagramService(IActivityDiagramService diagramService) {
		this.diagramService = diagramService;
	}

	public IActivityDiagramService getActivityDiagramService() {
		return this.diagramService;
	}

	public Suppression getSuppression(Process proc)
	{
		Suppression sup = null;
		if ( isPublishingMode)
		{
			if ( supressionMap == null )
			{
				supressionMap = new HashMap();
			}
			String guid = proc.getGuid();
			sup = (Suppression)supressionMap.get(guid);
			if ( sup == null )
			{
				sup = createSuppression(proc);
				supressionMap.put(guid, sup);
			}
		}
		else
		{
			sup = createSuppression(proc);
		}
		
		return sup;
	}
	
	public void prepareAdaptorFactoriesForProcess(Process proc)
	{
		// need to iterate the adaptor factory to generate the adaptor tree
		prepareAdapterfactoryFor(getCBSAdapterFactory(), proc);
		prepareAdapterfactoryFor(getTBSAdapterFactory(), proc);
		prepareAdapterfactoryFor(getWBSAdapterFactory(), proc);
		prepareAdapterfactoryFor(getWPBSAdapterFactory(), proc);
	}
	
	private void prepareAdapterfactoryFor(
			ComposedAdapterFactory adapterFactory, Object obj) {
		if (obj == null || adapterFactory == null) {
			return;
		}

		ITreeItemContentProvider provider = null;

		if (obj instanceof ITreeItemContentProvider) {
			provider = (ITreeItemContentProvider) obj;
		} else {
			provider = (ITreeItemContentProvider) adapterFactory.adapt(obj,
					ITreeItemContentProvider.class);
		}

		if (provider != null) {
			Collection items = provider.getChildren(obj);
			for (Iterator it = items.iterator(); it.hasNext();) {
				Object rawitem = it.next();

				MethodElement item = (MethodElement) LibraryUtil
						.unwrap(rawitem);
				if ((item instanceof Activity)) {
					prepareAdapterfactoryFor(adapterFactory, rawitem);
				}
			}
		}
	}
	
	private IElementLayout createLayout(MethodElement element) {
		
		// if the element is an activity, we need to fined the path to the process
		String path = null;
		Process owningProc = null;
		if ( element instanceof Activity )
		{
			Activity act = (Activity)element;
			path = AbstractProcessElementLayout.getPath(act);
			owningProc = TngUtil.getOwningProcess(act);
		}
		
		return createLayout(element, owningProc, path);
	}

	public IElementLayout createLayout(MethodElement element,
			Process owningProc, String path) {
		AbstractElementLayout layout = null;

		String className;
		if (ConfigurationHelper.isDescriptionElement(element)) {
			className = "ContentDescription"; //$NON-NLS-1$
		} else if (element instanceof Activity) {
			className = "Activity"; //$NON-NLS-1$
		} else if ((element instanceof WorkBreakdownElement)
				&& !(element instanceof TaskDescriptor)) {
			className = "WorkBreakdownElement"; //$NON-NLS-1$  // taskdescriptor has it's own layout
		} else {
			Class i[] = element.getClass().getInterfaces();
			if (i != null && i.length == 1) {
				className = i[0].getName();
			} else {
				className = element.getClass().getName();
			}

			int index = className.lastIndexOf("."); //$NON-NLS-1$
			if (index >= 0) {
				className = className.substring(index + 1);
			}
		}

		className = PACKAGE_NAME + ".elements." + className + "Layout"; //$NON-NLS-1$ //$NON-NLS-2$

		try {
			Class c = Class.forName(className);
			if (c != null) {
				layout = (AbstractElementLayout) c.newInstance();
			}
		} catch (Exception ex) {
			// ex.printStackTrace();
		}

		if (layout == null) {
			// System.out.println("Layout class '" + className + "' for element
			// '" + element.getName() + "' not defined. Use GeneralLayout");
			layout = new GeneralLayout();
		}

		if (layout instanceof AbstractProcessElementLayout) {
			((AbstractProcessElementLayout) layout).init(this, element,
					owningProc, path);
		} else {
			layout.init(this, element);
		}

		return layout;
	}

	public IElementLayout getLayout(MethodElement element, boolean create) {
		// String id = element.getGuid();
		IElementLayout layout = null; // (IElementLayout)layoutMap.get(id);
		if ((layout == null) && create) {
			layout = createLayout(element);
			// layoutMap.put(id, layout);
		}

		return layout;
	}

	private static final Pattern p_parameters = Pattern
			.compile(
					"\\?proc=(.*?)&path=(.*)?", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$

	public IElementLayout getLayout(String url) {

		Process owningProc = null;
		String path = null;

		Matcher m = p_parameters.matcher(url);
		if (m.find()) {
			String proc = m.group(1);
			path = m.group(2);
			ILibraryManager manager = LibraryService.getInstance()
			.getCurrentLibraryManager();
			if (manager != null) {
				owningProc = (Process) manager.getMethodElement(
						proc);
			}
		}

		// MethodElement element = (MethodElement)urlMap.get(url);
		MethodElement element = ResourceHelper.getElementFromFileName(url);
		if (element != null) {
			return createLayout(element, owningProc, path);
		} else {
			System.out.println("Can't find element for url '" + url + "'"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		return null;

	}

	public static String getQueryString(String proc, String path) {
		return "?" + ResourceHelper.URL_PARAMETER_PROCESS + "=" + proc + "&" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ ResourceHelper.URL_PARAMETER_PATH + "=" + path; //$NON-NLS-1$
	}

	public MethodConfiguration getConfiguration() {
		return this.config;
	}

	public void setPublishDir(String path) {
		this.publish_dir = path;
		if ( !this.publish_dir.endsWith(File.separator) ) {
			this.publish_dir += File.separator;
		}

		// initialize publishing site if needed
		init_publishingSite();

	}

	public String getPublishDir() {
		return this.publish_dir;
	}

	private void init_publishingSite() {
		if ( BrowsingLayoutSettings.INSTANCE.needUpdate(publish_dir) ) {
//			if ( LibraryPlugin.getDefault().isDebugging() ) {
				System.out
						.println("Begin initializing publishing site: " + publish_dir); //$NON-NLS-1$
//			}
			
			// copy the layout files from plugin layout to publishign site
			LayoutResources.copyLayoutFiles(publish_dir);
	
			if ( LibraryPlugin.getDefault().isDebugging() ) {
				System.out.println("End initializing publishing site: " + publish_dir); //$NON-NLS-1$
			}
		}
	}

//	public void copyLibraryResources() {
//		if ( LibraryPlugin.getDefault().isDebugging() ) {
//			System.out
//					.println("Begin copying library resources to publishing site: " + publish_dir); //$NON-NLS-1$
//		}
//		
//		// copy library resources from library to publishing site
//		File libRoot = new File(LibraryService.getInstance().getCurrentMethodLibraryPath());
//		if (libRoot != null) {
//			LayoutResources.copyDir(libRoot.getPath(), publish_dir);
//		}
//
//		if ( LibraryPlugin.getDefault().isDebugging() ) {
//			System.out
//					.println("End copying library resources to publishing site: " + publish_dir); //$NON-NLS-1$
//		}
//	}

	public void clear() {
		// layoutMap.clear();
		
		diagramService = null;
		
		if ( supressionMap != null )
		{
			supressionMap.clear();
		}
		
		layoutExtensions.clear();
		layoutExtensions = null;
		
		contentLayoutAdapterFactory.clear();
		diagramLayoutAdapterFactory.clear();
		
		if ( validator != null )
		{
			validator.dispose();
			validator = null;
		}
		
		if ( realizer != null ) {
			realizer.dispose();
			realizer = null;
		}
		
		if ( contentLayoutAdapterFactory != null ) {
			contentLayoutAdapterFactory.clear();
			contentLayoutAdapterFactory = null;			
		}
		
		if ( diagramLayoutAdapterFactory != null ) {
			diagramLayoutAdapterFactory.clear();
			diagramLayoutAdapterFactory = null;			
		}
		 
		toRenamePluginFolderMap = null;
	}

	public class LayoutAdapterFactory
	{
		public ConfigurableComposedAdapterFactory wbsAdapterFactory = null;
		public ConfigurableComposedAdapterFactory tbsAdapterFactory = null;
		public ConfigurableComposedAdapterFactory wpbsAdapterFactory = null;
		public ConfigurableComposedAdapterFactory cbsAdapterFactory = null;

		ProcessAdapterFactoryFilter configurator = null;
		
		public LayoutAdapterFactory(MethodConfiguration methodConfig)
		{
			// create adapt factories
			//
			if(isPublishingMode()) {
				wbsAdapterFactory = (ConfigurableComposedAdapterFactory) TngAdapterFactory.INSTANCE
				.createPublishingWBSAdapterFactory();
				tbsAdapterFactory = (ConfigurableComposedAdapterFactory) TngAdapterFactory.INSTANCE
				.createPublishingTBSAdapterFactory();
				wpbsAdapterFactory = (ConfigurableComposedAdapterFactory) TngAdapterFactory.INSTANCE
				.createPublishingWPBSAdapterFactory();
				cbsAdapterFactory = (ConfigurableComposedAdapterFactory) TngAdapterFactory.INSTANCE
				.createPublishingCBSAdapterFactory();
			}
			else {
				wbsAdapterFactory = (ConfigurableComposedAdapterFactory) TngAdapterFactory.INSTANCE
				.createWBSComposedAdapterFactory();
				tbsAdapterFactory = (ConfigurableComposedAdapterFactory) TngAdapterFactory.INSTANCE
				.createTBSComposedAdapterFactory();
				wpbsAdapterFactory = (ConfigurableComposedAdapterFactory) TngAdapterFactory.INSTANCE
				.createWPBSComposedAdapterFactory();
				cbsAdapterFactory = (ConfigurableComposedAdapterFactory) TngAdapterFactory.INSTANCE
				.createProcessComposedAdapterFactory();
			}

			// set configuration filter
			configurator = new ProcessAdapterFactoryFilter(methodConfig, this) {
				
				@Override
				public ElementRealizer getRealizer() {
					ElementRealizer realizer = ElementLayoutManager.this.getElementRealizer();
					if (realizer == null) {
						realizer = super.getRealizer();
					}
					return realizer;
				}
			};
			
			wbsAdapterFactory.setFilter(configurator);
			tbsAdapterFactory.setFilter(configurator);
			wpbsAdapterFactory.setFilter(configurator);
			cbsAdapterFactory.setFilter(configurator);		
		}
		
		public IFilter getFilter() {
			return configurator;
		}
		
		public void clear()
		{
						
			if (wbsAdapterFactory != null) {
				wbsAdapterFactory.setFilter(null);
				wbsAdapterFactory.dispose();
				wbsAdapterFactory = null;
			}

			if (tbsAdapterFactory != null) {
				tbsAdapterFactory.setFilter(null);
				tbsAdapterFactory.dispose();
				tbsAdapterFactory = null;
			}

			if (wpbsAdapterFactory != null) {
				wpbsAdapterFactory.setFilter(null);
				wpbsAdapterFactory.dispose();
				wpbsAdapterFactory = null;
			}

			if (cbsAdapterFactory != null) {
				cbsAdapterFactory.setFilter(null);
				cbsAdapterFactory.dispose();
				cbsAdapterFactory = null;
			}
			
			if ( configurator != null ) {
				configurator.dispose();
				configurator = null;
			}
		}
	}
	
	private Suppression createSuppression(Process proc) {
		// check if there are extensions
		if ( layoutExtensions != null && layoutExtensions.size() > 0 ) {
			for ( Iterator<LayoutExtension> it = layoutExtensions.values().iterator(); it.hasNext(); ) {
				LayoutExtension ext = it.next();
				Suppression sup = ext.getSuppression(proc);
				if ( sup != null ) {
					return sup;
				}
			}
		}
		
		return new Suppression(proc);
	}
	
	public void prepareElementPathAdjustment() {
		String fChars = BrowsingLayoutSettings.INSTANCE.getForbiddenUrlChars();
		if (fChars == null || fChars.trim().length() == 0) {			//if flag is not set
			return;
		}
		MethodConfiguration c = getConfiguration();
		if (c == null) {
			return;
		}
		
		toRenamePluginFolderMap = new HashMap<String, String>();
		Set<String> usedNameSet = new HashSet<String>();
		MethodLibrary lib = UmaUtil.getMethodLibrary(c);
		for (MethodPlugin p : lib.getMethodPlugins()) {
			usedNameSet.add(p.getName());
		}
		
		List<MethodPlugin> plugins = c.getMethodPluginSelection();
		Set<String> forbidenChars = new HashSet<String>();
		for (int i = 0; i < fChars.length(); i++) {			
			String str = String.valueOf((fChars.charAt(i)));
			if (str.trim().length() > 0) {
				forbidenChars.add(str);
			}
		}
		for (MethodPlugin plugin : plugins) {
			String name = plugin.getName();
			String replacingName = getReplacingName(name, forbidenChars);
			if (! name.equals(replacingName)) {
				replacingName = getUniqueName(replacingName, usedNameSet);
				usedNameSet.add(replacingName);
				toRenamePluginFolderMap.put(plugin.getName(), replacingName);
			}
					}
		if (toRenamePluginFolderMap.isEmpty()) {
			toRenamePluginFolderMap = null;
		}
	}
	
	private String getReplacingName(String name, Set<String> forbidenChars) {
		StringBuffer b = new StringBuffer();
		boolean modified = false;
		for (int i = 0; i < name.length(); i++) {			
			String str = String.valueOf((name.charAt(i)));
			if (forbidenChars.contains(str)) {
				b.append("_"); //$NON-NLS-1$ 
				modified = true;
			} else {
				b.append(str);
			}			
		}
		return modified ? b.toString() : name;
	}
	
	public String getAdjustedElementPath(String elementPath) {
		if (toRenamePluginFolderMap == null) {
			return elementPath;
		}
		String pluginName = getPluginName(elementPath);
		if (pluginName == null) {
			return elementPath;
		}
		String replacingPluginName = toRenamePluginFolderMap.get(pluginName);
		if (replacingPluginName == null) {
			return elementPath;
		}
					
		return elementPath.replaceFirst(pluginName, replacingPluginName);
	}

	private String getUniqueName(String name, Set<String> usedNameSet) {
		String uniqueName = name;
		int i = 0;
		while(usedNameSet.contains(uniqueName)) {
			i++;
			uniqueName = name + i;
		}
		
		return uniqueName;
	}
	
	private String getPluginName(String elementPath) {
		int ix = elementPath.indexOf("/");
		if (ix > 0) {
			return elementPath.substring(0, ix);
		}		
		return null;
	}
	
	public String getAdjustedElementPathStringValue(String source) {
		if (toRenamePluginFolderMap == null) {
			return source;
		}
		
		boolean localDebug = false;
		String str = getAdjustedElementPathStringValue(source, ResourceHelper.p_link_ref);
		str = getAdjustedElementPathStringValue(str, ResourceHelper.p_area_ref);
		if (localDebug) {
			if (str != source) {
				System.out.println("LD> original value: " + source);	//$NON-NLS-1$
				System.out.println("");	//$NON-NLS-1$
				System.out.println("LD> modified value: " + str);	//$NON-NLS-1$
			}
			System.out.println("");	//$NON-NLS-1$
		}		
		
		return str;
	}
		
	private String getAdjustedElementPathStringValue(String source, Pattern pattern) {
		boolean localDebug = false;
		
		boolean modified = false;
		StringBuffer sb = new StringBuffer();
		Matcher m = pattern.matcher(source);

		while (m.find()) {
			String text = m.group();
			String replacement = text;			
			
			Map attributeMap = ResourceHelper.getAttributesFromLink(pattern, text);
			
			if (localDebug) {
				System.out.println("LD> text:      " + text);					//$NON-NLS-1$
				System.out.println("LD> attributeMap: " + attributeMap);		//$NON-NLS-1$
			}
			
			String href = attributeMap == null ? null : (String) attributeMap.get(ResourceHelper.TAG_ATTR_HREF);
			
			int ix0 = -1;
			int ix1 = -1;
			if (href != null) {
				//example: href="./../../a.b.c.d.e/roles/new_role-1_BA944608.html"
				//						 |        |
				//						ix0       |
				//						         ix1
				ix0 = href.lastIndexOf("../");		//$NON-NLS-1$
				if (ix0 > 0) {
					ix0 += 3;			
					ix1 = href.substring(ix0).indexOf("/");		//$NON-NLS-1$
					if (ix1 > 0) {
						ix1 += ix0;
					}
				}
			}
			
			if (ix0 >= 0 && ix1 > ix0) {
				String replaced = href.substring(ix0, ix1);
				String replacing = toRenamePluginFolderMap.get(replaced);
				if (replacing != null) {
					replacement = text.replace(replaced, replacing);
					if (localDebug) {
						System.out.println("LD>  replaced: " + replaced);	//$NON-NLS-1$
						System.out.println("LD> replacing: " + replacing);	//$NON-NLS-1$
						System.out.println("");								//$NON-NLS-1$
					}
					
					modified = true;
				}
			}			
			m.appendReplacement(sb, Matcher.quoteReplacement(replacement));				
		}
		m.appendTail(sb);
		
		return modified ? sb.toString() : source;
	}
	
	public void handlePluginFolderRename() {
		if (toRenamePluginFolderMap == null) {
			return;
		}
		String topDirStr = getPublishDir();
	    for (Map.Entry<String, String> entry : toRenamePluginFolderMap.entrySet()) {
	    	File oldPluginFolder = new File(topDirStr, entry.getKey());
	    	if (oldPluginFolder.isDirectory()) {
	    		File newPluginFolder = new File(topDirStr, entry.getValue());
	    		boolean b = oldPluginFolder.renameTo(newPluginFolder);
	    		if (! b) {
	    			FileUtil.copyDir(oldPluginFolder, newPluginFolder);
	    			FileUtil.deleteAllFiles(oldPluginFolder.getAbsolutePath());
	    			oldPluginFolder.delete();
	    		}
	    	}
	    }
	}
	
	private Map<String, MethodElement> uriElementMap = new HashMap<String, MethodElement>();
	public Map<String, MethodElement> getUriElementMap() {
		return uriElementMap;
	}
}

