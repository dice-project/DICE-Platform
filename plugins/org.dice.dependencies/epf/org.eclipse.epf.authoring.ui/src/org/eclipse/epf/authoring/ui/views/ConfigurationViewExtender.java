package org.eclipse.epf.authoring.ui.views;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;

public class ConfigurationViewExtender {
	
	private ConfigurationView configurationView;
	public ConfigurationView getConfigurationView() {
		return configurationView;
	}

	private ActionBarExtender actionBarExtender;
	
	public ActionBarExtender getActionBarExtender() {
		if (actionBarExtender == null) {
			actionBarExtender = newActionBarExtender();
		}		
		return actionBarExtender;
	}
	
	public ConfigurationViewExtender(ConfigurationView configurationView) {
		this.configurationView = configurationView;
	}
	
	protected ActionBarExtender newActionBarExtender() {
		return new ActionBarExtender(getConfigurationView());
	}
	
	public static class ActionBarExtender {
		private ConfigurationView configurationView;

		public ActionBarExtender(ConfigurationView configurationView) {
			this.configurationView = configurationView;
		}
				
		protected ConfigurationView getConfigurationView() {
			return configurationView;
		}
		
		public void menuAboutToShow(IMenuManager menuManager) {			
		}
		
		public void updateSelection(ISelection selection) {				
		}
		
	}
	
	
}
