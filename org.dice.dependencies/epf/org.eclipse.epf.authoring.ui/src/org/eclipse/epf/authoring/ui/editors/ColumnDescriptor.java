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
package org.eclipse.epf.authoring.ui.editors;

import java.io.Serializable;

import org.eclipse.swt.SWT;

/**
 * Column descriptors used for process editors
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class ColumnDescriptor implements Serializable {

	private static final long serialVersionUID = 3256718476985970997L;

	public static final int CELL_EDITOR_TYPE_NONE = 0;

	public static final int CELL_EDITOR_TYPE_TEXT = 1;

	public static final int CELL_EDITOR_TYPE_COMBO_BOOLEAN = 2;

	public static final int CELL_EDITOR_TYPE_CHECK_BOOLEAN = 3;

	public String id;

	public String label;

	public int width;

	public boolean resizable;

	public int cellEditorType;

	public int weight;

	public int alignment;

	/**
	 * 
	 * @param id
	 * 			ID of the column
	 * @param label
	 * 			Column Name
	 * @param weight
	 * 			Column weight
	 * @param width
	 * 			Column Width
	 * @param resizable
	 * 			Flag to indicate whether column is resizable or not
	 * @param cellEditorType
	 * 			Column cell editor type
	 */
	public ColumnDescriptor(String id, String label, int weight, int width,
			boolean resizable, int cellEditorType) {
		this(id, label, weight, width, resizable, cellEditorType, SWT.LEFT);
	}

	/**
	 * Creates an instance
	 * 
	 * @param id
	 * 			ID of the column
	 * @param label
	 * 			Column Name
	 * @param weight
	 * 			Column weight
	 * @param width
	 * 			Column Width
	 * @param resiable
	 * 			Flag to indicate whether column is resizable or not
	 * @param cellEditorType
	 * 			Column cell editor type
	 * @param alignment
	 * 			Column alignment
	 */
	public ColumnDescriptor(String id, String label, int weight, int width,
			boolean resiable, int cellEditorType, int alignment) {
		this.id = id;
		this.label = label;
		this.resizable = resiable;
		this.weight = weight;
		this.width = width;
		this.cellEditorType = cellEditorType;
		this.alignment = alignment;
	}

}
