package org.eclipse.epf.library.edit.util;

import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.util.IUmaUtilProvider;
import org.eclipse.epf.uma.util.UmaUtil;

public class UmaUtilProvider implements IUmaUtilProvider {
		
	public static void init() {
		UmaUtil.setProvider(new UmaUtilProvider());
	}
	
	private UmaUtilProvider() {		
	}
	
	public boolean isSynFree() {
		return ProcessUtil.isSynFree();
	}
	
	public boolean isSynFreeLibrary(MethodLibrary lib) {
		return MethodLibraryPropUtil.getMethodLibraryPropUtil().isSynFree(lib);
	}
	
	public boolean isSynFreePlugin(MethodPlugin plugin) {
		return MethodPluginPropUtil.getMethodPluginPropUtil().isSynFree(plugin);
	}
	
	public boolean isSynFreeProcess(Process proc) {
		return ProcessPropUtil.getProcessPropUtil().isSynFree(proc);	
	}
	
	public void setSynFreeLibrary(MethodLibrary lib, boolean value) {
		MethodLibraryPropUtil.getMethodLibraryPropUtil().setSynFree(lib, value);
	}
	
	public void setSynFreePlugin(MethodPlugin plugin, boolean value) {
		MethodPluginPropUtil.getMethodPluginPropUtil().setSynFree(plugin, value);
	}
	
	public void setSynFreeProcess(Process proc, boolean value) {
		ProcessPropUtil.getProcessPropUtil().setSynFree(proc, value);
	}
	
}
