package org.dice.optimization.preferences.pages;

import org.dice.optimization.OptimizationActivator;
import org.dice.ui.preferences.pages.AbstractOpenBrowserPreferencesPage;
import org.eclipse.jface.preference.IPreferenceStore;

public class OptimizationPreferencesPage extends AbstractOpenBrowserPreferencesPage {

	private static OptimizationPreferencesPage singleton;

	public static OptimizationPreferencesPage getSingleton() {
		if (singleton == null) {
			singleton = new OptimizationPreferencesPage();
		}
		return singleton;
	}

	@Override
	public IPreferenceStore getPluginPreferenceStore() {
		return OptimizationActivator.getDefault().getPreferenceStore();
	}

	@Override
	protected String getPageDescription() {
		return "Preferences for Optimization Tool";
	}

	@Override
	public String getProtocolIdProperty() {
		return "optimization_protocol";
	}

	@Override
	public String getServerIdProperty() {
		return "optimization_server";
	}

	@Override
	public String getPortIdProperty() {
		return "optimization_port";
	}

	@Override
	protected String getDefaultProtocol() {
		return PROTOCOL.HTTP.name();
	}

	@Override
	protected String getDefaultServer() {
		return "8specclient1.dei.polimi.it";
	}

	@Override
	protected int getDefaultPort() {
		return 8017;
	}

}
