package org.eclipse.epf.library.edit.uma.impl;

import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.impl.MethodConfigurationImpl;
import org.eclipse.epf.uma.util.Scope;

/**
 * 
 * @author Weiping Lu
 * @since 1.5.1
 *
 */
public abstract class ScopeBase extends MethodConfigurationImpl implements Scope {

	private static final String defaultName = LibraryEditResources.scope_defualtName;
	
	public ScopeBase() {
		super(null);
		eSetDeliver(false);
		setName(defaultName);
	}
	
	public abstract void addToScope(MethodElement element);

	public abstract boolean inScope(MethodElement element);
	
	public abstract void addPlugin(MethodPlugin plugin);
	
	public abstract void removePlugin(MethodPlugin plugin);
		
	public void clearAll() {		
		Scope scope = null;
	}

}
