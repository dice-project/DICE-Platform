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
package org.eclipse.epf.authoring.ui.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.action.CopyAction;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.emf.edit.ui.action.CreateSiblingAction;
import org.eclipse.emf.edit.ui.action.CutAction;
import org.eclipse.emf.edit.ui.action.DeleteAction;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.edit.ui.action.LoadResourceAction;
import org.eclipse.emf.edit.ui.action.PasteAction;
import org.eclipse.emf.edit.ui.action.RedoAction;
import org.eclipse.emf.edit.ui.action.UndoAction;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.SubContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;

/**
 * The contributor for a Method editor.
 *  
 * @author Phong Nguyen Le
 * @author Shilpa Toraskar
 * @author Kelvin Low
 * @since 1.0
 */
public class MethodLibraryActionBarContributor extends
		EditingDomainActionBarContributor implements ISelectionChangedListener {

	// Keep track of the current selection provider.
	protected ISelectionProvider selectionProvider;

	// Handle the Show Properties view action.
	protected IAction showPropertiesViewAction = new Action(
			getString("_UI_ShowPropertiesView_menu_item")) { //$NON-NLS-1$
		public void run() {
			try {
				getPage().showView("org.eclipse.ui.views.PropertySheet"); //$NON-NLS-1$
			} catch (PartInitException exception) {
				AuthoringUIPlugin.getDefault().getLogger().logError(exception);
			}
		}
	};

	/**
	 * This action refreshes the viewer of the current editor if the editor
	 * implements {@link org.eclipse.emf.common.ui.viewer.IViewerProvider}.
	 */
	protected IAction refreshViewerAction = new Action(
			getString("_UI_RefreshViewer_menu_item")) { //$NON-NLS-1$
		public boolean isEnabled() {
			return activeEditor instanceof IViewerProvider;
		}

		public void run() {
			doRefresh();
		}
	};

	/**
	 * This will contain one
	 * {@link org.eclipse.emf.edit.ui.action.CreateChildAction} corresponding to
	 * each descriptor generated for the current selection by the item provider.
	 */
	protected Collection createChildActions;

	/**
	 * This is the menu manager into which menu contribution items should be
	 * added for CreateChild actions.
	 */
	protected IMenuManager createChildMenuManager;

	/**
	 * This will contain one
	 * {@link org.eclipse.emf.edit.ui.action.CreateSiblingAction} corresponding
	 * to each descriptor generated for the current selection by the item
	 * provider.
	 */
	protected Collection createSiblingActions;

	/**
	 * This is the menu manager into which menu contribution items should be
	 * added for CreateSibling actions.
	 */
	protected IMenuManager createSiblingMenuManager;

	protected String name;

	protected boolean enabled = true;

	public MethodLibraryActionBarContributor() {
		loadResourceAction = new LoadResourceAction();
		validateAction = new LibraryValidateAction();
		this.name = "MethodElementEditor";  	//$NON-NLS-1$
	}
	
	/**
	 * This creates an instance of the contributor.
	 */
	public MethodLibraryActionBarContributor(String name) {
		loadResourceAction = new LoadResourceAction();
		validateAction = new LibraryValidateAction();
		this.name = name;
	}

	/**
	 * Creates delete action
	 * @return Action
	 */
	protected DeleteAction createDeleteAction() {
		return new DeleteAction(removeAllReferencesOnDelete());
	}

	 /**
	 * 
	 * @see org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor#init(org.eclipse.ui.IActionBars)
	 */
	public void init(IActionBars actionBars) {
		super.init(actionBars);
		
		ISharedImages sharedImages = PlatformUI.getWorkbench()
				.getSharedImages();

		deleteAction = createDeleteAction();
		deleteAction.setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
		actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(),
				deleteAction);

		cutAction = createCutAction();
		cutAction.setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_CUT));
		actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(), cutAction);

		copyAction = createCopyAction();
		copyAction.setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
		actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(),
				copyAction);

		pasteAction = createPasteAction();
		pasteAction.setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
		actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(),
				pasteAction);

		undoAction = new UndoAction();
		undoAction.setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_UNDO));
		actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(),
				undoAction);

		redoAction = new RedoAction();
		redoAction.setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_REDO));
		actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(),
				redoAction);
	}

	/**
	 * Creates copy action
	 * @return Action
	 */
	protected CopyAction createCopyAction() {
		return new CopyAction();
	}

	/**
	 * Creates paste action
	 * @return Action
	 */
	protected PasteAction createPasteAction() {
		return new PasteAction();
	}

	/**
	 * Creates cut action
	 * @return Action
	 */
	protected CutAction createCutAction() {
		return new CutAction();
	}

	/**
	 * This adds Separators for editor additions to the tool bar.
	 */
	public void contributeToToolBar(IToolBarManager toolBarManager) {
		toolBarManager.add(new Separator(name + "-settings")); //$NON-NLS-1$
		toolBarManager.add(new Separator(name + "-additions")); //$NON-NLS-1$
	}

	/**
	 * Sets enabled flag
	 * @param enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * This adds to the menu bar a menu and some separators for editor
	 * additions, as well as the sub-menus for object creation items.
	 */
	public void contributeToMenu(IMenuManager menuManager) {
//		super.contributeToMenu(menuManager);
//		IMenuManager submenuManager = new MenuManager(
//				getString("_UI_" + name + "_menu"), "org.eclipse.epf.authoring." + name + "MenuID"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
//		menuManager.insertAfter("additions", submenuManager); //$NON-NLS-1$
//		submenuManager.add(new Separator("settings")); //$NON-NLS-1$
//		submenuManager.add(new Separator("actions")); //$NON-NLS-1$
//		submenuManager.add(new Separator("additions")); //$NON-NLS-1$
//		submenuManager.add(new Separator("additions-end")); //$NON-NLS-1$
//
//		// Prepare for CreateChild item addition or removal.
//		createChildMenuManager = new MenuManager(
//				getString("_UI_CreateChild_menu_item")); //$NON-NLS-1$
//		submenuManager.insertBefore("additions", createChildMenuManager); //$NON-NLS-1$
//
//		// Prepare for CreateSibling item addition or removal.
//		createSiblingMenuManager = new MenuManager(
//				getString("_UI_CreateSibling_menu_item")); //$NON-NLS-1$
//		submenuManager.insertBefore("additions", createSiblingMenuManager); //$NON-NLS-1$
//
//		// Force an update because Eclipse hides empty menus now.
//		submenuManager.addMenuListener(new IMenuListener() {
//			public void menuAboutToShow(IMenuManager menuManager) {
//				menuManager.updateAll(true);
//			}
//		});
//
//		addGlobalActions(submenuManager);
		
		IMenuManager editMenu= menuManager.findMenuUsingPath(IWorkbenchActionConstants.M_EDIT);
		if (editMenu != null) {
			editMenu.add(new Separator(ITextEditorActionConstants.GROUP_ASSIST));
		}
	}

	/**
	 * When the active editor changes, this remembers the change and registers
	 * with it as a selection provider.
	 */
	public void setActiveEditor(IEditorPart part) {
		super.setActiveEditor(part);

		// Switch to the new selection provider.
		if (selectionProvider != null) {
			selectionProvider.removeSelectionChangedListener(this);
		}
		if (part == null) {
			selectionProvider = null;
		} else {
			selectionProvider = part.getSite().getSelectionProvider();
			selectionProvider.addSelectionChangedListener(this);

			// Fake a selection changed event to update the menus.
			if (selectionProvider.getSelection() != null) {
				selectionChanged(new SelectionChangedEvent(selectionProvider,
						selectionProvider.getSelection()));
			}
		}
	}
	
	@Override
	public void setActivePage(IEditorPart part) {
		if (part != null) {
			setActiveEditor(part);
		}
		
		// Switch to the new selection provider.
		if (selectionProvider != null) {
			selectionProvider.removeSelectionChangedListener(this);
		}
		if (part == null) {
			selectionProvider = null;
		} else {
			selectionProvider = part.getSite().getSelectionProvider();
			selectionProvider.addSelectionChangedListener(this);

			// Fake a selection changed event to update the menus.
			if (selectionProvider.getSelection() != null) {
				selectionChanged(new SelectionChangedEvent(selectionProvider,
						selectionProvider.getSelection()));
			}
		}
	}
	
	/**
	 * This implements
	 * {@link org.eclipse.jface.viewers.ISelectionChangedListener}, handling
	 * {@link org.eclipse.jface.viewers.SelectionChangedEvent} by querying for
	 * the children and siblings that can be added to the selected object and
	 * updating the menus accordingly.
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		// Remove any menu items for old selection.
		if (createChildMenuManager != null) {
			depopulateManager(createChildMenuManager, createChildActions);
		}
		if (createSiblingMenuManager != null) {
			depopulateManager(createSiblingMenuManager, createSiblingActions);
		}

		// Query the new selection for appropriate new child/sibling
		// descriptors.
		Collection newChildDescriptors = null;
		Collection newSiblingDescriptors = null;

		ISelection selection = event.getSelection();
		if (selection instanceof IStructuredSelection
				&& ((IStructuredSelection) selection).size() == 1) {
			Object object = ((IStructuredSelection) selection)
					.getFirstElement();

			EditingDomain domain = ((IEditingDomainProvider) activeEditor)
					.getEditingDomain();

			newChildDescriptors = domain.getNewChildDescriptors(object, null);
			newSiblingDescriptors = domain.getNewChildDescriptors(null, object);
		}

		// Generate actions for selection; populate and redraw the menus.
		createChildActions = generateCreateChildActions(newChildDescriptors,
				selection);
		createSiblingActions = generateCreateSiblingActions(
				newSiblingDescriptors, selection);

		if (createChildMenuManager != null) {
			populateManager(createChildMenuManager, createChildActions, null);
			createChildMenuManager.update(true);
		}
		if (createSiblingMenuManager != null) {
			populateManager(createSiblingMenuManager, createSiblingActions,
					null);
			createSiblingMenuManager.update(true);
		}
		if (!(activeEditor instanceof FormEditor)) {
			update();
		}
	}

	/**
	 * This generates a {@link org.eclipse.emf.edit.ui.action.CreateChildAction}
	 * for each object in <code>descriptors</code>, and returns the
	 * collection of these actions.
	 */
	protected Collection generateCreateChildActions(Collection descriptors,
			ISelection selection) {
		Collection actions = new ArrayList();
		if (descriptors != null) {
			for (Iterator i = descriptors.iterator(); i.hasNext();) {
				actions.add(new CreateChildAction(activeEditor, selection,
						i.next()));
			}
		}
		return actions;
	}

	/**
	 * This generates a
	 * {@link org.eclipse.emf.edit.ui.action.CreateSiblingAction} for each
	 * object in <code>descriptors</code>, and returns the collection of
	 * these actions.
	 */
	protected Collection generateCreateSiblingActions(Collection descriptors,
			ISelection selection) {
		Collection actions = new ArrayList();
		if (descriptors != null) {
			for (Iterator i = descriptors.iterator(); i.hasNext();) {
				actions.add(new CreateSiblingAction(activeEditor,
						selection, i.next()));
			}
		}
		return actions;
	}

	/**
	 * This populates the specified <code>manager</code> with
	 * {@link org.eclipse.jface.action.ActionContributionItem}s based on the
	 * {@link org.eclipse.jface.action.IAction}s contained in the
	 * <code>actions</code> collection, by inserting them before the specified
	 * contribution item <code>contributionID</code>. If <code>ID</code> is
	 * <code>null</code>, they are simply added.
	 */
	protected void populateManager(IContributionManager manager,
			Collection actions, String contributionID) {
		if (actions != null) {
			for (Iterator i = actions.iterator(); i.hasNext();) {
				IAction action = (IAction) i.next();
				if (contributionID != null) {
					manager.insertBefore(contributionID, action);
				} else {
					manager.add(action);
				}
			}
		}
	}

	/**
	 * This removes from the specified <code>manager</code> all
	 * {@link org.eclipse.jface.action.ActionContributionItem}s based on the
	 * {@link org.eclipse.jface.action.IAction}s contained in the
	 * <code>actions</code> collection.
	 */
	protected void depopulateManager(IContributionManager manager,
			Collection actions) {
		if (actions != null) {
			IContributionItem[] items = manager.getItems();
			for (int i = 0; i < items.length; i++) {
				// Look into SubContributionItems
				IContributionItem contributionItem = items[i];
				while (contributionItem instanceof SubContributionItem) {
					contributionItem = ((SubContributionItem) contributionItem)
							.getInnerItem();
				}

				// Delete the ActionContributionItems with matching action.
				if (contributionItem instanceof ActionContributionItem) {
					IAction action = ((ActionContributionItem) contributionItem)
							.getAction();
					if (actions.contains(action)) {
						manager.remove(contributionItem);
					}
				}
			}
		}
	}
	
	protected void updatePasteAction() {
		ISelectionProvider selectionProvider = activeEditor instanceof ISelectionProvider ? (ISelectionProvider) activeEditor
				: activeEditor.getEditorSite().getSelectionProvider();

		if (selectionProvider != null) {
			ISelection selection = selectionProvider.getSelection();
			IStructuredSelection structuredSelection = selection instanceof IStructuredSelection ? (IStructuredSelection) selection
					: StructuredSelection.EMPTY;

			pasteAction.setEnabled(pasteAction.updateSelection(structuredSelection));
		}
	}

	/**
	 * This populates the pop-up menu before it appears.
	 */
	public void menuAboutToShow(IMenuManager menuManager) {
		// This is needed to enable Paste after Copy but the selection has not been changed yet
		//
		updatePasteAction();
		
		// Add our standard marker.
		menuManager.add(new Separator("additions")); //$NON-NLS-1$
		menuManager.add(new Separator("edit")); //$NON-NLS-1$

		// Add the edit menu actions.
		menuManager.add(new ActionContributionItem(undoAction));
		menuManager.add(new ActionContributionItem(redoAction));
		menuManager.add(new Separator());
		// menuManager.add(new ActionContributionItem(cutAction));
		menuManager.add(new ActionContributionItem(copyAction));
		menuManager.add(new ActionContributionItem(pasteAction));
		menuManager.add(new Separator());
		menuManager.add(new ActionContributionItem(deleteAction));
		menuManager.add(new Separator());

		// Add our other standard marker.
		menuManager.add(new Separator("additions-end")); //$NON-NLS-1$

		addGlobalActions(menuManager);

		if (enabled) {
			MenuManager submenuManager = null;
			submenuManager = new MenuManager(
					getString("_UI_CreateChild_menu_item")); //$NON-NLS-1$
			populateManager(submenuManager, createChildActions, null);
			menuManager.insertBefore("additions", submenuManager); //$NON-NLS-1$

			submenuManager = new MenuManager(
					getString("_UI_CreateSibling_menu_item")); //$NON-NLS-1$
			populateManager(submenuManager, createSiblingActions, null);
			menuManager.insertBefore("additions", submenuManager); //$NON-NLS-1$
		}
	}

	/**
	 * This inserts global actions before the "additions-end" separator.
	 */
	protected void addGlobalActions(IMenuManager menuManager) {
		menuManager.insertAfter("additions-end", new Separator("ui-actions")); //$NON-NLS-1$ //$NON-NLS-2$
		menuManager.insertAfter("ui-actions", showPropertiesViewAction); //$NON-NLS-1$

		refreshViewerAction.setEnabled(refreshViewerAction.isEnabled());
		menuManager.insertAfter("ui-actions", refreshViewerAction); //$NON-NLS-1$
		
	    String key = (style & ADDITIONS_LAST_STYLE) == 0 ? "additions-end" : "additions"; //$NON-NLS-1$ //$NON-NLS-2$
	    if (validateAction != null)
	    {
	      menuManager.insertBefore(key, new ActionContributionItem(validateAction));
	    }
	}

	protected String getString(String key) {
		return AuthoringUIPlugin.getDefault().getString(key);
	}

	/**
	 * Refresh the viewer
	 *
	 */
	protected void doRefresh() {
		if (activeEditor instanceof IViewerProvider) {
			Viewer viewer = ((IViewerProvider) activeEditor).getViewer();
			if (viewer != null) {
				viewer.refresh();
			}
		}
	}

	protected boolean removeAllReferencesOnDelete()
	{
		return false;
	}
	
	@Override
	public void activate() {
		// fix actions -- if new page is an IFormPage, we don't want EMF actions
		if (activeEditor != null) {
			IActionBars actionBars = activeEditor.getEditorSite()
				.getActionBars();
			if (activeEditor instanceof FormEditor) {
				IFormPage page = ((FormEditor)activeEditor).getActivePageInstance(); 
				if (page instanceof IFormPage) {			
					actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(),
							null);
					actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(),
							null);
					actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(),
							null);
					actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(),
							null);
					actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(),
							null);
					actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(),
							null);
				}
			} else {
				actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(),
						deleteAction);
				actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(),
						cutAction);
				actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(),
						copyAction);
				actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(),
						pasteAction);
				actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(),
						undoAction);
				actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(),
						redoAction);
			}
			actionBars.updateActionBars();
		}
		super.activate();
	}
}
