package org.dice.ui.preferences.pages;

import org.dice.ui.MonitoringActivator;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class MonitoringPreferencesPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	private static final String TITLE = "Monitoring Tools";
	private static final String DESCRIPTION = "Preferences for Monitoring Tools";

	public static final String MONITORING_PROTOCOL = "monitoring_protocol";
	public static final String MONITORING_SERVER = "monitoring_server";
	public static final String MONITORING_PORT = "monitoring_port";

	public MonitoringPreferencesPage() {
		this(GRID);
	}

	public MonitoringPreferencesPage(int style) {
		this(TITLE, style);
	}

	public MonitoringPreferencesPage(String title, int style) {
		this(title, null, style);
	}

	public MonitoringPreferencesPage(String title, ImageDescriptor image, int style) {
		super(title, image, style);
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(MonitoringActivator.getDefault().getPreferenceStore());
		setDescription(DESCRIPTION);
	}

	@Override
	protected void createFieldEditors() {
		addField(new ComboFieldEditor(MONITORING_PROTOCOL, "Protocol",
				new String[][] { { "http", "http" }, { "https", "https" } }, getFieldEditorParent()));
		addField(new StringFieldEditor(MONITORING_SERVER, "Server", getFieldEditorParent()));
		addField(new IntegerFieldEditor(MONITORING_PORT, "Port", getFieldEditorParent(), 5));
	}

	public static void initDefaults(IPreferenceStore store) {
		store.setDefault(MONITORING_PROTOCOL, "http");
		store.setDefault(MONITORING_SERVER, "85.120.206.43");
		store.setDefault(MONITORING_PORT, 5001);
	}

}
