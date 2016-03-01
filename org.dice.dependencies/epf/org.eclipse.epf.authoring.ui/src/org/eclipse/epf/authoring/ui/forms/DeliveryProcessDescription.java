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

import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditor;
import org.eclipse.epf.authoring.ui.richtext.IMethodRichText;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;


/**
 * Description page for delivery process
 * 
 * @author Shilpa Toraskar
 * @author Kelvin Low
 * @since 1.0
 */
public class DeliveryProcessDescription extends ProcessDescription {

	private IMethodRichText ctrl_scale, ctrl_project_characteristics;

	private IMethodRichText ctrl_risk_level, ctrl_estimating_techniques;

	private IMethodRichText ctrl_project_member_expertise,
			ctrl_type_of_contract;

	private DeliveryProcess deliveryProcess;

	/**
	 * Creates an instance
	 * @param editor
	 */
	public DeliveryProcessDescription(FormEditor editor) {
		super(editor);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.ProcessFormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		deliveryProcess = (DeliveryProcess) process;

	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.ProcessDescription#createDetailSection()
	 */
	protected void createDetailSection(FormToolkit toolkit) {
		super.createDetailSection(toolkit);

		// scale
		ctrl_scale = createRichTextEditWithLinkForSection(toolkit,
				detailComposite, AuthoringUIResources.Process_Scale, 40, 400, DETAIL_SECTION_ID); 

		// project characteristics
		ctrl_project_characteristics = createRichTextEditWithLinkForSection(
				toolkit,
				detailComposite,
				AuthoringUIResources.Process_ProjectCharacteristics, 40, 400, DETAIL_SECTION_ID); 

		// risk level
		ctrl_risk_level = createRichTextEditWithLinkForSection(
				toolkit,
				detailComposite,
				AuthoringUIResources.Process_RiskLevel, 40, 400, DETAIL_SECTION_ID); 

		// Estimating technique
		ctrl_estimating_techniques = createRichTextEditWithLinkForSection(
				toolkit,
				detailComposite,
				AuthoringUIResources.Process_EstimatingTechnique, 40, 400, DETAIL_SECTION_ID); 

		// Project Member expertise
		ctrl_project_member_expertise = createRichTextEditWithLinkForSection(
				toolkit,
				detailComposite,
				AuthoringUIResources.Process_ProjectMemberExpertise, 40, 400, DETAIL_SECTION_ID); 

		// Type of contract
		ctrl_type_of_contract = createRichTextEditWithLinkForSection(
				toolkit,
				detailComposite,
				AuthoringUIResources.Process_TypeOfContract, 40, 400, DETAIL_SECTION_ID); 
		toolkit.paintBordersFor(detailComposite);

	}

	/**
	 * Loads initial data from model
	 * 
	 * @see org.eclipse.epf.authoring.ui.forms.ProcessDescription#loadData()
	 */
	public void loadData() {
		super.loadData();

		org.eclipse.epf.uma.DeliveryProcessDescription content = (org.eclipse.epf.uma.DeliveryProcessDescription) deliveryProcess
				.getPresentation();
		String scale = content.getScale();
		String projectChar = content.getProjectCharacteristics();
		String estimatingTechnique = content.getEstimatingTechnique();
		String riskLevel = content.getRiskLevel();
		String projectMemberExpertiese = content.getProjectMemberExpertise();
		String typeOfContract = content.getTypeOfContract();

		ctrl_scale.setText(scale == null ? "" : scale); //$NON-NLS-1$
		ctrl_project_characteristics
				.setText(projectChar == null ? "" : projectChar); //$NON-NLS-1$
		ctrl_estimating_techniques
				.setText(estimatingTechnique == null ? "" : estimatingTechnique); //$NON-NLS-1$
		ctrl_risk_level.setText(riskLevel == null ? "" : riskLevel); //$NON-NLS-1$
		ctrl_project_member_expertise
				.setText(projectMemberExpertiese == null ? "" : projectMemberExpertiese); //$NON-NLS-1$
		ctrl_type_of_contract
				.setText(typeOfContract == null ? "" : typeOfContract); //$NON-NLS-1$
	}

	/**
	 * Add listeners
	 * 
	 * @see org.eclipse.epf.authoring.ui.forms.ProcessDescription#addListeners()
	 */
	protected void addListeners() {
		super.addListeners();
		MethodElementEditor editor = ((MethodElementEditor) getEditor());
		final IActionManager actionMgr = editor.getActionManager();
		final org.eclipse.epf.uma.DeliveryProcessDescription content = (org.eclipse.epf.uma.DeliveryProcessDescription) deliveryProcess
				.getPresentation();

		ctrl_scale.setModalObject(deliveryProcess.getPresentation());
		ctrl_scale.setModalObjectFeature(UmaPackage.eINSTANCE
				.getDeliveryProcessDescription_Scale());
		ctrl_scale.addModifyListener(contentModifyListener);
		ctrl_scale.addListener(SWT.Deactivate, new Listener() {
			public void handleEvent(Event e) {
				IMethodRichText control = descExpandFlag ? ctrl_expanded
						: ctrl_scale;
				if (!control.getModified()) {
					return;
				}
				String oldContent = content.getScale();
				if (((MethodElementEditor) getEditor()).mustRestoreValue(
						control, oldContent)) {
					return;
				}
				String newContent = control.getText();
				if (!newContent.equals(oldContent)) {
					actionMgr.doAction(IActionManager.SET, deliveryProcess
							.getPresentation(), UmaPackage.eINSTANCE
							.getDeliveryProcessDescription_Scale(), newContent,
							-1);
				}
			}
		});

		ctrl_project_characteristics.setModalObject(deliveryProcess
				.getPresentation());
		ctrl_project_characteristics.setModalObjectFeature(UmaPackage.eINSTANCE
				.getDeliveryProcessDescription_ProjectCharacteristics());
		ctrl_project_characteristics.addModifyListener(contentModifyListener);
		ctrl_project_characteristics.addListener(SWT.Deactivate,
				new Listener() {
					public void handleEvent(Event e) {
						IMethodRichText control = descExpandFlag ? ctrl_expanded
								: ctrl_project_characteristics;
						if (!control.getModified()) {
							return;
						}
						String oldContent = content.getProjectCharacteristics();
						if (((MethodElementEditor) getEditor())
								.mustRestoreValue(control, oldContent)) {
							return;
						}
						String newContent = control.getText();
						if (!newContent.equals(oldContent)) {
							actionMgr
									.doAction(
											IActionManager.SET,
											deliveryProcess.getPresentation(),
											UmaPackage.eINSTANCE
													.getDeliveryProcessDescription_ProjectCharacteristics(),
											newContent, -1);
						}
					}
				});

		ctrl_risk_level.setModalObject(deliveryProcess.getPresentation());
		ctrl_risk_level.setModalObjectFeature(UmaPackage.eINSTANCE
				.getDeliveryProcessDescription_RiskLevel());
		ctrl_risk_level.addModifyListener(contentModifyListener);
		ctrl_risk_level.addListener(SWT.Deactivate, new Listener() {
			public void handleEvent(Event e) {
				IMethodRichText control = descExpandFlag ? ctrl_expanded
						: ctrl_risk_level;
				if (!control.getModified()) {
					return;
				}
				String oldContent = content.getRiskLevel();
				if (((MethodElementEditor) getEditor()).mustRestoreValue(
						control, oldContent)) {
					return;
				}
				String newContent = control.getText();
				if (!newContent.equals(oldContent)) {
					actionMgr.doAction(IActionManager.SET, deliveryProcess
							.getPresentation(), UmaPackage.eINSTANCE
							.getDeliveryProcessDescription_RiskLevel(),
							newContent, -1);
				}
			}
		});

		ctrl_estimating_techniques.setModalObject(deliveryProcess
				.getPresentation());
		ctrl_estimating_techniques.setModalObjectFeature(UmaPackage.eINSTANCE
				.getDeliveryProcessDescription_EstimatingTechnique());
		ctrl_estimating_techniques.addModifyListener(contentModifyListener);
		ctrl_estimating_techniques.addListener(SWT.Deactivate, new Listener() {
			public void handleEvent(Event e) {
				IMethodRichText control = descExpandFlag ? ctrl_expanded
						: ctrl_estimating_techniques;
				if (!control.getModified()) {
					return;
				}
				String oldContent = content.getEstimatingTechnique();
				if (((MethodElementEditor) getEditor()).mustRestoreValue(
						control, oldContent)) {
					return;
				}
				String newContent = control.getText();
				if (!newContent.equals(oldContent)) {
					actionMgr
							.doAction(
									IActionManager.SET,
									deliveryProcess.getPresentation(),
									UmaPackage.eINSTANCE
											.getDeliveryProcessDescription_EstimatingTechnique(),
									newContent, -1);
				}
			}
		});

		ctrl_project_member_expertise.setModalObject(deliveryProcess
				.getPresentation());
		ctrl_project_member_expertise
				.setModalObjectFeature(UmaPackage.eINSTANCE
						.getDeliveryProcessDescription_ProjectMemberExpertise());
		ctrl_project_member_expertise.addModifyListener(contentModifyListener);
		ctrl_project_member_expertise.addListener(SWT.Deactivate,
				new Listener() {
					public void handleEvent(Event e) {
						IMethodRichText control = descExpandFlag ? ctrl_expanded
								: ctrl_project_member_expertise;
						if (!control.getModified()) {
							return;
						}
						String oldContent = content.getProjectMemberExpertise();
						if (((MethodElementEditor) getEditor())
								.mustRestoreValue(control, oldContent)) {
							return;
						}
						String newContent = control.getText();
						if (!newContent.equals(oldContent)) {
							actionMgr
									.doAction(
											IActionManager.SET,
											deliveryProcess.getPresentation(),
											UmaPackage.eINSTANCE
													.getDeliveryProcessDescription_ProjectMemberExpertise(),
											newContent, -1);
						}
					}
				});

		ctrl_type_of_contract.setModalObject(deliveryProcess.getPresentation());
		ctrl_type_of_contract.setModalObjectFeature(UmaPackage.eINSTANCE
				.getDeliveryProcessDescription_TypeOfContract());
		ctrl_type_of_contract.addModifyListener(contentModifyListener);
		ctrl_type_of_contract.addListener(SWT.Deactivate, new Listener() {
			public void handleEvent(Event e) {
				IMethodRichText control = descExpandFlag ? ctrl_expanded
						: ctrl_type_of_contract;
				if (!control.getModified()) {
					return;
				}
				String oldContent = content.getTypeOfContract();
				if (((MethodElementEditor) getEditor()).mustRestoreValue(
						control, oldContent)) {
					return;
				}
				String newContent = control.getText();
				if (!newContent.equals(oldContent)) {
					actionMgr.doAction(IActionManager.SET, deliveryProcess
							.getPresentation(), UmaPackage.eINSTANCE
							.getDeliveryProcessDescription_TypeOfContract(),
							newContent, -1);
				}
			}
		});
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.ProcessDescription#refresh(boolean)
	 */
	protected void refresh(boolean editable) {
		super.refresh(editable);
		ctrl_estimating_techniques.setEditable(editable);
		ctrl_project_characteristics.setEditable(editable);
		ctrl_project_member_expertise.setEditable(editable);
		ctrl_risk_level.setEditable(editable);
		ctrl_scale.setEditable(editable);
		ctrl_type_of_contract.setEditable(editable);
	}

}
