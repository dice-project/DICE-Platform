package org.eclipse.epf.library.realization;

import org.eclipse.epf.library.edit.realization.IRealizationManager;
import org.eclipse.epf.library.realization.impl.RealizationManager;
import org.eclipse.epf.uma.MethodConfiguration;

public class RealizationManagerFactory {
	
	public static RealizationManagerFactory instance = new RealizationManagerFactory();	

	public static RealizationManagerFactory getInstance() {
		return instance;
	}

	public RealizationManagerFactory() {		
	}
	
	public IRealizationManager newRealizationManager(MethodConfiguration config) {
		return new RealizationManager(config);
	}	
	
}
