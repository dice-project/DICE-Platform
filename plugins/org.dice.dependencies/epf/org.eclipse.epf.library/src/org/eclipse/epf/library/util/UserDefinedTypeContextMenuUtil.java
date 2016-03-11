/*******************************************************************************
* Licensed Materials - Property of IBM
* (c) Copyright IBM Corporation 2007,2011. All Rights Reserved.
*
* Note to U.S. Government Users Restricted Rights:
* Use, duplication or disclosure restricted by GSA ADP Schedule
* Contract with IBM Corp. 
*******************************************************************************/

package org.eclipse.epf.library.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.util.PracticePropUtil;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.UserDefinedTypeMeta;
import org.eclipse.jface.action.Separator;

public class UserDefinedTypeContextMenuUtil {
	private static final UserDefinedTypeContextMenuUtil INSTNACE = new UserDefinedTypeContextMenuUtil();
	
	private UserDefinedTypeContextMenuUtil() {
		//
	}
	
	public static UserDefinedTypeContextMenuUtil getInstance() {
		return INSTNACE;
	}
	
	public void addDescriptorsForUserDefinedType(Collection<Object> newChildDescriptors) {
		ILibraryManager libMgr = LibraryService.getInstance().getCurrentLibraryManager();
		Collection<UserDefinedTypeMeta> udtMetas = libMgr.getUserDefinedTypes();
		
		if (udtMetas == null) {
			return;
		}
		
		newChildDescriptors.add(new Separator());
		
		List<UserDefinedTypeMeta> udtMetaList = new ArrayList<UserDefinedTypeMeta>();
		for (UserDefinedTypeMeta udtMeta : udtMetas) {
			udtMetaList.add(udtMeta);
		}
		Collections.sort(udtMetaList, new UdtMetaComparator());
		
		try {
			for (UserDefinedTypeMeta udtMeta : udtMetaList) {
				Practice prac = UmaFactory.eINSTANCE.createPractice();
				PracticePropUtil.getPracticePropUtil().storeUtdData(prac, udtMeta);
				newChildDescriptors.add(new CommandParameter(null, UmaPackage.eINSTANCE
						.getContentPackage_ContentElements(), prac));
			}
		} catch (Exception e) {
			LibraryPlugin.getDefault().getLogger().logError(e);
		}
	}
	
	private class UdtMetaComparator implements Comparator<UserDefinedTypeMeta> {
	    public int compare(UserDefinedTypeMeta obj1, UserDefinedTypeMeta obj2) {
	    	String name1 = obj1.getRteNameMap().get(UserDefinedTypeMeta._typeName);
	    	String name2 = obj2.getRteNameMap().get(UserDefinedTypeMeta._typeName);
	    	
	    	return name1.compareTo(name2);
	    }		
	}
	
}
