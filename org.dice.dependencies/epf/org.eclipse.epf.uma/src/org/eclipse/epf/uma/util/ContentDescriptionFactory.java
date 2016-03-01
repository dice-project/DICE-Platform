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
package org.eclipse.epf.uma.util;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.impl.ContentElementImpl;
import org.eclipse.epf.uma.impl.ProcessElementImpl;

/**
 * Manages the creation of content descriptions.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public final class ContentDescriptionFactory {

	// Maps ContentElement implementation Class to ContentDesciptionInfo.
	private static final Map<EClass, EClass> contentDescClassMap = new HashMap<EClass, EClass>();

	static {
		contentDescClassMap.put(UmaPackage.eINSTANCE.getPractice(),
				UmaPackage.eINSTANCE.getPracticeDescription());
		contentDescClassMap.put(UmaPackage.eINSTANCE.getRole(),
				UmaPackage.eINSTANCE.getRoleDescription());
		contentDescClassMap.put(UmaPackage.eINSTANCE.getTask(),
				UmaPackage.eINSTANCE.getTaskDescription());
		contentDescClassMap.put(UmaPackage.eINSTANCE.getTemplate(),
				UmaPackage.eINSTANCE.getGuidanceDescription());
		contentDescClassMap.put(UmaPackage.eINSTANCE.getExample(),
				UmaPackage.eINSTANCE.getGuidanceDescription());
		contentDescClassMap.put(UmaPackage.eINSTANCE.getReusableAsset(),
				UmaPackage.eINSTANCE.getGuidanceDescription());
		contentDescClassMap.put(UmaPackage.eINSTANCE.getWhitepaper(),
				UmaPackage.eINSTANCE.getGuidanceDescription());
		contentDescClassMap.put(UmaPackage.eINSTANCE.getArtifact(),
				UmaPackage.eINSTANCE.getArtifactDescription());
		contentDescClassMap.put(UmaPackage.eINSTANCE.getDeliverable(),
				UmaPackage.eINSTANCE.getDeliverableDescription());
		contentDescClassMap.put(UmaPackage.eINSTANCE.getOutcome(),
				UmaPackage.eINSTANCE.getWorkProductDescription());

		contentDescClassMap.put(UmaPackage.eINSTANCE.getRoleDescriptor(),
				UmaPackage.eINSTANCE.getDescriptorDescription());
		contentDescClassMap.put(UmaPackage.eINSTANCE.getCompositeRole(),
				UmaPackage.eINSTANCE.getDescriptorDescription());
		contentDescClassMap.put(UmaPackage.eINSTANCE.getTaskDescriptor(),
				UmaPackage.eINSTANCE.getDescriptorDescription());
		contentDescClassMap.put(
				UmaPackage.eINSTANCE.getWorkProductDescriptor(),
				UmaPackage.eINSTANCE.getDescriptorDescription());

		contentDescClassMap.put(UmaPackage.eINSTANCE.getMilestone(),
				UmaPackage.eINSTANCE.getBreakdownElementDescription());
		contentDescClassMap.put(UmaPackage.eINSTANCE.getTeamProfile(),
				UmaPackage.eINSTANCE.getBreakdownElementDescription());

		contentDescClassMap.put(UmaPackage.eINSTANCE.getActivity(),
				UmaPackage.eINSTANCE.getActivityDescription());
		contentDescClassMap.put(UmaPackage.eINSTANCE.getIteration(),
				UmaPackage.eINSTANCE.getActivityDescription());
		contentDescClassMap.put(UmaPackage.eINSTANCE.getPhase(),
				UmaPackage.eINSTANCE.getActivityDescription());
		contentDescClassMap.put(UmaPackage.eINSTANCE.getCapabilityPattern(),
				UmaPackage.eINSTANCE.getProcessDescription());
		contentDescClassMap.put(UmaPackage.eINSTANCE.getDeliveryProcess(),
				UmaPackage.eINSTANCE.getDeliveryProcessDescription());
		contentDescClassMap.put(UmaPackage.eINSTANCE
				.getProcessPlanningTemplate(), UmaPackage.eINSTANCE
				.getProcessDescription());
	}

	public static EClass getContentDescriptionType(EClass describableElementType) {
		EClass type = (EClass) contentDescClassMap.get(describableElementType);
		if(type == null && UmaPackage.eINSTANCE.getDescribableElement().isSuperTypeOf(describableElementType)) {
			type = UmaPackage.eINSTANCE.getContentDescription();
		}
		return type;
	}

	public static final ContentDescription createContentDescription(
			DescribableElement e) {
		EClass eClass = (EClass) contentDescClassMap.get(e.eClass());
		if (eClass == null) {
			eClass = UmaPackage.eINSTANCE.getContentDescription();
		}
		ContentDescription content = (ContentDescription) UmaFactory.eINSTANCE
				.create(eClass);
		content.setName(UmaUtil.createContentDescriptionName(e));
		content.setGuid(UmaUtil.generateGUID(e.getGuid()));
		return content;
	}

	/**
	 * Creates a new ContentDescription object for the given ContentElement and
	 * save it to a file
	 * 
	 * @param e
	 * @return
	 * @deprecated use createContentDescription(DescribableElement e) instead
	 */
	public static ContentDescription createContentDescription(ContentElement e) {
		return createContentDescription(e, false);
	}

	/**
	 * 
	 * @param e
	 * @param saveContentOnly
	 * @return
	 * @deprecated use createContentDescription(DescribableElement e) instead
	 */
	public static ContentDescription createContentDescription(
			DescribableElement e, boolean saveContentOnly) {
		// if ( e instanceof ContentElement)
		// {
		// return createContentDescription((ContentElement)e, saveContentOnly);
		// }
		//		
		// return createContentDescription(e);

		return createContentDescription(e);
	}

	public static ContentDescription createContentDescription(ContentElement e,
			boolean saveContentOnly) {
		ContentDescription description = createContentDescription((DescribableElement) e);

		// Set descriptions = (Set)
		// contentDescriptionsMap.get(descInfo.packageName);
		// if(descriptions == null) {
		// descriptions = new HashSet();
		// contentDescriptionsMap.put(descInfo.packageName, descriptions);
		// }
		// descriptions.add(description);

		// saving ContentDescription right after its creation it no longer
		// needed since ContentDescription is
		// now contained by a DescribableElement
		//
		// if(libPersister != null) {
		// libPersister.save(e, description, saveContentOnly);
		// }
		return description;
	}

	// public static final Map getContentDescriptionsMap() {
	// return contentDescriptionsMap;
	// }

	public static boolean hasPresentation(MethodElement e) {
		if (e instanceof ContentElementImpl) {
			return ((ContentElementImpl) e).basicGetPresentation() != null;
		} else if (e instanceof ProcessElementImpl) {
			return ((ProcessElementImpl) e).basicGetPresentation() != null;
		}
		return false;
	}

	public static EClass getContentDescriptionEClass(DescribableElement e) {
		return (EClass) contentDescClassMap.get(e.eClass());
	}

	public static void main(String[] args) throws Exception {
		// MessageDigest md5 = MessageDigest.getInstance("MD5");
		String input = args[0];
		// byte[] hash = md5.digest(input.getBytes());
		System.out.println("input: '" + input + "'"); //$NON-NLS-1$ //$NON-NLS-2$
		System.out.println("Java's hash code: " + input.hashCode()); //$NON-NLS-1$
		// System.out.println("hash length: " + hash.length);
		// System.out.println("base64 encoded: " +
		// DataValue.Base64.encode(hash));
		System.out.println("new GUID: " + UmaUtil.generateGUID(input)); //$NON-NLS-1$
	}

}