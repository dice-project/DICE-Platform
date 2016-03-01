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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.DelegatingWrapperItemProvider;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.editors.IEditorKeeper;
import org.eclipse.epf.authoring.ui.views.LibraryView;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.edit.FeatureValueWrapperItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.command.DeleteMethodElementCommand;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.persistence.ILibraryResourceSet;
import org.eclipse.epf.library.ui.actions.MethodElementDeleteAction;
import org.eclipse.epf.library.ui.actions.ProcessDeleteAction;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;

/**
 * Deletes an element in the Library view.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class LibraryViewDeleteAction extends MethodElementDeleteAction {
	
	/** 
	 * Delets an element in library view
	 * 
	 */
	public LibraryViewDeleteAction() {
		this(true);
	}
	
	/**
	 * Deletes an element in library view after confirming with user
	 * @param confirm
	 */
	public LibraryViewDeleteAction(boolean confirm) {
		super();
		this.confirm = confirm; 
	}

	/**
	 * @see MethodElementDeleteAction#didDelete(Collection)
	 */
	protected void didDelete(Collection deletedElements) {
		super.didDelete(deletedElements);
		
		// Close all open editors associated with the deleted elements.
		for (Iterator iter = deletedElements.iterator(); iter.hasNext();) {
			Object element = TngUtil.unwrap(iter.next());
			if (element instanceof MethodElement
					&& ((MethodElement) element).eContainer() == null) {
				IEditorKeeper.REFERENCE.getEditorKeeper()
						.closeEditorsOnDeletion((MethodElement) element);
			}
		}
	}
	
	protected static CustomCategory getRootCustomCategory(Object object) {
		for(; object instanceof IWrapperItemProvider; object = ((IWrapperItemProvider)object).getOwner());
		object = TngUtil.unwrap(object);
		return object instanceof CustomCategory && TngUtil.isRootCustomCategory((CustomCategory) object) ?
			(CustomCategory) object : null;
	}

	/**
	 * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public boolean updateSelection(IStructuredSelection selection) {
		ArrayList filteredSelection = new ArrayList();

		for (Iterator iter = selection.iterator(); iter.hasNext();) {
			Object e = iter.next();
			if(e instanceof CustomCategory) {
				filteredSelection.add(e);
				continue;
			}
			Object element = TngUtil.unwrap(e);
			if (element instanceof ItemProviderAdapter
					|| (element instanceof MethodElement && TngUtil
							.isPredefined((MethodElement) element))) {
				continue;
			}
			if (element instanceof CustomCategory) {
				CustomCategory rootCustomCategory = getRootCustomCategory(e);
				CustomCategory cc = (CustomCategory) element;
				if (rootCustomCategory != null && 
						UmaUtil.getMethodPlugin(rootCustomCategory) == UmaUtil.getMethodPlugin(cc)) {
					// selected custome category is in the current plugin.
					// add the element to the selection instead of its
					// wrapper so the element
					// will be deleted permanently from the library
					//
					filteredSelection.add(element);					
				}
			} else {				
				if(e instanceof IWrapperItemProvider) {
					IWrapperItemProvider wrapper = (IWrapperItemProvider) e;
//					if(wrapper.getValue() instanceof IWrapperItemProvider &&
//							!(TngUtil.unwrap(wrapper.getOwner()) instanceof CustomCategory)) {
//						// disallow deleting element when its reference in
//						// CustomCategory tree
//						// that is not directly under a CustomCategory is
//						// selected
//						//
//						continue;
//					}
					
					if (TngUtil.isUnderCustomCategoryTree(wrapper)) {
						// disallow deleting element under a custom category that is not a custom category
						//
						continue;
					}
					else if (wrapper.getFeature() == UmaPackage.Literals.DISCIPLINE_GROUPING__DISCIPLINES 
							|| wrapper.getFeature() == UmaPackage.Literals.ROLE_SET_GROUPING__ROLE_SETS) 
					{
						Object owner = wrapper.getOwner();
						if(owner instanceof EObject && element instanceof EObject && 
								UmaUtil.getMethodPlugin((EObject) owner) == UmaUtil.getMethodPlugin((EObject) element)) {
							// category grouping and category are in the same plugin
							// add the element to the selection instead of its wrapper so the element 
							// will be deleted permanently from the library
							//
							filteredSelection.add(element);
							continue;
						} else {
							continue;
						}
					}
				}
				filteredSelection.add(e);
			}
		}

		if (filteredSelection.isEmpty())
			return false;

		return super
				.updateSelection(new StructuredSelection(filteredSelection));
	}

	/**
	 * @see org.eclipse.epf.library.ui.actions.MethodElementDeleteAction#performDelete()
	 */
	protected void performDelete() {
		HashSet configsToDelete = new HashSet();
		HashSet modifiedResources = new HashSet();
		ILibraryPersister persister = LibraryServiceUtil.getCurrentPersister();
		if (selection != null && selection.size() > 0) {
			for (Iterator iter = selection.iterator(); iter.hasNext();) {
				Object object = TngUtil.unwrap(iter.next());
				if (object instanceof MethodConfiguration) {
					configsToDelete.add(object);
				}
				if (object instanceof EObject && !persister.hasOwnResourceWithoutReferrer(object)) {
					EObject container = ((EObject) object).eContainer();
					if (container != null && container.eResource() != null) {
						modifiedResources.add(container.eResource());
					}
				}
				FileUtil.getValidateEdit().addDeleteResourceToCheck(modifiedResources, object);
			}
		}

		// Avoid deleting the default configuration of a process.
		if (!configsToDelete.isEmpty()) {
			HashSet configGUIDsToDelete = new HashSet();
			for (Iterator iter = configsToDelete.iterator(); iter.hasNext();) {
				MethodConfiguration config = (MethodConfiguration) iter.next();
				configGUIDsToDelete.add(config.getGuid());
			}
			ILibraryResourceSet resourceSet = (ILibraryResourceSet) LibraryService
					.getInstance().getCurrentMethodLibrary().eResource()
					.getResourceSet();
			resourceSet
					.loadOppositeFeatures(
							Collections
									.singletonList(AssociationHelper.MethodConfiguration_DependentProcesses),
							configGUIDsToDelete);

			for (Iterator iter = configsToDelete.iterator(); iter.hasNext();) {
				MethodConfiguration config = (MethodConfiguration) iter.next();

				List references = AssociationHelper
						.getDependentProcesses(config);
				if (references != null && references.size() > 0) {
					String processName = ((org.eclipse.epf.uma.Process) references
							.get(0)).getName();
					AuthoringUIPlugin
							.getDefault()
							.getMsgDialog()
							.displayWarning(
									AuthoringUIResources.deleteDialog_title, 
									AuthoringUIResources.deleteConfigError_msg, 
									AuthoringUIResources
											.bind(
													AuthoringUIResources.deleteConfigError_reason,
													processName)); 
					return;
				}

			}

		}

		IStatus status = UserInteractionHelper.checkModify(modifiedResources,
				LibraryEditPlugin.getDefault().getContext());
		if (!status.isOK()) {
			AuthoringUIPlugin.getDefault().getMsgDialog().display(
					AuthoringUIResources.deleteDialog_title, 
					status);
			return;
		}

		if (confirmDelete()) {
			// Collect the information of Refreshable Objects
			// ( Reference Objects should be refreshed in navigation tree) -
			ArrayList refreshList = new ArrayList();
			for (Iterator iter = selection.iterator(); iter.hasNext();) {
				Object deletingObject = TngUtil.unwrap(iter.next());
				if (deletingObject instanceof MethodElement) {
					// check for variability
					if (deletingObject instanceof VariabilityElement) {
						for (Iterator iterator = TngUtil
								.getGeneralizers((VariabilityElement) deletingObject); iterator
								.hasNext();) {
							Object aReference = iterator.next();
							refreshList.add(aReference);
						}
					}
					
					IEditorKeeper.REFERENCE.getEditorKeeper().closeEditors(deletingObject, false);
				}
			}

			DeleteMethodElementCommand cmd = (DeleteMethodElementCommand) (command instanceof DeleteMethodElementCommand ? command : null);
			
			// execute actual delete command.
			command.execute();

			// delete the referencing descriptors if there is any
			//
			if(cmd != null) {
				Collection<Descriptor> descriptorsToDelete = cmd.getDescriptorsToDelete();
				if(descriptorsToDelete != null && !descriptorsToDelete.isEmpty()) {
					ProcessDeleteAction.delete(descriptorsToDelete);
				}
			}

			// Refresh the refreshlist objects in navigation tree.
			if (refreshList.size() > 0) {
				TreeViewer viewer = ((TreeViewer) (LibraryView.getView()
						.getViewer()));
				for (Iterator iter = refreshList.iterator(); iter.hasNext();) {
					viewer.refresh(iter.next());
				}
			}			
		}
	}

	/**
	 * Notify propery change with old and new value
	 * 
	 * @param propertyName
	 * 			property name for which value is going to change
	 * @param oldValue
	 * 			Old value of the property
	 * @param newValue
	 * 			New value of the property
	 */
	public void notifyPropertyChange(String propertyName, Object oldValue,
			Object newValue) {
		super.firePropertyChange(propertyName, oldValue, newValue);
	}

	/**
	 * Handle the special case of customcategory Copy/Delete action If parent of
	 * customcategory selected, ignore the customcategory, otherwise compound
	 * command creates a command for parent and a command for child,
	 * CompoundCommand will execute both commands, command for parent will
	 * remove parent itself and removes child too, next it try to execute
	 * command for child, command will fail because child got remove in command
	 * for parent. 
	 * TODO: handle properly in RemoveCommand for this case instead here.
	 */
	public void filterCustomCategorySelection(Object e, List filteredSelection) {
		if (e instanceof FeatureValueWrapperItemProvider) {
			filteredSelection.add(e);
		} else {
			Object owner = ((DelegatingWrapperItemProvider) e).getOwner();
			while (owner instanceof DelegatingWrapperItemProvider) {
				if (owner instanceof FeatureValueWrapperItemProvider) {
					if (!filteredSelection.contains(owner)) {
						filteredSelection.add(e);
					}
					break;
				} else {
					if (filteredSelection.contains(owner))
						break;
					owner = ((DelegatingWrapperItemProvider) owner).getOwner();
					if (!(owner instanceof FeatureValueWrapperItemProvider)) {
						if (!filteredSelection.contains(owner)) {
							filteredSelection.add(e);
							break;
						}
					}
				}
			}
		}
	}

}