package org.eclipse.epf.library.edit.util;

import java.util.Set;

import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.uma.MethodElementExt;
import org.eclipse.epf.library.edit.uma.MethodElementExt.MethodConfigurationExt;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.UmaPackage;


public class MethodConfigurationPropUtil extends PropUtil {

	public static final String TOUCHED_BY_CONFIG_EDITOR = "TouchedByConfigEditor";				//$NON-NLS-1$	
	
	public static final String Config_elementsUnslectedPkgs = "config_elementsUnslectedPkgs"; 	//$NON-NLS-1$
	public static final String Config_selectedElements = "config_selectedElements"; 			//$NON-NLS-1$
	public static final String Config_deselectedElements = "config_deselectedElements"; 		//$NON-NLS-1$
	public static final String Config_doneLoadCheckPkgs = "Config_doneLoadCheckPkgs"; 		//$NON-NLS-1$	
	
	private static MethodConfigurationPropUtil methodConfigurationPropUtil = new MethodConfigurationPropUtil();

	public static MethodConfigurationPropUtil getMethodConfigurationPropUtil() {
		return methodConfigurationPropUtil;
	}

	public static MethodConfigurationPropUtil getMethodConfigurationPropUtil(
			IActionManager actionManager) {
		return new MethodConfigurationPropUtil(actionManager);
	}
	
	protected MethodConfigurationPropUtil() {		
	}
	
	protected MethodConfigurationPropUtil(IActionManager actionManager) {
		super(actionManager);
	}
		
	public Set<MethodPackage> getDoneLoadCheckPkgs(
			MethodConfiguration config) {
		return (Set<MethodPackage>) getElements(config, Config_doneLoadCheckPkgs, UmaPackage.eINSTANCE.getMethodPackage());
	}
	
	public void setDoneLoadCheckPkgs(MethodConfiguration config, Set<MethodPackage> pkgs) {
		setElements(config, Config_doneLoadCheckPkgs, pkgs, UmaPackage.eINSTANCE.getMethodPackage());
	}
	
	public Set<MethodPackage> getElementsUnslectedPkgs(
			MethodConfiguration config) {
		return (Set<MethodPackage>) getElements(config, Config_elementsUnslectedPkgs, UmaPackage.eINSTANCE.getMethodPackage());
	}
	
	public void setElementsUnslectedPkgsProp(MethodConfiguration config, Set<MethodPackage> pkgs) {
		setElements(config, Config_elementsUnslectedPkgs, pkgs, UmaPackage.eINSTANCE.getMethodPackage());
	}
	
	public Set<MethodElement> getSelectedElements(
			MethodConfiguration config) {
		return (Set<MethodElement>) getElements(config, Config_selectedElements, null);
	}
	
	public void setSelectedElements(MethodConfiguration config, Set<MethodElement> elements) {
		setElements(config, Config_selectedElements, elements, null);
	}
	
	public Set<MethodElement> getDeselectedElements(
			MethodConfiguration config) {
		return (Set<MethodElement>) getElements(config, Config_deselectedElements, null);
	}
	
	public void setDeselectedElements(MethodConfiguration config, Set<MethodElement> elements) {
		setElements(config, Config_deselectedElements, elements, null);
	}
	
	public MethodConfigurationExt getMethocConfigurationExt(MethodConfiguration config) {
		MethodElementExt ext = getExtendObject(config, true);
		return ext instanceof MethodConfigurationExt ? (MethodConfigurationExt) ext : null;		
	}
	
}
