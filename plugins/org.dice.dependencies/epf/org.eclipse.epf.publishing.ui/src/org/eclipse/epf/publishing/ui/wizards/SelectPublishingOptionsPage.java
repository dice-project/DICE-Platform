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
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.epf.authoring.ui.AuthoringUIHelpContexts;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.layout.elements.AbstractElementLayout;
import org.eclipse.epf.library.layout.elements.ActivityLayout;
import org.eclipse.epf.publishing.services.PublishHTMLOptions;
import org.eclipse.epf.publishing.services.PublishOptions;
import org.eclipse.epf.publishing.ui.PublishingUIPlugin;
import org.eclipse.epf.publishing.ui.PublishingUIResources;
import org.eclipse.epf.publishing.ui.preferences.PublishingUIPreferences;
import org.eclipse.epf.ui.wizards.BaseWizardPage;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

/**
 * A wizard page that prompts the user to select the publishing options for the
 * generated web site.
 * 
 * @author Kelvin Low
 * @author Bingxue Xu
 * @author Jinhua Xi
 * @since 1.0
 * fix for https://bugs.eclipse.org/bugs/show_bug.cgi?id=206567
 */
public class SelectPublishingOptionsPage extends BaseWizardPage {

	/**
	 * The wizard page name.
	 */
	public static final String PAGE_NAME = SelectPublishingOptionsPage.class
			.getName();

	protected Shell shell;
	
	protected Composite composite;
	
	protected Group layoutGroup;

	protected Text titleText;

	protected Text aboutHTMLText;

	protected Text feedbackURLText;

	protected Button selectHTMLButton;

	protected Button publishGlossaryCheckbox;

	protected Button publishIndexCheckbox;

	protected Text bannerImageText;

	protected Button selectImageButton;

	protected Button checkExternalLinksCheckbox;

	protected Button convertBrokenLinksCheckbox;

	protected Button publishUnopenADDCheckbox;

	protected Button publishBaseADCheckbox;

	protected Button publishLightWeightTreeCheckbox;

	protected Button extraDescriptorInfoCtr;
	
	protected Button showLinkedPageForDescriptorCtr;

	protected Button showRelatedDescriptors;
	
	protected Button showRelatedDescriptorsOption;

	protected Button showDescriptorsInNavigationTree;
	
	protected Button showRelatedLinks;

	protected ComboViewer activityTabViewer;

	protected MethodConfiguration config;

	protected List<MethodConfiguration> selectedConfigs = new ArrayList<MethodConfiguration>();

	public static Map<String, String> activityTabMap = new TreeMap<String, String>();

	static {
		activityTabMap.put(ActivityLayout.TAB_NAME_ACTIVITY_DESC,
				AuthoringUIResources.descriptionPage_title);
		activityTabMap.put(ActivityLayout.TAB_NAME_ACTIVITY_WBS,
				AuthoringUIResources.ProcessEditor_WorkBreakdownStructure);
		activityTabMap.put(ActivityLayout.TAB_NAME_ACTIVITY_TBS,
				AuthoringUIResources.ProcessEditor_TeamAllocation);
		activityTabMap.put(ActivityLayout.TAB_NAME_ACTIVITY_WPBS,
				AuthoringUIResources.ProcessEditor_WorkProductUsage);
	}

	/**
	 * Creates a new instance.
	 */
	public SelectPublishingOptionsPage(String pageName) {
		super(pageName,
				PublishingUIResources.selectPublishingOptionsWizardPage_title,
				PublishingUIResources.selectPublishingOptionsWizardPage_text,
				PublishingUIPlugin.getDefault().getImageDescriptor(
						"full/wizban/PublishConfiguration.gif")); //$NON-NLS-1$			
	}

	/**
	 * Creates a new instance.
	 */
	public SelectPublishingOptionsPage() {
		this(PAGE_NAME);
	}

	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		shell = parent.getShell();

		ScrolledComposite scrolledComposite = new ScrolledComposite(
				parent, 
				SWT.V_SCROLL | SWT.H_SCROLL);
		
		composite = createGridLayoutComposite(scrolledComposite, 1);
		
		scrolledComposite.setContent(composite);

		createTitleAndLinksGroup(composite);
		createGlossaryAndIndexGroup(composite);
		createLookAndFeelGroup(composite);
		createValidation(composite);
		createDiagramGenerationGroup(composite);
		createLayoutGroup(composite);

		Point defaultSize = composite.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		composite.setSize(defaultSize);
		
		initControls();

		addListeners();

		setControl(scrolledComposite);

		PlatformUI
				.getWorkbench()
				.getHelpSystem()
				.setHelp(
						scrolledComposite,
						AuthoringUIHelpContexts.CONFIGURATION_PUBLISH_WIZARD_ALL_PAGES_CONTEXT);
	}

	/**
	 * Creates the Title and links group.
	 * 
	 * @param composite
	 *            the parent composite
	 * @return the group composite
	 */
	protected Group createTitleAndLinksGroup(Composite composite) {
		Group titleAndLinksGroup = createGridLayoutGroup(composite,
				PublishingUIResources.titleAndLinksGroup_text, 3);

		createLabel(titleAndLinksGroup, PublishingUIResources.titleLabel_text);
		titleText = createEditableText(titleAndLinksGroup, "", 360, 1); //$NON-NLS-1$
		createLabel(titleAndLinksGroup, ""); //$NON-NLS-1$

		createLabel(titleAndLinksGroup,
				PublishingUIResources.aboutHTMLLabel_text);
		aboutHTMLText = createEditableText(titleAndLinksGroup, "", 360, 1); //$NON-NLS-1$
		selectHTMLButton = new Button(titleAndLinksGroup, SWT.NONE);
		selectHTMLButton.setText(AuthoringUIText.SELECT_BUTTON_TEXT);

		createLabel(titleAndLinksGroup,
				PublishingUIResources.feedbackURLLabel_text);
		feedbackURLText = createEditableText(titleAndLinksGroup, "", 360, 1); //$NON-NLS-1$
		createLabel(titleAndLinksGroup, ""); //$NON-NLS-1$

		return titleAndLinksGroup;
	}

	/**
	 * Creates the Look and feel group.
	 * 
	 * @param composite
	 *            the parent composite
	 * @return the group composite
	 */
	protected Group createLookAndFeelGroup(Composite composite) {
		Group lookAndFeelGroup = createGridLayoutGroup(composite,
				PublishingUIResources.lookAndFeelGroup_text, 3);

		createLabel(lookAndFeelGroup,
				PublishingUIResources.bannerImageLabel_text);
		bannerImageText = createEditableText(lookAndFeelGroup, "", 360, 1); //$NON-NLS-1$
		selectImageButton = new Button(lookAndFeelGroup, SWT.NONE);
		selectImageButton.setText(AuthoringUIText.SELECT_BUTTON_TEXT);

		return lookAndFeelGroup;
	}

	/**
	 * Creates the Glosary and index group.
	 * 
	 * @param composite
	 *            the parent composite
	 * @return the group composite
	 */
	protected Group createGlossaryAndIndexGroup(Composite composite) {
		Group glossaryAndIndexGroup = createGridLayoutGroup(composite,
				PublishingUIResources.glossaryAndIndexGroup_text, 2);

		publishGlossaryCheckbox = createCheckbox(glossaryAndIndexGroup,
				PublishingUIResources.publishGlossaryLabel_text);
		publishIndexCheckbox = createCheckbox(glossaryAndIndexGroup,
				PublishingUIResources.publishIndexLabel_text);

		return glossaryAndIndexGroup;
	}

	/**
	 * Creates the Validation group.
	 * 
	 * @param composite
	 *            the parent composite
	 * @return the group composite
	 */
	protected Group createValidation(Composite composite) {
		Group validationGroup = createGridLayoutGroup(composite,
				PublishingUIResources.validationGroup_text, 2);

		checkExternalLinksCheckbox = createCheckbox(validationGroup,
				PublishingUIResources.checkExternalLinksLabel_text);

		convertBrokenLinksCheckbox = createCheckbox(validationGroup,
				PublishingUIResources.convertBrokenLinksLabel_text);

		return validationGroup;
	}

	/**
	 * Creates the Diagram Generation group.
	 * 
	 * @param composite
	 *            the parent composite
	 * @return the group composite
	 */
	protected Group createDiagramGenerationGroup(Composite composite) {
		Group diagramGroup = createGridLayoutGroup(composite,
				PublishingUIResources.diagramGroup_text, 1);

		publishUnopenADDCheckbox = createCheckbox(diagramGroup,
				PublishingUIResources.publishActivityDetailDiagramsLabel_text);

		publishBaseADCheckbox = createCheckbox(diagramGroup,
				PublishingUIResources.publishExtendedActivityDiagramsLabel_text);

		return diagramGroup;
	}

	public Group getLayoutGroup() {
		return layoutGroup;
	}

	public void setLayoutGroup(Group layoutGroup) {
		this.layoutGroup = layoutGroup;
	}

	/**
	 * Creates the Layout group.
	 * 
	 * @param composite
	 *            the parent composite
	 * @return the group composite
	 */
	protected Group createLayoutGroup(Composite composite) {
		layoutGroup = createGridLayoutGroup(composite,
				PublishingUIResources.layoutGroup_text, 1);

		publishLightWeightTreeCheckbox = createCheckbox(layoutGroup,
				PublishingUIResources.publishLightWeightTreeLabel_text);

		showRelatedLinks = createCheckbox(layoutGroup,
				PublishingUIResources.showRelatedLinks_text);

		showDescriptorsInNavigationTree = createCheckbox(layoutGroup,
				PublishingUIResources.showDescriptorsInNavigationTree_text);

		extraDescriptorInfoCtr = createCheckbox(layoutGroup,
				PublishingUIResources.publishExtraDescriptorInfoLabel_text);
		
		showLinkedPageForDescriptorCtr = createCheckbox(layoutGroup,
				PublishingUIResources.showLinkedPageForDescriptorLabel_text);

		showRelatedDescriptors = createCheckbox(layoutGroup,
				PublishingUIResources.showRelatedDescriptors_text);		
		Composite descriptorComposite = createChildGridLayoutComposite(layoutGroup, 1);
		showRelatedDescriptorsOption = createCheckbox(descriptorComposite,
				PublishingUIResources.showRelatedDescriptors_option_text);
		
		Composite activityTabComposite = createGridLayoutComposite(layoutGroup,
				2);
		((GridLayout) activityTabComposite.getLayout()).marginTop = -5;
		((GridLayout) activityTabComposite.getLayout()).marginLeft = -5;

		createLabel(activityTabComposite,
				PublishingUIResources.selectDefaultActivityTab_text);

		activityTabViewer = new ComboViewer(activityTabComposite, SWT.BORDER
				| SWT.READ_ONLY);
		GridData gd = new GridData(GridData.BEGINNING
				| GridData.FILL_HORIZONTAL);
		activityTabViewer.getCombo().setLayoutData(gd);

		ILabelProvider labelProvider = new LabelProvider() {
			public String getText(Object param) {
				if (param instanceof Map.Entry) {
					return (String) ((Map.Entry) param).getValue();
				} else {
					return param.toString();
				}
			}
		};
		activityTabViewer.setLabelProvider(labelProvider);

		return layoutGroup;
	}

	/**
	 * Provide a means to add additional options to the published web site
	 * group.
	 * 
	 * @param composite
	 *            the parent composite
	 */
	protected void includeAdditionalPublishingOptions(Composite composite) {
	}

	/**
	 * Creates the publishing options groups to this wizard page.
	 * 
	 * @param composite
	 *            the parent composite
	 */
	protected void createPublishingOptionsGroups(Composite composite) {
	}

	/**
	 * Initializes the wizard page controls with data.
	 */
	protected void initControls() {
		String configId = config != null ? config.getGuid() : ""; //$NON-NLS-1$

		titleText.setText(PublishingUIPreferences.getTitle(configId));
		bannerImageText.setText(PublishingUIPreferences
				.getBannerImage(configId));
		aboutHTMLText.setText(PublishingUIPreferences.getAboutHTML(configId));
		feedbackURLText.setText(PublishingUIPreferences
				.getFeedbackURL(configId));
		publishGlossaryCheckbox.setSelection(PublishingUIPreferences
				.getIncludeGlossary(configId));
		publishIndexCheckbox.setSelection(PublishingUIPreferences
				.getIncludeIndex(configId));
		checkExternalLinksCheckbox.setSelection(PublishingUIPreferences
				.getCheckExternalLinks(configId));
		convertBrokenLinksCheckbox.setSelection(PublishingUIPreferences
				.getConvertBrokenLinks(configId));
		publishUnopenADDCheckbox.setSelection(PublishingUIPreferences
				.getPublishUnopenActivityDD(configId));
		publishBaseADCheckbox.setSelection(PublishingUIPreferences
				.getPublishADForActivityExtension(configId));
		publishLightWeightTreeCheckbox.setSelection(!PublishingUIPreferences
				.getLightWeightTree(configId));
		extraDescriptorInfoCtr.setSelection(PublishingUIPreferences
				.getExtraDescriptorInfo(configId));
		showLinkedPageForDescriptorCtr.setSelection(PublishingUIPreferences
				.getShowLinkedElementForDescriptor(configId));
		showRelatedDescriptors.setSelection(PublishingUIPreferences
				.getShowRelatedDescriptors(configId));
		showRelatedLinks.setSelection(PublishingUIPreferences
				.getShowRelatedLinks(configId));
		showRelatedDescriptorsOption.setSelection(PublishingUIPreferences
				.getShowRelatedDescriptorsOption(configId));
		if (showRelatedDescriptors.getSelection()) {
			showRelatedDescriptorsOption.setEnabled(true);
		} else {
			showRelatedDescriptorsOption.setEnabled(false);
		}
		if (showRelatedDescriptorsOption.getSelection()) {
			AbstractElementLayout.processDescritorsNewOption = true;
		} else {
			AbstractElementLayout.processDescritorsNewOption = false;
		}
		showDescriptorsInNavigationTree.setSelection(PublishingUIPreferences
				.getShowDescriptorsInNavigationTree(configId));

		if (activityTabViewer.getCombo().getItemCount() == 0) {
			activityTabViewer.add(activityTabMap.entrySet().toArray());
		}
		activityTabViewer.getCombo().setText(
				activityTabMap.get(PublishingUIPreferences
						.getDefaultActivityTab(configId)));

	}

	/**
	 * Adds the listeners for the controls on this page.
	 */
	protected void addListeners() {
		selectImageButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				try {
					FileDialog dialog = new FileDialog(shell, SWT.OPEN);
					dialog.setFilterExtensions(new String[] {
							"*.gif", "*.jpg", "*.bmp" }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					String imageFile = dialog.open();
					if (imageFile != null) {
						bannerImageText.setText(imageFile);
					}
				} catch (Exception e) {
					PublishingUIPlugin.getDefault().getLogger().logError(e);
				}
			}
		});

		selectHTMLButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				try {
					FileDialog dialog = new FileDialog(shell, SWT.OPEN);
					dialog
							.setFilterExtensions(new String[] {
									"*.htm", "*.html" }); //$NON-NLS-1$ //$NON-NLS-2$
					String htmlFile = dialog.open();
					if (htmlFile != null) {
						aboutHTMLText.setText(htmlFile);
					}
				} catch (Exception e) {
					PublishingUIPlugin.getDefault().getLogger().logError(e);
				}
			}
		});

		aboutHTMLText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				setPageComplete(isPageComplete());
			}
		});
		
		showRelatedDescriptors.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				if (showRelatedDescriptors.getSelection()) {
					showRelatedDescriptorsOption.setEnabled(true);
				} else {
					showRelatedDescriptorsOption.setSelection(false);
					AbstractElementLayout.processDescritorsNewOption = false;
					showRelatedDescriptorsOption.setEnabled(false);
				}
			}
		});
		
		showRelatedDescriptorsOption.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				if (showRelatedDescriptorsOption.getSelection()) {
					AbstractElementLayout.processDescritorsNewOption = true;
				} else {
					AbstractElementLayout.processDescritorsNewOption = false;
				}
			}
		});
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.BaseWizardPage#onEnterPage(Object)
	 */
	public void onEnterPage(Object obj) {
		if (obj != null && obj instanceof MethodConfiguration) {
			MethodConfiguration selectedConfig = (MethodConfiguration) obj;
			if (!selectedConfigs.contains(selectedConfig)) {
				selectedConfigs.add(selectedConfig);
			}
			if (selectedConfig != config) {
				config = selectedConfig;
				initControls();
			}
		}
		
		//resize the content composite in the scrolled parent
		Point size = composite.getSize();
		Point parentSize = composite.getParent().getSize();
		int borderWidth = composite.getParent().getBorderWidth();
		
		if(size.x < parentSize.x - 2 * borderWidth){
			composite.setSize(parentSize.x - 2 * borderWidth, size.y);
		}
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	public boolean isPageComplete() {
		String path = aboutHTMLText.getText();
		IPath ecPath = Path.fromOSString(path);
		boolean isValid = ecPath.isValidPath(path);
		if (!isValid) {
			setErrorMessage(PublishingUIResources.invalidAboutPathError_msg);
			return false;
		} else if (!StrUtil.isValidPublishPath(path)) {
			setErrorMessage(PublishingUIResources.invalidAboutPathCharsError_msg);
			return false;
		} else {
			setErrorMessage(null);
		}
		return true;
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.BaseWizardPage#getNextPageData()
	 */
	public Object getNextPageData() {
		return config;
	}

	/**
	 * Gets the user selected publishing options.
	 * 
	 * @return a <code>PublishOptions</code> object
	 */
	public PublishOptions getPublishingOptions() {
		PublishHTMLOptions options = new PublishHTMLOptions();

		options.setTitle(getWebSiteTitle());
		options.setBannerImage(getBannerImagePath());
		options.setAboutHTML(getAboutHTMLPath());
		options.setFeedbackURL(getFeedbackURL());
		options.setPublishGlossary(getPublishGlossarySelection());
		options.setPublishIndex(getPublishIndexSelection());
		options.setCheckExternalLinks(getCheckExternalLinksSelection());
		options.setPublishUnopenADD(getPublishUnopenADDSelection());
		options.setPublishBaseAD(getPublishBaseADSelection());
		options.setConvertBrokenLinks(getConvertBrokenLinksSelection());
		options.setPublishLightWeightTree(getPublishLightWeightTreeSelection());
		options.setShowMethodContentInDescriptors(getShowExtraDescriptorInfoSelection());
		options.setShowLinkedPageForDescriptor(getShowLinkedPageForDescriptorSelection());
		options.setShowRelatedDescriptors(showRelatedDescriptors.getSelection());
		options.setShowRelatedDescriptorsOption(showRelatedDescriptorsOption.getSelection());		
		options.setShowRelatedLinks(showRelatedLinks.getSelection());
		options.setShowDescriptorsInNavigationTree(showDescriptorsInNavigationTree.getSelection());

		String defaultActivityTab = getDefaultActivityTabSelection();
		if (defaultActivityTab != null) {
			options.setDefaultActivityTab(defaultActivityTab);
		}

		return options;
	}

	/**
	 * Gets the user specified web site title.
	 */
	protected String getWebSiteTitle() {
		return titleText.getText().trim();
	}

	/**
	 * Gets the user specified banner image path.
	 */
	protected String getBannerImagePath() {
		return bannerImageText.getText().trim();
	}

	/**
	 * Gets the user specified about HTML path.
	 */
	protected String getAboutHTMLPath() {
		return aboutHTMLText.getText().trim();
	}

	/**
	 * Gets the user specified feedback URL.
	 */
	protected String getFeedbackURL() {
		return feedbackURLText.getText().trim();
	}

	/**
	 * Gets the user specified publish glossary selection.
	 */
	protected boolean getPublishGlossarySelection() {
		return publishGlossaryCheckbox.getSelection();
	}

	/**
	 * Gets the user specified publish index selection.
	 */
	protected boolean getPublishIndexSelection() {
		return publishIndexCheckbox.getSelection();
	}

	/**
	 * Gets the user specified check external links selection.
	 */
	protected boolean getCheckExternalLinksSelection() {
		return checkExternalLinksCheckbox.getSelection();
	}

	/**
	 * Gets the user specified convert broken links selection.
	 */
	protected boolean getConvertBrokenLinksSelection() {
		return convertBrokenLinksCheckbox.getSelection();
	}

	/**
	 * Gets the user specified auto generate activity detail diagrams selection.
	 */
	protected boolean getPublishUnopenADDSelection() {
		return publishUnopenADDCheckbox.getSelection();
	}

	/**
	 * Gets the user specified publish activity diagram for base activties with
	 * unmodified extensions.
	 */
	protected boolean getPublishBaseADSelection() {
		return publishBaseADCheckbox.getSelection();
	}

	/**
	 * Gets the user specified generate light weight tree selection.
	 */
	protected boolean getPublishLightWeightTreeSelection() {
		return !publishLightWeightTreeCheckbox.getSelection();
	}

	/**
	 * Gets the user specified show extra descriptor info selection.
	 */
	protected boolean getShowExtraDescriptorInfoSelection() {
		return extraDescriptorInfoCtr.getSelection();
	}
	
	protected boolean getShowLinkedPageForDescriptorSelection() {
		return showLinkedPageForDescriptorCtr.getSelection();
	}

	/**
	 * Gets the user specified show related descriptor in content page
	 * selection.
	 */
	protected boolean getShowRelatedDescriptorsSelection() {
		return showRelatedDescriptors.getSelection();
	}
	
	/**
	 * Gets the user specified Show all indirect (green) occurrences 
	 * in extended patterns
	 */
	protected boolean getShowRelatedDescriptorsOptionSelection() {
		return showRelatedDescriptorsOption.getSelection();
	}
	
	/**
	 * Gets the user specified show related links in navigate page
	 * selection.
	 */
	protected boolean getShowRelatedLinksSelection() {
		return showRelatedLinks.getSelection();
	}
	
	/**
	 * Gets the user specified show descriptors in navigation tree selection.
	 */
	protected boolean getShowDescriptorsInNavigationTreeSelection() {
		return showDescriptorsInNavigationTree.getSelection();
	}

	/**
	 * Gets the user specified default activity tab selection.
	 */
	protected String getDefaultActivityTabSelection() {
		IStructuredSelection sel = (IStructuredSelection) activityTabViewer
				.getSelection();
		Map.Entry item = (Map.Entry) sel.getFirstElement();
		if (item == null) {
			return null;
		} else {
			return (String) item.getKey();
		}
	}

	/**
	 * Saves the user selections in this wizard page to preference store.
	 */
	public void savePreferences() {
		if (config != null) {
			String configId = config.getGuid();
			saveTitleAndLinksPreferences(configId);
			saveGlossaryAndIndexPreferences(configId);
			saveValidationPreferences(configId);
			saveDiagramGenerationPreferences(configId);
			saveLayoutPreferences(configId);
		}
	}

	/**
	 * Saves the title and links preferences.
	 */
	protected void saveTitleAndLinksPreferences(String configId) {
		PublishingUIPreferences.setTitle(configId, getWebSiteTitle());
		PublishingUIPreferences.setBannerImage(configId, getBannerImagePath());
		PublishingUIPreferences.setAboutHTML(configId, getAboutHTMLPath());
		PublishingUIPreferences.setFeedbackURL(configId, getFeedbackURL());
	}

	/**
	 * Saves the glosaary and index preferences.
	 */
	protected void saveGlossaryAndIndexPreferences(String configId) {
		PublishingUIPreferences.setIncludeGlossary(configId,
				getPublishGlossarySelection());
		PublishingUIPreferences.setIncludeIndex(configId,
				getPublishIndexSelection());
	}

	/**
	 * Saves the validation preferences.
	 */
	protected void saveValidationPreferences(String configId) {
		PublishingUIPreferences.setCheckExternalLinks(configId,
				getCheckExternalLinksSelection());
		PublishingUIPreferences.setConvertBrokenLinks(configId,
				getConvertBrokenLinksSelection());
	}

	/**
	 * Saves the diagram generation preferences.
	 */
	protected void saveDiagramGenerationPreferences(String configId) {
		PublishingUIPreferences.setPublishUnopenActivityDD(configId,
				getPublishUnopenADDSelection());
		PublishingUIPreferences.setPublishADForActivityExtension(configId,
				getPublishBaseADSelection());
	}

	/**
	 * Saves the layout preferences.
	 */
	protected void saveLayoutPreferences(String configId) {
		PublishingUIPreferences.setLightWeightTree(configId,
				getPublishLightWeightTreeSelection());
		PublishingUIPreferences.setExtraDescriptorInfo(configId,
				getShowExtraDescriptorInfoSelection());
		PublishingUIPreferences.setShowLinkedElementForDescriptor(configId,
				getShowLinkedPageForDescriptorSelection());
		PublishingUIPreferences.setShowRelatedDescriptors(configId,
				getShowRelatedDescriptorsSelection());
		PublishingUIPreferences.setShowRelatedDescriptorsOption(configId,
				getShowRelatedDescriptorsOptionSelection());
		PublishingUIPreferences.setShowRelatedLinks(configId,
				getShowRelatedLinksSelection());
		PublishingUIPreferences.setShowDescriptorsInNavigationTree(configId,
				getShowDescriptorsInNavigationTreeSelection());
		PublishingUIPreferences.setDefaultActivityTab(configId,
				getDefaultActivityTabSelection());
	}

}