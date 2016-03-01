package org.eclipse.epf.authoring.ui.views;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ISelection;

public class LibraryViewExtender {
	
	private LibraryView libraryView;
	public LibraryView getLibraryView() {
		return libraryView;
	}

	private ActionBarExtender actionBarExtender;
	
	public ActionBarExtender getActionBarExtender() {
		if (actionBarExtender == null) {
			actionBarExtender = newActionBarExtender();
		}		
		return actionBarExtender;
	}
	
	public LibraryViewExtender(LibraryView libraryView) {
		this.libraryView = libraryView;
	}
	
	protected ActionBarExtender newActionBarExtender() {
		return new ActionBarExtender(getLibraryView());
	}
	
	public static class ActionBarExtender {
		private LibraryView libraryView;

		public ActionBarExtender(LibraryView libraryView) {
			this.libraryView = libraryView;
		}
				
		protected LibraryView getLibraryView() {
			return libraryView;
		}
		
		public void menuAboutToShow(IMenuManager menuManager) {			
		}
		
		public void updateSelection(ISelection selection) {				
		}
		
		public void contributeToViewMenu(IMenuManager viewMenu) {			
		}
		
		public void decorate(Object element, IDecoration decoration) {
			
		}
		
	}
	
	public void decorate(Object element, IDecoration decoration) {
		 getActionBarExtender().decorate(element, decoration);
	}
	
}
