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
package org.eclipse.epf.authoring.ui.forms;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.library.edit.util.MethodElementPropUtil;
import org.eclipse.epf.library.edit.util.MethodElementUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.ui.LibraryUIText;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;


/**
 * The description page for the Content Package editor.
 * 
 * @author Shilpa Toraskar
 * @author Kelvin Low
 * @since 1.0
 */
public class ContentPackageDescriptionPage extends DescriptionFormPage implements IRefreshable {

	private static final String FORM_PREFIX = LibraryUIText.TEXT_CONTENT_PACKAGE
			+ ": "; //$NON-NLS-1$

	private CheckboxTableViewer ctrl_dependency;

	private ContentPackage contentPackage;
	protected Button supportingCheckbox;

	/**
	 * Creates a new instance.
	 */
	public ContentPackageDescriptionPage(FormEditor editor) {
		super(editor, AuthoringUIText.DESCRIPTION_PAGE_TITLE,
				AuthoringUIText.DESCRIPTION_PAGE_TITLE);
	}

	/**
	 * @see org.eclipse.ui.forms.editor.FormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		contentPackage = (ContentPackage) methodElement;
		detailSectionOn = false;
		fullDescOn = false;
		keyConsiderationOn = false;
		variabilitySectionOn = false;
		versionSectionOn = false;
	}

	@Override
	protected void createEditorContent(FormToolkit toolkit) {
		super.createEditorContent(toolkit);
		createDependencySection(toolkit);
		// Set focus on the Name text control.
		Display display = form.getBody().getDisplay();
		if (!(display == null || display.isDisposed())) {
			display.asyncExec(new Runnable() {
				public void run() {
					if(ctrl_name.isDisposed()) return;
					if (isAutoGenName()) {
						ctrl_presentation_name.setFocus();
						ctrl_presentation_name.setSelection(0, ctrl_presentation_name.getText().length());
					} else {
						ctrl_name.setFocus();
						ctrl_name.setSelection(0, ctrl_name.getText().length());
					}
				}
			});
		}
	}

	private void createDependencySection(FormToolkit toolkit) {
		Section dependencySection = createSection(toolkit, sectionComposite, 
				AuthoringUIText.DEPENDENCIES_SECTION_NAME, 
				AuthoringUIText.DEPENDENCIES_SECTION_DESC);
		Composite dependencyComposite = createComposite(toolkit, dependencySection);

		Table ctrl_table = toolkit.createTable(dependencyComposite, SWT.V_SCROLL
				| SWT.CHECK | SWT.READ_ONLY | SWT.COLOR_BLUE);
		{
			GridData gridData = new GridData(GridData.BEGINNING
					| GridData.FILL_HORIZONTAL);
			gridData.heightHint = 100;
			ctrl_table.setLayoutData(gridData);
		}

		ctrl_dependency = new CheckboxTableViewer(ctrl_table);

		ILabelProvider labelProvider = new LabelProvider() {
			public String getText(Object element) {
				if (element instanceof ContentPackage) {
					return TngUtil.getLabelWithPath((ContentPackage)element);
				} else {
					return element.toString();
				}
			}
		};
		ctrl_dependency.setLabelProvider(labelProvider);
		ctrl_dependency.setContentProvider(new IStructuredContentProvider() {
			public void dispose() {
			}
			
			public void inputChanged(Viewer viewer, Object oldInput,
					Object newInput) {
			}
			
			public Object[] getElements(Object inputElement) {
				if (inputElement instanceof ContentPackage) {
					return getDependenciesPackages(contentPackage).toArray();
				} else {
					return Collections.EMPTY_LIST.toArray();
				}
			}
		});
		ctrl_dependency.setSorter(new ViewerSorter());
		ctrl_dependency.setInput(contentPackage);

		ctrl_dependency.setAllChecked(true);
		ctrl_dependency.setAllGrayed(true);

		toolkit.paintBordersFor(dependencyComposite);
	}

	/**
	 * Add listeners
	 * 
	 */
	protected void addListeners() {
		super.addListeners();
		
		form.addListener(SWT.Activate, new Listener() {
			public void handleEvent(Event e) {
				// refreshViewers();
				ctrl_dependency.refresh();
				ctrl_dependency.setAllChecked(true);
				ctrl_dependency.setAllGrayed(true);
			}
		});

		ctrl_dependency.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				Object obj = event.getSource();
				((CheckboxTableViewer) obj).setAllChecked(true);
			}
		});

	}

	/**
	 * Get dependency packages for the given content package
	 * @return
	 * 		List of dependency packages
	 */
	public Collection<ContentPackage> getDependenciesPackages(ContentPackage contentPackage) {

		List elements = new ArrayList();
		for (Iterator iter = contentPackage.getContentElements().iterator(); iter
				.hasNext();) {
			ContentElement contentElement = (ContentElement) iter.next();
			if (contentElement.getVariabilityBasedOnElement() != null) {
				elements.add((ContentElement) contentElement
						.getVariabilityBasedOnElement());
			}
			elements.addAll(MethodElementUtil
					.getSelectedGuidances(contentElement));

			if (contentElement instanceof Role) {
/*				if (AssociationHelper
						.getModifiedWorkProducts((Role) contentElement) != null) {
					elements.addAll(AssociationHelper
							.getModifiedWorkProducts((Role) contentElement));
				}
				if (AssociationHelper.getPrimaryTasks((Role) contentElement) != null) {
					elements.addAll(AssociationHelper
							.getPrimaryTasks((Role) contentElement));
				}
				if (AssociationHelper.getSecondaryTasks((Role) contentElement) != null) {
					elements.addAll(AssociationHelper
							.getSecondaryTasks((Role) contentElement));
				}*/
			}
			if (contentElement instanceof Task) {
				if (((Task) contentElement).getPerformedBy() != null) {
					elements.addAll(((Task) contentElement).getPerformedBy());
				}
				if (((Task) contentElement).getAdditionallyPerformedBy() != null) {
					elements.addAll(((Task) contentElement)
							.getAdditionallyPerformedBy());
				}
				if (((Task) contentElement).getMandatoryInput() != null) {
					elements
							.addAll(((Task) contentElement).getMandatoryInput());
				}
				if (((Task) contentElement).getOptionalInput() != null) {
					elements.addAll(((Task) contentElement).getOptionalInput());
				}
				if (((Task) contentElement).getOutput() != null) {
					elements.addAll(((Task) contentElement).getOutput());
				}
			}
			if (contentElement instanceof WorkProduct) {
/*				if (AssociationHelper
						.getModifiedBy((WorkProduct) contentElement) != null) {
					elements.addAll(AssociationHelper
							.getModifiedBy((WorkProduct) contentElement));
				}
				if (AssociationHelper
						.getMandatoryInputToTasks((WorkProduct) contentElement) != null) {
					elements
							.addAll(AssociationHelper
									.getMandatoryInputToTasks((WorkProduct) contentElement));
				}
				if (AssociationHelper
						.getOutputtingTasks((WorkProduct) contentElement) != null) {
					elements.addAll(AssociationHelper
							.getOutputtingTasks((WorkProduct) contentElement));
				}
				List list = AssociationHelper.getResponsibleRoles((WorkProduct) contentElement);
				if (list != null) {
					elements.addAll(list);
				}*/
			}

		}

		List<ContentPackage> cpList = new ArrayList<ContentPackage>();
		for (int i = 0; i < elements.size(); i++) {
			Object object = ((ContentElement) elements.get(i)).eContainer();
			if (object instanceof ContentPackage) {
				if (!contentPackage.equals(object)) {
					if (!cpList.contains((ContentPackage)object)) {
						cpList.add((ContentPackage)object);
					}
				}
			}
		}
		return cpList;
	}

	@Override
	protected Object getContentElement() {
		return contentPackage;
	}

	@Override
	public void loadSectionDescription() {
		this.generalSectionDescription = MessageFormat.format(
				AuthoringUIText.GENERAL_INFO_SECTION_DESC,
				new String[] { LibraryUIText.getUITextLower(contentPackage) });
	}
	
	@Override
	protected void refresh(boolean editable) {
		super.refresh(editable);
		if(supportingCheckbox != null) {
			supportingCheckbox.setEnabled(editable);
			EObject parent = contentPackage.eContainer();
			if (parent instanceof MethodPlugin
					&& ((MethodPlugin) parent).isSupporting()) {
				supportingCheckbox.setEnabled(false);
			} else if (parent instanceof ContentPackage
					&& MethodElementPropUtil.getMethodElementPropUtil()
							.isSupporting((ContentPackage) parent)) {
				supportingCheckbox.setEnabled(false);
			}
		}
	}
	
	public void updateSupportingCheckbox() {
		if (supportingCheckbox == null) {
			return;
		}
		MethodElementPropUtil propUtil = MethodElementPropUtil.getMethodElementPropUtil();
		boolean isSupporitng0 = propUtil.ancestorIsSupporting(contentPackage);
		boolean isSuporting = isSupporitng0 || propUtil.isSupporting(contentPackage);
		if (supportingCheckbox.getSelection() != isSuporting) {
			supportingCheckbox.setSelection(isSuporting);
		}
		if (isSupporitng0) {
			supportingCheckbox.setEnabled(false);
		}
	}	
	
	@Override
	protected void createGeneralSectionContent() {
		super.createGeneralSectionContent();
		
		supportingCheckbox = toolkit
			.createButton(
				generalComposite,
				AuthoringUIResources.contentPackageDescriptionPage_supportingPackageLabel, SWT.CHECK);
	}
	
	@Override
	protected void addGeneralSectionListeners() {
		super.addGeneralSectionListeners();
		
		supportingCheckbox.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				IStatus status = TngUtil.checkEdit(contentPackage, getSite().getShell());
				if (status.isOK()) {
					MethodElementPropUtil propUtil = MethodElementPropUtil
							.getMethodElementPropUtil(actionMgr);
					propUtil.setSupporting(contentPackage, supportingCheckbox
							.getSelection());
					propUtil.updatePackageSupportingBits(contentPackage
							.getChildPackages(), supportingCheckbox
							.getSelection());
				} else {
					AuthoringUIPlugin.getDefault().getMsgDialog().displayError(
							AuthoringUIResources.editDialog_title,
							AuthoringUIResources.editDialog_msgCannotEdit,
							status);
					// restore old value
					//
					supportingCheckbox.setSelection(!supportingCheckbox.getSelection());
					return;
				}
			}

			
		});
	}
	
	@Override
	protected void loadGeneralSectionData() {
		super.loadGeneralSectionData();
		boolean isSuporting = MethodElementPropUtil.getMethodElementPropUtil().isSupporting(contentPackage);
		supportingCheckbox
			.setSelection(isSuporting);
	}
	

}
