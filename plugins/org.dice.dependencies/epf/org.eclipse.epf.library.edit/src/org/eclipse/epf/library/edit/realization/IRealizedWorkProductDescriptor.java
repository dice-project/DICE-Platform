package org.eclipse.epf.library.edit.realization;

import java.util.List;

import org.eclipse.epf.uma.WorkProductDescriptor;


public interface IRealizedWorkProductDescriptor  extends IRealizedDescriptor {

	List<WorkProductDescriptor> getDeliverableParts();
	
	List<WorkProductDescriptor> getContainedArtifacts();
	
}
