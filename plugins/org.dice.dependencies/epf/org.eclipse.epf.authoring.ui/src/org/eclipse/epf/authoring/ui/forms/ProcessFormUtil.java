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
package org.eclipse.epf.authoring.ui.forms;

import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.epf.authoring.ui.editors.ColumnDescriptor;
import org.eclipse.epf.authoring.ui.editors.ProcessEditor;

import com.ibm.icu.util.StringTokenizer;

/**
 * Helper class for the Process editor form pages.
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class ProcessFormUtil {

	/**
	 * Converts string representation into column descriptors list
	 * @param str
	 * @return
	 * 		List of column descriptors
	 */
	public static EList toColumnDescriptorList(String str) {
		EList columnDescriptors = new BasicEList();
		StringTokenizer tokens = new StringTokenizer(str, ","); //$NON-NLS-1$
		while (tokens.hasMoreTokens()) {
			String token = tokens.nextToken();
			Object columnDescriptor = ProcessEditor.idToColumnDescriptorMap
					.get(token);
			if (columnDescriptor != null) {
				columnDescriptors.add(columnDescriptor);
			}
		}
		return columnDescriptors;
	}

	/**
	 * Converts string representation into column descriptors array list
	 * @param str
	 * @return
	 * 		Array ist of column descriptors
	 */
	public static ColumnDescriptor[] toColumnDescriptors(String str) {
		List list = toColumnDescriptorList(str);
		ColumnDescriptor[] columns = new ColumnDescriptor[list.size()];
		list.toArray(columns);
		return columns;
	}

}
