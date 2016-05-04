package org.dice.optimization.preferences.pages;

import org.dice.optimization.OptimizationActivator;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class OptimizationPreferencesPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	private static final String TITLE = "Optimization Tool";
	private static final String DESCRIPTION = "Preferences for Optimization Tool";

	public static final String OPTIMIZATION_PROTOCOL = "optimization_protocol";
	public static final String OPTIMIZATION_SERVER = "optimization_server";
	public static final String OPTIMIZATION_PORT = "optimization_port";

	public OptimizationPreferencesPage() {
		this(GRID);
	}

	public OptimizationPreferencesPage(int style) {
		this(TITLE, style);
	}

	public OptimizationPreferencesPage(String title, int style) {
		this(title, null, style);
	}

	public OptimizationPreferencesPage(String title, ImageDescriptor image, int style) {
		super(title, image, style);
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(OptimizationActivator.getDefault().getPreferenceStore());
		setDescription(DESCRIPTION);
	}

	@Override
	protected void createFieldEditors() {
		addField(new ComboFieldEditor(OPTIMIZATION_PROTOCOL, "Protocol",
				new String[][] { { "http", "http" }, { "https", "https" } }, getFieldEditorParent()));
		addField(new StringFieldEditor(OPTIMIZATION_SERVER, "Server", getFieldEditorParent()));
		addField(new IntegerFieldEditor(OPTIMIZATION_PORT, "Port", getFieldEditorParent(), 5));
	}

	public static void initDefaults(IPreferenceStore store) {
		store.setDefault(OPTIMIZATION_PROTOCOL, "http");
		store.setDefault(OPTIMIZATION_SERVER, "8specclient1.dei.polimi.it");
		store.setDefault(OPTIMIZATION_PORT, 8017);
	}

}
