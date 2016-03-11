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
package org.eclipse.epf.authoring.ui.properties;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.AdapterFactoryTreeIterator;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkOrder;
import org.eclipse.epf.uma.WorkOrderType;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


/**
 * Dialog for adding predecessor dependency
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class PredecessorDialog extends Dialog {

	private Text predIdText;

	private Text presentationName;

	private Combo dependency;

	/** Dependency Literals * */
	private static final String FINISH_TO_START = PropertiesResources.WorkOrderType_FINISH_TO_START; 

	private static final String FINISH_TO_FINISH = PropertiesResources.WorkOrderType_FINISH_TO_FINISH; 

	private static final String START_TO_START = PropertiesResources.WorkOrderType_START_TO_START; 

	private static final String START_TO_FINISH = PropertiesResources.WorkOrderType_START_TO_FINISH; 

	private String[] dependencyList = new String[] { FINISH_TO_START,
			FINISH_TO_FINISH, START_TO_START, START_TO_FINISH };

	private WorkBreakdownElement element;

	private Object process;

	private AdapterFactory adapterFactory;

	private List predecessors = new ArrayList();

	private List predMapList;

	private IActionManager actionMgr;

	private PredecessorMap predMap;

	/**
	 * Dialog for setting predecessor for the element
	 * @param parentShell
	 * 			The parent shell
	 * @param element
	 * 			Work Break down Element
	 * @param process
	 * 			Process of the workbreakdown element
	 * @param adapterFactory
	 * 			Adapter Factory 
	 * @param predMap
	 * 			Predecessor Map
	 * @param predMapList
	 * 			Predecessor Map list
	 * @param actionMgr
	 * 			Action Manager
	 */
	public PredecessorDialog(Shell parentShell, WorkBreakdownElement element,
			Object process, AdapterFactory adapterFactory,
			PredecessorMap predMap, List predMapList, IActionManager actionMgr) {
		super(parentShell);

		this.element = element;
		this.process = process;
		this.adapterFactory = adapterFactory;
		this.predMap = predMap;
		this.predMapList = predMapList;
		this.actionMgr = actionMgr;

	}

	/**
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);

		GridLayout layout = (GridLayout) composite.getLayout();
		layout.numColumns = 2;
		GridData gridData = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(gridData);

		// dialog header
		Label label = new Label(composite, SWT.NONE);
		label.setText(PropertiesResources.PredecessorDialog_HeaderMessage); 
		gridData = new GridData(GridData.BEGINNING);
		gridData.horizontalSpan = 2;
		label.setLayoutData(gridData);

		// blank label
		label = new Label(composite, SWT.NONE);
		label.setText(""); //$NON-NLS-1$
		gridData = new GridData(GridData.BEGINNING);
		gridData.horizontalSpan = 2;
		label.setLayoutData(gridData);

		// Predecessor ID index
		label = new Label(composite, SWT.NONE);
		label.setText(PropertiesResources.PredecessorDialog_Index); 
		gridData = new GridData(GridData.BEGINNING);
		label.setLayoutData(gridData);

		predIdText = new Text(composite, SWT.BORDER);
		gridData = new GridData(GridData.GRAB_HORIZONTAL);
		gridData.widthHint = 100;
		predIdText.setLayoutData(gridData);
		predIdText.setText(new Integer(predMap.getId()).toString());
		// predIdText.setEditable(false);

		// Presentaion Name
		label = new Label(composite, SWT.NONE);
		label.setText(PropertiesResources.PredecessorDialog_PresentationName); 
		gridData = new GridData(GridData.BEGINNING);
		label.setLayoutData(gridData);

		presentationName = new Text(composite, SWT.BORDER);
		gridData = new GridData(GridData.GRAB_HORIZONTAL);
		gridData.widthHint = 400;
		presentationName.setEditable(false);
		presentationName.setLayoutData(gridData);

		WorkOrder wo = predMap.getWorkOrder();
		presentationName.setText(ProcessUtil.getPresentationName(wo.getPred()));

		// Dependency
		label = new Label(composite, SWT.NONE);
		label.setText(PropertiesResources.PredecessorDialog_Dependency); 
		gridData = new GridData(GridData.BEGINNING);
		label.setLayoutData(gridData);

		dependency = new Combo(composite, SWT.SINGLE | SWT.READ_ONLY);
		gridData = new GridData(GridData.BEGINNING);
		dependency.setLayoutData(gridData);
		dependency.setItems(dependencyList);

		String depedencyName = wo.getLinkType().getName();
		if (wo.getLinkType() == WorkOrderType.FINISH_TO_FINISH) {
			depedencyName = FINISH_TO_FINISH;
		} else if (wo.getLinkType() == WorkOrderType.FINISH_TO_START) {
			depedencyName = FINISH_TO_START;
		} else if (wo.getLinkType() == WorkOrderType.START_TO_FINISH) {
			depedencyName = START_TO_FINISH;
		} else if (wo.getLinkType() == WorkOrderType.START_TO_START) {
			depedencyName = START_TO_START;
		}
		dependency.setText(depedencyName);

		Label line = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL
				| SWT.BOLD);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 2;
		line.setLayoutData(gridData);

		predIdText.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent event) {
				int newId;
				try {
					newId = new Integer(predIdText.getText()).intValue();
					if (newId < 0)
						throw new Exception();
				} catch (Exception e) {
					AuthoringUIPlugin
							.getDefault()
							.getMsgDialog()
							.displayWarning(
									PropertiesResources.Process_predecessors_validNumberTitle, 
									PropertiesResources.Process_predecessors_validNumberMessage); 

					predIdText.setText(new Integer(predMap.getId()).toString());
					predIdText.setFocus();
					predIdText.selectAll();
					return;
				}

				if (alreadyExists(newId)) {
					predIdText.setText(new Integer(predMap.getId()).toString());

					predIdText.selectAll();
					return;
				}

				String predIDList = getPredId(newId);
				if (predIDList == null)
					return;

				String str = ProcessUtil.checkPredecessorList(element,
						predIDList, adapterFactory, process, predecessors);
				if (str != null) {
					AuthoringUIPlugin
							.getDefault()
							.getMsgDialog()
							.displayError(
									PropertiesResources.PredecessorDialog_PredecessorErrorDialogTitle, str); 
					predIdText.setText(new Integer(predMap.getId()).toString());
					return;
				}
				presentationName.setText(getName(newId));
			}
		});

		return composite;
	}

	/**
	 * Get presentation name/name for this predecessor id
	 * 
	 * @param newId
	 * @return
	 */
	private String getName(int newId) {
		String name = null;
		for (Iterator iter = new AdapterFactoryTreeIterator(adapterFactory,
				process); iter.hasNext();) {
			Object obj = iter.next();
			IBSItemProvider itemProvider = (IBSItemProvider) adapterFactory
					.adapt(obj, ITreeItemContentProvider.class);
			Integer id = new Integer(itemProvider.getId());
			obj = TngUtil.unwrap(obj);

			if (newId == id.intValue()) {
				name = ((BreakdownElement) obj).getPresentationName();
				if (StrUtil.isBlank(name)) {
					name = ((BreakdownElement) obj).getName();
				}
			}
		}
		return name;
	}

	/**
	 * Check to see whether predecessor Id already exists
	 * 
	 * @param newId
	 * @return
	 */
	private boolean alreadyExists(int newId) {
		for (Iterator itor = predMapList.iterator(); itor.hasNext();) {
			PredecessorMap map = (PredecessorMap) itor.next();
			if ((map.getId() == newId) && (predMap.getId() != newId)) {
				AuthoringUIPlugin
						.getDefault()
						.getMsgDialog()
						.displayError(
								PropertiesResources.PredecessorDialog_PredecessorErrorDialogTitle, 
								PropertiesResources.PredecessorDialog_PredecessorErrorMessage); 

				return true;
			}
		}
		return false;
	}

	/**
	 * Get command separated list of all predecessor
	 * 
	 * @param newId
	 * @return
	 */
	private String getPredId(int newId) {
		StringBuffer buf = new StringBuffer();

		for (Iterator itor = predMapList.iterator(); itor.hasNext();) {
			PredecessorMap map = (PredecessorMap) itor.next();
			if (predMap.getId() == map.getId()) {
				buf.append(newId);
			} else {
				buf.append(map.getId());
			}

			buf.append(","); //$NON-NLS-1$
		}
		return buf.toString();
	}

	/**
	 * 
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell parent) {
		super.configureShell(parent);
		parent
				.setText(PropertiesResources.PredecessorDialog_Title); 
	}

	/**
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		// new id
		int newId = new Integer(predIdText.getText()).intValue();
		// ne link type
		WorkOrderType workOrderType = getWorkOrderType();

		if ((newId != predMap.getId())
				|| (!workOrderType.equals(predMap.getWorkOrder().getLinkType()))) {
			String predIDList = getPredId(newId);
			if (predIDList == null)
				return;

			String str = ProcessUtil.checkPredecessorList(element, predIDList,
					adapterFactory, process, predecessors);
			if (str != null) {
				AuthoringUIPlugin
						.getDefault()
						.getMsgDialog()
						.displayError(
								PropertiesResources.PredecessorDialog_PredecessorErrorDialogTitle, str); 
				predIdText.setText(new Integer(predMap.getId()).toString());
			}

			// remove it
			boolean status = actionMgr.doAction(IActionManager.REMOVE, element,
					UmaPackage.eINSTANCE
							.getWorkBreakdownElement_LinkToPredecessor(),
					predMap.getWorkOrder(), -1);
			if (!status) {
				close();
				return;
			}
			predMapList.remove(predMap);

			int size = predecessors.size();

			for (int i = 0; i < size; i++) {
				WorkBreakdownElement e = (WorkBreakdownElement) predecessors
						.get(i);

				boolean found = false;
				find_pred: for (Iterator iterator = element
						.getLinkToPredecessor().iterator(); iterator.hasNext();) {
					WorkOrder wo = (WorkOrder) iterator.next();
					if (wo.getPred() == e) {
						found = true;
						break find_pred;
					}
				}
				if (!found) {
					WorkOrder wo = UmaFactory.eINSTANCE.createWorkOrder();
					wo.setPred(e);
					wo.setLinkType(workOrderType);
					boolean stat = actionMgr
							.doAction(
									IActionManager.ADD,
									element,
									UmaPackage.eINSTANCE
											.getWorkBreakdownElement_LinkToPredecessor(),
									wo, -1);
					if (!stat)
						return;

					predMap.setId(newId);
					predMap.setWorkOrder(wo);
					predMapList.add(predMap);
				}
			}
		}

		super.okPressed();
	}

	/**
	 * Get work order link type from UI
	 * 
	 * @return 
	 * 			Work Order type
	 */
	private WorkOrderType getWorkOrderType() {
		int index = dependency.getSelectionIndex();
		WorkOrderType type;
		if (index == 0) {
			type = WorkOrderType.FINISH_TO_START;
		} else if (index == 1) {
			type = WorkOrderType.FINISH_TO_FINISH;
		} else if (index == 2) {
			type = WorkOrderType.START_TO_START;
		} else {
			type = WorkOrderType.START_TO_FINISH;
		}

		return type;
	}

	/**
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#close()
	 */
	public boolean close() {

		return super.close();
	}
}
