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
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.DescriptorDescription;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.forms.widgets.ImageHyperlink;


/**
 * The document tab section for Descriptor
 * 
 * @author Shilpa Toraskar
 * @author Kelvin Low
 * @since 1.0
 */
public class DescriptorDocumentSection extends BreakdownElementDocumentSection {

	protected Descriptor element;

	private ImageHyperlink refinedLink;
	
	private IMethodRichText refinedDesc;
	
	private DescriptorPropUtil propUtil = DescriptorPropUtil.getDesciptorPropUtil();

	private Listener listener = new Listener() {
		public void handleEvent(Event e) {
			element = (Descriptor) getElement();
			IMethodRichText control = expandFlag ? ctrl_document_expanded
					: refinedDesc;
			if (!control.getModified()) {
				return;
			}
			String oldContent = ((DescriptorDescription) element
					.getPresentation()).getRefinedDescription();
			if (((MethodElementEditor) getEditor()).mustRestoreValue(
					refinedDesc, oldContent)) {
				return;
			}
			String newContent = control.getText();
			if (!newContent.equals(oldContent)) {
				actionMgr.doAction(IActionManager.SET, element
						.getPresentation(), UmaPackage.eINSTANCE
						.getDescriptorDescription_RefinedDescription(),
						newContent, -1);
			}
		}
	};

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.BreakdownElementDocumentSection#init()
	 */
	protected void init() {
		super.init();
		element = (Descriptor) getElement();
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.BreakdownElementDocumentSection#createDocumentSection(org.eclipse.swt.widgets.Composite)
	 */
	protected void createDocumentSection(Composite composite) {
		super.createDocumentSection(composite);
		int heightHint = 200;

		// refined description
		RichTextImageLinkContainer refinedContainer = FormUI.createRichTextWithLink(toolkit,
				documentComposite, heightHint, contentElementPath, element,
				PropertiesResources.Descriptor_RefinedDescription);
		addHyperLinkListener(refinedContainer.link);
		refinedLink = refinedContainer.link;
		refinedDesc = refinedContainer.richText;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.BreakdownElementDocumentSection#addListeners()
	 */
	protected void addListeners() {
		super.addListeners();
		refinedDesc.setModalObject(getElement());
		refinedDesc.setModalObjectFeature(UmaPackage.eINSTANCE
						.getDescriptorDescription_RefinedDescription());
		refinedDesc.addListener(SWT.Deactivate, listener);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.BreakdownElementDocumentSection#updateControls()
	 */
	protected void updateControls() {
		super.updateControls();
		refinedDesc.setEditable(editable);
		if (isSyncFree()) {
			syncFreeUpdateControls();
		}
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.properties.BreakdownElementDocumentSection#refresh()
	 */
	public void refresh() {
		try {
			if (getElement() instanceof Descriptor) {
				((MethodElementEditor) getEditor()).saveModifiedRichText();

				element = (Descriptor) getElement();
				DescriptorDescription content = (DescriptorDescription) element
						.getPresentation();

				if (contentModifyListener != null) {
					refinedDesc.removeModifyListener(contentModifyListener);		
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
				
				
				refinedDesc.setText(content.getRefinedDescription());

				if (expandFlag) {
					if (expandDocumentLabel.getText()
							.equals(
									PropertiesResources.Descriptor_RefinedDescription)) { 
						ctrl_document_expanded.setText(content
								.getRefinedDescription());
						ctrl_document_expanded.setSelection(0);
						ctrl_document_expanded.setModalObject(content);
						ctrl_document_expanded
								.setModalObjectFeature(UmaPackage.eINSTANCE
										.getDescriptorDescription_RefinedDescription());
					}
				}

				if (contentModifyListener instanceof MethodElementEditor.ModifyListener) {
					((MethodElementEditor.ModifyListener) contentModifyListener)
							.setDisable(false);
				}

				refinedDesc.setModalObject(content);
				refinedDesc.setModalObjectFeature(UmaPackage.eINSTANCE
						.getDescriptorDescription_RefinedDescription());
				refinedDesc.addModifyListener(contentModifyListener);
			}
		} catch (Exception ex) {
			logger
					.logError(
							"Error while refreshing descriptor documentation section :", ex); //$NON-NLS-1$
		}
	}
	
	protected boolean isSyncFree() {
		return propUtil.isDescriptor(element) && ProcessUtil.isSynFree();
	}
	
	private void syncFreeUpdateControls() {
		//main description
		refinedLink.setEnabled(false);
		refinedDesc.setEditable(false);
		
		//key considerations
		keyConsiderationsLink.setEnabled(false);
		keyConsiderations.setEditable(false);
		
		//brief description
		briefDescText.setEditable(false);
	}

}
