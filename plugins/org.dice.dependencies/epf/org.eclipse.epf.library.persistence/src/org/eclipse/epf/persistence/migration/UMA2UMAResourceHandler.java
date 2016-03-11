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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.ecore.xml.type.AnyType;

/**
 * Resource handler for migrating UMA library from one version to another.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class UMA2UMAResourceHandler extends MigrationResourceHandler {

	private static final boolean DEBUG = true;

	private List moveInfos;

	public UMA2UMAResourceHandler() {
		moveInfos = new ArrayList();
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

	/**
	 * Subclass can override this method to customize rules to move data
	 * 
	 * @param owner
	 * @param featureName
	 * @return
	 */
	protected EStructuralFeature getNewFeature(EObject owner, String featureName) {
		return null;
	}

	/**
	 * Subclass can override this method to customize rules to move data
	 * 
	 * @param oldOwner
	 * @param featureName
	 * @return
	 */
	protected EObject getNewOwner(EObject oldOwner, String featureName) {
		return null;
	}

	protected Object getNewValue(EObject oldOwner, String featureName,
			AnyType value) {
		return null;
	}

	public void moveData() {
		for (Iterator iter = moveInfos.iterator(); iter.hasNext();) {
			MoveInfo info = (MoveInfo) iter.next();
			EObject newOwner = getNewOwner(info.oldOwner, info.oldFeatureName);
			if (newOwner != null) {
				setValue(newOwner, info.newFeature, info.value);
			}
		}
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

	private static class MoveInfo {
		EObject oldOwner;

		String oldFeatureName;

		EStructuralFeature newFeature;

		Object value;

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			return new StringBuffer()
					.append(getClass().getName())
					.append("(\n") //$NON-NLS-1$
					.append("  oldFeatureName: ").append(oldFeatureName).append('\n') //$NON-NLS-1$
					.append("  newFeature: ").append(newFeature).append('\n') //$NON-NLS-1$
					.append("  value: ").append(value).append('\n') //$NON-NLS-1$
					.append(")").toString(); //$NON-NLS-1$
		}
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

	public void clearMoveInfos() {
		moveInfos.clear();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.persistence.migration.MigrationResourceHandler#handleUnknownFeature(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object)
	 */
	protected boolean handleUnknownFeature(EObject owner, EStructuralFeature feature, Object value) {
		String featureName = feature.getName();
		EStructuralFeature newFeature = getNewFeature(owner, featureName);
		if (newFeature != null) {
			MoveInfo moveInfo = new MoveInfo();
			moveInfo.oldOwner = owner;
			moveInfo.oldFeatureName = featureName;
			moveInfo.newFeature = newFeature;
			moveInfo.value = value instanceof AnyType ? getNewValue(
					owner, featureName, (AnyType) value)
					: value;
			moveInfos.add(moveInfo);
			if (DEBUG) {
				System.out.println(moveInfo);
				System.out.println();
			}
			return true;
		}
		return false;
	}

}
