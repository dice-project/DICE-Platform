package org.eclipse.epf.uma.util;

import java.util.List;

import org.eclipse.emf.ecore.EAttribute;

public interface ExtendedAttribute  extends ExtendedFeature {

	public EAttribute getAttribute();
	public String getValueType();
	
	public List<MetaElement> getChoiceValues();
	
}
