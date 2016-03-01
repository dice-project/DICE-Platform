package org.eclipse.epf.library.ui.xmi.internal.migration;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.epf.uma.MethodLibrary;

public class Uma106Migrator103 extends Migrator103 {

	protected void updateAllContents(IProgressMonitor monitor, MethodLibrary lib)
			throws Exception {
		super.updateAllContents(monitor, lib);
		Uma106Migrator105.handleConverToSynFree(monitor, getCallerInfo(), lib);
	}

}
