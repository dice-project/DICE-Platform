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
package org.eclipse.epf.library.ui.dialogs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.LibraryResources;
import org.eclipse.epf.library.edit.command.DeleteMethodElementCommand;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.ui.LibraryUIPlugin;
import org.eclipse.epf.library.ui.LibraryUIResources;
import org.eclipse.epf.library.ui.LibraryUIText;
import org.eclipse.epf.library.ui.util.TypeConverter;
import org.eclipse.epf.library.util.ConvertActivityType;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ListDialog;

/**
 * Dialog to convert activity type 
 * 
 * @author Jeff Hardy
 * @since 1.0
 */
public class ConvertActivityDialog {

	/**
	 * Query user and convert the old activity into new one
	 * 
	 * @param oldActivity
	 * @param shell
	 * @param command
	 * 
	 * @return Newly converted activity
	 */
	public static Activity queryUserAndConvert(Activity oldActivity, Shell shell,
			DeleteMethodElementCommand command) {
		
		if (shell == null)
			shell = Display.getCurrent().getActiveShell();
		ListDialog dlg = new ListDialog(shell);
		dlg.setHeightInChars(5);
		dlg.setContentProvider(new ArrayContentProvider());
		dlg.setLabelProvider(new LabelProvider() {
			public String getText(Object element) {
				switch (((Integer) element).intValue()) {
				// TODO: refactor these strings (and this whole dialog) into
				// library.ui
				case UmaPackage.ACTIVITY:
					return LibraryUIText.TEXT_ACTIVITY;
				case UmaPackage.ITERATION:
					return LibraryUIText.TEXT_ITERATION;
				case UmaPackage.PHASE:
					return LibraryUIText.TEXT_PHASE;
				default:
					return LibraryResources.unknownGuidance_text; 
				}

			}
		});
		List newActivityTypeList = getValidNewActivityTypes(oldActivity);
		if (newActivityTypeList == null) {
			LibraryUIPlugin
					.getDefault()
					.getMsgDialog()
					.displayError(
							LibraryResources.convertActivityError_title, 
							LibraryUIResources.unsupportedActivityType_msg,
							LibraryUIResources.bind(LibraryUIResources.unsupportedActivityType_reason, StrUtil.toLower(TngUtil.getTypeText(oldActivity))));
			return null;
		}
		dlg.setInput(newActivityTypeList);
		dlg.setTitle(LibraryUIResources.convertActivityDialog_title);
		dlg.setMessage(LibraryUIResources.convertActivityDialog_text);
		if (dlg.open() == Dialog.CANCEL)
			return null;

		Object[] selectionResult = dlg.getResult();
		if (selectionResult == null)
			return null;
		int chosenActivity = ((Integer) selectionResult[0]).intValue();
		
//		return ConvertActivityType.convertActivity(oldActivity, chosenActivity, command);
		
		return TypeConverter.convertActivity(oldActivity, TypeConverter.getActivityType(chosenActivity));
	}
	
	/**
	 * Gets list of valid new activity types based on old activity type
	 * 
	 * @param oldActivity
	 * 
	 * @return list of valid activity types
	 * 
	 */
	public static List getValidNewActivityTypes(Activity oldActivity) {
		if (oldActivity == null)
			return null;
		Integer oldActivityClassID = new Integer(oldActivity.eClass()
				.getClassifierID());
		if (!ConvertActivityType.compatibleActivitiesList.contains(oldActivityClassID))
			return null;
		List activityList = new ArrayList();
		for (Iterator iter = ConvertActivityType.compatibleActivitiesList.iterator(); iter.hasNext();) {
			Integer compatibleActivityTypeClassID = (Integer)iter.next();
			if (!oldActivityClassID.equals(compatibleActivityTypeClassID) &&
					compatibleActivityTypeClassID.intValue() != UmaPackage.CAPABILITY_PATTERN &&
					compatibleActivityTypeClassID.intValue() != UmaPackage.DELIVERY_PROCESS)
				activityList.add(compatibleActivityTypeClassID);
		}
		return activityList;
	}

	
}
