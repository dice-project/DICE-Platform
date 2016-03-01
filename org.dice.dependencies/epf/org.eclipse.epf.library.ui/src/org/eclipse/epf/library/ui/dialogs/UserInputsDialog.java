//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.ui.dialogs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.epf.library.edit.command.UserInput;
import org.eclipse.epf.library.edit.validation.IValidator;
import org.eclipse.epf.library.ui.providers.DelegateLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * This dialog acts as a input dialog during command execution based on user
 * input and inputType dialog behaviour changes.
 * 
 * @author Shashidhar Kannoori
 * @author Shilpa Toraskar
 * @since 1.2
 * 
 */
public class UserInputsDialog extends Dialog {

	IStructuredContentProvider contentProvider;

	ILabelProvider labelProvider;

	private String title;

	private String globalErrorTxt = ""; //$NON-NLS-1$
	
	private String message;

	private List userInputs;

	private boolean result = false;

	Label messageArea;

	Label errorArea;

	HashMap<Object,Object> oldInfoForCancel;
	
	private Color redColor;
	
	public UserInputsDialog(Shell parentShell, List userInputs, String title,
			String message) {
		super(parentShell);
		this.title = title;
		this.message = message;
		this.userInputs = userInputs;
		oldInfoForCancel = new HashMap<Object,Object>();
	}

	/**
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 
	 * @param msg
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Create a Tree Viewer.
	 * 
	 */
	protected void createTreeViewer(Composite parent, UserInput userInput) {
		TreeViewer viewer;
		if (!userInput.isMultiple()) {
			viewer = new TreeViewer(parent, SWT.SINGLE | SWT.H_SCROLL
					| SWT.V_SCROLL | SWT.BORDER);
		} else {
			viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL
					| SWT.V_SCROLL | SWT.BORDER);
		}

		GridData spec = new GridData(GridData.FILL_BOTH);
		{
			spec.widthHint = 200;
			spec.heightHint = 200;
			spec.horizontalSpan = 3;
			viewer.getControl().setLayoutData(spec);
		}
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				
			}

		});

		if (viewer != null) {
			viewer.setLabelProvider(labelProvider);
			if (contentProvider != null) {
				viewer.setContentProvider(contentProvider);
			} else {
				viewer.setContentProvider(new ArrayContentProvider());
			}
			viewer.setUseHashlookup(true);

			viewer.setInput(userInput.getChoices());
			viewer.getControl().setFont(parent.getFont());
			// TODO: treeViewer Sorter and Expand/Collapse
		}
	}

	/**
	 * Creates a TableViewer
	 * 
	 */
	protected void createTableViewer(Composite parent, UserInput userInput) {
		TableViewer viewer = new TableViewer(parent);
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				
			}
		});
	}

	/**
	 * Creates a Text control
	 * 
	 * @param parent
	 * @param userInput
	 */
	protected void createText(Composite parent, UserInput userInput) {
		final UserInput localinput = userInput;
		final IValidator validator = userInput.getValidator();
		final Text text = new Text(parent, SWT.SINGLE | SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		if (userInput.getInput() != null) {
			text.setText((String) userInput.getInput());
		}
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				getButton(OK).setEnabled(true);
				errorArea.setText(""); //$NON-NLS-1$
				String errorTxt = ""; //$NON-NLS-1$
				if (validator != null) {
					errorTxt = validator.isValid(text.getText());
					if (errorTxt != null && errorTxt.length() > 0) {
						errorArea.setText(errorTxt);
						getButton(OK).setEnabled(false);					
					} else {
						errorArea.setText(""); //$NON-NLS-1$
					}
					globalErrorTxt = errorTxt;
				}
				if (errorTxt == null || errorTxt.length() <= 0) {
					localinput.setInput(text.getText());
				}
			}
		});

		text.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				String errorTxt = ""; //$NON-NLS-1$
				if (validator != null) {
					errorTxt = validator.isValid(text.getText());
					if (errorTxt != null && errorTxt.length() > 0) {
						errorArea.setText(errorTxt);
						getButton(OK).setEnabled(false);					
					} else {
						errorArea.setText(""); //$NON-NLS-1$
					}
					globalErrorTxt = errorTxt;
				}
				if (errorTxt == null || errorTxt.length() <= 0) {
					localinput.setInput(text.getText());
				}
			}
		});
	}

	/**
	 * Method to setInput, subclass can override it.
	 * 
	 */
	protected void setInput(UserInput input, Object obj) {
		input.setInput(obj);
	}

	/**
	 * Creates a Comboviewer.
	 * 
	 * @param composite
	 * @param userInput
	 */
	protected void createComboViewer(Composite composite, UserInput userInput) {
		final UserInput localInput = userInput;
		final IValidator validator = userInput.getValidator();
		final ComboViewer viewer = new ComboViewer(composite);
		viewer.getCombo().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true));
		viewer.setContentProvider(new ArrayContentProvider());
		if (userInput.getLabelProvider() != null) {
			viewer.setLabelProvider(new DelegateLabelProvider(userInput.getLabelProvider()));
		}
		List choices = userInput.getChoices();
		viewer.setInput(choices);
		if (choices != null && choices.size() > 0) {
			viewer.setSelection(new StructuredSelection(choices.get(0)));
			localInput.setInput(choices.get(0));
		}
		viewer.getCombo().addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				String errorTxt = ""; //$NON-NLS-1$
				IStructuredSelection selected = (IStructuredSelection) viewer
						.getSelection();
				if (validator == null || selected == null) {
					return;
				}
				IStatus status = validator.isValid(selected.getFirstElement());
				if (status.getSeverity() == IStatus.ERROR) {
					errorTxt = status.getMessage();
				}
				if (errorTxt != null && errorTxt.length() > 0) {
					errorArea.setText(errorTxt);
					getButton(OK).setEnabled(false);
				} if (globalErrorTxt != null && globalErrorTxt.length() > 0) {
					errorArea.setText(globalErrorTxt);
					getButton(OK).setEnabled(false);
				}
				else
					errorArea.setText(""); //$NON-NLS-1$
			}
		});
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				getButton(OK).setEnabled(true);
				errorArea.setText(""); //$NON-NLS-1$

				IStructuredSelection selection = (IStructuredSelection) event
						.getSelection();
				Object obj = selection.getFirstElement();
				String errorTxt = ""; //$NON-NLS-1$
				if (validator != null) {
					IStatus status = validator.isValid(obj);
					if (status.getCode() == IStatus.ERROR) {
						errorTxt = status.getMessage();
					}
					if (errorTxt != null && errorTxt.length() > 0) {
						errorArea.setText(errorTxt);
					}
					else if (globalErrorTxt != null && globalErrorTxt.length() > 0) {
						errorArea.setText(globalErrorTxt);
						getButton(OK).setEnabled(false);
					}
					else
						errorArea.setText(""); //$NON-NLS-1$
				}
				if (errorTxt == null || errorTxt.length() <= 0) {
					localInput.setInput(obj);
				}
			}
		});	
	}

	/**
	 * Create a Label and Text for message area.
	 * 
	 * @param composite
	 * @return
	 */
	protected Label createMessageArea(Composite composite) {
		Composite messageAreaComp = new Composite(composite, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginLeft = 0;
		messageAreaComp.setLayout(gridLayout);
		GridData gd = new GridData(GridData.FILL_BOTH);
		messageAreaComp.setLayoutData(gd);
		
		messageArea = new Label(messageAreaComp, SWT.WRAP);
		if (message != null) {
			messageArea.setText(message);
		}
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.minimumWidth = 400;
		messageArea.setLayoutData(gd);
		applyDialogFont(messageArea);
		Label separator = new Label(messageAreaComp, SWT.HORIZONTAL | SWT.SEPARATOR);
		GridData gd1 = new GridData(GridData.FILL_HORIZONTAL);
		gd1.horizontalSpan = 2;
		separator.setLayoutData(gd1);
		return messageArea;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setFont(parent.getFont());

		GridLayout layout = (GridLayout) composite.getLayout();
		layout.marginWidth = 10;
		layout.marginHeight = 10;
	
		// create message area
		createMessageArea(composite);

		// create dialog area
		Composite dialogArea = new Composite(composite, SWT.NONE);
		GridLayout dialogLayout = new GridLayout(2, false);
		dialogLayout.marginLeft = 0;
		dialogArea.setLayout(new GridLayout(2, false));
		GridData gd = new GridData(GridData.FILL_BOTH);
		dialogArea.setLayoutData(gd);
		
		// Create controls based on UserInputs
		if (userInputs != null && userInputs.size() > 0) {
			for (Iterator iterator = userInputs.iterator(); iterator.hasNext();) {
				Object object = iterator.next();
				if (object instanceof UserInput) {
					UserInput userInput = (UserInput) object;

					// Store the userInput in the hashmap for cancel action.
					oldInfoForCancel.put(userInput, userInput.getInput());

					Label label = new Label(dialogArea, SWT.NONE);
					label.setText(userInput.getLabel());
					GridData gridData = new GridData(GridData.BEGINNING);
					label.setLayoutData(gridData);

					if (userInput.getType() == UserInput.TEXT) {
						createText(dialogArea, userInput);
					} else if (userInput.getType() == UserInput.SELECTION) {
						createComboViewer(dialogArea, userInput);
					}
					// createEmptyLabel(dialogArea, 2);
				}
			}
		}
		createErrorArea(composite);
		return composite;
	}

	/**
	 * Creates a Error Area.
	 * 
	 * @param composite
	 */
	private void createErrorArea(Composite composite) {	
		Composite errorAreaComp = new Composite(composite, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginLeft = 0;
		errorAreaComp.setLayout(layout);
		GridData gd = new GridData(GridData.FILL_BOTH);
		errorAreaComp.setLayoutData(gd);
		
		// create blank label
//		new Label(composite, SWT.WRAP);

		errorArea = new Label(errorAreaComp, SWT.WRAP);
		redColor = new Color(null, new RGB(255, 0, 0));
		if(redColor != null){
			errorArea.setForeground(redColor);
		}
		
		gd = new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_VERTICAL);
		gd.heightHint = 80;
		errorArea.setLayoutData(gd);
		applyDialogFont(errorArea);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		if (this.title != null) {
			newShell.setText(this.title);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		result = true;		
		super.okPressed();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#cancelPressed()
	 */
	protected void cancelPressed() {
		if (userInputs != null && userInputs.size() > 0) {
			for (Iterator iterator = userInputs.iterator(); iterator.hasNext();) {
				Object object = iterator.next();
				if (object instanceof UserInput) {
					UserInput userInput = (UserInput) object;
					userInput.setInput(oldInfoForCancel.get(userInput));
				}
			}
		}
		super.cancelPressed();
	}

	/**
	 * Returns the results.
	 * 
	 * @return
	 */
	public boolean getResult() {
		return result;
	}

	@Override
	public boolean close() {
		if(redColor != null){
			redColor.dispose();
		}
		return super.close();
	}
	/**
	 * Create a empty label.
	 * 
	 * @param composite
	 * @param span
	 */
	// private void createEmptyLabel(Composite composite, int span){
	// Label emptyLabel = new Label(composite, SWT.NONE);
	// emptyLabel.setBackground(Colors.INHERITED_ELEMENT_LABEL);
	// GridData emptyData = new GridData(GridData.FILL_HORIZONTAL);
	// emptyLabel.setText("");
	// emptyData = new GridData(GridData.FILL_HORIZONTAL);
	// emptyData.horizontalSpan =span;
	// emptyData.heightHint = 3;
	// emptyLabel.setLayoutData(emptyData);
	// }
	/**
	 * validates the input with given Validator in UserInput.
	 */
	// private boolean validate() {
	// for (Iterator iter = userInputs.iterator(); iter.hasNext();) {
	// UserInput element = (UserInput) iter.next();
	// IValidator validator = element.getValidator();
	// if (validator != null) {
	// if (element.getType() == UserInput.TEXT) {
	// errorTxt += validator
	// .isValid(element.getInput().toString());
	// } else {
	// errorTxt += validator.isValid(element.getInput());
	// }
	// }
	// }
	// errorTxt = "System out testing";
	// if (errorTxt != null && errorTxt.length() > 0) {
	// errorArea.setText(errorTxt);
	// errorArea.setForeground(ColorConstants.red);
	// return false;
	// }
	// return true;
	// }
}
