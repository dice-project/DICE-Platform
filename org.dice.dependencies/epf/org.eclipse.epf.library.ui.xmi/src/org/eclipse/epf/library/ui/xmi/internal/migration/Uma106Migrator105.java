package org.eclipse.epf.library.ui.xmi.internal.migration;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.ui.xmi.XMILibraryUIPlugin;
import org.eclipse.epf.library.util.SynFreeProcessConverter;
import org.eclipse.epf.library.xmi.XMILibraryResources;
import org.eclipse.epf.persistence.migration.UpgradeCallerInfo;
import org.eclipse.epf.uma.MethodLibrary;

public class Uma106Migrator105 extends Migrator104 {

	protected void updateAllContents(IProgressMonitor monitor, MethodLibrary lib)
			throws Exception {
		super.updateAllContents(monitor, lib);
		handleConverToSynFree(monitor, getCallerInfo(), lib);
	}

	public static void handleConverToSynFree(IProgressMonitor monitor, UpgradeCallerInfo info, MethodLibrary lib) {
		if (info == null || ! info.isConverToSynFree()) {
			return;
		}	
		updateStatus(monitor, XMILibraryResources.convertToSynFree_taskName);
		SynFreeProcessConverter converter = new SynFreeProcessConverter();
		try { 
			converter.convertLibrary(lib, false);
		} catch (Exception e) {
			XMILibraryUIPlugin.getDefault().getLogger().logError(e);
		}
	}

}