package org.eclipse.epf.library.edit.uma.impl;

import org.eclipse.epf.uma.MethodElement;

public class LibraryScopeImpl extends ScopeImpl {

	public LibraryScopeImpl() {		
	}
	
	public boolean inScope(MethodElement element) {
		return true;
	}
	
	public void addToScope(MethodElement element) {		
	}
}
