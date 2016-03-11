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
package org.eclipse.epf.library.edit.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.IConfigurable;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.IGroupContainer;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.element.GuidancesItemProvider;
import org.eclipse.epf.library.edit.element.IElementItemProvider;
import org.eclipse.epf.library.edit.util.PracticePropUtil;
import org.eclipse.epf.uma.Checklist;
import org.eclipse.epf.uma.Concept;
import org.eclipse.epf.uma.EstimationConsiderations;
import org.eclipse.epf.uma.Example;
import org.eclipse.epf.uma.Guideline;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.Report;
import org.eclipse.epf.uma.ReusableAsset;
import org.eclipse.epf.uma.Roadmap;
import org.eclipse.epf.uma.SupportingMaterial;
import org.eclipse.epf.uma.Template;
import org.eclipse.epf.uma.TermDefinition;
import org.eclipse.epf.uma.ToolMentor;
import org.eclipse.epf.uma.Whitepaper;

/**
 * The item provider adapter for the guidance folders.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class GuidanceGroupingItemProvider extends ItemProviderAdapter implements
		IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource,
		IElementItemProvider, IGroupContainer, IConfigurable {

	private Map groupItemProviderMap;

	protected ArrayList children;

	private MethodConfiguration methodConfig;

	private IFilter filter;

	/**
	 * A map of EClass / ItemProviderAdapter for each type of guidance
	 */
	private static final IFilter checkListFilter = new IFilter() {
		public boolean accept(Object obj) {
			return obj instanceof Checklist;
		}
	};

	private static final IFilter conceptFilter = new IFilter() {
		public boolean accept(Object obj) {
			return ((obj instanceof Concept) && (!(obj instanceof Whitepaper)));
		}
	};

	private static final IFilter exampleFilter = new IFilter() {
		public boolean accept(Object obj) {
			return obj instanceof Example;
		}
	};

	public static final IFilter practiceFilter = new IFilter() {
		public boolean accept(Object obj) {
			if (! (obj instanceof Practice)) {
				return false;
			}
			if (GuidancesItemProvider.showUDTElements) {
				return true;
			}
			return ! PracticePropUtil.getPracticePropUtil().isUdtType((Practice) obj);
		}
	};

	private static final IFilter reportFilter = new IFilter() {
		public boolean accept(Object obj) {
			return obj instanceof Report;
		}
	};

	private static final IFilter reusableAssetFilter = new IFilter() {
		public boolean accept(Object obj) {
			return obj instanceof ReusableAsset;
		}
	};

	private static final IFilter roadmapFilter = new IFilter() {
		public boolean accept(Object obj) {
			return obj instanceof Roadmap;
		}
	};

	private static final IFilter supportingMaterialFilter = new IFilter() {
		public boolean accept(Object obj) {
			return obj instanceof SupportingMaterial;
		}
	};

	private static final IFilter guidelineFilter = new IFilter() {
		public boolean accept(Object obj) {
			return obj instanceof Guideline;
		}
	};

	private static final IFilter templateFilter = new IFilter() {
		public boolean accept(Object obj) {
			return obj instanceof Template;
		}
	};

	private static final IFilter termDefinitionFilter = new IFilter() {
		public boolean accept(Object obj) {
			return obj instanceof TermDefinition;
		}
	};

	private static final IFilter toolMentorFilter = new IFilter() {
		public boolean accept(Object obj) {
			return obj instanceof ToolMentor;
		}
	};

	private static final IFilter whitePaperFilter = new IFilter() {
		public boolean accept(Object obj) {
			return obj instanceof Whitepaper;
		}
	};

	private static final IFilter estimationConsiderationsFilter = new IFilter() {
		public boolean accept(Object obj) {
			return obj instanceof EstimationConsiderations;
		}
	};

	/**
	 * Creates a new instance.
	 */
	public GuidanceGroupingItemProvider(AdapterFactory adapterFactory,
			MethodConfiguration methodConfig) {
		super(adapterFactory);
		this.methodConfig = methodConfig;
	}

	public Collection getChildren(Object object) {
		if (children == null) {
			children = new ArrayList();

			groupItemProviderMap = new HashMap();

			// checklist
			Object image = LibraryEditPlugin.getPlugin().getImage(
					"full/obj16/Checklists"); //$NON-NLS-1$
			String name = LibraryEditPlugin.INSTANCE
					.getString("_UI_Guidances_Checklists"); //$NON-NLS-1$
			GuidanceItemProvider child = new GuidanceItemProvider(
					adapterFactory, methodConfig, name, image);
			child.setGuidanceFilter(checkListFilter);
			children.add(child);
			groupItemProviderMap.put(name, child);

			// concept
			image = LibraryEditPlugin.getPlugin().getImage(
					"full/obj16/Concepts"); //$NON-NLS-1$
			name = LibraryEditPlugin.INSTANCE
					.getString("_UI_Guidances_Concepts"); //$NON-NLS-1$
			child = new GuidanceItemProvider(adapterFactory, methodConfig,
					name, image);
			child.setGuidanceFilter(conceptFilter);
			children.add(child);
			groupItemProviderMap.put(name, child);

			// estimation Considerations
			image = LibraryEditPlugin.getPlugin().getImage(
					"full/obj16/EstimationConsiderations"); //$NON-NLS-1$
			name = LibraryEditPlugin.INSTANCE
					.getString("_UI_Guidances_EstimationConsiderations"); //$NON-NLS-1$
			child = new GuidanceItemProvider(adapterFactory, methodConfig,
					name, image);
			child.setGuidanceFilter(estimationConsiderationsFilter);
			children.add(child);
			groupItemProviderMap.put(name, child);

			// example
			image = LibraryEditPlugin.getPlugin().getImage(
					"full/obj16/Examples"); //$NON-NLS-1$
			name = LibraryEditPlugin.INSTANCE
					.getString("_UI_Guidances_Examples"); //$NON-NLS-1$
			child = new GuidanceItemProvider(adapterFactory, methodConfig,
					name, image);
			child.setGuidanceFilter(exampleFilter);
			children.add(child);
			groupItemProviderMap.put(name, child);

			// practice
			image = LibraryEditPlugin.getPlugin().getImage(
					"full/obj16/Practices"); //$NON-NLS-1$
			name = LibraryEditPlugin.INSTANCE
					.getString("_UI_Guidances_Practices"); //$NON-NLS-1$
			child = new GuidanceItemProvider(adapterFactory, methodConfig,
					name, image);
			child.setGuidanceFilter(practiceFilter);
			children.add(child);
			groupItemProviderMap.put(name, child);

			// Report
			image = LibraryEditPlugin.getPlugin()
					.getImage("full/obj16/Reports"); //$NON-NLS-1$
			name = LibraryEditPlugin.INSTANCE
					.getString("_UI_Guidances_Reports"); //$NON-NLS-1$
			child = new GuidanceItemProvider(adapterFactory, methodConfig,
					name, image);
			child.setGuidanceFilter(reportFilter);
			children.add(child);
			groupItemProviderMap.put(name, child);

			// Reusable Asset
			image = LibraryEditPlugin.getPlugin().getImage(
					"full/obj16/ReusableAssets"); //$NON-NLS-1$
			name = LibraryEditPlugin.INSTANCE
					.getString("_UI_Guidances_ReusableAssets"); //$NON-NLS-1$
			child = new GuidanceItemProvider(adapterFactory, methodConfig,
					name, image);
			child.setGuidanceFilter(reusableAssetFilter);
			children.add(child);
			groupItemProviderMap.put(name, child);

			// Reusable Asset
			image = LibraryEditPlugin.getPlugin().getImage(
					"full/obj16/Roadmaps"); //$NON-NLS-1$
			name = LibraryEditPlugin.INSTANCE
					.getString("_UI_Guidances_Roadmap"); //$NON-NLS-1$
			child = new GuidanceItemProvider(adapterFactory, methodConfig,
					name, image);
			child.setGuidanceFilter(roadmapFilter);
			children.add(child);
			groupItemProviderMap.put(name, child);

			// Supporting Material
			image = LibraryEditPlugin.getPlugin().getImage(
					"full/obj16/SupportingMaterials"); //$NON-NLS-1$
			name = LibraryEditPlugin.INSTANCE
					.getString("_UI_Guidances_SupportingMaterials"); //$NON-NLS-1$
			child = new GuidanceItemProvider(adapterFactory, methodConfig,
					name, image);
			child.setGuidanceFilter(supportingMaterialFilter);
			children.add(child);
			groupItemProviderMap.put(name, child);

			// // Technique
			// image =
			// LibraryEditPlugin.getPlugin().getImage("full/obj16/Techniques");
			// name =
			// LibraryEditPlugin.INSTANCE.getString("_UI_Guidances_Techniques");
			// child = new GuidanceItemProvider(adapterFactory, methodConfig,
			// name, image);
			// child.setGuidanceFilter(techniqueFilter);
			// children.add(child);
			// groupItemProviderMap.put(name, child);

			// Template
			image = LibraryEditPlugin.getPlugin().getImage(
					"full/obj16/Templates"); //$NON-NLS-1$
			name = LibraryEditPlugin.INSTANCE
					.getString("_UI_Guidances_Templates"); //$NON-NLS-1$
			child = new GuidanceItemProvider(adapterFactory, methodConfig,
					name, image);
			child.setGuidanceFilter(templateFilter);
			children.add(child);
			groupItemProviderMap.put(name, child);

			// Term Definition
			image = LibraryEditPlugin.getPlugin().getImage(
					"full/obj16/TermDefinitions"); //$NON-NLS-1$
			name = LibraryEditPlugin.INSTANCE
					.getString("_UI_Guidances_TermDefinitions"); //$NON-NLS-1$
			child = new GuidanceItemProvider(adapterFactory, methodConfig,
					name, image);
			child.setGuidanceFilter(termDefinitionFilter);
			children.add(child);
			groupItemProviderMap.put(name, child);

			// Tool Mentor
			image = LibraryEditPlugin.getPlugin().getImage(
					"full/obj16/ToolMentors"); //$NON-NLS-1$
			name = LibraryEditPlugin.INSTANCE
					.getString("_UI_Guidances_ToolMentors"); //$NON-NLS-1$
			child = new GuidanceItemProvider(adapterFactory, methodConfig,
					name, image);
			child.setGuidanceFilter(toolMentorFilter);
			children.add(child);
			groupItemProviderMap.put(name, child);

			// Whitepaper
			image = LibraryEditPlugin.getPlugin().getImage(
					"full/obj16/Whitepapers"); //$NON-NLS-1$
			name = LibraryEditPlugin.INSTANCE
					.getString("_UI_Guidances_Whitepapers"); //$NON-NLS-1$
			child = new GuidanceItemProvider(adapterFactory, methodConfig,
					name, image);
			child.setGuidanceFilter(whitePaperFilter);
			children.add(child);
			groupItemProviderMap.put(name, child);

			// Guidelines
			image = LibraryEditPlugin.getPlugin().getImage(
					"full/obj16/WorkProductGuidelines"); //$NON-NLS-1$
			name = LibraryEditPlugin.INSTANCE
					.getString("_UI_Guidances_WorkProductGuidelines"); //$NON-NLS-1$
			child = new GuidanceItemProvider(adapterFactory, methodConfig,
					name, image);
			child.setGuidanceFilter(guidelineFilter);
			children.add(child);
			groupItemProviderMap.put(name, child);
		}
		
		if (filter != null) {
			// Should filter only copy only, not orginal cached children list.
			List copy = new ArrayList();
			copy.addAll(children);
			for (Iterator iterator = copy.iterator(); iterator.hasNext();) {
				GuidanceItemProvider obj = (GuidanceItemProvider) iterator
						.next();
				obj.setFilter(filter);
				if (obj.getChildren(obj).isEmpty()) {
					iterator.remove();
				}
			}
			return copy;
		}
		return children;
		
	}

	public Object getImage(Object object) {
		return LibraryEditPlugin.INSTANCE.getImage("full/obj16/GuidanceFolder"); //$NON-NLS-1$
	}

	public String getText(Object object) {
		return LibraryEditPlugin.INSTANCE.getString("_UI_Guidances_group"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IGroupContainer#getGroupItemProvider(java.lang.String)
	 */
	public Object getGroupItemProvider(String name) {
		return groupItemProviderMap.get(name);
	}

	public Collection getNewChildDescriptors(Object object,
			EditingDomain editingDomain, Object sibling) {
		return Collections.EMPTY_LIST;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getParent(java.lang.Object)
	 */
	public Object getParent(Object object) {
		return methodConfig;
	}

	public void setFilter(IFilter filter) {
		// TODO Auto-generated method stub
		this.filter = filter;
	}

	public void setLabel(String label) {
		// TODO Auto-generated method stub

	}
}
