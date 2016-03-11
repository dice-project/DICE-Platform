package org.eclipse.epf.library.ui.xmi.internal.migration;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.epf.persistence.migration.UpgradeCallerInfo;
import org.eclipse.epf.uma.MethodLibrary;

public class Uma106Migrator102 extends Migrator102_103 {

	protected void handleConverToSynFree(IProgressMonitor monitor, UpgradeCallerInfo info, MethodLibrary lib) {
		Uma106Migrator105.handleConverToSynFree(monitor, info, lib);
	}
}
