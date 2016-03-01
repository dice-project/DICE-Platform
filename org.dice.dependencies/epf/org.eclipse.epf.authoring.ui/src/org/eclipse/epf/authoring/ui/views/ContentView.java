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
package org.eclipse.epf.authoring.ui.views;

import java.io.File;
import java.util.Collection;

import org.eclipse.epf.authoring.gef.viewer.ActivityDiagramService;
import org.eclipse.epf.authoring.ui.AuthoringUIImages;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.BrowsingPerspective;
import org.eclipse.epf.authoring.ui.UIActionDispatcher;
import org.eclipse.epf.common.ui.util.PerspectiveUtil;
import org.eclipse.epf.diagram.ui.service.DiagramImageService;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.ILibraryServiceListener;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.events.ILibraryChangeListener;
import org.eclipse.epf.library.layout.ElementLayoutManager;
import org.eclipse.epf.library.layout.HtmlBuilder;
import org.eclipse.epf.library.ui.preferences.LibraryUIPreferences;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

/**
 * The Content view.
 * <p>
 * Displays the HTML representation of a Method element.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public class ContentView extends ViewPart {

	/**
	 * The view ID.
	 */
	public static final String VIEW_ID = ContentView.class.getName();

	private ElementHTMLViewer contentViewer;

	private Object displayedElement;

	private IPartListener partListener = new IPartListener() {
		public void partActivated(IWorkbenchPart part) {
			if (part instanceof ContentView && part == ContentView.this) {
				handleSelection(UIActionDispatcher.getInstance().getSelectionSource(), UIActionDispatcher.getInstance()
				        .getSelection());
			}
		}

		public void partBroughtToTop(IWorkbenchPart part) {
		}

		public void partClosed(IWorkbenchPart part) {
		}

		public void partDeactivated(IWorkbenchPart part) {
		}

		public void partOpened(IWorkbenchPart part) {
		}
	};

	private ISelectionChangedListener selectionChangeListener = new ISelectionChangedListener() {
		public void selectionChanged(SelectionChangedEvent event) {
			handleSelection(event.getSource(), event.getSelection());
		}
	};

	private ILibraryChangeListener libraryChangeListener = new ILibraryChangeListener() {
		public void libraryChanged(int option, Collection changedItems) {
//			if (option == ILibraryChangeListener.OPTION_LOADED
//					|| option == ILibraryChangeListener.OPTION_CREATED) {
//				if (contentViewer != null) {
//					contentViewer.setHtmlBuilder(new HtmlBuilder());
//					contentViewer.getBrowser().redraw();
//				}
//			}

			if (changedItems != null && displayedElement != null) {
				if (changedItems.contains(displayedElement)) {
					displayedElement = null;
				}
			}
		}
	};

	private ILibraryServiceListener libSvcListener = new ILibraryServiceListener() {

		public void configurationSet(MethodConfiguration config) {
			contentViewer.getBrowser().setUrl("about:blank"); //$NON-NLS-1$
			displayedElement = null;
		}

		public void libraryClosed(MethodLibrary library) {
		}

		public void libraryCreated(MethodLibrary library) {
			cleanup();
		}

		public void libraryOpened(MethodLibrary library) {
			cleanup();
		}

		public void libraryReopened(MethodLibrary library) {
			cleanup();
		}

		public void librarySet(MethodLibrary library) {
		}
		
		private void cleanup() {
			if (contentViewer != null) {
				contentViewer.setHtmlBuilder(new HtmlBuilder());
				contentViewer.getBrowser().redraw();
			}
		}
	};
	
	// Page navigation actions.
	private Action backAction;

	private Action forwardAction;

	private Action stopAction;

	private Action refreshAction;

	private Action printAction;

	// A hidden composite to hold the diagram viewer for diagram image
	// generation.
	private Composite diagramViewerHolder;

	/**
	 * Creates a new instance.
	 */
	public ContentView() {
	}

	/**
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(Composite)
	 */
	public void createPartControl(Composite parent) {
		try {
			GridLayout layout = new GridLayout();
			layout.marginWidth = 0;
			layout.marginHeight = 0;
			layout.horizontalSpacing = 0;
			layout.verticalSpacing = 0;
			parent.setLayout(layout);
			parent.setLayoutData(new GridData(GridData.FILL_BOTH));

			contentViewer = new ElementHTMLViewer(parent);
			diagramViewerHolder = new Composite(parent, SWT.NONE);
			diagramViewerHolder.setLayoutData(new GridData(1, 1)); // size
			// can't be
			// 0,0
			// otherwise
			// the
			// diagram
			// will not
			// be
			// painted
			diagramViewerHolder.setLayout(new GridLayout());
			diagramViewerHolder.setVisible(false);

			// Create the page navigation tool buttons.
			createActions();

			// Add a part listener to monitor part activation and update this
			// view
			// as and when needed.
			getSite().getWorkbenchWindow().getPartService().addPartListener(
					partListener);

			getSite().getWorkbenchWindow().addPerspectiveListener(
					new IPerspectiveListener() {
						public void perspectiveActivated(IWorkbenchPage page,
								IPerspectiveDescriptor desc) {
						}

						public void perspectiveChanged(IWorkbenchPage page,
								IPerspectiveDescriptor desc, String id) {
							if (desc.getId().equals(
									BrowsingPerspective.PERSPECTIVE_ID)) {
								if (id
										.equals(IWorkbenchPage.CHANGE_EDITOR_AREA_SHOW)) {
									page.setEditorAreaVisible(false);
									AuthoringUIPlugin
											.getDefault()
											.getMsgDialog()
											.displayWarning(
													AuthoringUIResources.warningDialog_title, 
													AuthoringUIResources.addEditorToPerspectiveWarning_msg); 
								}
							}
						}
					});

			// Add selection change listener to update this view as and when
			// needed.
			UIActionDispatcher.getInstance().addSelectionChangedListener(
					selectionChangeListener);

			LibraryService.getInstance().addListener(libSvcListener);

			ILibraryManager manager = LibraryService.getInstance().getCurrentLibraryManager();
			if (manager != null) {
				manager.addListener(libraryChangeListener);
			}
		} catch (Exception e) {
			// TODO: Display an error dialog.
			e.printStackTrace();
		}
	}

	/**
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus
	 */
	public void setFocus() {
		contentViewer.setFocus();
	}

	/**
	 * @see org.eclipse.ui.part.WorkbenchPart#dispose
	 */
	public void dispose() {
		LibraryService.getInstance().removeListener(libSvcListener);

		ILibraryManager manager = LibraryService.getInstance().getCurrentLibraryManager();
		if (manager != null) {
			manager.removeListener(libraryChangeListener);
		}
		UIActionDispatcher.getInstance().removeSelectionChangedListener(
				selectionChangeListener);
		getSite().getWorkbenchWindow().getPartService().removePartListener(
				partListener);
	}

	/**
	 * Creates the actions for this view.
	 */
	protected void createActions() {
		backAction = new Action() {
			public void run() {
				Browser browser = contentViewer.getBrowser();
				if (browser != null) {
					browser.back();
				}
			}
		};
		backAction.setText(AuthoringUIResources.back_text); 
		backAction.setToolTipText(AuthoringUIResources.back_text); 
		backAction.setImageDescriptor(PlatformUI.getWorkbench()
				.getSharedImages().getImageDescriptor(
						ISharedImages.IMG_TOOL_BACK));

		forwardAction = new Action() {
			public void run() {
				Browser browser = contentViewer.getBrowser();
				if (browser != null) {
					browser.forward();
				}
			}
		};
		forwardAction.setText(AuthoringUIResources.forward_text); 
		forwardAction.setToolTipText(AuthoringUIResources.forward_text); 
		forwardAction.setImageDescriptor(PlatformUI.getWorkbench()
				.getSharedImages().getImageDescriptor(
						ISharedImages.IMG_TOOL_FORWARD));

		stopAction = new Action() {
			public void run() {
				Browser browser = contentViewer.getBrowser();
				if (browser != null) {
					browser.stop();
				}
			}
		};
		stopAction.setText(AuthoringUIResources.stop_text); 
		stopAction.setToolTipText(AuthoringUIResources.stop_text); 
		stopAction.setImageDescriptor(AuthoringUIImages.IMG_DESC_STOP);

		refreshAction = new Action() {
			public void run() {
				// Browser browser = contentViewer.getBrowser();
				// if (browser != null) {
				// browser.refresh();
				// }

				// need to regenerate the content
				contentViewer.refresh();
			}
		};
		refreshAction.setText(AuthoringUIResources.refresh_text); 
		refreshAction.setToolTipText(AuthoringUIResources.refresh_text); 
		refreshAction.setImageDescriptor(AuthoringUIImages.IMG_DESC_REFRESH);

		printAction = new Action() {
			public void run() {
				contentViewer.print();
			}
		};
		printAction.setText(AuthoringUIResources.print_text); 
		printAction.setToolTipText(AuthoringUIResources.print_text); 
		printAction.setImageDescriptor(AuthoringUIImages.IMG_DESC_PRINT);

		IActionBars bars = getViewSite().getActionBars();
		IToolBarManager manager = bars.getToolBarManager();
		manager.add(backAction);
		manager.add(forwardAction);
		manager.add(stopAction);
		manager.add(refreshAction);
		manager.add(printAction);
	}

	protected boolean isViewVisible() {
		IWorkbenchPage activePage = getSite().getWorkbenchWindow()
		.getActivePage();
		if (activePage == null) {
			return false;
		}
		
		IViewPart view = activePage.findView(getViewId());
		if (view == null) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Handles the given selection.
	 * 
	 * @param source
	 *            The Library or Configuration view.
	 * @param selection
	 *            The selection in the Library or Configuration view.
	 */
	protected void handleSelection(Object source, ISelection selection) {
		if (selection == null || !isViewVisible() ) {
			return;
		}
	
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection sel = (IStructuredSelection) selection;
			Object[] selectedElements = sel.toArray();
			if (selectedElements != null && selectedElements.length > 0) {
				Object element = selectedElements[0];
				
				// don't unwrap the object. 
				// For activity, we need to wrapped item provider to get the suppress status.
				// Object element = LibraryUtil.unwrap(selectedElement);
				if (displayedElement != null && displayedElement == element) {
					displayedElement = null;
					return;
				}
				if (displayContentPage(source, element)) {
					displayedElement = element;
				}
			}else{
				contentViewer.getBrowser().setUrl("about:blank"); //$NON-NLS-1$
				displayedElement = null;
			}
		}
	}

	protected boolean isConfigSource(Object source) {
		return (source != null) && (source instanceof ConfigurationView);
	}
	
	protected ElementLayoutManager getLayoutMgr() {
		return 	LibraryService.getInstance()
			.getCurrentConfigurationManager().getElementLayoutManager();
	}
	
	protected String getViewId() {
		return VIEW_ID;
	}
	
	/**
	 * Displays the content page for the given ContentElement.
	 * 
	 * @param source
	 *            The Library or Configuration view.
	 * @param element
	 *            The selected Method element.
	 * @return <code>true</code> if the content page is displayed
	 *         successfully.
	 */
	protected boolean displayContentPage(Object source, Object raw_element) {
		Object element = LibraryUtil.unwrap(raw_element);
		element = ViewHelper.handleDangling(element);
		if (element == null) {
			return false;
		}

		ElementLayoutManager layoutMgr = null;
		boolean isBrowsing = PerspectiveUtil
				.isActivePerspective(BrowsingPerspective.PERSPECTIVE_ID);
		boolean isConfigSource = isConfigSource(source);

		// if in browsing but the source is not from configruation view
		// ignore it
		if (isBrowsing && !isConfigSource) {
			return false;
		}

		if (isConfigSource) {
			// Get the Element Layout Manager for the current configuration.
			layoutMgr = getLayoutMgr();
			if ( layoutMgr == null ) {
				return false;
			}
			
			// Add the activity diagram service to generate diagram images.
			
			String newDiagrams = AuthoringUIPlugin.getDefault().getPreferenceStore().getString("PUBLISH_NEW_DIAGRAM"); //$NON-NLS-1$
			Boolean newDiagram = new Boolean(true);
			if(newDiagrams != null && newDiagrams.length() > 0){
				newDiagram = new Boolean(newDiagrams);
			}
			if(newDiagram.booleanValue()){
				DiagramImageService diagramService = (DiagramImageService) layoutMgr
					.getActivityDiagramService();
				if (diagramService == null) {
					diagramService = new DiagramImageService(
						diagramViewerHolder,
						new File(layoutMgr.getPublishDir()));
					diagramService.setConfig(layoutMgr.getConfiguration());
					layoutMgr.setActivityDiagramService(diagramService);
				}
//				 For Activity Detail Diagram 
				boolean option = LibraryUIPreferences.getPublishUnopenActivitydd();
				diagramService.setPublishedUnCreatedADD(option);
				
				// For Activity Diagram 
				option = LibraryUIPreferences.getPublishADForActivityExtension();
				diagramService.setPublishADForActivityExtension(option);
			}else{
//				 Add the activity diagram service to generate diagram images.
				ActivityDiagramService diagramService = (ActivityDiagramService) layoutMgr
						.getActivityDiagramService();
				if (diagramService == null) {
					diagramService = new ActivityDiagramService(
							diagramViewerHolder,
							new File(layoutMgr.getPublishDir()));
					layoutMgr.setActivityDiagramService(diagramService);
				}
				
				// For Activity Detail Diagram 
				boolean option = LibraryUIPreferences.getPublishUnopenActivitydd();
				diagramService.setPublishedUnCreatedADD(option);
				
				// For Activity Diagram 
				option = LibraryUIPreferences.getPublishADForActivityExtension();
				diagramService.setPublishADForActivityExtension(option);
			}
			
			
			
		}

		contentViewer.setLayoutManager(layoutMgr);
		contentViewer.showElementContent(raw_element);

		return true;
	}

	/**
	 * Displays the HTML content page for a Method element.
	 * 
	 * @param element
	 *            A Method element.
	 * @return <code>true</code> if the HTML content page is displayed
	 *         successfully.
	 */
	public boolean displayHTMLContentPage(Object element) {
		element = LibraryUtil.unwrap(element);
		element = ViewHelper.handleDangling(element);
		if (element != null) {
			contentViewer.setLayoutManager(null);
			contentViewer.showElementContent(element);
			return true;
		}
		return false;
	}

}
