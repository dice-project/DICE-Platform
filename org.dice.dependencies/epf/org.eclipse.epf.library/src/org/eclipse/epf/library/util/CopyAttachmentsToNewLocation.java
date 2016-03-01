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
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.common.utils.NetUtil;
import org.eclipse.epf.library.ILibraryResourceManager;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.LibraryResources;
import org.eclipse.epf.library.edit.command.MethodElementAddCommand.BasicResourceManager;
import org.eclipse.epf.library.edit.meta.IMetaDef;
import org.eclipse.epf.library.edit.meta.TypeDefUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.layout.IContentValidator;
import org.eclipse.epf.persistence.FileManager;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.GuidanceDescription;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.util.ExtendedAttribute;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.osgi.util.NLS;

/**
 * utility class to copy attachmeents to another location
 * @author Jeff Hardy
 * @sicne 1.0
 *
 */
public class CopyAttachmentsToNewLocation extends BasicResourceManager {

	private Collection modifiedResourceSet;

	private boolean debug;

	private Map elementToOldPluginMap;

	private MethodPlugin lastOldPlugin = null;

	public CopyAttachmentsToNewLocation() {
		debug = LibraryPlugin.getDefault().isDebugging();
	}

	/**
	 * Copy attachments within a library, from one plugin to another
	 */
	public Collection copyReferencedContents(Collection elements,
			Map elementToOldPluginMap) {
		lastOldPlugin = null;
		modifiedResourceSet = new HashSet();
		if (elementToOldPluginMap == null) {
			return modifiedResourceSet;
		}
		this.elementToOldPluginMap = elementToOldPluginMap;

		Iterator iter = elements.iterator();
		while (iter.hasNext()) {
			Object o = iter.next();
			if (o instanceof Resource) {
				Resource r = (Resource) o;
				Iterator resourceIter = r.getContents().iterator();
				while (resourceIter.hasNext()) {
					EObject e = (EObject) resourceIter.next();
					HandleAttachmentsPlugin(e);
				}
			} else if (o instanceof EObject) {
				EObject e = (EObject) o;
				HandleAttachmentsPlugin(e);
			}
		}
		return modifiedResourceSet;
	}

	private void HandleAttachmentsPlugin(EObject e) {
		// handle self
		MethodElement elementToProcess = null;
		// find the old plugin, if there is one
		if (elementToOldPluginMap != null
				&& (MethodPlugin) elementToOldPluginMap.get(e) != null) {
			lastOldPlugin = (MethodPlugin) elementToOldPluginMap.get(e);
		}

		if (lastOldPlugin != null) {
			if (e instanceof DescribableElement) {
				DescribableElement de = (DescribableElement) e;

				// Custom icons
				try {
					// shape icon
					java.net.URI shapeIconUri = de.getShapeicon();
					if (shapeIconUri != null) {
						// To handle the shapeIcon/nodeIcon uri got changed, its relative to pluginPath.
						java.net.URI newShapeIconUri = handleIconURI(de,
								shapeIconUri);
						if (newShapeIconUri != null) {
							de.setShapeicon(newShapeIconUri);
							modifiedResourceSet.add(de.eResource());
						}
					}

					// node icon
					java.net.URI nodeIconUri = de.getNodeicon();
					if (nodeIconUri != null) {
						java.net.URI newNodeIconUri = handleIconURI(de,
								nodeIconUri);
						if (newNodeIconUri != null) {
							de.setNodeicon(newNodeIconUri);
							modifiedResourceSet.add(de.eResource());
						}
					}
				} catch (Exception ex) {
					LibraryPlugin.getDefault().getLogger().logError(ex);
				}
				HandleAttachmentsPlugin(de.getPresentation());
				return;
			} else if (e instanceof ContentDescription) {
				elementToProcess = (ContentDescription) e;
			} else if (e instanceof MethodElement) {
				elementToProcess = (MethodElement) e;
			} else {
				if (debug) {
					System.out
							.println("CopyAttachmentsToNewLocation.HandleAttachmentsPlugin: unknown type " + e); //$NON-NLS-1$
				}
			}

			if (elementToProcess != null
					&& elementToProcess.eResource() != null) {
				// set up ContentResourceScanner for this element
				// give ContentResourceScanner same directory for sourceRootPath
				// and targetRootPath
//				ILibraryManager manager = (ILibraryManager) LibraryService
//						.getInstance().getCurrentLibraryManager();
//				
//				URI elementURI = manager != null ? manager.getElementRelativeURI(elementToProcess) : null;
//				if (elementURI == null || elementURI.segmentCount() < 1)
//					return;
				
				
				ILibraryResourceManager resMgr = ResourceHelper.getResourceMgr(elementToProcess);
				ILibraryResourceManager lastOldPluginResMgr = ResourceHelper.getResourceMgr(lastOldPlugin);
				
				ContentResourceScanner scanner = getScanner(elementToProcess, lastOldPlugin);

				// iterate thru element's content
				String contentPath = ResourceHelper
						.getElementPath((MethodElement) elementToProcess);
				if (contentPath == null || contentPath.length() == 0) {
					LibraryPlugin
							.getDefault()
							.getLogger()
							.logWarning(
									NLS.bind(LibraryResources.CopyAttachmentsToNewLocation_log_noContentPath, (new Object[] { ((MethodElement) elementToProcess).getName() }))); 
					return;
				}

				// FIXME: this is not always correct this relative of the element's folder might have been changed
				// during move/copy
				String oldContentPath = contentPath.replaceFirst(UmaUtil
						.getMethodPlugin(elementToProcess).getName(),
						lastOldPlugin.getName());
				
				//Commented out the following and rolled back to the above to fix a bug
				//with attached file in content paste. But why it was introduced? Without it
				//the shape and node icon appear to be working fine.
				//String oldContentPath = ResourceHelper
				//	.getElementPath(lastOldPlugin);				

//				Iterator iter = elementToProcess.eClass().getEAllAttributes()
//						.iterator();
				Iterator iter = TypeDefUtil.getInstance().getEAllAttributes(elementToProcess).iterator();
				while (iter.hasNext()) {
					EAttribute attrib = (EAttribute) iter.next();
//					Object o = elementToProcess.eGet(attrib);
					Object o = TypeDefUtil.getInstance().eGet(elementToProcess, attrib);
					if (o instanceof String) {
						String content = (String) o;
						if (content.length() > 0) {
							String newContent = scanner.resolveResourcesPlugin(
									content, contentPath, oldContentPath);
							if (newContent != null
									&& newContent.trim().length() != 0
									&& !content.equals(newContent)) {
//								elementToProcess.eSet(attrib, newContent);
								TypeDefUtil.getInstance().eSet(elementToProcess, attrib, newContent);
								modifiedResourceSet.add(elementToProcess
										.eResource());
							}
							
							ExtendedAttribute eAtt = TypeDefUtil.getInstance().getAssociatedExtendedAttribute(attrib);
							if (eAtt != null && IMetaDef.attachment.equalsIgnoreCase(eAtt.getValueType())) {
								processAttachmentString(elementToProcess, resMgr,
										lastOldPluginResMgr, oldContentPath, newContent);
							}
						}
					}
				}

				// Template attachments
//				String libRoot = ResourceHelper.getVirtualLibraryRoot(elementToProcess);
				if (elementToProcess instanceof GuidanceDescription) {
					String attachmentString = ((GuidanceDescription) elementToProcess)
							.getAttachments();
					processAttachmentString(elementToProcess, resMgr,
							lastOldPluginResMgr, oldContentPath,
							attachmentString);
				}
			}
		}

		// handle children
		//Iterator iter = e.eAllContents();
		if (e.eContents() == null) {
			return;
		}
		Iterator iter = e.eContents().iterator();
		while (iter.hasNext()) {
			EObject child = (EObject) iter.next();
			HandleAttachmentsPlugin(child);
		}
	}

	private void processAttachmentString(MethodElement elementToProcess,
			ILibraryResourceManager resMgr,
			ILibraryResourceManager lastOldPluginResMgr, String oldContentPath,
			String attachmentString) {
		Iterator iter;
		List attachmentList = TngUtil
				.convertGuidanceAttachmentsToList(attachmentString);
		for (iter = attachmentList.iterator(); iter.hasNext();) {
			String attachment = (String) iter.next();
			if (attachment != null
					&& attachment.trim().length() > 0) {
				Matcher m = ResourceHelper.p_template_attachment_url.matcher(attachment);
				if (!m.find()) {
					File srcContentPath = new File(lastOldPluginResMgr.resolve(lastOldPlugin, oldContentPath));
					File srcFile = new File(srcContentPath,
							attachment);
					File tgtContentPath = new File(resMgr.getPhysicalPath(elementToProcess));
					File tgtFile = new File(tgtContentPath,
							attachment);
					if (!tgtFile.exists()) {
						FileManager.copyFile(srcFile, tgtFile);
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param de
	 * @param iconUri
	 * @return the new Icon URI (relative path, so its ready to be persisted to the model)
	 * @throws URISyntaxException
	 */
	private java.net.URI handleIconURI(DescribableElement de,
			java.net.URI iconUri) throws URISyntaxException {
		java.net.URI srcUri = TngUtil.getFullPathURI(de,
				iconUri);
		String iconPath = NetUtil.decodedFileUrl(iconUri.getPath());
		
		if(iconPath.indexOf(lastOldPlugin.getName()) < 0){
			srcUri = new File(ResourceHelper.getPluginPath(lastOldPlugin) + File.separator + iconPath).toURI();
		}
		File srcFile = new File(srcUri);
		java.net.URI tgtUri = new File(ResourceHelper.getPluginPath(de) + File.separator + iconPath).toURI();
		File tgtFile = new File(tgtUri);
		if (!tgtFile.exists()) {
			FileManager.copyFile(srcFile, tgtFile);
		}
		java.net.URI newIconUri = new java.net.URI(NetUtil
				.encodeFileURL(FileUtil.getRelativePath(
						tgtFile, new File(ResourceHelper.getPluginPath(de)))));
		return newIconUri;
	}

	/**
	 * Returns the content resource scanner for the element.
	 */
	private ContentResourceScanner getScanner(MethodElement owner, MethodPlugin sourcePlugin) {
		ILibraryResourceManager resMgr = ResourceHelper.getResourceMgr(owner);
		if ( resMgr == null ) {
			return null;
		}
		
		String rootContentPath = resMgr.getLogicalPluginPath(owner);
//		File src_root = new File(resMgr.getPhysicalPluginPath(owner));
//		File tgt_root = src_root;
		File tgt_root = new File(resMgr.getPhysicalPluginPath(owner));
		File src_root = new File(resMgr.getPhysicalPluginPath(sourcePlugin));
		
		ContentResourceScanner scanner = new ContentResourceScanner(src_root, tgt_root, rootContentPath, getValidator());

		return scanner;
	}
	
	public IContentValidator getValidator() {
		return null;
	}

	
	/**
	 * Copy all attachments from one library to another
	 * 
	 * @param oldLibDir
	 *            Original library root
	 * @param newLibDir
	 *            New library root
	 * @param xmiList
	 *            list of xmi File's (pass null to process all files in
	 *            newLibDir)
	 * @param monitor
	 *            optional (can be null) IProgressMonitor
	 */
	public static void copyAttachmentsLibrary(String oldLibDir,
			String newLibDir, List xmiList, IProgressMonitor monitor) {

		// get list of .xmi files in newLibDir
		if (xmiList == null)
			xmiList = FileUtil.fileList(new File(newLibDir), "xmi"); //$NON-NLS-1$

		if (monitor != null)
			monitor.subTask(LibraryResources.copyingAttachmentsTask_name);

		Iterator iter = xmiList.iterator();
		while (iter.hasNext()) {
			if (monitor != null)
				monitor.worked(1);
			File xmiFile = (File) iter.next();
			if (xmiFile.exists()) {
				// parse xmi file
				SAXParserXMI parser = new SAXParserXMI(null);
				parser.parse(xmiFile.getAbsolutePath());
				// iterate thru it's elements, checking content for attachments
				ContentResourceScanner scanner = new ContentResourceScanner(
						new File(oldLibDir), new File(newLibDir), null);
				HandleAttachments(parser.xmiMap, scanner, xmiFile.getParent()
						.substring(newLibDir.length()));
			}
		}
	}

	private static void HandleAttachments(HashMap eMap,
			ContentResourceScanner scanner, String contentPath) {
		// handle self
		String content = SAXParserXMI.getElementContent(eMap);
		if (content != null) {
			// scan content for attachments
			scanner.resolveResources(content, contentPath);
		}

		// handle children
		Iterator iter = ((ArrayList) eMap.get("children")).iterator(); //$NON-NLS-1$
		while (iter.hasNext()) {
			HashMap childMap = (HashMap) iter.next();
			HandleAttachments(childMap, scanner, contentPath);
		}
	}

}
