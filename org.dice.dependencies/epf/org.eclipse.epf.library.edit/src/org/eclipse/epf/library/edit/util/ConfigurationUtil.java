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
package org.eclipse.epf.library.edit.util;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.UmaPackage;

/**
 * Utility class to add/remove objects from a MethodConfiguration using an IActionManager
 * @author Jeff Hardy
 *
 */
public class ConfigurationUtil {
	
	private static boolean actionOnCollection(IActionManager actionMgr,
			MethodConfiguration config, Collection value, int actionType,
			EStructuralFeature feature, List listFeature) {
		if (config != null && value != null && !value.isEmpty()) {
			if (actionMgr != null) {
				return actionMgr.doAction(actionType, config, feature, value,
						-1);
			} else {
				if (actionType == IActionManager.ADD_MANY) {
					return listFeature.addAll(value);
				} else if (actionType == IActionManager.REMOVE_MANY) {
					return listFeature.removeAll(value);
				}
			}
		}
		return true;
	}
	
	private static boolean actionOnItem(IActionManager actionMgr,
			MethodConfiguration config, Object value, int actionType,
			EStructuralFeature feature, List listFeature) {
		if (config != null
				&& value != null
				&& ((actionType == IActionManager.ADD && !listFeature
						.contains(value)) || (actionType == IActionManager.REMOVE && listFeature
						.contains(value)))) {
			if (actionMgr != null) {
				return actionMgr.doAction(actionType, config, feature, value,
						-1);
			} else {
				if (actionType == IActionManager.ADD) {
					return listFeature.add(value);
				} else if (actionType == IActionManager.REMOVE) {
					return listFeature.remove(value);
				}
			}
		}
		return true;
	}

	
	/**
	 * Adds the given collection to the MethodConfiguration's list of selected MethodPlugins
	 * @param actionMgr use this IActionManager to change the MethodConfiguration, if null will change the MethodConfiguration directly.
	 * @param config
	 * @param value
	 * @return false if actionManager or list manipulation fails, true otherwise
	 */
	public static boolean addCollToMethodPluginList(IActionManager actionMgr, MethodConfiguration config, Collection value) {
		return actionOnCollection(actionMgr, config, value, IActionManager.ADD_MANY, UmaPackage.eINSTANCE
									.getMethodConfiguration_MethodPluginSelection(), config.getMethodPluginSelection());
	}

	/**
	 * Adds the given MethodPlugin to the MethodConfiguration's list of selected MethodPlugins
	 * @param actionMgr use this IActionManager to change the MethodConfiguration, if null will change the MethodConfiguration directly.
	 * @param config
	 * @param value
	 * @return false if actionManager or list manipulation fails, true otherwise
	 */
	public static boolean addToMethodPluginList(IActionManager actionMgr, MethodConfiguration config, MethodPlugin value) {
		return actionOnItem(actionMgr, config, value, IActionManager.ADD, UmaPackage.eINSTANCE
				.getMethodConfiguration_MethodPluginSelection(), config.getMethodPluginSelection());
	}

	/**
	 * Removes the given collection from the MethodConfiguration's list of selected MethodPlugins
	 * @param actionMgr use this IActionManager to change the MethodConfiguration, if null will change the MethodConfiguration directly.
	 * @param config
	 * @param value
	 * @return false if actionManager or list manipulation fails, true otherwise
	 */
	public static boolean removeCollFromMethodPluginList(IActionManager actionMgr, MethodConfiguration config, Collection value) {
		return actionOnCollection(actionMgr, config, value, IActionManager.REMOVE_MANY, UmaPackage.eINSTANCE
				.getMethodConfiguration_MethodPluginSelection(), config.getMethodPluginSelection());
	}
	
	/**
	 * Removes the given MethodPlugin from the MethodConfiguration's list of selected MethodPlugins
	 * @param actionMgr use this IActionManager to change the MethodConfiguration, if null will change the MethodConfiguration directly.
	 * @param config
	 * @param value
	 * @return false if actionManager or list manipulation fails, true otherwise
	 */
	public static boolean removeFromMethodPluginList(IActionManager actionMgr, MethodConfiguration config, MethodPlugin value) {
		return actionOnItem(actionMgr, config, value, IActionManager.REMOVE, UmaPackage.eINSTANCE
				.getMethodConfiguration_MethodPluginSelection(), config.getMethodPluginSelection());
	}

	/**
	 * Adds the given collection to the MethodConfiguration's list of selected MethodPackages
	 * @param actionMgr use this IActionManager to change the MethodConfiguration, if null will change the MethodConfiguration directly.
	 * @param config
	 * @param value
	 * @return false if actionManager or list manipulation fails, true otherwise
	 */
	public static boolean addCollToMethodPackageList(IActionManager actionMgr, MethodConfiguration config, Collection value) {
		return actionOnCollection(actionMgr, config, value, IActionManager.ADD_MANY, UmaPackage.eINSTANCE
				.getMethodConfiguration_MethodPackageSelection(), config.getMethodPackageSelection());
	}
	
	/**
	 * Adds the given MethodPackage to the MethodConfiguration's list of selected MethodPackages
	 * @param actionMgr use this IActionManager to change the MethodConfiguration, if null will change the MethodConfiguration directly.
	 * @param config
	 * @param value
	 * @return false if actionManager or list manipulation fails, true otherwise
	 */
	public static boolean addToMethodPackageList(IActionManager actionMgr, MethodConfiguration config, MethodPackage value) {
		return actionOnItem(actionMgr, config, value, IActionManager.ADD, UmaPackage.eINSTANCE
				.getMethodConfiguration_MethodPackageSelection(), config.getMethodPackageSelection());
	}

	
	/**
	 * Removes the given collection from the MethodConfiguration's list of selected MethodPackages
	 * @param actionMgr use this IActionManager to change the MethodConfiguration, if null will change the MethodConfiguration directly.
	 * @param config
	 * @param value
	 * @return false if actionManager or list manipulation fails, true otherwise
	 */
	public static boolean removeCollFromMethodPackageList(IActionManager actionMgr, MethodConfiguration config, Collection value) {
		return actionOnCollection(actionMgr, config, value, IActionManager.REMOVE_MANY, UmaPackage.eINSTANCE
				.getMethodConfiguration_MethodPackageSelection(), config.getMethodPackageSelection());
	}
	
	/**
	 * Removes the given MethodPackage from the MethodConfiguration's list of selected MethodPackages
	 * @param actionMgr use this IActionManager to change the MethodConfiguration, if null will change the MethodConfiguration directly.
	 * @param config
	 * @param value
	 * @return false if actionManager or list manipulation fails, true otherwise
	 */
	public static boolean removeFromMethodPackageList(IActionManager actionMgr, MethodConfiguration config, MethodPackage value) {
		return actionOnItem(actionMgr, config, value, IActionManager.REMOVE, UmaPackage.eINSTANCE
				.getMethodConfiguration_MethodPackageSelection(), config.getMethodPackageSelection());
	}


	/**
	 * Adds the given collection to the MethodConfiguration's list of selected added-categories
	 * @param actionMgr use this IActionManager to change the MethodConfiguration, if null will change the MethodConfiguration directly.
	 * @param config
	 * @param value
	 * @return false if actionManager or list manipulation fails, true otherwise
	 */
	public static boolean addCollToAddedCategoryList(IActionManager actionMgr, MethodConfiguration config, Collection value) {
		return actionOnCollection(actionMgr, config, value, IActionManager.ADD_MANY, UmaPackage.eINSTANCE
				.getMethodConfiguration_AddedCategory(), config.getAddedCategory());
	}
	
	/**
	 * Adds the given ContentCategory to the MethodConfiguration's list of selected added-categories
	 * @param actionMgr use this IActionManager to change the MethodConfiguration, if null will change the MethodConfiguration directly.
	 * @param config
	 * @param value
	 * @return false if actionManager or list manipulation fails, true otherwise
	 */
	public static boolean addToAddedCategoryList(IActionManager actionMgr, MethodConfiguration config, ContentCategory value) {
		return actionOnItem(actionMgr, config, value, IActionManager.ADD, UmaPackage.eINSTANCE
				.getMethodConfiguration_AddedCategory(), config.getAddedCategory());
	}

	/**
	 * Removes the given collection from the MethodConfiguration's list of selected added-categories
	 * @param actionMgr use this IActionManager to change the MethodConfiguration, if null will change the MethodConfiguration directly.
	 * @param config
	 * @param value
	 * @return false if actionManager or list manipulation fails, true otherwise
	 */
	public static boolean removeCollFromAddedCategoryList(IActionManager actionMgr, MethodConfiguration config, Collection value) {
		return actionOnCollection(actionMgr, config, value, IActionManager.REMOVE_MANY, UmaPackage.eINSTANCE
				.getMethodConfiguration_AddedCategory(), config.getAddedCategory());
	}

	/**
	 * Removes the given ContentCategory from the MethodConfiguration's list of selected added-categories
	 * @param actionMgr use this IActionManager to change the MethodConfiguration, if null will change the MethodConfiguration directly.
	 * @param config
	 * @param value
	 * @return false if actionManager or list manipulation fails, true otherwise
	 */
	public static boolean removeFromAddedCategoryList(IActionManager actionMgr, MethodConfiguration config, ContentCategory value) {
		return actionOnItem(actionMgr, config, value, IActionManager.REMOVE, UmaPackage.eINSTANCE
				.getMethodConfiguration_AddedCategory(), config.getAddedCategory());
	}

	/**
	 * Adds the given collection to the MethodConfiguration's list of selected subtracted-categories
	 * @param actionMgr use this IActionManager to change the MethodConfiguration, if null will change the MethodConfiguration directly.
	 * @param config
	 * @param value
	 * @return false if actionManager or list manipulation fails, true otherwise
	 */
	public static boolean addCollToSubtractedCategoryList(IActionManager actionMgr, MethodConfiguration config, Collection value) {
		return actionOnCollection(actionMgr, config, value, IActionManager.ADD_MANY, UmaPackage.eINSTANCE
				.getMethodConfiguration_SubtractedCategory(), config.getSubtractedCategory());
	}
	
	/**
	 * Adds the given ContentCategory to the MethodConfiguration's list of selected subtracted-categories
	 * @param actionMgr use this IActionManager to change the MethodConfiguration, if null will change the MethodConfiguration directly.
	 * @param config
	 * @param value
	 * @return false if actionManager or list manipulation fails, true otherwise
	 */
	public static boolean addToSubtractedCategoryList(IActionManager actionMgr, MethodConfiguration config, ContentCategory value) {
		return actionOnItem(actionMgr, config, value, IActionManager.ADD, UmaPackage.eINSTANCE
				.getMethodConfiguration_SubtractedCategory(), config.getSubtractedCategory());
	}

	/**
	 * Removes the given collection from the MethodConfiguration's list of selected subtracted-categories
	 * @param actionMgr use this IActionManager to change the MethodConfiguration, if null will change the MethodConfiguration directly.
	 * @param config
	 * @param value
	 * @return false if actionManager or list manipulation fails, true otherwise
	 */
	public static boolean removeCollFromSubtractedCategoryList(IActionManager actionMgr, MethodConfiguration config, Collection value) {
		return actionOnCollection(actionMgr, config, value, IActionManager.REMOVE_MANY, UmaPackage.eINSTANCE
				.getMethodConfiguration_SubtractedCategory(), config.getSubtractedCategory());
	}
	
	/**
	 * Removes the given ContentCategory from the MethodConfiguration's list of selected subtracted-categories
	 * @param actionMgr use this IActionManager to change the MethodConfiguration, if null will change the MethodConfiguration directly.
	 * @param config
	 * @param value
	 * @return false if actionManager or list manipulation fails, true otherwise
	 */
	public static boolean removeFromSubtractedCategoryList(IActionManager actionMgr, MethodConfiguration config, ContentCategory value) {
		return actionOnItem(actionMgr, config, value, IActionManager.REMOVE, UmaPackage.eINSTANCE
				.getMethodConfiguration_SubtractedCategory(), config.getSubtractedCategory());
	}
}
