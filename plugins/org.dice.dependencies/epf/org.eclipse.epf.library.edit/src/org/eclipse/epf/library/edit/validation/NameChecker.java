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
package org.eclipse.epf.library.edit.validation;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.AdapterFactoryTreeIterator;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.process.BSActivityItemProvider;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.NamedElement;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.osgi.util.NLS;

/**
 * This class defines various static methods to validate name of specified
 * object
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public final class NameChecker {

	private static final String ELEMENT_TEXT = LibraryEditResources.element_text; 
	
	private static final INameProvider presentationNameProvider = new INameProvider() {

		public String getName(Object object) {
			if(object instanceof BreakdownElement) {
				return ProcessUtil.getPresentationName((BreakdownElement) object);
			}
			else if(object instanceof DescribableElement) {
				return ((DescribableElement)object).getPresentationName();
			}
			return null;
		}
		
	};
	
	private static String checkName(AdapterFactory adapterFactory,
			Object parent, Object e, final Class type,
			INameProvider nameProvider, String newName, Suppression suppresion,
			boolean ignoreSuppressed) {
		IFilter childFilter = new IFilter() {

			public boolean accept(Object obj) {
				return type.isInstance(obj);
			}

		};

		return checkName(adapterFactory, parent, e, childFilter, nameProvider,
				newName, suppresion, ignoreSuppressed);
	}

	private static String checkName(AdapterFactory adapterFactory,
			Object parent, Object e, IFilter childFilter,
			EStructuralFeature nameFeature, String newName,
			Suppression suppression, boolean ignoreSuppressed) {
		return checkName(adapterFactory, parent, e, childFilter,
				createNameProvider(nameFeature), newName, suppression,
				ignoreSuppressed);
	}

	private static String checkName(AdapterFactory adapterFactory,
			Object parent, Object e, IFilter childFilter,
			INameProvider nameProvider, String newName,
			Suppression suppression, boolean ignoreSuppressed) 
	{
		if (ignoreSuppressed)
			if (suppression != null && suppression.isSuppressed(e)) {
				return null;
			}

		ITreeItemContentProvider adapter = (ITreeItemContentProvider) adapterFactory
				.adapt(parent, ITreeItemContentProvider.class);
		Collection children;

		// get children of the rolled-down dapter
		//
		boolean wasRolledUp = false;
		BSActivityItemProvider bsItemProvider = null;
		if (adapter instanceof BSActivityItemProvider) {
			bsItemProvider = ((BSActivityItemProvider) adapter);
			wasRolledUp = bsItemProvider.isRolledUp();
		}
		try {
			if (wasRolledUp) {
				bsItemProvider.setRolledUp(false);
			}
			children = adapter.getChildren(parent);
		} catch (NullPointerException ex) {
			throw ex;
		} finally {
			if (wasRolledUp) {
				bsItemProvider.basicSetRolledUp(wasRolledUp);
			}
		}
		
		if (e instanceof BreakdownElement) {
			return null;
		}

		for (Iterator iter = children.iterator(); iter.hasNext();) {
			Object item = iter.next();

			// skip checking on suppressed element
			//
/*170058	if (suppression != null && suppression.isSuppressed(item)) {
				continue;
			}*/

			Object child = TngUtil.unwrap(item);
			if (child != e && childFilter.accept(child)) {
				String name = nameProvider.getName(child);
				if (name.equalsIgnoreCase(newName)) {
					// return I18nUtil.formatString(RESOURCE_BUNDLE, key, data);
					return NLS.bind(
							LibraryEditResources.duplicateElementNameError_msg,
							name);
				}
			}
		}
		return null;
	}

	private static INameProvider createNameProvider(
			final EStructuralFeature nameFeature) {
		if(nameFeature == UmaPackage.eINSTANCE
								.getMethodElement_PresentationName()) {
			return presentationNameProvider; 
		}
		
		return new INameProvider() {

			public String getName(Object object) {
				if (object instanceof EObject) {
					return (String) ((EObject) object).eGet(nameFeature);
				} else {
					return null;
				}
			}

		};
	}

	public static String checkName(AdapterFactory adapterFactory,
			final BreakdownElement e, Class type, EStructuralFeature nameFeature,
			String newName, Suppression suppression) 
	{
		return checkName(adapterFactory, null, e, type, nameFeature, newName,
				suppression, false);
	}
	
	public static String checkName(AdapterFactory adapterFactory, Object parent,
			final BreakdownElement e, Class type, final EStructuralFeature nameFeature,
			String newName, Suppression suppression, boolean ignoreSuppressed) 
	{
		String msg = checkEmpty(e, newName);
		if(msg != null) {
			return msg;
		}

		if(type == null) {
			type = chooseType(e);
		}
		
		if(parent == null) {
			ITreeItemContentProvider itemProvider = (ITreeItemContentProvider) adapterFactory
					.adapt(e, ITreeItemContentProvider.class);
			parent = itemProvider.getParent(e);
			if(parent == null) {
				return null;
			}
		}

		// basic check
		//
		msg = checkName(adapterFactory, parent, e, type,
				createNameProvider(nameFeature), newName, suppression,
				ignoreSuppressed);

		if (msg != null)
			return msg;
		IFilter childFilter = null;
		if (e instanceof TeamProfile) {
			// don't allow team with the same name within the same scope
			//
			childFilter = new IFilter() {
				public boolean accept(Object obj) {
					return e instanceof TeamProfile;
				}
			};
		}

		// PLEASE DON'T CLEAN UP.
		// Commented out for now, since we change scope of Deliverable.
		// else if (e instanceof WorkProductDescriptor
		// && ((WorkProductDescriptor) e).getWorkProduct() instanceof
		// Deliverable) {
		// childFilter = new IFilter() {
		//	
		// public boolean accept(Object obj) {
		// return e instanceof WorkProductDescriptor
		// && ((WorkProductDescriptor) e).getWorkProduct() instanceof
		// Deliverable;
		// }
		// };
		// }

		if (childFilter != null) {
			return checkNameInScope(adapterFactory, parent, e, nameFeature, newName,
					childFilter, suppression, ignoreSuppressed);
		}

		return null;
	}
	
	private static String checkEmpty(Object e, String newName) {
		// skip checking on activity that is a extender or local contributor
		//
		if(e instanceof Activity && ProcessUtil.isExtendingOrLocallyContributing((BreakdownElement) e)) {
			return null;
		}
		
		String elementType;
		if (e instanceof NamedElement) {
			elementType = TngUtil.getTypeText((NamedElement) e);
		} else {
			elementType = ELEMENT_TEXT;
		}
		if (newName == null || newName.trim().length() == 0) {			
			return NLS.bind(LibraryEditResources.emptyElementNameError_msg,
					StrUtil.toLower(elementType));
		}
		return null;
	}

	/**
	 * Used by
	 * {@link #checkName(AdapterFactory, BreakdownElement, EStructuralFeature, String, Suppression, boolean) checkName()}
	 * 
	 * @param e
	 * @return
	 */
	private static Class chooseType(BreakdownElement e) {
		if (e instanceof Activity) {
			return Activity.class;
		} else if (e instanceof TaskDescriptor) {
			return TaskDescriptor.class;
		} else if (e instanceof RoleDescriptor) {
			return RoleDescriptor.class;
		} else if (e instanceof WorkProductDescriptor) {
			return WorkProductDescriptor.class;
		} else {
			return e.getClass();
		}
		// return BreakdownElement.class;
	}

	private static String checkNameInScope(AdapterFactory adapterFactory,
			Object currentParent, BreakdownElement e,
			EStructuralFeature feature, String name, IFilter childFilter,
			Suppression suppression, boolean ignoreSuppressed) {
		// check up
		for (Object parent = currentParent; parent != null;) {
			String msg = checkName(adapterFactory, parent, e, childFilter,
					feature, name, suppression, ignoreSuppressed);
			if (msg != null)
				return msg;
			ITreeItemContentProvider itemProvider = (ITreeItemContentProvider) adapterFactory
					.adapt(parent, ITreeItemContentProvider.class);
			parent = itemProvider.getParent(parent);
		}

		// check down
		if (currentParent != null) {
			Iterator iter = new AdapterFactoryTreeIterator(adapterFactory,
					currentParent, false);
			while (iter.hasNext()) {
				Object child = TngUtil.unwrap(iter.next());
				if (child != e && childFilter.accept(child)) {
					String childName = (String) ((EObject) child).eGet(feature);
					if (name.equalsIgnoreCase(childName)) {
						// return I18nUtil.formatString(RESOURCE_BUNDLE, key,
						// data);
						return NLS
								.bind(
										LibraryEditResources.duplicateElementNameError_msg,
										name);
					}

				}
			}
		}

		return null;
	}

	public static String checkName(AdapterFactory adapterFactory,
			final BreakdownElement e, EStructuralFeature feature, String name,
			Suppression suppression) {
		return checkName(adapterFactory, e, feature, name, suppression, false);
	}

	/**
	 * Checks the name or presentation name of a breakdown element that is
	 * already part of a process.
	 * 
	 * @param adapterFactory
	 * @param e
	 * @param feature
	 * @param name
	 * @param suppression
	 * @param ignoreSuppressed
	 * @return
	 */
	public static String checkName(AdapterFactory adapterFactory,
			final BreakdownElement e, EStructuralFeature feature, String name,
			Suppression suppression, boolean ignoreSuppressed) 
	{
		return checkName(adapterFactory, (Object)null, e, (Class)null, feature, name, suppression, ignoreSuppressed);
	}
	
	public static boolean canHaveEmptyPresentationName(DescribableElement e) {
		if(e instanceof VariabilityElement) {
			VariabilityType vType = ((VariabilityElement)e).getVariabilityType();
			return vType == VariabilityType.EXTENDS
				|| vType == VariabilityType.CONTRIBUTES
				|| vType == VariabilityType.LOCAL_CONTRIBUTION;			
		}
		return false;	
	}
	
	public static boolean checkFilePathLength(
			MethodElement parentElementWithResource,
			MethodElement element,
			String name,
			int maxLength,
			String[] filePathName) {
		Resource res = parentElementWithResource.eResource();
		URI uri = res.getURI();
		if (filePathName != null && filePathName.length >= 1) {
			//To do: calculate path
			//filePathName[0] = ??
		}		
		
		int parentResLen = uri.toFileString().length();		
		int localGroupLen = 20;
		if (parentElementWithResource instanceof MethodLibrary) {
			localGroupLen = 0;
		} else if (element instanceof Role) {
			localGroupLen = 5;		//"roles"
		} else if (element instanceof CapabilityPattern) {
			localGroupLen = 18;		//"capabilitypatterns"
		}
		//More else if here
		
		int totalLen = parentResLen + localGroupLen + name.length();		
		return totalLen <= maxLength;	
	}
}
