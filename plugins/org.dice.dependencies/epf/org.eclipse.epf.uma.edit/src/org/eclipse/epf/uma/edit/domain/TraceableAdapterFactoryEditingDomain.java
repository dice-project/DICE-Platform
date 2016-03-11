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
package org.eclipse.epf.uma.edit.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.StrictCompoundCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CopyCommand;
import org.eclipse.emf.edit.command.PasteFromClipboardCommand;
import org.eclipse.emf.edit.command.CopyCommand.Helper;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.epf.uma.MethodElement;

/**
 * A traceable adapter factory editing domain used for copying method elements.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class TraceableAdapterFactoryEditingDomain extends
		AdapterFactoryEditingDomain {

	// Maps copied method elements to their original counterparts.
	// Note: Copied elements are copies of the clipboard copies.
	private HashMap<Object, Object> copyToOriginalMap = null;

	// Maps original elements to their clipboard copies.
	private Map<Object, Object> originalToClipboardMap = null;
	
	private Map<EObject, EObject> clipboardToOriginalMap;

	/**
	 * Creates a new instance.
	 * 
	 * @param adapterFactory
	 *            an adapter factory used to create the adapter to which calls
	 *            are delegated
	 * @param commandStack
	 *            a command stack
	 */
	public TraceableAdapterFactoryEditingDomain(AdapterFactory adapterFactory,
			CommandStack commandStack) {
		super(adapterFactory, commandStack);
	}

	/**
	 * Creates a new instance.
	 * 
	 * @param adapterFactory
	 *            an adapter factory used to create the adapter to which calls
	 *            are delegated
	 * @param commandStack
	 *            a command stack
	 * @param resourceToReadOnlyMap
	 *            controls whether the domain is read only
	 */
	public TraceableAdapterFactoryEditingDomain(AdapterFactory adapterFactory,
			CommandStack commandStack, Map resourceToReadOnlyMap) {
		super(adapterFactory, commandStack, resourceToReadOnlyMap);
	}

	/**
	 * Creates a new instance.
	 * 
	 * @param adapterFactory
	 *            an adapter factory used to create the adapter to which calls
	 *            are delegated
	 * @param commandStack
	 *            a command stack
	 * @param resourceSet
	 *            a resource set
	 */
	public TraceableAdapterFactoryEditingDomain(AdapterFactory adapterFactory,
			CommandStack commandStack, ResourceSet resourceSet) {
		super(adapterFactory, commandStack, resourceSet);
	}

	/**
	 * Returns a map containing the copied elements mapped to their original
	 * counterparts.
	 * 
	 * @return a map containing the copied elements mapped to their original
	 *         counterparts
	 */
	public Map<?, ?> getCopyToOriginalMap() {
		if (copyToOriginalMap == null) {
			copyToOriginalMap = new HashMap<Object, Object>();
		}
		return copyToOriginalMap;
	}
	
	
	/**
	 * Returns a map containing the original elements mapped to their copied 
	 * counterparts on clipboard
	 * 
	 * @return a map containing the original elements mapped to their copied 
	 *         counterparts
	 */
	public Map getOriginalToClipboardMap() {
		if (originalToClipboardMap == null) {
			originalToClipboardMap = new HashMap();
		}
		return originalToClipboardMap;
	}

	/**
	 * Used by the CreateCopyCommand. Adds infomation to construct maps that
	 * keep track of the original method elements and their copies.
	 * 
	 * @param c
	 *            a collection to store the mapping info
	 * @param copyHelper
	 *            a helper class that is used to keep track of copied objects
	 *            and their associated copies
	 */
	public void addCopyInfo(Collection c, Helper copyHelper) {
		if (isNewCopy(c, copyHelper)) {
			addNewCopy(c, copyHelper);
			return;
		} else {
			chainCopy(c, copyHelper);
			return;
		}
	}

	/**
	 * Adds a new element that is being copied to the clipboard.
	 */
	private void addNewCopy(Collection c, Helper copyHelper) {
		// this means we are copying the Collection c to the clipboard
		if (originalToClipboardMap == null)
			originalToClipboardMap = new HashMap();
		if(clipboardToOriginalMap == null) {
			clipboardToOriginalMap = new HashMap<EObject, EObject>();
		}
		Iterator iter = copyHelper.keySet().iterator();
		while (iter.hasNext()) {
			EObject key = (EObject) iter.next();
			EObject value = (EObject) copyHelper.get(key);
			originalToClipboardMap.put(key, value);
			clipboardToOriginalMap.put(value, key);
		}
	}

	/**
	 * Creates a map from the copied elements to their original counterparts.
	 */
	private void chainCopy(Collection c, Helper copyHelper) {
		// chain the maps - change the copyToOriginalMap
		if (originalToClipboardMap != null) {
			if (copyToOriginalMap == null)
				copyToOriginalMap = new HashMap();
			Iterator iter = originalToClipboardMap.keySet().iterator();
			while (iter.hasNext()) {
				Object o2CKey = iter.next();
				Object o2CValue = originalToClipboardMap.get(o2CKey);
				Object copyValue = copyHelper.get(o2CValue);
				if (copyValue == null) {
					// error
					continue;
				}
				if (o2CKey instanceof MethodElement)
					copyToOriginalMap.put(copyValue, o2CKey);
			}
		}
	}

	private boolean isNewCopy(Collection c, Helper copyHelper) {
		// originalToClipboardMap was set to null at start of copy command
		if (originalToClipboardMap == null)
			return true;

		// iterate through copyHelper map, see if any copyHelper keys are o2C
		// values, which means we are copying clipboard
		Iterator iter = copyHelper.keySet().iterator();
		while (iter.hasNext()) {
			Object clipKey = iter.next();
			if (!originalToClipboardMap.containsValue(clipKey))
				return true;
		}

		return false;
	}

	/**
	 * Initializes the copy maps. It should be called within the copy action.
	 */
	public void resetCopyMaps() {
		if (originalToClipboardMap != null) {
			originalToClipboardMap.clear();
			originalToClipboardMap = null;
		}
		if(clipboardToOriginalMap != null) {
			clipboardToOriginalMap.clear();
			clipboardToOriginalMap = null;
		}
		if (copyToOriginalMap != null) {
			copyToOriginalMap.clear();
			copyToOriginalMap = null;
		}
	}

	public Map<EObject, EObject> getClipboardToOriginalMap() {
		return clipboardToOriginalMap;
	}
	
	private Map<Object, Object> extenalMaintainedCopyMap;	
	public Map<Object, Object> getExtenalMaintainedCopyMap() {
		return extenalMaintainedCopyMap;
	}

	public void setExtenalMaintainedCopyMap(
			Map<Object, Object> extenalMaintainedCopyMap) {
		this.extenalMaintainedCopyMap = extenalMaintainedCopyMap;
	}
	
	
	protected static class InternalPasteFromClipboardCommand extends  PasteFromClipboardCommand { 
		public InternalPasteFromClipboardCommand(EditingDomain domain, Object owner, Object feature, int index, boolean optimize) {
			super(domain, owner, feature, index, optimize);			
		}		

		  @Override
		  protected boolean prepare()
		  {
		    // Create a strict compound command to do a copy and then add the result
		    //
		    command = new StrictCompoundCommand();

		    // Create a command to copy the clipboard.
		    //
		    final Command copyCommand = CopyCommand.create(domain, domain.getClipboard());
		    command.append(copyCommand);

		    // Create a proxy that will create an add command.
		    //
		    command.append
		      (new CommandWrapper()
		       {
		         protected Collection<Object> original;
		         protected Collection<Object> copy;

		         @Override
		         protected Command createCommand()
		         {
		           original = domain.getClipboard();
		           copy = new ArrayList<Object>(copyCommand.getResult());

		           // Use the original to do the add, but only if it's of the same type as the copy.
		           // This ensures that if there is conversion being done as part of the copy,
		           // as would be the case for a cross domain copy in the mapping framework,
		           // that we do actually use the converted instance.
		           //
		           if (original.size() == copy.size())
		           {
		             for (Iterator<Object> i = original.iterator(), j = copy.iterator(); i.hasNext(); )
		             {
		               Object originalObject = i.next();
		               Object copyObject = j.next();
		               if (originalObject.getClass() != copyObject.getClass())
		               {
		                 original = null;
		                 break;
		               }
		             }
		           }
		           
		           Command addCommand = AddCommand.create(domain, owner, feature, copy, index);
		           return addCommand;
		         }

		         @Override
		         public void execute()
		         {
		           if (original != null)
		           {
		             domain.setClipboard(copy);
		           }
		           super.execute();
		         }

		         @Override
		         public void undo()
		         {
		           super.undo();
		           if (original != null)
		           {
		             domain.setClipboard(original);
		           }
		         }

		         @Override
		         public void redo()
		         {
		           if (original != null)
		           {
		             domain.setClipboard(copy);
		           }
		           super.redo();
		         }
		       });

		    boolean result;
		    if (optimize)
		    {
		      // This will determine canExecute as efficiently as possible.
		      //
		      result = optimizedCanExecute();
		    }
		    else
		    {
		      // This will actually execute the copy command in order to check if the add can execute.
		      //
		      result = command.canExecute();
		    }

		    return result;
		  }
		
	}
	
	public Command createCommand(Class commandClass, CommandParameter commandParameter) {
		if (commandClass == PasteFromClipboardCommand.class) {
			return new InternalPasteFromClipboardCommand(this, commandParameter
					.getOwner(), commandParameter.getFeature(),
					commandParameter.getIndex(), getOptimizeCopy());
		}
		return super.createCommand(commandClass, commandParameter);
	}
	
}
