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
package org.eclipse.epf.library.edit.process.command;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.PresentationContext;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IUserInteractionHandler;
import org.eclipse.epf.library.edit.command.MethodElementAddCommand;
import org.eclipse.epf.library.edit.command.UserInput;
import org.eclipse.epf.library.edit.navigator.ProcessPackageItemProvider;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.uma.ScopeFactory;
import org.eclipse.epf.library.edit.util.AdapterFactoryItemLabelProvider;
import org.eclipse.epf.library.edit.util.ExtensionManager;
import org.eclipse.epf.library.edit.util.ItemLabelProvider;
import org.eclipse.epf.library.edit.util.ProcessScopeUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.IValidator;
import org.eclipse.epf.library.edit.validation.IValidatorFactory;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.services.Services;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.Element;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessFamily;
import org.eclipse.epf.uma.ProcessPlanningTemplate;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.ContentDescriptionFactory;
import org.eclipse.epf.uma.util.MessageException;
import org.eclipse.epf.uma.util.Scope;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.osgi.util.NLS;


/**
 * Executes the Create Process Component command.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class CreateProcessComponentCommand extends CreateChildCommand {

	private static final Collection<EClass> ECLASSES = new HashSet<EClass>();

	private Process process;

	private IStatus status;

	static {
		ECLASSES.add(UmaPackage.eINSTANCE.getMethodPackage());
	}

	public static class CompareByName implements Comparator<MethodElement> {

		public int compare(MethodElement obj1, MethodElement obj2) {
			String name1, name2;
			if (PresentationContext.INSTANCE.isShowPresentationNames()) {
				name1 = TngUtil.getPresentationName(obj1);
				name2 = TngUtil.getPresentationName(obj2);
			} else {
				name1 = ((MethodElement) obj1).getName();
				name2 = ((MethodElement) obj2).getName();		
			}
			return name1.compareToIgnoreCase(name2);
		}
	}

	public class ConfigValidator implements IValidator {

		public IStatus isValid(Object obj) {
			if (obj == null) {
				String msg = LibraryEditResources.noDefaultConfigError_msg;
				return new Status(IStatus.ERROR, LibraryEditPlugin.getDefault()
						.getId(), 0, msg, null);
			}
			return Status.OK_STATUS;
		}

		public String isValid(String name) {
			return null;
		}
	}
	/**
	 * Creates a new instance.
	 */
	public CreateProcessComponentCommand(EditingDomain domain, EObject owner,
			EStructuralFeature feature, Object child, int index,
			Collection<?> selection, Helper helper) {
		super(domain, owner, feature, child, index, selection, helper);
	}

	public boolean canUndo() {
		return false;
	}

	public void execute() {
		MethodLibrary lib = UmaUtil.getMethodLibrary(owner);
		
		Object shell = LibraryEditPlugin.getDefault().getContext();
		
		// The owner must be updatable.
		//
		status = UserInteractionHelper.checkModify(owner, shell);
		if (!status.isOK()) {
			return;
		}

		List configs = lib.getPredefinedConfigurations();
		List methodConfigs = new ArrayList();
		
		Scope scope = ScopeFactory.getInstance().newProcessScope(null);
		methodConfigs.add(scope);
		
		for (Iterator iter = configs.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (!(element instanceof ProcessFamily)) {
				methodConfigs.add(element);
			}
		}

		if (methodConfigs.isEmpty()) {
			status = new Status(IStatus.ERROR, LibraryEditPlugin.getDefault().getId(),
								0, LibraryEditResources.noConfigError_msg, null);
//			Messenger.INSTANCE.showError(
//					LibraryEditResources.createProcess_text,
//					LibraryEditResources.noConfigError_msg);			
			return;
		}

		ProcessPackageItemProvider adapter = (ProcessPackageItemProvider) helper;

		ArrayList procClasses = new ArrayList();
		if (adapter.getProcessType() == UmaPackage.eINSTANCE
				.getProcessPlanningTemplate()) {
			procClasses.add(DeliveryProcess.class);
			procClasses.add(CapabilityPattern.class);
		}
		MethodPlugin plugin = UmaUtil.getMethodPlugin((Element) owner);		
		List baseProcList = TngUtil.getAvailableBaseProcesses(plugin,
				procClasses);

		// sort by name
		Collections.sort(methodConfigs, new CompareByName());

		MethodConfiguration[] procCtxs = new MethodConfiguration[methodConfigs
				.size()];
		methodConfigs.toArray(procCtxs);

		Process[] baseProcesses = new Process[baseProcList.size()];
		baseProcList.toArray(baseProcesses);

		final ProcessComponent procComp = (ProcessComponent) child;
		EClass procType = adapter.getProcessType();
		procComp.setProcess((Process) UmaFactory.eINSTANCE.create(procType));
		
		IUserInteractionHandler uiHandler = ExtensionManager.getDefaultUserInteractionHandler();
		List userInputs = new ArrayList();
		IValidator nameValidator = IValidatorFactory.INSTANCE.createNameValidator(
				owner, procComp);
		// name
		UserInput nameInput = new UserInput(LibraryEditResources.nameLabel_text, UserInput.TEXT,
				false, null, null, nameValidator, null);
		userInputs.add(nameInput);
		
		// default configuration
		//
		UserInput defaultConfigInput = new UserInput(LibraryEditResources.defaultConfigLabel_text, UserInput.SELECTION,
				false, methodConfigs, new ItemLabelProvider() {
			public String getText(Object element) {
				if (element instanceof MethodElement) {
					if (PresentationContext.INSTANCE.isShowPresentationNames())
						return TngUtil.getPresentationName(element);
					else return ((MethodElement) element).getName();
				} else {
					return element.toString();
				}
			}
		}, new ConfigValidator(), null);
		userInputs.add(defaultConfigInput);
		// base process
		UserInput baseProcInput = null;
		
		if(procComp.getProcess() instanceof ProcessPlanningTemplate) {
			baseProcInput = new UserInput(LibraryEditResources.basedOnProcessesLabel_text, UserInput.SELECTION,
					true, baseProcList, new AdapterFactoryItemLabelProvider(TngAdapterFactory.INSTANCE
							.getNavigatorView_ComposedAdapterFactory()),  null);
			userInputs.add(baseProcInput);
		}
		
		boolean canExecute = false;
		
//		String msg = NLS.bind(LibraryEditResources.CreateProcessComponentCommand_Message, 
//				TngUtil.getTypeText(procType.getName()).toLowerCase());
		
		String msg = ""; //$NON-NLS-1$
		if (procType.getName().equals("DeliveryProcess")) { //$NON-NLS-1$
			msg = NLS.bind(LibraryEditResources.CreateProcessComponentCommand_Message,
					LibraryEditResources.DeliveryProcessLabel);
		} else if (procType.getName().equals("CapabilityPattern")) { //$NON-NLS-1$
			msg = NLS.bind(LibraryEditResources.CreateProcessComponentCommand_Message,
					LibraryEditResources.CapabilityPatternLabel);
		}		
		
		// request input
		if (uiHandler.requestInput(LibraryEditResources.newProcessComponentDialog_title, 
				msg, userInputs)) {
			String name = (String) nameInput.getInput();
			procComp.setName(name);
			process = procComp.getProcess();
			process.setName(name);
			process.setPresentationName(name);
			process.setDefaultContext((MethodConfiguration) defaultConfigInput.getInput());
			if(baseProcInput != null) {
				List list = (List) baseProcInput.getInput();
				if(!list.isEmpty()) {
					((ProcessPlanningTemplate)procComp).getBasedOnProcesses().addAll(list);
				}
			}
			canExecute = true;
		}

		if (canExecute) {
			// create process component need to update the configuration that
			// has been selected as
			// default context of its process. Check if the configuration file
			// is updatable
			//
			status = UserInteractionHelper.checkModify(procComp.getProcess()
					.getDefaultContext(), shell);
			if (!status.isOK()) {
				return;
			}
			
			super.execute();

			Command cmd = getCommand();
			if (cmd instanceof MethodElementAddCommand) {
				IStatus status = ((MethodElementAddCommand) cmd).getStatus();
				if (status != null && !status.isOK()) {
					this.status = status;
					return;
				}
			}

			final MethodConfiguration procCtx = process.getDefaultContext();
			
			// need to add the parent packages and plugin into the configuration
			// as well
			// New process in new plug-in not automatically visible in
			// configuration view
			List pkgs = procCtx.getMethodPackageSelection();
			for (EObject obj = procComp; obj != null; obj = obj.eContainer()) {
				if (obj instanceof MethodPackage) {
					pkgs.add(obj);
				}
			}
			procCtx.getMethodPluginSelection().add(plugin);
			
			process.getValidContext().add(procCtx);
			
			Scope processScope = ProcessScopeUtil.getInstance().getScope(process);
			if (processScope != null) {
				process.setDefaultContext(null);
				process.getValidContext().clear();
			}
			
			process.setPresentation(ContentDescriptionFactory
					.createContentDescription(process));

			Runnable runnable = new Runnable() {
				public void run() {
					// save the resource of parent ProcessPackage
					Resource resource = ((EObject) owner).eResource();
					if (resource != null) {
						ILibraryPersister.FailSafeMethodLibraryPersister persister = Services.getDefaultLibraryPersister()
								.getFailSafePersister();
						try {
							// save the resource of newly created
							// ProcessComponent again after creating process's
							// presentation
							persister.save(procComp.eResource());

							persister.save(resource);

							// save the resource of the process's default
							// context
							persister.save(procCtx.eResource());

							persister.commit();
							
							// create new diagram file
							//
							
						} catch (Exception e) {
							try {
								persister.rollback();
							} catch (Exception ex) {
								LibraryEditPlugin.INSTANCE.log(ex);
								LibraryEditPlugin.INSTANCE.log(e);
							}
							
							status = Status.CANCEL_STATUS;
							throw new MessageException(
									NLS
											.bind(
													LibraryEditResources.saveProcessError_reason,
													procComp.getName()), e);
						}
					}
				}
			};

			UserInteractionHelper
					.runWithProgress(
							runnable,
							MessageFormat
									.format(
											LibraryEditResources.creatingProcessComponentTask_name,
											new Object[] { procComp.getName() }));
			
			if (processScope != null) {
				process.setDefaultContext(processScope);
				process.getValidContext().add(processScope);
				ProcessScopeUtil.getInstance().addReferenceToScope(
						processScope, process, new HashSet<MethodElement>());
			}
			
		}
	}

	public void redo() {
		super.redo();
		ProcessComponent procComp = (ProcessComponent) child;
		MethodConfiguration procCtx = process.getDefaultContext();
		if (procCtx instanceof MethodConfiguration) {
			((MethodConfiguration) procCtx).getMethodPackageSelection().add(
					procComp);
		}
	}

	/**
	 * @see org.eclipse.emf.edit.command.CreateChildCommand#undo()
	 */
	public void undo() {
		MethodConfiguration procCtx = process.getDefaultContext();
		if (procCtx instanceof MethodConfiguration) {
			((MethodConfiguration) procCtx).getMethodPackageSelection().remove(
					child);
		}
		super.undo();
	}

	public IStatus getStatus() {
		return status;
	}
}
