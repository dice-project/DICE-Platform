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
package org.eclipse.epf.library.edit.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.uma.Constraint;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.util.UmaUtil;


/**
 * Helper class that manages the use of Constraint in library to store
 * implementation-specific data
 * 
 * @author Phong Nguyen Le - Dec 1, 2005
 * @author Weiping Lu, Aripl 20, 2010
 * @since 1.0
 */
public final class ConstraintManager {
	
	/** Constants for constraint name */
	public static final String ACITIVY_DIAGRAM = "diagram"; //$NON-NLS-1$

	public static final String PROCESS_SUPPRESSION = ""; //$NON-NLS-1$
	
	//Owner: work-product, body: state name
	public static final String Plugin_wpState = "wpState"; //$NON-NLS-1$
	
	//Owner: wbe, body: guid of work-product descriptor
	public static final String Wbe_WpStates = "wpStates"; //$NON-NLS-1$	

	public static final Constraint getConstraint(MethodElement e,
			String constraintName, boolean create) {
		if (constraintName == null) {
			return null;
		}
		Constraint constraint = null;
		for (Iterator iter = e.getOwnedRules().iterator(); iter.hasNext();) {
			Constraint c = (Constraint) iter.next();
			if (c.getName().equals(constraintName)) {
				constraint = c;
				break;
			}
		}
		if (constraint == null && create) {
			constraint = UmaFactory.eINSTANCE.createConstraint();
			constraint.setName(constraintName);
			e.getOwnedRules().add(constraint);
		}
		return constraint;
	}
	
	public static final Constraint getWorkProductState(WorkProduct wp,
			String stateName, boolean create, IActionManager actionManager) {
		if (wp == null || stateName == null || stateName.trim().length() == 0) {
			return null;
		}
		
		WorkProductPropUtil propUtil = WorkProductPropUtil.getWorkProductPropUtil();
		List<Constraint> states = propUtil.getWorkProductStates(wp);
		for (Constraint state : states) {
			if (state.getBody().equals(stateName)) {
				return state;
			}
		}		
		
		MethodPlugin plugin = UmaUtil.getMethodPlugin(wp);
		if (plugin == null) {
			return null;
		}

		
		return getWorkProductState(plugin, stateName, create, actionManager);
	}

	public static Constraint getWorkProductState(MethodPlugin plugin, String stateName, boolean create,
			IActionManager actionManager) {
		MethodPluginPropUtil pluginPropUtil = MethodPluginPropUtil.getMethodPluginPropUtil(actionManager);

		for (Constraint state : pluginPropUtil.getWorkProductStatesInPlugin(plugin)) {
			if (state.getBody().equals(stateName)) {
				return state;
			}
		}
		
		if (create) {
			return createConstraint(plugin, Plugin_wpState, stateName, actionManager);
		}

		return null;
	}

	private static Constraint createConstraint(MethodElement owner, String name,
			String body, IActionManager actionManager) {
		Constraint constraint = UmaFactory.eINSTANCE.createConstraint();
		if (actionManager == null) {
			constraint.setName(name);
			constraint.setBody(body);
			owner.getOwnedRules().add(constraint);
		} else {
			UmaPackage up = UmaPackage.eINSTANCE;
			actionManager.doAction(IActionManager.SET, constraint, up
					.getNamedElement_Name(), name, -1);
			actionManager.doAction(IActionManager.SET, constraint, up
					.getConstraint_Body(), body, -1);
			actionManager.doAction(IActionManager.ADD, owner, up
					.getMethodElement_OwnedRules(), constraint, -1);
		}
		return constraint;
	}

	public static void addWpState(WorkBreakdownElement wbe, WorkProductDescriptor wpd,
			Constraint state, EReference ref, IActionManager actionManager) {
		if (wbe == null || wpd == null || state == null || ref == null) {
			return;
		}

		// Find the wp states holder constraint object for wpd
		Constraint wpStatesHolder = null;
		for (Constraint constraint : wbe.getOwnedRules()) {
			if (constraint.getName().equals(Wbe_WpStates)
					&& constraint.getBody().equals(wpd.getGuid())) {
				wpStatesHolder = constraint;
				break;
			}
		}
		if (wpStatesHolder == null) {
			wpStatesHolder = createConstraint(wbe, Wbe_WpStates, wpd
					.getGuid(), actionManager);
		}

		MethodElementPropUtil propUtil = new MethodElementPropUtil(actionManager);
		propUtil.addReferenceInfo(wpStatesHolder, state,
				MethodElementPropUtil.CONSTRAINT_WPStates, ref.getName());

	}

	public static void removeWpState(WorkBreakdownElement wbe,
			WorkProductDescriptor wpd, Constraint state, EReference ref,
			IActionManager actionManager) {
		if (wbe == null || wpd == null || state == null || ref == null) {
			return;
		}

		// Find the wp states holder constraint object for wpd
		Constraint wpStatesHolder = null;
		for (Constraint constraint : wbe.getOwnedRules()) {
			if (constraint.getName().equals(Wbe_WpStates)
					&& constraint.getBody().equals(wpd.getGuid())) {
				wpStatesHolder = constraint;
				break;
			}
		}
		if (wpStatesHolder == null) {
			return;
		}

		MethodElementPropUtil propUtil = new MethodElementPropUtil(actionManager);
		propUtil.removeReferenceInfo(wpStatesHolder, state,
				MethodElementPropUtil.CONSTRAINT_WPStates, ref.getName());

	}

	public static List<Constraint> getWpStates(WorkBreakdownElement wbe,
			WorkProductDescriptor wpd, EReference ref) {

		WorkProduct wp = wpd.getWorkProduct();
		if (wp == null) {
			return new ArrayList<Constraint>();
		}

		Constraint wpStatesHolder = null;
		for (Constraint constraint : wbe.getOwnedRules()) {
			if (constraint.getName().equals(Wbe_WpStates)
					&& constraint.getBody().equals(wpd.getGuid())) {
				wpStatesHolder = constraint;
				break;
			}
		}

		if (wpStatesHolder == null) {
			return new ArrayList<Constraint>();
		}

		MethodElementPropUtil propUtil = MethodElementPropUtil
				.getMethodElementPropUtil();

		return (List<Constraint>) propUtil.extractElements(wpStatesHolder,
				MethodElementPropUtil.CONSTRAINT_WPStates, ref.getName(),
				WorkProductPropUtil.getWorkProductPropUtil().getAllStates(wp));

	}
	
	public static List<Constraint> getWpStates(WorkBreakdownElement wbe) {
		List<Constraint> list = new ArrayList<Constraint>();		
		for (Constraint constraint : wbe.getOwnedRules()) {
			if (constraint.getName().equals(Wbe_WpStates)) {
				list.add(constraint);
			}
		}
		
		return list;
	}

}
