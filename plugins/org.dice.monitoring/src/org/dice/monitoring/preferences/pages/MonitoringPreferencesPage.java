package org.dice.monitoring.preferences.pages;

import org.dice.monitoring.MonitoringActivator;
import org.dice.ui.preferences.pages.AbstractOpenBrowserPreferencesPage;
import org.eclipse.jface.preference.IPreferenceStore;

public class MonitoringPreferencesPage extends AbstractOpenBrowserPreferencesPage {

	private static MonitoringPreferencesPage singleton;

	public static MonitoringPreferencesPage getSingleton() {
		if (singleton == null) {
			singleton = new MonitoringPreferencesPage();
		}
		return singleton;
	}

	@Override
	public IPreferenceStore getPluginPreferenceStore() {
		return MonitoringActivator.getDefault().getPreferenceStore();
	}

	@Override
	protected String getPageDescription() {
		return "Preferences for Monitoring Tool";
	}

	@Override
	public String getProtocolIdProperty() {
		return "monitoring_protocol";
	}

	@Override
	public String getServerIdProperty() {
		return "monitoring_server";
	}

	@Override
	public String getPortIdProperty() {
		return "monitoring_port";
	}

	@Override
	protected String getDefaultProtocol() {
		return PROTOCOL.HTTP.name();
	}

	@Override
	protected String getDefaultServer() {
		return "85.120.206.43";
	}

	@Override
	protected int getDefaultPort() {
		return 5001;
	}

}
