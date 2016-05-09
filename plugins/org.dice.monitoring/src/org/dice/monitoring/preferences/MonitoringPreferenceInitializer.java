package org.dice.monitoring.preferences;

import org.dice.monitoring.preferences.pages.MonitoringPreferencesPage;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;

public class MonitoringPreferenceInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		MonitoringPreferencesPage.getSingleton().initDefaults();
	}

}
