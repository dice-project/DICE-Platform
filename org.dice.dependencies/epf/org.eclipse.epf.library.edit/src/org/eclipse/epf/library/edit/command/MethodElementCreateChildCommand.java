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
package org.eclipse.epf.library.edit.command;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.util.ExtensionManager;
import org.eclipse.epf.library.edit.validation.AbstractStringValidator;
import org.eclipse.epf.library.edit.validation.IValidator;
import org.eclipse.epf.uma.NamedElement;
import org.eclipse.osgi.util.NLS;

/**
 * This command is used to create a child method element and add it to the
 * containing method element.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class MethodElementCreateChildCommand extends CreateChildCommand {

	private boolean executed = false;
	protected IValidator validator;

	/**
	 * Creates a new instance.
	 */
	public MethodElementCreateChildCommand(EditingDomain domain, EObject owner,
			EStructuralFeature feature, Object child, int index,
			Collection selection, Helper helper) {
		super(domain, owner, feature, child, index, selection, helper);
	}

	private void superExecute() {
		super.execute();
	}
	
	protected IValidator getValidator() {
		if(validator == null) {
			validator = new AbstractStringValidator() {
				public String isValid(String newText) {
					if (newText.trim().length() == 0) {
						String elementText = LibraryEditResources.element_text; 
						//		return I18nUtil.formatString(RESOURCE_BUNDLE, key, data);
						return NLS.bind(LibraryEditResources.emptyElementNameError_msg, elementText); 
					}
					List children = (List) owner.eGet(feature);
					for (Iterator iter = children.iterator(); iter.hasNext();) {
						NamedElement child = (NamedElement) iter.next();
						if (child.getName().equalsIgnoreCase(newText)) {
							//		return I18nUtil.formatString(RESOURCE_BUNDLE, key, data);
							return NLS.bind(LibraryEditResources.duplicateElementNameError_msg, newText); 
						}
					}
					return null;
				}
			};
		}
		return validator;
	}

	/**
	 * @see org.eclipse.emf.common.command.CommandWrapper#execute()
	 */
	public void execute() {
		String title = NLS.bind(LibraryEditResources.newElement_text, helper.getCreateChildText(owner, feature, child, selection)); 
		IValidator validator = getValidator();
//		final String name = UserInteractionHelper.requestName(child,
//				UmaPackage.eINSTANCE.getNamedElement_Name(), title, validator);
		IUserInteractionHandler uiHandler = ExtensionManager.getDefaultUserInteractionHandler();
		UserInput userInput = new UserInput(LibraryEditResources.nameLabel_text, UserInput.TEXT, 
				false, null, null, validator, this);		
		if(uiHandler.requestInput(title, LibraryEditResources.SpecifyNameText, Collections.singletonList(userInput))) { 
			String name = (String) userInput.getInput();
			if (name != null) {
				((NamedElement) child).setName(name);
				superExecute();
				executed = true;
			}
		}
	}

	/**
	 * @see org.eclipse.emf.edit.command.CreateChildCommand#getAffectedObjects()
	 */
	public Collection getAffectedObjects() {
		if (executed) {
			return super.getAffectedObjects();
		}
		return Collections.singletonList(owner);
	}

	/**
	 * @see org.eclipse.emf.edit.command.CreateChildCommand#getResult()
	 */
	public Collection getResult() {
		if (executed) {
			return super.getResult();
		}
		return Collections.EMPTY_LIST;
	}

	public EObject getOwner() {
		return owner;
	}
	
	public Object getChild() {
		return child;
	}
	
	public EStructuralFeature getFeature() {
		return feature;
	}
}
