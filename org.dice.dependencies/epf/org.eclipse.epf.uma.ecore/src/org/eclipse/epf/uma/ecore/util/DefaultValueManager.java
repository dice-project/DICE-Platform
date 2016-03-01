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
package org.eclipse.epf.uma.ecore.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * @author Phong Nguyen Le
 * @sine 1.2
 */
public class DefaultValueManager {
	public static final DefaultValueManager INSTANCE = new DefaultValueManager();
	
	private final Map<EStructuralFeature, Map<EClass, Object>> featureToTypedDefaultValuesMap = 
		new HashMap<EStructuralFeature, Map<EClass, Object>>();
	
	private final Map<EClass, Map<EStructuralFeature, Object>> typeToFeatureDefaultValueMap =
		new HashMap<EClass, Map<EStructuralFeature,Object>>();

	private final Map<EStructuralFeature, Map<EClass, Object>> featureToTypedStaticDefaultValuesMap = 
		new HashMap<EStructuralFeature, Map<EClass, Object>>();
	
	private final Map<EClass, Map<EStructuralFeature, Object>> typeToFeatureStaticDefaultValueMap =
		new HashMap<EClass, Map<EStructuralFeature,Object>>();

	private volatile boolean useStatic;
	
	protected DefaultValueManager() {
		
	}
	
	public void setUseStatic(boolean useStatic) {
		this.useStatic = useStatic;
	}
	
	public boolean isUseStatic() {
		return useStatic;
	}
	
	private static Map<EStructuralFeature, Object> getFeatureToDefaultValueMap(EClass type, 
			Map<EStructuralFeature, Map<EClass, Object>> featureToTypedDefaultValuesMap,
			Map<EStructuralFeature, Object> featureToStaticDefaultValueMap) {
		Map<EStructuralFeature, Object> featureToDefaultValueMap;
		if(featureToTypedDefaultValuesMap.isEmpty()) {
			featureToDefaultValueMap = Collections.EMPTY_MAP;
		}
		else {
			featureToDefaultValueMap = new HashMap<EStructuralFeature, Object>();
			for (Iterator<Map.Entry<EStructuralFeature, Map<EClass, Object>>> iter = 
				featureToTypedDefaultValuesMap.entrySet().iterator(); iter.hasNext();) {
				Map.Entry<EStructuralFeature, Map<EClass, Object>> entry = iter.next();
				EStructuralFeature feature = entry.getKey();
				if(type.getFeatureID(feature) != -1) {
					Object defaultValue = null;
					EClass selectedType = type;
					boolean found = false;
					for (Iterator<Map.Entry<EClass, Object>> iterator = entry.getValue().entrySet().iterator(); iterator
							.hasNext();) {
						Map.Entry<EClass, Object> ent = iterator.next();
						EClass currentType = ent.getKey();
						if(currentType.isSuperTypeOf(type) && 
								(selectedType == null || selectedType.isSuperTypeOf(currentType))) {
							selectedType = currentType;
							defaultValue = ent.getValue();
							found = true;
						}
					}
					if(found) {
						Object staticDefaultValue = featureToStaticDefaultValueMap.get(feature);
						if(staticDefaultValue == null) {
							staticDefaultValue = feature.getDefaultValue();
						}
						
						// default value is overridden either via:
						// - static default value
						// - dynamic default value
						// - dynamic default value that overrode a overriding static default value
						//
						if((defaultValue == null &&  staticDefaultValue != null)
								|| (defaultValue != null && (!defaultValue.equals(staticDefaultValue) || 
										(staticDefaultValue != null && !staticDefaultValue.equals(feature.getDefaultValue()))))) {
							featureToDefaultValueMap.put(feature, defaultValue);
						}
					}
				}
			}
			if(featureToDefaultValueMap.isEmpty()) {
				featureToDefaultValueMap = Collections.EMPTY_MAP;
			}
		}
		return featureToDefaultValueMap;
	}
	
	public synchronized Map<EStructuralFeature, Object> getFeatureToDefaultValueMap(EClass type) {
		if(useStatic) {
			return getFeatureToStaticDefaultValueMap(type);
		}
		else {
			return getFeatureToDynamicDefaultValueMap(type);
		}		
	}
	
	private synchronized Map<EStructuralFeature, Object> getFeatureToDynamicDefaultValueMap(EClass type) {
		Map<EStructuralFeature, Object> featureToDefaultValueMap = typeToFeatureDefaultValueMap.get(type);
		if(featureToDefaultValueMap == null) {
			featureToDefaultValueMap = getFeatureToDefaultValueMap(type, featureToTypedDefaultValuesMap,
					getFeatureToStaticDefaultValueMap(type));
			typeToFeatureDefaultValueMap.put(type, featureToDefaultValueMap);
		}
		return featureToDefaultValueMap;
	}	
	
	private synchronized Map<EStructuralFeature, Object> getFeatureToStaticDefaultValueMap(EClass type) {
		Map<EStructuralFeature, Object> featureToDefaultValueMap = typeToFeatureStaticDefaultValueMap.get(type);
		if(featureToDefaultValueMap == null) {
			featureToDefaultValueMap = getFeatureToDefaultValueMap(type, featureToTypedStaticDefaultValuesMap,
					Collections.EMPTY_MAP);
			typeToFeatureStaticDefaultValueMap.put(type, featureToDefaultValueMap);
		}
		return featureToDefaultValueMap;
	}

	
	private static final synchronized void setDefaultValue(EClass type, EStructuralFeature feature, Object defaultValue,
			Map<EStructuralFeature, Map<EClass, Object>> featureToTypedDefaultValuesMap ) 
	{
		if(!feature.getEContainingClass().isSuperTypeOf(type)) {
			return;
		}
		Map<EClass, Object> typeToDefaultValueMap = featureToTypedDefaultValuesMap.get(feature);
		if(typeToDefaultValueMap == null) {
			// this map must be sorted from sub type to super type
			//
			typeToDefaultValueMap = new HashMap<EClass, Object>();
			featureToTypedDefaultValuesMap.put(feature, typeToDefaultValueMap);
		}
		typeToDefaultValueMap.put(type, defaultValue);
	}
	
	public final synchronized void setDefaultValue(EClass type, EStructuralFeature feature, Object defaultValue) {
		setDefaultValue(type, feature, defaultValue, featureToTypedDefaultValuesMap);
		
		// clear typeToFeatureDefaultValueMap so the default value will be recalculated for each class
		//
		typeToFeatureDefaultValueMap.clear();
	}
	
	public final synchronized void setStaticDefaultValue(EClass type, EStructuralFeature feature, Object defaultValue) {
		setDefaultValue(type, feature, defaultValue);
		setDefaultValue(type, feature, defaultValue, featureToTypedStaticDefaultValuesMap);
	}
	
	public final void removeDefaultValue(EStructuralFeature feature) {
		featureToTypedDefaultValuesMap.remove(feature);
	}

	public final void removeDefaultValue(EStructuralFeature feature, EClass type) {
		assert feature.getEContainingClass().isSuperTypeOf(type);
		Map<EClass, Object> typeToDefaultValueMap = featureToTypedDefaultValuesMap.get(feature);
		if(typeToDefaultValueMap != null) {
			typeToDefaultValueMap.remove(type);
		}
		
		// clear typeToFeatureDefaultValueMap so the default value will be recalculated for each class
		//
		typeToFeatureDefaultValueMap.clear();
	}
	
	private static void assignDefaultValues(EObject eObject, Map<EStructuralFeature, Object> featureToDefaultValueMap) {
		if(featureToDefaultValueMap.isEmpty()) {
			return;
		}
		
		for (Iterator<Map.Entry<EStructuralFeature, Object>> iter = featureToDefaultValueMap
				.entrySet().iterator(); iter.hasNext();) {
			Map.Entry<EStructuralFeature, Object> entry = iter.next();
			eObject.eSet(entry.getKey(), entry.getValue());
		}

	}

	public void assignDefaultValues(EObject eObject) {
		EClass type = eObject.eClass();
		Map<EStructuralFeature, Object> featureToStaticDefaultValueMap = getFeatureToStaticDefaultValueMap(type);
		assignDefaultValues(eObject, featureToStaticDefaultValueMap);
		Map<EStructuralFeature, Object> featureToDefaultValueMap = getFeatureToDefaultValueMap(eObject.eClass());
		if(featureToDefaultValueMap != featureToStaticDefaultValueMap) {
			assignDefaultValues(eObject, featureToDefaultValueMap);
		}
	}
	
}
