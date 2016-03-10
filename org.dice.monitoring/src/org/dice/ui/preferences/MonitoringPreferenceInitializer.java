package org.dice.ui.preferences;

import org.dice.ui.MonitoringActivator;
import org.dice.ui.preferences.pages.MonitoringPreferencesPage;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class MonitoringPreferenceInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = MonitoringActivator.getDefault().getPreferenceStore();
		MonitoringPreferencesPage.initDefaults(store);
	}

}
