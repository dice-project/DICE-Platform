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
package org.eclipse.epf.library.ui.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.common.ui.util.MsgBox;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.LibraryResources;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.command.DeleteMethodElementCommand;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.IRunnableWithProgress;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.IValidator;
import org.eclipse.epf.library.edit.validation.internal.ContentElementNameValidator;
import org.eclipse.epf.library.edit.validation.internal.ValidatorFactory;
import org.eclipse.epf.library.ui.LibraryUIPlugin;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.ContentDescriptionFactory;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ListDialog;

/**
 * class to convert guidance types
 * 
 * @author ???
 * @sicne 1.0
 *
 */
public class ConvertGuidanceType {

	static IValidator validator;
	private static int[] compatibleGuidances = { UmaPackage.CONCEPT,
	// UmaPackage.CHECKLIST,
			// UmaPackage.EXAMPLE,
			UmaPackage.GUIDELINE,
			// UmaPackage.ESTIMATE,
			// UmaPackage.ESTIMATING_METRIC,
			// UmaPackage.ESTIMATION_CONSIDERATIONS,
			// UmaPackage.REPORT,
			// UmaPackage.TEMPLATE,
			UmaPackage.SUPPORTING_MATERIAL,
	// UmaPackage.TOOL_MENTOR,
	// UmaPackage.WHITEPAPER,
	// UmaPackage.TERM_DEFINITION,
	// UmaPackage.PRACTICE,
	// UmaPackage.REUSABLE_ASSET
	};

	private static List compatibleGuidancesList = new ArrayList();
	static {
		for (int i = 0; i < compatibleGuidances.length; i++)
			compatibleGuidancesList.add(new Integer(compatibleGuidances[i]));
	}

	public static Guidance convertGuidance(Guidance oldGuidance, Shell shell,
			DeleteMethodElementCommand command) {
		return convertGuidance(oldGuidance, shell, command, null);
	}
		
	public static Guidance convertGuidance(final Guidance oldGuidance, Shell shell,
				DeleteMethodElementCommand command, EClass newType) {
		if (newType == null) {
			if (shell == null) {
				shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
			}
			ListDialog dlg = new ListDialog(shell);
			dlg.setHeightInChars(5);
			dlg.setContentProvider(new ArrayContentProvider());
			dlg.setLabelProvider(new LabelProvider() {
				public String getText(Object element) {
					switch (((Integer) element).intValue()) {
					// TODO: refactor these strings (and this whole dialog) into
					// library.ui
					case UmaPackage.CONCEPT:
						return LibraryResources.concept_text; 
					case UmaPackage.CHECKLIST:
						return LibraryResources.checklist_text; 
					case UmaPackage.EXAMPLE:
						return LibraryResources.example_text; 
					case UmaPackage.GUIDELINE:
						return LibraryResources.guideline_text; 
					case UmaPackage.ESTIMATION_CONSIDERATIONS:
						return LibraryResources.estimationConsiderations_text;
					case UmaPackage.REPORT:
						return LibraryResources.report_text; 
					case UmaPackage.TEMPLATE:
						return LibraryResources.template_text; 
					case UmaPackage.SUPPORTING_MATERIAL:
						return LibraryResources.supportingMaterial_text;
					case UmaPackage.TOOL_MENTOR:
						return LibraryResources.toolMentor_text;
					case UmaPackage.WHITEPAPER:
						return LibraryResources.whitepaper_text;
					case UmaPackage.TERM_DEFINITION:
						return LibraryResources.termDefinition_text;
					case UmaPackage.PRACTICE:
						return LibraryResources.practice_text; 
					case UmaPackage.REUSABLE_ASSET:
						return LibraryResources.reusableAsset_text;
					default:
						return LibraryResources.unknownGuidance_text;
					}

				}
			});
			List newGuidanceTypeList = getValidNewGuidanceTypes(oldGuidance);
			if (newGuidanceTypeList == null) {
				LibraryUIPlugin
				.getDefault()
				.getMsgDialog()
				.displayError(
						LibraryResources.convertGuidanceError_title,
						LibraryResources.unsupportedGuidanceTypeError_msg,
						NLS.bind(LibraryResources.unsupportedGuidanceTypeError_reason, StrUtil.toLower(TngUtil.getTypeText(oldGuidance)))); 
				return null;
			}
			dlg.setInput(newGuidanceTypeList);
			dlg.setTitle(LibraryResources.convertGuidanceDialog_title);
			dlg.setMessage(LibraryResources.convertGuidanceDialog_text);
			if (dlg.open() == Dialog.CANCEL)
				return null;

			Object[] selectionResult = dlg.getResult();
			if (selectionResult == null)
				return null;
			int chosenGuidance = ((Integer) selectionResult[0]).intValue();

//			Guidance newGuidance = doConvert(oldGuidance, chosenGuidance, command);

			newType = getGuidanceType(chosenGuidance);
		}
		
		if(newType == null) {
			return null;
		}
		final EClass finalNewType = newType;
		final TypeConverter.TypeConversionCommand[] cmdHolder = new TypeConverter.TypeConversionCommand[1];
		try {
			final MultiStatus multiStatus = new MultiStatus(
					LibraryEditPlugin.INSTANCE.getSymbolicName(), 0, "", null); //$NON-NLS-1$
			UserInteractionHelper.getUIHelper().runWithProgress(new IRunnableWithProgress() {

				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {
					TypeConverter.TypeConversionCommand cmd = TypeConverter.createTypeConversionCommand(oldGuidance, finalNewType, null, null, true, true);
					cmdHolder[0] = cmd;
					if(!cmd.getIllegalReferencers().isEmpty()) {
						// type conversion will remove some illegal references
						// confirm with user before continue
						//
						for (Iterator iter = cmd.getIllegalReferencers().iterator(); iter.hasNext();) {
							MethodElement e = (MethodElement) iter.next();
							// don't show predefined element
							//
							if(!TngUtil.isPredefined(e)) {
								String msg = NLS.bind(LibraryEditResources.elementType_text, e.eClass().getName(), TngUtil.getLabelWithPath(e)); 
								IStatus status = new Status(IStatus.INFO,
										LibraryEditPlugin.INSTANCE.getSymbolicName(), 0, msg,
										null);
								multiStatus.add(status);
							}
						}
					}
				}
				
			}, false, "Looking for invalid references...");
			if (multiStatus.getChildren().length > 0 &&
					LibraryUIPlugin
					.getDefault()
					.getMsgDialog()
					.displayConfirmation(
							LibraryResources.convertGuidanceDialog_title,
							LibraryEditResources.confirm_remove_references_text,
							multiStatus) == Dialog.CANCEL) {
				return null;
			}
			final TypeConverter.TypeConversionCommand cmd = cmdHolder[0];
			if(cmd == null) {
				return null;
			}
			IStatus status = UserInteractionHelper.checkModify(cmd.getModifiedResources(), shell);
			if(!status.isOK()) {
				LibraryUIPlugin.getDefault().getMsgDialog().display(LibraryResources.convertGuidanceDialog_title, status);
				return null;
			}
			UserInteractionHelper.getUIHelper().runWithProgress(new IRunnableWithProgress() {

				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {
					cmd.execute();
				}
				
			}, false, "Performing type change...");
			final Guidance newGuidance = (Guidance) cmd.getResult().iterator().next();
			final Exception[] exceptionHolder = new Exception[1];
			Exception busyCursorWhileException = null;
			// save modified resources
			//
			try {
				PlatformUI.getWorkbench().getProgressService().busyCursorWhile(new org.eclipse.jface.operation.IRunnableWithProgress() {

					public void run(IProgressMonitor monitor)
							throws InvocationTargetException, InterruptedException {
						monitor.beginTask("Saving changes", IProgressMonitor.UNKNOWN);
						ILibraryPersister.FailSafeMethodLibraryPersister persister = LibraryServiceUtil.getCurrentPersister().getFailSafePersister();
						try {
							for (Iterator iter = cmd.getModifiedResources().iterator(); iter.hasNext();) {
								Resource resource = (Resource) iter.next();
								resource.setModified(true);
								monitor.subTask("Saving " + resource.getURI().toFileString());
								persister.save(resource);
							}
							monitor.subTask("Commiting saves");
							persister.commit();
							
							if(ContentDescriptionFactory.hasPresentation(newGuidance)) {
								Resource resource = newGuidance.getPresentation().eResource();
								monitor.subTask("Moving " + resource.getURI().toFileString() + " to right location");
								persister.adjustLocation(resource);
							}				
							monitor.subTask("Copying resources");
							cmd.copyResources();

						} catch (Exception e) {
							persister.rollback();
							exceptionHolder[0] = e;
						}
					}
					
				});
			} catch (InvocationTargetException e) {
				busyCursorWhileException = e;
			} catch (InterruptedException e) {
				busyCursorWhileException = e;
			}
//			UserInteractionHelper.getUIHelper().runWithProgress(new IRunnableWithProgress() {
//
//				public void run(IProgressMonitor monitor)
//						throws InvocationTargetException, InterruptedException {
//					monitor.beginTask("Saving changes", IProgressMonitor.UNKNOWN);
//					ILibraryPersister.FailSafeMethodLibraryPersister persister = LibraryServiceUtil.getCurrentPersister().getFailSafePersister();
//					try {
//						for (Iterator iter = cmd.getModifiedResources().iterator(); iter.hasNext();) {
//							Resource resource = (Resource) iter.next();
//							resource.setModified(true);
//							monitor.subTask("Saving " + resource.getURI().toFileString());
//							persister.save(resource);
//						}
//						monitor.subTask("Commiting saves");
//						persister.commit();
//						
//						if(ContentDescriptionFactory.hasPresentation(newGuidance)) {
//							Resource resource = newGuidance.getPresentation().eResource();
//							monitor.subTask("Moving " + resource.getURI().toFileString() + " to right location");
//							persister.adjustLocation(resource);
//						}				
//						monitor.subTask("Copying resources");
//						cmd.copyResources();
//
//					} catch (Exception e) {
//						persister.rollback();
//						exceptionHolder[0] = e;
//					}
//				}
//				
//			}, false, "Saving changes...");
			Exception exception = busyCursorWhileException != null ? busyCursorWhileException : exceptionHolder[0];
			if(exception != null) {
				LibraryUIPlugin.getDefault().getMsgDialog().displayError(
						LibraryResources.convertGuidanceError_title,
						NLS.bind(LibraryResources.saveConvertedGuidanceError_msg,
								newGuidance.getName()),
								LibraryResources.error_reason, exception);

			}
			
			return newGuidance;
		}
		finally {
			if(cmdHolder[0] != null) {
				try { cmdHolder[0].dispose(); } catch(Exception e) {}
			}
		}
	}

	/**
	 * @param oldGuidance
	 * @param newGuidance
	 * @return
	 */
	private static String checkName(Guidance oldGuidance, Guidance newGuidance) {
		// TODO Auto-generated method stub
		
		
		validator = new ContentElementNameValidator(oldGuidance.eContainer()
				, UmaPackage.eINSTANCE.getContentPackage_ContentElements(),
				(ContentElement) newGuidance, new ValidatorFactory.TypeFilter(newGuidance));
		
		String msg = validator.isValid(newGuidance.getName());
		
		if (msg != null) {
			String featureTxt = TngUtil.getFeatureText(UmaPackage.eINSTANCE
					.getNamedElement_Name());
			String title = LibraryEditResources.resolveNameConflictDialog_title; 
			String dlgMsg = NLS.bind(
					LibraryEditResources.resolveNameConflictDialog_text,
					StrUtil.toLower(featureTxt), newGuidance.getName());
			String currentValue = (String) newGuidance.eGet(UmaPackage.eINSTANCE
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
	
	private static EClass getGuidanceType(int newGuidanceType) {
		switch (newGuidanceType) {
		case UmaPackage.CONCEPT:
			return UmaPackage.Literals.CONCEPT;
		case UmaPackage.CHECKLIST:
			return UmaPackage.Literals.CHECKLIST;
		case UmaPackage.EXAMPLE:
			return UmaPackage.Literals.EXAMPLE;
		case UmaPackage.GUIDELINE:
			return UmaPackage.Literals.GUIDELINE;
		case UmaPackage.ESTIMATION_CONSIDERATIONS:
			return UmaPackage.Literals.ESTIMATION_CONSIDERATIONS;
		case UmaPackage.REPORT:
			return UmaPackage.Literals.REPORT;
		case UmaPackage.TEMPLATE:
			return UmaPackage.Literals.TEMPLATE;
		case UmaPackage.SUPPORTING_MATERIAL:
			return UmaPackage.Literals.SUPPORTING_MATERIAL;
		case UmaPackage.TOOL_MENTOR:
			return UmaPackage.Literals.TOOL_MENTOR;
		case UmaPackage.WHITEPAPER:
			return UmaPackage.Literals.WHITEPAPER;
		case UmaPackage.TERM_DEFINITION:
			return UmaPackage.Literals.TERM_DEFINITION;
		case UmaPackage.PRACTICE:
			return UmaPackage.Literals.PRACTICE;
		case UmaPackage.REUSABLE_ASSET:
			return UmaPackage.Literals.REUSABLE_ASSET;
		default:
			return null;
		}
	}

	private static Guidance createNewGuidance(int newGuidanceType) {
		switch (newGuidanceType) {
		case UmaPackage.CONCEPT:
			return UmaFactory.eINSTANCE.createConcept();
		case UmaPackage.CHECKLIST:
			return UmaFactory.eINSTANCE.createChecklist();
		case UmaPackage.EXAMPLE:
			return UmaFactory.eINSTANCE.createExample();
		case UmaPackage.GUIDELINE:
			return UmaFactory.eINSTANCE.createGuideline();
		case UmaPackage.ESTIMATION_CONSIDERATIONS:
			return UmaFactory.eINSTANCE.createEstimationConsiderations();
		case UmaPackage.REPORT:
			return UmaFactory.eINSTANCE.createReport();
		case UmaPackage.TEMPLATE:
			return UmaFactory.eINSTANCE.createTemplate();
		case UmaPackage.SUPPORTING_MATERIAL:
			return UmaFactory.eINSTANCE.createSupportingMaterial();
		case UmaPackage.TOOL_MENTOR:
			return UmaFactory.eINSTANCE.createToolMentor();
		case UmaPackage.WHITEPAPER:
			return UmaFactory.eINSTANCE.createWhitepaper();
		case UmaPackage.TERM_DEFINITION:
			return UmaFactory.eINSTANCE.createTermDefinition();
		case UmaPackage.PRACTICE:
			return UmaFactory.eINSTANCE.createPractice();
		case UmaPackage.REUSABLE_ASSET:
			return UmaFactory.eINSTANCE.createReusableAsset();
		default:
			return null;
		}
	}

	public static List getValidNewGuidanceTypes(Guidance oldGuidance) {
		if (oldGuidance == null)
			return null;
		Integer oldGuidanceClassID = new Integer(oldGuidance.eClass()
				.getClassifierID());
		if (!compatibleGuidancesList.contains(oldGuidanceClassID))
			return null;
		List guidanceList = new ArrayList();
		for (int i = 0; i < compatibleGuidances.length; i++) {
			Integer compatibleGuidanceTypeClassID = new Integer(
					compatibleGuidances[i]);
			if (!oldGuidanceClassID.equals(compatibleGuidanceTypeClassID))
				guidanceList.add(compatibleGuidanceTypeClassID);
		}
		return guidanceList;
	}

}