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
package org.eclipse.epf.authoring.ui.editors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.views.ViewHelper;
import org.eclipse.epf.common.ui.util.MsgDialog;
import org.eclipse.epf.diagram.core.part.DiagramEditorInput;
import org.eclipse.epf.diagram.core.part.DiagramEditorInputProxy;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.ui.editors.IMethodEditor;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.DisciplineGrouping;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleSet;
import org.eclipse.epf.uma.RoleSetGrouping;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.Tool;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductType;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * Launches a Method editor that is appropriate for a given method element.
 * 
 * @author Shilpa Toraskar
 * @author Kelvin Low
 * @since 1.0
 */
public class EditorChooser implements IEditorKeeper {

	private static EditorChooser singleton = null;

	/**
	 * Returns a singleton instance
	 * 
	 */
	public static EditorChooser getInstance() {
		if (singleton == null) {
			synchronized (EditorChooser.class) {
				if (singleton == null) {
					singleton = new EditorChooser();
					IEditorKeeper.REFERENCE.setEditorKeeper(singleton);
				}
			}
		}
		return singleton;
	}

	public EditorChooser() {
		
	}
	
	
	/**
	 * Opens the respective editor depending on the given Method element object.
	 * 
	 * @param obj
	 *            A Method element.
	 */
	public void openEditor(Object obj) {
		try {
			obj = ViewHelper.handleDangling(obj);
			if (obj == null)
				return;

			obj = LibraryUtil.unwrap(obj);

			ArrayList errors = new ArrayList();
			if (obj instanceof EObject) {
				Resource res = ((EObject) obj).eResource();
				if (res != null && !res.getErrors().isEmpty()) {
					errors.addAll(res.getErrors());
				}
				if (obj instanceof DescribableElement) {
					Resource presRes = ((DescribableElement) obj)
							.getPresentation().eResource();
					if (presRes != null && !presRes.getErrors().isEmpty()) {
						errors.addAll(presRes.getErrors());
					}
				}
				if (!errors.isEmpty()) {
					final MultiStatus multiStatus = new MultiStatus(
							AuthoringUIPlugin.getDefault().getId(),
							0,
							AuthoringUIResources.EditorChooser_ResourcesError, null); 
					for (Iterator iter = errors.iterator(); iter.hasNext();) {
						Diagnostic e = (Diagnostic) iter.next();
						IStatus status = new Status(IStatus.WARNING,
								LibraryPlugin.getDefault().getId(), 0, e
										.getMessage(), null);
						multiStatus.add(status);
					}
					try {
						if (AuthoringUIPlugin
								.getDefault()
								.getMsgDialog()
								.displayConfirmation(
										AuthoringUIResources.EditorChooser_loaddErrorDlgTitle,
										AuthoringUIResources.EditorChooser_ResourcesError, 
										multiStatus) == Dialog.CANCEL) {
							return;
						}

					} catch (Exception e) {
						AuthoringUIPlugin.getDefault().getLogger().logError(e);
					}
				}
			}

			EditorOpener opener = EditorOpenerFactory.getInstance().getOpener(obj);
			if ( opener != null && opener.canOpen(obj) ) {
				opener.openEditor(obj);
			} else if ((obj instanceof MethodPlugin)
					|| (obj instanceof ContentPackage) || (obj instanceof Role)
					|| (obj instanceof Task) || (obj instanceof WorkProduct)
					|| (obj instanceof Guidance) || (obj instanceof Discipline)
					|| (obj instanceof DisciplineGrouping)
					|| (obj instanceof Domain)
					|| (obj instanceof WorkProductType)
					|| (obj instanceof RoleSet) || (obj instanceof Tool)
					|| (obj instanceof RoleSetGrouping)
					|| (obj instanceof MethodLibrary)) {
				openEditor((MethodElement) obj, MethodElementEditor.EDITOR_ID);
			} else if (obj instanceof CustomCategory) {
				CustomCategory custCat = (CustomCategory) obj;
				if(TngUtil.isRootCustomCategory(custCat)) {
					return;
				}
				openEditor((MethodElement) obj, MethodElementEditor.EDITOR_ID);
			} else if (obj instanceof ProcessComponent) {
				openEditor((MethodElement) obj, ProcessEditor.EDITOR_ID);

				// open properties view by default when we open Process Editor
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow()
							.getActivePage().showView(
									"org.eclipse.ui.views.PropertySheet", null, IWorkbenchPage.VIEW_VISIBLE); //$NON-NLS-1$
				} catch (PartInitException exception) {
					AuthoringUIPlugin.getDefault().getLogger().logError(
							exception);
				}
			} else if (obj instanceof Process) {
				Object container = ((Process) obj).eContainer();
				if (container instanceof ProcessComponent) {
					openEditor((MethodElement) container,
							ProcessEditor.EDITOR_ID);
				}
			}else if (obj instanceof MethodConfiguration) {
				openEditor((MethodConfiguration) obj,
						ConfigurationEditor.EDITOR_ID);
			}else if(obj instanceof BreakdownElement){
				Process process = TngUtil.getOwningProcess((BreakdownElement)obj);
				Object container = process.eContainer();
				if (container instanceof ProcessComponent) {
					openEditor((MethodElement) container,
							ProcessEditor.EDITOR_ID);
				}
			}
		} catch (Throwable t) {
			t.printStackTrace();
			String title = AuthoringUIResources.editors_EditorChooser_systemErrorDialog_title; 
			String message = AuthoringUIResources.editors_EditorChooser_systemErrorDialog_message; 
			String reason = AuthoringUIResources.editors_EditorChooser_systemErrorDialog_reason; 
			MsgDialog dialog = AuthoringUIPlugin.getDefault().getMsgDialog();
			dialog.displayError(title, message, reason, t);
		}
	}

	/**
	 * Opens the Configuration editor.
	 * 
	 * @param config
	 * @param editorId
	 */
	private void openEditor(MethodConfiguration config, String editorId)
			throws PartInitException {
		ConfigurationEditorInput editorInput = new ConfigurationEditorInput(
				config);
		AuthoringUIPlugin.getDefault().getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().openEditor(
						editorInput, editorId);
	}

	/**
	 * Opens the Method editor for the given method element.
	 * 
	 * @param element
	 *            A Method element.
	 * @param editorId
	 *            The editor ID.
	 */
	private void openEditor(MethodElement element, String editorId)
			throws PartInitException {
		MethodElementEditorInput editorInput = new MethodElementEditorInput(
				element);
		AuthoringUIPlugin.getDefault().getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().openEditor(
						editorInput, editorId);
	}

	/**
	 * Selects (brings to front, but does not give focus) the Method editor for
	 * the given object. If there is no open editor for the object, does
	 * nothing.
	 * 
	 * @param obj
	 */
	public void selectEditor(Object obj) {
		try {
			obj = ViewHelper.handleDangling(obj);
			if (obj == null)
				return;
			obj = LibraryUtil.unwrap(obj);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		IEditorInput editorInput = null;
		if (obj instanceof MethodConfiguration)
			editorInput = new ConfigurationEditorInput(
					(MethodConfiguration) obj);
		else if (obj instanceof MethodElement)
			editorInput = new MethodElementEditorInput((MethodElement) obj);
		if (editorInput != null) {
			IWorkbenchPage page = AuthoringUIPlugin.getDefault().getWorkbench()
					.getActiveWorkbenchWindow().getActivePage();
			IEditorPart editorPart = page.findEditor(editorInput);
			if (editorPart != null)
				page.bringToTop(editorPart);
		}
	}

	/**
	 * 
	 * Returns selected editor input
	 * @return The object which the currently selected Method editor is editing (or
	 *         null if there is no editor or if the editor is not a Method editor).
	 */
	public Object getActiveMethodEditorInput() {
		IEditorPart editorPart = AuthoringUIPlugin.getDefault().getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		return getMethodEditorInput(editorPart);
	}

	public Object getMethodEditorInput(IEditorPart editorPart) {
		if (editorPart != null) {
			IEditorInput editorInput = editorPart.getEditorInput();
			if (editorInput instanceof ConfigurationEditorInput)
				return ((ConfigurationEditorInput) editorInput)
						.getConfiguration();
			if (editorInput instanceof MethodElementEditorInput)
				return ((MethodElementEditorInput) editorInput)
						.getMethodElement();
		}
		return null;
	}

	/**
	 * Closes the Method editor for the given Method element.
	 * 
	 * @param obj
	 *            A Method element.
	 */
	public void closeEditor(Object obj) {
		try {
			IEditorPart editor = findEditor(obj);
			if (editor != null) {
				AuthoringUIPlugin.getDefault().getWorkbench()
						.getActiveWorkbenchWindow().getActivePage()
						.closeEditor(editor, false);
			}
		} catch (NullPointerException e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(e);
		}
	}
	
	public IEditorPart findEditor(Object obj) {
		try {
			MethodElementEditorInput editorInput = new MethodElementEditorInput(
					(MethodElement) obj);
			IEditorPart editor = AuthoringUIPlugin.getDefault().getWorkbench()
					.getActiveWorkbenchWindow().getActivePage().findEditor(
							editorInput);
			return editor;
		} catch (NullPointerException e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(e);
		}
		return null;
	}

	/**
	 * Returns list of dirty editors for the given elements
	 * @param elements
	 * @return	List of dirty editor
	 */
	public Collection getElementsWithDirtyEditor(Collection elements) {
		ArrayList result = new ArrayList();

		IWorkbenchPage workbenchPage = AuthoringUIPlugin.getDefault()
				.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IEditorReference[] editorReferences = workbenchPage
				.getEditorReferences();
		// ArrayList closeEditorRefs = new ArrayList();
		for (int i = 0; i < editorReferences.length; i++) {
			IEditorReference reference = editorReferences[i];
			IEditorPart editor = reference.getEditor(true);
			if (editor != null && editor.isDirty()) {
				IEditorInput input = editor.getEditorInput();
				MethodElement element = null;
				if (input instanceof MethodElementEditorInput) {
					element = ((MethodElementEditorInput) input)
							.getMethodElement();
				} else if (input instanceof ConfigurationEditorInput) {
					element = ((ConfigurationEditorInput) input)
							.getConfiguration();
				}
				if (element != null) {
					for (Iterator iter = elements.iterator(); iter.hasNext();) {
						Object e = iter.next();
						if (e == element || UmaUtil.isContainedBy(element, e)) {
							result.add(element);
							iter.remove();
						}
					}
				}
			}
		}

		return result;
	}

	/**
	 * Closes the all open Method editors ofor the given (deleted) element and
	 * all of its children and grand children.
	 * 
	 * @param e
	 *            the deleted MethodElement
	 */
	public void closeEditorsOnDeletion(Object e) {
		IWorkbenchPage workbenchPage = AuthoringUIPlugin.getDefault()
				.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IEditorReference[] editorReferences = workbenchPage
				.getEditorReferences();
		ArrayList closeEditorRefs = new ArrayList();
		for (int i = 0; i < editorReferences.length; i++) {
			IEditorReference reference = editorReferences[i];
			IEditorPart editor = reference.getEditor(true);
			if (editor != null) {
				IEditorInput input = editor.getEditorInput();
				MethodElement element = null;
				if (input instanceof MethodElementEditorInput) {
					element = ((MethodElementEditorInput) input)
							.getMethodElement();
				} else if (input instanceof ConfigurationEditorInput) {
					element = ((ConfigurationEditorInput) input)
							.getConfiguration();
				}
				if (element != null
						&& (element.eContainer() == null || UmaUtil
								.isContainedBy(element, e))) {
					closeEditorRefs.add(reference);
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
	 * Closes all Method Editors without saving.
	 *
	 */
	public void closeAllMethodEditors() {
		closeAllMethodEditors(false);
	}
	
	public void closeAllMethodEditors(boolean saveFlag) {
		final IWorkbench workbench = PlatformUI.getWorkbench();
		final boolean save = saveFlag;
		workbench.getDisplay().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
				if (window != null) {
					IWorkbenchPage workbenchPage = window.getActivePage();
					List<IEditorReference> closeEditorRefs = getOpenMethodEditors();
					workbenchPage.closeEditors(closeEditorRefs.toArray(new IEditorReference[closeEditorRefs.size()]), save);
				}
			}
		});
	}
	
	/**
	 * Closes all Method Editors with saving.
	 *
	 */
	public void closeAllMethodEditorsWithSaving() {
		closeAllMethodEditors(true);
	}
	
	/**
	 * @return a list of all open Method Editors
	 */
	public List<IEditorReference> getOpenMethodEditors() {
		final IWorkbench workbench = PlatformUI.getWorkbench();
		final List<IEditorReference> methodEditorRefs = new ArrayList<IEditorReference>();
		workbench.getDisplay().syncExec(new Runnable() {
			public void run() {
				IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
				if (window != null) {
					IWorkbenchPage workbenchPage = window.getActivePage();
					IEditorReference[] editorReferences = workbenchPage.getEditorReferences();
					for (int i = 0; i < editorReferences.length; i++) {
						IEditorReference reference = editorReferences[i];
						IEditorPart editorPart = reference.getEditor(false);
						String editorId = reference.getId();
						if (editorId.startsWith("org.eclipse.epf") || //$NON-NLS-1$
								editorPart instanceof IMethodEditor) {
							methodEditorRefs.add(reference);
						}
					}
				}
			}
		});
		return methodEditorRefs;
	}
	

	/**
	 * Close all MethodEditors associated with the given Plugin.
	 * Will cause a user-prompt for each dirty editor
	 * 
	 */
	public void closeMethodEditorsForPluginElements(MethodPlugin methodplugin) {
		if (AuthoringUIPlugin.getDefault().getWorkbench()
				.getActiveWorkbenchWindow() != null
				&& AuthoringUIPlugin.getDefault().getWorkbench()
						.getActiveWorkbenchWindow().getActivePage() != null) {
			IWorkbenchPage workbenchPage = AuthoringUIPlugin.getDefault()
				.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			List<IEditorReference> list = getOpenMethodEditors();
			List<IEditorReference> editorRefsToClose = new ArrayList<IEditorReference>();
			for (IEditorReference editorReference : list) {
				IEditorPart editor = editorReference.getEditor(true);
				if (editor != null) {
					IEditorInput input = editor.getEditorInput();
					MethodElement element = null;
					if (input instanceof MethodElementEditorInput) {
						element = ((MethodElementEditorInput) input)
								.getMethodElement();
					} else if (input instanceof ConfigurationEditorInput) {
						element = ((ConfigurationEditorInput) input)
								.getConfiguration();
					} else if(input instanceof DiagramEditorInputProxy){
						DiagramEditorInput eInput = ((DiagramEditorInputProxy)input).getDiagramEditorInput();
						element = eInput.getMethodElement();
						if(element == null){
							if(eInput.getWrapper() != null){
								element = (MethodElement)TngUtil.unwrap(eInput.getWrapper());
							}
						}
					}
					if (element != null
							&& (element.eContainer() == null || UmaUtil
									.isContainedBy(element, methodplugin))) {
						if (!element.equals(methodplugin))
							editorRefsToClose.add(editorReference);
					}
				}
			}
			workbenchPage.closeEditors(editorRefsToClose.toArray(new IEditorReference[editorRefsToClose.size()]), true);
		}
	}

	public void closeEditors(Object e, boolean promptSave) {
		IWorkbenchPage workbenchPage = AuthoringUIPlugin.getDefault()
				.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IEditorReference[] editorReferences = workbenchPage
				.getEditorReferences();
		ArrayList<IEditorReference> closeEditorRefs = new ArrayList<IEditorReference>();
		for (int i = 0; i < editorReferences.length; i++) {
			IEditorReference reference = editorReferences[i];
			IEditorPart editor = reference.getEditor(true);
			if (editor != null) {
				IEditorInput input = editor.getEditorInput();
				MethodElement element = null;
				if (input instanceof MethodElementEditorInput) {
					element = ((MethodElementEditorInput) input)
							.getMethodElement();
				} else if (input instanceof ConfigurationEditorInput) {
					element = ((ConfigurationEditorInput) input)
							.getConfiguration();
				}
				if (element != null && (element == e || UmaUtil.isContainedBy(element, e))) {
					closeEditorRefs.add(reference);
				}
			}
		}
		int size = closeEditorRefs.size();
		IEditorReference[] references = new IEditorReference[size];
		for (int i = 0; i < size; i++) {
			references[i] = (IEditorReference) closeEditorRefs.get(i);
		}
		workbenchPage.closeEditors(references, promptSave);
	}

}