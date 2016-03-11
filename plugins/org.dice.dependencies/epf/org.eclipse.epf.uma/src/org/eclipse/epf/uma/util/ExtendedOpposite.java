package org.eclipse.epf.uma.util;

import org.eclipse.epf.uma.ecore.util.OppositeFeature;

public interface ExtendedOpposite extends MetaElement {
	
	public ExtendedReference getTargetReference();	
	
	public OppositeFeature getOFeature();
	
}
