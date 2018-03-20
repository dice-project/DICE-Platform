package org.dice.ui.preferences.pages;

import org.dice.ui.DiceUIActivator;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class DicePreferencesPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	private static final String TITLE = "DICE";
	private static final String DESCRIPTION = "Preferences for DICE";

	public static final String DICE_BROWSER = "dice_browser";

	public DicePreferencesPage() {
		this(GRID);
	}

	public DicePreferencesPage(int style) {
		this(TITLE, style);
	}

	public DicePreferencesPage(String title, int style) {
		this(title, null, style);
	}

	public DicePreferencesPage(String title, ImageDescriptor image, int style) {
		super(title, image, style);
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(DiceUIActivator.getDefault().getPreferenceStore());
		setDescription(DESCRIPTION);
	}

	@Override
	protected void createFieldEditors() {
		addField(new BooleanFieldEditor(DICE_BROWSER, "Use Eclipse internal browser to open DICE External Tools",
				getFieldEditorParent()));
	}

	public static void initDefaults(IPreferenceStore store) {
		store.setDefault(DICE_BROWSER, true);
	}

}
