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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.authoring.ui.dialogs.ItemsFilterDialog;
import org.eclipse.epf.authoring.ui.filters.ProcessGuidanceFilter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.ChangeUdtCommand;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.configuration.GuidanceItemProvider;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.itemsfilter.FilterInitializer;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.process.command.AddGuidanceToBreakdownElementCommand;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.library.edit.util.PracticePropUtil;
import org.eclipse.epf.library.edit.util.ProcessScopeUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Checklist;
import org.eclipse.epf.uma.Concept;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.EstimationConsiderations;
import org.eclipse.epf.uma.Example;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.Guideline;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.Report;
import org.eclipse.epf.uma.ReusableAsset;
import org.eclipse.epf.uma.SupportingMaterial;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.Template;
import org.eclipse.epf.uma.ToolMentor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableFontProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * The breakdown element guidance section. It list all available guidances for an breakdown element
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 * 
 */
public class BreakdownElementGuidanceSection extends AbstractSection {
	private FormToolkit toolkit;

	private Button ctrl_add_1, ctrl_remove_1;

	private Table ctrl_table_1;

	private TableViewer viewer_1;

	// element
	private BreakdownElement element;

	// action manager
	private IActionManager actionMgr;

	public final String tabName = FilterConstants.GUIDANCE;

	private IFilter generalGuidanceFilter = null;

	private DescriptorPropUtil propUtil;

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
							|| (obj instanceof Template)
							|| (obj instanceof Report)
							|| (obj instanceof ToolMentor)
							|| (obj instanceof EstimationConsiderations))
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
		element = (BreakdownElement) getElement();

		// get toolkit
		toolkit = getWidgetFactory();

		// get action manager
		actionMgr = EPFPropertySheetPage.getActionManager();
		
		propUtil = DescriptorPropUtil.getDesciptorPropUtil(actionMgr);
	}


	/**
	 *  Update controls based on editable flag. Controls can become editable or un-editable
	 */
	public void updateControls() {
		ctrl_add_1.setEnabled(editable);
		
		IStructuredSelection selection = (IStructuredSelection) viewer_1
				.getSelection();
		if (selection.size() > 0 && editable) {
			ctrl_remove_1.setEnabled(true);
		} else {
			ctrl_remove_1.setEnabled(false);
		}
	}


	/**
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
	 */
	public void refresh() {
		try {
			if (getElement() instanceof BreakdownElement) {
				element = (BreakdownElement) getElement();

				initContentProvider();
				initLabelProvider();
				
				viewer_1.refresh();

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

		// GENERAL GUIDANCE
		{
			sectionTitle = PropertiesResources.Activity_GeneralGuidanceTitle; 
			sectionDesc = PropertiesResources.Activity_GeneralGuidanceDescription; 
			tableTitle = PropertiesResources.Activity_Selected_GeneralGuidance; 

			if (isSyncFree()) {
				sectionDesc = sectionDesc + " " + PropertiesResources.Process_SyncFree_FontStyle; //$NON-NLS-1$
			}
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
			
			initContentProvider();
			initLabelProvider();

			viewer_1.setInput(element);

			// create buttons for table2
			Composite pane2 = FormUI.createComposite(toolkit, sectionComposite,
					GridData.VERTICAL_ALIGN_CENTER
							| GridData.HORIZONTAL_ALIGN_CENTER);

			ctrl_add_1 = FormUI.createButton(toolkit, pane2,
					PropertiesResources.Process_Add);
			ctrl_remove_1 = FormUI.createButton(toolkit, pane2,
					PropertiesResources.Process_Remove);

			toolkit.paintBordersFor(pane1);
		}
	}
	
	protected void initContentProvider() {
		IStructuredContentProvider contentProvider = new AdapterFactoryContentProvider(
				getAdapterFactory()) {
			public Object[] getElements(Object object) {
				List<MethodElement> elements = new ArrayList<MethodElement>();
				elements.addAll(getSelectedGuidances());

				if (element instanceof Descriptor) {
					Descriptor des = (Descriptor) element;
	
					if (isSyncFree() && !propUtil.isNoAutoSyn(des)) {
						elements.addAll((des).getGuidanceExclude());
					}
	
					MethodElement linkedElement = ProcessUtil
							.getAssociatedElement(des);
	
					if (linkedElement != null) {
						Process process = ProcessUtil.getProcess(des
								.getSuperActivities());
						if (ProcessScopeUtil.getInstance().isConfigFree(process)) {
							Map<EReference, EReference> refMap = LibraryEditUtil.getInstance().getGuidanceRefMap(
											linkedElement.eClass());
							Set<Object> set = new HashSet<Object>();
							if (refMap != null) {
							for (EReference ref : refMap.keySet()) {
//								Object value = linkedElement.eGet(ref);
								Object value = propUtil.eGet(linkedElement, ref, false);
								if (value instanceof List) {
									set.addAll((List) value);
								}
								List<Guidance> locals = des.getGuidanceAdditional(); 
								if (locals != null && !locals.isEmpty()) {
									set.addAll(locals);
								}
							}
							}
							elements.retainAll(set);
						}
					}				
				}

				return getFilteredList(elements).toArray();
			}
		};

		viewer_1.setContentProvider(contentProvider);		
	}
	
	protected void initLabelProvider() {		
		ILabelProvider labelProvider = null;
		
		if (isSyncFree()) {
			labelProvider = new GuidanceSyncFreeLabelProvider(
					TngAdapterFactory.INSTANCE.getNavigatorView_ComposedAdapterFactory(),
					(Descriptor)element, getConfiguration());  
		} else {
			labelProvider = new AdapterFactoryLabelProvider(
					TngAdapterFactory.INSTANCE.getNavigatorView_ComposedAdapterFactory());
		}
		
		viewer_1.setLabelProvider(labelProvider);
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
							if ((selection.size() > 0) && editable) {
								if (isSyncFree()) {
									syncFreeUpdateBtnStatus(selection);
								} else {
									ctrl_remove_1.setEnabled(true);
								}
							}
						}
					});

			ctrl_add_1.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					if (isSyncFree()) {
						IStructuredSelection selection = (IStructuredSelection) viewer_1.getSelection();
						if (syncFreeAdd(selection)) { 
							viewer_1.refresh();
							return;
						}
					}
					
					IFilter filter = getGeneralGuidanceFilter();
					List existingElements = null;
					if (isSyncFree()) {
						existingElements = getSelectedGuidances();
						if (! propUtil.isNoAutoSyn((Descriptor)element)) {
							existingElements.addAll(((Descriptor)element).getGuidanceExclude());
						}
					} else {
						existingElements = getSelectedGuidances();
					}					
					ItemsFilterDialog fd = new ItemsFilterDialog(PlatformUI
							.getWorkbench().getActiveWorkbenchWindow()
							.getShell(), filter, element,
							FilterConstants.GUIDANCE, existingElements);

					fd.setTitle(FilterConstants.GUIDANCE);
					fd.setInput(UmaUtil.getMethodLibrary((EObject) element));
					fd.setBlockOnOpen(true);
					fd.setTypes(getFilterTypes());	
					fd.setEnableProcessScope(true);
					fd.setSection(getSection());
					fd.open();
					addGuidances(fd.getSelectedItems());
					viewer_1.refresh();
				}
			});

			ctrl_remove_1.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					if (isSyncFree()) {
						IStructuredSelection selection = (IStructuredSelection) viewer_1.getSelection();
						if (syncFreeRemove(selection)) {
							viewer_1.refresh();
							ctrl_remove_1.setEnabled(false);
							return;
						}							
					} 
					
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
	}
	
	private void addGuidances(List<Guidance> addItems) {
		addGuidances(addItems, false);
	}
	
	/**
	 * Add guidances to the element
	 * @param addItems
	 * 				list of guidances to add
	 */
	private void addGuidances(List<Guidance> addItems, boolean calledForExculded) {
		// update the model
		AddGuidanceToBreakdownElementCommand command = new AddGuidanceToBreakdownElementCommand(
				element, addItems, calledForExculded);
		actionMgr.execute(command);
	}
	
	private void removeGuidances(List<Guidance> rmItems) {
		removeGuidances(rmItems, true);
	}

	/**
	 * Remove guidances from the element
	 * @param rmItems
	 * 				list of guidances to remove
	 */
	private void removeGuidances(List<Guidance> rmItems, boolean localUse) {
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
				
				if (! utdItems.isEmpty()) {
					actionMgr.execute(new ChangeUdtCommand(element, utdItems, true));
				}
				
				if (isSyncFree()) {
					if (localUse) {
						actionMgr.doAction(IActionManager.REMOVE, element,
								UmaPackage.eINSTANCE.getDescriptor_GuidanceAdditional(),
								item, -1);
						if (element instanceof TaskDescriptor) {
							TaskDescriptor td = (TaskDescriptor) element;
							TaskDescriptor greenParent = (TaskDescriptor) propUtil.getGreenParentDescriptor(td);
							if (greenParent != null) {
								EReference aRef = UmaPackage.eINSTANCE.getDescriptor_GuidanceAdditional();
								List<Guidance> parentAdditionalList = (List<Guidance>) greenParent.eGet(aRef);
								propUtil.removeGreenRefDelta(td, item, aRef, true);
								if (parentAdditionalList != null && parentAdditionalList.contains(item)) {
									propUtil.addGreenRefDelta(td, item, aRef, false);
								}
							}
						}
					} else {
						actionMgr.doAction(IActionManager.ADD, element,
								UmaPackage.eINSTANCE.getDescriptor_GuidanceExclude(),
								item, -1);
						
						//for Green parent
						Descriptor parent = propUtil.getGreenParentDescriptor((Descriptor)element);
						if ((parent != null) && (parent instanceof TaskDescriptor)) {
							TaskDescriptor greenParent = (TaskDescriptor)parent;
							EReference ref = propUtil.getGuidanceEReference(item);
							EReference eRef = LibraryEditUtil.getInstance().getExcludeFeature(ref);
							List<MethodElement> parentExecludeList = (List<MethodElement>) greenParent.eGet(eRef);
							
							propUtil.removeGreenRefDelta((Descriptor) element, item, eRef, false);
							if (parentExecludeList != null && !parentExecludeList.contains(item)) {
								propUtil.addGreenRefDelta((Descriptor) element, item, eRef, true);
							}
						}
					}				
				}
			}
		}
	}

	/**
	 * Get selected guidances
	 * @return
	 * 			list of existing selected guidances
	 */
	private List<Guidance> getSelectedGuidances() {
		List<Guidance> itemList = new ArrayList();

		itemList.addAll(element.getChecklists());
		itemList.addAll(element.getConcepts());
		itemList.addAll(element.getExamples());
		itemList.addAll(element.getGuidelines());
		itemList.addAll(element.getReusableAssets());
		itemList.addAll(element.getSupportingMaterials());
		itemList.addAll(element.getTemplates());
		itemList.addAll(element.getReports());
		itemList.addAll(element.getEstimationconsiderations());
		itemList.addAll(element.getToolmentor());
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
	
	protected boolean isSyncFree() {
		return propUtil.isDescriptor(element) && ProcessUtil.isSynFree();
	}
	
	protected boolean syncFreeAdd(IStructuredSelection selection) {
		if (selection.size() == 0) {
			return false;			
		} 
				
		boolean result = propUtil.CheckSelectionForGuidance(selection.toList(), (Descriptor)element, getConfiguration());	
		
		if (! result) {
			return true;
		}
		
		Object testObj = selection.getFirstElement();
		EReference ref = propUtil.getGuidanceEReference((Guidance)testObj);
		if (propUtil.isDynamicAndExclude(testObj, (Descriptor)element, ref, getConfiguration())) {				
			addGuidances(selection.toList(), true);
			return true;
		} 
		
		return false;
	}
	
	protected boolean syncFreeRemove(IStructuredSelection selection) {
		if (selection.size() == 0) {
			return true;			
		} 
		
		boolean result = propUtil.CheckSelectionForGuidance(selection.toList(), (Descriptor)element, getConfiguration());
		if (! result) {
			return true;
		}

		Object testObj = selection.getFirstElement();
		EReference ref = propUtil.getGuidanceEReference((Guidance)testObj);		
		if (propUtil.isDynamicAndExclude(testObj, (Descriptor)element, ref, getConfiguration())) {
			return true;
		} 
		
		if (propUtil.isGuidanceDynamic(testObj, (Descriptor)element, getConfiguration())) {
			removeGuidances(selection.toList(), false);
			return true;
		} 
				
		return false;
	}
	
	protected void syncFreeUpdateBtnStatus(IStructuredSelection selection) {		
		boolean result = propUtil.CheckSelectionForGuidance(selection.toList(), (Descriptor)element, getConfiguration());
		
		if (!result) {
			ctrl_add_1.setEnabled(false);
			ctrl_remove_1.setEnabled(false);
		} else {
			Object testObj = selection.getFirstElement();
			EReference ref = propUtil.getGuidanceEReference((Guidance)testObj);
			if (propUtil.isDynamicAndExclude(testObj, (Descriptor)element, ref, getConfiguration())) {
				ctrl_add_1.setEnabled(true);
				ctrl_remove_1.setEnabled(false);
			} else {
				ctrl_add_1.setEnabled(true);
				ctrl_remove_1.setEnabled(true);
			}
		}		
	}
	
	public class GuidanceSyncFreeLabelProvider extends AdapterFactoryLabelProvider implements ITableFontProvider {
		private DescriptorPropUtil propUtil = DescriptorPropUtil.getDesciptorPropUtil();
		private Font systemFont = Display.getCurrent().getSystemFont();
		private	Font boldFont = null;
		private Font boldAndItalicFont = null;
		
		private Descriptor desc;
		private MethodConfiguration config;

		public GuidanceSyncFreeLabelProvider(AdapterFactory adapterFactory, Descriptor desc, MethodConfiguration config) {
			super(adapterFactory);
			this.desc = desc;		
			this.config = config;
			boldFont = createFont(SWT.BOLD);
			boldAndItalicFont = createFont(SWT.BOLD | SWT.ITALIC);
		}
	    
	    public Font getFont(Object obj, int columnIndex) {
			if (propUtil.isGuidanceFromGreenParentLocalList(obj, desc, config)) {
				return boldAndItalicFont;
			}
			
		 	if (!propUtil.isGuidanceDynamic(obj, desc, config)) {
		 		return boldFont;
	    	}
			
	    	return systemFont;
	    }	    	
	    
	    public String getColumnText(Object obj, int columnIndex) {
	    	String original = super.getColumnText(obj, columnIndex);
	    	
	    	EReference ref = propUtil.getGuidanceEReference((Guidance)obj);
	    	
	    	if (propUtil.isDynamicAndExclude(obj, desc, ref, config)) {
	    		return "--<" + original + ">";	    		 //$NON-NLS-1$ //$NON-NLS-2$
	    	}
	    	
	    	return original;	    	
	    }
	    
	    public void dispose() {
	    	super.dispose();
	    	
	    	if (boldFont != null) {
	    		boldFont.dispose();
	    	}
	    	
	    	if (boldAndItalicFont != null) {
	    		boldAndItalicFont.dispose();
	    	}
	    }
	    
	    private Font createFont(int style) {
			FontData[] fontdata = systemFont.getFontData();
			for (FontData data : fontdata) {
				data.setStyle(style);
			}
			
			return new Font(Display.getCurrent(), fontdata);    	
	    }	    
	}
	
}
