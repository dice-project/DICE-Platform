package org.dice.monitoring.preferences.pages;

import org.dice.monitoring.MonitoringActivator;
import org.dice.ui.preferences.pages.AbstractOpenBrowserPreferencesPage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;

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
	protected void createFieldEditors() {
		super.createFieldEditors();
		addVisualizationPortField();
	}

	@Override
	public void initDefaults() {
		super.initDefaults();
		getPluginPreferenceStore().setDefault(getVisualizationPortIdProperty(), getDefaultVisualizationPort());
	}

	private void addVisualizationPortField() {
		addField(new IntegerFieldEditor(getVisualizationPortIdProperty(), getVisualizationPortLabel(),
				getFieldEditorParent(), 5));
	}

	@Override
	protected String getPageDescription() {
		return "Preferences for Monitoring Tool";
	}

	@Override
	protected String getPortLabel() {
		return "Admin Port";
	}

	/**
	 * The label for the visualization port attribute
	 */
	private String getVisualizationPortLabel() {
		return "Visualization Port";
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
	public String getPathIdProperty() {
		return "monitoring_path";
	}

	/**
	 * The ID of the Visualization Port property
	 */
	public String getVisualizationPortIdProperty() {
		return "monitoring_visualization_port";
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

	@Override
	protected String getDefaultPath() {
		return "";
	}

	/**
	 * The default value for visualization port property
	 */
	private int getDefaultVisualizationPort() {
		return 5601;
	}

}
