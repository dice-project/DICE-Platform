package org.eclipse.epf.uma.util;

import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;


/**
 * @author Weiping Lu
 * @since 1.5.1
 */
public interface Scope extends MethodConfiguration {

	boolean inScope(MethodElement element);
	void addToScope(MethodElement element);
	
	void addPlugin(MethodPlugin plugin);
	void removePlugin(MethodPlugin plugin);
	
	void clearAll();
		
	boolean debug = false;
	
}
