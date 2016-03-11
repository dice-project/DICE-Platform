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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.IReferencer;
import org.eclipse.epf.library.edit.IStatefulItemProvider;
import org.eclipse.epf.library.edit.breakdownelement.ActivityItemProvider;
import org.eclipse.epf.library.edit.breakdownelement.MilestoneItemProvider;
import org.eclipse.epf.library.edit.category.CustomCategoryItemProvider;
import org.eclipse.epf.library.edit.category.DisciplineCategoryItemProvider;
import org.eclipse.epf.library.edit.category.DisciplineItemProvider;
import org.eclipse.epf.library.edit.category.DomainItemProvider;
import org.eclipse.epf.library.edit.category.RoleSetCategoryItemProvider;
import org.eclipse.epf.library.edit.category.RoleSetItemProvider;
import org.eclipse.epf.library.edit.category.ToolItemProvider;
import org.eclipse.epf.library.edit.category.WorkProductTypeItemProvider;
import org.eclipse.epf.library.edit.element.ArtifactItemProvider;
import org.eclipse.epf.library.edit.element.ContentDescriptionItemProvider;
import org.eclipse.epf.library.edit.element.ContentPackageItemProvider;
import org.eclipse.epf.library.edit.element.GuidanceItemProvider;
import org.eclipse.epf.library.edit.element.RoleItemProvider;
import org.eclipse.epf.library.edit.element.SectionItemProvider;
import org.eclipse.epf.library.edit.element.TaskItemProvider;
import org.eclipse.epf.library.edit.element.WorkProductItemProvider;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory;

/**
 * The item provider adapter factory for the Library view.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class ItemProviderAdapterFactory extends UmaItemProviderAdapterFactory
		implements IReferencer {

	protected WorkProductItemProvider workProductItemProvider;

	protected ArtifactItemProvider myArtifactItemProvider;

	protected GuidanceItemProvider guidanceItemProvider;

	protected WorkProductItemProvider myOutcomeItemProvider;

	protected WorkProductItemProvider myDeliverableItemProvider;

	/**
	 * References to stateful adapters created by this adapter factory
	 */
	protected Set disposableAdapters = new HashSet();

	// protected Collection adapters = new ArrayList();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#adapt(org.eclipse.emf.common.notify.Notifier,
	 *      java.lang.Object)
	 */
	public Adapter adapt(Notifier notifier, Object type) {
		Adapter adapter = super.adapt(notifier, type);
		if (adapter instanceof IStatefulItemProvider
				&& notifier instanceof EObject
				&& ((EObject) notifier).eContainer() != null) {
			disposableAdapters.add(adapter);
		}
		return adapter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.util.CorecontentAdapterFactory#createRoleAdapter()
	 */
	public Adapter createRoleAdapter() {
		if (roleItemProvider == null) {
			roleItemProvider = new RoleItemProvider(this);
		}
		return roleItemProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.util.CorecontentAdapterFactory#createTaskAdapter()
	 */
	public Adapter createTaskAdapter() {
		if (taskItemProvider == null) {
			taskItemProvider = new TaskItemProvider(this);
		}

		return taskItemProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.util.WorkproductsAdapterFactory#createArtifactAdapter()
	 */
	public Adapter createArtifactAdapter() {
		if (myArtifactItemProvider == null) {
			myArtifactItemProvider = new ArtifactItemProvider(this);
		}

		return myArtifactItemProvider;
	}

	public Adapter createWorkProductAdapter() {
		if (workProductItemProvider == null) {
			workProductItemProvider = new WorkProductItemProvider(this);
		}
		return workProductItemProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.util.WorkproductsAdapterFactory#createDeliverableAdapter()
	 */
	public Adapter createDeliverableAdapter() {
		if (myDeliverableItemProvider == null) {
			myDeliverableItemProvider = new WorkProductItemProvider(this,
					(ItemProviderAdapter) super.createDeliverableAdapter());
		}

		return myDeliverableItemProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.util.WorkproductsAdapterFactory#createOutcomeAdapter()
	 */
	public Adapter createOutcomeAdapter() {
		if (myOutcomeItemProvider == null) {
			myOutcomeItemProvider = new WorkProductItemProvider(this,
					(ItemProviderAdapter) super.createOutcomeAdapter());
		}

		return myOutcomeItemProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createMethodConfigurationAdapter()
	 */
	public Adapter createMethodConfigurationAdapter() {
		if (methodConfigurationItemProvider == null) {
			methodConfigurationItemProvider = new MethodConfigurationItemProvider(
					this);
		}

		return methodConfigurationItemProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createMethodLibraryAdapter()
	 */
	public Adapter createMethodLibraryAdapter() {
		Adapter adapter = new MethodLibraryItemProvider(this);
		// adapters.add(adapter);
		return adapter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createProcessFamilyAdapter()
	 */
	/*
	public Adapter createProcessFamilyAdapter() {
		if (processFamilyItemProvider == null) {
			processFamilyItemProvider = new ProcessFamilyItemProvider(this);
		}

		return processFamilyItemProvider;
	}
	*/

	public Adapter createContentPackageAdapter() {
		Adapter adapter = new ContentPackageItemProvider(this);
		// adapters.add(adapter);
		return adapter;
	}

	public Adapter createMethodPluginAdapter() {
		MethodPluginItemProvider adapter = new MethodPluginItemProvider(this);
		adapter.setModelStructure(ModelStructure.DEFAULT);
		// adapters.add(adapter);
		return adapter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.util.MethodmodelsAdapterFactory#createProcessComponentAdapter()
	 */
	public Adapter createProcessComponentAdapter() {
		if (processComponentItemProvider == null) {
			processComponentItemProvider = new ProcessComponentItemProvider(
					this);
		}

		return processComponentItemProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.util.MethodmodelsAdapterFactory#createProcessPackageAdapter()
	 */
	public Adapter createProcessPackageAdapter() {
		Adapter adapter = new ProcessPackageItemProvider(this);
		// adapters.add(adapter);
		return adapter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.util.ContentcategoriesAdapterFactory#createDisciplineGroupingAdapter()
	 */
	public Adapter createDisciplineGroupingAdapter() {
		if (disciplineGroupingItemProvider == null) {
			disciplineGroupingItemProvider = new DisciplineCategoryItemProvider(
					this);
		}
		return disciplineGroupingItemProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.util.ContentcategoriesAdapterFactory#createDomainAdapter()
	 */
	public Adapter createDomainAdapter() {
		if (domainItemProvider == null) {
			domainItemProvider = new DomainItemProvider(this);
		}

		return domainItemProvider;
	}

	public Adapter createRoleSetGroupingAdapter() {
		Adapter adapter = new RoleSetCategoryItemProvider(this);
		// adapters.add(adapter);
		return adapter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.util.ContentcategoriesAdapterFactory#createDisciplineAdapter()
	 */
	public Adapter createDisciplineAdapter() {
		// if (disciplineItemProvider == null) {
		// disciplineItemProvider = new DisciplineItemProvider(this);
		// }
		//
		// return disciplineItemProvider;

		Adapter adapter = new DisciplineItemProvider(this);
		// adapters.add(adapter);
		return adapter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.util.ContentcategoriesAdapterFactory#createRoleSetAdapter()
	 */
	public Adapter createRoleSetAdapter() {
		// if (roleSetItemProvider == null) {
		// roleSetItemProvider = new RoleSetItemProvider(this);
		// }
		//
		// return roleSetItemProvider;

		Adapter adapter = new RoleSetItemProvider(this);
		// adapters.add(adapter);
		return adapter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.ContentcategoriesItemProviderAdapterFactory#createToolAdapter()
	 */
	public Adapter createToolAdapter() {
		Adapter adapter = new ToolItemProvider(this);
		// adapters.add(adapter);
		return adapter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.ContentcategoriesItemProviderAdapterFactory#createWorkProductTypeAdapter()
	 */
	public Adapter createWorkProductTypeAdapter() {
		Adapter adapter = new WorkProductTypeItemProvider(this);
		// adapters.add(adapter);
		return adapter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.util.UmaAdapterFactory#createGuidanceAdapter()
	 */
	public Adapter createGuidanceAdapter() {
		if (guidanceItemProvider == null) {
			guidanceItemProvider = new GuidanceItemProvider(this);
		}
		return guidanceItemProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createChecklistAdapter()
	 */
	public Adapter createChecklistAdapter() {
		return createGuidanceAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createConceptAdapter()
	 */
	public Adapter createConceptAdapter() {
		return createGuidanceAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createEstimateAdapter()
	 */
	public Adapter createEstimateAdapter() {
		return createGuidanceAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createEstimationConsiderations
	 *      Adapter()
	 */
	public Adapter createEstimationConsiderationsAdapter() {
		return createGuidanceAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createEstimatingMetricAdapter()
	 */
	public Adapter createEstimatingMetricAdapter() {
		return createGuidanceAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createExampleAdapter()
	 */
	public Adapter createExampleAdapter() {
		return createGuidanceAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createPracticeAdapter()
	 */
	public Adapter createPracticeAdapter() {
		if (practiceItemProvider == null) {
			practiceItemProvider = new PracticeItemProvider(this);
		}
		return practiceItemProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createReportAdapter()
	 */
	public Adapter createReportAdapter() {
		return createGuidanceAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createRoadmapAdapter()
	 */
	public Adapter createRoadmapAdapter() {
		return createGuidanceAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createReusableAssetAdapter()
	 */
	public Adapter createReusableAssetAdapter() {
		return createGuidanceAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createSupportingMaterialAdapter()
	 */
	public Adapter createSupportingMaterialAdapter() {
		return createGuidanceAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createTechniqueAdapter()
	 */
	public Adapter createTechniqueAdapter() {
		return createGuidanceAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createTemplateAdapter()
	 */
	public Adapter createTemplateAdapter() {
		return createGuidanceAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createTermDefinitionAdapter()
	 */
	public Adapter createTermDefinitionAdapter() {
		return createGuidanceAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createToolMentorAdapter()
	 */
	public Adapter createToolMentorAdapter() {
		return createGuidanceAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createWorkProductGuidelineAdapter()
	 */
	public Adapter createWorkProductGuidelineAdapter() {
		return createGuidanceAdapter();
	}

	public Adapter createSectionAdapter() {
		if (sectionItemProvider == null) {
			sectionItemProvider = new SectionItemProvider(this);
		}

		return sectionItemProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createCustomCategoryAdapter()
	 */
	public Adapter createCustomCategoryAdapter() {
		if (customCategoryItemProvider == null) {
			customCategoryItemProvider = new CustomCategoryItemProvider(this);
		}

		return customCategoryItemProvider;
	}

	public Adapter createWhitepaperAdapter() {
		// if (whitepaperItemProvider == null) {
		// whitepaperItemProvider = new WhitepaperItemProvider(this);
		// }
		//
		// return whitepaperItemProvider;

		return createGuidanceAdapter();
	}

	public Adapter createCapabilityPatternAdapter() {
		if (capabilityPatternItemProvider == null) {
			capabilityPatternItemProvider = new CapabilityPatternItemProvider(
					this);
		}

		return capabilityPatternItemProvider;
	}

	public Adapter createDeliveryProcessAdapter() {
		if (deliveryProcessItemProvider == null) {
			deliveryProcessItemProvider = new DeliveryProcessItemProvider(this);
		}

		return deliveryProcessItemProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createGuidelineAdapter()
	 */
	public Adapter createGuidelineAdapter() {
		return createGuidanceAdapter();
	}

	public Adapter createContentDescriptionAdapter() {
		if (contentDescriptionItemProvider == null) {
			contentDescriptionItemProvider = new ContentDescriptionItemProvider(
					this);
		}

		return contentDescriptionItemProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createActivityDescriptionAdapter()
	 */
	public Adapter createActivityDescriptionAdapter() {
		return createContentDescriptionAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createArtifactDescriptionAdapter()
	 */
	public Adapter createArtifactDescriptionAdapter() {
		return createContentDescriptionAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createBreakdownElementDescriptionAdapter()
	 */
	public Adapter createBreakdownElementDescriptionAdapter() {
		return createContentDescriptionAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createDeliverableDescriptionAdapter()
	 */
	public Adapter createDeliverableDescriptionAdapter() {
		return createContentDescriptionAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createDeliveryProcessDescriptionAdapter()
	 */
	public Adapter createDeliveryProcessDescriptionAdapter() {
		return createContentDescriptionAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createDescriptorDescriptionAdapter()
	 */
	public Adapter createDescriptorDescriptionAdapter() {
		return createContentDescriptionAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createGuidanceDescriptionAdapter()
	 */
	public Adapter createGuidanceDescriptionAdapter() {
		return createContentDescriptionAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createPracticeDescriptionAdapter()
	 */
	public Adapter createPracticeDescriptionAdapter() {
		return createContentDescriptionAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createProcessDescriptionAdapter()
	 */
	public Adapter createProcessDescriptionAdapter() {
		return createContentDescriptionAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createRoleDescriptionAdapter()
	 */
	public Adapter createRoleDescriptionAdapter() {
		return createContentDescriptionAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createTaskDescriptionAdapter()
	 */
	public Adapter createTaskDescriptionAdapter() {
		return createContentDescriptionAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createWorkProductDescriptionAdapter()
	 */
	public Adapter createWorkProductDescriptionAdapter() {
		return createContentDescriptionAdapter();
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.epf.uma.Milestone}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createMilestoneAdapter() {
		if (milestoneItemProvider == null) {
			milestoneItemProvider = new MilestoneItemProvider(this);
		}
		return milestoneItemProvider;
	}
	
	@Override
	public Adapter createActivityAdapter() {
		return new ActivityItemProvider(this);
	}
	@Override
	public Adapter createPhaseAdapter() {
		return new ActivityItemProvider(this);
	}
	@Override
	public Adapter createIterationAdapter() {
		return createActivityAdapter();
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#dispose()
	 */
	public void dispose() {
		if (guidanceItemProvider != null) {
			guidanceItemProvider.dispose();
			guidanceItemProvider = null;
		}
		if (myArtifactItemProvider != null) {
			myArtifactItemProvider.dispose();
			myArtifactItemProvider = null;
		}
		if (myDeliverableItemProvider != null) {
			myDeliverableItemProvider.dispose();
			myDeliverableItemProvider = null;
		}
		if (myOutcomeItemProvider != null) {
			myOutcomeItemProvider.dispose();
			myOutcomeItemProvider = null;
		}
		if (workProductItemProvider != null) {
			workProductItemProvider.dispose();
			workProductItemProvider = null;
		}

		// for (Iterator iter = new ArrayList(adapters).iterator();
		// iter.hasNext();) {
		// Object adapter = iter.next();
		// if(adapter instanceof IDisposable) {
		// ((IDisposable)adapter).dispose();
		// }
		// }
		// adapters.clear();

		if (!disposableAdapters.isEmpty()) {
			for (Iterator iter = disposableAdapters.iterator(); iter.hasNext();) {
				IDisposable adapter = (IDisposable) iter.next();
				adapter.dispose();
			}
			disposableAdapters.clear();
		}

		super.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IReferencer#remove(java.lang.Object)
	 */
	public boolean remove(Object ref) {
		return disposableAdapters.remove(ref);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.IReferencer#cleanUp()
	 */
	public void cleanUp() {
		ArrayList adaptersToRemove = new ArrayList();
		for (Iterator iter = new ArrayList(disposableAdapters).iterator(); iter
				.hasNext();) {
			Adapter adapter = (Adapter) iter.next();
			if (adapter instanceof IStatefulItemProvider
					&& adapter.getTarget() == null) {
				adaptersToRemove.add(adapter);
			}
		}
		if (TngUtil.DEBUG) {
			System.out
					.println("ItemProviderAdapterFactory.cleanUp(): number of adapters to remove: " //$NON-NLS-1$
							+ adaptersToRemove.size());
		}
		disposableAdapters.removeAll(adaptersToRemove);
	}

}
