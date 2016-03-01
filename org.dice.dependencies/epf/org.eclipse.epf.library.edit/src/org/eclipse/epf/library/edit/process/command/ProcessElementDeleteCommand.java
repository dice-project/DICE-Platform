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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.epf.library.edit.command.DeleteMethodElementCommand;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.util.ContentDescriptionFactory;
import org.eclipse.epf.uma.util.UmaUtil;


/**
 * @author Phong Nguyen Le - Jan 26, 2006
 * @since  1.0
 */
public class ProcessElementDeleteCommand extends
		DeleteMethodElementCommand {
	private Set contentsToRemove;

	private ArrayList elementsToRemoveReferences;

	private boolean ownedElementsIncluded;

	/**
	 * @param command
	 * @param elements
	 */
	public ProcessElementDeleteCommand(Command command, Collection elements) {
		super(command, elements);
	}
	
	/**
	 * @param command
	 * @param elements
	 * @param confirmRemoveRefs
	 */
	public ProcessElementDeleteCommand(Command command, Collection elements, boolean confirmRemoveRefs) {
		super(command, elements, confirmRemoveRefs);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.command.DeleteMethodElementCommand#execute()
	 */
	public void execute() {
		if(!ownedElementsIncluded) {
			Collection ownedElements = getOwnedElements(elements);
			if(!ownedElements.isEmpty()) {
				includeElements(ownedElements);
			}
			ownedElementsIncluded = true;
		}
		
		super.execute();
	}
	
	protected Collection getOwnedElements(Collection elements) {
		ArrayList ownedElements = new ArrayList();
		for (Iterator iter = elements.iterator(); iter.hasNext();) {
			Object element = (Object) iter.next();
			if(element instanceof WorkProductDescriptor) {
				WorkProductDescriptor wpd = ((WorkProductDescriptor)element);
				WorkProduct wp = wpd.getWorkProduct();
				if(wp instanceof Artifact) {
					Iterator iter1 = new AbstractTreeIterator(wp, false) {

						/**
						 * Comment for <code>serialVersionUID</code>
						 */
						private static final long serialVersionUID = 422181782268878289L;

						protected Iterator getChildren(Object object) {
							return ((Artifact)object).getContainedArtifacts().iterator();
						}
						
					};
					HashSet workProducts = new HashSet();
					while(iter1.hasNext()) {
						workProducts.add(iter1.next());
					}
					Activity activity = UmaUtil.getParentActivity(wpd);
					for (Iterator iterator = activity.getBreakdownElements().iterator(); iterator.hasNext();) {
						Object be = iterator.next();
						if (be instanceof WorkProductDescriptor
								&& workProducts.contains(((WorkProductDescriptor) be)
										.getWorkProduct())) {
							ownedElements.add(be);
						}
					}
					
				}
			}
		}
		return ownedElements;
	}

	protected void excludeElements(Collection excludedElements) {
		elements.removeAll(excludedElements);
		for (Iterator iter = excludedElements.iterator(); iter.hasNext();) {
			Object element = (Object) iter.next();
			RemoveCommand cmd = getRemoveCommand(element);
			if(cmd != null) {
				cmd.getCollection().remove(element);
			}
		}
	}
	
	protected void includeElements(Collection includedElements) {
		for (Iterator iter = includedElements.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if(!elements.contains(element)) {
				elements.add(element);
				RemoveCommand cmd = getRemoveCommand(element);
				if(cmd != null) {
					Collection collection = cmd.getCollection();
					if(!collection.contains(element)) {
						collection.add(element);
					}
				}
			}
		}
	}

	public Collection getElementsToRemoveReferences() {
		if (elementsToRemoveReferences == null) {
			elementsToRemoveReferences = new ArrayList();
			for (Iterator iter = elements.iterator(); iter.hasNext();) {
				Object element = iter.next();
				if (element instanceof Activity) {
					EObject pkg = ((Activity) element).eContainer();
					if (pkg != null) {
						elementsToRemoveReferences.add(pkg);
					} else {
						elementsToRemoveReferences.add(element);
					}
				} else {
					elementsToRemoveReferences.add(element);
				}
			}
		}
		return elementsToRemoveReferences;
	}

	protected boolean canRemoveReferences(org.eclipse.epf.uma.MethodElement e) {
		return true;
	}

	protected void removeReferences() {
		contentsToRemove = new HashSet();
		// have to remove the deleted process elements from its container
		// and/or resource
		// so super.removeReferences() can remove their references
		//
		for (Iterator iter = elements.iterator(); iter.hasNext();) {
			Object obj = iter.next();
			if (obj instanceof EObject) {
				EObject eObj = (EObject) obj;
				if (obj instanceof Activity) {
					Activity act = (Activity) obj;

					// remove content of the activity from its resource
					//
					// removeContentFromResource(act, modifiedResources);
					if (ContentDescriptionFactory.hasPresentation(act)) {
						contentsToRemove.add(act.getPresentation());
					}

					// remove the process package of this activity
					//
					EObject container = eObj.eContainer();
					if (container != null) {
						EcoreUtil.remove(container);
						for (Iterator iterator = container.eAllContents(); iterator
								.hasNext();) {
							Object element = iterator.next();
							if (element instanceof Activity) {
								act = (Activity) element;

								// remove content of the activity from its
								// resource
								//
								// removeContentFromResource(act,
								// modifiedResources);
								if (ContentDescriptionFactory
										.hasPresentation(act)) {
									contentsToRemove.add(act
											.getPresentation());
								}
							}
						}
					}
				} else {
					EcoreUtil.remove((EObject) obj);
				}
			}
		}

		super.removeReferences();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see DeleteMethodElementCommand#deleteContent()
	 */
	protected void deleteContent() throws Exception {
		for (Iterator iter = contentsToRemove.iterator(); iter.hasNext();) {
			EcoreUtil.remove((EObject) iter.next());
		}
		elementsToDeleteContent.addAll(contentsToRemove);
		super.deleteContent();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.command.DeleteMethodElementCommand#collectObjectsToRemove(java.util.Collection, org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject, java.util.Collection)
	 */
	protected boolean collectObjectsToRemove(Collection objectsToRemove, EObject elementToDelete, EObject referencer, Collection references) {
		boolean ret = super.collectObjectsToRemove(objectsToRemove, elementToDelete,
				referencer, references);
		for (Iterator iter = references.iterator(); iter.hasNext();) {
			EReference ref = (EReference) iter.next();
			if(ref == UmaPackage.eINSTANCE.getUMASemanticModelBridge_Element()) {
				EObject container = referencer.eContainer();
				if(container != null && container.eContainer() != null) {
					objectsToRemove.add(container);
					ret = true;
				}
			}
		}
		return ret;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.CommandWrapper#dispose()
	 */
	public void dispose() {
		if(contentsToRemove != null) {
			contentsToRemove.clear();
		}
		if(elementsToRemoveReferences != null) {
			elementsToRemoveReferences.clear();
		}
		
		super.dispose();
	}
	
	@Override
	protected void removeReferenceFollowUp(EObject referencer,
			EObject referenced, EStructuralFeature feature) {
		if (! ProcessUtil.isSynFree()) {
			return;
		}
		if (! (referencer instanceof Descriptor)) {
			return;
		}
		if (! (referenced instanceof Descriptor)) {
			return;
		}
		if (! (feature instanceof EReference)) {
			return;
		}
		
		
		DescriptorPropUtil propUtil = DescriptorPropUtil.getDesciptorPropUtil();
		EReference ref = (EReference) feature;		
		EReference eref = LibraryEditUtil.getInstance().getExcludeFeature(ref);
		if (eref == null) {
			return;
		}
		MethodElement element = ProcessUtil.getAssociatedElement((Descriptor) referenced);
		boolean localUse = propUtil.localUse((Descriptor) referenced, (Descriptor) referencer, ref);
		if (localUse) {
			propUtil.removeLocalUse((Descriptor) referenced, (Descriptor) referencer, ref);	
			
		} else if (element != null) {
			List excludeList = (List) referencer.eGet(eref);
			excludeList.add(element);
			
		}
	
	}
}
