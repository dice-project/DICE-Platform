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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.ui.action.CommandActionHandler;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.editors.ProcessEditor;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.DependencyChecker;
import org.eclipse.epf.richtext.RichTextImages;
import org.eclipse.epf.richtext.RichTextResources;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Update suppression information from base elements
 * 
 * @author Phong Nguyen Le
 * @since  1.2
 */
public class OutdentAction extends
		CommandActionHandler implements IWorkbenchPartAction, IModifyingAction {
	public static class OutdentCommand extends AbstractCommand implements IResourceAwareCommand {
		private static final AdapterFactory[] ADAPTER_FACTORIES = {
			TngAdapterFactory.INSTANCE.getWBS_ComposedAdapterFactory(),
			TngAdapterFactory.INSTANCE.getOBS_ComposedAdapterFactory(),
			TngAdapterFactory.INSTANCE.getWBS_ComposedAdapterFactory()
		};
		
		private Collection<BreakdownElement> elements;
		private Set modifiedResources;
		private BreakdownElement element;
		private Activity oldParent;
		private Activity newParent;
		private int oldIndex;
		private int newIndex;
		private List<Object> newChildren;

		private AdapterFactory adapterFactory;

		public OutdentCommand(Collection<BreakdownElement> collection, AdapterFactory adapterFactory) {
			this.elements = collection;
			this.adapterFactory = adapterFactory;
			setLabel(LABEL);
		}
		
		@Override
		public void dispose() {
			if(newChildren != null) {
				newChildren.clear();
				newChildren = null;
			}
			
			super.dispose();
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
			Map<List<Object>, Integer> childrenToStartIndexMap = new HashMap<List<Object>, Integer>();
			AdapterFactory[] adapterFactories;
			if(element instanceof Activity) {
				adapterFactories = ADAPTER_FACTORIES;
			}
			else {
				adapterFactories = new AdapterFactory[] {
					adapterFactory	
				};
			}
			for (int i = 0; i < adapterFactories.length; i++) {
				AdapterFactory adapterFactory = adapterFactories[i];
				ITreeItemContentProvider ip = (ITreeItemContentProvider) adapterFactory.adapt(oldParent, 
						ITreeItemContentProvider.class);
				List<Object> children = new ArrayList<Object>(ip.getChildren(oldParent));
				int index = children.indexOf(element);
				int size = children.size();
				int newChildrenFromIndex = index + 1;
				if(newChildrenFromIndex < size) {
					for(int j = newChildrenFromIndex; j < size; j++) {
						Object child = children.get(j);
						if(ProcessUtil.isInherited(child)) {
							// cannot outdent activity that have inherited sibling below it
							//
							return false;
						}
					}
					childrenToStartIndexMap.put(children, Integer.valueOf(newChildrenFromIndex));
				}
			}
			if(!(element instanceof Activity) && !childrenToStartIndexMap.isEmpty()) {
				return false;
			}
			newParent = oldParent.getSuperActivities();
			if(newParent != null) {
				newIndex = newParent.getBreakdownElements().indexOf(oldParent) + 1;
				if(!childrenToStartIndexMap.isEmpty()) {
					newChildren = new UniqueEList();
					for (Iterator<Map.Entry<List<Object>, Integer>> iter = childrenToStartIndexMap.entrySet().iterator(); iter.hasNext();) {
						Map.Entry<List<Object>, Integer> entry = iter.next();
						List children = entry.getKey();
						newChildren.addAll(children.subList(entry.getValue().intValue(), children.size()));
					}
					if(!DependencyChecker.checkCircularForMovingVariabilityElement((VariabilityElement) element, newChildren, true)) {
						return false;						
					}
				}
				return DependencyChecker.checkCircularForMovingVariabilityElement(newParent, Collections.singleton(element), true);
			}
			return false;
		}

		public void execute() {
			newParent.getBreakdownElements().add(newIndex, element);
			if(element instanceof Activity) {
				ProcessPackage parentPkg = (ProcessPackage) newParent.eContainer();
				parentPkg.getChildPackages().add((MethodPackage)element.eContainer());

				if(newChildren != null) {
//					((Activity)element).getBreakdownElements().addAll(newChildren);			
					// move the process pacakge of the activity to new parent package
					//
					for (Iterator iter = newChildren.iterator(); iter.hasNext();) {
						Object child = iter.next();
						if (child instanceof BreakdownElement)
							((Activity)element).getBreakdownElements().add((BreakdownElement) child);
						if(child instanceof Activity) {
							parentPkg.getChildPackages().add((MethodPackage)((Activity)child).eContainer());
						}
					}
				}
			}
		}

		public void redo() {
			execute();
		}
		
		@Override
		public void undo() {
			oldParent.getBreakdownElements().add(oldIndex, element);
			if(element instanceof Activity) {
				ProcessPackage parentPkg = (ProcessPackage) oldParent.eContainer();
				parentPkg.getChildPackages().add((MethodPackage)element.eContainer());
				if(newChildren != null) {
					((Activity) element).getBreakdownElements().removeAll(newChildren);
					for (Iterator iter = newChildren.iterator(); iter.hasNext();) {
						Object child = iter.next();
						if(child instanceof Activity) {
							parentPkg.getChildPackages().add((MethodPackage)((Activity)child).eContainer());
						}
					}
				}
			}
		}
		
		@Override
		public Collection getAffectedObjects() {
			return elements;
		}
	}

	private static final String LABEL = AuthoringUIResources.ProcessEditor_Action_Outdent;

	private ProcessEditor editor;

	/**
	 * Creates an instance
	 * @param text
	 */
	public OutdentAction(String text) {
		super(null, text);
	}
	
	/**
	 * Creates an instance
	 *
	 */
	public OutdentAction() {
		this(LABEL);
		setImageDescriptor(RichTextImages.IMG_DESC_OUTDENT);
		setDisabledImageDescriptor(RichTextImages.DISABLED_IMG_DESC_OUTDENT);
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
			return new OutdentCommand(selection, ((AdapterFactoryEditingDomain)domain).getAdapterFactory());
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
