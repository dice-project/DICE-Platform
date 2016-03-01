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
package org.eclipse.epf.persistence.migration.internal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.epf.persistence.MultiFileResourceSetImpl;
import org.eclipse.epf.persistence.migration.UMA2UMAResourceHandler;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.ProcessElement;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkOrder;
import org.eclipse.epf.uma.WorkOrderType;

/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class UMA2UMAResourceHandler0 extends UMA2UMAResourceHandler {

	private Set procMovedFeatureNames;

	private Map guidToPresentationMap;

	private Set classNamesToSavePresentationURI;

	public UMA2UMAResourceHandler0() {
		super();

		guidToPresentationMap = new HashMap();

		procMovedFeatureNames = new HashSet(Arrays.asList(new String[] {
				"purpose" //$NON-NLS-1$
				, "keyConsiderations" //$NON-NLS-1$
				, "alternatives" //$NON-NLS-1$
				, "howtoStaff" //$NON-NLS-1$
				, "usageGuidance" //$NON-NLS-1$
				, "externalId" //$NON-NLS-1$
				, "scope" //$NON-NLS-1$
				, "usageNotes" //$NON-NLS-1$
				, "scale" //$NON-NLS-1$
				, "projectCharacteristics" //$NON-NLS-1$
				, "riskLevel" //$NON-NLS-1$
				, "estimatingTechnique" //$NON-NLS-1$
				, "projectMemberExpertise" //$NON-NLS-1$
				, "typeOfContract" //$NON-NLS-1$
				, "techniques" //$NON-NLS-1$
				, "mainDescription" //$NON-NLS-1$
		}));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.persistence.migration.UMA2UMAResourceHandler#getNewFeature(org.eclipse.emf.ecore.EObject,
	 *      java.lang.String)
	 */
	protected EStructuralFeature getNewFeature(EObject owner, String featureName) {
		if ("presentationName".equals(featureName) && owner instanceof ContentDescription) { //$NON-NLS-1$
			return UmaPackage.eINSTANCE
					.getMethodElement_PresentationName();
		}
		if (procMovedFeatureNames.contains(featureName)) {
			if (owner instanceof ProcessElement) {
				ContentDescription content = ((ProcessElement) owner)
						.getPresentation();
				EAttribute attrib = getEAttribute(content.eClass(), featureName);
				if (attrib != null) {
					return attrib;
				}
			}
		}
		if ("WorkOrder".equals(featureName) && owner instanceof ProcessPackage) { //$NON-NLS-1$
			return UmaPackage.eINSTANCE.getProcessPackage_ProcessElements();
		}

		return super.getNewFeature(owner, featureName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.persistence.migration.UMA2UMAResourceHandler#getNewOwner(org.eclipse.emf.ecore.EObject,
	 *      java.lang.String)
	 */
	protected EObject getNewOwner(EObject oldOwner, String featureName) {
		if ("presentationName".equals(featureName) && oldOwner instanceof ContentDescription) { //$NON-NLS-1$
			DescribableElement de = ((DescribableElement) oldOwner.eContainer());
			if (de instanceof Activity) {
				return null;
			}
			return de;
		}
		if (procMovedFeatureNames.contains(featureName)
				&& oldOwner instanceof ProcessElement) {
			return ((ProcessElement) oldOwner).getPresentation();
		}
		if ("WorkOrder".equals(featureName) && oldOwner instanceof ProcessPackage) { //$NON-NLS-1$
			return oldOwner;
		}

		return super.getNewOwner(oldOwner, featureName);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.persistence.migration.UMA2UMAResourceHandler#getNewValue(org.eclipse.emf.ecore.EObject, java.lang.String, org.eclipse.emf.ecore.xml.type.AnyType)
	 */
	protected Object getNewValue(EObject oldOwner, String featureName, AnyType value) {
		if ("WorkOrder".equals(featureName) && oldOwner instanceof ProcessPackage) { //$NON-NLS-1$
			WorkOrder workOrder = UmaFactory.eINSTANCE.createWorkOrder();
			workOrder.setGuid((String) getSingleValue(value.getAnyAttribute(),
					"guid")); //$NON-NLS-1$
			String predGuid = (String) getSingleValue(value.getAnyAttribute(),
					"pred"); //$NON-NLS-1$
			if (predGuid == null) {
				InternalEObject obj = (InternalEObject) getSingleValue(value
						.getMixed(), "pred"); //$NON-NLS-1$
				predGuid = obj.eProxyURI().authority();
			}
			MultiFileResourceSetImpl resourceSet = (MultiFileResourceSetImpl) oldOwner
					.eResource().getResourceSet();
			WorkBreakdownElement pred = (WorkBreakdownElement) resourceSet
					.getEObject(predGuid);
			workOrder.setPred(pred);
			workOrder.setLinkType(WorkOrderType.FINISH_TO_FINISH);
			return workOrder;
		}
		return getText(value);
	}

	public void savePresentationURIFor(Set classNames) {
		classNamesToSavePresentationURI = classNames;
	}

	public URI getPresentationURI(String guid) {
		return (URI) guidToPresentationMap.get(guid);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.persistence.migration.MigrationResourceHandler#handleUnknownData(org.eclipse.emf.ecore.EObject, java.lang.Object)
	 */
	protected void handleUnknownData(EObject e, AnyType unknownData) {
		super.handleUnknownData(e, unknownData);
		
		if (classNamesToSavePresentationURI != null
				&& e instanceof ContentPackage) {
			for (Iterator iter = getValue(unknownData.getMixed(), "contentElements").iterator(); iter.hasNext();) { //$NON-NLS-1$
				AnyType value = (AnyType) iter.next();
				if (value != null
						&& classNamesToSavePresentationURI.contains(value
								.eClass().getName())) {
					Object presentation = getSingleValue(value.getMixed(),
							"presentation"); //$NON-NLS-1$
					if (presentation != null) {
						Object guid = getSingleValue(value.getAnyAttribute(),
								"guid"); //$NON-NLS-1$
						URI uri = ((InternalEObject) presentation).eProxyURI();
						guidToPresentationMap.put(guid, uri);
					}
				}
			}
		}		
	}

}
