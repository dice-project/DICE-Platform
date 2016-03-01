/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.library.configuration.closure;

import java.util.List;

/**
 * this class identifies a configuration error between two packages
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public class PackageError extends ErrorInfo {

	List<ElementError> elementErros = null;
	
	public PackageError(int errorLevel, String message, Object ownerElement, 
			Object causeElement, int relation) {
		super(errorLevel, message, ownerElement, causeElement, relation, null);
		
		//checkElementErrors();
	}
	
	public void setErrorLevel(int level) {
		errorLevel |= level;
	}
	
	public boolean hasElementError() {
		return elementErros != null && elementErros.size() > 0;
	}
	
	/**
	 * 
	 * @return boolean
	 */
	public boolean isChildError() {
		return (errorLevel & CHILD_ERROR) > 0;
	}

	/**
	 * 
	 * @return boolean
	 */
	public boolean isChildWarning() {
		return (errorLevel & CHILD_WARNING) > 0;
	}
	
//	/**
//	 * get a list element errors within this PackageError
//	 * @return
//	 */
//	public List<ElementError> getElementErrorList() {
//		
//		return this.elementErros;
//	}
	
//
//	private void checkElementErrors() {
//		if ( isChildError() || isChildWarning() ) {
//			return;
//		}
//		
//		this.elementErros = new ArrayList<ElementError>();
//
//		int relation = this.getRelation();
//		MethodElement ownerElement = (MethodElement) this.getOwnerElement();
//		MethodElement causeElement = (MethodElement) this.getCauseElement();
//		PackageDependency dep;
//		PackageReference pkgRef = null;
//
//		if (relation == ErrorInfo.REFERENCE_TO) {
//			dep = closure.getConfigurationManager().getDependencyManager()
//					.getDependency(ownerElement);
//			pkgRef = dep.getReference(causeElement, false);
//		} else if (relation == ErrorInfo.REFERENCED_BY) {
//			dep = closure.getConfigurationManager().getDependencyManager()
//					.getDependency(causeElement);
//			pkgRef = dep.getReference(ownerElement, false);
//		}
//
//		if (pkgRef != null) {
//			for (Iterator it = pkgRef.getReferences().iterator(); it
//					.hasNext();) {
//				ElementReference ref = (ElementReference) it.next();
//
//				// Don't show the entry if the reference can be ignored.
//				if (ref.canIgnore()) {
//					continue;
//				}
//				ElementError elementError;
//				String message = "";
//				MethodElement e1, e2;
//
//				if (relation == ErrorInfo.REFERENCE_TO) {
//					e1 = (MethodElement)ref.getElement();
//					e2 = (MethodElement)ref.getRefElement();
//				} else {
//					e2 = (MethodElement)ref.getElement();
//					e1 = (MethodElement)ref.getRefElement();
//				}
//
//				// if the element is subtracted, don't include the reference
//				if ( closure.isElementSubtracted(e1) ) {
//					continue;
//				}
//				
//				message = LibraryResources.bind(LibraryResources.ElementError_missing_element_message, 
//						(new String[] {LibraryUtil.getTypeName(e1), 
//								LibraryUtil.getTypeName(e2) }));
//				
//				elementError = new ElementError(
//						this.closure, this.getErrorLevel(),
//						message, e1, e2, relation); //$NON-NLS-1$
//
//				this.elementErros.add(elementError);
//			}
//		}
//	}
	
}
