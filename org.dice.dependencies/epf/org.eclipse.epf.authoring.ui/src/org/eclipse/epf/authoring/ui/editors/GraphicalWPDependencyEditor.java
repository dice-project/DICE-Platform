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
import java.util.List;

import org.eclipse.epf.authoring.gef.edit.DiagramEditPart;
import org.eclipse.epf.authoring.gef.edit.WPDiagramEditPartFactory;
import org.eclipse.epf.authoring.gef.util.DiagramUIResources;
import org.eclipse.epf.authoring.gef.util.TemplateConstants;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.diagram.model.Diagram;
import org.eclipse.epf.diagram.model.ModelFactory;
import org.eclipse.epf.diagram.model.WorkProductNode;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.PanningSelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;


/**
 * An editor for graphical work product depenedency diagram
 * @author Phong Nguyen Le
 * @author Shashidhar Kannoori
 * @since 1.0 
 */
public class GraphicalWPDependencyEditor extends AbstractDiagramEditor {

	private CreationFactory wpNodeCreationFactory;

//	private IPropertySheetEntry rootEntry;

	private static final String CREATE_WP_NODE = "create_workproduct_descriptor_node"; //$NON-NLS-1$

	/*
	 * Create an instance
	 */
	public GraphicalWPDependencyEditor() {
		super();
	}

	protected void initializeCreationFactories() {
		super.initializeCreationFactories();

		if (wpNodeCreationFactory == null) {
			wpNodeCreationFactory = new CreationFactory() {
				public Object getNewObject() {
					WorkProductNode node = ModelFactory.eINSTANCE
							.createWorkProductNode();
					Diagram diagram = (Diagram) editPart.getModel();
					node.setUMADiagram(diagram.getUMADiagram());
					WorkProductDescriptor wpd = UmaFactory.eINSTANCE
							.createWorkProductDescriptor();
					node.setDiagram(diagram);
					node.setObject(wpd);
					return node;
				}

				public Object getObjectType() {

					return TemplateConstants.WORK_PRODUCT;
				}
			};
		}
		CreationFactory creationFactory = wpNodeCreationFactory;
		templateNameToCreationFactoryMap.put(creationFactory.getObjectType(),
				creationFactory);

	}

	/**
	 * 
	 */
	protected void contributeToContextMenu(IMenuManager menu) {
		super.contributeToContextMenu(menu);

		boolean canModify = !isReadOnly();

		// IAction action = getActionRegistry().getAction(PROPERTIES_PAGE);
		// if(action.isEnabled()) {
		// menu.appendToGroup(GEFActionConstants.MB_ADDITIONS, action);
		// }
		IAction action = getActionRegistry().getAction(CREATE_WP_NODE);
		if (action.isEnabled() && canModify) {
			menu.appendToGroup(GEFActionConstants.MB_ADDITIONS, action);
		}
	}

	protected void createActions() {
		super.createActions();
		// ActionRegistry registry = getActionRegistry();
		// IAction action;
		//
		// action = new Action("Show &Properties View") {
		// public String getId() {
		// return PROPERTIES_PAGE;
		// }
		// public void run() {
		// try {
		//
		// getSite().getPage().showView(
		// "org.eclipse.ui.views.PropertySheet");
		// // setId("Show_Properties");
		// }
		// catch (PartInitException exception) {
		// //AuthoringPlugin.getDefault().getLogger().logError(exception);
		// }
		// }
		// public boolean isEnabled() {
		// List editParts = getGraphicalViewer().getSelectedEditParts();
		// return (editParts.size() == 1 && editParts.get(0) instanceof
		// EditPart);
		// }
		// };
		//		
		// registry.registerAction(action);

		IAction action = createAnAction(
				DiagramUIResources.AbstractDiagram_WorkProductDescriptor_text, wpNodeCreationFactory, 
				CREATE_WP_NODE,
				DiagramUIResources.AbstractDiagram_WorkProductDescriptor_tooltip, 
				AuthoringUIPlugin.getDefault().getImageDescriptor(
						"full/obj16/WorkProductDescriptor.gif")); //$NON-NLS-1$
		getActionRegistry().registerAction(action);

	}

	protected PaletteContainer createControlGroup(PaletteRoot root) {
		initializeCreationFactories();
		PaletteGroup controlGroup = new PaletteGroup("Control Group"); //$NON-NLS-1$

		List entries = new ArrayList();

		ToolEntry tool = new PanningSelectionToolEntry(DiagramUIResources.AbstractDiagram_Select_text, 
				DiagramUIResources.AbstractDiagram_Select_tooltip
		);
		tool.setSmallIcon(AuthoringUIPlugin.getDefault().getImageDescriptor(
				"select_pal16.gif")); //$NON-NLS-1$
		tool.setLargeIcon(AuthoringUIPlugin.getDefault().getImageDescriptor(
				"select_pal24.gif")); //$NON-NLS-1$
		entries.add(tool);
		root.setDefaultEntry(tool);

		tool = new MarqueeToolEntry();
		tool.setSmallIcon(AuthoringUIPlugin.getDefault().getImageDescriptor(
				"marq_pal16.gif")); //$NON-NLS-1$
		tool.setLargeIcon(AuthoringUIPlugin.getDefault().getImageDescriptor(
				"marq_pal24.gif")); //$NON-NLS-1$
		entries.add(tool);

		tool = new ConnectionCreationToolEntry(
				DiagramUIResources.AbstractDiagram_Link_text, DiagramUIResources.AbstractDiagram_Link_tooltip, 
				null, AuthoringUIPlugin.getDefault().getImageDescriptor(
						"link_pal16.gif"), //$NON-NLS-1$
				AuthoringUIPlugin.getDefault().getImageDescriptor(
						"link_pal24.gif")//$NON-NLS-1$
		);
		entries.add(tool);
		CombinedTemplateCreationEntry combined = new CombinedTemplateCreationEntry(
				DiagramUIResources.AbstractDiagram_FreeText_text, 
				DiagramUIResources.AbstractDiagram_FreeText_tooltip, 
				TemplateConstants.FREE_TEXT, freeTxtNodeCreationFactory,
				AuthoringUIPlugin.getDefault().getImageDescriptor(
						"freetext_pal16.gif"), //$NON-NLS-1$
				AuthoringUIPlugin.getDefault().getImageDescriptor(
						"freetext_pal24.gif")//$NON-NLS-1$
		);
		entries.add(combined);

		PaletteSeparator sep = new PaletteSeparator(
				GraphicalWPDependencyEditor.class.getName() + "sep1"); //$NON-NLS-1$
		sep
				.setUserModificationPermission(PaletteEntry.PERMISSION_NO_MODIFICATION);
		entries.add(sep); 

		combined = new CombinedTemplateCreationEntry(
				DiagramUIResources.AbstractDiagram_WorkProductDescriptor_text, 
				DiagramUIResources.AbstractDiagram_WorkProductDescriptor_tooltip, 
				TemplateConstants.WORK_PRODUCT, wpNodeCreationFactory,
				AuthoringUIPlugin.getDefault().getImageDescriptor(
						"full/obj16/WorkProductDescriptor.gif"), //$NON-NLS-1$
				AuthoringUIPlugin.getDefault().getImageDescriptor(
						"full/obj16/WorkProductDescriptor.gif")//$NON-NLS-1$
		);
		entries.add(combined);
		controlGroup.addAll(entries);
		return controlGroup;
	}

	protected EditPartFactory createEditPartFactory() {
		return new WPDiagramEditPartFactory();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.authoring.ui.editors.AbstractDiagramEditor#createDiagramEditPart()
	 */
	protected EditPart createDiagramEditPart() {
		 DiagramEditPart editPart = new DiagramEditPart(ModelFactory.eINSTANCE
				.createWorkProductDependencyDiagram());
		 editPart.markDirty(true);
		 return editPart;
	}
	
//	protected EditPart createEditPart(Object e) {
//		WorkProductDependencyDiagram diagram = ModelFactory.eINSTANCE
//				.createWorkProductDependencyDiagram();
//		diagram.setObject(e);
//		EditPart part = new DiagramEditPart(diagram);
//		part.setModel(diagram);
//		diagram.addConsumer(this);
//		return part;
//	}

	protected String getPartNamePrefix() {
		return DiagramUIResources.WorkProductDependencyDiagram_prefix; 
	}

	/**
	 * An editor input 
	 *
	 *
	 */
	/**
	 * @author shilpat
	 *
	 */
	public class EditorInput implements IEditorInput {

		/**
		 * @param e
		 */
		public EditorInput(MethodElement e) {
		}

		/**
		 * @see org.eclipse.ui.IEditorInput#exists()
		 */
		public boolean exists() {
			return false;
		}

		/**
		 * @see org.eclipse.ui.IEditorInput#getImageDescriptor()
		 */
		public ImageDescriptor getImageDescriptor() {
			return null;
		}

		/**
		 * @see org.eclipse.ui.IEditorInput#getName()
		 */
		public String getName() {
			return null;
		}

		/**
		 * @see org.eclipse.ui.IEditorInput#getPersistable()
		 */
		public IPersistableElement getPersistable() {
			return null;
		}

		/**
		 * @see org.eclipse.ui.IEditorInput#getToolTipText()
		 */
		public String getToolTipText() {
			return null;
		}

		/**
		 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
		 */
		public Object getAdapter(Class type) {
			// if (type.equals(IPropertySheetPage.class)) {
			// return getPropertySheetPage();
			// }else
			return null;
		}

//		private Object getPropertySheetPage() {
//
//			if (rootEntry == null) {
//				// create a new root
//				PropertySheetEntry root = new PropertySheetEntry();
//				rootEntry = root;
//			}
//			if (rootEntry instanceof PropertySheetEntry) {
//				// = wpEditPart.getViewer();
//				// ISelectionProvider provider = new
//				// AdapterFactoryContentProvider(WP);
//				// ((PropertySheetEntry) rootEntry).setPropertySourceProvider();
//			}
//			return rootEntry;
//		}
	}

	protected String getDiagramType() {
		return ResourceHelper.DIAGRAM_TYPE_WP_DEPENDENCY;
	}
	
}
