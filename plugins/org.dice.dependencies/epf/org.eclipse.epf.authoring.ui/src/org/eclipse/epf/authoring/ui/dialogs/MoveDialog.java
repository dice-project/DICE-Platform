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
package org.eclipse.epf.authoring.ui.dialogs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.views.ViewHelper;
import org.eclipse.epf.common.ui.util.MsgDialog;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.TransientGroupItemProvider;
import org.eclipse.epf.library.edit.category.StandardCategoriesItemProvider;
import org.eclipse.epf.library.edit.command.MethodElementAddCommand.MoveOperation;
import org.eclipse.epf.library.edit.command.MethodElementAddCommand.MoveOperationExt;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.DependencyChecker;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.VariabilityElement;
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
 * Dialog for moving MethodElement within MethodLibrary.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class MoveDialog extends Dialog implements ISelectionChangedListener {

	private TreeViewer treeViewer;

	protected Collection elements;

	// private IStructuredSelection selection;

	private boolean lockedUI = false;

	// The progress monitor
	private ProgressMonitorPart progressMonitorPart;

	private EditingDomain editingDomain;

	protected Object destination;

	private Cursor waitCursor;

	private boolean moving;

	public MoveDialog(Shell parentShell, Collection elementsToMove,
			EditingDomain editingDomain) {
		super(parentShell);
		this.editingDomain = editingDomain;

		// filter out the predefined elements to prevent them from getting moved
		//
		elements = new ArrayList();
		for (Iterator iter = elementsToMove.iterator(); iter.hasNext();) {
			Object element = iter.next();
			Object e = TngUtil.unwrap(element);
			if (e instanceof MethodElement
					&& TngUtil.isPredefined((MethodElement) e)) {
				continue;
			}
			elements.add(element);
		}
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

		// Insert a progress monitor
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(AuthoringUIResources.MoveDialog_move_text); 
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
		if (doMove()) {
			super.okPressed();
		}
	}

	protected boolean isValidDestination() {
		// preventing moving elements to category
		//
		if (destination instanceof ContentCategory) {
			return false;
		}

		// do not allow moving into Standard Categories
		if (destination instanceof StandardCategoriesItemProvider) {
			return false;
		}

		// check containers for compatible types will not work if we want to move a sub-artifact
		// to a content package
		//
		
//		// do not allow move if destination is not the same type as every
//		// elements' eContainer
//		Object realDest = destination;
//		if (destination instanceof TransientContentPackageItemProvider) {
//			realDest = ((TransientContentPackageItemProvider) destination)
//					.getTarget();
//		}
//		if (destination instanceof TransientGroupItemProvider) {
//			realDest = ((TransientGroupItemProvider) destination).getTarget();
//		}		
//		try {
//			for (Iterator iter = elements.iterator(); iter.hasNext();) {
//				Object e = iter.next();
//				if (e instanceof EObject) {
//					if (!realDest.getClass().isInstance(
//							((EObject) e).eContainer()))
//						return false;
//				}
//			}
//		} catch (Exception ex) {
//			AuthoringUIPlugin.getDefault().getLogger().logError(ex);
//			return false;
//		}
		
		//Do not allow moving into a destination which is 
		//locked through extended locker.
		if(ViewHelper.isExtendedLocked(destination)){
			return false;
		}	
		if (DependencyChecker.newCheck) {
			if (destination instanceof VariabilityElement) {
				if (! DependencyChecker.checkCircularForMovingVariabilityElement
						((VariabilityElement) destination, elements)) {
					return false;
				}
			}			
		}
		// Sub Artifact circular check code
		else if(destination instanceof Artifact){
			return DependencyChecker.checkCircularForArtifacts((Artifact)destination, 
					elements);
		}		
		
		Command addCommand = AddCommand.create(editingDomain, destination,
				null, elements);
		boolean result;
		try {
			result = addCommand.canExecute();
		}
		finally {
			addCommand.dispose();
		}
		if (!result) {
			return false;
		}

		if (destination instanceof TransientGroupItemProvider) {
			destination = ((TransientGroupItemProvider) destination)
					.getTarget();
		}

		// IStatus status = UserInteractionHelper.checkModify((EObject)
		// destination, getShell());
		// if (status.isOK()) {
		// return true;
		// }
		// else {
		// AuthoringUIPlugin.getDefault().getMsgDialog().display(
		// AuthoringUIResources.getString("AuthoringUIPlugin.moveDialog.title"),
		// //$NON-NLS-1$
		// status);
		// return false;
		// }

		return true;
	}

	/**
	 * @return
	 */
	private boolean doMove() {
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

		moving = true;
		final Shell shell = getShell();
		shell.setCursor(waitCursor);

		getButton(IDialogConstants.OK_ID).setEnabled(false);
		getButton(IDialogConstants.CANCEL_ID).setEnabled(false);
		treeViewer.getControl().setEnabled(false);

		progressMonitorPart.setVisible(true);
		progressMonitorPart
				.beginTask(
						AuthoringUIResources.MoveDialog_moving_text, IProgressMonitor.UNKNOWN); 
		MoveOperation moveOp = null;
		try {
			// create command
			//
			Command command = AddCommand.create(editingDomain, destination,
					null, elements);

			moveOp = newMoveOperation(command, progressMonitorPart, shell);
			
			moveOp.run();
		} finally {
			moving = false;
			progressMonitorPart.done();

			// clear cursor
			if (shell != null && !shell.isDisposed()) {
				shell.setCursor(null);
			}
		}

		if (moveOp != null) {
			IStatus status = moveOp.getStatus();
			if (!status.isOK()) {
				String title = AuthoringUIResources.dialogs_MoveDialog_errorTitle; 
				String message = AuthoringUIResources.dialogs_MoveDialog_errorMessage; 
				AuthoringUIPlugin.getDefault().getMsgDialog().displayError(
						title, message, status);
				if (moveOp.reloadNeeded()) {
					ViewHelper.reloadCurrentLibrary(shell, null);
				}
			}
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#close()
	 */
	public boolean close() {
		if (moving)
			return false;
		return super.close();
	}
	
	protected MoveOperation newMoveOperation(Command command, IProgressMonitor monitor,
			Object shell) {
		return new MoveOperation(command, monitor, shell);
	}

	//MoveDialog for moving custom category
	public static class MoveDialogExt extends MoveDialog {
		private List<CustomCategory> movingCCs = new ArrayList<CustomCategory>();
		private List<CustomCategory> movingCCsrcParents;
		private CustomCategory tgtParent;
		private boolean samePluginMove = false;
		
		public MoveDialogExt(Shell parentShell, Collection elementsToMove,
			EditingDomain editingDomain, List<CustomCategory> movingCCsrcParents) {
			super(parentShell, elementsToMove, editingDomain);
			this.movingCCsrcParents = movingCCsrcParents;
			movingCCs.addAll(elementsToMove);
			Set<CustomCategory> set = new HashSet<CustomCategory>(elements);
			if (set.size() != elements.size()) {
				elements = new ArrayList();
				for (CustomCategory cc : set) {
					elements.add(cc);
				}
			}
		}
		
		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			destination = TngUtil.unwrap(((IStructuredSelection) event
					.getSelection()).getFirstElement());
			tgtParent = null;
			if (destination instanceof CustomCategory) {
				tgtParent = (CustomCategory) destination;
				MethodPlugin plugin = UmaUtil.getMethodPlugin(tgtParent);
				samePluginMove = UmaUtil.getMethodPlugin(movingCCs.get(0)) == UmaUtil.getMethodPlugin(tgtParent);
				if (! samePluginMove) {
					destination = UmaUtil.findContentPackage(plugin, ModelStructure.DEFAULT.customCategoryPath);
				}
			}
		}
		
		@Override
		protected MoveOperation newMoveOperation(Command command, IProgressMonitor monitor,
				Object shell) {
			return new MoveOperationExt(command, monitor, shell, movingCCs, movingCCsrcParents, tgtParent);
		}
		
		@Override
		protected boolean isValidDestination() {
			if (! (tgtParent instanceof CustomCategory)) {
				return false;
			}
			if (samePluginMove) {
				if (!DependencyChecker
						.checkCircularForMovingVariabilityElement(
								(VariabilityElement) destination, movingCCs)) {
					return false;
				}
				return true;
			}
			return super.isValidDestination();
		}
		
		
	}

}
