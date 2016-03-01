package org.eclipse.epf.library.edit.realization;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;

public interface IRealizedElement {

	MethodElement getElement();
	
	boolean handle(Object featureOrOFeature);
	
	boolean handleFeature(EStructuralFeature feature);
	
	boolean handleOFeature(OppositeFeature ofeature);
	
	Object getFeatureValue(EStructuralFeature feature);
	
	Object getOFeatureValue(OppositeFeature ofeature);
		
	String getName();	
	String getPresentationName();
	String getBriefDescription();
	
}
