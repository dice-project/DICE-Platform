package org.eclipse.epf.library.edit.uma;

import org.eclipse.epf.library.edit.uma.impl.LibraryScopeImpl;
import org.eclipse.epf.library.edit.uma.impl.ScopeImpl;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.uma.util.Scope;

public class ScopeFactory {

	private static ScopeFactory instance = new ScopeFactory();
	public static ScopeFactory getInstance() {
		return instance;
	}
		
	private ScopeFactory() {		
	}
	
	public Scope newProcessScope(String name) {
		Scope scope = new ScopeImpl(); 
		
		if (name != null) {
			scope.setName(name);
		}
		
		return scope;
	}
	
	public Scope newLibraryScope() {
		Scope libScope = new LibraryScopeImpl();		
		return libScope;
	}
	
}
