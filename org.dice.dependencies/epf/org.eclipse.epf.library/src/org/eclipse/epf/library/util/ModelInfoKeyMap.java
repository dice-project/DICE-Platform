//------------------------------------------------------------------------------
// Copyright (c) 2005, 2008 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.epf.library.LibraryResources;
import org.eclipse.epf.library.edit.meta.TypeDefUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.provider.UmaEditPlugin;
import org.eclipse.epf.uma.util.ExtendedReference;
import org.eclipse.epf.uma.util.ModifiedTypeMeta;


/**
 * class to map model info values to model info key values
 * 
 * @author Weiping Lu
 * @since 1.5
 *
 */
public class ModelInfoKeyMap {
	
	private static boolean localDebug = false;
	private static ModelInfoKeyMap instance = new ModelInfoKeyMap();
	private Map<String, String> map = new HashMap<String, String>();
	
	private ModelInfoKeyMap() {
		map.put(getString("_UI_Task_mandatoryInput_feature"), "mandatoryInput");//$NON-NLS-1$ //$NON-NLS-2$ 
		map.put(getString("_UI_Task_optionalInput_feature"), "optionalInput");//$NON-NLS-1$ //$NON-NLS-2$ 
		map.put(getString("_UI_TaskDescriptor_externalInput_feature"), "externalInput");//$NON-NLS-1$ //$NON-NLS-2$ 
		map.put(getString("_UI_Task_output_feature"), "output");//$NON-NLS-1$ //$NON-NLS-2$ 
		map.put(getString("_UI_TaskDescriptor_performedPrimarilyBy_feature"), "performedPrimarilyBy");//$NON-NLS-1$ //$NON-NLS-2$ 
		map.put(getString("_UI_TaskDescriptor_additionallyPerformedBy_feature"), "additionallyPerformedBy");//$NON-NLS-1$ //$NON-NLS-2$ 
		map.put(getString("_UI_TaskDescriptor_assistedBy_feature"), "assistedBy");//$NON-NLS-1$ //$NON-NLS-2$ 

		map.put(TngUtil.getFeatureText(UmaPackage.eINSTANCE.getRoleDescriptor_ResponsibleFor()), 
				"ResponsibleFor");//$NON-NLS-1$  
		map.put(TngUtil.getFeatureText(UmaPackage.eINSTANCE.getRoleDescriptor_Modifies()), 
				"Modifies");//$NON-NLS-1$ 
		map.put(LibraryResources.ActivityLayout_primaryTasks_text, 
				"primaryTasks");//$NON-NLS-1$ 
		map.put(LibraryResources.ActivityLayout_additionalTasks_text, 
				"additionalTasks");//$NON-NLS-1$
		map.put(LibraryResources.ActivityLayout_assistTasks_text, 
				"assistTasks");//$NON-NLS-1$ 
		
		ModifiedTypeMeta taskMeta = TypeDefUtil.getMdtMeta(UmaPackage.eINSTANCE.getTask());
		ModifiedTypeMeta roleMeta = TypeDefUtil.getMdtMeta(UmaPackage.eINSTANCE.getRole());

		addForModifiedType(taskMeta);
		addForModifiedType(roleMeta);
	}

	private void addForModifiedType(ModifiedTypeMeta meta) {
		if (meta == null) {
			return;
		}
		for (ExtendedReference eRef : meta.getReferences()) {
			boolean w = ExtendedReference.WorkProducts.equals(eRef.getContributeTo());
			boolean r = ExtendedReference.Roles.equals(eRef.getContributeTo());
			if (w || r) {
				map.put(eRef.getName(), eRef.getName());
			}			
			if (r) {
				String info = LibraryResources.bind(LibraryResources.ActivityLayout_performAs_text, (new String[] {eRef.getName()}));
				map.put(info, info);
			}
		}
	}
	
	public static ModelInfoKeyMap getInstance() {
		return instance;
	}
		
	private String getString(String uiKey) {
		return UmaEditPlugin.INSTANCE.getString(uiKey);
	}
	
	public String getModelInfoKey(String ModelInfo) {
		String ModelInfoKey = "";		//$NON-NLS-1$ 
		List<String> strList = TngUtil.convertStringsToList(ModelInfo);
		for (String value: strList) {
			String key = map.get(value);
			if (key != null) {
				if (ModelInfoKey.length() > 0) {
					ModelInfoKey += ", ";	//$NON-NLS-1$ 
				}
				ModelInfoKey += key;
			}
		}
		if (localDebug) {
			System.out.println("LD> ModelInfo: " + ModelInfo);//$NON-NLS-1$ 
			System.out.println("LD> ModelInfoKey: " + ModelInfoKey);//$NON-NLS-1$ 
			System.out.println("");//$NON-NLS-1$ 
		}
		
		return ModelInfoKey;
	}
		
/*	
    Mandatory Input
    Optional Input
    External Input
    Output

    Primary Performer
    Additional Performer
    Assisted By

    ActivityLayout_primaryTasks_text=Performs as Owner
	ActivityLayout_additionalTasks_text=Performs as Additional
	ActivityLayout_assistTasks_text=Performs as assist

    Responsible for
    Modifies
*/
	
}
