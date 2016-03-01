//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.authoring.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.epf.authoring.ui.dialogs.ContributionSelection;
import org.eclipse.epf.authoring.ui.editors.EditorChooser;
import org.eclipse.epf.authoring.ui.views.ConfigurationView;
import org.eclipse.epf.authoring.ui.views.LibraryView;
import org.eclipse.epf.common.ui.util.PerspectiveUtil;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveListener;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * Handles UI actions in the workbench.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class UIActionDispatcher implements ISelectionProvider {

	private static UIActionDispatcher instance = null;

	protected List<ISelectionChangedListener> selectionChangedListeners = new ArrayList<ISelectionChangedListener>();

	protected List<IDoubleClickListener> doubleClickListeners = new ArrayList<IDoubleClickListener>();

	private CurrentSelection currentSelection = new CurrentSelection();

	private IDoubleClickListener doubleClickListener = null;

	/**
	 * Returns the singleton instance.
	 */
	public static UIActionDispatcher getInstance() {
		if (instance == null) {
			synchronized (UIActionDispatcher.class) {
				if (instance == null) {
					instance = new UIActionDispatcher();
				}
			}
		}
		return instance;
	}

	/**
	 * Private default constructor.
	 */
	private UIActionDispatcher() {
		this(null);
	}

	/**
	 * create an action dispatcher of my own
	 * 
	 * @param owner
	 */
	public UIActionDispatcher(Object owner) {
		// Create the double click listener used for launching the Method
		// editors.
		doubleClickListener = new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent e) {
				ISelection selection = e.getSelection();
				if (selection != null) {
					IWorkbenchWindow window = PlatformUI.getWorkbench()
							.getActiveWorkbenchWindow();
					IWorkbenchPartSite site = window.getActivePage()
							.getActivePart().getSite();

					IStructuredSelection sel = (IStructuredSelection) selection;
					Object[] selObjs = sel.toArray();
					Object selectedObject = null;

					if ((selObjs != null) && (selObjs.length > 0)) {
						// Open the Authoring persepective.
						UIActionDispatcher.openAuthoringPerspective();

						// Locate the selected Method element.
						Object obj = TngUtil.unwrap(selObjs[0]);
						ConfigurationView view = ConfigurationView.getView();						
						if ((view != null ) && site.equals(view.getSite()) ) {
							// Double-click orginated from the Configuration
							// view.
							if (obj instanceof VariabilityElement) {
								VariabilityElement element = (VariabilityElement) obj;
								ContributionSelection contribSelection = new ContributionSelection();
								selectedObject = contribSelection
										.getSelectedContributor(element);
							}
							else if (obj instanceof Milestone) {
								selectedObject = TngUtil.getOwningProcess((BreakdownElement) obj);
							}
						} else {
							selectedObject = obj;
						}

						if (selectedObject != null) {
							
							EditorChooser.getInstance().openEditor(
									selectedObject);
						}
					}
				}
			}
		};

		// Add a perspective listener to enable/disable double-click listening.
		IWorkbenchWindow activeWorkbenchWindow = AuthoringUIPlugin.getDefault()
				.getWorkbench().getActiveWorkbenchWindow();
		if (activeWorkbenchWindow != null) {
			activeWorkbenchWindow
					.addPerspectiveListener(new IPerspectiveListener() {
						public void perspectiveActivated(IWorkbenchPage page,
								IPerspectiveDescriptor desc) {
							if (desc.getId().equals(
									AuthoringPerspective.PERSPECTIVE_ID)) {
								addDoubleClickListener(doubleClickListener);
								if (LibraryView.getViewInstance() == null) {
									LibraryView.setNeedRegisterChangeListenersInCreate(true);
								} else {
									LibraryView.getViewInstance().registerChangeListeners();
								}
							} else {
								removeDoubleClickListener(doubleClickListener);
							}
							
							ConfigurationHelper.getDelegate().setAuthoringPerspective(
									AuthoringPerspective.PERSPECTIVE_ID.equals(desc.getId()));
						}

						public void perspectiveChanged(IWorkbenchPage page,
								IPerspectiveDescriptor desc, String id) {

							ConfigurationHelper.getDelegate().setAuthoringPerspective(
									AuthoringPerspective.PERSPECTIVE_ID.equals(desc.getId()));
						}
					});
		}

		if (PerspectiveUtil
				.isActivePerspective(AuthoringPerspective.PERSPECTIVE_ID)) {
			addDoubleClickListener(doubleClickListener);
		}
	}

	public static void editConfiguration(String configName) {
	}

	/**
	 * Open library perspective
	 * 
	 */
	public static void openLibraryPerspective() {
		PerspectiveUtil.openPerspective(BrowsingPerspective.PERSPECTIVE_ID);
		IWorkbenchPage wkbPage = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		IViewPart viewPart = wkbPage.findView(LibraryView.VIEW_ID);
		if (viewPart != null)
			wkbPage.hideView(viewPart);
		viewPart = wkbPage.findView(ConfigurationView.VIEW_ID);
		wkbPage.activate(viewPart);
	}

	/**
	 * Open authoring perspective
	 * 
	 */
	public static void openAuthoringPerspective() {
		PerspectiveUtil.openPerspective(AuthoringPerspective.PERSPECTIVE_ID);
		IWorkbenchPage wkbPage = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		IViewPart viewPart = wkbPage.findView(LibraryView.VIEW_ID);
		wkbPage.activate(viewPart);
	}

	/**
	 * @see org.eclipse.jface.viewers.ISelectionProvider#addSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		if (!selectionChangedListeners.contains(listener)) {
			selectionChangedListeners.add(listener);
		}
	}

	/**
	 * @see org.eclipse.jface.viewers.ISelectionProvider#removeSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		selectionChangedListeners.remove(listener);
	}

	/**
	 * Return current selection
	 */
	public ISelection getSelection() {
		return currentSelection.selection;
	}

	public ISelectionProvider getSelectionSource() {
		return currentSelection.source;
	}

	/**
	 * Sets current selection
	 */
	public void setSelection(ISelection selection) {
		setSelection(this, selection);
	}

	/**
	 * Sets current selection
	 */
	public void setSelection(ISelectionProvider source, ISelection selection) {
		synchronized (this) {
			currentSelection.selection = selection;
			currentSelection.source = source;

			SelectionChangedEvent event = new SelectionChangedEvent(source,
					selection);

			// Avoid concurrent error.
			List<ISelectionChangedListener> processedItems = new ArrayList<ISelectionChangedListener>();
			while (selectionChangedListeners.size() > 0) {
				ISelectionChangedListener listener = (ISelectionChangedListener) selectionChangedListeners
						.remove(0);
				if (listener != null) {
					try {
						listener.selectionChanged(event);
					} catch (Exception e) {
						AuthoringUIPlugin.getDefault().getLogger().logError(e);
					}
					processedItems.add(listener);
				}
			}

			selectionChangedListeners.addAll(processedItems);
		}
	}

	/**
	 * Add double click listener
	 * 
	 * @param listener
	 */
	public void addDoubleClickListener(IDoubleClickListener listener) {
		if (!doubleClickListeners.contains(listener)) {
			doubleClickListeners.add(listener);
		}
	}

	/**
	 * Remove double click listener
	 * 
	 * @param listener
	 */
	public void removeDoubleClickListener(IDoubleClickListener listener) {
		doubleClickListeners.remove(listener);
	}

	/**
	 * Handle double click event
	 * 
	 * @param event
	 */
	public void handleDoubleClickEvent(DoubleClickEvent event) {
		synchronized (this) {
			// Avoid concurrent error.
			List<IDoubleClickListener> processedItems = new ArrayList<IDoubleClickListener>();
			while (doubleClickListeners.size() > 0) {
				IDoubleClickListener listener = (IDoubleClickListener) doubleClickListeners
						.remove(0);
				try {
					listener.doubleClick(event);
				} catch (Exception e) {
					AuthoringUIPlugin.getDefault().getLogger().logError(e);
				}
				processedItems.add(listener);
			}
			doubleClickListeners.addAll(processedItems);
		}
	}

	public class CurrentSelection {

		public ISelectionProvider source = null;

		public ISelection selection = StructuredSelection.EMPTY;

	}

}
