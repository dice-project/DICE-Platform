package org.eclipse.epf.uma.util;

import java.util.List;

import org.eclipse.emf.ecore.EReference;

public interface ExtendedReference extends ExtendedFeature {
		
	public String Roles = "roles"; 					//$NON-NLS-1$
	
	public String WorkProducts = "workproducts"; 	//$NON-NLS-1$
	
	public EReference getReference();
	
	public List<QualifiedReference> getQualifiedReferences();
	
	public List<String> getValueTypes();
	
	public String getContributeTo();
	
	public ExtendedOpposite getOpposite();
	
}
