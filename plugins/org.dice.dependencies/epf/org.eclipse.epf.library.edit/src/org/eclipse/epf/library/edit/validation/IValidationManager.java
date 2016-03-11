package org.eclipse.epf.library.edit.validation;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.DiagnosticChain;

public interface IValidationManager {

	void setNameCheck(boolean b);
	boolean isNameCheck();

	void setCircularDependancyCheck(boolean b);
	boolean isCircularDependancyCheck();

	void setUndeclaredDependancyCheck(boolean b);
	
	void validate(DiagnosticChain diagnostics, Object scope, IProgressMonitor progressMonitor);
	
	
}
