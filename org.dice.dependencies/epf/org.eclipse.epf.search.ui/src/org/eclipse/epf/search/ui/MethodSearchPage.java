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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.util.ExtensionManager;
import org.eclipse.epf.search.ui.internal.IMethodSearchInputExtension;
import org.eclipse.epf.search.ui.internal.IMethodSearchInputFactory;
import org.eclipse.epf.search.ui.internal.IMethodSearchScopeGroup;
import org.eclipse.epf.search.ui.internal.IMethodSearchScopeGroupFactory;
import org.eclipse.epf.search.ui.internal.MethodSearchInput;
import org.eclipse.epf.search.ui.internal.MethodSearchQuery;
import org.eclipse.epf.search.ui.internal.MethodSearchScope;
import org.eclipse.epf.search.ui.internal.MethodSearchScopeViewer;
import org.eclipse.epf.search.ui.preferences.SearchUIPreferences;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.search.ui.ISearchPage;
import org.eclipse.search.ui.ISearchPageContainer;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.osgi.framework.Bundle;

/**
 * Displays the Method Search page in the Search dialog.
 * 
 * @author Kelvin Low
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class MethodSearchPage extends DialogPage implements ISearchPage {

	public static final String SEARCH_PAGE_ID = MethodSearchPage.class
			.getName();

	private static List<IMethodSearchInputFactory> searchInputFactories;

	private Combo searchStringCombo;

	private Combo namePatternCombo;

	private Button caseSensitiveCheckbox;

	private MethodSearchScopeViewer searchScopeViewer;

	private ISearchPageContainer container;

	private List<IMethodSearchInputExtension> additionalSearchInputs = new ArrayList<IMethodSearchInputExtension>();

	/**
	 * Creates a new instance.
	 */
	public MethodSearchPage() {
		super();
	}

	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		initializeDialogUnits(parent);

		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.horizontalSpacing = 5;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label searchStringLabel = new Label(composite, SWT.NONE);
		searchStringLabel.setText(SearchUIResources.searchStringLabel_text); 

		new Label(composite, SWT.NONE);

		searchStringCombo = new Combo(composite, SWT.NONE);
		searchStringCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		String savedSearchString = SearchUIPreferences.getSearchString();
		if (savedSearchString != null && savedSearchString.length() > 0) {
			searchStringCombo.setText(savedSearchString);
		}

		caseSensitiveCheckbox = new Button(composite, SWT.CHECK);
		caseSensitiveCheckbox.setText(SearchUIResources.caseSensitiveCheckbox_text); 
		caseSensitiveCheckbox.setSelection(SearchUIPreferences
				.getCaseSensitive());

		Label elementNameLabel = new Label(composite, SWT.NONE);
		elementNameLabel.setText(SearchUIResources.elementNameLabel_text); 

		new Label(composite, SWT.NONE);

		namePatternCombo = new Combo(composite, SWT.NONE);
		namePatternCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		String savedNamePattern = SearchUIPreferences.getNamePattern();
		if (savedNamePattern != null && savedNamePattern.length() > 0) {
			namePatternCombo.setText(savedNamePattern);
		} 
//		else {
//			namePatternCombo.setText("*"); //$NON-NLS-1$
//		}

		namePatternCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				container.setPerformActionEnabled(getSearchButtonEnabled());
			}
		});
		namePatternCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent event) {
				container.setPerformActionEnabled(getSearchButtonEnabled());
			}
		});

		new Label(composite, SWT.NONE);

		collectContributedSearchInputs(composite);
		
		searchScopeViewer = createSearchScopeGroup(composite).getSearchScopeViewer();

		container.setPerformActionEnabled(getSearchButtonEnabled());

		setControl(composite);
		Dialog.applyDialogFont(composite);
	}
	
	private IMethodSearchScopeGroup createSearchScopeGroup(Composite parent) {
		Object ext = ExtensionManager.getExtension(SearchUIPlugin.getDefault().getId(), "searchScopeGroupFactory"); //$NON-NLS-1$
		if(ext instanceof IMethodSearchScopeGroupFactory) {
			IMethodSearchScopeGroup group = ((IMethodSearchScopeGroupFactory) ext).createSearchScopeGroup(parent);
			if(group != null) {
				return group;
			}
		}
		Group searchScopeGroup = new Group(parent, SWT.NONE);
		searchScopeGroup.setLayout(new GridLayout(1, false));
		GridData searchScopeGroupGridData = new GridData(GridData.FILL_BOTH);
		searchScopeGroupGridData.heightHint = 200;
		searchScopeGroup.setLayoutData(searchScopeGroupGridData);
		searchScopeGroup.setText(SearchUIResources.scopeGroup_text); 
		final MethodSearchScopeViewer viewer = new MethodSearchScopeViewer(searchScopeGroup, SWT.BORDER);
		return new IMethodSearchScopeGroup() {

			public MethodSearchScopeViewer getSearchScopeViewer() {
				return viewer;
			}
			
		};
	}

	private static List<IMethodSearchInputFactory> getSearchInputFactories() {
		if (searchInputFactories == null) {
			searchInputFactories = new ArrayList<IMethodSearchInputFactory>();
			// Process the contributors.
			//
			IExtensionRegistry extensionRegistry = Platform
					.getExtensionRegistry();
			IExtensionPoint extensionPoint = extensionRegistry
					.getExtensionPoint(SearchUIPlugin.getDefault().getId(),
							"searchInputFactories"); //$NON-NLS-1$
			if (extensionPoint != null) {
				IExtension[] extensions = extensionPoint.getExtensions();
				Object ext = null;
				for (int i = 0; i < extensions.length; i++) {
					IExtension extension = extensions[i];
					String pluginId = extension.getNamespaceIdentifier();
					Bundle bundle = Platform.getBundle(pluginId);
					IConfigurationElement[] configElements = extension
							.getConfigurationElements();
					for (int j = 0; j < configElements.length; j++) {
						IConfigurationElement configElement = configElements[j];
						try {
							String className = configElement
									.getAttribute("class"); //$NON-NLS-1$
							if (className != null) {
								ext = bundle.loadClass(className).newInstance();
								if (ext instanceof IMethodSearchInputFactory) {
									searchInputFactories
											.add((IMethodSearchInputFactory) ext);
								}
							}
						} catch (Exception e) {
							SearchUIPlugin.getDefault().getLogger().logError(e);
						}
					}
				}
			}
		}
		return searchInputFactories;

	}
	
	private void collectContributedSearchInputs(Composite parent) {
		for (IMethodSearchInputFactory factory : getSearchInputFactories()) {
			try {
				IMethodSearchInputExtension searchInput = factory.createSearchInput(parent);
				if(searchInput != null) {
					additionalSearchInputs .add(searchInput);
				}
			}
			catch(Exception e) {
				SearchUIPlugin.getDefault().getLogger().logError(e);
			}
		}
	}

	/**
	 * @see org.eclipse.search.ui.ISearchPage#performAction()
	 */
	public boolean performAction() {
		MethodLibrary library = LibraryService.getInstance().getCurrentMethodLibrary();
		if (library == null) {
			SearchUIPlugin.getDefault().getMsgDialog().displayError(
					SearchUIResources.searchError_title, 
					SearchUIResources.searchError_msg, 
					SearchUIResources.searchError_reason); 
			return false;
		}
		String searchString = searchStringCombo.getText().trim();
		String namePattern = namePatternCombo.getText().trim();
		if(StrUtil.isBlank(namePattern)) {
			namePattern = "*"; //$NON-NLS-1$
		}
		MethodSearchScope searchScope = searchScopeViewer.getSearchScope();
		MethodSearchInput searchInput = new MethodSearchInput(searchString,
				namePattern, caseSensitiveCheckbox.getSelection(), false,
				false, searchScope);
		if(!additionalSearchInputs.isEmpty()) {
			for (IMethodSearchInputExtension input : additionalSearchInputs) {
				searchInput.getAdditionalInput().putAll(input.getInput());
			}
		}
		MethodSearchQuery searchQuery = new MethodSearchQuery(searchInput);
		NewSearchUI.activateSearchResultView();
		NewSearchUI.runQueryInBackground(searchQuery);
		SearchUIPreferences.setSearchString(searchStringCombo.getText());
		SearchUIPreferences.setNamePattern(namePatternCombo.getText());
		SearchUIPreferences.setCaseSensitive(caseSensitiveCheckbox
				.getSelection());
		return true;
	}

	/**
	 * @see org.eclipse.search.ui.ISearchPage#setContainer(ISearchPageContainer)
	 */
	public void setContainer(ISearchPageContainer container) {
		this.container = container;
	}

	/**
	 * @see org.eclipse.search.ui.IReplacePage#performReplace()
	 */
	public boolean performReplace() {
		return true;
	}

	/**
	 * @see org.eclipse.jface.dialogs.DialogPage#dispose()
	 */
	public void dispose() {
		super.dispose();
	}

	/**
	 * Gets the enabled status of the Search button.
	 * 
	 * @return <code>true<code> if the Search button should be enabled
	 */
	private boolean getSearchButtonEnabled() {
		return true;
	}

}
