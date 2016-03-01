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
package org.eclipse.epf.migration.diagram.ad.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.XMLSave;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.xmi.impl.XMISaveImpl;


/**
 * @author Shashidhar Kannoori
 * @author Shilpa Toraskar
 * @since 1.2
 * 
 */

public class Workflow2XMIResourceImpl extends XMIResourceImpl {

	public static final String DEFAULT_ENCODING = "UTF-8"; //$NON-NLS-1$
	Map map = new HashMap();
	/**
	 * 
	 */
	public Workflow2XMIResourceImpl() {
		super();
	}

	/**
	 * @param uri
	 */
	public Workflow2XMIResourceImpl(URI uri) {
		super(uri);

		getDefaultSaveOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
		getDefaultLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);

		getDefaultSaveOptions().put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);

		getDefaultLoadOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
		getDefaultSaveOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
		
		getDefaultLoadOptions().put(XMLResource.OPTION_ENCODING, DEFAULT_ENCODING);
		getDefaultSaveOptions().put(XMLResource.OPTION_ENCODING, DEFAULT_ENCODING);
		

		getDefaultLoadOptions().put(XMLResource.OPTION_USE_LEXICAL_HANDLER, Boolean.TRUE);

		getDefaultSaveOptions().put(XMIResource.OPTION_USE_XMI_TYPE, Boolean.TRUE);
		getDefaultLoadOptions().put(XMIResource.OPTION_USE_XMI_TYPE, Boolean.TRUE);
		setTrackingModification(true);
		//setIntrinsicIDToEObjectMap(map);
		idToEObjectMap = new HashMap();
		createXMLHelper();
	}
	
	protected boolean useUUIDs() {
		return true;
	}
	
	protected XMLSave createXMLSave() {
		return new XMISaveImpl(createXMLHelper());
	}
	
	public void save(Map options) throws IOException {
		super.save(options);
	}

}
