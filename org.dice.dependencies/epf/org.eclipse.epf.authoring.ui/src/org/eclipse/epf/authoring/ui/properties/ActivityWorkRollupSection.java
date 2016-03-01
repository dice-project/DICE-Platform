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

import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.epf.authoring.ui.editors.ColumnDescriptor;
import org.eclipse.epf.authoring.ui.editors.ProcessEditor;
import org.eclipse.epf.authoring.ui.preferences.ApplicationPreferenceConstants;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.util.ConfigurableComposedAdapterFactory;

import com.ibm.icu.util.StringTokenizer;


/**
 * The work rollup section for activity
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 * 
 */
public class ActivityWorkRollupSection extends ActivityRollupSection {

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.ActivityRollupSection#initAdapterFactory()
	 */
	protected void initAdapterFactory() {
		// get column descriptors
		columnDescriptors = getColumnDescriptors();

		// create adapter factory
		adapterFactory = TngAdapterFactory.INSTANCE
				.createWBSComposedAdapterFactory();

		if (adapterFactory instanceof ConfigurableComposedAdapterFactory) {
			((ConfigurableComposedAdapterFactory) adapterFactory)
					.setFilter(configurator);
		}

	}
	
	private ColumnDescriptor[] getColumnDescriptors() {
		ColumnDescriptor[] descriptors = toColumnDescriptors(store
				.getString(ApplicationPreferenceConstants.PREF_WBS_COLUMNS));
		
		// don't show column "Predecessors"
		//
		int index = -1;
		for (int i = 0; i < descriptors.length; i++) {
			if(descriptors[i] == ProcessEditor.COL_DESC_PREDECESSORS) {
				index = i;
				break;
			}			
		}
		if(index != -1) {
			ColumnDescriptor[] colDescriptors = new ColumnDescriptor[descriptors.length - 1];
			if(index > 0) {
				System.arraycopy(descriptors, 0, colDescriptors, 0, index);
			}
			int len = descriptors.length - 1 - index;
			if(len > 0) {
				System.arraycopy(descriptors, index + 1, colDescriptors, index, len);
			}
			return colDescriptors;
		}
		return descriptors;
	}

	/**
	 * Convert string into colum descriptor list
	 * @param str
	 * @return
	 * 		List of column descriptors
	 */
	public static EList toColumnDescriptorList(String str) {
		EList columnDescriptors = new BasicEList();
		StringTokenizer tokens = new StringTokenizer(str, ","); //$NON-NLS-1$
		while (tokens.hasMoreTokens()) {
			String token = tokens.nextToken();
			// DON"T PUT INDEX COLUMN
			if (!(token.equals(ProcessEditor.COL_DESC_ID.id))) {
				Object columnDescriptor = ProcessEditor.idToColumnDescriptorMap
						.get(token);
				if (columnDescriptor != null) {
					columnDescriptors.add(columnDescriptor);
				}
			}
		}
		return columnDescriptors;
	}

	/**
	 * Convert string into column descriptors
	 * @param str
	 * @return
	 * 		list of column descriptors
	 */
	public static ColumnDescriptor[] toColumnDescriptors(String str) {
		List list = toColumnDescriptorList(str);
		ColumnDescriptor[] columns = new ColumnDescriptor[list.size()];
		list.toArray(columns);
		return columns;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.ActivityRollupSection#setSectionLabels()
	 */
	protected void setSectionLabels() {
		section.setText(PropertiesResources.Activity_WorkRollup); 
		section.setDescription(PropertiesResources.Activity_WorkRollupDescription); 
	}

}