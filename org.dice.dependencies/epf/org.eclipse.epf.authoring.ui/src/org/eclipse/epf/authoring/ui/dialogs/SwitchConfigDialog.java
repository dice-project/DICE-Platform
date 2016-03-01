//------------------------------------------------------------------------------
// Copyright (c) 2005, 2006 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.authoring.ui.dialogs;

import org.eclipse.epf.authoring.ui.editors.AbstractDiagramEditor;
import org.eclipse.epf.authoring.ui.editors.BreakdownElementEditorInput;
import org.eclipse.epf.authoring.ui.editors.EditorChooser;
import org.eclipse.epf.authoring.ui.editors.ProcessEditor;
import org.eclipse.epf.common.ui.util.MsgBox;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.ui.LibraryUIPlugin;
import org.eclipse.epf.library.ui.LibraryUIResources;
import org.eclipse.epf.library.ui.preferences.LibraryUIPreferences;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.util.Scope;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;

/**
 * Prompts the user to switch the current Configuration to a recommended
 * Configuration.
 * 
 * @author Jeff Hardy
 * @author Kelvin Low
 * @since 1.0
 */
public class SwitchConfigDialog {

	private Shell shell;
	
	private IEditorPart part;

	/**
	 * Creates a new instance.
	 * 
	 * @param shell
	 *            The parent shell.
	 * @param recommendedConfig
	 *            The recommended configuration.
	 */
	public SwitchConfigDialog(Shell shell, IEditorPart part) {
		this.shell = shell;
		this.part = part;
	}

	/**
	 * Displays the dialog iff the given recommendedConfig is different than the
	 * currently selected config
	 * 
	 * returns IDialogConstants.OK_ID if dialog wasn't shown, or if config was
	 * switched returns IDialogConstants.CANCEL_ID if dialog was shown and user
	 * did not click Yes
	 */
	public void execute() {
		Display display = shell != null ? shell.getDisplay() : null;
		if (display == null) {
			display = MsgBox.getDisplay();
		}
		if (display == null) {
			// could not get display
			return;
		}
		display.asyncExec(new Runnable() {
			public void run() {
				Object obj = null;
				if (part instanceof AbstractDiagramEditor) {
					if (((AbstractDiagramEditor)part).isDisposed()) {
						return;
					}
					BreakdownElementEditorInput input = (BreakdownElementEditorInput) ((IEditorPart) part)
							.getEditorInput();
					if (input.getSuppression().getProcess() != null) {
						obj = input.getSuppression().getProcess().eContainer();
					}
				} else if (part instanceof ProcessEditor) {
					if (((ProcessEditor)part).isDisposed()) {
						return;
					}
					obj = EditorChooser.getInstance().getMethodEditorInput(part);
				}
				if (obj != null && obj instanceof ProcessComponent) {
					MethodConfiguration recommendedConfig = ((ProcessComponent) obj).getProcess().getDefaultContext();
					if (recommendedConfig instanceof Scope || recommendedConfig == null) {
						return;
					}

					String switchConfigPref = LibraryUIPreferences.getSwitchConfig();
					if (MessageDialogWithToggle.NEVER.equals(switchConfigPref)) {
						// Call this to refresh active part if needed.
						// TODO: Review implementation.
//						LibraryService.getInstance().setCurrentMethodConfiguration(
//								LibraryService.getInstance()
//										.getCurrentMethodConfiguration());
						return;
					}
					if (LibraryService.getInstance().getCurrentMethodConfiguration() != recommendedConfig) {
						final String configName = recommendedConfig.getName();
						if (MessageDialogWithToggle.PROMPT.equals(switchConfigPref)) {
							MessageDialogWithToggle dialog = MessageDialogWithToggle
							.openYesNoQuestion(shell,
									LibraryUIResources.switchConfigDialog_title,
									LibraryUIResources
									.bind(
											LibraryUIResources.switchConfigDialog_text,
											configName),
									null, false, LibraryUIPlugin.getDefault()
											.getPreferenceStore(),
									LibraryUIPreferences.getSwitchConfigPreferenceKey());
							if (dialog.getReturnCode() == IDialogConstants.YES_ID) {
								MethodConfiguration config = LibraryServiceUtil
										.getMethodConfiguration(LibraryService
												.getInstance().getCurrentMethodLibrary(),
												configName);
								LibraryService.getInstance().setCurrentMethodConfiguration(
										config);
							} else {
								// Call this to refresh active part if needed.
								// TODO: Review implementation.
								LibraryService.getInstance().setCurrentMethodConfiguration(
										LibraryService.getInstance()
												.getCurrentMethodConfiguration());
							}
							
						} else if (MessageDialogWithToggle.ALWAYS.equals(switchConfigPref)) {
							MethodConfiguration config = LibraryServiceUtil
									.getMethodConfiguration(LibraryService.getInstance()
											.getCurrentMethodLibrary(), configName);
							LibraryService.getInstance().setCurrentMethodConfiguration(
									config);
						}
					}
				}
			}
		});
	}
	
	public static void run(Shell shell, IEditorPart part) {
		SwitchConfigDialog dialog = new SwitchConfigDialog(Display
				.getCurrent().getActiveShell(), (IEditorPart)part);
		dialog.execute();
	}

}
