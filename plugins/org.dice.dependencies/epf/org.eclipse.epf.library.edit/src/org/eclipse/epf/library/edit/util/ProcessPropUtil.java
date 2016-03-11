package org.eclipse.epf.library.edit.util;

import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.uma.Process;


public class ProcessPropUtil extends WbePropUtil {

	public static final String Process_SynFree = "process_synFree"; //$NON-NLS-1$

	private static ProcessPropUtil processPropUtil = new ProcessPropUtil();
	public static ProcessPropUtil getProcessPropUtil() {
		return processPropUtil;
	}
	public static ProcessPropUtil getProcessPropUtil(IActionManager actionManager) {
		return new ProcessPropUtil(actionManager);
	}
	
	protected ProcessPropUtil() {		
	}
	
	protected ProcessPropUtil(IActionManager actionManager) {
		super(actionManager);
	}
	
	public boolean isSynFree(Process d) {
		Boolean value = getBooleanValue(d, Process_SynFree);
		return value == null ? false : value.booleanValue();
	}
	
	public void setSynFree(Process d, boolean value) {
		setBooleanValue(d, Process_SynFree, value);
	}	
	
}