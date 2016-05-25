package org.dice.simulation.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import es.unizar.disco.simulation.ui.launcher.SimulationLaunchShortcut;

public class SimulationHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ILaunchShortcut launchShortcut = new SimulationLaunchShortcut();

		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		IEditorPart editorPart = window.getActivePage().getActiveEditor();

		if (editorPart != null) {
			launchShortcut.launch(editorPart, ILaunchManager.RUN_MODE);
			return null;
		}

		ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
		if (!selection.isEmpty()) {
			launchShortcut.launch(selection, ILaunchManager.RUN_MODE);
			return null;
		}

		MessageDialog.openError(window.getShell(), "Could not open the Simulation Tool",
				"Please, select or open an UML model in the Project Explorer");

		return null;
	}

}
