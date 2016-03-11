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
package org.eclipse.epf.library.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.configuration.PracticeItemProvider;
import org.eclipse.epf.library.edit.configuration.PracticeSubgroupItemProvider;
import org.eclipse.epf.uma.provider.UmaEditPlugin;

/**
 * utility class to manage icon urls
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public class IconUtil {

	private static URL nodeIconPluginRoot = null;
	private static URL nodeIconPluginRoot1 = null;

	public static final String ICON_Activity = "full/obj16/Activity"; //$NON-NLS-1$

	public static final String ICON_Artifact = "full/obj16/Artifact"; //$NON-NLS-1$

	public static final String ICON_CapabilityPattern = "full/obj16/CapabilityPattern"; //$NON-NLS-1$

	public static final String ICON_Checklist = "full/obj16/Checklist"; //$NON-NLS-1$

	public static final String ICON_Concept = "full/obj16/Concept"; //$NON-NLS-1$

	public static final String ICON_ContentPackage = "full/obj16/ContentPackage"; //$NON-NLS-1$

	public static final String ICON_CustomCategory = "full/obj16/CustomCategory"; //$NON-NLS-1$

	public static final String ICON_Deliverable = "full/obj16/Deliverable"; //$NON-NLS-1$

	public static final String ICON_DeliveryProcess = "full/obj16/DeliveryProcess"; //$NON-NLS-1$

	public static final String ICON_Discipline = "full/obj16/Discipline"; //$NON-NLS-1$

	public static final String ICON_DisciplineGrouping = "full/obj16/DisciplineGrouping"; //$NON-NLS-1$

	public static final String ICON_Domain = "full/obj16/Domain"; //$NON-NLS-1$

	public static final String ICON_Estimate = "full/obj16/Estimate"; //$NON-NLS-1$

	public static final String ICON_EstimationConsiderations = "full/obj16/EstimationConsiderations"; //$NON-NLS-1$

	public static final String ICON_Example = "full/obj16/Example"; //$NON-NLS-1$

	public static final String ICON_Guideline = "full/obj16/Guideline"; //$NON-NLS-1$

	public static final String ICON_Iteration = "full/obj16/Iteration"; //$NON-NLS-1$

	public static final String ICON_MethodConfiguration = "full/obj16/MethodConfiguration"; //$NON-NLS-1$

	public static final String ICON_MethodLibrary = "full/obj16/MethodLibrary"; //$NON-NLS-1$

	public static final String ICON_MethodPackage = "full/obj16/MethodPackage"; //$NON-NLS-1$

	public static final String ICON_MethodPlugin = "full/obj16/MethodPlugin"; //$NON-NLS-1$

	public static final String ICON_Milestone = "full/obj16/Milestone"; //$NON-NLS-1$

	public static final String ICON_Outcome = "full/obj16/Outcome"; //$NON-NLS-1$

	public static final String ICON_Phase = "full/obj16/Phase"; //$NON-NLS-1$

	public static final String ICON_Practice = "full/obj16/Practice"; //$NON-NLS-1$

	public static final String ICON_ProcessContribution = "full/obj16/ProcessContribution"; //$NON-NLS-1$

	public static final String ICON_ProcessFamily = "full/obj16/ProcessFamily"; //$NON-NLS-1$

	public static final String ICON_ProcessPackage = "full/obj16/ProcessPackage"; //$NON-NLS-1$

	public static final String ICON_Report = "full/obj16/Report"; //$NON-NLS-1$

	public static final String ICON_ReusableAsset = "full/obj16/ReusableAsset"; //$NON-NLS-1$

	public static final String ICON_Roadmap = "full/obj16/Roadmap"; //$NON-NLS-1$

	public static final String ICON_Role = "full/obj16/Role"; //$NON-NLS-1$

	public static final String ICON_RoleSet = "full/obj16/RoleSet"; //$NON-NLS-1$

	public static final String ICON_RoleSetGrouping = "full/obj16/RoleSetGrouping"; //$NON-NLS-1$

	public static final String ICON_SupportingMaterial = "full/obj16/SupportingMaterial"; //$NON-NLS-1$

	public static final String ICON_Task = "full/obj16/Task"; //$NON-NLS-1$

	public static final String ICON_Template = "full/obj16/Template"; //$NON-NLS-1$

	public static final String ICON_TermDefinition = "full/obj16/TermDefinition"; //$NON-NLS-1$

	public static final String ICON_Tool = "full/obj16/Tool"; //$NON-NLS-1$

	public static final String ICON_ToolMentor = "full/obj16/ToolMentor"; //$NON-NLS-1$

	public static final String ICON_Whitepaper = "full/obj16/Whitepaper"; //$NON-NLS-1$

	public static final String ICON_WorkProduct = "full/obj16/WorkProduct"; //$NON-NLS-1$

	public static final String ICON_WorkProductType = "full/obj16/WorkProductType"; //$NON-NLS-1$

	public static final String ICON_Guidance = "full/obj16/Guidance"; //$NON-NLS-1$

	public static final String ICON_Processes = "full/obj16/Process"; //$NON-NLS-1$
	
	public static final String ICON_UDT = "full/obj16/UdtPublishNode"; //$NON-NLS-1$
	
	// map of image type to image
	private static Map nodeIconUrlMap = new HashMap();
	static {
		nodeIconUrlMap.put("activity", ICON_Activity); //$NON-NLS-1$
		nodeIconUrlMap.put("artifact", ICON_Artifact); //$NON-NLS-1$
		nodeIconUrlMap.put("capabilitypattern", ICON_CapabilityPattern); //$NON-NLS-1$
		nodeIconUrlMap.put("checklist", ICON_Checklist); //$NON-NLS-1$
		nodeIconUrlMap.put("concept", ICON_Concept); //$NON-NLS-1$
		nodeIconUrlMap.put("contentpackage", ICON_ContentPackage); //$NON-NLS-1$
		nodeIconUrlMap.put("customcategory", ICON_CustomCategory); //$NON-NLS-1$
		nodeIconUrlMap.put("deliverable", ICON_Deliverable); //$NON-NLS-1$
		nodeIconUrlMap.put("deliveryprocess", ICON_DeliveryProcess); //$NON-NLS-1$
		nodeIconUrlMap.put("discipline", ICON_Discipline); //$NON-NLS-1$
		nodeIconUrlMap.put("disciplinegrouping", ICON_DisciplineGrouping); //$NON-NLS-1$
		nodeIconUrlMap.put("domain", ICON_Domain); //$NON-NLS-1$
		nodeIconUrlMap.put("estimate", ICON_Estimate); //$NON-NLS-1$
		nodeIconUrlMap.put(
				"estimationconsiderations", ICON_EstimationConsiderations); //$NON-NLS-1$
		nodeIconUrlMap.put("example", ICON_Example); //$NON-NLS-1$
		nodeIconUrlMap.put("guideline", ICON_Guideline); //$NON-NLS-1$
		nodeIconUrlMap.put("iteration", ICON_Iteration); //$NON-NLS-1$
		nodeIconUrlMap.put("methodconfiguration", ICON_MethodConfiguration); //$NON-NLS-1$
		nodeIconUrlMap.put("methodlibrary", ICON_MethodLibrary); //$NON-NLS-1$
		nodeIconUrlMap.put("methodpackage", ICON_MethodPackage); //$NON-NLS-1$
		nodeIconUrlMap.put("methodplugin", ICON_MethodPlugin); //$NON-NLS-1$
		nodeIconUrlMap.put("milestone", ICON_Milestone); //$NON-NLS-1$
		nodeIconUrlMap.put("outcome", ICON_Outcome); //$NON-NLS-1$
		nodeIconUrlMap.put("phase", ICON_Phase); //$NON-NLS-1$
		nodeIconUrlMap.put("practice", ICON_Practice); //$NON-NLS-1$
		nodeIconUrlMap.put("processcontribution", ICON_ProcessContribution); //$NON-NLS-1$
		nodeIconUrlMap.put("processfamily", ICON_ProcessFamily); //$NON-NLS-1$
		nodeIconUrlMap.put("processpackage", ICON_ProcessPackage); //$NON-NLS-1$
		nodeIconUrlMap.put("report", ICON_Report); //$NON-NLS-1$
		nodeIconUrlMap.put("reusableasset", ICON_ReusableAsset); //$NON-NLS-1$
		nodeIconUrlMap.put("roadmap", ICON_Roadmap); //$NON-NLS-1$
		nodeIconUrlMap.put("role", ICON_Role); //$NON-NLS-1$
		nodeIconUrlMap.put("roleset", ICON_RoleSet); //$NON-NLS-1$
		nodeIconUrlMap.put("rolesetgrouping", ICON_RoleSetGrouping); //$NON-NLS-1$
		nodeIconUrlMap.put("supportingmaterial", ICON_SupportingMaterial); //$NON-NLS-1$
		nodeIconUrlMap.put("task", ICON_Task); //$NON-NLS-1$
		nodeIconUrlMap.put("template", ICON_Template); //$NON-NLS-1$
		nodeIconUrlMap.put("termdefinition", ICON_TermDefinition); //$NON-NLS-1$
		nodeIconUrlMap.put("tool", ICON_Tool); //$NON-NLS-1$
		nodeIconUrlMap.put("toolmentor", ICON_ToolMentor); //$NON-NLS-1$
		nodeIconUrlMap.put("whitepaper", ICON_Whitepaper); //$NON-NLS-1$
		nodeIconUrlMap.put("workproduct", ICON_WorkProduct); //$NON-NLS-1$
		nodeIconUrlMap.put("workproducttype", ICON_WorkProductType); //$NON-NLS-1$

		nodeIconUrlMap.put("roledescriptor", ICON_Role); //$NON-NLS-1$
		nodeIconUrlMap.put("taskdescriptor", ICON_Task); //$NON-NLS-1$
		nodeIconUrlMap.put("workproductdescriptor", ICON_WorkProduct); //$NON-NLS-1$

		nodeIconUrlMap.put("guidances", ICON_Guidance); //$NON-NLS-1$
		nodeIconUrlMap.put("processes", ICON_Processes); //$NON-NLS-1$
		nodeIconUrlMap.put("UDT", ICON_UDT); //$NON-NLS-1$
		
		try {
			nodeIconPluginRoot = FileLocator.resolve(UmaEditPlugin.INSTANCE
					.getBaseURL());
			nodeIconPluginRoot1 = nodeIconPluginRoot;
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
/*		try {
			nodeIconPluginRoot1 = FileLocator.resolve(LibraryEditPlugin.INSTANCE
					.getInstallURL());
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}

	/**
	 * get the node icon url
	 * 
	 * @param type String the element type
	 * @return URL
	 */
	public static URL getNodeIconURL(String type) {
		try {
			String key = (String) nodeIconUrlMap.get(type.toLowerCase());
			if (key != null) {
				return new URL(nodeIconPluginRoot, "icons/" + key + ".gif"); //$NON-NLS-1$ //$NON-NLS-2$
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static URL getNodeIconURL(PracticeSubgroupItemProvider provider) {
		try {
			String key = PracticeItemProvider.getImageStr(provider.getText(null));
			key = key.replace("full/obj16", "full/obj16_external");//$NON-NLS-1$ //$NON-NLS-2$
			if (key != null) {
				return new URL(nodeIconPluginRoot1, "icons/" + key + ".gif"); //$NON-NLS-1$ //$NON-NLS-2$
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * get the node icon file location for the element type
	 * @param type String the element type
	 * @return File
	 */
	public static File getNodeIconFile(String type) {
		URL url = getNodeIconURL(type);
		if (url != null) {
			return new File(url.getFile());
		}

		return null;
	}
	
	public static File getNodeIconFile(PracticeSubgroupItemProvider provider) {
		URL url = getNodeIconURL(provider);
		if (url != null) {
			return new File(url.getFile());
		}

		return null;
	}
}
