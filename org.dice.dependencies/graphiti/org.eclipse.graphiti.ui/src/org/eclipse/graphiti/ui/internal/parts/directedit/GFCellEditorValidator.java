/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.parts.directedit;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Control;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFCellEditorValidator implements ICellEditorValidator {
	private IDirectEditHolder directEditHolder;
	private CellEditor cellEditor;

	public GFCellEditorValidator(IDirectEditHolder directEditHolder, CellEditor cellEditor) {
		this.directEditHolder = directEditHolder;
		setCellEditor(cellEditor);
	}

	public String isValid(Object value) {
		String ret = null;

		if (value instanceof String) {
			ret = directEditHolder.getDirectEditingFeature().checkValueValid((String) value, directEditHolder.getDirectEditingContext());
		} else if (value instanceof Integer && getCellEditor() instanceof ComboBoxCellEditor) {
			ComboBoxCellEditor cb = (ComboBoxCellEditor) getCellEditor();
			String sValue = null;
			int index = (Integer) value;
			if (index < 0) { // -1 if user inserted a new value
				Control control = cb.getControl();
				if (control instanceof CCombo) {
					CCombo cc = (CCombo) control;
					sValue = cc.getText();
				}
			} else {
				String[] items = cb.getItems();
				if (items != null && index < items.length) {
					sValue = items[index];
				}
			}
			if (sValue != null) {
				ret = directEditHolder.getDirectEditingFeature().checkValueValid(sValue, directEditHolder.getDirectEditingContext());
			}
		}
		return ret;
	}

	private CellEditor getCellEditor() {
		return cellEditor;
	}

	private void setCellEditor(CellEditor cellEditor) {
		this.cellEditor = cellEditor;
	}
}
