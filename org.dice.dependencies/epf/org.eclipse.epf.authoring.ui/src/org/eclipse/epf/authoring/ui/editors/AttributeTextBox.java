/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.authoring.ui.editors;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author Phong Nguyen Le
 * @since 1.5.0.1
 *
 */
public class AttributeTextBox implements ModifyListener {
	public static final AttributeTextBox createAttributeTextBox(Text textCtrl, EObject object,
			EAttribute attr, boolean checkEditOnContainer,
			IActionManager actionMgr) {
//		IAttributeTextBoxFactory factory = AttributeTextBox.getAttributeTextBoxFactory();
//		if(factory != null) {
//			AttributeRichTextBox box = factory.createAttributeRichTextBox(richText, object, attr, checkEditOnContainer, actionMgr);
//			if(box != null) {
//				return box;
//			}
//		}
		return new AttributeTextBox(textCtrl, object, attr, checkEditOnContainer, actionMgr);
	}
	
	protected EObject object;
	protected EAttribute attribute;
	private boolean checkEditOnContainer;
	private IActionManager actionMgr;
	protected Text textCtrl;
	
	protected AttributeTextBox(Text text, EObject object,
			EAttribute attr, boolean checkEditOnContainer,
			IActionManager actionMgr) {
		assert attr != null
				&& attr.getEAttributeType().getInstanceClass()
						.isAssignableFrom(String.class);
		this.object = object;
		this.attribute = attr;
		this.checkEditOnContainer = checkEditOnContainer;
		this.actionMgr = actionMgr;
		this.textCtrl = text;
		text.addModifyListener(this);
	}
		
	public void setElement(EObject object) {
		if (object != this.object) {
			this.object = object;
			setAttribute();
		}
	}
	
	protected void setAttribute() {
		setText(getAttribute());
	}
	
	protected void setText(String text) {
		textCtrl.removeModifyListener(this);
		try {
			textCtrl.setText(text);
		}
		finally {
			textCtrl.addModifyListener(this);
		}
	}
	
	protected String getAttribute() {
		Object val = object != null ? object.eGet(attribute) : null;
		return val == null ? "" : val.toString(); //$NON-NLS-1$
	}
	
	public Text getTextControl() {
		return textCtrl;
	}

	public void modifyText(ModifyEvent e) {
		if(object != null) {
			Shell shell = textCtrl.getShell();
			IStatus status = TngUtil.checkEdit(object, shell);
			if(status.isOK()) {
				if(checkEditOnContainer && object.eContainer() != null) {
					status = TngUtil.checkEdit(object.eContainer(), shell);
				}
			}
			if(status.isOK()) {
				actionMgr.doAction(IActionManager.SET, object, attribute, textCtrl.getText(), -1); 
			}
			else {
				// restore the text in the text widget
				//
				setAttribute();
				textCtrl.setEditable(true);
			}
		}
	}
	
}
