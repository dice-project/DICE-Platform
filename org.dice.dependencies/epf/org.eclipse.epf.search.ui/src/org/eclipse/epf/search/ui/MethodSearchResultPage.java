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
package org.eclipse.epf.search.ui;

import org.eclipse.epf.authoring.ui.AuthoringPerspective;
import org.eclipse.epf.authoring.ui.BrowsingPerspective;
import org.eclipse.epf.authoring.ui.editors.EditorChooser;
import org.eclipse.epf.authoring.ui.views.ContentView;
import org.eclipse.epf.common.ui.util.PerspectiveUtil;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceListener;
import org.eclipse.epf.library.edit.util.ExtensionManager;
import org.eclipse.epf.library.ui.providers.ElementTreeLabelProvider;
import org.eclipse.epf.search.ui.internal.ISearchResultProviderFactory;
import org.eclipse.epf.search.ui.internal.MethodElementViewSorter;
import org.eclipse.epf.search.ui.internal.SearchResultTableContentProvider;
import org.eclipse.epf.search.ui.internal.SearchResultTreeContentProvider;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.ProcessElement;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.search.ui.text.AbstractTextSearchViewPage;
import org.eclipse.search.ui.text.Match;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * Displays the Method Search result in the Search view. The search result
 * can be displayed in a hierarchical or flat view.
 * 
 * @author Kelvin Low
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class MethodSearchResultPage extends AbstractTextSearchViewPage
		implements ISelectionChangedListener {

	private IStructuredContentProvider contentProvider;

	private ILabelProvider labelProvider;

	/**
	 * Creates a new instance.
	 */
	public MethodSearchResultPage() {
		super();
		labelProvider = createLabelProvider();
		
		// Clear the search result when the current method library is closed by the user.
		LibraryService.getInstance().addListener(new LibraryServiceListener() {
			public void libraryClosed(MethodLibrary library) {
				MethodSearchResultPage.this.getInput().removeAll();
			}			
		});
	}

	/**
	 * @see org.eclipse.search.ui.text.AbstractTextSearchViewPage#isLayoutSupported(int)
	 */
	public boolean isLayoutSupported(int layout) {
		return ((layout & FLAG_LAYOUT_FLAT) != 0 || (layout & FLAG_LAYOUT_TREE) != 0);
	}
	
	private static final ISearchResultProviderFactory getSearchResultProviderFactory() {
		return (ISearchResultProviderFactory) ExtensionManager.getExtension(
				SearchUIPlugin.getDefault().getId(),
				"searchResultProviderFactory"); //$NON-NLS-1$
	}
	
	protected IStructuredContentProvider createContentProvider(Viewer viewer) {
		ISearchResultProviderFactory factory = getSearchResultProviderFactory();
		if(viewer instanceof TreeViewer) {
			IStructuredContentProvider cp = null;
			if(factory != null) {
				cp = factory.createTreeContentProvider();
			}
			return cp != null ? cp : new SearchResultTreeContentProvider();
		}
		else if(viewer instanceof TableViewer) {
			IStructuredContentProvider cp = null;
			if(factory != null) {
				cp = factory.createTableContentProvider();
			}
			return cp != null ? cp : new SearchResultTableContentProvider();
		}
		return null;
	}
	
	protected ILabelProvider createLabelProvider() {
		ILabelProvider lp = null;
		ISearchResultProviderFactory factory = getSearchResultProviderFactory();
		if(factory != null) {
			lp = factory.createLabelProvider();
		}
		return lp != null ? lp : new ElementTreeLabelProvider();
	}

	/**
	 * @see org.eclipse.search.ui.text.AbstractTextSearchViewPage#configureTreeViewer(TreeViewer)
	 */
	protected void configureTreeViewer(TreeViewer viewer) {
		contentProvider = createContentProvider(viewer);
		viewer.setContentProvider(contentProvider);
		viewer.setLabelProvider(labelProvider);
		viewer.setSorter(new MethodElementViewSorter());
		viewer.addSelectionChangedListener(this);
	}

	/**
	 * @see org.eclipse.search.ui.text.AbstractTextSearchViewPage#configureTableViewer(TableViewer)
	 */
	protected void configureTableViewer(TableViewer viewer) {
		contentProvider = createContentProvider(viewer);
		viewer.setContentProvider(contentProvider);
		viewer.setLabelProvider(labelProvider);
		viewer.setSorter(new MethodElementViewSorter());
	}

	/**
	 * @see org.eclipse.search.ui.text.AbstractTextSearchViewPage#elementsChanged(Object[])
	 */
	protected void elementsChanged(Object[] objects) {
		if (contentProvider != null) {
			if (contentProvider instanceof SearchResultTreeContentProvider) {
				((SearchResultTreeContentProvider) contentProvider)
						.elementsChanged(objects);
			} else if (contentProvider instanceof SearchResultTableContentProvider) {
				((SearchResultTableContentProvider) contentProvider)
						.elementsChanged(objects);
			}
		}
	}
	
	/**
	 * @see org.eclipse.search.ui.text.AbstractTextSearchViewPage#clear()
	 */
	protected void clear() {
		if (contentProvider != null) {
			if (contentProvider instanceof SearchResultTreeContentProvider) {
				((SearchResultTreeContentProvider) contentProvider).clear();
			} else if (contentProvider instanceof SearchResultTableContentProvider) {
				((SearchResultTableContentProvider) contentProvider).clear();
			}
		}
	}

	/**
	 * @see org.eclipse.search.ui.text.AbstractTextSearchViewPage#showMatch(Match,
	 *      int, int, boolean)
	 */
	protected void showMatch(Match match, int currentOffset, int currentLength,
			boolean activate) throws PartInitException {
		if (match.getElement() instanceof MethodElement && activate) {
			MethodElement element = (MethodElement) match.getElement();
			if (PerspectiveUtil
					.isActivePerspective(BrowsingPerspective.PERSPECTIVE_ID)) {
				PerspectiveUtil
						.openPerspective(AuthoringPerspective.PERSPECTIVE_ID);
				IWorkbenchPage activePage = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage();
				if (activePage != null) {
					IViewPart view = activePage
							.findView(NewSearchUI.SEARCH_VIEW_ID);
					if (view != null) {
						activePage.showView(NewSearchUI.SEARCH_VIEW_ID);
					}
				}
			}
			displayEditor(element);
		}
	}

	/**
	 * Displays the Method editor for the selected Method element.
	 */
	protected void displayEditor(MethodElement element) {
		if (element instanceof ProcessElement) {
			EditorChooser.getInstance().openEditor(
					UmaUtil.getProcessComponent(element));
		} else {
			EditorChooser.getInstance().openEditor(element);
		}
	}

	/**
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(SelectionChangedEvent)
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		if (PerspectiveUtil
				.isActivePerspective(BrowsingPerspective.PERSPECTIVE_ID)) {
			ISelection selection = event.getSelection();
			if (selection instanceof IStructuredSelection) {
				IStructuredSelection sel = (IStructuredSelection) selection;
				Object[] selectedElements = sel.toArray();
				if (selectedElements != null && selectedElements.length > 0) {
					if (selectedElements[0] instanceof MethodElement) {
						try {
							displayHTMLPage((MethodElement) selectedElements[0]);
						} catch (PartInitException e) {
						}
					}
				}
			}
		}
	}

	/**
	 * Displays the HTML content page for the selected method element.
	 * 
	 * @param element
	 *            a method element
	 * @throws PartInitException
	 *             if the Content view cannot be displayed
	 */
	protected void displayHTMLPage(MethodElement element)
			throws PartInitException {
		if (element instanceof MethodPlugin
				|| element instanceof ContentPackage) {
			return;
		}
		IWorkbenchPage activePage = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		if (activePage != null) {
			IViewPart view = activePage.findView(ContentView.VIEW_ID);
			if (view == null) {
				view = activePage.showView(ContentView.VIEW_ID);
			}
			if (view != null) {
				((ContentView) view).displayHTMLContentPage(element);
			}
		}
	}

}
