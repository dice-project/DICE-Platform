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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditor;
import org.eclipse.epf.authoring.ui.filters.AllFilter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.PresentationContext;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.ChangeQrCommand;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.command.MethodElementSetPropertyCommand;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.util.CategorySortHelper;
import org.eclipse.epf.library.edit.util.ContentElementOrderList;
import org.eclipse.epf.library.edit.util.MethodElementPropUtil;
import org.eclipse.epf.library.edit.util.PracticePropUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.DependencyChecker;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.UserDefinedTypeMeta;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;


/**
 * The References page in the Practice editor.
 * 
 * @author Shilpa Toraskar
 * @author Kelvin Low
 * @since 1.0
 */
public class PracticeReferencesPage extends AssociationFormPage {

	private static final String FORM_PAGE_ID = "practiceReferencesPage"; //$NON-NLS-1$

	private Practice practice;
	private UserDefinedTypeMeta meta;

	private IActionManager actionMgr;
	
	private ContentElementOrderList contentElementOrderList;
	private ContentElementOrderList activityOrderList;
	private Button manualSortCheckButton;
	private Button typeSortButton;
	private Button assignQualifierButton, unassignQualifierButton;

	/**
	 * Creates a new instance.
	 */
	public PracticeReferencesPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.REFERENCES_PAGE_TITLE);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		practice = (Practice) contentElement;
		meta = PracticePropUtil.getPracticePropUtil().getUdtMeta(practice);
		actionMgr = ((MethodElementEditor) getEditor()).getActionManager();
		setIsUpAndDownButtonsRequired1(true);
		setUseCategory2(false);
		setUseCategory3(false);
	}
	
	@Override
	protected void createFormContent(IManagedForm managedForm) {
		super.createFormContent(managedForm);
		manualSortCheckButton = toolkit.createButton(category1pane2, AuthoringUIResources.practiceReferencesPage_sortOrderButton_text, SWT.CHECK);
		manualSortCheckButton.moveAbove(ctrl_up1);
		manualSortCheckButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		boolean isManual = CategorySortHelper.isManualCategorySort(contentElement);
		manualSortCheckButton.setSelection(isManual);
		manualSortCheckButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean isManual = manualSortCheckButton.getSelection();
				String value = CategorySortHelper.V_CATEGORY_ELEMENTS__SORT_TYPE_MANUAL;
				if (!isManual) {
					value = CategorySortHelper.V_CATEGORY_ELEMENTS__SORT_TYPE_METHOD_TYPE;
				}
				actionMgr.execute(new MethodElementSetPropertyCommand(practice,
						CategorySortHelper.KEY_CATEGORY_ELEMENTS__SORT_TYPE, value));
				enableUpDownButtons1();
			}
		});
		
		typeSortButton = toolkit.createButton(category1pane2, AuthoringUIResources.practiceReferencesPage_sortTypeButton_text, SWT.PUSH);
		typeSortButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		typeSortButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// sort elements
				List<Object> list = new ArrayList<Object>();
				list.addAll(practice.getContentReferences());
				Comparator comparator = PresentationContext.INSTANCE.getMethodElementTypeComparator();
				Collections.<Object>sort(list, comparator);

				actionMgr.doAction(IActionManager.REMOVE_MANY, practice,
						UmaPackage.eINSTANCE
								.getPractice_ContentReferences(), practice.getContentReferences(), -1);

				actionMgr.doAction(IActionManager.ADD_MANY, practice,
						UmaPackage.eINSTANCE
								.getPractice_ContentReferences(), list, -1);
				refreshViewers();
			}
		});
		
		//For user defined type
		if (PracticePropUtil.getPracticePropUtil().isUdtType(practice)) {
			assignQualifierButton = toolkit.createButton(category1pane2, AuthoringUIResources.practiceReferencesPage_assignQualifierButton_text, SWT.PUSH);
			assignQualifierButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			assignQualifierButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					ElementListSelectionDialog dialog = new ElementListSelectionDialog(
							editor.getSite().getShell(),
							getLabelProviderForSelectQualifierDialog());
					dialog.setElements(getInputForSelectQualifierDialog());
					dialog.setMultipleSelection(true);
					dialog.setTitle(AuthoringUIResources.practiceReferencesPage_selectQualifierDialog_title);
					dialog.setMessage(AuthoringUIResources.practiceReferencesPage_selectQualifierDialog_msg);					
					dialog.setImage(null);
					if (dialog.open() == Dialog.CANCEL) {
						return;
					}
					
					Object[] objs = dialog.getResult();
					List<String> selectedQualifiers = new ArrayList<String>();
					for (Object obj : objs) {
						if (obj instanceof String) {
							selectedQualifiers.add((String)obj);
						}
					}
					
					assignQualifier(getSelectedElement(), selectedQualifiers);
					refreshViewers();
				}
			});
			
			unassignQualifierButton = toolkit.createButton(category1pane2, AuthoringUIResources.practiceReferencesPage_unassignQualifierButton_text, SWT.PUSH);
			unassignQualifierButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			unassignQualifierButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					unassignQualifier(getSelectedElement());
					refreshViewers();
				}
			});
		}
	}
	
	private ILabelProvider getLabelProviderForSelectQualifierDialog() {
		ILabelProvider provider = new LabelProvider();		
		return provider;
	}
	
	private String[] getInputForSelectQualifierDialog() {
		return getAllQualifiersOfUDT();
	}
	
	private String[] getAllQualifiersOfUDT() {
		if  (meta != null) {
			return meta.getReferenceQualifierNames();
		}		
		return new String[0];
	}
	
	@Override
	protected void enableUpDownButtons1() {
		super.enableUpDownButtons1();
		if (typeSortButton != null) {
			if (isShouldEnableAlphaSort()) {
				typeSortButton.setEnabled(true);
			} else {
				typeSortButton.setEnabled(false);
			}
		}
	}

	protected boolean isShouldEnableAlphaSort() {
		if (contentElement != null) {
			if (!TngUtil.isLocked(contentElement) && 
					CategorySortHelper.isManualCategorySort(contentElement)) {
				return true;
			}
		} 
		return false;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#initContentProviderSelected()
	 */
	protected void initContentProviderSelected() {
		contentProviderSelected = new AdapterFactoryContentProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {
			public Object[] getElements(Object object) {
				if (contentElementOrderList == null) {
					contentElementOrderList = new ContentElementOrderList(
							contentElement,
							ContentElementOrderList.CONTENT_ELEMENTS__FOR_ELEMENT_ONLY,
							getContentElementOrderFeature());
				}
				if (activityOrderList == null) {
					activityOrderList = new ContentElementOrderList(
							contentElement,
							ContentElementOrderList.CONTENT_ELEMENTS__FOR_ELEMENT_ONLY,
							getActivityOrderFeature());
				}
				if (getProviderExtender().useContentProviderAPIs()) {
					return getProviderExtender().getElements(object, 1);
				}
				List<Object> list = new ArrayList<Object>();
				list.addAll(contentElementOrderList);
				list.addAll(activityOrderList);
				return list.toArray();
			}
		};
		viewer_selected.setContentProvider(contentProviderSelected);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#addItemsToModel1(ArrayList)
	 */
	protected void addItemsToModel1(ArrayList addItems) {
		
		boolean ok = DependencyChecker.checkCircularForMovingVariabilityElement(practice, addItems);
		if(! ok) {
			String title = AuthoringUIResources.circular_dependency_error_title;
			AuthoringUIPlugin.getDefault().getMsgDialog().displayError(title, LibraryEditResources.circular_dependency_error_msg);						
			return;
		}
		
		// Update the model.
		if (!addItems.isEmpty()) {
			for (Iterator it = addItems.iterator(); it.hasNext();) {
				Object item = it.next();
				if (item instanceof ContentElement) {
					actionMgr.doAction(IActionManager.ADD, practice,
							UmaPackage.eINSTANCE
									.getPractice_ContentReferences(), item, -1);
				} else if (item instanceof Activity) {
					actionMgr
							.doAction(IActionManager.ADD, practice,
									UmaPackage.eINSTANCE
											.getPractice_ActivityReferences(),
									item, -1);
				}
			}
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#removeItemsFromModel1(ArrayList)
	 */
	protected void removeItemsFromModel1(ArrayList rmItems) {
		// Update the model.
		if (!rmItems.isEmpty()) {
			for (Iterator it = rmItems.iterator(); it.hasNext();) {
				Object item = it.next();
				if (item instanceof ContentElement) {
					actionMgr.doAction(IActionManager.REMOVE, practice,
							UmaPackage.eINSTANCE
									.getPractice_ContentReferences(), item, -1);
				} else if (item instanceof Activity) {
					actionMgr
							.doAction(IActionManager.REMOVE, practice,
									UmaPackage.eINSTANCE
											.getPractice_ActivityReferences(),
									item, -1);
				}
			}
		}
	}
		
	
	@Override
	protected EStructuralFeature getOrderFeature() {
		List<ContentElement> ceList = getSelectedContentElements();
		List<Activity> aList = getSelectedActivities();
		if (ceList.size() > 0 && aList.size() == 0) {
			return getContentElementOrderFeature();
		} else if (aList.size() > 0 && ceList.size() == 0) {
			return getActivityOrderFeature();
		}
		return null;
	}
	
	private EStructuralFeature getContentElementOrderFeature() {
		return UmaPackage.eINSTANCE.getPractice_ContentReferences();
	}

	private EStructuralFeature getActivityOrderFeature() {
		return UmaPackage.eINSTANCE.getPractice_ActivityReferences();
	}
	
	@Override
	public ContentElementOrderList getContentElementOrderList() {
		List<ContentElement> ceList = getSelectedContentElements();
		List<Activity> aList = getSelectedActivities();
		if (ceList.size() > 0 && aList.size() == 0) {
			return contentElementOrderList;
		} else if (aList.size() > 0 && ceList.size() == 0) {
			return activityOrderList;
		}
		return null;
	}
	
	private List<ContentElement> getSelectedContentElements() {
		List<ContentElement> result = new ArrayList<ContentElement>();
		IStructuredSelection selection = (IStructuredSelection) viewer_selected
				.getSelection();
		for (Iterator iter = selection.iterator();iter.hasNext();) {
			Object o = iter.next();
			if (o instanceof ContentElement) {
				result.add((ContentElement)o);
			}
		}
		return result;
	}

	private List<Activity> getSelectedActivities() {
		List<Activity> result = new ArrayList<Activity>();
		IStructuredSelection selection = (IStructuredSelection) viewer_selected
				.getSelection();
		for (Iterator iter = selection.iterator();iter.hasNext();) {
			Object o = iter.next();
			if (o instanceof Activity) {
				result.add((Activity)o);
			}
		}
		return result;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getContentElement()
	 */
	protected Object getContentElement() {
		return practice;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getTabString()
	 */
	protected String getTabString() {
		return FilterConstants.ALL_ELEMENTS;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getFilter()
	 */
	protected IFilter getFilter() {
		return filter = new AllFilter() {
			protected boolean childAccept(Object obj) {
				if(obj instanceof Milestone) return false;
				if(obj instanceof ContentElement
					|| obj instanceof BreakdownElement
					|| obj instanceof ProcessPackage) 
					return true;
				return false;
			}
		};
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getMultipleSelectDescription(int)
	 */
	protected String getMultipleSelectDescription(int count) {
		return super.getMultipleSelectDescription(count, AuthoringUIResources.practiceReferencesPage_multipleSelectDescription);
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSectionDescription()
	 */
	protected String getSectionDescription() {
		if (PracticePropUtil.getPracticePropUtil().isUdtType(practice)) {
			try {
				String typeName = PracticePropUtil.getPracticePropUtil()
					.getUtdData(practice)
					.getRteNameMap().get(UserDefinedTypeMeta._typeName).toLowerCase();
				return NLS.bind(AuthoringUIResources.practiceReferencesPage_sectionDescription_udt, typeName);
			} catch (Exception e) {
				AuthoringUIPlugin.getDefault().getLogger().logError(e);
			}
		}
		
		return AuthoringUIResources.practiceReferencesPage_sectionDescription;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSectionName()
	 */
	protected String getSectionName() {
		return AuthoringUIResources.practiceReferencesPage_sectionName;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel()
	 */
	protected String getSelectedLabel() {
		return AuthoringUIResources.practiceReferencesPage_selectedLabel;
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel2()
	 */
	protected String getSelectedLabel2() {
		return AuthoringUIResources.practiceReferencesPage_selectedLabel;
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel3()
	 */
	protected String getSelectedLabel3() {
		return AuthoringUIResources.practiceReferencesPage_selectedLabel;
	}
	
	protected void enableControls(boolean editable) {
		super.enableControls(editable);
		
		if (PracticePropUtil.getPracticePropUtil().isUdtType(practice)) {
			if (editable && (getSelectedElement().size() > 0)) {
				assignQualifierButton.setEnabled(true);
				unassignQualifierButton.setEnabled(true);
			} else {
				assignQualifierButton.setEnabled(false);
				unassignQualifierButton.setEnabled(false);
			}
		}		
	}
	
	protected void addListeners() {
		super.addListeners();
		
		viewer_selected.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				if (PracticePropUtil.getPracticePropUtil().isUdtType(practice)) {
					if (!TngUtil.isLocked(contentElement) && (getSelectedElement().size() > 0)) {
						assignQualifierButton.setEnabled(true);
						unassignQualifierButton.setEnabled(true);
					} else {
						assignQualifierButton.setEnabled(false);
						unassignQualifierButton.setEnabled(false);
					}				
				}
			}
		});
	}
	
	private List<MethodElement> getSelectedElement() {
		List<MethodElement> result = new ArrayList<MethodElement>();
		
		IStructuredSelection selection = (IStructuredSelection)viewer_selected.getSelection();
		List elements = selection.toList();
		for (Object element : elements) {
			if (element instanceof MethodElement) {
				result.add((MethodElement)element);
			}
		}
		
		return result;	
	}
	
	private List<String> getAllQualifierNames(MethodElement element) {
		List<String> result = new ArrayList<String>();
		if (meta == null) {
			return result;
		}
		
		MethodElementPropUtil util = MethodElementPropUtil.getMethodElementPropUtil();		
		for(EReference ref : meta.getQualifiedReferences()) {
			String qualifierId = ref.getName();
			List<MethodElement> elementList = util.getQReferenceListById(practice, qualifierId, false);
			if (elementList.contains(element)) {
				result.add(meta.getReferenceQualifierName(qualifierId));
			}
		}
		
		Collections.sort(result);
		
		return result;
	}
	
	private void assignQualifier(List<MethodElement> elements, List<String> qualifiers) {
		for (String qualifier : qualifiers) {
			for (MethodElement element : elements) {
				List<String> allQualifiers = getAllQualifierNames(element);
				if (!allQualifiers.contains(qualifier)) {
					List<MethodElement> targetList = new ArrayList<MethodElement>();
					targetList.add(element);
					ChangeQrCommand cmd = new ChangeQrCommand(practice, targetList, meta.getReferenceQualifierId(qualifier), false);
					editor.getActionManager().execute(cmd);
				}				
			}			
		}
	}
	
	private void unassignQualifier(List<MethodElement> elements) {
		for (MethodElement element : elements) {
			List<String> allQualifiers = getAllQualifierNames(element);
			if (allQualifiers.size() > 0) {
				List<MethodElement> targetList = new ArrayList<MethodElement>();
				targetList.add(element);
				for (String qualifier : allQualifiers) {
					ChangeQrCommand cmd = new ChangeQrCommand(practice, targetList, meta.getReferenceQualifierId(qualifier), true);
					editor.getActionManager().execute(cmd);
				}
			}
		}
	}
	
	public String getDecorator(Object object) {
		if (PracticePropUtil.getPracticePropUtil().isUdtType(practice)) {
			StringBuffer buf = new StringBuffer();
			List<String> allQualifiers = getAllQualifierNames((MethodElement)object);
			
			if (allQualifiers.size() > 0) {
				buf.append("["); //$NON-NLS-1$
				buf.append(TngUtil.convertListToString(allQualifiers));
				buf.append("]  "); //$NON-NLS-1$
			}
			
			return buf.toString();
		}
		
		return super.getDecorator(object);
	}
	
}
