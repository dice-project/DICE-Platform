//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.publishing.services;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.common.serviceability.Logger;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.common.utils.NetUtil;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.edit.configuration.PracticeSubgroupItemProvider;
import org.eclipse.epf.library.edit.util.MethodElementPropUtil;
import org.eclipse.epf.library.edit.util.PracticePropUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.layout.Bookmark;
import org.eclipse.epf.library.layout.ElementLayoutManager;
import org.eclipse.epf.library.layout.HtmlBuilder;
import org.eclipse.epf.library.layout.IElementLayout;
import org.eclipse.epf.library.util.IconUtil;
import org.eclipse.epf.publishing.PublishingPlugin;
import org.eclipse.epf.publishing.PublishingResources;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;
import org.eclipse.epf.uma.util.UserDefinedTypeMeta;
import org.eclipse.osgi.util.NLS;

/**
 * The abstract base class for all publishing view builders.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @author Weiping Lu
 * @since 1.0
 */
public abstract class AbstractViewBuilder {

	protected static final int TIMEOUT_INTERVAL = 600000; // in milliseconds

	protected static boolean profiling = PublishingPlugin.getDefault()
			.isProfiling();

	protected ISiteGenerator siteGenerator;

	protected PublishOptions options;

	protected MethodConfiguration config;

	protected Bookmark defaultView;

	PublishingRunnable runnable = null;

	/**
	 * Creates a new instance.
	 * 
	 * @param generator
	 *            a site generator
	 */
	public AbstractViewBuilder(ISiteGenerator generator) {
		this.siteGenerator = generator;
		this.options = generator.getPublishOptions();
		this.config = generator.getHtmlBuilder().getLayoutManager()
				.getConfiguration();
		this.runnable = new PublishingRunnable(generator.getHtmlBuilder(), TIMEOUT_INTERVAL);
	}

	/**
	 * Gets the site generator.
	 * 
	 * @return the site generator assigned to this view builder
	 */
	public ISiteGenerator getSiteGenerator() {
		return this.siteGenerator;
	}

	/**
	 * Gets the HTML builder.
	 * 
	 * @return the HTML builder assigned to this view builder
	 */
	public HtmlBuilder getHtmlBuilder() {
		return this.siteGenerator.getHtmlBuilder();
	}

	/**
	 * Gets the publishing options.
	 * 
	 * @return the publishing options assigned to this view builder
	 */
	public PublishOptions getOptions() {
		return this.siteGenerator.getPublishOptions();
	}

	/**
	 * Gets the layout manager.
	 * 
	 * @return an element layout manager assigned to this view builder
	 */
	public ElementLayoutManager getLayoutMgr() {
		return getHtmlBuilder().getLayoutManager();
	}

	/**
	 * Gets the content validator.
	 * 
	 * @return an element layout manager assigned to this view builder
	 */
	public PublishingContentValidator getValidator() {
		return (PublishingContentValidator) getHtmlBuilder().getValidator();
	}

	/**
	 * Checks whether a method element can be published or not.
	 * 
	 * @param element
	 *            a method element
	 * @return <code>true</code> if the method element can be published
	 */
	protected boolean canPublish(MethodElement element) {
		return isVisible(element);
	}

	/**
	 * Checks whether a method element will be visible in the published web
	 * site.
	 * 
	 * @param element
	 *            a method element
	 * @return <code>true</code> if the method element will be visible in the
	 *         published web site
	 */
	protected boolean isVisible(MethodElement element) {
		if (element == null) {
			return false;
		}

		if (getValidator().isDiscarded(null, null, element)) {
			return false;
		}

		if (!ConfigurationHelper.canShow(element, config)) {
			return false;
		}

		if (options.isPublishProcess()) {
			return getValidator().inClosure(element);
		} else {
			return true;
		}
	}

	protected void discardEmptyCategory(ContentCategory element, boolean discard) {
		if (discard) {
			getValidator().setDiscardedElement(element);
			getValidator().logWarning(element,
					PublishingResources.discaredCategoryWarning_msg);
		} else {
			getValidator().addValidCategory((ContentCategory) element);
		}
	}

	protected MethodElement calc01FeatureValue(MethodElement element,
			EStructuralFeature feature) {
		return ConfigurationHelper.calc01FeatureValue(element, feature,
				getLayoutMgr().getElementRealizer());
	}

	protected List calc0nFeatureValue(MethodElement element,
			EStructuralFeature feature) {
		return ConfigurationHelper.calc0nFeatureValue(element, feature,
				getLayoutMgr().getElementRealizer());
	}

	protected MethodElement calc01FeatureValue(MethodElement element,
			OppositeFeature feature) {
		return ConfigurationHelper.calc01FeatureValue(element, feature,
				getLayoutMgr().getElementRealizer());
	}

	protected List calc0nFeatureValue(MethodElement element,
			OppositeFeature feature) {
		return ConfigurationHelper.calc0nFeatureValue(element, feature,
				getLayoutMgr().getElementRealizer());
	}

	protected List getPublishedElements() {
		return getValidator().getPublishedElements();
	}

	
	/**
	 * Publishes the element, collects the linked elements in the published
	 * content.
	 * 
	 * @param monitor
	 *            a progress monitor
	 * @param element
	 *            a method element
	 */
	protected void publish(final IProgressMonitor monitor,
			final MethodElement element) {
		
		// System.out.println("--- Begin publishing element " +
		// element.getGuid() + ":" + element.getType().getName() + ":" +
		// element.getName() );
		try {
			List linkedElements = getValidator()
					.getReferencedElements();
			List publishedElements = getValidator()
					.getPublishedElements();					
			HtmlBuilder htmlBuilder = getHtmlBuilder();
			if (!canPublish(element)) {
				htmlBuilder.getValidator().logWarning(element,
						PublishingResources.invalidElementWarning_msg);
			} else if (!publishedElements.contains(element)) {
				try {
					if (monitor != null) {
						String str;
						if (linkedElements != null) {
							str = NLS
									.bind(
											PublishingResources.publishingLinkedElementTask_name,
											Integer
													.toString(publishedElements
															.size()),
											Integer
													.toString(linkedElements
															.size()));
						} else {
							str = NLS
									.bind(
											PublishingResources.publishingElementTask_name,
											element.getType().getName(),
											element.getName());
						}
						monitor.subTask(str);

					}
					IElementLayout layout = htmlBuilder
							.getLayoutManager()
							.getLayout(element, true);
//					String htmlfile = htmlBuilder.generateHtml(layout);
					String htmlfile = runnable.generateHtml(layout);
					elementPublished(layout, htmlfile);

				} catch (Exception ex) {
					htmlBuilder
							.getValidator()
							.logError(
									element,
									NLS
											.bind(
													PublishingResources.publishElementError_msg,
													ex.getMessage()),
									ex);
				}
				publishedElements.add(element);
			} else {
				// System.out.println("Already generated: " +
				// getURL(element) );
			}
		} catch (RuntimeException e) {
			getHtmlBuilder()
					.getValidator()
					.logError(
							element,
							NLS
									.bind(
											PublishingResources.publishElementError_msg,
											e.getMessage()), e);
		}
	}

	protected void elementPublished(IElementLayout layout, String htmlfile) {
	}

	private String copyNodeIcon(File source,boolean isCustomized) {
		String name = source.getName();

		File dest = null;
		if(isCustomized){
			dest = new File(siteGenerator.getCustomizedNodeIconPath(), name);
		}else{
			dest = new File(siteGenerator.getNodeIconPath(), name);
		}
		if (FileUtil.copyFile(source, dest) == false) {
			getHtmlBuilder().getValidator().logWarning(
					NLS.bind(PublishingResources.copyFileWarning_msg, source
							.getAbsolutePath(), dest.getAbsolutePath()));
		}

		if(isCustomized){
			name = AbstractSiteGenerator.customizedName + "/" + name; 
		}
		return name;
	}

	protected String getNodeIconName(Object obj) {
		File iconFile = null;
		String iconName = null;
		boolean isCustomized = true;

		if (obj instanceof DescribableElement) {
			URI uri = ((DescribableElement) obj).getNodeicon();
			
			VariabilityElement uriInheritingBase = null;
			if (uri == null && config != null && obj instanceof VariabilityElement) {
				VariabilityElement[] uriInheritingBases = new VariabilityElement[1];
				uri = ConfigurationHelper.getInheritingUri((DescribableElement) obj, uri, uriInheritingBases, config, 0);
				uriInheritingBase = uriInheritingBases[0];
			}

			String elementName = ((DescribableElement) obj).getType().getName()
					.toLowerCase();
/*			if (DefaultElementTypeResources.useDefaultIcon(elementName))
				uri = null;*/

			if (uri != null) {
				// try if this is a valid URL or not
				boolean isFullPath = false;
				try {
					URL url = uri.toURL();
					if (url != null) {
						iconFile = new File(URLDecoder.decode(url.getFile(),
								"UTF-8")); //$NON-NLS-1$
						isFullPath = true;
					}
				} catch (Exception ex) {
					// not a valid url, maybe a relative path
				}

				if (!isFullPath) {
					// iconFile = new
					// File(LibraryService.getInstance().getCurrentMethodLibraryPath(),
					// URLDecoder.decode(uri.toString()));
					// To handle the nodeIcon uri, EPF 1.2, its changed now it
					// is relative to plugin path.
					iconFile = new File(
							TngUtil.getFullPathofNodeorShapeIconURI(
									uriInheritingBase == null ? (EObject) obj : uriInheritingBase, uri));
				}
			}
		}

		if ((iconFile != null) && !iconFile.exists()) {
			iconFile = null;
		}

		if (iconFile == null) {
			isCustomized = false;
			// get the default icon name
			if (obj instanceof MethodElement) {
				String type = ((MethodElement) obj).getType().getName()
						.toLowerCase();
				if (obj instanceof CustomCategory) {
					CustomCategory cc = (CustomCategory) obj;
					MethodElementPropUtil propUtil = MethodElementPropUtil
							.getMethodElementPropUtil();
					if (propUtil.isTransientElement(cc)) {
						if (cc.getName().equals("Roles")) {//$NON-NLS-1$
							type = "roleset";//$NON-NLS-1$
						} else if (cc.getName().equals("Tasks")) {//$NON-NLS-1$
							type = "discipline";//$NON-NLS-1$
						} else if (cc.getName().equals("Work Products")) {//$NON-NLS-1$
							type = "domain";//$NON-NLS-1$
						} else if (cc.getName().equals("Guidance")) {//$NON-NLS-1$
							type = "guidances";//$NON-NLS-1$
						} else if (cc.getName().equals("Processes")) {//$NON-NLS-1$
							type = "processes";//$NON-NLS-1$
						}
					}
				}
				
				if ((obj instanceof Practice) && (PracticePropUtil.getPracticePropUtil().isUdtType((Practice)obj))) {
					type = "UDT"; //$NON-NLS-1$
					//for user defined type
					try {
						boolean debug = PublishingPlugin.getDefault().isDebugging();
						Logger logger = PublishingPlugin.getDefault().getLogger();
						
						UserDefinedTypeMeta udtMeta = PracticePropUtil.getPracticePropUtil().getUtdData((Practice)obj);
						String icon = udtMeta.getRteNameMap().get(UserDefinedTypeMeta._icon);
						if (debug) {
							logger.logInfo("The udt node icon get from meta: " + icon); //$NON-NLS-1$
						}
						if (icon != null) {
							iconFile = new File(NetUtil.decodedFileUrl(new URL(icon).getFile()));
							if (debug) {
								logger.logInfo("The udt node icon file: " + iconFile); //$NON-NLS-1$
							}
						}
					} catch (Exception e) {
						getHtmlBuilder().getValidator().logError("", e); //$NON-NLS-1$
						iconFile = null;
					}			
				}
				
				if (iconFile == null) {				
					iconFile = IconUtil.getNodeIconFile(type);
				}
			} else if (obj instanceof PracticeSubgroupItemProvider) {
				iconFile = IconUtil.getNodeIconFile((PracticeSubgroupItemProvider) obj);
			}
		}

		if (iconFile != null) {
			// need to copy the file together and zip for the applet
			if (!iconFile.exists()) {
				if (obj instanceof MethodElement) {
					getHtmlBuilder()
							.getValidator()
							.logWarning(
									(MethodElement) obj,
									NLS
											.bind(
													PublishingResources.missingIconFileWarning_msg,
													iconFile.getAbsolutePath()));
				} else {
					getHtmlBuilder()
							.getValidator()
							.logWarning(
									NLS
											.bind(
													PublishingResources.missingIconFileWarning_msg,
													iconFile.getAbsolutePath()));
				}
			}

			iconName = copyNodeIcon(iconFile,isCustomized);
		}

		if (iconName == null || iconName.length() == 0) {
			String name;
			if (obj instanceof MethodElement) {
				name = ((MethodElement) obj).getName();
			} else {
				name = obj.toString();
			}

			getHtmlBuilder().getValidator().logWarning(
					NLS.bind(PublishingResources.missingIconNameWarning_msg,
							name));
		}

		return iconName;
	}

	/**
	 * Gets the display name for a method element or iterm provider adapter.
	 * 
	 * @param obj
	 *            a method element or iterm provider adapter
	 * @return the display name or <code>null</code>
	 */
	private String getName(Object obj) {
		String name = null;
		if (obj instanceof MethodElement) {
			MethodElement e = (MethodElement) obj;

			// calculate the presentation name, for extenders, get from base if
			// needed
			name = ConfigurationHelper.getPresentationName(e, config);
			if (name == null || name.equals("")) { //$NON-NLS-1$
				name = e.getClass().getName();
				int index = name.lastIndexOf("."); //$NON-NLS-1$
				if (index >= 0) {
					name = name.substring(index + 1);
					if (name.endsWith("Impl")) { //$NON-NLS-1$
						name = name.substring(0, name.length() - 4);
					}
				}
			}
		} else if (obj instanceof ItemProviderAdapter) {
			ItemProviderAdapter provider = (ItemProviderAdapter) obj;
			name = provider.getText(obj);
		}
		return name;
	}

	/**
	 * Gets the GUID for a method element.
	 * 
	 * @param obj
	 *            a method element
	 * @return the GUID for a method element or <code>null</code>
	 */
	private String getGUID(Object obj) {
		if (obj instanceof MethodElement) {
			return ((MethodElement) obj).getGuid();
		} else
			return null;
	}

	/**
	 * Gets thr URL for a method element.
	 * 
	 * @param obj
	 *            a method element
	 * @return the URL for a method element or a blank HTML
	 */
	private String getURL(Object obj) {
		if (obj instanceof MethodElement) {
			return getLayoutMgr().getLayout((MethodElement) obj, true).getUrl();
		} else {
			// FIXME: layout needs to be provided for uncategorized and category
			// object
			return "applet//empty.htm"; //$NON-NLS-1$
		}
	}

	/**
	 * Creates a bookmark for a method element
	 * 
	 * @param element
	 *            a method element
	 * @return a <code>Bookmark</code>
	 */
	protected Bookmark createBookmark(IProgressMonitor monitor, Object element) {
		// publish this element
		if (element instanceof MethodElement) {
			// delay the publishing till the bookmarks are created.
			getHtmlBuilder().getValidator().addReferencedElement(null,
					(MethodElement) element);
		}

		// create a bookmark for this element
		String name = getName(element);
		String guid = getGUID(element);
		String url = getURL(element);
		String nodeIcon = getNodeIconName(element);

		String msg = NLS.bind(PublishingResources.generatingBookmarkTask_name,
				name);
		monitor.subTask(msg);
		return createBookmark(name, guid, url, nodeIcon, nodeIcon, element);
	}

	protected Bookmark createBookmark(String name, String guid, String url,
			String closeIcon, String openIcon, Object owner) {

		Bookmark b = new Bookmark(name);
		b.setPresentationName(name);
		b.setUniqueId(guid);
		b.setClosedIconName(closeIcon);
		b.setOpenIconName(openIcon);
		b.setFileName(url);
		b.setFromContentLibrary(true);
		b.setEnabled(true);
		b.setExist(true);
		b.setVisible(true);
		b.setTransparency(false);
		b.setDefault(true);
		b.setCurrent(false);
		b.setOwner(owner);
		return b;
	}

	/**
	 * Gets the default view.
	 * 
	 * @return a <code>Bookmark</code> that encapsulates the default view
	 */
	public Bookmark getDefaultView() {
		return defaultView;
	}

	/**
	 * Builds the views and returns a list of bookmark objects.
	 * 
	 * @param monitor
	 *            a progress monitor
	 * @return a list of <code>Bookmark</code>
	 */
	public abstract List<Bookmark> buildViews(IProgressMonitor monitor);

	/**
	 * Disposes this object.
	 */
	public void dispose() {

		config = null;
		if ( runnable != null ) {
			runnable.dispose();
			runnable = null;
		}
		
		if ( siteGenerator != null ) {
			siteGenerator.dispose();
			siteGenerator = null;
		}
	}

}
