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
package org.eclipse.epf.authoring.ui.actions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.epf.authoring.ui.wizards.NewConfigurationWizard;
import org.eclipse.epf.common.ui.util.ErrorDialogNoReason;
import org.eclipse.epf.common.ui.util.MsgBox;
import org.eclipse.epf.library.edit.command.IUserInteractionHandler;
import org.eclipse.epf.library.edit.command.MethodElementCreateChildCommand;
import org.eclipse.epf.library.edit.command.UserInput;
import org.eclipse.epf.library.ui.LibraryUIPlugin;
import org.eclipse.epf.library.ui.dialogs.UncancelableListSelectionDialog;
import org.eclipse.epf.library.ui.dialogs.UserInputsDialog;
import org.eclipse.epf.library.ui.providers.DelegateLabelProvider;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

/**
 * Implements handling of user interaction during command execution
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class UserInteractionHandler implements IUserInteractionHandler {

	private Shell shell;

	private IMessenger messenger = new IMessenger() {

		/*
		 * @see org.eclipse.epf.library.edit.command.IUserInteractionHandler.IMessenger#showWarning(java.lang.String,
		 *      java.lang.String)
		 */
		public void showWarning(final String title, final String msg) {
			if (Display.getCurrent() == null) {
				// current thread is not a user-interface thread
				//
				Display.getDefault().syncExec(new Runnable() {

					public void run() {
						LibraryUIPlugin.getDefault().getMsgDialog()
								.displayWarning(title, msg);
					}

				});
			} else {
				LibraryUIPlugin.getDefault().getMsgDialog().displayWarning(
						title, msg);
			}
		}

		/*
		 * @see org.eclipse.epf.library.edit.command.IUserInteractionHandler.IMessenger#showError(java.lang.String,
		 *      java.lang.String, org.eclipse.core.runtime.IStatus)
		 */
		public void showError(final String title, final String msg,
				final IStatus status) {
			if (status == null) {
				showError(title, msg);
			} else {
				if (Display.getCurrent() == null) {
					// current thread is not a user-interface thread
					//
					Display.getDefault().syncExec(new Runnable() {

						public void run() {
							LibraryUIPlugin.getDefault().getMsgDialog()
									.displayError(title, msg, status);
						}

					});
				} else {
					LibraryUIPlugin.getDefault().getMsgDialog().displayError(
							title, msg, status);
				}
			}
		}

		/*
		 * @see org.eclipse.epf.library.edit.command.IUserInteractionHandler.IMessenger#showError(java.lang.String,
		 *      java.lang.String, java.lang.String, java.lang.Exception)
		 */
		public void showError(final String title, final String msg,
				final String reason, final Exception exception) {
			if (Display.getCurrent() == null) {
				// current thread is not a user-interface thread
				//
				Display.getDefault().syncExec(new Runnable() {

					public void run() {
						LibraryUIPlugin.getDefault().getMsgDialog()
								.displayError(title, msg, reason, exception);
					}

				});
			} else {
				LibraryUIPlugin.getDefault().getMsgDialog().displayError(title,
						msg, reason, exception);
			}
		}

		/*
		 * @see org.eclipse.epf.library.edit.command.IUserInteractionHandler.IMessenger#showWarning(java.lang.String,
		 *      java.lang.String, java.lang.String)
		 */
		public void showWarning(final String title, final String msg,
				final String reason) {
			if (Display.getCurrent() == null) {
				// current thread is not a user-interface thread
				//
				Display.getDefault().syncExec(new Runnable() {

					public void run() {
						LibraryUIPlugin.getDefault().getMsgDialog()
								.displayWarning(title, msg, reason);
					}

				});
			} else {
				LibraryUIPlugin.getDefault().getMsgDialog().displayWarning(
						title, msg, reason);
			}
		}

		/*
		 * @see org.eclipse.epf.library.edit.command.IUserInteractionHandler.IMessenger#showError(java.lang.String,
		 *      java.lang.String)
		 */
		public void showError(final String title, final String msg) {
			if (Display.getCurrent() == null) {
				// current thread is not a user-interface thread
				//
				Display.getDefault().syncExec(new Runnable() {

					public void run() {
						LibraryUIPlugin.getDefault().getMsgDialog()
								.displayError(title, msg);
					}

				});
			} else {
				LibraryUIPlugin.getDefault().getMsgDialog().displayError(title,
						msg);
			}
		}

		/*
		 * @see org.eclipse.epf.library.edit.command.IUserInteractionHandler.IMessenger#showInfo(java.lang.String,
		 *      java.lang.String)
		 */
		public void showInfo(final String title, final String msg) {
			if (Display.getCurrent() == null) {
				// current thread is not a user-interface thread
				//
				Display.getDefault().syncExec(new Runnable() {

					public void run() {
						LibraryUIPlugin.getDefault().getMsgDialog()
								.displayInfo(title, msg);
					}

				});
			} else {
				LibraryUIPlugin.getDefault().getMsgDialog().displayInfo(title,
						msg);
			}
		}

		/*
		 * @see org.eclipse.epf.library.edit.command.IUserInteractionHandler.IMessenger#showError(java.lang.String,
		 *      java.lang.String, java.lang.String, java.lang.String,
		 *      java.lang.Exception)
		 */
		public void showError(final String title, final String msg,
				final String reason, final String details,
				final Exception exception) {
			if (Display.getCurrent() == null) {
				// current thread is not a user-interface thread
				//
				Display.getDefault().syncExec(new Runnable() {

					public void run() {
						LibraryUIPlugin.getDefault().getMsgDialog()
								.displayError(title, msg, reason, details,
										exception);
					}

				});
			} else {
				LibraryUIPlugin.getDefault().getMsgDialog().displayError(title,
						msg, reason, details, exception);
			}
		}

	};

	public UserInteractionHandler() {

	}

	/**
	 * @param shell
	 *            the shell to set
	 */
	public void setShell(Shell shell) {
		this.shell = shell;
	}

	/*
	 * @see org.eclipse.epf.library.edit.command.IUserInteractionHandler#getUIContext()
	 */
	public Object getUIContext() {
		if (shell == null) {
			return MsgBox.getDefaultShell();
		}
		return shell;
	}

	/*
	 * @see org.eclipse.epf.library.edit.command.IUserInteractionHandler#select(java.util.List,
	 *      org.eclipse.jface.viewers.ILabelProvider, boolean, java.util.List,
	 *      java.lang.String, java.lang.String)
	 */
	public List select(List objectsToSelect, final IItemLabelProvider labelProvider,
			boolean multiple, List intitialSelection, String title, String msg) {
		ILabelProvider lp = new DelegateLabelProvider(labelProvider);
		if (multiple) {
			IStructuredContentProvider contentProvider = new ArrayContentProvider();
			UncancelableListSelectionDialog dlg = new UncancelableListSelectionDialog(
					shell, objectsToSelect, contentProvider, lp, msg);
			dlg.setTitle(title);
			dlg.setBlockOnOpen(true);
			dlg.open();
			Object objs[] = dlg.getResult();
			if (objs == null) {
				return null;
			} else {
				if (objs.length == 0) {
					return Collections.EMPTY_LIST;
				}
				return Arrays.asList(objs);
			}
		} else {
			ElementListSelectionDialog dlg = new ElementListSelectionDialog(
					shell, lp);

			dlg.setBlockOnOpen(true);
			dlg.setElements(objectsToSelect.toArray());
			dlg.setMultipleSelection(false);
			dlg.setMessage(msg);
			dlg.setTitle(title);
			dlg.setFilter(null);
			if (dlg.open() == Dialog.CANCEL) {
				return null;
			}
			Object obj = dlg.getFirstResult();
			return Collections.singletonList(obj);
		}
	}

	/*
	 * @see org.eclipse.epf.library.edit.command.IUserInteractionHandler#selectOne(int[],
	 *      java.lang.String, java.lang.String)
	 */
	public int selectOne(int[] actions, String title, String msg, IStatus status) {
		final String[] buttonLabels = new String[actions.length];
		for (int i = 0; i < actions.length; i++) {
			buttonLabels[i] = getActionText(actions[i]);
		}
		Image image = null;
		try {
			shell = LibraryUIPlugin.getDefault().getWorkbench().getDisplay()
					.getActiveShell();
			image = shell.getImage();
		} catch (Exception e) {
		}
		if (status == null) {
			MessageDialog msgDlg = new MessageDialog(shell, title, image, msg,
					MessageDialog.QUESTION, buttonLabels, 0);
			int id = msgDlg.open();
			return actions[id];
		} else {
			ErrorDialogNoReason dlg = new ErrorDialogNoReason(shell, title,
					msg, status, IStatus.OK | IStatus.INFO | IStatus.WARNING
							| IStatus.ERROR) {

				protected void createButtonsForButtonBar(Composite parent) {
					// create OK and Details buttons
					for (int i = 0; i < buttonLabels.length; i++) {
						String label = buttonLabels[i];
						createButton(parent, i, label, i == 0);
					}
					if (shouldShowDetailsButton()) {
						detailsButton = createButton(parent,
								IDialogConstants.DETAILS_ID,
								IDialogConstants.SHOW_DETAILS_LABEL, false);
					}

				}

				/*
				 * (non-Javadoc)
				 * 
				 * @see org.eclipse.epf.common.serviceability.ErrorDialogNoReason#buttonPressed(int)
				 */
				protected void buttonPressed(int id) {
					if (id == IDialogConstants.DETAILS_ID) {
						super.buttonPressed(id);
					} else {
						setReturnCode(id);
						close();
					}
				}
			};
			int id = dlg.open();
			return actions[id];
		}
	}

	/**
	 * Return action text
	 * 
	 * @param action
	 * @return Action text - either ABORT, CANCEL, OK, RETRY
	 */
	public static String getActionText(int action) {
		switch (action) {
		case IUserInteractionHandler.ACTION_ABORT:
			return IDialogConstants.ABORT_LABEL;
		case IUserInteractionHandler.ACTION_CANCEL:
			return IDialogConstants.CANCEL_LABEL;
		case IUserInteractionHandler.ACTION_OK:
			return IDialogConstants.OK_LABEL;
		case IUserInteractionHandler.ACTION_RETRY:
			return IDialogConstants.RETRY_LABEL;
		case IUserInteractionHandler.ACTION_YES:
			return IDialogConstants.YES_LABEL;
		case IUserInteractionHandler.ACTION_NO:
			return IDialogConstants.NO_LABEL;
		default:
			break;
		}
		return null;
	}

	/*
	 * @see org.eclipse.epf.library.edit.command.IUserInteractionHandler#getMessenger()
	 */
	public IMessenger getMessenger() {
		return messenger;
	}

	/*
	 * @see org.eclipse.epf.library.edit.command.IUserInteractionHandler#requestInput(java.lang.String,
	 *      java.lang.String, java.util.List)
	 */
	public boolean requestInput(final String title, final String msg, final List userInputs) {
		if (userInputs == null || userInputs.size() == 0)
			return false;
		
		if (Display.getCurrent() == null) {
			// current thread is not a user-interface thread
			//
			final boolean retHolder[] = new boolean[1];
			Display.getDefault().syncExec(new Runnable() {

				public void run() {
					retHolder[0] = doRequestInput(title, msg, userInputs);
				}

			});
			return retHolder[0];
		} else {
			return doRequestInput(title, msg, userInputs);
		}
		
	}
	
	private boolean doRequestInput(String title, String msg, List userInputs) {
		final UserInput userInput = (UserInput) userInputs.get(0);
		if (userInput.getContext() instanceof MethodElementCreateChildCommand) {
			MethodElementCreateChildCommand cmd = (MethodElementCreateChildCommand) userInput
					.getContext();
			if (cmd.getFeature() == UmaPackage.eINSTANCE
					.getMethodLibrary_PredefinedConfigurations()) {
				NewConfigurationWizard wizard = new NewConfigurationWizard();
				wizard.init(PlatformUI.getWorkbench(), null);
				wizard.setMethodConfiguration((MethodConfiguration) cmd.getChild());
				WizardDialog dialog = new WizardDialog(Display.getCurrent()
						.getActiveShell(), wizard);
				dialog.create();
				if (dialog.open() == Window.OK) {
					userInput.setInput(wizard.getMethodConfiguration().getName());
					return true;
				}
				else {
					return false;
				}
			}
		}
		
		if(userInputs.size() == 1 && userInput.getType() == UserInput.TEXT && userInput.getLabel() == null) {
			// user InputDialog for this case until we fix this dialog box
			//
			IInputValidator validator = userInput.getValidator() != null ? new IInputValidator() {

				public String isValid(String newText) {
					return userInput.getValidator().isValid(newText);
				}
			
			} : null;
			InputDialog dlg = new InputDialog(PlatformUI
				.getWorkbench().getDisplay().getActiveShell(),
				title, msg, (String)userInput.getInput(), validator);
			if(dlg.open() == Window.OK) {
				userInput.setInput(dlg.getValue());
				return true;
			}
			return false;
		}
		
		UserInputsDialog dialog = new UserInputsDialog(PlatformUI
				.getWorkbench().getDisplay().getActiveShell(), userInputs,
				title, msg);
		dialog.setBlockOnOpen(true);
		dialog.open();
		return dialog.getResult();
	}

}