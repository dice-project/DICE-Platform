package org.eclipse.epf.library.edit.realization;

import java.util.List;

import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.Section;
import org.eclipse.epf.uma.WorkProductDescriptor;

public interface IRealizedTaskDescriptor  extends IRealizedDescriptor {

	List<RoleDescriptor> getPerformedPrimarilyBy();
	
	List<WorkProductDescriptor> getMandatoryInput();
	
	List<RoleDescriptor> getAdditionallyPerformedBy();
	
	List<WorkProductDescriptor> getOptionalInput();

	List<WorkProductDescriptor> getOutput();
	
	List<Section> getSelectedSteps();
}
