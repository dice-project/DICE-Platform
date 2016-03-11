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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.epf.library.ILibraryResourceManager;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.layout.util.XmlElement;
import org.eclipse.epf.library.util.ContentResourceScanner;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.TermDefinition;


/**
 * Holds all glossary items and sort them in desired order grouped by first
 * letter of the glossary name TODO: Add a brief description for this class.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class GlossaryList {

	// using the default order from the configuration explorer.
	// maybe a special sorted list later,
	// TreeMap of the first letter of the glossary map to a (Sorted) List of
	// glossary Items
	private TreeMap itemMap = new TreeMap();

	private String pubDir;
	
	/**
	 * default constructor
	 *
	 */
	public GlossaryList(String pubDir) {
		this.pubDir = pubDir;
	}

	/**
	 * initialize the list
	 *
	 */
	public void clear() {
		itemMap.clear();
	}

	/**
	 * add a TermDefinition into the glossary list
	 * 
	 * @param element TermDefinition
	 */
	public void add(TermDefinition element) {
		String name = TngUtil.getPresentationName(element);
		String group = "" + name.charAt(0); //$NON-NLS-1$
		getItemList(group).add(element);
	}

	private List getItemList(String group) {
		group = group.toUpperCase();
		List l = (List) itemMap.get(group);
		if (l == null) {
			l = new ArrayList();
			itemMap.put(group, l);
		}

		return l;
	}

	/**
	 * get the xml document for the list
	 * 
	 * @return StringBuffer
	 */
	public StringBuffer getXml() {
		XmlElement xe = new XmlElement("Glossary"); //$NON-NLS-1$

		// set the language attribute
		Locale locale = Locale.getDefault();
		String lang = locale.getLanguage();
		xe.setAttribute("lang", lang); //$NON-NLS-1$

		for (Iterator it = itemMap.entrySet().iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			String group = (String) entry.getKey();
			List items = (List) entry.getValue();

			// create a bookmark for the group
			xe.newChild("item") //$NON-NLS-1$
					.setAttribute("name", group) //$NON-NLS-1$
					.setAttribute("presentationName", group) //$NON-NLS-1$
					.setAttribute("navigation-mark", "true") //$NON-NLS-1$ //$NON-NLS-2$
					.setAttribute("content", ""); //$NON-NLS-1$ //$NON-NLS-2$

			// then create entry for each item
			for (Iterator itItem = items.iterator(); itItem.hasNext();) {
				TermDefinition element = (TermDefinition) itItem.next();
				String name = element.getName();
				String presentationName = TngUtil.getPresentationName(element);
				String content = element.getPresentation().getMainDescription();

				// scan the content and copy images if any
				scanContentForResources(element, content, ResourceHelper.getElementPath(element));

				// fix the links in the content
				content = ResourceHelper.fixContentUrlPath(content,
						ResourceHelper.getElementPath(element),
						GlossaryBuilder.GLOSSARY_BACKPATH);
				
				xe.newChild("item") //$NON-NLS-1$
						.setAttribute("name", name) //$NON-NLS-1$
						.setAttribute("presentationName", presentationName) //$NON-NLS-1$
						.setAttribute("content", content); //$NON-NLS-1$
			}
		}

		return xe.toXml();
	}

	/**
	 * Scans the content for resource references.
	 */
	private void scanContentForResources(MethodElement owner, String content,
			String contentPath) {
		ContentResourceScanner scanner = getScanner(owner);
		if ( scanner != null ) {
			scanner.resolveResources(owner, content, contentPath);
		}
	}
	
	/**
	 * Returns the content resource scanner for the element.
	 */
	private ContentResourceScanner getScanner(MethodElement owner) {
		ILibraryResourceManager resMgr = ResourceHelper.getResourceMgr(owner);
		if ( resMgr == null ) {
			return null;
		}
		
		String rootContentPath = resMgr.getLogicalPluginPath(owner);
		File src_root = new File(resMgr.getPhysicalPluginPath(owner));
		File tgt_root = new File(this.pubDir, rootContentPath);
		ContentResourceScanner scanner = new ContentResourceScanner(src_root, tgt_root, rootContentPath, null);

		return scanner;
	}
	
}
