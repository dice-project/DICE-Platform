package org.dice.ui.preferences;

import org.dice.ui.DiceUIActivator;
import org.dice.ui.preferences.pages.DicePreferencesPage;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class DicePreferenceInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = DiceUIActivator.getDefault().getPreferenceStore();
		DicePreferencesPage.initDefaults(store);
	}

}
