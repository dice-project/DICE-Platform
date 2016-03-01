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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.epf.library.edit.IConfigurator;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.Providers;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.BatchCommand;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.Messenger;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Deliverable;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;


/**
 * Command for one-way synchronization from method to process.
 * 
 * @author Phong Nguyen Le - Nov 22, 2005
 * @since 1.0
 */
public class SynchronizeCommand extends CompoundCommand implements
		IResourceAwareCommand {

	private Collection elements;

	private IConfigurator configurator;

	protected boolean aborted;

	private ArrayList deleteList;

	protected boolean preExecSuccessful;

	protected List deleteCommandList;

	protected boolean successful;

	private MethodConfiguration config;

	private Set synchFeatures;

	private DeleteUnusedDescriptorsCommand deleteUnusedDescriptorsCommand;
	
	private Collection activities;

	private boolean showSuccessfulMsg = true;

	private boolean intialized;
	
	private BatchCommand batchCommand = new BatchCommand(false);
	private Map<VariabilityElement, VariabilityElement> replacerToBaseMap = new HashMap<VariabilityElement, VariabilityElement>();  
	
//	private Object UIContext;

	/**
	 * Constructs a SynchronizeCommand that use the process default
	 * configuration to synchronize all the synchronizable features
	 * 
	 * @param elements
	 *            a list of BreakdownElement objects
	 */
	public SynchronizeCommand(String label, Collection elements) {
		super(label);
		this.elements = elements;
	}
	
	/**
	 * Constructs a SynchronizeCommand that use the given configuration 
	 * and list of synchronizable features to synchronize
	 * 
	 * @param elements
	 * @param config the configuration
	 * @param synchFeatures the synchronizable features
	 */
	public SynchronizeCommand(Collection elements, MethodConfiguration config, Set synchFeatures, boolean showSuccessfulMsg) {
		super(LibraryEditResources.AutoSynchronizeCommand_label); 
		this.elements = elements;
		this.config = config;
		this.synchFeatures = synchFeatures;
		this.showSuccessfulMsg = showSuccessfulMsg; 
	}
	
	public void setMethodConfiguration(MethodConfiguration config) {
		this.config = config;
		if(configurator != null) {
			configurator.setMethodConfiguration(config);
		}
	}
	
	public void setSynchronizationFeatures(Set synchFeatures) {
		this.synchFeatures = synchFeatures;
	}
	
	private boolean doInitialize() {
		commandList.clear();
		
		if (elements == null || elements.isEmpty()) {
			return false;
		}

		deleteCommandList = new ArrayList();
		deleteList = new ArrayList();
		activities = new ArrayList();
		for (Iterator iter = elements.iterator(); iter.hasNext();) {
			Object element = (Object) iter.next();
			addToDeleteList(element, deleteList);
			if(element instanceof Activity) {
				activities.add(element);
			}
		}
		elements.removeAll(deleteList);
		
		for (Iterator<?> iter = elements.iterator(); iter.hasNext();) {
			Object object = iter.next();
			if (object instanceof Descriptor) {
				if(!deleteList.contains(object)) {
					Descriptor descriptor = (Descriptor) object;
					if (descriptor.getIsSynchronizedWithSource().booleanValue()) {
						if(descriptor.getSuperActivities() == null) {						
							// descriptor is used by TeamProfile to represent a role or used by a deliverable descriptor to
							// represent a deliverable part
							//
							if(descriptor instanceof WorkProductDescriptor && ((WorkProductDescriptor)descriptor).getWorkProduct() instanceof Deliverable) {
								append(new SynchronizeDeliverableDescriptorCommand((WorkProductDescriptor) descriptor, synchFeatures, config));
							}
							else {
								append(new BasicSynchronizeDescriptorCommand(descriptor, synchFeatures, config));
							}
						}
						else {
							Activity act = descriptor.getSuperActivities();
							if (object instanceof TaskDescriptor) {
								Task task = ((TaskDescriptor) object).getTask();
								if (task != null) {
									if(replacerToBaseMap.containsKey(task)) {
										task = (Task) replacerToBaseMap.get(task);
									}
									append(new WBSDropCommand(act, Collections
											.singletonList(task), 
											Collections
											.singletonList((TaskDescriptor)object), config, synchFeatures));
								}
							} else if (object instanceof RoleDescriptor) {
								Role role = ((RoleDescriptor) object).getRole();
								if (role != null) {
									if(replacerToBaseMap.containsKey(role)) {
										role = (Role) replacerToBaseMap.get(role);
									}
									append(new OBSDropCommand(act, Collections
											.singletonList(role), config, synchFeatures, configurator));
								}
							} else if (object instanceof WorkProductDescriptor) {
								WorkProduct wp = ((WorkProductDescriptor) object)
								.getWorkProduct();
								if (wp != null) {
									if(replacerToBaseMap.containsKey(wp)) {
										wp = (WorkProduct) replacerToBaseMap.get(wp);
									}
									append(new PBSDropCommand(act, Collections
											.singletonList(wp), config, synchFeatures, configurator));
								}
							}
						}
					}
					else {
						appendRemoveDuplicateGuidanceRefCommand(descriptor);
					}
				}
			} else if (object instanceof Activity) {
				appendCommands((Activity) object);
			} else if (object instanceof TeamProfile) {
				appendCommands((TeamProfile)object);
			}
		}
		return !deleteList.isEmpty() || !commandList.isEmpty() || !activities.isEmpty();
	}

	public boolean initilize() {
		boolean b = isPrepared;
		try {
			// since Eclipse 3.3, CompoundCommand.append() throws IllegalStateException if isPrepared is already set
			// but this method is called after isPrepared is set in canExecute() and it makes many calls to append()
			// work-around is to unset isPrepared at beginning of this method and set it back to old value at method end.
			//
			isPrepared = false;
			return intialized = doInitialize();		
		}
		finally {
			isPrepared = b;
		}
	}
	
	/**
	 * @return the intialized
	 */
	public boolean isIntialized() {
		return intialized;
	}

	/**
	 * @param activity
	 */
	private void appendCommands(Activity activity) {
		List<Task> tasks = new ArrayList<Task>();
		List<TaskDescriptor> tds = new ArrayList<TaskDescriptor>();

		List<Role> roles = new ArrayList<Role>();
		List<WorkProduct> workProducts = new ArrayList<WorkProduct>();
		List<Activity> activities = new ArrayList<Activity>();
		for (Iterator<?> iter = activity.getBreakdownElements().iterator(); iter
				.hasNext();) {
			Object element = iter.next();
			if (element instanceof Descriptor) {
				if(!deleteList.contains(element)) {
					Descriptor descriptor = ((Descriptor) element);
					if (descriptor.getIsSynchronizedWithSource()
							.booleanValue()) {
						if (element instanceof TaskDescriptor) {
							Task task = ((TaskDescriptor) element).getTask();
							if (task != null) {
								if(replacerToBaseMap.containsKey(task)) {
									task = (Task) replacerToBaseMap.get(task);
								}
								tasks.add(task);
								tds.add((TaskDescriptor) element);
							}
						} else if (element instanceof RoleDescriptor) {
							Role role = ((RoleDescriptor) element).getRole();
							if (role != null) {
								if(replacerToBaseMap.containsKey(role)) {
									role = (Role) replacerToBaseMap.get(role);
								}
								roles.add(role);
							}
						} else if (element instanceof WorkProductDescriptor) {
							WorkProduct wp = ((WorkProductDescriptor) element)
							.getWorkProduct();
							if (wp != null) {
								if(replacerToBaseMap.containsKey(wp)) {
									wp = (WorkProduct) replacerToBaseMap.get(wp);
								}
								workProducts.add(wp);
							}
						}
					} else {
						appendRemoveDuplicateGuidanceRefCommand(descriptor);
					}
				}
			} else if (element instanceof Activity) {
				activities.add((Activity) element);
			} else if (element instanceof TeamProfile) {
				appendCommands((TeamProfile)element);
			}
		}
		if (!tasks.isEmpty()) {
			append(new WBSDropCommand(activity, tasks, tds, config, synchFeatures));
		}
		if (!roles.isEmpty()) {
			append(new OBSDropCommand(activity, roles, config, synchFeatures, configurator));
		}
		if (!workProducts.isEmpty()) {
			append(new PBSDropCommand(activity, workProducts, config,
					synchFeatures, configurator));
		}
		for (Iterator<Activity> iter = activities.iterator(); iter.hasNext();) {
			appendCommands(iter.next());
		}
	}
	
	private void appendRemoveDuplicateGuidanceRefCommand(Descriptor descriptor) {
		append(new RemoveDuplicateReferenceCommand(descriptor, ProcessCommandUtil.CONTENT_ELEMENT_GUIDANCE_REFERENCES, config));
	}

	private void appendCommands(TeamProfile team) {
		Iterator iter = new AbstractTreeIterator(team, false) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected Iterator getChildren(Object object) {
				if(object instanceof TeamProfile) {
					TeamProfile team = ((TeamProfile)object);
					List children = new ArrayList(team.getSubTeam());
					children.addAll(team.getTeamRoles());
					return children.iterator();
				}
				else {
					return Collections.EMPTY_LIST.iterator();
				}
			}
			
		};
		while(iter.hasNext()) {
			Object obj = iter.next();
			
			// synch only own role descriptor of team profile
			//
			if(obj instanceof RoleDescriptor && ((RoleDescriptor)obj).getSuperActivities() == null) {
				append(new BasicSynchronizeDescriptorCommand((Descriptor) obj, synchFeatures, config));
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.command.IResourceAwareCommand#getModifiedResources()
	 */
	public Collection getModifiedResources() {
		HashSet modifiedResources = new HashSet();
		for (Iterator iter = commandList.iterator(); iter.hasNext();) {
			
			Object cmd = iter.next();
			if(cmd instanceof IResourceAwareCommand) {
				modifiedResources.addAll(((IResourceAwareCommand)cmd).getModifiedResources());
			}
		}
		return modifiedResources;
	}
	
//	public void setUIContext(Object UIContext) {
//		this.UIContext = UIContext;
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.CompoundCommand#execute()
	 */
	public void execute() {
		// delete all descriptors whose linked element are no longer in the
		// configuration
		//
		if (!deleteList.isEmpty()) {
			Command cmd = delete(deleteList);
			if (cmd != null) {
				deleteCommandList.add(cmd);
			}
		}
		
		batchCommand.execute();
		
		if (!aborted) {
//			IRunnableWithProgress runnable = new IRunnableWithProgress() {
//
//				public void run(IProgressMonitor monitor)
//						throws InvocationTargetException, InterruptedException {
//					preExecSuccessful = preExecute();
//				}
//
//			};
//						
//			UserInteractionHelper.runWithProgress(runnable, false, null);
//
//			if (preExecSuccessful) {
//				try {
//					BusyIndicator.showWhile(Display.getCurrent(), new Runnable() {
//						
//						public void run() {
//							superRedo();
//							successful = true;
//						}
//						
//					});
//				}
//				catch(RuntimeException e) {
//					LibraryEditPlugin.getDefault().getMsgDialog().displayError(getLabel(), e.toString());
//				}
//			}
			
			Runnable runnable = new Runnable() {

				public void run() {
					preExecSuccessful = preExecute();
					if(preExecute()) {
						superRedo();
						successful = true;
					}
				}

			};
			UserInteractionHelper.runInUI(runnable, getLabel());
			
			if (successful) {				
				if(!activities.isEmpty()) {
					if(deleteUnusedDescriptorsCommand == null) {
						deleteUnusedDescriptorsCommand = new DeleteUnusedDescriptorsCommand(elements, true, deleteList) {
							
							protected Command delete(List elements) {
								return SynchronizeCommand.this.delete(elements);
							}
							
						};
						deleteCommandList.add(deleteUnusedDescriptorsCommand);
					}
					deleteUnusedDescriptorsCommand.execute();
				}
				
				if (showSuccessfulMsg) {
					if(!replacerToBaseMap.isEmpty()) {
						refreshViewer();
					}
					Messenger.INSTANCE.showInfo(
									LibraryEditResources.SynchronizeCompleteDialog_Title,
									LibraryEditResources.AutoSynchronizeCommand_sucessful);
				}
			}
		}
	}

	private void refreshViewer() {
		if(elements.isEmpty())  return;
		Process proc = TngUtil.getOwningProcess(elements.iterator().next());
		if(proc != null) {
			for (int i = 0; i < TngAdapterFactory.processAdapterFactories.length; i++) {
				AdapterFactory adapterFactory = TngAdapterFactory.processAdapterFactories[i];
				ProcessUtil.refreshViewer(adapterFactory, proc);
			}
		}
	}

	private void superRedo() {
		super.redo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.CompoundCommand#redo()
	 */
	public void redo() {
		execute();
	}

	public boolean isSucessful() {
		return successful;
	}

	protected boolean preExecute() {
		for (ListIterator commands = commandList.listIterator(); commands
				.hasNext();) {
			Object command = commands.next();
			if(command instanceof BSDropCommand && !((BSDropCommand)command).preExecute()) {
				return false;
			}
			
			// be polite to other threads (no effect on some platforms)
			//
			Thread.yield();
		}
		return true;
	}

	/**
	 * Deletes the specified elements
	 * @param elements
	 * @return the executed command that deleted the given elements
	 */
	protected Command delete(List elements) {
		EditingDomain domain = new AdapterFactoryEditingDomain(
				TngAdapterFactory.INSTANCE.getProcessComposedAdapterFactory(),
				new BasicCommandStack());
		Command cmd = new ProcessElementDeleteCommand(RemoveCommand.create(domain,
				elements), elements);
		cmd.execute();
		return cmd;
	}

	private IConfigurator getConfigurator() {
		if (configurator == null) {
			MethodConfiguration config = this.config;
			if(config == null) {
				Process proc = TngUtil.getOwningProcess((BreakdownElement) elements
						.iterator().next());
				config = proc.getDefaultContext();
			}
			configurator = Providers.getConfiguratorFactory()
					.createConfigurator(config);
		}
		return configurator;
	}

	/**
	 * @param element
	 * @param deleteList
	 */
	private void addToDeleteList(Object element, List deleteList) {
		if (element instanceof Descriptor) {
			if (!getConfigurator().accept(element)) {
				MethodElement linkedElement = ProcessUtil.getAssociatedElement((Descriptor) element);				
				if(linkedElement instanceof VariabilityElement && TngUtil.isReplacer((VariabilityElement) linkedElement)) {
					// if the linked element of the descriptor is a replacer, delete the descriptor in this synchronization
					// only if the base of linked element is not in the configuration
					//
					VariabilityElement base = ((VariabilityElement)linkedElement).getVariabilityBasedOnElement();
					while(base != null && TngUtil.isContributorOrReplacer(base)) {
						base = base.getVariabilityBasedOnElement();
					}
					if(base != null) {
						if(!getConfigurator().accept(base)) {
							deleteList.add(element);
						}
						else {
							batchCommand.addFeatureValue((EObject) element, 
									ProcessUtil.getLinkReference((Descriptor) element), base);
							replacerToBaseMap.put((VariabilityElement) linkedElement, base);
						}
					}
				}
				else {
					deleteList.add(element);
				}
			}
		} else if (element instanceof Activity) {
			for (Iterator iter = ((Activity) element).getBreakdownElements()
					.iterator(); iter.hasNext();) {
				addToDeleteList(iter.next(), deleteList);
			}
		}
	}

	private void superUndo() {
		super.undo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.CompoundCommand#undo()
	 */
	public void undo() {
		UserInteractionHelper.getUIHelper().runWithBusyIndicator(new Runnable() {

			public void run() {
				if (!deleteCommandList.isEmpty()) {
					for (ListIterator commands = deleteCommandList
							.listIterator(deleteCommandList.size()); commands
							.hasPrevious();) {
						try {
							Command command = (Command) commands.previous();
							command.undo();
						} catch (RuntimeException exception) {
							// Skip over the command that threw the exception.
							//
							commands.next();

							try {
								// Iterate forward over the undone commands to
								// redo them.
								//
								while (commands.hasNext()) {
									Command command = (Command) commands.next();
									command.redo();
								}
							} catch (RuntimeException nestedException) {
								CommonPlugin.INSTANCE
										.log(new WrappedException(
												CommonPlugin.INSTANCE
														.getString("_UI_IgnoreException_exception"), nestedException).fillInStackTrace()); //$NON-NLS-1$
							}

							throw exception;
						}
					}
				}

				superUndo();
				
				batchCommand.undo();
			}

		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.CompoundCommand#prepare()
	 */
	protected boolean prepare() {
		return true;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.CompoundCommand#dispose()
	 */
	public void dispose() {
		if(activities != null) {
			activities.clear();
		}
		if(deleteCommandList != null) {
			for (Iterator iter = deleteCommandList.iterator(); iter.hasNext();) {
				Command cmd = (Command) iter.next();
				cmd.dispose();
			}
		}
		if(deleteList != null) {
			deleteList.clear();
		}
		
		super.dispose();
	}
}
