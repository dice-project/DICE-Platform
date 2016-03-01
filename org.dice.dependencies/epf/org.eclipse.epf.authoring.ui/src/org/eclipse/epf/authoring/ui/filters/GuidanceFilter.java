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
package org.eclipse.epf.authoring.ui.filters;

import org.eclipse.epf.library.edit.itemsfilter.FilterInitializer;
import org.eclipse.epf.library.edit.util.PracticePropUtil;
import org.eclipse.epf.uma.Checklist;
import org.eclipse.epf.uma.Concept;
import org.eclipse.epf.uma.EstimationConsiderations;
import org.eclipse.epf.uma.Example;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.Guideline;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.Report;
import org.eclipse.epf.uma.ReusableAsset;
import org.eclipse.epf.uma.SupportingMaterial;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.Template;
import org.eclipse.epf.uma.ToolMentor;
import org.eclipse.epf.uma.WorkProduct;


/**
 * Filter for {@link Guidance} type elements.
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class GuidanceFilter extends ContentFilter {

	protected boolean childAccept(Object obj) {
//		 When type is selected allow those Class types to display.
		Class cls = FilterInitializer.getInstance().
						getClassForType(helper.getFilterTypeStr());
		if(cls != null){
			if(cls.isInstance(obj)){
				return true;
			}else{
				return false;
			}
		}
		// For all ContentElement - these guidances are associated
		if (obj instanceof Checklist || obj instanceof Concept
				|| obj instanceof Example || obj instanceof SupportingMaterial
				|| obj instanceof Guideline || obj instanceof ReusableAsset)	
			return true;
		
		// For task extra guidances
		if(helper.getContentElement() instanceof Task){
			if(obj instanceof ToolMentor 
					|| obj instanceof EstimationConsiderations)
				return true;
		}
		// For workProduct Extra guidances.
		if(helper.getContentElement() instanceof WorkProduct){
			if(obj instanceof Report || obj instanceof Template
					|| obj instanceof ToolMentor 
					|| obj instanceof EstimationConsiderations)
				return true;
		}
		
		if (obj instanceof Practice) {
			if (PracticePropUtil.getPracticePropUtil().isUdtType((Practice) obj)) {
				return true;
			}
		}
				
		return false;
	}

}
