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
package org.eclipse.epf.library.edit.command;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.meta.TypeDefUtil;
import org.eclipse.epf.library.edit.util.PropUtil;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.util.ExtendedAttribute;

/**
 * Manages the execution of editing commands with full support for dirty flag,
 * undo and redo.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class ActionManager implements IActionManager {

	protected static final Map RESOURCE_TO_MODIFIERS_MAP = new HashMap();

	private EditingDomain editingDomain;

	protected FullyRevertibleCommandStack commandStack;

	public ActionManager() {
		commandStack = createCommandStack();
		editingDomain = createEditingDomain();
	}
	
	protected EditingDomain createEditingDomain() {
		return new AdapterFactoryEditingDomain(
				TngAdapterFactory.INSTANCE
				.getNavigatorView_ComposedAdapterFactory(),
		commandStack);
	}

	protected FullyRevertibleCommandStack createCommandStack() {
		return new FullyRevertibleCommandStack(this);
	}

	protected void registerAsModifierOf(EObject object) {
		Resource resource = object.eResource();
		if (resource == null)
			return;
		registerAsModifierOf(resource);
	}

	protected void registerAsModifierOf(Resource resource) {
		synchronized (RESOURCE_TO_MODIFIERS_MAP) {
			Set mods = (Set) RESOURCE_TO_MODIFIERS_MAP.get(resource);
			if (mods == null) {
				mods = new HashSet();
				RESOURCE_TO_MODIFIERS_MAP.put(resource, mods);
			}
			mods.add(this);
		}
	}

	protected void unregisterAsModifier() {
		synchronized (RESOURCE_TO_MODIFIERS_MAP) {
			for (Iterator iter = getModifiedResources().iterator(); iter
					.hasNext();) {
				Resource resource = (Resource) iter.next();
				Set mods = (Set) RESOURCE_TO_MODIFIERS_MAP.get(resource);
				if (mods != null) {
					mods.remove(this);
					if (mods.isEmpty()) {
						// save the resource before it removed from
						// RESOURCE_TO_MODIFIERS_MAP
						// 
						save(resource);
						RESOURCE_TO_MODIFIERS_MAP.remove(resource);
					}
				}
			}
		}
	}

	public boolean doAction(int actionType, EObject object,
			EStructuralFeature feature, Object value, int index) {
		Command cmd = null;
		Object oldValue = null;
		switch (actionType) {
		case ADD: {
			if (index >= 0) {
				cmd = new AddCommand(editingDomain, object, feature, value,
						index);
			} else {
				cmd = new AddCommand(editingDomain, object, feature, value);
			}
			break;
		}
		case ADD_MANY: {
			if (index >= 0) {
				cmd = new AddCommand(editingDomain, object, feature,
						(Collection) value, index);
			} else {
				cmd = new AddCommand(editingDomain, object, feature,
						(Collection) value);
			}
			break;
		}
		case REMOVE: {
			cmd = new RemoveCommand(editingDomain, object, feature, value);
			break;
		}
		case REMOVE_MANY: {
			cmd = new RemoveCommand(editingDomain, object, feature,
					(Collection) value);
			break;
		}
		case SET: {
			TypeDefUtil typeDefUtil = TypeDefUtil.getInstance();
			ExtendedAttribute att = typeDefUtil.getAssociatedExtendedAttribute(feature);
			oldValue = att == null ? object.eGet(feature) : typeDefUtil.eGet(object, feature);
			if ((oldValue != null && !oldValue.equals(value))
					|| (oldValue == null && value != null)) {
				if (att == null) {
					cmd = new SetCommand(editingDomain, object, feature, value);
				} else if ((value == null || value instanceof String) && object instanceof ContentDescription) {
					cmd = PropUtil.getSetExtendedAttributeCommand((ContentDescription) object, att, (String) value);
				}
			}
			break;
		}
		}
		if (cmd != null) {
			return commandStack.doExecute(cmd);
		}
		return false;
	}

	public void undo() {
		commandStack.undo();
	}

	public void redo() {
		commandStack.redo();
	}

	public boolean undoAll() {
		boolean b = commandStack.undoAll();
		unregisterAsModifier();
		// fireNotifyChanged(null);
		return b;
	}

	public void saveIsDone() {
		commandStack.saveIsDone();
		unregisterAsModifier();
		// fireNotifyChanged(null);
	}

	public boolean isSaveNeeded() {
		boolean ret = commandStack.isSaveNeeded();
		if (!ret)
			return false;
		if (getModifiedResources().isEmpty())
			return false;
		return ret;
	}

	public void dispose() {
		commandStack.flush();
	}

	public Collection getModifiedResources() {
		synchronized (RESOURCE_TO_MODIFIERS_MAP) {
			Collection modifiedResources = new HashSet();
			for (Iterator iter = RESOURCE_TO_MODIFIERS_MAP.entrySet()
					.iterator(); iter.hasNext();) {
				Map.Entry entry = (Map.Entry) iter.next();
				Set mods = (Set) entry.getValue();
				if (mods.contains(this)) {
					modifiedResources.add(entry.getKey());
				}
			}
			return modifiedResources;
		}
	}

	// public void fireNotifyChanged(Notification notification) {
	// changeNotifier.fireNotifyChanged(notification);
	// }
	//
	// public void addListener(INotifyChangedListener notifyChangedListener) {
	// changeNotifier.add(notifyChangedListener);
	// }
	//
	// public void removeListener(INotifyChangedListener notifyChangedListener)
	// {
	// changeNotifier.remove(notifyChangedListener);
	// }

	/**
	 * Does nothing here, subclass should implement to provide saving the
	 * resource before it got removed from RESOURCE_TO_MODIFIERS_MAP
	 * 
	 * @param resource
	 */
	protected void save(Resource resource) {
	}

	public CommandStack getCommandStack() {
		return commandStack;
	}

	public boolean execute(IResourceAwareCommand cmd) {
		if (cmd != null) {
			return commandStack.doExecute(cmd);
		}
		return false;
	}

}
