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
package org.eclipse.epf.library.ui;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.epf.common.ui.util.MsgBox;
import org.eclipse.epf.common.ui.util.MsgDialog;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.persistence.FileManager;
import org.eclipse.epf.services.Services;
import org.eclipse.epf.ui.EPFUIResources;
import org.eclipse.epf.ui.dialogs.RenameFileConflictDialog;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * Helper utilities for implementing method library UI.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class LibraryUIUtil {

	/**
	 * Updates the application shell title to display the application name and
	 * the default method library path.
	 */
	public static void updateShellTitle() {
		IWorkbenchWindow window = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		if (window != null) {
			Shell shell = window.getShell();
			if (shell != null) {
				String libPath = ""; //$NON-NLS-1$
				if (LibraryService.getInstance().getCurrentMethodLibrary() != null) {
					libPath = LibraryServiceUtil.getCurrentMethodLibraryPath();
					if (libPath == null) {
						libPath = LibraryService.getInstance()
								.getCurrentMethodLibrary().getName();
					}
				}
				String appName = Platform.getProduct().getName();
				String appTitle = NLS.bind(
						LibraryUIResources.application_title, new Object[] {
								appName, libPath });
				shell.setText(appTitle);
			}
		}
	}

	/**
	 * Displays a dialog to prompts the user to save the open library if it has
	 * been modified.
	 * 
	 * @return <code>SWT.YES</code>, <code>SWT.NO</code> or
	 *         <code>SWT.CANCEL</code>
	 */
	public static int displaySaveDirtyEditorsDialog() {
		ILibraryManager manager = (ILibraryManager) LibraryService
				.getInstance().getCurrentLibraryManager();
		if (manager != null && manager.isMethodLibraryModified()) {
			int ret = MsgBox.prompt(LibraryUIResources.saveLibraryDialog_title,
					LibraryUIResources.saveLibraryDialog_text, SWT.YES | SWT.NO
							| SWT.CANCEL);
			switch (ret) {
			case SWT.YES:
				try {
					LibraryService.getInstance().saveCurrentMethodLibrary();
				} catch (Exception e) {
					MsgDialog dialog = LibraryUIPlugin.getDefault()
							.getMsgDialog();
					dialog.displayError(
							LibraryUIResources.saveLibraryDialog_title,
							LibraryUIResources.saveLibraryError_msg,
							LibraryUIResources.error_reason, e);
				}
				return SWT.YES;
			case SWT.NO:
				// Discard all changes by resetting all resources as unchanged.
				manager.discardMethodLibraryChanges();
				return SWT.NO;
			case SWT.CANCEL:
				return SWT.CANCEL;
			}
		}

		return SWT.CANCEL;
	}

	/**
	 * Returns file URL for an attachment
	 * 
	 * @param attachment
	 *            the file to attach
	 * @param element
	 *            the MethodElement referencing the file
	 * @param copyFile
	 *            if true, will copy the file (if it isn't already in the plugin
	 *            path)
	 * @return URL of the form ./../&lt;roles&gt;/resources/&lt;filename of
	 *         attachment&gt;
	 * @throws IOException
	 */
	public static String getURLForAttachment(Shell shell, File attachment,
			MethodElement element, boolean copyFile) throws IOException {
		String pluginDir = FileUtil.appendSeparator(new File(UmaUtil.getMethodPlugin(element)
				.eResource().getURI().toFileString()).getParent());
		File formatFile = null;
		String resourceLoc = ResourceHelper.getAbsoluteElementResourcePath(element);
		// File resourceDir = new File(resourceLoc);
		formatFile = new File(resourceLoc + File.separator
				+ attachment.getName());
		if (copyFile) {
			File newFile = copyResourceToLib(shell, attachment, element);
			if (newFile != null) {
				formatFile = new File(resourceLoc + File.separator
						+ newFile.getName());
			} else {
				// user hit cancel
				return null;
			}
			IResource wsResource = FileManager.getResourceForLocation(formatFile.getAbsolutePath());
			if(wsResource != null) {
				try {
					FileManager.refresh(wsResource);
				}
				catch(Exception e) {
					LibraryPlugin.getDefault().getLogger().logError(e);
				}
			}
		}

		return ResourceHelper.getRelativePathToFileFromElement(element,
				formatFile);

	}
	
	/**
	 * Copies the given file into the methodElement's resource folder.
	 * Resolves filename conflict by prompting user to overwrite or rename
	 * @param shell if null, will overwrite file without prompting
	 * @param resource
	 * @param methodElement
	 * @return the File representing the user's final choice of library resource file
	 */
	public static File copyResourceToLib(Shell shell, File resource, MethodElement methodElement) {
		String resourceLoc = ResourceHelper.getAbsoluteElementResourcePath(methodElement);

		File libFile = new File(resourceLoc, resource.getName());
	
		if (resource.equals(libFile)) {
			// source file is already in resources dir
			return libFile;
		}
		
		// if no shell, will just overwrite
		if (libFile.exists() && shell != null) {
			RenameFileConflictDialog dialog = new RenameFileConflictDialog(shell);
			dialog.setMessageStr(MessageFormat.format(
									EPFUIResources.Dialog_fileNameConflict_msg, 
									new Object[] { resource.getName(), resourceLoc }));
			dialog.setDestination(resourceLoc);
			dialog.setFilePath(resource.getName());
			dialog.open();

			if (dialog.getReturnCode() == IDialogConstants.CANCEL_ID) {
				return null;
			} else {
				File oldLibFile = libFile;
				libFile = new File(resourceLoc, dialog.getFilePath());
				if (oldLibFile.equals(libFile)) {
					IStatus status = Services.getFileManager().checkModify(libFile.getAbsolutePath(), shell);
					if (!status.isOK()) {
						return null;
					}
				}
			}			
		}

		FileUtil.copyFile(resource, libFile);
		
		IResource wsResource = FileManager.getResourceForLocation(libFile.getAbsolutePath());
		if(wsResource != null) {
			try {
				FileManager.refresh(wsResource);
			}
			catch(Exception e) {
				LibraryPlugin.getDefault().getLogger().logError(e);
			}
		}


		return libFile;
	}
	
}
