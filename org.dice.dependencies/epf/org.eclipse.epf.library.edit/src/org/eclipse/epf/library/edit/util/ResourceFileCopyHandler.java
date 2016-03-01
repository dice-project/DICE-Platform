//------------------------------------------------------------------------------
// Copyright (c) 2005, 2008 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.edit.util;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.uma.MethodElement;

/**
 * Utility class to scan method element contents and copy resource files for
 * copied method elements
 * 
 * @author Weiping Lu, August 31, 2008
 *
 */
public class ResourceFileCopyHandler {
	
	private IResourceScanner scanner;
	private static boolean localDebug = false;
	private Map<MethodElement, MethodElement> originalToCopyMap_;
	
	public ResourceFileCopyHandler(IResourceScanner scanner) {
		this.scanner = scanner;
	}
	
	public void execute() {
		execute(getOriginalToCopyMap());
	}
	
	public void execute(Map<MethodElement, MethodElement> originalToCopyMap) {
		int sz = originalToCopyMap == null ? 0 : originalToCopyMap.size();
		if (localDebug) {
			System.out.println("LD> copyToOriginalMap: " + sz);
		}
		if (sz == 0) {
			return;
		}
		for (Map.Entry<MethodElement, MethodElement> entry: originalToCopyMap.entrySet()) {
			scanElement(entry.getKey(), entry.getValue());
		}
		scanner.copyFiles();
	}

	public IResourceScanner getScanner() {
		return scanner;
	}	
	
	private void scanElement(MethodElement source, MethodElement copy) {
		if (localDebug) {
			System.out.println("LD> source: " + source);
			System.out.println("LD> copy: " + copy); 
			System.out.println("");
		}
		
		List features = source.eClass().getEAllStructuralFeatures();
		for (int i = 0; i < features.size(); i++) {
			EStructuralFeature feature = (EStructuralFeature) features.get(i);
			if (feature instanceof EAttribute) {
				scanAttribute(source, copy, (EAttribute) feature);
			}
		}
	}
	
	private void scanAttribute(MethodElement source, MethodElement copy,
			EAttribute feature) {
		Object sourceValue = source.eGet(feature);
		if (sourceValue == null) {
			return;
		}
		Object copiedValue = sourceValue;

		if (sourceValue instanceof URI) {
			URI uri = (URI) sourceValue;
			String urlStr = scanner.registerFileCopy(uri.toString());
			try {
				copiedValue = new URI(urlStr);
			} catch (Exception e) {
				copiedValue = sourceValue;
			}
		} else if (sourceValue instanceof String) {
			copiedValue = scanner.scan(source, copy, (String) sourceValue, feature);
		} else {
			return;
		}

		if (! sourceValue.equals(copiedValue)) {
			copy.eSet(feature, copiedValue);
		}
		
	}

	private Map<MethodElement, MethodElement> getOriginalToCopyMap() {
		return originalToCopyMap_;
	}

	public void setOriginalToCopyMap(
			Map<MethodElement, MethodElement> originalToCopyMap) {
		this.originalToCopyMap_ = originalToCopyMap;
	}

	public void dispose() {
		originalToCopyMap_ = null;
	}
	
}
