package org.dice.monitoring.preferences;

import org.dice.monitoring.MonitoringActivator;
import org.dice.monitoring.preferences.pages.MonitoringPreferencesPage;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class MonitoringPreferenceInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = MonitoringActivator.getDefault().getPreferenceStore();
		MonitoringPreferencesPage.initDefaults(store);
	}

}
