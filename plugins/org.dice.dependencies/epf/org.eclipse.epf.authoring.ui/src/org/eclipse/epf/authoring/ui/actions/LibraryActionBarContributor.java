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
import org.eclipse.emf.edit.ui.action.CutAction;
import org.eclipse.emf.edit.ui.action.DeleteAction;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.edit.ui.action.PasteAction;
import org.eclipse.emf.edit.ui.action.RedoAction;
import org.eclipse.emf.edit.ui.action.UndoAction;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.editors.ProcessEditor;
import org.eclipse.epf.validation.LibraryEValidator;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IMenuListener;
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
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

/**
 * The Library view action bar contributor.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class LibraryActionBarContributor extends
		EditingDomainActionBarContributor implements
		ILibraryActionBarContributor, ISelectionChangedListener {

	// Keep track of the active editor.
	protected IViewPart activeViewPart;

	// Keep track of the current selection provider.
	protected ISelectionProvider selectionProvider;

	protected IActionBars viewActionBars;

	// Library view edit action
	protected LibraryViewEditAction libraryViewEditAction;

	// Configuration view edit action
	protected ConfigurationViewEditAction configViewEditAction;

	private EditingDomain editingDomain;
	
	/**
	 * Displays the Properties view.
	 */
	protected IAction showPropertiesViewAction = new Action(
			AuthoringUIResources._UI_ShowPropertiesView_menu_item) { 
		public void run() {
			try {
				try {
					getPage().showView("org.eclipse.ui.views.PropertySheet"); //$NON-NLS-1$
				} catch (PartInitException exception) {
					exception.printStackTrace();
				}
			} catch (RuntimeException e) {
				AuthoringUIPlugin.getDefault().getLogger().logError(e);
			}
		}
	};

	/**
	 * Refreshes the viewer of the current editor if the editor implements
	 * {@link org.eclipse.emf.common.ui.viewer.IViewerProvider}.
	 */
	protected IAction refreshViewerAction = new Action(
			AuthoringUIResources._UI_RefreshViewer_menu_item) { 
		public boolean isEnabled() {
			return activeViewPart instanceof IViewerProvider;
		}

		public void run() {
			if (activeViewPart instanceof IViewerProvider) {
				Viewer viewer = ((IViewerProvider) activeViewPart).getViewer();
				if (viewer != null) {
					refreshViewer(viewer);
				}
			}
		}
	};

	/**
	 * This will contain one
	 * {@link org.eclipse.emf.edit.ui.action.CreateChildAction} corresponding to
	 * each descriptor generated for the current selection by the item provider.
	 */
	protected Collection createChildActions;

	/**
	 * The menu manager into which menu contribution items should be added for
	 * CreateChild actions.
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

	/**
	 * Creates a new instance.
	 */
	public LibraryActionBarContributor(EditingDomain editingDomain) {
		this.editingDomain = editingDomain;
		
		validateAction = new LibraryValidateAction();
		((LibraryValidateAction)validateAction).putContextData(LibraryEValidator.CTX_ADAPTER_FACTORY_PROVIDER,
				ProcessEditor.adapterFactoryProvider);
	}

	/**
	 * @param viewer
	 */
	protected void refreshViewer(Viewer viewer) {
		viewer.refresh();
	}

	/**
	 * Adds Separators for editor additions to the tool bar.
	 */
	public void contributeToToolBar(IToolBarManager toolBarManager) {
		toolBarManager.add(new Separator("method-settings")); //$NON-NLS-1$
		toolBarManager.add(new Separator("method-additions")); //$NON-NLS-1$
	}

	/**
	 * Adds to the menu bar a menu and some separators for editor
	 * additions, as well as the sub-menus for object creation items.
	 */
	public void contributeToMenu(IMenuManager menuManager) {
		super.contributeToMenu(menuManager);

		IMenuManager submenuManager = new MenuManager(
				AuthoringUIResources._UI_MethodEditor_menu, "com.ibm.methodMenuID"); //$NON-NLS-1$  
		menuManager.insertAfter("additions", submenuManager); //$NON-NLS-1$
		submenuManager.add(new Separator("settings")); //$NON-NLS-1$
		submenuManager.add(new Separator("actions")); //$NON-NLS-1$
		submenuManager.add(new Separator("additions")); //$NON-NLS-1$
		submenuManager.add(new Separator("additions-end")); //$NON-NLS-1$

		// Prepare for CreateChild item addition or removal.
		createChildMenuManager = new MenuManager(
				AuthoringUIResources._UI_CreateChild_menu_item); 
		submenuManager.insertBefore("additions", createChildMenuManager); //$NON-NLS-1$

		// Prepare for CreateSibling item addition or removal.
		createSiblingMenuManager = new MenuManager(
				AuthoringUIResources._UI_CreateSibling_menu_item); 
		submenuManager.insertBefore("additions", createSiblingMenuManager); //$NON-NLS-1$

		// Force an update because Eclipse hides empty menus now.
		submenuManager.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager menuManager) {
				menuManager.updateAll(true);
			}
		});

		addGlobalActions(submenuManager);
	}

	public IViewPart getActiveView() {
		return activeViewPart;
	}

	public void setActiveView(IViewPart part) {
		// TODO - may need to check other method
		if (part != null)
			super.setActiveView(part);
		if (part == activeViewPart)
			return;
		if (activeViewPart != null) {
			deactivate();
		}
		if (part == null) {
			selectionProvider = null;
		}
		if (!(part instanceof IEditingDomainProvider))
			return;
		activeViewPart = part;
		editingDomain = ((IEditingDomainProvider) part).getEditingDomain();

		// Switch to the new selection provider.
		if (selectionProvider != null) {
			selectionProvider.removeSelectionChangedListener(this);
		} else {
			selectionProvider = part.getSite().getSelectionProvider();
			selectionProvider.addSelectionChangedListener(this);

			// Fake a selection changed event to update the menus.
			if (selectionProvider.getSelection() != null) {
				selectionChanged(new SelectionChangedEvent(selectionProvider,
						selectionProvider.getSelection()));
			}
		}

		activate();

	}

	public void deactivate() {
		activeViewPart.removePropertyListener(this);

		deleteAction.setEditingDomain(null);
		cutAction.setEditingDomain(null);
		copyAction.setEditingDomain(null);
		pasteAction.setEditingDomain(null);
		undoAction.setEditingDomain(null);
		redoAction.setEditingDomain(null);

		if (loadResourceAction != null) {
			loadResourceAction.setEditingDomain(null);
		}

		ISelectionProvider selectionProvider = activeViewPart instanceof ISelectionProvider ? (ISelectionProvider) activeViewPart
				: activeViewPart.getSite().getSelectionProvider();
		selectionProvider.removeSelectionChangedListener(deleteAction);
		selectionProvider.removeSelectionChangedListener(libraryViewEditAction);
		selectionProvider.removeSelectionChangedListener(cutAction);
		selectionProvider.removeSelectionChangedListener(copyAction);
		selectionProvider.removeSelectionChangedListener(pasteAction);
	}

	public void disableGlobalEditMenu() {
		deleteAction.setEnabled(false);
		if (libraryViewEditAction != null)
			libraryViewEditAction.setEnabled(false);
		if (configViewEditAction != null)
			configViewEditAction.setEnabled(false);
		cutAction.setEnabled(false);
		copyAction.setEnabled(false);
		pasteAction.setEnabled(false);
		undoAction.setEnabled(false);
		redoAction.setEnabled(false);
	}

	public void enableGlobalEditMenu() {
		deleteAction.setEnabled(true);
		if (libraryViewEditAction != null)
			libraryViewEditAction.setEnabled(true);
		if (configViewEditAction != null)
			configViewEditAction.setEnabled(true);
		cutAction.setEnabled(true);
		copyAction.setEnabled(true);
		pasteAction.setEnabled(true);
		undoAction.setEnabled(true);
		redoAction.setEnabled(true);
	}

	/**
	 * @see org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor#activate()
	 */
	public void activate() {
		activeViewPart.addPropertyListener(this);
		
		deleteAction.setEditingDomain(editingDomain);
		cutAction.setEditingDomain(editingDomain);
		copyAction.setEditingDomain(editingDomain);
		pasteAction.setEditingDomain(editingDomain);
		undoAction.setEditingDomain(editingDomain);
		redoAction.setEditingDomain(editingDomain);

		if (loadResourceAction != null) {
			loadResourceAction.setEditingDomain(editingDomain);
		}

		ISelectionProvider selectionProvider = activeViewPart instanceof ISelectionProvider ? (ISelectionProvider) activeViewPart
				: activeViewPart.getSite().getSelectionProvider();
		selectionProvider.addSelectionChangedListener(deleteAction);
		selectionProvider.addSelectionChangedListener(libraryViewEditAction);
		selectionProvider.addSelectionChangedListener(cutAction);
		selectionProvider.addSelectionChangedListener(copyAction);
		selectionProvider.addSelectionChangedListener(pasteAction);
		if (validateAction != null) {
			selectionProvider.addSelectionChangedListener(validateAction);
		}

		if (controlAction != null) {
			selectionProvider.addSelectionChangedListener(controlAction);
		}      		

		update();
	}
	
	protected void updatePasteAction() {
		if (activeViewPart == null)
			return;

		ISelectionProvider selectionProvider = activeViewPart instanceof ISelectionProvider ? (ISelectionProvider) activeViewPart
				: activeViewPart.getSite().getSelectionProvider();
		ISelection selection = selectionProvider.getSelection();
		IStructuredSelection structuredSelection = selection instanceof IStructuredSelection ? (IStructuredSelection) selection
				: StructuredSelection.EMPTY;
		pasteAction.setEnabled(pasteAction.updateSelection(structuredSelection));
	}


	/**
	 * @see org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor#update()
	 */
	public void update() {
		if (activeViewPart == null)
			return;

		ISelectionProvider selectionProvider = activeViewPart instanceof ISelectionProvider ? (ISelectionProvider) activeViewPart
				: activeViewPart.getSite().getSelectionProvider();
		ISelection selection = selectionProvider.getSelection();
		IStructuredSelection structuredSelection = selection instanceof IStructuredSelection ? (IStructuredSelection) selection
				: StructuredSelection.EMPTY;

		deleteAction.updateSelection(structuredSelection);
		libraryViewEditAction.updateSelection(structuredSelection);
		cutAction.updateSelection(structuredSelection);
		copyAction.updateSelection(structuredSelection);
		pasteAction.updateSelection(structuredSelection);
		undoAction.update();
		redoAction.update();

		if (loadResourceAction != null) {
			loadResourceAction.update();
		}

		if (validateAction != null) {
			validateAction.updateSelection(structuredSelection);
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

		// Query the new selection for appropriate new child/sibling descriptors
		Collection newChildDescriptors = null;

		ISelection selection = event.getSelection();
		if (selection instanceof IStructuredSelection
				&& ((IStructuredSelection) selection).size() == 1) {
			Object object = ((IStructuredSelection) selection)
					.getFirstElement();

			EditingDomain domain = ((IEditingDomainProvider) activeViewPart)
					.getEditingDomain();

			newChildDescriptors = domain.getNewChildDescriptors(object, null);
		}

		// Generate actions for selection; populate and redraw the menus.
		createChildActions = generateCreateChildActions(newChildDescriptors,
				selection);

		if (createChildMenuManager != null) {
			populateManager(createChildMenuManager, createChildActions, null);
			createChildMenuManager.update(true);
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
				Object obj = i.next();
				if (obj instanceof Separator) {
					actions.add(obj);
				} else {
					actions.add(new MethodCreateChildAction(editingDomain,
							selection, obj));
				}
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
				actions.add(new MethodCreateSiblingAction(activeViewPart,
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
				Object obj = i.next();
				if (obj instanceof IAction) {
					IAction action = (IAction)obj;
					if (contributionID != null) {
						manager.insertBefore(contributionID, action);
					} else {
						manager.add(action);
					}
				} else if (obj instanceof Separator) {
					manager.add((Separator)obj);
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

	/**
	 * This populates the pop-up menu before it appears.
	 */
	public void menuAboutToShow(IMenuManager menuManager) {
		super.menuAboutToShow(menuManager);
		MenuManager submenuManager = null;

		submenuManager = new MenuManager(AuthoringUIResources._UI_CreateChild_menu_item); 
		populateManager(submenuManager, createChildActions, null);
		menuManager.insertBefore("additions", submenuManager); //$NON-NLS-1$
	}

	/**
	 * Inserts global actions before the "additions-end" separator.
	 */
	protected void addGlobalActions(IMenuManager menuManager) {
		menuManager.insertAfter("additions-end", new Separator("ui-actions")); //$NON-NLS-1$ //$NON-NLS-2$

		refreshViewerAction.setEnabled(refreshViewerAction.isEnabled());
		menuManager.insertAfter("ui-actions", refreshViewerAction); //$NON-NLS-1$

		menuManager.insertBefore("additions-end", new Separator()); //$NON-NLS-1$
		
	    String key = (style & ADDITIONS_LAST_STYLE) == 0 ? "additions-end" : "additions"; //$NON-NLS-1$ //$NON-NLS-2$
	    if (validateAction != null)
	    {
	      menuManager.insertBefore(key, new ActionContributionItem(validateAction));
	    }

	}

	/**
	 * @see org.eclipse.ui.part.EditorActionBarContributor#init(IActionBars,
	 *      IWorkbenchPage)
	 */
	public void init(IActionBars actionBars, IWorkbenchPage page) {
		super.init(actionBars, page);
	}

	/**
	 * @see org.eclipse.ui.part.EditorActionBarContributor#init(IActionBars)
	 */
	public void init(IActionBars actionBars) {
		viewActionBars = actionBars;

		ISharedImages sharedImages = PlatformUI.getWorkbench()
				.getSharedImages();

		libraryViewEditAction = new LibraryViewEditAction(AuthoringUIResources.actionLabel_edit); 
		actionBars.setGlobalActionHandler(LibraryViewEditAction.ACTION_ID,
				libraryViewEditAction);

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

		deleteAction = createDeleteAction();
		deleteAction.setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
		actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(),
				deleteAction);
		
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

		libraryViewEditAction.setEnabled(false);
		
		contributeToToolBar(actionBars.getToolBarManager());
	}

	/**
	 * Creates cut action
	 * @return action
	 */
	protected CutAction createCutAction() {
		return new CutAction();
	}
	
	/**
	 * Creates paste action
	 * @return action
	 */
	protected PasteAction createPasteAction() {
		return new PasteAction();
	}
	
	/**
	 * Creates copy action
	 * @return action
	 */
	protected CopyAction createCopyAction() {
		return new CopyAction();
	}
	
	/**
	 * Creates delete action
	 * @return action
	 */
	protected DeleteAction createDeleteAction() {
		return new DeleteAction();
	}
	
	/**
	 * @see org.eclipse.ui.part.EditorActionBarContributor#getActionBars()
	 */
	public IActionBars getActionBars() {
		return viewActionBars;
	}
}