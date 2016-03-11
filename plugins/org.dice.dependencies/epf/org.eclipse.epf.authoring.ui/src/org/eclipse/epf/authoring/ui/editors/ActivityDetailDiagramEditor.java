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
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.authoring.gef.edit.ActivityDetailDiagramEditPart;
import org.eclipse.epf.authoring.gef.edit.ActivityDetailDiagramEditPartFactory;
import org.eclipse.epf.authoring.gef.edit.DiagramUpdateService;
import org.eclipse.epf.authoring.gef.edit.FreeTextNodeEditPart;
import org.eclipse.epf.authoring.gef.util.DiagramUIResources;
import org.eclipse.epf.authoring.gef.util.TemplateConstants;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.diagram.model.Diagram;
import org.eclipse.epf.diagram.model.ModelFactory;
import org.eclipse.epf.diagram.model.NamedNode;
import org.eclipse.epf.diagram.model.RoleTaskComposite;
import org.eclipse.epf.diagram.model.util.IAdapterFactoryFilter;
import org.eclipse.epf.library.configuration.ProcessConfigurator;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.util.ConfigurableComposedAdapterFactory;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PanningSelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;


/**
 * An editor for activity detail diagram
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class ActivityDetailDiagramEditor extends AbstractDiagramEditor {


	private static final String RESET_DIAGRAM_LAYOUT = "reset_diagram_layout"; //$NON-NLS-1$

	private CreationFactory roleNodeCreationFactory;

	private CreationFactory taskNodeCreationFactory;

	private CreationFactory wpdNodeCreationFactory;

	private boolean initialCleanUp = false;
	private BreakdownAdapterFactory factory = null;

	// a hack
	/**
	 * 
	 * @see org.eclipse.epf.authoring.ui.editors.AbstractDiagramEditor#createEditPartFactory()
	 */
	protected EditPartFactory createEditPartFactory() {
		return new ActivityDetailDiagramEditPartFactory();
		// return new EditPartFactory() {
		//
		// public EditPart createEditPart(EditPart context, Object model) {
		// if(model instanceof ActivityDetailDiagram) {
		// return new ActivityDetailDiagramEditPart((ActivityDetailDiagram)
		// model);
		// }
		// else if(model instanceof RoleTaskComposite) {
		// return new RoleTaskCompositeEditPart((RoleTaskComposite) model);
		// }
		// else if(model instanceof WorkProductComposite){
		// return new WorkProductCompositeEditPart((WorkProductComposite)
		// model);
		// }
		// else if(model instanceof RoleNode
		// || model instanceof TaskNode
		// || model instanceof WorkProductDescriptorNode
		// )
		// {
		// return new DescriptorNodeEditPart((NamedNode) model);
		// }
		// else if(model instanceof Link) {
		// return new LinkEditPart((Link) model);
		// }
		// if(model instanceof TypedNode) {
		// TypedNode node = (TypedNode) model;
		// switch(node.getType()) {
		// case TypedNode.START: return new StartNodeEditPart(node);
		// case TypedNode.END: return new EndNodeEditPart(node);
		// case TypedNode.DECISION: return new DecisionNodeEditPart(node);
		// case TypedNode.SYNCH_BAR: return new SynchBarNodeEditPart(node);
		// case TypedNode.FREE_TEXT: return new FreeTextEditPart(node);
		// }
		// }
		// return null;
		// }
		//			
		// };
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.editors.AbstractDiagramEditor#createDiagramEditPart()
	 */
	protected EditPart createDiagramEditPart() {
		return new ActivityDetailDiagramEditPart(ModelFactory.eINSTANCE
				.createActivityDetailDiagram());
	}

//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see org.eclipse.epf.authoring.ui.editors.AbstractDiagramEditor#createEditPart(java.lang.Object)
//	 */
//	protected EditPart createEditPart(Object e) {
//		ActivityDetailDiagram diagram = ModelFactory.eINSTANCE
//				.createActivityDetailDiagram();
//		diagram.setObject(e);
//		EditPart part = new ActivityDetailDiagramEditPart(diagram);
//		part.setModel(diagram);
//		diagram.addConsumer(this);
//		return part;
//	}
		
	/**
	 * @see org.eclipse.epf.authoring.ui.editors.AbstractDiagramEditor#getPartNamePrefix()
	 */
	protected String getPartNamePrefix() {
		return DiagramUIResources.ActivityDetailDiagram_prefix; 
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

		controlGroup.addAll(entries);
		return controlGroup;
	}

	protected void initializeCreationFactories() {
		super.initializeCreationFactories();

		if (roleNodeCreationFactory == null) {
			roleNodeCreationFactory = new CreationFactory() {

				public Object getNewObject() {
					RoleTaskComposite node = ModelFactory.eINSTANCE
							.createRoleTaskComposite();
					Diagram diagram = (Diagram) editPart.getModel();
					node.setUMADiagram(diagram.getUMADiagram());
					node.setObject(UmaFactory.eINSTANCE.createRoleDescriptor());
					return node;
				}

				public Object getObjectType() {
					return TemplateConstants.ROLE_DESCRIPTOR;
				}

			};
		}
		CreationFactory creationFactory = roleNodeCreationFactory;
		templateNameToCreationFactoryMap.put(creationFactory.getObjectType(),
				creationFactory);

		if (taskNodeCreationFactory == null) {
			taskNodeCreationFactory = new CreationFactory() {

				public Object getNewObject() {
					NamedNode node = ModelFactory.eINSTANCE.createTaskNode();
					Diagram diagram = (Diagram) editPart.getModel();
					node.setUMADiagram(diagram.getUMADiagram());
					node.setObject(UmaFactory.eINSTANCE.createTaskDescriptor());
					return node;
				}

				public Object getObjectType() {
					return TemplateConstants.TASK_DESCRIPTOR;
				}

			};
		}
		creationFactory = taskNodeCreationFactory;
		templateNameToCreationFactoryMap.put(creationFactory.getObjectType(),
				creationFactory);

		if (wpdNodeCreationFactory == null) {
			wpdNodeCreationFactory = new CreationFactory() {

				public Object getNewObject() {
					NamedNode node = ModelFactory.eINSTANCE
							.createWorkProductDescriptorNode();
					Diagram diagram = (Diagram) editPart.getModel();
					node.setUMADiagram(diagram.getUMADiagram());
					node.setObject(UmaFactory.eINSTANCE
							.createWorkProductDescriptor());
					return node;
				}

				public Object getObjectType() {
					return TemplateConstants.TASK_DESCRIPTOR;
				}

			};
		}
		creationFactory = wpdNodeCreationFactory;
		templateNameToCreationFactoryMap.put(creationFactory.getObjectType(),
				creationFactory);
	}

	/**
	 * Editor Input
	 *
	 */
	public static class EditorInput extends BreakdownElementEditorInput {

		/**
		 * @param e
		 */
		public EditorInput(Object e, Suppression suppression) {
			super(e, suppression);
		}

	}

	/**
	 * Set focus on the editor
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#setFocus()
	 */
	public void setFocus() {
		super.setFocus();
		getGraphicalViewer().getContents().refresh();
		if (!initialCleanUp) {
			cleanUpDiagram();
			initialCleanUp = true;
			// Locked Plugin case: Display generated ADD diagram, Permission denied to save,  
			// and No dirty flag on editor. 
			if(TngUtil.isLocked(getMethodElementFromInput())){
				// Flush all commands, means will not save.
				getCommandStack().flush();
				// Fire dirty flag property change, no dirty flag on editor.
				firePropertyChange(PROP_DIRTY);
			}
		}
	}

	protected String getDiagramType() {
		return ResourceHelper.DIAGRAM_TYPE_ACTIVITY_DETAIL;
	}

	/**
	 * Clean diagram
	 *
	 */
	public void cleanUpDiagram() {
//		MethodElement obj = ((MethodElementEditorInput) getEditorInput())
//				.getMethodElement();
		DiagramUpdateService service = new DiagramUpdateService(
				getGraphicalViewer(), getEditDomain(), getActionRegistry());
		service.cleanUpDiagram();
		// if (TngUtil.isGeneralizer(obj,
		// getExtendAndContributeVariabilityTypes())
		// && !initialCleanUp) {
		// initialCleanUp = true;
		// IAction reset = getActionRegistry().getAction(
		// ActivityDetailDiagramEditor.RESET_DIAGRAM_LAYOUT);
		// reset.run();
		// doSave(new NullProgressMonitor());
		// }
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.editors.AbstractDiagramEditor#contributeToContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	protected void contributeToContextMenu(IMenuManager menu) {
		super.contributeToContextMenu(menu);

		boolean canModify = !isReadOnly();

		IAction action = getActionRegistry().getAction(RESET_DIAGRAM_LAYOUT);
		if (action.isEnabled() && canModify) {
			menu.insertBefore(DELETE_DIAGRAM, action);
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.editors.AbstractDiagramEditor#createActions()
	 */
	protected void createActions() {
		super.createActions();
		Action resetLayoutAction = new Action(
				DiagramUIResources.ActivityDetailDiagramEditor_ResetDiagramLayout_text) { 

			public void run() {
				getActionRegistry().getAction(REFRESH).run();
				if (getGraphicalViewer().getContents() instanceof ActivityDetailDiagramEditPart) {
					ActivityDetailDiagramEditPart dep = (ActivityDetailDiagramEditPart) getGraphicalViewer()
							.getContents();
					List local = new ArrayList();
					if(dep.getChildren() != null && !dep.getChildren().isEmpty()){
						for(Iterator iterator = dep.getChildren().iterator(); iterator.hasNext();){
							Object next = iterator.next();
							if(!(next instanceof FreeTextNodeEditPart)){
								local.add(next);
							}
						}
					}
					dep.getRecentlyAddedParts().addAll(local);
					cleanUpDiagram();
				}
			}

			public boolean isEnabled() {
				return getGraphicalViewer().getSelectedEditParts().size() == 0;
			}

			public String getId() {
				return RESET_DIAGRAM_LAYOUT;
			}

		};
		getActionRegistry().registerAction(resetLayoutAction);
	}

//	private static Collection extendAndContributeVariabilityTypes = null;

//	private static Collection getExtendAndContributeVariabilityTypes() {
//		if (extendAndContributeVariabilityTypes == null) {
//			extendAndContributeVariabilityTypes = new ArrayList();
//			extendAndContributeVariabilityTypes
//					.add(VariabilityType.EXTENDS_LITERAL);
//			extendAndContributeVariabilityTypes
//					.add(VariabilityType.CONTRIBUTES_LITERAL);
//		}
//		return extendAndContributeVariabilityTypes;
//	}
	
	/**
	 * Break down adapter factory class
	 */
	public class BreakdownAdapterFactory
	{
		public ConfigurableComposedAdapterFactory wbsAdapterFactory = null;
		public ConfigurableComposedAdapterFactory tbsAdapterFactory = null;
		public ConfigurableComposedAdapterFactory wpbsAdapterFactory = null;
		public ConfigurableComposedAdapterFactory cbsAdapterFactory = null;

		public BreakdownAdapterFactory(MethodConfiguration methodConfig)
		{
			// create adapt factories
			wbsAdapterFactory = (ConfigurableComposedAdapterFactory) TngAdapterFactory.INSTANCE
					.createWBSComposedAdapterFactory();
			tbsAdapterFactory = (ConfigurableComposedAdapterFactory) TngAdapterFactory.INSTANCE
					.createTBSComposedAdapterFactory();
			wpbsAdapterFactory = (ConfigurableComposedAdapterFactory) TngAdapterFactory.INSTANCE
					.createWPBSComposedAdapterFactory();
			cbsAdapterFactory = (ConfigurableComposedAdapterFactory) TngAdapterFactory.INSTANCE
					.createProcessComposedAdapterFactory();

			// set configuration filter
			DiagramAdapterFactoryFilter configurator = new DiagramAdapterFactoryFilter(
					methodConfig, this);

			wbsAdapterFactory.setFilter(configurator);
			tbsAdapterFactory.setFilter(configurator);
			wpbsAdapterFactory.setFilter(configurator);
			cbsAdapterFactory.setFilter(configurator);		}
		
		public void clear()
		{
			if (wbsAdapterFactory != null) {
				wbsAdapterFactory.dispose();
				wbsAdapterFactory = null;
			}

			if (tbsAdapterFactory != null) {
				tbsAdapterFactory.dispose();
				tbsAdapterFactory = null;
			}

			if (wpbsAdapterFactory != null) {
				wpbsAdapterFactory.dispose();
				wpbsAdapterFactory = null;
			}

			if (cbsAdapterFactory != null) {
				cbsAdapterFactory.dispose();
				cbsAdapterFactory = null;
			}
		}
	}
	
	/**
	 * Diagram Adapter factory filter
	 *
	 */
	public class DiagramAdapterFactoryFilter extends ProcessConfigurator
			implements IAdapterFactoryFilter {

		private ActivityDetailDiagramEditor.BreakdownAdapterFactory factory;

		/*
		 * Create an instance
		 */
		public DiagramAdapterFactoryFilter(MethodConfiguration methodConfig,
				ActivityDetailDiagramEditor.BreakdownAdapterFactory factory) {
			super(methodConfig);
			this.factory = factory;
		}

		/**
		 * Return WBS Adapter Factory
		 */
		public AdapterFactory getWBSAdapterFactory() {
			return factory.wbsAdapterFactory;
		}

		/**
		 * Return Team Allocation Adapter Factory
		 */
		public AdapterFactory getTBSAdapterFactory() {
			return factory.tbsAdapterFactory;
		}

		/**
		 * Return WP Usage Adapter Factory
		 */
		public AdapterFactory getWPBSAdapterFactory() {
			return factory.wpbsAdapterFactory;
		}

		/**
		 * Return consolidated view adapter factory
		 */
		public AdapterFactory getCBSAdapterFactory() {
			return factory.cbsAdapterFactory;
		}

		public boolean accept(Object obj) {
			return super.accept(obj);
		}
	}
	

	/**
	 * @see org.eclipse.epf.authoring.ui.editors.AbstractDiagramEditor#dispose()
	 */
	public void dispose() {
		if(factory != null){
			factory.clear();
		}
		super.dispose();
	}

}
