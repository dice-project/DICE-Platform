package org.dice.monitoring.handlers;

import org.dice.monitoring.preferences.pages.MonitoringPreferencesPage;
import org.dice.ui.handlers.AbstractOpenBrowserHandler;

public class MonitoringVisualizationHandler extends AbstractOpenBrowserHandler {

	@Override
	protected String getBrowserId() {
		return "MONITORING_VISUALIZATION_BROWSER_ID";
	}

	@Override
	protected MonitoringPreferencesPage getOpenBrowserPreferencesPage() {
		return MonitoringPreferencesPage.getSingleton();
	}

	@Override
	protected Integer getPort() {
		Integer port = getStore().getInt(getOpenBrowserPreferencesPage().getVisualizationPortIdProperty());
		return port != null ? port : 80;
	}

}
