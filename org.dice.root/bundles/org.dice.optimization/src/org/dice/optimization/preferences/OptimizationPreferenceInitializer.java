package org.dice.optimization.preferences;

import org.dice.optimization.preferences.pages.OptimizationPreferencesPage;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;

public class OptimizationPreferenceInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		OptimizationPreferencesPage.getSingleton().initDefaults();
	}

}
