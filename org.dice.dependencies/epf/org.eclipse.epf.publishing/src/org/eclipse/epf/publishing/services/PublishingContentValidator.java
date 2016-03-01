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
package org.eclipse.epf.publishing.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.net.proxy.IProxyData;
import org.eclipse.core.net.proxy.IProxyService;
import org.eclipse.core.runtime.Platform;
import org.eclipse.epf.common.utils.Timer;
import org.eclipse.epf.library.layout.DefaultContentValidator;
import org.eclipse.epf.library.layout.LinkInfo;
import org.eclipse.epf.library.layout.util.XmlElement;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.publishing.PublishingPlugin;
import org.eclipse.epf.publishing.PublishingResources;
import org.eclipse.epf.publishing.util.http.HttpResponse;
import org.eclipse.epf.publishing.util.http.HttpUtil;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.ibm.icu.util.Calendar;

/**
 * content validator used for publishing. This class will be responsible for
 * validating the content to be published, fixing problems such as links in the
 * content, and logging information about missing elements, missing resources,
 * etc.
 * 
 * @author Jinhua Xi
 * @since 1.0
 * 
 */
public class PublishingContentValidator extends DefaultContentValidator {

	private boolean debug = PublishingPlugin.getDefault().isDebugging();

	// private boolean showExtraInfoForDescriptors = false;
	// private boolean showRelatedDescriptors = false;

	protected PublishHTMLOptions options = null;

	class InvalidExternalLinkInfo {
		public MethodElement owner;

		public String url;

		public String message;

		public InvalidExternalLinkInfo(MethodElement owner, String url,
				String message) {
			this.owner = owner;
			this.url = url;
			this.message = message;
		}
	}

	class MissingReference {
		public MethodElement owner;

		public MethodElement refElement;

		public String guid;

		public String linkedText;

		public MissingReference(MethodElement owner, MethodElement refElement) {
			this.owner = owner;
			this.refElement = refElement;
		}

		public MissingReference(MethodElement owner, String guid,
				String linkedText) {
			this.owner = owner;
			this.guid = guid;
			this.linkedText = linkedText;
		}
	}

	class MissingResource {
		public MethodElement owner;

		public File resourceFile;

		public String url;

		public MissingResource(MethodElement owner, File resourceFile,
				String url) {
			this.owner = owner;
			this.resourceFile = resourceFile;
			this.url = url;
		}
	}

	static final String LOGS_FOLDER = "logs"; //$NON-NLS-1$

	static final String ERROR_LOG_FILENAME = "error.log"; //$NON-NLS-1$

	static final String WARNING_LOG_FILENAME = "warning.log"; //$NON-NLS-1$

	static final String INFO_LOG_FILENAME = "info.log"; //$NON-NLS-1$

	static final String CORE_NET_BUNDLE = "org.eclipse.core.net"; //$NON-NLS-1$

	protected File logPath;

	protected boolean validateExternalLinks = false;

	protected List invalidExternalLinks = new ArrayList();

	// cache the valided external links to avoid multiple checking
	protected List validatedExternalLinks = new ArrayList();

	protected List missingReferences = new ArrayList();

	protected List missingResources = new ArrayList();

	protected List discardedElements = new ArrayList();

	protected List validCategories = new ArrayList();

	protected long publishing_start = 0;

	protected long time_for_external_link_checking = 0;

	// collect the elements referenced by the published contents so we can
	// publish them
	// this will be the elements to be published
	private List referencedElements = new ArrayList();

	// published elements
	private List publishedElements = new ArrayList();

	// this is the default target element for the content validator
	// set this before publishing the element and set to null after the
	// publishign is done
	protected MethodElement defaultTarget = null;

	/**
	 * consructor
	 * 
	 * @param pubDir
	 *            String
	 * @param validateExternalLinks
	 *            boolean
	 */
	public PublishingContentValidator(String pubDir, PublishHTMLOptions options) {
		super(pubDir);
		this.options = options;

		this.validateExternalLinks = options.isCheckExternalLinks();

		this.logPath = new File(pubDir, LOGS_FOLDER);
		super.info = getStream(INFO_LOG_FILENAME);
		super.warning = getStream(WARNING_LOG_FILENAME);
		super.error = getStream(ERROR_LOG_FILENAME);

		// set the start time
		publishing_start = Calendar.getInstance().getTimeInMillis();
	}

	/**
	 * dispose the object
	 */
	public void dispose() {
		invalidExternalLinks.clear();
		validatedExternalLinks.clear();
		missingReferences.clear();
		missingResources.clear();
		discardedElements.clear();
		validCategories.clear();

		referencedElements.clear();
		publishedElements.clear();

		info.close();
		warning.close();
		error.close();

	}

	protected PrintStream getStream(String fileName) {
		try {
			File f = new File(logPath, fileName);
			File dir = f.getParentFile();
			dir.mkdirs();

			if (!f.exists()) {
				f.createNewFile();
			}

			return new PrintStream(new FileOutputStream(f), true);
		} catch (Exception e) {

		}

		return null;
	}

	private IProxyService getProxyService() {
		Bundle bundle = Platform.getBundle(CORE_NET_BUNDLE);
		if (bundle != null) {
			BundleContext ctx = bundle.getBundleContext();
			String name = IProxyService.class.getName();
			ServiceReference ref = ctx.getServiceReference(name);
			if (ref != null)
				return (IProxyService) bundle.getBundleContext()
						.getService(ref);
		}
		return null;
	}

	/**
	 * validate the link attributes fro the element.
	 * 
	 * @param owner
	 *            MethodElement the owner element
	 * @param attributes
	 *            String the attributes in the link
	 * @param text
	 *            String the text allow with the link
	 * @param config
	 *            MethodConfiguration
	 * 
	 * @return LinkInfo
	 */
	public LinkInfo validateLink(MethodElement owner, String attributes,
			String text, MethodConfiguration config, String tag) {
		LinkInfo info = super
				.validateLink(owner, attributes, text, config, tag);

		if (validateExternalLinks) {

			String url = info.getUrl();
			if ((url != null) && ResourceHelper.isExternalLink(url)
					&& !url.startsWith("ftp://")) //$NON-NLS-1$
			{
				if (!validatedExternalLinks.contains(url)) {
					Timer t = new Timer();
					IProxyService proxyService = getProxyService();

					if (proxyService.isProxiesEnabled()) {
						IProxyData proxy = null;
						boolean accessible = false;
						boolean useProxy = false;
						String exceptionMessage = null;
						String[] proxyTypes = { IProxyData.HTTP_PROXY_TYPE,
								IProxyData.HTTPS_PROXY_TYPE,
								IProxyData.SOCKS_PROXY_TYPE };
						for (String proxyType : proxyTypes) {
							// access external link by using different proxys
							proxy = proxyService.getProxyData(proxyType);
							if ((proxy.getHost()==null)||proxy.getPort()==-1)
							{
								continue;
							}
							try {
								HttpResponse resp = HttpUtil.doGet(url, null,
										6000, proxy); // timeout
								useProxy = true;
								accessible = true;
								break;
							} catch (java.net.UnknownHostException e) {
							} catch (Exception e) {
								if (exceptionMessage == null)
								{
									exceptionMessage = proxy.getHost() + ":" //$NON-NLS-1$
											+ proxy.getPort() + "[" + proxyType //$NON-NLS-1$
											+ "]:" + e.getMessage(); //$NON-NLS-1$
								} else {
									exceptionMessage += ";" + proxy.getHost() //$NON-NLS-1$
											+ ":" + proxy.getPort() + "["		//$NON-NLS-1$ //$NON-NLS-2$
											+ proxyType + "]:" + e.getMessage(); //$NON-NLS-1$
								}
							}
						}
						if (useProxy)
						{
							if (!accessible) {
								if (exceptionMessage != null) {
									logInvalidExternalLink(owner, url,
											exceptionMessage);
								} else {
									logInvalidExternalLink(owner, url, null);
								}
							}
						}
						else	// connect directly
						{
							try {
								HttpResponse resp = HttpUtil.doGet(url, null, 6000); // timeout
							} catch (java.net.UnknownHostException e) {
								logInvalidExternalLink(owner, url, null);
							} catch (Exception e) {
								logInvalidExternalLink(owner, url, e.getMessage());
							}
						}
						
					} else {
						try {
							HttpResponse resp = HttpUtil.doGet(url, null, 6000); // timeout
							// System.out
							// .println(time
							// + " mini-seconds querying Url '" + url + "',
							// return
							// status=" + resp.getStatus()); //$NON-NLS-1$
							// //$NON-NLS-2$
						} catch (java.net.UnknownHostException e) {
							logInvalidExternalLink(owner, url, null);
						} catch (Exception e) {
							logInvalidExternalLink(owner, url, e.getMessage());
						}
					}

					t.stop();
					time_for_external_link_checking += t.getTime();

					// cache it
					validatedExternalLinks.add(url);

					// do we need to log the info so that user know what
					// external
					// urls are referenced in the content?
					logInfo(owner, NLS.bind(
							PublishingResources.externalUrl_msg, new Object[] {
									new Integer(t.getTime()), url }));
				}

			}
		}
		return info;
	}

	/**
	 * log missing reference.
	 * 
	 * @param owner
	 *            MethodElement
	 * @param refElement
	 *            MethodElement the missing element
	 */
	public void logMissingReference(MethodElement owner,
			MethodElement refElement) {
		super.logMissingReference(owner, refElement);
		missingReferences.add(new MissingReference(owner, refElement));
	}

	/**
	 * log missing reference
	 * 
	 * @param owner
	 *            M<ethodElement
	 * @param guid
	 *            String the guid of the missing element
	 * @param linkedText
	 *            String the linked text.
	 */
	public void logMissingReference(MethodElement owner, String guid,
			String linkedText) {
		super.logMissingReference(owner, guid, linkedText);
		missingReferences.add(new MissingReference(owner, guid, linkedText));
	}

	/**
	 * log missing resource.
	 * 
	 * @param owner
	 *            MethodElement
	 * @param resourceFile
	 *            File
	 * @param url
	 *            String
	 */
	public void logMissingResource(MethodElement owner, File resourceFile,
			String url) {
		super.logMissingResource(owner, resourceFile, url);
		missingResources.add(new MissingResource(owner, resourceFile, url));
	}

	/**
	 * log invalid external link
	 * 
	 * @param owner
	 * @param url
	 *            String
	 * @param message
	 *            String
	 */
	public void logInvalidExternalLink(MethodElement owner, String url,
			String message) {
		super.logInvalidExternalLink(owner, url, message);
		invalidExternalLinks.add(new InvalidExternalLinkInfo(owner, url,
				message));
	}

	/**
	 * get report about the content validation.
	 * 
	 * @return XmlElement
	 */
	public XmlElement getReport() {
		XmlElement reportXml = new XmlElement("validatorInfo"); //$NON-NLS-1$

		if (invalidExternalLinks.size() > 0) {
			String msg = time_for_external_link_checking / 1000
					+ " seconds validating external links"; //$NON-NLS-1$
			System.out.println(msg);
			logInfo(msg);

			XmlElement invalidExternalLinksXml = reportXml
					.newChild("invalidExternalLinks"); //$NON-NLS-1$
			for (Iterator it = invalidExternalLinks.iterator(); it.hasNext();) {
				InvalidExternalLinkInfo info = (InvalidExternalLinkInfo) it
						.next();
				invalidExternalLinksXml
						.newChild("entry") //$NON-NLS-1$
						.setAttribute("url", info.url) //$NON-NLS-1$
						.setAttribute(
								"owner", (info.owner == null) ? "" : LibraryUtil.getLocalizeTypeName(info.owner)) //$NON-NLS-1$ //$NON-NLS-2$
						.setAttribute("message", info.message); //$NON-NLS-1$
			}
		}

		if (missingReferences.size() > 0) {
			XmlElement invalidReferencesXml = reportXml
					.newChild("invalidReferences"); //$NON-NLS-1$
			XmlElement missingReferencesXml = reportXml
					.newChild("missingReferences"); //$NON-NLS-1$
			for (Iterator it = missingReferences.iterator(); it.hasNext();) {
				MissingReference info = (MissingReference) it.next();
				if (info.refElement == null) {
					invalidReferencesXml
							.newChild("entry") //$NON-NLS-1$
							.setAttribute("element", info.linkedText) //$NON-NLS-1$
							.setAttribute("guid", info.guid) //$NON-NLS-1$
							.setAttribute(
									"owner", (info.owner == null) ? "" : LibraryUtil.getLocalizeTypeName(info.owner)); //$NON-NLS-1$ //$NON-NLS-2$
				} else {
					missingReferencesXml
							.newChild("entry") //$NON-NLS-1$
							.setAttribute(
									"element", (info.refElement == null) ? "" : LibraryUtil.getLocalizeTypeName(info.refElement)) //$NON-NLS-1$ //$NON-NLS-2$
							.setAttribute("guid", info.refElement.getGuid()) //$NON-NLS-1$
							.setAttribute(
									"owner", (info.owner == null) ? "" : LibraryUtil.getLocalizeTypeName(info.owner)); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
		}

		if (missingResources.size() > 0) {
			XmlElement missingResourcesXml = reportXml
					.newChild("missingResources"); //$NON-NLS-1$
			for (Iterator it = missingResources.iterator(); it.hasNext();) {
				MissingResource info = (MissingResource) it.next();
				missingResourcesXml
						.newChild("entry") //$NON-NLS-1$
						.setAttribute("url", info.url) //$NON-NLS-1$
						.setAttribute(
								"resource", (info.resourceFile == null) ? "" : info.resourceFile.getPath()) //$NON-NLS-1$ //$NON-NLS-2$
						.setAttribute(
								"owner", (info.owner == null) ? "" : LibraryUtil.getLocalizeTypeName(info.owner)); //$NON-NLS-1$ //$NON-NLS-2$

			}
		}

		long publishing_time = (Calendar.getInstance().getTimeInMillis() - publishing_start) / 1000;
		int minutes = (int) publishing_time / 60;
		int seconds = (int) publishing_time - minutes * 60;

		logInfo("Publishing time: " + minutes + " minutes " + seconds + " seconds"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		return reportXml;
	}

	/**
	 * add a category that should be published.
	 * 
	 * @param e
	 *            ContentCategory
	 */
	public void addValidCategory(ContentCategory e) {
		if (!validCategories.contains(e)) {
			validCategories.add(e);
		}
	}

	/**
	 * check if the element is discarded or not discarded elements will be
	 * treated as out side the configursation
	 * 
	 * @param owner
	 *            MethodElement the owner of the element
	 * @param feature
	 *            Object EStructuralFeature or OppositeFeature
	 * @param e
	 *            MethodElement the element to be checked
	 * 
	 * @return boolean
	 */
	public boolean isDiscarded(MethodElement owner, Object feature,
			MethodElement e) {
		if (discardedElements.contains(e)) {
			return true;
		}

		// if the element is a ContentCategory and is not discarded
		if (e instanceof ContentCategory) {
			if (validCategories.contains(e)) {
				return false;
			}

			// otherwise, check if it should be discarded or not
			// TODO
			// for now, discard all referenced content categories if they are
			// not included in the publishing view.
			// NO!!!!!!!!!!!! This will lead to a lot of broken links and
			// missing element
			// TOO strong limitation. Let open it for now
			// Publishing:Overview page in published website
			// have broken links to BM
			// return true;
			return false;
		}
		return false;
	}

	/**
	 * add a referenced element
	 * 
	 * @param owner
	 *            MethodElement
	 * @param e
	 *            MethodElement
	 */
	public void addReferencedElement(MethodElement owner, MethodElement e) {
		if (e == null) {
			return;
		}
								
		// don't add discarded elements
		if (isDiscarded(owner, null, e)) {
			if (debug) {
				System.out
						.println("Element is discarded: " + LibraryUtil.getLocalizeTypeName(e)); //$NON-NLS-1$
			}
			return;
		}

		if (e != null && !referencedElements.contains(e)
				&& !publishedElements.contains(e)) {
			referencedElements.add(e);
			logReference(owner, e);
		}
	}

	/**
	 * log a refernece
	 * 
	 * @param owner
	 *            MethodElement
	 * @param e
	 *            MethodElement
	 */
	public void logReference(MethodElement owner, MethodElement e) {
		if (debug) {
			System.out
					.println("--- Referenece Element Added: " + LibraryUtil.getLocalizeTypeName(e)); //$NON-NLS-1$
		}
	}

	/**
	 * remove element from referenced list
	 * 
	 * @param e
	 *            MethodElement
	 */
	public void removeReferencedElement(MethodElement e) {
		if (referencedElements.contains(e)) {
			referencedElements.remove(e);
			if (debug) {
				System.out
						.println("--- Reference Element Removed: " + LibraryUtil.getLocalizeTypeName(e)); //$NON-NLS-1$
			}
		}
	}

	/**
	 * get all the referenced elements
	 * 
	 * @return List
	 */
	public List getReferencedElements() {
		return referencedElements;
	}

	/**
	 * aet the discarded element for this publication. If an element is
	 * discarded, it should not be published and link to it should be link to
	 * mising element page
	 * 
	 * @param e
	 *            MethodElement
	 */
	public void setDiscardedElement(MethodElement e) {

		if (e == null) {
			return;
		}

		if (!discardedElements.contains(e)) {
			discardedElements.add(e);
		}

		// if th fdiscarded element is in the reference list, remove it as well
		removeReferencedElement(e);
	}

	/**
	 * check if an elenment is referenced or not.
	 * 
	 * @param e
	 *            MethodElement
	 * @return boolean
	 */
	public boolean isReferencedElement(MethodElement e) {
		return (e != null) && referencedElements.contains(e);
	}

	/**
	 * get the published elements
	 * 
	 * @return List
	 */
	public List getPublishedElements() {
		return publishedElements;
	}

	/**
	 * set the default target for the referenced elements
	 * 
	 * @param target
	 *            MethodElement
	 */
	public void setTargetElement(MethodElement target) {
		this.defaultTarget = target;
	}

	/**
	 * check if there is a closure or not
	 * 
	 * @return boolean
	 */
	public boolean hasClosure() {
		return false;
	}

	/**
	 * check if an element is in closure or not.
	 * 
	 * @param e
	 *            MethodElement
	 * @return boolean
	 */
	public boolean inClosure(MethodElement e) {
		return true;
	}

	/**
	 * add elements to closure
	 * 
	 * @param items
	 *            List
	 */
	public void addClosureElements(List items) {
		// do nothing
	}

	/**
	 * make a closure
	 * 
	 */
	public void makeElementClosure(MethodConfiguration config) {
		// do nothing
	}

	public boolean showBrokenLinks() {
		return !options.isConvertBrokenLinks();
	}

	/**
	 * get the flag on whether to show extra descriptor info. If true,
	 * information from linked element will be included in the descriptor page.
	 * 
	 * @return boolean
	 */
	public boolean showExtraInfoForDescriptors() {
		return options.isShowMethodContentInDescriptors();
	}

	/**
	 * show descriptors on method element page
	 */
	public boolean showRelatedDescriptors() {
		return options.showRelatedDescriptors;
	}

	/**
	 * get the tab id for the activity layout
	 * 
	 * @return String
	 */
	public String getDefaultActivityTab() {
		return options.getDefaultActivityTab();
	}
	
	@Override
	public boolean showLinkedPageForDescriptor() {
		return options.isShowLinkedPageForDescriptor();
	}
	
}
