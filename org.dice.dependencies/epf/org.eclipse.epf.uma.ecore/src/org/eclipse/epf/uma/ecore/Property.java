package org.eclipse.epf.uma.ecore;

import org.eclipse.emf.ecore.EStructuralFeature;

public class Property implements EProperty {

	private EStructuralFeature feature;

	public Property(EStructuralFeature feature) {
		this.feature = feature;
	}

	public EStructuralFeature getEStructuralFeature() {
		return feature;
	}

}
