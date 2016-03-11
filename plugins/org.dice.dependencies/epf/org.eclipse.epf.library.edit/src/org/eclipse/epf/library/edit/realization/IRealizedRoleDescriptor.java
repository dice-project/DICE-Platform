package org.eclipse.epf.library.edit.realization;

import java.util.List;

import org.eclipse.epf.uma.WorkProductDescriptor;


public interface IRealizedRoleDescriptor extends IRealizedDescriptor {
	
	List<WorkProductDescriptor> getResponsibleFor();

}
