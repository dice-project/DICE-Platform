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
package org.eclipse.epf.persistence.migration;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.BasicResourceHandler;
import org.eclipse.emf.ecore.xml.type.AnyType;

/**
 * Resource handler for migrating UMA library from one version to another.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public abstract class MigrationResourceHandler extends BasicResourceHandler {

	private static final boolean DEBUG = true;

	public MigrationResourceHandler() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.ecore.xmi.impl.BasicResourceHandler#postLoad(org.eclipse.emf.ecore.xmi.XMLResource,
	 *      java.io.InputStream, java.util.Map)
	 */
	public void postLoad(XMLResource resource, InputStream inputStream,
			Map options) {
		if (!resource.getEObjectToExtensionMap().isEmpty()) {
			if (DEBUG) {
				System.out
						.println("MigrationResourceHandler.postLoad(): " + resource.getURI()); //$NON-NLS-1$
				System.out.println("---- Start unknown features ----"); //$NON-NLS-1$
			}
			for (Iterator iter = resource.getEObjectToExtensionMap().entrySet()
					.iterator(); iter.hasNext();) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() instanceof EObject) {
					// handle moved data
					//
					handleUnknownData((EObject) entry.getKey(), (AnyType)entry.getValue());
				}
			}
			if (DEBUG)
				System.out.println("---- End unknown features ----"); //$NON-NLS-1$
		}
	}

	public static String getText(AnyType value) {
		try {
			if (value == null)
				return null;
			FeatureMap.Entry entry = (FeatureMap.Entry) value.getMixed().get(0);
			return (String) entry.getValue();
		} catch (RuntimeException e) {
			throw e;
		}
	}

	protected static EAttribute getEAttribute(EClass eClass, String name) {
		for (Iterator iterator = eClass.getEAllAttributes().iterator(); iterator
				.hasNext();) {
			EAttribute attrib = (EAttribute) iterator.next();
			if (attrib.getName().equals(name)) {
				return attrib;
			}
		}
		return null;
	}

	public static void setValue(EObject eObject, EStructuralFeature feature,
			Object value) {
		if (!feature.isMany()) {
			eObject.eSet(feature, value);
		} else {
			Collection values = (Collection) eObject.eGet(feature);
			if (value instanceof Collection) {
				values.addAll((Collection) value);
			} else {
				values.add(value);
			}
		}

	}
	
	protected abstract boolean handleUnknownFeature(EObject owner, EStructuralFeature feature, Object value);

	private void handleUnknownFeatures(EObject owner, FeatureMap featureMap) {
		for (Iterator iter = featureMap.iterator(); iter.hasNext();) {
			FeatureMap.Entry entry = (FeatureMap.Entry) iter.next();
			EStructuralFeature f = entry.getEStructuralFeature();
			if(handleUnknownFeature(owner, f, entry.getValue())) {
				iter.remove();
			}
		}
	}

	/**
	 * @param key
	 * @param value
	 */
	protected void handleUnknownData(EObject e, AnyType unknownData) {
		handleUnknownFeatures(e, unknownData.getMixed());
		handleUnknownFeatures(e, unknownData.getAnyAttribute());
	}


	public static Object getSingleValue(FeatureMap featureMap,
			String featureName) {
		for (Iterator iter = featureMap.iterator(); iter.hasNext();) {
			FeatureMap.Entry entry = (Entry) iter.next();
			if (entry.getEStructuralFeature().getName().equals(featureName)) {
				return entry.getValue();
			}
		}
		return null;
	}

	protected static Collection getValue(FeatureMap featureMap,
			String featureName) {
		Collection list = new ArrayList();
		for (Iterator iter = featureMap.iterator(); iter.hasNext();) {
			FeatureMap.Entry entry = (Entry) iter.next();
			if (entry.getEStructuralFeature().getName().equals(featureName)) {
				list.add(entry.getValue());
			}
		}
		return list;
	}

}
