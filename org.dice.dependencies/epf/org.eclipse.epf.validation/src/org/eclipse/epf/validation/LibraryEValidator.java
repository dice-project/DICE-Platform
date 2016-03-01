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
package org.eclipse.epf.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.edit.provider.AdapterFactoryTreeIterator;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.validation.model.Category;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.model.IConstraintStatus;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.emf.validation.service.IConstraintFilter;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.edit.IAdapterFactoryProvider;
import org.eclipse.epf.library.edit.process.DescribableElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.DependencyChecker;
import org.eclipse.epf.library.edit.validation.DependencyValidationMgr;
import org.eclipse.epf.library.edit.validation.IValidationManager;
import org.eclipse.epf.library.edit.validation.IValidator;
import org.eclipse.epf.library.edit.validation.IValidatorFactory;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.NamedElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.validation.constraints.LibraryTraversalStrategy;
import org.eclipse.epf.validation.util.ValidationStatus;

/**
 * An adapter that plugs the EMF Model Validation Service API into the
 * {@link org.eclipse.emf.ecore.EValidator} API.
 * 
 * @author Phong Nguyen Le
 * @since 1.1
 */
public class LibraryEValidator extends EObjectValidator {
	private static final boolean DEBUG = Activator.getDefault().isDebugging();
	private static final Map featureToPropertyMap = createFeatureToAttributeMap();

	public static final String CTX_ADAPTER_FACTORY_PROVIDER = "CTX_ADAPTER_FACTORY_PROVIDER"; //$NON-NLS-1$
	public static final String CTX_DEPENDENCY_VALIDATION_MGR = "CTX_DEPENDENCY_VALIDATION_MGR"; //$NON-NLS-1$
	public static final String CTX_CONSTRAINT_FILTER = "CTX_CONSTRAINT_FILTER"; //$NON-NLS-1$
	
	public static final String CONSTRAINT_CATEGORY = "org.eclipse.epf.validation.library.category"; //$NON-NLS-1$

	private static Map createFeatureToAttributeMap() {
		Map map = new HashMap();
		map.put(UmaPackage.Literals.NAMED_ELEMENT__NAME, IBSItemProvider.COL_NAME);
		map.put(UmaPackage.Literals.METHOD_ELEMENT__PRESENTATION_NAME, IBSItemProvider.COL_PRESENTATION_NAME);
		return map;
	}
	
	/**
	 * Model Validation Service interface for batch validation of EMF elements.
	 */
	private final IBatchValidator batchValidator;

	/**
	 * Initializes me.
	 */
	public LibraryEValidator() {
		super();

		batchValidator = (IBatchValidator) ModelValidationService.getInstance()
				.newValidator(EvaluationMode.BATCH);
		batchValidator.setIncludeLiveConstraints(true);
		batchValidator.setReportSuccesses(false);
		batchValidator.addConstraintFilter(new IConstraintFilter() {

			public boolean accept(IConstraintDescriptor constraint,
					EObject target) {
				Set<Category> categories = constraint.getCategories();
				if(categories != null && !categories.isEmpty()) {
					for (Iterator<Category> iterator = categories.iterator(); iterator
							.hasNext();) {
						Category category = (Category) iterator.next();
						if(CONSTRAINT_CATEGORY.equals(category.getId())) {
							return true;
						}
					}
				}
				return false;
			}
			
		});
	}

	public boolean validate(EObject eObject, DiagnosticChain diagnostics,
			Map context) {
		return validate(eObject.eClass(), eObject, diagnostics, context);
	}

	/**
	 * Implements validation by delegation to the EMF validation framework.
	 */
	public boolean validate(EClass eClass, EObject eObject,
			DiagnosticChain diagnostics, Map context) {
		// first, do whatever the basic EcoreValidator does
		// super.validate(eClass, eObject, diagnostics, context);

		IStatus status = Status.OK_STATUS;

		IValidationManager validationMgr = LibraryEditUtil.getInstance().getValidationManager();
		boolean checkCircular = validationMgr == null ? false : validationMgr.isCircularDependancyCheck();
		boolean circularCheckOk = true;
		DependencyValidationMgr mgr = (DependencyValidationMgr) context.get(CTX_DEPENDENCY_VALIDATION_MGR);
		if (checkCircular && DependencyChecker.newCheck && mgr != null && 
				(eObject instanceof VariabilityElement || eObject instanceof MethodPlugin)) {
			status = mgr.checkCircularDependnecy((MethodElement) eObject);
			if(!status.isOK()) {
				circularCheckOk = false;
				String msg = status.getMessage();
				if(StrUtil.isBlank(msg)) {
					//msg = NLS.bind(ValidationResources.circularDependency_error, ref.getEContainingClass().getName(), ref.getName());
					msg = ValidationResources.circularDependency_error;
				}
				appendDiagnostics(status, diagnostics);
			}
		}
		
		// no point in validating if we can't report results
		if (diagnostics != null) {
			// if EMF Mode Validation Service already covered the sub-tree,
			// which it does for efficient computation and error reporting,
			// then don't repeat (the Diagnostician does the recursion
			// externally). If there is no context map, then we can't
			// help it
			if (!hasProcessed(eObject, context)) {
				if (getTraversalType(context) == LibraryTraversalStrategy.DEEP) {
					batchValidator
							.setTraversalStrategy(LibraryTraversalStrategy.deepTraversalStrategy);
				}
				
				IConstraintFilter filter = (IConstraintFilter) context.get(CTX_CONSTRAINT_FILTER);
				if(filter != null) {
					batchValidator.addConstraintFilter(filter);
				}
				try {
					boolean checkName = validationMgr == null ? false : validationMgr.isNameCheck();
					if (checkName) {
						status = batchValidator.validate(eObject,
							new NullProgressMonitor());
					}
				} finally {
					if (filter != null) {
						batchValidator.removeConstraintFilter(filter);
					}
				}

				processed(eObject, context, status);

				appendDiagnostics(status, diagnostics);
			}
		}

		if (eObject instanceof ProcessComponent) {
			Process proc = ((ProcessComponent) eObject).getProcess();
			if (proc != null) {
				validateProcess(proc, diagnostics, context);
			}
		}

		return circularCheckOk && status.isOK();
	}
	
	/**
	 * @param proc
	 * @param diagnostics
	 * @param context
	 */
	public void validateProcess(Process proc, final DiagnosticChain diagnostics,
			Map context) {
		Object o = context.get(CTX_ADAPTER_FACTORY_PROVIDER);
		if (!(o instanceof IAdapterFactoryProvider)) {
			return;
		}

		class FilteredAdapterFactoryTreeIterator extends AdapterFactoryTreeIterator {
			
			public FilteredAdapterFactoryTreeIterator(AdapterFactory adapterFactory, Object object) {
				super(adapterFactory, object);
			}
			
			protected boolean accept(Object child) {
				return child instanceof NamedElement;
			}
			
			/**
			 * Comment for <code>serialVersionUID</code>
			 */
			private static final long serialVersionUID = 0L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.emf.edit.provider.AdapterFactoryTreeIterator#getChildren(java.lang.Object)
			 */
			protected Iterator getChildren(Object o) {
				ArrayList children = new ArrayList();
				for (Iterator iter = super.getChildren(o); iter.hasNext();) {
					Object child = iter.next();
					if (child instanceof MethodElement
							|| (child instanceof DescribableElementWrapperItemProvider
							&& !((DescribableElementWrapperItemProvider) child).isReadOnly())) {
						children.add(child);
						child = TngUtil.unwrap(child);
					}
					if (accept(child)) {
						try {
							ArrayList features = new ArrayList();
							features.add(UmaPackage.Literals.NAMED_ELEMENT__NAME);
							if(child instanceof DescribableElement) {
								features.add(UmaPackage.Literals.METHOD_ELEMENT__PRESENTATION_NAME);
							}
							NamedElement e = (NamedElement) child;
							if (e instanceof BreakdownElement) {
								//continue;
							}
							if(DEBUG) {
								System.out
										.println("FilteredAdapterFactoryTreeIterator.getChildren(): validating '" + e.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$
							}
							for (Iterator iterator = features.iterator(); iterator
									.hasNext();) {
								EStructuralFeature feature = (EStructuralFeature) iterator.next();
								IValidator validator = IValidatorFactory.INSTANCE
										.createValidator(o, null,
												(EObject) child, feature,
												adapterFactory);
								Object itemProvider = adapterFactory.adapt(child, ITreeItemContentProvider.class);
								String prop = (String) featureToPropertyMap.get(feature);
								String name;
								if(itemProvider instanceof IBSItemProvider && prop != null) {
									name = ((IBSItemProvider)itemProvider).getAttribute(child, prop);
								}
								else {
									name = (String) e.eGet(feature);
								}
								String msg = validator.isValid(name);
								if (msg != null) {
									appendDiagnostics(new ValidationStatus(
											IStatus.ERROR, 0, msg, e, feature),
											diagnostics);
								}
							}
						} catch (Exception e) {

						}
					}
				}				
				return children.iterator();
			}
		};
		IAdapterFactoryProvider provider = (IAdapterFactoryProvider) o;
		Iterator iterator = new FilteredAdapterFactoryTreeIterator(provider
				.getWBSAdapterFactory(), proc);
		for (; iterator.hasNext(); iterator.next())
			;
		
		iterator = new FilteredAdapterFactoryTreeIterator(provider.getTBSAdapterFactory(), proc) {
			/**
			 * Comment for <code>serialVersionUID</code>
			 */
			private static final long serialVersionUID = 1L;

			/* (non-Javadoc)
			 * @see #accept(java.lang.Object)
			 */
			protected boolean accept(Object child) {
				return child instanceof RoleDescriptor || child instanceof TeamProfile;
			}
		};
		for (; iterator.hasNext(); iterator.next())
			;
		
		iterator = new FilteredAdapterFactoryTreeIterator(provider.getWPBSAdapterFactory(), proc) {
			/**
			 * Comment for <code>serialVersionUID</code>
			 */
			private static final long serialVersionUID = 1L;

			/* (non-Javadoc)
			 * @see #accept(java.lang.Object)
			 */
			protected boolean accept(Object child) {
				return child instanceof WorkProductDescriptor;
			}
		};
		for (; iterator.hasNext(); iterator.next())
			;
	}

	private static int getTraversalType(Map context) {
		Object type = context.get(LibraryTraversalStrategy.TRAVERSAL_TYPE);
		return (type instanceof Number && ((Number) type).intValue() == LibraryTraversalStrategy.DEEP) ? LibraryTraversalStrategy.DEEP
				: LibraryTraversalStrategy.SHADOW;
	}

	/**
	 * Direct validation of {@link EDataType}s is not supported by the EMF
	 * validation framework; they are validated indirectly via the
	 * {@link EObject}s that hold their values.
	 */
	public boolean validate(EDataType eDataType, Object value,
			DiagnosticChain diagnostics, Map context) {
		return super.validate(eDataType, value, diagnostics, context);
	}

	/**
	 * If we have a context map, record this object's <code>status</code> in
	 * it so that we will know later that we have processed it and its sub-tree.
	 * 
	 * @param eObject
	 *            an element that we have validated
	 * @param context
	 *            the context (may be <code>null</code>)
	 * @param status
	 *            the element's validation status
	 */
	private void processed(EObject eObject, Map context, IStatus status) {
		if (context != null) {
			context.put(eObject, status);
		}
	}

	/**
	 * Determines whether we have processed this <code>eObject</code> before,
	 * by automatic recursion of the EMF Model Validation Service. This is only
	 * possible if we do, indeed, have a context.
	 * 
	 * @param eObject
	 *            an element to be validated (we hope not)
	 * @param context
	 *            the context (may be <code>null</code>)
	 * @return <code>true</code> if the context is not <code>null</code> and
	 *         the <code>eObject</code> or one of its containers has already
	 *         been validated; <code>false</code>, otherwise
	 */
	private boolean hasProcessed(EObject eObject, Map context) {
		boolean result = false;

		if (context != null) {
			// this is O(NlogN) but there's no helping it
			while (eObject != null) {
				if (context.containsKey(eObject)) {
					result = true;
					eObject = null;
				} else {
					eObject = eObject.eContainer();
				}
			}
		}

		return result;
	}

	/**
	 * Converts a status result from the EMF validation service to diagnostics.
	 * 
	 * @param status
	 *            the EMF validation service's status result
	 * @param diagnostics
	 *            a diagnostic chain to accumulate results on
	 */
	public static void appendDiagnostics(IStatus status, DiagnosticChain diagnostics) {
		if (status.isMultiStatus()) {
			IStatus[] children = status.getChildren();

			for (int i = 0; i < children.length; i++) {
				appendDiagnostics(children[i], diagnostics);
			}
		} else if (status instanceof IConstraintStatus) {
			diagnostics.add(new BasicDiagnostic(status.getSeverity(), status
					.getPlugin(), status.getCode(), status.getMessage(),
					((IConstraintStatus) status).getResultLocus().toArray()));
		} else if (status instanceof org.eclipse.epf.library.edit.validation.ValidationStatus) {
			diagnostics.add(new BasicDiagnostic(status.getSeverity(), status
					.getPlugin(), status.getCode(), status.getMessage(),
					new Object[] { ((org.eclipse.epf.library.edit.validation.ValidationStatus) status)
							.getCheckedObject() }));
		} else {
			diagnostics.add(new BasicDiagnostic(status.getSeverity(), status
					.getPlugin(), status.getCode(), status.getMessage(),
					null));
		}
	}

}
