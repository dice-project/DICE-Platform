package org.dice.methodology;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.papyrus.uml.diagram.wizards.wizards.InitModelWizard;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;

public class CreateModelHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Shell activeShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		InitModelWizard wizard = new InitModelWizard();

		ISelectionService selectionService = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		ISelection selection = selectionService.getSelection("org.eclipse.jdt.ui.PackageExplorer");
		if (selection == null) {
			selection = selectionService.getSelection("org.eclipse.ui.views.ResourceNavigator");
		}
		if (selection == null) {
			selection = selectionService.getSelection(IPageLayout.ID_PROJECT_EXPLORER);
		}

		if (selection == null) {
			MessageDialog.openInformation(activeShell, "Select a project",
					"Please, select first a project in the workspace");
			return null;
		}

		wizard.init(PlatformUI.getWorkbench(), (IStructuredSelection) selection);

		WizardDialog wd = new WizardDialog(activeShell, wizard);
		wd.setTitle(wizard.getWindowTitle());
		wd.open();

		return null;
	}

}
