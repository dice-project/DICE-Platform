package org.dice.quality_testing.handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.eclipse.m2e.core.ui.internal.actions.EnableNatureAction;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

@SuppressWarnings("restriction")
public class QualityTestingHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (window == null) {
			MessageDialog.openError(Display.getCurrent().getActiveShell(), "DICE Quality Testing",
					"No actived workbench available");
			return null;
		}

		IStructuredSelection selection = (IStructuredSelection) window.getSelectionService().getSelection();
		if (selection == null) {
			MessageDialog.openError(Display.getCurrent().getActiveShell(), "DICE Quality Testing",
					"You need to select a project");
			return null;
		}

		Object firstElement = selection.getFirstElement();
		if (!(firstElement instanceof IAdaptable)) {
			MessageDialog.openError(Display.getCurrent().getActiveShell(), "DICE Quality Testing",
					"You need to select a project");
			return null;
		}

		IProject project = ((IAdaptable) firstElement).getAdapter(IProject.class);
		if (project == null) {
			MessageDialog.openError(Display.getCurrent().getActiveShell(), "DICE Quality Testing",
					"You need to select a project");
			return null;
		}

		if (isMavenProject(project)) {
			MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "DICE Quality Testing",
					"The project has the Maven Nature installed");
		} else {
			boolean answer = MessageDialog.openConfirm(Display.getCurrent().getActiveShell(),
					"DICE Quality Testing - Convert to Maven Project",
					"Do you want to convert the selected project '" + project.getName() + "' into a Maven Project?");
			if (!answer) {
				return null;
			}

			convertIntoMavenProject(project);

			MessageDialog.openConfirm(Display.getCurrent().getActiveShell(),
					"DICE Quality Testing - Convert to Maven Project", "Your project has now the Maven Nature");
		}

		StringBuilder sb = new StringBuilder();
		sb.append("<dependencies>\n");
		sb.append("\t<dependency>\n");
		sb.append("\t\t<groupId>com.github.dice-project</groupId>\n");
		sb.append("\t\t<artifactId>qt-lib</artifactId>\n");
		sb.append("\t\t<version>1.0.0</version>\n");
		sb.append("\t</dependency>\n");
		sb.append("</dependencies>");

		InputDialog dlg = new InputDialog(Display.getCurrent().getActiveShell(), "DICE Quality Testing",
				"You need to include the following dependency into your pom.xml file", sb.toString(), null) {
			/**
			 * Override this method to make the text field multilined and give
			 * it a scroll bar. But...
			 */
			@Override
			protected int getInputTextStyle() {
				return SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.READ_ONLY;
			}

			/**
			 * ...it still is just one line high. This hack is not very nice,
			 * but at least it gets the job done... ;o)
			 */
			@Override
			protected Control createDialogArea(Composite parent) {
				Control res = super.createDialogArea(parent);
				((GridData) this.getText().getLayoutData()).heightHint = 150;
				return res;
			}
		};
		dlg.open();

		return null;
	}

	private boolean isMavenProject(IProject project) {
		IProjectDescription description;
		try {
			description = project.getDescription();
			if (description == null) {
				return false;
			}
		} catch (CoreException e) {
			return false;
		}

		List<String> natures = new ArrayList<>();
		natures.addAll(Arrays.asList(description.getNatureIds()));

		return natures.contains(IMavenConstants.NATURE_ID);
	}

	private void convertIntoMavenProject(IProject project) {
		EnableNatureAction action = new EnableNatureAction();
		action.selectionChanged(null,
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection());
		action.run(null);
	}

}
