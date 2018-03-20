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
		addProtocolField();
		addServerField();
		addPortField();
		addPathField();
	}

	/**
	 * Init the default properties for this Preferences Page
	 */
	public void initDefaults() {
		getPluginPreferenceStore().setDefault(getProtocolIdProperty(), getDefaultProtocol());
		getPluginPreferenceStore().setDefault(getServerIdProperty(), getDefaultServer());
		getPluginPreferenceStore().setDefault(getPortIdProperty(), getDefaultPort());
		getPluginPreferenceStore().setDefault(getPathIdProperty(), getDefaultPath());
	}

	/**
	 * Adds the Protocol Field to the preferences page
	 */
	protected void addProtocolField() {
		addField(new ComboFieldEditor(getProtocolIdProperty(), getProtocolLabel(), new String[][] {
				{ PROTOCOL.HTTP.name(), PROTOCOL.HTTP.name() }, { PROTOCOL.HTTPS.name(), PROTOCOL.HTTPS.name() } },
				getFieldEditorParent()));
	}

	/**
	 * Adds the Server Field to the preferences page
	 */
	protected void addServerField() {
		addField(new StringFieldEditor(getServerIdProperty(), getServerLabel(), getFieldEditorParent()));
	}

	/**
	 * Adds the Port Field to the preferences page
	 */
	protected void addPortField() {
		addField(new IntegerFieldEditor(getPortIdProperty(), getPortLabel(), getFieldEditorParent(), 5));
	}

	/**
	 * Adds the Path Field to the preferences page
	 */
	protected void addPathField() {
		addField(new StringFieldEditor(getPathIdProperty(), getPathLabel(), getFieldEditorParent()));
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
	 * The label for the protocol attribute
	 */
	protected String getProtocolLabel() {
		return "Protocol";
	}

	/**
	 * The label for the server attribute
	 */
	protected String getServerLabel() {
		return "Server";
	}

	/**
	 * The label for the port attribute
	 */
	protected String getPortLabel() {
		return "Port";
	}

	/**
	 * The label for the path attribute
	 */
	protected String getPathLabel() {
		return "Path";
	}

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
	 * The ID of the Path property
	 */
	public abstract String getPathIdProperty();

	/**
	 * The default value for Protocol property
	 */
	protected abstract String getDefaultProtocol();

	/**
	 * The default value for Server property
	 */
	protected abstract String getDefaultServer();

	/**
	 * The default value for Port property
	 */
	protected abstract int getDefaultPort();

	/**
	 * The default value for Path property
	 */
	protected abstract String getDefaultPath();

	protected enum PROTOCOL {
		HTTP, HTTPS;
	}

}
