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
package org.eclipse.epf.authoring.ui.properties;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.editors.ProcessEditor;
import org.eclipse.epf.common.serviceability.Logger;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.configuration.ProcessAuthoringConfigurator;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.util.ProcessScopeUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.events.ILibraryChangeListener;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.util.Scope;
import org.eclipse.jface.util.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.eclipse.ui.views.properties.tabbed.TabContents;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * Base class for a section in a tab in the properties view.
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class AbstractSection extends AbstractPropertySection implements
		PropertyChangeListener {

	protected BreakdownElement element;

	protected boolean editable = false;

	protected TabbedPropertySheetPage page;

	protected ProcessAuthoringConfigurator configurator = new ProcessAuthoringConfigurator(
			LibraryService.getInstance().getCurrentMethodConfiguration());

	protected Logger logger;

	/*
	 * Create an instance
	 */
	public AbstractSection() {
		super();

		// listen to change for current selection of MethodConfiguration
		ILibraryManager manager = LibraryService.getInstance().getCurrentLibraryManager();
		if (manager != null) {
			manager.addListener(libraryListener);
		}

		// get logger
		logger = AuthoringUIPlugin.getDefault().getLogger();
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#createControls(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite comp, TabbedPropertySheetPage page) {
		super.createControls(comp, page);
		this.page = page;
	}

	private ILibraryChangeListener libraryListener = new ILibraryChangeListener() {

		public void libraryChanged(int option, Collection collection) {
			switch (option) {
			case ILibraryChangeListener.OPTION_CHANGED: {
				sectionRefresh();
			}
			}
		}
	};

	/**
	 * Refresh the current section
	 * 
	 */
	private void sectionRefresh() {
		if (page != null) {
			TabContents tab = page.getCurrentTab();
			if (tab != null) {
				ISection section = tab.getSectionAtIndex(0);

				if ((section != null)
						&& (section instanceof BreakdownElementGeneralSection)) {
					section.refresh();
				}
			}
		}
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#dispose()
	 */
	public void dispose() {
		super.dispose();
		if (libraryListener != null) {
			ILibraryManager manager = LibraryService.getInstance().getCurrentLibraryManager();
			if (manager != null) {
				manager.removeListener(libraryListener);
			}
		}
	}


	/**
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#setInput(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		// System.out.println("SetInput::selection - "+selection);
		Assert.isTrue(selection instanceof IStructuredSelection);
		Object input = ((IStructuredSelection) selection).getFirstElement();

		initElementData(input);

	}

	private void initElementData(Object input) {

		if (input instanceof BreakdownElement) {
			this.element = (BreakdownElement) input;
			if (TngUtil.isLocked(this.element))
				this.editable = false;
			else {
				this.editable = true;

				if (element instanceof RoleDescriptor) {
					RoleDescriptor roleDesc = (RoleDescriptor) element;
					if ((roleDesc.getSuperActivities() == null)
							|| (roleDesc.getSuperActivities() == null))
						this.editable = false;
					else
						this.editable = true;
				}
			}
		} else if (input instanceof BreakdownElementWrapperItemProvider) {
			this.element = (BreakdownElement) LibraryUtil.unwrap(input);
			this.editable = false;
		}

	}

	/**
	 * Return selection
	 */
	public ISelection getSelection() {
		return EPFPropertySheetPage.getSelection();
	}

	/**
	 * Return first element from selection
	 */
	public Object getInput() {
		ISelection selection = getSelection();
		if (selection instanceof IStructuredSelection) {
			Object input = ((IStructuredSelection) selection).getFirstElement();
			return input;
		}
		return null;
	}

	/**
	 * Return process editor
	 * 
	 * @return
	 * 		process editor
	 */
	public ProcessEditor getEditor() {
		return EPFPropertySheetPage.getEditor();
	}

	/**
	 * Return ItemProvider
	 * 
	 * @return
	 * 			adapter
	 */
	public ItemProviderAdapter getAdapter() {
		try {
			AdapterFactory factory = EPFPropertySheetPage.getAdapterFactory();
			ItemProviderAdapter provider = (ItemProviderAdapter) factory.adapt(
					element, ITreeItemContentProvider.class);
			return provider;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * Return Adapter factory
	 * 
	 * @return
	 * 			adapter factory
	 */
	public AdapterFactory getAdapterFactory() {
		AdapterFactory adapterFactory = EPFPropertySheetPage
				.getAdapterFactory();
		return adapterFactory;
	}

	/**
	 * Get the element.
	 * 
	 * @return the element.
	 */
	public BreakdownElement getElement() {
		ISelection selection = getSelection();
		if (selection instanceof IStructuredSelection) {
			Object input = ((IStructuredSelection) selection).getFirstElement();
			// System.out.println("getElement::selection - "+selection);
			try {
				initElementData(input);
			} catch (Exception e) {
				logger
						.logError(
								"Error getting element from the selection " + element, e); //$NON-NLS-1$
				return null;
			}
			return element;
		}
		return null;
	}

	/**
	 * Return property sheet page
	 * @return
	 * 			property sheet page
	 */
	public TabbedPropertySheetPage getPropertySheetPage() {
		return this.page;
	}

	/**
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		// System.out.println("ProeprtyChangeEvent" + evt);
		refresh();
	}

	/**
	 * Return filter list of elements. Filter is based on current method configuration
	 * @param list
	 * 			Original list
	 * @return
	 * 			filtered list
	 */
	public List getFilteredList(List list) {
		List newList = new ArrayList();
		
		if (configurator != null) {
			boolean toSetConfig = true; 
		
			if (getEditor() != null || getEditor().getSelectedProcess() != null) {
				Scope scope = ProcessScopeUtil.getInstance().getScope(getEditor().getSelectedProcess());
				if (scope != null) {
					configurator.setMethodConfiguration(scope);
					toSetConfig = false;
				}
			}
				
			if (toSetConfig) {
				configurator.setMethodConfiguration(LibraryService
				.getInstance().getCurrentMethodConfiguration());
			}
		}
		
		for (Iterator itor = list.iterator(); itor.hasNext();) {
			if (configurator != null) {
				Object obj = (Object) itor.next();
//				configurator.setMethodConfiguration(LibraryService
//						.getInstance().getCurrentMethodConfiguration());
				if (configurator.accept(obj)) {
					newList.add(obj);
				}
			}
		}
		return newList;
	}

	/**
	 * Get default method configuration
	 * 
	 * @return 
	 * 			default method configuration
	 */
	protected MethodConfiguration getDefaultConfiguration() {
		ItemProviderAdapter adapter = (ItemProviderAdapter) getAdapter();
		Object parent = null;
		if (adapter instanceof IBSItemProvider) {
			IBSItemProvider bsItemProvider = (IBSItemProvider) adapter;
			parent = bsItemProvider.getTopItem();
			MethodConfiguration config = ((org.eclipse.epf.uma.Process) parent)
					.getDefaultContext();
			return config;
		} else {
			logger
					.logError("AbstractSection::getDefaultConfiguration - IBSItemProvider is null"); //$NON-NLS-1$
			return null;
		}
	}

	/**
	 * Return current method configuration
	 * 
	 * @return
	 * 			current method configuration
	 */
	public MethodConfiguration getConfiguration() {
		if (getEditor() != null) {
			Scope scope = ProcessScopeUtil.getInstance().getScope(
					getEditor().getSelectedProcess());
			if (scope != null) {
				int scopeType = ProcessScopeUtil.getInstance().getElemementSelectionScopeType();
				if (scopeType == ProcessScopeUtil.ScopeType_Config) {
					MethodConfiguration config = LibraryService.getInstance()
					.getCurrentMethodConfiguration();
//					if (config == null) {
//						return scope;
//					}
					return config;
				} 
				if (scopeType == ProcessScopeUtil.ScopeType_Process) {
					return scope;
				}
				if (scopeType == ProcessScopeUtil.ScopeType_Library) {
					return ProcessScopeUtil.getInstance().getLibraryScope();
				}
				if (scopeType == ProcessScopeUtil.ScopeType_Plugins) {
					return ProcessScopeUtil.getInstance().getPluginScope();
				}
			}
		}

		MethodConfiguration config = LibraryService.getInstance()
				.getCurrentMethodConfiguration();
		if (config == null) {
			// if configuration is not selected, use default configuration
			return getDefaultConfiguration();
		}

		return config;
	}


	// public void updateTitle(Object obj)
	// {
	// String name = getNamePrefix() + ((BreakdownElement)obj).getName();
	// TabbedPropertyComposite comp = (TabbedPropertyComposite)
	// page.getControl();
	// TabbedPropertyTitle title = comp.getTitle();
	// if (titleImage == null)
	// {
	// titleImage = Display.getCurrent().getActiveShell().getImage();
	// }
	// title.setTitle(name, titleImage);
	// }
	
	
	
	/**
	 * Get name prefix for the element
	 */
	public String getNamePrefix() {
		return "ProcessElement: "; //$NON-NLS-1$
	}
	
	protected AbstractSection getSection() {
		return this;
	}
	
	protected boolean isSyncFree() {
		return false;
	}
	
	protected void removeOutdatedReferences(MethodElement element, EReference eRef, Set validValueSet) {
		if (! (element instanceof Descriptor) || ProcessUtil.getAssociatedElement((Descriptor) element) == null) {
			return;
		}
		Object value = element.eGet(eRef);
		if (! (value instanceof List)) {
			return;
		}
		List list = (List) value;
		if (list.isEmpty()) {
			return;
		}
		Set toRemoveSet = new HashSet();
		for (Object obj : list) {
			if (! validValueSet.contains(obj)) {
				toRemoveSet.add(obj);
			}
		}
		if (toRemoveSet.isEmpty()) {
			return;
		}
		list.removeAll(toRemoveSet);
	}
	
}
