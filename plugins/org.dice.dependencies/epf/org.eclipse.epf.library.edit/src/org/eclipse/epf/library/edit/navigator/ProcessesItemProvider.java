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

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * The item provider adapter for the "Processes" folder in the Library view.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class ProcessesItemProvider extends AbstractProcessesItemProvider {

	private static final String PROCESS_CONTRIBUTIONS_LABEL = LibraryEditPlugin.INSTANCE
			.getString("_UI_ProcessContributions_text"); //$NON-NLS-1$

	private MethodPlugin plugin;

	private ProcessPackage processContributionsPackage;
	private ProcessPackage capabilityPatternPackage;
	private ProcessPackage deliveryProcessPackage;

	/**
	 * Creates a new instance.
	 */
	public ProcessesItemProvider(AdapterFactory adapterFactory,
			MethodPlugin plugin, ModelStructure modelStruct) {
		super(adapterFactory, modelStruct);
		this.plugin = plugin;
	}

	public Collection getChildren(Object object) {
		if (children == null) {
			children = new ArrayList();
			org.eclipse.epf.uma.ProcessPackage pkg = (org.eclipse.epf.uma.ProcessPackage) UmaUtil
					.findMethodPackage(plugin,
							modelStruct.capabilityPatternPath);
			capabilityPatternPackage = pkg;
			if (pkg != null) {
				ProcessPackageItemProvider adapter = (ProcessPackageItemProvider) TngUtil
						.getBestAdapterFactory(adapterFactory).adapt(pkg,
								ITreeItemContentProvider.class);
				adapter.setProcessType(UmaPackage.eINSTANCE
						.getCapabilityPattern());
				adapter.setLabel(LibraryEditPlugin.INSTANCE
						.getString("_UI_CapabilityPatterns_text")); //$NON-NLS-1$
				adapter.setParent(this);
				children.add(pkg);
			}
			pkg = (org.eclipse.epf.uma.ProcessPackage) UmaUtil
					.findMethodPackage(plugin, modelStruct.deliveryProcessPath);
			deliveryProcessPackage = deliveryProcessPackage;
			if (pkg != null) {
				ProcessPackageItemProvider adapter = (ProcessPackageItemProvider) TngUtil
						.getBestAdapterFactory(adapterFactory).adapt(pkg,
								ITreeItemContentProvider.class);
				adapter.setProcessType(UmaPackage.eINSTANCE
						.getDeliveryProcess());
				adapter.setLabel(LibraryEditPlugin.INSTANCE
						.getString("_UI_DeliveryProcesses_text")); //$NON-NLS-1$
				adapter.setParent(this);
				children.add(pkg);
			}

			if (processContributionEnabled) {
				children.add(getProcessContributionsPackage());
			}

			// groupItemProviderMap = new HashMap();
			// String name =
			// LibraryEditPlugin.INSTANCE.getString("_UI_PlanningTemplates_group");
			// Object child = new TransientGroupItemProvider(adapterFactory,
			// plugin, name);
			// children.add(child);
			// groupItemProviderMap.put(name, child);

			// comment out the planning templates for TNG release one at
			// 2005-05-09
			// pkg = (org.eclipse.epf.uma.ProcessPackage)
			// UmaUtil.findMethodPackage(plugin,
			// modelStruct.processPlanningTemplatePath);
			// if(pkg == null) {
			// pkg =
			// ModelStructure.createProcessPlanningTemplatePackage(plugin);
			// }
			// adapter = (ProcessPackageItemProvider)
			// TngUtil.getBestAdapterFactory(adapterFactory).adapt(pkg,
			// ITreeItemContentProvider.class);
			// adapter.setProcessType(UmaPackage.eINSTANCE.getProcessPlanningTemplate());
			// adapter.setLabel(LibraryEditPlugin.INSTANCE.getString("_UI_PlanningTemplates_text"));
			// children.add(pkg);

		} else {
			if (processContributionEnabled) {
				ProcessPackage pkg = getProcessContributionsPackage();
				if (!children.contains(pkg)) {
					children.add(pkg);
				}
			} else {
				ProcessPackage pkg = getProcessContributionsPackage();
				children.remove(pkg);
			}
		}

		return children;
	}

	private ProcessPackage getProcessContributionsPackage() {
		if (processContributionsPackage == null) {
			processContributionsPackage = (org.eclipse.epf.uma.ProcessPackage) UmaUtil
					.findMethodPackage(plugin,
							modelStruct.processContributionPath);
			if (processContributionsPackage == null) {
				processContributionsPackage = ModelStructure
						.createProcessContributionPackage(plugin);
			}
			ProcessPackageItemProvider adapter = (ProcessPackageItemProvider) getRootAdapterFactory()
					.adapt(processContributionsPackage,
							ITreeItemContentProvider.class);
			adapter.setLabel(PROCESS_CONTRIBUTIONS_LABEL);
			adapter.setParent(this);
		}
		return processContributionsPackage;
	}

	public Object getParent(Object object) {
		return plugin;
	}

	public ProcessPackage getCapabilityPatternPackage() {
		return capabilityPatternPackage;
	}
	
	public ProcessPackage getDeliveryProcessPackage() {
		return deliveryProcessPackage;
	}
}
