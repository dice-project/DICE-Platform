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
package org.eclipse.epf.validation.constraints;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.IValidator;
import org.eclipse.epf.library.edit.validation.IValidatorFactory;
import org.eclipse.epf.library.edit.validation.internal.ValidatorFactory.TypeFilter;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.NamedElement;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.epf.validation.Activator;
import org.eclipse.epf.validation.util.ValidationStatus;

/**
 * @author Phong Nguyen Le
 * @since 1.1
 */
public class NameConstraint extends AbstractModelConstraint {
	private static final boolean DEBUG = Activator.getDefault().isDebugging();
	
//	private static final EClass[] CONTENT_ELEMENT_TYPES = getContentElementTypes();
//
//	private static EClass[] getContentElementTypes() {
//		ArrayList types = new ArrayList();
//		for (Iterator iter = UmaPackage.eINSTANCE.getEClassifiers().iterator(); iter.hasNext();) {
//			Object type = iter.next();
//			if(type instanceof EClass && UmaPackage.Literals.CONTENT_ELEMENT.isSuperTypeOf((EClass) type)) {
//				types.add(type);
//			}
//		}
//		EClass[] eClasses = new EClass[types.size()];
//		types.toArray(eClasses);
//		return eClasses;
//	}
	
	private boolean canCheck(Object eObj) {
		return eObj instanceof MethodPlugin
				|| eObj instanceof MethodConfiguration
				|| eObj instanceof ContentPackage
				|| eObj instanceof ProcessComponent
				|| ((eObj instanceof ProcessPackage || eObj instanceof ContentElement) && UmaUtil
						.getProcessComponent((MethodElement) eObj) == null);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	public IStatus validate(IValidationContext ctx) {
		EObject eObj = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();

		if(DEBUG) {
			if(eObj instanceof NamedElement) {
				NamedElement e = (NamedElement) eObj;
				System.out.println("NameConstraint.validate(): " + TngUtil.getTypeText(e) + ": '" + e.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}
			if(eObj instanceof Milestone) {
				System.out.println();
			}
		}
		MultiStatus multiStatus = new MultiStatus(Activator.PLUGIN_ID, 0, "", null); //$NON-NLS-1$

		// In the case of batch mode.
		if (eType == EMFEventType.NULL) {
			if((eObj instanceof MethodElement && TngUtil.isPredefined((MethodElement) eObj)))
			{
				// skip validating predefined method elements, ContentDescription
				//
				return ctx.createSuccessStatus();
			}
			
			// validate object's name
			//
			if(eObj instanceof CustomCategory) {
				CustomCategory cc = (CustomCategory)eObj;
				List list = AssociationHelper.getCustomCategories(cc);
				for (Iterator iter = list.iterator(); iter.hasNext();) {
					CustomCategory parent = (CustomCategory) iter.next();
					IValidator validator = IValidatorFactory.INSTANCE.createNameValidator(parent, cc);
					String msg = validator.isValid(cc.getName());
					if(msg != null) {
						multiStatus.add(new ValidationStatus(IStatus.ERROR, 0, msg, cc, UmaPackage.Literals.NAMED_ELEMENT__NAME));
					}
					validator = IValidatorFactory.INSTANCE.createPresentationNameValidator(parent, cc);
					msg = validator.isValid(cc.getPresentationName());
					if(msg != null) {
						multiStatus.add(new ValidationStatus(IStatus.ERROR, 0, msg, cc, UmaPackage.Literals.METHOD_ELEMENT__PRESENTATION_NAME));
					}
				}
			}
			else if(canCheck(eObj)) 
			{
				NamedElement e = (NamedElement)eObj;
				IValidator validator = IValidatorFactory.INSTANCE.createNameValidator(eObj.eContainer(), e);
				String msg = validator.isValid(e.getName());
				if(msg != null) {
					multiStatus.add(new ValidationStatus(IStatus.ERROR, 0, msg, e, UmaPackage.Literals.NAMED_ELEMENT__NAME));
				}
/*				if(eObj instanceof DescribableElement) {
					DescribableElement de = (DescribableElement) eObj;
					validator = IValidatorFactory.INSTANCE.createPresentationNameValidator(eObj.eContainer(), de);
					msg = validator.isValid(de.getPresentationName());
					if(msg != null) {
						multiStatus.add(new ValidationStatus(IStatus.ERROR, 0, msg, e, UmaPackage.Literals.METHOD_ELEMENT__PRESENTATION_NAME));
					}
				}*/
			}
			
//			// validate name of the children
//			//
//			if(eObj instanceof MethodLibrary) {
//				IStatus status = checkChildren(eObj, UmaPackage.Literals.METHOD_LIBRARY__METHOD_PLUGINS, 
//						UmaPackage.Literals.METHOD_PLUGIN);
//				if(!status.isOK()) {
//					multiStatus.merge(status);
//				}
//				status = checkChildren(eObj, UmaPackage.Literals.METHOD_LIBRARY__PREDEFINED_CONFIGURATIONS, 
//						UmaPackage.Literals.METHOD_CONFIGURATION);
//				if(!status.isOK()) {
//					multiStatus.merge(status);
//				}
//			}
//			else if(eObj instanceof ContentPackage) {
//				ContentPackage pkg = (ContentPackage) eObj;
//				boolean checked = false;
//				if(TngUtil.isPredefined(pkg)) {
//					String name = pkg.getName();
//					if(ModelStructure.DISCIPLINE_PACKAGE_NAME.equals(name)) {
//						IStatus status = checkChildren(eObj, UmaPackage.Literals.CONTENT_PACKAGE__CONTENT_ELEMENTS, 
//								UmaPackage.Literals.DISCIPLINE);				
//						if(!status.isOK()) {
//							multiStatus.merge(status);
//						}
//						checked = true;
//					}
//					else if (ModelStructure.ROLE_SET_PACKAGE_NAME.equals(name)) {
//						IStatus status = checkChildren(eObj, UmaPackage.Literals.CONTENT_PACKAGE__CONTENT_ELEMENTS, 
//								UmaPackage.Literals.ROLE_SET);				
//						if(!status.isOK()) {
//							multiStatus.merge(status);
//						}
//						checked = true;
//					}
//					else if (!(ModelStructure.CORE_CONTENT_PACAKGE_NAME.equals(name))) {
//						checked = true;
//					}
//				}
//				if(!checked) {
//					for (int i = 0; i < CONTENT_ELEMENT_TYPES.length; i++) {
//						IStatus status = checkChildren(eObj, UmaPackage.Literals.CONTENT_PACKAGE__CONTENT_ELEMENTS, 
//								CONTENT_ELEMENT_TYPES[i]);				
//						if(!status.isOK()) {
//							multiStatus.merge(status);
//						}
//					}
//				}
//			}
//			else if(eObj instanceof ProcessPackage) {
//				IStatus status = checkChildren(eObj, UmaPackage.Literals.METHOD_PACKAGE__CHILD_PACKAGES, 
//						UmaPackage.Literals.PROCESS_PACKAGE);
//				if(!status.isOK()) {
//					multiStatus.merge(status);
//				}
//			}
			
			if(multiStatus.isOK()) {
				return ctx.createSuccessStatus();
			}
			else {
				return multiStatus;
			}
			
		// In the case of live mode.
		} else {
			Object newValue = ctx.getFeatureNewValue();
			
			if (newValue == null || ((String)newValue).length() == 0) {
				return ctx.createFailureStatus(new Object[] {eObj.eClass().getName()});
			}
		}
		
		return ctx.createSuccessStatus();
	}

	private static class ChildFilter extends TypeFilter {

		private Set checkedChildren;

		public ChildFilter(EClass eClass, Set checkedChildren) {
			super(eClass);
			this.checkedChildren = checkedChildren;
		}

		public boolean accept(Object obj) {
			if (checkedChildren.contains(obj)) {
				return false;
			}
			return super.accept(obj);
		}
	}

	private IStatus checkChildren(EObject object, EReference childFeature,
			EClass eClass) {
		List statusList = new ArrayList();
		List children = (List) object.eGet(childFeature);
		Set checkedChildren = new HashSet();
		IFilter childFilter = new ChildFilter(eClass, checkedChildren);
		for (Iterator iter = children.iterator(); iter.hasNext();) {
			EObject child = (EObject) iter.next();
			if (child instanceof NamedElement) {
				IValidator validator = IValidatorFactory.INSTANCE
						.createValidator(object, childFeature, childFilter,
								child, UmaPackage.Literals.NAMED_ELEMENT__NAME);
				NamedElement e = (NamedElement) child;
				String msg = validator.isValid(e.getName());
				if (msg != null) {
					statusList.add(new ValidationStatus(IStatus.ERROR, 0, msg,
							e, UmaPackage.Literals.NAMED_ELEMENT__NAME));
				}
			}
			if (child instanceof DescribableElement) {
				IValidator validator = IValidatorFactory.INSTANCE
						.createValidator(
								object,
								childFeature,
								childFilter,
								child,
								UmaPackage.Literals.METHOD_ELEMENT__PRESENTATION_NAME);
				DescribableElement e = (DescribableElement) child;
				String msg = validator.isValid(e.getPresentationName());
				if (msg != null) {
					statusList
							.add(new ValidationStatus(
									IStatus.ERROR,
									0,
									msg,
									e,
									UmaPackage.Literals.METHOD_ELEMENT__PRESENTATION_NAME));
				}
			}
		}
		if (!statusList.isEmpty()) {
			if (statusList.size() == 1) {
				return (IStatus) statusList.get(0);
			} else {
				IStatus[] newChildren = new IStatus[statusList.size()];
				statusList.toArray(newChildren);
				return new MultiStatus(Activator.PLUGIN_ID, 0, newChildren,
						"", null); //$NON-NLS-1$
			}
		}
		return Status.OK_STATUS;
	}
}
