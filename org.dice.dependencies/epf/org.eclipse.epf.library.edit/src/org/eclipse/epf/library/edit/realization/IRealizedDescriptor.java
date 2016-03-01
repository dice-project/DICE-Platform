package org.eclipse.epf.library.edit.realization;

import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.Guidance;


public interface IRealizedDescriptor extends IRealizedElement {
	
	EReference ArtifactDescriptor_ContainedArtifacts = EcoreFactory.eINSTANCE.createEReference();
	Set<Descriptor> updateAndGetAllReferenced();
	
	List<Guidance> getGuidances();
	
}
