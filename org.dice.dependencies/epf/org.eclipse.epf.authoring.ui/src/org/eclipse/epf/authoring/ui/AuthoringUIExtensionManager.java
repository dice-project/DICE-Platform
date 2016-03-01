package org.eclipse.epf.authoring.ui;

import org.eclipse.epf.authoring.ui.forms.AssociationFormPage;
import org.eclipse.epf.authoring.ui.forms.DescriptionFormPage;
import org.eclipse.epf.authoring.ui.forms.DescriptionFormPage.DescriptionFormSectionExtender;
import org.eclipse.epf.authoring.ui.forms.TableViewerProviderDelegate;
import org.eclipse.epf.authoring.ui.properties.BreakdownElementGeneralSection;
import org.eclipse.epf.authoring.ui.properties.BreakdownElementGeneralSection.BeGeneralSectionExtender;
import org.eclipse.epf.authoring.ui.providers.FormPageProviderExtender;
import org.eclipse.epf.authoring.ui.views.ConfigurationView;
import org.eclipse.epf.authoring.ui.views.ConfigurationViewExtender;
import org.eclipse.epf.authoring.ui.views.LibraryView;
import org.eclipse.epf.authoring.ui.views.LibraryViewExtender;
import org.eclipse.epf.common.utils.ExtensionHelper;

//Class managing extended functionality for the authoring UI plug-in
public class AuthoringUIExtensionManager {

	private static AuthoringUIExtensionManager instance;

	public AuthoringUIExtensionManager() {
	}

	public static AuthoringUIExtensionManager getInstance() {
		if (instance == null) {
			Object obj = ExtensionHelper.create(
					AuthoringUIExtensionManager.class, null);
			if (obj instanceof AuthoringUIExtensionManager) {
				instance = (AuthoringUIExtensionManager) obj;
			} else {

				instance = new AuthoringUIExtensionManager();
			}
		}
		return instance;
	}
	
	public LibraryViewExtender createLibraryViewExtender(LibraryView libraryView) {
		return new LibraryViewExtender(libraryView);
	}
	
	public ConfigurationViewExtender createConfigurationViewExtender(ConfigurationView configurationView) {
		return new ConfigurationViewExtender(configurationView);
	}
		
	public TableViewerProviderDelegate createTableViewerProviderDelegate() {
		return new TableViewerProviderDelegate();
	}
	
	public FormPageProviderExtender createFormPageProviderExtender(AssociationFormPage formPage) {
		return new FormPageProviderExtender(formPage);
	}
	
	public DescriptionFormSectionExtender createDescriptionFormSectionExtender(DescriptionFormPage formPage) {
		return new DescriptionFormSectionExtender(formPage);
	}
		
	public BeGeneralSectionExtender createBeGeneralSectionExtender(BreakdownElementGeneralSection section) {
		return new BeGeneralSectionExtender(section);
	}
	
}
