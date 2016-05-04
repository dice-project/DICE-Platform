package org.dice.optimization.preferences;

import org.dice.optimization.OptimizationActivator;
import org.dice.optimization.preferences.pages.OptimizationPreferencesPage;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class OptimizationPreferenceInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = OptimizationActivator.getDefault().getPreferenceStore();
		OptimizationPreferencesPage.initDefaults(store);
	}

}
