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
package org.eclipse.epf.library.edit.validation.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.AbstractStringValidator;
import org.eclipse.epf.library.edit.validation.IValidator;
import org.eclipse.epf.library.edit.validation.IValidatorFactory;
import org.eclipse.epf.library.edit.validation.NameChecker;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.NamedElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.UmaPackage;
import org.osgi.framework.Bundle;

/**
 * Implementation of IValidatorFactory.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class ValidatorFactory implements IValidatorFactory {
	private static List contributedValidatorFactories;

	private static IValidatorFactory instance;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.validation.IValidatorFactory#createNameValidator(org.eclipse.epf.uma.ContentElement)
	 */
	public IValidator createNameValidator(Object parent, ContentElement e) {
		if (parent instanceof CustomCategory && e instanceof CustomCategory) {
			return new ContentElementNameValidator((EObject) parent,
					UmaPackage.eINSTANCE
							.getCustomCategory_CategorizedElements(), e,
					new TypeFilter(e));
		}
		EObject container = null;
		if (parent instanceof EObject) {
			container = (EObject) parent;
		}
		return new ContentElementNameValidator(container, e, new TypeFilter(e));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.validation.IValidatorFactory#createPresentationNameValidator(org.eclipse.epf.uma.ContentElement)
	 *      Changed from ContentElement to DescribableElement, because
	 *      PresentationName attribute is available in DescribableElement.
	 */
	public IValidator createPresentationNameValidator(Object parent,
			DescribableElement e) {
		if (parent instanceof EObject) {
			Object[] arr = getChildReferenceAndFilter(parent, e);
			EReference reference = (EReference) arr[0];
			IFilter childFilter = (IFilter) arr[1];
			if (reference != null) {
				if (childFilter == null) {
					childFilter = new TypeFilter(e);
				}
				return new UniquenessValidator((EObject) parent, reference,
						childFilter, e, UmaPackage.eINSTANCE
								.getMethodElement_PresentationName()) {
					/*
					 * (non-Javadoc)
					 * 
					 * @see org.eclipse.epf.library.edit.validation.internal.UniquenessValidator#isValid(java.lang.String)
					 */
					public String isValid(String newText) {
						if (StrUtil.isBlank(newText)
								&& NameChecker
										.canHaveEmptyPresentationName((DescribableElement) object)) {
							return null;
						}
						return super.isValid(newText);
					}
				};
			}
		}

		return DoNothingValidator.INSTANCE;
	}

	public static class TypeFilter implements IFilter {
		private int classID;

		private EClass eClass;

		/**
		 * 
		 */
		public TypeFilter(EObject obj) {
			classID = obj.eClass().getClassifierID();
		}

		public TypeFilter(EClass eClass) {
			this.eClass = eClass;
		}

		public boolean accept(Object obj) {
			if (eClass != null) {
				return eClass.isInstance(obj);
			}

			return obj instanceof EObject
					&& classID == ((EObject) obj).eClass().getClassifierID();
		}

	}

	public static final IFilter nonFilter = new IFilter() {

		public boolean accept(Object obj) {
			return true;
		}

	};

	private static Object[] getChildReferenceAndFilter(Object parent,
			Object child) {
		EReference reference = null;
		IFilter childFilter = null;
		if (child instanceof ContentElement) {
			if (parent instanceof CustomCategory
					&& child instanceof CustomCategory) {
				reference = UmaPackage.Literals.CUSTOM_CATEGORY__CATEGORIZED_ELEMENTS;
				childFilter = new TypeFilter(
						UmaPackage.Literals.CUSTOM_CATEGORY);
			} else if (parent instanceof ContentPackage) {
				reference = UmaPackage.Literals.CONTENT_PACKAGE__CONTENT_ELEMENTS;
			}
		} else if (child instanceof MethodPackage
				&& parent instanceof MethodPackage) {
			reference = UmaPackage.Literals.METHOD_PACKAGE__CHILD_PACKAGES;
		} else if (parent instanceof MethodLibrary) {
			if (child instanceof MethodPlugin) {
				reference = UmaPackage.Literals.METHOD_LIBRARY__METHOD_PLUGINS;
				childFilter = nonFilter;
			} else if (child instanceof MethodConfiguration) {
				reference = UmaPackage.Literals.METHOD_LIBRARY__PREDEFINED_CONFIGURATIONS;
				childFilter = nonFilter;
			}
		}
		return new Object[] { reference, childFilter };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.validation.IValidatorFactory#createNameValidator(org.eclipse.epf.uma.NamedElement)
	 */
	public IValidator createNameValidator(Object parent, final NamedElement e) {
		final EStructuralFeature feature = UmaPackage.Literals.NAMED_ELEMENT__NAME;
		Object[] arr = getChildReferenceAndFilter(parent, e);
		EReference reference = (EReference) arr[0];
		IFilter childFilter = (IFilter) arr[1];
		if (reference != null) {
			return createValidator((EObject) parent, reference, childFilter, e,
					feature);
		} else if (e instanceof MethodConfiguration
			&& parent instanceof MethodLibrary) {
			return new MethodConfigurationNameValidator(
				(MethodLibrary) parent, (MethodConfiguration) e);
		}
		return DoNothingValidator.INSTANCE;
	}

	public IValidator createNameValidator(Object parent, final NamedElement e, EClass newType) {
		final EStructuralFeature feature = UmaPackage.Literals.NAMED_ELEMENT__NAME;
		Object[] arr = getChildReferenceAndFilter(parent, e);
		EReference reference = (EReference) arr[0];
		IFilter childFilter = new TypeFilter(newType);
		if (reference != null) {
			return createValidator((EObject) parent, reference, childFilter, e,
					feature);
		}
		return DoNothingValidator.INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.validation.IValidatorFactory#createNameValidator(org.eclipse.epf.uma.NamedElement,
	 *      org.eclipse.emf.common.notify.AdapterFactory)
	 */
	public IValidator createNameValidator(NamedElement e,
			AdapterFactory adapterFactory) {
		EStructuralFeature containingFeature = TngUtil.getContainingFeature(e,
				adapterFactory);
		if (containingFeature != null) {
			EObject parent = (EObject) ((ITreeItemContentProvider) adapterFactory
					.adapt(e, ITreeItemContentProvider.class)).getParent(e);
			if (e instanceof ContentElement) {
				return new ContentElementNameValidator(parent,
						containingFeature, (ContentElement) e,
						new TypeFilter(e));
			} else if (e instanceof ProcessComponent
					&& parent instanceof ProcessPackage) {
				return new ProcessComponentNameValidator(
						(ProcessPackage) parent, (ProcessComponent) e);
			} else if (e instanceof MethodPlugin
					&& parent instanceof MethodLibrary) {
				return new MethodPluginNameValidator(
						(MethodLibrary) parent, (MethodPlugin) e);
			} else if (e instanceof MethodConfiguration
					&& parent instanceof MethodLibrary) {
				return new MethodConfigurationNameValidator(
						(MethodLibrary) parent, (MethodConfiguration) e);
			} else {
				return new UniquenessValidator(parent, containingFeature,
						new TypeFilter(e), e, UmaPackage.eINSTANCE
								.getNamedElement_Name());
			}
		}
		return createNameValidator(e.eContainer(), e);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.validation.IValidatorFactory#createValidator(org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.ecore.EReference, org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.ecore.EClass,
	 *      org.eclipse.emf.ecore.EStructuralFeature)
	 */
	public IValidator createValidator(EObject parent, EReference reference,
			EClass eClass, final EObject object, EStructuralFeature feature) {
		IFilter childFilter = eClass != null ? new TypeFilter(eClass) : null;
		return createValidator(parent, reference, childFilter, object, feature);
	}

	public static IValidatorFactory createValidatorFactory() {
		if (contributedValidatorFactories != null) {
			contributedValidatorFactories = new ArrayList();
			IExtensionRegistry extensionRegistry = Platform
					.getExtensionRegistry();
			IExtensionPoint extensionPoint = extensionRegistry
					.getExtensionPoint(LibraryEditPlugin.getDefault().getId(),
							"validationSupport"); //$NON-NLS-1$
			if (extensionPoint != null) {
				IExtension[] extensions = extensionPoint.getExtensions();
				for (int i = 0; i < extensions.length; i++) {
					IExtension extension = extensions[i];
					String pluginId = extension.getNamespaceIdentifier();
					Bundle bundle = Platform.getBundle(pluginId);
					IConfigurationElement[] configElements = extension
							.getConfigurationElements();
					for (int j = 0; j < configElements.length; j++) {
						IConfigurationElement configElement = configElements[j];
						try {
							String className = configElement
									.getAttribute("class"); //$NON-NLS-1$
							if (className != null) {
								Object ext = bundle.loadClass(className)
										.newInstance();
								boolean replace = new Boolean(configElement
										.getAttribute("replace")).booleanValue(); //$NON-NLS-1$
								if (replace) {
									contributedValidatorFactories.clear();
									instance = (IValidatorFactory) ext;
								} else if (instance == null) {
									contributedValidatorFactories.add(ext);
								}
							}
						} catch (Exception e) {
							LibraryEditPlugin.INSTANCE.log(e);
						}
					}
				}
			}

		}
		if (instance == null) {
			instance = new ValidatorFactory();
		}
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.validation.IValidatorFactory#createValidator(org.eclipse.emf.ecore.EClass,
	 *      org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.ecore.EStructuralFeature,
	 *      org.eclipse.emf.common.notify.AdapterFactory)
	 */
	public IValidator createValidator(final Object parent, EClass eClass,
			final EObject object, final EStructuralFeature feature,
			final AdapterFactory adapterFactory) {
		if (object instanceof BreakdownElement) {
			if (feature == UmaPackage.Literals.NAMED_ELEMENT__NAME
					|| feature == UmaPackage.Literals.METHOD_ELEMENT__PRESENTATION_NAME) {
				final BreakdownElement be = (BreakdownElement) object;
				Process proc = TngUtil.getOwningProcess(parent);
				if (proc != null) {
					final Suppression supp = Suppression.getSuppression(proc);
					return new AbstractStringValidator() {

						public String isValid(String newText) {
							return NameChecker.checkName(adapterFactory,
									parent, be, null, feature, newText, supp,
									true);
						}

					};
				}
			}
		}

		return DoNothingValidator.INSTANCE;
	}

	private static IFilter getFilter(IFilter filter, EObject eObject) {
		return filter != null ? filter : (eObject != null ? new TypeFilter(
				eObject) : nonFilter);
	}

	public IValidator createValidator(EObject parent, EReference reference,
			IFilter childFilter, final EObject object,
			EStructuralFeature feature) {
		if (feature == UmaPackage.Literals.NAMED_ELEMENT__NAME) {
			IValidator validator = null;
			if (object instanceof ContentElement) {
				if (reference == UmaPackage.eINSTANCE
						.getContentPackage_ContentElements()
						|| (reference == UmaPackage.Literals.CUSTOM_CATEGORY__CATEGORIZED_ELEMENTS && object instanceof CustomCategory)) {
					validator = new ContentElementNameValidator(parent,
							reference, (ContentElement) object, getFilter(
									childFilter, object));

				} else if ((reference == UmaPackage.Literals.DISCIPLINE_GROUPING__DISCIPLINES)
						|| (reference == UmaPackage.Literals.ROLE_SET_GROUPING__ROLE_SETS)) {
					// Discipline and DisciplineGrouping, RoleSet and
					// RoleSetGrouping
					// handling
					// necessary in order to maintain uniqueness across plugin.
					validator = new UniquenessValidator(parent.eContainer(),
							UmaPackage.eINSTANCE
									.getContentPackage_ContentElements(),
							getFilter(childFilter, object), object, feature);
				} else if ((reference == UmaPackage.Literals.ARTIFACT__CONTAINED_ARTIFACTS)
						|| (reference == UmaPackage.Literals.DISCIPLINE__SUBDISCIPLINE)
						|| (reference == UmaPackage.Literals.PRACTICE__SUB_PRACTICES)
						|| (reference == UmaPackage.Literals.DOMAIN__SUBDOMAINS)
						) {
					validator = new ContentElementNameValidator(parent,
							reference, (ContentElement) object, getFilter(
									childFilter, object));
				}
			} else if (object instanceof ProcessComponent
					&& parent instanceof ProcessPackage
					&& reference == UmaPackage.eINSTANCE
							.getMethodPackage_ChildPackages()) {
				ProcessPackage pkg = (ProcessPackage) parent;
				if (pkg.eResource().getURI().isFile()) {
					return new ProcessComponentNameValidator(
							(ProcessPackage) parent, (ProcessComponent) object);
				} else {
					return new UniquenessValidator((EObject) parent, reference,
							getFilter(childFilter, object), object, feature);
				}
			} else if (parent instanceof MethodLibrary) {
				MethodLibrary lib = (MethodLibrary) parent;
				if (reference == UmaPackage.Literals.METHOD_LIBRARY__METHOD_PLUGINS) {
					if (lib.eResource().getURI().isFile()) {
						return new MethodPluginNameValidator(lib,
								(MethodPlugin) object);
					} else {
						return new UniquenessValidator(
								lib,
								UmaPackage.Literals.METHOD_LIBRARY__METHOD_PLUGINS,
								nonFilter, object, UmaPackage.eINSTANCE
										.getNamedElement_Name());
					}
				} else if (reference == UmaPackage.Literals.METHOD_LIBRARY__PREDEFINED_CONFIGURATIONS) {
					if (lib.eResource().getURI().isFile()) {
						return new MethodConfigurationNameValidator(lib,
								(MethodConfiguration) object);
					} else {
						return new UniquenessValidator(
								lib,
								UmaPackage.Literals.METHOD_LIBRARY__PREDEFINED_CONFIGURATIONS,
								nonFilter, object, UmaPackage.eINSTANCE
										.getNamedElement_Name());
					}
				}
			} else {
				validator = new UniquenessValidator(parent, reference,
						getFilter(childFilter, object), object, feature);
			}

			return validator;
		} else if (feature == UmaPackage.Literals.METHOD_ELEMENT__PRESENTATION_NAME) {
			return createPresentationNameValidator(parent,
					(DescribableElement) object);
		}

		return DoNothingValidator.INSTANCE;
	}

}