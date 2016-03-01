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
/**
 * 
 */
package org.eclipse.epf.diagram.core.util;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.common.ui.util.MsgDialog;
import org.eclipse.epf.diagram.core.DiagramCorePlugin;
import org.eclipse.epf.diagram.core.DiagramCoreResources;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.model.NamedNode;
import org.eclipse.epf.diagram.model.WorkBreakdownElementNode;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.tools.TextDirectEditManager;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.ActivityNode;

/**
 * Manages and validates the direct editing of editpart. Prompts accordingly.  
 * Should implement abstract methods when extends.
 */
public abstract class ValidatingTextDirectEditManager extends TextDirectEditManager {

	public ValidatingTextDirectEditManager(GraphicalEditPart source, Class editorType, CellEditorLocator locator) {
		super(source, editorType, locator);
	}
	
	/**
	 * @see org.eclipse.gef.tools.DirectEditManager#commit()
	 */
	protected void commit() {
		String msg = validate((String) getCellEditor().getValue());
		if (msg != null) {
			bringDown(false);
			Display.getCurrent().asyncExec(
					new PromptEdit(this, msg));
			return;
		}
		super.commit();
	}
	
	/**
	 * @see org.eclipse.gef.tools.DirectEditManager#bringDown()
	 */
	protected void bringDown() {
		bringDown(true);
	}

	/**
	 * @see org.eclipse.gef.tools.DirectEditManager#bringDown()
	 */
	protected void bringDown(boolean check) {
		super.bringDown();
		if (check) {
			Node node = (Node) getEditPart().getModel();
			EObject aNode = node.getElement();
			String name = null;
			if (aNode instanceof ActivityNode) {
				name = ((ActivityNode)aNode).getName();
				Object wbelement = BridgeHelper.getMethodElement((EModelElement)aNode);
				if (wbelement instanceof Activity
						&& ProcessUtil
								.isExtendingOrLocallyContributing((BreakdownElement) wbelement)) {
					name = ProcessUtil
							.getPresentationName((BreakdownElement) wbelement);
				}

			}else if(aNode instanceof NamedNode){
				NamedNode nameNode = (NamedNode) aNode;
				name = nameNode.getName();
				if (node instanceof WorkBreakdownElementNode) {
					Object wbelement = nameNode.getObject();
					if (wbelement instanceof Activity
							&& ProcessUtil
									.isExtendingOrLocallyContributing((BreakdownElement) wbelement)) {
						name = ProcessUtil
								.getPresentationName((BreakdownElement) wbelement);
					}

				}
			}
			
			if (name == null || name.trim().length() == 0) {
				Display.getCurrent().asyncExec(
						new PromptEdit(this,DiagramCoreResources.err_name_empty)); //$NON-NLS-1$
			}
		}
	}


	static class PromptEdit implements Runnable {

		private ValidatingTextDirectEditManager manager;

		private String msg;

		PromptEdit(ValidatingTextDirectEditManager manager, String msg) {
			this.manager = manager;
			this.msg = msg;
		}

		/**
		 * @see java.lang.Runnable#run()
		 */
		public void run() {
			String title = DiagramCoreResources.diagram_errorDialog_title;
			MsgDialog dialog = DiagramCorePlugin.getDefault().getMsgDialog();
			dialog.displayError(title, msg); //$NON-NLS-1$
			manager.performDirectEdit();
		}

	}
	
	/**
	 * Checks if the given text is valid. Subclasses can override this method.
	 * 
	 * @param txt
	 * @return null if the given text is valid, an error message otherwise
	 */
	protected abstract String validate(String txt);
	
	protected abstract void performDirectEdit();
}
