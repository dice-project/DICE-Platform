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

import java.io.IOException;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLSave;
import org.eclipse.emf.ecore.xmi.impl.XMLHelperImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.epf.xml.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * @see org.eclipse.epf.xml.uma.util.UmaResourceFactoryImpl
 * @generated
 */
public class UmaResourceImpl extends XMLResourceImpl {
	/**
	 * Creates an instance of the resource.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param uri the URI of the new resource.
	 * @generated
	 */
	public UmaResourceImpl(URI uri) {
		super(uri);
	}

	////////////////////////////////////////////////////////////////////////
	// customization here, Jinhua Xi 04/28/2006
	////////////////////////////////////////////////////////////////////////
	
	/*
	 * Javadoc copied from interface.
	 */
	public void save(Map options) throws IOException {
		super.save(options);
	}

	protected XMLSave createXMLSave() {
		// return super.createXMLSave();
		return new UmaXMLSaveImpl(createXMLHelper());
	}
		
	protected XMLHelper createXMLHelper() {
		XMLHelperImpl helper = new XMLHelperImpl(this) {
			public void addPrefix(String prefix, String uri) {
				if (prefix.equals("uma")) {		//$NON-NLS-1$
					uri = UmaPackage.eNS_URI;
				}
				super.addPrefix(prefix, uri);
			}
		};
		return helper;
	}
	
} // UmaResourceImpl
