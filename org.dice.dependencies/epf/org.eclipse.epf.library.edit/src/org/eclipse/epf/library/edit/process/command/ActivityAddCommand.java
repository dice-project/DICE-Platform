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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.CopyCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.util.Messenger;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.edit.domain.TraceableAdapterFactoryEditingDomain;

/**
 * AddCommand for activity
 * 
 * @author Phong Nguyen Le - Sep 16, 2005
 * @since 1.0
 * @see 
 */
public class ActivityAddCommand extends ProcessElementAddCommand {
	private List procPackages;

	/**
	 * @param command
	 */
	public ActivityAddCommand(Command command) {
		super(command);
	}

	private Map getCopyToOriginalMap() {
		EditingDomain ed = addCommand.getDomain();
		if (ed instanceof TraceableAdapterFactoryEditingDomain) {
			return ((TraceableAdapterFactoryEditingDomain) ed)
					.getCopyToOriginalMap();
		}
		return null;
	}

	private void copyProcessPackages() {
		if (procPackages == null) {
			if (hasCopyToOriginalMap()) {
				boolean showWarning = false;
				procPackages = new ArrayList();
				ArrayList<Activity> copiedActivities = new ArrayList();

				// check first element of addCommand.getCollection()
				// if it's a key in the CopyToOriginalMap, we are pasting and
				// may need to warn the user
				// if it's not a key in the CopyToOriginalMap, then we are
				// probably adding a new element				
				if (addCommand.getCollection() != null
						&& addCommand.getCollection().size() > 0
						&& getOriginal(addCommand.getCollection().toArray()[0]) != null) {
					for (Iterator iter = addCommand.getCollection().iterator(); iter
							.hasNext();) {
						Object element = iter.next();
						if (element instanceof Activity) {
							Activity orig = (Activity) getOriginal(element);
							if (orig != null) {
								if (orig.eContainer() != null) {
									procPackages.add(orig.eContainer());
									copiedActivities.add((Activity) element);
								}
							} else {
								showWarning = true;
							}
						}
					}
				}

				if (showWarning) {
					Messenger.INSTANCE
							.showWarning(
									getLabel(),
									LibraryEditResources.ActivityAddCommand_originalNotFoundWarning_msg);
				}

				if (!procPackages.isEmpty()) {
					// copy the ProcessPackage of the original Activity to add
					// it to the ProcessPackage
					// of the target Activity.
					//
					// AdapterFactoryEditingDomain editingDomain = new
					// AdapterFactoryEditingDomain(TngAdapterFactory.INSTANCE.getProcessComposedAdapterFactory()
					// , new BasicCommandStack());
					Command command = CopyCommand.create(
							addCommand.getDomain(), procPackages);
					try {
						command.execute();

						// Refresh the AddCommand.collection with new activity copies.
						//
						Collection collection = new ArrayList(addCommand.getCollection());
						Collection elements = addCommand.getCollection();						
						elements.clear();	
						Collection col = command.getResult();
						ArrayList<ProcessPackage> procPkgCopies = new ArrayList<ProcessPackage>(col);
//						ArrayList<ProcessPackage> procPkgCopies = new ArrayList<ProcessPackage>(command.getResult());
						for (Iterator iter = collection.iterator(); iter
								.hasNext();) {
							Object element = (Object) iter.next();
							int index = copiedActivities.indexOf(element);
							if(index != -1) {
								ProcessPackage copy = procPkgCopies.get(index);
								element = getActivityCopy(copy);
							}
							if(element != null) {
								elements.add(element);
							}
						}
						
					} finally {
						if (command != null) {
							command.dispose();
						}
					}
				}
			}
		}
	}

	private Activity getActivityCopy(ProcessPackage copy) {
		if (copy instanceof ProcessComponent) {
			Activity actCopy = ((ProcessComponent) copy)
					.getProcess();
			// copy data from ProcessComponent to a new
			// ProcessPackage
			ProcessPackage pkgCopy = UmaFactory.eINSTANCE
					.createProcessPackage();
			pkgCopy.setName(actCopy.getName());
			pkgCopy.getProcessElements().add(actCopy);
			pkgCopy.getProcessElements().addAll(
					copy.getProcessElements());
			pkgCopy.getDiagrams()
					.addAll(copy.getDiagrams());
			pkgCopy.getChildPackages().addAll(
					copy.getChildPackages());

			return actCopy;
		} else {
			for (Iterator iterator = copy.getProcessElements().iterator(); iterator
					.hasNext();) {
				Object element = iterator.next();
				if (element instanceof Activity) {
					return (Activity) element;
				}
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.command.MethodElementAddCommand#execute()
	 */
	public void execute() {
		prepareAddList();
		copyProcessPackages();
		super.execute();
	}

	/**
	 * Checks if e is a child, direct or of act
	 * 
	 * @param act
	 * @param e
	 * @return
	 */
	private static boolean isChildOf(Object parent, Object e) {
		if (parent instanceof Activity) {
			Activity act = (Activity) parent;
			for (Iterator iter = act.getBreakdownElements().iterator(); iter
					.hasNext();) {
				Object child = iter.next();
				if (e == child) {
					return true;
				}
				if (isChildOf(child, e)) {
					return true;
				}
			}
		}
		// else if(parent instanceof WorkProductDescriptor) {
		// WorkProduct wp = ((WorkProductDescriptor)parent).getWorkProduct();
		// if(wp instanceof Artifact) {
		// if(e instanceof WorkProductDescriptor) {
		// if(UmaUtil.isContainedBy(((WorkProductDescriptor)e).getWorkProduct(),
		// wp)) {
		// return true;
		// }
		// }
		// }
		// }
		return false;
	}

	private static boolean isChildOf(Collection elements, Object e) {
		for (Iterator iter = elements.iterator(); iter.hasNext();) {
			if (isChildOf(iter.next(), e)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean hasCopyToOriginalMap() {
		EditingDomain ed = addCommand.getDomain();
		if (ed instanceof TraceableAdapterFactoryEditingDomain) {
			TraceableAdapterFactoryEditingDomain editingDomain = ((TraceableAdapterFactoryEditingDomain) ed);
			return (editingDomain.getCopyToOriginalMap() != null &&
					!editingDomain.getCopyToOriginalMap().isEmpty())
				|| (editingDomain.getClipboardToOriginalMap() != null &&
						!editingDomain.getClipboardToOriginalMap().isEmpty());
		}
		return false;
	}
	
	private Object getOriginal(Object copy) {
		TraceableAdapterFactoryEditingDomain ed = (TraceableAdapterFactoryEditingDomain) addCommand.getDomain();
		Object original = ed.getCopyToOriginalMap().get(copy);
		if(original == null && ed.getClipboardToOriginalMap() != null) {
			original = ed.getClipboardToOriginalMap().get(copy);
		}
		return original;
	}

	private void prepareAddList() {
		// cleanup element list before adding it to the activity to avoid adding
		// elements multiple times
		//		
		if (hasCopyToOriginalMap()) {
			ArrayList originals = new ArrayList();
			Map originalToCopyMap = new HashMap();
			for (Iterator iter = addCommand.getCollection().iterator(); iter
					.hasNext();) {
				Object element = iter.next();
				Object original = getOriginal(element);
				if (original != null) {
					originals.add(original);
					originalToCopyMap.put(original, element);
				}
			}

			ArrayList removeList = new ArrayList();
			// ArrayList addList = new ArrayList();
			for (Iterator iter = originals.iterator(); iter.hasNext();) {
				Object element = iter.next();
				if (isChildOf(originals, element)) {
					removeList.add(originalToCopyMap.get(element));
				}
			}
			addCommand.getCollection().removeAll(removeList);
		}
	}

}