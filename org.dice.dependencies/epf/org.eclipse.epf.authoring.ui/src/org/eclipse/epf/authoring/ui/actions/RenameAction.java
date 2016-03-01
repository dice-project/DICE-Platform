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

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.action.CommandActionHandler;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.editors.EditorChooser;
import org.eclipse.epf.common.ui.util.MsgBox;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.diagram.core.services.DiagramManager;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.IValidator;
import org.eclipse.epf.library.edit.validation.IValidatorFactory;
import org.eclipse.epf.library.ui.actions.LibraryLockingOperationRunner;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.services.ILibraryPersister.FailSafeMethodLibraryPersister;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.NamedElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.util.ContentDescriptionFactory;
import org.eclipse.epf.uma.util.MessageException;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Renames method element.
 * 
 * @author Phong Nguyen Le
 * @since  1.2
 */
public class RenameAction extends
		CommandActionHandler implements IWorkbenchPartAction, IModifyingAction {

	private static final String LABEL = AuthoringUIResources.renameAction_text;

	private IStructuredSelection selection;

	private IWorkbenchPart activePart;

	/**
	 * Creates an instance
	 * @param text
	 */
	public RenameAction(String text) {
		super(null, text);
	}
	
	/**
	 * Creates an instance
	 *
	 */
	public RenameAction() {
		this(LABEL);
	}
	
	/**
	 * Returns <code>true</code> if the selected method elements can be
	 * renamed.
	 */
	private boolean canRename(IStructuredSelection selection) {
		if (!(domain instanceof AdapterFactoryEditingDomain) || selection.size() > 1)
			return false;
		Object element = TngUtil.unwrap(selection.getFirstElement());
		if (element instanceof NamedElement
				&& !TngUtil.isPredefined((MethodElement) element)) {
			return true;
		}
		return false;
	}

	
	/**
	 * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public boolean updateSelection(IStructuredSelection selection) {
		boolean ret = false;
		if(canRename(selection)) {
			this.selection = selection;
			ret = super.updateSelection(selection);
		}
		setEnabled(ret);
		return ret;
	}
	
	@Override
	public Command createCommand(Collection<?> selection) {
		if(selection.isEmpty()) {
			return UnexecutableCommand.INSTANCE;
		}
		Object o = TngUtil.unwrap(selection.iterator().next());
		if(o instanceof NamedElement) {
			return new RenameCommand((NamedElement) o, null);
		}
		return UnexecutableCommand.INSTANCE;
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.actions.IWorkbenchPartAction#setActiveWorkbenchPart(org.eclipse.ui.IWorkbenchPart)
	 */
	public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
		if(workbenchPart instanceof IEditingDomainProvider) {
			domain = ((IEditingDomainProvider)workbenchPart).getEditingDomain();
		}
		activePart = workbenchPart;
	}	
	
	private void superRun() {
		final NamedElement e = (NamedElement) TngUtil.unwrap(selection.getFirstElement());
//		Resource resource = e.eResource();
//		if(resource != null) {
//			org.eclipse.core.resources.IFile file = WorkspaceSynchronizer.getFile(e.eResource());
//			if(file != null) {
//				RepositoryProvider provider = RepositoryProvider.getProvider(file.getProject());
//				if(provider != null) {
//					IFileHistoryProvider historyProvider = provider.getFileHistoryProvider();
//					FileModificationValidator modValidator = provider.getFileModificationValidator2();
//					String id = provider.getID();
//					Subscriber subscriber = provider.getSubscriber();
//					System.out.println(id);
//				}
//			}
//		}

		Shell shell = activePart.getSite().getShell();
		
		// check if container's resource can be modified
		//
		if (e.eContainer() != null
				&& e.eContainer().eResource() != e.eResource()) {
			IStatus status = UserInteractionHelper.checkModify(e.eContainer(), shell);
			if (!status.isOK()) {
				AuthoringUIPlugin
				.getDefault()
				.getMsgDialog()
				.displayError(
						AuthoringUIResources.renameDialog_title,
						AuthoringUIResources.renameDialog_renameError,
						status);
				return;
			}
		}
		
		if (! FileUtil.getValidateEdit().renamePrecheck(e, shell)) {
			return;
		}

		final IValidator validator = IValidatorFactory.INSTANCE
		.createNameValidator(e, ((AdapterFactoryEditingDomain)domain).getAdapterFactory());
		IInputValidator inputValidator = new IInputValidator() {
			public String isValid(String newText) {
				if (validator != null) {
					return UserInteractionHelper
					.getSimpleErrorMessage(validator
							.isValid(newText));
				}
				return null;
			}
		};

		boolean getInput = true;
		InputDialog inputDlg = new InputDialog(Display.getCurrent()
				.getActiveShell(), AuthoringUIResources.rename_text,
				AuthoringUIResources.newname_text, e.getName(),
				inputValidator);
		while (getInput) {
			getInput = false;
			if (inputDlg.open() == Window.OK) {
				String newName = inputDlg.getValue().trim();
				if(e instanceof ContentElement) {
					newName = StrUtil.makeValidFileName(newName);
				}
				if (!newName.equals(e.getName())) {
					if (e instanceof MethodConfiguration) {
						String[] configNames = LibraryServiceUtil
						.getMethodConfigurationNames(LibraryService
								.getInstance()
								.getCurrentMethodLibrary());
						for (int i = 0; i < configNames.length; i++) {
							if (newName.equals(configNames[i])) {
								AuthoringUIPlugin
								.getDefault()
								.getMsgDialog()
								.displayError(
										AuthoringUIResources.renameDialog_title,
										AuthoringUIResources
										.bind(
												AuthoringUIResources.duplicateElementNameError_msg,
												newName));
								getInput = true;
								break;
							}
						}
					}
					if (getInput == true) {
						inputDlg = new InputDialog(Display.getCurrent()
								.getActiveShell(),
								AuthoringUIResources.rename_text,
								AuthoringUIResources.newname_text, e
								.getName(), inputValidator);
						continue;
					}

					if (e instanceof MethodPlugin) {
						String msg = AuthoringUIResources.bind(AuthoringUIResources.methodPluginDescriptionPage_confirmRename, (new Object[] { e.getName(), newName })); 
						String title = AuthoringUIResources.methodPluginDescriptionPage_confirmRename_title; 
						if (!MessageDialog.openConfirm(shell, title, msg)) {
							return;
						}

						EditorChooser.getInstance().closeMethodEditorsForPluginElements((MethodPlugin)e);
					}
					
					RenameCommand renameCmd = (RenameCommand) command;
					renameCmd.setNewName(newName);
					renameCmd.setShell(shell);
					super.run();					
				}
			}
		}
	}
	
	/**
	 * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#run()
	 */
	public void run() {
		BusyIndicator.showWhile(activePart.getSite()
				.getShell().getDisplay(), new Runnable() {

					public void run() {
						LibraryLockingOperationRunner runner = new LibraryLockingOperationRunner();
						runner.run(new IRunnableWithProgress() {

							public void run(IProgressMonitor monitor)
									throws InvocationTargetException,
									InterruptedException {
								superRun();
							}
							
						});
					}
			
		});
	}

	public static void doRename(final NamedElement e, final String newName) {
		new RenameCommand(e, newName).execute();
	}
	
	private static class RenameCommand extends AbstractCommand {		
		private NamedElement e;
		private String newName;
		private String oldName;
		private Shell shell;
		Collection<Resource> renamedResources;
		
		public RenameCommand(NamedElement e, String newName) {
			this.e = e;
			this.newName = newName;
		}		
		
		public void setNewName(String newName) {
			this.newName = newName;
		}
		
		public void setShell(Shell shell) {
			this.shell = shell;
		}
		
		private void rollback() {
			// restore old name
			//
			setName(oldName);
			
			// restore old location
			//
			if(!renamedResources.isEmpty()) {
				adjustLocation(renamedResources, new ArrayList<Resource>());
			}
		}
		
		private void handlePersistenceException(Exception e1) {
			AuthoringUIPlugin.getDefault().getLogger().logError(e1);

			String details = e1.getMessage() != null ? MessageFormat
					.format(": {0}", new Object[] { e1.getMessage() }) : ""; //$NON-NLS-1$ //$NON-NLS-2$
			String msg = MessageFormat.format(
					AuthoringUIResources.ElementsView_err_saving,
					new Object[] {
							e.eResource().getURI().toFileString(),
							details });
			throw new MessageException(msg);
		}
		
		private ILibraryPersister.FailSafeMethodLibraryPersister getPersister() {
			return LibraryServiceUtil.getPersisterFor(e.eResource())
						.getFailSafePersister();
		}
		
		/**
		 * Adjusts location of all affected resources.
		 * 
		 * @return resources whose location has been adjusted
		 */
		private IStatus adjustLocation(Collection<Resource> renamedResources) {
			Collection<Resource> resourcesToRename = new ArrayList<Resource>();
			if (e instanceof ContentElement
					&& ContentDescriptionFactory
							.hasPresentation((MethodElement) e)) {
				resourcesToRename.add(((ContentElement) e).getPresentation()
						.eResource());
			}
			resourcesToRename.add(e.eResource());
			return adjustLocation(resourcesToRename, renamedResources);
		}
		
		/**
		 * Adjusts location of the specified resources.
		 * 
		 * @return resources whose location has been adjusted
		 */
		private IStatus adjustLocation(Collection<Resource> resourcesToRename, Collection<Resource> renamedResources) {
			FailSafeMethodLibraryPersister persister = getPersister();
			try {
				for (Resource resource : resourcesToRename) {
					URI oldURI = resource.getURI();
					persister.adjustLocation(resource);
					if(!resource.getURI().equals(oldURI)) {
						renamedResources.add(resource);
					}
				}
				return Status.OK_STATUS;
			} catch (Exception e) {
				AuthoringUIPlugin.getDefault().getLogger().logError(e);
				try {
					persister.rollback();
				}
				catch(Exception ex) {
					AuthoringUIPlugin.getDefault().getLogger().logError(e);
				}
				return new Status(IStatus.ERROR, AuthoringUIPlugin.getDefault().getId(),
						e.getMessage(), e);
			}
		}		
		
		private void setName(String newName) {
			e.setName(newName);
			
			// Special handling for ProcessComponent to keep its
			// name and the name of its process in sync.
			if (e instanceof ProcessComponent) {
				Process proc = ((ProcessComponent) e).getProcess();
				proc.setName(newName);
			}
		}

		public void execute() {
			oldName = e.getName();			
			setName(newName);
			
			//Do check at this early point
			IStatus status = UserInteractionHelper.checkModify(e, 
					shell == null ? MsgBox.getDefaultShell() : shell);
			if (status.isOK() && e instanceof DescribableElement) {
				ContentDescription presentation = ((DescribableElement) e).getPresentation();
				status = UserInteractionHelper.checkModify(presentation, 
						shell == null ? MsgBox.getDefaultShell() : shell);
			}
			if (!status.isOK()) {
				AuthoringUIPlugin.getDefault().getMsgDialog().displayError(
						AuthoringUIResources.renameDialog_title,
						AuthoringUIResources.renameDialog_renameError,
						status);
				return;
			}
			
			renamedResources = new ArrayList<Resource>();

			// rename file(s)
			//
			Runnable runnable = new Runnable() {
		
				public void run() {
					IStatus status = adjustLocation(renamedResources);
					if(!status.isOK()) {
						rollback();
						Exception ex = (Exception) status.getException();
						throw ex instanceof RuntimeException ? (RuntimeException) ex : new WrappedException(ex);
					}
					
				}
		
			};
		
			boolean result = UserInteractionHelper.runWithProgress(runnable,
					AuthoringUIResources.ElementsView_renaming_text);
			if(!result) {
				return;
			}
			
			status = UserInteractionHelper.checkModify(e, 
					shell == null ? MsgBox.getDefaultShell() : shell);
			if (!status.isOK()) {
				AuthoringUIPlugin.getDefault().getMsgDialog().displayError(
						AuthoringUIResources.renameDialog_title,
						AuthoringUIResources.renameDialog_renameError,
						status);
				UserInteractionHelper.runWithProgress(new Runnable() {

					public void run() {
						rollback();
					}
					
				}, ""); //$NON-NLS-1$
				return;
			}
			
			// Save the modified file(s).
			//
			runnable = new Runnable() {
				
				public void run() {
					// Save the modified file(s).
					//
					ILibraryPersister.FailSafeMethodLibraryPersister persister = getPersister();
					try {
						try {
							persister.save(e.eResource());
							persister.commit();		
						} catch (Exception e1) {		
							try {
								persister.rollback();
							}
							catch(Exception e) {
								AuthoringUIPlugin.getDefault().getLogger().logError(e);
							}
							handlePersistenceException(e1);
						}
					}
					catch(RuntimeException e) {
						rollback();						
						throw e;
					}
					
					// change diagram resource as well
					if (e instanceof ProcessComponent) {
						Process proc = ((ProcessComponent) e).getProcess();	
						DiagramManager mgr = DiagramManager.getInstance(proc, this);
						if(mgr != null) {
							try {
								mgr.updateResourceURI();
							}
							catch(Exception e) {
								AuthoringUIPlugin.getDefault().getLogger().logError(e);
							}
							finally {
								try { 
									mgr.removeConsumer(this);
								}
								catch(Exception e) {
									AuthoringUIPlugin.getDefault().getLogger().logError(e);
								}
							}
						}
						
					}
				}
		
			};
			UserInteractionHelper.runWithProgress(runnable,
					AuthoringUIResources.ElementsView_renaming_text);
		}

		public void redo() {
			execute();
		}
		
		@Override
		protected boolean prepare() {
			return true;
		}
		
		@Override
		public void dispose() {
			e = null;
			super.dispose();
		}
	}
}
