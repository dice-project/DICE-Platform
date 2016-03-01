package org.eclipse.epf.uma.util;

import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Process;

public interface IUmaUtilProvider {
	
	boolean isSynFree();
	
	boolean isSynFreeLibrary(MethodLibrary lib);
	boolean isSynFreePlugin(MethodPlugin lib);
	boolean isSynFreeProcess(Process proc);
	
	void setSynFreeLibrary(MethodLibrary lib, boolean value);
	void setSynFreePlugin(MethodPlugin lib, boolean value);
	void setSynFreeProcess(Process proc, boolean value);
	
}
