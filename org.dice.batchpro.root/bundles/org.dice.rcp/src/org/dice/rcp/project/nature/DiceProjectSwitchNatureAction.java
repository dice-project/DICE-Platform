package org.dice.rcp.project.nature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class DiceProjectSwitchNatureAction implements IObjectActionDelegate {

	private ISelection selection;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	public void run(IAction action) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			Object firstElement = structuredSelection.getFirstElement();
			if (!(firstElement instanceof IProject)) {
				return;
			}
			IProject project = (IProject) firstElement;
			try {
				switchDiceNature(project);
			} catch (CoreException e) {
			}
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

	public static void switchDiceNature(IProject project) throws CoreException {
		IProjectDescription description = project.getDescription();
		if (description == null) {
			return;
		}

		List<String> natures = new ArrayList<>();
		natures.addAll(Arrays.asList(description.getNatureIds()));
		if (natures.contains(DiceProjectNature.ID)) {
			natures.remove(DiceProjectNature.ID);
		} else {
			natures.add(DiceProjectNature.ID);
		}

		description.setNatureIds(natures.toArray(new String[0]));

		project.setDescription(description, new NullProgressMonitor());
	}

}