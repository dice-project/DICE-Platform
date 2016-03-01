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
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Phong Nguyen Le
 * @since 7.5
 *
 */
public class AttributeStyledTextBox implements ModifyListener {
//	public static final IAttributeTextBoxFactory getAttributeTextBoxFactory() {
//		return (IAttributeTextBoxFactory) ExtensionManager.getExtension(AuthoringUIPlugin
//				.getDefault().getId(), "attributeTextBoxFactory"); //$NON-NLS-1$
//	}
	
	public static final AttributeStyledTextBox createAttributeTextBox(
			Composite parent, int styles, EObject object, EAttribute attr,
			boolean checkEditOnContainer, IActionManager actionMgr) {
//		IAttributeTextBoxFactory factory = getAttributeTextBoxFactory();
//		if(factory != null) {
//			AttributeTextBox box = factory.createAttributeTextBox(parent, styles, object, attr, checkEditOnContainer, actionMgr);
//			if(box != null) {
//				return box;
//			}
//		}
		return new AttributeStyledTextBox(parent, styles, object, attr, checkEditOnContainer, actionMgr);
	}
	
	protected TextViewer viewer;
	protected EObject object;
	protected EAttribute attribute;
	private boolean checkEditOnContainer;
	private IActionManager actionMgr;
	
	protected AttributeStyledTextBox(Composite parent, int styles, EObject object, EAttribute attr, boolean checkEditOnContainer, IActionManager actionMgr) {
		assert attr != null && attr.getEAttributeType().getInstanceClass().isAssignableFrom(String.class);
		this.object = object;
		this.attribute = attr;
		this.checkEditOnContainer = checkEditOnContainer;
		this.actionMgr = actionMgr;
		viewer = createViewer(parent, styles);		
		final StyledText control = viewer.getTextWidget();
		control.setIndent(2);
		control.addModifyListener(this);
	}
	
	protected TextViewer createViewer(Composite parent, int styles) {
		TextViewer viewer = new TextViewer(parent, styles);
		viewer.setDocument(new Document(getAttribute()));
		return viewer;
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
		StyledText textCtrl = viewer.getTextWidget();
		textCtrl.removeModifyListener(this);
		try {
			textCtrl.setText(text);
		}
		finally {
			textCtrl.addModifyListener(this);
		}
	}
	
	protected String getAttribute() {
		Object val = object.eGet(attribute);
		return val == null ? "" : val.toString();
	}
	
	public TextViewer getViewer() {
		return viewer;
	}

	public void modifyText(ModifyEvent e) {
		StyledText textCtrl = viewer.getTextWidget();
		Shell shell = textCtrl.getShell();
		IStatus status = TngUtil.checkEdit(AttributeStyledTextBox.this.object, shell);
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
			int offset = textCtrl.getCaretOffset();
			setAttribute();
			viewer.setEditable(true);
			try {
				textCtrl.setCaretOffset(offset);
			} catch (Exception ex) {
				//
			}
		}
	}
}
