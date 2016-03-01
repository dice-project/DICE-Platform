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
package org.eclipse.epf.library.edit.category;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.epf.library.edit.IDefaultNameSetter;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.IStatefulItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.PresentationContext;
import org.eclipse.epf.library.edit.command.MethodElementAddCommand;
import org.eclipse.epf.library.edit.internal.IListenerProvider;
import org.eclipse.epf.library.edit.util.LibraryEditConstants;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * The item provider adapter for a discipline.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class DisciplineItemProvider extends
		org.eclipse.epf.uma.provider.DisciplineItemProvider implements
		ILibraryItemProvider, IStatefulItemProvider, IDefaultNameSetter, IListenerProvider {

	private Object parent;

	/**
	 * Creates a new instance.
	 */
	public DisciplineItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
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
//		if (parent != null)
//			return parent;
//		return super.getParent(object);
		Discipline discipline = (Discipline)object;
		if(discipline.eContainer() instanceof Discipline){
			parent =  super.getParent(discipline);
			return parent;
		}
		
		MethodPlugin model = UmaUtil.getMethodPlugin(discipline);
		if (model == null)
			return null;

		String[] path = {
				LibraryEditPlugin.INSTANCE.getString("_UI_Content_group") //$NON-NLS-1$
				,
				LibraryEditPlugin.INSTANCE
						.getString("_UI_Standard_Categories_group") //$NON-NLS-1$
				, LibraryEditPlugin.INSTANCE.getString("_UI_Disciplines_group") //$NON-NLS-1$
		};

		return TngUtil.getAdapter(model, path);
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
				.getDiscipline_Subdiscipline(), UmaFactory.eINSTANCE.createDiscipline()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildrenFeatures(java.lang.Object)
	 */
	public Collection getChildrenFeatures(Object object) {
		//return Collections.EMPTY_LIST;
		if(childrenFeatures == null){
			childrenFeatures = new ArrayList();
			childrenFeatures.add(UmaPackage.eINSTANCE.getDiscipline_Subdiscipline());
		}
		return childrenFeatures;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		TngUtil.setDefaultName(this, notification);
		updateChildren(notification);
		TngUtil.refreshParentIfNameChanged(notification, this);
		switch (notification.getFeatureID(Discipline.class)) {
		case UmaPackage.DISCIPLINE__NAME:
			TngUtil.refreshContributors(this, notification, false, true);
			break;
		case UmaPackage.DISCIPLINE__TASKS:
			fireNotifyChanged(new ViewerNotification(notification, notification
					.getNotifier(), true, false));
			return;

		case UmaPackage.DISCIPLINE__REFERENCE_WORKFLOWS:
			fireNotifyChanged(new ViewerNotification(notification, notification
					.getNotifier(), true, false));
			// refresh uncategorized tasks folder
			// TngUtil.refreshAdapter(LibraryEditConstants.UNCATEGORIZED_TASKS_PATH,
			// notification);
			return;
		case UmaPackage.DISCIPLINE__SUBDISCIPLINE:
			fireNotifyChanged(new ViewerNotification(notification, notification
					.getNotifier(), true, false));
			return;		
		case UmaPackage.DISCIPLINE__VARIABILITY_BASED_ON_ELEMENT:
			fireNotifyChanged(new ViewerNotification(notification, notification
				.getNotifier(), true, false));
			return;
			
		}

		super.notifyChanged(notification);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		return TngUtil.getLabel((VariabilityElement) object,
				getString("_UI_Discipline_type"), true); //$NON-NLS-1$
	}

	protected boolean isWrappingNeeded(Object object) {
		return true;
	}

	protected Object createWrapper(EObject object, EStructuralFeature feature,
			Object value, int index) {
		if (!isWrappingNeeded(object))
			return value;
		return TngUtil.createWrapper(adapterFactory, object, feature, value,
				index);
	}
	
	public List getNotifyChangedListeners() {
		if(changeNotifier instanceof Collection) {
			return new ArrayList((Collection) changeNotifier);
		}
		return Collections.EMPTY_LIST;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#hasChildren(java.lang.Object)
	 */
	public boolean hasChildren(Object object) {
		// Change Categories display in Library Navigator
		// (commented for this defect)
		// return !((Discipline) object).getTasks().isEmpty();
		//return false;
		return super.hasChildren(object);
	}

	protected Command createAddCommand(EditingDomain domain, EObject owner,
			EStructuralFeature feature, Collection collection, int index) {
		// return new MethodElementAddCommand((AddCommand)
		// super.createAddCommand(domain, owner, feature, collection, index));
		//return UnexecutableCommand.INSTANCE;
		Collection selection = new ArrayList();
		for (Iterator iter = collection.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (element instanceof Discipline) {
				selection.add(element);
			}
		}
		if (selection.isEmpty()) {
			return UnexecutableCommand.INSTANCE;
		}

		return new MethodElementAddCommand((AddCommand) super.createAddCommand(
				domain, owner, feature, collection, index));
	}

	public int getInterestedFeatureID() {
		// TODO Auto-generated method stub
		return UmaPackage.DISCIPLINE__SUBDISCIPLINE;
	}

	public Class getInterestedFeatureOwnerClass() {
		// TODO Auto-generated method stub
		return Discipline.class;
	}

	public void setDefaultName(Object obj) {
		// TODO Auto-generated method stub
		String baseName = null;
		if (obj instanceof Discipline) {
			baseName = LibraryEditConstants.NEW_DISCIPLINE;
		}
		if (baseName != null) {
			TngUtil.setDefaultName(((Discipline) target).getSubdiscipline(),
					(MethodElement) obj, baseName);
		}
	}
	
	public Collection getChildren(Object object) {
		// TODO Auto-generated method stub
		//return super.getChildren(object);
		Collection children = super.getChildren(object);
		if (children instanceof List)
			Collections.sort((List) children, PresentationContext.INSTANCE.getComparator());
		return children;
	}
	
}
