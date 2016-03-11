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
package org.eclipse.epf.publishing.ui.wizards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIHelpContexts;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.util.Comparators;
import org.eclipse.epf.library.ui.LibraryUIResources;
import org.eclipse.epf.publishing.ui.PublishingUIPlugin;
import org.eclipse.epf.publishing.ui.PublishingUIResources;
import org.eclipse.epf.ui.wizards.BaseWizardPage;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

/**
 * A wizard page that prompts the user to select a method configuration to
 * publish.
 * 
 * @author Kelvin Low
 * @author Bingxue Xu
 * @author Jinhua Xi
 * @since 1.0
 */
public class SelectConfigPage extends BaseWizardPage {

	/**
	 * The wizard page name.
	 */
	public static final String PAGE_NAME = SelectConfigPage.class.getName();

	protected Table table;

	protected TableViewer configViewer;

	protected Text briefDescText;

	protected List processViews;

	protected String selectedConfigName;
	
	private MethodConfiguration selectedConfig;
	
	private boolean enableConfigFree = true;

	/**
	 * 
	 * @author skannoor
	 * @deprecated (see below AdapterFactoryLabelProvider.)
	 */
	protected class ConfigurationTableLabelProvider extends LabelProvider
			implements ITableLabelProvider {

		public Image getColumnImage(Object element, int index) {
			return null;
		}

		public String getColumnText(Object element, int index) {
			MethodConfiguration config = (MethodConfiguration) element;
			return config.getName();
		}
	}

	protected ILabelProvider labelProvider = new AdapterFactoryLabelProvider(
			TngAdapterFactory.INSTANCE
					.getNavigatorView_ComposedAdapterFactory()) {
		@Override
		public String getColumnText(Object object, int columnIndex) {
			return super.getColumnText(object, columnIndex);
		}

		@Override
		public Image getColumnImage(Object object, int columnIndex) {
			return super.getColumnImage(object, columnIndex);
		}
	};

	/**
	 * Creates a new instance.
	 */
	public SelectConfigPage() {
		super(PAGE_NAME);
		setTitle(PublishingUIResources.selectConfigWizardPage_title);
		setDescription(PublishingUIResources.selectConfigWizardPage_text);
		setImageDescriptor(PublishingUIPlugin.getDefault().getImageDescriptor(
				"full/wizban/PublishConfiguration.gif")); //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Composite composite = createGridLayoutComposite(parent, 1);

		createLabel(composite, PublishingUIResources.configLabel_text);

		table = new Table(composite, SWT.BORDER | SWT.V_SCROLL);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = 120;
		gridData.widthHint = 280;
		table.setLayoutData(gridData);

		configViewer = new TableViewer(table);

		createLabel(composite, AuthoringUIText.DESCRIPTION_TEXT);

		briefDescText = createMultiLineText(composite, "", 360, 80, 1); //$NON-NLS-1$

		initControls();

		addListeners();

		setControl(composite);

		PlatformUI
				.getWorkbench()
				.getHelpSystem()
				.setHelp(
						composite,
						AuthoringUIHelpContexts.CONFIGURATION_PUBLISH_WIZARD_ALL_PAGES_CONTEXT);
	}

	/**
	 * Initializes the wizard page controls with data.
	 */
	public void initControls() {
		// configViewer.setLabelProvider(new ConfigurationTableLabelProvider());
		configViewer.setLabelProvider(labelProvider);
		configViewer.setContentProvider(new ArrayContentProvider());
		MethodConfiguration[] configs = LibraryServiceUtil
				.getMethodConfigurations(LibraryService.getInstance()
						.getCurrentMethodLibrary());
		List<Object> configsList = new ArrayList<Object>();
		configsList.addAll(Arrays.asList(configs));
		Collections.sort(configsList, Comparators.DEFAULT_COMPARATOR);
		
		//For config free process publish
		if (getEnableConfigFree()) {
			MethodConfiguration mockConfig = ConfigFreeProcessPublishUtil.getInstance()
				.getMethodConfigurationForConfigFreeProcess();
			configsList.add(mockConfig);
		}
		
		configViewer.setInput(configsList.toArray());
		// configViewer.setInput(configs);

		if (configsList.size() > 0) {
			// Select the first config and display its brief description.
			table.select(0);
			setBriefDescription((MethodConfiguration)configsList.get(0));
		}
	}

	/**
	 * Adds event handlers to the wizard page controls.
	 */
	protected void addListeners() {
		configViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						StructuredSelection selection = (StructuredSelection) event
								.getSelection();
						if (!selection.isEmpty()) {
							Object[] configs = selection.toArray();
							setBriefDescription((MethodConfiguration) configs[0]);
						}
						setPageComplete(isPageComplete());
						getWizard().getContainer().updateButtons();
					}
				});
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#isCompleted()
	 */
	public boolean isPageComplete() {
		if (LibraryService.getInstance().getCurrentMethodLibrary() == null) {
			setErrorMessage(LibraryUIResources.noOpenLibraryWarning_msg);
			return false;
		}

		setErrorMessage(null);

		if (table.getSelectionCount() > 0) {
			TableItem[] items = table.getSelection();
			selectedConfigName = items[0].getText();
			/*MethodConfiguration config = LibraryServiceUtil
					.getMethodConfiguration(LibraryService.getInstance()
							.getCurrentMethodLibrary(), selectedConfigName);*/
			
			
			MethodConfiguration config =  items[0].getData() instanceof MethodConfiguration ?
					(MethodConfiguration) items[0].getData() : null;
			setSelectedConfig(config);
					
			processViews = null;
			if (config != null) {
				//For config free process publish
				//Data layer will add view for config free process during publishing
				if (ConfigFreeProcessPublishUtil.getInstance().isSameMethodConfiguration(config)) {
					return true;
				}
				
				processViews = getValidConfigViews(config);
			}

			if (processViews != null && processViews.size() > 0) {
				return true;
			} else {
				setErrorMessage(PublishingUIResources.missingViewError_msg);
			}
		}

		return false;
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.BaseWizardPage#getNextPageData()
	 */
	public Object getNextPageData() {
		return getConfigName();
	}

	/**
	 * Gets a list of valid configuration views.
	 * 
	 * @param config
	 *            a method configuration
	 * @return a list of configuration views
	 */
	protected List<ContentCategory> getValidConfigViews(
			MethodConfiguration config) {
		String msg;
		StringBuffer message = new StringBuffer();
		List<ContentCategory> views = new ArrayList<ContentCategory>();
		for (Iterator<ContentCategory> it = config.getProcessViews().iterator(); it
				.hasNext();) {
			ContentCategory view = it.next();
			if (ConfigurationHelper.isContributor(view)) {
				msg = PublishingUIResources.bind(
						PublishingUIResources.invalidViewContributorInfo_msg,
						view.getPresentationName());
				message.append(StrUtil.TAB).append(msg).append(
						StrUtil.LINE_FEED);
			} else {
				view = (ContentCategory) ConfigurationHelper
						.getCalculatedElement(view, config);
				if (view != null) {
					if (views.contains(view)) {
						msg = PublishingUIResources
								.bind(
										PublishingUIResources.invalidViewSameViewInfo_msg,
										view.getPresentationName());
						message.append(StrUtil.TAB).append(msg).append(
								StrUtil.LINE_FEED);

					} else {
						views.add(view);
					}
				}
			}
		}

		if (message.length() > 0) {
			msg = PublishingUIResources.bind(
					PublishingUIResources.invalidViewsInfo_msg, config
							.getName());
			message.insert(0, msg + StrUtil.LINE_FEED + StrUtil.LINE_FEED);

			// FIXME! Should not display warning messages in the brief
			// description field.
			briefDescText.setText(message.toString());
		}

		return views;
	}

	/**
	 * Gets the user selected method configuration.
	 * 
	 * @return the name of the user selected configuration or <code>null</code>
	 */
	public String getConfigName() {
		return selectedConfig.getName();
	}

	/**
	 * Populates the Brief Description text control with the given
	 * configuration's brief description.
	 */
	private void setBriefDescription(MethodConfiguration config) {
		briefDescText.setText(config.getBriefDescription());
	}

	public MethodConfiguration getSelectedConfig() {
		return selectedConfig;
	}

	public void setSelectedConfig(MethodConfiguration selectedConfig) {
		this.selectedConfig = selectedConfig;
	}
	
	public void setEnableConfigFree(boolean enable) {
		enableConfigFree = enable;
	}
	
	public boolean getEnableConfigFree() {
		return enableConfigFree;
	}
	
}
