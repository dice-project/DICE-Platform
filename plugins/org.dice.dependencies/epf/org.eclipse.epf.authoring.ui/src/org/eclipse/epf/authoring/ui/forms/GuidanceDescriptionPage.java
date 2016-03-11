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
package org.eclipse.epf.authoring.ui.forms;

import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditor;
import org.eclipse.epf.authoring.ui.filters.ContentFilter;
import org.eclipse.epf.authoring.ui.richtext.IMethodRichText;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.itemsfilter.VariabilityBaseElementFilter;
import org.eclipse.epf.library.edit.util.PracticePropUtil;
import org.eclipse.epf.library.ui.LibraryUIText;
import org.eclipse.epf.uma.Checklist;
import org.eclipse.epf.uma.Concept;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.EstimationConsiderations;
import org.eclipse.epf.uma.Example;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.Guideline;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.Report;
import org.eclipse.epf.uma.ReusableAsset;
import org.eclipse.epf.uma.Roadmap;
import org.eclipse.epf.uma.SupportingMaterial;
import org.eclipse.epf.uma.Template;
import org.eclipse.epf.uma.TermDefinition;
import org.eclipse.epf.uma.ToolMentor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.Whitepaper;
import org.eclipse.epf.uma.util.UserDefinedTypeMeta;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;


/**
 * The Description page in a Guidance editor.
 * 
 * @author Kelvin Low
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class GuidanceDescriptionPage extends DescriptionFormPage {

	private static final String FORM_PAGE_ID = "guidanceDescriptionPage"; //$NON-NLS-1$

	protected Guidance guidance;

	private String elementLabel;

	private IMethodRichText ctrl_content;
	protected boolean contentOn = true;

	private int contentFieldHeight = 400;

	/**
	 * Creates a new instance.
	 */
	public GuidanceDescriptionPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.DESCRIPTION_PAGE_TITLE);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		guidance = (Guidance) contentElement;
		elementLabel = LibraryUIText.getUIText(guidance);
		setElementTypeOn(true);
		if (guidance instanceof TermDefinition)
			setBriefDescOn(false);
		setFullDescOn(false);
		setKeyConsiderationOn(false);
		setIconSectionOn(true);
		setExternalIDOn(true);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getFormPrefix()
	 */
	protected String getFormPrefix() {
		return new StringBuffer(LibraryUIText.TEXT_GUIDANCE)
				.append(" (").append(elementLabel).append("): ").toString(); //$NON-NLS-1$ //$NON-NLS-2$	
	}

	/**
	 * Creates the editor tab content
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 */
	protected void createEditorContent(FormToolkit toolkit) {
		super.createEditorContent(toolkit);
		if (contentOn) {
			ctrl_content = createRichTextEditWithLinkForSection(toolkit,
					detailComposite, AuthoringUIText.MAIN_DESCRIPTION_TEXT,
					contentFieldHeight, 400, DETAIL_SECTION_ID);
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#addListeners()
	 */
	protected void addListeners() {
		super.addListeners();
		if (contentOn) {
	
			final MethodElementEditor editor = (MethodElementEditor) getEditor();
	
			ctrl_content.setModalObject(contentElement.getPresentation());
			ctrl_content.setModalObjectFeature(UmaPackage.eINSTANCE
					.getContentDescription_MainDescription());
			ctrl_content.addModifyListener(contentModifyListener);
			ctrl_content.addListener(SWT.Deactivate, new Listener() {
				public void handleEvent(Event e) {
					IMethodRichText control = descExpandFlag ? ctrl_expanded
							: ctrl_content;
					if (!control.getModified()) {
						return;
					}
					String oldContent = guidance.getPresentation()
							.getMainDescription();
					if (((MethodElementEditor) getEditor()).mustRestoreValue(
							control, oldContent)) {
						return;
					}
					String newContent = control.getText();
					if (!newContent.equals(oldContent)) {
						boolean success = editor.getActionManager().doAction(
								IActionManager.SET,
								contentElement.getPresentation(),
								UmaPackage.eINSTANCE
										.getContentDescription_MainDescription(),
								newContent, -1);
						if (success && isVersionSectionOn()) {
							updateChangeDate();
						}
					}
				}
			});
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#refresh(boolean)
	 */
	protected void refresh(boolean editable) {
		super.refresh(editable);
		if (contentOn) {
			ctrl_content.setEditable(editable);
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#loadData()
	 */
	protected void loadData() {
		super.loadData();
		if (contentOn) {
			if (guidance != null) {
				String content = null;
				if (guidance.getPresentation() != null) {
					content = guidance.getPresentation().getMainDescription();
				}
				ctrl_content.setText(content == null ? "" : content); //$NON-NLS-1$
			}
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getContentElement()
	 */
	protected Object getContentElement() {
		return guidance;
	}

	/**
	 * Returns the height of the Main Description text box.
	 */
	public int getContentFieldHeight() {
		return contentFieldHeight;
	}

	/**
	 * Sets the height of the Main Description text box.
	 */
	public void setContentFieldHeight(int contentFieldHeight) {
		this.contentFieldHeight = contentFieldHeight;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getTabString()
	 */
	protected String getTabString() {
		return FilterConstants.GUIDANCE;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getFilter()
	 */
	protected IFilter getFilter() {
		filter = new ContentFilter() {
			protected boolean childAccept(Object obj) {
				if (obj instanceof Guidance) {
					return (((ContentElement) obj).getType()
							.equals(((Guidance) contentElement).getType()));
				}
				return false;
			}
		};

		// Set additional filter for variability base element checking.
		((ContentFilter) filter)
				.setAdditionalFilters(new IFilter[] { new VariabilityBaseElementFilter(
						guidance) });
		return filter;
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#loadSectionDescription()
	 */
	public void loadSectionDescription() {
		if(contentElement instanceof Whitepaper){
			this.generalSectionDescription = AuthoringUIResources.whitepaper_generalInfoSection_desc;
			this.detailSectionDescription = AuthoringUIResources.whitepaper_detailSection_desc;
			this.variabilitySectionDescription = AuthoringUIResources.whitepaper_variabilitySection_desc;
			this.versionSectionDescription = AuthoringUIResources.whitepaper_versionInfoSection_desc;
			this.iconSectionDescription = AuthoringUIResources.whitepaper_iconSection_desc;
		}
		else if(contentElement instanceof Concept){
			this.generalSectionDescription = AuthoringUIResources.concept_generalInfoSection_desc;
			this.detailSectionDescription = AuthoringUIResources.concept_detailSection_desc;
			this.variabilitySectionDescription = AuthoringUIResources.concept_variabilitySection_desc;
			this.versionSectionDescription = AuthoringUIResources.concept_versionInfoSection_desc;
			this.iconSectionDescription = AuthoringUIResources.concept_iconSection_desc;
		}
		else if(contentElement instanceof Checklist){
			this.generalSectionDescription = AuthoringUIResources.checklist_generalInfoSection_desc;
			this.detailSectionDescription = AuthoringUIResources.checklist_detailSection_desc;
			this.variabilitySectionDescription = AuthoringUIResources.checklist_variabilitySection_desc;
			this.versionSectionDescription = AuthoringUIResources.checklist_versionInfoSection_desc;
			this.iconSectionDescription = AuthoringUIResources.checklist_iconSection_desc;
		}
		else if(contentElement instanceof Example){
			this.generalSectionDescription = AuthoringUIResources.example_generalInfoSection_desc;
			this.detailSectionDescription = AuthoringUIResources.example_detailSection_desc;
			this.variabilitySectionDescription = AuthoringUIResources.example_variabilitySection_desc;
			this.versionSectionDescription = AuthoringUIResources.example_versionInfoSection_desc;
			this.iconSectionDescription = AuthoringUIResources.example_iconSection_desc;
		}
		else if(contentElement instanceof Guideline){
			this.generalSectionDescription = AuthoringUIResources.guideline_generalInfoSection_desc;
			this.detailSectionDescription = AuthoringUIResources.guideline_detailSection_desc;
			this.variabilitySectionDescription = AuthoringUIResources.guideline_variabilitySection_desc;
			this.versionSectionDescription = AuthoringUIResources.guideline_versionInfoSection_desc;
			this.iconSectionDescription = AuthoringUIResources.guideline_iconSection_desc;
		}
		else if(contentElement instanceof EstimationConsiderations){
			this.generalSectionDescription = AuthoringUIResources.estimationconsideration_generalInfoSection_desc;
			this.detailSectionDescription = AuthoringUIResources.estimationconsideration_detailSection_desc;
			this.variabilitySectionDescription = AuthoringUIResources.estimationconsideration_variabilitySection_desc;
			this.versionSectionDescription = AuthoringUIResources.estimationconsideration_versionInfoSection_desc;
			this.iconSectionDescription = AuthoringUIResources.estimationconsideration_iconSection_desc;
		}
		else if(contentElement instanceof Practice){
			if (PracticePropUtil.getPracticePropUtil().isUdtType(contentElement)) {
				try {
					String typeName = PracticePropUtil.getPracticePropUtil()
						.getUtdData((Practice)contentElement)
						.getRteNameMap().get(UserDefinedTypeMeta._typeName).toLowerCase();
					this.generalSectionDescription = NLS.bind(AuthoringUIResources.generalInfoSection_desc, typeName);
					this.detailSectionDescription = NLS.bind(AuthoringUIResources.detailSection_desc, typeName);
					this.variabilitySectionDescription = NLS.bind(AuthoringUIResources.variabilitySection_desc, typeName);
					this.versionSectionDescription = NLS.bind(AuthoringUIResources.versionInfoSection_desc, typeName);
					this.iconSectionDescription = NLS.bind(AuthoringUIResources.iconSection_desc, typeName);
				} catch (Exception e) {
					AuthoringUIPlugin.getDefault().getLogger().logError(e);
				}				
			} else {			
				this.generalSectionDescription = AuthoringUIResources.practice_generalInfoSection_desc;
				this.detailSectionDescription = AuthoringUIResources.practice_detailSection_desc;
				this.variabilitySectionDescription = AuthoringUIResources.practice_variabilitySection_desc;
				this.versionSectionDescription = AuthoringUIResources.practice_versionInfoSection_desc;
				this.iconSectionDescription = AuthoringUIResources.practice_iconSection_desc;
			}
		}
		else if(contentElement instanceof SupportingMaterial){
			this.generalSectionDescription = AuthoringUIResources.supportingmaterial_generalInfoSection_desc;
			this.detailSectionDescription = AuthoringUIResources.supportingmaterial_detailSection_desc;
			this.variabilitySectionDescription = AuthoringUIResources.supportingmaterial_variabilitySection_desc;
			this.versionSectionDescription = AuthoringUIResources.supportingmaterial_versionInfoSection_desc;
			this.iconSectionDescription = AuthoringUIResources.supportingmaterial_iconSection_desc;
		}
		else if(contentElement instanceof Report){
			this.generalSectionDescription = AuthoringUIResources.report_generalInfoSection_desc;
			this.detailSectionDescription = AuthoringUIResources.report_detailSection_desc;
			this.variabilitySectionDescription = AuthoringUIResources.report_variabilitySection_desc;
			this.versionSectionDescription = AuthoringUIResources.report_versionInfoSection_desc;
			this.iconSectionDescription = AuthoringUIResources.report_iconSection_desc;
		}
		else if(contentElement instanceof Roadmap){
			this.generalSectionDescription = AuthoringUIResources.roadmap_generalInfoSection_desc;
			this.detailSectionDescription = AuthoringUIResources.roadmap_detailSection_desc;
			this.variabilitySectionDescription = AuthoringUIResources.roadmap_variabilitySection_desc;
			this.versionSectionDescription = AuthoringUIResources.roadmap_versionInfoSection_desc;
			this.iconSectionDescription = AuthoringUIResources.roadmap_iconSection_desc;
		}
		else if(contentElement instanceof Template){
			this.generalSectionDescription = AuthoringUIResources.template_generalInfoSection_desc;
			this.detailSectionDescription = AuthoringUIResources.template_detailSection_desc;
			this.variabilitySectionDescription = AuthoringUIResources.template_variabilitySection_desc;
			this.versionSectionDescription = AuthoringUIResources.template_versionInfoSection_desc;
			this.iconSectionDescription = AuthoringUIResources.template_iconSection_desc;
		}
		else if(contentElement instanceof TermDefinition){
			this.generalSectionDescription = AuthoringUIResources.termdefinition_generalInfoSection_desc;
			this.detailSectionDescription = AuthoringUIResources.termdefinition_detailSection_desc;
			this.variabilitySectionDescription = AuthoringUIResources.termdefinition_variabilitySection_desc;
			this.versionSectionDescription = AuthoringUIResources.termdefinition_versionInfoSection_desc;
			this.iconSectionDescription = AuthoringUIResources.termdefinition_iconSection_desc;
		}
		else if(contentElement instanceof ReusableAsset){
			this.generalSectionDescription = AuthoringUIResources.resuableasset_generalInfoSection_desc;
			this.detailSectionDescription = AuthoringUIResources.resuableasset_detailSection_desc;
			this.variabilitySectionDescription = AuthoringUIResources.resuableasset_variabilitySection_desc;
			this.versionSectionDescription = AuthoringUIResources.resuableasset_versionInfoSection_desc;
			this.iconSectionDescription = AuthoringUIResources.reusableasset_iconSection_desc;
		}
		else if(contentElement instanceof ToolMentor){
			this.generalSectionDescription = AuthoringUIResources.toolmentor_generalInfoSection_desc;
			this.detailSectionDescription = AuthoringUIResources.toolmentor_detailSection_desc;
			this.variabilitySectionDescription = AuthoringUIResources.toolmentor_variabilitySection_desc;
			this.versionSectionDescription = AuthoringUIResources.toolmentor_versionInfoSection_desc;
			this.iconSectionDescription = AuthoringUIResources.toolmentor_iconSection_desc;
		}
	}

}