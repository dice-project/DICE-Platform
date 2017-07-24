package org.dice.rcp.prespective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class DicePerspective implements IPerspectiveFactory {

	public static final String ID = "org.dice.rcp.perspective";

	public void createInitialLayout(IPageLayout layout) {
		defineActions(layout);
		defineLayout(layout);
	}

	private void defineActions(IPageLayout layout) {
		// Add File New Item
		layout.addNewWizardShortcut("org.dice.rcp.wizard.project.generic");
		layout.addNewWizardShortcut("org.dice.rcp.wizard.project.qualitytesting");
		layout.addNewWizardShortcut("org.eclipse.papyrus.uml.diagram.wizards.createproject");
		layout.addNewWizardShortcut("org.eclipse.papyrus.uml.diagram.wizards.createmodel");

		// Add "show views".
		layout.addShowViewShortcut(IPageLayout.ID_PROJECT_EXPLORER);
		layout.addShowViewShortcut("org.eclipse.papyrus.views.modelexplorer.modelexplorer");
		layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
		layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
		layout.addShowViewShortcut("org.eclipse.ui.console.ConsoleView");
		layout.addShowViewShortcut("org.tigris.subversion.subclipse.ui.repository.RepositoriesView");
		layout.addShowViewShortcut("org.eclipse.egit.ui.RepositoriesView");
		layout.addShowViewShortcut("es.unizar.disco.simulation.ui.views.InvocationsView");
		layout.addShowViewShortcut("org.eclipse.ui.cheatsheets.views.CheatSheetView");

		// Add Perspectives
		layout.addPerspectiveShortcut(ID);
		layout.addPerspectiveShortcut("org.eclipse.papyrus.infra.core.perspective");
		layout.addPerspectiveShortcut("org.eclipse.jdt.ui.JavaPerspective");
	}

	private void defineLayout(IPageLayout layout) {
		layout.createFolder("left", IPageLayout.LEFT, 0.2f, layout.getEditorArea());
		layout.createFolder("leftTop", IPageLayout.TOP, 0.33f, "left").addView(IPageLayout.ID_PROJECT_EXPLORER);
		layout.createFolder("leftMiddle", IPageLayout.TOP, 0.5f, "left")
				.addView("org.eclipse.papyrus.views.modelexplorer.modelexplorer");
		layout.createFolder("leftBottom", IPageLayout.TOP, 0.5f, "left").addView(IPageLayout.ID_OUTLINE);

		layout.createFolder("bottom", IPageLayout.BOTTOM, 0.7f, layout.getEditorArea());
		IFolderLayout bottomTabs = layout.createFolder("bottomTabs", IPageLayout.TOP, 1f, "bottom");
		bottomTabs.addPlaceholder("*");
		bottomTabs.addView(IPageLayout.ID_PROP_SHEET);
		bottomTabs.addView("org.eclipse.ui.console.ConsoleView");
		bottomTabs.addView("org.tigris.subversion.subclipse.ui.repository.RepositoriesView");
		bottomTabs.addView("org.eclipse.egit.ui.RepositoriesView");
		bottomTabs.addView("es.unizar.disco.simulation.ui.views.InvocationsView");
		bottomTabs.addView("org.eclipse.ui.cheatsheets.views.CheatSheetView");
	}
}
