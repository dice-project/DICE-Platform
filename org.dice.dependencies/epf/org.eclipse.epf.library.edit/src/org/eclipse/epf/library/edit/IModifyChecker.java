package org.eclipse.epf.library.edit;

import java.util.Collection;

import org.eclipse.emf.ecore.resource.Resource;

public interface IModifyChecker {

	public boolean checkModify(Resource res);

	public boolean checkModify(Collection<Resource> modifiedResources);
	
}
