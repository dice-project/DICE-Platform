package org.dice.ui.preferences.pages;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public abstract class AbstractOpenBrowserPreferencesPage extends FieldEditorPreferencePage
		implements IWorkbenchPreferencePage {

	public AbstractOpenBrowserPreferencesPage() {
		super(GRID);
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(getPluginPreferenceStore());
		setDescription(getPageDescription());
	}

	@Override
	protected void createFieldEditors() {
		addField(new ComboFieldEditor(getProtocolIdProperty(), "Protocol", new String[][] {
				{ PROTOCOL.HTTP.name(), PROTOCOL.HTTP.name() }, { PROTOCOL.HTTPS.name(), PROTOCOL.HTTPS.name() } },
				getFieldEditorParent()));
		addField(new StringFieldEditor(getServerIdProperty(), "Server", getFieldEditorParent()));
		addField(new IntegerFieldEditor(getPortIdProperty(), "Port", getFieldEditorParent(), 5));
	}

	/**
	 * Init the default properties for this Preferences Page
	 */
	public void initDefaults() {
		getPluginPreferenceStore().setDefault(getProtocolIdProperty(), getDefaultProtocol());
		getPluginPreferenceStore().setDefault(getServerIdProperty(), getDefaultServer());
		getPluginPreferenceStore().setDefault(getPortIdProperty(), getDefaultPort());
	}

	/**
	 * The Preferences Store of this plugin (you need to implement this method,
	 * not {@link PreferencePage#getPreferencesStore} one: the behavior is
	 * different)
	 */
	public abstract IPreferenceStore getPluginPreferenceStore();

	/**
	 * The description for this Preferences Page
	 */
	protected abstract String getPageDescription();

	/**
	 * The ID of the Protocol property
	 */
	public abstract String getProtocolIdProperty();

	/**
	 * The ID of the Server property
	 */
	public abstract String getServerIdProperty();

	/**
	 * The ID of the Port property
	 */
	public abstract String getPortIdProperty();

	/**
	 * The default value for Protocol property
	 */
	protected abstract String getDefaultProtocol();

	/**
	 * The default value for Server property
	 */
	protected abstract String getDefaultServer();

	/**
	 * The default valur for Port property
	 */
	protected abstract int getDefaultPort();

	protected enum PROTOCOL {
		HTTP, HTTPS;
	}

	protected enum PORT {
		P_80(80), P_8080(8080);

		public int port;

		private PORT(int port) {
			this.port = port;
		}
	}

}
