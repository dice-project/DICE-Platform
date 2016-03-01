/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.xml.uma.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.impl.XMLSaveImpl;

/**
 * This class customize the XMLSaveImpl to save the string tags as CDATA
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public class UmaXMLSaveImpl extends XMLSaveImpl {

	public static List cdataFeatureNames = new ArrayList();
	static {
		
		cdataFeatureNames.add("alternatives");
		cdataFeatureNames.add("mainDescription");
		cdataFeatureNames.add("howToStaff");
		cdataFeatureNames.add("keyConsiderations");
		cdataFeatureNames.add("purpose");	
		cdataFeatureNames.add("scope");					// ProcessDescription
		cdataFeatureNames.add("usageNotes");			// ProcessDescription
	}
	
	private boolean isCDATAFeature(EStructuralFeature f, boolean isAttribute) {
		
		if ( isAttribute ) {
			return false;
		}
		
//		if ( f == UmaPackage.eINSTANCE.getContentDescription_KeyConsiderations() 
//			|| f == UmaPackage.eINSTANCE.getContentDescription_MainDescription()
//			|| f == UmaPackage.eINSTANCE.getSection_Description()
//		) {
//			System.out.println("test");
//		}
		
		return cdataFeatureNames.contains(f.getName());
	}
	
	/**
	 * 
	 * @param helper
	 */
	public UmaXMLSaveImpl(XMLHelper helper) {
		super(helper);
	}

	/**
	 * 
	 * @param options Map
	 * @param helper XMLHelper
	 * @param encoding String
	 */
	public UmaXMLSaveImpl(Map options, XMLHelper helper, String encoding) {
		super(options, helper, encoding);
	}

	protected String getDatatypeValue(Object value, EStructuralFeature f,
			boolean isAttribute) {	
		
		if ( value == null || value.toString().equals("") ) {
			return "";
		}				
		
		if ( isCDATAFeature(f, isAttribute) ) {
			String str = value.toString();
			String replaced = "]]>";								//$NON-NLS-1$
			if (str != null && str.indexOf(replaced) >= 0) {
				str = str.replaceAll(replaced, "]]]]><![CDATA[>" );	//$NON-NLS-1$	//175395
			}
			return "<![CDATA[" + str + "]]>";
		}
		
		return super.getDatatypeValue(value, f, isAttribute);
	}
	
}
