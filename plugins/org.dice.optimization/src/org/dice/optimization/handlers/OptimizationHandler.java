package org.dice.optimization.handlers;

import org.dice.optimization.preferences.pages.OptimizationPreferencesPage;
import org.dice.ui.handlers.AbstractOpenBrowserHandler;
import org.dice.ui.preferences.pages.AbstractOpenBrowserPreferencesPage;

public class OptimizationHandler extends AbstractOpenBrowserHandler {

	@Override
	protected String getBrowserId() {
		return "OPTIMIZATION_BROWSER_ID";
	}

	@Override
	protected AbstractOpenBrowserPreferencesPage getOpenBrowserPreferencesPage() {
		return OptimizationPreferencesPage.getSingleton();
	}

}
