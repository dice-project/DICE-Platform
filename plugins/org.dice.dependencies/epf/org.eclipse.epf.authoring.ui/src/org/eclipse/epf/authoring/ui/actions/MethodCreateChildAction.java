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
package org.eclipse.epf.authoring.ui.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.command.CreateChildCommand.Helper;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.ui.action.StaticSelectionCommandAction;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.library.edit.process.command.CreateProcessComponentCommand;
import org.eclipse.epf.library.edit.util.PracticePropUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.ui.actions.LibraryLockingOperationRunner;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.provider.UmaEditPlugin;
import org.eclipse.epf.uma.util.UserDefinedTypeMeta;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;


/**
 * Method Create Child action.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public final class MethodCreateChildAction extends StaticSelectionCommandAction {
	/**
	 * This describes the child to be created.
	 */
	protected Object descriptor;

	/**
	 * This constructs an instance of an action that creates a child specified
	 * by <code>descriptor</code> for the single object in the
	 * <code>selection</code>.
	 */
	public MethodCreateChildAction(EditingDomain editingDomain,
			ISelection selection, Object descriptor) {
		super();
		this.editingDomain = editingDomain;
		this.descriptor = descriptor;
		configureAction(selection);
	}

	/**
	 * This creates the command for {@link
	 * StaticSelectionCommandAction#createActionCommand}.
	 */
	protected Command createActionCommand(EditingDomain editingDomain,
			Collection collection) {
		if (collection.size() == 1) {
			Object owner = collection.iterator().next();
			Command cmd = null;

			if (descriptor instanceof CommandParameter
					&& ((CommandParameter) descriptor).getValue() instanceof ProcessComponent) {
				CommandParameter newChildParameter = (CommandParameter) descriptor;
				CreateChildCommand.Helper helper = (Helper) ((AdapterFactoryEditingDomain)editingDomain).getAdapterFactory().adapt(owner, IEditingDomainItemProvider.class);
//				for (Iterator iter = ((EObject) owner).eAdapters().iterator(); iter
//						.hasNext();) {
//					Object o = iter.next();
//					if (o instanceof ProcessPackageItemProvider) {
//						helper = (ProcessPackageItemProvider) o;
//						break;
//					}
//				}
				cmd = new CreateProcessComponentCommand(editingDomain,
						(EObject) owner, newChildParameter
								.getEStructuralFeature(), newChildParameter
								.getValue(), newChildParameter.getIndex(),
						collection, helper) {
				};
			} else {
				cmd = CreateChildCommand.create(editingDomain, owner,
						descriptor, collection);
			}

			if (isUserDefinedType()) {
				return new CreateMethodElementCommand(cmd) {
					public String getText() {
						String name = getNameForUserDefinedType();
						if (name != null) {
							return name;
						} else {
							return super.getText();
						}
					}
					
					public Object getImage() {
						Object img = getImageForUserDefinedType();
						if (img != null) {
							return img;
						} else {
							return super.getImage();
						}
					}
				};
			} else {
				return new CreateMethodElementCommand(cmd);
			}
		}
		return UnexecutableCommand.INSTANCE;
	}
	
	private void superRun() {
		super.run();
	}
	
	/**
	 * @see org.eclipse.emf.edit.ui.action.StaticSelectionCommandAction#run()
	 */
	public void run() {
		LibraryLockingOperationRunner runner = new LibraryLockingOperationRunner();
		runner.run(new IRunnableWithProgress() {

			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				superRun();
			}
			
		});
	}

	private boolean isUserDefinedType() {
		try {
			if (descriptor instanceof CommandParameter
					&& ((CommandParameter) descriptor).getValue() instanceof Practice) {
				Practice prac = (Practice)((CommandParameter) descriptor).getValue();
				UserDefinedTypeMeta udtMeta = PracticePropUtil.getPracticePropUtil().getUtdData(prac);
				if (udtMeta != null) {
					return true;
				}
			}
		} catch (Exception e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(e);
		}
		
		return false;
	}
	
	private String getNameForUserDefinedType() {
		try {
			if (isUserDefinedType()) {
				Practice prac = (Practice)((CommandParameter) descriptor).getValue();
				UserDefinedTypeMeta udtMeta = PracticePropUtil.getPracticePropUtil().getUtdData(prac);
				return udtMeta.getRteNameMap().get(UserDefinedTypeMeta._typeName);
			}
		} catch (Exception e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(e);
		}
		
		return null;
	}
	
	private Object getImageForUserDefinedType() {
		if (isUserDefinedType()) {
			Practice prac = (Practice) ((CommandParameter) descriptor).getValue();
			Object image = TngUtil.getImageForUdt(prac);
			if (image == null) {
				return UmaEditPlugin.INSTANCE.getImage("full/obj16/UdtNode");
			}
			return image;
		}
		return null;
	}
	
}
