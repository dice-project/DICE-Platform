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
package org.eclipse.epf.authoring.ui.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.editors.BreakdownElementEditorInput;
import org.eclipse.epf.common.ui.util.MsgBox;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.diagram.core.part.AbstractDiagramEditor;
import org.eclipse.epf.diagram.core.part.DiagramEditorInput;
import org.eclipse.epf.diagram.core.part.DiagramEditorInputProxy;
import org.eclipse.epf.diagram.ui.service.DiagramEditorHelper;
import org.eclipse.epf.library.edit.IModifyChecker;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.MethodElementUtil;
import org.eclipse.epf.library.edit.util.PracticePropUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.DependencyChecker;
import org.eclipse.epf.library.edit.validation.IValidator;
import org.eclipse.epf.library.edit.validation.internal.ContentElementNameValidator;
import org.eclipse.epf.library.edit.validation.internal.ValidatorFactory;
import org.eclipse.epf.library.services.SafeUpdateController;
import org.eclipse.epf.library.ui.LibraryUIText;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.Deliverable;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.util.UserDefinedTypeMeta;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.osgi.util.TextProcessor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;


/**
 * Helper Utility class for UI
 * 
 * @author Shilpa Toraskar
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class UIHelper {

	/**
	 * Setting items in a table
	 * 
	 * @param table
	 * @param items
	 */
	public static void setItems(Table table, ArrayList items) {
		table.removeAll();
		for (int i = 0; i < items.size(); i++) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText((String) items.get(i));
		}
	}

	/**
	 * Create list on the given composite with given style
	 * @param toolkit
	 * @param parent
	 * @param style
	 */
	public static List createList(FormToolkit toolkit, Composite parent,
			int style) {
		List list = new List(parent, style | SWT.NULL);
		toolkit.adapt(list, true, true);
		return list;
	}

	/*
	 * Method will return Object[] of guidance excluding Practice.
	 * MethodElementUtil#getSelectedGuidances(EObject) calculates all guidance.
	 */
	public static Object[] getSelectedGuidances(ContentElement contentElement) {
		java.util.List list = MethodElementUtil
				.getSelectedGuidances(contentElement);
		for (Iterator it = list.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof Practice)
				it.remove();
		}
		return list.toArray();
	}

	private static String getFormPageTitlePrefixFor(MethodElement methodElement) {
		String elementLabel = LibraryUIText.getUIText(methodElement);
		if (methodElement instanceof WorkProduct) {
			return LibraryUIText.TEXT_WORK_PRODUCT + " (" + elementLabel + ")"; //$NON-NLS-1$ //$NON-NLS-2$
		} else if (methodElement instanceof Guidance) {
			if (methodElement instanceof Practice) {
				Practice prac = (Practice)methodElement;
				if (PracticePropUtil.getPracticePropUtil().isUdtType(prac)) {
					elementLabel = getNameForUtd(prac);
					return elementLabel;
				}
			}			
			return LibraryUIText.TEXT_GUIDANCE + " (" + elementLabel + ")"; //$NON-NLS-1$ //$NON-NLS-2$
		} else {
			return elementLabel;
		}
	}
	
	private static String getNameForUtd(Practice prac) {
		try {
			String typeName = PracticePropUtil.getPracticePropUtil().getUtdData(prac)
				.getRteNameMap().get(UserDefinedTypeMeta._typeName);			
			return typeName;
		} catch (Exception e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(e);
		}
		
		return null;
	}

	/**
	 * Sets text for the given form based on the given element, including variable information of the element
	 * if there is any.
	 * 
	 * @param form
	 * @param element
	 */
	public static void setFormText(ScrolledForm form, MethodElement element) {
		String delimiter = ". ,  ;  ! ? ~ @ # $ % ^ & *  ( ) { } [ ]   < > \\ / \" ' `  | ";	//$NON-NLS-1$
		if (form != null && !form.isDisposed()) {
			StringBuffer str = new StringBuffer();
			str.append(getFormPageTitlePrefixFor(element));
			str.append(AuthoringUIResources.editor_title_colon_with_spaces);
			
			if(element instanceof VariabilityElement) {
				str.append(TngUtil.getLabel((VariabilityElement) element,
						"", true)); //$NON-NLS-1$
			}
			else if(element.getName() != null){
				str.append(element.getName());
			}
			form.setText(TextProcessor.process(str.toString(), delimiter));
		}
	}

	/**
	 * Check whether given deliverables leads to circular dependency
	 * @param toBePart
	 * @param deliverable
	 * @return
	 * 			Boolean value to indicate whether cicular dependency is detected
	 * @deprecated moved to {@link DependencyChecker#checkCircularDeliverables(Deliverable, Deliverable)}
	 */
	public static boolean checkCircularDeliverables(Deliverable toBePart,
			Deliverable deliverable) {
		return DependencyChecker.checkCircularDeliverables(toBePart, deliverable);
	}

	/**
	 * Check whether deliverable parts are in chain
	 * 
	 * @param e
	 * @param roots
	 */
	public static void deliverablePartsChain(Deliverable e, java.util.List roots) {
		java.util.List list = e.getDeliveredWorkProducts();
		if (list != null && list.size() > 0) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Object obj = iterator.next();
				if (obj instanceof Deliverable) {
					roots.add(obj);
					deliverablePartsChain((Deliverable) obj, roots);
				}
			}
		}
	}
	

	/**
	 * Resolve content element name conflicts
	 * @param container
	 * @param element
	 * @param reference
	 */
	public static String resolveContentElementNameConflict(EObject container, MethodElement element, EReference
			reference) {

		final IValidator validator = new ContentElementNameValidator(container
				, reference,
				(ContentElement) element, new ValidatorFactory.TypeFilter(element));
		
		String msg = validator.isValid(element.getName());
		
		if (msg != null) {
			String featureTxt = TngUtil.getFeatureText(UmaPackage.eINSTANCE
					.getNamedElement_Name());
			String title = LibraryEditResources.resolveNameConflictDialog_title; 
			String dlgMsg = NLS.bind(
					LibraryEditResources.resolveNameConflictDialog_text,
					StrUtil.toLower(featureTxt), element.getName());
			String currentValue = (String) element.eGet(UmaPackage.eINSTANCE
					.getNamedElement_Name());

			IInputValidator inputValidator = new IInputValidator() {
				public String isValid(String newText) {
					return validator.isValid(newText);
				}
			};

			InputDialog inputDlg = new InputDialog(
					MsgBox.getDefaultShell(), title, dlgMsg, currentValue,
					inputValidator);
			if (inputDlg.open() == Window.CANCEL) {
				throw new OperationCanceledException();
			}
			return inputDlg.getValue();
		}
		return null;
	}
	
	
	/**
	 * Diagram editors of object itself will be closed. And Parent diagram editors 
	 * will be refreshed.  
	 *  @deprecated  (see:  {@link DiagramEditorHelper} - refreshParentDiagramEditors())
	 */
	public static void refreshOpenDiagramEditorsOfParent(Object refreshElement, java.util.List openEditorRefs) {
		openEditorRefs = new ArrayList();
		// Now refresh object's parent's diagrams.
		IWorkbenchPage workbenchPage = AuthoringUIPlugin.getDefault()
				.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage();
		IEditorReference[] editorReferences = workbenchPage
				.getEditorReferences();
		for (int i = 0; i < editorReferences.length; i++) {
			IEditorReference reference = editorReferences[i];
			IEditorPart editor = reference.getEditor(true);
			if (editor != null) {
				IEditorInput input = editor.getEditorInput();
				Object element = null;
				if (input instanceof BreakdownElementEditorInput) {
					BreakdownElementEditorInput binput = (BreakdownElementEditorInput)input;
					if (binput.getWrapper() != null) {
						element = binput.getWrapper();
						if(element instanceof BreakdownElementWrapperItemProvider){
							Object parent = ((BreakdownElementWrapperItemProvider)element).getParent(null);
							if(parent instanceof BreakdownElementWrapperItemProvider){
								if(element.equals(refreshElement))
									refreshOpenDiagramEditorsOfParent(parent, openEditorRefs);
							}
						}
					} else {
						element = binput.getMethodElement();
					}
				} 
				else if (input instanceof DiagramEditorInputProxy) {
					DiagramEditorInput diagramInput = ((DiagramEditorInputProxy) input).getDiagramEditorInput();
					if (diagramInput != null) {
						element = diagramInput.getMethodElement();
					}
				}
				if (element != null)
				{
					if(element instanceof Activity){
						if(((Activity)element).getBreakdownElements().contains(refreshElement))
							openEditorRefs.add(reference);
						else if(refreshElement instanceof BreakdownElementWrapperItemProvider){
							Object localRefreshElement = refreshElement;
							while((localRefreshElement instanceof BreakdownElementWrapperItemProvider)
									&& ((BreakdownElementWrapperItemProvider)localRefreshElement).getOwner()!= null){
								if(((BreakdownElementWrapperItemProvider)localRefreshElement).getOwner()
										.equals(element)){
									openEditorRefs.add(reference);
								}
								localRefreshElement = ((BreakdownElementWrapperItemProvider)localRefreshElement).getOwner();
							}
						}
					}else if(element instanceof BreakdownElementWrapperItemProvider){
						Collection c = ((BreakdownElementWrapperItemProvider)element).getChildren(element);
						if(c != null && c.contains(refreshElement)){
							openEditorRefs.add(reference);
						}
					}
					
				}
			}
		}
		int size = openEditorRefs.size();
		//IEditorReference[] references = new IEditorReference[size];
		for (int i = 0; i < size; i++) {
			IEditorReference reference = (IEditorReference) openEditorRefs.get(i);
			IEditorPart editor = reference.getEditor(true);
			if(editor instanceof AbstractDiagramEditor ){
				((AbstractDiagramEditor)editor).refreshDiagram();
			}
		}
	}
	
	/**
	 * Close diagram editors including parent editors
	 * @param closeElement
	 * @param closeEditorRefs
	 * @deprecated
	 */
	public static void closeDiagramEditorsIncludingParent(Object closeElement, java.util.List closeEditorRefs) {
		closeEditorRefs = new ArrayList();
		IWorkbenchPage workbenchPage = AuthoringUIPlugin.getDefault()
				.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage();
		IEditorReference[] editorReferences = workbenchPage
				.getEditorReferences();
		for (int i = 0; i < editorReferences.length; i++) {
			IEditorReference reference = editorReferences[i];
			IEditorPart editor = reference.getEditor(true);
			if (editor != null) {
				IEditorInput input = editor.getEditorInput();
				Object element = null;
				if (input instanceof BreakdownElementEditorInput) {
					BreakdownElementEditorInput binput = (BreakdownElementEditorInput)input;
					if (binput.getWrapper() != null) {
						element = binput.getWrapper();
						if(element instanceof BreakdownElementWrapperItemProvider){
							Object parent = ((BreakdownElementWrapperItemProvider)element).getParent(null);
							if(parent instanceof BreakdownElementWrapperItemProvider){
								if(element.equals(closeElement))
									closeDiagramEditorsIncludingParent(parent, closeEditorRefs);
							}
						}
					} else {
						element = binput.getMethodElement();
					}
				} 
				else if (input instanceof DiagramEditorInputProxy) {
					DiagramEditorInput diagramInput = ((DiagramEditorInputProxy) input).getDiagramEditorInput();
					if (diagramInput != null) {
						element = diagramInput.getMethodElement();
					}
				}
				if (element != null)
				{
						//closeEditorRefs.add(reference);
					if(element instanceof Activity){
						if(element.equals(closeElement))
							closeEditorRefs.add(reference);
						
						if(((Activity)element).getBreakdownElements().contains(closeElement))
							closeEditorRefs.add(reference);
						else if(closeElement instanceof BreakdownElementWrapperItemProvider){
							if(((BreakdownElementWrapperItemProvider)closeElement).getOwner()
									!= null)
								if(((BreakdownElementWrapperItemProvider)closeElement).getOwner()
									.equals(element)){
									closeEditorRefs.add(reference);
								if(((BreakdownElementWrapperItemProvider)closeElement).getOwner()
									instanceof BreakdownElementWrapperItemProvider){
									if(((BreakdownElementWrapperItemProvider)((BreakdownElementWrapperItemProvider)
											closeElement).getOwner()).getOwner().equals(element))
										closeEditorRefs.add(reference);
								}
							}
						}
					}else if(element instanceof BreakdownElementWrapperItemProvider){
						Collection c = ((BreakdownElementWrapperItemProvider)element).getChildren(element);
						if(c != null && c.contains(closeElement)){
							closeEditorRefs.add(reference);
						}
					}
				}
			}
		}
		int size = closeEditorRefs.size();
		IEditorReference[] references = new IEditorReference[size];
		for (int i = 0; i < size; i++) {
			references[i] = (IEditorReference) closeEditorRefs.get(i);
		}
		workbenchPage.closeEditors(references, false);
	}
	
	/**
	 * Get base activity for the given activity
	 * @param activity
	 * @return
	 * 			Base activity
	 */
	public static VariabilityElement getBaseActivity(Activity activity){
		while (!activity.getVariabilityType().equals(
				VariabilityType.NA)) {

			VariabilityElement ve = activity
					.getVariabilityBasedOnElement();
			if ((ve != null) && (ve instanceof Activity)) {
				activity = (Activity) ve;
			} else {
				break;
			}
		}
		return activity;
	}
	
	/**
	 * Close diagram editors
	 * 
	 * @param closeElement
	 * @param closeEditorRefs
	 *  @deprecated (see:  {@link DiagramEditorHelper})
	 *  
	 */
	public static void closeDiagramEditors(Object closeElement, java.util.List closeEditorRefs) {
		closeEditorRefs = new ArrayList();
		IWorkbenchPage workbenchPage = AuthoringUIPlugin.getDefault()
				.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage();
		IEditorReference[] editorReferences = workbenchPage
				.getEditorReferences();
		for (int i = 0; i < editorReferences.length; i++) {
			IEditorReference reference = editorReferences[i];
			IEditorPart editor = reference.getEditor(true);
			if (editor != null) {
				IEditorInput input = editor.getEditorInput();
				Object element = null;
				if (input instanceof BreakdownElementEditorInput) {
					BreakdownElementEditorInput binput = (BreakdownElementEditorInput)input;
					if (binput.getWrapper() != null) {
						element = binput.getWrapper();
						if(element instanceof BreakdownElementWrapperItemProvider){
							Object parent = ((BreakdownElementWrapperItemProvider)element).getParent(element);
							if(parent instanceof BreakdownElementWrapperItemProvider){
								if(element.equals(closeElement))
									closeDiagramEditors(parent, closeEditorRefs);
							}
						}
					} else {
						element = binput.getMethodElement();
					}
				} 
				else if (input instanceof DiagramEditorInputProxy) {
					DiagramEditorInput diagramInput = ((DiagramEditorInputProxy) input).getDiagramEditorInput();
					if (diagramInput != null) {
						element = diagramInput.getMethodElement();
					}
				}
				if (element != null) {
					if (element.equals(closeElement)) {
						closeEditorRefs.add(reference);
					} else {
						// check whether element is child of closeElement
						// directly/indirectly
						if (closeElement instanceof Activity) {
							Collection collection = new ArrayList();
							ProcessUtil.getChildElements(
									(Activity) closeElement, Activity.class,
									collection);
							if (collection.contains(element)) {
								closeEditorRefs.add(reference);
							}
						}
					}
				}
			}
		}
		int size = closeEditorRefs.size();
		IEditorReference[] references = new IEditorReference[size];
		for (int i = 0; i < size; i++) {
			references[i] = (IEditorReference) closeEditorRefs.get(i);
		}
		workbenchPage.closeEditors(references, false);
		references = null;
	}
	
	/**
	 * Diagram editors of object itself will be closed. And Parent diagram editors 
	 * will be refreshed.  code not tested use refreshOpenDiagramEditors method.
	 * Not used currently
	 * @param object
	 */
	public static void syncDiagramEditors(Object object) {
		java.util.List closeEditors = new ArrayList();
		java.util.List refreshEditors = new ArrayList();
		
		IWorkbenchPage workbenchPage = AuthoringUIPlugin.getDefault()
				.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IEditorReference[] editorReferences = workbenchPage
				.getEditorReferences();
		for (int i = 0; i < editorReferences.length; i++) {
			IEditorReference reference = editorReferences[i];
			IEditorPart editor = reference.getEditor(true);
			if (editor != null) {
				IEditorInput input = editor.getEditorInput();
				Object editorElement = null;
				if (input instanceof BreakdownElementEditorInput) {
					BreakdownElementEditorInput binput = (BreakdownElementEditorInput)input;
					if(binput.getWrapper() != null){
						editorElement = binput.getWrapper();
					}else{
						editorElement = binput.getMethodElement();
					}
				}
				else if (input instanceof DiagramEditorInputProxy) {
					DiagramEditorInput diagramInput = ((DiagramEditorInputProxy) input).getDiagramEditorInput();
					if (diagramInput != null) {
						editorElement = diagramInput.getMethodElement();
					}
				}
				if(editorElement != null){
					if(editorElement.equals(object)){
						closeEditors.add(reference);
					}else{
						if(editorElement instanceof Activity){
							if(((Activity)editorElement).getBreakdownElements().contains(object))
								refreshEditors.add(reference);
							else if(object instanceof BreakdownElementWrapperItemProvider){
								Object local = object;
								while((local instanceof BreakdownElementWrapperItemProvider)
										&& ((BreakdownElementWrapperItemProvider)local).getOwner()!= null){
									if(((BreakdownElementWrapperItemProvider)local).getOwner()
											.equals(editorElement)){
										refreshEditors.add(reference);
									}
									local = ((BreakdownElementWrapperItemProvider)local).getOwner();
								}
							}
						}else if(editorElement instanceof BreakdownElementWrapperItemProvider){
							Collection c = ((BreakdownElementWrapperItemProvider)editorElement).getChildren(editorElement);
							if(c != null && c.contains(object)){
								refreshEditors.add(reference);
							}
						}
					}
				}
			}
		}
		// Close editor references and refresh editors.
		int size = closeEditors.size();
		IEditorReference[] references = new IEditorReference[size];
		for (int i = 0; i < size; i++) {
			references[i] = (IEditorReference) closeEditors.get(i);
		}
		workbenchPage.closeEditors(references, false);
		references = null;
		
		size = refreshEditors.size();
		//IEditorReference[] references = new IEditorReference[size];
		for (int i = 0; i < size; i++) {
			IEditorReference reference = (IEditorReference) refreshEditors.get(i);
			IEditorPart editor = reference.getEditor(true);
			if(editor instanceof AbstractDiagramEditor ){
				((AbstractDiagramEditor)editor).refreshDiagram();
			}
		}
		
	}
	
	public static void showProblemsView() {
 		SafeUpdateController
		.asyncExec(new Runnable() {
			public void run() {
				try {
					if (PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null &&
							PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null) {
						IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
						IViewPart view = page.findView(IPageLayout.ID_PROBLEM_VIEW);
						if (view == null) {
							page.showView(IPageLayout.ID_PROBLEM_VIEW, null, IWorkbenchPage.VIEW_VISIBLE);
						}
					}
				} catch (Exception e) {
					// couldn't open the
					// problem view, too
					// bad..
					AuthoringUIPlugin.getDefault().getLogger().logError(e);
				}
			}
		});
	}
	
	public static boolean checkModify(Resource res, Shell shell) {
		return checkModify(Collections.singleton(res), shell);
	}
	
	public static class ModifyChecker implements IModifyChecker {
		private Shell shell;
		
		public ModifyChecker(Shell shell) {
			this.shell = shell;
		}
		
		public boolean checkModify(Resource res) {
			return UIHelper.checkModify(res, shell);
		}

		public boolean checkModify(Collection<Resource> modifiedResources) {
			return UIHelper.checkModify(modifiedResources, shell);	
		}
	}
	
	public static boolean checkModify(Collection modifiedResources, Shell shell) {
		if (shell == null) {
			Display display = Display.getCurrent();
			if (display != null) {
				shell = display.getActiveShell();
			}
		}
		IStatus status = UserInteractionHelper.checkModify(modifiedResources,
				shell);
		if (!status.isOK()) {
			handleError(status);
			return false;
		}
		return true;
	}

	private static void handleError(IStatus status) {
		AuthoringUIPlugin
				.getDefault()
				.getMsgDialog()
				.display(AuthoringUIResources.errorDialog_title,
						AuthoringUIResources.editDialog_msgCannotEdit, status);
	}
}
