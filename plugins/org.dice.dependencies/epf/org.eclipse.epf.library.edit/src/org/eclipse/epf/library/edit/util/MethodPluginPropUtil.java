package org.eclipse.epf.library.edit.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.uma.MethodElementExt;
import org.eclipse.epf.library.edit.uma.MethodPluginExt;
import org.eclipse.epf.uma.Constraint;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.util.UmaUtil;


public class MethodPluginPropUtil extends PropUtil {

	public static final String PLUGIN_SynFree = "plugin_synFree"; //$NON-NLS-1$
	public static final String Plugin_customizedConfig = 	"plugin_customizedConfig";					//$NON-NLS-1$
	public static final String Plugin_customizedParent = 	"plugin_customizedParent";					//$NON-NLS-1$
	public static final String Plugin_embeddedConfigXmiString = "plugin_embeddedConfigXmiString";		//$NON-NLS-1$

	private static MethodPluginPropUtil methodPluginPropUtil = new MethodPluginPropUtil();
	public static MethodPluginPropUtil getMethodPluginPropUtil() {
		return methodPluginPropUtil;
	}
	
	public static MethodPluginPropUtil getMethodPluginPropUtil(IActionManager actionManager) {
		return new MethodPluginPropUtil(actionManager);
	}
	
	protected MethodPluginPropUtil() {		
	}
	
	protected MethodPluginPropUtil(IActionManager actionManager) {
		super(actionManager);
	}	
		
	public void saveEmbeddedConfig(MethodPlugin plugin, MethodConfiguration config) {
		String xmiString = LibraryEditUtil.getInstance().getXmiString(config);
		setStringValue(plugin, Plugin_embeddedConfigXmiString, xmiString);
		MethodPluginExt ext = getMethodPluginExt(plugin, true);
		ext.setEmbeddedConfig(config);
	}
	
	private MethodPluginExt getMethodPluginExt(MethodPlugin plugin, boolean create) {
		MethodElementExt extObj = getExtendObject(plugin, create);
		if (! (extObj instanceof MethodPluginExt)) {
			LibraryEditPlugin.getDefault().getLogger().logError("saveEmbeddedConfig, extObj: " + extObj);	//$NON-NLS-1$
			return null;
		}
		return (MethodPluginExt) extObj;
	}
	
	public MethodConfiguration getEmbeddedConfig(MethodPlugin plugin) {
		MethodPluginExt ext = getMethodPluginExt(plugin, true);
		if (ext.isEmbeddedConfigLoaded()) {
			return ext.getEmbeddedConfig();
		}		
		ext.setEmbeddedConfigLoaded(true);
		MethodConfiguration config = null;
		
		String xmiString = getStringValue(plugin, Plugin_embeddedConfigXmiString);		
		EObject obj = LibraryEditUtil.getInstance().loadObject(xmiString);
		if (obj instanceof MethodConfiguration) {
			config = (MethodConfiguration) obj;
		}
		ext.setEmbeddedConfig(config);
		return config;
	}
	
	public boolean isSynFree(MethodPlugin d) {
		Boolean value = getBooleanValue(d, PLUGIN_SynFree);
		return value == null ? false : value.booleanValue();
	}
	
	public void setSynFree(MethodPlugin d, boolean value) {
		setBooleanValue(d, PLUGIN_SynFree, value);
	}
	
	public MethodPlugin getCustomizedParent(MethodPlugin plugin) {
		MethodElement element = getElement(plugin, Plugin_customizedParent);
		return element instanceof MethodPlugin ? (MethodPlugin) element : null;
	}
	
	public MethodConfiguration getCustomizedConfig(MethodPlugin plugin) {
		MethodElement element = getElement(plugin, Plugin_customizedConfig);
		return element instanceof MethodConfiguration ? (MethodConfiguration) element : null;
	}
	
	public boolean isCustomizePlugin(MethodPlugin plugin) {
		return getCustomizedConfig(plugin) != null;
	}
	
	/**
	 * Find the state under the plug-in with the given name "stateName".
	 * If such state cannot be found, return null if "create" is false, otherwise create a new state
	 * under the plug-in and return it.
	 * 
	 * @param plugin
	 * @param stateName
	 * @param create
	 * @return the state
	 */
	public Constraint getWorkProductState(MethodPlugin plugin, String stateName, boolean create) {		
		return ConstraintManager.getWorkProductState(plugin, stateName, create, getActionManager());
	}
	
	/**
	 * Get all states created under the given "plugin".
	 * @param plugin
	 * @return the states
	 */
	public List<Constraint> getWorkProductStatesInPlugin(MethodPlugin plugin) {
		List<Constraint> list = new ArrayList<Constraint>();
		
		for (Iterator iter = plugin.getOwnedRules().iterator(); iter.hasNext();) {
			Constraint state = (Constraint) iter.next();
			if (state.getName().equals(ConstraintManager.Plugin_wpState)) {
				list.add(state);
			}
		}	
		
		return list;
	}
	
	/**
	 * Remove from the "plugin" a state with the given name "stateName".
	 * 
	 * @param plugin
	 * @param stateName
	 * @return
	 */
	public Constraint removeWorkProductState(MethodPlugin plugin,
			String stateName) {
		for (Iterator iter = plugin.getOwnedRules().iterator(); iter.hasNext();) {
			Constraint state = (Constraint) iter.next();
			if (state.getName().equals(ConstraintManager.Plugin_wpState) && state.getBody().equals(stateName)) {

				if (getActionManager() == null) {
					plugin.getOwnedRules().remove(state);
				}
				getActionManager().doAction(IActionManager.REMOVE, plugin,
						UmaPackage.eINSTANCE.getMethodElement_OwnedRules(),
						state, -1);
				return state;
			}
		}

		return null;
	}
	
	/**
	 * Get all states under the given "activePlugin"s library.
	 * If many states have a same name, only one of them will be returned in the list.
	 * If one of these same name states is in the "activePlugin", the method will make sure
	 * it is the one which gets returned in the list.
	 * 
	 * @param activePlugin
	 * @return
	 */
	public List<Constraint> getWorkProductStatesInLibrary(MethodPlugin activePlugin) {
		List<Constraint> resultList = getWorkProductStatesInPlugin(activePlugin);
		Set<String> stateIDs = new HashSet<String>();
		for (Constraint state : resultList) {
			stateIDs.add(getStateId(state));
		}
		MethodLibrary lib = UmaUtil.getMethodLibrary(activePlugin);
		if (lib == null) {
			return resultList;
		}
		for (MethodPlugin plugin : lib.getMethodPlugins()) {
			List<Constraint> list = getWorkProductStatesInPlugin(plugin);
			for (Constraint state : list) {
				String id = getStateId(state);
				if (! stateIDs.contains(id)) {
					stateIDs.add(id);
					resultList.add(state);
				}
			}
		}
		
		return resultList;
	}
	
	private String getStateId(Constraint state) {
		return state.getBody() + ", " + state.getBriefDescription();  //$NON-NLS-1$
	}
	
	private boolean isWpStatesLoaded(MethodPlugin plugin) {
		MethodPluginExt ext = (MethodPluginExt) getExtendObject(plugin, true);
		return ext.isWpStatesLoaded();
	}
	
	private void setWpStatesLoaded(MethodPlugin plugin, boolean wpStatesLoaded) {
		MethodPluginExt ext = (MethodPluginExt) getExtendObject(plugin, true);
		ext.setWpStatesLoaded(wpStatesLoaded);
	}
	
	public void loadWpStates(MethodPlugin plugin) {
		if (isWpStatesLoaded(plugin)) {
			return;
		}
		WorkProductPropUtil wpPropUtil = WorkProductPropUtil.getWorkProductPropUtil();
		
		for (WorkProduct wp : LibraryEditUtil.getInstance().getAllWorkProducts(plugin)) {
			wpPropUtil.getWorkProductStates(wp);
		}
		setWpStatesLoaded(plugin, true);
	}
	
}