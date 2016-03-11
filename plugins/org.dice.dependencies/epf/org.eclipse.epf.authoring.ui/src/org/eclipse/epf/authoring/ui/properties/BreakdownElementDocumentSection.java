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

import java.util.Iterator;

import org.eclipse.epf.authoring.ui.AuthoringUIImages;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditor;
import org.eclipse.epf.authoring.ui.forms.BaseFormPage;
import org.eclipse.epf.authoring.ui.forms.MethodFormToolkit;
import org.eclipse.epf.authoring.ui.richtext.IMethodRichText;
import org.eclipse.epf.authoring.ui.richtext.IMethodRichTextEditor;
import org.eclipse.epf.authoring.ui.util.RichTextImageLinkContainer;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.richtext.RichTextListener;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.BreakdownElementDescription;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;


/**
 * The breakdown element documentation section
 * 
 * @author Shilpa Toraskar
 * @author Kelvin Low
 * @since 1.0
 */
public class BreakdownElementDocumentSection extends AbstractSection {

	// controls
	protected Text prefixText;
	
	protected Label prefixLabel;

	protected Text briefDescText;
	
	protected Label briefDescLabel;
	
	protected Text externalIDText;

	protected Label externalIDLabel;

	protected FormToolkit toolkit;

	protected Section documentSection;

	protected Composite parent;
	
	protected Composite documentComposite;

	protected Composite expandDocumentComposite;

	protected IMethodRichTextEditor ctrl_document_expanded;

	protected IMethodRichText usageGuidance;
	
	protected RichTextImageLinkContainer usageGuidanceContainer;

	protected ImageHyperlink keyConsiderationsLink;
	
	protected IMethodRichText keyConsiderations;
	
	protected RichTextImageLinkContainer keyConsiderationsContainer;

	protected Text ctrl_document_expanded_Text;

	protected ImageHyperlink expandDocumentLink;

	protected Label expandDocumentLabel;

	protected boolean expandFlag = false;

	protected boolean expandFlagText = false;

	protected IMethodRichText activeControl;

	protected Text activeControlText;

	// element
	protected BreakdownElement element;

	// action manager
	protected IActionManager actionMgr;

	// modify listener
	protected ModifyListener modelModifyListener;

	protected ModifyListener contentModifyListener;

	protected String contentElementPath;

	protected String type = ""; //$NON-NLS-1$

	protected String contentElementName = ""; //$NON-NLS-1$

	protected int heightHint = 40;
	
	private Listener usageGuidanceListener = new Listener() {
		public void handleEvent(Event e) {
			element = (BreakdownElement) getElement();
			IMethodRichText control = expandFlag ? ctrl_document_expanded
					: usageGuidance;
			if (!control.getModified()) {
				return;
			}
			String oldContent = ((BreakdownElementDescription) element
					.getPresentation()).getUsageGuidance();
			if (((MethodElementEditor) getEditor()).mustRestoreValue(
					usageGuidance, oldContent)) {
				return;
			}
			String newContent = control.getText();
			if (!newContent.equals(oldContent)) {
				actionMgr.doAction(IActionManager.SET, element
						.getPresentation(), UmaPackage.eINSTANCE
						.getBreakdownElementDescription_UsageGuidance(),
						newContent, -1);
			}
		}
	};

	private Listener keyConsiderationsListener = new Listener() {
		public void handleEvent(Event e) {
			element = (BreakdownElement) getElement();
			IMethodRichText control = expandFlag ? ctrl_document_expanded
					: keyConsiderations;
			if (!control.getModified()) {
				return;
			}
			String oldContent = ((ContentDescription) element.getPresentation())
					.getKeyConsiderations();
			if (((MethodElementEditor) getEditor()).mustRestoreValue(
					keyConsiderations, oldContent)) {
				return;
			}
			String newContent = control.getText();
			if (!newContent.equals(oldContent)) {
				actionMgr.doAction(IActionManager.SET, element
						.getPresentation(), UmaPackage.eINSTANCE
						.getContentDescription_KeyConsiderations(), newContent,
						-1);
			}
		}
	};

	/**
	 * Initialize
	 */
	protected void init() {
		// get BreakdownElement object
		element = (BreakdownElement) getElement();

		// get toolkit
		toolkit = getWidgetFactory();

		// get action manager
		actionMgr = EPFPropertySheetPage.getActionManager();

		// set content element path
		contentElementPath = ResourceHelper.getFolderAbsolutePath(element); 
	}


	/**
	 * @see org.eclipse.epf.authoring.ui.properties.AbstractSection#createControls(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		this.parent = parent;
		super.createControls(parent, tabbedPropertySheetPage);
		init();
		parent.setLayout(new GridLayout());
		parent.setLayoutData(new GridData(GridData.FILL_BOTH));
		createControls_(parent, tabbedPropertySheetPage);
	}
	
	protected void createControls_(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		// create Docment section
		createDocumentSection(parent);

		// add listeners
		addListeners();

		// update controls
		updateControls();
	}

	/**
	 * Add listeners
	 */
	protected void addListeners() {

		parent.addControlListener(new ControlListener() {
			public void controlResized(ControlEvent e) {
				if (ctrl_document_expanded != null) {
					((GridData) ctrl_document_expanded.getLayoutData()).heightHint = getRichTextEditorHeight();
					((GridData) ctrl_document_expanded.getLayoutData()).widthHint = getRichTextEditorWidth();
				}
				parent.layout(true, true);
			}

			public void controlMoved(ControlEvent e) {
			}
		});

		
		prefixText.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				((MethodElementEditor) getEditor()).setCurrentFeatureEditor(e.widget,
						UmaPackage.eINSTANCE.getBreakdownElement_Prefix());
			}

			public void focusLost(FocusEvent e) {
				element = (BreakdownElement) getElement();
				String oldContent = element.getPrefix();
				if (((MethodElementEditor) getEditor()).mustRestoreValue(
						prefixText, oldContent)) {
					return;
				}
				String newContent = StrUtil.getPlainText(prefixText.getText());
				if (!newContent.equals(oldContent)) {
					boolean status = actionMgr.doAction(IActionManager.SET,
							element, UmaPackage.eINSTANCE
									.getBreakdownElement_Prefix(), newContent,
							-1);
					if (status) {
						prefixText.setText(newContent);
					}
				}
			}
		});

		briefDescText.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				((MethodElementEditor) getEditor()).setCurrentFeatureEditor(e.widget,
						UmaPackage.eINSTANCE.getMethodElement_BriefDescription());
			}

			public void focusLost(FocusEvent e) {
				element = (BreakdownElement) getElement();
				String oldContent = element.getBriefDescription();
				if (((MethodElementEditor) getEditor()).mustRestoreValue(
						briefDescText, oldContent)) {
					return;
				}
				String newContent = StrUtil.getPlainText(briefDescText
						.getText());
				if (!newContent.equals(oldContent)) {
					boolean status = actionMgr.doAction(IActionManager.SET,
							element, UmaPackage.eINSTANCE
									.getMethodElement_BriefDescription(),
							newContent, -1);
					if (status) {
						briefDescText.setText(newContent);
					}
				}
			}
		});
		
		
		externalIDText.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				((MethodElementEditor) getEditor()).setCurrentFeatureEditor(e.widget,
						UmaPackage.eINSTANCE.getContentDescription_ExternalId());
			}

			public void focusLost(FocusEvent e) {
				element = (BreakdownElement) getElement();				
				
				String oldContent = element.getPresentation().getExternalId();
				if (((MethodElementEditor) getEditor()).mustRestoreValue(
						externalIDText, oldContent)) {
					return;
				}
				String newContent = StrUtil.getPlainText(externalIDText
						.getText());
				if (!newContent.equals(oldContent)) {
					boolean status = actionMgr.doAction(IActionManager.SET,
							element.getPresentation(), UmaPackage.eINSTANCE
									.getContentDescription_ExternalId(),
							newContent, -1);
					if (status) {
						externalIDText.setText(newContent);
					}
				}
			}
		});

		BreakdownElement element = getElement();
		
		usageGuidance.setModalObject(element);
		usageGuidance.setModalObjectFeature(UmaPackage.eINSTANCE.getBreakdownElementDescription_UsageGuidance());
		usageGuidance.addListener(SWT.Deactivate, usageGuidanceListener);
		
		keyConsiderations.setModalObject(element);
		keyConsiderations.setModalObjectFeature(UmaPackage.eINSTANCE.getContentDescription_KeyConsiderations());
		keyConsiderations
				.addListener(SWT.Deactivate, keyConsiderationsListener);
	}

	/**
	 * Add documentation section on the given composite
	 * @param composite
	 */
	protected void createDocumentSection(Composite composite) {
		int horizontalSpan = 2;

		// create document section
		documentSection = FormUI
				.createSection(
						toolkit,
						composite,
						PropertiesResources.Process_DocumentInformationTitle, 
						PropertiesResources.bind(PropertiesResources.Process_documentInformationDescription, PropertiesUtil.getTypeLower(element))); 

		// create document composite
		documentComposite = FormUI.createComposite(toolkit, documentSection, 3,
				false);

		// prefix
		prefixLabel = FormUI.createLabel(toolkit, documentComposite, PropertiesResources.Process_prefix, horizontalSpan); 
		prefixText = FormUI.createText(toolkit, documentComposite, heightHint);

		// brief description
		briefDescLabel = FormUI.createLabel(toolkit, documentComposite, PropertiesResources.Process_briefDescription, horizontalSpan); 
		briefDescText = FormUI.createText(toolkit, documentComposite,
				heightHint);
		
		// external ID
		externalIDLabel = FormUI.createLabel(toolkit, documentComposite, AuthoringUIResources.Process_ExternalID, horizontalSpan); 
		externalIDText = FormUI.createText(toolkit, documentComposite);

		// create usage guidance
		usageGuidanceContainer = FormUI.createRichTextWithLink(toolkit,
				documentComposite, heightHint, contentElementPath, element,
				PropertiesResources.Process_usageGuidance);
		addHyperLinkListener(usageGuidanceContainer.link);
		usageGuidance = usageGuidanceContainer.richText;

		// create key considerations
		keyConsiderationsContainer = FormUI.createRichTextWithLink(toolkit,
				documentComposite, heightHint, contentElementPath, element,
				PropertiesResources.BreakdownElement_keyConsiderations);
		addHyperLinkListener(keyConsiderationsContainer.link);
		keyConsiderationsLink = keyConsiderationsContainer.link;
		keyConsiderations = keyConsiderationsContainer.richText;

		// create expanded composite
		expandDocumentComposite = FormUI.createComposite(toolkit,
				documentSection, 2, true);

		// Hyperlink desc
		expandDocumentLink = FormUI.createImageHyperLink(toolkit,
				expandDocumentComposite, AuthoringUIImages.IMG_EXPANDED,
				AuthoringUIResources.closeRTE); 
		addHyperLinkListener(expandDocumentLink);

		// expand label
		expandDocumentLabel = BaseFormPage.createDecoratedLabel(toolkit, expandDocumentComposite, ""); //$NON-NLS-1$
	}

	protected void addHyperLinkListener(ImageHyperlink link) {
		link.addHyperlinkListener(new HyperlinkAdapter() {
			public void linkActivated(HyperlinkEvent e) {
				toggle(e);
			}
		});
	}

	protected IMethodRichTextEditor toggle(HyperlinkEvent e) {
		IMethodRichText ctrl = (IMethodRichText) e.getHref();
		if (ctrl_document_expanded == null) {
			ctrl_document_expanded = MethodFormToolkit.createRichTextEditor(
					toolkit, expandDocumentComposite,
					"", SWT.MULTI | SWT.WRAP, contentElementPath, element, expandDocumentLabel, getEditor().getEditorSite()); //$NON-NLS-1$
			{
				GridData gridData = new GridData(GridData.FILL_BOTH);
				gridData.heightHint = getRichTextEditorHeight();
				gridData.widthHint = getRichTextEditorWidth();
				gridData.horizontalSpan = 2;
				ctrl_document_expanded.setLayoutData(gridData);
				// doesn't seem to work - RTE is not proper width until window is resized
//				((GridData)expandDocumentComposite.getLayoutData()).widthHint = getRichTextEditorWidth();
//				((GridData)parent.getLayoutData()).widthHint = getRichTextEditorWidth();
//				parent.layout(true, true);
			}

			ctrl_document_expanded.addModifyListener(contentModifyListener);
		}

		if (expandFlag) {
			ctrl_document_expanded.collapse();
			documentComposite.setVisible(true);
			documentSection.setClient(documentComposite);

			expandDocumentComposite.setVisible(false);

			ctrl = getActiveRichTextControl();
			ctrl.setText(ctrl_document_expanded.getText());

			for (Iterator i = ctrl.getListeners(); i.hasNext();) {
				RichTextListener listener = (RichTextListener) i.next();
				ctrl_document_expanded.removeListener(listener.getEventType(),
						listener.getListener());
			}

			if (ctrl_document_expanded.getModified()) {
				((MethodElementEditor) getEditor())
						.saveModifiedRichText(ctrl_document_expanded);
			}

			ctrl.setFocus();
		} else {
			// expanded
			documentComposite.setVisible(false);
			documentSection.setClient(expandDocumentComposite);

			expandDocumentComposite.setVisible(true);

			expandDocumentLabel.setText((String) ((ImageHyperlink) e
					.getSource()).getData("Title")); //$NON-NLS-1$
			setActiveRichTextControl(ctrl);
			ctrl_document_expanded.setInitialText(ctrl.getText());
			ctrl_document_expanded.setModalObject(ctrl.getModalObject());
			ctrl_document_expanded.setModalObjectFeature(ctrl
					.getModalObjectFeature());
			ctrl_document_expanded.setFindReplaceAction(ctrl.getFindReplaceAction());

			for (Iterator i = ctrl.getListeners(); i.hasNext();) {
				RichTextListener listener = (RichTextListener) i.next();
				ctrl_document_expanded.addListener(listener.getEventType(),
						listener.getListener());
			}

		}

		documentSection.layout(true);

		// set editable
		if (ctrl_document_expanded != null)
			ctrl_document_expanded.setEditable(editable);

		expandFlag = !expandFlag;
		return ctrl_document_expanded;
	}


	/**
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
	 */
	public void refresh() {
		try {
			if (getElement() instanceof BreakdownElement) {
				((MethodElementEditor) getEditor()).saveModifiedRichText();

				element = (BreakdownElement) getElement();
				BreakdownElementDescription content = (BreakdownElementDescription) element
						.getPresentation();

				// update section name
				documentSection
						.setDescription(PropertiesResources.bind(PropertiesResources.Process_documentInformationDescription, PropertiesUtil.getTypeLower(element))); 

				if (modelModifyListener != null) {
					prefixText.removeModifyListener(modelModifyListener);
					briefDescText.removeModifyListener(modelModifyListener);
					
				}

				if (contentModifyListener != null) {
					externalIDText.removeModifyListener(contentModifyListener);
					usageGuidance.removeModifyListener(contentModifyListener);
					keyConsiderations
							.removeModifyListener(contentModifyListener);
				}

				modelModifyListener = getEditor().createModifyListener(element);
				contentModifyListener = getEditor().createModifyListener(
						element.getPresentation());

				if (modelModifyListener != null
						&& modelModifyListener instanceof MethodElementEditor.ModifyListener) {
					((MethodElementEditor.ModifyListener) modelModifyListener)
							.setElement(element);
					((MethodElementEditor.ModifyListener) modelModifyListener)
							.setDisable(true);
				}

				if (contentModifyListener != null
						&& contentModifyListener instanceof MethodElementEditor.ModifyListener) {
					((MethodElementEditor.ModifyListener) contentModifyListener)
							.setElement(element.getPresentation());
					((MethodElementEditor.ModifyListener) contentModifyListener)
							.setDisable(true);
				}

				prefixText.setText(element.getPrefix());
				briefDescText.setText(element.getBriefDescription());
				externalIDText.setText(content.getExternalId());

				usageGuidance.setText(content.getUsageGuidance());
				keyConsiderations.setText(content.getKeyConsiderations());

				if (expandFlag) {
					if (expandDocumentLabel.getText().equals(
							PropertiesResources.Process_usageGuidance)) 
					{
						ctrl_document_expanded.setText(content
								.getUsageGuidance());
						ctrl_document_expanded.setSelection(0);
						ctrl_document_expanded.setModalObject(content);
						ctrl_document_expanded
								.setModalObjectFeature(UmaPackage.eINSTANCE
										.getBreakdownElementDescription_UsageGuidance());
					} else if (expandDocumentLabel.getText()
							.equals(
									PropertiesResources.BreakdownElement_keyConsiderations)) 
					{
						ctrl_document_expanded.setText(content
								.getKeyConsiderations());
						ctrl_document_expanded.setModalObject(content);
						ctrl_document_expanded
								.setModalObjectFeature(UmaPackage.eINSTANCE
										.getContentDescription_KeyConsiderations());
					}
				}

				updateControls();

				if (modelModifyListener instanceof MethodElementEditor.ModifyListener) {
					((MethodElementEditor.ModifyListener) modelModifyListener)
							.setDisable(false);
				}
				if (contentModifyListener instanceof MethodElementEditor.ModifyListener) {
					((MethodElementEditor.ModifyListener) contentModifyListener)
							.setDisable(false);
				}

				prefixText.addModifyListener(modelModifyListener);
				briefDescText.addModifyListener(modelModifyListener);
				
				externalIDText.addModifyListener(contentModifyListener);
				usageGuidance.setModalObject(content);
				usageGuidance.setModalObjectFeature(UmaPackage.eINSTANCE
						.getBreakdownElementDescription_UsageGuidance());
				usageGuidance.addModifyListener(contentModifyListener);

				keyConsiderations.setModalObject(content);
				keyConsiderations.setModalObjectFeature(UmaPackage.eINSTANCE
						.getContentDescription_KeyConsiderations());
				keyConsiderations.addModifyListener(contentModifyListener);
			}
		} catch (Exception ex) {
			logger
					.logError(
							"Error refreshing breakdown element documentation section", ex); //$NON-NLS-1$
		}
	}

	protected void updateControls() {
		prefixText.setEditable(editable);
		briefDescText.setEditable(editable);
		externalIDText.setEditable(editable);
		usageGuidance.setEditable(editable);
		keyConsiderations.setEditable(editable);
	}

	/**
	 * Sets the active "unexpanded" rich text control.
	 */
	private void setActiveRichTextControl(IMethodRichText ctrl) {
		activeControl = ctrl;
	}

	/**
	 * Returns the active "unexpanded" rich text control.
	 */
	private IMethodRichText getActiveRichTextControl() {
		return activeControl;
	}
	
	/**
	 * Get height of the rich text control
	 * @return
	 * 		Height of the rich text control
	 */
	public int getRichTextEditorHeight() {
		return parent.getBounds().height - 3 * 32;
	}

	/**
	 * Get width of the rich text control
	 * @return
	 * 		Width of the rich text control
	 */
	public int getRichTextEditorWidth() {
		return parent.getBounds().width - 2 * 32;
	}

}