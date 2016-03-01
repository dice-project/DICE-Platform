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
package org.eclipse.epf.library.edit.element;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.PresentationContext;
import org.eclipse.epf.library.edit.util.LibraryEditConstants;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.library.edit.util.PracticePropUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Checklist;
import org.eclipse.epf.uma.Concept;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.EstimationConsiderations;
import org.eclipse.epf.uma.Example;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.Guideline;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.Report;
import org.eclipse.epf.uma.ReusableAsset;
import org.eclipse.epf.uma.Roadmap;
import org.eclipse.epf.uma.SupportingMaterial;
import org.eclipse.epf.uma.Template;
import org.eclipse.epf.uma.TermDefinition;
import org.eclipse.epf.uma.ToolMentor;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.Whitepaper;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.epf.uma.util.UserDefinedTypeMeta;

/**
 * The item provider adapter for the "Guidance" folder in the Library view.
 * <p>
 * This class will be renamed as GuidanceFolderItemProvider in EPF M5.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class GuidancesItemProvider extends TransientContentPackageItemProvider {

	public static boolean showUDTElements = false;
	
	/**
	 * Creates a new instance.
	 */
	public GuidancesItemProvider(AdapterFactory adapterFactory,
			ContentPackage contentPkg) {
		super(adapterFactory, contentPkg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.element.TransientContentPackageItemProvider#acceptAsChild(java.lang.Object)
	 */
	protected boolean acceptAsChild(Object obj) {
		if (!super.acceptAsChild(obj))
			return false;
		if (! showUDTElements) {
			if (obj instanceof Practice) {
				return ! PracticePropUtil.getPracticePropUtil().isUdtType((Practice) obj);
			}
		}
		return obj instanceof Guidance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildrenFeatures(java.lang.Object)
	 */
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(UmaPackage.eINSTANCE
					.getContentPackage_ContentElements());
		}
		return childrenFeatures;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		return LibraryEditPlugin.INSTANCE.getString("_UI_Guidances_group"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getImage(java.lang.Object)
	 */
	public Object getImage(Object object) {
		return LibraryEditPlugin.INSTANCE.getImage("full/obj16/GuidanceFolder"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#collectNewChildDescriptors(java.util.Collection,
	 *      java.lang.Object)
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
		// newChildDescriptors.add
		// (createChildParameter
		// (UmaPackage.eINSTANCE.getContentPackage_ContentElements(),
		// UmaFactory.eINSTANCE.createAttachment()));

		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getContentPackage_ContentElements(), UmaFactory.eINSTANCE
				.createChecklist()));

		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getContentPackage_ContentElements(), UmaFactory.eINSTANCE
				.createConcept()));

		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getContentPackage_ContentElements(), UmaFactory.eINSTANCE
				.createExample()));

		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getContentPackage_ContentElements(), UmaFactory.eINSTANCE
				.createGuideline()));

		// newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
		// .getContentPackage_ContentElements(), UmaFactory.eINSTANCE
		// .createEstimate()));
		//
		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getContentPackage_ContentElements(), UmaFactory.eINSTANCE
				.createEstimationConsiderations()));

		// newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
		// .getContentPackage_ContentElements(), UmaFactory.eINSTANCE
		// .createEstimatingMetric()));

		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getContentPackage_ContentElements(), UmaFactory.eINSTANCE
				.createPractice()));

		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getContentPackage_ContentElements(), UmaFactory.eINSTANCE
				.createReport()));

		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getContentPackage_ContentElements(), UmaFactory.eINSTANCE
				.createReusableAsset()));

		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getContentPackage_ContentElements(), UmaFactory.eINSTANCE
				.createRoadmap()));

		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getContentPackage_ContentElements(), UmaFactory.eINSTANCE
				.createSupportingMaterial()));

		// newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
		// .getContentPackage_ContentElements(), UmaFactory.eINSTANCE
		// .createTechnique()));

		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getContentPackage_ContentElements(), UmaFactory.eINSTANCE
				.createTemplate()));

		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getContentPackage_ContentElements(), UmaFactory.eINSTANCE
				.createTermDefinition()));

		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getContentPackage_ContentElements(), UmaFactory.eINSTANCE
				.createToolMentor()));

		// newChildDescriptors.add
		// (createChildParameter
		// (UmaPackage.eINSTANCE.getContentPackage_ContentElements(),
		// UmaFactory.eINSTANCE.createTrainingClass()));
		//
		// newChildDescriptors.add
		// (createChildParameter
		// (UmaPackage.eINSTANCE.getContentPackage_ContentElements(),
		// UmaFactory.eINSTANCE.createTrainingModule()));

		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getContentPackage_ContentElements(), UmaFactory.eINSTANCE
				.createWhitepaper()));

		// newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
		// .getContentPackage_ContentElements(), UmaFactory.eINSTANCE
		// .createWorkProductGuideline()));
		if (showUDTElements) {
			LibraryEditUtil.getInstance().createUserDefinedTypeContextMenuOnGuidanceNode(newChildDescriptors);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getParent(java.lang.Object)
	 */
	// public Object getParent(Object object) {
	// EObject eObj = (EObject) super.getParent(object);
	// if(eObj == null) return null;
	// ContentPackageItemProvider itemProvider = (ContentPackageItemProvider)
	// TngUtil.getAdapter(eObj, ContentPackageItemProvider.class);
	// if(itemProvider != null) {
	// return itemProvider.getGuidances();
	// }
	// return eObj;
	// }
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#setDefaultName(java.lang.Object)
	 */
	public void setDefaultName(Object obj) {
		String baseName = null;
		if (obj instanceof Checklist) {
			baseName = LibraryEditConstants.NEW_CHECKLIST;
		} else if (obj instanceof Whitepaper) {
			baseName = LibraryEditConstants.NEW_WHITEPAPER;
		} else if (obj instanceof Concept) {
			baseName = LibraryEditConstants.NEW_CONCEPT;
		} else if (obj instanceof EstimationConsiderations) {
			baseName = LibraryEditConstants.NEW_ESTIMATION_CONSIDERATION;
		} else if (obj instanceof Example) {
			baseName = LibraryEditConstants.NEW_EXAMPLE;
		} else if (obj instanceof Practice) {
			baseName = getDefaultNameForPractice((Practice)obj);
		} else if (obj instanceof Report) {
			baseName = LibraryEditConstants.NEW_REPORT;
		} else if (obj instanceof Roadmap) {
			baseName = LibraryEditConstants.NEW_ROADMAP;
		} else if (obj instanceof ReusableAsset) {
			baseName = LibraryEditConstants.NEW_REUSABLE_ASSET;
		} else if (obj instanceof SupportingMaterial) {
			baseName = LibraryEditConstants.NEW_SUPPORTING_MATERIAL;
			// } else if (obj instanceof Technique) {
			// baseName = "new_Technique";
		} else if (obj instanceof Template) {
			baseName = LibraryEditConstants.NEW_TEMPLATE;
		} else if (obj instanceof TermDefinition) {
			baseName = LibraryEditConstants.NEW_TERM_DEFINITION;
		} else if (obj instanceof ToolMentor) {
			baseName = LibraryEditConstants.NEW_TOOL_MENTOR;
		} else if (obj instanceof Guideline) {
			baseName = LibraryEditConstants.NEW_GUIDELINE;
		} else if (obj instanceof Guidance) {
			baseName = LibraryEditConstants.NEW_GUIDANCE;
		}
		if (baseName != null) {
			TngUtil.setDefaultName(((ContentPackage) target)
					.getContentElements(), (MethodElement) obj, baseName);
		}
	}
	
	public static String getDefaultNameForPractice(Practice prac) {
		try {
			UserDefinedTypeMeta udtMeta = PracticePropUtil.getPracticePropUtil().getUtdData(prac);
			if (udtMeta != null) {
				String rawName = udtMeta.getRteNameMap().get(UserDefinedTypeMeta._typeName);
				return buildDefaultNameForUdt(rawName);
			}	
		} catch (Exception e) {
			LibraryEditPlugin.getDefault().getLogger().logError(e);
		}	
		
		return LibraryEditConstants.NEW_PRACTICE;
	}
	
	public static String buildDefaultNameForUdt(String rawName) {
		StringBuffer buf = new StringBuffer();
		buf.append("new_"); //$NON-NLS-1$
		
		String[] nameParts = rawName.split(" "); //$NON-NLS-1$		
		for (String namePart : nameParts) {
			buf.append(namePart).append("_"); //$NON-NLS-1$
		}		
		buf.deleteCharAt(buf.length() -1);
		
		return buf.toString().toLowerCase();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.element.TransientContentPackageItemProvider#newChildAdded(org.eclipse.emf.common.notify.Notification)
	 */
	protected void childrenChanged(Notification msg) {
		boolean refresh = false;
		switch (msg.getEventType()) {
		case Notification.ADD:
			if (msg.getNewValue() instanceof ToolMentor) {
				refresh = true;
			}
			break;
		case Notification.REMOVE:
			if (msg.getOldValue() instanceof ToolMentor) {
				refresh = true;
			}
			break;
		case Notification.ADD_MANY:
			check_add: for (Iterator iter = ((Collection) msg.getNewValue())
					.iterator(); iter.hasNext();) {
				if (iter.next() instanceof ToolMentor) {
					refresh = true;
					break check_add;
				}
			}
			break;
		case Notification.REMOVE_MANY:
			check_remove: for (Iterator iter = ((Collection) msg.getOldValue())
					.iterator(); iter.hasNext();) {
				if (iter.next() instanceof ToolMentor) {
					refresh = true;
					break check_remove;
				}
			}
			break;
		}
		if (refresh)
			TngUtil.refreshUncategorizedToolMentorsItemProvider(UmaUtil
					.getMethodPlugin((EObject) target), msg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		List children = null;
		Collection collection = super.getChildren(object);
		if (collection instanceof List) {
			children = (List) collection;
		} else {
			children = new ArrayList(collection);
		}
		Collections.sort(children, PresentationContext.INSTANCE.getGuidanceTypeComparator());
		return children;
	}

}
