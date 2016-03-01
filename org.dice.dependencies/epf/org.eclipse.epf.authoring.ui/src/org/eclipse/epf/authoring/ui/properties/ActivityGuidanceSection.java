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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.authoring.ui.dialogs.ItemsFilterDialog;
import org.eclipse.epf.authoring.ui.filters.ProcessGuidanceFilter;
import org.eclipse.epf.diagram.model.util.DiagramInfo;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.ChangeUdtCommand;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.configuration.GuidanceGroupingItemProvider;
import org.eclipse.epf.library.edit.configuration.GuidanceItemProvider;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.itemsfilter.FilterInitializer;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.process.command.AddGuidanceToBreakdownElementCommand;
import org.eclipse.epf.library.edit.util.MethodElementPropUtil;
import org.eclipse.epf.library.edit.util.PracticePropUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Checklist;
import org.eclipse.epf.uma.Concept;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.EstimationConsiderations;
import org.eclipse.epf.uma.Example;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.Guideline;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.Report;
import org.eclipse.epf.uma.ReusableAsset;
import org.eclipse.epf.uma.Roadmap;
import org.eclipse.epf.uma.SupportingMaterial;
import org.eclipse.epf.uma.Template;
import org.eclipse.epf.uma.ToolMentor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * The activity guidance section. It list all avaiable guidances of an activity
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 * 
 */
public class ActivityGuidanceSection extends AbstractSection {
	protected ILabelProvider labelProvider = new AdapterFactoryLabelProvider(
			TngAdapterFactory.INSTANCE
					.getNavigatorView_ComposedAdapterFactory());

	private FormToolkit toolkit;

	private Button ctrl_add_1, ctrl_remove_1, ctrl_add_2, ctrl_remove_2;

	private Button ctrl_add_3, ctrl_remove_3, ctrl_add_4, ctrl_remove_4;

	private Table ctrl_table_1, ctrl_table_2, ctrl_table_3, ctrl_table_4;

	private Section communicationMaterialSection, educationMaterialSection;

	private TableViewer viewer_1, viewer_2, viewer_3, viewer_4;

	// element
	private Activity element;

	// action manager
	private IActionManager actionMgr;

	public final String tabName = FilterConstants.GUIDANCE;

	private IFilter roadMapFilter = null;

	private IFilter generalGuidanceFilter = null;

	private IFilter supportingMaterialFilter = null;

	/**
	 * Get road map guidance filter
	 */
	public IFilter getRoadMapFilter() {
		if (roadMapFilter == null) {
			roadMapFilter = new ProcessGuidanceFilter(getConfiguration(), null,
					tabName) {
				public boolean childAccept(Object obj) {
					if (obj instanceof GuidanceGroupingItemProvider)
						return true;
					if (obj instanceof GuidanceItemProvider) {
						String name = ((GuidanceItemProvider) obj).getText(obj);
						if (name.equalsIgnoreCase(LibraryEditPlugin.INSTANCE
								.getString("_UI_Guidances_Roadmap")) && //$NON-NLS-1$
								!((GuidanceItemProvider) obj).getChildren(obj)
										.isEmpty())
							return true;
						else
							return false;
					}
					if ((obj instanceof Roadmap))
						return true;
					return false;
				}
			};
		} else {
			((ProcessGuidanceFilter) roadMapFilter).setMethodConfiguration(getConfiguration());
		}

		return roadMapFilter;
	}

	/**
	 * Get General guidance filter
	 * 
	 */
	public IFilter getGeneralGuidanceFilter() {
		if (generalGuidanceFilter == null) {
			generalGuidanceFilter = new ProcessGuidanceFilter(
					getConfiguration(), null, tabName) {
				public boolean childAccept(Object obj) {
					if (super.childAccept(obj))
						return true;
					if (obj instanceof GuidanceItemProvider) {
						if (((GuidanceItemProvider) obj).getChildren(obj)
								.isEmpty())
							return false;
						else
							return true;
					}
					Class cls = FilterInitializer.getInstance()
							.getClassForType(helper.getFilterTypeStr());
					if (cls != null) {
						if (cls.isInstance(obj)) {
							return true;
						} else {
							return false;
						}
					}
					if ((obj instanceof Checklist) || (obj instanceof Concept)
							|| (obj instanceof Example)
							|| (obj instanceof Guideline)
							|| (obj instanceof ReusableAsset)
							|| (obj instanceof SupportingMaterial)
					//		|| (obj instanceof Template)
							|| (obj instanceof Report)
					//		|| (obj instanceof ToolMentor)
					//		|| (obj instanceof EstimationConsiderations)
							)
						return true;
					
					if (obj instanceof Practice) {
						if (PracticePropUtil.getPracticePropUtil().isUdtType((Practice) obj)) {
							return true;
						}
					}

					return false;

				}
			};
		}else {
			((ProcessGuidanceFilter) generalGuidanceFilter).setMethodConfiguration(getConfiguration());
		}
		return generalGuidanceFilter;
	}

	/**
	 * Get Supporting materical guidance filter
	 * 
	 */
	public IFilter getSupportingMaterialFilter() {
		if (supportingMaterialFilter == null) {
			supportingMaterialFilter = new ProcessGuidanceFilter(
					getConfiguration(), null, tabName) {
				public boolean childAccept(Object obj) {
					if (super.childAccept(obj))
						return true;
					if ((obj instanceof GuidanceItemProvider)
							|| (obj instanceof SupportingMaterial))
						return true;
					return false;
				}
			};
		} else {
			((ProcessGuidanceFilter) supportingMaterialFilter).setMethodConfiguration(getConfiguration());
		}
		return supportingMaterialFilter;
	}


	/**
	 * @see org.eclipse.epf.authoring.ui.properties.AbstractSection#createControls(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {

		super.createControls(parent, tabbedPropertySheetPage);
		init();

		parent.setLayout(new GridLayout());
		parent.setLayoutData(new GridData(GridData.FILL_BOTH));

		// create guidance section
		createGuidanceSection(parent);

		// add listeners
		addListeners();

		// update controls
		updateControls();

	}

	/**
	 * Initialize
	 */
	private void init() {
		// get activity object
		element = (Activity) getElement();

		// get toolkit
		toolkit = getWidgetFactory();

		// get action manager
		actionMgr = EPFPropertySheetPage.getActionManager();
	}

	private void toggleSection() {
		if (!(element instanceof DeliveryProcess)) {
			if (communicationMaterialSection.isExpanded())
				communicationMaterialSection.setExpanded(false);
			if (educationMaterialSection.isExpanded())
				educationMaterialSection.setExpanded(false);

			if (communicationMaterialSection.isVisible())
				communicationMaterialSection.setVisible(false);
			if (educationMaterialSection.isVisible())
				educationMaterialSection.setVisible(false);
		} else {
			if (!communicationMaterialSection.isExpanded())
				communicationMaterialSection.setExpanded(true);
			if (!educationMaterialSection.isExpanded())
				educationMaterialSection.setExpanded(true);

			if (!communicationMaterialSection.isVisible())
				communicationMaterialSection.setVisible(true);
			if (!educationMaterialSection.isVisible())
				educationMaterialSection.setVisible(true);
		}

	}

	/**
	 *  Update controls based on editable flag. Controls can become editable or un-editable
	 */
	public void updateControls() {
		ctrl_add_1.setEnabled(editable);
		ctrl_add_2.setEnabled(editable);
		ctrl_add_3.setEnabled(editable);
		ctrl_add_4.setEnabled(editable);

		IStructuredSelection selection = (IStructuredSelection) viewer_1
				.getSelection();
		if (selection.size() > 0 && editable) {
			ctrl_remove_1.setEnabled(true);
		} else {
			ctrl_remove_1.setEnabled(false);
		}

		selection = (IStructuredSelection) viewer_2.getSelection();
		if (selection.size() > 0 && editable) {
			ctrl_remove_2.setEnabled(true);
		} else {
			ctrl_remove_2.setEnabled(false);
		}

		selection = (IStructuredSelection) viewer_3.getSelection();
		if (selection.size() > 0 && editable) {
			ctrl_remove_3.setEnabled(true);
		} else {
			ctrl_remove_3.setEnabled(false);
		}

		selection = (IStructuredSelection) viewer_4.getSelection();
		if (selection.size() > 0 && editable) {
			ctrl_remove_4.setEnabled(true);
		} else {
			ctrl_remove_4.setEnabled(false);
		}
	}


	/**
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
	 */
	public void refresh() {
		try {
			if (getElement() instanceof Activity) {
				element = (Activity) getElement();

				viewer_1.refresh();
				viewer_2.refresh();
				viewer_3.refresh();
				viewer_4.refresh();

				// hide/show certain sections.
				toggleSection();

				// hide/show controls
				updateControls();
			}

		} catch (Exception ex) {
			logger.logError("Error refreshing activity guidance section: ", ex); //$NON-NLS-1$
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.AbstractSection#dispose()
	 */
	public void dispose() {
		super.dispose();

		if (labelProvider != null) {
			labelProvider.dispose();
		}
	}

	/**
	 * Create guidance section on the given composite
	 * @param composite
	 */
	private void createGuidanceSection(Composite composite) {
		int tableHeight = 50;
		String sectionTitle = null;
		String sectionDesc = null;
		String tableTitle = null;
		// Label tableLabel = null;

		// ROADMAP
		{
			sectionTitle = PropertiesResources.Activity_RoadmapTitle; 
			sectionDesc = PropertiesResources.Activity_RoadmapDescription; 
			tableTitle = PropertiesResources.Activity_Selected_Roadmap; 

			Section section = FormUI.createSection(toolkit, composite,
					sectionTitle, sectionDesc);

			// create composite
			Composite sectionComposite = FormUI.createComposite(toolkit,
					section, 2, false);

			Composite pane1 = FormUI.createComposite(toolkit, sectionComposite,
					GridData.FILL_BOTH);
			FormUI.createLabel(toolkit, pane1, tableTitle);

			ctrl_table_1 = FormUI.createTable(toolkit, pane1, tableHeight);
			viewer_1 = new TableViewer(ctrl_table_1);
			IStructuredContentProvider contentProvider = new AdapterFactoryContentProvider(
					getAdapterFactory()) {
				public Object[] getElements(Object object) {
					return getFilteredList(element.getRoadmaps()).toArray();
				}
			};

			viewer_1.setContentProvider(contentProvider);
			viewer_1.setLabelProvider(labelProvider);
			viewer_1.setInput(element);

			// create buttons for table1
			Composite pane2 = FormUI.createComposite(toolkit, sectionComposite,
					GridData.VERTICAL_ALIGN_CENTER
							| GridData.HORIZONTAL_ALIGN_CENTER);

			ctrl_add_1 = FormUI.createButton(toolkit, pane2,
					PropertiesResources.Process_Add);
			ctrl_remove_1 = FormUI.createButton(toolkit, pane2,
					PropertiesResources.Process_Remove);

			toolkit.paintBordersFor(pane1);
		}

		// GENERAL GUIDANCE
		{
			sectionTitle = PropertiesResources.Activity_GeneralGuidanceTitle; 
			sectionDesc = PropertiesResources.Activity_GeneralGuidanceDescription; 
			tableTitle = PropertiesResources.Activity_Selected_GeneralGuidance; 

			Section section = FormUI.createSection(toolkit, composite,
					sectionTitle, sectionDesc);

			// create composite
			Composite sectionComposite = FormUI.createComposite(toolkit,
					section, 2, false);

			Composite pane1 = FormUI.createComposite(toolkit, sectionComposite,
					GridData.FILL_BOTH);
			FormUI.createLabel(toolkit, pane1, tableTitle);

			ctrl_table_2 = FormUI.createTable(toolkit, pane1, tableHeight);
			viewer_2 = new TableViewer(ctrl_table_2);
			IStructuredContentProvider contentProvider = new AdapterFactoryContentProvider(
					getAdapterFactory()) {
				public Object[] getElements(Object object) {
					return getFilteredList(getSelectedGuidances()).toArray();
				}
			};

			viewer_2.setContentProvider(contentProvider);
			viewer_2.setLabelProvider(labelProvider);
			viewer_2.setInput(element);

			// create buttons for table2
			Composite pane2 = FormUI.createComposite(toolkit, sectionComposite,
					GridData.VERTICAL_ALIGN_CENTER
							| GridData.HORIZONTAL_ALIGN_CENTER);

			ctrl_add_2 = FormUI.createButton(toolkit, pane2,
					PropertiesResources.Process_Add);
			ctrl_remove_2 = FormUI.createButton(toolkit, pane2,
					PropertiesResources.Process_Remove);

			toolkit.paintBordersFor(pane1);
		}

		// COMMUNICATION MATERIAL
		{
			sectionTitle = PropertiesResources.Activity_CommunicationMaterialTitle; 
			sectionDesc = PropertiesResources.Activity_CommunicationMaterialDescription; 
			tableTitle = PropertiesResources.Activity_Selected_CommunicationMaterial; 

			Section section = FormUI.createSection(toolkit, composite,
					sectionTitle, sectionDesc);

			// create composite
			Composite sectionComposite = FormUI.createComposite(toolkit,
					section, 2, false);

			Composite pane1 = FormUI.createComposite(toolkit, sectionComposite,
					GridData.FILL_BOTH);
			FormUI.createLabel(toolkit, pane1, tableTitle);

			ctrl_table_3 = FormUI.createTable(toolkit, pane1, tableHeight);
			viewer_3 = new TableViewer(ctrl_table_3);
			IStructuredContentProvider contentProvider = new AdapterFactoryContentProvider(
					getAdapterFactory()) {
				public Object[] getElements(Object object) {
					if (element instanceof DeliveryProcess) {
						return getFilteredList(
								((DeliveryProcess) element)
										.getCommunicationsMaterials())
								.toArray();
					} else
						return Collections.EMPTY_LIST.toArray();
				}
			};

			viewer_3.setContentProvider(contentProvider);
			viewer_3.setLabelProvider(labelProvider);
			viewer_3.setInput(element);

			// create buttons for table2
			Composite pane2 = FormUI.createComposite(toolkit, sectionComposite,
					GridData.VERTICAL_ALIGN_CENTER
							| GridData.HORIZONTAL_ALIGN_CENTER);

			ctrl_add_3 = FormUI.createButton(toolkit, pane2,
					PropertiesResources.Process_Add);
			ctrl_remove_3 = FormUI.createButton(toolkit, pane2,
					PropertiesResources.Process_Remove);

			toolkit.paintBordersFor(pane1);

			communicationMaterialSection = section;

		}

		// EDUCATION MATERIAL
		{
			sectionTitle = PropertiesResources.Activity_EducationMaterialTitle; 
			sectionDesc = PropertiesResources.Activity_EducationMaterialDescription; 
			tableTitle = PropertiesResources.Activity_Selected_EducationMaterial; 

			Section section = FormUI.createSection(toolkit, composite,
					sectionTitle, sectionDesc);

			// create composite
			Composite sectionComposite = FormUI.createComposite(toolkit,
					section, 2, false);

			Composite pane1 = FormUI.createComposite(toolkit, sectionComposite,
					GridData.FILL_BOTH);
			FormUI.createLabel(toolkit, pane1, tableTitle);

			ctrl_table_4 = FormUI.createTable(toolkit, pane1, tableHeight);
			viewer_4 = new TableViewer(ctrl_table_4);
			IStructuredContentProvider contentProvider = new AdapterFactoryContentProvider(
					getAdapterFactory()) {
				public Object[] getElements(Object object) {
					if (element instanceof DeliveryProcess) {
						return getFilteredList(
								((DeliveryProcess) element)
										.getEducationMaterials()).toArray();
					} else
						return Collections.EMPTY_LIST.toArray();
				}
			};

			viewer_4.setContentProvider(contentProvider);
			viewer_4.setLabelProvider(labelProvider);
			viewer_4.setInput(element);

			// create buttons for table2
			Composite pane2 = FormUI.createComposite(toolkit, sectionComposite,
					GridData.VERTICAL_ALIGN_CENTER
							| GridData.HORIZONTAL_ALIGN_CENTER);

			ctrl_add_4 = FormUI.createButton(toolkit, pane2,
					PropertiesResources.Process_Add);
			ctrl_remove_4 = FormUI.createButton(toolkit, pane2,
					PropertiesResources.Process_Remove);

			toolkit.paintBordersFor(pane1);

			educationMaterialSection = section;

		}

		// hide/show certain sections
		toggleSection();
	}

	/**
	 * Add listeners
	 * 
	 */
	private void addListeners() {
		ItemProviderAdapter adapter = (ItemProviderAdapter) getAdapter();
		Object parent = null;
		if (adapter instanceof IBSItemProvider) {
			IBSItemProvider bsItemProvider = (IBSItemProvider) adapter;
			parent = bsItemProvider.getTopItem();
		} else {
			logger
					.logError("ActivityGuidanceSection::addListeners - IBSItemProvider is null"); //$NON-NLS-1$
			return;
		}

		{
			ctrl_table_1.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent e) {
					IStructuredSelection selection = (IStructuredSelection) viewer_1
							.getSelection();
					if ((selection.size() > 0) && editable)
						ctrl_remove_1.setEnabled(true);
				}
			});

			viewer_1
					.addSelectionChangedListener(new ISelectionChangedListener() {
						public void selectionChanged(SelectionChangedEvent event) {
							IStructuredSelection selection = (IStructuredSelection) viewer_1
									.getSelection();
							if ((selection.size() > 0) && editable)
								ctrl_remove_1.setEnabled(true);
						}
					});

			ctrl_add_1.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					IFilter filter = getRoadMapFilter();
					ItemsFilterDialog fd = new ItemsFilterDialog(PlatformUI
							.getWorkbench().getActiveWorkbenchWindow()
							.getShell(), filter, element,
							FilterConstants.ROADMAP, element.getRoadmaps());

					fd.setTitle(FilterConstants.ROADMAP);
					fd.setInput(UmaUtil.getMethodLibrary((EObject) element));
					fd.setBlockOnOpen(true);
					fd.setEnableProcessScope(true);
					fd.setSection(getSection());
					fd.open();
					addGuidances(fd.getSelectedItems());
					viewer_1.refresh();
				}
			});

			ctrl_remove_1.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					IStructuredSelection selection = (IStructuredSelection) viewer_1
							.getSelection();
					if (selection.size() > 0) {
						// update the model
						ArrayList rmItems = new ArrayList();
						rmItems.addAll(selection.toList());
						removeGuidances(rmItems);
						viewer_1.refresh();

						// clear the selection
						viewer_1.setSelection(null, true);
					}
					ctrl_remove_1.setEnabled(false);
				}
			});
		}

		{
			ctrl_table_2.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent e) {
					IStructuredSelection selection = (IStructuredSelection) viewer_2
							.getSelection();
					if ((selection.size() > 0) && editable)
						ctrl_remove_2.setEnabled(true);
				}
			});

			viewer_2
					.addSelectionChangedListener(new ISelectionChangedListener() {
						public void selectionChanged(SelectionChangedEvent event) {
							IStructuredSelection selection = (IStructuredSelection) viewer_2
									.getSelection();
							if ((selection.size() > 0) && editable)
								ctrl_remove_2.setEnabled(true);
						}
					});

			ctrl_add_2.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					IFilter filter = getGeneralGuidanceFilter();
					ItemsFilterDialog fd = new ItemsFilterDialog(PlatformUI
							.getWorkbench().getActiveWorkbenchWindow()
							.getShell(), filter, element,
							FilterConstants.GUIDANCE, getExistingElements()); 

					fd.setTitle(FilterConstants.GUIDANCE);
					fd.setInput(UmaUtil.getMethodLibrary((EObject) element));
					fd.setBlockOnOpen(true);
					fd.setTypes(getFilterTypes());
					fd.setEnableProcessScope(true);
					fd.setSection(getSection());
					fd.open();
					addGuidances(fd.getSelectedItems());
					viewer_2.refresh();
				}
			});

			ctrl_remove_2.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					IStructuredSelection selection = (IStructuredSelection) viewer_2
							.getSelection();
					if (selection.size() > 0) {
						// update the model
						ArrayList rmItems = new ArrayList();
						rmItems.addAll(selection.toList());
						removeGuidances(rmItems);
						viewer_2.refresh();

						// clear the selection
						viewer_2.setSelection(null, true);
					}
					ctrl_remove_2.setEnabled(false);
				}
			});
		}

		{
			ctrl_table_3.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent e) {
					IStructuredSelection selection = (IStructuredSelection) viewer_3
							.getSelection();
					if ((selection.size() > 0) && editable)
						ctrl_remove_3.setEnabled(true);
				}
			});

			viewer_3
					.addSelectionChangedListener(new ISelectionChangedListener() {
						public void selectionChanged(SelectionChangedEvent event) {
							IStructuredSelection selection = (IStructuredSelection) viewer_3
									.getSelection();
							if ((selection.size() > 0) && editable)
								ctrl_remove_3.setEnabled(true);
						}
					});

			ctrl_add_3.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					if (element instanceof DeliveryProcess) {
						DeliveryProcess proc = (DeliveryProcess) element;

						IFilter filter = getSupportingMaterialFilter();
						ItemsFilterDialog fd = new ItemsFilterDialog(PlatformUI
								.getWorkbench().getActiveWorkbenchWindow()
								.getShell(), filter, proc,
								FilterConstants.SUPPORTING_MATERIALS, proc
										.getCommunicationsMaterials());

						fd.setTitle(FilterConstants.GUIDANCE);
						fd
								.setInput(UmaUtil
										.getMethodLibrary((EObject) element));
						fd.setBlockOnOpen(true);
						fd.setEnableProcessScope(true);
						fd.setSection(getSection());
						fd.open();

						actionMgr
								.doAction(
										IActionManager.ADD_MANY,
										proc,
										UmaPackage.eINSTANCE
												.getDeliveryProcess_CommunicationsMaterials(),
										fd.getSelectedItems(), -1);
					}

					viewer_3.refresh();
				}
			});

			ctrl_remove_3.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					IStructuredSelection selection = (IStructuredSelection) viewer_3
							.getSelection();
					if (selection.size() > 0) {
						// update the model
						ArrayList rmItems = new ArrayList();
						rmItems.addAll(selection.toList());

						if (element instanceof DeliveryProcess) {
							DeliveryProcess proc = (DeliveryProcess) element;
							actionMgr
									.doAction(
											IActionManager.REMOVE_MANY,
											proc,
											UmaPackage.eINSTANCE
													.getDeliveryProcess_CommunicationsMaterials(),
											rmItems, -1);
						}
						viewer_3.refresh();

						// clear the selection
						viewer_3.setSelection(null, true);
					}
					ctrl_remove_3.setEnabled(false);
				}
			});
		}

		{
			ctrl_table_4.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent e) {
					IStructuredSelection selection = (IStructuredSelection) viewer_4
							.getSelection();
					if ((selection.size() > 0) && editable)
						ctrl_remove_4.setEnabled(true);
				}
			});

			viewer_4
					.addSelectionChangedListener(new ISelectionChangedListener() {
						public void selectionChanged(SelectionChangedEvent event) {
							IStructuredSelection selection = (IStructuredSelection) viewer_4
									.getSelection();
							if ((selection.size() > 0) && editable)
								ctrl_remove_4.setEnabled(true);
						}
					});

			ctrl_add_4.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					if (element instanceof DeliveryProcess) {
						DeliveryProcess proc = (DeliveryProcess) element;

						IFilter filter = getSupportingMaterialFilter();
						ItemsFilterDialog fd = new ItemsFilterDialog(PlatformUI
								.getWorkbench().getActiveWorkbenchWindow()
								.getShell(), filter, proc,
								FilterConstants.SUPPORTING_MATERIALS, proc
										.getEducationMaterials());

						fd.setTitle(FilterConstants.GUIDANCE);
						fd
								.setInput(UmaUtil
										.getMethodLibrary((EObject) element));
						fd.setBlockOnOpen(true);
						fd.setEnableProcessScope(true);
						fd.setSection(getSection());
						fd.open();

						actionMgr
								.doAction(
										IActionManager.ADD_MANY,
										proc,
										UmaPackage.eINSTANCE
												.getDeliveryProcess_EducationMaterials(),
										fd.getSelectedItems(), -1);

						viewer_4.refresh();
					}
				}
			});

			ctrl_remove_4.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					IStructuredSelection selection = (IStructuredSelection) viewer_4
							.getSelection();
					if (selection.size() > 0) {
						// update the model
						ArrayList rmItems = new ArrayList();
						rmItems.addAll(selection.toList());
						if (element instanceof DeliveryProcess) {
							DeliveryProcess proc = (DeliveryProcess) element;
							actionMgr
									.doAction(
											IActionManager.REMOVE_MANY,
											proc,
											UmaPackage.eINSTANCE
													.getDeliveryProcess_EducationMaterials(),
											rmItems, -1);
						}
						viewer_4.refresh();

						// clear the selection
						viewer_4.setSelection(null, true);

					}
					ctrl_remove_4.setEnabled(false);
				}
			});
		}

	}

	/**
	 * Add guidances to the element
	 * @param addItems
	 * 				list of guidances to add
	 */
	private void addGuidances(ArrayList addItems) {
		// update the model
		AddGuidanceToBreakdownElementCommand command = new AddGuidanceToBreakdownElementCommand(
				element, addItems);
		actionMgr.execute(command);
	}

	/**
	 * Remove guidances from the element
	 * @param rmItems
	 * 				list of guidances to remove
	 */
	private void removeGuidances(ArrayList rmItems) {
		// update the model
		List<Practice> utdItems = new ArrayList<Practice>();
		if (!rmItems.isEmpty()) {
			for (Iterator it = rmItems.iterator(); it.hasNext();) {
				Guidance item = (Guidance) it.next();

				// guidances for activity
				if (item instanceof Checklist) {
					actionMgr.doAction(IActionManager.REMOVE, element,
							UmaPackage.eINSTANCE.getBreakdownElement_Checklists(),
							item, -1);
				} else if (item instanceof Concept) {
					actionMgr.doAction(IActionManager.REMOVE, element,
							UmaPackage.eINSTANCE.getBreakdownElement_Concepts(), item,
							-1);
				} else if (item instanceof Example) {
					actionMgr.doAction(IActionManager.REMOVE, element,
							UmaPackage.eINSTANCE.getBreakdownElement_Examples(), item,
							-1);
				} else if (item instanceof SupportingMaterial) {
					actionMgr.doAction(IActionManager.REMOVE, element,
							UmaPackage.eINSTANCE
									.getBreakdownElement_SupportingMaterials(), item,
							-1);
				} else if (item instanceof Guideline) {
					actionMgr.doAction(IActionManager.REMOVE, element,
							UmaPackage.eINSTANCE.getBreakdownElement_Guidelines(),
							item, -1);
				} else if (item instanceof ReusableAsset) {
					actionMgr.doAction(IActionManager.REMOVE, element,
							UmaPackage.eINSTANCE.getBreakdownElement_ReusableAssets(),
							item, -1);
				} else if (item instanceof Roadmap) {
					actionMgr.doAction(IActionManager.REMOVE, element,
							UmaPackage.eINSTANCE.getActivity_Roadmaps(), item,
							-1);
				}else if (item instanceof Template) {
					actionMgr.doAction(IActionManager.REMOVE, element,
							UmaPackage.eINSTANCE.getBreakdownElement_Templates(),
							item, -1);
				}else if (item instanceof Report) {
					actionMgr.doAction(IActionManager.REMOVE, element,
							UmaPackage.eINSTANCE.getBreakdownElement_Reports(),
							item, -1);
				}else if (item instanceof ToolMentor) {
					actionMgr.doAction(IActionManager.REMOVE, element,
							UmaPackage.eINSTANCE.getBreakdownElement_Toolmentor(),
							item, -1);
				}else if (item instanceof EstimationConsiderations) {
					actionMgr.doAction(IActionManager.REMOVE, element,
							UmaPackage.eINSTANCE.getBreakdownElement_Estimationconsiderations(),
							item, -1);
				} else if (item instanceof Practice) {
					if (PracticePropUtil.getPracticePropUtil().isUdtType((Practice) item)) {
						utdItems.add((Practice) item);
					}
				} else {
					logger
							.logError("Can't remove Guidance: " + item.getType().getName() + ":" + item.getName()); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
		}
		
		if (! utdItems.isEmpty()) {
			actionMgr.execute(new ChangeUdtCommand(element, utdItems, true));
		}
	}

	/**
	 * Get existing guidances of the element
	 * @return
	 * 			list of existing guidances.
	 */
	private List getExistingElements() {

		List itemList = new ArrayList();

		itemList.addAll(element.getChecklists());
		itemList.addAll(element.getConcepts());
		itemList.addAll(element.getExamples());
		itemList.addAll(element.getGuidelines());
		itemList.addAll(element.getReusableAssets());
		itemList.addAll(element.getSupportingMaterials());

		DiagramInfo info = new DiagramInfo((Activity) element);
		if (info.getActivityDiagram() != null)
			itemList.add(info.getActivityDiagram());
		if (info.getActivityDetailDiagram() != null)
			itemList.add(info.getActivityDetailDiagram());
		if (info.getWPDDiagram() != null)
			itemList.add(info.getWPDDiagram());
		
		itemList.addAll(element.getTemplates());
		itemList.addAll(element.getToolmentor());
		itemList.addAll(element.getReports());
		itemList.addAll(element.getEstimationconsiderations());

		MethodElementPropUtil propUtil = MethodElementPropUtil.getMethodElementPropUtil();
		if (propUtil.hasUdtList(element)) {
			itemList.addAll(propUtil.getUdtList(element, false));
		}
		
		return itemList;
	}

	/**
	 * Get selected guidances
	 * @return
	 * 			list of selected guidances
	 */
	private List getSelectedGuidances() {
		List itemList = new ArrayList();

		itemList.addAll(element.getChecklists());
		itemList.addAll(element.getConcepts());
		itemList.addAll(element.getExamples());
		itemList.addAll(element.getGuidelines());
		itemList.addAll(element.getReusableAssets());
		List supportingMaterials = element.getSupportingMaterials();
		DiagramInfo info = new DiagramInfo((Activity) element);
		for (Iterator itor = supportingMaterials.iterator(); itor.hasNext();) {
			Object obj = itor.next();
			if (obj instanceof SupportingMaterial)
				if (!info.isDiagram((SupportingMaterial) obj)) {
					itemList.add(obj);
				}
		}
		
		itemList.addAll(element.getTemplates());
		itemList.addAll(element.getToolmentor());
		itemList.addAll(element.getReports());
		itemList.addAll(element.getEstimationconsiderations());

		MethodElementPropUtil propUtil = MethodElementPropUtil.getMethodElementPropUtil();
		if (propUtil.hasUdtList(element)) {
			itemList.addAll(propUtil.getUdtList(element, false));
		}
		
		return itemList;
	}

	/**
	 * Return list of filter types
	 */
	protected String[] getFilterTypes() {
		String[] str = new String[12];
		int i = 0;
		str[i++] = FilterConstants.GUIDANCE;
		str[i++] = FilterConstants.space + FilterConstants.CHECKLISTS;
		str[i++] = FilterConstants.space + FilterConstants.CONCEPTS;
		str[i++] = FilterConstants.space
				+ FilterConstants.ESTIMATE_CONSIDERATIONS;
		str[i++] = FilterConstants.space + FilterConstants.EXAMPLES;
		str[i++] = FilterConstants.space + FilterConstants.GUIDELINES;
		str[i++] = FilterConstants.space + FilterConstants.REPORTS;
		str[i++] = FilterConstants.space + FilterConstants.REUSABLE_ASSETS;
		str[i++] = FilterConstants.space + FilterConstants.SUPPORTING_MATERIALS;
		str[i++] = FilterConstants.space + FilterConstants.TEMPLATES;
		str[i++] = FilterConstants.space + FilterConstants.TOOL_MENTORS;
		str[i++] = FilterConstants.space + FilterConstants.WHITE_PAPERS;
		return str;
	}
}