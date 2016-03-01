package org.eclipse.epf.authoring.ui.wizards;

import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.util.AuthoringAccessibleListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class SelectProcessPage extends
		org.eclipse.epf.library.ui.wizards.SelectProcessPage {

	private Button expandButton;
	private Button collapseButton;
	
	protected void createControl_(Composite composite) {
		
		Composite buttonsComposite = new Composite(composite, SWT.NONE);		
		
		GridData gd2 = new GridData(GridData.FILL_HORIZONTAL
			| GridData.HORIZONTAL_ALIGN_END);
		gd2.horizontalSpan = 3;
		buttonsComposite.setLayoutData(gd2);
		GridLayout buttonsLayout = new GridLayout();
		buttonsLayout.numColumns = 2;
		buttonsLayout.marginRight = 0;
		buttonsComposite.setLayout(buttonsLayout);
		
		expandButton = new Button(buttonsComposite, SWT.BUTTON1);

		expandButton.setImage(AuthoringUIPlugin.getDefault().getSharedImage(
				"expandall.gif")); //$NON-NLS-1$
		expandButton.setToolTipText(AuthoringUIResources.FilterDialog_ExpandAll); 
		
		expandButton.getAccessible().addAccessibleListener(new AuthoringAccessibleListener(
						AuthoringUIResources.FilterDialog_ExpandAll));		

		collapseButton = new Button(buttonsComposite, SWT.PUSH);
		collapseButton.setImage(AuthoringUIPlugin.getDefault().getSharedImage(
				"collapseall.gif")); //$NON-NLS-1$

		collapseButton.setToolTipText(AuthoringUIResources.FilterDialog_CollapseAll); 
		collapseButton.getAccessible().addAccessibleListener(new AuthoringAccessibleListener(
				AuthoringUIResources.FilterDialog_CollapseAll));
		
		super.createControl_(composite);
	}
	
	protected void addListeners() {
		expandButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {				
				expandOrCollapse(true);
			}
		});
		collapseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				expandOrCollapse(false);
			}
		});
		
		super.addListeners();
	}
	
	private void expandOrCollapse(boolean expand){
		processTreeViewer.getTree().setVisible(false);
		if(expand){
			processTreeViewer.expandAll();
		}else{
			processTreeViewer.collapseAll();
		}
		processTreeViewer.getTree().setVisible(true);
	}
	
}
