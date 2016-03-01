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
/**
 * Implementation for ITextReferenceReplacer. Used in deep copy
 * 
 * @author Weiping Lu
 *
 */
package org.eclipse.epf.library.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.edit.util.IResourceScanner;
import org.eclipse.epf.library.edit.util.ITextReferenceReplacer;
import org.eclipse.epf.persistence.MultiFileSaveUtil;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.MethodElement;

public class TextReferenceReplacerImpl implements ITextReferenceReplacer {
	
	private static String CAPABILITY_PATH = MultiFileSaveUtil.CAPABILITY_PATTERN_PATH + "/"; //$NON-NLS-1$
	private static String DELIVERY_PATH = MultiFileSaveUtil.DELIVERY_PROCESS_PATH + "/"; //$NON-NLS-1$
	private static boolean debug = LibraryPlugin.getDefault().isDebugging();
	private final static boolean localDebug  = false;
	private static IResourceScanner resourceScanner;
	
	public IResourceScanner getResourceScanner() {
		if (resourceScanner == null) {
			resourceScanner = new ResourceScanner(null, null);
		}
		return resourceScanner;
	}
	
	private Map convertMap(Map oldToNewObjectMap) {
		if (oldToNewObjectMap == null) {
			if (localDebug) {
				System.out.println("LD> oldToNewObjectMap is null."); //$NON-NLS-1$
			}
			return null;
		}
		Map m = null;
		Iterator it = oldToNewObjectMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
			if (localDebug) {
				System.out.println("LD> old key = " + key); //$NON-NLS-1$
				System.out.println("LD> old val = " + val); //$NON-NLS-1$
			}
			if ( key instanceof MethodElement && val instanceof MethodElement) {
				String newKey = ((MethodElement)key).getGuid();
				String newVal = ((MethodElement)val).getGuid();
				if (key != null && val != null) {
					if (m == null) {
						m = new HashMap();
					}
					m.put(newKey, newVal);
				}
				if (localDebug) {
					System.out.println("LD> new key = " + newKey + ", newVal = " + newVal); //$NON-NLS-1$ //$NON-NLS-2$
					System.out.println(""); //$NON-NLS-1$
				}
			} 
		}
		return m;
	}

	/**
	 * @param source String
	 * @param owner EObject
	 * @oldToNewObjectMap Map
	 * @return String
	 */
	public String replace(String source, EObject owner, Map oldToNewObjectMap) {
		if (localDebug) {
			System.out.println("LD> Entering TextReferenceReplacerImpl.replace: " + source); //$NON-NLS-1$
			System.out.println("LD> owner: " + owner); //$NON-NLS-1$
		}
		String ret = replace_(source, owner, oldToNewObjectMap);
		if (localDebug) {
			boolean noChange = ! source.equals(ret);
			String str = source.equals(ret) ? "No chnange!" : ret; //$NON-NLS-1$
			System.out.println("LD> Exiting TextReferenceReplacerImpl.replace: " + str); //$NON-NLS-1$
			System.out.println(""); //$NON-NLS-1$
		}
		return ret;
	}
	
	private String replace_(String source, EObject owner, Map oldToNewObjectMap) {	
		Map localMap = convertMap(oldToNewObjectMap);
		if (localMap == null) {
			return source;
		}
		
		String replacingPath = null;
		String replacedPath = null;			
		if (owner instanceof DeliveryProcess) {
			replacingPath = DELIVERY_PATH;
			replacedPath = CAPABILITY_PATH;
		}
		else if (owner instanceof CapabilityPattern) {
			replacingPath = CAPABILITY_PATH;
			replacedPath = DELIVERY_PATH;
		} else {
			replacingPath = ""; //$NON-NLS-1$
			replacedPath = ""; //$NON-NLS-1$
		}
		
		StringBuffer sb = new StringBuffer();
		Matcher m = ResourceHelper.p_link_ref.matcher(source);

		while (m.find()) {
			String text = m.group();						
			Map attributeMap = ResourceHelper.getAttributesFromLink(text);
			String oldGuid = (String) attributeMap.get(ResourceHelper.TAG_ATTR_GUID);	
			String newGuid = oldGuid == null ? null : (String) localMap.get(oldGuid);
			
			if (localDebug) {
				System.out.println("LD> text:      " + text); //$NON-NLS-1$
				System.out.println("LD> attributeMap: " + attributeMap); //$NON-NLS-1$
				System.out.println("LD> oldGuid: " + oldGuid); //$NON-NLS-1$
				System.out.println("LD> newGuid: " + newGuid); //$NON-NLS-1$
			}			
			if (newGuid != null && !newGuid.equals(oldGuid)) {
								
				//String replacement = text.replaceAll(oldGuid, newGuid);
				//Can't use the above commented out line, because some guid strings are not valid regex patterns
				String replacement = replaceAll(text, oldGuid, newGuid);		
				if (replacement.indexOf(replacedPath) > 0) {
					replacement = replacement.replaceFirst(replacedPath, replacingPath);
				}
				if (localDebug) {
					System.out.println("LD> replacement: " + replacement); //$NON-NLS-1$
				}
				m.appendReplacement(sb, replacement);	
			}
			
		}
		m.appendTail(sb);
		return sb.toString();
	}
	
	/**
	 * 
	 * @param text String
	 * @param replaced String
	 * @param replacing String
	 * @return String
	 */
	public static String replaceAll(String text, String replaced, String replacing) {
		int fromIndex = 0;
		int ix = text.indexOf(replaced, fromIndex);
		if (ix < 0) {
			return text;
		}			
		int replacedLen = replaced.length();
		StringBuffer newText = new StringBuffer();
		while (ix >= fromIndex) {
			if (ix > fromIndex) {
				newText.append(text.substring(fromIndex, ix));
			}
			newText.append(replacing);
			fromIndex = ix + replacedLen;
			ix = text.indexOf(replaced, fromIndex);
		}
		if (fromIndex < text.length() ) {
			newText.append(text.substring(fromIndex, text.length()));
		}
		return newText.toString();
	}

	
	
}
