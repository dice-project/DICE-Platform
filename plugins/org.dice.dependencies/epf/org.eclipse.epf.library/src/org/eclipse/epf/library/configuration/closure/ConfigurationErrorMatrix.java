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
package org.eclipse.epf.library.configuration.closure;

import org.eclipse.epf.library.LibraryResources;
import org.eclipse.epf.library.configuration.ConfigurationData;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.WorkProduct;

/**
 * define the configuration error level metrix
 * 
 * @author Jinhua Xi
 * @author Weiping Lu
 * @since 1.2
 *
 */
public class ConfigurationErrorMatrix {
	
//	private static List level1Features = new ArrayList();
//	private static List level2Features = new ArrayList();
//	private static List level3Features = new ArrayList();
//	static {
//		level2Features.add(UmaPackage.eINSTANCE.getTask_PerformedBy());
//	}
	
//	MethodConfiguration config;
//	public ConfigurationErrorMatrix(MethodConfiguration config) {
//		this.config = config;
//	}
	
	public static ElementError getError(MethodConfiguration config, ElementReference ref) {
		
		MethodElement e_ref = (MethodElement)ref.getRefElement();		
		ConfigurationData cData = ConfigurationHelper.getDelegate().getConfigurationData(config);
		if (cData != null && cData.isElementSubtracted(e_ref)) {
			return null;
		}
		
		if ( ref.hasFeature(UmaPackage.eINSTANCE.getVariabilityElement_VariabilityBasedOnElement()) ) {
			return getVariabilityError(config, ref);
		} 
		
		int errorLevel = ErrorInfo.INFO;
		String messageId = LibraryResources.ElementError_missing_element;
		
		MethodElement e = (MethodElement)ref.getElement();

		if ( ref.hasFeature(UmaPackage.eINSTANCE.getTask_MandatoryInput()) ) {
			errorLevel = ErrorInfo.ERROR;
			messageId = LibraryResources.ElementError_missing_mandatory_input;
		} else if ( ref.hasFeature(UmaPackage.eINSTANCE.getTask_Output()) ) {
			errorLevel = ErrorInfo.ERROR;
			messageId = LibraryResources.ElementError_missing_output;
		} else if ( ref.hasFeature(UmaPackage.eINSTANCE.getTask_PerformedBy()) ) {
			errorLevel = ErrorInfo.WARNING;
			messageId = LibraryResources.ElementError_missing_primary_performer;
		} else if ( ref.hasFeature(UmaPackage.eINSTANCE.getRole_ResponsibleFor()) ) {
			errorLevel = ErrorInfo.WARNING;
			messageId = LibraryResources.ElementError_missing_responsible_for_workProduct;
		} else if ( ref.hasFeature(UmaPackage.eINSTANCE.getTask_AdditionallyPerformedBy()) 
				|| ref.hasFeature(UmaPackage.eINSTANCE.getTask_OptionalInput()) 
				|| ref.hasFeature(UmaPackage.eINSTANCE.getRole_Modifies())
				 ) {
			// default
		} else if ( isBreakdownReference(e, e_ref) ) {
			errorLevel = ErrorInfo.INFO;
		} else if( isGuidanceReference(e, e_ref) ) {
			// default
		} else {
			return null;
		}

		String message = LibraryResources.bind(messageId, 
				(new String[] {LibraryUtil.getTypePath(e), 
						LibraryUtil.getTypePath(e_ref) }));

		return new ElementError(config, errorLevel, message, e, e_ref, ErrorInfo.REFERENCE_TO, messageId);
	}
	
	private static boolean isBreakdownReference(MethodElement e, MethodElement e_ref) {
		return (e_ref instanceof Task || e_ref instanceof Role || e_ref instanceof WorkProduct) 
			&& (e instanceof BreakdownElement);
	}
	
	private static boolean isGuidanceReference(MethodElement e, MethodElement e_ref) {
		return (e instanceof Task || e instanceof Role || e instanceof WorkProduct) 
			&& (e_ref instanceof Guidance);
	}
	
	private static ElementError getVariabilityError(MethodConfiguration config, ElementReference ref) {
	
		VariabilityElement e = (VariabilityElement)ref.getElement();
		VariabilityElement e_ref = (VariabilityElement)ref.getRefElement();
		
		if ( e.getVariabilityBasedOnElement() != e_ref ) {
			return null;
		}
		
		VariabilityType type = e.getVariabilityType();
		
		int errorLevel = ErrorInfo.WARNING;
		String messageId = LibraryResources.ElementError_missing_element;

		if ( type == VariabilityType.CONTRIBUTES ) {
			if ( e instanceof ContentCategory || e instanceof Guidance ) {
				errorLevel = ErrorInfo.INFO;
			}
			
			messageId = LibraryResources.ElementError_contributor_missing_base;
		} else if ( type == VariabilityType.EXTENDS ) {
			messageId = LibraryResources.ElementError_extender_missing_base;
			
		} else if ( type == VariabilityType.REPLACES 
				|| type == VariabilityType.EXTENDS_REPLACES) {
			messageId = LibraryResources.ElementError_replacer_missing_base;
		}
		
		String message = LibraryResources.bind(messageId, 
				(new String[] {LibraryUtil.getTypePath(e), 
						LibraryUtil.getTypePath(e_ref) }));

		return new ElementError(config, errorLevel, message, e, e_ref, ErrorInfo.REFERENCE_TO, messageId);
	}
}
