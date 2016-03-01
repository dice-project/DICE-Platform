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

import org.eclipse.epf.authoring.ui.editors.MethodElementEditor;
import org.eclipse.epf.authoring.ui.richtext.IMethodRichText;
import org.eclipse.epf.authoring.ui.util.RichTextImageLinkContainer;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.ActivityDescription;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyComposite;


/**
 * The document tab for Activity
 * 
 * @author Shilpa Toraskar
 * @author Kelvin Low
 * @since 1.0
 */
public class ActivityDocumentSection extends BreakdownElementDocumentSection {

	protected Activity element;

	protected IMethodRichText mainDescText;
	
	protected RichTextImageLinkContainer mainDescContainer;

	protected IMethodRichText purposeText;
	
	protected RichTextImageLinkContainer purposeContainer;

	protected IMethodRichText alternativesText;
	
	protected RichTextImageLinkContainer alternativesContainer;

	protected IMethodRichText howToStaffText;
	
	protected RichTextImageLinkContainer howToStaffContainer;

	private Listener purposeListener = new Listener() {
		public void handleEvent(Event e) {
			element = (Activity) getElement();
			IMethodRichText control = expandFlag ? ctrl_document_expanded
					: purposeText;
			if (!control.getModified()) {
				return;
			}
			String oldContent = ((ActivityDescription) element
					.getPresentation()).getPurpose();
			if (((MethodElementEditor) getEditor()).mustRestoreValue(
					purposeText, oldContent)) {
				return;
			}
			String newContent = control.getText();
			if (!newContent.equals(oldContent)) {
				actionMgr.doAction(IActionManager.SET, element
						.getPresentation(), UmaPackage.eINSTANCE
						.getActivityDescription_Purpose(), newContent, -1);
			}
		}
	};

	private Listener mainDescListener = new Listener() {
		public void handleEvent(Event e) {
			element = (Activity) getElement();
			IMethodRichText control = expandFlag ? ctrl_document_expanded
					: mainDescText;
			if (!control.getModified()) {
				return;
			}
			String oldContent = ((ActivityDescription) element
					.getPresentation()).getMainDescription();
			if (((MethodElementEditor) getEditor()).mustRestoreValue(
					mainDescText, oldContent)) {
				return;
			}
			String newContent = control.getText();
			if (!newContent.equals(oldContent)) {
				actionMgr.doAction(IActionManager.SET, element
						.getPresentation(), UmaPackage.eINSTANCE
						.getContentDescription_MainDescription(), newContent,
						-1);
			}
		}
	};

	private Listener alternativesListener = new Listener() {
		public void handleEvent(Event e) {
			element = (Activity) getElement();
			IMethodRichText control = expandFlag ? ctrl_document_expanded
					: alternativesText;
			if (!control.getModified()) {
				return;
			}
			String oldContent = ((ActivityDescription) element
					.getPresentation()).getAlternatives();
			if (((MethodElementEditor) getEditor()).mustRestoreValue(
					alternativesText, oldContent)) {
				return;
			}
			String newContent = control.getText();
			if (!newContent.equals(oldContent)) {
				actionMgr.doAction(IActionManager.SET, element
						.getPresentation(), UmaPackage.eINSTANCE
						.getActivityDescription_Alternatives(), newContent, -1);
			}
		}
	};

	private Listener howToStaffListener = new Listener() {
		public void handleEvent(Event e) {
			element = (Activity) getElement();
			IMethodRichText control = expandFlag ? ctrl_document_expanded
					: howToStaffText;
			if (!control.getModified()) {
				return;
			}
			String oldContent = ((ActivityDescription) element
					.getPresentation()).getHowtoStaff();
			if (((MethodElementEditor) getEditor()).mustRestoreValue(
					howToStaffText, oldContent)) {
				return;
			}
			String newContent = control.getText();
			if (!newContent.equals(oldContent)) {
				actionMgr.doAction(IActionManager.SET, element
						.getPresentation(), UmaPackage.eINSTANCE
						.getActivityDescription_HowtoStaff(), newContent, -1);
			}
		}
	};

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.BreakdownElementDocumentSection#init()
	 */
	protected void init() {
		super.init();
		element = (Activity) getElement();
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.BreakdownElementDocumentSection#createDocumentSection(org.eclipse.swt.widgets.Composite)
	 */
	protected void createDocumentSection(Composite composite) {
		int mainDescheightHint = 100;

		super.createDocumentSection(composite);

		// main description
		mainDescContainer = FormUI.createRichTextWithLink(toolkit,
				documentComposite, mainDescheightHint, contentElementPath, element,
				PropertiesResources.Process_mainDescription);
		addHyperLinkListener(mainDescContainer.link);
		mainDescText = mainDescContainer.richText;

		// purpose
		purposeContainer = FormUI.createRichTextWithLink(toolkit,
				documentComposite, heightHint, contentElementPath, element,
				PropertiesResources.Process_purpose);
		addHyperLinkListener(purposeContainer.link);
		purposeText = purposeContainer.richText;

		// create alternatives
		alternativesContainer = FormUI.createRichTextWithLink(toolkit,
				documentComposite, heightHint, contentElementPath, element,
				PropertiesResources.Activity_alternatives);
		addHyperLinkListener(alternativesContainer.link);
		alternativesText = alternativesContainer.richText;
		
		// create howtoStaff
		howToStaffContainer = FormUI.createRichTextWithLink(toolkit,
				documentComposite, heightHint, contentElementPath, element,
				PropertiesResources.Activity_howToStaff);
		addHyperLinkListener(howToStaffContainer.link);
		howToStaffText = howToStaffContainer.richText;
		
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.BreakdownElementDocumentSection#addListeners()
	 */
	protected void addListeners() {
		super.addListeners();
		
		BreakdownElement element = getElement();
		
		purposeText.setModalObject(element);
		purposeText.setModalObjectFeature(UmaPackage.eINSTANCE
						.getActivityDescription_Purpose());
		purposeText.addListener(SWT.Deactivate, purposeListener);
		
		mainDescText.setModalObject(element);
		mainDescText.setModalObjectFeature(UmaPackage.eINSTANCE.getContentDescription_MainDescription());
		mainDescText.addListener(SWT.Deactivate, mainDescListener);
				
		alternativesText.setModalObject(element);
		alternativesText.setModalObjectFeature(UmaPackage.eINSTANCE.getActivityDescription_Alternatives());
		alternativesText.addListener(SWT.Deactivate, alternativesListener);
		
		howToStaffText.setModalObject(element);
		howToStaffText.setModalObjectFeature(UmaPackage.eINSTANCE.getActivityDescription_HowtoStaff());
		howToStaffText.addListener(SWT.Deactivate, howToStaffListener);
	}
		
	/**
	 * @see org.eclipse.epf.authoring.ui.properties.BreakdownElementDocumentSection#updateControls()
	 */
	protected void updateControls() {
		super.updateControls();
		purposeText.setEditable(editable);
		mainDescText.setEditable(editable);
		alternativesText.setEditable(editable);
		howToStaffText.setEditable(editable);
		// hide fields for top-level processes:
		if (ProcessUtil.isTopProcess(element)) {
			briefDescLabel.setVisible(false);
			briefDescText.setVisible(false);
			externalIDLabel.setVisible(false);
			externalIDText.setVisible(false);
			keyConsiderationsContainer.setVisible(false);
			mainDescContainer.setVisible(false);
			purposeContainer.setVisible(false);
			alternativesContainer.setVisible(false);
			howToStaffContainer.setVisible(false);
			
			// move usage guidance to be right after prefix field
			usageGuidanceContainer.moveBelow(prefixText);
			
		} else {
			briefDescLabel.setVisible(true);
			briefDescText.setVisible(true);
			externalIDLabel.setVisible(true);
			externalIDText.setVisible(true);
			keyConsiderationsContainer.setVisible(true);
			mainDescContainer.setVisible(true);
			purposeContainer.setVisible(true);
			alternativesContainer.setVisible(true);
			howToStaffContainer.setVisible(true);
			// move usage guidance to be right after externalID field
			usageGuidanceContainer.moveBelow(externalIDText);
		}
		parent.layout(true, true);
		// make parent be totally repainted
		parent.setVisible(false);
		parent.setVisible(true);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.BreakdownElementDocumentSection#refresh()
	 */
	public void refresh() {
		try {
			if (getElement() instanceof Activity) {
				((MethodElementEditor) getEditor()).saveModifiedRichText();

				element = (Activity) getElement();
				ActivityDescription content = (ActivityDescription) element
						.getPresentation();

				if (contentModifyListener != null) {
					mainDescText.removeModifyListener(contentModifyListener);
					purposeText.removeModifyListener(contentModifyListener);
					alternativesText
							.removeModifyListener(contentModifyListener);
					howToStaffText.removeModifyListener(contentModifyListener);
				}
				
				super.refresh();

				if (contentModifyListener != null) {
					if (contentModifyListener instanceof MethodElementEditor.ModifyListener) {
						((MethodElementEditor.ModifyListener) contentModifyListener)
								.setElement(element.getPresentation());
						((MethodElementEditor.ModifyListener) contentModifyListener)
								.setDisable(true);
					}
				}

				mainDescText.setText(content.getMainDescription());
				purposeText.setText(content.getPurpose());
				alternativesText.setText(content.getAlternatives());
				howToStaffText.setText(content.getHowtoStaff());

				if (expandFlag) {
					if (expandDocumentLabel.getText().equals(
							PropertiesResources.Process_mainDescription)) { 
						ctrl_document_expanded.setText(content
								.getMainDescription());
						ctrl_document_expanded.setSelection(0);
						ctrl_document_expanded.setModalObject(content);
						ctrl_document_expanded
								.setModalObjectFeature(UmaPackage.eINSTANCE
										.getContentDescription_MainDescription());

					} else if (expandDocumentLabel.getText().equals(
							PropertiesResources.Process_purpose)) { 
						ctrl_document_expanded.setText(content.getPurpose());
						ctrl_document_expanded.setModalObject(content);
						ctrl_document_expanded
								.setModalObjectFeature(UmaPackage.eINSTANCE
										.getActivityDescription_Purpose());
					} else if (expandDocumentLabel.getText().equals(
							PropertiesResources.Activity_alternatives)) { 
						ctrl_document_expanded.setText(content
								.getAlternatives());
						ctrl_document_expanded.setModalObject(content);
						ctrl_document_expanded
								.setModalObjectFeature(UmaPackage.eINSTANCE
										.getActivityDescription_Alternatives());
					} else if (expandDocumentLabel.getText().equals(
							PropertiesResources.Activity_howToStaff)) { 
						ctrl_document_expanded.setText(content.getHowtoStaff());
						ctrl_document_expanded.setModalObject(content);
						ctrl_document_expanded
								.setModalObjectFeature(UmaPackage.eINSTANCE
										.getActivityDescription_HowtoStaff());
					}
				}

				if (contentModifyListener instanceof MethodElementEditor.ModifyListener) {
					((MethodElementEditor.ModifyListener) contentModifyListener)
							.setDisable(false);
				}

				mainDescText.setModalObject(content);
				mainDescText.setModalObjectFeature(UmaPackage.eINSTANCE
						.getContentDescription_MainDescription());
				mainDescText.addModifyListener(contentModifyListener);

				purposeText.setModalObject(content);
				purposeText.setModalObjectFeature(UmaPackage.eINSTANCE
						.getActivityDescription_Purpose());
				purposeText.addModifyListener(contentModifyListener);

				alternativesText.setModalObject(content);
				alternativesText.setModalObjectFeature(UmaPackage.eINSTANCE
						.getActivityDescription_Alternatives());
				alternativesText.addModifyListener(contentModifyListener);

				howToStaffText.setModalObject(content);
				howToStaffText.setModalObjectFeature(UmaPackage.eINSTANCE
						.getActivityDescription_HowtoStaff());
				howToStaffText.addModifyListener(contentModifyListener);
			}
		} catch (Exception ex) {
			logger.logError(
					"Error refreshing activity documentation section :", ex); //$NON-NLS-1$
		}
	}

}