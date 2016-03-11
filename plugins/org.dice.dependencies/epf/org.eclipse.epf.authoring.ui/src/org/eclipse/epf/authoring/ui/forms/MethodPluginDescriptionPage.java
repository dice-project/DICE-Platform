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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.authoring.ui.editors.EditorChooser;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditor;
import org.eclipse.epf.authoring.ui.views.ViewHelper;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.command.RemoveReferencesCommand;
import org.eclipse.epf.library.edit.util.Comparators;
import org.eclipse.epf.library.edit.util.MethodElementPropUtil;
import org.eclipse.epf.library.edit.util.Misc;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.ui.LibraryUIText;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.library.util.PluginReferenceChecker;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

/**
 * Description page for method plugin
 * 
 * @author Shilpa Toraskar
 * @author Kelvin Low
 * @author Phong Nguyen Le
 * @since 1.0
 * fix for https://bugs.eclipse.org/bugs/show_bug.cgi?id=176382
 */
public class MethodPluginDescriptionPage extends DescriptionFormPage implements IRefreshable {

	protected static final String FORM_PREFIX = LibraryUIText.TEXT_METHOD_PLUGIN
			+ ": "; //$NON-NLS-1$


	protected Text ctrl_r_brief_desc;
	protected CheckboxTableViewer ctrl_refModel;
	protected Section refModelSection;
	protected Composite refModelComposite;

	protected MethodPlugin plugin;

	protected Button ctrl_changeable;
	protected Button supportingPluginCheckbox;

	public boolean notificationEnabled = true;

	protected Adapter userChangeableAdapter;

//	protected ModifyListener nameModifyListener;
	protected Listener deactiveListener;
	
	protected ISelectionChangedListener refModelSelChangedListener = new ISelectionChangedListener() {
		public void selectionChanged(SelectionChangedEvent event) {
			StructuredSelection selectedList = (StructuredSelection) event
					.getSelection();
			MethodPlugin selectedObj = (MethodPlugin) selectedList
					.getFirstElement();
			if (selectedObj == null)
				return;
			ctrl_r_brief_desc.setText(selectedObj
					.getBriefDescription());
		}
	};
	
	protected ICheckStateListener refModelCheckStateListener = new ICheckStateListener() {
		public void checkStateChanged(CheckStateChangedEvent event) {
			Object obj = event.getElement();

			ctrl_r_brief_desc.setText(((MethodPlugin) obj)
					.getBriefDescription());

			if (TngUtil.isLocked(plugin)) {
				ctrl_refModel.setChecked(obj, !event.getChecked());
				return;
			}

			if (event.getChecked()) {
				// TODO: Change this to be not un-doable due to the circular
				// dependency check.
				actionMgr.doAction(IActionManager.ADD, plugin,
						UmaPackage.eINSTANCE.getMethodPlugin_Bases(),
						(MethodPlugin) obj, -1);
			} else {
				final MethodPlugin base = (MethodPlugin) obj;
				
				if(removeAllReferences(base)){
					ctrl_refModel.setChecked(base, false);
				}else{
					Display.getCurrent().asyncExec(new Runnable() {
						public void run() {
							ctrl_refModel.setChecked(base, true);
						}
					});
					return;
				}
				// change this to be not un-doable due to the circular
				// dependency check
				// plugin.getBases().remove(obj);
				actionMgr.doAction(IActionManager.REMOVE, plugin,
						UmaPackage.eINSTANCE.getMethodPlugin_Bases(),
						(MethodPlugin) obj, -1);
			}

			// double check circular dependency, not necessary here
			PluginReferenceChecker.hasCircularConflictWithPlugin(plugin);

			updateChangeDate();
		}

	};

	/**
	 * Creates a new instance.
	 */
	public MethodPluginDescriptionPage(FormEditor editor) {
		super(editor, AuthoringUIText.DESCRIPTION_PAGE_TITLE,
				AuthoringUIText.DESCRIPTION_PAGE_TITLE);

		userChangeableAdapter = new UserChangeableAdapter();
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.BaseFormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		plugin = (MethodPlugin) methodElement;
		if (userChangeableAdapter != null) {
			plugin.eAdapters().add(userChangeableAdapter);
		}
		detailSectionOn = false;
		fullDescOn = false;
		keyConsiderationOn = false;
		variabilitySectionOn = false;
		
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#createEditorContent(FormToolkit)
	 */
	protected void createEditorContent(FormToolkit toolkit) {
		super.createEditorContent(toolkit);
		createReferenceSection(toolkit);
		// Set focus on the Name text control.
		Display display = form.getBody().getDisplay();
		if (!(display == null || display.isDisposed())) {
			display.asyncExec(new Runnable() {
				public void run() {
					if(ctrl_name.isDisposed()) return;
					if (isAutoGenName()) {
						ctrl_presentation_name.setFocus();
						ctrl_presentation_name.setSelection(0, ctrl_presentation_name.getText().length());
					} else {
						ctrl_name.setFocus();
						ctrl_name.setSelection(0, ctrl_name.getText().length());
					}
				}
			});
		}
	}

	protected void createReferenceContent(FormToolkit toolkit) {

		Table ctrl_table = toolkit.createTable(refModelComposite, SWT.CHECK);
		{
			GridData gridData = new GridData(GridData.BEGINNING
					| GridData.FILL_BOTH);
			gridData.heightHint = 100;
			ctrl_table.setLayoutData(gridData);
		}

		ctrl_refModel = new CheckboxTableViewer(ctrl_table);
		ILabelProvider labelProvider = new LabelProvider() {
			public String getText(Object element) {
				MethodPlugin plugin = (MethodPlugin) element;
				return plugin.getName();
			}
		};
		ctrl_refModel.setLabelProvider(labelProvider);

		createRefModelBriefDescField(toolkit);

		toolkit.paintBordersFor(refModelComposite);
	}

	protected void createRefModelBriefDescField(FormToolkit toolkit) {
		Label l_r_brief_desc = toolkit.createLabel(refModelComposite,
				AuthoringUIText.BRIEF_DESCRIPTION_TEXT);
		{
			GridData gridData = new GridData(GridData.BEGINNING);
			gridData.horizontalSpan = 2;
			l_r_brief_desc.setLayoutData(gridData);
		}

		ctrl_r_brief_desc = toolkit.createText(refModelComposite,
				"", SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.READ_ONLY); //$NON-NLS-1$
		{
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL
					| GridData.GRAB_HORIZONTAL);
			gridData.heightHint = 80;
			gridData.horizontalSpan = 2;
			ctrl_r_brief_desc.setLayoutData(gridData);
		}
	}

	protected void createReferenceSection(FormToolkit toolkit) {
		// Ref Model Section
		refModelSection = toolkit.createSection(sectionComposite,
				Section.DESCRIPTION | Section.TWISTIE | Section.EXPANDED
						| Section.TITLE_BAR);
		GridData td1 = new GridData(GridData.FILL_BOTH);
		refModelSection.setLayoutData(td1);
		refModelSection
				.setText(AuthoringUIText.REFERENCED_PLUGINS_SECTION_NAME);
		refModelSection
				.setDescription(AuthoringUIText.REFERENCED_PLUGINS_SECTION_DESC);
		refModelSection.setLayout(new GridLayout());

		refModelComposite = toolkit.createComposite(refModelSection);
		refModelComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		refModelComposite.setLayout(new GridLayout(2, false));
		refModelSection.setClient(refModelComposite);
		createReferenceContent(toolkit);
	}

	protected void setCheckboxForCurrentBase(List<MethodPlugin> currentBaseList) {
		ctrl_refModel.setAllChecked(false);
		for (int i = 0; i < currentBaseList.size(); i++) {
			MethodPlugin model = (MethodPlugin) currentBaseList.get(i);
			ctrl_refModel.setChecked(model, true);
		}
	}

	/**
	 * Add listeners
	 * 
	 */
	protected void addListeners() {
		super.addListeners();

		final MethodElementEditor editor = (MethodElementEditor) getEditor();

		form.addListener(SWT.Activate, new Listener() {
			public void handleEvent(Event e) {
				refreshReferencedModels();

				if (!plugin.getUserChangeable().booleanValue()) {
					refresh(false);
				} else {
					refresh(true);
				}
			}
		});

		ctrl_name.removeFocusListener(nameFocusListener);
//		nameModifyListener = editor.createModifyListener(plugin, true);
//		ctrl_name.addModifyListener(nameModifyListener);
		
		//FixFor: RATLC01378591
		//The changePlguinName() depends SWT.Deactivate listener to be invoked, but when try to close edtior for 
		//elements which have been changed of the plugin, it will invoke SWT.Deactivate even again, that's
		//why the dialog pop up again and again 
		deactiveListener = new Listener() {
			public void handleEvent(Event e) {
				String oldContent = plugin.getName();
				if (((MethodElementEditor) getEditor()).mustRestoreValue(
						e.widget, oldContent)) {
					return;
				}
				if (ctrl_name.getText().equals(plugin.getName())) {
					return;
				}

				// Check invalid characters first.
				
				// 178462
				String msg = null;
				String name = ctrl_name.getText().trim();
				if (name != null) {
					if (oldContent.indexOf("&") < 0 && name.indexOf("&") > -1) { //$NON-NLS-1$ //$NON-NLS-2$
						msg = NLS
								.bind(
										LibraryEditResources.invalidElementNameError4_msg,
										name);
					} else {
						msg = LibraryUtil.checkPluginName(null, name);
					}
				}
				
				String validName = StrUtil.makeValidFileName(ctrl_name
						.getText());
				if (msg == null) {
					// Check duplicate plug-in name.
					msg = LibraryUtil.checkPluginName(plugin, validName);
				}
				if (msg == null) {
					if (!validName.equals(plugin.getName())) {
						ctrl_name.removeListener(SWT.Deactivate, deactiveListener);
						if (!changePlguinName(e, validName)) {
							ctrl_name.addListener(SWT.Deactivate, deactiveListener);
							return;
						}
						ctrl_name.addListener(SWT.Deactivate, deactiveListener);
					}
				} else {
					ctrl_name.setText(plugin.getName());
					Shell shell = getSite().getShell();
					AuthoringUIPlugin.getDefault().getMsgDialog().displayError(
							shell.getText(), msg);
					e.doit = false;
					ctrl_name.getDisplay().asyncExec(new Runnable() {
						public void run() {
							ctrl_name.setFocus();
							ctrl_name.selectAll();
						}
					});
				}
			}

		};
		
		ctrl_name.addListener(SWT.Deactivate, deactiveListener);
		ctrl_name.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				((MethodElementEditor) getEditor()).setCurrentFeatureEditor(e.widget,
						UmaPackage.eINSTANCE.getNamedElement_Name());
			}
		});



		addReferencedModelListener();
	}

	protected void addReferencedModelListener() {
		ctrl_refModel.addSelectionChangedListener(refModelSelChangedListener);

		ctrl_refModel.addCheckStateListener(refModelCheckStateListener);
	}

	@Override
	protected void refresh(boolean editable) {
		super.refresh(editable);
		ctrl_r_brief_desc.setEditable(false);
		if(supportingPluginCheckbox != null) {
			supportingPluginCheckbox.setEnabled(editable);
		}
	}
	
	@Override
	protected void createGeneralSectionContent() {
		super.createGeneralSectionContent();
		
		supportingPluginCheckbox = toolkit
			.createButton(
				generalComposite,
				AuthoringUIResources.methodPluginDescriptionPage_supportingPluginLabel, SWT.CHECK);
	}
	
	@Override
	protected void addGeneralSectionListeners() {
		super.addGeneralSectionListeners();
		
		supportingPluginCheckbox.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				IStatus status = TngUtil.checkEdit(plugin, getSite().getShell());
				if (status.isOK()) {
					boolean ret = actionMgr.doAction(IActionManager.SET,
							plugin, UmaPackage.eINSTANCE
									.getMethodPlugin_Supporting(),
							new Boolean(supportingPluginCheckbox.getSelection()),
							-1);
					// in case of readonly file, roll back changes.
					if (!ret) {
						supportingPluginCheckbox.setSelection(!supportingPluginCheckbox
								.getSelection());
						return;
					} else {
						MethodElementPropUtil
								.getMethodElementPropUtil(actionMgr)
								.updatePackageSupportingBits(plugin.getMethodPackages(),
										supportingPluginCheckbox.getSelection());
					}
				} else {
					AuthoringUIPlugin.getDefault().getMsgDialog().displayError(
							AuthoringUIResources.editDialog_title,
							AuthoringUIResources.editDialog_msgCannotEdit,
							status);
					// restore old value
					//
					supportingPluginCheckbox.setSelection(!supportingPluginCheckbox.getSelection());
					return;
				}
			}

			
		});
	}
	
	@Override
	protected void loadGeneralSectionData() {
		super.loadGeneralSectionData();
		supportingPluginCheckbox
			.setSelection(plugin.isSupporting());
	}
	
	/**
	 * Create the Version section content.
	 */
	protected void createVersionSectionContent() {
		super.createVersionSectionContent();

		ctrl_changeable = toolkit
				.createButton(
						versionComposite,
						AuthoringUIResources.methodPluginDescriptionPage_lockPluginLabel, SWT.CHECK); 

	}


	protected void addVersionSectionListeners() {
		super.addVersionSectionListeners();

		ctrl_changeable.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				notificationEnabled = true;

				
				IStatus status = TngUtil.checkEdit(plugin, getSite().getShell());
				if (status.isOK()) {
					
					String message = AuthoringUIResources
							.bind(
									AuthoringUIResources.methodPluginDescriptionPage_lockPlugin_message,
									plugin.getName());
					Shell shell = getSite().getShell();
					if (AuthoringUIPlugin.getDefault().getMsgDialog()
							.displayConfirmation(shell.getText(), message)) {
						// close editors on any change in ctrl_changeable
						// if (ctrl_changeable.getSelection()) {
						EditorChooser.getInstance().closeMethodEditorsForPluginElements(plugin);
						boolean ret = actionMgr.doAction(IActionManager.SET,
								plugin, UmaPackage.eINSTANCE
										.getMethodPlugin_UserChangeable(),
								new Boolean(!ctrl_changeable.getSelection()),
								-1);
						// in case of readonly file, roll back changes.
						if (!ret) {
							ctrl_changeable.setSelection(!ctrl_changeable
									.getSelection());
							return;
						}

						// }
						refresh(!ctrl_changeable.getSelection());
					} else {
						// actionMgr.undo();
						ctrl_changeable.setSelection(!ctrl_changeable
								.getSelection());
						refresh(!ctrl_changeable.getSelection());
						// return;
					}
				} else {
					AuthoringUIPlugin.getDefault().getMsgDialog().displayError(
							AuthoringUIResources.editDialog_title,
							AuthoringUIResources.editDialog_msgCannotEdit,
							status);
					// restore old value
					//
					ctrl_changeable.setSelection(!ctrl_changeable.getSelection());
					return;
				}
				copyright_viewer.refresh();
				
				// refresh editor title image. 
				((MethodElementEditor)getEditor()).refreshTitleImage();
			}

			
		});
	}


	protected void loadVersionSectionData() {
		super.loadVersionSectionData();
		ctrl_changeable
				.setSelection(!plugin.getUserChangeable().booleanValue());
	}

	protected class UserChangeableAdapter extends AdapterImpl {
		public void notifyChanged(Notification msg) {
			switch (msg.getFeatureID(MethodPlugin.class)) {
			case UmaPackage.METHOD_PLUGIN__USER_CHANGEABLE:
				Boolean b = (Boolean) msg.getNewValue();
				setUserChangeable(b.booleanValue());
				return;
			}
		}
	}

	public void setUserChangeable(boolean userChangeable) {
		if (!notificationEnabled)
			return;
		notificationEnabled = false;
		plugin.setUserChangeable(Boolean.valueOf(userChangeable));
	}

	/**
	 * @see org.eclipse.ui.forms.editor.FormPage#dispose()
	 */
	public void dispose() {
		plugin.eAdapters().remove(userChangeableAdapter);
		super.dispose();
	}
	
	/**
	 * Checks if the given MethodPlugin <code>base</code> is one in the given
	 * plugins collection or base of any plugin in the collection.
	 * 
	 * @param base
	 * @param plugins
	 * @return
	 */
	protected static boolean isOneOrBaseOf(MethodPlugin base, Collection<MethodPlugin> plugins, Map<String, Boolean> map) {
		for (MethodPlugin plugin : plugins) {
			if(base == plugin || Misc.isBaseOf(base, plugin, map)) {
				return true;
			}
		}
		return false;
	}

	protected boolean removeAllReferences(MethodPlugin unCheckedPlugin) {
		ArrayList<MethodPlugin> removedBases = new ArrayList<MethodPlugin>();
		removedBases.add(unCheckedPlugin);
		
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		
		for (Iterator<MethodPlugin> iter = new AbstractTreeIterator<MethodPlugin>(unCheckedPlugin, false) {
		
			private static final long serialVersionUID = 1L;

			@Override
			protected Iterator<? extends MethodPlugin> getChildren(Object object) {
				if(object instanceof MethodPlugin && object != plugin) {
					return ((MethodPlugin)object).getBases().iterator();
				}
				Collection<? extends MethodPlugin> empty = Collections.emptyList();
				return empty.iterator();
			}
		
		}; iter.hasNext();) {
			MethodPlugin base = iter.next();
			ArrayList<MethodPlugin> plugins = new ArrayList<MethodPlugin>(plugin.getBases());
			plugins.remove(unCheckedPlugin);
			if(!isOneOrBaseOf(base, plugins, map)) {
				removedBases.add(base);
			}
		}
		
		ArrayList<MethodPlugin> affectedPlugins = new ArrayList<MethodPlugin>();
		
		// get all plug-ins in library that extend this plug-in
		//
		List<?> plugins = LibraryService.getInstance().getCurrentMethodLibrary()
				.getMethodPlugins();
		for (Iterator<?> iterator = plugins.iterator(); iterator.hasNext();) {
			MethodPlugin mp = (MethodPlugin) iterator.next();
			if(mp != plugin && Misc.isBaseOf(plugin, mp, map)) {				
				affectedPlugins.add(mp);
			}
		}
		
		ArrayList<RemoveReferencesCommand> commands = new ArrayList<RemoveReferencesCommand>();
		for (MethodPlugin base : removedBases) {			
			if(UmaUtil.hasReference(plugin, base)) {
				commands.add(new RemoveReferencesCommand(plugin, base));
			}
			for (MethodPlugin mp : affectedPlugins) {
				ArrayList<MethodPlugin> bases = new ArrayList<MethodPlugin>(mp.getBases());
				bases.remove(base);
				if(!isOneOrBaseOf(base, bases, map) && UmaUtil.hasReference(mp, base)) {
					commands.add(new RemoveReferencesCommand(mp, base));
				}
			}
		}

		if (!commands.isEmpty()) {
			String message = AuthoringUIResources
					.bind(AuthoringUIResources.methodPluginDescriptionRemoveRefConfirm_message,
							plugin.getName());
			Shell shell = getSite().getShell();
			if (AuthoringUIPlugin.getDefault().getMsgDialog()
					.displayConfirmation(shell.getText(), message)) {
				int i = 0;
				try {
					for (RemoveReferencesCommand cmd : commands) {
						actionMgr.execute(cmd);
						i++;
					}
					return true;
				}
				catch(Exception e) {
					AuthoringUIPlugin.getDefault().getLogger().logError(e);
					// undo the executed commands
					for (; i > 0; i--) {
						actionMgr.undo();
					}
					return false;
				}
			} else {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#loadSectionDescription()
	 */
	public void loadSectionDescription() {
		this.generalSectionDescription = AuthoringUIResources.plugin_generalInfoSection_desc;
		this.versionSectionDescription = AuthoringUIResources.plugin_versionInfoSection_desc;
	}

	@Override
	protected Object getContentElement() {
		return plugin;
	}

	protected void refreshReferencedModels() {
		// Clear the old items and add the newly allowable items.
		ctrl_refModel.getTable().clearAll();
		ctrl_refModel.refresh();

		List allowableList = PluginReferenceChecker
				.getApplicableBasePlugins(plugin);
		Collections.<Object>sort(allowableList, Comparators.PLUGINPACKAGE_COMPARATOR);
		ctrl_refModel.add(allowableList.toArray());

		List<MethodPlugin> currentBaseList = plugin.getBases();
		setCheckboxForCurrentBase(currentBaseList);
	}
	
	private boolean changePlguinName(Event e, String validName) {
		String msg;
		Shell shell = getSite().getShell();
		msg = AuthoringUIResources.bind(AuthoringUIResources.methodPluginDescriptionPage_confirmRename, (new Object[] { plugin.getName(), ctrl_name.getText() })); 
		String title = AuthoringUIResources.methodPluginDescriptionPage_confirmRename_title; 
		if (!MessageDialog.openConfirm(shell, title, msg)) {
			ctrl_name.setText(plugin.getName());
			return false;
		}
		
		if (e != null) {
			e.doit = true;
		}
		
		EditorChooser.getInstance().closeMethodEditorsForPluginElements(plugin);		
		ctrl_name.setText(validName);
		boolean status = actionMgr.doAction(IActionManager.SET,
				plugin, UmaPackage.eINSTANCE
						.getNamedElement_Name(), validName, -1);

		if (!status) {
			return false;
		}
		form.setText(FORM_PREFIX + plugin.getName());
		updateChangeDate();

		// adjust plugin location and save the editor
		//
		BusyIndicator.showWhile(getSite().getShell()
				.getDisplay(), new Runnable() {
			public void run() {
				MethodElementEditor editor = (MethodElementEditor) getEditor();
				editor.doSave(new NullProgressMonitor());
				ILibraryPersister.FailSafeMethodLibraryPersister persister = editor
						.getPersister();
				try {
					persister
							.adjustLocation(plugin.eResource());
					persister.commit();
				} catch (RuntimeException e) {
					AuthoringUIPlugin.getDefault().getLogger()
							.logError(e);
					try {
						persister.rollback();
					} catch (Exception ex) {
						AuthoringUIPlugin.getDefault()
								.getLogger().logError(ex);
						ViewHelper
								.reloadCurrentLibaryOnRollbackError(getSite()
										.getShell());
						return;
					}
					AuthoringUIPlugin
							.getDefault()
							.getMsgDialog()
							.displayWarning(
									getSite().getShell()
											.getText(),
									AuthoringUIResources.methodPluginDescriptionPage_cannotRenamePluginFolder
									, e.getMessage(), e);
				}
			}
		});
		
		return true;
	}

	protected boolean changeElementName(String name) {
		return changePlguinName(null, name);
	}

}