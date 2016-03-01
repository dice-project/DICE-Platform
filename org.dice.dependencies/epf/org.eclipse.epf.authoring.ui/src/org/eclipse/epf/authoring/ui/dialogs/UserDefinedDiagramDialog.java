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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditorInput;
import org.eclipse.epf.authoring.ui.editors.ProcessEditor;
import org.eclipse.epf.authoring.ui.filters.ProcessGuidanceFilter;
import org.eclipse.epf.diagram.model.util.DiagramInfo;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.configuration.GuidanceItemProvider;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.SupportingMaterial;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;


/**
 * Dialog to assign user defined diagram to process elements
 * 
 * @author Shilpa Toraskar
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class UserDefinedDiagramDialog extends Dialog {

	private Button assignADImageButton, assignADDImageButton,
			assignWPDImageButton;

	private Button clearADImageButton, clearADDImageButton,
			clearWPDImageButton;

	private Button useADImageButton, useADDImageButton, useWPDImageButton;

	private Text adImageText, addImageText, wpdImageText;

	private IEditorPart editor;

	private Activity activity;

	private Composite area;

	private final String tabName = FilterConstants.GUIDANCE;

	private IFilter filter;

	private SupportingMaterial activityDiagram, activityDetailDiagram,
			wpdDiagram;

	private static final int buttonWidthHint = 60;
	
	private Set existingList;
	
	private boolean isWrapped = false;

	/**
	 * Creates an instance
	 * @param parent
	 * @param editor
	 * @param activity
	 * @param config
	 * @param isWrapped
	 */
	public UserDefinedDiagramDialog(Shell parent, IEditorPart editor,
			Activity activity, MethodConfiguration config, boolean isWrapped) {
		super(parent);

		this.editor = editor;
		this.isWrapped = isWrapped;
		
		this.activity = activity;
		this.filter = new ProcessGuidanceFilter(config, null, tabName) {
			public boolean childAccept(Object obj) {
				if (super.childAccept(obj))
					return true;
				if (obj instanceof GuidanceItemProvider) {
					String name = ((GuidanceItemProvider) obj).getText(obj);
					if (name
							.equalsIgnoreCase(FilterConstants.SUPPORTING_MATERIALS)
							&& 
							!((GuidanceItemProvider) obj).getChildren(obj)
									.isEmpty())
						return true;
					else
						return false;
				}
				if ((obj instanceof SupportingMaterial))
					return true;
				return false;
			}
		};
		
		existingList = new HashSet();
	}

	/**
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createDialogArea(Composite parent) {
		area = (Composite) super.createDialogArea(parent);
		final GridLayout gridLayout = new GridLayout(4, false);
		area.setLayout(gridLayout);

		// Use AD Image for publishing
		Label blankLabel = new Label(area, SWT.NONE);
		blankLabel.setText(""); //$NON-NLS-1$

		useADImageButton = new Button(area, SWT.CHECK);
		useADImageButton.setText(AuthoringUIResources.UserDefinedDiagramDialog_useADImage); 
		GridData buttonGD = new GridData(GridData.BEGINNING);
		buttonGD.horizontalSpan = 3;
		useADImageButton.setLayoutData(buttonGD);

		// Assign activity diagram image
		Label aLabel = new Label(area, SWT.NONE);
		aLabel.setText(AuthoringUIResources.UserDefinedDiagramDialog_adImage); 

		adImageText = new Text(area, SWT.BORDER | SWT.READ_ONLY);
		GridData gd = new GridData(GridData.BEGINNING
				| GridData.FILL_HORIZONTAL);
		gd.widthHint = 300;
		adImageText.setLayoutData(gd);

		assignADImageButton = new Button(area, SWT.NONE);
		assignADImageButton
				.setText(AuthoringUIResources.UserDefinedDiagramDialog_assignButton); 
		GridData buttonGridData = new GridData(GridData.BEGINNING
				| GridData.FILL_HORIZONTAL);
		buttonGridData.widthHint = buttonWidthHint;
		assignADImageButton.setLayoutData(buttonGridData);

		clearADImageButton = new Button(area, SWT.NONE);
		clearADImageButton.setText(AuthoringUIResources.clearButton_text); 
		clearADImageButton.setLayoutData(buttonGridData);

		// Use ADD Image for publishing
		blankLabel = new Label(area, SWT.NONE);
		blankLabel.setText(""); //$NON-NLS-1$

		useADDImageButton = new Button(area, SWT.CHECK);
		useADDImageButton.setText(AuthoringUIResources.UserDefinedDiagramDialog_useADDImage); 
		buttonGD = new GridData(GridData.BEGINNING);
		buttonGD.horizontalSpan = 3;
		useADDImageButton.setLayoutData(buttonGD);

		// Assign activity detail diagram image
		aLabel = new Label(area, SWT.NONE);
		aLabel.setText(AuthoringUIResources.UserDefinedDiagramDialog_addImage); 

		addImageText = new Text(area, SWT.BORDER | SWT.READ_ONLY);
		addImageText.setLayoutData(gd);

		assignADDImageButton = new Button(area, SWT.NONE);
		assignADDImageButton
				.setText(AuthoringUIResources.UserDefinedDiagramDialog_assignButton); 
		assignADDImageButton.setLayoutData(buttonGridData);

		clearADDImageButton = new Button(area, SWT.NONE);
		clearADDImageButton.setText(AuthoringUIResources.clearButton_text); 
		clearADDImageButton.setLayoutData(buttonGridData);

		// Use WPD Image for publishing
		blankLabel = new Label(area, SWT.NONE);
		blankLabel.setText(""); //$NON-NLS-1$

		useWPDImageButton = new Button(area, SWT.CHECK);
		useWPDImageButton.setText(AuthoringUIResources.UserDefinedDiagramDialog_useWPDImage); 
		buttonGD = new GridData(GridData.BEGINNING );
		buttonGD.horizontalSpan = 3;
		useWPDImageButton.setLayoutData(buttonGD);

		// Assign work product dependency diagram image
		aLabel = new Label(area, SWT.NONE);
		aLabel.setText(AuthoringUIResources.UserDefinedDiagramDialog_wpdImage); 

		wpdImageText = new Text(area, SWT.BORDER | SWT.READ_ONLY);
		wpdImageText.setLayoutData(gd);

		assignWPDImageButton = new Button(area, SWT.NONE);
		assignWPDImageButton
				.setText(AuthoringUIResources.UserDefinedDiagramDialog_assignButton); 
		assignWPDImageButton.setLayoutData(buttonGridData);

		clearWPDImageButton = new Button(area, SWT.NONE);
		clearWPDImageButton.setText(AuthoringUIResources.clearButton_text); 
		clearWPDImageButton.setLayoutData(buttonGridData);

		// loadData
		loadData();
		
		// disableControls
		if (TngUtil.isLocked(activity) || isWrapped){
			disableControls();
		}

		// add listeners
		addListeners();

		// set image and title
		super
				.getShell()
				.setText(
						AuthoringUIResources.UserDefinedDiagramDialog_title); 

		return area;
	}

	/**
	 * Disable controls
	 *
	 */
	private void disableControls()
	{
		useADImageButton.setEnabled(false);
		useADDImageButton.setEnabled(false);
		useWPDImageButton.setEnabled(false);
		
		assignADImageButton.setEnabled(false);
		assignADDImageButton.setEnabled(false);
		assignWPDImageButton.setEnabled(false);
		
		clearADImageButton.setEnabled(false);
		clearADDImageButton.setEnabled(false);
		clearWPDImageButton.setEnabled(false);
	}
	
	/**
	 * Loads initial data
	 *
	 */
	private void loadData() {		
		//	 load existing supporting materials list
		existingList.addAll(activity.getSupportingMaterials());

		
		DiagramInfo info = new DiagramInfo(activity);
		if (info.getActivityDiagram() != null) {
			adImageText.setText(TngUtil.getLabelWithPath(info
					.getActivityDiagram()));
			activityDiagram = info.getActivityDiagram();
			
			existingList.add(activityDiagram);
		}
		if (info.getActivityDetailDiagram() != null) {
			addImageText.setText(TngUtil.getLabelWithPath(info
					.getActivityDetailDiagram()));
			activityDetailDiagram = info.getActivityDetailDiagram();
			
			existingList.add(activityDetailDiagram);
		}
		if (info.getWPDDiagram() != null) {
			wpdImageText
					.setText(TngUtil.getLabelWithPath(info.getWPDDiagram()));
			wpdDiagram = info.getWPDDiagram();
			
			existingList.add(wpdDiagram);
		}

		useADImageButton.setSelection(info.canPublishADImage());
		useADDImageButton.setSelection(info.canPublishADDImage());
		useWPDImageButton.setSelection(info.canPublishWPDImage());
	}

	/**
	 * Add listeners
	 */
	private void addListeners() {
		assignADImageButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					SupportingMaterial sm = openFilterDialog();
					if (sm != null) {
						activityDiagram = sm;
						adImageText.setText(TngUtil.getLabelWithPath(sm));
						existingList.add(activityDiagram);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		assignADDImageButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					SupportingMaterial sm = openFilterDialog();
					if (sm != null) {
						activityDetailDiagram = sm;
						addImageText.setText(TngUtil.getLabelWithPath(sm));
						existingList.add(activityDetailDiagram);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		assignWPDImageButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					SupportingMaterial sm = openFilterDialog();
					if (sm != null) {
						wpdDiagram = sm;
						wpdImageText.setText(TngUtil.getLabelWithPath(sm));
						existingList.add(wpdDiagram);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		clearADImageButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				existingList.remove(activityDiagram);
				adImageText.setText(""); //$NON-NLS-1$
				activityDiagram = null;
			}
		});

		clearADDImageButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				existingList.remove(activityDetailDiagram);
				addImageText.setText(""); //$NON-NLS-1$
				activityDetailDiagram = null;
			}
		});

		clearWPDImageButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				existingList.remove(wpdDiagram);
				wpdImageText.setText(""); //$NON-NLS-1$
				wpdDiagram = null;
			}
		});
	}

	/**
	 * Open a filter dialog
	 */
	private SupportingMaterial openFilterDialog() {
	
		ItemsFilterDialog fd = new ItemsFilterDialog(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(), filter, activity,
				FilterConstants.SUPPORTING_MATERIALS, Arrays.asList(existingList.toArray()));
		
		fd.setTitle(FilterConstants.SUPPORTING_MATERIALS);
		fd.setViewerSelectionSingle(true);
		fd.setInput(UmaUtil.getMethodLibrary((EObject) activity));
		fd.setBlockOnOpen(true);
		fd.open();

		List list = fd.getSelectedItems();
		if ((list != null) && (!list.isEmpty())) {
			Object obj = list.get(0);
			if (obj instanceof SupportingMaterial) {
				return (SupportingMaterial) obj;
			}
		}

		return null;
	}

	/**
	 * Called when the OK button is selected.
	 */
	protected void okPressed() {
		if (TngUtil.isLocked(activity) || isWrapped){
			// then don't need to do anything.. just return
			super.okPressed();
		}
		boolean editorDirty = false;
		DiagramInfo info = new DiagramInfo(activity);

		// set activity diagram
		if (activityDiagram != info.getActivityDiagram()) {
			editorDirty = true;
		}
		info.setActivityDiagram(activityDiagram);

		// set activity detail diagram
		if (!editorDirty) {
			if (activityDetailDiagram != info.getActivityDetailDiagram())
				editorDirty = true;
		}
		info.setActivityDetailDiagram(activityDetailDiagram);

		// set work product dependency diagram
		if (!editorDirty) {
			if (wpdDiagram != info.getWPDDiagram())
				editorDirty = true;
		}
		info.setWPDDiagram(wpdDiagram);

		// set can publish flag for activity diagram
		boolean oldCanPublishFlag = info.canPublishADImage();
		boolean newCanPublishFlag = useADImageButton.getSelection();
		if ((!editorDirty) && (oldCanPublishFlag != newCanPublishFlag))
			editorDirty = true;
		info.setPublishADImage(useADImageButton.getSelection());

		// set can publish flag for activity detail diagram
		oldCanPublishFlag = info.canPublishADDImage();
		newCanPublishFlag = useADDImageButton.getSelection();
		if ((!editorDirty) && (oldCanPublishFlag != newCanPublishFlag))
			editorDirty = true;
		info.setPublishADDImage(useADDImageButton.getSelection());

		// set can publish flag for work product dependency diagram
		oldCanPublishFlag = info.canPublishWPDImage();
		newCanPublishFlag = useWPDImageButton.getSelection();
		if ((!editorDirty) && (oldCanPublishFlag != newCanPublishFlag))
			editorDirty = true;
		info.setPublishWPDImage(useWPDImageButton.getSelection());

		// System.out.println("EDitor dirty=" + editorDirty);
		if (editorDirty) {

			if (!promptSaveActiveEditor()) {
				return;
			}
		}

		saveActiveEditor();
		super.okPressed();
	}

	private boolean promptSaveActiveEditor() {
		String title = AuthoringUIResources.processFormEditorSaveDialog_title; 
		String message = AuthoringUIResources.processFormEditorSaveDialog_message2; 
		// if (editor.isDirty()) {
		return AuthoringUIPlugin.getDefault().getMsgDialog()
				.displayConfirmation(title, message);
		// }
		// return true;
	}

	private void saveActiveEditor() {
		// save the editor
		BusyIndicator.showWhile(editor.getEditorSite().getShell().getDisplay(),
				new Runnable() {

					public void run() {
						((ProcessEditor) editor).resourcesToSave
								.add(((ProcessComponent) ((MethodElementEditorInput) editor
										.getEditorInput()).getMethodElement())
										.getProcess().eResource());
						// System.out.println("saving..."); //$NON-NLS-1$
						editor.doSave(new NullProgressMonitor());
						// System.out.println("saving done"); //$NON-NLS-1$
					}

				});
	}

}
