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
package org.eclipse.epf.library.edit.ui;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.library.edit.IConfigurator;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.Providers;
import org.eclipse.epf.library.edit.command.IUserInteractionHandler;
import org.eclipse.epf.library.edit.command.UserInput;
import org.eclipse.epf.library.edit.util.ExtensionManager;
import org.eclipse.epf.library.edit.util.IRunnableWithProgress;
import org.eclipse.epf.library.edit.util.Messenger;
import org.eclipse.epf.library.edit.util.ProcessScopeUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.AbstractStringValidator;
import org.eclipse.epf.library.edit.validation.IValidator;
import org.eclipse.epf.services.IAccessController;
import org.eclipse.epf.services.Services;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Deliverable;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.NamedElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.ecore.impl.MultiResourceEObject;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;
import org.eclipse.epf.uma.util.Scope;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.osgi.util.NLS;

/**
 * Defines static methods that interact with user via dialog boxes to retrieve
 * more information or confirmation from the users.
 * 
 * @author Phong Nguyen Le
 * @author Shilpa Toraskar
 * @since 1.0
 */
public final class UserInteractionHelper {
	
	private static final boolean canInteract = true;
	
	private static final IUIHelper uiHelper = (IUIHelper) ExtensionManager.getExtension(
			LibraryEditPlugin.getDefault().getId(), "uiHelper"); //$NON-NLS-1$

	private UserInteractionHelper() {
		super();
	}

	public static boolean canInteract() {
		return canInteract;
	}

	/**
	 * Checks against default configuration of the given process whether the
	 * given (method) object can be used in the process. This method will prompt
	 * user to add the object and those elements that it is associated with to
	 * the default configuration if the default configuration does not have them
	 * yet.
	 * 
	 * @param proc
	 *            the process
	 * @param object
	 *            the method object
	 * @return
	 *            <li>1 if the process'es default configuration already has the
	 *            object
	 *            <li>2 if the object is not in the configuration, but user
	 *            wants to add it now
	 *            <li>0 if the object is not in the configuration and user
	 *            don't want to add it
	 *            <li>-1 if user cancel the prompt to add the objects and
	 *            associated dependencies to the default configuration
	 */
	public static int checkAgainstDefaultConfiguration(Process proc,
			Object object) {
		return checkAgainstDefaultConfiguration(proc, object, null);
	}

	/**
	 * Checks against default configuration of the given process whether the
	 * given (method) object can be used in the process. This method will prompt
	 * user to add the object and those elements that it is associated with to
	 * the default configuration if the default configuration does not have them
	 * yet.
	 * 
	 * @param proc
	 *            the process
	 * @param object
	 *            the method object
	 * @return
	 *            <li>1 if the process'es default configuration already has the
	 *            object
	 *            <li>2 if the object is not in the configuration, but user
	 *            wants to add it now
	 *            <li>0 if the object is not in the configuration and user
	 *            don't want to add it
	 *            <li>-1 if user cancel the prompt to add the objects and
	 *            associated dependencies to the default configuration
	 */
	public static int checkAgainstDefaultConfiguration(Process proc,
			Object object, IConfigurator configurator) {
		Object e = TngUtil.unwrap(object);
		if (!(e instanceof MethodElement))
			return 0;

		Scope scope = ProcessScopeUtil.getInstance().getScope(proc);
		if (scope != null) {
			ProcessScopeUtil.getInstance().addReferenceToScope(
					scope, (MethodElement) e, new HashSet<MethodElement>());
			return 1;
		}
		
		if (configurator == null)
			configurator = Providers.getConfiguratorFactory()
					.createConfigurator(proc.getDefaultContext());
		if (e instanceof VariabilityElement) {
			// check all the base elements if there is any
			//
			boolean allIn = true;
			for (VariabilityElement c = ((VariabilityElement) object); c != null; c = (VariabilityElement) c
					.getVariabilityBasedOnElement()) {
				if (!configurator.accept(c)) {
					allIn = false;
					break;
				}
			}
			if (allIn) {
				return 1;
			}
		}
		if (configurator.accept(e))
			return 1;

		// object is not in the configuration
		// ask user if he/she want to add it to the default configuration
		//
		String msg = NLS
				.bind(
						LibraryEditResources.ui_UserInteractionHelper_defaultconfigcheck,
						((MethodElement) e).getName());		
		IUserInteractionHandler uiHandler = ExtensionManager.getDefaultUserInteractionHandler();
		if(uiHandler != null) {
			int ret = uiHandler.selectOne(new int[] {
					IUserInteractionHandler.ACTION_YES,
					IUserInteractionHandler.ACTION_NO,
					IUserInteractionHandler.ACTION_CANCEL
				}, LibraryEditResources.add_to_default_config_dlg_title, msg, null);
			if (TngUtil.DEBUG) {
				System.out
				.println("UserInteractionHelper.checkAgainstDefaultConfiguration(): element=" //$NON-NLS-1$
						+ e + ", path=" + TngUtil.getLabelWithPath(e)); //$NON-NLS-1$
			}
			switch (ret) {
			case IUserInteractionHandler.ACTION_YES:
				IStatus status = TngUtil.checkEdit(proc.getDefaultContext(), null);
				if (!status.isOK()) {
					return 0;
				}
				return 2;
			case IUserInteractionHandler.ACTION_NO:
				return 0;
			case IUserInteractionHandler.ACTION_CANCEL:
				return -1;
			}
		}
		return 0;
	}

	/**
	 * Select tasks which has given workproduct as output.
	 * 
	 * @param taskList
	 * @param wp
	 * @return
	 */
	public static List selectTasks(List taskList, WorkProduct wp) {
		return uiHelper.selectTasks(taskList, wp);
	}

	/**
	 * Select responsible work products for the given role
	 * 
	 * @param wpList
	 * @param role
	 * @return
	 */
	public static List selectWorkProducts(List wpList, Role role) {
		return uiHelper.selectWorkProducts(wpList, role);
	}

	/**
	 * Requests name from user
	 * 
	 * @param object
	 * @param nameFeature
	 * @return the requested name of null if user canceled
	 */
	public static String requestName(Object child,
			EStructuralFeature nameFeature, String title,
			final IValidator validator) {
		IValidator inputValidator = new AbstractStringValidator() {

			public String isValid(String newText) {
				if (validator != null) {
					return getSimpleErrorMessage(validator.isValid(newText));
				}
				return null;
			}

		};
		String name = ""; //$NON-NLS-1$
		if (child instanceof EObject) {
			EObject e = (EObject) child;
			String str = (String) e.eGet(nameFeature);
			if (str != null) {
				name = str;
			}
		}
		IUserInteractionHandler uiHandler = ExtensionManager.getDefaultUserInteractionHandler();
		if(uiHandler == null) {
			return null;
		}
		UserInput input = new UserInput("", UserInput.TEXT, false, null, null, inputValidator, null); //$NON-NLS-1$
		input.setInput(name);
		boolean ret = uiHandler.requestInput(title, LibraryEditResources.UserInteractionHelper_ProcessPackage_Name, 
			Collections.singletonList(input));
		if(ret) {
			return input.getInput().toString().trim();
		}
		return null;
	}

	public static String getSimpleErrorMessage(String msg) {
		if (msg == null)
			return null;

		int s = msg.indexOf(':');

		String prefix = ""; //$NON-NLS-1$
		if (s >= 0) {
			prefix = msg.substring(0, s);

			String emptyElementNameErrorMsg = LibraryEditResources.emptyElementNameError_msg; 
			String dupContentFileErrorMsg = LibraryEditResources.duplicateContentFileError_msg; 
			String dupElementNameErrorMsg = LibraryEditResources.duplicateElementNameError_msg; 

			if (emptyElementNameErrorMsg != null
					&& emptyElementNameErrorMsg.startsWith(prefix))
				return LibraryEditResources.emptyElementNameError_simple_msg; 
			else if (dupContentFileErrorMsg != null
					&& dupContentFileErrorMsg.startsWith(prefix))
				return LibraryEditResources.duplicateContentFileError_simple_msg; 
			else if (dupElementNameErrorMsg != null
					&& dupElementNameErrorMsg.startsWith(prefix))
				return LibraryEditResources.duplicateElementNameError_simple_msg; 
		}

		if (s < 0)
			s = 0;
		else
			s++;
		int t = msg.indexOf('\n');
		if (t < 0)
			t = msg.length();

		return msg.substring(s, t).trim();
	}

	/**
	 * Return team in which user would automatically assign a role into
	 * 
	 * @param activity
	 * @param role
	 * @return
	 * 
	 */
	public static TeamProfile getTeam(Activity activity, Role role) {
		return getTeam(activity, role, null);
	}

	public static TeamProfile getTeam(Activity activity, Role role,
			Object UIContext) {
		return uiHelper.getTeam(activity, role, UIContext);
	}

	public static final boolean runWithProgress(final Runnable runnable,
			final String msg) {
		final IRunnableWithProgress operation = new IRunnableWithProgress() {

			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				runnable.run();
			}

		};
		
		return runWithProgress(operation, msg);
	}

	public static final boolean runWithProgress(
			final IRunnableWithProgress runnable, final String msg) {
		return runWithProgress(runnable, false, msg);
	}

	public static final boolean runWithProgress(
			final IRunnableWithProgress runnable, final boolean canCancel,
			final String msg) {
		return uiHelper.runWithProgress(runnable, canCancel, msg);
	}

	public static final IStatus runAsJob(IRunnableWithProgress runnable,
			String taskName) {
		Object shell = LibraryEditPlugin.getDefault().getContext();
		if (shell == null) {
			try {
				runnable.run(new NullProgressMonitor());
				return Status.OK_STATUS;
			} catch (Exception e) {
				return new Status(IStatus.ERROR, LibraryEditPlugin.getPlugin()
						.getId(), 0, e.toString(), e);
			}
		} else {
			return runAsJob(runnable, taskName, shell);
		}
	}

	public static final IStatus runAsJob(final IRunnableWithProgress runnable,
			final String taskName, Object shell) {
		return uiHelper.runAsJob(runnable, taskName, shell);
	}
	
	public static final boolean runInUI(final Runnable runnable,
			final String taskName) {
		return runInUI(runnable, taskName, null);
	}

	public static final boolean runInUI(final Runnable runnable,
			final String taskName, ISchedulingRule rule) {
		Object shell = LibraryEditPlugin.getDefault().getContext();
		if (shell == null) {
			runnable.run();
			return true;
		} else {
			IRunnableWithProgress runner = new IRunnableWithProgress() {

				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {
					try {
						monitor.beginTask(taskName, 2);
						monitor.worked(1);
						runnable.run();
					} finally {
						monitor.done();
					}
				}

			};
			return runInUI(runner, rule, shell);
		}
	}

	public static final boolean runInUI(IRunnableWithProgress runnable,
			Object shell) {
		return runInUI(runnable, null, shell);
	}

	public static final boolean runInUI(IRunnableWithProgress runnable,
			ISchedulingRule rule, Object shell) {
		return uiHelper.runInUI(runnable, rule, shell);
	}

	/**
	 * Return Deliverable in which user would automatically assign a wp into
	 * 
	 * @param activity
	 * @param wp
	 * @return
	 * 
	 */
	public static WorkProductDescriptor getDeliverable(Activity activity,
			WorkProduct wp) {

		// PLEAE DON'T CLEAN UP
		// commented out for now since we shut-off automatic assignment
		// of deliverable for now
		// List deliverableList = new ArrayList();
		// AdapterFactory adapterFactory = TngAdapterFactory.INSTANCE
		// .getPBS_ComposedAdapterFactory();
		// // find out all deliverables in visible scope
		// getDeliverablesInScope(adapterFactory, activity, wp,
		// deliverableList);
		// if (deliverableList.size() == 1) {
		// return (WorkProductDescriptor) deliverableList.get(0);
		// }
		// if (deliverableList.size() > 1) {
		// return DeliverableSelection.getSelectedDeliverable(deliverableList,
		// wp);
		// }
		// there are no deliverable to assign
		return null;
	}

	/**
	 * PLEASE DON'T CLEAN UP. This method is currently not used since we
	 * shut-off automatic assignment of deliverable. Get deliverable in scope
	 * 
	 * @param adapterFactory
	 * @param e
	 * @param WorkProductDescriptor
	 * @param deliverableList
	 */
	private static void getDeliverablesInScope(AdapterFactory adapterFactory,
			BreakdownElement e, WorkProduct wp, List deliverableList) {
		// get children for activity
		ITreeItemContentProvider itemProvider = (ITreeItemContentProvider) adapterFactory
				.adapt(e, ITreeItemContentProvider.class);
		Collection children = itemProvider.getChildren(e);
		for (Iterator itor = children.iterator(); itor.hasNext();) {
			Object obj = itor.next();
			if ((obj instanceof WorkProductDescriptor)
					&& (ProcessUtil
							.getAssociatedElement((WorkProductDescriptor) obj) instanceof Deliverable)) {
				WorkProductDescriptor wpDesc = (WorkProductDescriptor) obj;

				// get deliverable parts from deliverable
				List parts = ProcessUtil.getAssociatedElementList(wpDesc
						.getDeliverableParts());
				if (parts.contains(wp)) {
					deliverableList.add(obj);
				}
			}
		}

		// get parent
		Object currentParent = itemProvider.getParent(e);
		if (currentParent != null) {
			// go up
			getDeliverablesInScope(adapterFactory,
					(BreakdownElement) currentParent, wp, deliverableList);
		}
	}

	/**
	 * Checks if the given element can be modified. This includes lock check and
	 * edit check.
	 * 
	 * @param element
	 * @param shell
	 * @return
	 */
	public static IStatus checkModify(EObject element, Object shell) {
		if (TngUtil.isLocked(element)) {
			String msg = MessageFormat
					.format(
							LibraryEditResources.UserInteractionHelper_lockedPlugin,
							new Object[] { UmaUtil.getMethodPlugin(element)
									.getName() }); 
			return new Status(IStatus.ERROR,
					LibraryEditPlugin.INSTANCE.getId(), 0, msg, null);
		}
		return TngUtil.checkEdit((EObject) element, shell);
	}

	/**
	 * Checks for unmodifiable resources.
	 * 
	 * @param modifiedResources
	 *            A collection of resources.
	 * @param context
	 *            The context of this call. For RCP, this is the shell.
	 * @return An <code>IStatus</code> object.
	 */
	public static IStatus checkModify(Collection modifiedResources, Object context) {
		IAccessController ac = Services.getAccessController();
		if (ac == null) {
			return Status.OK_STATUS;
		}
		Resource[] resources = new Resource[modifiedResources.size()];
		modifiedResources.toArray(resources);
		return ac.checkModify(resources, context);
	}

	public static IResource getWorkspaceResource(Resource resource) {
		if (!resource.getURI().isFile()) {
			return null;
		}
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IPath path = new Path(resource.getURI().toFileString());
		return workspaceRoot.getFileForLocation(path);
	}

	public static boolean checkOutOfSynch(Collection resources) {
		ArrayList outOfSynchResources = new ArrayList();
		for (Iterator iter = resources.iterator(); iter.hasNext();) {
			Resource resource = (Resource) iter.next();
			IResource wsResource = getWorkspaceResource(resource);
			if (wsResource != null
					&& !wsResource.isSynchronized(IResource.DEPTH_ZERO)) {
				outOfSynchResources.add(wsResource);
			}
		}
		if (outOfSynchResources.isEmpty()) {
			return true;
		} else {
			MultiStatus multiStatus = new MultiStatus(
					LibraryEditPlugin.INSTANCE.getSymbolicName(), IStatus.OK,
					"", null); //$NON-NLS-1$
			for (Iterator iter = outOfSynchResources.iterator(); iter.hasNext();) {
				IResource wsResource = (IResource) iter.next();
				IStatus status = new Status(IStatus.INFO,
						LibraryEditPlugin.INSTANCE.getSymbolicName(),
						IStatus.OK, wsResource.getLocation().toOSString(), null);
				multiStatus.add(status);
			}
			String title = LibraryEditResources.update_outofsynch_title;
			String msg = LibraryEditResources.update_outofsynch_msg;

			IUserInteractionHandler uiHandler = ExtensionManager.getDefaultUserInteractionHandler();
			return uiHandler.selectOne(new int[] {IUserInteractionHandler.ACTION_OK,
					IUserInteractionHandler.ACTION_CANCEL}, title, msg, multiStatus) != IUserInteractionHandler.ACTION_CANCEL;
		}
	}

	/**
	 * Check if change to the given feature of the given featureOwner will
	 * modify the element in opposite feature value
	 * 
	 * @param featureOwner
	 * @param feature
	 * @param element
	 * @return
	 */
	public static boolean checkModifyOpposite(MethodElement featureOwner,
			EStructuralFeature feature, MethodElement element) {
		OppositeFeature oppositeFeature = OppositeFeature
				.getOppositeFeature(feature);
		MultiResourceEObject mrEObj = (MultiResourceEObject) element;
		if (oppositeFeature != null && !oppositeFeature.isMany()) {
			NamedElement oldOppositeFeatureValue = (NamedElement) mrEObj
					.getOppositeFeatureMap().get(oppositeFeature);
			if (oldOppositeFeatureValue != null
					// make sure the element is still in the library and not
					// deleted yet.
					//
					&& (oldOppositeFeatureValue instanceof MethodLibrary || oldOppositeFeatureValue
							.eContainer() != null)
					&& oldOppositeFeatureValue.eResource() != null) {
				// simple reject for 7.0.0 release
				//
				String msg = MessageFormat
						.format(
								LibraryEditResources.UserInteractionHelper_errRelationshipExists,
								new Object[] {
										element.getName(),
										TngUtil
												.getLabelWithPath(oldOppositeFeatureValue),
										featureOwner.getName() });
				Messenger.INSTANCE.showWarning(
						LibraryEditResources.errorDialog_title, msg);
				return false;

				// TODO: uncomment to use this code for 7.0.1 release
				//
				// String title = "Update Relationship";
				// String msg = MessageFormat.format("Adding ''{0}'' to ''{1}''
				// will remove ''{0}'' from ''{2}''. Do you want to continue?"
				// , new Object[] { element.getName(), featureOwner.getName(),
				// oldOppositeFeatureValue.getName() });
				// if(!LibraryEditPlugin.INSTANCE.getMsgDialog().displayConfirmation(title,
				// msg)) {
				// return false;
				// }
				//				
				// IStatus status =
				// UserInteractionHelper.checkModify(oldOppositeFeatureValue,
				// null);
				// if(!status.isOK()) {
				// LibraryEditPlugin.INSTANCE.getMsgDialog().displayError(title,
				// "Cannot update relationship", status);
				// return false;
				// }
			}
		}

		return true;
	}

	/**
	 * Checks if
	 * 
	 * @return
	 */
	public static IStatus checkConfigurationsToUpdate(AddCommand addCommand,
			Object shell) {
		//wlu0 9-12-2012: we are now using "loadCheckPkgs" property mechanism to handle adding to configuration. No need to checkModify here.
		//				  Therefore, directly return ok stat.	
		if (true) {
			return Status.OK_STATUS;
		}
		
		EObject parent = addCommand.getOwner();
		if (!(parent instanceof MethodPackage)) {
			return Status.OK_STATUS;
		}
		EStructuralFeature feature = addCommand.getFeature();
		if (!(feature instanceof EReference)
				|| !((EReference) feature).isContainment()) {
			return Status.OK_STATUS;
		}

		// check if the configurations that will be updated after this command
		// can be modified
		//
		ArrayList configsToUpdate = new ArrayList();
		MethodPackage parentPkg = (MethodPackage) parent;
		for (Iterator iter = addCommand.getCollection().iterator(); iter
				.hasNext();) {
			Object element = iter.next();
			if (element instanceof MethodPackage) {
				TngUtil.getConfigurationsToUpdate(parentPkg,
						(MethodPackage) element, configsToUpdate);
			}
		}
		if (!configsToUpdate.isEmpty()) {
			Collection resourcesToCheck = new HashSet();
			for (Iterator iter = configsToUpdate.iterator(); iter.hasNext();) {
				EObject config = (EObject) iter.next();
				Resource resource = config.eResource();
				if (resource != null) {
					resourcesToCheck.add(resource);
				}
			}
			// check affected resources for unmodifiable
			return UserInteractionHelper.checkModify(resourcesToCheck, shell);
		}
		return Status.OK_STATUS;
	}

	public static boolean confirmDeepCopy(Collection activities) {
		if (UserInteractionHelper.canInteract()) {
			// checks if the pattern or activity contains any extending elements
			// and prompts the user if he really
			// wants to deep copy dynamically linked activities warning the
			// process author that he will get a copy
			// of all dynamically linked elements that he needs to maintain
			// separately from now on
			boolean promptNeeded = false;
			for (Iterator iter = activities.iterator(); iter.hasNext();) {
				Object e = (Object) iter.next();
				if (e instanceof Activity
						&& ProcessUtil.hasInherited((Activity) e)) {
					promptNeeded = true;
					break;
				}
			}
			if (promptNeeded) {
				String title = LibraryEditResources.deepCopy_title;
				String msg = LibraryEditResources.deepCopy_promptMsg;
				int ret = ExtensionManager.getDefaultUserInteractionHandler()
					.selectOne(new int[] {
							IUserInteractionHandler.ACTION_YES,
							IUserInteractionHandler.ACTION_NO
					}, title, msg, null);
				if (ret == IUserInteractionHandler.ACTION_NO) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Prompts user to choose configuration for deep copy
	 * 
	 * @param targetProcess
	 * @param adapterFactory
	 * @return
	 * @exception OperationCanceledException
	 *                if user cancelled
	 */
	public static MethodConfiguration chooseDeepCopyConfiguration(
			Process targetProcess, AdapterFactory adapterFactory) {
		IFilter filter = ProcessUtil.getFilter(adapterFactory);
		MethodConfiguration deepCopyConfig = null;
		if (filter instanceof IConfigurator) {
			MethodConfiguration currentConfig = ((IConfigurator) filter)
					.getMethodConfiguration();
			if (currentConfig != null
					&& currentConfig != targetProcess.getDefaultContext()) {
				if (UserInteractionHelper.canInteract()) {
					String msg = LibraryEditResources.ActivityDropCommand_deepCopy_promptConfigurationMsg;
					IUserInteractionHandler uiHandler = ExtensionManager.getDefaultUserInteractionHandler();
					if(uiHandler != null) {
						int ret = uiHandler.selectOne(new int[] {
								IUserInteractionHandler.ACTION_YES,
								IUserInteractionHandler.ACTION_NO,
								IUserInteractionHandler.ACTION_CANCEL
							}, LibraryEditResources.deepCopy_title, msg, null);						
						switch (ret) {
						case IUserInteractionHandler.ACTION_YES:
							break;
						case IUserInteractionHandler.ACTION_NO:
							deepCopyConfig = currentConfig;
							break;
						case IUserInteractionHandler.ACTION_CANCEL:
							throw new OperationCanceledException();
						}
					}
				}
			}
		}
		return deepCopyConfig;
	}
	
	public static boolean copyExternalVariationsAllowed(Process targetProcess, AdapterFactory adapterFactory) {
		// ask users to copy external contribution(s)/replacement(s) if
		// current config is not the default config of the target process
		//
		IFilter filter = ProcessUtil.getFilter(adapterFactory);
		if (filter instanceof IConfigurator) {
			MethodConfiguration currentConfig = ((IConfigurator) filter)
					.getMethodConfiguration();
			if (currentConfig != null
					&& currentConfig != targetProcess.getDefaultContext()) {				
				String msg = LibraryEditResources.activity_deep_copy_variability_prompt;
				IUserInteractionHandler uiHandler = ExtensionManager.getDefaultUserInteractionHandler();
				if(uiHandler != null) {
					int ret = uiHandler.selectOne(new int[] {
							IUserInteractionHandler.ACTION_YES,
							IUserInteractionHandler.ACTION_NO,
							IUserInteractionHandler.ACTION_CANCEL
						}, LibraryEditResources.deepCopy_title, msg, null);						
					switch (ret) {
					case IUserInteractionHandler.ACTION_YES:
						return true;
					case IUserInteractionHandler.ACTION_NO:
						return false;
					case IUserInteractionHandler.ACTION_CANCEL:
						throw new OperationCanceledException();
					}
				}				
			}
		}
		return true;
	}

	public static IUIHelper getUIHelper() {
		return uiHelper;
	}
}