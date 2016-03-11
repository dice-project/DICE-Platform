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

import org.eclipse.epf.authoring.ui.forms.ProcessFormUtil;
import org.eclipse.epf.authoring.ui.preferences.ApplicationPreferenceConstants;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.util.ConfigurableComposedAdapterFactory;


/**
 * The team rollup section for activity
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 * 
 */
public class ActivityTeamRollupSection extends ActivityRollupSection {

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.ActivityRollupSection#initAdapterFactory()
	 */
	protected void initAdapterFactory() {
		// get column descriptors
		columnDescriptors = ProcessFormUtil.toColumnDescriptors(store
				.getString(ApplicationPreferenceConstants.PREF_TBS_COLUMNS));

		// create adapter factory
		adapterFactory = TngAdapterFactory.INSTANCE
				.createTBSComposedAdapterFactory();
		if (adapterFactory instanceof ConfigurableComposedAdapterFactory) {
			((ConfigurableComposedAdapterFactory) adapterFactory)
					.setFilter(configurator);
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.ActivityRollupSection#setSectionLabels()
	 */
	protected void setSectionLabels() {
		section.setText(PropertiesResources.Activity_TeamRollup); 
		section.setDescription(PropertiesResources.Activity_TeamRollupDescription); 
	}

}