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
package org.eclipse.epf.authoring.ui.actions;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.ui.action.CommandActionHandler;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.editors.ProcessEditor;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.DependencyChecker;
import org.eclipse.epf.richtext.RichTextImages;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkOrder;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Indents breakdown elements in a process tree.
 * 
 * @author Phong Nguyen Le
 * @since  1.2
 */
public class IndentAction extends
		CommandActionHandler implements IWorkbenchPartAction, IModifyingAction {

	public static class IndentCommand extends AbstractCommand implements IResourceAwareCommand {
		
		private Collection<BreakdownElement> elements;
		private AdapterFactory adapterFactory;
		private Set modifiedResources;
		private BreakdownElement element;
		private Activity oldParent;
		private Activity newParent;
		private int oldIndex;
		private Map<WorkBreakdownElement, WorkOrder> circularWorkOrders;

		public IndentCommand(Collection<BreakdownElement> collection, AdapterFactory adapterFactory) {
			this.elements = collection;
			this.adapterFactory = adapterFactory;
			setLabel(LABEL);
		}

		public Collection getModifiedResources() {
			if(modifiedResources == null) {
				Process proc = null;
				for (Iterator<BreakdownElement> iter = elements.iterator(); proc == null && iter.hasNext();) {
					BreakdownElement e = iter.next();
					proc = TngUtil.getOwningProcess(e);
				}
				if(proc != null) {
					Resource resource = proc.eResource();
					if(resource != null) {
						modifiedResources = Collections.singleton(proc.eResource());
					}
				}
				if(modifiedResources == null) {
					modifiedResources = Collections.EMPTY_SET;
				}
			}
			return modifiedResources;
		}
		
		@Override
		protected boolean prepare() {
			element = elements.iterator().next();
			oldParent = element.getSuperActivities();
			if(oldParent == null) {
				return false;
			}
			oldIndex = oldParent.getBreakdownElements().indexOf(element);
			ITreeItemContentProvider ip = (ITreeItemContentProvider) adapterFactory.adapt(oldParent, 
					ITreeItemContentProvider.class);
			List<Object> children = new LinkedList<Object>(ip.getChildren(oldParent));
			int index = children.indexOf(element);
			if(index > 0) {
				Object prev = children.get(index - 1);
				if(prev instanceof Activity) {
					newParent = (Activity) prev;
					return DependencyChecker.checkCircularForMovingVariabilityElement(newParent, Collections.singleton(element), true);
				}
			}
			return false;
		}

		public void execute() {
			newParent.getBreakdownElements().add(element);
			
			// remove circular work orders
			//
			if(element instanceof WorkBreakdownElement) {
				if(circularWorkOrders == null) {
					circularWorkOrders = new HashMap<WorkBreakdownElement, WorkOrder>();
				}
				else {
					circularWorkOrders.clear();
				}
				WorkOrder wo = UmaUtil.findWorkOrder(newParent, element);
				if(wo != null) {	
					newParent.getLinkToPredecessor().remove(wo);
					circularWorkOrders.put(newParent, wo);
				}
				WorkBreakdownElement wbe = (WorkBreakdownElement) element;
				wo = UmaUtil.findWorkOrder(wbe, newParent);
				if(wo != null) {
					wbe.getLinkToPredecessor().remove(wo);
					circularWorkOrders.put(wbe, wo);
				}
			}			
			
			// move the process pacakge of the activity to new parent package
			//
			if(element instanceof Activity) {
				ProcessPackage parentPkg = (ProcessPackage) newParent.eContainer();
				parentPkg.getChildPackages().add((MethodPackage) element.eContainer());
			}
		}

		public void redo() {
			execute();
		}
		
		@Override
		public void undo() {
			if(circularWorkOrders != null && !circularWorkOrders.isEmpty()) {
				for (Map.Entry<WorkBreakdownElement, WorkOrder> entry : circularWorkOrders.entrySet()) {
					entry.getKey().getLinkToPredecessor().add(entry.getValue());
				}
				circularWorkOrders.clear();
			}
			oldParent.getBreakdownElements().add(oldIndex, element);			
			if(element instanceof Activity) {
				ProcessPackage parentPkg = (ProcessPackage) oldParent.eContainer();
				parentPkg.getChildPackages().add((MethodPackage) element.eContainer());
			}
		}
		
		@Override
		public Collection getAffectedObjects() {
			return elements;
		}
	}

	private static final String LABEL = AuthoringUIResources.ProcessEditor_Action_Indent;

	private ProcessEditor editor;

	/**
	 * Creates an instance
	 * @param text
	 */
	public IndentAction(String text) {
		super(null, text);
	}
	
	/**
	 * Creates an instance
	 *
	 */
	public IndentAction() {
		this(LABEL);
		setImageDescriptor(RichTextImages.IMG_DESC_INDENT);
		setDisabledImageDescriptor(RichTextImages.DISABLED_IMG_DESC_INDENT);
		setToolTipText(LABEL);
	}
	
	/**
	 * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public boolean updateSelection(IStructuredSelection selection) {
		// support indent only one activity for now
		//
		boolean ret = false;
		if(selection.size() == 1) {
			Object e = selection.getFirstElement();
			if(e instanceof Activity) {
				ret = super.updateSelection(selection);				
			}
		}
		setEnabled(ret);
		return ret;
		
		//TODO: support indent multiple activities in selection
//		ArrayList<Activity> list = new ArrayList<Activity>();
//		for (Iterator iter = selection.iterator(); iter.hasNext();) {
//			Object element = iter.next();
//			if(element instanceof Activity) {
//				list.add((Activity) element);
//			}
//		}
//		if(list.isEmpty()) {
//			return false;
//		}
//		return super.updateSelection(new StructuredSelection(list));
	}
	
	/**
	 * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#createCommand(java.util.Collection)
	 */
	public Command createCommand(Collection selection) {
		if(domain instanceof AdapterFactoryEditingDomain) {
			return new IndentCommand(selection, ((AdapterFactoryEditingDomain)domain).getAdapterFactory());
		}
		return UnexecutableCommand.INSTANCE;
	}	

	/**
	 * @see org.eclipse.epf.authoring.ui.actions.IWorkbenchPartAction#setActiveWorkbenchPart(org.eclipse.ui.IWorkbenchPart)
	 */
	public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
		if(workbenchPart instanceof IEditingDomainProvider) {
			domain = ((IEditingDomainProvider)workbenchPart).getEditingDomain();
		}
		if(workbenchPart instanceof ProcessEditor) {
			editor = (ProcessEditor) workbenchPart;
		}		
		else if(workbenchPart == null) {
			editor = null;
		}
	}	
	
	private void superRun() {
		super.run();
	}
	
	/**
	 * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#run()
	 */
	public void run() {
		BusyIndicator.showWhile(editor.getEditorSite()
				.getShell().getDisplay(), new Runnable() {

					public void run() {
						superRun();
					}
			
		});
	}
	
}
