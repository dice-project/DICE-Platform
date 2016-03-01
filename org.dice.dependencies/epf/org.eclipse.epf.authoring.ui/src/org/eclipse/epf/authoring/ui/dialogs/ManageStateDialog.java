/*******************************************************************************
* Licensed Materials - Property of IBM
* (c) Copyright IBM Corporation 2007,2009. All Rights Reserved.
*
* Note to U.S. Government Users Restricted Rights:
* Use, duplication or disclosure restricted by GSA ADP Schedule
* Contract with IBM Corp. 
*******************************************************************************/

package org.eclipse.epf.authoring.ui.dialogs;

import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.forms.WorkProductStatesPage;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.util.MethodElementPropUtil;
import org.eclipse.epf.library.edit.util.MethodPluginPropUtil;
import org.eclipse.epf.uma.Constraint;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableFontProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * 
 * @author achen
 * @since 7.5.1
 *
 */
public class ManageStateDialog extends Dialog {
	private TableViewer statesViewer;
	private IStructuredContentProvider statesViewerContentProvider;
	private ITableLabelProvider statesViewerLabelProvider;
	private Text des;
	private Button addBtn, deleteBtn, modifyBtn, closeBtn;
	
	private Shell shell;
	private MethodPlugin activePlugin;
	private IActionManager actionMgr;
	private WorkProductStatesPage page;
	
	public ManageStateDialog(Shell shell, MethodPlugin activePlugin,
			IActionManager actionMgr, WorkProductStatesPage page) {
		super(shell);
		
		this.shell = shell;
		this.activePlugin = activePlugin;
		this.actionMgr = actionMgr;
		this.page = page;
	}
	
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(AuthoringUIResources.ManageStateDialog_title);
	}
	
	protected void createButtonsForButtonBar(Composite parent) {
		//
	}
	
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		GridLayout layout = (GridLayout) composite.getLayout();
		layout.numColumns = 2;
		
		Composite viewComp = new Composite(composite, SWT.NONE);
		viewComp.setLayoutData(new GridData(GridData.FILL_BOTH));
		viewComp.setLayout(new GridLayout());
		Label stateLabel = new Label(viewComp, SWT.NULL);
		stateLabel.setText(AuthoringUIResources.ManageStateDialog_label_state);
		statesViewer = new TableViewer(viewComp, SWT.BORDER);
		{
			GridData gd = new GridData(GridData.FILL_BOTH);
			gd.widthHint = 250;
			gd.heightHint = 200;
			statesViewer.getTable().setLayoutData(gd);
		}
		initProviders();
		statesViewer.setContentProvider(statesViewerContentProvider);
		statesViewer.setLabelProvider(statesViewerLabelProvider);
		statesViewer.setComparator(new StateViewerComparator());
		statesViewer.setInput(new Object());
		Label desLabel = new Label(viewComp, SWT.NULL);
		desLabel.setText(AuthoringUIResources.ManageStateDialog_label_des);
		des = new Text(viewComp, SWT.MULTI | SWT.WRAP | SWT.BORDER | SWT.V_SCROLL);
		{
			GridData gd = new GridData(GridData.FILL_BOTH);
			gd.heightHint = 100;
			des.setLayoutData(gd);
		}
		des.setEditable(false);
		des.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
				
		Composite btnComp = new Composite(composite, SWT.NONE);
		btnComp.setLayoutData(new GridData(GridData.FILL_BOTH));
		btnComp.setLayout(new GridLayout());
		new Label(btnComp, SWT.NULL);
		addBtn = new Button(btnComp, SWT.PUSH);
		addBtn.setText(AuthoringUIResources.ManageStateDialog_addBtn_label);
		addBtn.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		deleteBtn = new Button(btnComp, SWT.PUSH);
		deleteBtn.setText(AuthoringUIResources.ManageStateDialog_deleteBtn_label);
		deleteBtn.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		deleteBtn.setEnabled(false);
		modifyBtn = new Button(btnComp, SWT.PUSH);
		modifyBtn.setText(AuthoringUIResources.ManageStateDialog_modifyBtn_label);
		modifyBtn.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		modifyBtn.setEnabled(false);
		closeBtn = new Button(btnComp, SWT.NULL);
		closeBtn.setText(AuthoringUIResources.ManageStateDialog_closeBtn_label);
		closeBtn.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		addListeners();
		updateControls();
				
		return composite;
	}
	
	private void initProviders() {
		statesViewerContentProvider = new AdapterFactoryContentProvider(
				TngAdapterFactory.INSTANCE.getNavigatorView_ComposedAdapterFactory()) {
			public Object[] getElements(Object object) {
		    	List<Constraint> allLocalStates = MethodPluginPropUtil.getMethodPluginPropUtil(actionMgr)
		    	.getWorkProductStatesInPlugin(activePlugin);
		    	
		    	return allLocalStates.toArray();
			}
		};

		statesViewerLabelProvider = new StatesLabelProvider(
				TngAdapterFactory.INSTANCE.getNavigatorView_ComposedAdapterFactory());
	}
	
	private void addListeners() {
		statesViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				updateControls();
			}			
		});
		
		addBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				StateEditDialog dialog = new StateEditDialog(shell, true, null, null);
				if (dialog.open() == Dialog.OK) {
					Constraint state = MethodPluginPropUtil.getMethodPluginPropUtil(actionMgr).getWorkProductState(
							activePlugin, dialog.getStateName(), true);
					if (state != null) {
						UmaPackage up = UmaPackage.eINSTANCE;
						actionMgr.doAction(IActionManager.SET, state,
								up.getMethodElement_BriefDescription(), dialog.getStateDes(), -1);						
					}					
				}
				
				updateControls();
			}
		});
		
		deleteBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection)statesViewer.getSelection();
				if (selection.size() > 0) {
					Constraint state = (Constraint)selection.getFirstElement();
					if (!getConfirm(state)) {
						return;
					}
					MethodPluginPropUtil.getMethodPluginPropUtil(actionMgr).removeWorkProductState(
							activePlugin, state.getBody());
				}
				
				updateControls();
			}
		});
		
		modifyBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection)statesViewer.getSelection();
				if (selection.size() > 0) {
					Constraint state = (Constraint)selection.getFirstElement();
					if (!getConfirm(state)) {
						return;
					}
					String oldStateName = state.getBody();
					String oldStateDes = state.getBriefDescription();
					
					StateEditDialog dialog = new StateEditDialog(shell, false, oldStateName, oldStateDes);
					if (dialog.open() == Dialog.OK) {
						UmaPackage up = UmaPackage.eINSTANCE;
						String newStateName = dialog.getStateName();
						String newStateDes = dialog.getStateDes();
						if (!newStateName.equals(oldStateName)) {
							actionMgr.doAction(IActionManager.SET, state,
									up.getConstraint_Body(), newStateName, -1);
						}
						if (!newStateDes.equals(oldStateDes)) {
							actionMgr.doAction(IActionManager.SET, state,
									up.getMethodElement_BriefDescription(), newStateDes, -1);
						}
					}				
				}
				
				updateControls();
			}
		});
		
		closeBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				buttonPressed(IDialogConstants.CANCEL_ID);
			}
		});
	}
	
	private boolean getConfirm(Constraint state) {		
		List<WorkProduct> wps = MethodElementPropUtil.getMethodElementPropUtil().getAssignedToWorkProducts(state);
		
		if (wps.size() == 0) {
			return true;
		} else {
			StringBuffer names = new StringBuffer();
			for (WorkProduct wp : wps) {
				names.append(wp.getName());
				names.append(","); //$NON-NLS-1$
			}			
			String namesList = names.substring(0, names.length() -1);			
			String msg = AuthoringUIResources.bind(AuthoringUIResources.ManageStateDialog_warn_msg,
					new Object[]{state.getBody(), namesList});
			
			if (MessageDialog.openConfirm(shell, AuthoringUIResources.ManageStateDialog_warn_title, msg)) {
				return true;
			}			
		}
		
		return false;		
	}
	
	private void updateControls() {
		IStructuredSelection selection = (IStructuredSelection)statesViewer.getSelection();
		if (selection.size() > 0) {
			Constraint state = (Constraint)selection.getFirstElement();
			des.setText(state.getBriefDescription());
			deleteBtn.setEnabled(true);
			modifyBtn.setEnabled(true);
		} else {
			des.setText(""); //$NON-NLS-1$
			deleteBtn.setEnabled(false);
			modifyBtn.setEnabled(false);
		}

		statesViewer.refresh();
		
		//Since we change model in the dialog, so also need update UI of the WP state page
		page.updateControls();
	}
	
	//All states here are in host plug-in, so show as boldface
	private class StatesLabelProvider extends AdapterFactoryLabelProvider implements ITableFontProvider {
		private FontRegistry registry = new FontRegistry();
		private Font systemFont;
		
		public StatesLabelProvider(AdapterFactory adapterFactory) {
			super(adapterFactory);
		}
		
		public String getColumnText(Object element, int columnIndex) {
			if (element instanceof Constraint) {
				return ((Constraint)element).getBody();
			}
			
			return null;
		}
		
	    public Font getFont(Object element, int columnIndex) {
	    	if (systemFont == null) {
	    		systemFont = Display.getCurrent().getSystemFont();
	    	}
	    	
	    	if ((element instanceof Constraint)) {
	    		return registry.getBold(systemFont.getFontData()[0].getName());
	    	}

	    	return systemFont;
	    }	
	}
	
	public class StateViewerComparator extends ViewerComparator {
		public int compare(Viewer viewer, Object e1, Object e2) {
			if ((e1 instanceof Constraint) && (e2 instanceof Constraint)) {
				String name1 = ((Constraint)e1).getBody();
				String name2 = ((Constraint)e2).getBody();					
				return getComparator().compare(name1, name2);
			}
			
			return 0;
		}
	}

}
