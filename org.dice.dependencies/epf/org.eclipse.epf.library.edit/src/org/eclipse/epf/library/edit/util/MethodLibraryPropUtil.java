package org.eclipse.epf.library.edit.util;

import org.eclipse.epf.uma.MethodLibrary;

public class MethodLibraryPropUtil extends PropUtil {

	public static final String Library_SynFree = "library_synFree"; //$NON-NLS-1$
	
	private static MethodLibraryPropUtil methodLibraryPropUtil = new MethodLibraryPropUtil();
	public static MethodLibraryPropUtil getMethodLibraryPropUtil() {
		return methodLibraryPropUtil;
	}
	
	protected MethodLibraryPropUtil() {		
	}

	
	public boolean isSynFree(MethodLibrary d) {
		Boolean value = getBooleanValue(d, Library_SynFree);
		return value == null ? false : value.booleanValue();
	}
	
	public void setSynFree(MethodLibrary d, boolean value) {
		setBooleanValue(d, Library_SynFree, value);
	}
	
	
}
