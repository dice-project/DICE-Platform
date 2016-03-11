//------------------------------------------------------------------------------
// Copyright (c) 2005, 2008 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.authoring.ui.dialogs;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.actions.LibraryViewSimpleAction;
import org.eclipse.epf.authoring.ui.actions.UnassignAction;
import org.eclipse.epf.authoring.ui.forms.CustomCategoryAssignPage;
import org.eclipse.epf.common.ui.util.MsgDialog;
import org.eclipse.epf.common.utils.ExtensionHelper;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.IRunnableWithProgress;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.UniqueNamePNameHandler;
import org.eclipse.epf.library.services.LibraryModificationHelper;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.library.util.ResourceScanner;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.ProgressMonitorPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;


/**
 * Dialog for assiging MethodElement within MethodLibrary.
 * 
 * @author Weiping Lu
 * @since 1.5
 */
public class AssignDialog extends Dialog implements ISelectionChangedListener {

	ArrayList usedCategories = new ArrayList();
	
	private TreeViewer treeViewer;

	private ArrayList elements;

	private Object destination;
	
	private boolean assigning = false;

	private ProgressMonitorPart progressMonitorPart;

	private Cursor waitCursor;
	
	private boolean lockedUI = false;

	public static AssignDialog newAssignDialog(Shell parentShell, Collection elements) {
		return new AssignDialog(parentShell, elements);
	}
	
	public static AssignDialog newReassignDialog(Shell parentShell,
			Collection elements, MethodElement parentElement) {
		return new ReassignDialog(parentShell, elements, parentElement);
	}
	
	public static AssignDialog newDeepCopyDialog(Shell parentShell, Collection elements) {
		Object[] context = new Object[] {parentShell, elements};
		Object obj = ExtensionHelper.create(CustomCategoryDeepCopyDialog.class, context);
		if (obj instanceof CustomCategoryDeepCopyDialog) {
			return (CustomCategoryDeepCopyDialog) obj;
		}				
		return new CustomCategoryDeepCopyDialog(parentShell, elements);
	}
	
	protected AssignDialog(Shell parentShell, Collection elementsToAssign) {
		super(parentShell);

		elements = new ArrayList();
		elements.addAll(elementsToAssign);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);

		GridLayout layout = (GridLayout) composite.getLayout();
		layout.marginWidth = 10;
		layout.marginHeight = 10;

		Label label = new Label(composite, SWT.NONE);
		label.setText(AuthoringUIResources.MoveDialog_destination_text); 
		GridData layoutData = new GridData(SWT.BEGINNING);
		label.setLayoutData(layoutData);

		treeViewer = new TreeViewer(composite, SWT.SINGLE | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER);
		AdapterFactory adapterFactory = TngAdapterFactory.INSTANCE
				.getNavigatorView_ComposedAdapterFactory();
		treeViewer.setLabelProvider(new AdapterFactoryLabelProvider(
				adapterFactory));
		treeViewer.setContentProvider(new AdapterFactoryContentProvider(
				adapterFactory));
		treeViewer.addSelectionChangedListener(this);
		// treeViewer.addDoubleClickListener(this);

		GridData spec = new GridData(GridData.FILL_BOTH);
		{
			spec.widthHint = 300;
			spec.heightHint = 300;
			treeViewer.getControl().setLayoutData(spec);
		}

		treeViewer.setInput(LibraryService.getInstance().getCurrentMethodLibrary());

		GridLayout pmlayout = new GridLayout();
		pmlayout.numColumns = 1;
		progressMonitorPart = createProgressMonitorPart(composite, pmlayout);
		progressMonitorPart
				.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		progressMonitorPart.setVisible(false);

		waitCursor = parent.getShell().getDisplay().getSystemCursor(
				SWT.CURSOR_WAIT);
		
		return composite;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(AuthoringUIResources.AssignDialog_assign_text); 
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		destination = TngUtil.unwrap(((IStructuredSelection) event
				.getSelection()).getFirstElement());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		if (doAssign()) {
			super.okPressed();
		}
	}

	private boolean isValidDestination() {
		if (! (destination instanceof CustomCategory) ) {
			return false;
		}
		if (elements == null || elements.isEmpty()) {
			return false;
		}
		MethodPlugin targetPlugin = LibraryUtil.getMethodPlugin((CustomCategory) destination);
		if (targetPlugin == null) {
			return false;
		}
		
		if (elements.get(0) instanceof MethodElement) {
			MethodPlugin sourcePlugin = LibraryUtil.getMethodPlugin((MethodElement) elements.get(0));
			if (sourcePlugin == targetPlugin) {
				return true;
			}
			
			//TO DO: remove the following temp solution by implementing the move across plugins.
//			if (this instanceof ReassignDialog && elements.get(0) instanceof CustomCategory) {
//				if (TngUtil.isInPluginWithLessThanOneSuperCustomCategory(
//						(CustomCategory) elements.get(0), sourcePlugin)) {
//					return false;
//				}
//			}						
			
			if (targetPlugin.getBases().contains(sourcePlugin)) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * @return
	 */
	private boolean doAssign() {
		//To do: 
		//(1) Implement circular dependency check for isValidDestination (nice to have the check
		//    at this point, although the circulat check would be done during "run" action too 
		//(2) Change "move" to "assign" strings
		if (destination == null) {
			String title = AuthoringUIResources.errorDialog_title; 
			String problem = AuthoringUIResources.MoveDialog_nomove_destination_problem_msg; 
			String msg = AuthoringUIResources.MoveDialog_selectdestination_text; 
			MsgDialog dialog = AuthoringUIPlugin.getDefault().getMsgDialog();
			dialog.displayError(title, problem, msg); 
			return false;
		}
		if (!isValidDestination()) {
			String title = AuthoringUIResources.errorDialog_title; 
			String problem = AuthoringUIResources.MoveDialog_invalid_move_destination_problem_msg; 
			String msg = AuthoringUIResources.MoveDialog_validdestination_text; 
			MsgDialog dialog = AuthoringUIPlugin.getDefault().getMsgDialog();
			dialog.displayError(title, problem, msg); 
			return false;
		} else {
			IStatus status = UserInteractionHelper.checkModify(
					(EObject) destination, getShell());
			if (!status.isOK()) {
				String title = AuthoringUIResources.errorDialog_title; 
				String msg = AuthoringUIResources.MoveDialog_cannotModifyDestination; 
				MsgDialog dialog = AuthoringUIPlugin.getDefault()
						.getMsgDialog();
				dialog.displayError(title, msg, status); 
				return false;
			}
		}

		assigning = true;		
		
		IRunnableWithProgress runnable = new IRunnableWithProgress() {

			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				monitor.subTask(AuthoringUIResources.assignAction_text);
				try {
					Collection<Resource> resouresToSave = doWorkBeforeSave();
					LibraryViewSimpleAction.save(resouresToSave);
				} finally {
					assigning = false;
				}
			}

		};
		
		final Shell shell = getShell();
		shell.setCursor(waitCursor);

		getButton(IDialogConstants.OK_ID).setEnabled(false);
		getButton(IDialogConstants.CANCEL_ID).setEnabled(false);
		treeViewer.getControl().setEnabled(false);

		progressMonitorPart.setVisible(true);
		IStatus stat = null;
		try {
			stat = UserInteractionHelper.getUIHelper().runInModalContext(runnable, true, progressMonitorPart, shell);
		} catch (Exception e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(e);
			return true;
		} finally {
			progressMonitorPart.done();
		}
		
		if(stat != null && !stat.isOK()) {
				String title = AuthoringUIResources.errorDialog_title; 
				String msg = AuthoringUIResources.dialogs_AssignDialog_errorMessage; 
				MsgDialog dialog = AuthoringUIPlugin.getDefault()
						.getMsgDialog();
				dialog.displayError(title, msg, stat);
		}

		return true;
	}

	protected Collection<Resource> doWorkBeforeSave() {
		final LibraryModificationHelper helper = new LibraryModificationHelper();
		final CustomCategory category = (CustomCategory) destination;
		final Collection<Resource> resouresToSave = new HashSet<Resource>();
		
		getShell().getDisplay().syncExec(new Runnable() {
			public void run() {
				CustomCategoryAssignPage.addItemsToModel1(elements, category,
						usedCategories, helper.getActionManager(),
						CustomCategoryAssignPage.getAncestors(category));
				resouresToSave.add(category.eResource());
			}
		});
		
		return resouresToSave;
	}
		
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#close()
	 */
	public boolean close() {
		if (assigning)
			return false;
		return super.close();
	}
	

	protected ProgressMonitorPart createProgressMonitorPart(
			Composite composite, GridLayout pmlayout) {
		return new ProgressMonitorPart(composite, pmlayout, SWT.DEFAULT) {
			String currentTask = null;

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.jface.wizard.ProgressMonitorPart#setBlocked(org.eclipse.core.runtime.IStatus)
			 */
			public void setBlocked(IStatus reason) {
				super.setBlocked(reason);
				if (!lockedUI)// Do not show blocked if we are locking the UI
					getBlockedHandler().showBlocked(getShell(), this, reason,
							currentTask);
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.jface.wizard.ProgressMonitorPart#clearBlocked()
			 */
			public void clearBlocked() {
				super.clearBlocked();
				if (!lockedUI)// Do not vlear if we never set it
					getBlockedHandler().clearBlocked();
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.jface.wizard.ProgressMonitorPart#beginTask(java.lang.String,
			 *      int)
			 */
			public void beginTask(String name, int totalWork) {
				super.beginTask(name, totalWork);
				currentTask = name;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.jface.wizard.ProgressMonitorPart#setTaskName(java.lang.String)
			 */
			public void setTaskName(String name) {
				super.setTaskName(name);
				currentTask = name;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.jface.wizard.ProgressMonitorPart#subTask(java.lang.String)
			 */
			public void subTask(String name) {
				super.subTask(name);
				// If we haven't got anything yet use this value for more
				// context
				if (currentTask == null)
					currentTask = name;
			}
		};
	}
	
	protected ArrayList getElements() {
		return elements;
	}
	
	protected Object getDestination() {
		return destination;
	}

	private static class ReassignDialog extends AssignDialog {
		
		private CustomCategory parentElement;
		
		protected ReassignDialog(Shell parentShell, 
				Collection elements, MethodElement parentElement) {
			super(parentShell, elements);
			this.parentElement = (CustomCategory) parentElement;
		}
		
		protected Collection<Resource> doWorkBeforeSave() {
			Collection<Resource> resouresToSave = super.doWorkBeforeSave();
			resouresToSave.add(parentElement.eResource());			
			UnassignAction.unassign(getShell(), getElements().get(0), parentElement, new ArrayList());
			return resouresToSave;
		}
		
		protected void configureShell(Shell newShell) {
			super.configureShell(newShell);
			newShell.setText(AuthoringUIResources.AssignDialog_reassign_text); 
		}
		
	}
	
	public static class CustomCategoryDeepCopyDialog extends AssignDialog {
		ContentPackage customCategoryPkg;
		private UniqueNamePNameHandler nameHandler;
		private ResourceScanner scanner;
		
		public CustomCategoryDeepCopyDialog(Shell parentShell, 
				Collection elements) {
			super(parentShell, elements);
		}
		
		protected Collection<Resource> doWorkBeforeSave() {
			if (getElements() == null
					|| !(getElements().get(0) instanceof CustomCategory)
					|| !(getDestination() instanceof CustomCategory)) {
				return null;
			}
					
			CustomCategory source = (CustomCategory) getElements().get(0);
			CustomCategory targetParent = (CustomCategory) getDestination();
			
			initDeepCopy(source, targetParent); 
			CustomCategory copy = (CustomCategory) deepCopy(source, null);			
			
			//ITextReferenceReplacer txtRefReplacer = ExtensionManager.getTextReferenceReplacer();
			customCategoryPkg.getContentElements().add(copy);
			targetParent.getCategorizedElements().add(copy);
			
			Collection<Resource> resouresToSave = new ArrayList();				
			resouresToSave.add(targetParent.eResource());
			
			if (scanner != null) {
				scanner.copyFiles();
			}
			
			return resouresToSave;
		}		
		
		protected EObject deepCopy(EObject source, MethodElement copiedOwner) {			
			EObject copy = UmaFactory.eINSTANCE.create(source.eClass());
			handleNames(source, copy);
		
			List features = source.eClass().getEAllStructuralFeatures();
			for (int i = 0; i < features.size(); i++) {
				EStructuralFeature feature = (EStructuralFeature) features.get(i);				
				//System.out.println("LD> feature: " + feature.getName());
				copyFeatureValue(source, copy, feature, copiedOwner);
			}
			return copy;
		}

		private void handleNames(EObject source, EObject copy) {
			if (copy instanceof CustomCategory) {
				Object name = source.eGet(UmaPackage.eINSTANCE.getNamedElement_Name());
				Object pname = source.eGet(UmaPackage.eINSTANCE.getMethodElement_PresentationName());
				
				copy.eSet(UmaPackage.eINSTANCE.getNamedElement_Name(), name);
				copy.eSet(UmaPackage.eINSTANCE.getMethodElement_PresentationName(), pname);

				nameHandler.ensureUnique((CustomCategory) copy);
			}
		}
		
		private void copyFeatureValue(EObject sourceObj, EObject copiedObj,
				EStructuralFeature feature, MethodElement copiedOwner) {

			if (feature instanceof EAttribute) {
				copyAttributeFeatureValue(sourceObj, copiedObj,
						(EAttribute) feature, copiedOwner);
			} else if (feature instanceof EReference) {
				copyReferenceFeatureValue(sourceObj, copiedObj,
						(EReference) feature);
			}

		}
		
		private void copyAttributeFeatureValue(EObject sourceObj,
				EObject copiedObj, EAttribute feature, MethodElement copiedOwner) {
			Object sourceValue = sourceObj.eGet(feature);
			if (sourceValue == null) {
				return;
			}
			Object copiedValue = sourceValue;

			if (sourceObj instanceof CustomCategory) {
				if (feature == UmaPackage.eINSTANCE.getMethodElement_Guid()) {
					copiedValue = EcoreUtil.generateUUID();
					copiedObj.eSet(feature, copiedValue);
					return;
				} else if (feature == UmaPackage.eINSTANCE.getNamedElement_Name()) {
					return;
				} else if (feature == UmaPackage.eINSTANCE.getMethodElement_PresentationName()) {
					return;
				}
			} else if (copiedOwner != null && sourceObj instanceof ContentDescription) {
				if (feature == UmaPackage.eINSTANCE.getMethodElement_Guid()) {
					copiedValue =  UmaUtil.generateGUID(copiedOwner.getGuid());
					copiedObj.eSet(feature, copiedValue);
					return;
				}
			}
			
			if (scanner != null) {
				if (sourceValue instanceof URI) {
					URI uri = (URI) sourceValue;
					String urlStr = scanner.registerFileCopy(uri.toString());
					try {
						copiedValue = new URI(urlStr);
					} catch (Exception e) {
						copiedValue = sourceValue;
					}
				} else if (sourceValue instanceof String) {
					if (sourceObj instanceof MethodElement
							&& copiedObj instanceof MethodElement) {
						copiedValue = scanner
								.scan((MethodElement) sourceObj,
										(MethodElement) copiedObj,
										(String) sourceValue, feature);
					}
				}
			}

			copiedObj.eSet(feature, copiedValue);
		}

		private void copyReferenceFeatureValue(EObject sourceObj, EObject copiedObj, EReference feature) {
			Object sourceValue = sourceObj.eGet(feature);
			if (sourceValue == null) {
				return;
			}
			Object copiedValue = sourceValue;

			MethodElement copiedOwner = copiedObj instanceof MethodElement ? (MethodElement) copiedObj
					: null;
			
			if (feature.isContainment()) {
				if (feature.isMany()) {
					List<EObject> sourceList = (List<EObject>) sourceValue;
					List<EObject> copiedList = (List<EObject>) copiedObj
							.eGet(feature);
					for (EObject sobj : sourceList) {
						EObject cobj = deepCopy(sobj, copiedOwner);
						copiedList.add(cobj);
					}
					return;
				}
				copiedValue = deepCopy((EObject) sourceValue, copiedOwner);

			} else if (feature.isMany()) {
				List sourceList = (List) sourceValue;
				List copiedList = (List) copiedObj.eGet(feature);
				for (Object sobj : sourceList) {
					Object cobj = sobj;
					if (sobj instanceof CustomCategory) {
						cobj = (CustomCategory) deepCopy((CustomCategory) sobj, copiedOwner);
						CustomCategory ccobj = (CustomCategory) cobj;
						customCategoryPkg.getContentElements().add(ccobj);
					}
					copiedList.add(cobj);
				}
				return;
			}

			copiedObj.eSet(feature, copiedValue);
		}

		protected void configureShell(Shell newShell) {
			super.configureShell(newShell);
			newShell.setText(AuthoringUIResources.deepCopy_text); 
		}
		
		private void initDeepCopy(CustomCategory source, CustomCategory targetParent) {
			MethodPlugin srcPlugin = UmaUtil.getMethodPlugin(source);
			MethodPlugin tgtPlugin = UmaUtil.getMethodPlugin(targetParent);
			customCategoryPkg = UmaUtil.findContentPackage(tgtPlugin, ModelStructure.DEFAULT.customCategoryPath);
			nameHandler = new UniqueNamePNameHandler(customCategoryPkg.getContentElements());
			if (srcPlugin != tgtPlugin) {
				scanner = new ResourceScanner(srcPlugin, tgtPlugin);
			}
		}
			
	}





}
