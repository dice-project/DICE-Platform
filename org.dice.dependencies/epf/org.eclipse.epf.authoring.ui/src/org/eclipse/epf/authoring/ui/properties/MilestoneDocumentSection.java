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
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;


/**
 * The document tab section for Milestone
 * 
 * @author Shilpa Toraskar
 * @author Kelvin Low
 * @since 1.0
 */
public class MilestoneDocumentSection extends BreakdownElementDocumentSection {

	protected BreakdownElement element;

	private IMethodRichText mainDesc;

	private Listener listener = new Listener() {
		public void handleEvent(Event e) {
			element = (BreakdownElement) getElement();
			IMethodRichText control = expandFlag ? ctrl_document_expanded
					: mainDesc;
			if (!control.getModified()) {
				return;
			}
			String oldContent = ((ContentDescription) element.getPresentation())
					.getMainDescription();
			if (((MethodElementEditor) getEditor()).mustRestoreValue(mainDesc,
					oldContent)) {
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

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.BreakdownElementDocumentSection#init()
	 */
	protected void init() {
		super.init();
		element = (BreakdownElement) getElement();

	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.BreakdownElementDocumentSection#createDocumentSection(org.eclipse.swt.widgets.Composite)
	 */
	protected void createDocumentSection(Composite composite) {
		super.createDocumentSection(composite);
		int heightHint = 200;

		// main description
		RichTextImageLinkContainer mainDescContainer = FormUI.createRichTextWithLink(toolkit,
				documentComposite, heightHint, contentElementPath, element,
				PropertiesResources.Process_mainDescription);
		addHyperLinkListener(mainDescContainer.link);
		mainDesc = mainDescContainer.richText;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.BreakdownElementDocumentSection#addListeners()
	 */
	protected void addListeners() {
		super.addListeners();
		mainDesc.setModalObject(getElement());
		mainDesc.setModalObjectFeature(UmaPackage.eINSTANCE
						.getContentDescription_MainDescription());
		mainDesc.addListener(SWT.Deactivate, listener);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.BreakdownElementDocumentSection#updateControls()
	 */
	protected void updateControls() {
		super.updateControls();
		mainDesc.setEditable(editable);
	}


	/**
	 * @see org.eclipse.epf.authoring.ui.properties.BreakdownElementDocumentSection#refresh()
	 */
	public void refresh() {
		try {
			super.refresh();

			if (getElement() instanceof BreakdownElement) {
				((MethodElementEditor) getEditor()).saveModifiedRichText();

				element = (BreakdownElement) getElement();
				ContentDescription content = (ContentDescription) element
						.getPresentation();

				if (contentModifyListener != null) {
					mainDesc.removeModifyListener(contentModifyListener);		
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
				
				mainDesc.setText(content.getMainDescription());

				if (expandFlag) {
					if (expandDocumentLabel.getText().equals(
							PropertiesResources.Process_mainDescription)) 
					{
						ctrl_document_expanded.setText(content
								.getMainDescription());
						ctrl_document_expanded.setSelection(0);
						ctrl_document_expanded.setModalObject(content);
						ctrl_document_expanded
								.setModalObjectFeature(UmaPackage.eINSTANCE
										.getContentDescription_MainDescription());
					}
				}

				if (contentModifyListener instanceof MethodElementEditor.ModifyListener) {
					((MethodElementEditor.ModifyListener) contentModifyListener)
							.setDisable(false);
				}

				mainDesc.setModalObject(content);
				mainDesc.setModalObjectFeature(UmaPackage.eINSTANCE
						.getContentDescription_MainDescription());
				mainDesc.addModifyListener(contentModifyListener);
			}
		} catch (Exception ex) {
			logger
					.logError(
							"Error while refreshing milestone documentation section :", ex); //$NON-NLS-1$
		}
	}

}