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
package org.eclipse.epf.library.edit.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.library.edit.IStatefulItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.PresentationContext;
import org.eclipse.epf.library.edit.command.MethodElementAddCommand;
import org.eclipse.epf.library.edit.command.MethodElementCreateChildCommand;
import org.eclipse.epf.library.edit.process.IProcessItemProvider;
import org.eclipse.epf.library.edit.process.command.CreateProcessComponentCommand;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.provider.UmaEditPlugin;

/**
 * The item provider adapter for a process package in the Library view.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class ProcessPackageItemProvider extends
		org.eclipse.epf.uma.provider.ProcessPackageItemProvider implements
		IProcessItemProvider, IStatefulItemProvider {

	protected EClass processType;

	private String label;

	private Object parent;

	/**
	 * Creates a new instance.
	 */
	public ProcessPackageItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	public EClass getProcessType() {
		return processType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#collectNewChildDescriptors(java.util.Collection,
	 *      java.lang.Object)
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getMethodPackage_ChildPackages(), UmaFactory.eINSTANCE
				.createProcessPackage()));

		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getMethodPackage_ChildPackages(), UmaFactory.eINSTANCE
				.createProcessComponent()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildrenFeatures(java.lang.Object)
	 */
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			childrenFeatures = new ArrayList();
			childrenFeatures.add(UmaPackage.eINSTANCE
					.getMethodPackage_ChildPackages());
		}
		return childrenFeatures;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		Collection children = super.getChildren(object);
		for (Iterator iter = children.iterator(); iter.hasNext();) {
			Object child = iter.next();
			if (!(child instanceof ProcessComponent)
					&& (child instanceof ProcessPackage)) {
				ProcessPackageItemProvider itemProvider = (ProcessPackageItemProvider) getRootAdapterFactory()
						.adapt(child, ITreeItemContentProvider.class);
				if (processType != null)
					itemProvider.setProcessType(processType);
			}
		}

		Collections.sort((List) children, PresentationContext.INSTANCE
				.getProcessPackageComparator());

		return children;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		if (label != null)
			return label;
		return TngUtil.getLabel(object);
	}

	public Object getImage(Object object) {
		EClass procType = getProcessType();
		if (procType != null) {
			Object parent = getParent(object);
			if (parent != null
					&& parent instanceof org.eclipse.epf.library.edit.navigator.ProcessesItemProvider) {
				if (procType
						.equals(UmaPackage.eINSTANCE.getCapabilityPattern()))
					return LibraryEditPlugin.INSTANCE
							.getImage("full/obj16/CapabilityPatterns"); //$NON-NLS-1$
				else if (procType.equals(UmaPackage.eINSTANCE
						.getDeliveryProcess()))
					return LibraryEditPlugin.INSTANCE
							.getImage("full/obj16/DeliveryProcesses"); //$NON-NLS-1$
			}
		}
		return UmaEditPlugin.INSTANCE.getImage("full/obj16/ProcessPackage"); //$NON-NLS-1$
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setProcessType(EClass processType) {
		this.processType = processType;
	}

	protected Command createCreateChildCommand(EditingDomain domain,
			EObject owner, EStructuralFeature feature, Object value, int index,
			Collection collection) {
		// System.out.println("ProcessPackageItemProvider.createCreateChildCommand()");
		if (value instanceof ProcessComponent) {
			return new CreateProcessComponentCommand(domain, owner, feature,
					value, index, collection, this);
		}
		// return super.createCreateChildCommand(domain, owner, feature, value,
		// index, collection);
		return new MethodElementCreateChildCommand(domain, owner, feature,
				value, index, collection, this);
	}

	public String getCreateChildText(Object owner, Object feature,
			Object child, Collection selection) {
		if (child instanceof ProcessComponent) {
			EClass processType = getProcessType();
			if (processType != null) {
				if (processType.equals(UmaPackage.eINSTANCE
						.getCapabilityPattern())) {
					return LibraryEditPlugin.INSTANCE
							.getString("_UI_CapabilityPattern_text"); //$NON-NLS-1$
				} else if (processType.equals(UmaPackage.eINSTANCE
						.getDeliveryProcess())) {
					return LibraryEditPlugin.INSTANCE
							.getString("_UI_DeliveryProcess_text"); //$NON-NLS-1$
				} else if (processType == UmaPackage.eINSTANCE
						.getProcessPlanningTemplate()) {
					return getString("_UI_ProcessPlanningTemplate_type"); //$NON-NLS-1$
				}
			}
		}

		return super.getCreateChildText(owner, feature, child, selection);
	}

	public Object getCreateChildImage(Object owner, Object feature,
			Object child, Collection selection) {
		if (child instanceof ProcessComponent) {
			EClass processType = getProcessType();
			if (processType != null) {
				if (processType.equals(UmaPackage.eINSTANCE
						.getCapabilityPattern())) {
					return UmaEditPlugin.INSTANCE
							.getImage("full/obj16/CapabilityPattern"); //$NON-NLS-1$
				} else if (processType.equals(UmaPackage.eINSTANCE
						.getDeliveryProcess())) {
					return UmaEditPlugin.INSTANCE
							.getImage("full/obj16/DeliveryProcess"); //$NON-NLS-1$
				} else if (processType == UmaPackage.eINSTANCE
						.getProcessPlanningTemplate()) {
					return UmaEditPlugin.INSTANCE
							.getImage("full/obj16/ProcessPlanningTemplate"); //$NON-NLS-1$
				}
			}
		}

		return super.getCreateChildImage(owner, feature, child, selection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#createAddCommand(org.eclipse.emf.edit.domain.EditingDomain,
	 *      org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.ecore.EStructuralFeature, java.util.Collection, int)
	 */
	protected Command createAddCommand(EditingDomain domain, EObject owner,
			EStructuralFeature feature, Collection collection, int index) {
		Collection selection = new ArrayList();
		for (Iterator iter = collection.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (element instanceof ProcessPackage) {
				// check to exclude any pasted-in package that contains
				// process of different type than that of this package
				//
				if (!ProcessUtil.hasProcessNotOfType((ProcessPackage) element,
						processType)) {
					selection.add(element);
				}
			}
		}
		if (selection.isEmpty()) {
			return UnexecutableCommand.INSTANCE;
		}

		// return super.createAddCommand(domain, owner, feature, collection,
		// index);
		return new MethodElementAddCommand((AddCommand) super.createAddCommand(
				domain, owner, feature, selection, index)) {
			protected void featureChanged(EObject object,
					EStructuralFeature feature, Object newValue) {
				if (object instanceof ProcessComponent
						&& feature == UmaPackage.eINSTANCE
								.getNamedElement_Name()) {
					Process proc = ((ProcessComponent) object).getProcess();
					if (proc != null) {
						proc.setName((String) newValue);
					}
				}
			}
		};
	}

	public void setParent(Object parent) {
		this.parent = parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getParent(java.lang.Object)
	 */
	public Object getParent(Object object) {
		if (parent != null)
			return parent;
		return super.getParent(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.ProcessPackageItemProvider#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		TngUtil.refreshParentIfNameChanged(notification, this);

		super.notifyChanged(notification);
	}
}
