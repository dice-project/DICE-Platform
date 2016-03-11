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
package org.eclipse.epf.authoring.ui.actions;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CommandActionDelegate;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.editors.IEditorKeeper;
import org.eclipse.epf.authoring.ui.preferences.AuthoringUIPreferences;
import org.eclipse.epf.authoring.ui.views.ViewHelper;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceException;
import org.eclipse.epf.library.edit.command.CommandStatusChecker;
import org.eclipse.epf.library.edit.command.MethodElementAddCommand;
import org.eclipse.epf.library.edit.process.command.CreateProcessComponentCommand;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodElementProperty;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.UmaFactory;

/**
 * Creates a new Method element.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class CreateMethodElementCommand extends CommandWrapper implements
		CommandActionDelegate {

	/**
	 * Creates a new instance.
	 */
	public CreateMethodElementCommand(Command command) {
		super(command);
	}
	
	private void reloadCurrentLibraryOnRollbackError() {
		ViewHelper.reloadCurrentLibaryOnRollbackError(null);
	}

	/**
	 * @see org.eclipse.emf.common.command.CommandWrapper#execute()
	 */
	public void execute() {
		Command cmd = TngUtil.unwrap(this);

		super.execute();

		if (command instanceof CreateProcessComponentCommand) {
			IStatus status = ((CreateProcessComponentCommand) command)
					.getStatus();
			if (status != null && !status.isOK()) {
				if (CommandStatusChecker.hasRollbackError(status)) {					
					reloadCurrentLibraryOnRollbackError();
				} else if (!CommandStatusChecker.hasSaveError(status)) {
					displayError(status);
				}
				return;
			}
		} else {
			cmd = this;

			while (cmd instanceof CommandWrapper
					&& !(cmd instanceof MethodElementAddCommand)) {
				cmd = ((CommandWrapper) cmd).getCommand();
			}

			if (cmd instanceof MethodElementAddCommand) {
				MethodElementAddCommand addCmd = ((MethodElementAddCommand) cmd);
				IStatus status = addCmd.getStatus();
				if (status != null && !status.isOK()) {
					if (CommandStatusChecker.hasRollbackError(status)) {
						reloadCurrentLibraryOnRollbackError();
					} else if (!CommandStatusChecker.hasSaveError(status)) {
						displayError(status);
					}
					return;
				}
			}
		}

		Collection<?> result = getCommand().getResult();
		for (Object o : result) {
			Object obj = TngUtil.unwrap(o);
			if (obj instanceof EObject && ((EObject) obj).eContainer() != null) {
				// Open the editor for the newly created element.
				if (AuthoringUIPreferences.getEnableAutoNameGen() && obj instanceof MethodElement) {
					LibraryUtil.addNameTrackPresentationNameMark((MethodElement) obj);				
				}
				
				//Set the publish back link for Practice and UDT before opening editor
				if (obj instanceof Practice) {
					Practice prac = (Practice)obj;
					MethodElementProperty prop = UmaFactory.eINSTANCE.createMethodElementProperty();
					prop.setName(TngUtil.PUBLISH_CATEGORY_PROPERTY);
					prop.setValue(new Boolean(true).toString());
					prac.getMethodElementProperty().add(prop);
					LibraryEditUtil.save(Collections.singleton(prac.eResource()));
				}
				
				IEditorKeeper.REFERENCE.getEditorKeeper().openEditor(obj);
			}
		}
	}

	/**
	 * @see org.eclipse.emf.common.command.CommandWrapper#canUndo()
	 */
	public boolean canUndo() {
		return false;
	}

	/**
	 * @see org.eclipse.emf.edit.command.CommandActionDelegate#getImage()
	 */
	public Object getImage() {
		CommandActionDelegate cmd = (CommandActionDelegate) getCommand();
		return cmd.getImage();
	}

	/**
	 * @see org.eclipse.emf.edit.command.CommandActionDelegate#getText()
	 */
	public String getText() {
		CommandActionDelegate cmd = (CommandActionDelegate) getCommand();
		return cmd.getText();
	}

	/**
	 * @see org.eclipse.emf.edit.command.CommandActionDelegate#getToolTipText()
	 */
	public String getToolTipText() {
		CommandActionDelegate cmd = (CommandActionDelegate) getCommand();
		return cmd.getToolTipText();
	}

	/**
	 * Display an error dialog.
	 */
	protected void displayError(IStatus status) {
		int severity = status.getSeverity();
		if (severity == IStatus.ERROR || severity == IStatus.WARNING) {
			AuthoringUIPlugin.getDefault().getMsgDialog().display(
					getLabel(),
					AuthoringUIResources.errorDialog_createError
					, status);
		}
	}

}
