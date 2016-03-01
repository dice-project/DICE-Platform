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
package org.eclipse.epf.authoring.ui.editors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EventObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.AdapterFactoryTreeIterator;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.actions.IWorkbenchPartAction;
import org.eclipse.epf.authoring.ui.actions.LibraryValidateAction;
import org.eclipse.epf.authoring.ui.dnd.EditingDomainTableTreeViewerDropAdapter;
import org.eclipse.epf.authoring.ui.forms.DeliveryProcessDescription;
import org.eclipse.epf.authoring.ui.forms.ProcessBreakdownStructureFormPage;
import org.eclipse.epf.authoring.ui.forms.ProcessDescription;
import org.eclipse.epf.authoring.ui.preferences.ApplicationPreferenceConstants;
import org.eclipse.epf.authoring.ui.properties.EPFPropertySheetPage;
import org.eclipse.epf.authoring.ui.providers.IMethodElementEditorPageProviderExtension;
import org.eclipse.epf.authoring.ui.util.ProcessEditorUtil;
import org.eclipse.epf.authoring.ui.views.ProcessViewer;
import org.eclipse.epf.common.preferences.IPreferenceStoreWrapper;
import org.eclipse.epf.common.preferences.IPropertyChangeEventWrapper;
import org.eclipse.epf.common.preferences.IPropertyChangeListenerWrapper;
import org.eclipse.epf.diagram.core.part.AbstractDiagramEditor;
import org.eclipse.epf.diagram.core.part.DiagramEditorInput;
import org.eclipse.epf.diagram.core.part.DiagramEditorInputProxy;
import org.eclipse.epf.diagram.ui.service.DiagramEditorHelper;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.ILibraryServiceListener;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.configuration.ProcessAuthoringConfigurator;
import org.eclipse.epf.library.edit.IAdapterFactoryProvider;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.ActionManager;
import org.eclipse.epf.library.edit.command.CommandStackChangedEvent;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.meta.TypeDefUtil;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.process.command.ActivityDropCommand;
import org.eclipse.epf.library.edit.realization.IRealizationManager;
import org.eclipse.epf.library.edit.ui.IActionTypeProvider;
import org.eclipse.epf.library.edit.util.ConfigurableComposedAdapterFactory;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.EditingDomainComposedAdapterFactory;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.library.edit.util.ProcessScopeUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.events.ILibraryChangeListener;
import org.eclipse.epf.library.prefs.PreferenceUtil;
import org.eclipse.epf.library.ui.LibraryUIImages;
import org.eclipse.epf.library.util.ResourceUtil;
import org.eclipse.epf.persistence.FileManager;
import org.eclipse.epf.persistence.refresh.RefreshJob;
import org.eclipse.epf.persistence.util.PersistenceUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.edit.domain.TraceableAdapterFactoryEditingDomain;
import org.eclipse.epf.uma.util.Scope;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.epf.validation.LibraryEValidator;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheet;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.ibm.icu.util.StringTokenizer;

/**
 * Editor for process authoring.
 * 
 * @author Phong Nguyen Le
 * @author Shilpa Toraskar
 * @author Jinhua Xi
 * @since 1.0
 */
public class ProcessEditor extends MethodElementEditor implements
		IEditingDomainProvider, IMenuListener, ISelectionProvider,
		IViewerProvider, ITabbedPropertySheetPageContributor {

	/**
	 * The editor ID.
	 */
	public static final String EDITOR_ID = ProcessEditor.class.getName();

	public static final String WORKFLOW_EDITOR_ID = GraphicalWorkflowEditor.class
			.getName();

	public static final String ACTIVITY_DETAIL_DIAGRAM_EDITOR_ID = ActivityDetailDiagramEditor.class
			.getName();

	public static final String WPDEPENDENCY_EDITOR_ID = GraphicalWPDependencyEditor.class
			.getName();

	/** Column descriptor constants */
	public static final ColumnDescriptor COL_DESC_NAME = new ColumnDescriptor(
			IBSItemProvider.COL_NAME, ProcessUtil.getColumnDisplayName(IBSItemProvider.COL_NAME),
			3, 200, true, ColumnDescriptor.CELL_EDITOR_TYPE_TEXT);

	public static final ColumnDescriptor COL_DESC_ID = new ColumnDescriptor(
			IBSItemProvider.COL_ID, ProcessUtil.getColumnDisplayName(IBSItemProvider.COL_ID),
			0, 40, true, ColumnDescriptor.CELL_EDITOR_TYPE_NONE);

	public static final ColumnDescriptor COL_DESC_PREFIX = new ColumnDescriptor(
			IBSItemProvider.COL_PREFIX,
			ProcessUtil.getColumnDisplayName(IBSItemProvider.COL_PREFIX), 0, 80, true,
			ColumnDescriptor.CELL_EDITOR_TYPE_TEXT);

	public static final ColumnDescriptor COL_DESC_MODEL_INFO = new ColumnDescriptor(
			IBSItemProvider.COL_MODEL_INFO,
			ProcessUtil.getColumnDisplayName(IBSItemProvider.COL_MODEL_INFO), 0, 120, true,
			ColumnDescriptor.CELL_EDITOR_TYPE_NONE); 

	public static final ColumnDescriptor COL_DESC_TYPE = new ColumnDescriptor(
			IBSItemProvider.COL_TYPE, ProcessUtil.getColumnDisplayName(IBSItemProvider.COL_TYPE),
			0, 80, true, ColumnDescriptor.CELL_EDITOR_TYPE_NONE);

	public static final ColumnDescriptor COL_DESC_PREDECESSORS = new ColumnDescriptor(
			IBSItemProvider.COL_PREDECESSORS,
			ProcessUtil.getColumnDisplayName(IBSItemProvider.COL_PREDECESSORS), 2, 100, true,
			ColumnDescriptor.CELL_EDITOR_TYPE_TEXT); 

	public static final ColumnDescriptor COL_DESC_IS_REPEATABLE = new ColumnDescriptor(
			IBSItemProvider.COL_IS_REPEATABLE,
			ProcessUtil.getColumnDisplayName(IBSItemProvider.COL_IS_REPEATABLE), 1, 60, true,
			ColumnDescriptor.CELL_EDITOR_TYPE_CHECK_BOOLEAN); 

	public static final ColumnDescriptor COL_DESC_IS_ONGOING = new ColumnDescriptor(
			IBSItemProvider.COL_IS_ONGOING,
			ProcessUtil.getColumnDisplayName(IBSItemProvider.COL_IS_ONGOING), 1, 60, true,
			ColumnDescriptor.CELL_EDITOR_TYPE_CHECK_BOOLEAN);

	public static final ColumnDescriptor COL_DESC_IS_EVENT_DRIVEN = new ColumnDescriptor(
			IBSItemProvider.COL_IS_EVENT_DRIVEN,
			ProcessUtil.getColumnDisplayName(IBSItemProvider.COL_IS_EVENT_DRIVEN), 1, 60, true,
			ColumnDescriptor.CELL_EDITOR_TYPE_CHECK_BOOLEAN); 

	public static final ColumnDescriptor COL_DESC_TEAM = new ColumnDescriptor(
			IBSItemProvider.COL_TEAMS, ProcessUtil.getColumnDisplayName(IBSItemProvider.COL_TEAMS),
			1, 100, true, ColumnDescriptor.CELL_EDITOR_TYPE_TEXT);

	public static final ColumnDescriptor COL_DESC_ENTRY_STATE = new ColumnDescriptor(
			IBSItemProvider.COL_ENTRY_STATE,
			ProcessUtil.getColumnDisplayName(IBSItemProvider.COL_ENTRY_STATE), 1, 60, true,
			ColumnDescriptor.CELL_EDITOR_TYPE_TEXT); 

	public static final ColumnDescriptor COL_DESC_EXIT_STATE = new ColumnDescriptor(
			IBSItemProvider.COL_EXIT_STATE,
			ProcessUtil.getColumnDisplayName(IBSItemProvider.COL_EXIT_STATE), 1, 60, true,
			ColumnDescriptor.CELL_EDITOR_TYPE_TEXT); 

	public static final ColumnDescriptor COL_DESC_PRESENTATION_NAME = new ColumnDescriptor(
			IBSItemProvider.COL_PRESENTATION_NAME,
			ProcessUtil.getColumnDisplayName(IBSItemProvider.COL_PRESENTATION_NAME), 3, 200, true,
			ColumnDescriptor.CELL_EDITOR_TYPE_TEXT); 

	public static final ColumnDescriptor COL_DESC_DELIVERABLE = new ColumnDescriptor(
			IBSItemProvider.COL_DELIVERABLE,
			ProcessUtil.getColumnDisplayName(IBSItemProvider.COL_DELIVERABLE), 1, 100, true,
			ColumnDescriptor.CELL_EDITOR_TYPE_TEXT); 

	public static final ColumnDescriptor COL_DESC_IS_OPTIONAL = new ColumnDescriptor(
			IBSItemProvider.COL_IS_OPTIONAL,
			ProcessUtil.getColumnDisplayName(IBSItemProvider.COL_IS_OPTIONAL), 1, 60, true,
			ColumnDescriptor.CELL_EDITOR_TYPE_CHECK_BOOLEAN); 

	public static final ColumnDescriptor COL_DESC_IS_PLANNED = new ColumnDescriptor(
			IBSItemProvider.COL_IS_PLANNED,
			ProcessUtil.getColumnDisplayName(IBSItemProvider.COL_IS_PLANNED), 1, 60, true,
			ColumnDescriptor.CELL_EDITOR_TYPE_CHECK_BOOLEAN);

	public static final ColumnDescriptor COL_DESC_HAS_MULTIPLE_OCCURRENCES = new ColumnDescriptor(
			IBSItemProvider.COL_HAS_MULTIPLE_OCCURRENCES,
			ProcessUtil.getColumnDisplayName(IBSItemProvider.COL_HAS_MULTIPLE_OCCURRENCES), 1, 60,
			true, ColumnDescriptor.CELL_EDITOR_TYPE_CHECK_BOOLEAN); 

	public static final String WBS_FORM_ID = "wbs"; //$NON-NLS-1$

	public static final String TA_FORM_ID = "tbs"; //$NON-NLS-1$

	public static final String WPBS_FORM_ID = "wpbs"; //$NON-NLS-1$

	public static final String CONSOLIDATED_FORM_ID = "consolidated"; //$NON-NLS-1$
	
	DiagramEditorHelper diagramEditorHelper = null;

	// change the default order based on 152867 - Publish option and prefrences
	/* WBS: Presentation Name, Index, Predecessors, Model Info, Type, Planned,
			Repeatable, Multiple Occurrences, Ongoing, Event-driven, Optional
		TA: Presentation Name, Model Info, Team, Type, Planned, Multiple
			Occurrences, Optional
		Work Product Usage: Presentation Name, Model Info, Entry State, Exit State, 
			Deliverable, Type, Planned, Multiple Occurrences, Optional
	*/
	// Jinhua Xi, 08/21/2006
	
	// the default list must be available in the library plugin as well.
	// duplicate definition for now, will need to merge together later
	// see PreferenceConstants.org.eclipse.epf.library.prefs
	
	public static final List<ColumnDescriptor> DEFAULT_TBS_COLUMNS = Arrays
			.asList(new ColumnDescriptor[] {
					COL_DESC_PRESENTATION_NAME,
					// COL_DESC_ID,
					//COL_DESC_PREFIX, 
					COL_DESC_MODEL_INFO, COL_DESC_TEAM, COL_DESC_TYPE,
					COL_DESC_IS_PLANNED,
					COL_DESC_HAS_MULTIPLE_OCCURRENCES, COL_DESC_IS_OPTIONAL });

	public static final List DEFAULT_WPBS_COLUMNS = Arrays
			.asList(new ColumnDescriptor[] {
					COL_DESC_PRESENTATION_NAME,
					// COL_DESC_ID,
					//COL_DESC_PREFIX, 
					COL_DESC_MODEL_INFO, COL_DESC_ENTRY_STATE,
					COL_DESC_EXIT_STATE, 
					COL_DESC_DELIVERABLE, COL_DESC_TYPE, 
					COL_DESC_IS_PLANNED, COL_DESC_HAS_MULTIPLE_OCCURRENCES, COL_DESC_IS_OPTIONAL });

	public static final List<ColumnDescriptor> DEFAULT_WBS_COLUMNS = Arrays
			.asList(new ColumnDescriptor[] { COL_DESC_PRESENTATION_NAME,
					COL_DESC_ID, 
					//COL_DESC_PREFIX, 
					COL_DESC_PREDECESSORS, COL_DESC_MODEL_INFO,
					COL_DESC_TYPE, COL_DESC_IS_PLANNED, 
					COL_DESC_IS_REPEATABLE, COL_DESC_HAS_MULTIPLE_OCCURRENCES, COL_DESC_IS_ONGOING,
					COL_DESC_IS_EVENT_DRIVEN, COL_DESC_IS_OPTIONAL
					 });

	 
	public static final List<ColumnDescriptor> ALL_WBS_COLUMNS = new ArrayList<ColumnDescriptor>(
			DEFAULT_WBS_COLUMNS);

	public static final List<ColumnDescriptor> ALL_TBS_COLUMNS = new ArrayList<ColumnDescriptor>(
			DEFAULT_TBS_COLUMNS);

	public static final List<ColumnDescriptor> ALL_WPBS_COLUMNS = new ArrayList<ColumnDescriptor>(
			DEFAULT_WPBS_COLUMNS);

	public static final Map<String, ColumnDescriptor> idToColumnDescriptorMap = new HashMap<String, ColumnDescriptor>();
	static {
		ALL_WBS_COLUMNS.add(COL_DESC_PREFIX);
		ALL_TBS_COLUMNS.add(COL_DESC_PREFIX);
		ALL_WPBS_COLUMNS.add(COL_DESC_PREFIX);
		
		ProcessEditor.idToColumnDescriptorMap.put(COL_DESC_NAME.id,
				COL_DESC_NAME);
		ProcessEditor.idToColumnDescriptorMap.put(COL_DESC_ID.id, COL_DESC_ID);
		ProcessEditor.idToColumnDescriptorMap.put(COL_DESC_PREFIX.id,
				COL_DESC_PREFIX);
		ProcessEditor.idToColumnDescriptorMap.put(COL_DESC_MODEL_INFO.id,
				COL_DESC_MODEL_INFO);
		ProcessEditor.idToColumnDescriptorMap.put(COL_DESC_TYPE.id,
				COL_DESC_TYPE);
		ProcessEditor.idToColumnDescriptorMap.put(COL_DESC_PREDECESSORS.id,
				COL_DESC_PREDECESSORS);
		ProcessEditor.idToColumnDescriptorMap.put(COL_DESC_IS_REPEATABLE.id,
				COL_DESC_IS_REPEATABLE);
		ProcessEditor.idToColumnDescriptorMap.put(COL_DESC_IS_ONGOING.id,
				COL_DESC_IS_ONGOING);
		ProcessEditor.idToColumnDescriptorMap.put(COL_DESC_IS_EVENT_DRIVEN.id,
				COL_DESC_IS_EVENT_DRIVEN);
		ProcessEditor.idToColumnDescriptorMap.put(COL_DESC_TEAM.id,
				COL_DESC_TEAM);
		ProcessEditor.idToColumnDescriptorMap.put(COL_DESC_ENTRY_STATE.id,
				COL_DESC_ENTRY_STATE);
		ProcessEditor.idToColumnDescriptorMap.put(COL_DESC_EXIT_STATE.id,
				COL_DESC_EXIT_STATE);
		ProcessEditor.idToColumnDescriptorMap.put(
				COL_DESC_PRESENTATION_NAME.id, COL_DESC_PRESENTATION_NAME);
		ProcessEditor.idToColumnDescriptorMap.put(COL_DESC_DELIVERABLE.id,
				COL_DESC_DELIVERABLE);
		ProcessEditor.idToColumnDescriptorMap.put(COL_DESC_IS_OPTIONAL.id,
				COL_DESC_IS_OPTIONAL);
		ProcessEditor.idToColumnDescriptorMap.put(COL_DESC_IS_PLANNED.id,
				COL_DESC_IS_PLANNED);
		ProcessEditor.idToColumnDescriptorMap.put(
				COL_DESC_HAS_MULTIPLE_OCCURRENCES.id,
				COL_DESC_HAS_MULTIPLE_OCCURRENCES);

		ALL_WBS_COLUMNS.add(COL_DESC_NAME);
		ALL_TBS_COLUMNS.add(COL_DESC_NAME);
		ALL_WPBS_COLUMNS.add(COL_DESC_NAME);
	}

	protected static boolean addAdapterFactoryListeners = false;

	private class ProcessEditorDropAdapter extends
			EditingDomainTableTreeViewerDropAdapter {

		/**
		 * @param domain
		 * @param viewer
		 */
		public ProcessEditorDropAdapter(EditingDomain domain, Viewer viewer) {
			super(domain, viewer);
		}

		/** 
		 * @see org.eclipse.epf.authoring.ui.dnd.EditingDomainTableTreeViewerDropAdapter#getDropTarget(org.eclipse.swt.widgets.Widget)
		 */
		protected Object getDropTarget(Widget item) {
			Object target = super.getDropTarget(item);
			if (target == null && selectedProcessComponent != null) {
				target = selectedProcessComponent.getProcess();
			}
			return target;
		}

		/**
		 * @see org.eclipse.epf.authoring.ui.dnd.EditingDomainTableTreeViewerDropAdapter#drop(org.eclipse.swt.dnd.DropTargetEvent)
		 */
		public void drop(DropTargetEvent event) {
			if (currentViewer instanceof IActionTypeProvider) {
				((IActionTypeProvider) currentViewer).setInputData(new Point(
						event.x, event.y));
			}
			super.drop(event);
		}
	}

	protected ComposedAdapterFactory adapterFactory;

	protected AdapterFactoryEditingDomain editingDomain;

	protected Viewer currentViewer;

	protected ISelectionChangedListener selectionChangedListener;

	protected Collection selectionChangedListeners = new ArrayList();

	protected ISelection viewSelection;

	protected ProcessComponent selectedProcessComponent;

	protected Process selectedProcess;

	protected Adapter processComponentListener = new AdapterImpl() {
		/**
		 * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
		 */
		public void notifyChanged(Notification msg) {
			switch (msg.getFeatureID(ProcessComponent.class)) {
			case UmaPackage.PROCESS_COMPONENT__NAME:
				String name = msg.getNewStringValue();
				if (name == null)
					name = ""; //$NON-NLS-1$
				setPartName(name);

				if (pages != null) {
					for (Iterator iter = pages.iterator(); iter.hasNext();) {
						Object page = iter.next();
						if (page instanceof ProcessDescription) {
							((ProcessDescription) page)
									.refreshElementName(name);
						}

					}
				}

				break;
			}
		}
	};

	protected AdapterFactoryContentProvider propContentProvider;

	protected MethodConfiguration currentConfig;

	protected ILibraryChangeListener libraryListener = new ILibraryChangeListener() {

		public void libraryChanged(int option, Collection collection) {
			switch (option) {
			// case ILibraryChangeListener.OPTION_LOADED:
			// // close all open diagram editors
			// //
			// IEditorReference[] editorRefs =
			// getSite().getPage().getEditorReferences();
			// for (int i = 0; i < editorRefs.length; i++) {
			// IEditorPart editorPart = editorRefs[i].getEditor(false);
			// if(editorPart instanceof GraphicalWorkflowEditor
			// || editorPart instanceof ProcessFormEditor) {
			// getSite().getPage().closeEditor(editorPart, true);
			// }
			// }
			// getSite().getPage().closeEditor(ProcessFormEditor.this, true);
			case ILibraryChangeListener.OPTION_CHANGED: {
				if (collection != null && collection.contains(currentConfig)) {
					MethodConfiguration config = ProcessAuthoringConfigurator.INSTANCE
							.getMethodConfiguration();
					try {
						ProcessAuthoringConfigurator.INSTANCE
								.setMethodConfiguration(currentConfig);
						refreshAll();
					} finally {
						ProcessAuthoringConfigurator.INSTANCE
								.setMethodConfiguration(config);
					}
				}
				
				synUpdate(collection);
				
				break;
			}
				// handled by libSvcListener
				// case ILibraryChangeListener.OPTION_CONFIGURATION_SELECTED: {
				// configChanged();
				// break;
				// }

			}
			
		}

		private void synUpdate(Collection collection) {
			if (! ProcessUtil.isSynFree()) {
				return;
			}
			if (collection == null || collection.isEmpty()) {
				return;
			}
			
			for (Object obj : collection) {
				if (! (obj instanceof MethodElement)) {
					continue;
				}
				if (obj instanceof ContentDescription) {
					obj = ((ContentDescription) obj).eContainer();
				}
				MethodElement element = (MethodElement) obj;
				if (obj instanceof Task || obj instanceof Role
						|| obj instanceof WorkProduct || TypeDefUtil.hasLinkTypes(element)) {					
					if (changedElementSet == null) {
						changedElementSet = new HashSet<MethodElement>();
					}
					changedElementSet.add((MethodElement) obj);
				} else if (obj instanceof TaskDescriptor) {
					DescriptorPropUtil propUtil = DescriptorPropUtil
							.getDesciptorPropUtil();
					Set<MethodElement> greenDescendents = new HashSet<MethodElement>();
					propUtil.collectCustomizingDescendants(
							(TaskDescriptor) obj, greenDescendents);
					if (!greenDescendents.isEmpty()) {
						if (changedElementSet == null) {
							changedElementSet = new HashSet<MethodElement>();
						}
						changedElementSet.addAll(greenDescendents);
					}
				}
			}
			updateAndRefreshProcessModel();
		}					

	};
	
	protected ILibraryServiceListener libSvcListener = new ILibraryServiceListener() {

		public void configurationSet(MethodConfiguration config) {
			configChanged();
		}

		public void libraryClosed(MethodLibrary library) {
		}

		public void libraryCreated(MethodLibrary library) {
		}

		public void libraryOpened(MethodLibrary library) {
		}

		public void libraryReopened(MethodLibrary library) {
		}

		public void librarySet(MethodLibrary library) {
		}

	};

	protected ProcessBreakdownStructureFormPage WBSTab;

	protected ProcessBreakdownStructureFormPage OBSTab;

	protected ProcessBreakdownStructureFormPage PBSTab;

	protected ProcessBreakdownStructureFormPage procTab;

	protected Collection<ProcessBreakdownStructureFormPage> extensionTabs = null;

	protected ProcessBreakdownStructureFormPage[] bsPages;

	protected EPFPropertySheetPage propertySheetPage;

	public Collection resourcesToSave = new ArrayList();

	// private boolean firstExpanded = false;

	/**
	 * a listener that is interested in part activation events.
	 */
	protected IPartListener partActivationListener = new IPartListener() {

		public void partActivated(IWorkbenchPart part) {
			if (part instanceof PropertySheet) {
				removePropertiesDropDownMenu(part);
			}
		}

		public void partBroughtToTop(IWorkbenchPart part) {
			// if(!firstExpanded) {
			// synchronized(ProcessFormEditor.this) {
			// if(firstExpanded) {
			// ((AbstractTreeViewer) WBSTab.getViewer()).expandAll();
			// ((AbstractTreeViewer) OBSTab.getViewer()).expandAll();
			// ((AbstractTreeViewer) PBSTab.getViewer()).expandAll();
			// firstExpanded = true;
			// }
			// }
			// }
		}

		public void partClosed(IWorkbenchPart part) {
			if (part instanceof PropertySheet) {
				propertySheetPage = null;
			}
		}

		public void partDeactivated(IWorkbenchPart part) {
		}

		public void partOpened(IWorkbenchPart part) {
			if (part instanceof PropertySheet) {
				removePropertiesDropDownMenu(part);
			}
		}
	};

	protected IPropertyChangeListenerWrapper prefStoreListener;

	// private ProcessConfigurator configurator;

	protected Suppression suppression;

	protected boolean synchronizingSelection;

	protected boolean inputChanged;

	public static final IAdapterFactoryProvider adapterFactoryProvider = new IAdapterFactoryProvider() {

		public AdapterFactory getCBSAdapterFactory() {
			return TngAdapterFactory.INSTANCE.getProcessComposedAdapterFactory();
		}

		public AdapterFactory getTBSAdapterFactory() {
			return TngAdapterFactory.INSTANCE.getOBS_ComposedAdapterFactory();
		}

		public AdapterFactory getWBSAdapterFactory() {
			return TngAdapterFactory.INSTANCE.getWBS_ComposedAdapterFactory();
		}

		public AdapterFactory getWPBSAdapterFactory() {
			return TngAdapterFactory.INSTANCE.getPBS_ComposedAdapterFactory();
		}
	};
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.authoring.ui.editors.MethodElementEditor#isValidateResourcesBeforeSaveRequired()
	 */
	protected boolean isValidateResourcesBeforeSaveRequired() {
		return true;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.authoring.ui.editors.MethodElementEditor#createValidateResourceAction()
	 */
	protected LibraryValidateAction createValidateResourceAction() {
		LibraryValidateAction validateAction = super.createValidateResourceAction();
		validateAction.putContextData(LibraryEValidator.CTX_ADAPTER_FACTORY_PROVIDER,
				adapterFactoryProvider);
		return validateAction;
	}
	
	protected void inputChanged(Object newInput) {
		inputChanged = false;
		if (!(newInput instanceof MethodElementEditorInput))
			return;
		MethodElementEditorInput methodElementInput = (MethodElementEditorInput) newInput;

		Object obj = methodElementInput.getMethodElement();
		if (obj instanceof ProcessComponent) {
			selectedProcessComponent = (ProcessComponent) obj;
			selectedProcess = getTopBreakdownElement(selectedProcessComponent);
			suppression = getSuppression(selectedProcess);

			// for (Iterator iter = pages.iterator(); iter.hasNext();) {
			// Object page = iter.next();
			// if(page instanceof ProcessBreakdownStructureFormPage) {
			// ((ProcessBreakdownStructureFormPage)page).setProcess(proc);
			// }
			// }

			if (WBSTab != null) {
				WBSTab.setProcess(selectedProcess);
			}
			
			if ( OBSTab != null ) {
				OBSTab.setProcess(selectedProcess);
			}
			
			if ( PBSTab != null ) {
				PBSTab.setProcess(selectedProcess);
			}
			
			if ( procTab != null ) {
				procTab.setProcess(selectedProcess);
			}
			
			if (extensionTabs != null) {
				for (ProcessBreakdownStructureFormPage extPage : extensionTabs) {
					extPage.setProcess(selectedProcess);
				}
			}
		}
	}

	
	/**
	 * @return
	 */
	protected Process  getTopBreakdownElement(ProcessComponent selectedProcessComponent) {
		return (Process) ProcessUtil.getTopBreakdownElement(selectedProcessComponent);
	}
	
	   /**
     * @param selectedProcess
     * @return
     */
    protected Suppression getSuppression(Process selectedProcess) {
        return Suppression.getSuppression(selectedProcess);
    }

    
	// private String getTitlePrefix() {
	// IEditorInput input = getEditorInput();
	// if (input instanceof MethodElementEditorInput) {
	// Object obj = ((MethodElementEditorInput) input).getMethodElement();
	// if (obj instanceof ProcessComponent) {
	// ProcessComponent pc = (ProcessComponent) obj;
	// Process proc = pc.getProcess();
	// if (proc instanceof CapabilityPattern) {
	// return LibraryUIText.TEXT_CAPABILITY_PATTERN;
	// } else if (proc instanceof DeliveryProcess) {
	// return LibraryUIText.TEXT_DELIVERY_PROCESS;
	// } else if (proc instanceof ProcessContribution) {
	// return LibraryUIText.TEXT_PROCESS_CONTRIBUTION;
	// }
	// }
	// }
	// return AuthoringUIResources
	// .getString("AuthoringUI.ProcessEditor.Title"); //$NON-NLS-1$
	// }

	protected Image getProcTitleImage() {
		IEditorInput input = getEditorInput();
		if (input instanceof MethodElementEditorInput) {
			Object obj = ((MethodElementEditorInput) input).getMethodElement();
			if (obj instanceof ProcessComponent) {
				ProcessComponent pc = (ProcessComponent) obj;
				Process proc = pc.getProcess();
				if(proc != null) {
					return ExtendedImageRegistry.getInstance().getImage(TngUtil.getImage(proc));
				}
			}
		}
		return LibraryUIImages.IMG_PROCESS;
	}

	/**
	 * Remove Properties view drop down menu
	 * 
	 * @param part
	 */
	protected void removePropertiesDropDownMenu(IWorkbenchPart part) {
		if (part instanceof PropertySheet) {
			IViewSite view = ((PropertySheet) part).getViewSite();

			IMenuManager menuMgr = view.getActionBars().getMenuManager();
			// IContributionItem[] items = menuMgr.getItems();
			// for (int i=0; i < items.length; i++)
			// {
			// System.out.println("Id =" + i + " " + items[i].getId() + "$");
			// }
			menuMgr.removeAll();
			menuMgr.updateAll(true);
		}
	}

	/**
	 * @see org.eclipse.ui.forms.editor.FormEditor#init(org.eclipse.ui.IEditorSite,
	 *      org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		super.init(site, input);
		site.setSelectionProvider(this);

		// TODO: need revisit
		// site.setSelectionProvider(new FormEditorSelectionProvider(this));
		// actionManager = getActionManager();

		List factories = new ArrayList();
		factories.add(new ResourceItemProviderAdapterFactory());
		factories.add(new ReflectiveItemProviderAdapterFactory());

		adapterFactory = new ComposedAdapterFactory(factories);

		// Create the command stack that will notify this editor as commands are
		// executed.
		//
		CommandStack commandStack = actionMgr.getCommandStack(); // new
		// BasicCommandStack();

		// Add a listener to set the most recent command's affected objects to
		// be the selection of the viewer with focus.
		//
		commandStack.addCommandStackListener(new CommandStackListener() {
			public void commandStackChanged(final EventObject event) {
				boolean run = false;
				if (event instanceof CommandStackChangedEvent) {
					switch (((CommandStackChangedEvent) event).getType()) {
					case CommandStackChangedEvent.EXECUTED:
					case CommandStackChangedEvent.UNDO:
						run = true;
						break;
					}
				} else {
					run = true;
				}
				if (run) {
					Display.getCurrent().asyncExec(new Runnable() {
						public void run() {
							firePropertyChange(IEditorPart.PROP_DIRTY);

							// Try to select the affected objects.
							//
							Command mostRecentCommand = ((CommandStack) event
									.getSource()).getMostRecentCommand();
							if (mostRecentCommand != null) {
									if (!(TngUtil.unwrap(mostRecentCommand) instanceof SetCommand)) {
										if (mostRecentCommand.getAffectedObjects().size() == 1) {		//Multiple select does not make sense
											setSelectionToViewer(mostRecentCommand
													.getAffectedObjects());
										}
									}
									if (mostRecentCommand instanceof CreateChildCommand
											&& currentViewer instanceof ProcessViewer) {
										Object[] arr = mostRecentCommand
												.getAffectedObjects().toArray();
										if (arr.length == 1) {
											ProcessViewer viewer = (ProcessViewer) currentViewer;
											viewer.editElement(arr[0], 0);
										}
									}
							}
							// if (propertySheetPage != null) {
							// propertySheetPage.refresh();
							// }
						}
					});
				}
			}
		});

		// Create the editing domain with a special command stack.
		editingDomain = new TraceableAdapterFactoryEditingDomain(
				adapterFactory, commandStack);

		LibraryService.getInstance().addListener(libSvcListener);
		ILibraryManager manager = (ILibraryManager) LibraryService
				.getInstance().getCurrentLibraryManager();
		if (manager != null) {
			manager.addListener(libraryListener);
			manager.startListeningTo(actionMgr.getCommandStack());

			// TODO: need to revisit this code
			if (!addAdapterFactoryListeners) {
				addAdapterFactoryListeners = true;
				manager.startListeningTo(TngAdapterFactory.INSTANCE
						.getWBS_ComposedAdapterFactory());
				manager.startListeningTo(TngAdapterFactory.INSTANCE
						.getOBS_ComposedAdapterFactory());
				manager.startListeningTo(TngAdapterFactory.INSTANCE
						.getPBS_ComposedAdapterFactory());
			}
		}

		//setPartName(input.getName());
		//setTitleImage(getProcTitleImage());

		setPartFacade(input);

		// get method element object from Editor input
		MethodElementEditorInput methodElementInput = (MethodElementEditorInput) input;
		elementObj = methodElementInput.getMethodElement();

		if (elementObj instanceof ProcessComponent) {
			selectedProcessComponent = ((ProcessComponent) elementObj);
			selectedProcess = selectedProcessComponent.getProcess();
		}

		if (selectedProcessComponent != null) {
			selectedProcessComponent.eAdapters().add(processComponentListener);
		}

		// listen to resource change to update dirty flag
		//
		// addResourceChangedListener();

		// add part listener to listen to propertySheet close event
		getSite().getWorkbenchWindow().getPartService().addPartListener(
				partActivationListener);

		// inputChanged(input);

		// listen to the change of column list in preference store
		//
		if (prefStoreListener == null) {
			prefStoreListener = new IPropertyChangeListenerWrapper() {

				public void propertyChange(IPropertyChangeEventWrapper event) {
					ProcessBreakdownStructureFormPage page = null;
					if (event.getProperty().equals(
							ApplicationPreferenceConstants.PREF_WBS_COLUMNS)) {
						page = WBSTab;
					} else if (event.getProperty().equals(
							ApplicationPreferenceConstants.PREF_TBS_COLUMNS)) {
						page = OBSTab;
					} else if (event.getProperty().equals(
							ApplicationPreferenceConstants.PREF_WPBS_COLUMNS)) {
						page = PBSTab;
					}
					if (page != null) {
						ColumnDescriptor[] cols = toColumnDescriptors(getPreferenceStore().getString(
										event.getProperty()));
						page.updateColumns(cols);
						if (page == WBSTab || page == OBSTab) {
							// update consolidated view as well
							//
							// get WBS columns
							List list = toColumnDescriptorList(getPreferenceStore()
									.getString(ApplicationPreferenceConstants.PREF_WBS_COLUMNS));

							// get TBS columns 
							ColumnDescriptor[] teamColumnDescriptors = toColumnDescriptors(getPreferenceStore()
									.getString(ApplicationPreferenceConstants.PREF_TBS_COLUMNS));

							// if TBS columns has TEAM column then add to consolidated view
							for (int i = 0; i < teamColumnDescriptors.length; i++) {
								ColumnDescriptor colDesc = teamColumnDescriptors[i];
								if (colDesc == COL_DESC_TEAM) {
									list.add(colDesc);
									break;
								}
							}
							
							ColumnDescriptor[] columns = new ColumnDescriptor[list.size()];
							list.toArray(columns);
							procTab.updateColumns(columns);
						}
					}
				}

			};
		}

		getPreferenceStore()
				.addPropertyChangeListener(prefStoreListener);
		
		diagramEditorHelper = new DiagramEditorHelper();

	}

    /**
     * @param input
     */
    protected void setPartFacade(IEditorInput input) {
        setPartName(input.getName());
        setTitleImage();
    }
    
    @Override
    protected void setTitleImage() {
    	setTitleImage(getProcTitleImage());
    }

	/**
	 * This sets the selection into whichever viewer is active. 
	 * 
	 */
	public void setSelectionToViewer(Collection collection) {
		if (currentViewer != null && collection != null && !collection.isEmpty()) {
					// Try to select the items in the current content viewer of
					// the editor.
					//
			currentViewer.setSelection(new StructuredSelection(
					collection.toArray()), true);
		}
	}

	public EditingDomainActionBarContributor getActionBarContributor() {
		return (EditingDomainActionBarContributor) getEditorSite()
				.getActionBarContributor();
	}

	public void dispose() {
		if (getSelectedProcess() != null) {
			Scope scope = ProcessScopeUtil.getInstance().getScope(getSelectedProcess());
			if (scope != null) {
				ProcessScopeUtil.getInstance().endProcessEdit(scope);
			}
		}
		
		// close all diagram editors of this process
		//
		closeAllDiagramEditors();
		
		getPreferenceStore()
				.removePropertyChangeListener(prefStoreListener);

		// ResourcesPlugin.getWorkspace().removeResourceChangeListener(resourceChangeListener);
		//
		// getSite().getPage().removePartListener(partListener);

		adapterFactory.dispose();

		if (getActionBarContributor().getActiveEditor() == this) {
			getActionBarContributor().setActiveEditor(null);
		}

		if (propertySheetPage != null) {
			propertySheetPage.dispose();
		}

		ILibraryManager manager = LibraryService.getInstance()
				.getCurrentLibraryManager();
		if (manager != null) {
			manager.removeListener(libraryListener);
			manager.stopListeningTo(editingDomain.getCommandStack());
		}
		LibraryService.getInstance().removeListener(libSvcListener);

		if (selectedProcessComponent != null) {
			selectedProcessComponent.eAdapters().remove(
					processComponentListener);
		}

		// removeResourceChangedListener();

		getSite().getWorkbenchWindow().getPartService().removePartListener(
				partActivationListener);

		boolean saveNeeded = isDirty();
		
		super.dispose();
		
		if(diagramEditorHelper != null)
			diagramEditorHelper.dispose();


		// call this to set modified flag of suppression to false
		//
		suppression.saveIsDone();

		if (saveNeeded) {
			// user discarded all the changes, save the resources after changes
			// are undone.
			// TODO: reload the process component resource instead since it is
			// not shared.
			//
			// for (Iterator iter = resources.iterator(); iter.hasNext();) {
			// Resource resource = (Resource) iter.next();
			// saveResource(resource);
			// }
		}
	}

	private void closeAllDiagramEditors() {
		IWorkbenchPage workbenchPage = AuthoringUIPlugin.getDefault()
				.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		if(workbenchPage == null) {
			// workbench is closing, all editor will be closed so no need to close the diagram editor here
			//
			return;
		}
		IEditorReference[] editorReferences = workbenchPage
				.getEditorReferences();
		ArrayList<IEditorReference> diagramEditorRefs = new ArrayList<IEditorReference>();
		for (int i = 0; i < editorReferences.length; i++) {
			IEditorReference reference = editorReferences[i];
			IEditorPart editor = reference.getEditor(true);
			IEditorInput input = editor.getEditorInput();
			if (input instanceof DiagramEditorInputProxy) {
				DiagramEditorInput diagramInput = ((DiagramEditorInputProxy) input)
				.getDiagramEditorInput();
				if (diagramInput != null) {
					MethodElement element = diagramInput.getMethodElement();
					if (element instanceof BreakdownElement
							&& selectedProcess == TngUtil
							.getOwningProcess((BreakdownElement) element)) {
						diagramEditorRefs.add(reference);
					}
				}
			}
		}
		if(!diagramEditorRefs.isEmpty()) {
			IEditorReference[] refs = new IEditorReference[diagramEditorRefs.size()];
			diagramEditorRefs.toArray(refs);
			workbenchPage.closeEditors(refs, true);
		}
	}

	protected void addDescriptionPage() throws PartInitException {
		if (selectedProcess instanceof DeliveryProcess) {
			// Delivery Process
			addPage(0, new DeliveryProcessDescription(this));
		} else {
			// Capability Pattern
			// Process Contribution
			addPage(0, new ProcessDescription(this));
		}
	}

	/**
	 * @see org.eclipse.ui.forms.editor.FormEditor#addPages()
	 */
	protected void addPages() {
		try {
			addDescriptionPage();

			AdapterFactory adapterFactory;

			// create DND transfer objects
			// int dndOperations = DND.DROP_COPY | DND.DROP_LINK;
			// Transfer[] transfers = new Transfer[]
			// {LocalTransfer.getInstance() };

			// preference is managed by library plugin,
			// since this preferences is also used for browsing and publishing
			// Jinhua Xi 08/21/2006
			// IPreferenceStore store = AuthoringUIPlugin.getDefault()
			// .getPreferenceStore();
			PreferenceUtil.validatePreferences();
			IPreferenceStoreWrapper store = getPreferenceStore();
			
			List pages = new ArrayList();

			// WBS tab
			WBSTab = new ProcessBreakdownStructureFormPage(this, WBS_FORM_ID,
					AuthoringUIResources.ProcessEditor_WorkBreakdownStructure); 
			ColumnDescriptor[] columnDescriptors = toColumnDescriptors(store
					.getString(ApplicationPreferenceConstants.PREF_WBS_COLUMNS));
			adapterFactory = TngAdapterFactory.INSTANCE
					.getWBS_ComposedAdapterFactory();
			currentConfig = LibraryService.getInstance()
					.getCurrentMethodConfiguration();
			ProcessAuthoringConfigurator.INSTANCE
					.setMethodConfiguration(currentConfig);
			if (selectedProcess != null && selectedProcess.getDefaultContext() == null) {	
				ProcessScopeUtil.getInstance().loadScope(selectedProcess);
			}
			
			Scope scope = ProcessScopeUtil.getInstance().getScope(selectedProcess);
			if (scope != null) {
				ProcessScopeUtil.getInstance().beginProcessEdit(scope);
			}
			if (IRealizationManager.debug) {
				if (selectedProcess == null) {
					Thread.dumpStack();
				}
			}
			
			if (ProcessUtil.isSynFree()) {
				updateProcessModel();
			}
			
			if (adapterFactory instanceof ConfigurableComposedAdapterFactory) {
				((ConfigurableComposedAdapterFactory) adapterFactory)
						.setFilter(getConfiguratorInstance());
			}
			WBSTab.setAdapterFactory(adapterFactory);
			WBSTab.setColumnDescriptors(columnDescriptors);
			int id = addPage(WBSTab.createControl(getContainer()));
			setPageText(id, WBSTab.getTitle());
			WBSTab.setTabIndex(id);
			StructuredViewer viewer = (StructuredViewer) WBSTab.getViewer();
			createContextMenuFor(viewer);
			pages.add(WBSTab);
			// viewer.addDropSupport(dndOperations, transfers, new
			// ProcessEditorDropAdapter(editingDomain, viewer));
			// viewer.addDoubleClickListener(new IDoubleClickListener()
			// {
			// public void doubleClick(DoubleClickEvent event)
			// {
			// UIActionDispatcher.getInstance().handleDoubleClickEvent(event);
			// // System.out.println("double clicked ...");
			// //
			// // StructuredSelection selection = (StructuredSelection)
			// event.getSelection();
			// // System.out.println("sel"+selection.getFirstElement());
			// //
			// ProductEditorView.getView().getProductViewer().setInput(selection.getFirstElement());
			// }
			// });
			// addPage(WBSTab);

			// OBS tab
			OBSTab = new ProcessBreakdownStructureFormPage(this, TA_FORM_ID,
					AuthoringUIResources.ProcessEditor_TeamAllocation); 
			columnDescriptors = toColumnDescriptors(store
					.getString(ApplicationPreferenceConstants.PREF_TBS_COLUMNS));
			// setColumnIndexToNameMap(TngAdapterFactory.INSTANCE.getOBS_AdapterFactories(),
			// columnDescriptors);
			adapterFactory = TngAdapterFactory.INSTANCE
					.getOBS_ComposedAdapterFactory();
			if (adapterFactory instanceof ConfigurableComposedAdapterFactory) {
				((ConfigurableComposedAdapterFactory) adapterFactory)
						.setFilter(getConfiguratorInstance());
			}
			OBSTab.setAdapterFactory(adapterFactory);
			OBSTab.setColumnDescriptors(columnDescriptors);
			id = addPage(OBSTab.createControl(getContainer()));
			setPageText(id, OBSTab.getTitle());
			OBSTab.setTabIndex(id);
			viewer = (StructuredViewer) OBSTab.getViewer();
			createContextMenuFor(viewer);
			pages.add(OBSTab);
			// viewer.addDropSupport(dndOperations, transfers, new
			// ProcessEditorDropAdapter(editingDomain, viewer));
			// addPage(OBSTab);

			// PBS tab
			PBSTab = new ProcessBreakdownStructureFormPage(this, WPBS_FORM_ID,
					AuthoringUIResources.ProcessEditor_WorkProductUsage); 
			columnDescriptors = toColumnDescriptors(store
					.getString(ApplicationPreferenceConstants.PREF_WPBS_COLUMNS));
			// setColumnIndexToNameMap(TngAdapterFactory.INSTANCE.getPBS_AdapterFactories(),
			// columnDescriptors);
			adapterFactory = TngAdapterFactory.INSTANCE
					.getPBS_ComposedAdapterFactory();
			if (adapterFactory instanceof ConfigurableComposedAdapterFactory) {
				((ConfigurableComposedAdapterFactory) adapterFactory)
						.setFilter(getConfiguratorInstance());
			}
			PBSTab.setAdapterFactory(adapterFactory);
			PBSTab.setColumnDescriptors(columnDescriptors);
			id = addPage(PBSTab.createControl(getContainer()));
			setPageText(id, PBSTab.getTitle());
			PBSTab.setTabIndex(id);
			viewer = (StructuredViewer) PBSTab.getViewer();
			createContextMenuFor(viewer);
			pages.add(PBSTab);
			// viewer.addDropSupport(dndOperations, transfers, new
			// ProcessEditorDropAdapter(editingDomain, viewer));
			// addPage(PBSTab);

			// consolidated tab
			procTab = new ProcessBreakdownStructureFormPage(this,
					CONSOLIDATED_FORM_ID,
					AuthoringUIResources.ProcessEditor_ConsolidatedView); 
			procTab.setReadOnly(true);
			adapterFactory = TngAdapterFactory.INSTANCE
					.getProcessComposedAdapterFactory();
			if (adapterFactory instanceof ConfigurableComposedAdapterFactory) {
				((ConfigurableComposedAdapterFactory) adapterFactory)
						.setFilter(getConfiguratorInstance());
			}
			procTab.setAdapterFactory(adapterFactory);
//			columnDescriptors = toColumnDescriptors(store
//					.getString(ApplicationPreferenceConstants.PREF_WBS_COLUMNS));
//			
			// get WBS columns
			List list = toColumnDescriptorList(store
					.getString(ApplicationPreferenceConstants.PREF_WBS_COLUMNS));

			// get TBS columns 
			ColumnDescriptor[] teamColumnDescriptors = toColumnDescriptors(store
					.getString(ApplicationPreferenceConstants.PREF_TBS_COLUMNS));

			// if TBS columns has TEAM column then add to consolidated view
			for (int i = 0; i < teamColumnDescriptors.length; i++) {
				ColumnDescriptor colDesc = teamColumnDescriptors[i];
				if (colDesc == COL_DESC_TEAM) {
					list.add(colDesc);
					break;
				}
			}

			ColumnDescriptor[] columns = new ColumnDescriptor[list.size()];
			list.toArray(columns);
			procTab.setColumnDescriptors(columns);
			
			id = addPage(procTab.createControl(getContainer()));
			setPageText(id, procTab.getTitle());
			procTab.setTabIndex(id);
			viewer = (StructuredViewer) procTab.getViewer();
			createContextMenuFor(viewer);
			pages.add(procTab);

			
			// TODO: properly implement extension point
			// this extension point is supposed to let extensions modify the 
			// list of pages.  But this editor is so messy that we can't
			// just put the pages in a map to be passed to extensions
			// (like in the MethodElementEditor).
			
			// check for extenstion point and add the page if there
			Map<Object,String> pageMap = new LinkedHashMap<Object,String>();
			List<IMethodElementEditorPageProviderExtension> pageProviders = getAllPageProviders();
			if (pageProviders != null && pageProviders.size() > 0) {
				for (IMethodElementEditorPageProviderExtension extension : pageProviders) {
					pageMap = extension.getPages(pageMap, this, selectedProcess);
				}
			}

			if (!pageMap.isEmpty()) {
				extensionTabs = new ArrayList<ProcessBreakdownStructureFormPage>();
				for (Map.Entry<Object, String> pageEntry : pageMap.entrySet()) {
					Object page = pageEntry.getKey();
					String name = pageEntry.getValue();
					int index = -1;
					if (page instanceof Control) {
						index = addPage((Control)page);
					} else if (page instanceof IFormPage) {
						if (page instanceof ProcessBreakdownStructureFormPage) {
							ProcessBreakdownStructureFormPage extendedPage = (ProcessBreakdownStructureFormPage) page;
							extensionTabs.add(extendedPage);
							index = addPage(extendedPage
									.createControl(getContainer()));
							setPageText(index, extendedPage.getTitle());
							extendedPage.setTabIndex(index);
							viewer = (StructuredViewer) extendedPage
									.getViewer();
							createContextMenuFor(viewer);
	
							pages.add(extendedPage);
						}
					} else if (page instanceof IEditorPart) {
						index = addPage((IEditorPart)page, getEditorInput());
					}
					if (name != null) {
						setPageText(index, name);
					}
				}
			}
			
			bsPages = new ProcessBreakdownStructureFormPage[pages.size()];
			for (int i = 0; i < pages.size(); i++) {
				bsPages[i] = (ProcessBreakdownStructureFormPage) pages.get(i);
			}

			// bsPages = new ProcessBreakdownStructureFormPage[] { WBSTab,
			// OBSTab,
			// PBSTab, procTab };

			// if(getEditorInput() instanceof ProcessEditorInput) {
			// int pageId =
			// ((ProcessEditorInput)getEditorInput()).getActivePage();
			// if(pageId != -1) {
			// setActivePage(0);
			// setActivePage(pageId);
			// }
			// }

			inputChanged(getEditorInput());
		} catch (PartInitException e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(e);
			e.printStackTrace();
		}
	}

	public void updateConfigFreeProcessModelAndRefresh() {
		updateProcessModel();
		refreshAll();
	}
	
	private void updateProcessModel() {
		if (! ProcessUtil.isSynFree()) {
			return;
		}
		Process proc = getSelectedProcess();
		IRealizationManager mgr = getConfiguratorInstance()
				.getRealizationManager();
		if (mgr == null) {
			mgr = LibraryEditUtil.getInstance().getRealizationManager(proc.getDefaultContext());
		}
		if (proc != null && mgr != null) {
			if (ProcessScopeUtil.getInstance().isConfigFree(proc)) {
				ConfigurationHelper.getDelegate().setLoadForBrowsingNeeded(true);
			}
			mgr.updateProcessModel(proc);
		}
	}

	protected void setActivePage(int pageIndex) {
		super.setActivePage(pageIndex);

		// work around for active page that is not a form page. Super method
		// does not call pageChange() in this case
		//
		handlePageChange();
		if (pageIndex > 0 && bsPages.length <= pageIndex) {
			Viewer viewer = bsPages[pageIndex].getViewer();
			ISelection selection = viewer.getSelection();
			if (selection == null || selection.isEmpty()) {
				ISelection initialSelection = null;
				if (getEditorInput() instanceof ProcessEditorInput) {
					initialSelection = ((ProcessEditorInput) getEditorInput())
							.getInitialSelection();
				}
				if (initialSelection == null) {
					selection = new StructuredSelection(selectedProcess);
				} else {
					selection = initialSelection;
				}
				viewer.setSelection(selection, true);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormEditor#setActivePage(java.lang.String)
	 */
	public IFormPage setActivePage(String pageId) {
		int id = -1;
		IFormPage activePage = null;
		for (int i = 0; i < pages.size(); i++) {
			Object page = pages.get(i);
			if (page instanceof IFormPage) {
				IFormPage fpage = (IFormPage) page;
				if (fpage.getId().equals(pageId)) {
					id = i;
					activePage = fpage;
					break;
				}
			}
		}
		if(id == -1) {
			for (int i = 0; i < bsPages.length; i++) {
				ProcessBreakdownStructureFormPage page = bsPages[i];
				if (page.getId().equals(pageId)) {
					id = page.getTabIndex();
					activePage = page;
					break;
				}
			}
		}
		if(id == -1 && extensionTabs != null) {
			for (ProcessBreakdownStructureFormPage extPage : extensionTabs) {
				extPage.setProcess(selectedProcess);
				if (extPage.getId().equals(pageId)) {
					id = extPage.getTabIndex();
					activePage = extPage;
					break;
				}
			}
		}

		if(activePage != null) {
			setActivePage(id);
			return activePage;
		}
		return null;
	}
	
	protected void handlePageChange() {
		int id = getActivePage();
		if (id != -1) {
			Object page = pages.get(id);
			if (page instanceof ProcessDescription) {
				((ProcessDescription) page).setFormText();
			}
			if (WBSTab != null && id == WBSTab.getTabIndex()) {
				setCurrentViewer(WBSTab.getViewer());
			} else if (OBSTab != null && id == OBSTab.getTabIndex()) {
				setCurrentViewer(OBSTab.getViewer());
			} else if (PBSTab != null && id == PBSTab.getTabIndex()) {
				setCurrentViewer(PBSTab.getViewer());
			} else if (procTab != null && id == procTab.getTabIndex()) {
				setCurrentViewer(procTab.getViewer());
			} else if (extensionTabs != null) {
				for (ProcessBreakdownStructureFormPage extPage : extensionTabs) {
					if (id == extPage.getTabIndex())
						setCurrentViewer(extPage.getViewer());
				}
			} else {
				setCurrentViewer(null);
			}

			if (propertySheetPage != null) {
				propertySheetPage.refresh();
			}
		}
	}

	protected void pageChange(int newPageIndex) {
		super.pageChange(newPageIndex);
		handlePageChange();
	}

	/**
	 * Create context menu for the give viewer
	 * @param viewer
	 */
	public void createContextMenuFor(final StructuredViewer viewer) {
		MenuManager contextMenu = new MenuManager("#PopUp"); //$NON-NLS-1$
		contextMenu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		contextMenu.setRemoveAllWhenShown(true);
		contextMenu.addMenuListener(this);
		Menu menu = contextMenu.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(contextMenu, viewer);

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection sel = (IStructuredSelection) event
						.getSelection();
				setSelection(sel);
			}
		});

		int dndOperations = DND.DROP_LINK | DND.DROP_COPY | DND.DROP_MOVE;
		Transfer[] transfers = new Transfer[] { LocalTransfer.getInstance() };
		viewer.addDropSupport(dndOperations, transfers,
				new ProcessEditorDropAdapter(editingDomain, viewer));
		
		dndOperations = DND.DROP_MOVE | DND.DROP_COPY;
		transfers = new Transfer[] { LocalTransfer.getInstance() };
		viewer.addDragSupport(dndOperations, transfers, new ViewerDragAdapter(viewer));

	}

	protected void setCurrentViewer(Viewer viewer) {
		// If it is changing...
		//
		if (currentViewer != viewer) {
			if (selectionChangedListener == null) {
				// Create the listener on demand.
				//
				selectionChangedListener = new ISelectionChangedListener() {
					// This just notifies those things that are affected by the
					// section.
					//
					public void selectionChanged(
							SelectionChangedEvent selectionChangedEvent) {
						setSelection(selectionChangedEvent.getSelection());
					}
				};
			}

			// Stop listening to the old one.
			//
			if (currentViewer != null) {
				currentViewer
						.removeSelectionChangedListener(selectionChangedListener);
			}

			// Start listening to the new one.
			//
			if (viewer != null) {
				viewer.addSelectionChangedListener(selectionChangedListener);
			}

			// Remember it.
			//
			currentViewer = viewer;

			// Set adapter factory of editingDomain to that of the current view
			//
			if (currentViewer instanceof ProcessViewer) {
				IContentProvider contentProvider = ((ProcessViewer) currentViewer)
						.getContentProvider();
				if (contentProvider instanceof AdapterFactoryContentProvider) {
					AdapterFactoryContentProvider adapterFactoryContentProvider = ((AdapterFactoryContentProvider) contentProvider);
					AdapterFactory adapterFactory = adapterFactoryContentProvider
							.getAdapterFactory();
					if (adapterFactory instanceof EditingDomainComposedAdapterFactory) {
						((EditingDomainComposedAdapterFactory) adapterFactory)
								.setEditingDomain(editingDomain);
					} else {
						editingDomain.setAdapterFactory(adapterFactory);
					}

					if (propContentProvider != null) {
						propContentProvider.setAdapterFactory(adapterFactory);
					}
				}
			}

			// Set the editors selection based on the current viewer's
			// selection.
			//
			setSelection(currentViewer == null ? StructuredSelection.EMPTY
					: currentViewer.getSelection());

		}
	}

	public static Activity getParentActivity(BreakdownElement e,
			AdapterFactory adapterFactory) {
		Activity act = UmaUtil.getParentActivity(e);
		if (act != null) {
			return act;
		}

		// TODO: handle element that does not directly belong to an activity
		// (like deliverable part of a deliverable descriptor)
		//

		return null;
	}

	public static AdapterFactory getAdapterFactory(StructuredViewer viewer) {
		return ((AdapterFactoryContentProvider) viewer.getContentProvider())
				.getAdapterFactory();
	}

	public static void setSelectionToViewer(Viewer viewer,
			IStructuredSelection otherSel, boolean alternateSelection) {
		if (alternateSelection) {
			IStructuredSelection sel = (IStructuredSelection) viewer
					.getSelection();
			Object currentSelection = null;
			if (sel.size() == 1) {
				currentSelection = sel.getFirstElement();
				if (currentSelection instanceof BreakdownElement) {
					Activity act = getParentActivity(
							(BreakdownElement) currentSelection,
							getAdapterFactory((StructuredViewer) viewer));
					if (act != otherSel.getFirstElement()) {
						viewer.setSelection(otherSel, true);
					}
				}
			} else {
				viewer.setSelection(otherSel, true);
			}
		} else {
			viewer.setSelection(otherSel, true);
		}
	}

	/**
	 * @see org.eclipse.jface.viewers.ISelectionProvider#setSelection(org.eclipse.jface.viewers.ISelection)
	 */
	public void setSelection(ISelection selection) {
		if (synchronizingSelection) {
			return;
		}

		// // check if the resources of selected objects are out of synch or any
		// of the selected objects
		// // became proxy then refresh them if needed
		// //
		// if(refreshOutOfSynch(selection)) {
		// return;
		// }

		viewSelection = selection;
		ArrayList listenerList = new ArrayList(selectionChangedListeners);

		for (int i = 0; i < listenerList.size(); i++) {
			ISelectionChangedListener listener = (ISelectionChangedListener) listenerList
					.get(i);
			listener
					.selectionChanged(new SelectionChangedEvent(this, selection));
		}

		// synchronize selection in all BS views if only one element is selected
		//
		synchronizeSelection(selection, currentViewer);
	}

	/**
	 * @param selection
	 * @return
	 */
	protected boolean refreshOutOfSynch(ISelection selection) {
		if (currentViewer != null && selection instanceof IStructuredSelection) {
			boolean refreshRequired = false;
			IStructuredSelection sel = (IStructuredSelection) selection;
			HashSet<Resource> resources = new HashSet<Resource>();
			for (Iterator iter = sel.iterator(); iter.hasNext();) {
				Object e = TngUtil.unwrap(iter.next());
				if (e instanceof EObject) {
					EObject eObject = ((EObject) e);
					if (eObject.eIsProxy()) {
						eObject = RefreshJob.getInstance().resolve(eObject);
						refreshRequired = true;
					}
					Resource resource = eObject.eResource();
					if (resource != null) {
						resources.add(resource);
					}
				}
			}
			if (!resources.isEmpty()) {
				ArrayList<Resource> removedResources = new ArrayList<Resource>();
				ArrayList<Resource> changedResources = new ArrayList<Resource>();
				ResourceUtil.checkOutOfSynchResources(resources,
						removedResources, changedResources);
				if (!removedResources.isEmpty() || !changedResources.isEmpty()) {
					boolean ret = promptReloadFiles();
					if (ret) {
						// unload the removed resources
						//
						if (!removedResources.isEmpty()) {
							PersistenceUtil.unload(removedResources);
						}

						Collection reloadedResources = null;
						if (!changedResources.isEmpty()) {
							// Reload the changed resources.
							ILibraryManager manager = (ILibraryManager) LibraryService
									.getInstance().getCurrentLibraryManager();
							if (manager != null) {
								reloadedResources = manager
										.reloadResources(changedResources);
							}
						}

						if (!removedResources.isEmpty()
								|| reloadedResources != null) {
							refreshRequired = true;
						}

						// refresh out-of-synch resources
						//
						for (Iterator iter = changedResources.iterator(); iter
								.hasNext();) {
							FileManager.getInstance().refresh(
									(Resource) iter.next());
						}
					}
				}
			}
			if (refreshRequired) {
				currentViewer.refresh();
				return true;
			}
		}

		return false;
	}

	/**
	 * @param selection
	 */
	protected void synchronizeSelection(ISelection selection, Viewer currentViewer) {
		try {
			synchronizingSelection = true;
			if (currentViewer != null
					&& selection instanceof IStructuredSelection) {
				IStructuredSelection sel = (IStructuredSelection) selection;
				if (sel.size() == 1) {
					Object selected = sel.getFirstElement();
					IStructuredSelection otherSel = null;
					boolean alternateSelection = false;
					ArrayList selectedPath = null;
					AdapterFactory adapterFactory = getAdapterFactory((StructuredViewer) currentViewer);
					if (selected instanceof BreakdownElementWrapperItemProvider) {
						if (((BreakdownElementWrapperItemProvider) selected)
								.isReadOnly()) {
							if (adapterFactory != null) {
								// get path
								//
								Object e = TngUtil.unwrap(selected);
								selectedPath = new ArrayList();
								ITreeItemContentProvider adapter = (ITreeItemContentProvider) selected;
								do {
									selectedPath.add(0, e);
									Object parent = adapter.getParent(e);
									if (parent == null) {
										break;
									}
									if (parent instanceof BreakdownElementWrapperItemProvider) {
										adapter = (ITreeItemContentProvider) parent;
										e = TngUtil.unwrap(parent);
									} else {
										adapter = (ITreeItemContentProvider) adapterFactory
												.adapt(
														parent,
														ITreeItemContentProvider.class);
										e = parent;
									}
								} while (true);
							}
						} else if (procTab.getViewer() == currentViewer) {
							selected = TngUtil.unwrap(selected);
							selection = new StructuredSelection(selected);
						} else if (extensionTabs != null) {
							for (ProcessBreakdownStructureFormPage extPage : extensionTabs) {
								if (extPage.getViewer() == currentViewer) {
									selected = TngUtil.unwrap(selected);
									selection = new StructuredSelection(
											selected);							
								}
							}
						}
					}
					if (selected instanceof BreakdownElement) {
						if (procTab.getViewer() != currentViewer) {
							procTab.getViewer().setSelection(selection, false);
						}
						if (extensionTabs != null) {
							for (ProcessBreakdownStructureFormPage extPage : extensionTabs) {
								if (extPage.getViewer() != currentViewer)
									extPage.getViewer().setSelection(selection, false);
							}
						}

						if (selected instanceof Activity
								|| selected instanceof Milestone) {
							otherSel = (IStructuredSelection) selection;
						} else {
							Activity act = getParentActivity(
									(BreakdownElement) selected, adapterFactory);
							if (act != null) {
								otherSel = new StructuredSelection(act);
								alternateSelection = true;
							}
						}
					}
					if (otherSel != null) {
						// own element is selected
						//
						Viewer viewer = null;

						if ( WBSTab != null ) {
							viewer = WBSTab.getViewer();
						}
						
						if (viewer != null && viewer != currentViewer) {
							if (selected instanceof WorkBreakdownElement) {
								viewer.setSelection(selection, false);
							} else {
								setSelectionToViewer(viewer, otherSel,
										alternateSelection);
							}
						}
						
						if ( OBSTab != null ) {
							viewer = OBSTab.getViewer();
						}
						
						if (viewer != null && viewer != currentViewer) {
							if (selected instanceof RoleDescriptor) {
								viewer.setSelection(selection, false);
							} else {
								setSelectionToViewer(viewer, otherSel,
										alternateSelection);
							}
						}
						
						if ( PBSTab != null ) {
							viewer = PBSTab.getViewer();
						}
						
						if (viewer != null && viewer != currentViewer) {
							if (selected instanceof WorkProductDescriptor) {
								viewer.setSelection(selection, false);
							} else {
								setSelectionToViewer(viewer, otherSel,
										alternateSelection);
							}
						}
					} else if (selectedPath != null) {
						// inherited element is selected
						//
						Viewer viewer = null;
						if ( WBSTab != null ) {
							viewer = WBSTab.getViewer();
							if (viewer != currentViewer) {
								selection = new StructuredSelection(findSelection(
										selectedPath, WBSTab.getAdapterFactory()));
								viewer.setSelection(selection, false);
							}
						}
												
						if ( OBSTab != null ) {
							viewer = OBSTab.getViewer();						
							if (viewer != currentViewer) {
								selection = new StructuredSelection(findSelection(
										selectedPath, OBSTab.getAdapterFactory()));
								viewer.setSelection(selection, false);
							}
						}
						
						if ( PBSTab != null ) {
							viewer = PBSTab.getViewer();						
							if (viewer != currentViewer) {
								selection = new StructuredSelection(findSelection(
										selectedPath, PBSTab.getAdapterFactory()));
								viewer.setSelection(selection, false);
							}
						}
						
						if ( procTab != null ) {
							viewer = procTab.getViewer();
							if (viewer != currentViewer) {
								selection = new StructuredSelection(findSelection(
										selectedPath, procTab.getAdapterFactory()));
								viewer.setSelection(selection, false);
							}
						}
						
						if (extensionTabs != null) {
							for (ProcessBreakdownStructureFormPage extPage : extensionTabs) {
								viewer = extPage.getViewer();
								if (viewer != currentViewer) {
									selection = new StructuredSelection(
											findSelection(
													selectedPath,
													extPage
															.getAdapterFactory()));
									viewer.setSelection(selection, false);
								}
								
							}
						}
					}
				}
			}
		} finally {
			synchronizingSelection = false;
		}
	}

	public static Object findSelection(List selectedPath,
			AdapterFactory adapterFactory) {
		int size = selectedPath.size();
		if (size == 0) {
			return null;
		}
		Object e = selectedPath.get(0);
		Object selection = e;
		ITreeItemContentProvider adapter = (ITreeItemContentProvider) adapterFactory
				.adapt(e, ITreeItemContentProvider.class);
		Collection children = adapter.getChildren(e);
		find_selection: for (int i = 1; i < size; i++) {
			e = selectedPath.get(i);
			for (Iterator iter = children.iterator(); iter.hasNext();) {
				Object child = iter.next();
				Object element = TngUtil.unwrap(child);
				if (element == e) {
					selection = child;
					adapter = (ITreeItemContentProvider) adapterFactory.adapt(
							child, ITreeItemContentProvider.class);
					children = adapter.getChildren(e);
					continue find_selection;
				}
			}
			break find_selection;
		}
		return selection;
	}

	// Implements ISelectionProvider
	//
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.add(listener);
	}

	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		selectionChangedListeners.remove(listener);
	}

	/**
	 * @see org.eclipse.jface.viewers.ISelectionProvider#getSelection()
	 */
	public ISelection getSelection() {
		return viewSelection;
	}

	/**
	 * @see org.eclipse.emf.edit.domain.IEditingDomainProvider#getEditingDomain()
	 */
	public EditingDomain getEditingDomain() {
		return editingDomain;
	}

	/**
	 * @see org.eclipse.emf.common.ui.viewer.IViewerProvider#getViewer()
	 */
	public Viewer getViewer() {
		return currentViewer;
	}

	/**
	 * Gets the adapter factory of the current page if there is any
	 * 
	 * @return
	 * 		Adapter Factory of the current page of the editor
	 */
	public AdapterFactory getAdapterFactory() {
		IFormPage page = getActivePageInstance();
		if (page instanceof ProcessBreakdownStructureFormPage) {
			return ((ProcessBreakdownStructureFormPage) page)
					.getAdapterFactory();
		}

		return null;
	}

	/**
	 * @see org.eclipse.jface.action.IMenuListener#menuAboutToShow(org.eclipse.jface.action.IMenuManager)
	 */
	public void menuAboutToShow(IMenuManager menuManager) {
		((IMenuListener) getEditorSite().getActionBarContributor())
				.menuAboutToShow(menuManager);
		
		// populate contributed menu items
		//
		IFormPage activePage = getActivePageInstance();
		if(activePage instanceof ProcessBreakdownStructureFormPage) {
			IAction[] actions = ((ProcessBreakdownStructureFormPage)activePage).getAdditionalActions();
			if(actions != null && actions.length > 0) {
				menuManager.insertAfter(IWorkbenchActionConstants.MB_ADDITIONS, new Separator());
				for (int i = 0; i < actions.length; i++) {
					IAction action = actions[i];
					ISelection selection = getSelection();
					if(selection instanceof IStructuredSelection && action instanceof BaseSelectionListenerAction) {
						((BaseSelectionListenerAction)action).selectionChanged((IStructuredSelection) selection);
					}
					if (action instanceof IWorkbenchPartAction) {
						((IWorkbenchPartAction) action)
								.setActiveWorkbenchPart(this);
					}
					menuManager.insertAfter(IWorkbenchActionConstants.MB_ADDITIONS, action);
				}
			}
		}
	}

	/**
	 * Return property sheet page
	 * @return
	 * 			PropertySheet Page
	 */
	public IPropertySheetPage getPropertySheetPage() {
		if (propertySheetPage == null) {
			propertySheetPage = (EPFPropertySheetPage) createPropertySheetPage();

			// {
			// public void makeContributions(IMenuManager menuManager,
			// IToolBarManager toolBarManager, IStatusLineManager
			// statusLineManager) {
			// super.makeContributions(menuManager, toolBarManager,
			// statusLineManager);
			// }
			//
			// public void setActionBars(IActionBars actionBars) {
			// super.setActionBars(actionBars);
			// getActionBarContributor().shareGlobalActions(this, actionBars);
			// }
			// };
			// System.out.println("Setting provider ");
			if (currentViewer != null) {
				AdapterFactoryContentProvider contentProvider = (AdapterFactoryContentProvider) ((ProcessViewer) currentViewer)
						.getContentProvider();
				propContentProvider = new AdapterFactoryContentProvider(
						contentProvider.getAdapterFactory());
				propertySheetPage
						.setPropertySourceProvider(propContentProvider);
			}
		}
		return propertySheetPage;
	}

    /**
     * 
     */
    protected TabbedPropertySheetPage createPropertySheetPage() {
        return new EPFPropertySheetPage(this);
    }

	/**
	 * @see org.eclipse.ui.part.MultiPageEditorPart#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class key) {
		if (key.equals(IPropertySheetPage.class)) {
			return getPropertySheetPage();
		} else if (key.equals(IGotoMarker.class)) {
			return this;
		} else {
			return super.getAdapter(key);
		}
	}


	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor#getContributorId()
	 */
	public String getContributorId() {
		return getSite().getId();
	}

	
	/**
	 * Return column descriptor list for given string
	 * @param str
	 * @return
	 * 			list of column descriptors
	 */
	public static EList toColumnDescriptorList(String str) {
		EList columnDescriptors = new BasicEList();
		StringTokenizer tokens = new StringTokenizer(str, ","); //$NON-NLS-1$
		while (tokens.hasMoreTokens()) {
			String token = tokens.nextToken();
			Object columnDescriptor = ProcessEditor.idToColumnDescriptorMap
					.get(token);
			if (columnDescriptor != null) {
				columnDescriptors.add(columnDescriptor);
			}
		}
		return columnDescriptors;
	}

	/**
	 * Return array of column descriptors for the given string
	 * @param str
	 * @return
	 * 			list of column descriptors
	 */
	public static ColumnDescriptor[] toColumnDescriptors(String str) {
		List list = toColumnDescriptorList(str);
		ColumnDescriptor[] columns = new ColumnDescriptor[list.size()];
		list.toArray(columns);
		return columns;
	}

	/**
	 * @see org.eclipse.ui.forms.editor.FormEditor#getCurrentPage()
	 */
	public int getCurrentPage() {
		return super.getCurrentPage();
	}

	/**
	 * Return suppression info
	 */
	public Suppression getSuppression() {
		return suppression;
	}

	protected void configChanged() {
		MethodConfiguration config = LibraryService.getInstance()
				.getCurrentMethodConfiguration();
		if (config != currentConfig) {
			if (! isEditingConfigFreeProcess()) {
				updateProcessModel();
			}
			
			// refresh only if the active part is this editor or diagram editor
			// of any activity in this process
			//
			IWorkbenchPart activePart = getSite().getWorkbenchWindow()
					.getPartService().getActivePart();
			boolean refresh = activePart == this;
			if (!refresh) {
				if (activePart instanceof AbstractDiagramEditor) {
					DiagramEditorInput input = ((DiagramEditorInputProxy)((IEditorPart) activePart)
							.getEditorInput()).getDiagramEditorInput();
					refresh = input.getSuppression().getProcess() == selectedProcess;
				}
			}
			if (refresh) {
				currentConfig = config;
				refreshAll();
			}
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.editors.MethodElementEditor#setInput(org.eclipse.ui.IEditorInput)
	 */
	protected void setInput(IEditorInput input) {
		if (input != getEditorInput()) {
			inputChanged = true;
		}
		super.setInput(input);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.editors.MethodElementEditor#updatePages()
	 */
	protected void updatePages() {
		// remove and add description page if the model.xmi or content.xmi of
		// the process have been changed
		//
		Resource modelResource = selectedProcess.eResource();
		Resource contentResource = selectedProcess.getPresentation()
				.eResource();
		if (inputChanged || changedResources.contains(modelResource)
				|| (contentResource != null && changedResources
						.contains(contentResource))) {
			removePage(0);
			try {
				addDescriptionPage();
			} catch (PartInitException e) {
				AuthoringUIPlugin.getDefault().getMsgDialog().displayError(
						AuthoringUIResources.ProcessEditor_refreshErrorTitle, 
						AuthoringUIResources.ProcessEditor_refreshErrorMsg, e); 
			}
		}
		if (inputChanged) {
			inputChanged(getEditorInput());
			if (propertySheetPage != null) {
				propertySheetPage.refresh();
			}
		} else {
			refreshAll();
		}
	}

	protected void refreshAll() {
		BusyIndicator.showWhile(getSite().getShell().getDisplay(),
				new Runnable() {

					public void run() {
						doRefreshAll(true);
					}

				});
	}

	/**
	 * Refresh all viewers 
	 * @param updateIDs
	 * 			if true, then refresh all index in viewers
	 */
	public void doRefreshAll(boolean updateIDs) {
//		if (updateIDs) {
//			AdapterFactory adapterFactory = TngAdapterFactory.INSTANCE
//					.getWBS_ComposedAdapterFactory();
//			ProcessUtil.updateIDs(adapterFactory, selectedProcess);
//			ProcessUtil.refreshPredeccessorLists(adapterFactory,
//					selectedProcess);
//
//			adapterFactory = TngAdapterFactory.INSTANCE
//					.getProcessComposedAdapterFactory();
//			ProcessUtil.updateIDs(adapterFactory, selectedProcess);
//		}

		updateIDs(updateIDs);
		
        if (WBSTab != null && WBSTab.getViewer() != null) {
            WBSTab.getViewer().refresh();
        }
        
        if (OBSTab != null && OBSTab.getViewer() != null) {
        	OBSTab.getViewer().refresh();
        }
        if (PBSTab != null && PBSTab.getViewer() != null) {
        	PBSTab.getViewer().refresh();
        }
        if (procTab != null && procTab.getViewer() != null) {
        	procTab.getViewer().refresh();
        }

		
		if (extensionTabs != null) {
			for (ProcessBreakdownStructureFormPage extPage : extensionTabs) {
				extPage.getViewer().refresh();
			}
		}

		if (propertySheetPage != null) {
			propertySheetPage.refresh();
		}
	}
	
	
    /**
     * @param updateIDs
     */
    protected void updateIDs(boolean updateIDs) {
        if (updateIDs) {
            AdapterFactory adapterFactory = null;
            if (WBSTab != null) {
                adapterFactory = TngAdapterFactory.INSTANCE.getWBS_ComposedAdapterFactory();
                ProcessUtil.updateIDs(adapterFactory, selectedProcess);
                ProcessUtil.refreshPredeccessorLists(adapterFactory, selectedProcess);
            }
            if (procTab != null) {
                adapterFactory = TngAdapterFactory.INSTANCE.getProcessComposedAdapterFactory();
                ProcessUtil.updateIDs(adapterFactory, selectedProcess);
            }
        }
    }


	/**
	 * Refresh all open process editors
	 */
	public void refreshAllProcessEditors() {
		IEditorReference[] editorReferences = getSite().getPage()
				.getEditorReferences();
		for (int i = 0; i < editorReferences.length; i++) {
			IEditorReference reference = editorReferences[i];
			IEditorPart editor = reference.getEditor(true);
			if (editor instanceof ProcessEditor) {
				((ProcessEditor) editor).doRefreshAll(false);
			}
		}
	}

	/**
	 * @see org.eclipse.ui.forms.editor.FormEditor#getActivePageInstance()
	 */
	public IFormPage getActivePageInstance() {
		int index = getActivePage();
		if (index == -1) {
			return null;
		}
		if (index == 0) {
			return super.getActivePageInstance();
		}
		return bsPages[index - 1];
	}

	protected Collection getModifiedResources() {
		Collection modifiedResources = super.getModifiedResources();
		if (suppression.isSaveNeeded()
				&& !modifiedResources.contains(selectedProcessComponent
						.eResource())) {
			modifiedResources.add(selectedProcessComponent.eResource());
		}
		modifiedResources.addAll(resourcesToSave);

		return modifiedResources;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.editors.MethodElementEditor#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void doSave(IProgressMonitor monitor) {
		suppression.saveToModel();
//		Scope scope = ProcessScopeUtil.getInstance().getScope(selectedProcess);
//		try {
//			if (scope != null) {
//				selectedProcess.setDefaultContext(null);
//				selectedProcess.getValidContext().clear();
//			}
		super.doSave(monitor);
//		} finally {
//			if (scope != null) {
//				selectedProcess.setDefaultContext(scope);
//				selectedProcess.getValidContext().add(scope);
//			}
//		}
		suppression.saveIsDone();
		resourcesToSave.clear();
		firePropertyChange(PROP_DIRTY);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.editors.MethodElementEditor#isDirty()
	 */
	public boolean isDirty() {
		if (suppression.isSaveNeeded()) {
			return true;
		}
		return super.isDirty();
	}

	
	/**
	 * @see org.eclipse.ui.part.WorkbenchPart#firePropertyChange(int)
	 */
	public void firePropertyChange(int propertyId) {
		super.firePropertyChange(propertyId);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.editors.MethodElementEditor#getUsedResources()
	 */
	public Collection<Resource> getUsedResources() {
		HashSet<Resource> usedResources = new HashSet<Resource>();

		// model.xmi
		//
		Resource resource = elementObj.eResource();
		if (resource != null) {
			usedResources.add(resource);
		}

		// content.xmi
		//
		resource = selectedProcess.getPresentation().eResource();
		if (resource != null) {
			usedResources.add(resource);
		}

		// configuration.xmi
		//
		MethodConfiguration config = selectedProcess.getDefaultContext();
		if (config != null) {
			resource = config.eResource();
			if (resource != null) {
				usedResources.add(resource);
			}
		}

		// resources of other processes that this process is depending on
		//
		AdapterFactory adapterFactory = getAdapterFactoryFromTab();
		for (Iterator iter = new AdapterFactoryTreeIterator(adapterFactory,
				selectedProcess); iter.hasNext();) {
			Object obj = TngUtil.unwrap(iter.next());
			if (obj instanceof EObject) {
				resource = ((EObject) obj).eResource();
				if (resource != null) {
					usedResources.add(resource);
				}
			}
		}

		lastUsedResources = usedResources;

		return usedResources;
	}

	/**
     * @return
     */
    protected AdapterFactory getAdapterFactoryFromTab() {
        return WBSTab.getAdapterFactory();
    }

    
	/**
	 * Check whether selected objects can be revealed or not and retuns result
	 * 
	 * @param selection
	 * 			List of objects
	 * @param adapterFactory
	 * 			Adapter factory of the selected objects
	 * @param suppression
	 * 			Suppression information
	 * @return
	 * 		Boolean value to indicate whether selected objects can be revealed
	 */
	public static boolean canReveal(Collection<?> selection,
			AdapterFactory adapterFactory, Suppression suppression) {
		// NO more check for duplication of name and presentation name
		//
//		// check if revealing the selected elements will cause name duplication
//		//
//		String msg = suppression.checkDuplicateNameAfterReveal(selection,
//				adapterFactory);
//		if (msg != null) {
//			AuthoringUIPlugin.getDefault().getMsgDialog().displayError(
//					AuthoringUIResources.ProcessEditor_Reveal_Title, //$NON-NLS-1$ 
//					msg);
//			return false;
//		}
//
//		// check if revealing the selected elements will cause duplicate
//		// descriptors.
//		// If so, prompt user to delete the duplicates before continuing.
//		//
//		Collection duplicates = ProcessUtil
//				.getDuplicateDescriptorsAfterReveal(selection);
//		if (!duplicates.isEmpty()) {
//			MultiStatus status = new MultiStatus(AuthoringUIPlugin.getDefault()
//					.getId(), 0, "", null); //$NON-NLS-1$
//			for (Iterator iter = duplicates.iterator(); iter.hasNext();) {
//				Descriptor desc = (Descriptor) iter.next();
//				String descTxt = TngUtil.getTypeText(desc)
//						+ ": " + desc.getPresentationName(); //$NON-NLS-1$
//				status.add(new Status(IStatus.INFO, AuthoringUIPlugin
//						.getDefault().getId(), 0, descTxt, null));
//			}
//			if (AuthoringUIPlugin
//					.getDefault()
//					.getMsgDialog()
//					.displayConfirmation(
//							AuthoringUIResources.ProcessEditor_Reveal_Title, //$NON-NLS-1$ 
//							AuthoringUIResources.ProcessEditor_promptToDeleteBeforeReveal, //$NON-NLS-1$
//							status) == Dialog.CANCEL) {
//				return false;
//			}
//
//			// delete duplicate descriptors
//			try {
//				ProcessDeleteAction.delete(duplicates);
//			} catch (OperationCanceledException e) {
//				return false;
//			}
//		}

		return true;
	}

	public static boolean hasInherited(Collection selection) {
		for (Iterator iter = selection.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (ProcessUtil.isInherited(element)) {
				return true;
			}
		}
		return false;
	}

	protected IPreferenceStoreWrapper getPreferenceStore() {
		// preference is managed by library plugin,
		// since this preferences is also used for browsing and publishing
		// Jinhua Xi 08/21/2006
		// IPreferenceStore store = AuthoringUIPlugin.getDefault()
		// .getPreferenceStore();
		IPreferenceStoreWrapper store = LibraryPlugin.getDefault()
				.getPreferenceStore();

		return store;
	}
	
	/**
	 * 
	 * @return
	 */
	protected DiagramEditorHelper getDiagramEditorHelper(){
		if(diagramEditorHelper == null){
			diagramEditorHelper = new DiagramEditorHelper();
			diagramEditorHelper.getDiagramManager(selectedProcess);
		}
		return diagramEditorHelper;
	}
	
	/**
	 * Override without any code change just for {@link ProcessEditorActionBarContributor} to access it.
	 */
	@Override
	public int getActivePage() {
		return super.getActivePage();
	}
	
	protected ActionManager newActionManager() {

		final Collection<ActivityDropCommand> adCommands = new ArrayList<ActivityDropCommand>();

		ActionManager mgr = new MeEditorActionManager() {

			protected void registerExecutedCommand(Command command) {
				if (command instanceof ActivityDropCommand) {
					ActivityDropCommand adc = (ActivityDropCommand) command;
					if (adc.getResourceFileCopyHandler() != null) {
						adCommands.add(adc);
					}
				}
			}

			public void saveIsDone() {
				for (ActivityDropCommand adc : adCommands) {
					adc.scanAndCopyResources();
				}
				adCommands.clear();
				super.saveIsDone();
			}

			public void undo() {
				adCommands.clear();
				super.undo();
			}
			
			public boolean execute(IResourceAwareCommand cmd) {
				boolean ret = super.execute(cmd);
				if (ret) {				
					ProcessEditorUtil.deSelectSynchronize(cmd);
				}
				return ret;
			}
			
			public boolean doAction(int actionType, EObject object,
					org.eclipse.emf.ecore.EStructuralFeature feature,
					Object value, int index) {
				boolean ret = super.doAction(actionType, object, feature,
						value, index);

				if (ret) {
					if (object instanceof Descriptor) {
						ProcessEditorUtil.deSelectSynchonize((Descriptor) object, feature.getFeatureID());
					}
				}
				return ret;
			}
		};
		return mgr;
	}
	
	private ProcessAuthoringConfigurator scopeConfigurator;
	private ProcessAuthoringConfigurator getConfiguratorInstance() {
		if (selectedProcess != null
				&& selectedProcess.getDefaultContext() instanceof Scope) {
			if (scopeConfigurator == null) {
				Scope scope = (Scope) selectedProcess.getDefaultContext();
				scopeConfigurator = new ProcessAuthoringConfigurator(scope) {
					public IRealizationManager getRealizationManager() {
						return LibraryEditUtil.getInstance().getDefaultRealizationManager();
					}
				};
			}
			return scopeConfigurator;
		}
		return ProcessAuthoringConfigurator.INSTANCE;
	}
	
	private boolean isEditingConfigFreeProcess() {
		return getConfiguratorInstance() == scopeConfigurator;
	}
	
	public Process getSelectedProcess() {
		return selectedProcess;
	}
	
	private Set<MethodElement> changedElementSet;
	public synchronized void updateAndRefreshProcessModel() {
		if (changedElementSet == null) {
			return;
		}
		//System.out.println("LD> getSite().getPage().getActiveEditor(): " + getSite().getPage().getActiveEditor());
		
		if (getSite().getPage().getActiveEditor() != this) {
			return;
		}
		//System.out.println("LD> updateAndRefreshProcessModel: " + getSelectedProcess());
		
		Set<MethodElement> elementSet = changedElementSet;
		changedElementSet = null;
		
		Process proc = getSelectedProcess();
		IRealizationManager mgr = getConfiguratorInstance()
				.getRealizationManager();
		if (mgr == null) {
			mgr = LibraryEditUtil.getInstance().getRealizationManager(proc.getDefaultContext());
		}
		if (proc != null && mgr != null)
			mgr.elementUpdateProcessModel(proc, elementSet);
		
		refreshAll();
	}
	
	public void updateOnLinkedElementChange(BreakdownElement be) {
		if (! ProcessUtil.isSynFree()) {
			return;
		}
		if (changedElementSet == null) {
			changedElementSet = new HashSet<MethodElement>();
		}
		changedElementSet.add(be);
		
		updateAndRefreshProcessModel();
	}
	
}