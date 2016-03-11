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
package org.eclipse.epf.diagram.core.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.CopyCommand.Helper;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.epf.common.CommonPlugin;
import org.eclipse.epf.diagram.core.DiagramCorePlugin;
import org.eclipse.epf.diagram.core.part.DiagramEditorInput;
import org.eclipse.epf.diagram.core.part.DiagramEditorInputProxy;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.edit.command.DeleteMethodElementCommand;
import org.eclipse.epf.library.edit.command.INestedCommandProvider;
import org.eclipse.epf.library.edit.command.MethodElementAddCommand;
import org.eclipse.epf.library.edit.command.ResourceAwareCompoundCommand;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.command.ActivityDropCommand;
import org.eclipse.epf.library.edit.process.command.CopyHelper;
import org.eclipse.epf.library.edit.process.command.ProcessDeepCopyCommand;
import org.eclipse.epf.library.edit.ui.IActionTypeProvider;
import org.eclipse.epf.library.edit.util.ActivityHandler;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.edit.domain.TraceableAdapterFactoryEditingDomain;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * @author Shilpa Toraskar
 * @author Phong Nguyen Le
 * @since 1.2
 */
public class NestedCommandProvider implements INestedCommandProvider {

	private static final boolean DEBUG = DiagramCorePlugin.getDefault()
			.isDebugging();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.command.INestedCommandProvider#createNestedCommand(org.eclipse.emf.common.command.Command)
	 */
	public Command createNestedCommand(Command command) {
		if (command instanceof ActivityDropCommand) {
			ActivityDropCommand cmd = (ActivityDropCommand) command;
			if (IActionTypeProvider.COPY == cmd.getType())
				return createNestedCommandForCopy(cmd);
			else if (IActionTypeProvider.DEEP_COPY == cmd.getType())
				return createNestedCommandForDeepCopy(cmd);
		}
		else if (command instanceof ProcessDeepCopyCommand) {
			ProcessDeepCopyCommand cmd = (ProcessDeepCopyCommand) command;
			Process deepCopy = null;
			Collection<?> result = cmd.getResult();
			if(!result.isEmpty()) {
				deepCopy = (Process) result.iterator().next();
			}
			if(deepCopy != null) {
				Collection<Activity> deepCopies = new ArrayList<Activity>();
				deepCopies.add(deepCopy);
				return createNestedCommandForDeepCopy(deepCopies, cmd.getTargetProcess(), 
						cmd.getCopyHelper(), cmd.getMethodConfiguration(), true);
			}
		}
		return null;
	}

	/**
	 * Create nested command for Drag & Drop Copy
	 * 
	 * @param command
	 * @return
	 */
	private Command createNestedCommandForCopy(ActivityDropCommand command) {
		Collection<Resource> modifiedResources = ((ActivityDropCommand) command).getModifiedResources(); 
		if (modifiedResources != null && !modifiedResources.isEmpty()) {
			CompoundCommand cmd = new ResourceAwareCompoundCommand(
					CompoundCommand.MERGE_COMMAND_ALL);

			Helper copiedHelper = command.getActivityHandler().getCopyHelper();
			Activity targetActivity = command.getActivity();
			Process targetProc = TngUtil.getOwningProcess(targetActivity);
			if (targetProc != null) {
				try {
					cmd.append(new CopyDiagramCommand(copiedHelper.values(),
							copiedHelper, targetProc));
				
				} catch (Exception ex) {
					CommonPlugin.getDefault().getLogger().logError(ex);
					if (DEBUG) {
						ex.printStackTrace();
					}
				} finally {
	
				}
			}
			if (!cmd.isEmpty()) {
				return cmd;
			}
		}
		return null;
	}
	
	/**
	 * Created nested command for Drag and Drop Deep copy
	 * 
	 * @param command
	 * @return
	 */
	private Command createNestedCommandForDeepCopy(ActivityDropCommand command) {
		ActivityHandler activityHandler = ((ActivityDropCommand) command)
				.getActivityHandler();
		if (activityHandler != null
				&& !activityHandler.getDeepCopies().isEmpty()) {
			return createNestedCommandForDeepCopy(activityHandler
					.getDeepCopies(), activityHandler.getTargetProcess(),
					activityHandler.getDeepCopyHelper(), activityHandler.getDeepCopyConfig(), false);
		}
		return null;
	}
	
	private Command createNestedCommandForDeepCopy(
			Collection<Activity> deepCopies, final Process targetProc,
			CopyHelper copyHelper, MethodConfiguration config,
			boolean saveDiagram) {
		CompoundCommand cmd;
		if (saveDiagram) {
			cmd = new ResourceAwareCompoundCommand(
					CompoundCommand.MERGE_COMMAND_ALL) {
				@Override
				public void execute() {
					super.execute();

					// save diagram file
					//
					if (org.eclipse.epf.diagram.core.services.DiagramManager
							.hasDiagramManager(targetProc)) {
						org.eclipse.epf.diagram.core.services.DiagramManager mgr = org.eclipse.epf.diagram.core.services.DiagramManager
								.getInstance(targetProc, this);
						ILibraryPersister.FailSafeMethodLibraryPersister persister = LibraryServiceUtil
								.getPersisterFor(targetProc.eResource())
								.getFailSafePersister();
						try {
							Resource resource = mgr.getResource();
							persister.save(resource);
							persister.commit();
						} catch (Exception e) {
							DiagramCorePlugin.getDefault().getLogger()
									.logError(e);
							try {
								persister.rollback();
							} catch (Exception ex) {
								DiagramCorePlugin.getDefault().getLogger()
										.logError(ex);
							}
						} finally {
							mgr.removeConsumer(this);
						}
					}
				}
			};
		} else {
			cmd = new ResourceAwareCompoundCommand(
					CompoundCommand.MERGE_COMMAND_ALL);
		}
		for (Activity copyAct : deepCopies) {
			Activity origAct = (Activity) copyHelper.getOriginal(copyAct);
			Process srcProc = TngUtil.getOwningProcess(origAct);
			Map<Activity, Activity> copyToOriginalMap = new HashMap<Activity, Activity>();
			Iterator<BreakdownElement> copyTree = new AbstractTreeIterator<BreakdownElement>(
					copyAct) {

				@Override
				protected Iterator<? extends BreakdownElement> getChildren(
						Object object) {
					if (object instanceof Activity) {
						return ((Activity) object).getBreakdownElements()
								.iterator();
					}
					return Collections.EMPTY_LIST.iterator();
				}

			};
			while (copyTree.hasNext()) {
				BreakdownElement e = copyTree.next();
				if (e instanceof Activity) {
					Object orig = copyHelper.getOriginal(e);
					if (orig == null) {
						System.out
								.println("NestedCommandProvider.createNestedCommandForDeepCopy(): FATAL ERROR: could not find source activity of deep copy: " + e); //$NON-NLS-1$
					} else {
						copyToOriginalMap.put((Activity) e, (Activity) orig);
					}
				}
			}
			cmd.append(new CopyDiagramForDeepCopyCommand(copyToOriginalMap.keySet(),
					copyToOriginalMap, srcProc, targetProc, copyHelper, config));
		}
		return !cmd.isEmpty() ? cmd : null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.command.INestedCommandProvider#createRelatedObjects(java.util.Collection,
	 *      org.eclipse.emf.common.command.Command)
	 */
	public Command createRelatedObjects(Collection createdElements,
			Command createCommand) {

		CompoundCommand cmd = new ResourceAwareCompoundCommand(
				CompoundCommand.MERGE_COMMAND_ALL);
		if (createCommand instanceof MethodElementAddCommand) {

			MethodElementAddCommand meCommand = ((MethodElementAddCommand) createCommand);
			Command addCommand = meCommand.getCommand();
			if (addCommand instanceof AddCommand) {
				EditingDomain ed = ((AddCommand) addCommand).getDomain();
				
				if (((AddCommand)addCommand).getOwner() instanceof ProcessPackage || ((AddCommand)addCommand).getOwner() instanceof MethodLibrary) {
					if (ed instanceof TraceableAdapterFactoryEditingDomain) {
						Map copyToOriginalMap = ((TraceableAdapterFactoryEditingDomain) ed)
								.getCopyToOriginalMap();
						if (!copyToOriginalMap.isEmpty()) {
							Collection affects = meCommand.getAffectedObjects();
							for (Iterator iter = affects.iterator(); iter
									.hasNext();) {
								MethodElement element = (MethodElement) iter
										.next();
								
								if (element instanceof MethodPlugin) {
									for (Process proc : TngUtil
											.getAllProcesses(((MethodPlugin) element))) {
										cmd.append(new CopyDiagramCommand(
												copyToOriginalMap.keySet(),
												copyToOriginalMap, proc));
									}
								}
								
								if (element instanceof ProcessComponent) {
									cmd.append(new CopyDiagramCommand(
											copyToOriginalMap.keySet(),
											copyToOriginalMap,
											((ProcessComponent) element)
													.getProcess()));
								}
							}
						}
					}
				} else {
					// Copy/paste within the process editor
					if (ed instanceof TraceableAdapterFactoryEditingDomain) {
						Map originalToCopyMap = ((TraceableAdapterFactoryEditingDomain) ed)
								.getOriginalToClipboardMap();
						Map copyToOriginalMap = new HashMap();
						Object owner = ((AddCommand) addCommand).getOwner();
						Process targetProc = TngUtil.getOwningProcess(owner);
						if (targetProc != null) {
							for (Iterator iter = originalToCopyMap.keySet()
									.iterator(); iter.hasNext();) {
								Object key = iter.next();
								Object val = originalToCopyMap.get(key);
	
								copyToOriginalMap.put(val, key);
							}
	
							cmd.append(new CopyDiagramCommand(copyToOriginalMap.keySet(),
									copyToOriginalMap, targetProc));
						}
					}
				}
			}
		}
		if (!cmd.isEmpty()) {
			return cmd;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.command.INestedCommandProvider#removeRelatedObjects(java.util.Collection,
	 *      org.eclipse.emf.common.command.Command)
	 */
	public Command removeRelatedObjects(Collection deletedElements,
			Command deleteCommand) {
		CompoundCommand cmd = new ResourceAwareCompoundCommand(
				CompoundCommand.MERGE_COMMAND_ALL);
		if(deleteCommand instanceof DeleteMethodElementCommand){
			Command removeCommand = ((DeleteMethodElementCommand)deleteCommand).getCommand();
			List<RemoveCommand> removeCommands = null;
			if(removeCommand instanceof RemoveCommand){
				removeCommands = Collections.singletonList((RemoveCommand)removeCommand);
			}
			else if(removeCommand instanceof CompoundCommand) {
				removeCommands = new ArrayList<RemoveCommand>();
				for (Command command : ((CompoundCommand)removeCommand).getCommandList()) {
					if(command instanceof RemoveCommand) {
						removeCommands.add((RemoveCommand) command);
					}
				}
			}
			if(removeCommands != null && !removeCommands.isEmpty()) {
				Object owner = removeCommands.get(0).getOwner();
				if(owner instanceof Activity) {
					Process process = TngUtil.getOwningProcess((Activity)owner);
					Collection<Activity> allElements = new HashSet<Activity>();
					for (Iterator iter = deletedElements.iterator(); iter
					.hasNext();) {
						Object element = iter.next();
						if (element instanceof Activity) {
							Iterator iterator = new AbstractTreeIterator<Object>(element) {

								private static final long serialVersionUID = 1L;

								@Override
								protected Iterator<? extends Object> getChildren(
										Object object) {
									if(object instanceof Activity) {
										return ((Activity)object).getBreakdownElements().iterator();
									}
									else {
										return Collections.emptyList().iterator();
									}
								}

							};
							while(iterator.hasNext()) {
								Object e = iterator.next();
								if(e instanceof Activity) {
									allElements.add((Activity) e);
								}
							}
						} 
					}					
					if(!allElements.isEmpty()){
						cmd.append(new DeleteDiagramCommand(allElements, process));
					}

					for (RemoveCommand command : removeCommands) {
						if(command.getOwner() instanceof Activity) {
							DeleteDiagramNodeCommand deleteDiagramNodeCommand = new DeleteDiagramNodeCommand(
									(Activity) command.getOwner(), command.getCollection());
							if(deleteDiagramNodeCommand.canExecute()) {
								cmd.append(deleteDiagramNodeCommand);
							}
						}
					}
				}
			}
		}
		if(!deletedElements.isEmpty()){
			for (Iterator iter = deletedElements.iterator(); iter.hasNext();) {
				Object element = (Object) iter.next();
				closeDiagramEditors(element);
			}
		}
		if(!cmd.isEmpty()){
			return cmd;
		}
		return null;
	}
	
	/**
	 * Closes Diagram Editors if the owning process is deleted.
	 * @param process
	 */
	private void closeDiagramEditors(Object e) {
		IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if(activeWorkbenchWindow == null) {
			return;
		}
		IWorkbenchPage workbenchPage = activeWorkbenchWindow.getActivePage();
		IEditorReference[] editorReferences = workbenchPage
				.getEditorReferences();
		ArrayList<IEditorReference> editorsToClose = new ArrayList<IEditorReference>();
		for (int i = 0; i < editorReferences.length; i++) {
			IEditorReference editorRef = editorReferences[i];
			IEditorInput editorInput;
			try {
				editorInput = editorRef.getEditorInput();
				if (editorInput instanceof DiagramEditorInputProxy) {
					DiagramEditorInput input = ((DiagramEditorInputProxy) editorInput)
							.getDiagramEditorInput();
					Object wrapper = input.getWrapper();
					Process owningProcess = null;
					if (wrapper != null) {
						if (wrapper instanceof BreakdownElementWrapperItemProvider) {
							owningProcess = TngUtil
									.getOwningProcess(((BreakdownElementWrapperItemProvider) wrapper));
						}
					} else {
						wrapper = input.getMethodElement();
						if (wrapper instanceof BreakdownElement) {
							owningProcess = TngUtil
									.getOwningProcess(((BreakdownElement) wrapper));
						}
					}
					if (owningProcess != null ) {
						if (e == owningProcess)
							editorsToClose.add(editorRef);
						if ((owningProcess.eContainer() == null || UmaUtil
									.isContainedBy(owningProcess, e))) {
							editorsToClose.add(editorRef);
						}
					}
				}
			} catch (PartInitException ex) {
				DiagramCorePlugin.getDefault().getLogger().logError(ex);
			}
		}
		if(!editorsToClose.isEmpty()) {
			IEditorReference[] refs = new IEditorReference[editorsToClose.size()];
			editorsToClose.toArray(refs);
			workbenchPage.closeEditors(refs, false);
		}
	}
}
