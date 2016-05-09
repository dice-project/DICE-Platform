package org.dice.monitoring.handlers;

import org.dice.monitoring.preferences.pages.MonitoringPreferencesPage;
import org.dice.ui.handlers.AbstractOpenBrowserHandler;
import org.dice.ui.preferences.pages.AbstractOpenBrowserPreferencesPage;

public class MonitoringHandler extends AbstractOpenBrowserHandler {

	@Override
	protected String getBrowserId() {
		return "MONITORING_BROWSER_ID";
	}

	protected AbstractOpenBrowserPreferencesPage getOpenBrowserPreferencesPage() {
		return MonitoringPreferencesPage.getSingleton();
	}

}
