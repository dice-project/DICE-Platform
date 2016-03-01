package org.eclipse.epf.common.utils;

import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.IStatus;

public class ValidateEdit {

	public ValidateEdit() {		
	}
	
	public IStatus validateEdit(IWorkspace workspace, IFile[] files, Object context) {
		return workspace.validateEdit(files, context);
	}
	
	public boolean renamePrecheck(Object element, Object shell) {
		return true;
	}
	
	public void sychnConneciton() {		
	}
	
	public void addDeleteResourceToCheck(Set modifiedResources, Object deleteObject) {		
	}
	
}
