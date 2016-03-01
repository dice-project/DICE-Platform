package org.eclipse.epf.library.edit.realization;

import java.util.Set;

import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Process;

public interface IRealizationManager {
	
	/**
	 * @param element
	 * @return
	 */
	IRealizedElement getRealizedElement(MethodElement element);
	
	/**
	 * @param element
	 * @return
	 */
	IRealizedElement removeRealizedElement(MethodElement element);
	
	/**
	 * Update process model with realization
	 */
	void updateProcessModel(Process proc);
	
	/**
	 * Update process model with realization
	 */
	void elementUpdateProcessModel(Process proc, Set<MethodElement> changedElementSet);
	
	/**
	 * Update activity model with realization
	 */
	void updateActivityModel(Activity act);
	
	/**
	 * Update all process models 
	 */
	void updateAllProcesseModels();
		
	void dispose();
	
	boolean debug = false;
	
	boolean timing = false;
	
}
