/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.authoring.ui.providers;

import java.util.Map;

import org.eclipse.epf.authoring.ui.forms.ChecklistItemsPage;
import org.eclipse.epf.authoring.ui.forms.ContentElementGuidancePage;
import org.eclipse.epf.authoring.ui.forms.ContentPackageDescriptionPage;
import org.eclipse.epf.authoring.ui.forms.CustomCategoryAssignPage;
import org.eclipse.epf.authoring.ui.forms.CustomCategoryDescriptionPage;
import org.eclipse.epf.authoring.ui.forms.DisciplineDescriptionPage;
import org.eclipse.epf.authoring.ui.forms.DisciplineGroupingDescriptionPage;
import org.eclipse.epf.authoring.ui.forms.DisciplineGroupingDisciplinesPage;
import org.eclipse.epf.authoring.ui.forms.DisciplineReferenceWorkflowPage;
import org.eclipse.epf.authoring.ui.forms.DisciplineTasksPage;
import org.eclipse.epf.authoring.ui.forms.DomainDescriptionPage;
import org.eclipse.epf.authoring.ui.forms.DomainWorkProductsPage;
import org.eclipse.epf.authoring.ui.forms.GuidanceDescriptionPage;
import org.eclipse.epf.authoring.ui.forms.GuidanceWithAttachmentsDescriptionPage;
import org.eclipse.epf.authoring.ui.forms.MethodLibraryDescriptionFormPage;
import org.eclipse.epf.authoring.ui.forms.MethodPluginDescriptionPage;
import org.eclipse.epf.authoring.ui.forms.PracticeDescriptionPage;
import org.eclipse.epf.authoring.ui.forms.PracticeReferencesPage;
import org.eclipse.epf.authoring.ui.forms.RoleCategoriesPage;
import org.eclipse.epf.authoring.ui.forms.RoleDescriptionPage;
import org.eclipse.epf.authoring.ui.forms.RoleSetDescriptionPage;
import org.eclipse.epf.authoring.ui.forms.RoleSetGroupingDescriptionPage;
import org.eclipse.epf.authoring.ui.forms.RoleSetGroupingRoleSets;
import org.eclipse.epf.authoring.ui.forms.RoleSetRolesPage;
import org.eclipse.epf.authoring.ui.forms.RoleWorkProductsPage;
import org.eclipse.epf.authoring.ui.forms.TaskCategoriesPage;
import org.eclipse.epf.authoring.ui.forms.TaskDescriptionPage;
import org.eclipse.epf.authoring.ui.forms.TaskRolesPage;
import org.eclipse.epf.authoring.ui.forms.TaskStepsPage;
import org.eclipse.epf.authoring.ui.forms.TaskWorkProductsPage;
import org.eclipse.epf.authoring.ui.forms.ToolDescriptionPage;
import org.eclipse.epf.authoring.ui.forms.ToolToolMentorsPage;
import org.eclipse.epf.authoring.ui.forms.WorkProductCategoriesPage;
import org.eclipse.epf.authoring.ui.forms.WorkProductDeliverablePartsPage;
import org.eclipse.epf.authoring.ui.forms.WorkProductDescriptionPage;
import org.eclipse.epf.authoring.ui.forms.WorkProductStatesPage;
import org.eclipse.epf.authoring.ui.forms.WorkProductTypeDescriptionPage;
import org.eclipse.epf.authoring.ui.forms.WorkProductTypeWorkProductsPage;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Checklist;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.Deliverable;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.DisciplineGrouping;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleSet;
import org.eclipse.epf.uma.RoleSetGrouping;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.Tool;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductType;
import org.eclipse.ui.forms.editor.FormEditor;

public class MethodElementEditorDefaultPageProvider implements
		IMethodElementEditorPageProviderExtension {

	public Map<Object, String> getPages(Map<Object,String> pageMap, FormEditor editor, Object input) {
		if (input instanceof MethodLibrary) {
			pageMap.put(new MethodLibraryDescriptionFormPage(editor), null);
		} else if (input instanceof MethodPlugin) {
			pageMap.put(new MethodPluginDescriptionPage(editor), null);
		} else if (input instanceof ContentPackage) {
			pageMap.put(new ContentPackageDescriptionPage(editor), null);
		} else if (input instanceof Role) {
			pageMap.put(new RoleDescriptionPage(editor), null);
			pageMap.put(new RoleWorkProductsPage(editor), null);
			pageMap.put(new ContentElementGuidancePage(editor), null);
			pageMap.put(new RoleCategoriesPage(editor), null);
		} else if (input instanceof Task) {
			pageMap.put(new TaskDescriptionPage(editor), null);
			pageMap.put(new TaskStepsPage(editor), null);
			pageMap.put(new TaskRolesPage(editor), null);
			pageMap.put(new TaskWorkProductsPage(editor), null);
			pageMap.put(new ContentElementGuidancePage(editor), null);
			pageMap.put(new TaskCategoriesPage(editor), null);
		} else if (input instanceof WorkProduct) {
			pageMap.put(new WorkProductDescriptionPage(editor), null);
			if (input instanceof Deliverable) {
				pageMap.put(new WorkProductDeliverablePartsPage(editor), null);
			}
			pageMap.put(new ContentElementGuidancePage(editor), null);
			pageMap.put(new WorkProductCategoriesPage(editor), null);
			pageMap.put(new WorkProductStatesPage(editor), null);
		} else if (input instanceof Guidance) {
			if (TngUtil.isAllowedAttachments((Guidance)input)) {
				pageMap.put(new GuidanceWithAttachmentsDescriptionPage(editor), null);
			} else if (input instanceof Practice) {
				pageMap.put(new PracticeDescriptionPage(editor), null);
				pageMap.put(new PracticeReferencesPage(editor), null);
			} else if (input instanceof Checklist) {
				pageMap.put(new GuidanceDescriptionPage(editor), null);
				pageMap.put(new ChecklistItemsPage(editor), null);
			} else {
				pageMap.put(new GuidanceDescriptionPage(editor), null);
			}
			if (!(input instanceof Practice))
				pageMap.put(new ContentElementGuidancePage(editor), null);
		} else if (input instanceof Discipline) {
			pageMap.put(new DisciplineDescriptionPage(editor), null);
			pageMap.put(new DisciplineTasksPage(editor), null);
			pageMap.put(new DisciplineReferenceWorkflowPage(editor), null);
			pageMap.put(new ContentElementGuidancePage(editor), null);
		} else if (input instanceof DisciplineGrouping) {
			pageMap.put(new DisciplineGroupingDescriptionPage(editor), null);
			pageMap.put(new DisciplineGroupingDisciplinesPage(editor), null);
		} else if (input instanceof Domain) {
			pageMap.put(new DomainDescriptionPage(editor), null);
			pageMap.put(new DomainWorkProductsPage(editor), null);
			pageMap.put(new ContentElementGuidancePage(editor), null);
		} else if (input instanceof WorkProductType) {
			pageMap.put(new WorkProductTypeDescriptionPage(editor), null);
			pageMap.put(new WorkProductTypeWorkProductsPage(editor), null);
			pageMap.put(new ContentElementGuidancePage(editor), null);
		} else if (input instanceof RoleSet) {
			pageMap.put(new RoleSetDescriptionPage(editor), null);
			pageMap.put(new RoleSetRolesPage(editor), null);
			pageMap.put(new ContentElementGuidancePage(editor), null);
		} else if (input instanceof RoleSetGrouping) {
			pageMap.put(new RoleSetGroupingDescriptionPage(editor), null);
			pageMap.put(new RoleSetGroupingRoleSets(editor), null);
		} else if (input instanceof Tool) {
			pageMap.put(new ToolDescriptionPage(editor), null);
			pageMap.put(new ToolToolMentorsPage(editor), null);
			pageMap.put(new ContentElementGuidancePage(editor), null);
		} else if (input instanceof CustomCategory) {
			pageMap.put(new CustomCategoryDescriptionPage(editor), null);
			pageMap.put(new CustomCategoryAssignPage(editor), null);
		}
		return pageMap;
	}
}
