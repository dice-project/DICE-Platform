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
package org.eclipse.epf.library.ui.wizards;

import java.io.File;

import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.ui.LibraryUIPlugin;
import org.eclipse.epf.library.ui.LibraryUIResources;

/**
 * Utility class for validating and creating a directory.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=162153
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=177500
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=182056
 */
public class DirectoryValidator {

	/**
	 * Validates and creates a given directory.
	 * <p>
	 * Displays an error dialog if the directory path is not valid.
	 * 
	 * @param dir
	 *            The path to be validated and created.
	 * @param title
	 *            The dialog title.
	 * @param errorMsg
	 *            The error message to be displayed in the error dialog.
	 * @return <code>true</code> if path is valid.
	 */
	public static boolean checkAndCreateDir(String dir, String title,
			String errorMsg) {		
		boolean answer = false;
		File file = new File(dir);
		if (file.exists()) {
			File[] files = file.listFiles();
			if (files != null && files.length > 0) {
				answer = LibraryUIPlugin
						.getDefault()
						.getMsgDialog()
						.displayConfirmation(
								title,
								LibraryUIResources
										.bind(
												LibraryUIResources.deleteFilesConfirmation_msg,
												dir));
				if (answer == true) {
					try {
						answer = FileUtil
								.deleteAllFiles(file.getAbsolutePath());
						if (answer == false) {
							LibraryUIPlugin
									.getDefault()
									.getMsgDialog()
									.displayError(
											title,
											errorMsg,
											LibraryUIResources
													.bind(
															LibraryUIResources.deleteFilesError_reason,
															dir));
						}
					} catch (Exception e) {
						LibraryUIPlugin
								.getDefault()
								.getMsgDialog()
								.displayError(
										title,
										errorMsg,
										LibraryUIResources
												.bind(
														LibraryUIResources.deleteFilesError_reason,
														dir), e);
						answer = false;
					}
				}
			} else {
				answer = true;
			}
		} else {
//			if (!file.isAbsolute()) {
//				LibraryUIPlugin.getDefault().getMsgDialog().displayError(title,
//						LibraryUIResources.invalidPath_msg);
//				answer = false;
//			} else {

				try {
					if (file.mkdirs() == false) {
						LibraryUIPlugin
								.getDefault()
								.getMsgDialog()
								.displayError(
										title,
										errorMsg,
										LibraryUIResources
												.bind(
														LibraryUIResources.createPathError_reason,
														dir));
						answer = false;
					} else {
						answer = true;
					}
				} catch (Exception e) {
					LibraryUIPlugin.getDefault().getMsgDialog().displayError(
							title,
							errorMsg,
							LibraryUIResources.bind(
									LibraryUIResources.createPathError_reason,
									dir), e);
					answer = false;
				}
			}
//		}

		return answer;
	}
	
	/**
	 * Validates and creates a given directory.
	 * <p>
	 * Displays an error dialog if the directory path is not valid.
	 * 
	 * @param dir
	 *            The path to be validated and created.
	 * @param title
	 *            The dialog title.
	 * @param errorMsg
	 *            The error message to be displayed in the error dialog.
	 * @param allowCurrentLibPath
	 * 			  Allow paths containing current library or not
	 * @return <code>true</code> if path is valid.
	 */
	public static boolean checkAndCreateDir(String dir, String title,
			String errorMsg, boolean allowCurrentLibPath) {
		if(!allowCurrentLibPath){
			String currentLibPath = LibraryService.getInstance().getCurrentMethodLibraryLocation();
			if (currentLibPath != null) {
				String targetPathFileStr = new File(dir).getAbsolutePath();
				String libPathFileStr = new File(currentLibPath).getAbsolutePath();
				if (libPathFileStr.equals(targetPathFileStr)) {
//				if(dir.startsWith(currentLibPath) || currentLibPath.startsWith(dir)){
					LibraryUIPlugin.getDefault().getMsgDialog().
					displayError(title, LibraryUIResources.overrideCurrentLibraryError_msg0);
					return false;
				}
			}
		}
		return checkAndCreateDir(dir, title, errorMsg);
	}
}
