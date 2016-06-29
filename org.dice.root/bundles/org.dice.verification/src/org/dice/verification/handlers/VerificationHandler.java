package org.dice.verification.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import it.polimi.dice.verification.DiceVerificationPlugin;

public class VerificationHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// ILaunchShortcut launchShortcut = new VerificationLaunchShortcut();
		//
		// IWorkbenchWindow window =
		// HandlerUtil.getActiveWorkbenchWindowChecked(event);
		// IEditorPart editorPart = window.getActivePage().getActiveEditor();
		//
		// if (editorPart != null) {
		// launchShortcut.launch(editorPart, ILaunchManager.RUN_MODE);
		// }
		//
		// ISelection selection =
		// PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
		// if (!selection.isEmpty()) {
		// launchShortcut.launch(selection, ILaunchManager.RUN_MODE);
		// return null;
		// }
		//
		// MessageDialog.openError(window.getShell(), "Could not open the
		// Verification Tool",
		// "Please, select or open an UML model in the Project Explorer");

		ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
		String configTypeId = DiceVerificationPlugin.VERIFICATION_LAUNCH_CONFIGURATION_TYPE;
		configTypeId = "it.polimi.dice.verification.launchConfigurationType1";
		ILaunchConfigurationType configType = manager.getLaunchConfigurationType(configTypeId);

		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		DebugUITools.openLaunchConfigurationDialogOnGroup(window.getShell(), new StructuredSelection(configType),
				IDebugUIConstants.ID_RUN_LAUNCH_GROUP, null);

		return null;
	}

}
