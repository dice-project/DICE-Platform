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
package org.eclipse.epf.library.edit.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * This command is used to change the features of multiple objects in
 * a single batch.
 * 
 * @author Phong Nguyen Le
 * @since  1.0
 */
public class BatchCommand extends AbstractCommand {
	private Map objectToOldFeatureValuesMap;
	private Map objectToNewFeatureValuesMap;
	private boolean clear;
	
	/**
	 * @param clear if true will clear the many-valued feature before adding new values 
	 */
	public BatchCommand(boolean clear) {
		super();
		this.clear = clear;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.AbstractCommand#canExecute()
	 */
	public boolean canExecute() {
		return objectToNewFeatureValuesMap != null && !objectToNewFeatureValuesMap.isEmpty();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		if(objectToNewFeatureValuesMap == null || objectToNewFeatureValuesMap.isEmpty()) {
			return;
		}
		
		if(objectToOldFeatureValuesMap == null) {
			objectToOldFeatureValuesMap = new HashMap();
		}

		for (Iterator iter = objectToNewFeatureValuesMap.entrySet()
				.iterator(); iter.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			EObject eObj = (EObject) entry.getKey();
			Map featureMap = (Map) entry.getValue();
			for (Iterator iterator = featureMap.entrySet().iterator(); iterator
					.hasNext();) {
				entry = (Map.Entry) iterator.next();
				EStructuralFeature feature = (EStructuralFeature) entry
						.getKey();
				if (feature.isMany()) {
					if(clear) {
						eObj.eSet(feature, entry.getValue());
					}
					else {
						((List) eObj.eGet(feature)).addAll((Collection) entry.getValue());
					}
				} else {
					Object oldValue = eObj.eGet(feature);
					if (oldValue != entry.getValue()) {
						saveOldFeatureValue(eObj, feature);
						eObj.eSet(feature, entry.getValue());
					}
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {
		execute();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.AbstractCommand#undo()
	 */
	public void undo() {
		if (objectToNewFeatureValuesMap != null && !objectToNewFeatureValuesMap.isEmpty()) {
			for (Iterator iter = objectToNewFeatureValuesMap.entrySet()
					.iterator(); iter.hasNext();) {
				Map.Entry entry = (Map.Entry) iter.next();
				EObject eObj = (EObject) entry.getKey();
				Map featureMap = (Map) entry.getValue();
				for (Iterator iterator = featureMap.entrySet().iterator(); iterator
						.hasNext();) {
					entry = (Map.Entry) iterator.next();
					EStructuralFeature feature = (EStructuralFeature) entry
							.getKey();
					if (feature.isMany()) {
						((List) eObj.eGet(feature))
								.removeAll((Collection) entry.getValue());
					}
				}
			}			
		}

		// restore old value
		//
		if (objectToOldFeatureValuesMap != null && !objectToOldFeatureValuesMap.isEmpty()) {
			for (Iterator iter = objectToOldFeatureValuesMap.entrySet()
					.iterator(); iter.hasNext();) {
				Map.Entry entry = (Map.Entry) iter.next();
				EObject eObj = (EObject) entry.getKey();
				Map featureMap = (Map) entry.getValue();
				for (Iterator iterator = featureMap.entrySet().iterator(); iterator
						.hasNext();) {
					entry = (Map.Entry) iterator.next();
					EStructuralFeature feature = (EStructuralFeature) entry
							.getKey();
					eObj.eSet(feature, entry.getValue());
				}
			}
			objectToOldFeatureValuesMap.clear();
		}
	}
	
	public void addFeatureValue(EObject obj, EStructuralFeature feature, Object value) {
		addFeatureValue(getObjectToNewFeatureValuesMap(), obj, feature, value);
	}
	
	/**
	 * Adds values to a 'many' feature.
	 * 
	 * @param obj
	 * @param feature the feature with many values
	 * @param values
	 */
	public void addFeatureValues(EObject obj, EStructuralFeature feature, Collection values) {
		addFeatureValues(getObjectToNewFeatureValuesMap(), obj, feature, values);
	}

	public Map getObjectToNewFeatureValuesMap() {
		if(objectToNewFeatureValuesMap == null) {
			objectToNewFeatureValuesMap = new HashMap();
		}
		return objectToNewFeatureValuesMap;
	}
	
	protected void saveOldFeatureValue(EObject eObj,
			EStructuralFeature feature) {
		Object value = eObj.eGet(feature);
		if(feature.isMany()) {
			value = new ArrayList((List)value);
		}
		Map featureMap = (Map) objectToOldFeatureValuesMap.get(eObj);
		if (featureMap == null) {
			featureMap = new HashMap();
			objectToOldFeatureValuesMap.put(eObj, featureMap);
		}
		featureMap.put(feature, value);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.AbstractCommand#dispose()
	 */
	public void dispose() {
		if(objectToNewFeatureValuesMap != null) {
			objectToNewFeatureValuesMap.clear();
			objectToNewFeatureValuesMap = null;
		}
		
		if(objectToOldFeatureValuesMap != null) {
			objectToOldFeatureValuesMap.clear();
			objectToOldFeatureValuesMap = null;
		}
	}

	public static void addFeatureValue(Map descriptorToNewFeatureValuesMap,
			EObject object, EStructuralFeature feature, Object value) {
		Map featureMap = (Map) descriptorToNewFeatureValuesMap.get(object);
		if (featureMap == null) {
			featureMap = new HashMap();
			descriptorToNewFeatureValuesMap.put(object, featureMap);
		}
		if (feature.isMany()) {
			List list = (List) featureMap.get(feature);
			if (list == null) {
				list = new ArrayList();
				featureMap.put(feature, list);
			}
			list.add(value);
		} else {
			featureMap.put(feature, value);
		}
	}

	public static void addFeatureValues(Map descriptorToNewFeatureValuesMap,
			EObject object, EStructuralFeature feature, Collection values) {
		Map featureMap = (Map) descriptorToNewFeatureValuesMap.get(object);
		if (featureMap == null) {
			featureMap = new HashMap();
			descriptorToNewFeatureValuesMap.put(object, featureMap);
		}
		List list = (List) featureMap.get(feature);
		if (list == null) {
			list = new ArrayList();
			featureMap.put(feature, list);
		}
		list.addAll(values);
	}
}
