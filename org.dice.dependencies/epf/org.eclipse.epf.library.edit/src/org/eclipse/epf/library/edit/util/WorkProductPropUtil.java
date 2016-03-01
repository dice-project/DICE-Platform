package org.eclipse.epf.library.edit.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.uma.Constraint;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.util.UmaUtil;

public class WorkProductPropUtil extends PropUtil {

	
	public static final String WORKPRODUCT_States = "Workproduct_states";		//$NON-NLS-1$
	
	private static WorkProductPropUtil workProductPropUtil = new WorkProductPropUtil();
	public static WorkProductPropUtil getWorkProductPropUtil() {
		return workProductPropUtil;
	}
	
	public static WorkProductPropUtil getWorkProductPropUtil(IActionManager actionManager) {
		return new WorkProductPropUtil(actionManager);
	}
		
	protected WorkProductPropUtil() {		
	}
	
	protected WorkProductPropUtil(IActionManager actionManager) {
		super(actionManager);
	}
	
	/**
	 * Get all the states assigned to the given work-product "wp" and all its variability relatives
	 * @param wp
	 * @return a set of all states
	 */
	public Set<Constraint> getAllStates(WorkProduct wp) {
		Set<WorkProduct> wpSet = (Set<WorkProduct>) LibraryEditUtil.getInstance()
				.collectVariabilityRelatives(wp);

		Set<Constraint> stateSet = new HashSet<Constraint>();

		for (WorkProduct w : wpSet) {
			List<Constraint> stateList = getWorkProductStates(w);
			stateSet.addAll(stateList);
		}

		return stateSet;
	}
	
	/**
	 * Find the state under the plug-in containing the work-product "wp" with the given name "stateName".
	 * If such state cannot be found, return null if "create" is false, otherwise create a new state
	 * under the plug-in and return it.
	 * 
	 * Note that this API does not assign the state to the wp.
	 * 
	 * @param wp
	 * @param stateName
	 * @param create
	 * @return the state
	 */
	private Constraint getState(WorkProduct wp, String stateName, boolean create) {		
		return ConstraintManager.getWorkProductState(wp, stateName, create, getActionManager());
	}
	
	/**
	 * Get the name of the given state
	 * @param state
	 * @return the name
	 */
	public String getStateName(Constraint state) {
		if (state != null && state.getName().equals(ConstraintManager.Plugin_wpState)) {
			return state.getBody();
		}
		
		return null;
	}
	
	/**
	 * Get all the states assigned to the given work-product "wp".
	 * 
	 * @param wp
	 * @return the states
	 */
	public List<Constraint> getWorkProductStates(WorkProduct wp) {
		List<Constraint> list = new ArrayList<Constraint>();
		
		String propValue = getStringValue(wp, WORKPRODUCT_States);
		if (propValue == null) {
			return list;
		}
		String[] guidList = propValue.split(infoSeperator); 
		if (guidList == null || guidList.length == 0) {
			return list;
		}
		
		MethodPlugin plugin = UmaUtil.getMethodPlugin(wp);
		if (plugin == null) {
			return list;
		}
		Set<Constraint> statesInPlugin = new HashSet<Constraint>();
		List<Constraint> stateListInPlugin = MethodPluginPropUtil.getMethodPluginPropUtil().getWorkProductStatesInPlugin(plugin);
		if (stateListInPlugin.isEmpty()) {
			return list;
		}
		statesInPlugin.addAll(stateListInPlugin);
		
		boolean modified = false;
		String newValue = ""; 			//$NON-NLS-1$
		for (String guid : guidList) {
			MethodElement element = LibraryEditUtil.getInstance().getMethodElement(guid);
			if (element instanceof Constraint && statesInPlugin.contains(element)) {
				Constraint c = (Constraint) element;
				if (c.getName().equals(ConstraintManager.Plugin_wpState)) {
					list.add(c);
					MethodElementPropUtil.getMethodElementPropUtil().addToAssignedToWps(wp, c);
				}
				if (newValue.length() > 0) {
					newValue = newValue.concat(infoSeperator);
				}
				newValue = newValue.concat(c.getGuid());
			} else {
				modified = true;
			}
		}
		
		
		if (modified) {
			setStringValue(wp, WORKPRODUCT_States, newValue);
		}
				
		return list;
	}
	
	/**
	 * Assign to the work-product "wp" a state with a name given by "stateName".
	 * If such a state has been assigned before, do nothing and return.
	 * If such state is already in the "wp"s plug-in, get it and assign to "wp".
	 * If such state is not in the "wp"s plug-in, create it and assign to "wp".
	 * @param wp
	 * @param stateName
	 */
	public void addWorkProductState(WorkProduct wp, Constraint srcState) {
		Constraint tgtState = addWorkProductState_(wp, srcState);
		MethodElementPropUtil.getMethodElementPropUtil().addToAssignedToWps(wp, tgtState);
	}
	
	private Constraint addWorkProductState_(WorkProduct wp, Constraint srcState) {
		String stateName = srcState.getBody();
		
		String oldValue = getStringValue(wp, WORKPRODUCT_States);
		
		//No old state
		if (oldValue == null || oldValue.trim().length() == 0) {
			Constraint state = getState(wp, stateName, true);
			copyDescription(srcState, state);
			setStringValue(wp, WORKPRODUCT_States, state.getGuid());
			return state;
		}
		
		//Find if a state with the same name exists
		String[] guidList = oldValue.split(infoSeperator);
		for (String guid : guidList) {
			MethodElement element = LibraryEditUtil.getInstance()
					.getMethodElement(guid);
			if (element instanceof Constraint) {
				Constraint c = (Constraint) element;
				if (c.getName().equals(ConstraintManager.Plugin_wpState)
						&& c.getBody().equals(stateName)) {
					return c;
				}
			}
		}
		
		//Append the new state
		Constraint state = getState(wp, stateName, true);
		copyDescription(srcState, state);
		String newValue = oldValue.concat(infoSeperator).concat(state.getGuid());
		setStringValue(wp, WORKPRODUCT_States, newValue);
		return state;
	}

	private void copyDescription(Constraint srcState, Constraint tgtState) {
		if (tgtState == srcState) {
			return;			
		}
		tgtState.setBriefDescription(srcState.getBriefDescription());
	}
	
	/**
	 * Un-assign from "wp" the state with name given by "stateName".
	 * Do nothing if such state is not assigned to "wp".
	 * @param wp
	 * @param stateName
	 */
	public void removeWorkProductState(WorkProduct wp, String stateName) {
		Constraint state = removeWorkProductState_(wp, stateName);
		if (state != null) {
			MethodElementPropUtil.getMethodElementPropUtil().removeFromAssignedToWps(wp, state);
		}
		
	}
	private Constraint removeWorkProductState_(WorkProduct wp, String stateName) {
		String oldValue = getStringValue(wp, WORKPRODUCT_States);

		if (oldValue == null || oldValue.trim().length() == 0) {
			return null;
		}

		Constraint state = null;		
		boolean modified = false;
		String newValue = ""; 			//$NON-NLS-1$
		String[] guidList = oldValue.split(infoSeperator);
		for (String guid : guidList) {
			MethodElement element = LibraryEditUtil.getInstance()
					.getMethodElement(guid);
			if (element instanceof Constraint) {
				Constraint c = (Constraint) element;
				if (c.getName().equals(ConstraintManager.Plugin_wpState)) {
					if (c.getBody().equals(stateName)) {
						state = c;
						modified = true;
					} else {
						if (newValue.length() > 0) {
							newValue = newValue.concat(infoSeperator);
						}
						newValue = newValue.concat(c.getGuid());
					}
				}
			}
		}
		
		if (modified) {
			setStringValue(wp, WORKPRODUCT_States, newValue);
		}
		
		return state;
	}
	
	/**
	 * Fix states assigned to the given work-product "wp".
	 * The states need to be fixed are those belonging to other plug-in
	 * @param wp
	 * @param modifeiedResources
	 */
	public void fixWorkProductStates(WorkProduct wp, Set<Resource> modifeiedResources) {
		String propValue = getStringValue(wp, WORKPRODUCT_States);
		if (propValue == null) {
			return;
		}
		String[] guidList = propValue.split(infoSeperator); 
		if (guidList == null || guidList.length == 0) {
			return;
		}		
		MethodPlugin plugin = UmaUtil.getMethodPlugin(wp);
		if (plugin == null) {
			return;
		}
		Set<Constraint> statesInPlugin = new HashSet<Constraint>();
		List<Constraint> stateListInPlugin = MethodPluginPropUtil.getMethodPluginPropUtil().getWorkProductStatesInPlugin(plugin);
		statesInPlugin.addAll(stateListInPlugin);
		
		boolean modified = false;
		String newValue = ""; 			//$NON-NLS-1$
		for (String guid : guidList) {
			MethodElement element = LibraryEditUtil.getInstance().getMethodElement(guid);
			if (element instanceof Constraint) {
				Constraint c = (Constraint) element;
				if (statesInPlugin.contains(element)) {
					if (c.getName().equals(ConstraintManager.Plugin_wpState)) {
						MethodElementPropUtil.getMethodElementPropUtil().addToAssignedToWps(wp, c);
					}
					if (newValue.length() > 0) {
						newValue = newValue.concat(infoSeperator);
					}
					newValue = newValue.concat(c.getGuid());
				} else {
					Constraint copyState = MethodPluginPropUtil.getMethodPluginPropUtil().getWorkProductState(plugin, c.getBody(), false);
					if (copyState == null) {
						copyState = MethodPluginPropUtil.getMethodPluginPropUtil().getWorkProductState(plugin, c.getBody(), true);
						copyState.setBriefDescription(c.getBriefDescription());
					}
					if (newValue.length() > 0) {
						newValue = newValue.concat(infoSeperator);
					}
					newValue = newValue.concat(copyState.getGuid());
					modified = true;
				}
			} else {
				modified = true;
			}
		}
		
		
		if (modified) {
			setStringValue(wp, WORKPRODUCT_States, newValue);
			if (wp.eResource() != null) {
				modifeiedResources.add(wp.eResource());
			}
			if (plugin.eResource() != null) {
				modifeiedResources.add(plugin.eResource());	
			}
		}
	}
		
	/**
	 * Fix states assigned to WPDs of a task-descriptor or milestone
	 * A state need to be fixed is one that can not be found in the wpd's linked work-product 
	 * @param element - a task-descriptor or milestone
	 * @param modifeiedResources
	 */
	public static void fixProcessWpStates(Object element, Set<Resource> modifeiedResources) {
		if (! (element instanceof TaskDescriptor || element instanceof Milestone)) {
			return;
		}
		WorkBreakdownElement wbe = (WorkBreakdownElement) element;
		for (Constraint constraint : wbe.getOwnedRules()) {
			if (constraint.getName().equals(ConstraintManager.Wbe_WpStates)) {
				MethodElement e = LibraryEditUtil.getInstance().getMethodElement(constraint.getBody());
				if (e instanceof WorkProductDescriptor) {
					fixProcessWorkProductStates(constraint, (WorkProductDescriptor) e, modifeiedResources);
				}
			}
		}

	}
	
	private static void fixProcessWorkProductStates(Constraint propertyOwner, WorkProductDescriptor wpd, Set<Resource> modifeiedResources) {
		if (propertyOwner == null || wpd == null || wpd.getWorkProduct() == null) {
			return;
		}
		WorkProduct wp = wpd.getWorkProduct();				
		String propName = MethodElementPropUtil.CONSTRAINT_WPStates;
		
		
		MethodElementPropUtil methodElementPropUtil = MethodElementPropUtil.getMethodElementPropUtil();
		String value = methodElementPropUtil.getStringValue(propertyOwner, propName);
		if (value == null || value.length() == 0) {
			return ;
		}
		
		String[] infos = value.split(infoSeperator);
		if (infos == null || infos.length == 0) {
			return;
		}
		
		MethodPlugin plugin = UmaUtil.getMethodPlugin(wp);
		if (plugin == null) {
			return;
		}
		
		boolean modified = false;
		String newValue = ""; //$NON-NLS-1$
		int sz = infos.length / 2; 		
		for (int i = 0; i < sz; i++) {
			int i1 = i*2;
			int i2 = i1 + 1;
			String iGuid = infos[i1];
			String iRefName = infos[i2];			
			MethodElement element = LibraryEditUtil.getInstance()
					.getMethodElement(iGuid);
			if (element instanceof Constraint) {
				Constraint c = (Constraint) element;
				Constraint copyState = MethodPluginPropUtil.getMethodPluginPropUtil().getWorkProductState(plugin, c.getBody(), false);
				if (copyState != null) {
					if (copyState != c) {
						iGuid = copyState.getGuid();
						modified = true;
					}					
					if (newValue.length() > 0) {
						newValue = newValue.concat(infoSeperator);
					}
					newValue = newValue.concat(iGuid.concat(infoSeperator)
							.concat(iRefName));
				} else {
					modified = true;		
				}
			} else {
				modified = true;
			}
		} 	
		if (modified) {
			methodElementPropUtil.setStringValue(propertyOwner, propName, newValue);
			if (propertyOwner.eResource() != null) {
				modifeiedResources.add(propertyOwner.eResource());
			}
		}
		

	}
	
}
