/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
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
package org.eclipse.epf.richtext.html;

/**
 * Models a simplified HTML &lt;table&gt; tag.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class Table {

	/**
	 * A table with no headers.
	 */
	public static final int TABLE_HEADERS_NONE = 0;

	/**
	 * A table with column headers.
	 */
	public static final int TABLE_HEADERS_COLS = 1;

	/**
	 * A table with row headers.
	 */
	public static final int TABLE_HEADERS_ROWS = 2;

	/**
	 * A table with both column and row headers.
	 */
	public static final int TABLE_HEADERS_BOTH = 3;

	// The number of rows.
	private int rows = 2;

	// The number of columns.
	private int cols = 2;

	// The table width.
	private String width = "85%"; //$NON-NLS-1$

	// The type of tableheaders for this table.
	private int tableHeaders = 0;

	// The table summary.
	private String summary;

	// The table caption.
	private String caption;

	/**
	 * Creates a new instance.
	 */
	public Table() {
	}

	/**
	 * Gets the number of rows in the table.
	 * 
	 * @return the number of rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Sets the number of rows in the table.
	 * 
	 * @param rows
	 *            the number of rows
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * Gets the number of columns in the table.
	 * 
	 * @return the number of columns
	 */
	public int getColumns() {
		return cols;
	}

	/**
	 * Sets the number of columns in the table.
	 * 
	 * @param cols
	 *            the number of columns
	 */
	public void setColumns(int cols) {
		this.cols = cols;
	}

	/**
	 * Gets the table width.
	 * 
	 * @return the width of the table
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * Sets the table width.
	 * 
	 * @param width
	 *            the width of the table
	 */
	public void setWidth(String width) {
		this.width = width;
	}

	/**
	 * Gets the table caption.
	 * 
	 * @return the table caption
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * Sets the table caption.
	 * 
	 * @para caption the table caption
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

	/**
	 * Gets the table summary.
	 * 
	 * @return the table summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * Sets the table summary.
	 * 
	 * @param summary
	 *            the table summary
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * Gets the table headers style.
	 * 
	 * @return the integer with value representing
	 *         <code>TABLE_HEADERS_NONE</code>,
	 *         <code>TABLE_HEADERS_COLS</code>,
	 *         <code>TABLE_HEADERS_ROWS</code> or
	 *         <code>TABLE_HEADERS_BOTH</code>
	 */
	public int getTableHeaders() {
		return tableHeaders;
	}

	/**
	 * Sets the table headers style.
	 * 
	 * @param tableHeaders
	 *            an integer with value representing
	 *            <code>TABLE_HEADERS_NONE</code>,
	 *            <code>TABLE_HEADERS_COLS</code>,
	 *            <code>TABLE_HEADERS_ROWS</code> or
	 *            <code>TABLE_HEADERS_BOTH</code>
	 */
	public void setTableHeaders(int tableHeaders) {
		this.tableHeaders = tableHeaders;
	}

}
