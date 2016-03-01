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
package org.eclipse.epf.library.edit.util;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.util.UserDefinedTypeMeta;

/**
 * Utility class for debug purpose
 * 
 * @author Weiping Lu
 * @since 1.2
 */
public final class DebugUtil {

	public static final boolean uiDebug = LibraryEditPlugin.getDefault().debug("UiDebug");			//$NON-NLS-1$
	public static final boolean udtDebug = LibraryEditPlugin.getDefault().debug("UdtDebug");		//$NON-NLS-1$

	private static PrintStream output;
	private static String prompt = "LD> "; 					//$NON-NLS-1$
	
	public static void setOutput(PrintStream op) {
		output = op;
	}
	
	public static void setPrompt(String pt) {
		prompt = pt;
	}
	
	private static PrintStream out() {
		if (output != null) {
			return output;
		}
		return System.out;
	}
	
	public static void print(String msg) {
		out().println(prompt + msg);
	}
	
	public static void print() {
		out().println("");	//$NON-NLS-1$ 
	}
	
	public static void print(Collection<? extends MethodElement> elements) {
		out().println(prompt + "elements: " + (elements == null ? "null" : elements.size()));//$NON-NLS-1$ //$NON-NLS-2$
		if (elements == null || elements.isEmpty()) {
			return;
		}
		for (MethodElement element : elements) {
			out().println(prompt + element);
		}
		out().println("");//$NON-NLS-1$
	}
	
	public static void print(String beginTitle, String endTitle, Collection<? extends MethodElement> elements, int ix) {
		if (beginTitle != null) {
			String str = "";				//$NON-NLS-1$
			if (elements == null) {
				str = "null";				//$NON-NLS-1$
			} else if (elements.isEmpty()) {
				str = elements.toString();
			}
			out().println(prompt + beginTitle + str);
		}
		
		if (elements == null) {
			return;
		}
		for (Iterator<? extends MethodElement> it = elements.iterator(); it.hasNext();) {
			MethodElement element = it.next();
			out().println(prompt + toString(element, ix));
		}
		
		if (endTitle != null) {
			out().println(prompt + endTitle);
		}
		out().println("");			//$NON-NLS-1$
	}
	
	public static String toString(MethodElement element, int ix) {
		if (element == null) {
			return "null";//$NON-NLS-1$
		}
		if (ix == 1) {
			return TngUtil.getLabelWithPath(element);
		} 
		if (ix == 2) {
			String typeName = element.eClass().getName();
			if (element instanceof Practice) {
				UserDefinedTypeMeta meta = PracticePropUtil.getPracticePropUtil().getUdtMeta((Practice) element);
				if (meta != null) {
					typeName = meta.getRteNameMap().get(UserDefinedTypeMeta._typeName);
				}
			}			
			return typeName + ", " +  //$NON-NLS-1$
					element.getGuid() + ", " + TngUtil.getLabelWithPath(element);//$NON-NLS-1$ 
		}
		return element.toString();
	}	
}