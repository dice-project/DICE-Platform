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
package org.eclipse.epf.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.SAXXMIHandler;
import org.eclipse.emf.ecore.xmi.impl.XMILoadImpl;
import org.eclipse.epf.common.service.versioning.EPFVersions;
import org.eclipse.epf.common.service.versioning.VersionUtil;
import org.xml.sax.helpers.DefaultHandler;

/**
 * XMLLoad implementation for library XMI persistence
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class MultiFileXMILoadImpl extends XMILoadImpl {

	public MultiFileXMILoadImpl(XMLHelper helper) {
		super(helper);
	}

	protected DefaultHandler makeDefaultHandler() {
		return new SAXXMIHandler(resource, helper, options) {
			  
			protected EStructuralFeature getFeature(EObject object, String prefix, String name, boolean isElement) {
				//shijin Defect 42141 [version information]should not have activte the action of  setting the "version" attribute when read "rmc:version" attribute
				if (EPFVersions.TOOL_ID.equals(prefix) || VersionUtil.getPrimaryToolID().equals(prefix)) {
					return null;
				}
				return super.getFeature(object, prefix, name, isElement);
			}
			
			protected void handleUnknownFeature(String prefix, String name, boolean isElement, EObject peekObject, String value) {
				//shijin Defect42141 just like dealing with the "epf:version"
				if (EPFVersions.TOOL_ID.equals(prefix) || VersionUtil.getPrimaryToolID().equals(prefix)) {
					return;
				}
				super.handleUnknownFeature(prefix, name, isElement, peekObject, value);
			}
			
			public void endDocument() {
				super.endDocument();

				if (helper instanceof MultiFileXMIHelperImpl) {
					List sameDocReferences = ((MultiFileXMIHelperImpl) helper).sameDocReferences;
					if (!sameDocReferences.isEmpty()) {
						for (Iterator iter = sameDocReferences.iterator(); iter
								.hasNext();) {
							MultiFileXMIHelperImpl.ProxyReference proxyRef = (MultiFileXMIHelperImpl.ProxyReference) iter
									.next();

							// this will merge the opposite feature maps as well
							EObject resolved = proxyRef.getOwner()
									.eResolveProxy(proxyRef.getProxy());

							InternalEList values = (InternalEList) proxyRef
									.getOwner().eGet(proxyRef.getReference());
							int index = values.basicList().indexOf(
									proxyRef.getProxy());
							if (index != -1) {
								values.setUnique(index, resolved);
							}
						}
					}
				}
			}
		};
	}

	/**
	 * @see org.eclipse.emf.ecore.xmi.impl.XMLLoadImpl#load(org.eclipse.emf.ecore.xmi.XMLResource,
	 *      java.io.InputStream, java.util.Map)
	 */
	public void load(XMLResource resource, InputStream inputStream, Map options)
			throws IOException {
		try {
			// strip off the UTF-8 BOM bytes if they are still there
			//
			PushbackInputStream pbis = new PushbackInputStream(inputStream, 3);
			byte bom[] = new byte[3];
			if (pbis.read(bom) == 3) {
				if (bom[0] != (byte) 0xEF || bom[1] != (byte) 0xBB
						|| bom[2] != (byte) 0xBF) {
					pbis.unread(bom);
				}
			}

			super.load(resource, pbis, options);
		} catch (Resource.IOWrappedException e) {
			if (!resource.getErrors().isEmpty() && helper == null) {
				// unknown features detected, log the warning and ignore them
				for (Iterator iter = resource.getErrors().iterator(); iter
						.hasNext();) {
					Exception ex = (Exception) iter.next();
					CommonPlugin.INSTANCE.log(ex.getMessage());
				}
				return;
			}
			throw e;
		}
	}

}
